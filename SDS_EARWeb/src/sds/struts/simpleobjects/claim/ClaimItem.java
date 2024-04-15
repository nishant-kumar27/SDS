package sds.struts.simpleobjects.claim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import rispl.db.model.claim.ClaimTranDscItm;

public class ClaimItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -24532921555342487L;
	private String itemID;
	private String itemDescription;
	private BigDecimal quantitySold;
	private BigDecimal priceSoldAt;
	private BigDecimal netPrice;
	private BigDecimal unitPrice;
	private BigDecimal claimQuantityRegistered;
	private String claimReasonCode;
	private String claimReasonCodeValue;
	private BigDecimal claimPriceRegistered;
	private String priceChangeReasonCode;
	private BigDecimal claimQuantityApproved;
	private BigDecimal claimPriceApproved;
	private BigDecimal claimQuantityReceived;
	private BigDecimal taxAmount;
	private BigDecimal discountAmount = BigDecimal.ZERO;
	private BigDecimal discountPercent = BigDecimal.ZERO;
	private BigDecimal itemLevelDiscAmt = BigDecimal.ZERO;
	private BigDecimal itemLevelDiscPer = BigDecimal.ZERO;
	private String tranDiscDsc;
	private String itemDiscDsc;
	private List<ClaimTranDscItm> dscList;
	private Boolean serviceItem = Boolean.FALSE;
	
	public List<ClaimTranDscItm> getDscList() {
		return dscList;
	}
	public void setDscList(List<ClaimTranDscItm> dscList) {
		this.dscList = dscList;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getItemLevelDiscAmt() {
		return itemLevelDiscAmt;
	}
	public void setItemLevelDiscAmt(BigDecimal itemLevelDiscAmt) {
		this.itemLevelDiscAmt = itemLevelDiscAmt;
	}
	public BigDecimal getItemLevelDiscPer() {
		return itemLevelDiscPer;
	}
	public void setItemLevelDiscPer(BigDecimal itemLevelDiscPer) {
		this.itemLevelDiscPer = itemLevelDiscPer;
	}
	public String getTranDiscDsc() {
		return tranDiscDsc;
	}
	public void setTranDiscDsc(String tranDiscDsc) {
		this.tranDiscDsc = tranDiscDsc;
	}
	public String getItemDiscDsc() {
		return itemDiscDsc;
	}
	public void setItemDiscDsc(String itemDiscDsc) {
		this.itemDiscDsc = itemDiscDsc;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public BigDecimal getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(BigDecimal quantitySold) {
		this.quantitySold = quantitySold;
	}
	public BigDecimal getPriceSoldAt() {
		return priceSoldAt;
	}
	public void setPriceSoldAt(BigDecimal priceSoldAt) {
		this.priceSoldAt = priceSoldAt;
	}
	public BigDecimal getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}
	public BigDecimal getClaimQuantityRegistered() {
		return claimQuantityRegistered;
	}
	public void setClaimQuantityRegistered(BigDecimal claimQuantityRegistered) {
		this.claimQuantityRegistered = claimQuantityRegistered;
	}
	public String getClaimReasonCode() {
		return claimReasonCode;
	}
	public void setClaimReasonCode(String claimReasonCode) {
		this.claimReasonCode = claimReasonCode;
	}
	public BigDecimal getClaimPriceRegistered() {
		return claimPriceRegistered;
	}
	public void setClaimPriceRegistered(BigDecimal claimPriceRegistered) {
		this.claimPriceRegistered = claimPriceRegistered;
	}
	public String getPriceChangeReasonCode() {
		return priceChangeReasonCode;
	}
	public void setPriceChangeReasonCode(String priceChangeReasonCode) {
		this.priceChangeReasonCode = priceChangeReasonCode;
	}
	public BigDecimal getClaimQuantityApproved() {
		return claimQuantityApproved;
	}
	public void setClaimQuantityApproved(BigDecimal claimQuantityApproved) {
		this.claimQuantityApproved = claimQuantityApproved;
	}
	public BigDecimal getClaimPriceApproved() {
		return claimPriceApproved;
	}
	public void setClaimPriceApproved(BigDecimal claimPriceApproved) {
		this.claimPriceApproved = claimPriceApproved;
	}
	public BigDecimal getClaimQuantityReceived() {
		return claimQuantityReceived;
	}
	public void setClaimQuantityReceived(BigDecimal claimQuantityReceived) {
		this.claimQuantityReceived = claimQuantityReceived;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
	}
	public Boolean isServiceItem() {
		return serviceItem;
	}
	public void setServiceItem(Boolean serviceItem) {
		this.serviceItem = serviceItem;
	}
	@Override
	public String toString() {
		return "ClaimItem [itemID=" + itemID + ", itemDescription=" + itemDescription + ", quantitySold=" + quantitySold
				+ ", priceSoldAt=" + priceSoldAt + ", netPrice=" + netPrice + ", unitPrice=" + unitPrice
				+ ", claimQuantityRegistered=" + claimQuantityRegistered + ", claimReasonCode=" + claimReasonCode
				+ ", claimReasonCodeValue=" + claimReasonCodeValue + ", claimPriceRegistered=" + claimPriceRegistered
				+ ", priceChangeReasonCode=" + priceChangeReasonCode + ", claimQuantityApproved="
				+ claimQuantityApproved + ", claimPriceApproved=" + claimPriceApproved + ", claimQuantityReceived="
				+ claimQuantityReceived + ", taxAmount=" + taxAmount + ", discountAmount=" + discountAmount
				+ ", discountPercent=" + discountPercent + ", itemLevelDiscAmt=" + itemLevelDiscAmt
				+ ", itemLevelDiscPer=" + itemLevelDiscPer + ", tranDiscDsc=" + tranDiscDsc + ", itemDiscDsc="
				+ itemDiscDsc + ", dscList=" + dscList + ", serviceItem=" + serviceItem + "]";
	}
	public String getClaimReasonCodeValue() {
		return claimReasonCodeValue;
	}
	public void setClaimReasonCodeValue(String claimReasonCodeValue) {
		this.claimReasonCodeValue = claimReasonCodeValue;
	}
	
}
