package rispl.ds.changepswd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;
import rispl.ds.login.LoginAction;

public class ChangePassword extends DSAction {

	private static final long serialVersionUID = 1L;

	private String CurrentPassword;
	private String NewPassword;
	private String ReEnterNewPassword;
	private EmployeeIfc employee;

	private static Logger LOGGER = LogManager.getLogger(LoginAction.class);

	public String newPassword() {
		LOGGER.info("Inside ChangePassword.newPassword");
		//HttpSession session = ServletActionContext.getRequest().getSession(false);
		//HttpServletRequest request = ServletActionContext.getRequest();
		employee = getEmployee();
		LOGGER.info("Getting employee details from session");
		//LOGGER.info("Setting CurrentPassword");
		//setCurrentPassword(CurrentPassword);

		//String enc_password = "";

		try {
			String employeeLoginId = employee.getLoginId();

			LookUpEmployeeIfc remoteBean = DKartContext.getLookupEmployee();
			Integer updateStatus = remoteBean.updateEmployeePassword(employeeLoginId, CurrentPassword, NewPassword);

			if (updateStatus != null) {
				if (updateStatus == 0) {
					String passwordChange_success = "Password changed successfully";
					LOGGER.info(passwordChange_success);
					addActionMessage(passwordChange_success);
					return SUCCESS;
				} else if (updateStatus == 1) {
					String passwordChange_error_empNotFound = "Employee not found with login id: " + employeeLoginId;
					addActionError(passwordChange_error_empNotFound);
					LOGGER.error(passwordChange_error_empNotFound);
					return ERROR;
				} else if (updateStatus == 2) {
					String passwordChange_error_currentPasswordNotMatch = "Current password entered is incorrect!";
					addActionError(passwordChange_error_currentPasswordNotMatch);
					LOGGER.error(passwordChange_error_currentPasswordNotMatch);
					return ERROR;
				} else {
					String unknown_error = "An unknown error has occured";
					addActionError(unknown_error);
					LOGGER.error(unknown_error);
					return ERROR;
				}
			}

			else {
				String unknown_error = "An unknown error has occured";
				addActionError(unknown_error);
				LOGGER.error(unknown_error);
				return ERROR;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return SUCCESS;
	}

	public String getCurrentPassword() {
		return CurrentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		CurrentPassword = currentPassword;
	}

	public String getNewPassword() {
		return NewPassword;
	}

	public void setNewPassword(String newPassword) {
		NewPassword = newPassword;
	}

	public String getReEnterNewPassword() {
		return ReEnterNewPassword;
	}

	public void setReEnterNewPassword(String reEnterNewPassword) {
		ReEnterNewPassword = reEnterNewPassword;
	}
}
