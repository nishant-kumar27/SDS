package rispl.dkart.order.lookup.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import com.test.entities.OrderDetail;
import com.test.entities.OrderDetailsWithInvoice;
import com.test.entities.SdsOrdersDashboard;

import rispl.db.model.customer.RisplDkCustSeg;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.ejb.transaction.OrderTransactions;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;

/**
 * Session Bean implementation class OrderDAOBean
 */
@Stateless(mappedName="orderDAOBean")
public class OrderDAOBean implements OrderDAOBeanRemote {
	
	OrderDao dao;

	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	@Inject
	OrderTransactions orderTransactions;
	
	@PostConstruct
    void init()
    {
    	//dao=new OrderDao(emf.createEntityManager());
		dao=new OrderDao(emf);
    }

    public OrderDAOBean() {
    }

    @Override
    public List<SdsOrdersDashboard> getOrdersCountByEmpId(String empId,String range){
    	
    	List<SdsOrdersDashboard> orders_count=dao.getOrdersCountByEmpId(empId,range);
    	return orders_count;
    }
    
	@Override
	public List<OrderDetail> getorders(String empId) {
		// TODO Auto-generated method stub
		
		List<OrderDetail> orders=dao.getOrdersByEmpId(empId);
		return orders;
	}

	@Override
	public List<OrderDetail> getorders(String empId, String range,String status) {
		// TODO Auto-generated method stub
		
    	List<OrderDetail> orders=dao.getOrdersByEmpId(empId, range, status);
		return orders;
	}

	@Override
	public List<OrderDetail> getorders(String empId, String range) {
		// TODO Auto-generated method stub
		
    	List<OrderDetail> orders=dao.getOrdersByEmpId(empId, range);
		return orders;
	}

	@Override
	public List<OrderDetailsWithInvoice> getOrderByOrderTotals(EmployeeIfc employee, String from,String to) {
		// TODO Auto-generated method stub
		
    	List<OrderDetailsWithInvoice> orders=dao.getOrdersByOrderTotals(employee, from, to);
		return filterDuplicateOrders(orders);
	}

	@Override
	public List<OrderDetailsWithInvoice> getOrderByOrderDate(EmployeeIfc employee, Date from, Date to) {
		// TODO Auto-generated method stub
		List<OrderDetailsWithInvoice> orders=dao.getOrdersByOrderDate(employee, from, to);
		return filterDuplicateOrders(orders);
	}

	@Override
	public List<OrderDetailsWithInvoice> getOrderByOrderId(EmployeeIfc employee, String orderId) {
		// TODO Auto-generated method stub
		List<OrderDetailsWithInvoice> orders=dao.getOrdersByOrderId(employee, orderId);
		
		return filterDuplicateOrders(orders);
		//return orders;
	}

	@Override
	public List<OrderDetailsWithInvoice> getOrdersByInvoiceTotals(EmployeeIfc employee,
			String from, String to) {
		List<OrderDetailsWithInvoice> orders=dao.getOrdersByInvoiceTotals(employee, from, to);
		return filterDuplicateOrders(orders);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderDetailsWithInvoice> getOrdersByInvoiceDate(EmployeeIfc employee,
			Date from, Date to) {
		// TODO Auto-generated method stub
		List<OrderDetailsWithInvoice> orders=dao.getOrdersByInvoiceDate(employee, from, to);
		return filterDuplicateOrders(orders);
	}

	@Override
	public List<OrderDetailsWithInvoice> getOrderByInvoiceId(EmployeeIfc employee,
			String invoiceId) {
		// TODO Auto-generated method stub
		List<OrderDetailsWithInvoice> orders=dao.getOrdersByInvoiceId(employee, invoiceId);
		return filterDuplicateOrders(orders);
	}
	
	@Override
	public List<OrderDetailsWithInvoice> getOrderByCustomer(EmployeeIfc employee,
			String customerData) {
		// TODO Auto-generated method stub
		List<OrderDetailsWithInvoice> orders=dao.getOrdersByCustomer(employee, customerData);
		return filterDuplicateOrders(orders);
	}

	@Override
	public List<OrderDetailsWithInvoice> getOrderByItem(EmployeeIfc employee,
			String itemData) {
		// TODO Auto-generated method stub
		List<OrderDetailsWithInvoice> orders=dao.getOrdersByItem(employee, itemData);
		return filterDuplicateOrders(orders);
	}

	private List<OrderDetailsWithInvoice> filterDuplicateOrders(List<OrderDetailsWithInvoice> orders){
		List<OrderDetailsWithInvoice> filteredList = null;
		if(orders != null){
			Map<String, OrderDetailsWithInvoice> filterMap = new LinkedHashMap<>();
			orders.stream().forEach(ord -> {
				filterMap.put(ord.getId().getOrderId(), ord);
			});
			filteredList  = filterMap.values().stream().collect(Collectors.toList());
		}
		return filteredList;
	}


	@Override
	public RisplDkCustSeg getRisplDkCustSegByGroupId(BigDecimal grpId) {
		// TODO Auto-generated method stub
		RisplDkCustSeg cust_seg=dao.getRisplDkCustSegByGroupId(grpId);
		return cust_seg;
	}
	
/*	@Override
	public List<SdsOrdersDashboard> getOrdersCountByDivision(int division, String range) {*/
	@Override
	public List<SdsOrdersDashboard> getOrdersCountByDivision(List<Integer> division, String range) {
	
		List<SdsOrdersDashboard> orders=dao.getOrdersCountByDivision(division, range);
		return orders;
		
	}

	/*@Override
	public List<OrderDetail> getOrdersByDivision(int division, String range) {*/
		@Override
		public List<OrderDetail> getOrdersByDivision(List<Integer> division, String range) {
		// TODO Auto-generated method stub
		List<OrderDetail> orders=dao.getOrdersByDivision(division,range);
		return orders;
	}

	/*@Override
	public List<OrderDetail> getOrdersByDivision(int division, String range, String status) {*/
		@Override
		public List<OrderDetail> getOrdersByDivision(List<Integer> division, String range, String status) {
		// TODO Auto-generated method stub
		List<OrderDetail> orders=dao.getOrdersByDivision(division,range,status);
		return orders;
	}

	@Override
	public List<OrderDetail> getOrdersByDivision(int division) {
		// TODO Auto-generated method stub
		List<OrderDetail> orders=dao.getOrdersByDivision(division);
		return orders;
	}

	
	/* * delivered order search commented by mudassir, will commit once development is done 
	 * 
	 **/ @Override
	public DelvOrderSearchVO deliveredOrderSearchByTab1(OrderTransactionSearchCriteriaIfc searchCreteria, EmployeeIfc employee)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.deliveredOrderSearchByTab1(searchCreteria,employee);
	}

	@Override
	public List setDeliveredOrderDetails(String custInvPK) throws Exception {
		List orderTranHeaderList = dao.getTranHeadPkList(custInvPK);
		
		if (orderTranHeaderList != null && orderTranHeaderList.get(1)!=null)
			orderTransactions.addShippedQtyDetails(Arrays.asList((OrderTranHeader)orderTranHeaderList.get(1)));

		return orderTranHeaderList;
	}

	@Override
	public List<OrderDetailsWithInvoice> getOpenOrdersWithTab1andTab2(OpenOrderSearchVo search, EmployeeIfc emp) {
		// TODO Auto-generated method stub
		return dao.openOrderSearchWithBothTabs(search, emp);
	}

	@Override
	public List<SdsOrdersDashboard> getOrdersCountByAllDiv(String range) {
		// TODO Auto-generated method stub
		List<SdsOrdersDashboard> orders_count=dao.getOrdersCountByAllDiv(range);
    	return orders_count;
	}

	@Override
	public List<OrderDetail> getOrdersByAllDiv(String range) {
		List<OrderDetail> orders=dao.getOrdersByAllDiv(range);
		return orders;
	}

	@Override
	public List<OrderDetail> getOrdersByAllDiv(String range, String status) {
		List<OrderDetail> orders=dao.getOrdersByAllDiv(range,status);
		return orders;
	}
	
	@Override
	public List<ReturnedOrdersVO> getReturnedOrderByOrderAndCustomer(String orderId, String OrderDateFrom,
			String orderDateTo, String OrderTotalFrom, String orderTotalTo,String customer,String rtnitem, EmployeeIfc employee) {
		List<ReturnedOrdersVO> returnedOrders = null;
		try{
			returnedOrders = dao.getReturnedOrderByOrderAndCustomer(orderId, OrderDateFrom, orderDateTo, OrderTotalFrom, orderTotalTo,customer,rtnitem,employee);
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnedOrders;
	}
	
	
	/*Kunal : Timestamp */
	@Override
	public String getTimestampByOrderId (String orderId){
		String timestamp = null;
		try{
			timestamp = dao.getTimestampByOrderId(orderId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return timestamp;
	}
}
