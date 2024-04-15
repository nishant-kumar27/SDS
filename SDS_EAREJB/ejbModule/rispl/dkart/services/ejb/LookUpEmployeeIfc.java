package rispl.dkart.services.ejb;

import java.util.List;

import javax.ejb.Remote;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;

@Remote
public interface LookUpEmployeeIfc {

	public EmployeeIfc validateEmployee(EmployeeSearchCriteriaIfc criteria);

	public EmployeeIfc[] lookupSalesAgent(EmployeeSearchCriteriaIfc criteria);

	public List<String> getSalesAgentsList(String[] emids);
	
	public EmployeeIfc lookupSalesAgentForOrder(String orderID) throws Exception;
	
	public String getMerchNameById(String merchGroupId);
	
	/**
	 * Returns<br>
	 * --------<br>
	 * 0 - Password changed successfully<br>
	 * 1 - Employee not found with specified login id<br>
	 * 2 - Current password does not match<br>
	 * 3 - Other exceptions<br>
	 * 
	 * @return status
	 */
	public Integer updateEmployeePassword(String loginId, String oldPassword, String newPassword);
	
	public EmployeeIfc lookupEmployeeByLoginId(String loginId);
	
	public EmployeeIfc lookupEmployeeByLoginIdOrEmpId(String empInfo);

	public boolean updateEmployeeTemporaryPassword(String loginId, String tempPassword);
	
	public List<EmployeeIfc> getEmployeeByRoleAndDivision(Long roleId, String divisionId);
	
	public List<Integer> getEmployeeDivisions(EmployeeIfc employee);
	/**
	 * Temp function to get all emp data from emp view
	 */
	public List<List<String>> getAllEmp();
}
