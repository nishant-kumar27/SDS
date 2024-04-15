package rispl.dkart.services.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;


public class PaymentDetails implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1878762458992242076L;
	protected BigDecimal totalAmountPaid;
	protected ArrayList <TenderDetails>tenderDetails;
	protected String orderId;
	protected String customerId;
	protected String customerName;
	protected String customerSegmentationId;
	protected String customerInvoiceId;
	protected String operatorId;
	protected String customerSiteID;
	protected String storeId;
	protected String workStationID;
	protected long transactionSequenceID;
	protected String paymentTransactionId;
	protected String businessDate;

	public PaymentDetails(){
		tenderDetails=new ArrayList<TenderDetails>();
		totalAmountPaid=new BigDecimal(0);
	}
	
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getWorkStationID() {
		return workStationID;
	}
	public void setWorkStationID(String workStationID) {
		this.workStationID = workStationID;
	}
	public long getTransactionSequenceID() {
		return transactionSequenceID;
	}
	public void setTransactionSequenceID(long l) {
		this.transactionSequenceID = l;
	}
	public String getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerSiteID() {
		return customerSiteID;
	}


	public void setCustomerSiteID(String customerSiteID) {
		this.customerSiteID = customerSiteID;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerSegmentationId() {
		return customerSegmentationId;
	}


	public void setCustomerSegmentationId(String customerSegmentationId) {
		this.customerSegmentationId = customerSegmentationId;
	}


	public String getCustomerInvoiceId() {
		return customerInvoiceId;
	}


	public void setCustomerInvoiceId(String customerInvoiceId) {
		this.customerInvoiceId = customerInvoiceId;
	}


	public String getOperatorId() {
		return operatorId;
	}


	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}


	public String getOpratorId() {
		return operatorId;
	}
	public void setOpratorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public BigDecimal getTotalAmountPaid() {
		return totalAmountPaid;
	}
	public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}
	public ArrayList<TenderDetails> getTenderDetails() {
		return tenderDetails;
	}
	public void setTenderDetails(ArrayList<TenderDetails> tenderDetails) {
		this.tenderDetails = tenderDetails;
	}
	
	public String getPaymentTransactionId() {
		return paymentTransactionId;
	}


	public void setPaymentTransactionId(String paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}


	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public void calculateTotalAmount() {
		totalAmountPaid=new BigDecimal("0.00");
		for(TenderDetails tenderline:tenderDetails)
		{
			totalAmountPaid=totalAmountPaid.add(tenderline.getTenderAmount());
		}
	}

}
