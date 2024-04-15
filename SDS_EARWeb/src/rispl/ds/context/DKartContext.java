package rispl.ds.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.retailsols.sds.creditmemo.LookUpCreditMemoIfc;
import com.retailsols.sds.invoice.cancel.CancelCustomerInvoiceIfc;
import com.retailsols.sds.receipt.LookUpReceiptDetailsIfc;
import com.rispl.roles.access.service.EmployeeRolesAccessRemote;
import com.rispl.sds.cancel.order.service.CancelOrderServiceIfc;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;
import com.rispl.sds.parameter.service.ParameterException;
import com.test.entities.OrderDetailsWithInvoice;

import rispl.dkart.claim.lookup.dao.ClaimDAOBeanIfc;
import rispl.dkart.collection.lookup.dao.CollectionDAOBeanIfc;
import rispl.dkart.invoice.lookup.dao.InvoiceDAOBeanInfc;
import rispl.dkart.order.lookup.dao.OrderDAOBeanRemote;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.LookUpItemIfc;
import rispl.dkart.services.ejb.claim.ClaimRemote;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.dkart.services.ejb.mail.automated.NewInvoiceEmailRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.ejb.utils.UtilsBeanRemote;
import rispl.dkart.services.promotions.RisplApplyDiscountRulesIfc;
import rispl.dkart.services.transaction.save.SavePostPaymentTransactionIfc;

public class DKartContext {

	static final Logger LOGGER = LogManager.getLogger(DKartContext.class);

	private static InitialContext initialContext;

	private static final String CONFIG_FILE = "/bean_config.properties";
	//private static String INITIAL = "INITIAL";
	//private static String URL = "URL";// "t3://192.168.2.129:9090";	// "t3://pavanjesta-pc:7001";
	private static final String LOOKUP_EMPLOYEE = "LOOKUP_EMPLOYEE";
	private static final String LOOKUP_CUSTOMER = "LOOKUP_CUSTOMER";
	private static final String LOOKUP_ITEM = "LOOKUP_ITEM";
	private static final String LOOKUP_ORDER = "LOOKUP_ORDER";
	private static final String ORDER_DAO = "ORDER_DAO";
	private static final String INVOICE_DAO = "INVOICE_DAO";
	private static final String CLAIM_DAO = "CLAIM_DAO";
	private static final String COLLECTION_DAO = "COLLECTION_DAO";
	private static final String DISCOUNT_RULES = "DISCOUNT_RULES";
	private static final String CLAIM = "CLAIM";
	private static final String LOOKUP_POSTPAYMENT = "LOOKUP_POSTPAYMENT";
	private static final String MAIL_BEAN = "MAIL_BEAN";
	private static final String UTILS_BEAN = "UTILS_BEAN";
	//Chiranjibee Comments For Adding The Paramter LookUp Starts
	private static String CONFIG_PARAMETER ="CONFIG_PARAMETER";
	//Chiranjibee Comments For Adding The Paramter LookUp Ends
	//Chiranjibee Comments For Adding The Paramter LookUp Starts
	private static String CANCEL_ORDER ="CANCEL_ORDER";
	//Chiranjibee Comments For Adding The Paramter LookUp Ends
	// ADDED BY LOKESH
	private static String CREDIT_MEMO_DETAILS = "CREDIT_MEMO_DETAILS";

	private static String NEWINVOICE_EMAIL_BEAN = "NEWINVOICE_EMAIL_BEAN";
	
	private static final String ROLES_SECURITY = "ROLES_SECURITY";
	
	private static final String INVOICE_CANCEL_UTIL = "INVOICE_CANCEL_UTIL";
	private static final String RECEIPT_DETAILS = "RECEIPT_DETAILS";
	//Added by Srinivas
	private Map<String,String> roleNameIDs = new HashMap<String,String>();

	private static String getConfig(String property) throws Exception {
		Properties p = new Properties();
		p.load(DKartContext.class.getResourceAsStream(CONFIG_FILE));
		if (p.containsKey(property))
			return p.getProperty(property);
		else
			throw new Exception("Property not found in configuration: " + property);
	}

	private static InitialContext getInitialContext() throws Exception {

		if (initialContext == null) {
			//Properties p = new Properties();
			//p.put("java.naming.factory.initial", getConfig(INITIAL));
			//p.put("java.naming.provider.url", getConfig(URL));
			//p.put(Context.SECURITY_PRINCIPAL,"weblogic");
			//p.put(Context.SECURITY_CREDENTIALS,"xstore@123");
			//initialContext = new InitialContext(p);
			initialContext = new InitialContext();
			LOGGER.debug("Initial context loaded", initialContext);
		}
		return initialContext;
	}

	public static LookUpEmployeeIfc getLookupEmployee() throws Exception {
		InitialContext context = getInitialContext();
		LookUpEmployeeIfc lookUpEmployee = (LookUpEmployeeIfc) context.lookup(getConfig(LOOKUP_EMPLOYEE));
		return lookUpEmployee;
	}

	public static OrderDAOBeanRemote getOrderDAOBean() throws Exception {
		InitialContext context = getInitialContext();
		OrderDAOBeanRemote orderDAO = (OrderDAOBeanRemote) context.lookup(getConfig(ORDER_DAO));
		return orderDAO;
	}

	public static ClaimDAOBeanIfc getClaimDAOBean() throws Exception {
		InitialContext context = getInitialContext();
		ClaimDAOBeanIfc claimDAO = (ClaimDAOBeanIfc) context.lookup(getConfig(CLAIM_DAO));
		return claimDAO;
	}

	public static InvoiceDAOBeanInfc getInvoiceDAOBean() throws Exception {
		InitialContext context = getInitialContext();
		InvoiceDAOBeanInfc invoiceDAO = (InvoiceDAOBeanInfc) context.lookup(getConfig(INVOICE_DAO));
		return invoiceDAO;
	}

	public static CollectionDAOBeanIfc getCollectionDAOBean() throws Exception {
		InitialContext context = getInitialContext();
		CollectionDAOBeanIfc collectionDAO = (CollectionDAOBeanIfc) context.lookup(getConfig(COLLECTION_DAO));
		return collectionDAO;
	}

	public static LookUpCustomerIfc getLookUpCustomer() throws Exception {
		InitialContext context = getInitialContext();
		LookUpCustomerIfc lookUpCustomer = (LookUpCustomerIfc) context.lookup(getConfig(LOOKUP_CUSTOMER));
		return lookUpCustomer;
	}

	public static LookUpItemIfc getLookupItem() throws Exception {
		InitialContext context = getInitialContext();
		LookUpItemIfc lookUpItem = (LookUpItemIfc) context.lookup(getConfig(LOOKUP_ITEM));
		return lookUpItem;
	}

	//ARjun
	public static LookUpItemIfc getLookupServiceItem() throws Exception {
		InitialContext context = getInitialContext();
		LookUpItemIfc lookUpItem = (LookUpItemIfc) context.lookup(getConfig(LOOKUP_ITEM));
		return lookUpItem;
	}

	public static OrderTransactionsIfc getLookupOrder() throws Exception {
		InitialContext context = getInitialContext();
		OrderTransactionsIfc ordTrns = (OrderTransactionsIfc) context.lookup(getConfig(LOOKUP_ORDER));
		return ordTrns;
	}

	public static RisplApplyDiscountRulesIfc getLookupTransOfQuote() throws Exception {
		InitialContext context = getInitialContext();
		RisplApplyDiscountRulesIfc discRules = (RisplApplyDiscountRulesIfc) context.lookup(getConfig(DISCOUNT_RULES));
		return discRules;
	}

	public static ClaimRemote getClaimBean() throws Exception {
		InitialContext context = getInitialContext();
		ClaimRemote claim = (ClaimRemote) context.lookup(getConfig(CLAIM));
		return claim;
	}

	public static SavePostPaymentTransactionIfc getPaymentLookup() throws Exception {
		InitialContext context = getInitialContext();
		SavePostPaymentTransactionIfc lookUpPostPayment = (SavePostPaymentTransactionIfc) context
				.lookup(getConfig(LOOKUP_POSTPAYMENT));
		return lookUpPostPayment;
	}

	public static MailBeanRemote getMailBean() throws Exception {
		InitialContext context = getInitialContext();
		MailBeanRemote mailBeanRemote = (MailBeanRemote) context.lookup(getConfig(MAIL_BEAN));
		return mailBeanRemote;
	}

	public static UtilsBeanRemote getUtilsBean() throws Exception {
		InitialContext context = getInitialContext();
		UtilsBeanRemote utilsBeanRemote = (UtilsBeanRemote) context.lookup(getConfig(UTILS_BEAN));
		return utilsBeanRemote;
	}
	
	//Chiranjibee Comments To Access The Parameter Bean
	public static ParameterConfigurationServiceIfc getParamterBean() throws ParameterException,Exception{
		InitialContext context = getInitialContext();
		ParameterConfigurationServiceIfc paramterLookUpService = (ParameterConfigurationServiceIfc) context.lookup(getConfig(CONFIG_PARAMETER));
		return paramterLookUpService;
	}
	
	//Chiranjibee Comments To Access The Parameter Bean
		public static CancelOrderServiceIfc getCancelOrderBean() throws Exception{
			InitialContext context = getInitialContext();
			CancelOrderServiceIfc cancelOrderService = (CancelOrderServiceIfc) context.lookup(getConfig(CANCEL_ORDER));
			return cancelOrderService;
		}

		//added by lokesh to get credit memo and invoice cancel details
		public static LookUpCreditMemoIfc getCreditMemoDetails() throws Exception {
			InitialContext context = getInitialContext();
			LookUpCreditMemoIfc creditMemoService = (LookUpCreditMemoIfc) context.lookup(getConfig(CREDIT_MEMO_DETAILS));
			return creditMemoService;
		}
		public static CancelCustomerInvoiceIfc getInvoiceCancelUtil() throws Exception {
			InitialContext context = getInitialContext();
			CancelCustomerInvoiceIfc invoiceCanelUtil = (CancelCustomerInvoiceIfc) context.lookup(getConfig(INVOICE_CANCEL_UTIL));
			return invoiceCanelUtil;
		}
		public static LookUpReceiptDetailsIfc getCustomerReceiptDetails() throws Exception {
			InitialContext context = getInitialContext();
			LookUpReceiptDetailsIfc receiptDetails = (LookUpReceiptDetailsIfc) context.lookup(getConfig(RECEIPT_DETAILS));
			return receiptDetails;
		}
		
		/*
		 * SAIDEEP: NOT THE PLACE TO WRITE HELPER METHODS 
		 *  
		//Added by Srinivas to Get Mapped Role and ID's in Parameter file
		public static Map<String, String> getRoleNameIDs() {
			Map<String,String> roleNameID = new HashMap<String,String>();
			try {
				roleNameID.put("DataEntry",DKartContext.getParamterBean().fetchXMLParameterValues().getDataEntryRoleID());
				roleNameID.put("SalesAgent",DKartContext.getParamterBean().fetchXMLParameterValues().getSalesAgentRoleID());
				roleNameID.put("DivisionHead",DKartContext.getParamterBean().fetchXMLParameterValues().getDivisionHeadRoleID());
			} catch (ParameterException e) {
				// TODO Auto-generated catch block
				LOGGER.error("Exception While Getting Parameter", e);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error("Exception While Getting Parameter", e);
			}
			return roleNameID;
		}
		
		//Added by mudassir to format result list in order search
		public static List<OrderDetailsWithInvoice> formatResultListOfOrderSearch(Map<Integer, List<OrderDetailsWithInvoice>> combined){

			List<OrderDetailsWithInvoice> tab1List = null;
			if (combined.size() > 0) {
			if(combined.get(1)!=null && combined.get(2)!=null && combined.get(3)!=null && !combined.get(1).isEmpty() && !combined.get(2).isEmpty() && !combined.get(3).isEmpty()){
			combined.get(2).retainAll(combined.get(3));
			combined.get(1).retainAll(combined.get(2));
			tab1List=combined.get(1);
			}
			else if(combined.get(1)!=null && combined.get(2)!=null && !combined.get(1).isEmpty() && !combined.get(2).isEmpty()){
				combined.get(1).retainAll(combined.get(2));
				tab1List=combined.get(1);
			}
			else if (combined.get(2)!=null && combined.get(3)!=null && !combined.get(2).isEmpty() && !combined.get(3).isEmpty()){
				combined.get(2).retainAll(combined.get(3));
				tab1List=combined.get(2);
			}
			else if (combined.get(1)!=null && combined.get(3)!=null && !combined.get(1).isEmpty() && !combined.get(3).isEmpty()){
				combined.get(1).retainAll(combined.get(3));
				tab1List=combined.get(1);
			}
			
			else{
				if(combined.get(1)!=null && !combined.get(1).isEmpty()){
					if(combined.get(2)!=null && combined.get(2).isEmpty() && combined.get(3)!=null && combined.get(3).isEmpty()){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if (combined.get(2)!=null && combined.get(2).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if(combined.get(3)!=null && combined.get(3).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else{
						tab1List=combined.get(1);
					}
					
				}
				if(combined.get(2)!=null && !combined.get(2).isEmpty()){
					if(combined.get(3)!=null && combined.get(3).isEmpty() && combined.get(1)!=null && combined.get(1).isEmpty()){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if (combined.get(3)!=null && combined.get(3).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if(combined.get(1)!=null && combined.get(1).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else{
						tab1List=combined.get(2);
					}
					
				}
				if(combined.get(3)!=null && !combined.get(3).isEmpty()){
					if(combined.get(2)!=null && combined.get(2).isEmpty() && combined.get(1)!=null && combined.get(1).isEmpty()){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if (combined.get(2)!=null && combined.get(2).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if(combined.get(1)!=null && combined.get(1).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else{
						tab1List=combined.get(3);
					}
					
				}
			}
			
			} else {
				return new ArrayList<OrderDetailsWithInvoice>();
			}
			return tab1List;
		
		}

		public void setRoleNameIDs(Map<String, String> roleNameIDs) {
			this.roleNameIDs = roleNameIDs;
		}*/

		public static NewInvoiceEmailRemote getNewInvoiceEmailBean() throws Exception {
			InitialContext context = getInitialContext();
			NewInvoiceEmailRemote newInvoiceEmailRemote = (NewInvoiceEmailRemote) context.lookup(getConfig(NEWINVOICE_EMAIL_BEAN));
			return newInvoiceEmailRemote;
			
		}
		
		
		public static EmployeeRolesAccessRemote getEmployeeRoles() throws Exception{
			InitialContext context = getInitialContext();
			EmployeeRolesAccessRemote employeeRoles = (EmployeeRolesAccessRemote) context.lookup(getConfig(ROLES_SECURITY));
			return employeeRoles;
		}
		
	/*
	 * public static RisplApplyDiscountRulesIfc getLookupTransOfQuote() throws
	 * NamingException { Properties p = new Properties();
	 * p.put("java.naming.factory.initial",
	 * "weblogic.jndi.WLInitialContextFactory");
	 * p.put("java.naming.provider.url", "t3://192.168.2.129:9090");
	 * InitialContext in = null; RisplApplyDiscountRulesIfc remoteBean = null;
	 * try { in = new InitialContext(p);
	 * 
	 * OrderTranHeader transaction = new OrderTranHeader(); OrderTranHeaderPK pk
	 * = new OrderTranHeaderPK(); pk.setRtStrId("40401"); transaction.setId(pk);
	 * transaction.setOrdTranLineItems(new ArrayList<OrderTranLineItem>());
	 * 
	 * remoteBean = (RisplApplyDiscountRulesIfc) in .lookup(
	 * "applyValidPromotions#rispl.dkart.services.promotions.RisplApplyDiscountRulesIfc"
	 * );
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return remoteBean;
	 * 
	 * }
	 */

}
