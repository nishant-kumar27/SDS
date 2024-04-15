package com.retailsols.sds.creditmemo;

import java.math.BigDecimal;

public class CreditMemoBean {

	private String crediMemoId;
	private String custId;
	private String crMemoDate;
	private BigDecimal crMemoAmount;
	private String claimId;
	
	
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	public String getCrediMemoId() {
		return crediMemoId;
	}
	public void setCrediMemoId(String crediMemoId) {
		this.crediMemoId = crediMemoId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCrMemoDate() {
		return crMemoDate;
	}
	public void setCrMemoDate(String crMemoDate) {
		this.crMemoDate = crMemoDate;
	}
	public BigDecimal getCrMemoAmount() {
		return crMemoAmount;
	}
	public void setCrMemoAmount(BigDecimal crMemoAmount) {
		this.crMemoAmount = crMemoAmount;
	}
	
	
}
