package rispl.dkservices.common;

public class OrderTransactionSearchCriteria implements OrderTransactionSearchCriteriaIfc {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String invoiceNumberOrOrderNumber;
	
	boolean searchByinvoiceNumberOrOrderNumber;
	
	CustomerSearchCriteriaIfc customerInfo;
	
	boolean searchByCustomerInfo;
	
	String InvoiceTotalFrom;
	
	String InvoiceTotalTo;
	
	//mudassir
	String custIdOrDesc;
	

	
	int maximum;
	
	////////////////////////0 for all ,1 for open and 2 for closed
	int typeOfInvoice;
	
	String orderDateRangeTo;
	
	String orderDateRangeFrom; 
	
	String invoiceDateRangeTo;
	
	String invoiceDateRangeFrom; 
	
	boolean searchByInvoiceTotal;
	
	String itemIdOrDescription;
	
	String orderTotalFrom;
	
	String orderTotalTo;
	
	boolean isFromDeliverySearch=false;
	private boolean deliveredORderSearchByItem=false;
	
	public String getInvoiceNumberOrOrderNumber() {
		return invoiceNumberOrOrderNumber;
	}

	public void setInvoiceNumberOrOrderNumber(String invoiceNumberOrOrderNumber) {
		this.invoiceNumberOrOrderNumber = invoiceNumberOrOrderNumber;
	}

	public boolean isSearchByinvoiceNumberOrOrderNumber() {
		return searchByinvoiceNumberOrOrderNumber;
	}

	public void setSearchByinvoiceNumberOrOrderNumber(boolean searchByinvoiceNumberOrOrderNumber) {
		this.searchByinvoiceNumberOrOrderNumber = searchByinvoiceNumberOrOrderNumber;
	}

	public CustomerSearchCriteriaIfc getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerSearchCriteriaIfc customerInfo) {
		this.customerInfo = customerInfo;
	}

	public boolean isSearchByCustomerInfo() {
		return searchByCustomerInfo;
	}

	public void setSearchByCustomerInfo(boolean searchByCustomerInfo) {
		this.searchByCustomerInfo = searchByCustomerInfo;
	}

	public String getInvoiceTotalFrom() {
		return InvoiceTotalFrom;
	}

	public void setInvoiceTotalFrom(String invoiceTotalFrom) {
		InvoiceTotalFrom = invoiceTotalFrom;
	}

	public String getInvoiceTotalTo() {
		return InvoiceTotalTo;
	}

	public void setInvoiceTotalTo(String invoiceTotalTo) {
		InvoiceTotalTo = invoiceTotalTo;
	}

	public boolean isSearchByInvoiceTotal() {
		return searchByInvoiceTotal;
	}

	public void setSearchByInvoiceTotal(boolean searchByInvoiceTotal) {
		this.searchByInvoiceTotal = searchByInvoiceTotal;
	}

	public int getTypeOfInvoice() {
		return typeOfInvoice;
	}

	public void setTypeOfInvoice(int typeOfInvoice) {
		this.typeOfInvoice = typeOfInvoice;
	}

	@Override
	public void setMaximumResults(int maximum) {
		// TODO Auto-generated method stub
		this.maximum=maximum;
	}

	@Override
	public int getMaximumResults() {
		// TODO Auto-generated method stub
		return this.maximum;
	}

	public String getOrderDateRangeTo() {
		return orderDateRangeTo;
	}

	public void setOrderDateRangeTo(String orderDateRangeTo) {
		this.orderDateRangeTo = orderDateRangeTo;
	}

	public String getOrderDateRangeFrom() {
		return orderDateRangeFrom;
	}

	public void setOrderDateRangeFrom(String orderDateRangeFrom) {
		this.orderDateRangeFrom = orderDateRangeFrom;
	}

	public String getInvoiceDateRangeTo() {
		return invoiceDateRangeTo;
	}

	public void setInvoiceDateRangeTo(String invoiceDateRangeTo) {
		this.invoiceDateRangeTo = invoiceDateRangeTo;
	}

	public String getInvoiceDateRangeFrom() {
		return invoiceDateRangeFrom;
	}

	public void setInvoiceDateRangeFrom(String invoiceDateRangeFrom) {
		this.invoiceDateRangeFrom = invoiceDateRangeFrom;
	}

	public String getItemIdOrDescription() {
		return itemIdOrDescription;
	}

	public void setItemIdOrDescription(String itemIdOrDescription) {
		this.itemIdOrDescription = itemIdOrDescription;
	}

	public String getOrderTotalFrom() {
		return orderTotalFrom;
	}

	public void setOrderTotalFrom(String orderTotalFrom) {
		this.orderTotalFrom = orderTotalFrom;
	}

	public String getOrderTotalTo() {
		return orderTotalTo;
	}

	public void setOrderTotalTo(String orderTotalTo) {
		this.orderTotalTo = orderTotalTo;
	}

	public boolean isFromDeliverySearch() {
		return isFromDeliverySearch;
	}

	public void setFromDeliverySearch(boolean isFromDeliverySearch) {
		this.isFromDeliverySearch = isFromDeliverySearch;
	}

	
	
	@Override
	public boolean deliveredOrderSearchWithItem() {
		return deliveredORderSearchByItem;
	}

	public void setDeliveredORderSearchByItem(boolean deliveredORderSearchByItem) {
		this.deliveredORderSearchByItem = deliveredORderSearchByItem;
	}

	@Override
	public void setDeliveredOrderCustIdOrDes(String val) {
		// TODO Auto-generated method stub
		this.custIdOrDesc=val;
	}

	@Override
	public String getDelvCustIdorDesc() {
		// TODO Auto-generated method stub
		return this.custIdOrDesc;
	}

	@Override
	public String toString() {
		return "OrderTransactionSearchCriteria [invoiceNumberOrOrderNumber=" + invoiceNumberOrOrderNumber
				+ ", searchByinvoiceNumberOrOrderNumber=" + searchByinvoiceNumberOrOrderNumber + ", customerInfo="
				+ customerInfo + ", searchByCustomerInfo=" + searchByCustomerInfo + ", InvoiceTotalFrom="
				+ InvoiceTotalFrom + ", InvoiceTotalTo=" + InvoiceTotalTo + ", custIdOrDesc=" + custIdOrDesc
				+ ", maximum=" + maximum + ", typeOfInvoice=" + typeOfInvoice + ", orderDateRangeTo=" + orderDateRangeTo
				+ ", orderDateRangeFrom=" + orderDateRangeFrom + ", invoiceDateRangeTo=" + invoiceDateRangeTo
				+ ", invoiceDateRangeFrom=" + invoiceDateRangeFrom + ", searchByInvoiceTotal=" + searchByInvoiceTotal
				+ ", itemIdOrDescription=" + itemIdOrDescription + ", orderTotalFrom=" + orderTotalFrom
				+ ", orderTotalTo=" + orderTotalTo + ", isFromDeliverySearch=" + isFromDeliverySearch
				+ ", deliveredORderSearchByItem=" + deliveredORderSearchByItem + "]";
	}
	
	
	
}
