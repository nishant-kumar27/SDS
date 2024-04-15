package rispl.dk.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rispl.db.model.creditmemo.RisplDkArCreditMemo;
import rispl.db.model.payment.RisplDkArPaym;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.customer.CustomerLimit;
import rispl.dkart.services.entities.customer.CustomerPaymentTerm;
import rispl.dkart.services.entities.customer.CustomerSite;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;

public interface CustomerIfc extends Serializable {

	/*
	 * public List<CustomerPaymentTerms> getPaymentTerms() ;
	 * 
	 * public void setPaymentTerms(List<CustomerPaymentTerms> paymentTerms) ;
	 * 
	 * 
	 * 
	 * public List<CustomerSiteAddress> getCustomerSiteAddress() ;
	 * 
	 * public void setCustomerSiteAddress(List<CustomerSiteAddress> customerSiteAddress) ;
	 * 
	 * public List<CustomerSiteInvoice> getCustomerSiteInvoice() ;
	 * 
	 * public void setCustomerSiteInvoice(List<CustomerSiteInvoice>
	 * customerSiteInvoice) ;
	 * 
	 * public List<CustomerSiteLimit> getCustomerSiteLimit() ;
	 * 
	 * public void setCustomerSiteLimit(List<CustomerSiteLimit>
	 * customerSiteLimit) ;
	 * 
	 * public List<CustomerSitePhone> getCustomerSitePhone() ;
	 * 
	 * public void setCustomerSitePhone(List<CustomerSitePhone>
	 * customerSitePhone) ;
	 * 
	 * public CustomerSiteStore getCustomerSiteStore() ;
	 * 
	 * 
	 * public void setCustomerSiteStore(CustomerSiteStore customerSiteStore) ;
	 */

	public List<CustomerSiteAddress> getCustomerSiteAddress() ;
	
	public void setCustomerSiteAddress(List<CustomerSiteAddress> customerSiteAddress) ;
	
	public List<CustomerSite> getCustomerSite();

	public void setCustomerSite(List<CustomerSite> customerSite);

	public CustomerHeader getCustomerHeader();

	public void setCustomerHeader(CustomerHeader customerHeader);

	public void setCustomerLimits(CustomerLimit limits);

	public CustomerLimit getCustomerLimits();

	public List<CustomerOrderDetailsIfc> getCustomerOrderDetails();

	public void setCustomerOrderDetails(List<CustomerOrderDetailsIfc> details);

	public CustomerPaymentTerm getPaymentTerms();

	public List<CustomerSiteInvoice> getSiteInvoices();

	public void setSiteInvoices(List<CustomerSiteInvoice> siteInvoices);

	public void setPaymentTerms(CustomerPaymentTerm paymentTerms);

	public String getCustomerSegmentID();

	public void setCustomerSegmentID(String customerSegmentID);

	public Map<Integer, Object> getCustomerTotals();

	public void setCustomerTotals(Map<Integer, Object> customerTotals);

	public void setCustomerReceipts(List<RisplDkArPaym> customerReceipts);

	public List<RisplDkArPaym> getCustomerReceipts();
	
	public void setCustomerCreditMemos(List<RisplDkArCreditMemo> customerCreditMemos);

	public List<RisplDkArCreditMemo> getCustomerCreditMemos();
	
	public BigDecimal getMaxCrdtLmtOvrrdAllwd();

	public void setMaxCrdtLmtOvrrdAllwd(BigDecimal maxCrdtLmtOvrrdAllwd);
}
