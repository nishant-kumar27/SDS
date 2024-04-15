package rispl.dkart.order.lookup.dao;

public class OpenOrderSearchVo {
	private String openOrderStatus;
	private String activeTab;
	private String orderID;
	private String orderFromDate;
	private String ordertoDate;
	private String OrderTotalFrom;
	private String OrderTotalTo;
	private String customerInfo;
	private String InvItemId;
	private boolean isSearchByOrderTab;
	private boolean isSearchByCustomerTab;
	private boolean isSearchByItemTab;
	
	
	public OpenOrderSearchVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOpenOrderStatus() {
		return openOrderStatus;
	}
	public void setOpenOrderStatus(String openOrderStatus) {
		this.openOrderStatus = openOrderStatus;
	}
	public String getActiveTab() {
		return activeTab;
	}
	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getOrderFromDate() {
		return orderFromDate;
	}
	public void setOrderFromDate(String orderFromDate) {
		this.orderFromDate = orderFromDate;
	}
	public String getOrdertoDate() {
		return ordertoDate;
	}
	public void setOrdertoDate(String ordertoDate) {
		this.ordertoDate = ordertoDate;
	}
	public String getOrderTotalFrom() {
		return OrderTotalFrom;
	}
	public void setOrderTotalFrom(String orderTotalFrom) {
		OrderTotalFrom = orderTotalFrom;
	}
	public String getOrderTotalTo() {
		return OrderTotalTo;
	}
	public void setOrderTotalTo(String orderTotalTo) {
		OrderTotalTo = orderTotalTo;
	}
	public String getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	public String getInvItemId() {
		return InvItemId;
	}
	public void setInvItemId(String invItemId) {
		InvItemId = invItemId;
	}
	public boolean isSearchByOrderTab() {
		return isSearchByOrderTab;
	}
	public void setSearchByOrderTab(boolean isSearchByOrderTab) {
		this.isSearchByOrderTab = isSearchByOrderTab;
	}
	public boolean isSearchByCustomerTab() {
		return isSearchByCustomerTab;
	}
	public void setSearchByCustomerTab(boolean isSearchByCustomerTab) {
		this.isSearchByCustomerTab = isSearchByCustomerTab;
	}
	public boolean isSearchByItemTab() {
		return isSearchByItemTab;
	}
	public void setSearchByItemTab(boolean isSearchByItemTab) {
		this.isSearchByItemTab = isSearchByItemTab;
	}
	
	
}
