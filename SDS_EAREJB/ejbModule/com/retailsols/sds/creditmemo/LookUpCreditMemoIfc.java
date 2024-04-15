package com.retailsols.sds.creditmemo;

import javax.ejb.Remote;

import rispl.dkart.services.entities.transaction.OrderTranHeader;

@Remote
public interface LookUpCreditMemoIfc {

	public CreditMemoBean getCreditMemoDetails(String crMemoId);
	
	public OrderTranHeader getClaimDetailsByClaimID(String claimId);
}
