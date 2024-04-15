package rispl.dkart.order.lookup.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReturnedOrdersVO implements Serializable{

	private String orderId;
	
	private Date orderDate;
	
	private BigDecimal OrderTotal; 
	
	private String customerId;
	
	private String customerName;
	
	private String salesAgent;
	
	private String reasonCode;

	private String originalOrderId;
	
	private String acceptedClaimId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	

	public BigDecimal getOrderTotal() {
		return OrderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		OrderTotal = orderTotal;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSalesAgent() {
		return salesAgent;
	}

	public void setSalesAgent(String salesAgent) {
		this.salesAgent = salesAgent;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOriginalOrderId() {
		return originalOrderId;
	}

	public void setOriginalOrderId(String originalOrderId) {
		this.originalOrderId = originalOrderId;
	}

	public String getAcceptedClaimId() {
		return acceptedClaimId;
	}

	public void setAcceptedClaimId(String acceptedClaimId) {
		this.acceptedClaimId = acceptedClaimId;
	}

	@Override
	public String toString() {
		return "ReturnedOrdersVO [orderId=" + orderId + ", orderDate=" + orderDate + ", OrderTotal=" + OrderTotal
				+ ", customerId=" + customerId + ", customerName=" + customerName + ", salesAgent=" + salesAgent
				+ ", reasonCode=" + reasonCode + "]";
	}
}
