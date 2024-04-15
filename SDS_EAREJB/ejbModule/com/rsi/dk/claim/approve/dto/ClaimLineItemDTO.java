package com.rsi.dk.claim.approve.dto;

import java.math.BigDecimal;

public class ClaimLineItemDTO {
	
	private String retailStoreId;   
	private String workStationId;
	private BigDecimal tranSeqNO;
	private String businessDate;
	
	public String getRetailStoreId() {
		return retailStoreId;
	}
	public void setRetailStoreId(String retailStoreId) {
		this.retailStoreId = retailStoreId;
	}
	public String getWorkStationId() {
		return workStationId;
	}
	public void setWorkStationId(String workStationId) {
		this.workStationId = workStationId;
	}
	public BigDecimal getTranSeqNO() {
		return tranSeqNO;
	}
	public void setTranSeqNO(BigDecimal tranSeqNO) {
		this.tranSeqNO = tranSeqNO;
	}
	public String getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}	
	

}
