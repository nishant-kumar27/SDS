package rispl.dkart.services.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.retailsols.dkart.login.BaseKeyStoreEncryptionService;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;

import rispl.db.model.employee.EmployeeV;
import rispl.db.model.employee.RisplDkEmpMerchAssoc;
import rispl.db.model.employee.RisplDkEmpMstr;
import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkservices.common.EmployeeSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;
import rispl.services.Employee.AbstractEmployeeService;
import rispl.services.Employee.EmployeeFindException;

@Stateless(mappedName="lookUpEmployee")
@LocalBean
public class LookUpEmployee extends AbstractEmployeeService implements LookUpEmployeeIfc{

	//static Logger logger=Logger.getLogger(LookUpEmployee.class);
	private static final Logger LOGGER = LogManager.getLogger(LookUpEmployee.class);
	
	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	
	@EJB
	ParameterConfigurationServiceIfc params;
	
	@Override
	public EmployeeIfc validateEmployee(EmployeeSearchCriteriaIfc criteria) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EmployeeIfc employee=null;
		
		LOGGER.info("looking Up with mappedName=lookUpEmployee");
			try {
				employee=lookupEmployeeLogIn(criteria);
				String id=employee.getEmployeeId();
				String dbpassword=employee.getAccessPassword();
				String dbsalt=employee.getSaltPassword();
				byte[] encBytes;
				try {
					encBytes = new BaseKeyStoreEncryptionService().superHash(criteria.getPassword().getBytes(),dbsalt, false);
					String enc_password=new BaseKeyStoreEncryptionService().getBase64encode(encBytes);
					if(dbpassword.equals(enc_password)){
						LOGGER.info("Employee found with valid credentials");
						return employee;
					}
					else{
						LOGGER.info("Employee found invalid credentials");
						return null;
					}
			} catch (Exception e) {
				LOGGER.error("Exception occured when looking up for employee " + e.getMessage());
				return null;
			}
				
				
			} catch (EmployeeFindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.error("Exception occured when looking up for employee " + e.getMessage());
			}
		return employee;
	}
	
	@Override
	public EmployeeIfc lookupEmployeeByLoginId(String loginId){
		em = emf.createEntityManager();
		EmployeeIfc employee=null;
		try {
			EmployeeSearchCriteriaIfc criteria = new EmployeeSearchCriteria();
			criteria.setLoginId(loginId);
			employee=lookupEmployeeLogIn(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("Exception occured when looking up for employee " + e.getMessage());
		}
		
		return employee;
	}
	
	@Override
	public EmployeeIfc lookupEmployeeByLoginIdOrEmpId(String empInfo){
		em = emf.createEntityManager();
		EmployeeIfc employee=null;
		try {
			EmployeeSearchCriteriaIfc criteria = new EmployeeSearchCriteria();
			criteria.setLoginId(empInfo);
			criteria.setEmployeeId(empInfo);
			LOGGER.info("::looking up for employee:: ");
			employee=lookupEmployeeLogInOrEmpId(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("Exception occured when looking up for employee " + e.getMessage());
		}
		
		return employee;
	}
	
	@Override
	public EmployeeIfc[] lookupSalesAgent(EmployeeSearchCriteriaIfc criteria) {
		em = emf.createEntityManager();
		EmployeeIfc[] employee=null;
		try {
			String saleAgentRoleId = params.fetchXMLParameterValues().getSalesAgentRoleID();
			criteria.setRoleId(Long.parseLong(saleAgentRoleId));
			LOGGER.error("Looking up for sales agent with given criteria.......");
			employee=lookupEmployeeByRoleId(criteria);
		}
		catch(Exception e){
			LOGGER.error("Exception occured when looking up for sale agent " + e.getMessage());
		}
		return employee;
	}

	@Override
	//for getting the names of the sales agent @mallikarjun
	public ArrayList<String> getSalesAgentsList(String[] emids) {
		em = emf.createEntityManager();
		ArrayList<String> salesAgnts = null;
		try {
			salesAgnts = retrivSalesAgents(emids);
		} catch (EmployeeFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salesAgnts;
	}

	@Override
	public EmployeeIfc lookupSalesAgentForOrder(String orderID) throws EmployeeFindException {
		EmployeeIfc salesAgent = null;
		em = emf.createEntityManager();
		//find sales agent id from order id
		LOGGER.info("lookupSalesAgentForOrder:::::IN SALE AGENT LOOKUP");
		//System.out.println("IN SALE AGENT LOOKUP::::::");
		TypedQuery<String> query = em.createNamedQuery("FIND_SALES_AGENT_FOR_ORDER", String.class);
		query.setParameter("orderID", orderID);
		List<String> salesAgentString = query.getResultList();
		if (salesAgentString != null && salesAgentString.size() > 0) {
			String salesAgentId = salesAgentString.get(0);

			EmployeeSearchCriteriaIfc criteria = new EmployeeSearchCriteria();
			criteria.setEmployeeId(salesAgentId);
			try {
				EmployeeIfc employee = lookupEmployeeLogInOrEmpId(criteria);
				String exceptionMsg = "No sale agent was found for Order ID:" + orderID;
				if (employee == null)
					throw new EmployeeFindException(exceptionMsg);
				salesAgent = employee;
			} catch (EmployeeFindException e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return salesAgent;
	}

	@Override
	public String getMerchNameById(String merchGroupId) {
		return super.getMerchNameById(merchGroupId);
	}

	/*
	 * (non-Javadoc)
	 * @see rispl.dkart.services.ejb.LookUpEmployeeIfc#updateEmployeePassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Integer updateEmployeePassword(String employeeLoginId, String currentPassword, String newPassword) {
		//EmployeeIfc emp = null;

		try {
			em = emf.createEntityManager();

			//LOGGER.info("Transaction begin");
			TypedQuery<RisplDkEmpMstr> query = em.createNamedQuery("FIND_EMPLOYEE", RisplDkEmpMstr.class).setParameter("employeeLogInId",
					employeeLoginId);
			//LOGGER.info("Query executed");
			List<RisplDkEmpMstr> list = query.getResultList();
			if (list!=null && list.size() > 0) {
				LOGGER.info("Employee information found with login id: " + employeeLoginId);

				RisplDkEmpMstr employee = list.get(0);

				/*byte[] currentPasswordHash = new BaseKeyStoreEncryptionService().superHash(currentPassword.getBytes(),
						employee.getEmpPwdSlt(), false);
				String currentPasswordEncoded = new BaseKeyStoreEncryptionService()
						.getBase64encode(currentPasswordHash);*/
				
				String currentPasswordEncoded = getEncryptedPassword(currentPassword,employee.getEmpPwdSlt());
				LOGGER.info("Validation current password");
				/*if (!currentPasswordEncoded.equals(employee.getEmpAcsPwd())) {*/
				if (!employee.getEmpAcsPwd().equals(currentPasswordEncoded)) {
					LOGGER.info("Current password did not match!");
					return 2;
				}

				/*byte[] newPasswordHash = new BaseKeyStoreEncryptionService().superHash(newPassword.getBytes(),
						employee.getEmpPwdSlt(), false);
				String newPasswordEncoded = new BaseKeyStoreEncryptionService().getBase64encode(newPasswordHash);*/
				String newPasswordEncoded = getEncryptedPassword(newPassword, employee.getEmpPwdSlt());
						
				employee.setEmpAcsPwd(newPasswordEncoded);
				employee.setTsCrtPw(new Date());
				if(employee.getFlPwNwReq()!=null && employee.getFlPwNwReq().equalsIgnoreCase("1"))
					employee.setFlPwNwReq("0");
				
				em.getTransaction().begin();
				em.merge(employee);
				em.getTransaction().commit();
				LOGGER.info("Password updated successfully.");
				return 0;
			} else {
				LOGGER.info("No employee found with login id: " + employeeLoginId);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 3;
	}

	@Override
	public boolean updateEmployeeTemporaryPassword(String loginId, String tempPassword) {
		try {
			em = emf.createEntityManager();

			//LOGGER.info("Transaction begin");
			TypedQuery<RisplDkEmpMstr> query = em.createNamedQuery("FIND_EMPLOYEE", RisplDkEmpMstr.class)
					.setParameter("employeeLogInId", loginId);
			//LOGGER.info("Query executed");
			List<RisplDkEmpMstr> list = query.getResultList();
			if (list != null && list.size() > 0) {
				LOGGER.info("Employee information found with login id: " + loginId);

				RisplDkEmpMstr employee = list.get(0);

				//TODO fix employee password history table to maintain record of previous passwords
				/*
				 * RisplDkEmpPwdHistPK empPasswordHistoryPk = new
				 * RisplDkEmpPwdHistPK();
				 * empPasswordHistoryPk.setEmpId(employee.getId().getEmpId());
				 * empPasswordHistoryPk.setIdStrRt(employee.getId().getIdStrRt()
				 * );
				 * 
				 * RisplDkEmpPwdHist empPasswordHistory =
				 * em.find(RisplDkEmpPwdHist.class, empPasswordHistoryPk);
				 */

				String tempPasswordEncoded = getEncryptedPassword(tempPassword, employee.getEmpPwdSlt());

				employee.setEmpAcsPwd(tempPasswordEncoded);
				employee.setTsCrtPw(new Date());
				// Set new password flag to ask for password change after login
				if (employee.getFlPwNwReq() != null && employee.getFlPwNwReq().equalsIgnoreCase("0"))
					employee.setFlPwNwReq("1");

				em.getTransaction().begin();
				em.merge(employee);
				em.getTransaction().commit();
				LOGGER.info("Temporary password & new password required flag updated successfully.");
				return true;
			} else {
				LOGGER.info("No employee found with login id: " + loginId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	@Override
	public List<EmployeeIfc> getEmployeeByRoleAndDivision(Long roleId, String divisionId) {
		List<EmployeeIfc> ret = new ArrayList<>();
		em = emf.createEntityManager();
		List<RisplDkEmpMstr> employeesListByDiv = em.createNamedQuery("FIND_EMP_BY_ROLE", RisplDkEmpMstr.class) // Filter by role id
				.setParameter(1, roleId).getResultList();
		if (employeesListByDiv != null && employeesListByDiv.size() > 0) {
			for (RisplDkEmpMstr risplDkEmpMstr : employeesListByDiv) {
				List<RisplDkEmpMerchAssoc> empMerchAssocList = risplDkEmpMstr.getRisplDkEmpMerchAssocs();
				for (RisplDkEmpMerchAssoc empMerchAssoc : empMerchAssocList) {
					if (empMerchAssoc.getId().getMerchId().trim().equalsIgnoreCase(divisionId)) // Filter by division id
						ret.add(castEmpMstrToEmpIfc(risplDkEmpMstr));
				}
			}
		}

		return ret;
	}

	@Override
	public List<Integer> getEmployeeDivisions(EmployeeIfc employee) {
		List<Integer> empDivisions = new ArrayList<>();
		if (employee != null && employee.getMerchAssoc() != null) {
			for (EmpMerchAssociationIfc merchAssoc : employee.getMerchAssoc()) {
				String merchIdString = merchAssoc.getMerchId();
				if(merchIdString.startsWith("1")){ //TODO get division prefix from parameter to be configurable
					int indexOfColon = merchIdString.lastIndexOf(":");
					if (indexOfColon != -1) {
						merchIdString = merchIdString.substring(indexOfColon + 1);
					}
					empDivisions.add(Integer.parseInt(merchIdString));
				}
			}
		}
		return empDivisions;
	}

	@Override
	public List<List<String>> getAllEmp() {
		List<List<String>> mainList = new ArrayList<>();
		//mailList.add(new ArrayList<String>());

		EntityManager em = emf.createEntityManager();
		List<EmployeeV> empList = em.createNamedQuery("EmployeeV.findAll", EmployeeV.class).getResultList();
		if (empList != null && empList.size() > 0) {
			for (EmployeeV emp : empList) {
				List<String> subList = new ArrayList<>();
				subList.add(emp.getEmpId());
				subList.add(emp.getStatus());
				subList.add(emp.getLoginId());
				subList.add(emp.getEmpNme());
				subList.add(emp.getRoleName());
				subList.add(emp.getSearchCriteria());
				subList.add(emp.getEmail());
				subList.add(emp.getDivision());
				mainList.add(subList);
			}
		} else
			mainList.add(new ArrayList<String>());
		return mainList;
	}
}
