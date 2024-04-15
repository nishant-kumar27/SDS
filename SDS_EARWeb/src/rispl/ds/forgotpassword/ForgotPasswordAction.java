package rispl.ds.forgotpassword;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class ForgotPasswordAction extends DSAction {

	private static final long serialVersionUID = 1L;

	Logger LOG = LogManager.getLogger(getClass());

	String loginid;
	String email;

	LookUpEmployeeIfc empLookup;

	@Override
	public void validate() {
		if (loginid == null)
			addActionError("loginid cannot be empty");
		if (email == null)
			addActionError("email cannot be empty");
	}

	@Override
	public String execute() {
		try {
			empLookup = DKartContext.getLookupEmployee();
			
			EmployeeIfc employee = empLookup.lookupEmployeeByLoginId(loginid);
			
			if (employee == null) {
				String errorMsg = "Employee details not found. Please try again.";
				LOG.error(errorMsg);
				addActionError(errorMsg);
			} else {
				LOG.error("Employee Found");

				if (employee.getEmail() != null && employee.getEmail().length() > 0) {
					if (email.trim().equalsIgnoreCase(employee.getEmail().trim())) {
						String tempPassword = generateTempPassword();
						LOG.debug("Temp password is #0",tempPassword);
						
						boolean tempPasswordupdated = empLookup.updateEmployeeTemporaryPassword(loginid, tempPassword);
						
						if(tempPasswordupdated){
							sendEmailWithTempPassword(employee, tempPassword);
							addActionMessage("Temporary password has been sent to your email");
							return SUCCESS;
						}
						else{
							addActionError("An unknown exception has occured. Please contact Administrator.");
						}

					} else {
						addActionError("Employee details did not match. Please try again.");
					}
				} else {
					addActionError("Employee email is not configured. Please contact Administrator.");
				}

			}

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return ERROR;
	}

	private void sendEmailWithTempPassword(EmployeeIfc employee, String tempPassword) {
		try {
			MailBeanRemote mailBean = DKartContext.getMailBean();
			String refNo = employee.getLoginId();
			String subject = "SDS temporary password";
			String content = "Your temporary password has been successfully generated.\n\n\tTemporary Password: "
					+ tempPassword + "\n\nAfter logging in you will be asked to change your temporary password.";
			String to = employee.getEmail();
			mailBean.sendEmailText(MailBeanRemote.TransTypeEnum.EMP_TEMP_PASSWORD, refNo, MailBeanRemote.MailRecipientTypeEnum.EMPLOYEE, subject, content,
					to);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * Generate random string of length 10
	 * @return String
	 */
	private String generateTempPassword() {

		return new BigInteger(50, new SecureRandom()).toString(32).toUpperCase();
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
