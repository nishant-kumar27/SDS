package com.retailsols.sds.receipt;

import java.util.List;

import javax.ejb.Remote;

import com.test.entities.OrderDetailsWithInvoice;

import rispl.db.model.payment.RisplDkArPaym;

@Remote
public interface LookUpReceiptDetailsIfc {

	public List<RisplDkArPaym> getReceiptDetails(String reciptID);
	
	public OrderDetailsWithInvoice getCustomerInvoiceDetails(String invoiceID);
	
	public List<String> getAppliedInvoicesForReceipt(String receiptId);
}
