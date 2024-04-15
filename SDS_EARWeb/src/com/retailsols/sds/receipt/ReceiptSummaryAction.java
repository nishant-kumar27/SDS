package com.retailsols.sds.receipt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.test.entities.OrderDetailsWithInvoice;

import rispl.db.model.payment.RisplDkArPaym;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class ReceiptSummaryAction extends DSAction{

	private static final long serialVersionUID = 1L;
	private String receiptId;
	private RisplDkArPaym receipt;
	private List<OrderDetailsWithInvoice> invoice;
//	static Log logger = LogFactory.getLog(ReceiptSummaryAction.class);
	private String custName;
	
	
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public List<OrderDetailsWithInvoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<OrderDetailsWithInvoice> invoice) {
		this.invoice = invoice;
	}

	public RisplDkArPaym getReceipt() {
		return receipt;
	}

	public void setReceipt(RisplDkArPaym receipt) {
		this.receipt = receipt;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	@Override
	public String execute() throws Exception {
		
		try{
		LookUpReceiptDetailsIfc receiptIfc = DKartContext.getCustomerReceiptDetails();
		LookUpCustomerIfc customerIfc = DKartContext.getLookUpCustomer();
			CustomerSearchCriteria custCriteria = new CustomerSearchCriteria();
			
		// get receipt details
		List<RisplDkArPaym> receipts = receiptIfc.getReceiptDetails(receiptId);
		setReceipt(receipts.get(0));
		custCriteria.setCustomerId(receipt.getCustId());
		
		CustomerIfc customer = customerIfc.lookUpCust(custCriteria)[0];
		setCustName(customer.getCustomerHeader().getCtNm());
		List<OrderDetailsWithInvoice> custInvoices = new ArrayList<OrderDetailsWithInvoice>();
		Iterator<RisplDkArPaym> receiptItr = receipts.iterator();
		while(receiptItr.hasNext()){
			RisplDkArPaym receipt = (RisplDkArPaym)receiptItr.next();
			custInvoices.add(receiptIfc.getCustomerInvoiceDetails(receipt.getId().getArInvNum()));
		}
		
		setInvoice(custInvoices);
		
		return SUCCESS;
		}catch(Exception e){
//			logger.error("Error Occured in fetching the receipt details for the customer",e);
			return ERROR;
		}
		
	}
}
