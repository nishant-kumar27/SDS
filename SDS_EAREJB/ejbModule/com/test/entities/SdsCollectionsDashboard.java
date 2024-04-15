package com.test.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SDS_COLLECTIONS_DASHBOARD database table.
 * 
 */
@Entity
@Table(name="SDS_COLLECTIONS_DASHBOARD")
@Cacheable(false)
@NamedQuery(name="SdsCollectionsDashboard.findAll", query="SELECT s FROM SdsCollectionsDashboard s")
public class SdsCollectionsDashboard implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SdsCollectionsDashboardPK id;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;

	@Column(name="PAYMENT_AMOUNT")
	private BigDecimal paymentAmount;

	@Column(name="PAYMENT_MODE")
	private String paymentMode;
	
	@Column(name="INV_NUM")
	private String invNum;
	
	@Column(name="ORD_ID")
	private String ordId;
	
	@Column(name="EMI_ID")
	private String emId;
	
	@Column(name="EMP_NME")
	private String empNme;

	@Column(name="DIVISION_ID")
	private BigDecimal divisionId;
	
	public SdsCollectionsDashboard() {
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	

	public SdsCollectionsDashboardPK getId() {
		return id;
	}

	public void setId(SdsCollectionsDashboardPK id) {
		this.id = id;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getInvNum() {
		return invNum;
	}

	public void setInvNum(String invNum) {
		this.invNum = invNum;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getEmId() {
		return emId;
	}

	public void setEmId(String emId) {
		this.emId = emId;
	}

	public String getEmpNme() {
		return empNme;
	}

	public void setEmpNme(String empNme) {
		this.empNme = empNme;
	}
	
	

}