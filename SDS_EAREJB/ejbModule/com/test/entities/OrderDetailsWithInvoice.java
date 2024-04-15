package com.test.entities;

import java.io.Serializable;
import javax.persistence.*;

import rispl.dkart.order.lookup.dao.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ORDER_DETAILS_WITH_INVOICE database table.
 * 
 */
@Entity
@Table(name="ORDER_DETAILS_WITH_INVOICE")
@Cacheable(value=false)
@NamedQuery(name="OrderDetailsWithInvoice.findAll", query="SELECT o FROM OrderDetailsWithInvoice o")
public class OrderDetailsWithInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderDetailsWithInvoicePK id;
	
	@Transient
	private String status;

	@Column(name="ORDER_TOTAL")
	private BigDecimal orderTotal;
	
	@Column(name="ID_OPR")
	private String idOpr;
	
	@Column(name="CUST_ID")
	private String custId;

	@Column(name="CUSTOMER_NAME")
	private String customerName;

	@Column(name="DIVISION_ID")
	private BigDecimal divisionId;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_DATE")
	private Date effectiveDate;

	@Column(name="EMPLOYEE_ID")
	private String employeeId;

	@Column(name="INVOICE_AMOUNT")
	private BigDecimal invoiceAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="INVOICE_DATE")
	private Date invoiceDate;

	@Column(name="INVOICE_ID")
	private String invoiceId;

	@Column(name="LPO_NUMBER")
	private String lpoNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="ORDER_DATE")
	private Date orderDate;

	@Column(name="ORDER_TYPE")
	private String ordTy;

	@Column(name="SALES_AGENT")
	private String salesAgent;

	@Column(name="SC_ORD")
	private BigDecimal scOrd;

	@Column(name="SC_TRAN")
	private BigDecimal scTran;

	private BigDecimal tlog;
	
	@Column(name="ITEM_COUNT")
	private BigDecimal itemCount;
	
	
	@Column(name="DLVR_DATE")
	private Date deliveryDate;

	public OrderDetailsWithInvoice() {
	
	}
	
	
	public OrderDetailsWithInvoicePK getId() {
		return id;
	}



	public void setId(OrderDetailsWithInvoicePK id) {
		this.id = id;
	}

	public String getStatus() {
		status = OrderStatus.UNKNOWN;
		if (ordTy.equals("25")){
			status = OrderStatus.CANCELLED;		
		}
		else if(scOrd!=null && scTran!=null && ordTy!=null){
			 if (ordTy.equals("2") && scOrd != null)
				status = OrderStatus.RETURNED;
			else if (ordTy.equals("18") && scOrd != null)
				status = OrderStatus.PAYMENT;
			else if (scOrd.compareTo(new BigDecimal(0)) <= 0 &&  scTran.compareTo(new BigDecimal(2)) == 0)
				status = OrderStatus.NEW;
			else if (scOrd.compareTo(new BigDecimal(1)) <= 0 && scTran.compareTo(new BigDecimal(2)) == 0)
				status = OrderStatus.OPEN;
			else if (scOrd.compareTo(new BigDecimal(3)) <= 0 && scTran.compareTo(new BigDecimal(2)) == 0)
				status = OrderStatus.IN_PROGRESS;
			else if (scOrd.compareTo(new BigDecimal(5)) <= 0 && scTran.compareTo(new BigDecimal(2)) == 0)
				status = OrderStatus.COMPELETED;
			else if (scOrd.compareTo(new BigDecimal(7)) <= 0 && scTran.compareTo(new BigDecimal(2)) == 0)
				status = OrderStatus.DELIVERED;
			else if (scOrd.compareTo(new BigDecimal(0)) <= 0 && scTran.compareTo(new BigDecimal(4)) == 0)
				status = OrderStatus.QUOTE;
			else
				status = OrderStatus.UNKNOWN;
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getLpoNumber() {
		return this.lpoNumber;
	}

	public void setLpoNumber(String lpoNumber) {
		this.lpoNumber = lpoNumber;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	
	
	public String getOrdTy() {
		return ordTy;
	}



	public void setOrdTy(String ordTy) {
		this.ordTy = ordTy;
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



	public String getIdOpr() {
		return idOpr;
	}



	public void setIdOpr(String idOpr) {
		this.idOpr = idOpr;
	}



	public BigDecimal getOrderTotal() {
		return orderTotal;
	}



	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}



	public BigDecimal getItemCount() {
		return itemCount;
	}



	public void setItemCount(BigDecimal itemCount) {
		this.itemCount = itemCount;
	}



	public Date getDeliveryDate() {
		return deliveryDate;
	}



	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((divisionId == null) ? 0 : divisionId.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idOpr == null) ? 0 : idOpr.hashCode());
		result = prime * result + ((invoiceAmount == null) ? 0 : invoiceAmount.hashCode());
		result = prime * result + ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
		result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
		result = prime * result + ((itemCount == null) ? 0 : itemCount.hashCode());
		result = prime * result + ((lpoNumber == null) ? 0 : lpoNumber.hashCode());
		result = prime * result + ((ordTy == null) ? 0 : ordTy.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderTotal == null) ? 0 : orderTotal.hashCode());
		result = prime * result + ((salesAgent == null) ? 0 : salesAgent.hashCode());
		result = prime * result + ((scOrd == null) ? 0 : scOrd.hashCode());
		result = prime * result + ((scTran == null) ? 0 : scTran.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tlog == null) ? 0 : tlog.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsWithInvoice other = (OrderDetailsWithInvoice) obj;
		if (custId == null) {
			if (other.custId != null)
				return false;
		} else if (!custId.equals(other.custId))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (divisionId == null) {
			if (other.divisionId != null)
				return false;
		} else if (!divisionId.equals(other.divisionId))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idOpr == null) {
			if (other.idOpr != null)
				return false;
		} else if (!idOpr.equals(other.idOpr))
			return false;
		if (invoiceAmount == null) {
			if (other.invoiceAmount != null)
				return false;
		} else if (!invoiceAmount.equals(other.invoiceAmount))
			return false;
		if (invoiceDate == null) {
			if (other.invoiceDate != null)
				return false;
		} else if (!invoiceDate.equals(other.invoiceDate))
			return false;
		if (invoiceId == null) {
			if (other.invoiceId != null)
				return false;
		} else if (!invoiceId.equals(other.invoiceId))
			return false;
		if (itemCount == null) {
			if (other.itemCount != null)
				return false;
		} else if (!itemCount.equals(other.itemCount))
			return false;
		if (lpoNumber == null) {
			if (other.lpoNumber != null)
				return false;
		} else if (!lpoNumber.equals(other.lpoNumber))
			return false;
		if (ordTy == null) {
			if (other.ordTy != null)
				return false;
		} else if (!ordTy.equals(other.ordTy))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderTotal == null) {
			if (other.orderTotal != null)
				return false;
		} else if (!orderTotal.equals(other.orderTotal))
			return false;
		if (salesAgent == null) {
			if (other.salesAgent != null)
				return false;
		} else if (!salesAgent.equals(other.salesAgent))
			return false;
		if (scOrd == null) {
			if (other.scOrd != null)
				return false;
		} else if (!scOrd.equals(other.scOrd))
			return false;
		if (scTran == null) {
			if (other.scTran != null)
				return false;
		} else if (!scTran.equals(other.scTran))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tlog == null) {
			if (other.tlog != null)
				return false;
		} else if (!tlog.equals(other.tlog))
			return false;
		return true;
	}
	
	

}