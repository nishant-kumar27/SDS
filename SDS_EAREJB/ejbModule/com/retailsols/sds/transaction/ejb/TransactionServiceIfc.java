package com.retailsols.sds.transaction.ejb;

import javax.ejb.Remote;

import rispl.dkart.services.entities.transaction.OrderTranHeader;

@Remote
public interface TransactionServiceIfc {
	
	// this method will create a new order in the database with header details and staTus 
	// as In-Progress and returns the empty Transaction Object
	public OrderTranHeader getNewTransaction(String custId, String tranType, String empId);
	
	// get store id based on the customer id, segment, divison
	public String getStoreId(String custID);
	
	public long getTranSeq(String storeId);
	
	
}
