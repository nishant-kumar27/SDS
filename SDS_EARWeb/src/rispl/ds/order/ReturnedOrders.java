/**
* This ReturnedOrders programme is used
* to search for the Returned Orders with claim Details
*
* @author  Srinivas Reddy G
* @version 1.0
* @since   30-05-2017 
*/
package rispl.ds.order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Preparable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.rispl.cancel.order.dao.RisplDkCancelOrderSearchV;
import com.test.entities.OrderDetailsWithInvoice;

import rispl.db.model.claim.ClaimDetail;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.ClaimTranSum;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.order.lookup.dao.OrderDAOBeanRemote;
import rispl.dkart.order.lookup.dao.ReturnedOrdersVO;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import rispl.dkservices.common.OrderTransactionSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.ReasonCodes;
import rispl.ds.DSAction.SESSION;
import rispl.ds.context.DKartContext;

/*
 * 31/05/2017 - Srinivas Reddy G - Implementing the Returned Order Search Functionality using OrderID,OrderDate Range,OrderTotal Range,Customer Info,ItemInfo
 */
/**
 * This class is used to search for the returned orders
 */
public class ReturnedOrders extends DSAction implements Preparable {

	static Logger LOGGER = LogManager.getLogger(ReturnedOrders.class);

	private static final long serialVersionUID = 1;

	/**
	 * The order ID for which the return is registered
	 */
	private String orderID;
	/**
	 * The Date on which the order is placed
	 */
	private String order_date_from;
	/**
	 * The Date on which the order is placed
	 */
	private String order_date_to;
	/**
	 * The Date on which the invoice is generated
	 */
	private String datepicker3;
	/**
	 * The Date on which the invoice is generated
	 */
	private String datepicker4;
	/**
	 * The Net Total of the order
	 */
	private String OrderTotalFrom;
	/**
	 * The Net Total of the order
	 */
	private String OrderTotalTo;
	/**
	 * The Customer ID/Name to which the orders are placed
	 */
	private String customerInfo;
	/**
	 * The Item ID/Description present in the order
	 */
	private String rtnItemId;
	/**
	 * Returned Orders list
	 */
	private List<ReturnedOrdersVO> returnedOrdersVOlist;

	/**
	 * Employee class object
	 */
	private EmployeeIfc employee;
	/**
	 * DAO interface for the Orders
	 */
	OrderDAOBeanRemote dao;
	/**
	 * ord_tran_header class for the Orders
	 */
	private OrderTranHeader ord_tran_header;
	/**
	 * orderSearchCriteria class for the ordertransactionSearch criteria
	 */
	private OrderTransactionSearchCriteriaIfc orderSearchCriteria;
	/**
	 * returnReasonCodeMap map object to hold the returned orders
	 */
	private Map<String, String> returnReasonCodeMap = new HashMap<String, String>();
	/**
	 * orderId is the Id for Order
	 */
	private String orderId;
	/**
	 * customerheader object for the CustomerHeader class
	 */
	private CustomerHeader customer;
	/**
	 * cust_seg filed represents customer segment
	 */
	private String cust_seg;
	/**
	 * ord_tran_sum object for the OrderTranSum class
	 */
	private OrderTranSum ord_tran_sum;
	/**
	 * order object for the OrderDetailsWithInvoice class
	 */
	private OrderDetailsWithInvoice order;
	/**
	 * claim_tran_header object for the ClaimTranHeader class
	 */
	private ClaimTranHeader claim_tran_header;
	/**
	 * claim_tran_sum object for the ClaimTranSum class
	 */
	public ClaimTranSum claim_tran_sum;
	/**
	 * claim_items object for the List<ClaimTranLineItem> class
	 */
	private List<ClaimTranLineItem> claim_items;
	/**
	 * DATETIME_FORMAT DATETIME_FORMAT for the DateFormat class
	 */
	private DateFormat DATETIME_FORMAT;
	/**
	 * range of the search
	 */
	private String range;

	/**
	 * @return
	 */
	public String Execute() {
		return SUCCESS;
	}

	/**
	 * @throws Exception
	 */
	public void prepare() throws Exception {
		/**
		 * Creating DateFormat class object with required DateFormat
		 */
		DATETIME_FORMAT = new SimpleDateFormat(getText("format.date"));
	}
	/**
	 * To do returned orders search with order info
	 * 
	 * @author Srinivas Reddy G
	 * @return "success" "error"
	 */
	/*
	 * public String getReturnedOrdersByOrderInfo() { LOGGER.info(
	 * "getReturnedOrdersByOrderInfo() method started executing"); Date
	 * parsed_to, parsed_from; parsed_from =
	 * convertStringToDate(order_date_from); //converting the order_date_from
	 * from String to required DateFormat parsed_to =
	 * convertStringToDate(order_date_to); //converting the order_date_to from
	 * String to required DateFormat employee = super.getEmployee(); //getting
	 * the employee object from super class DSAction try{ dao =
	 * DKartContext.getOrderDAOBean(); returnedOrders =
	 * dao.getReturnedOrdersByOrderId(employee, orderID); returnedOrders =
	 * dao.getReturnedOrdersByOrderTotals(employee,OrderTotalFrom,OrderTotalTo);
	 * returnedOrders =
	 * dao.getReturnedOrdersByOrderDateRange(employee,order_date_from,
	 * order_date_to); } catch(Exception e){ LOGGER.error(
	 * "Error while searching for return orders"); } return SUCCESS;
	 * 
	 * }// end of getReturnedOrdersByOrderInfo() method
	 */
	/**
	 * To do returned orders search with Customer and Item info
	 * 
	 * @author Srinivas Reddy G
	 * @return "success" "error"
	 */
	/*
	 * public String getReturnedOrdersByCustomerInfo() { LOGGER.info(
	 * "getReturnedOrdersByCustomerInfo() method started executing"); return
	 * SUCCESS; }
	 */
	// end of getReturnedOrdersByCustomerInfo() method

	/**
	 * This method is used to convert String to Date
	 * 
	 * @author Srinivas Reddy G
	 * @param stringDate
	 *            date with string type
	 * @return Date object for the specified stringDate
	 * @see Date
	 */
	public Date convertStringToDate(String stringDate) {
		if (stringDate == null) {
			return null;
		}
		try {
			return DATETIME_FORMAT.parse(stringDate);
		} catch (ParseException e) {
			return null;
		}
	}// end of convertStringToDate() method

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getRtnItemId() {
		return rtnItemId;
	}

	public void setRtnItemId(String rtnItemId) {
		if (rtnItemId.contains("---")) {
			String[] itm = rtnItemId.split("---");
			try {
				this.rtnItemId = itm[1];
			} catch (Exception e) {
				System.out.println(e);
				this.rtnItemId = itm[0];
			}
		} else {
			this.rtnItemId = rtnItemId;
		}
	}

	/**
	 * To do returned orders search with order or customer or iteminfo
	 * 
	 * @author Srinivas Reddy G
	 * @return "success" "error"
	 */
	public String getReturnedOrdersByOrderAndCustomerAndItemInfo() {
		LOGGER.info("inside getReturnedOrdersByOrderAndCustomerAndItemInfo()");
		employee = super.getEmployee();
		try {
			dao = DKartContext.getOrderDAOBean();
			returnedOrdersVOlist = dao.getReturnedOrderByOrderAndCustomer(orderID, order_date_from, order_date_to,
					OrderTotalFrom, OrderTotalTo, customerInfo, rtnItemId, employee);
			returnReasonCodeMap = ReasonCodes.getReturnReasonCodes();
			LOGGER.info("fetching the default Search Range of Returned Orders");
			setRange(DKartContext.getParamterBean().fetchXMLParameterValues().getReturnedOrderSearchRange());
			LOGGER.info("Default Search Range Fetched is :  "+DKartContext.getParamterBean().fetchXMLParameterValues().getReturnedOrderSearchRange());
		} catch (Exception e) {
			LOGGER.error("Error while fetching the Returned Orders :" + e);
		}
		return "success";
	}

	public String getReturnedOrderDetailsByOrderId() {
		employee = super.getEmployee();
		try {
			OrderTransactionsIfc trans = DKartContext.getLookupOrder();
			returnReasonCodeMap = ReasonCodes.getReturnReasonCodes();
			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setMaximumResults(99);
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(orderId.trim());
			OrderTranHeader[] orders1 = trans.getTransactionsInvoices(search);
			ord_tran_header = orders1[0];
			claim_tran_header = trans.getClaimTranHeader(ord_tran_header.getAcceptClaimId(), null);
			claim_tran_sum = claim_tran_header.getClaimTranSum();
			claim_items = claim_tran_header.getClaimTranLineItems();
			ord_tran_sum = ord_tran_header.getOrdTranSum();
			customer = ord_tran_header.getCustomer().getCustomerHeader();
			cust_seg = ord_tran_header.getCustomer().getCustomerSegmentID();
			dao = DKartContext.getOrderDAOBean();
			order = dao.getOrderByOrderId(employee, orderId).get(0);
			putInSession(SESSION.ORDER_TRANHEADER, ord_tran_header);
			putInSession(SESSION.CLAIM_TRANHEADER, claim_tran_header);

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage(), e);
			addActionError("Error looking up order details for Order ID: " + orderId);
			return ERROR;
		}

		return SUCCESS;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getDatepicker3() {
		return datepicker3;
	}

	public void setDatepicker3(String datepicker3) {
		this.datepicker3 = datepicker3;
	}

	public String getDatepicker4() {
		return datepicker4;
	}

	public void setDatepicker4(String datepicker4) {
		this.datepicker4 = datepicker4;
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

	public String getOrder_date_from() {
		return order_date_from;
	}

	public void setOrder_date_from(String order_date_from) {
		this.order_date_from = order_date_from;
	}

	public String getOrder_date_to() {
		return order_date_to;
	}

	public void setOrder_date_to(String order_date_to) {
		this.order_date_to = order_date_to;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public List<ReturnedOrdersVO> getReturnedOrdersVOlist() {
		return returnedOrdersVOlist;
	}

	public void setReturnedOrdersVOlist(List<ReturnedOrdersVO> returnedOrdersVOlist) {
		this.returnedOrdersVOlist = returnedOrdersVOlist;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderTranHeader getOrd_tran_header() {
		return ord_tran_header;
	}

	public void setOrd_tran_header(OrderTranHeader ord_tran_header) {
		this.ord_tran_header = ord_tran_header;
	}

	public OrderTransactionSearchCriteriaIfc getOrderSearchCriteria() {
		return orderSearchCriteria;
	}

	public void setOrderSearchCriteria(OrderTransactionSearchCriteriaIfc orderSearchCriteria) {
		this.orderSearchCriteria = orderSearchCriteria;
	}

	public CustomerHeader getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerHeader customer) {
		this.customer = customer;
	}

	public String getCust_seg() {
		return cust_seg;
	}

	public void setCust_seg(String cust_seg) {
		this.cust_seg = cust_seg;
	}

	public OrderDetailsWithInvoice getOrder() {
		return order;
	}

	public void setOrder(OrderDetailsWithInvoice order) {
		this.order = order;
	}

	public ClaimTranHeader getClaim_tran_header() {
		return claim_tran_header;
	}

	public void setClaim_tran_header(ClaimTranHeader claim_tran_header) {
		this.claim_tran_header = claim_tran_header;
	}

	public ClaimTranSum getClaim_tran_sum() {
		return claim_tran_sum;
	}

	public void setClaim_tran_sum(ClaimTranSum claim_tran_sum) {
		this.claim_tran_sum = claim_tran_sum;
	}

	public OrderTranSum getOrd_tran_sum() {
		return ord_tran_sum;
	}

	public void setOrd_tran_sum(OrderTranSum ord_tran_sum) {
		this.ord_tran_sum = ord_tran_sum;
	}

	public List<ClaimTranLineItem> getClaim_items() {
		return claim_items;
	}

	public void setClaim_items(List<ClaimTranLineItem> claim_items) {
		this.claim_items = claim_items;
	}

	public Map<String, String> getReturnReasonCodeMap() {
		return returnReasonCodeMap;
	}

	public void setReturnReasonCodeMap(Map<String, String> returnReasonCodeMap) {
		this.returnReasonCodeMap = returnReasonCodeMap;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}
}
