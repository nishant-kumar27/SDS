package rispl.dkart.services.ejb.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.rispl.dk.claim.model.DiffClaimsList;
import com.test.entities.OrderDetail;
import com.test.entities.OrderDetailsWithInvoice;

import rispl.db.model.claim.ClaimTranHeader;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.detail.claim.ClaimDetailTable;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.tenders.TranLineItemTender;
import rispl.dkart.services.entities.transaction.DkartReasonCodes;
import rispl.dkart.services.entities.transaction.ExciseTaxItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeaderPK;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkart.services.payment.PaymentDetails;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.services.transaction.find.OrderTransactionException;

@Remote
public interface OrderTransactionsIfc {

	//	public OrderTranHeader getTranHeader();

	public OrderTranHeader[] getTransactionsInvoices(OrderTransactionSearchCriteriaIfc criteria);

	// displaying comment in cancel print page 
	public String getTransactionsPrintcomment(String criteria);
	
	public List<CustomerIfc> getOrderIdByItemId(String Item_Id);
	
	// search by invoice customer Info and item info by jagadish:: commented by jagadish
	//public List<CustomerIfc> getCustInfoAndItemInfo(String Item_Id,String Customer_Id);

	public OrderTranHeader createNewOrder(OrderTranHeader transaction);

	public boolean saveOrderTransaction(OrderTranHeader transaction) throws OrderTransactionException;

	public OrderTranHeader[] getCustomerSiteInvoices(OrderTransactionSearchCriteriaIfc search);
	
	// jagadish code for in Invoice, OrderSearch & InvoiceSearch
	public OrderTranHeader[] getCustomerSiteAllInvoices(OrderTransactionSearchCriteriaIfc search,String emplID,String empRoleAcc,List<Integer> divIds);
	
	public Map<String, Map<String, String>> getAllReasonCodes();

	public boolean savePayments(TranLineItemTender[] tenders);

	PaymentDetails SavePaymentTransaction(PaymentDetails pd);

	public OrderTranHeader[] getOrderbyCustomerInfo(OrderTransactionSearchCriteriaIfc search);

	public ArrayList<OrderTranHeader[]> getDelOrderIdByItemId(String invItemId);

	public ArrayList<OrderTranHeader[]> getDelOrderbyCustid(String[] searchCustIds,
			CustomerSearchCriteria customerSearchCriteria);

	public OrderTranHeader saveQuote(OrderTranHeader transaction) throws OrderTransactionException;

	public List<ClaimDetailTable> getClaimTableDetails(String string);

	ClaimTranHeader getClaimTranHeader(String claimID, String calimStatus);

	public boolean persistClaimTransaction(ClaimTranHeader cth);

	public CustomerHeader getCustomerHeader(String customerId);

	public String SaveClaimTransaction(ClaimTranHeader cth);

	public OrderTranHeader createNewOrderForClaims(OrderTranHeader transaction);
	
	// process for disable option
	public String disablePermissionForEmpl(Long empRoleId,String functionId);
	
	
	// For claim Search by Order ID, Claim ID , CustomerId, Customer info. jagadish code
	public List<ClaimDetailTable> getRjectedClaimTableDetails(String string,String emplName,List<Integer> divIds,String emplID, String orderID,String claimID,String customerInfo, String claimItemId,String empRoleAcc);
	// For claim Search by  Claim date AND Totals. jagadish code
	public List<ClaimDetailTable> getRjectedClaimDateAndTotalDetails(String string,String emplName,List<Integer> divIds,String emplID,String claimID,Date parsed_from,Date parsed_to,String ClaimTotalFrom,String ClaimTotalTo,String claimItemId,String empRoleAcc);
	// For claim Search by  order date AND Totals. jagadish code
	public List<ClaimDetailTable> getRjectedClaimOrderDetails(String Status,String string,String emplName,List<Integer> divIds,String emplID,String orderID,Date parsed_from,Date parsed_to,String ordertotalfrom,String ordertotalto);
	
	//for reject claim search by order,claim,customer info
	public List<ClaimDetailTable> getRejectedClaimSearchByClaim(String string, String empRoleAcc, List<Integer> divIds,String emplID, String claimID, Date parsed_from, Date parsed_to, String claimTotalFrom, String claimTotalTo,String itemId);

	public List<ClaimDetailTable> getRejectedClaimSearchByOrder(String string, String empRoleAcc, List<Integer> divIds,String emplID, String orderID, Date parsed_from, Date parsed_to, String order_total_from,String order_total_to, String itemId);

	public List<ClaimDetailTable> getRejectedClaimSearchByCustomerInfo(String string, String empRoleAcc,List<Integer> divIds, String emplID, String orderID, String claimID, String customerInfo, String itemId);
	
	/* added for mark order as delivered */ //String datepicker3,String datepicker4
	/* mudassir */
	public boolean markOrderAsDelivered(List<String> order) throws Exception;

	public List<OrderTranHeaderPK> getTranHeadPkForDelivered() throws Exception;

	public List<OrderDetail> getPendingOrders(EmployeeIfc emp) throws Exception;

	// for quoted order when it is retrived
	public OrderTranHeader markAsSuspendRetrived(OrderTranHeader head) throws Exception;

	public LinkedHashMap<String, String> validateInventory(OrderTranHeader head) throws Exception;

	public boolean isOrderShipped(String idOrd);

	/**
	 * 
	 * @param oth
	 * @param empId
	 * @param result
	 * @return OrderTranHeader and Hashtable<String, Object> Key1=PERSIST value1=boolean
	 * 
	 */
	public Hashtable<String, Object> cancelSalesOrder(String orderId, String transComment, String empId,String reasonCode);

	public OrderTranHeader getOrderByOrderID(String orderID, String orderType, BigDecimal orderstatus, BigDecimal tranStatus);

	public String getSalesAgentMailId(String ordId);

	public String getDataEntryOptrMailId(String ordId);
	
	public String[] getDepartmentHeadMailId(String ordId);

	//public List<OrdInvShpQtySrlno> getSerialNos(OrderTranHeader oth);

	public List<OrderTranLineItem> getDeliveredQty(String orderID);
	
	
	// lalit to get all excisetex
	public List<ExciseTaxItem> getExciseTax();
	
	// jagadish to get all discount reason code descriptions
		public List<DkartReasonCodes> getAllDisRsnCode();
	
	// code to dispay Order date in invoice_Details page. @jagadish
	public List<OrderDetailsWithInvoice> getOrderDateForOrderId(String orderNum);
	
	public List<Object> getShippedQty(String orderId);
	
	public List<Object> getPickedQty(String orderId);

	public Map<String,StringBuffer> getItemSerialNumbers(OrderTranHeader oth);
	
	public boolean isPartialShippingAvailable(String orderId);
	public boolean inProgressOrder(String orderId);
	
	public Map<String, Map> getShippingDetails(String orderId);

	public Map<String, OrderTranHeader> processPartialShipping(String orderId, String partialShippingReasonCode, String employeeId);

	public String getCustomerSiteAddrss(String customerId, String custSiteId);
	
	//Added By Veeresh Singh to validate LPO Number duplicate
	public boolean isLPONumberDuplicate(OrderTranHeader transaction, String lpoNum) throws OrderTransactionException;

}
