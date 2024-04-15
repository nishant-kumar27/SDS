package com.test.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the RECEIVABLES_DETAILS database table.
 * 
 */
@Entity
@Table(name="RECEIVABLES_DETAILS")
@Cacheable(false)
@NamedQuery(name="ReceivablesDetail.findAll", query="SELECT r FROM ReceivablesDetail r")
public class ReceivablesDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int age;
	
	@EmbeddedId
	private ReceivablesDetailPK id;
		
	@Column(name="BALANCE_DUE")
	private BigDecimal balanceDue;

	@Column(name="CUSTOMER_NAME")
	private String customerName;


	@Column(name="INVOICE_STATUS")
	private String invoiceStatus;

	@Column(name="INVOICE_TOTAL")
	private BigDecimal invoiceTotal;

	@Column(name="ORDER_ID")
	private String orderId;

	private BigDecimal paid;

	@Column(name="PAYMENT_STATUS")
	private String paymentStatus;

	@Column(name="PAYMENT_TERMS")
	private BigDecimal paymentTerms;

	private BigDecimal quantity;

	
	
	public ReceivablesDetailPK getId() {
		return id;
	}

	public void setId(ReceivablesDetailPK id) {
		this.id = id;
	}

	public ReceivablesDetail() {
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getBalanceDue() {
		return this.balanceDue;
	}

	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	

	public String getInvoiceStatus() {
		return this.invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public BigDecimal getInvoiceTotal() {
		return this.invoiceTotal;
	}

	public void setInvoiceTotal(BigDecimal invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPaid() {
		return this.paid;
	}

	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getPaymentTerms() {
		return this.paymentTerms;
	}

	public void setPaymentTerms(BigDecimal paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}