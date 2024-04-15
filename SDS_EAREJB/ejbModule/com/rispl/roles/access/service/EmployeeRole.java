package com.rispl.roles.access.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class EmployeeRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private long roleId;

	private BigDecimal createdByUserId;

	private Timestamp createdDatetime;

	private Timestamp effectiveDatetime;

	private Timestamp endDatetime;

	private String roleDesc;

	private String homePage;
	
	private String searchCriteria;
	

	public EmployeeRole() {
	}

	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public BigDecimal getCreatedByUserId() {
		return this.createdByUserId;
	}

	public void setCreatedByUserId(BigDecimal createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Timestamp getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getEffectiveDatetime() {
		return this.effectiveDatetime;
	}

	public void setEffectiveDatetime(Timestamp effectiveDatetime) {
		this.effectiveDatetime = effectiveDatetime;
	}

	public Timestamp getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	

	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

}