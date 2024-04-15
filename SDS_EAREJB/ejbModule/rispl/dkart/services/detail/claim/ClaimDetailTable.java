package rispl.dkart.services.detail.claim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ClaimDetailTable implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String claimId;
	private Date claimDate,receivedDate;
	private BigDecimal claimTotal;
	private String customerId;
	private String customerName;
	private String salesAgent;
	private String reasonCode;
	private String qty;
	private String status;
	
	
	
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public BigDecimal getClaimTotal() {
		return claimTotal;
	}
	public void setClaimTotal(BigDecimal claimTotal) {
		this.claimTotal = claimTotal;
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
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	

}
