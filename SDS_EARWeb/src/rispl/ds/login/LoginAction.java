package rispl.ds.login;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.EmployeeSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.DSAction.SESSION;
import rispl.ds.context.DKartContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.retailsols.dkart.login.BaseKeyStoreEncryptionService;

public class LoginAction extends DSAction {

	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = LogManager.getLogger(LoginAction.class);
	
	public String loginid, password;
	public EmployeeIfc employee;
	
	public boolean managerOverride;
	private boolean validCredentials;
	private boolean validCreditLimit;
	private String newCreditLimit;
	private OrderTranHeader orderTran;
	
	@Override
	public void validate() {
		// If client side validation fails, fallback to server side validation
		if (loginid != null && password != null) {
			if (loginid.length() == 0) {
				addActionError("Login ID cannot be empty");
			}
			if (password.length() == 0) {
				addActionError("Password cannot be empty");
			}
		}
	}

	public String execute() throws Exception {
		try {
			LookUpEmployeeIfc empRemote = DKartContext.getLookupEmployee();
			EmployeeSearchCriteriaIfc employeeSearchCriteria = new EmployeeSearchCriteria();
			employeeSearchCriteria.setLoginId(loginid);
			employeeSearchCriteria.setPassword(password);
			employee = empRemote.validateEmployee(employeeSearchCriteria);
		} catch (Exception e) {
			// TODO remove after testing
			e.printStackTrace();
			addActionError("Invalid Credentials. Please enter correct login details.");
			LOGGER.error("Invalid Credentials. Please enter correct login details.");
			return ERROR;
		}
		
		if (employee == null) {
			addActionError("Invalid Credentials. Please enter correct login details.");
			LOGGER.error("Invalid Credentials. Please enter correct login details.");
			return ERROR;
		}
		else 
		{
			if(!employee.getEmployeeStatusCode().trim().equalsIgnoreCase("1"))
			{
				LOGGER.error("Employee status is In-Active");
				addActionError("Employee status is In-Active. Please contact Administrator.");
				return ERROR;
			}
		}
		
		//SHIRO LOGIN
		{
			String dbsalt = employee.getSaltPassword();
			String enc_password = "";

			try {
				byte[] encBytes = new BaseKeyStoreEncryptionService().superHash(password.getBytes(), dbsalt, false);
				enc_password = new BaseKeyStoreEncryptionService().getBase64encode(encBytes);

				UsernamePasswordToken token = new UsernamePasswordToken(loginid, enc_password);
				//token.setRememberMe(true);
				LOGGER.info("Logging in Employee into SHIRO");
				SecurityUtils.getSubject().login(token);

				Subject sub = SecurityUtils.getSubject();
				LOGGER.debug(sub.toString());
			} catch (Exception e) {
				LOGGER.error("Shiro Exception during login", e);
				addActionError("Exception during login");
				return ERROR;
			}

		}
		// Remove employee password before storing into session
		employee.setAccessPassword("");
		employee.setSaltPassword("");
		
		LOGGER.info("Loading Employee into session");
		putInSession(SESSION.EMPLOYEE, employee);
		
		LOGGER.info("Successful login");
		
		// If new password required to override temp password
		if(employee.isNewPasswordRequired())
		{
			return "change";
		}
		
		return SUCCESS;

	}

	public String logout() {

		if (getFromSession(SESSION.EMPLOYEE) == null)
			addActionError("Session timed out. Please Login again.");

		LOGGER.info("Clearing session information");
		invalidateSession();

		if (SecurityUtils.getSubject() != null && SecurityUtils.getSubject().isAuthenticated())
			SecurityUtils.getSubject().logout();
		LOGGER.info("Successfully logged out");
		// Set error code status for implementing logout in Ajax Calls
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		return SUCCESS;
	}
	// laxmikanth: authentication while manager override
	public String managerOverride(){
		managerOverride = false;
		setValidCredentials(false);
		try {
			LookUpEmployeeIfc remoteBean = DKartContext.getLookupEmployee();
			EmployeeSearchCriteriaIfc employeeSearchCriteria = new EmployeeSearchCriteria();
			employeeSearchCriteria.setLoginId(loginid);
			employeeSearchCriteria.setPassword(password);
			setPassword(null);
			employee = remoteBean.validateEmployee(employeeSearchCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Invalid Credentials. Please enter correct login details.");
		}
		if(employee != null){
			setValidCredentials(true);
			if(managerOverrideAllowed(employee)){
				setManagerOverride(true);
				LOGGER.info("manager override done successfully with EMPID : " +loginid);
			}
		}
		return SUCCESS;
	}
	
	public String overrideCreditLimit() {
		managerOverride = false;
		setValidCredentials(false);
		try {
			LookUpEmployeeIfc remoteBean = DKartContext.getLookupEmployee();
			EmployeeSearchCriteriaIfc employeeSearchCriteria = new EmployeeSearchCriteria();
			employeeSearchCriteria.setLoginId(loginid);
			employeeSearchCriteria.setPassword(password);
			setPassword(null);
			employee = remoteBean.validateEmployee(employeeSearchCriteria);

		} catch (Exception e) {
			// TODO remove after testing
			e.printStackTrace();
			addActionError("Invalid Credentials. Please enter correct login details.");
		}

		if (employee != null) {
			setValidCredentials(true);
			if (managerOverrideAllowed(employee)) {
				setManagerOverride(true);
				
				int order=0;
				setOrderTran((OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION));
				if(getOrderTran()==null){
					setOrderTran((OrderTranHeader) getFromSession(SESSION.QUOTE_DETAILS));
					order =1;
				}
				BigDecimal orgTranBal = BigDecimal.ZERO;
				
				if(getFromSession(SESSION.ORDER_TRAN_TOT)!=null){
					orgTranBal = (BigDecimal)getFromSession(SESSION.ORDER_TRAN_TOT);
				}else{
					orgTranBal = orderTran.getOrdTranSum().getDkartNetTot();
				}
				
				try {
				
					// Used during new order
					if (orderTran.getTransCrdtLimit() != null) {
						long transCreditLimit = orderTran.getTransCrdtLimit().longValue();

						BigDecimal newCreditLimitBigDec = new BigDecimal(newCreditLimit);
						/*if (newCreditLimitBigDec.longValue() > transCreditLimit &&
								newCreditLimitBigDec.compareTo(orderTran.getOrdTranSum().getDkartNetTot())>=0 ) {*/
						if (newCreditLimitBigDec.longValue() > transCreditLimit &&
								newCreditLimitBigDec.compareTo(orgTranBal)>=0 && newCreditLimitBigDec.compareTo(orderTran.getCustomer().getMaxCrdtLmtOvrrdAllwd())!=1) {
							setValidCreditLimit(true);
							orderTran.setTransCrdtLimit(newCreditLimitBigDec);
							orderTran.setCreditLimitOverride(newCreditLimitBigDec);
							orderTran.setCreditLimitOverridenBy(employee.getEmployeeId());
							/*if(orgTranBal.compareTo(BigDecimal.ZERO)==0){
								orderTran.getCustomer().getCustomerLimits().setAvCrdtLimit(newCreditLimitBigDec);
							}*/
							if(order==0)
								putInSession(SESSION.ORDER_TRANSACTION, orderTran);
							else
								putInSession(SESSION.QUOTE_DETAILS, orderTran);

						}else{
							setValidCreditLimit(false);
						}
					}
					// Used during retrieving pending orders
					else if (orderTran.getCustomer().getCustomerLimits().getAvCrdtLimit() != null) {
						long transCreditLimit = orderTran.getCustomer().getCustomerLimits().getAvCrdtLimit().longValue();

						BigDecimal newCreditLimitBigDec = new BigDecimal(newCreditLimit);
						if (newCreditLimitBigDec.longValue() > transCreditLimit &&
								newCreditLimitBigDec.compareTo(orderTran.getOrdTranSum().getDkartNetTot())>=0 ) {
							setValidCreditLimit(true);
							orderTran.setTransCrdtLimit(newCreditLimitBigDec);
							orderTran.setCreditLimitOverride(newCreditLimitBigDec);
							orderTran.setCreditLimitOverridenBy(employee.getEmployeeId());
							//orderTran.getCustomer().getCustomerLimits().setAvCrdtLimit(new BigDecimal(newCreditLimit));

							if(order==0)
								putInSession(SESSION.ORDER_TRANSACTION, orderTran);
							else
								putInSession(SESSION.QUOTE_DETAILS, orderTran);
						}
					}
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
			
		}
		return SUCCESS;
	}

	private boolean managerOverrideAllowed(EmployeeIfc employee) {
		//FIXME update this based on access function
		long roleId = employee.getRoleId();
		if(roleId == 2 || roleId == 1 || roleId == 3)
			return true;
		return false;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public boolean isManagerOverride() {
		return managerOverride;
	}

	public void setManagerOverride(boolean managerOverride) {
		this.managerOverride = managerOverride;
	}

	public boolean isValidCreditLimit() {
		return validCreditLimit;
	}

	public void setValidCreditLimit(boolean validCreditLimit) {
		this.validCreditLimit = validCreditLimit;
	}

	public boolean isValidCredentials() {
		return validCredentials;
	}

	public void setValidCredentials(boolean validCredentials) {
		this.validCredentials = validCredentials;
	}

	public String getNewCreditLimit() {
		return newCreditLimit;
	}

	public void setNewCreditLimit(String newCreditLimit) {
		this.newCreditLimit = newCreditLimit;
	}

	public OrderTranHeader getOrderTran() {
		return orderTran;
	}

	public void setOrderTran(OrderTranHeader orderTran) {
		this.orderTran = orderTran;
	}

}