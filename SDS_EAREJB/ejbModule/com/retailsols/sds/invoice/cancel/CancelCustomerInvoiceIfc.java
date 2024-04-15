package com.retailsols.sds.invoice.cancel;

import javax.ejb.Remote;

@Remote
public interface CancelCustomerInvoiceIfc {

	public boolean isInvoiceAlreadyCancelled(String invoiceId, String orderId);
	
	public boolean isOfflineInvoice(String invoiceId);
	
	public boolean isPaymentRecevied(String invoiceId);
	
	public boolean isMarkedAsDelivered(String orderId);
}
