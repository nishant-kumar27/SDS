package rispl.dkservices.common;

import java.io.Serializable;

public interface EmployeeSearchCriteriaIfc extends Serializable {

	public String getSalesAgent();

	public void setSalesAgent(String salesAgent);

	public void setRoleId(long roleId);

	public long getRoleId();

	public String getEmployeeId();

	public void setEmployeeId(String employeeId);

	public String getLoginId();

	public void setLoginId(String loginId);

	public String getStoreId();

	public void setStoreId(String storeId);

	public String getPassword();

	public void setPassword(String password);
}
