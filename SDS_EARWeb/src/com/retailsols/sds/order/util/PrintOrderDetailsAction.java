package com.retailsols.sds.order.util;

import java.util.List;

import com.test.entities.OrderDetailsWithInvoice;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.order.lookup.dao.OrderDAOBeanRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import rispl.dkservices.common.OrderTransactionSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class PrintOrderDetailsAction extends DSAction {

	private String empId, orderId;
	private List<OrderTranLineItem> order_items;
	private OrderDetailsWithInvoice order;
	private OrderTranSum ord_tran_sum;
	private OrderDAOBeanRemote dao;
	private CustomerHeader customer;
	private OrderTranHeader ord_tran_header;
	private String cust_seg;
	private EmployeeIfc employee;
	private CustomerSiteAddress ship_address;
	private String errorMessage;
	private String orderID;
	private String order_Type;
	private String order_comment;
	private boolean download;
	
	public String getOrder_comment() {
		return order_comment;
	}

	public void setOrder_comment(String order_comment) {
		this.order_comment = order_comment;
	}

	public String getOrder_Type() {
		return order_Type;
	}

	public void setOrder_Type(String order_Type) {
		this.order_Type = order_Type;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OrderTranLineItem> getOrder_items() {
		return order_items;
	}

	public void setOrder_items(List<OrderTranLineItem> order_items) {
		this.order_items = order_items;
	}

	public OrderDetailsWithInvoice getOrder() {
		return order;
	}

	public void setOrder(OrderDetailsWithInvoice order) {
		this.order = order;
	}

	public OrderTranSum getOrd_tran_sum() {
		return ord_tran_sum;
	}

	public void setOrd_tran_sum(OrderTranSum ord_tran_sum) {
		this.ord_tran_sum = ord_tran_sum;
	}

	public OrderDAOBeanRemote getDao() {
		return dao;
	}

	public void setDao(OrderDAOBeanRemote dao) {
		this.dao = dao;
	}

	public CustomerHeader getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerHeader customer) {
		this.customer = customer;
	}

	public OrderTranHeader getOrd_tran_header() {
		return ord_tran_header;
	}

	public void setOrd_tran_header(OrderTranHeader ord_tran_header) {
		this.ord_tran_header = ord_tran_header;
	}

	public String getCust_seg() {
		return cust_seg;
	}

	public void setCust_seg(String cust_seg) {
		this.cust_seg = cust_seg;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public CustomerSiteAddress getShip_address() {
		return ship_address;
	}

	public void setShip_address(CustomerSiteAddress ship_address) {
		this.ship_address = ship_address;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	



	@Override
	public String execute() throws Exception {
		employee = super.getEmployee();
		try {
			OrderTransactionsIfc trans = DKartContext.getLookupOrder();
//			globalReasonCode = trans.getAllReasonCodes().get("Returns");
			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setMaximumResults(99);
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(orderID.trim());
			OrderTranHeader[] orders1 = trans.getTransactionsInvoices(search);
			ord_tran_header = orders1[0];
			order_items = ord_tran_header.getOrdTranLineItems();
			customer = ord_tran_header.getCustomer().getCustomerHeader();
			cust_seg = ord_tran_header.getCustomer().getCustomerSegmentID();
			order_Type=ord_tran_header.getOrdTy();
			// print comment in cancel print page 
			if(order_Type.equalsIgnoreCase("25"))
			{
				String results = trans.getTransactionsPrintcomment(orderID);
				order_comment=results;
			}else
			{
				order_comment=ord_tran_header.getCtDvrInfoIns();
			}
			ord_tran_sum = ord_tran_header.getOrdTranSum();
			dao = DKartContext.getOrderDAOBean();
			order = dao.getOrderByOrderId(employee, orderID).get(0);
			if (ord_tran_header.getCustomer().getCustomerSiteAddress() != null){
				ship_address = ord_tran_header.getCustomer().getCustomerSiteAddress().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public boolean isDownload() {
		return download;
	}

	public void setDownload(boolean download) {
		this.download = download;
	}
}
