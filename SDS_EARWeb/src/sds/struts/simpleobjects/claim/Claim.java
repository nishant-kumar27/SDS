package sds.struts.simpleobjects.claim;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import rispl.dkart.services.entities.customer.CustomerSite;

public class Claim {
	private String claimID;
	private String customerID;
	private String customerName;
	private String customerSegment;
	private String originalInvoiceID;
	private String originalOrderID;
	private String claimReasonCode;
	private String claimReasonCodeDesc;
	private String salesAgent;
	private Integer claimStatus;
	private Date claimDate;
	private Date claimRejectedDate;
	private BigDecimal restockingFee;
	private BigDecimal transportFee;
	private String claimApprovedBy = "";
	private String claimRejectedBy;
	private String claimRejectComment;
	private List<ClaimItem> claimItemsList;
	private BigDecimal claimTotal;
	private String claimQty;
	private List<CustomerSite> customerSites;
	/**
	 * The SalesAgentId for which the claim is registered
	 */
	private String salesAgentId;
	private String site_address;
	private String siteId;
	public String getClaimID() {
		return claimID;
	}

	public void setClaimID(String claimID) {
		this.claimID = claimID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSegment() {
		return customerSegment;
	}

	public void setCustomerSegment(String customerSegment) {
		this.customerSegment = customerSegment;
	}

	public String getOriginalInvoiceID() {
		return originalInvoiceID;
	}

	public void setOriginalInvoiceID(String originalInvoiceID) {
		this.originalInvoiceID = originalInvoiceID;
	}

	public String getClaimReasonCode() {
		return claimReasonCode;
	}

	public void setClaimReasonCode(String claimReasonCode) {
		this.claimReasonCode = claimReasonCode;
	}

	public String getClaimReasonCodeDesc() {
		return claimReasonCodeDesc;
	}

	public void setClaimReasonCodeDesc(String claimReasonCodeDesc) {
		this.claimReasonCodeDesc = claimReasonCodeDesc;
	}

	public String getSalesAgent() {
		return salesAgent;
	}

	public void setSalesAgent(String salesAgent) {
		this.salesAgent = salesAgent;
	}

	public Integer getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(Integer claimStatus) {
		this.claimStatus = claimStatus;
	}

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public Date getClaimRejectedDate() {
		return claimRejectedDate;
	}

	public void setClaimRejectedDate(Date claimRejectedDate) {
		this.claimRejectedDate = claimRejectedDate;
	}

	public BigDecimal getRestockingFee() {
		return restockingFee;
	}

	public void setRestockingFee(BigDecimal restockingFee) {
		this.restockingFee = restockingFee;
	}

	public BigDecimal getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(BigDecimal transportFee) {
		this.transportFee = transportFee;
	}

	public String getClaimApprovedBy() {
		return claimApprovedBy;
	}

	public void setClaimApprovedBy(String claimApprovedBy) {
		this.claimApprovedBy = claimApprovedBy;
	}

	public String getClaimRejectedBy() {
		return claimRejectedBy;
	}

	public void setClaimRejectedBy(String claimRejectedBy) {
		this.claimRejectedBy = claimRejectedBy;
	}

	public String getClaimRejectComment() {
		return claimRejectComment;
	}

	public void setClaimRejectComment(String claimRejectComment) {
		this.claimRejectComment = claimRejectComment;
	}

	public List<ClaimItem> getClaimItemsList() {
		return claimItemsList;
	}

	public void setClaimItemsList(List<ClaimItem> claimItemsList) {
		this.claimItemsList = claimItemsList;
	}

	public String getOriginalOrderID() {
		return originalOrderID;
	}

	public void setOriginalOrderID(String originalOrderID) {
		this.originalOrderID = originalOrderID;
	}

	public BigDecimal getClaimTotal() {
		return claimTotal;
	}

	public void setClaimTotal(BigDecimal claimTotal) {
		this.claimTotal = claimTotal;
	}

	public String getClaimQty() {
		return claimQty;
	}

	public void setClaimQty(String claimQty) {
		this.claimQty = claimQty;
	}
	/**
	 * @return salesAgentId
	 */
	public String getSalesAgentId() {
		return salesAgentId;
	}
	/**
	 * @param salesAgentId salesAgentId to set 
	 */
	public void setSalesAgentId(String salesAgentId) {
		this.salesAgentId = salesAgentId;
	}
	
	public List<CustomerSite> getCustomerSites() {
		return customerSites;
	}

	public void setCustomerSites(List<CustomerSite> customerSites) {
		this.customerSites = customerSites;
	}
	
	public String getSite_address() {
		return site_address;
	}

	public void setSite_address(String site_address) {
		this.site_address = site_address;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	@Override
	public String toString() {
		return "Claim [claimID=" + claimID + ", customerID=" + customerID + ", customerName=" + customerName
				+ ", customerSegment=" + customerSegment + ", originalInvoiceID=" + originalInvoiceID
				+ ", originalOrderID=" + originalOrderID + ", claimReasonCode=" + claimReasonCode
				+ ", claimReasonCodeDesc=" + claimReasonCodeDesc + ", salesAgent=" + salesAgent + ", claimStatus="
				+ claimStatus + ", claimDate=" + claimDate + ", claimRejectedDate=" + claimRejectedDate
				+ ", restockingFee=" + restockingFee +", salesAgentId=" + salesAgentId + ", transportFee=" + transportFee + ", claimApprovedBy="
				+ claimApprovedBy + ", claimRejectedBy=" + claimRejectedBy + ", claimRejectComment="
				+ claimRejectComment + ", claimItemsList=" + claimItemsList + ", claimTotal=" + claimTotal
				+ ", getClaimID()=" + getClaimID() + ", getCustomerID()=" + getCustomerID() + ", getCustomerName()="
				+ getCustomerName() + ", getCustomerSegment()=" + getCustomerSegment() + ", getOriginalInvoiceID()="
				+ getOriginalInvoiceID() + ", getClaimReasonCode()=" + getClaimReasonCode()
				+ ", getSalesAgentId()=" + getSalesAgentId()
				+  ", getClaimReasonCodeDesc()=" + getClaimReasonCodeDesc() + ", getSalesAgent()=" + getSalesAgent()
				+ ", getClaimStatus()=" + getClaimStatus() + ", getClaimDate()=" + getClaimDate()
				+ ", getClaimRejectedDate()=" + getClaimRejectedDate() + ", getRestockingFee()=" + getRestockingFee()
				+ ", getTransportFee()=" + getTransportFee() + ", getClaimApprovedBy()=" + getClaimApprovedBy()
				+ ", getClaimRejectedBy()=" + getClaimRejectedBy() + ", getClaimRejectComment()="
				+ getClaimRejectComment() + ", getClaimItemsList()=" + getClaimItemsList() + ", getOriginalOrderID()="
				+ getOriginalOrderID() + ", getClaimTotal()=" + getClaimTotal() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	

}
