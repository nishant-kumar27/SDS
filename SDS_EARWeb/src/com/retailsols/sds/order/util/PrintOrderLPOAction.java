package com.retailsols.sds.order.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.test.entities.OrderDetailsWithInvoice;
import com.test.entities.OrderDetailsWithInvoicePK;

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
import rispl.ds.DSAction.SESSION;
import rispl.ds.context.DKartContext;

public class PrintOrderLPOAction extends DSAction {

	private OrderDetailsWithInvoice order;
	private OrderTranHeader ord_tran_header;
	private EmployeeIfc employee;
	private String orderID;
	

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}


	public OrderDetailsWithInvoice getOrder() {
		return order;
	}

	public void setOrder(OrderDetailsWithInvoice order) {
		this.order = order;
	}

	public OrderTranHeader getOrd_tran_header() {
		return ord_tran_header;
	}

	public void setOrd_tran_header(OrderTranHeader ord_tran_header) {
		this.ord_tran_header = ord_tran_header;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	@Override
	public String execute() throws Exception {
		employee = super.getEmployee();
		if(orderID.contains("-LPO") && employee!=null){
		try {
			OrderTransactionsIfc trans = DKartContext.getLookupOrder();
//			globalReasonCode = trans.getAllReasonCodes().get("Returns");
			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setMaximumResults(99);
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(orderID.split("-LPO")[0].trim());
			OrderTranHeader[] orders1 = trans.getTransactionsInvoices(search);
			ord_tran_header = orders1[0];
			if(ord_tran_header!=null && ord_tran_header.getOrdTranLpo()!=null && ord_tran_header.getOrdTranLpo().getLpoSlipType()!=null && ord_tran_header.getOrdTranLpo().getLpoSlipContent()!=null){
				writePDFToPrint();
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		
	}else{
		order = new OrderDetailsWithInvoice();
		OrderDetailsWithInvoicePK pk = new OrderDetailsWithInvoicePK();
		pk.setOrderId(orderID);
		order.setId(pk);
	}
		return SUCCESS;
	}

	public void writePDFToPrint(){

		byte []fileContent = ord_tran_header.getOrdTranLpo().getLpoSlipContent();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType(ord_tran_header.getOrdTranLpo().getLpoSlipType());
			ServletOutputStream outStream = response.getOutputStream();
			outStream.write(fileContent);
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
	}
}
