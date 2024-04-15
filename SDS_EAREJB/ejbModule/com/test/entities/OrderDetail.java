package com.test.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the ORDER_DETAILS database table.
 * 
 */
@Entity
@Table(name = "ORDER_DETAILS")
@EntityListeners(OrderDetailListener.class)
@Cacheable(value = false)
@NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	OrderDetailPk id;
	
	@Column(name = "ORDER_ID")
	private String ORDERID;


	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "DIVISION_ID")
	private BigDecimal divisionId;

	@Temporal(TemporalType.DATE)
	@Column(name = "EFFECTIVE_DATE")
	private Date effectiveDate;

	@Column(name = "EMP_ID")
	private String empId;

	@Column(name = "LPO_NUMBER")
	private String lpoNumber;

	@Column(name = "ORD_TY")
	private String ordTy;

	@Column(name = "ORDER_TOTAL")
	private BigDecimal orderTotal;

	@Column(name = "SALES_AGENT")
	private String salesAgent;

	@Column(name = "SC_ORD")
	private BigDecimal scOrd;

	@Column(name = "SC_TRAN")
	private BigDecimal scTran;

	@Column(name = "TOTAL_QUANTITY")
	private BigDecimal qty;

	@Column(name = "DC_DY_ORD")
	private String businessDate;

	private BigDecimal tlog;

	@Transient
	private OrderStatusEnum statusEnum;

	@Transient
	private String status;

	public OrderDetail() {
	}

	public OrderDetailPk getId() {
		return id;
	}

	public void setId(OrderDetailPk id) {
		this.id = id;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getDivisionId() {
		return this.divisionId;
	}

	public void setDivisionId(BigDecimal divisionId) {
		this.divisionId = divisionId;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getLpoNumber() {
		return this.lpoNumber;
	}

	public void setLpoNumber(String lpoNumber) {
		this.lpoNumber = lpoNumber;
	}

	public String getOrdTy() {
		return this.ordTy;
	}

	public void setOrdTy(String ordTy) {
		this.ordTy = ordTy;
	}

	public BigDecimal getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getSalesAgent() {
		return this.salesAgent;
	}

	public void setSalesAgent(String salesAgent) {
		this.salesAgent = salesAgent;
	}

	public BigDecimal getScOrd() {
		return this.scOrd;
	}

	public void setScOrd(BigDecimal scOrd) {
		this.scOrd = scOrd;
	}

	public BigDecimal getScTran() {
		return this.scTran;
	}

	public void setScTran(BigDecimal scTran) {
		this.scTran = scTran;
	}

	public BigDecimal getTlog() {
		return this.tlog;
	}

	public void setTlog(BigDecimal tlog) {
		this.tlog = tlog;
	}

	public OrderStatusEnum getStatusEnum() {
		return this.statusEnum;
	}

	public void setStatusEnum(OrderStatusEnum status) {
		this.statusEnum = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getORDERID() {
		return ORDERID;
	}

	public void setORDERID(String oRDERID) {
		ORDERID = oRDERID;
	}
	
	
	public enum OrderStatusEnum {

		QUOTE(10), NEW(20), OPEN(30), CANCELLED(40), IN_PROGRESS(50), COMPELETED(60), DELIVERED(70), 
		RETURNED(80), PAYMENT(90), UNKNOWN(100);

		int value;

		OrderStatusEnum(int value) {
			this.value = value;
		}

	};
}