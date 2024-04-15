package com.rispl.roles.access.service;

import java.io.Serializable;

public class EmployeeRoleAccessDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roleID;
	
	private String roleName;
	
	private String functionName;
	
	private String functionDescription;
	
	private String hasAccess;

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

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionDescription() {
		return functionDescription;
	}

	public void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}

	public String getHasAccess() {
		return hasAccess;
	}

	public void setHasAccess(String hasAccess) {
		this.hasAccess = hasAccess;
	}
	
	
	
	

}
