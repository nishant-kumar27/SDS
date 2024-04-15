package rispl.dk.customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import rispl.db.model.creditmemo.RisplDkArCreditMemo;
import rispl.db.model.payment.RisplDkArPaym;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.customer.CustomerLimit;
import rispl.dkart.services.entities.customer.CustomerPaymentTerm;
import rispl.dkart.services.entities.customer.CustomerSite;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;

public class Customer implements CustomerIfc {

	private static final long serialVersionUID = 1L;

	private CustomerHeader customerHeader;

	private List<CustomerSite> customerSite;

	private CustomerLimit customerlimit;

	private List<CustomerOrderDetailsIfc> customerOrder;

	private CustomerPaymentTerm paymentTerms;

	private List<CustomerSiteInvoice> siteInvoices;

	private String customerSegmentID;

	private Map customerTotals;

	private List<RisplDkArPaym> customerReceipts;
	
	private List<RisplDkArCreditMemo> customerCreditMemos;
	
	private List<CustomerSiteAddress> customerSiteAddress;
	
	private BigDecimal maxCrdtLmtOvrrdAllwd;

	/*
	 * private List<CustomerPaymentTerms> paymentTerms;
	 * 
	 * 
	 * 
	 * private List<CustomerSiteAddress> customerSiteAddress;
	 * 
	 * private List<CustomerSiteInvoice> customerSiteInvoice;
	 * 
	 * private List<CustomerSiteLimit> customerSiteLimit;
	 * 
	 * private List<CustomerSitePhone> customerSitePhone;
	 * 
	 * private CustomerSiteStore customerSiteStore;
	 */

	public List<CustomerSiteInvoice> getSiteInvoices() {
		return siteInvoices;
	}

	public void setSiteInvoices(List<CustomerSiteInvoice> siteInvoices) {
		this.siteInvoices = siteInvoices;
	}

	public CustomerPaymentTerm getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(CustomerPaymentTerm paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public CustomerHeader getCustomerHeader() {
		return customerHeader;
	}

	public void setCustomerHeader(CustomerHeader customerHeader) {
		this.customerHeader = customerHeader;
	}

	public List<CustomerSite> getCustomerSite() {
		return customerSite;
	}

	public void setCustomerSite(List<CustomerSite> customerSite) {
		this.customerSite = customerSite;
	}

	public void setCustomerLimits(CustomerLimit limits) {
		this.customerlimit = limits;
	}

	public CustomerLimit getCustomerLimits() {

		return this.customerlimit;
	}

	public List<CustomerOrderDetailsIfc> getCustomerOrderDetails() {

		return this.customerOrder;
	}

	public void setCustomerOrderDetails(List<CustomerOrderDetailsIfc> details) {

		this.customerOrder = details;
	}

	@Override
	public String getCustomerSegmentID() {
		return customerSegmentID;
	}

	@Override
	public void setCustomerSegmentID(String customerSegmentID) {
		this.customerSegmentID = customerSegmentID;
	}

	@Override
	public Map<Integer, Object> getCustomerTotals() {
		return customerTotals;
	}

	@Override
	public void setCustomerTotals(Map<Integer, Object> customerTotals) {
		this.customerTotals = customerTotals;

	}

	@Override
	public void setCustomerReceipts(List<RisplDkArPaym> customerReceipts) {
		this.customerReceipts = customerReceipts;

	}

	@Override
	public List<RisplDkArPaym> getCustomerReceipts() {
		return customerReceipts;
	}

	@Override
	public void setCustomerCreditMemos(List<RisplDkArCreditMemo> customerCreditMemos) {
		this.customerCreditMemos = customerCreditMemos;
		
	}

	@Override
	public List<RisplDkArCreditMemo> getCustomerCreditMemos() {
		// TODO Auto-generated method stub
		return customerCreditMemos;
	}

	@Override
	public List<CustomerSiteAddress> getCustomerSiteAddress() {
		// TODO Auto-generated method stub
		return customerSiteAddress;
	}

	@Override
	public void setCustomerSiteAddress(List<CustomerSiteAddress> customerSiteAddress) {
		// TODO Auto-generated method stub
		this.customerSiteAddress=customerSiteAddress;
	}

	/*
	 * public List<CustomerPaymentTerms> getPaymentTerms() { return
	 * paymentTerms; }
	 * 
	 * public void setPaymentTerms(List<CustomerPaymentTerms> paymentTerms) {
	 * this.paymentTerms = paymentTerms; }
	 * 
	 * 
	 * 
	 * public List<CustomerSiteAddress> getCustomerSiteAddress() { return
	 * customerSiteAddress; }
	 * 
	 * public void setCustomerSiteAddress(List<CustomerSiteAddress>
	 * customerSiteAddress) { this.customerSiteAddress = customerSiteAddress; }
	 * 
	 * public List<CustomerSiteInvoice> getCustomerSiteInvoice() { return
	 * customerSiteInvoice; }
	 * 
	 * public void setCustomerSiteInvoice(List<CustomerSiteInvoice>
	 * customerSiteInvoice) { this.customerSiteInvoice = customerSiteInvoice; }
	 * 
	 * public List<CustomerSiteLimit> getCustomerSiteLimit() { return
	 * customerSiteLimit; }
	 * 
	 * public void setCustomerSiteLimit(List<CustomerSiteLimit>
	 * customerSiteLimit) { this.customerSiteLimit = customerSiteLimit; }
	 * 
	 * public List<CustomerSitePhone> getCustomerSitePhone() { return
	 * customerSitePhone; }
	 * 
	 * public void setCustomerSitePhone(List<CustomerSitePhone>
	 * customerSitePhone) { this.customerSitePhone = customerSitePhone; }
	 * 
	 * public CustomerSiteStore getCustomerSiteStore() { return
	 * customerSiteStore; }
	 * 
	 * public void setCustomerSiteStore(CustomerSiteStore customerSiteStore) {
	 * this.customerSiteStore = customerSiteStore; }
	 */
	
	public BigDecimal getMaxCrdtLmtOvrrdAllwd() {
		return maxCrdtLmtOvrrdAllwd;
	}

	public void setMaxCrdtLmtOvrrdAllwd(BigDecimal maxCrdtLmtOvrrdAllwd) {
		this.maxCrdtLmtOvrrdAllwd = maxCrdtLmtOvrrdAllwd;
	}

}
