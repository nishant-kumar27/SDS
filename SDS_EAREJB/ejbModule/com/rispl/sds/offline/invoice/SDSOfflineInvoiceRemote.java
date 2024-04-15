package com.rispl.sds.offline.invoice;

import javax.ejb.Remote;

@Remote
public interface SDSOfflineInvoiceRemote {
	
	public static final String OFFLINE_INVOICE ="SELECT DISTINCT INVOICE.trnSeq,INVOICE.custId,INVOICE.dcDyOrd,INVOICE.idOrd,INVOICE.invId,INVOICE.siteId,INVOICE.timeInterval,INVOICE.total FROM SdsOfflineInvoiceV INVOICE WHERE INVOICE.timeInterval >= :TIMEINTERVAL";
	
	public static final String OFFLINE_INVOICE_LINE="SELECT * FROM  SDS_OFFLINE_INVOICE_V WHERE ID_ORD = ? ORDER BY ORD_LN_ITM_SEQ";
	
	public void generateOfflineInvoice();

}