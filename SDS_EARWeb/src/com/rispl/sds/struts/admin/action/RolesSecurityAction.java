package com.rispl.sds.struts.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

import com.rispl.roles.access.service.EmployeeRole;
import com.rispl.roles.access.service.EmployeeRoleAccessDTO;
import com.rispl.roles.access.service.EmployeeRolesAccessRemote;
import com.rispl.roles.access.service.UpdateEmployeeRoleAccess;
import com.rispl.roles.access.service.UpdateHomePage;

import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;
import util.security.SdsRealm;

public class RolesSecurityAction extends DSAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roleID;

	private String roleName;

	private String homePage;

	private List<EmployeeRole> empRoleList = new ArrayList<EmployeeRole>();

	private Map<String, String> redirectPageList = new HashMap<String, String>();

	private String newRoleError;

	private String maxRoleID;

	private ArrayList<UpdateHomePage> homePageList = new ArrayList<UpdateHomePage>();

	private String selectRoleName;

	private List<String> roleDesc = new ArrayList<String>();

	private String permissionError;

	private List<EmployeeRoleAccessDTO> accessPermissionList = new ArrayList<EmployeeRoleAccessDTO>();

	/*private ArrayList<String> hasAccessList = new ArrayList<String>();
*/
	private ArrayList<UpdateEmployeeRoleAccess> updateEmpRoleAccessList = new ArrayList<UpdateEmployeeRoleAccess>();

	private UpdateEmployeeRoleAccess updateroleAccess;
	
	private String filterCriteria;
	
	private ArrayList<String> searchCriteriaList = new ArrayList<String>();
	

	public ArrayList<String> getSearchCriteriaList() {
		return searchCriteriaList;
	}

	public void setSearchCriteriaList(ArrayList<String> searchCriteriaList) {
		this.searchCriteriaList = searchCriteriaList;
	}

	public String getFilterCriteria() {
		return filterCriteria;
	}

	public void setFilterCriteria(String filterCriteria) {
		this.filterCriteria = filterCriteria;
	}

	public ArrayList<UpdateEmployeeRoleAccess> getUpdateEmpRoleAccessList() {
		return updateEmpRoleAccessList;
	}

	public void setUpdateEmpRoleAccessList(ArrayList<UpdateEmployeeRoleAccess> updateEmpRoleAccessList) {
		this.updateEmpRoleAccessList = updateEmpRoleAccessList;
	}

	public UpdateEmployeeRoleAccess getUpdateroleAccess() {
		return updateroleAccess;
	}

	public void setUpdateroleAccess(UpdateEmployeeRoleAccess updateroleAccess) {
		this.updateroleAccess = updateroleAccess;
	}

/*	public ArrayList<String> getHasAccessList() {
		return hasAccessList;
	}

	public void setHasAccessList(ArrayList<String> hasAccessList) {
		this.hasAccessList = hasAccessList;
	}
*/
	public List<EmployeeRoleAccessDTO> getAccessPermissionList() {
		return accessPermissionList;
	}

	public void setAccessPermissionList(List<EmployeeRoleAccessDTO> accessPermissionList) {
		this.accessPermissionList = accessPermissionList;
	}

	public String getPermissionError() {
		return permissionError;
	}

	public void setPermissionError(String permissionError) {
		this.permissionError = permissionError;
	}

	public List<String> getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(List<String> roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getSelectRoleName() {
		return selectRoleName;
	}

	public void setSelectRoleName(String selectRoleName) {
		this.selectRoleName = selectRoleName;
	}

	public ArrayList<UpdateHomePage> getHomePageList() {
		return homePageList;
	}

	public void setHomePageList(ArrayList<UpdateHomePage> homePageList) {
		this.homePageList = homePageList;
	}

	public String getMaxRoleID() {
		return maxRoleID;
	}

	public void setMaxRoleID(String maxRoleID) {
		this.maxRoleID = maxRoleID;
	}

	public Map<String, String> getRedirectPageList() {
		return redirectPageList;
	}

	public void setRedirectPageList(Map<String, String> redirectPageList) {
		this.redirectPageList = redirectPageList;
	}

	public String getNewRoleError() {
		return newRoleError;
	}

	public void setNewRoleError(String newRoleError) {
		this.newRoleError = newRoleError;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public List<EmployeeRole> getEmpRoleList() {
		return empRoleList;
	}

	public void setEmpRoleList(List<EmployeeRole> empRoleList) {
		this.empRoleList = empRoleList;
	}

	public String execute() {
		try {
			fetchempRoleList();
			displayPageAfterLoginBasedOnRole();
			fetchSearchCriteriaList();
			maxRoleId();
			for (EmployeeRole role : fetchempRoleList()) {
				roleDesc.add(role.getRoleDesc());
			}
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String fetchEmployeePermissionList() {
		permissionError = "FALSE";
		try {
			EmployeeRolesAccessRemote remote = DKartContext.getEmployeeRoles();
			accessPermissionList = remote.fetchEmployeeRolePermission(selectRoleName);
			/*hasAccessList.add("YES");
			hasAccessList.add("NO");*/
			if (accessPermissionList.size() > 0 || accessPermissionList.size() == 0) {
				execute();
				return SUCCESS;
			} else {
				permissionError = "TRUE";
				execute();
				addActionError("Unable To Fetch The Employee Based Roles Permission From Database");
				return ERROR;
			}
		} catch (Exception e) {
			permissionError = "TRUE";
			execute();
			addActionError("Unable To Find The Employee Based Roles Permission From Database");
			return ERROR;
		}

	}

	public String updateEmployeeRoleBasedPermisssion() {
		boolean flag = false;
		permissionError = "FALSE";
		try {
			if (updateEmpRoleAccessList.size() == 0) {
				execute();
				permissionError = "TRUE";
				addActionError("Please Select Role");
				return ERROR;

			} else {
				EmployeeRolesAccessRemote remote = DKartContext.getEmployeeRoles();
				flag = remote.assignRoleSpecificPermission(updateEmpRoleAccessList, getEmployee().getEmployeeId());
				execute();
				if (flag) {
					permissionError = "FALSE";
					
					// after updating permission call this to clear shiro cache
					 ((RealmSecurityManager)SecurityUtils.getSecurityManager()).getRealms().stream()
								.filter(SdsRealm.class::isInstance)
								.map(SdsRealm.class::cast)
								.forEach(realm -> realm.clearCache(SecurityUtils.getSubject().getPrincipals()));

					addActionMessage("Roles Specific Permission Has Been Updated Successfully");
					return SUCCESS;
				} else {
					permissionError = "TRUE";
					addActionError("Unable To Update Roles Specific Permission");
					return ERROR;
				}
			}
		} catch (Exception e) {
			permissionError = "TRUE";
			execute();
			addActionError("Unable To Update Roles Specific Permission");
			return ERROR;
		}

	}

	public List<EmployeeRole> fetchempRoleList() {
		try {
			EmployeeRolesAccessRemote remote = DKartContext.getEmployeeRoles();
			empRoleList = remote.fetchAllEmployeeRoles();
		} catch (Exception e) {
			addActionError("Unable To Fetch Employee Roles From Database");
		}
		return empRoleList;
	}

	public Map<String, String> displayPageAfterLoginBasedOnRole() {
		redirectPageList = new HashMap<String, String>();
		redirectPageList.put("invoices", "Invoices Search");
		redirectPageList.put("customerSearch", "Customers Search");
		redirectPageList.put("dashboard", "DashBoard");
		redirectPageList.put("paramterConfig", "Admin Panel");
		redirectPageList.put("Inventory_Lookup", "Inventory Inquiry");

		return redirectPageList;

	}
	
	public ArrayList<String> fetchSearchCriteriaList() {
		searchCriteriaList = new ArrayList<String>();
		searchCriteriaList.add("All");
		searchCriteriaList.add("Linked Agent");
		searchCriteriaList.add("Within Division");
		return searchCriteriaList;
	}

	public String maxRoleId() {
		maxRoleID = "";
		try {
			EmployeeRolesAccessRemote remote = DKartContext.getEmployeeRoles();
			maxRoleID = remote.fetchMaxRoleID();
		} catch (Exception e) {
			addActionError("Unable To Fetch Max Role ID From Database");
		}
		return maxRoleID;
	}

	public String createNewRole() {
		boolean flag = false;
		newRoleError = "FALSE";
		if (roleID == null || roleID.equals("")) {
			flag = true;
		} else if (roleName == null || roleName.equals("")) {
			flag = true;
		} else if (homePage == null || homePage.equals("") || homePage.equals("0")) {
			flag = true;
		}
		else if (filterCriteria == null || filterCriteria.equals("") || filterCriteria.equals("0")) {
			flag = true;
		}
		if (flag) {
			newRoleError = "TRUE";
			execute();
			addActionError("All Fields Are Mandatory. Please Enter All The Details And Try Again");
			return ERROR;
		} else {
			try {
				EmployeeRolesAccessRemote remote = DKartContext.getEmployeeRoles();
				flag = remote.checkSameRoleExistOrNot(roleName);
				if(flag)
				{
					newRoleError = "TRUE";
					execute();
					addActionError("Same Role Alreday Exist in The System.Please Enter a Different Role Name While Creating New Role");
					return SUCCESS;
				}
				else{
				flag = remote.createRole(roleID, roleName, homePage, filterCriteria,getEmployee().getEmployeeId());
				if (flag) {
					execute();
					newRoleError = "FALSE";
					addActionMessage("New Role Has Been Created Successfully");
					return SUCCESS;
				} else {
					newRoleError = "TRUE";
					execute();
					addActionError("Unable To Add New Role To Database.Please Try After Some Time");
					return ERROR;
				}
			} 
			}catch (Exception e) {
				newRoleError = "TRUE";
				execute();
				addActionError("Unable To Add New Role To Database.Please Try After Some Time");
				return ERROR;
			}
		}

	}

	public String updateHomePageBasedOnEmpRole() {
		boolean flag = false;
		newRoleError = "FALSE";
		try {
			EmployeeRolesAccessRemote remote = DKartContext.getEmployeeRoles();
			flag = remote.updateHomePageBasedEmpRole(homePageList, getEmployee().getEmployeeId());
			if (flag) {
				execute();
				newRoleError = "FALSE";
				for(UpdateHomePage empRoleHomePage : homePageList)
				{
					if(empRoleHomePage.getNewRoleID().equals(String.valueOf(getEmployee().getRoleId())))
					{
						getEmployee().setRoleAccess(empRoleHomePage.getFilterCriteria());
					}
				}
				addActionMessage("Redirect To Home Page/Filter Criteria For Roles Has Been Updated Successfully");
				return SUCCESS;
			} else {
				newRoleError = "TRUE";
				execute();
				addActionError("Unable To Update Employee Role Home Page");
				return ERROR;
			}
		} catch (Exception e) {
			newRoleError = "TRUE";
			execute();
			addActionError("Unable To Update Employee Role Home Page.Please Try After Some Time");
			return ERROR;
		}
	}

}
