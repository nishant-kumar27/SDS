package com.rispl.cancel.order.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the RISPL_DK_CANCEL_ORDER_SEARCH_V database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_CANCEL_ORDER_SEARCH_V")
@NamedQuery(name="RisplDkCancelOrderSearchV.findAll", query="SELECT r FROM RisplDkCancelOrderSearchV r")
public class RisplDkCancelOrderSearchV implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID")
	private String custId;

	@Column(name="CUSTOMER_NAME")
	private String customerName;

	@Column(name="DELIVERY_COMMENT")
	private String deliveryComment;

	@Column(name="EMP_NAME")
	private String empName;

	@Column(name="LPO_NUMBER")
	private String lpoNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="ORD_EF_DATE")
	private Date ordEfDate;

	@Column(name="ORD_WS")
	private String ordWs;

	@Temporal(TemporalType.DATE)
	@Column(name="ORDER_DATE")
	private Date orderDate;

	@Id
	@Column(name="ORDER_ID")
	private String orderId;

	@Column(name="ORDER_TOTAL")
	private BigDecimal orderTotal;

	@Column(name="RT_STR_ID")
	private String rtStrId;

	@Column(name="SALES_AGENT")
	private String salesAgent;

	@Column(name="SC_ORD")
	private BigDecimal scOrd;

	@Column(name="TOTAL_COUNT_ITEMS")
	private BigDecimal totalCountItems;

	@Column(name="TRN_SEQ")
	private BigDecimal trnSeq;
	
	@Column(name="DIVISION_ID")
	private String divisionID;
	
	@Column(name="REASON_CODE")
	private String reasonCode;
	

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getDivisionID() {
		return divisionID;
	}

	public void setDivisionID(String divisionID) {
		this.divisionID = divisionID;
	}

	public RisplDkCancelOrderSearchV() {
	}

	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeliveryComment() {
		return this.deliveryComment;
	}

	public void setDeliveryComment(String deliveryComment) {
		this.deliveryComment = deliveryComment;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getLpoNumber() {
		return this.lpoNumber;
	}

	public void setLpoNumber(String lpoNumber) {
		this.lpoNumber = lpoNumber;
	}

	public Date getOrdEfDate() {
		return this.ordEfDate;
	}

	public void setOrdEfDate(Date ordEfDate) {
		this.ordEfDate = ordEfDate;
	}

	public String getOrdWs() {
		return this.ordWs;
	}

	public void setOrdWs(String ordWs) {
		this.ordWs = ordWs;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getRtStrId() {
		return this.rtStrId;
	}

	public void setRtStrId(String rtStrId) {
		this.rtStrId = rtStrId;
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

	public BigDecimal getTotalCountItems() {
		return this.totalCountItems;
	}

	public void setTotalCountItems(BigDecimal totalCountItems) {
		this.totalCountItems = totalCountItems;
	}

	public BigDecimal getTrnSeq() {
		return this.trnSeq;
	}

	public void setTrnSeq(BigDecimal trnSeq) {
		this.trnSeq = trnSeq;
	}

}