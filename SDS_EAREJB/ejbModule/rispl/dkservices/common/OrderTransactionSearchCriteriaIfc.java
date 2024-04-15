package rispl.dkservices.common;

import java.io.Serializable;

public interface OrderTransactionSearchCriteriaIfc extends Serializable {

	public String getInvoiceNumberOrOrderNumber();

	public void setInvoiceNumberOrOrderNumber(String invoiceNumberOrOrderNumber);

	public boolean isSearchByinvoiceNumberOrOrderNumber();

	public void setSearchByinvoiceNumberOrOrderNumber(boolean searchByinvoiceNumberOrOrderNumber);

	public CustomerSearchCriteriaIfc getCustomerInfo();

	public void setCustomerInfo(CustomerSearchCriteriaIfc customerInfo);

	public boolean isSearchByCustomerInfo();

	public void setSearchByCustomerInfo(boolean searchByCustomerInfo);

	public String getInvoiceTotalFrom();

	public void setInvoiceTotalFrom(String invoiceTotalFrom);

	public String getInvoiceTotalTo();

	public void setInvoiceTotalTo(String invoiceTotalTo);

	public boolean isSearchByInvoiceTotal();

	public void setSearchByInvoiceTotal(boolean searchByInvoiceTotal);

	public int getTypeOfInvoice();

	public void setTypeOfInvoice(int typeOfInvoice);
	
	public void setMaximumResults(int maximum);
	
	public int getMaximumResults();
	
	
	public String getOrderDateRangeTo() ;

	public void setOrderDateRangeTo(String orderDateRangeTo) ;

	public String getOrderDateRangeFrom() ;

	public void setOrderDateRangeFrom(String orderDateRangeFrom) ;

	public String getInvoiceDateRangeTo() ;

	public void setInvoiceDateRangeTo(String invoiceDateRangeTo) ;

	public String getInvoiceDateRangeFrom() ;

	public void setInvoiceDateRangeFrom(String invoiceDateRangeFrom) ;

	public String getItemIdOrDescription();

	public void setItemIdOrDescription(String itemIdOrDescription) ;

	public String getOrderTotalFrom();

	public void setOrderTotalFrom(String orderTotalFrom) ;

	public String getOrderTotalTo() ;
	public void setOrderTotalTo(String orderTotalTo) ;
	
	public void setFromDeliverySearch(boolean flag);
	public boolean isFromDeliverySearch();
//mudassir flag for deliveredOrderSearcchBy item
	public boolean deliveredOrderSearchWithItem();
	public void setDeliveredORderSearchByItem(boolean flag);
	public void setDeliveredOrderCustIdOrDes(String val);
	public String getDelvCustIdorDesc();
}
