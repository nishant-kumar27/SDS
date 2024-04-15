package rispl.dkart.order.lookup.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;


import com.test.entities.OrderDetail;
import com.test.entities.OrderDetailsWithInvoice;
import com.test.entities.SdsOrdersDashboard;

import rispl.db.model.customer.RisplDkCustSeg;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;

@Remote
public interface OrderDAOBeanRemote {
		public List<OrderDetail> getorders(String empId);
		public List<OrderDetail> getorders(String empId,String range);
		public List<OrderDetail> getorders(String empId,String range,String status);
		
		/*public List<OrderDetail> getOrdersByDivision(int division, String range);*/
		public List<OrderDetail> getOrdersByDivision(List<Integer> division, String range);
		
		/*public List<OrderDetail> getOrdersByDivision(int  division, String range, String status);*/
		public List<OrderDetail> getOrdersByDivision(List<Integer>  division, String range, String status);
		public List<OrderDetail> getOrdersByDivision(int division);
		
		public List<SdsOrdersDashboard> getOrdersCountByEmpId(String empId,String status);
		/*
		 * 
		 * changed by mudassir 
		 */
	//	public List<SdsOrdersDashboard> getOrdersCountByDivision(int division, String range) ;
		public List<SdsOrdersDashboard> getOrdersCountByDivision(List<Integer> division, String range) ;
		public List<OrderDetailsWithInvoice> getOrderByOrderTotals(EmployeeIfc employee,String from,String to);
		public List<OrderDetailsWithInvoice> getOrderByOrderDate(EmployeeIfc employee,Date from,Date to);
		public List<OrderDetailsWithInvoice> getOrderByOrderId(EmployeeIfc employee,String orderId);
		
		public List<OrderDetailsWithInvoice> getOrdersByInvoiceTotals(EmployeeIfc employee,String from,String to);
		public List<OrderDetailsWithInvoice> getOrdersByInvoiceDate(EmployeeIfc employee,Date from,Date to);
		public List<OrderDetailsWithInvoice> getOrderByInvoiceId(EmployeeIfc employee,String invoiceId);
		
		public List<OrderDetailsWithInvoice> getOrderByCustomer(EmployeeIfc employee,String customerData);
		public List<OrderDetailsWithInvoice> getOrderByItem(EmployeeIfc employee,String itemData);
		

		
	
		
		public RisplDkCustSeg getRisplDkCustSegByGroupId(BigDecimal grpId);
	/*	 delivered order search commented by mudassir, will commit once development is done  
		mudassir for deliveredOrder search with tab1*/
		public DelvOrderSearchVO deliveredOrderSearchByTab1(OrderTransactionSearchCriteriaIfc searchCreteria,EmployeeIfc employee) throws Exception;
		
		public List setDeliveredOrderDetails(String custInvPK)throws Exception;
		
		/*Mudassir open order search start*/
		public List<OrderDetailsWithInvoice> getOpenOrdersWithTab1andTab2(OpenOrderSearchVo search,EmployeeIfc emp);
			/*open order search end*/
		
		/*sharanya for Dashboard*/
		public List<SdsOrdersDashboard> getOrdersCountByAllDiv(String range);
		public List<OrderDetail> getOrdersByAllDiv(String range);
		public List<OrderDetail> getOrdersByAllDiv(String range, String status);
		/*Srinivas Returned Order Search*/	
		public List<ReturnedOrdersVO> getReturnedOrderByOrderAndCustomer(String orderId,String OrderTotalFrom,String orderTotalTo,String OrderDateFrom,String orderDateTo,String customer,String rtnitem,EmployeeIfc employee);
		/*returned Order Search end*/
		
		/*Kunal: order approval time*/
		public String getTimestampByOrderId (String orderId);
}
