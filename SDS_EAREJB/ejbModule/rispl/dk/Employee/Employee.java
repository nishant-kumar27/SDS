package rispl.dk.Employee;

import java.util.Date;
import java.util.List;

public class Employee implements EmployeeIfc {

	private static final long serialVersionUID = 1L;

	String employeeId;

	String loginId;
	String alternateId;
	String saltPassword;
	String accessPassword;
	String employeeName;
	String employeeLastName;
	String employeeFirstName;
	String employeeMiddleName;
	String EmployeeStatusCode;
	long roleId;
	String roleName;
	String employeeLocale;
	int numberOfDaysValid;

	int employeeType;

	String storeId;

	boolean isNewPasswordRequired;

	int numberOffailedpasswords;
	String lastLogin;
	int storeGroupFunction;
	String groupId;
	String groupType;
	String Email;
//mudassir
	private String roleAccess;
	
	private List<EmpMerchAssociationIfc> merchAssoc;

	private Date pswdCreateTime;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getAlternateId() {
		return alternateId;
	}

	public void setAlternateId(String alternateId) {
		this.alternateId = alternateId;
	}

	public String getSaltPassword() {
		return saltPassword;
	}

	public void setSaltPassword(String saltPassword) {
		this.saltPassword = saltPassword;
	}

	public String getAccessPassword() {
		return accessPassword;
	}

	public void setAccessPassword(String accessPassword) {
		this.accessPassword = accessPassword;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeMiddleName() {
		return employeeMiddleName;
	}

	public void setEmployeeMiddleName(String employeeMiddleName) {
		this.employeeMiddleName = employeeMiddleName;
	}

	public String getEmployeeStatusCode() {
		return EmployeeStatusCode;
	}

	public void setEmployeeStatusCode(String employeeStatusCode) {
		EmployeeStatusCode = employeeStatusCode;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getEmployeeLocale() {
		return employeeLocale;
	}

	public void setEmployeeLocale(String employeeLocale) {
		this.employeeLocale = employeeLocale;
	}

	public int getNumberOfDaysValid() {
		return numberOfDaysValid;
	}

	public void setNumberOfDaysValid(int numberOfDaysValid) {
		this.numberOfDaysValid = numberOfDaysValid;
	}

	public int getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(int employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public boolean isNewPasswordRequired() {
		return isNewPasswordRequired;
	}

	public void setNewPasswordRequired(boolean isNewPasswordRequired) {
		this.isNewPasswordRequired = isNewPasswordRequired;
	}

	public int getNumberOffailedpasswords() {
		return numberOffailedpasswords;
	}

	public void setNumberOffailedpasswords(int numberOffailedpasswords) {
		this.numberOffailedpasswords = numberOffailedpasswords;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getStoreGroupFunction() {
		return storeGroupFunction;
	}

	public void setStoreGroupFunction(int storeGroupFunction) {
		this.storeGroupFunction = storeGroupFunction;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	@Override
	public String getRoleName() {
		return roleName;
	}

	@Override
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public void setMerchAssoc(List<EmpMerchAssociationIfc> merchAssoc) {
		this.merchAssoc = merchAssoc;
	}

	@Override
	public List<EmpMerchAssociationIfc> getMerchAssoc() {
		return merchAssoc;
	}
	

	public String getRoleAccess() {
		return roleAccess;
	}

	public void setRoleAccess(String roleAccess) {
		this.roleAccess = roleAccess;
	}

	@Override
	public void setPswdCreateTime(Date pswdCreateTime) {
		this.pswdCreateTime = pswdCreateTime;
	}

	@Override
	public Date getPswdCreateTime() {
		return pswdCreateTime;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", loginId=" + loginId + ", alternateId=" + alternateId
				+ ", saltPassword=" + saltPassword + ", accessPassword=" + accessPassword + ", employeeName="
				+ employeeName + ", employeeLastName=" + employeeLastName + ", employeeFirstName=" + employeeFirstName
				+ ", employeeMiddleName=" + employeeMiddleName + ", EmployeeStatusCode=" + EmployeeStatusCode
				+ ", roleId=" + roleId + ", roleName=" + roleName + ", employeeLocale=" + employeeLocale
				+ ", numberOfDaysValid=" + numberOfDaysValid + ", employeeType=" + employeeType + ", storeId=" + storeId
				+ ", isNewPasswordRequired=" + isNewPasswordRequired + ", numberOffailedpasswords="
				+ numberOffailedpasswords + ", lastLogin=" + lastLogin + ", storeGroupFunction=" + storeGroupFunction
				+ ", groupId=" + groupId + ", groupType=" + groupType + ", Email=" + Email + ", roleAccess="
				+ roleAccess + ", merchAssoc=" + merchAssoc + ", pswdCreateTime=" + pswdCreateTime + "]";
	}

}
