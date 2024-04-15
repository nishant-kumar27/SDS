package rispl.dkservices.common;

public class EmployeeSearchCriteria implements EmployeeSearchCriteriaIfc {

	private static final long serialVersionUID = 1L;

	String salesAgent;

	String loginId;

	String storeId;

	String password;

	long roleId;

	private String employeeId;

	public String getSalesAgent() {
		return salesAgent;
	}

	public void setSalesAgent(String salesAgent) {
		this.salesAgent = salesAgent;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String getEmployeeId() {
		// TODO Auto-generated method stub
		return employeeId;
	}

	@Override
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
		
	}

}
