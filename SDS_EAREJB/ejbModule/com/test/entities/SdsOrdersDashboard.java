package com.test.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the SDS_ORDERS_DASHBOARD database table.
 * 
 */
@Entity
@Table(name = "SDS_ORDERS_DASHBOARD")
@Cacheable(value = false)
@NamedQuery(name = "SdsOrdersDashboard.findAll", query = "SELECT s FROM SdsOrdersDashboard s")
public class SdsOrdersDashboard implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SdsOrdersDashboardPK id;

	@Column(name = "CANCELLED_ORDERS")
	private BigDecimal cancelledOrders;

	private BigDecimal completed;

	@Column(name = "DELIVERED_ORDERS")
	private BigDecimal deliveredOrders;

	@Column(name = "DIVISION_ID")
	private BigDecimal divisionId;

	@Column(name = "IN_PROGRESS")
	private BigDecimal inProgress;

	@Column(name = "NEW_ORDERS")
	private BigDecimal newOrders;

	@Column(name = "OPEN_ORDERS")
	private BigDecimal openOrders;

	@Column(name = "PENDING_ORDERS")
	private BigDecimal pendingOrders;

	@Column(name = "RETURNED_ORDERS")
	private BigDecimal returnedOrders;

	public SdsOrdersDashboard() {
	}

	public SdsOrdersDashboardPK getId() {
		return id;
	}

	public void setId(SdsOrdersDashboardPK id) {
		this.id = id;
	}

	public BigDecimal getCancelledOrders() {
		return this.cancelledOrders;
	}

	public void setCancelledOrders(BigDecimal cancelledOrders) {
		this.cancelledOrders = cancelledOrders;
	}

	public BigDecimal getCompleted() {
		return this.completed;
	}

	public void setCompleted(BigDecimal completed) {
		this.completed = completed;
	}

	public BigDecimal getDeliveredOrders() {
		return this.deliveredOrders;
	}

	public void setDeliveredOrders(BigDecimal deliveredOrders) {
		this.deliveredOrders = deliveredOrders;
	}

	public BigDecimal getDivisionId() {
		return this.divisionId;
	}

	public void setDivisionId(BigDecimal divisionId) {
		this.divisionId = divisionId;
	}

	public BigDecimal getInProgress() {
		return this.inProgress;
	}

	public void setInProgress(BigDecimal inProgress) {
		this.inProgress = inProgress;
	}

	public BigDecimal getNewOrders() {
		return this.newOrders;
	}

	public void setNewOrders(BigDecimal newOrders) {
		this.newOrders = newOrders;
	}

	public BigDecimal getOpenOrders() {
		return this.openOrders;
	}

	public void setOpenOrders(BigDecimal openOrders) {
		this.openOrders = openOrders;
	}

	public BigDecimal getPendingOrders() {
		return this.pendingOrders;
	}

	public void setPendingOrders(BigDecimal pendingOrders) {
		this.pendingOrders = pendingOrders;
	}

	public BigDecimal getReturnedOrders() {
		return this.returnedOrders;
	}

	public void setReturnedOrders(BigDecimal returnedOrders) {
		this.returnedOrders = returnedOrders;
	}

}