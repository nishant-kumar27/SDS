package com.rispl.roles.access.service;

import java.io.Serializable;

public class UpdateHomePage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String newRoleID;
	
	private String newRedirectPage;
	
	private String newRoleDesc;
	
	private String filterCriteria;
	
	public String getFilterCriteria() {
		return filterCriteria;
	}

	public void setFilterCriteria(String filterCriteria) {
		this.filterCriteria = filterCriteria;
	}

	public String getNewRoleDesc() {
		return newRoleDesc;
	}

	public void setNewRoleDesc(String newRoleDesc) {
		this.newRoleDesc = newRoleDesc;
	}

	public String getNewRoleID() {
		return newRoleID;
	}

	public void setNewRoleID(String newRoleID) {
		this.newRoleID = newRoleID;
	}

	public String getNewRedirectPage() {
		return newRedirectPage;
	}

	public void setNewRedirectPage(String newRedirectPage) {
		this.newRedirectPage = newRedirectPage;
	}
	
	
	
	
	

}
