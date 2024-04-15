package rispl.ds.resetPassword;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class ResetPasswordAction extends DSAction {

	private static final long serialVersionUID = 1L;

	Logger LOG = LogManager.getLogger(getClass());

	String empInfo;
	EmployeeIfc emp;
	
	LookUpEmployeeIfc empLookup;

	@Override
	public void validate() {
		if (empInfo == null)
			addActionError("Employee details cannot be empty");
	}
	
	public String lookupEmployee() {
		try {
			empLookup = DKartContext.getLookupEmployee();

			emp = empLookup.lookupEmployeeByLoginIdOrEmpId(empInfo);

			if (emp == null) {
				String errorMsg = "Employee details not found.";
				LOG.error(errorMsg);
				addActionError(errorMsg);
			} else
				return SUCCESS;
			
		} catch (Exception e) {
			addActionError("Error occured during employee lookup.");
			LOG.error(e.getMessage(), e);
		}

		return ERROR;
	}

	public String resetPassword() {
		try {
			empLookup = DKartContext.getLookupEmployee();

			EmployeeIfc employee = empLookup.lookupEmployeeByLoginIdOrEmpId(empInfo);

			if (employee == null) {
				String errorMsg = "Employee details not found. Please try again.";
				LOG.error(errorMsg);
				addActionError(errorMsg);
			} else {
				LOG.info("Employee Found");

				/*if (employee.getEmail() != null && employee.getEmail().length() > 0) {*/

					String tempPassword = getDefaultPassword();
					LOG.debug("Default password is #0", tempPassword);

					boolean tempPasswordUpdated = empLookup.
							updateEmployeeTemporaryPassword(employee.getLoginId(), tempPassword);

					if (tempPasswordUpdated) {
						addActionMessage("Password had been reset to \""+tempPassword+"\"");
						if (employee.getEmail() != null && employee.getEmail().length() > 0) {
							sendEmailWithResetPassword(employee, tempPassword);
							addActionMessage("Reset password has been sent to employee email");
						}
						else
						{
							addActionError("Reset password could not be sent to employee email");
						}
						return SUCCESS;
					} else {
						addActionError("An unknown exception has occured. Please contact Administrator.");
					}

				/*} else {
					addActionError("Employee email is not configured.");
				}*/

			}

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return ERROR;
	}

	private void sendEmailWithResetPassword(EmployeeIfc employee, String tempPassword) {
		try {
			MailBeanRemote mailBean = DKartContext.getMailBean();
			String refNo = employee.getLoginId();
			String subject = "SDS reset password";
			String content = "Your password has been successfully reset.\n\n\tReset Password: "
					+ tempPassword + "\n\nAfter logging in you will be asked to change your temporary password.";
			String to = employee.getEmail();
			mailBean.sendEmailText(MailBeanRemote.TransTypeEnum.EMP_RESET_PASSWORD, refNo, MailBeanRemote.MailRecipientTypeEnum.EMPLOYEE, subject, content,
					to);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private String getDefaultPassword() {
		String resetPassword = getText("default.resetpassword");
		if(resetPassword!=null && resetPassword.length() >0 )
			return resetPassword;
		return "pos1234";
	}

	public String getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(String empInfo) {
		this.empInfo = empInfo;
	}

	public EmployeeIfc getEmp() {
		return emp;
	}

}
