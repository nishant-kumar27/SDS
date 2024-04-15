package com.rispl.roles.access.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface EmployeeRolesAccessRemote {
	
	public static final String fetchmaxRoleID="SELECT MAX(empRole.roleId) FROM RisplDkEmpRole empRole";
	
	public static final String fetchAllRoles="SELECT empRole FROM RisplDkEmpRole empRole order by empRole.roleId asc";
	
	public static final String deleteRoles="DELETE FROM RisplDkEmpRole empRole WHERE empRole.roleDesc in :roleDesc";
	
	public static final String fetchRolePermisssion = "SELECT ROLEFUNCTION.ROLE_ID,ROLEFUNCTION.ROLE_DESC,ROLEFUNCTION.FUNCTION_ID,ROLEFUNCTION.FUNCTION_DESC, "
                                                     +"ROLEACCESS.HAS_ACCESS FROM (SELECT EMPROLE.ROLE_ID AS ROLE_ID,EMPROLE.ROLE_DESC AS ROLE_DESC, "
			                                         +"FUNC.FUNCTION_ID AS FUNCTION_ID,FUNC.FUNCTION_DESC AS FUNCTION_DESC FROM RISPL_DK_FUNC FUNC, "
                                                     +"RISPL_DK_EMP_ROLE EMPROLE) ROLEFUNCTION,RISPL_DK_EMP_ROLE_ACCESS ROLEACCESS "
			                                         +"WHERE ROLEFUNCTION.FUNCTION_ID  = ROLEACCESS.FUNCTION_ID(+) AND "
                                                     +"ROLEFUNCTION.ROLE_DESC = ? AND ROLEFUNCTION.ROLE_ID  = ROLEACCESS.ROLE_ID(+) "
			                                         +"ORDER BY ROLEFUNCTION.FUNCTION_ID ASC";
	public static final String checkSameRoleExistOrNot ="SELECT * FROM RISPL_DK_EMP_ROLE WHERE UPPER(ROLE_DESC) = ?";
		
	public String fetchMaxRoleID();
	
	public boolean createRole(String roleId,String roleName,String homePage,String searchCriteria,String empID);
	
	public boolean deleteRole(List<String> roleName);
	
	public List<EmployeeRole> fetchAllEmployeeRoles();
	
	public boolean updateHomePageBasedEmpRole(ArrayList<UpdateHomePage> homePageList,String employeeID);
	
	public List<EmployeeRoleAccessDTO> fetchEmployeeRolePermission(String roleName);
	
	public boolean assignRoleSpecificPermission(ArrayList<UpdateEmployeeRoleAccess> employeeRoleAcsList,String employeeID);
	
	public boolean checkSameRoleExistOrNot(String roleName);


}
