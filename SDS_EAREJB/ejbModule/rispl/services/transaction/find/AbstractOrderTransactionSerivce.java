package rispl.services.transaction.find;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.entities.OrderDetailsWithInvoice;

import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.employee.RisplDkEmpMstr;
import rispl.db.model.employee.RisplDkEmpRoleAccess;
import rispl.db.model.item.RisplDkItemMstr;
import rispl.dk.customer.Customer;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.customer.CustomerLimit;
import rispl.dkart.services.entities.customer.CustomerPaymentTerm;
import rispl.dkart.services.entities.customer.CustomerSite;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;
import rispl.dkart.services.entities.customer.CustomerSitePaymentTerms;
import rispl.dkart.services.entities.tenders.TranLineItemTender;
import rispl.dkart.services.entities.tenders.TranLineItemTenderPK;
import rispl.dkart.services.entities.transaction.DkartReasonCodes;
import rispl.dkart.services.entities.transaction.ExciseTaxItem;
import rispl.dkart.services.entities.transaction.OrdInvQtyShp;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItem;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItemPK;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeaderPK;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import rispl.dkart.services.entities.transaction.OrderTranSumPK;
import rispl.dkart.services.entities.transaction.lpo.OrderTransactionLpo;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.services.Customer.CustomerException;
import rispl.services.item.DatabaseSourceIfc;
import utility.ConfigUtils;

/**
 * 14/11/2016 -Krishna: Moved the code for post payment transaction to
 * SavePostPaymentTransaction Class
 * 
 */
@SuppressWarnings("unchecked")
public class AbstractOrderTransactionSerivce implements DatabaseSourceIfc {

	private static final Logger LOGGER = LogManager.getLogger(AbstractOrderTransactionSerivce.class);
	protected EntityManagerFactory emf;

	protected LookUpCustomerIfc lookupCustomer;

	//Krishna Dynamic Query
	String emid;
	String empRoleAcc;
	List<Integer> divid;
	String SELECT_CUSTOMER_INVOICE = null;//"SELECT inv FROM CustomerSiteInvoice inv WHERE inv.invStatus like ?99";
	String WHERE_ORDER_ID = " AND inv.orderNum =?1 ";
	String WHERE_CUST_INFO = " AND inv.customerSite =?20 ";
	String ORDER_BY= " ORDER BY inv.arInvDate DESC";
	String FROM_ORDER_DATE = " AND od.id.orderDate>= ?22 ";
	String TO_ORDER_DATE = " AND od.id.orderDate<= ?33";
	String ORDER_TOTAL_RANGE = " AND inv.orderNum IN ?4 ";
	String WHERE_INVOICE_ID = " AND inv.arInvNum =?1 ";
	String FROM_INVOICE_DATE = " AND inv.arInvDate>= ?2 ";
	String TO_INVOICE_DATE = " AND inv.arInvDate<= ?3";
	String INVOICE_TOTAL_FROM_RANGE = " AND inv.invAmount>=?4";
	String INVOICE_TOTAL_TO_RANGE = " AND inv.invAmount<=?5";

	String SELECT_ORDER_TOTAL = "SELECT order.idOrd FROM OrderTranSum order WHERE ";
	String ORDER_TOTAL_FROM_RANGE = " AND order.dkartNetTot>=?1";
	String ORDER_TOTAL_TO_RANGE = " AND order.dkartNetTot<=?2";

	public final static String PAYMENT_TRANSACTION_TYPE = "18";
	public static final String CLAIM_TRANSACTION_TYPE = "2";
	public static final String DELIVERED_OR_COMPLET_RTLOG_GEN_ORD_TY = "24";
	public static final String PARTIAL_RTLOG_GEN_ORD_TY = "26";

	// This is the main method to search invoices 
	public OrderTranHeader[] lookUpOrderInvoices(OrderTransactionSearchCriteriaIfc criteria)
			throws OrderTransactionException {

		OrderTranHeader[] orderHeader = null;

		try {
				if (criteria.isSearchByinvoiceNumberOrOrderNumber())// true =  search by invoice no
				{
					orderHeader = SearchByInvoice(criteria, emid, empRoleAcc,divid);
				} else {
					orderHeader = SearchByOrderId(criteria, emid, empRoleAcc,divid); 
				}
		} catch (Exception e) {
			throw new OrderTransactionException(e.getMessage());
		}

		return orderHeader;
	}
	
		// invoice search as per role and security
	public OrderTranHeader[] lookUpOrderAllInvoices(OrderTransactionSearchCriteriaIfc criteria, String emplID,String empRole, List<Integer> divIds)
			throws OrderTransactionException {
			OrderTranHeader[] orderHeader = null;
				 emid=emplID;
				 empRoleAcc=empRole;
				 divid=divIds;
				orderHeader = lookUpOrderInvoices(criteria);
				return orderHeader;
	}

	// Invoice search By Order details
	private OrderTranHeader[] SearchByOrderId(OrderTransactionSearchCriteriaIfc criteria,String emid, String empRoleAcc,List<Integer> divid) throws Exception 
	{
		/*List allOrders=null;	
		String ORDER_TOTAL_QUERY=new String(SELECT_ORDER_TOTAL);
		*/String fromDate = criteria.getOrderDateRangeFrom();
		String toDate = criteria.getOrderDateRangeTo();
		DateFormat formatter = new SimpleDateFormat(ConfigUtils.getInstance().getDateFormat());
		Date startDate =null;
		Date endDate = null;
			LOGGER.info("empID="+emid+" empRoleName="+empRoleAcc+" empdivision"+divid);
		
		String div = divid.toString(); 
		char a=div.charAt(1);
		String finaldiv=String.valueOf(a);
		BigDecimal divisionid=new BigDecimal(finaldiv);
		
		
		try {
			if (fromDate != null && !fromDate.equalsIgnoreCase(""))
				startDate = (Date) formatter.parse(fromDate);
			if (toDate != null && !toDate.equalsIgnoreCase(""))
				endDate = (Date) formatter.parse(toDate);
		} catch (ParseException e) {

			LOGGER.error("Exception During parsing Date");
		}
		
		 String orderType="24"; // For full shipping order status
		 String orderTypePartial="26"; // For Partial shipping order status
		/* String orderStatus="7";
		 String orderstatus5="5";
		 BigDecimal orderSta=new BigDecimal(orderStatus);
		 BigDecimal orderSta5=new BigDecimal(orderstatus5);
		 
		*/ 
		
		if(empRoleAcc.equalsIgnoreCase("Linked Agent")) //Within Division All Linked Agent
		{
			SELECT_CUSTOMER_INVOICE = "SELECT inv FROM CustomerSiteInvoice inv,OrderTranSum ots,OrderTranHeader oth,OrderDetail od WHERE"
					+ " inv.orderNum=ots.idOrd AND ots.id.rtStrId=oth.id.rtStrId AND"
					+ " ots.id.ordWs=oth.id.ordWs AND ots.id.trnSeq=oth.id.trnSeq AND"
					+ " ots.id.dcDyOrd=oth.id.dcDyOrd AND inv.orderNum = od.id.orderId AND"
					+ " od.empId =?6 AND (oth.ordTy =?7 OR oth.ordTy =?8) AND inv.invStatus like ?99";
			
		}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
		{
			SELECT_CUSTOMER_INVOICE = "SELECT inv FROM CustomerSiteInvoice inv,OrderTranSum ots,OrderTranHeader oth,OrderDetail od WHERE"
					+ " inv.orderNum=ots.idOrd AND ots.id.rtStrId=oth.id.rtStrId AND"
					+ " ots.id.ordWs=oth.id.ordWs AND ots.id.trnSeq=oth.id.trnSeq AND"
					+ " ots.id.dcDyOrd=oth.id.dcDyOrd AND inv.orderNum = od.id.orderId AND"
					+ " od.divisionId =?10 AND (oth.ordTy =?7 OR oth.ordTy =?8) AND inv.invStatus like ?99";
			
		}else if(empRoleAcc.equalsIgnoreCase("All"))
		{
			SELECT_CUSTOMER_INVOICE = "SELECT inv FROM CustomerSiteInvoice inv,OrderTranSum ots,OrderTranHeader oth,OrderDetail od WHERE"
					+ " inv.orderNum=ots.idOrd AND ots.id.rtStrId=oth.id.rtStrId AND"
					+ " ots.id.ordWs=oth.id.ordWs AND ots.id.trnSeq=oth.id.trnSeq AND"
					+ " ots.id.dcDyOrd=oth.id.dcDyOrd AND inv.orderNum = od.id.orderId"
					+ " AND (oth.ordTy =?7 OR oth.ordTy =?8) AND inv.invStatus like ?99";
		}
		
		String INVOICE_SEARCH_QUERY = new String(SELECT_CUSTOMER_INVOICE);
		if (criteria.getInvoiceNumberOrOrderNumber() != null
				&& !criteria.getInvoiceNumberOrOrderNumber().equalsIgnoreCase("")) {
			INVOICE_SEARCH_QUERY += WHERE_ORDER_ID;
		}
		if (criteria.getOrderDateRangeFrom() != null && !criteria.getOrderDateRangeFrom().equalsIgnoreCase("")) {
			INVOICE_SEARCH_QUERY += FROM_ORDER_DATE;
		}
		if (criteria.getOrderDateRangeTo() != null && !criteria.getOrderDateRangeTo().equalsIgnoreCase("")) {
			INVOICE_SEARCH_QUERY += TO_ORDER_DATE;
		}
		if (criteria.getOrderTotalFrom() != null && !criteria.getOrderTotalFrom().equalsIgnoreCase("")) {
			INVOICE_SEARCH_QUERY += INVOICE_TOTAL_FROM_RANGE;
		}
		if (criteria.getOrderTotalTo() != null && !criteria.getOrderTotalTo().equalsIgnoreCase("")) {
			INVOICE_SEARCH_QUERY += INVOICE_TOTAL_TO_RANGE;
		}
		INVOICE_SEARCH_QUERY += ORDER_BY;
		OrderTranHeader[] orderHeader = null;
		EntityManager em = getEntityManager();
		//System.out.println("query: "+INVOICE_SEARCH_QUERY);
		Query qe = em.createQuery(INVOICE_SEARCH_QUERY, CustomerSiteInvoice.class);//.setParameter("emplID", emplID).setParameter("startDate", startDate);
		
		try {
			if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
			{
				qe.setParameter(6, emid);
				qe.setParameter(7, orderType).setParameter(8, orderTypePartial);
				//qe.setParameter(11, orderSta);
				
			}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
			{
				qe.setParameter(7, orderType).setParameter(8, orderTypePartial);
				qe.setParameter(10, divisionid);
				// qe.setParameter(11, orderSta);
			}else if(empRoleAcc.equalsIgnoreCase("All"))
			{
				qe.setParameter(7, orderType).setParameter(8, orderTypePartial);
				/*qe.setParameter(11, orderSta);
				qe.setParameter(12, orderSta5);
				*/
			}
			if (criteria.getInvoiceNumberOrOrderNumber() != null
					&& !criteria.getInvoiceNumberOrOrderNumber().equalsIgnoreCase(""))
				qe.setParameter(1, criteria.getInvoiceNumberOrOrderNumber());
			if (criteria.getOrderDateRangeFrom() != null && !criteria.getOrderDateRangeFrom().equalsIgnoreCase(""))
				qe.setParameter(22, startDate);
			if (criteria.getOrderDateRangeTo() != null && !criteria.getOrderDateRangeTo().equalsIgnoreCase(""))
				qe.setParameter(33, endDate);

			if(criteria.getOrderTotalFrom()!=null && !criteria.getOrderTotalFrom().equalsIgnoreCase(""))
				qe.setParameter(4, ConfigUtils.getInstance().createBigDecimal(criteria.getOrderTotalFrom(), "format.currency"));
			if(criteria.getOrderTotalTo()!=null&&!criteria.getOrderTotalTo().equalsIgnoreCase(""))
				qe.setParameter(5, ConfigUtils.getInstance().createBigDecimal(criteria.getOrderTotalTo(), "format.currency"));
		} catch (Exception w) {
			LOGGER.error(w);
		}

				setInvoiceTypeParameter(qe, criteria);
		List<CustomerSiteInvoice> sites = qe.getResultList();

		return getOrdertransactionHeader(sites, orderHeader, criteria);

	}
	
//Search By Invoice details.
	private OrderTranHeader[] SearchByInvoice(OrderTransactionSearchCriteriaIfc criteria,String emplID,String empRole, List<Integer> divIds) throws Exception {

		String fromDate = criteria.getInvoiceDateRangeFrom();
		String toDate = criteria.getInvoiceDateRangeTo();
		DateFormat formatter = new SimpleDateFormat(ConfigUtils.getInstance().getDateFormat());
		Date startDate = null;
		Date endDate = null;
		
		
		//System.out.println("EMpl role name: "+empName+"EMployee ID"+emplID+"division: "+divIds);
		
		String div = divIds.toString(); 
		char a=div.charAt(1);
		String finaldiv=String.valueOf(a);
		BigDecimal divisionid=new BigDecimal(finaldiv);
		
		try {
			if (fromDate != null && !fromDate.equalsIgnoreCase(""))
				startDate = (Date) formatter.parse(fromDate);
			if (toDate != null && !toDate.equalsIgnoreCase(""))
				endDate = (Date) formatter.parse(toDate);
		} catch (ParseException e) {

			LOGGER.error("Exception During parsing Date");
		}
		
		 String orderType="24"; // For Full shipping order status
		 String orderTypePartial="26"; // For Partial shipping order status 
 		/* String orderStatus="7";
		 * String orderStatus5="5";
		 * BigDecimal ordSta5=new BigDecimal(orderstatus5);
		 * BigDecimal ordSta=new BigDecimal(orderStatus);
		 */	
		
		if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
		{
		 SELECT_CUSTOMER_INVOICE = "SELECT inv FROM CustomerSiteInvoice inv,OrderTranSum ots,OrderTranHeader oth,OrderDetail od WHERE"
				+ " inv.orderNum=ots.idOrd AND ots.id.rtStrId=oth.id.rtStrId AND"
				+ " ots.id.ordWs=oth.id.ordWs AND ots.id.trnSeq=oth.id.trnSeq AND"
				+ " ots.id.dcDyOrd=oth.id.dcDyOrd AND inv.orderNum = od.id.orderId AND"
				+ " od.empId =?6 AND (oth.ordTy =?7 OR oth.ordTy =?8) AND inv.invStatus like ?99";
		 
		}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
		{
		SELECT_CUSTOMER_INVOICE = "SELECT inv FROM CustomerSiteInvoice inv,OrderTranSum ots,OrderTranHeader oth,OrderDetail od WHERE"
				+ " inv.orderNum=ots.idOrd AND ots.id.rtStrId=oth.id.rtStrId AND"
				+ " ots.id.ordWs=oth.id.ordWs AND ots.id.trnSeq=oth.id.trnSeq AND"
				+ " ots.id.dcDyOrd=oth.id.dcDyOrd AND inv.orderNum = od.id.orderId AND"
				+ " od.divisionId =?10 AND (oth.ordTy =?7 OR oth.ordTy =?8) AND inv.invStatus like ?99";
		
		}else if(empRoleAcc.equalsIgnoreCase("All"))
		{
		SELECT_CUSTOMER_INVOICE = "SELECT inv FROM CustomerSiteInvoice inv,OrderTranSum ots,OrderTranHeader oth WHERE"
				+ " inv.orderNum=ots.idOrd AND ots.id.rtStrId=oth.id.rtStrId AND"
				+ " ots.id.ordWs=oth.id.ordWs AND ots.id.trnSeq=oth.id.trnSeq AND"
				+ " ots.id.dcDyOrd=oth.id.dcDyOrd AND (oth.ordTy =?7 OR oth.ordTy =?8) AND inv.invStatus like ?99";
		}
		
		String INVOICE_SEARCH_QUERY = new String(SELECT_CUSTOMER_INVOICE);
		if (criteria.getInvoiceNumberOrOrderNumber() != null
				&& !criteria.getInvoiceNumberOrOrderNumber().equalsIgnoreCase("")) {
			INVOICE_SEARCH_QUERY += WHERE_INVOICE_ID;
		}
		if (criteria.getInvoiceDateRangeFrom() != null && !criteria.getInvoiceDateRangeFrom().equalsIgnoreCase("")) {
			INVOICE_SEARCH_QUERY += FROM_INVOICE_DATE;
		}
		if (criteria.getInvoiceDateRangeTo() != null && !criteria.getInvoiceDateRangeTo().equalsIgnoreCase("")) {
			INVOICE_SEARCH_QUERY += TO_INVOICE_DATE;
		}
		if (criteria.getInvoiceTotalFrom() != null && !criteria.getInvoiceTotalFrom().equalsIgnoreCase("")) {
			INVOICE_SEARCH_QUERY += INVOICE_TOTAL_FROM_RANGE;
		}
		if (criteria.getInvoiceTotalTo() != null && !criteria.getInvoiceTotalTo().equalsIgnoreCase("")) {
			INVOICE_SEARCH_QUERY += INVOICE_TOTAL_TO_RANGE;
		}
		INVOICE_SEARCH_QUERY += ORDER_BY;
		OrderTranHeader[] orderHeader = null;
		EntityManager em = getEntityManager();
		//System.out.println("query: "+INVOICE_SEARCH_QUERY);
		Query qe = em.createQuery(INVOICE_SEARCH_QUERY, CustomerSiteInvoice.class);//.setParameter("emplID", emplID).setParameter("startDate", startDate);

		try {
			if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
			{
				qe.setParameter(6, emplID);
				qe.setParameter(7, orderType).setParameter(8, orderTypePartial);
				//qe.setParameter(11, ordSta);
			}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
			{
				qe.setParameter(7, orderType).setParameter(8, orderTypePartial);
				qe.setParameter(10, divisionid);
				//qe.setParameter(11, ordSta);
				
			}else if(empRoleAcc.equalsIgnoreCase("All"))
			{
				qe.setParameter(7, orderType).setParameter(8, orderTypePartial);
				//qe.setParameter(11, ordSta);
			}
			if (criteria.getInvoiceNumberOrOrderNumber() != null
					&& !criteria.getInvoiceNumberOrOrderNumber().equalsIgnoreCase(""))
				qe.setParameter(1, criteria.getInvoiceNumberOrOrderNumber());
			if (criteria.getInvoiceDateRangeFrom() != null && !criteria.getInvoiceDateRangeFrom().equalsIgnoreCase(""))
				qe.setParameter(2, startDate);
			if (criteria.getInvoiceDateRangeTo() != null && !criteria.getInvoiceDateRangeTo().equalsIgnoreCase(""))
				qe.setParameter(3, endDate);
			if (criteria.getInvoiceTotalFrom() != null && !criteria.getInvoiceTotalFrom().equalsIgnoreCase(""))
				qe.setParameter(4, new BigDecimal(criteria.getInvoiceTotalFrom()));
			if (criteria.getInvoiceTotalTo() != null && !criteria.getInvoiceTotalTo().equalsIgnoreCase(""))
				qe.setParameter(5, new BigDecimal(criteria.getInvoiceTotalTo()));

		} catch (Exception w) {
			LOGGER.error(w);
		}

		setInvoiceTypeParameter(qe, criteria);
		//LOGGER.info("set parameter");
		List<CustomerSiteInvoice> sites = qe.getResultList();
		LOGGER.info("Finished set parameter");
		return getOrdertransactionHeader(sites, orderHeader, criteria);

	}

	// 99 All Invoice; 0 Closed Invoice ; 1 Open Invoice
	private void setInvoiceTypeParameter(Query qe, OrderTransactionSearchCriteriaIfc criteria) {

		int str=criteria.getTypeOfInvoice();
		if (criteria.getTypeOfInvoice() != 99) {
			
	
			//LOGGER.info("status number:"+str);
			qe.setParameter(99, criteria.getTypeOfInvoice());
		} else {
			qe.setParameter(99, '%');
			//LOGGER.info("status ELse number:"+str);
		}

	}

	// Each Order will only have one customer site invoice to get correct result
	private OrderTranHeader[] getOrdertransactionHeader(List<CustomerSiteInvoice> sites, OrderTranHeader[] orderHeader,
			OrderTransactionSearchCriteriaIfc criteria)
	{String customerId = "";
	String orderId = "";
    EntityManager em = getEntityManager();
    ArrayList<OrderTranHeader> orderTransaction = new ArrayList<OrderTranHeader>();
	if (sites.size() > 0)
	{
		for (CustomerSiteInvoice site : sites) 
		{
			orderId = site.getOrderNum();
			customerId = site.getCustomerSite().getCustomerSitePK().getCustId();
			CustomerHeader header = null;
			if (!customerId.equalsIgnoreCase("") && customerId != null) // look  up  using  the  customer  id  and  add  the  customer  to  the  transaction
			{
				Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);
				
				header = (CustomerHeader) customerQ.getSingleResult();
			}
			
			

			if (header!=null)
			{
				CustomerIfc customer = new Customer();
				// set the customer to the order trasnaction
				customer.setCustomerHeader(header);
				customer.setCustomerLimits(getCustomerlimits(customerId));
				Query payments = em.createNamedQuery("CUSTOMER_PAYMENT_TERMS", CustomerPaymentTerm.class)
						.setParameter("custId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

				List<CustomerPaymentTerm> payment = payments.getResultList();
				if (payment.size() > 0) {
					customer.setPaymentTerms(payment.get(0));
				}
				List<CustomerSiteInvoice> customersites = new ArrayList<>();
				
				customersites.add(site);
				customer.setSiteInvoices(customersites);
				
				/// using the order id found from the customer site table find the order transaction
				Query tranQuery = em.createNamedQuery("INVOICE_DETAILS_ORDERID", OrderTranHeader.class)
						.setParameter("orderId", orderId);/// removed for
				/// testing the issue

				//List<OrderTranHeader> ordertrans = tranQuery.getResultList();
				//tranQuery.setFirstResult(0);
				//tranQuery.setMaxResults(1);
				
				
				List <OrderTranHeader> ordertrans=null;
				try
				{
				ordertrans= tranQuery.getResultList();
				}catch(Exception e)
				{
					LOGGER.info(tranQuery.toString(),e);
				}
				
				
				
				
				if (ordertrans!=null&&ordertrans.size()>0) {
					for( OrderTranHeader ordertran:ordertrans)
					{
						if(ordertran.getOrdTy().equalsIgnoreCase("26")
								||ordertran.getOrdTy().equalsIgnoreCase("24"))
						{
						ordertran.setCustomer(customer);/// set customer to the Order Transaction
						orderTransaction.add(ordertran);
						}
					}

					

				} else {
					////// exception if one transaction header is not found
					continue;
				}
			} else {

				////// exception if one transaction header is not found
				continue;
			}

		}
		//mudassir
		if(criteria.isFromDeliverySearch()){
		orderTransaction=	getDistinctTranSum(orderTransaction);
		}
		if (orderTransaction!=null && orderTransaction.size() > 0) {
		
			orderHeader = new OrderTranHeader[orderTransaction.size()];
			orderHeader= orderTransaction.toArray(orderHeader);
		} else {
			/// if not data was found from any of the two queries then set the values to null
			orderHeader = null;
		}
	}
	return orderHeader;
}
	private EntityManager getEntityManager() {
        return emf.createEntityManager();
}

	//mudassir getting order arryList
	private ArrayList<OrderTranHeader>  getDistinctTranSum(List<OrderTranHeader> orderhead){
		EntityManager em = getEntityManager();
		List<String> res=new ArrayList();
		ArrayList<OrderTranHeader> main=new ArrayList();
		try{
	List<OrderTranHeaderPK> headp	=em.createNamedQuery("DELIVERED_ORDERS_HEADER_SELECT").setParameter("param1", "24").setParameter("param2", 5).setParameter("param3", "26").setParameter("param4", 4).getResultList();	
	if(headp!=null){
		for(OrderTranHeaderPK wrap:headp){
		if(Long.valueOf(wrap.getTrnSeq())!=null && wrap.getDcDyOrd()!=null)
			res.add(wrap.getTrnSeq()+"@"+wrap.getDcDyOrd());
		}
	}
		if(res!=null && !res.isEmpty()){
		if(orderhead!=null){
			for(OrderTranHeader wrap:orderhead){
				if(res.contains(wrap.getId().getTrnSeq()+"@"+wrap.getId().getDcDyOrd())){
					//orderhead.remove(wrap);
					main.add(wrap);
				}
			}
		}
		}
		else{
			orderhead.clear();	
		}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		if(main!=null && !main.isEmpty() ){
			return main;
		}
		else{
			return null;
		}
		
	}

	private void addOrderHedersTolist(ArrayList<OrderTranHeader> orderHeaderList, OrderTranHeader[] orderHeader) {
		if (orderHeaderList.isEmpty() && orderHeader != null && orderHeader.length > 0) {
			orderHeaderList.addAll(Arrays.asList(orderHeader));
		} else if (orderHeader != null && orderHeader.length > 0) {
			orderHeaderList.retainAll(Arrays.asList(orderHeader));
		}

	}

	public OrderTranHeader[] lookUpOrdersOrInvoices(OrderTransactionSearchCriteriaIfc criteria)
			throws OrderTransactionException {

		OrderTranHeader[] orderHeader = null;

		try {
			if (criteria.isSearchByinvoiceNumberOrOrderNumber() && criteria.getInvoiceNumberOrOrderNumber() != null
					&& !criteria.getInvoiceNumberOrOrderNumber().equalsIgnoreCase("")) {
				orderHeader = lookUpTransactionByinvoiceOrOrder(orderHeader, criteria);
			} else if (criteria.isSearchByCustomerInfo() && criteria.getCustomerInfo() != null) {
				orderHeader = lookUpTransactionByCustomerInfo(orderHeader, criteria);

			} else if (criteria.isSearchByInvoiceTotal() && criteria.getInvoiceTotalFrom() != null
					&& !criteria.getInvoiceTotalFrom().equalsIgnoreCase("") && criteria.getInvoiceTotalTo() != null
					&& !criteria.getInvoiceTotalTo().equalsIgnoreCase("")) {
				orderHeader = lookUpTransactionByInvoiceTotals(orderHeader, criteria);
			} else if (criteria.getInvoiceDateRangeFrom() != null
					&& !criteria.getInvoiceDateRangeFrom().equalsIgnoreCase("")
					&& criteria.getInvoiceDateRangeTo() != null
					&& !criteria.getInvoiceDateRangeTo().equalsIgnoreCase("")) {
				orderHeader = lookUpTransactionByInvoiceDates(orderHeader, criteria);
			} else if (criteria.getOrderDateRangeFrom() != null
					&& !criteria.getOrderDateRangeFrom().equalsIgnoreCase("") && criteria.getOrderDateRangeTo() != null
					&& !criteria.getOrderDateRangeTo().equalsIgnoreCase("")) {
				orderHeader = lookUpTransactionByOrderDates(orderHeader, criteria);
			} else if (criteria.getOrderTotalFrom() != null && !criteria.getOrderTotalFrom().equalsIgnoreCase("")
					&& criteria.getOrderTotalTo() != null && !criteria.getOrderTotalTo().equalsIgnoreCase("")) {
				orderHeader = lookUpTransactionByOrderTotals(orderHeader, criteria);
			}
		} catch (Exception e) {
			throw new OrderTransactionException(e.getMessage());
		} finally {
			// em.close();
		}

		return orderHeader;
	}
	
	// process for disable option
	public String disablePermissionForAll(Long empRoleId,String functionId)
	{
		String Disable_option=null;
		List<RisplDkEmpRoleAccess> RisplDkEmpRoleAccessList=new ArrayList<>();
		try{
			
			String str = "SELECT rispl FROM RisplDkEmpRoleAccess rispl WHERE rispl.id.roleId=:empRoleId AND rispl.id.functionId=:functionId";
								
		EntityManager em = getEntityManager();
		Query qe = em.createQuery(str, OrderTranHeader.class).setParameter("empRoleId", empRoleId).setParameter("functionId", functionId);
		
			RisplDkEmpRoleAccessList=qe.getResultList();
			for(RisplDkEmpRoleAccess RisplDkEmpRoleAccess:RisplDkEmpRoleAccessList)
			{
			Disable_option=RisplDkEmpRoleAccess.getHasAccess();
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return Disable_option;
	}
	
	// code for to get complete order info @ jagadish
	public List<OrderDetailsWithInvoice> getCompleteOrderInfo(String orderNum)
	{
		List<OrderDetailsWithInvoice> OrderDetailsWithInvoice=new ArrayList<>();
			try{
				String str = "select odwi from OrderDetailsWithInvoice odwi WHERE odwi.id.orderId=:orderNum";
				
				EntityManager em = getEntityManager();
				Query qe = em.createQuery(str, OrderDetailsWithInvoice.class).setParameter("orderNum", orderNum);
				OrderDetailsWithInvoice=qe.getResultList();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return OrderDetailsWithInvoice;
	}
	
	// by jagadish for display reason code description in order details page & invoice Details page
	public List<DkartReasonCodes> getAllDisRsnCodeDiscrpn()
	{
		List<DkartReasonCodes> DkartReasonCodes=new ArrayList<>();
			try{
				String str = "select dkartReasonCodes from DkartReasonCodes dkartReasonCodes";
				
				EntityManager em = getEntityManager();
				Query qe = em.createQuery(str, OrderTranDiscountItem.class);
				DkartReasonCodes=qe.getResultList();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return DkartReasonCodes;
	}
	
	// by lalit for display exciseTax in order details page & invoice Details page
		public List<ExciseTaxItem> getAllExciseTaxItem()
		{
			List<ExciseTaxItem> ExciseTaxItem=new ArrayList<>();
				try{
					String str = "select exciseTaxItem from ExciseTaxItem exciseTaxItem";
					
					EntityManager em = getEntityManager();
					Query qe = em.createQuery(str, ExciseTaxItem.class);
					ExciseTaxItem=qe.getResultList();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			return ExciseTaxItem;
		}
	
	//print comment in cancel print page 
	public String lookUpOrdersOrInvoicesForComment(String criteria)
			throws OrderTransactionException 
	{
		List<OrderTranHeader> OrderTranHeaderList=new ArrayList<>();
		
		String ordertype="23";
		String result=null;
			try{
				
				String str = "SELECT oth FROM OrderTranHeader oth,OrderTranSum ots WHERE"
						+ " ots.id.rtStrId=oth.id.rtStrId AND ots.id.dcDyOrd=oth.id.dcDyOrd AND"
						+ " ots.id.ordWs=oth.id.ordWs AND ots.id.trnSeq=oth.id.trnSeq AND"
						+ " ots.idOrd=:criteria AND oth.ordTy=:ordertype";
			
			EntityManager em = getEntityManager();
			Query qe = em.createQuery(str, OrderTranHeader.class).setParameter("criteria", criteria).setParameter("ordertype", ordertype);
			
			OrderTranHeaderList=qe.getResultList();
			for(OrderTranHeader orderTranHeader:OrderTranHeaderList)
			{
				result=orderTranHeader.getCtDvrInfoIns();
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	
	
	/*
	private OrderTranHeader[] lookUpTransactionForComment(OrderTranHeader[] orderHeader, String criteria) 
	{
		
		Query qe = em.createNamedQuery("SEARCH_CUSTOMER_ARINV_NUM_ORD_ID", CustomerSiteInvoice.class)
				.setParameter("orderId", criteria.getInvoiceNumberOrOrderNumber())
				.setParameter("invNumber", criteria.getInvoiceNumberOrOrderNumber());// Query("Select OrderTranHeader.class);
		List<CustomerSiteInvoice> invoiceList = qe.getResultList();
		return null;
	}
*/
	public OrderTranHeader[] lookUpTransactionByinvoiceOrOrder(OrderTranHeader[] orderHeader,
			OrderTransactionSearchCriteriaIfc criteria) {
		EntityManager em = getEntityManager();
		Vector<OrderTranHeader> orderTransaction = new Vector<OrderTranHeader>();

		try {
			// using the order id or invoice number entered on the UI get the customer Information first and then the order transaction
			Query qe = em.createNamedQuery("SEARCH_CUSTOMER_ARINV_NUM_ORD_ID", CustomerSiteInvoice.class)
					.setParameter("orderId", criteria.getInvoiceNumberOrOrderNumber())
					.setParameter("invNumber", criteria.getInvoiceNumberOrOrderNumber());// Query("Select OrderTranHeader.class);
			List<CustomerSiteInvoice> invoiceList = qe.getResultList();
			String customerId = "";
			String orderId = "";
			if (invoiceList.size() > 0) {

				customerId = invoiceList.get(0).getCustomerSite().getCustomerSitePK().getCustId();

				orderId = invoiceList.get(0).getOrderNum();
				// for(CustomerSiteInvoice site:sites){
				// site.getCustomerSite().getCustomerPaymentTerms();
				// site.getCustomerSite().getCustomerSiteAddressList().size();
				// site.getCustomerSite().getCustomerSiteInvoiceList().size();
				// site.getCustomerSite().getCustomerSiteLimitList().size();
				// site.getCustomerSite().getCustomerSitePhoneList().size();
				// site.getCustomerSite().getCustomerSiteStoreList().size();
				// }

				// as the search with the order id or invoice id so the list
				// will be only one

				List<CustomerHeader> customerList = null;
				if (!customerId.equalsIgnoreCase("") && customerId != null) {
					// look up using the customer id and add the customer to the
					// transaction
					Query customer = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
							.setParameter("customerId", customerId);

					customerList = customer.getResultList();
				}

				if (customerList.size() > 0) {
					CustomerIfc customer = new Customer();
					
					//customer.setCustomerSegmentID(getSegmentId(customerId));
					Query payments = em.createNamedQuery("CUSTOMER_PAYMENT_TERMS", CustomerPaymentTerm.class)
							.setParameter("custId", customerId);

					List<CustomerPaymentTerm> payment = payments.getResultList();
					if (payment.size() > 0) {
						customer.setPaymentTerms(payment.get(0));
					}

					customer.setSiteInvoices(invoiceList);

					customer.setCustomerSegmentID(lookupCustomer.getCustomerSegmentID(customerId));
					// set the customer to the order transaction

					// header.get(0).getPrcngGrpId().setCustomerHeaderList(null);
					customer.setCustomerHeader(customerList.get(0));
					customerList.size();

					/// using the order id found from the customer site table find the order transaction
					Query tranQuery = em.createNamedQuery("INVOICE_DETAILS_ORDERID", OrderTranHeader.class)
							.setParameter("orderId", orderId);/// removed for testing the issue

					List<OrderTranHeader> ordertrans = tranQuery.getResultList();
					OrderTransactionLpo lpoDetails = null;
					for(OrderTranHeader ordHead : ordertrans){
						if(ordHead.getOrdTranLpo()!=null){
							lpoDetails = ordHead.getOrdTranLpo();
						}
					}
					if (ordertrans.size() > 0) {
						//Adding setting customer site address for that particular order @Vishal
						Query address_query = em.createNamedQuery("CUSTOMER_SITE_ADDRESS_BY_ADS_ID")
								.setParameter("adsId", ordertrans.get(0).getDeliveryAddressID());
						List<CustomerSiteAddress> address = address_query.getResultList();
						if (address != null && address.size() > 0) {
							customer.setCustomerSiteAddress(address);
						}
						//End @Vishal
						
						customer.setCustomerSegmentID(lookupCustomer.getCustomerSegmentID(customer.getCustomerHeader().getCustomerHeaderPK().getCustId()));
						
						
						
						ordertrans.get(0).setCustomer(customer);/// set customer to the order transaction
						if(ordertrans.get(0).getOrdTranLpo()==null){
							ordertrans.get(0).setOrdTranLpo(lpoDetails);
						}
						orderTransaction.add(ordertrans.get(0));
						ordertrans.get(0).getOrdTranLineItems().size();
						//ordertrans.get(0).getOrdTranSums().size();
						orderHeader = new OrderTranHeader[orderTransaction.size()];
						orderTransaction.copyInto(orderHeader);
					} else {
						/// if not data was found from any of the two queries then set the values to null
						orderHeader = null;
					}
				} else {
					/// if not data was found from any of the two queries then set the values to null
					orderHeader = null;
				}

			} else {
				Query tranQuery = em.createNamedQuery("INVOICE_DETAILS_ORDERID", OrderTranHeader.class)
						.setParameter("orderId", criteria.getInvoiceNumberOrOrderNumber());/// removed for testing the issue

				List<OrderTranHeader> ordertrans = tranQuery.getResultList();

				OrderTransactionLpo lpo = null;
				for(OrderTranHeader tranHead : ordertrans){
					if(tranHead.getOrdTranLpo()!=null){
						lpo = tranHead.getOrdTranLpo();
					}
				}

				if (ordertrans!=null && ordertrans.size() > 0) {
					customerId = ordertrans.get(0).getOrdTranSum().getOrdIdCt();
					List<CustomerHeader> header = null;
					if (customerId != null /*&& customerId.equalsIgnoreCase("")*/  ) {
						///// look up using the customer id and add the customer to the transaction
						Query customer = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
								.setParameter("customerId", customerId);

						header = customer.getResultList();
					}

					if (header!=null && header.size() > 0) {
						CustomerIfc customer = new Customer();

						//Adding setting customer site address for that particular order @Vishal
						Query address_query = em.createNamedQuery("CUSTOMER_SITE_ADDRESS_BY_ADS_ID")
								.setParameter("adsId", ordertrans.get(0).getDeliveryAddressID());
						List<CustomerSiteAddress> address = address_query.getResultList();
						if (address != null && address.size() > 0) {
							customer.setCustomerSiteAddress(address);
						}
						//End @Vishal

						customer.setSiteInvoices(invoiceList);
						// header.get(0).getPrcngGrpId().getCustomerHeaderList().size();////not instantiated error
						header.get(0).getPrcngGrpId().setCustomerHeaderList(null);
						// set the customer to the order transaction
						customer.setCustomerHeader(header.get(0));
						customer.setCustomerSegmentID(lookupCustomer.getCustomerSegmentID(customerId));
						ordertrans.get(0).setCustomer(customer);/// set customer to the order transaction
						Query payments = em.createNamedQuery("CUSTOMER_PAYMENT_TERMS", CustomerPaymentTerm.class)
								.setParameter("custId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

						List<CustomerPaymentTerm> payment = payments.getResultList();
						if (payment!=null && payment.size() > 0) {
							customer.setPaymentTerms(payment.get(0));
						}
						
						
						
					}

					ordertrans.get(0).getOrdTranLineItems().size();
					ordertrans.get(0).getOrdTranLineItems().get(0).getOrdTranDscItms().size();
					//ordertrans.get(0).getOrdTranSums().size();
					if(lpo!=null && ordertrans.get(0).getOrdTranLpo()==null){
						ordertrans.get(0).setOrdTranLpo(lpo);
					}
					orderTransaction.add(ordertrans.get(0));

				} else {
					/// if not data was found from any of the two queries then set the values to null
					orderHeader = null;
				}

			}
			orderHeader = new OrderTranHeader[orderTransaction.size()];
			orderTransaction.copyInto(orderHeader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// apply all reason codes to orderTransHeader 
		for (OrderTranHeader orderTranHeader : orderHeader) {
			orderTranHeader.setReasonCodes(getAllReasonCodes());
		}
		
		// add shipped quantity details
		List<OrderTranHeader> orderTranHeaderList = new ArrayList<OrderTranHeader>(Arrays.asList(orderHeader));
		addShippedQtyDetails(orderTranHeaderList);
		
		return orderHeader;
	}

	/**
	 * Distribute the shipped item details across the LineItems in OrderTranHeader
	 * @param orderHeader
	 */
	public void addShippedQtyDetails(List<OrderTranHeader> orderHeader) {
		for (OrderTranHeader orderTranHeader : orderHeader) {
			List<Object> shippedItems = getShippedQty(orderTranHeader.getOrdTranSum().getIdOrd());

			if (shippedItems != null && shippedItems.size() > 0) {

				Map<String, Long> shippedItemsQtyMap = shippedItems.stream().collect(
						Collectors.toMap(item -> ((Object[]) item)[1].toString(), item -> (Long) ((Object[]) item)[2]));
				LOGGER.debug("Shipped Item-Qty Bucket before:\n" + shippedItemsQtyMap);
				orderTranHeader.getOrdTranLineItems().stream()
						.filter(line -> shippedItemsQtyMap.containsKey(line.getItemId())).forEach(line -> {
							if (shippedItemsQtyMap.get(line.getItemId()) > line.getLineQnt().longValue()) {
								line.setShippedQty(line.getLineQnt().longValue());
							} else {
								line.setShippedQty(shippedItemsQtyMap.get(line.getItemId()));
							}
							//Update Item-Qty bucket
							shippedItemsQtyMap.put(line.getItemId(),
									shippedItemsQtyMap.get(line.getItemId()) - line.getShippedQty());
						});
				LOGGER.debug("Shipped Item-Qty Bucket after:\n" + shippedItemsQtyMap);
			}
		}
	}

	public List<Object> getShippedQty(String orderId){
		List<Object> DeliveredQty=null;
		try{
			EntityManager em = getEntityManager();
			DeliveredQty = em.createNamedQuery("GET_SHIPPED_QTY").setParameter("orderId",orderId).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return DeliveredQty;
		
	}
	
	public List<Object> getPickedQty(String orderId){
		List<Object> DeliveredQty=null;
		try{
			EntityManager em = getEntityManager();
			DeliveredQty = em.createNamedQuery("GET_PICKED_QTY").setParameter("orderId",orderId).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return DeliveredQty;
		
	}

	public OrderTranHeader[] lookUpTransactionByCustomerInfo(OrderTranHeader[] orderHeader,
			OrderTransactionSearchCriteriaIfc criteria) {
		///// search by customer information
		EntityManager em = getEntityManager();
		Vector<OrderTranHeader> orderTransaction = new Vector<OrderTranHeader>();
		try {
			// once the customer was selected on the browser then the customers related order transaction will be queried
			if (criteria.getCustomerInfo() != null && criteria.getCustomerInfo().getCustomerId() != null) {
				Query qe = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", criteria.getCustomerInfo().getCustomerId());

				/////// for order search with customer info just add the extra query to execute

				List<CustomerHeader> headers = qe.getResultList();
				int valuesInterating = 0;
				int maximumResults = criteria.getMaximumResults();
				/// as the search with the customer info the values can be more then one
				if (headers.size() > 0) {
					CustomerIfc customer = new Customer();////// look up for customers details
					customer.setCustomerHeader(headers.get(0));
					try {
						customer = lookUpCustomerInfo(customer);
					} catch (CustomerException e) {
						// TODO Auto-generated catch block
						throw new OrderTransactionException();
					}

					for (CustomerHeader header : headers) {
						//// this will be only one because customers search by id results only one
						/// using the order id found from the customer site table find the order transaction
						Query tranQuery = em.createNamedQuery("INVOICE_DETAILS_CUSTID", OrderTranHeader.class)
								.setParameter("customerId", header.getCustomerHeaderPK().getCustId());

						List<OrderTranHeader> ordertrans = tranQuery.getResultList();
						if (ordertrans.size() > 0) {
							for (OrderTranHeader orderTran : ordertrans) {

								Query payments = em
										.createNamedQuery("CUSTOMER_PAYMENT_TERMS", CustomerPaymentTerm.class)
										.setParameter("custId",
												customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

								List<CustomerPaymentTerm> payment = payments.getResultList();
								if (payment.size() > 0) {
									customer.setPaymentTerms(payment.get(0));
								}

								orderTran.setCustomer(customer);/// set customer to the order transaction
								orderTran.getOrdTranLineItems().size();
								for (OrderTranLineItem item : orderTran.getOrdTranLineItems()) {
									item.getOrdTranDscItms().size();
								}
								orderTran.getOrdTranLineItems().get(0).getOrdTranDscItms().size();
								//orderTran.getOrdTranSums().size();
								///// add all the transactions found to the vector
								orderTransaction.add(orderTran);
								// check maximum number of order
								if (maximumResults != 0) {/// if maximum results is by default dont check it
									valuesInterating++;
									if (valuesInterating == maximumResults) {
										break;//// dont iterate for more values
									}
								}

							}
							orderTransaction.copyInto(orderHeader);
						} else {
							/// if not data was found from any of the two queries then set the values to null
							orderHeader = null;
						}
					}
				} else {
					/// if not data was found from any of the two queries then set the values to null
					orderHeader = null;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderHeader;
	}

	public OrderTranHeader[] lookUpTransactionByInvoiceDates(OrderTranHeader[] orderHeader,
			OrderTransactionSearchCriteriaIfc criteria) throws OrderTransactionException {
		EntityManager em = getEntityManager();
		String datestr = criteria.getInvoiceDateRangeFrom();
		String datestr1 = criteria.getInvoiceDateRangeTo();
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = (Date) formatter.parse(datestr);
			endDate = (Date) formatter.parse(datestr1);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Vector<OrderTranHeader> orderTransaction = new Vector<OrderTranHeader>();
		Query qe = em.createNamedQuery("INVOICE_SEARCH_BY_INVOICE_DATE_RANGE", CustomerSiteInvoice.class)
				.setParameter("from", startDate).setParameter("to", endDate);

		List<CustomerSiteInvoice> sites = qe.getResultList();

		String customerId = "";
		String orderId = "";
		if (sites.size() > 0) {
			for (CustomerSiteInvoice site : sites) {
				orderId = site.getOrderNum();
				if (sites.size() > 0) {

					customerId = site.getCustomerSite().getCustomerSitePK().getCustId();

					orderId = site.getOrderNum();

				} else {
					orderHeader = null;
				}

				/// as the search with the order id or invoice id so the list will be only one

				List<CustomerHeader> header = null;
				if (!customerId.equalsIgnoreCase("") && customerId != null) {// look  up  using  the  customer  id  and  add  the  customer  to  the  transaction
					Query customer = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
							.setParameter("customerId", customerId);

					header = customer.getResultList();
				}

				if (header.size() > 0) {
					CustomerIfc customer = new Customer();

					customer.setSiteInvoices(sites);
					// set the customer to the order trasnaction
					customer.setCustomerHeader(header.get(0));
					try {
						customer = lookUpCustomerInfo(customer);
					} catch (CustomerException e) {
						// TODO Auto-generated catch block
						throw new OrderTransactionException();
					}

					/// using the order id found from the customer site table find the order transaction
					Query tranQuery = em.createNamedQuery("INVOICE_DETAILS_ORDERID", OrderTranHeader.class)
							.setParameter("orderId", orderId);/// removed for
					/// testing the issue

					List<OrderTranHeader> ordertrans = tranQuery.getResultList();
					if (ordertrans.size() > 0) {
						Query payments = em.createNamedQuery("CUSTOMER_PAYMENT_TERMS", CustomerPaymentTerm.class)
								.setParameter("custId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

						List<CustomerPaymentTerm> payment = payments.getResultList();
						if (payment.size() > 0) {
							customer.setPaymentTerms(payment.get(0));
						}
						ordertrans.get(0).setCustomer(customer);/// set customer to the order transaction
						orderTransaction.add(ordertrans.get(0));

					} else {
						////// exception if one transaction header is not found
						continue;
					}
				} else {
					////// exception if one transaction header is not found
					continue;
				}

			}
			if (orderTransaction.size() > 0) {
				orderHeader = new OrderTranHeader[orderTransaction.size()];
				orderTransaction.copyInto(orderHeader);
			} else {
				/// if not data was found from any of the two queries then set the values to null
				orderHeader = null;
			}
		}
		return orderHeader;
	}

	public OrderTranHeader[] lookUpTransactionByOrderDates(OrderTranHeader[] orderHeader,
			OrderTransactionSearchCriteriaIfc criteria) {
		EntityManager em = getEntityManager();
		String datestr = criteria.getInvoiceDateRangeFrom();
		String datestr1 = criteria.getInvoiceDateRangeTo();
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = (Date) formatter.parse(datestr);
			endDate = (Date) formatter.parse(datestr1);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Vector<OrderTranHeader> orderTransaction = new Vector<OrderTranHeader>();
		Query qe = em.createNamedQuery("INVOICE_SEARCH_BY_ORDER_DATE_RANGE", CustomerSiteInvoice.class)
				.setParameter("from", startDate).setParameter("to", endDate);

		List<CustomerSiteInvoice> sites = qe.getResultList();

		String customerId = "";
		String orderId = "";
		if (sites.size() > 0) {
			for (CustomerSiteInvoice site : sites) {
				orderId = site.getOrderNum();
				if (sites.size() > 0) {

					customerId = site.getCustomerSite().getCustomerSitePK().getCustId();

					orderId = site.getOrderNum();

				} else {
					orderHeader = null;
				}

				/// as the search with the order id or invoice id so the list will be only one

				List<CustomerHeader> header = null;
				if (!customerId.equalsIgnoreCase("") && customerId != null) {// look  up  using  the  customer  id  and  add  the  customer  to  the  transaction
					Query customer = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
							.setParameter("customerId", customerId);

					header = customer.getResultList();
				}

				if (header.size() > 0) {
					CustomerIfc customer = new Customer();

					Query payments = em.createNamedQuery("CUSTOMER_PAYMENT_TERMS", CustomerPaymentTerm.class)
							.setParameter("custId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

					List<CustomerPaymentTerm> payment = payments.getResultList();
					if (payment.size() > 0) {
						customer.setPaymentTerms(payment.get(0));
					}

					customer.setSiteInvoices(sites);
					// set the customer to the order trasnaction
					customer.setCustomerHeader(header.get(0));

					/// using the order id found from the customer site table find the order transaction
					Query tranQuery = em.createNamedQuery("INVOICE_DETAILS_ORDERID", OrderTranHeader.class)
							.setParameter("orderId", orderId);/// removed for
					/// testing the issue

					List<OrderTranHeader> ordertrans = tranQuery.getResultList();
					if (ordertrans.size() > 0) {
						ordertrans.get(0).setCustomer(customer);/// set customer
																/// to
																/// the
																/// order
																/// transaction
						orderTransaction.add(ordertrans.get(0));

					} else {
						////// exception if one transaction header is not found
						continue;
					}
				} else {

					////// exception if one transaction header is not found
					continue;
				}

			}
			if (orderTransaction.size() > 0) {
				orderHeader = new OrderTranHeader[orderTransaction.size()];
				orderTransaction.copyInto(orderHeader);
			} else {
				/// if not data was found from any of the two queries then set the values to null
				orderHeader = null;
			}
		}

		return orderHeader;
	}

	public OrderTranHeader[] lookUpTransactionByOrderTotals(OrderTranHeader[] orderHeader,
			OrderTransactionSearchCriteriaIfc criteria) {

		EntityManager em = getEntityManager();
		Vector<OrderTranHeader> orderTransaction = new Vector<OrderTranHeader>();
		Query tranQuery = em.createNamedQuery("ORDER_DETAILS_BETWEEN_AMOUNTS", OrderTranHeader.class)
				.setParameter("from", new BigDecimal(criteria.getOrderTotalFrom()))
				.setParameter("to", new BigDecimal(criteria.getOrderTotalTo()));
		List<OrderTranHeader> ordertrans = tranQuery.getResultList();
		int valuesInterating = 0;
		int maximumResults = criteria.getMaximumResults();
		if (ordertrans.size() > 0) {
			for (OrderTranHeader orderTran : ordertrans) {

				orderTransaction.add(orderTran);

				//check maximum number of order
				if (maximumResults != 0) {/// if maximum results is by default dont check it
					valuesInterating++;
					if (valuesInterating == maximumResults) {
						break;//// dont iterate for more values
					}
				}

			}
			orderTransaction.copyInto(orderHeader);
		} else {
			/// if not data was found from any of the two queries then set the values to null
			orderHeader = null;
		}

		return orderHeader;
	}

	public OrderTranHeader[] lookUpTransactionByInvoiceTotals(OrderTranHeader[] orderHeader,
			OrderTransactionSearchCriteriaIfc criteria) {
		EntityManager em = getEntityManager();
		Vector<OrderTranHeader> orderTransaction = new Vector<OrderTranHeader>();
		Query qe = em.createNamedQuery("INVOICE_DETAILS_BETWEEN_AMOUNTS", CustomerSiteInvoice.class)
				.setParameter("from", new BigDecimal(criteria.getInvoiceTotalFrom()))
				.setParameter("to", new BigDecimal(criteria.getInvoiceTotalTo()));
		List<CustomerSiteInvoice> sites = qe.getResultList();
		String customerId = "";
		String orderId = "";
		if (sites.size() > 0) {
			for (CustomerSiteInvoice site : sites) {
				orderId = site.getOrderNum();
				if (sites.size() > 0) {

					customerId = site.getCustomerSite().getCustomerSitePK().getCustId();

					orderId = site.getOrderNum();

				} else {
					continue;
				}

				/// as the search with the order id or invoice id so the list will be only one

				List<CustomerHeader> header = null;
				if (!customerId.equalsIgnoreCase("") && customerId != null) {// look  up  using  the  customer  id  and  add  the  customer  to  the  transaction
					Query customer = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
							.setParameter("customerId", customerId);

					header = customer.getResultList();
				}

				if (header.size() > 0) {
					CustomerIfc customer = new Customer();
					Query payments = em.createNamedQuery("CUSTOMER_PAYMENT_TERMS", CustomerPaymentTerm.class)
							.setParameter("custId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

					List<CustomerPaymentTerm> payment = payments.getResultList();
					if (payment.size() > 0) {
						customer.setPaymentTerms(payment.get(0));
					}

					customer.setSiteInvoices(sites);
					// set the customer to the order trasnaction
					customer.setCustomerHeader(header.get(0));

					/// using the order id found from the customer site table find the order transaction
					Query tranQuery = em.createNamedQuery("INVOICE_DETAILS_ORDERID", OrderTranHeader.class)
							.setParameter("orderId", orderId);/// removed for
					/// testing the issue

					List<OrderTranHeader> ordertrans = tranQuery.getResultList();
					if (ordertrans.size() > 0) {
						ordertrans.get(0).setCustomer(customer);/// set customer
																/// to
																/// the
																/// order
																/// transaction
						orderTransaction.add(ordertrans.get(0));

					} else {
						////// exception if one transaction header is not found
						continue;
					}
				} else {

					////// exception if one transaction header is not found
					continue;
				}

			}
			if (orderTransaction.size() > 0) {
				orderHeader = new OrderTranHeader[orderTransaction.size()];
				orderTransaction.copyInto(orderHeader);
			} else {
				/// if not data was found from any of the two queries then set the values to null
				orderHeader = null;
			}
		}

		return orderHeader;
	}

	/*
	 * Method to Get All reason Codes
	 */
	public Map<String, Map<String, String>> getAllReasonCodes() {
		Map<String, Map<String, String>> reasonCodes = new HashMap<String, Map<String, String>>();
		EntityManager em = getEntityManager();
		Query qe = em.createQuery("select dkartReasonCodes from DkartReasonCodes dkartReasonCodes");
		List<DkartReasonCodes> allReasons = qe.getResultList();
		for (DkartReasonCodes alRsn : allReasons) {
			if (reasonCodes.containsKey(alRsn.getRsnGrpNm())) {
				reasonCodes.get(alRsn.getRsnGrpNm()).put(new Long(alRsn.getRsnCode()).toString(), alRsn.getRsnDesc());
			} else {
				Map<String, String> codeName = new HashMap<String, String>();
				codeName.put(new Long(alRsn.getRsnCode()).toString(), alRsn.getRsnDesc());
				reasonCodes.put(alRsn.getRsnGrpNm(), codeName);
			}
		}
		return reasonCodes;
	}

	////// this method is called when a transaction sequence is required to assign to a transaction.
	public long getTransactionSequence() {
		EntityManager em = getEntityManager();
		long nextSequence = 0;
		try {
			em = getEntityManager();
			Query qe = em.createNativeQuery("SELECT  TRAN_SEQ.nextval FROM DUAL");/// gets the next value for the sequence number

			BigDecimal sequenceResult = (BigDecimal) qe.getSingleResult();
			nextSequence = sequenceResult.longValue();
		} catch (Exception e) {
			LOGGER.error("Exception while creating order sequence number");
			e.printStackTrace();
		} finally {
			em.close();
			LOGGER.info("Entity manager connection closed");
		}
		return nextSequence;
	}

	//method to check and remove suspended qoutation transaction
	public void removeSuspendedTransaction(OrderTranHeader transaction, EntityManager em) {
		LOGGER.info("Executing removeSuspendedTransaction method");
//		EntityManager em = null;
		try {
//			em = getEntityManager();
//			if (!em.getTransaction().isActive()) {
//				em.getTransaction().begin();
//			}

			Query query = em.createNamedQuery("RMV_SUS_TRAN", OrderTranHeader.class);
			query.setParameter("tran", transaction.getId());
			int dltdRows = query.executeUpdate();
			if (dltdRows > 0) {
				LOGGER.info("Suspended Transaction Found and Removed");
			}
			//em.getTransaction().commit();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		finally {
			//if(em!=null && em.isOpen()) em.close();
		}
	}

	public boolean saveCompletedTransactions(OrderTranHeader transaction) throws OrderTransactionException {
		boolean transactionPersistedSuccessfully = false;// initially false
		transaction.setIdTrlogBtch("-1");
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			//Check and remove any suspended transaction
			removeSuspendedTransaction(transaction, em);

			LOGGER.info("Persisting of order transaction in method saveCompletedTransactions");

			if (transaction.getOrdTy() == null
					|| (transaction.getOrdTy() != null && transaction.getOrdTy().equalsIgnoreCase(""))) {
				transaction.setOrdTy("23");
			}
			
			OrderTranSumPK sumPk = new OrderTranSumPK();
			sumPk.setDcDyOrd(transaction.getId().getDcDyOrd());
			sumPk.setOrdWs(transaction.getId().getOrdWs());
			sumPk.setRtStrId(transaction.getId().getRtStrId());
			sumPk.setTrnSeq(transaction.getId().getTrnSeq());
			transaction.getOrdTranSum().setId(sumPk);

			OrderTranSum sum = transaction.getOrdTranSum();

			//transaction.setOrdTranSums(null);
			//transaction.setOrdTranLineItems(null);
			
			transaction.setTsOrdEnd(new Timestamp(new Date().getTime()));
			transaction.setTsCrtRcrd(new Timestamp(new Date().getTime()));
			
			//Calendar cal = Calendar.getInstance();
			//cal.setTime(new Date());
			//transaction.setTsCrtRcrd(cal);
			transaction.setIdTlogBtch(new BigDecimal("-1"));
			
			// em.getTransaction().commit();
			// em=getEntityManager();

			sum.setOrdTranHeader(transaction);
			if (transaction.getCustomer() != null) {
				sum.setOrdIdCt(transaction.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId());
			}
			//em.persist(sum);

			//// em.getTransaction().commit();
			List<OrderTranLineItem> lineItems = transaction.getOrdTranLineItems();
			if (lineItems != null) { // To handle null pointer exception when saving cancelled orders that do not have any line items
				for (OrderTranLineItem lineItem : lineItems) {
					lineItem.getId().setDcDyOrd(transaction.getId().getDcDyOrd());
					lineItem.getId().setOrdWs(transaction.getId().getOrdWs());
					lineItem.getId().setRtStrId(transaction.getId().getRtStrId());
					lineItem.getId().setTrnSeq(transaction.getId().getTrnSeq());
					if (lineItem.getPluItem() != null && lineItem.getPluItem().getItem().getItm() != null) {
						lineItem.setUomSls(lineItem.getPluItem().getItem().getItm().getSlsUomCd());
					}
					List<OrderTranDiscountItem> discountItems = lineItem.getOrdTranDscItms();
					lineItem.setOrdTranDscItms(null);

					if (discountItems != null && discountItems.size() > 0) {
						lineItem.setFlItmDsc("0");
					} else {
						lineItem.setFlItmDsc("1");
					}

					/// em=getEntityManager();
					//em.persist(lineItem);
					/// em.getTransaction().commit();
					int disLineSeq = 1;
					if (discountItems != null) {
						for (OrderTranDiscountItem discountItem : discountItems) {
							OrderTranDiscountItemPK orderTranDiscountItemPK = new OrderTranDiscountItemPK();
							orderTranDiscountItemPK.setDiscSeqNum(disLineSeq);
							orderTranDiscountItemPK.setDcDyOrd(transaction.getId().getDcDyOrd());
							orderTranDiscountItemPK.setOrdWs(transaction.getId().getOrdWs());
							orderTranDiscountItemPK.setRtStrId(transaction.getId().getRtStrId());
							orderTranDiscountItemPK.setTrnSeq(transaction.getId().getTrnSeq());
							orderTranDiscountItemPK.setOrdLnItmSeq(lineItem.getId().getOrdLnItmSeq());
							discountItem.setId(orderTranDiscountItemPK);
							discountItem.setOrdTranLineItem(lineItem);
							//// discountItem.setOrdTranLineItem(lineItem);
							/// em=getEntityManager();
							//em.persist(discountItem);
							/// em.getTransaction().commit();
							disLineSeq++;
						}
						lineItem.setOrdTranDscItms(discountItems);
					}
				}
			}

			/*
			 * insert tender line item as house account so rtlog gets generated
			 * with the tender type
			 */
			if (transaction.getOrdTy().equalsIgnoreCase("23")) {
				TranLineItemTender tenderLineItem = new TranLineItemTender();
				TranLineItemTenderPK tenderLineItempk = new TranLineItemTenderPK();

				tenderLineItempk.setRtStrId(transaction.getId().getRtStrId());
				tenderLineItempk.setOrdWs(transaction.getId().getOrdWs());
				tenderLineItempk.setTrnSeq(transaction.getId().getTrnSeq());
				tenderLineItempk.setTrnLnItmSeq(Long.parseLong("1"));////set hard coded for now as no payment is accepted at the time of payment
				tenderLineItempk.setDcDyOrd(transaction.getId().getDcDyOrd());

				tenderLineItem.setId(tenderLineItempk);
				tenderLineItem.setMoItmLnTnd(sum.getDkartNetTot());
				tenderLineItem.setTyTnd("CARD");///payment type as house account
				tenderLineItem.setIdOrd("HOUSE");////using unused column to store sub tender type
				try {
					/*BigDecimal custId = new BigDecimal(
							transaction.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId());*/
					tenderLineItem.setIdAcntNmb(transaction.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId());
				} catch (Exception e) {
				}
				//em.merge(tenderLineItem);///persist the tender lines data
				em.persist(tenderLineItem);
			}

			/// commit all the above actions
			try{
				em.merge(transaction);
				em.getTransaction().commit();
				if(transaction.getOrdTy().equalsIgnoreCase("25") || transaction.getOrdTy().equalsIgnoreCase("3"))//Order Cancel or Order Void
				{
					transactionPersistedSuccessfully = true;
				}
			}catch(Exception e)
			{
				em.persist(transaction);
				em.getTransaction().commit();
			}

			if (transaction.getOrdTy().equalsIgnoreCase("23") || transaction.getOrdTy().equalsIgnoreCase("2")) //added by hanu to save the returns
			{
				em.getTransaction().begin();
				Query limits = em.createNamedQuery("CUSTOMER_LIMIT_INFO", CustomerLimit.class).setParameter("custId",
						transaction.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId());

				/// as a customer can have only one limit
				List<CustomerLimit> limit = limits.getResultList();
				BigDecimal avCredLimit = ConfigUtils.getInstance().createBigDecimal(0, "format.currency");
				if (limit.size() > 0) {
					avCredLimit = limit.get(0).getAvCrdtLimit();
				}
				// Condition to check final av credit limit >=0
				if (avCredLimit.subtract(sum.getDkartNetTot()).compareTo(BigDecimal.ZERO) < 0)
					avCredLimit = BigDecimal.ZERO;
				else
					avCredLimit = avCredLimit.subtract(sum.getDkartNetTot());
				//// update the customer pending due
				Query updateLimits = em
						.createNativeQuery("update RISPL_DK_CUST_LMT set AV_CRDT_LIMIT=? where CUST_ID=?")
						.setParameter(1, avCredLimit).setParameter(2,
								transaction.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId());

				updateLimits.executeUpdate();
				em.getTransaction().commit();///// commit the update

				transactionPersistedSuccessfully = true;
			}
			/// update the order shipment table for the duplicated items
			if (transaction.getOrdTy().equalsIgnoreCase("24") || transaction.getOrdTy().equalsIgnoreCase("26")) {
				em.getTransaction().begin();
				LOGGER.info("trying to update the shippment table for the duplicated orders.... ");
				Date dt = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				String value = format.format(dt);
				Query updateShipQtyTbl = em
						.createNativeQuery("update ORD_INV_SHP_QTY_DK set RTLOGBATCH=? where ID_ORD=?")
						.setParameter(1, value).setParameter(2, sum.getIdOrd());
				updateShipQtyTbl.executeUpdate();
				em.getTransaction().commit();///// commit the update
				LOGGER.info("updated the shippment table for the duplicated orders.... ");

			}

		} catch (Exception e) {
			e.printStackTrace();
			transactionPersistedSuccessfully = false;
			LOGGER.error("Error occured while persisting the order transactions " + e.getMessage());
			throw new OrderTransactionException(e.getMessage());

		} finally {
			if(em!=null && em.isOpen()) em.close();
		}
		return transactionPersistedSuccessfully;
	}

	public boolean updatePostedWMSTransactions(StringBuilder successList) throws OrderTransactionException {
		boolean transactionPersistedSuccessFully = true;// initially false
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		try {

			Date dt = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String value = format.format(dt);

			String[] orderIds = successList.toString().split(",");

			StringBuffer inClause = new StringBuffer("(");
			for (int a = 0; a < orderIds.length; a++) {
				if (a != (orderIds.length - 1)) {
					inClause.append("?,");
				} else {
					inClause.append("?)");
				}
			}
			String sqlQuery = "UPDATE ORD_TRAN_HEADER " + "SET ID_TLOG_BTCH=?, SC_ORD=1 " // Updating order status to reflect Open status once order is sent to WMS
					+ "WHERE SC_TRAN=2 AND (TRN_SEQ||DC_DY_ORD||ORD_WS||RT_STR_ID) in "	// Added SC_TRAN=2 to avoid marking 23 0 6 as 23 1 6
					+ "(SELECT (T1.TRN_SEQ||T1.DC_DY_ORD||T1.ORD_WS||T1.RT_STR_ID) " + "  FROM ORD_TRAN_SUM T2, "
					+ "  ORD_TRAN_HEADER T1 " + "  WHERE T1.RT_STR_ID = T2.RT_STR_ID "
					+ "  AND T1.ORD_WS      = T2.ORD_WS " + "  AND T1.TRN_SEQ     = T2.TRN_SEQ "
					+ "  AND T1.DC_DY_ORD   = T2.DC_DY_ORD " + "  AND T2.ID_ORD in " + inClause.toString() + "  )";

			Query qe = em.createNativeQuery(sqlQuery);
			int indx = 1;
			qe.setParameter(indx, value);
			for (String idOrd : orderIds) {
				indx++;
				qe.setParameter(indx, idOrd);
			}
			qe.executeUpdate();
			// no of rows updated can be used to log here

			em.getTransaction().commit();//// commit the updated transaction

		} catch (Exception e) {
			transactionPersistedSuccessFully = false;
			LOGGER.error("Error occured while updating the post transactions to warehouse" + e.getMessage());
			throw new OrderTransactionException(e.getMessage());

		} finally {
			// em.close();
		}
		return transactionPersistedSuccessFully;
	}
	
	//public OrderTranHeader[] lookUpTransactionToProcess() throws OrderTransactionException {
	// Saideep: Changing from primitive arrays to List<E>
	public List<OrderTranHeader> lookUpTransactionToProcess() throws OrderTransactionException {
		EntityManager em = getEntityManager();
	
		//Vector<OrderTranHeader> headers = new Vector<OrderTranHeader>();

		//OrderTranHeader[] oHeaders = null;
		List<OrderTranHeader> oHeaders = new ArrayList<>();
		
		try {
			LOGGER.info("looking up for the orders to process to warehouse...");

			Query qe = em.createNamedQuery("CHECK_FOR_TRANSACTIONS_TO_PROCESS", OrderTranHeader.class)
					.setParameter("constantValue", -1);

			//List<OrderTranHeader> orderHeaders = qe.getResultList();
			oHeaders = qe.getResultList();
			
			/*oHeaders = new OrderTranHeader[orderHeaders.size()];
			if (orderHeaders.size() > 0) {
				for (OrderTranHeader header : orderHeaders) {
					headers.add(header);
				}
				headers.copyInto(oHeaders);
			}*/
		}

		catch (Exception e) {
			LOGGER.error(
					"Exception occured while looking up for the transations to process to warehouse " + e.getMessage());
			throw new OrderTransactionException(e.getMessage());

		} finally {
			// em.close();
		}
		return oHeaders;
	}

	public boolean saveTenderLineItems(TranLineItemTender tenders) throws OrderTransactionException {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(tenders);
			/// commit all the above actions
			em.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error("Exception occured while persisting the tender lines " + e.getMessage());
			throw new OrderTransactionException(e.getMessage());

		} finally {
			// em.close();
		}

		return false;
	}

	////////////////// for customer details to post to warehouse
	public CustomerIfc lookUpCustomerInfo(String customerId, String selectedCustomerSiteId) throws CustomerException {
		EntityManager em = getEntityManager();
		CustomerIfc customer = new Customer();

		if (customerId != null && !customerId.equalsIgnoreCase("")) {
			try {
				Query qe = em.createNamedQuery("CUSTOMER_ADDITIONAL_INFO_FOR_WMS", CustomerSite.class)
						.setParameter("customerId", customerId)
						.setParameter("customerSiteId", new BigInteger(selectedCustomerSiteId));
				
				
				
				//chiranjibee comments to send the customer info to RWMS starts
				Query customerSearchQuery = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);
				
				CustomerHeader customerHeader = (CustomerHeader) customerSearchQuery.getSingleResult();
				////chiranjibee comments to send the customer info to RWMS Ends
				
				/////// for order search with customer info just add the extra query to execute

				List<CustomerHeader> headers = qe.getResultList();

				
				List<CustomerSite> customerSite = qe.getResultList();

				CustomerSite custm = null;
				if (customerSite.size() > 0) {
					// custm=header.get(0);
					// cust.setSize(header.size());
					/// use only required fields to send data to warehouse
					customerSite.get(0).getCustomerSiteAddressList().size();// as to fix non instantiated issue
					// customerSite.get(0).getCustomerSiteInvoiceList().size();// as to fix non instantiated issue
					// customerSite.get(0).getCustomerSiteLimitList().size();// as to fix non instantiated issue
					customerSite.get(0).getCustomerSitePhoneList().size();// as to fix non instantiated issue
					// customerSite.get(0).getCustomerSiteStoreList().size();// as to fix non instantiated issue
					customer.setCustomerSite(customerSite);
					

					//Chiranjibee Comments To Add The Customer Header Details To CustomerIfc Starts
					customer.setCustomerHeader(customerHeader);
					//Chiranjibee Comments To Add The Customer Header Details To CustomerIfc Ends
				}
			} catch (Exception e) {
				LOGGER.error("failed while looking for customer in order/invoice search " + e.getMessage());
				throw new CustomerException(e.getMessage());
			}
		} else {
			customer = null;
		}

		return customer;

	}
	//Mallikarjun

@SuppressWarnings({ "unchecked", "null" })	
	public List<CustomerIfc> getOrdIdListbyItemId(String Item_Id)
	{
		List<String> ordList=new ArrayList<String>();
		EntityManager em = getEntityManager();
		Query qe=null;
			/*SELECT inv.* FROM RISPL_DK_CUST_SITE_INV inv,ORD_TRAN_HEADER oth,ORD_TRAN_SUM ots,ORD_TRAN_LINE_ITEM otli,RISPL_DK_ITEM_MSTR im WHERE 
			ots.RT_STR_ID = otli.RT_STR_ID AND ots.DC_DY_ORD = otli.DC_DY_ORD AND ots.ORD_WS = otli.ORD_WS AND ots.TRN_SEQ = otli.TRN_SEQ AND 
			oth.RT_STR_ID = otli.RT_STR_ID AND oth.DC_DY_ORD = otli.DC_DY_ORD AND oth.ORD_WS = otli.ORD_WS AND oth.TRN_SEQ = otli.TRN_SEQ AND
			inv.ORDER_NUM=ots.ID_ORD AND AND im.ITM_ID=otli.ITEM_ID --otli.DE_ITM_SHRT_RCPT='Holstein Beer' AND 
			otli.ITEM_ID='102208401' AND im.ID_ITM_POS='1234563149596'AND inv.CUST_ID='1028';
			String itemquery="SELECT inv FROM CustomerSiteInvoice inv,OrderTranSum ots,OrderTranHeader oth,OrderTranLineItem otli,RisplDkItemMstr im,OrderDetailsWithInvoice odwi WHERE"
			+ " inv.orderNum=ots.idOrd AND im.id.itmId=otli.itemId AND ots.id.rtStrId=otli.id.rtStrId AND"
			+ " ots.id.dcDyOrd=otli.id.dcDyOrd AND ots.id.ordWs=otli.id.ordWs AND ots.id.trnSeq=otli.id.trnSeq AND"
			+ " oth.id.rtStrId=otli.id.rtStrId AND oth.id.dcDyOrd=otli.id.dcDyOrd AND oth.id.ordWs=otli.id.ordWs AND"
			+ " oth.id.trnSeq=otli.id.trnSeq AND odwi.id.orderId=inv.orderNum"
			+ " AND (otli.itemId=:Item_Id OR otli.deItmShrtRcpt=:Item_Id OR im.id.idItmPos=:Item_Id)";*/

		try 
		{
				try 
				{
					String getItemId=getParticularItemId(Item_Id); //calling services to get Particular item id
						if(getItemId!=null){
							Item_Id=getItemId;
						}
				} catch (Exception e) {
				e.printStackTrace();
				}

				String itemquery="SELECT oiqs FROM OrdInvQtyShp oiqs WHERE oiqs.itemId=:Item_Id";	
				qe = em.createQuery(itemquery, OrdInvQtyShp.class).setParameter("Item_Id", Item_Id);
			
		/*Query qe = em.createNamedQuery("SEARCH_ARINV_NUM_ORD_ID_BY_ITEM_ID",OrdInvQtyShp.class)
				.setParameter("Item_Id", Item_Id);*/
		List<OrdInvQtyShp> rsltList=qe.getResultList();
				for(int i=0;i<rsltList.size();i++){
					ordList.add(rsltList.get(i).getId().getIdOrd());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		ordList = new ArrayList<String>(new LinkedHashSet<String>(ordList));
		
		List<CustomerIfc> CustomerInvList = new ArrayList<CustomerIfc>();
		OrderTransactionSearchCriteriaIfc criteria= new OrderTransactionSearchCriteria();
		criteria.setSearchByinvoiceNumberOrOrderNumber(true);
		int j=1,k=0;
		while(j<=ordList.size())
			{
				criteria.setInvoiceNumberOrOrderNumber(ordList.get(j-1));
				OrderTranHeader[] orderHeader=null;
				OrderTranHeader[] orderHeaderNew =lookUpTransactionByinvoiceOrOrder(orderHeader, criteria) ;
				if(orderHeaderNew.length>0 && orderHeaderNew[0].getCustomer().getSiteInvoices()!=null && orderHeaderNew[0].getCustomer().getSiteInvoices().size()>0 )
				{
					System.out.println(orderHeaderNew[0].getCustomer());
					/*customer.setCustomerSite(new ArrayList<CustomerSite>());
					customer.getCustomerSite().add(new CustomerSite());
					customer.getCustomerSite().get(0).setCustomerPaymentTerms(new CustomerSitePaymentTerms());
					customer.getCustomerSite().get(0).getCustomerPaymentTerms().setPayIn(payment.get(0).getPayIn());*/
					orderHeaderNew[0].getCustomer().getSiteInvoices().get(0).getCustomerSite().setCustomerPaymentTerms(new CustomerSitePaymentTerms());
					orderHeaderNew[0].getCustomer().getSiteInvoices().get(0).getCustomerSite().getCustomerPaymentTerms().setPayIn(orderHeaderNew[0].getCustomer().getPaymentTerms().getPayIn());
					CustomerInvList.add(k,orderHeaderNew[0].getCustomer());
					k=k+1;
				}
				j=j+1;
			}

		return CustomerInvList;

	}

	//get particular item id @ jagadish
	public String getParticularItemId(String item_Id) {
		EntityManager em = getEntityManager();
		String getItemId=item_Id;
		String getItemIdQuery="SELECT rdim FROM RisplDkItemMstr rdim WHERE (rdim.itmDesc=:getItemId OR rdim.id.idItmPos=:getItemId)";
		Query resultQuery=em.createQuery(getItemIdQuery, RisplDkItemMstr.class).setParameter("getItemId", getItemId);
		List<RisplDkItemMstr> resultData=resultQuery.getResultList();
			for (RisplDkItemMstr RisplDkItemMstr : resultData) {
				getItemId=RisplDkItemMstr.getId().getItmId();
			}
	return getItemId;
	}
	
	//for delivery orders search details @mallikarjun
	public ArrayList<OrderTranHeader[]> getDelOrdIdListbyItemId(String Item_Id) {
		List<String> ordList = null;
		EntityManager em = getEntityManager();
		String sqlQry = "select e.ID_ORD from ord_tran_sum e where e.trn_seq "
				+ "in (select c.trn_seq from ord_tran_line_item c where c.ITEM_ID='" + Item_Id + "')";
		Query qe = em.createNativeQuery(sqlQry);
		ordList = qe.getResultList();

		ordList = new ArrayList<String>(new LinkedHashSet<String>(ordList));

		ArrayList<OrderTranHeader[]> delOrderList = new ArrayList<OrderTranHeader[]>();
		OrderTransactionSearchCriteriaIfc criteria = new OrderTransactionSearchCriteria();
		criteria.setSearchByinvoiceNumberOrOrderNumber(true);
		int j = 1;
		while (j <= ordList.size()) {
			criteria.setInvoiceNumberOrOrderNumber(ordList.get(j - 1));
			OrderTranHeader[] orderHeader = null;
			OrderTranHeader[] orderHeaderNew = lookUpTransactionByinvoiceOrOrder(orderHeader, criteria);
			if(orderHeaderNew[0]!=null && orderHeaderNew[0].getCustomer()!=null){
			if (orderHeaderNew[0].getCustomer().getSiteInvoices() != null
					&& orderHeaderNew[0].getCustomer().getSiteInvoices().size() > 0) {
				delOrderList.add(orderHeaderNew);
			}
		}
			j = j + 1;
		}

		return delOrderList;

	}

	public ArrayList<OrderTranHeader[]> lookUpOrdersbyCustIds(String[] custid) throws OrderTransactionException {

		EntityManager em = getEntityManager();
		List<String> custids = Arrays.asList(custid);
		String xy = "";
		int cnt = 0;
		while (cnt < custid.length) {
			String x;
			if (cnt < custid.length - 1) {
				x = "'" + custid[cnt] + "',";
			} else {
				x = "'" + custid[cnt] + "'";
			}
			xy = xy.concat(x);
			cnt = cnt + 1;
		}

		String sqlQry = "select e.order_num from rispl_dk_cust_site_inv e where e.cust_id IN (" + xy + ")";
		//String sqlQry="select e.order_num from rispl_dk_cust_site_inv e where e.cust_id in('1270','1271')";
		Query qe = em.createNativeQuery(sqlQry);
		List<String> ordList = qe.getResultList();

		ordList = new ArrayList<String>(new LinkedHashSet<String>(ordList));

		ArrayList<OrderTranHeader[]> delOrderList = new ArrayList<OrderTranHeader[]>();
		OrderTransactionSearchCriteriaIfc criteria = new OrderTransactionSearchCriteria();
		criteria.setSearchByinvoiceNumberOrOrderNumber(true);
		int j = 1;
		while (j <= ordList.size()) {
			criteria.setInvoiceNumberOrOrderNumber(ordList.get(j - 1));
			OrderTranHeader[] orderHeader = null;
			OrderTranHeader[] orderHeaderNew = lookUpTransactionByinvoiceOrOrder(orderHeader, criteria);
			if (orderHeaderNew[0].getCustomer().getSiteInvoices() != null
					&& orderHeaderNew[0].getCustomer().getSiteInvoices().size() > 0) {
				delOrderList.add(orderHeaderNew);
			}
			j = j + 1;
		}

		return delOrderList;

	}

	//mallikarjun-end
	// as we dont need all the information at a time keep the customer info in a different method all the sites info
	public CustomerIfc lookUpCustomerInfo(CustomerIfc customer) throws CustomerException {
		EntityManager em = getEntityManager();
		if (customer != null) {
			try {
				Query qe = em.createNamedQuery("CUSTOMER_ADDITIONAL_INFO", CustomerSite.class)
						.setParameter("customerId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

				@SuppressWarnings("unchecked")
				List<CustomerSite> customerSite = qe.getResultList();

				CustomerSite custm = null;
				if (customerSite.size() > 0) {
					// custm=header.get(0);
					// cust.setSize(header.size());
					for (int i = 0; i < customerSite.size(); i++) {
						customerSite.get(i).getCustomerSiteAddressList().size();// as to fix non instantiated issue
						customerSite.get(i).getCustomerSiteInvoiceList().size();// as to fix non instantiated issue
						customerSite.get(i).getCustomerSiteLimitList().size();// as to fix non instantiated issue
						customerSite.get(i).getCustomerSitePhoneList().size();// as to fix non instantiated issue
						customerSite.get(i).getCustomerSiteStoreList().size();// as to fix non instantiated issue

					}
					customer.setCustomerSite(customerSite);
				}
				Query limits = getEntityManager().createNamedQuery("CUSTOMER_LIMIT_INFO", CustomerLimit.class)
						.setParameter("custId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());
				/// as a customer can have only one limit
				List<CustomerLimit> limit = limits.getResultList();
				if (limit.size() > 0) {
					customer.setCustomerLimits(limit.get(0));
				}

				/// get the customer payment terms
				Query payments = em.createNamedQuery("CUSTOMER_PAYMENT_TERMS", CustomerPaymentTerm.class)
						.setParameter("custId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

				List<CustomerPaymentTerm> payment = payments.getResultList();
				if (payment.size() > 0) {
					customer.setPaymentTerms(payment.get(0));
				}

				// to show on the customer display screen
				/*
				 * Query orderDetails =
				 * em.createNativeQuery(lookUpCustomerOrders).setParameter("1",
				 * customer.getCustomerHeader().getCustomerHeaderPK().getCustId(
				 * ));
				 * 
				 * List<String[]> obj=orderDetails.getResultList();
				 * List<CustomerOrderDetailsIfc> details=new
				 * ArrayList<CustomerOrderDetailsIfc>(); for(Object[] ob:obj){
				 * CustomerOrderDetailsIfc orderObj=new CustomerOrderDetails();
				 * 
				 * 
				 * orderObj.setOrderId((String)ob[0]);
				 * orderObj.setOrderDate((String)ob[2]);
				 * orderObj.setOrderTotal(ob[1].toString());
				 * orderObj.setOrderStatus(ob[3].toString());
				 * details.add(orderObj); }
				 * customer.setCustomerOrderDetails(details);
				 */

			} catch (Exception e) {
				LOGGER.error("failed while looking for customer info in order/invoice search " + e.getMessage());
				throw new CustomerException(e.getMessage());
			}
		} else {
			customer = null;
		}

		return customer;

	}

	//Pavan Code: 
	public boolean saveTenderLineItems(TranLineItemTender[] tenders) throws OrderTransactionException {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			for (TranLineItemTender tender : tenders) {/////insert tender for each and every line received 
				tender.setDkTsCrtRcrd(new Timestamp(new Date().getTime()));
				em.persist(tender);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error("Exception occured while persisting the tender lines " + e.getMessage());
			throw new OrderTransactionException(e.getMessage());

		} finally {
			//em.close();
		}

		return false;
	}

	public CustomerLimit getCustomerlimits(String customerId) {

		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Query limits = em.createNamedQuery("CUSTOMER_LIMIT_INFO", CustomerLimit.class).setParameter("custId",
				customerId);

		List<CustomerLimit> limit = limits.getResultList();
		if (limit != null && limit.size() > 0) {
			return limit.get(0);
		}

		return null;
	}
	
	public List<OrderTranSum> getOrderTransactionSum(String orderId)
	{
		EntityManager em = emf.createEntityManager();
		
		List<OrderTranSum> result = em.createNamedQuery("SEARCH_ORDER_BY_ORDER_ID", OrderTranSum.class)
		.setParameter("orderId", orderId).getResultList();
		
		return result;
	}

}
