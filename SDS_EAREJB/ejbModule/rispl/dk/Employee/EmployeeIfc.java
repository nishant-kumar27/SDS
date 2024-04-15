package rispl.dk.Employee;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface EmployeeIfc extends Serializable {

	public String getEmployeeId();

	public void setEmployeeId(String employeeId);

	public String getLoginId();

	public void setLoginId(String loginId);

	public String getAlternateId();

	public void setAlternateId(String alternateId);

	public String getSaltPassword();

	public void setSaltPassword(String saltPassword);

	public String getAccessPassword();

	public void setAccessPassword(String accessPassword);

	public String getEmployeeName();

	public void setEmployeeName(String employeeName);

	public String getEmployeeLastName();

	public void setEmployeeLastName(String employeeLastName);

	public String getEmployeeFirstName();

	public void setEmployeeFirstName(String employeeFirstName);

	public String getEmployeeMiddleName();

	public void setEmployeeMiddleName(String employeeMiddleName);

	public String getEmployeeStatusCode();

	public void setEmployeeStatusCode(String employeeStatusCode);

	public long getRoleId();

	public void setRoleId(long roleId);

	public String getRoleName();

	public void setRoleName(String roleName);

	public String getEmployeeLocale();

	public void setEmployeeLocale(String employeeLocale);

	public int getNumberOfDaysValid();

	public void setNumberOfDaysValid(int numberOfDaysValid);

	public int getEmployeeType();

	public void setEmployeeType(int employeeType);

	public String getStoreId();

	public void setStoreId(String storeId);

	public boolean isNewPasswordRequired();

	public void setNewPasswordRequired(boolean isNewPasswordRequired);

	public int getNumberOffailedpasswords();

	public void setNumberOffailedpasswords(int numberOffailedpasswords);

	public String getLastLogin();

	public void setLastLogin(String lastLogin);

	public int getStoreGroupFunction();

	public void setStoreGroupFunction(int storeGroupFunction);

	public String getGroupId();

	public void setGroupId(String groupId);

	public String getGroupType();

	public void setGroupType(String groupType);
	
	public String getEmail();
	
	public void setEmail(String email);

	public List<EmpMerchAssociationIfc> getMerchAssoc();

	void setMerchAssoc(List<EmpMerchAssociationIfc> merchAssoc);
	
	public void setRoleAccess(String roleAccess);
	public String getRoleAccess();

	public void setPswdCreateTime(Date pswdCreateTime);
	public Date getPswdCreateTime();
}
