
package rispl.ds.invoice;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.retailsols.sds.invoice.cancel.CancelCustomerInvoiceIfc;
import com.test.entities.OrderDetailsWithInvoice;

import rispl.db.model.claim.ClaimTranDscItm;
import rispl.db.model.claim.ClaimTranDscItmPK;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.ClaimTranSum;
import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.order.lookup.dao.DelvOrderSearchVO;
import rispl.dkart.order.lookup.dao.OrderDAOBeanRemote;
import rispl.dkart.services.detail.claim.ClaimDetailTable;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.LookUpItemIfc;
import rispl.dkart.services.ejb.claim.ClaimRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerSite;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;
import rispl.dkart.services.entities.transaction.DkartReasonCodes;
import rispl.dkart.services.entities.transaction.ExciseTaxItem;
import rispl.dkart.services.entities.transaction.OrdInvShpQtySrlno;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeaderPK;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkart.services.entities.transaction.lpo.OrderTransactionLpo;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;
import utility.ConfigUtils;


@SuppressWarnings("deprecation")
public class Invoice extends DSAction
{

	static Logger LOGGER = LogManager.getLogger(Invoice.class);
	private static final long serialVersionUID = 1L;

	/*	private String CUSTOMER_NAME;
	private String CUSTOMER_ID;
	private String BILLING;
	private String INVOICE_NO;
	private String INVOICE_DT;
	private String SHIPPING;
	private String SHIPPING_METHOD;
	private String ORDER_NO;
	private String ORDER_DT;
	private String LPO_NO;
	private String LPO_DT;
	private String SALES_PERSON;
	private String PAYMENT_TERMS;
	private String SUBTOTAL;
	private String DUE_DATE;
	private String TAX;
	private String TOTAL;*/
	private String shipping = "", billing = "";
	private boolean download;
	private String age="";
	private Date orderDateInfo;
	public Date getOrderDateInfo() {
		return orderDateInfo;
	}

	public void setOrderDateInfo(Date orderDateInfo) {
		this.orderDateInfo = orderDateInfo;
	}
	private List<String[]> LINEITEMS = new ArrayList<>();

	private SessionMap<String, Object> sessionmap;
	private EmployeeIfc employee;
	private String orderID, invoiceID, customerInfo,itemInfo;
	
	private Map<String,String> getAllDiscountReasnCode=new HashMap<String,String>();
	private Map<String,String> getExciseTaxItem=new HashMap<String,String>();
	private List<String> ExcisTaxItemList= new ArrayList<>();
	
	
	private OrderTranHeader[] orders;
	private List<CustomerSiteInvoice> invoices;
	private String[] customerName;
	private InputStream inputStream;
	private File invoicePdf = null;
	private String tempFolder = System.getProperty("java.io.tmpdir");
	private String datepicker2;
	private String datepicker1;
	private String datepicker3;
	private String datepicker4;
	private String OrderTotalFrom;
	private String OrderTotalTo;
	private String InvoiceTotalFrom;
	private String InvoiceTotalTo;
	private String invoiceStatus;
	private String activeTab;
	private boolean overDueInvoices;
	private boolean EnblRcrdPaymnt;
	private Date currentDate;
	private String invDueDate;
	public String getInvDueDate() {
		return invDueDate;
	}

	public void setInvDueDate(String invDueDate) {
		this.invDueDate = invDueDate;
	}
	private String fileName;
	
	private CustomerIfc[] customers;
	/**
	 * range of the search
	 */
	private String searchRange;
	/**
	 * range of the openInvoiceSearch
	 */
	private String openInvoiceSearchRange;
	// for displaying 0 invoices found
	public ClaimDetailTable[] rejectClaim_List;
	ClaimDetailTable[] newRejectClaimList = null;
	
	public void setRejecClaimList(ClaimDetailTable[] newAcceptClaimList) {
		this.rejectClaim_List = newAcceptClaimList;
	}

	public ClaimDetailTable[] getRejecClaimList() {
		return rejectClaim_List;
	}
	private List<CustomerSiteInvoice> custinvList=null;

	private String invType;
	List<CustomerSiteInvoice> newCustinvList=new ArrayList<CustomerSiteInvoice>();
	List<CustomerSiteInvoice> newCustSiteinvList=new ArrayList<CustomerSiteInvoice>();
	List<String> newCustNames=new ArrayList<String>();
	
	
	List<BigInteger> paymntTrmsList = new ArrayList<BigInteger>();

	private String InvItemId=null;

	private CustomerSiteInvoice custSiteinv;

	private OrderTranHeader orderDetail;

	private SessionMap<String,Object> sessionMap;

	private CustomerSiteInvoice invDetail;

	private String[] salesAgents;

	private BigDecimal AmtToPay;

	private byte[] order_Lpocntnt;

	private ImageIcon imgIcn;

	private static String lpoImg; 
	private String contentType;
	private String filename;
	//private InputStream inputStream;
	private String contentDisposition;
	private String salesAgntNme;	

	private static OrderTranHeader[] ordersLpo;
	/* invoice cancel attributes start */
	private String invoiceId;
	private String orderId;
	private Date orderDate;
	private String custId;
	private String custName;
	private String invAmt;
	private String invStatus;
	private String mimeType;
	/* invoice cancel attributes end */
	
	/**
	 *serialNoList list of serial numbers
	 */
	private List<OrdInvShpQtySrlno> serialNoList;
	
	/**
	 * serialNo serialNo of Item
	 */
	private OrdInvShpQtySrlno serialNo;
	// Laxmikanth
	private boolean enableRecordPaymentButton;
	
	// mudassir
	private DelvOrderSearchVO delvORderSearchVO;
	private BigDecimal deliveredQuantity;
	private boolean showDeliveredQuantity=true;
	/**
	 * serialNo serialNo of Item
	 */
	private Map<String,StringBuffer> slnoMap = null;
	
	public EmployeeIfc getEmployee() {
	return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
	this.employee = employee;
	}
	
	public Map<String, String> getGetAllDiscountReasnCode() {
		return getAllDiscountReasnCode;
	}

	public void setGetAllDiscountReasnCode(Map<String, String> getAllDiscountReasnCode) {
		this.getAllDiscountReasnCode = getAllDiscountReasnCode;
	}

	private List<Integer> getEmpMrchAssc(EmployeeIfc employee2) {
		
		List<EmpMerchAssociationIfc> empMrchAss = employee.getMerchAssoc();
		List<Integer> divIds = new ArrayList<Integer>();
		if (empMrchAss != null) {
			for (EmpMerchAssociationIfc empMrchAsc : empMrchAss) {
				if (empMrchAsc.getMerchId().startsWith("1:")) {
					int merchId = Integer.parseInt(empMrchAsc.getMerchId().split("1:")[1]);
					divIds.add(merchId);
				}
			}
		}
		return divIds;
	}
	public String getInvStatus() {
		return invStatus;
	}

	public void setInvStatus(String invStatus) {
		this.invStatus = invStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getInvAmt() {
		return invAmt;
	}

	public void setInvAmt(String invAmt) {
		this.invAmt = invAmt;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Map<String, String> getGetExciseTaxItem() {
		return getExciseTaxItem;
	}

	public void setGetExciseTaxItem(Map<String, String> getExciseTaxItem) {
		this.getExciseTaxItem = getExciseTaxItem;
	}
	public List<String> getExcisTaxItemList() {
		return ExcisTaxItemList;
	}

	public void setExcisTaxItemList(List<String> excisTaxItemList) {
		ExcisTaxItemList = excisTaxItemList;
	}
	

	@Override
	public String execute() throws Exception
	{	
		OrderTransactionsIfc trans = DKartContext.getLookupOrder();
		employee = super.getEmployee();
		orders= (OrderTranHeader[])getFromSession(SESSION.INVOICE_ORDER_TRAN);
		invDetail= (CustomerSiteInvoice)getFromSession(SESSION.INVOICE_DETAIL);
		salesAgntNme=(String)getFromSession(SESSION.SALESAGENT_NAME);
		//added by srinivas to get the serial numbers\
		try{
			if(orders != null || orders.length>=0){
			//serialNoList = trans.getSerialNos(orders[0]);
			slnoMap = trans.getItemSerialNumbers(orders[0]); //added by srinivas to get the serial numbers of Items 
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		putInSession(SESSION.INVOICE_ORDER_TRAN, null);
		putInSession(SESSION.INVOICE_DETAIL, null);
		return SUCCESS;
	}

	public String invSearchByOrder()
	{
		employee=super.getEmployee();
		String emplID=employee.getEmployeeId();
		String empRoleAcc=employee.getRoleAccess();
		List<Integer> divIds = getEmpMrchAssc(employee);
		
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String	url = request.getHeader("referer");
			OrderTransactionsIfc trans = DKartContext.getLookupOrder();

			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			//mdasssir
			if(url!=null)
			{
				if(url.contains("delvInvoiceSearchByOrder")){
					search.setFromDeliverySearch(true);
				}
			
				else if (url.contains("delvOrderSearchbyCustomer")){
					search.setFromDeliverySearch(true);
				}
			}
			search.setMaximumResults(99);
			
			if(invoiceStatus.equalsIgnoreCase("OverDue")){
				overDueInvoices=true;
				search.setTypeOfInvoice(1);
			}else
			{
				overDueInvoices=false;
			search.setTypeOfInvoice(Integer.parseInt(invoiceStatus));
			}
			
			if(activeTab.equalsIgnoreCase("tab_1"))
			{
			search.setSearchByinvoiceNumberOrOrderNumber(false);
			search.setInvoiceNumberOrOrderNumber(orderID.trim());
			search.setOrderDateRangeFrom(datepicker1);
			search.setOrderDateRangeTo(datepicker2);
			search.setOrderTotalFrom(OrderTotalFrom);
			search.setOrderTotalTo(OrderTotalTo);
			search.setSearchByCustomerInfo(false);
			
			}else
				if(activeTab.equalsIgnoreCase("tab_2"))
			{
				search.setSearchByinvoiceNumberOrOrderNumber(true);
				search.setInvoiceNumberOrOrderNumber(invoiceID.trim());
				search.setInvoiceDateRangeFrom(datepicker3);
				search.setInvoiceDateRangeTo(datepicker4);
				search.setInvoiceTotalFrom(InvoiceTotalFrom);
				search.setInvoiceTotalTo(InvoiceTotalTo);
				search.setSearchByInvoiceTotal(true);
			}
				else{
					search.setSearchByCustomerInfo(true);

					CustomerSearchCriteria customerSearchCriteria = null;
						customerSearchCriteria = getLookupCriteria();
						search.setCustomerInfo(customerSearchCriteria);
						InvItemId=InvItemId.trim();
					search.setItemIdOrDescription(InvItemId);
				}
			//OrderTranHeader[] orders = trans.getTransactionsInvoices(search);
			
			orders = trans.getCustomerSiteAllInvoices(search,emplID,empRoleAcc,divIds);//,InvItemId,customerInfo);

			if(overDueInvoices && orders!=null)
			{
				orders=getOverDueInvoices(orders);
			}
			
			if(orders!=null){
			setSalesAgents(getSalesAgents(orders));//added for salesagent to show in deliveredOrderedscreen
			}else{
				//manish code
				orders=(orders==null)?new OrderTranHeader[0]:orders;
				addActionError("No invoices found");
			}
				setOrders(orders);
			
			if (orders != null && orders.length > 0)
			{
				customerName=new String[orders.length];
				for(int i=0;i<orders.length;i++)
				{
					if (orders[i].getCustomer().getSiteInvoices() != null
							&& orders[i].getCustomer().getSiteInvoices().size() > 0)
					{
						if(getInvoices()!=null)
						{
						    getInvoices().addAll(orders[i].getCustomer().getSiteInvoices());
						}else
						{
							setInvoices(orders[i].getCustomer().getSiteInvoices());
						}
						
						customerName[i]=orders[i].getCustomer().getCustomerHeader().getCtNm();
					}
				}
				
			}
			else{
				int count=0;
				newRejectClaimList = new ClaimDetailTable[count];		
				setRejecClaimList(newRejectClaimList);
				
				addActionError("No invoices were found with this information.");
			}
				
			}  catch (Exception e)
		{
			// TODO remove after testing and  send standard error message
			addActionError(e.toString());
		}
		//data 
		
		try{
			if(customerName!=null && customerName.length>0){
				setEnblRcrdPaymnt(getrecrdpymt(customerName,orders));
				}
			LOG.info("fetching the default Search Range of Invoices");
			setSearchRange(DKartContext.getParamterBean().fetchXMLParameterValues().getInvoiceSearchRange());
			setOpenInvoiceSearchRange(DKartContext.getParamterBean().fetchXMLParameterValues().getOpenInvoiceRange());
			LOG.info("Default Search Range Fetched is :  "+DKartContext.getParamterBean().fetchXMLParameterValues().getInvoiceSearchRange());
		}catch(Exception e){
			LOGGER.error("Error while fetching parameter");
		}
		putInSession(SESSION.INVOICE_ORDER_TRAN, orders);
		return SUCCESS;

	}

	//mallikarjun-Start	
	
	public String InvoiceDetailSearch()
	{
		employee=super.getEmployee();
		System.out.println("getInvoice().invoiceID : " + invoiceID);
		try
		{
			OrderTransactionsIfc trans = DKartContext.getLookupOrder();

			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(invoiceID);

			orders = trans.getTransactionsInvoices(search); 
			ordersLpo=orders;
			lpoImg=null;
			if(orders[0].getOrdTranLpo()!=null)
				lpoImg="printLPO";
/*			if(orders[0].getOrdTranLpo()!=null){
			inputStream = new ByteArrayInputStream(orders[0].getOrdTranLpo().getLpoSlipContent());
			contentType = orders[0].getOrdTranLpo().getLpoSlipType();
			filename = orders[0].getOrdTranLpo().getLpoSlipName();
			contentDisposition = "inline;filename=\"" + filename + "\"";
			lpoImg=Base64.getEncoder().encodeToString(orders[0].getOrdTranLpo().getLpoSlipContent());
			}*/
			/*getShippingAdrs(orders[0].getCustomer().getSiteInvoices().get(0).getCustomerSite().getCustomerSiteAddressList());*/
			//getShippingAdrs(orders[0].getCtDvrInf());
			shipping=orders[0].getCtDvrInf();
			getBillingAdrs(orders[0].getCustomer().getSiteInvoices().get(0).getCustomerSite().getCustomerSiteAddressList());
			List<CustomerSiteInvoice> tempInvs = orders[0].getCustomer().getSiteInvoices();
			ListIterator<CustomerSiteInvoice> invItr = tempInvs.listIterator();
			while(invItr.hasNext()){
				CustomerSiteInvoice tempInv1 = invItr.next();
				if(tempInv1.getArInvNum().equalsIgnoreCase(invoiceID)){
					invDetail=tempInv1;
					setAge(calculateInvoiceAge(invDetail.getArInvDate(),invDetail.getInvStatus()));
					break;
				}
			}
			// code to dispay Order date in invoice_Details page. @jagadish
			
			String orderNum=invDetail.getOrderNum();
			List<OrderDetailsWithInvoice> orderInfoList=trans.getOrderDateForOrderId(orderNum); 
			for(OrderDetailsWithInvoice orderInfo: orderInfoList)
			{
				 orderDateInfo=orderInfo.getOrderDate();
			} // Jagadish end 
			
			
			// process start by lalit for need to display excise tax in invoice_Details page. 
						List<ExciseTaxItem> getExciseTaxList = trans.getExciseTax();
						for(ExciseTaxItem getExtax: getExciseTaxList)
							{
								String item=getExtax.getItemId();
								String itemExT=getExtax.getExcise();
								getExciseTaxItem.put(item,itemExT);
							} // process has been end 
						
						ExcisTaxItemList = new ArrayList(getExciseTaxItem.keySet());
			// process start by jagadish for need to display discount description in invoice_Details page. 
			List<DkartReasonCodes> getDisResnCodesList = trans.getAllDisRsnCode();
			for(DkartReasonCodes getDisResnCodes: getDisResnCodesList)
				{
					String resnCodeDes=getDisResnCodes.getRsnDesc();
					long longrsncode=getDisResnCodes.getRsnCode();
					String resnCode=Long.toString(longrsncode);
					getAllDiscountReasnCode.put(resnCode,resnCodeDes);
				} // process has been end 
			
			//code to display delivered qty @sharanya strt
			if(orders[0].getScOrd().compareTo(new BigDecimal(5))==0 || orders[0].getScOrd().compareTo(new BigDecimal(7))==0 ){
				//deliveredQty(order_items);
				String OrderID=orders[0].getOrdTranSum().getIdOrd();
				Map<String,Long> quantityMap=new HashMap<>();
				OrderTransactionsIfc tran=DKartContext.getLookupOrder();
				List<OrderTranLineItem> shipOrders = tran.getDeliveredQty(OrderID); 
				for(OrderTranLineItem line:shipOrders){
					quantityMap.get(line.getLineQnt());	
				}
				
			}else{
				this.setShowDeliveredQuantity(false);
			}
			//sharanya end
			String EmID=orders[0].getEmId();
			String[] salesAgent =new String[1];
			salesAgent[0]=EmID;
			LookUpEmployeeIfc lookUpEmp = DKartContext.getLookupEmployee();
			String salesAgntNme=lookUpEmp.getSalesAgentsList(salesAgent).get(0);
		    String PAYMNT_TRMS=String.valueOf(orders[0].getCustomer().getPaymentTerms().getPayIn());
		    int payT=Integer.parseInt(PAYMNT_TRMS);
		    //  putInSession(SESSION.PAYMENT_TERMS, PAYMNT_TRMS);
			
			     
		    
		         String invDate=String.valueOf(invDetail.getArInvDate());
				 DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				    Date date = (Date)formatter.parse(invDate);
				    Calendar cal = Calendar.getInstance();
				    cal.setTime(date);
				    String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(formatedDate));
				//Incrementing the date by paytrm-1 day
				c.add(Calendar.DAY_OF_MONTH, payT-1);  
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
				invDueDate = sdf1.format(c.getTime());  
				setInvDueDate(invDueDate);
			
		    putInSession(SESSION.SALESAGENT_NAME, salesAgntNme);
			putInSession(SESSION.INVOICE_ORDER_TRAN, orders);
			putInSession(SESSION.INVOICE_DETAIL, invDetail);
			
			//for open invoices to be printed on invoice Print
			if(orders!=null && orders.length>0){
			invType="1";
			customerInfo=orders[0].getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId();
			InvoiceSearchbyCustomer();
			
			HashMap<String, String> exciseTaxItm = new HashMap<String, String>();
			String term="";
			try {
				//LookUpItemIfc lookupItem = DKartContext.getLookupItem();
				//lookupItem.lookForItemExciseTax(term);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setSalesAgents(getSalesAgentsItemLevel(orders[0]));
			// Laxmikanth: to parameterize the recordPayment button
			String recordPaymentButton = DKartContext.getParamterBean().fetchXMLParameterValues().getEnableRecordPaymentButton();
			if(recordPaymentButton != null && recordPaymentButton.equalsIgnoreCase("yes"))
				setEnableRecordPaymentButton(true);
			}


		} catch (Exception e)
		{
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}
	
	public String printInvoice() {
		String ret = InvoiceDetailSearch();
		List<String> ordlis = new ArrayList<String>();
		// Saideep: When an invoice is printed, mark the order status as delivered
		// Using Mudasir code logic to implement the same.
		try {
			String v1 = invDetail.getOrderNum();
			String v2 = String.valueOf(orders[0].getId().getTrnSeq());
			String v3 = orders[0].getId().getRtStrId();
			String v4 = orders[0].getId().getOrdWs();
			String v5 = orders[0].getId().getDcDyOrd();
			String v6 = "@";
			String res = v1 + v6 + v2 + v6 + v3 + v6 + v4 + v6 + v5;
			ordlis.add(res);
			OrderTransactionsIfc order = DKartContext.getLookupOrder();
			order.markOrderAsDelivered(ordlis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	

	private String calculateInvoiceAge(Date arInvDate, char status)
	{
		String age="";
		if(arInvDate!=null&&status=='1') //Age should be calculated only for open invoice
		{
			Date todayDate=new Date();
			int intAge = (int)( (todayDate.getTime() - arInvDate.getTime()) 
	                 / (1000 * 60 * 60 * 24) );
			age=String.valueOf(intAge)+" days";
		}
		return age;
	}

	public String delOrderSearchbyCustomer() throws Exception{
		try{
			CustomerSearchCriteria customerSearchCriteria = null;
			//mudassir
			List<OrderTranHeaderPK> pkList=null;
			if(customerInfo!=null){
				customerSearchCriteria = getLookupCriteria();
			}
			/*search if item id/desc!=null*/
			if(InvItemId!=null && InvItemId.length()>0){
				System.out.println(getInvItemId());
				OrderTransactionsIfc trans = DKartContext.getLookupOrder();
				//mudassir
				pkList=trans.getTranHeadPkForDelivered();
				ArrayList<OrderTranHeader[]> invordList = trans.getDelOrderIdByItemId(InvItemId);
				OrderTranHeader[] orders=new OrderTranHeader[invordList.size()];
				for(int i=0;i<invordList.size();i++){
				OrderTranHeader[] temp = invordList.get(i);
				if(customerInfo!=null){
					if(customerSearchCriteria.getCustomerId()!=null){
						if(temp[0].getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId().equalsIgnoreCase(customerInfo)){
							orders[i]=temp[0];
						}
					}else
						if(customerSearchCriteria.getFirstName()!=null){
							if(temp[0].getCustomer().getCustomerHeader().getCtNm().toLowerCase().contains(customerInfo)){
								orders[i]=temp[0];
							}
						}
					
				}else{				
				orders[i]=temp[0];
				}
				}
				int count=0,i=0;
				while(i<orders.length){
					if(orders[i]!=null){
						count=count+1;
					}
					i++;
				}
				OrderTranHeader[] orders1=new OrderTranHeader[count];
				int notNull=0;
				for(int k=0;k<orders.length;k++){
					if(orders[k]!=null){
						orders1[notNull]=orders[k];
						notNull++;
					}
					
				}		
			//	doProcessHeader(pkList,orders1);
				orders1=doProcessHeaderforItem(pkList,orders1);
				if(orders1!=null && orders1.length>0){
					
					setSalesAgents(getSalesAgents(orders1));//added for salesagent to show in deliveredOrderedscreen
					}else{
						addActionError("No orders found");
					}
				setOrders(orders1);

			}
			else{
				
				LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();
				customers = lookUpCustomer.lookUpCust(customerSearchCriteria);
				Set<OrderTranHeader> temporders=new HashSet<OrderTranHeader>();
				if(customers!=null && customers.length>0){
					 int custlngth=0;
					 String [] searchCustIds = new String[customers.length];
					while(custlngth<=customers.length-1){
						searchCustIds[custlngth] = customers[custlngth].getCustomerHeader().getCustomerHeaderPK().getCustId();
						
						custlngth=custlngth+1;
					}
					
				OrderTransactionsIfc trans = DKartContext.getLookupOrder();		
				//mudassir
				pkList=trans.getTranHeadPkForDelivered();
				ArrayList<OrderTranHeader[]> neworders = trans.getDelOrderbyCustid(searchCustIds,customerSearchCriteria);
				ListIterator<OrderTranHeader[]> newordLst = neworders.listIterator();
				while(newordLst.hasNext()){
					OrderTranHeader[] y = newordLst.next();
					if(y!=null){
					for(int a=0;a<y.length;a++){
					temporders.add(y[a]);
						 }
					}
					}
				ArrayList<OrderTranHeader> tempTranOders= new ArrayList<OrderTranHeader>();
				tempTranOders.addAll(temporders);
				//mudassir
				tempTranOders=	doProcessHeader(pkList,tempTranOders);
				//end
				orders= new OrderTranHeader[tempTranOders.size()];
				orders=tempTranOders.toArray(orders);
				
				orders[0].getOrdTranSum().getOrdDlvrDate();
			
				if(orders!=null){
					setSalesAgents(getSalesAgents(orders));//added for salesagent to show in deliveredOrderedscreen
					}else{
						addActionError("No Orders found");
					}
				}
				setOrders(orders);
				}
				
			
		}catch(Exception e){
			System.out.println(e);
			//manish code
			orders= new OrderTranHeader[0];
			addActionError("No Orders found");
		}
		return SUCCESS;
	}
	
	//mudassir
	private ArrayList<OrderTranHeader> doProcessHeader(List<OrderTranHeaderPK> tranpk,	ArrayList<OrderTranHeader> orders1){
		OrderTranHeader[] finalorders=null;
		ArrayList<OrderTranHeader> orderlist=new ArrayList();
		try{
			if(tranpk!=null && orders1!=null){
		for(OrderTranHeader head:orders1){
			 if(!tranpk.contains(head.getId())){
				 orderlist.add(head);
			 }
		}
			}
	}
	catch(Exception e){
		
	}
		return orderlist;
	}
	//mudassir
		private OrderTranHeader[] doProcessHeaderforItem(List<OrderTranHeaderPK> tranpk,	OrderTranHeader[] orders1){
			OrderTranHeader[] finalorders=null;
			ArrayList<OrderTranHeader> orderlist=new ArrayList();
			try{
				if(tranpk!=null && orders1!=null){
			for (int i=0;i<orders1.length;i++){
				 if(!tranpk.contains(orders1[i].getId())){
					 orderlist.add(orders1[i]);
				 }
			}
				}
				finalorders = orderlist.toArray(new OrderTranHeader[orderlist.size()]);
		}
		catch(Exception e){
			e.printStackTrace();
		}
			return finalorders;
		}
	
	private String[] getSalesAgents(OrderTranHeader[] orders2) throws Exception {
		String[] emids=new String[orders2.length];
		for(int i=0;i<orders2.length;i++){
			emids[i]=orders2[i].getEmId();
		}
		LookUpEmployeeIfc lookUpEmp = DKartContext.getLookupEmployee();
		List<String> salesAgent=lookUpEmp.getSalesAgentsList(emids);
		String[] salesAgntsList=new String[salesAgent.size()];
		salesAgntsList=salesAgent.toArray(salesAgntsList);
		return salesAgntsList;
	}
	
	private String[] getSalesAgentsItemLevel(OrderTranHeader orders) throws Exception {
		String[] emids=new String[orders.getOrdTranLineItems().size()];
		for(int i=0;i<orders.getOrdTranLineItems().size();i++){
			if(orders.getOrdTranLineItems().get(i).getEmpId()!=null){
			emids[i]=orders.getOrdTranLineItems().get(i).getEmpId();
			}
			else{
				emids[i]=orders.getEmId();
			}
		}
		LookUpEmployeeIfc lookUpEmp = DKartContext.getLookupEmployee();
		List<String> salesAgent=lookUpEmp.getSalesAgentsList(emids);
		String[] salesAgntsList=new String[salesAgent.size()];
		salesAgntsList=salesAgent.toArray(salesAgntsList);
		return salesAgntsList;
	}
	

	@SuppressWarnings("null")
	public String InvoiceSearchbyCustomer() throws Exception{
		employee=super.getEmployee();
			        
		if((InvItemId==null || InvItemId.equalsIgnoreCase("")) && (customerInfo!=null && !customerInfo.equalsIgnoreCase(""))){
				
			searchByCustomerNameOrId();
			} else {
				searchByItemId(); // combination of both customer id/name and item id/description
			} 
		
		if(newCustNames.size()>0){
				customerName=new String[newCustNames.size()-1];
				setCustomerName(newCustNames.toArray(customerName));
			}else
				{
					int count=0;
					addActionError("No invoices were found with this information.");
					newRejectClaimList = new ClaimDetailTable[count];		
					setRejecClaimList(newRejectClaimList);
					
				}

					System.out.println("customerlookup.Service reply: " + customers);
					
					if(newCustinvList!=null && newCustinvList.size()>0)
					{
					setEnblRcrdPaymnt(getrecrdpymt(getCustomerName(),newCustinvList));
					}
					
					return SUCCESS;
		}
	
	




	private String searchByCustomerNameOrId(){

		/*	retreiving invoices based on entered customer name;itemid=null*/
		try{
			int j=0;
			System.out.println(getInvItemId());
			//System.out.println("InvoiceSearchbyCustomer() custInfo: " + customerInfo +",ItemID/desc = " + this.InvItemId);
			System.out.println("InvoiceSearchbyCustomer() custInfo: " + this.customerInfo + ",ItemID/desc = " + this.InvItemId);
			setInvoices(new ArrayList<CustomerSiteInvoice>());
			LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();
			CustomerSearchCriteria customerSearchCriteria = getLookupCriteria();
			customers = lookUpCustomer.lookUpCust(customerSearchCriteria);
			int custLength=customers.length;
	
			if(customers!=null && custLength>0){
				while(custLength>0){
							if(customers[custLength-1].getCustomerSite()!=null){
				
								List<CustomerSite> custSite = customers[custLength-1].getCustomerSite();
								ListIterator<CustomerSite> itr = custSite.listIterator();
								while(itr.hasNext()){
									custinvList=itr.next().getCustomerSiteInvoiceList();
									ListIterator<CustomerSiteInvoice> custinvListInd = custinvList.listIterator();
									while(custinvListInd.hasNext()){
										CustomerSiteInvoice custind = custinvListInd.next();
				
				
										if(getInvType()!=null){
											if(custind!=null && getInvType().equalsIgnoreCase(String.valueOf(custind.getInvStatus())))
											{
												//adding customer invoices on selection(either open or closed invoices)
												newCustinvList.add(j, custind);
												paymntTrmsList .add(j,customers[custLength-1].getPaymentTerms().getPayIn());
												//setInvoices(newCustinvList);
												newCustNames.add(j, customers[custLength-1].getCustomerHeader().getCtNm());
												j=j+1;	
											}
										}
										else
										{//adding customer invoices on selection(ALL)
											newCustinvList.add(j, custind);
											//setInvoices(newCustinvList);
												newCustNames.add(j, customers[custLength-1].getCustomerHeader().getCtNm());
												paymntTrmsList .add(j,customers[custLength-1].getPaymentTerms().getPayIn());					
												j=j+1;}
									}
				
								}

							}			
								custLength=custLength-1;
				}
			}
			else
			{
				addActionError("No invoices were found with this information.");
			}
			
			//if overdue is selected in search
			if(overDueInvoices){
				setInvoices(getOverDueInvoices(newCustinvList,paymntTrmsList,newCustNames));
			}else
			{
				setInvoices(newCustinvList);
			}
			
			return SUCCESS;
			
		}catch(Exception e){
			
			addActionError("No invoices were found with this information.");
			return ERROR;
		}
		
		
	}
	// search by invoice item info
	private String searchByItemId(){

		try{
		System.out.println(getInvItemId());
		OrderTransactionsIfc trans = DKartContext.getLookupOrder();
		//InvItemId="%".concat(InvItemId.concat("%"));
		InvItemId=InvItemId.toUpperCase().trim();
		List<CustomerIfc> invordList = trans.getOrderIdByItemId(InvItemId);
		int k=0;
		ListIterator<CustomerIfc> itr = invordList.listIterator();
		
			while(itr.hasNext())
			{
		
				CustomerIfc cust1 = itr.next();				
				List<CustomerSiteInvoice> inr =cust1.getSiteInvoices();
				ListIterator<CustomerSiteInvoice> imr = inr.listIterator();
		
				while(imr.hasNext())
				{
			
					if(customerInfo.length()>0){
						/*comparing the entered customer for retrieved invoices based on item id/desc*/
						if((cust1.getCustomerHeader().getCtNm().toLowerCase()).contains((CharSequence)customerInfo.toLowerCase())){
							custSiteinv = imr.next();
					
							if(invType!=null){
						/*when Itemid!=null and custmername!=null;Adding invoices based on checked radio button(if not open/close)*/
								if(invType.equalsIgnoreCase(String.valueOf(custSiteinv.getInvStatus())))
								{
											newCustinvList.add(k, custSiteinv);
											paymntTrmsList.add(k,cust1.getPaymentTerms().getPayIn());	
											newCustNames.add(k, cust1.getCustomerHeader().getCtNm());
								}else
									{
										continue;
										}
								}else
									{
										/*when Itemid!=null and custmername!=null;Adding invoices based on checked radio button(ALL)*/
										newCustinvList.add(k, custSiteinv);
										paymntTrmsList.add(k,cust1.getPaymentTerms().getPayIn());	
										newCustNames.add(k, cust1.getCustomerHeader().getCtNm());
									}
					
					
							}else
								{
									continue;
								}
						}else
							{
				
							if(invType!=null)
							{
									/*when Itemid!=null and custmername=null;Adding invoices based on checked radio button(if not open/close)*/
									custSiteinv = imr.next();
									if(invType.equalsIgnoreCase(String.valueOf(custSiteinv.getInvStatus()))){
										newCustinvList.add(k, custSiteinv);
										paymntTrmsList.add(k,cust1.getPaymentTerms().getPayIn());	
										newCustNames.add(k, cust1.getCustomerHeader().getCtNm());
									}else
										{
											continue;
										}
									}
									else
										{
											/*when Itemid!=null and custmername=null;Adding invoices based on checked radio button(ALL)*/
											newCustinvList.add(k, imr.next());
											paymntTrmsList.add(k,cust1.getPaymentTerms().getPayIn());	
											newCustNames.add(k, cust1.getCustomerHeader().getCtNm());
										}
								}
							}
						}
	
			if(overDueInvoices)
			{
					setInvoices(getOverDueInvoices(newCustinvList,paymntTrmsList,newCustNames));
			}else
				{
					setInvoices(newCustinvList);
				}
			
				return SUCCESS;
				
		}catch(Exception e)
		{
			addActionError("No invoices were found with this information.");
			return ERROR;
		}
	}

	
	private CustomerSearchCriteria getLookupCriteria()
	{
		CustomerSearchCriteria customerSearchCriteria = new CustomerSearchCriteria();
		try
		{
			customerInfo=customerInfo.toUpperCase().trim();
			Long.parseLong(customerInfo);
			customerSearchCriteria.setCustomerId(customerInfo);
		} catch (Exception e)
		{
			customerInfo=customerInfo.toUpperCase().trim();
			customerSearchCriteria.setFirstName(customerInfo);
		}

		return customerSearchCriteria;
	}
	//mallikarjun-End
	
	
	/*public String getInvoice()
	{
		System.out.println("getInvoice().invoiceID : " + invoiceID);
		try
		{
			OrderTransactionsIfc trans = DKartContext.getLookupOrder();

			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(invoiceID);

			OrderTranHeader[] orders = trans.getTransactionsInvoices(search);		
			
			if (orders == null)
			{
				throw new Exception("Internal Server Error!!");
			}
			
			
			//File newPDFFile=getPDF(orders[0]);
			//inputStream = new FileInputStream(newPDFFile);
			
			if (newPDFFile == null)
				throw new Exception("Internal Server Error!!");

			inputStream = new FileInputStream(newPDFFile); 
			mallikarjun
			
			
			// TODO remove after testing
			// System.out.println(orders);
		
			invoicePdf = createInvoicePdf(orders[0]);

			if (invoicePdf == null)
				throw new Exception("Internal Server Error!!");

			inputStream = new FileInputStream(invoicePdf);
			
			if (invoicePdf == null)
				throw new Exception("Internal Server Error!!");

			inputStream = new FileInputStream(invoicePdf);

		} catch (Exception e)
		{
			// TODO remove after testing and send standard error message
			addActionError(e.getMessage());
			inputStream = new StringBufferInputStream(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}*/
	
	public String downloadLPO()
	{
		try
		{
			OrderTransactionsIfc trans = DKartContext.getLookupOrder();

			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(orderID);
			OrderTranHeader[] orders = trans.getTransactionsInvoices(search);	
			String orderId = "";
			if (orders == null)
			{
				throw new Exception("Internal Server Error!!");
			}else{
				OrderTransactionLpo orderLpo = null;
				for(OrderTranHeader tranHead : orders){
					if(tranHead.getOrdTranLpo()!=null){
						orderLpo = tranHead.getOrdTranLpo();
						orderId = tranHead.getOrdTranSum().getIdOrd();
						break;
					}
				}
				
				if(orderLpo!=null && orderLpo.getLpoSlipType()!=null){
					StringBuffer fileName = new StringBuffer(orderId);
					fileName.append("_");
					fileName.append(orderLpo.getLpoSlipName());
					setFileName(fileName.toString());
					setMimeType(orderLpo.getLpoSlipType());
					inputStream = new ByteArrayInputStream(orderLpo.getLpoSlipContent());
				}else{
					throw new Exception("LPO Details Not Found!!");
				}
			}
		
			
		} catch (Exception e)
		{
			// TODO remove after testing and send standard error message
			addActionError(e.getMessage());
			inputStream = new StringBufferInputStream(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	//@mallikarjun
/*	private File getPDF(OrderTranHeader order) {
		
		SimpleDateFormat dt = new SimpleDateFormat(getText("format.date"));
		
		Map<String, String> pdfDetails= new HashMap<String, String>();
		pdfDetails.put("Customer Name:", order.getCustomer().getCustomerHeader().getCtNm());
		pdfDetails.put("Customer ID:", order.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId());
		pdfDetails.put("Shipping Details:", "");
		pdfDetails.put("Invoice No:", order.getCustomer().getSiteInvoices().get(0).getArInvNum());
		pdfDetails.put("Invoice Date:", dt.format(order.getCustomer().getSiteInvoices().get(0).getArInvDate()));
		pdfDetails.put("Shipping Method:", "");
		pdfDetails.put("Order No.:", order.getCustomer().getSiteInvoices().get(0).getOrderNum());
		pdfDetails.put("Billing Details:", "");
		pdfDetails.put("Order Date:", dt.format( order.getCustomer().getSiteInvoices().get(0).getOrderDate()));
		pdfDetails.put("LPO No.:", order.getOrdTranSums().get(0).getCustLpoNum());
		pdfDetails.put("LPO Date:", dt.format(order.getOrdTranSums().get(0).getCustLpoDate()));
		pdfDetails.put("Sales Person:", order.getEmId());
		
		float[] lineItemColmWidth={1f, 3f, 6f, 2f, 2f, 3f};
		
		List<String> headerNames=new ArrayList<String>();
		headerNames.add("S.NO");
		headerNames.add("Item ID");
		headerNames.add("Description");
		headerNames.add("Shipped Qty.");
		headerNames.add("Unit Price");
		headerNames.add("Amount");
		
		
		List<String[]> items=new ArrayList<String[]>();
		for (OrderTranLineItem lineItem : order.getOrdTranLineItems())
		{
			items.add(new String[] { lineItem.getItemId(),
					lineItem.getDeItmShrtRcpt(),
					lineItem.getLineQnt().toString(),
					lineItem.getItmPrnPrc().toString(), lineItem.getLineQnt()
							.multiply(lineItem.getItmPrnPrc()).toString() });
		}
		
		Map<String, String> Totals= new HashMap<String, String>();
		Totals.put("Payment Terms", order.getCustomer().getPaymentTerms().getPayIn().toString());
		Totals.put("Sub Total", order.getOrdTranSums().get(0).getDkartSlsTot().toString());
		Totals.put("Due Date", " ");
		Totals.put("Tax", order.getOrdTranSums().get(0).getDkartTaxTot().toString());
		Totals.put("Total",order.getOrdTranSums().get(0).getDkartNetTot().toString());
		

		PDFTemplate pdfFile=new PDFTemplate();
		File makePDF = pdfFile.PDFCreate("invoice", "ORDER", pdfDetails, headerNames, items, lineItemColmWidth, Totals);
		return makePDF;
	}*/

	/*private File createInvoicePdf(OrderTranHeader order)
	{
		fillFields(order);

		File file = null;
		int BORDER = 0;
		// if (invoiceID != null && invoiceID.length() > 0) {
		Document document = new Document(PageSize.A4_LANDSCAPE.rotate(), 20f,
				20f, 20f, 40f);
		try
		{
			file = new File(tempFolder + "/invoice.pdf");
			System.out.println(file.getAbsolutePath());
			if (file.exists())
				file.delete();

			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(file));
			writer.setPageEvent(new CustomPDFEvent());
			document.open();

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 0.6f, 1.1f, 0.8f, 1.5f });

			Font labelFont = FontFactory.getFont(FontFactory.HELVETICA, 10,
					Font.BOLD);
			Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 12,
					Font.NORMAL);

			// HEADER DETAILS
			// LOGO
			URL logoUrl = ServletActionContext.getServletContext()
					.getResource("/img/salam-xs.png");
			System.out.println("Real Path: " + logoUrl);
			Image leftLogo = Image.getInstance(logoUrl);
			
			leftLogo.setAlt("Salam DS");
			PdfPCell cell1 = new PdfPCell(leftLogo);
			cell1.setPaddingBottom(2);
			// cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			// cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell1.setBorder(BORDER);

			PdfPCell empty = new PdfPCell(new Phrase("\u0020"));
			empty.setBorder(BORDER);

			// TITLE
			Font headerFont = new Font(FontFamily.HELVETICA, 20, Font.BOLD);
			Phrase header = new Phrase("INVOICE", headerFont);
			PdfPCell cell3 = new PdfPCell(header);
			cell3.setBorder(BORDER);

			// SYSTEM
			Paragraph sds = new Paragraph("Salam Distribution System");
			sds.setAlignment(Element.ALIGN_RIGHT);
			PdfPCell cell4 = new PdfPCell();
			cell4.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell4.setBorder(BORDER);
			cell4.addElement(sds);

			table.addCell(cell1);
			table.addCell(empty);
			table.addCell(cell3);
			table.addCell(cell4);

			// DIVIDER
			PdfPCell divider = new PdfPCell(new Phrase("\u0020"));
			divider.setBorder(BORDER);
			divider.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(divider);
			table.addCell(divider);
			table.addCell(divider);
			table.addCell(divider);

			// ----------------------------- END OF HEADER

			// INVOICE DETAILS
			// LINE 1
			PdfPCell custNameLabel = new PdfPCell(
					new Phrase("Customer Name:", labelFont));
			custNameLabel.setBorder(BORDER);
			PdfPCell custName = new PdfPCell(
					new Phrase(CUSTOMER_NAME, dataFont));
			custName.setBorder(BORDER);
			PdfPCell shippingLabel = new PdfPCell(
					new Phrase("Shipping Details:", labelFont));
			shippingLabel.setBorder(BORDER);
			PdfPCell shipping = new PdfPCell(new Phrase(SHIPPING, dataFont));
			shipping.setRowspan(4);
			shipping.setBorder(BORDER);

			table.addCell(custNameLabel);
			table.addCell(custName);
			table.addCell(shippingLabel);
			table.addCell(shipping);

			// LINE 2
			PdfPCell custIdLabel = new PdfPCell(
					new Phrase("Customer ID:", labelFont));
			custIdLabel.setBorder(BORDER);
			PdfPCell custId = new PdfPCell(new Phrase(CUSTOMER_ID, dataFont));
			custId.setBorder(BORDER);

			table.addCell(custIdLabel);
			table.addCell(custId);
			table.addCell(empty);

			// LINE 3
			table.addCell(empty);
			table.addCell(empty);
			table.addCell(empty);

			// LINE 4
			PdfPCell invoiceNoLabel = new PdfPCell(
					new Phrase("Invoice No:", labelFont));
			invoiceNoLabel.setBorder(BORDER);
			PdfPCell invoiceNo = new PdfPCell(new Phrase(INVOICE_NO, dataFont));
			invoiceNo.setBorder(BORDER);

			table.addCell(invoiceNoLabel);
			table.addCell(invoiceNo);
			table.addCell(empty);

			// LINE 5
			PdfPCell invoiceDtLabel = new PdfPCell(
					new Phrase("Invoice Date:", labelFont));
			invoiceDtLabel.setBorder(BORDER);
			PdfPCell invoiceDt = new PdfPCell(new Phrase(INVOICE_DT, dataFont));
			invoiceDt.setBorder(BORDER);
			PdfPCell shippingMethodLabel = new PdfPCell(
					new Phrase("Shipping Method:", labelFont));
			shippingMethodLabel.setBorder(BORDER);
			PdfPCell shippingMethod = new PdfPCell(
					new Phrase(SHIPPING_METHOD, dataFont));
			shippingMethod.setBorder(BORDER);

			table.addCell(invoiceDtLabel);
			table.addCell(invoiceDt);
			table.addCell(shippingMethodLabel);
			table.addCell(shippingMethod);

			// LINE 6
			table.addCell(empty);
			table.addCell(empty);
			table.addCell(empty);
			table.addCell(empty);

			// LINE 7
			PdfPCell orderNoLabel = new PdfPCell(
					new Phrase("Order No.:", labelFont));
			orderNoLabel.setBorder(BORDER);
			PdfPCell orderNo = new PdfPCell(new Phrase(ORDER_NO, dataFont));
			orderNo.setBorder(BORDER);
			PdfPCell billingLabel = new PdfPCell(
					new Phrase("Billing Details:", labelFont));
			billingLabel.setBorder(BORDER);
			PdfPCell billing = new PdfPCell(new Phrase(BILLING, dataFont));
			billing.setBorder(BORDER);
			billing.setRowspan(4);

			table.addCell(orderNoLabel);
			table.addCell(orderNo);
			table.addCell(billingLabel);
			table.addCell(billing);

			// LINE 8
			PdfPCell orderDtLabel = new PdfPCell(
					new Phrase("Order Date:", labelFont));
			orderDtLabel.setBorder(BORDER);
			PdfPCell orderDt = new PdfPCell(new Phrase(ORDER_DT, dataFont));
			orderDt.setBorder(BORDER);

			table.addCell(orderDtLabel);
			table.addCell(orderDt);
			table.addCell(empty);

			// LINE 9
			table.addCell(empty);
			table.addCell(empty);
			table.addCell(empty);

			// LINE 10
			PdfPCell lpoNoLabel = new PdfPCell(
					new Phrase("LPO No.:", labelFont));
			lpoNoLabel.setBorder(BORDER);
			PdfPCell lpoNo = new PdfPCell(new Phrase(LPO_NO, dataFont));
			lpoNo.setBorder(BORDER);

			table.addCell(lpoNoLabel);
			table.addCell(lpoNo);
			table.addCell(empty);

			// LINE 11
			PdfPCell lpoDtLabel = new PdfPCell(
					new Phrase("LPO Date:", labelFont));
			lpoDtLabel.setBorder(BORDER);
			PdfPCell lpoDt = new PdfPCell(new Phrase(LPO_DT, dataFont));
			lpoDt.setBorder(BORDER);
			PdfPCell salesPersonLabel = new PdfPCell(
					new Phrase("Sales Person:", labelFont));
			salesPersonLabel.setBorder(BORDER);
			PdfPCell salesPerson = new PdfPCell(
					new Phrase(SALES_PERSON, dataFont));
			salesPerson.setBorder(BORDER);

			table.addCell(lpoDtLabel);
			table.addCell(lpoDt);
			table.addCell(salesPersonLabel);
			table.addCell(salesPerson);

			// DIVIDER
			table.addCell(divider);
			table.addCell(divider);
			table.addCell(divider);
			table.addCell(divider);

			document.add(table);
			// ----------------------END OF DETAILS

			// ITEMS PART
			table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1f, 3f, 6f, 2f, 2f, 3f });

			// ORDER LINE ITEMS HEADER
			PdfPCell sNoLabel = new PdfPCell(new Phrase("S.No.", labelFont));
			PdfPCell itemIdLabel = new PdfPCell(
					new Phrase("Item ID", labelFont));
			PdfPCell descLabel = new PdfPCell(
					new Phrase("Description", labelFont));
			PdfPCell shippedLabel = new PdfPCell(
					new Phrase("Shipped Qty.", labelFont));
			PdfPCell unitPriceLabel = new PdfPCell(
					new Phrase("Unit Price", labelFont));
			PdfPCell AmountLabel = new PdfPCell(
					new Phrase("Amount", labelFont));

			table.addCell(sNoLabel);
			table.addCell(itemIdLabel);
			table.addCell(descLabel);
			table.addCell(shippedLabel);
			table.addCell(unitPriceLabel);
			table.addCell(AmountLabel);
			table.setHeaderRows(1);

			// ORDER LINE ITEMS
			for (int i = 0; i < LINEITEMS.size(); i++)
			{
				PdfPCell a = new PdfPCell(
						new Phrase(String.valueOf(i + 1), dataFont));
				a.setBorder(BORDER);
				PdfPCell b = new PdfPCell(
						new Phrase(LINEITEMS.get(i)[0], dataFont));
				b.setBorder(BORDER);
				PdfPCell c = new PdfPCell(
						new Phrase(LINEITEMS.get(i)[1], dataFont));
				c.setBorder(BORDER);
				PdfPCell d = new PdfPCell(
						new Phrase(LINEITEMS.get(i)[2], dataFont));
				d.setBorder(BORDER);
				PdfPCell e = new PdfPCell(
						new Phrase(LINEITEMS.get(i)[3], dataFont));
				e.setBorder(BORDER);
				PdfPCell f = new PdfPCell(
						new Phrase(LINEITEMS.get(i)[4], dataFont));
				f.setBorder(BORDER);

				table.addCell(a);
				table.addCell(b);
				table.addCell(c);
				table.addCell(d);
				table.addCell(e);
				table.addCell(f);
			}

			table.addCell(divider);
			table.addCell(divider);
			table.addCell(divider);
			table.addCell(divider);
			table.addCell(divider);
			table.addCell(divider);
			document.add(table);
			document.add(Chunk.NEWLINE);
			// --------------------------- END OF LINE ITEMS

			// TOTALS PART
			table = new PdfPTable(5);
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 2f, 1f, 3f, 1f, 2f });

			// Line 1
			PdfPCell paymentTermsLabel = new PdfPCell(
					new Phrase("Payment Terms:", labelFont));
			paymentTermsLabel.setBorder(BORDER);
			PdfPCell paymentTerms = new PdfPCell(
					new Phrase(PAYMENT_TERMS, dataFont));
			paymentTerms.setBorder(BORDER);
			PdfPCell subTotalLabel = new PdfPCell(
					new Phrase("Sub Total:", labelFont));
			subTotalLabel.setBorder(BORDER);
			PdfPCell subTotal = new PdfPCell(new Phrase(SUBTOTAL, dataFont));
			subTotal.setBorder(BORDER);

			table.addCell(paymentTermsLabel);
			table.addCell(paymentTerms);
			table.addCell(empty);
			table.addCell(subTotalLabel);
			table.addCell(subTotal);

			// Line 2
			PdfPCell dueDateLabel = new PdfPCell(
					new Phrase("Due Date:", labelFont));
			dueDateLabel.setBorder(BORDER);
			PdfPCell dueDate = new PdfPCell(new Phrase(DUE_DATE, dataFont));
			dueDate.setBorder(BORDER);
			PdfPCell taxLabel = new PdfPCell(new Phrase("Tax:", labelFont));
			taxLabel.setBorder(BORDER);
			PdfPCell tax = new PdfPCell(new Phrase(TAX, dataFont));
			tax.setBorder(BORDER);

			table.addCell(dueDateLabel);
			table.addCell(dueDate);
			table.addCell(empty);
			table.addCell(taxLabel);
			table.addCell(tax);

			// Line 3
			PdfPCell totalLabel = new PdfPCell(new Phrase("Total:", labelFont));
			totalLabel.setBorder(BORDER);
			PdfPCell total = new PdfPCell(new Phrase(TOTAL, dataFont));
			total.setBorder(BORDER);

			table.addCell(empty);
			table.addCell(empty);
			table.addCell(empty);
			table.addCell(totalLabel);
			table.addCell(total);

			document.add(table);
			// --------------------------- END OF TOTALS

			document.close();
			writer.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

		return file;
	}

	private void fillFields(OrderTranHeader order)
	{
		CUSTOMER_NAME = order.getCustomer().getCustomerHeader().getCtNm();
		CUSTOMER_ID = order.getCustomer().getCustomerHeader()
				.getCustomerHeaderPK().getCustId();

		INVOICE_NO = order.getCustomer().getSiteInvoices().get(0).getArInvNum();
		Date arInvDate = order.getCustomer().getSiteInvoices().get(0)
				.getArInvDate();
		SimpleDateFormat dt = new SimpleDateFormat(getText("format.date"));
		INVOICE_DT = dt.format(arInvDate);

		ORDER_NO = order.getCustomer().getSiteInvoices().get(0).getOrderNum();
		Date orderDate = order.getCustomer().getSiteInvoices().get(0)
				.getOrderDate();
		ORDER_DT = dt.format(orderDate);

		LPO_NO = order.getOrdTranSum().getCustLpoNum();
		// TODO add null validation
		Date lpoDate = order.getOrdTranSum().getCustLpoDate();
		LPO_DT = dt.format(lpoDate);

		List<CustomerSiteAddress> customerSiteAddressList = order.getCustomer()
				.getSiteInvoices().get(0).getCustomerSite()
				.getCustomerSiteAddressList();
		//getShippingAdrs(order);
		SHIPPING=order.getCtDvrInf();
		

		SALES_PERSON = order.getEmId();

		for (OrderTranLineItem lineItem : order.getOrdTranLineItems())
		{
			if(lineItem.getOverRidePrice() == null)
				LINEITEMS.add(new String[] { lineItem.getItemId(),
						lineItem.getDeItmShrtRcpt(),
						lineItem.getLineQnt().toString(),
						lineItem.getItmPrnPrc().toString(), lineItem.getLineQnt()
								.multiply(lineItem.getItmPrnPrc()).toString() });
				else
					LINEITEMS.add(new String[] { lineItem.getItemId(),
							lineItem.getDeItmShrtRcpt(),
							lineItem.getLineQnt().toString(),
							lineItem.getOverRidePrice().toString(), lineItem.getLineQnt()
									.multiply(lineItem.getOverRidePrice()).toString() });	
		}

		PAYMENT_TERMS = order.getCustomer().getPaymentTerms().getPayIn()
				.toString();

		Calendar c = Calendar.getInstance();
		c.setTime(arInvDate); // Now use today date.
		c.add(Calendar.DATE, Integer.parseInt(PAYMENT_TERMS)); // Adding 5 days
		String dueDate = dt.format(c.getTime());
		DUE_DATE = dueDate;

		SUBTOTAL = order.getOrdTranSum().getDkartSlsTot().toString();
		TAX = order.getOrdTranSum().getDkartTaxTot().toString();
		TOTAL = order.getOrdTranSum().getDkartNetTot().toString();
	}*/
	
	private String getBillingAdrs(List<CustomerSiteAddress> customerSiteAddressList) {
		if (customerSiteAddressList != null
				&& customerSiteAddressList.size() > 0)
		{
			for (CustomerSiteAddress address : customerSiteAddressList)
			{
				if (address.getTyAds() == '0'){

					billing = "";
					billing += address.getA1Cnct() + ",";
					billing += (address.getA2Cnct() != null)
							? address.getA2Cnct() + "," : "";
					billing += (address.getA3Cnct() != null)
							? address.getA3Cnct() + "," : "";
					billing += (address.getA4Cnct() != null)
							? address.getA4Cnct() + "," : "";
					billing += (address.getCiCnct() != null)
							? address.getCiCnct() + "," : "";
					billing += (address.getStCnct() != null)
							? address.getStCnct() + "," : "";
					billing += (address.getPcCnct() != null)
							? address.getPcCnct() + "," : "";
					billing += (address.getCoCnct() != null)
							? address.getCoCnct() : "";
				
				}
				
			}
		}else
			billing="unknown";
		
		return SUCCESS;
		
	}

	private String getShippingAdrs(OrderTranHeader orders2){
		if (orders2 != null && orders2.getCustomer().getSiteInvoices().size()>0)
		{
			String selectedAdres = String.valueOf(orders2.getDeliveryAddressID());
			if(orders2.getCustomer().getSiteInvoices().get(0).getCustomerSite().getCustomerSiteAddressList().size()>0){
			List<CustomerSiteAddress> adrsList = orders2.getCustomer().getSiteInvoices().get(0).getCustomerSite().getCustomerSiteAddressList();
			for (CustomerSiteAddress address : adrsList)
			{
				if (address.getTyAds() == '1' && selectedAdres.equalsIgnoreCase(address.getAdsId()))
				{
					shipping = "";
					shipping += address.getA1Cnct() + ",";
					shipping += (address.getA2Cnct() != null)
							? address.getA2Cnct() + "," : "";
					shipping += (address.getA3Cnct() != null)
							? address.getA3Cnct() + "," : "";
					shipping += (address.getA4Cnct() != null)
							? address.getA4Cnct() + "," : "";
					shipping += (address.getCiCnct() != null)
							? address.getCiCnct() + "," : "";
					shipping += (address.getStCnct() != null)
							? address.getStCnct() + "," : "";
					shipping += (address.getPcCnct() != null)
							? address.getPcCnct() + "," : "";
					shipping += (address.getCoCnct() != null)
							? address.getCoCnct() : "";

				}
				else
					shipping = "unknown";
			}
			}
		}
		return SUCCESS;
	}
	
	
	private OrderTranHeader[] getOverDueInvoices(OrderTranHeader[] orders) {
		OrderTranHeader[] overDueOrders=new OrderTranHeader[orders.length];
		OrderTranHeader[] newOverDueOrders=null;
		int i=0;
		for(OrderTranHeader order:orders){
			BigInteger payTerm = order.getCustomer().getPaymentTerms().getPayIn();
			Date invDate = order.getCustomer().getSiteInvoices().get(0).getArInvDate();
		
				String age[]=calculateInvoiceAge( invDate, '1').split(" ");
				BigInteger invAge =new BigInteger(age[0]);
				if(invAge.compareTo(payTerm)>0){
					overDueOrders[i]=order;
					i++;
				}			
		}
		
		newOverDueOrders=new OrderTranHeader[i--];		
		for(int k=0;k<=i;k++){
			newOverDueOrders[k]=overDueOrders[k];
		}
		return newOverDueOrders;		
	}
	
	private List<CustomerSiteInvoice> getOverDueInvoices(List<CustomerSiteInvoice> CustinvList, List<BigInteger> paymntTrmsList2, List<String> newCustNames) {
		List<CustomerSiteInvoice> overDueInv = new ArrayList<CustomerSiteInvoice>();
		
		List<String> CustNames=new ArrayList<String>();
		for(int i=0;i<CustinvList.size();i++){
			Date invDate=CustinvList.get(i).getArInvDate();
			BigInteger payTerm =paymntTrmsList2.get(i);
			
			String age[]=calculateInvoiceAge( invDate, '1').split(" ");
			BigInteger invAge =new BigInteger(age[0]);
			if(invAge.compareTo(payTerm)>0){
				
				overDueInv.add(CustinvList.get(i));
				CustNames.add(newCustNames.get(i));
				
			}
		}
		
		newCustNames.clear();
		newCustNames.addAll(CustNames);
		return overDueInv;		
	}

	


	private boolean getrecrdpymt(String[] strings, OrderTranHeader[] orders2) {
		boolean result=true;
		int i=0;
		BigDecimal totalPay=BigDecimal.valueOf(0);
		while(i<strings.length){
			if(strings.length!=i+1 && !strings[i].equals(strings[i+1])){
				result=false;
				break;
			}
			
			totalPay=totalPay.add(orders2[i].getCustomer().getSiteInvoices().get(0).getInvPendAmount());
			i++;
		}
		setAmtToPay(totalPay);
		return result;
	}


	private boolean getrecrdpymt(String[] strings, List<CustomerSiteInvoice> newCustinvList2) {
		boolean result=true;
		try{
		int i=0;
		BigDecimal totalPay=BigDecimal.valueOf(0);
		while(i<strings.length){
			if(strings.length!=i+1 && !strings[i].equals(strings[i+1])){
				result=false;
				break;
			}
			
			totalPay=totalPay.add(newCustinvList2.get(i).getInvPendAmount());
			i++;
		}
		setAmtToPay(totalPay);
		}
		catch (Exception e) {
			return result;
			// TODO: handle exception
		}
		return result;
	}
	
/*	public void lpoImage(){
		orders= (OrderTranHeader[])sessionmap.get("invoiceOrdTran");
		lpoImg=Base64.getEncoder().encodeToString(orders[0].getOrdTranLpo().getLpoSlipContent());
	}
	*/


	public String lpoAttributesLoad() throws FileNotFoundException {
	System.out.println(ordersLpo);
		//ordersLpo = (OrderTranHeader[])sessionmap.get("invoiceOrdTran");
		if(ordersLpo[0].getOrdTranLpo()!=null){
			inputStream = new ByteArrayInputStream(ordersLpo[0].getOrdTranLpo().getLpoSlipContent());
			contentType = ordersLpo[0].getOrdTranLpo().getLpoSlipType();
			filename = ordersLpo[0].getOrdTranLpo().getLpoSlipName();
			contentDisposition = "inline;filename=\"" + filename + "\"";
		}

		return SUCCESS;
	}
	
	public String getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
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
	public String getOrderID()
	{
		return orderID;
	}

	public void setOrderID(String orderID)
	{
		this.orderID = orderID;
	}

	public OrderTranHeader[] getOrders()
	{
		return orders;
	}

	public void setOrders(OrderTranHeader[] orders)
	{
		this.orders= orders;
	}

	public String getInvoiceID()
	{
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID)
	{
		this.invoiceID = invoiceID;
	}

	public String getCustomerInfo()
	{
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo)
	{
		this.customerInfo = customerInfo;
	}
	public String getInvItemId()
	{
		return InvItemId;
	}

	public void setInvItemId(String InvItemId)
	{
		if(InvItemId.contains("---")){
		String [] itm=InvItemId.split("---");
		try{
		Long tempId=Long.parseLong(itm[1]);
		this.InvItemId = itm[1];
		}catch(Exception e){
			System.out.println(e);
			this.InvItemId = itm[0];
		}
		}else{
			this.InvItemId =InvItemId;
		}
	}
	
	
	public String getInvType()
	{
		return invType;
	}

	public void setInvType(String invType)
	{
		if(invType.equalsIgnoreCase("Open Invoices")){
			this.invType = "1";
			overDueInvoices=false;
		}else
			if(invType.equalsIgnoreCase("Closed Invoices")){
				this.invType = "0";
				overDueInvoices=false;
			}else
				if(invType.equalsIgnoreCase("Cancelled Invoices")){
					this.invType = "2";
					overDueInvoices=false;
				}else
					if(invType.equalsIgnoreCase("Over due")){
						this.invType = "1";
						overDueInvoices=true;
					}else
						{
							this.invType = null;
							overDueInvoices=false;
						}
		
	}

	public List<CustomerSiteInvoice> getInvoices()
	{
		return invoices;
	}

	public void setInvoices(List<CustomerSiteInvoice> list)
	{
		this.invoices = list;
	}
	
	public OrderTranHeader getOrderDetail()
	{
		return orderDetail;
	}

	public void setOrderDetail(OrderTranHeader orders2)
	{
		this.orderDetail = orders2;
	}

/*	public List<String> getCustomerName()
	{
		return customerName;
	}

	public void customerNamecustomerName(List<String> customerName)
	{
		this.customerName = customerName;
	}*/

	public InputStream getInputStream()
	{
		return inputStream;
	}

	public void setInputStream(InputStream is)
	{
		inputStream = is;
	}

	public String getDatepicker2() {
		return datepicker2;
	}

	public void setDatepicker2(String datepicker2) {
		this.datepicker2 = datepicker2;
	}

	public String getDatepicker1() {
		return datepicker1;
	}

	public void setDatepicker1(String datepicker1) {
		this.datepicker1 = datepicker1;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}
	
	public String[] getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String[] customerName) {
		this.customerName = customerName;
	}
	
	public String getSearchRange() {
		return searchRange;
	}

	public void setSearchRange(String searchRange) {
		this.searchRange = searchRange;
	}
	
	class CustomPDFEvent extends PdfPageEventHelper
	{
		PdfTemplate total;

		@Override
		public void onEndPage(PdfWriter writer, Document document)
		{
			PdfContentByte cb = writer.getDirectContent();
			String pageNo = String.valueOf(writer.getPageNumber());
			Phrase footer = new Phrase(pageNo);

			ColumnText
					.showTextAligned(cb, Element.ALIGN_CENTER, footer,
							(document.right() - document.left()) / 2
									+ document.leftMargin(),
							document.bottom() - 10, 0);
		}

		@Override
		public void onOpenDocument(PdfWriter writer, Document document)
		{
			total = writer.getDirectContent().createTemplate(100, 50);
		}

		// TODO fix broken (x of y) page number printing
		@Override
		public void onCloseDocument(PdfWriter writer, Document document)
		{
			// PdfContentByte cb = writer.getDirectContent();
			// String pageNo = String.valueOf(writer.getPageNumber() - 1);
			// Phrase footer = new Phrase("of " + pageNo);

			// ColumnText.showTextAligned(total, Element.ALIGN_CENTER, footer,
			// 2, 2, 0);

		}

	}

	
	public String getSalesAgntNme() {
		return salesAgntNme;
	}
	public void setSalesAgntNme(String salesAgntNme) {
		this.salesAgntNme = salesAgntNme;
	}
	
	public CustomerSiteInvoice getInvDetail() {
		return invDetail;
	}

	public void setInvDetail(CustomerSiteInvoice invDetail) {
		this.invDetail = invDetail;
	}
	
	public void setSalesAgents(String[] salesAgents) {
		
		this.salesAgents=salesAgents;
	}
	public String[] getSalesAgents() {
		
	return salesAgents;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getBilling() {
		return billing;
	}

	public void setBilling(String billing) {
		this.billing = billing;
	}

	public boolean isDownload() {
		return download;
	}

	public void setDownload(boolean download) {
		this.download = download;
	}

	public boolean getEnblRcrdPaymnt() {
		return EnblRcrdPaymnt;
	}
	
	public void setEnblRcrdPaymnt(boolean EnblRcrdPaymnt) {
		this.EnblRcrdPaymnt=EnblRcrdPaymnt;
	}
	
	public BigDecimal getAmtToPay() {
		return AmtToPay;
	}
	
	public void setAmtToPay(BigDecimal AmtToPay) {
		this.AmtToPay=AmtToPay;
	}
	
	public byte[] getOrder_Lpocntnt() {
		return order_Lpocntnt;
	}
	
	public void setOrder_Lpocntnt(byte[] order_Lpocntnt) {
		this.order_Lpocntnt=order_Lpocntnt;
	}
	
	public java.awt.Image getImgIcn() {
		return imgIcn.getImage();
	}
	
	public void setImgIcn(ImageIcon imgIcn) {
		this.imgIcn=imgIcn;
	}
	
	public String getLpoIcmg() {
		return lpoImg;
	}
	
	public void setLpoIcmg(String lpoImg) {
		this.lpoImg=lpoImg;
	}
	


	/*public InputStream getInputStream() {
		return inputStream;
	}*/

	/*public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}*/

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	
	public OrderTranHeader[] getOrdersLpo() {
		return ordersLpo;
	}

	public void setOrdersLpo(OrderTranHeader[] ordersLpo) {
		this.ordersLpo = ordersLpo;
	}
//to compare the invoice date with current date for the age of invoice
	public Date getCurrentDate() {
		return new java.util.Date();
	}

// Invoice Cancel added by lokesh start
	
	public String cancelCustomerInvoice(){
		String status = "success";
		try {
			
			// check weather the invoice is already cancelled but not yet reached ebs. it the invoice cancel cycle is done
			// end to end the cancel invoice will not be displayed in open invoices itself
			CancelCustomerInvoiceIfc invCancelUtil = DKartContext.getInvoiceCancelUtil();
			
			boolean isOfflineInvoice = invCancelUtil.isOfflineInvoice(invoiceId);
			if (!isOfflineInvoice) {
				boolean isPaymentReceived = invCancelUtil.isPaymentRecevied(invoiceId);
				if(!isPaymentReceived){
				boolean isInvoiceCancelled = invCancelUtil.isInvoiceAlreadyCancelled(invoiceId, orderId);
				if (!isInvoiceCancelled) {
					boolean isMarkedAsDelivered = invCancelUtil.isMarkedAsDelivered(orderId);
					if(!isMarkedAsDelivered){
					// first get the order details for that invoice
					ClaimRemote claim = null;
					// OrderTransactionsIfc ordTran = DKartContext.getLookupOrder();
					// // if the invoice is generated for that order then sc_ord = 7 and sc_tran = 2 , tran_type = 24
					// OrderTranHeader orderTran = ordTran.getOrderByOrderID(orderId, "24", new BigDecimal("7"), new BigDecimal("2"));
					OrderTransactionsIfc trans = DKartContext.getLookupOrder();
					OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
					search.setSearchByinvoiceNumberOrOrderNumber(true);
					search.setInvoiceNumberOrOrderNumber(invoiceId);
					OrderTranHeader orderTran = trans.getTransactionsInvoices(search)[0];
					// build the CLaim Transaction object based on the above
					// Order Tran object
					ClaimTranHeader claimHeader = new ClaimTranHeader();
					claimHeader.setIdOpr(orderTran.getIdOpr()); // Operater ID
					claimHeader.setEmId(orderTran.getEmId()); // EMPOLYEE ID
					claimHeader.setRcRtnMr("1001"); // header level return reason code defaulted to damaged
					claimHeader.setFlInvCncl("Y");
					claimHeader.setCtDvrInf(orderTran.getCtDvrInf());						//	|to set Pick-Up Address by LAXMIKANTH
					claimHeader.setScTran(new BigDecimal(orderTran.getIdBtchInvResv()));	//	|
					
					ClaimTranSum claimSum = new ClaimTranSum();
					claimSum.setIdOrd(orderId);
					claimSum.setOrdIdCt(orderTran.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId()); // CUSTOMER ID
					// attach totals to claim sum
					String format = "format.currency";
					ConfigUtils config = ConfigUtils.getInstance();
					claimSum.setDkartNetTot(config.createBigDecimal(orderTran.getOrdTranSum().getDkartNetTot(), format));
					claimSum.setDkartSlsTot(config.createBigDecimal(orderTran.getOrdTranSum().getDkartSlsTot(), format));
					claimSum.setDkartTndTot(config.createBigDecimal(orderTran.getOrdTranSum().getDkartTndTot(), format));
					claimSum.setDkartDscTot(config.createBigDecimal(orderTran.getOrdTranSum().getDkartDscTot(), format));
					claimSum.setIdOrdArNmb(invoiceId);
					/*
					 * claimSum.setIdOrd(orderTran.getOrdTranSums().get(0).
					 * getIdOrd()); //ORIGINAL ORDER ID
					 */ claimSum.setIdEm(orderTran.getEmId()); // EMPOLYEE ID
					claimHeader.setClaimTranSum(claimSum);


					List<OrderTranLineItem> OrderTranLineItemList = orderTran.getOrdTranLineItems();
					List<ClaimTranLineItem> claimLineItems = new ArrayList<>();
					for (OrderTranLineItem orderTranLineItem : OrderTranLineItemList) {
						boolean split = false;
						ClaimTranLineItem claimTranLineItem = new ClaimTranLineItem();
						claimTranLineItem.setItemId(orderTranLineItem.getItemId()); // ITEM ID
						claimTranLineItem.setDeItmShrtRcpt(orderTranLineItem.getDeItmShrtRcpt()); // ITEM DESCRIPTION
						claimTranLineItem.setLineQnt(orderTranLineItem.getLineQnt()); // QUANTITY SOLD
						claimTranLineItem.setItmPrnPrc(config.createBigDecimal(orderTranLineItem.getItmPrnPrc(), format)); // ITEM SELLING PRICE
						BigDecimal qty = orderTranLineItem.getLineQnt();
						if (orderTranLineItem.getReturnQtyFlag() != null
								&& orderTranLineItem.getReturnQtyFlag().equals("1")) {
							qty = orderTranLineItem.getLineQntRtn();
						}
						claimTranLineItem.setLineQntRtn(qty); // QUANTITY REGISTERED FOR RETURN
						claimTranLineItem.setExtnLnItmRtn(config.createBigDecimal(orderTranLineItem.getExtnLnItmRtn(), format));// Extended Price
						claimTranLineItem.setExtnDscLnItm(config.createBigDecimal(orderTranLineItem.getExtnDscLnItm(), format));// Discounted Price
						claimTranLineItem.setRcRtnMr("1001"); // ITEM LEVEL REASON CODE defaulted to damaged
						claimTranLineItem.setItmTy(orderTranLineItem.getItmTy()); // STOCK ITEM OR SERVICE ITEM

						if (orderTranLineItem.getPriceOverRideFlag() != null
								&& orderTranLineItem.getPriceOverRideFlag().equals("1")) {
							claimTranLineItem.setOvrdPrc(config.createBigDecimal(orderTranLineItem.getOverRidePrice(), format)); // PRICE AFTER PRICE CHANGE
							claimTranLineItem.setRcPrcOvrr(config.createBigDecimal(orderTranLineItem.getPriceOvrrRsnCode(), format).toString()); // PRICE CHANGE REASON CODE
						}
						//set discount lines
						List<OrderTranDiscountItem> discountLines = orderTranLineItem.getOrdTranDscItms();
						List<ClaimTranDscItm> claimDiscounts = new ArrayList<ClaimTranDscItm>();
						for(OrderTranDiscountItem discount : discountLines){
							ClaimTranDscItm claimDisc = new ClaimTranDscItm();
							int scale = ConfigUtils.getInstance().createBigDecimal(0, "format.currency").scale();
							claimDisc.setDscAmt(discount.getDscAmt());
							claimDisc.setUnitDscAmt(claimDisc.getDscAmt().divide(qty,scale,RoundingMode.HALF_EVEN));
							/* check if unitDiscount amount of complete qty and total discount amount id matching or not, if not
							*	split the lines to avoid the decimal discrepancy.@laxmikanth
							*/
							if(claimDisc.getUnitDscAmt().multiply(qty).compareTo(claimDisc.getDscAmt()) != 0){
								split = true;
							}
							claimDisc.setDscPer(discount.getDscPer());
							claimDisc.setPrmCmpDtlid(discount.getPrmCmpDtlid());
							claimDisc.setPrmCmpId(discount.getPrmCmpId());
							claimDisc.setPrmDesc(discount.getPrmDesc());
							claimDisc.setPrmId(discount.getPrmId());
							claimDisc.setDiscReasonCode(discount.getDiscReasonCode());// setting discount reason code: laxmikanth
							if(discount.getPrmType() == null)
								claimDisc.setPrmType(BigDecimal.ZERO); // SET PRM_TYPE TO ZERO(MANUAL DISCOUNTS)
							else
								claimDisc.setPrmType(discount.getPrmType());
							claimDisc.setSrcTrgList(discount.getSrcTrgList());
							claimDisc.setTyDsc(discount.getTyDsc());
							
							ClaimTranDscItmPK claimDiscPK = new ClaimTranDscItmPK();
							claimDiscPK.setDiscSeqNum(discount.getId().getDiscSeqNum());
							claimDiscPK.setOrdLnItmSeq(discount.getId().getOrdLnItmSeq());
							
							claimDisc.setId(claimDiscPK);
							claimDisc.setClaimTranLineItem(claimTranLineItem);
							
							claimDiscounts.add(claimDisc);
						}
						claimTranLineItem.setClaimTranDscItms(claimDiscounts);
						if(split){
							LOGGER.info("Lines are being splitted to maintain the proper decimal calculations[unitDiscount] for Item : {}",claimTranLineItem.getItemId());
							ClaimTranLineItem clonedClaimLineItem = SerializationUtils.clone(claimTranLineItem);
							ClaimRemote claimBean =  DKartContext.getClaimBean();
							List<ClaimTranLineItem> splittedLines = claimBean.splitLines(clonedClaimLineItem,qty);
							claimLineItems.addAll(splittedLines);
						}else{
							claimLineItems.add(claimTranLineItem);
						}
					}
					claimHeader.setClaimTranLineItems(claimLineItems);
					try {
						claim = DKartContext.getClaimBean();
						claimHeader.setScOrd(BigDecimal.ONE); // Claim Registered
						claim.saveClaimTranHeader(claimHeader);
						status = "success";
					} catch (Exception e) {
						e.printStackTrace();
						status = "failed";
					}

					// now set values to variables to use via value stack
					setOrderId(orderId);
					setInvoiceId(invoiceId);
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
					setOrderDate(sdf.parse(orderTran.getId().getDcDyOrd()));
					setCustId(orderTran.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId());
					setCustName(orderTran.getCustomer().getCustomerHeader().getCtNm());
					setInvAmt(config.createBigDecimal(orderTran.getOrdTranSum().getDkartNetTot().toString(), format).toString());
					setInvStatus("Invoice Cancelled");
					} else {
						setInvStatus("Invoice Cancellation Failed");
						addActionError("Invoice for the Order that is Delivered Cannot be Cancelled");
						status = "success";
					}
					
					} else {
					// already a return found the order, (one invoice one order)
					setInvStatus("Invoice Cancellation Failed");
					addActionError(
							"Invoice Already Cancelled (or) Claim is Already Registered for this Invoice / Order");
					status = "success";
				}
				}
				else{
					setInvStatus("Invoice Cancellation Failed");
					addActionError("Invoice's for which Full Payment / Partial Payment is Received Cannot be Cancelled");
					status = "success";
				}
				
			} else {
				// this is a offline invoice, & offline invoice cannot be cancelled
				setInvStatus("Invoice Cancellation Failed");
				addActionError("Offline Invoice cannot be Cancelled. Try Performing a Return");
				status = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error Occured during Cancelling the Invoice : "+invoiceId);
			setInvStatus("Invoice Cancellation Failed");
			status = "failed";
		}
		return status;
	}
// Invoice Cancel added by lokesh end

	/* mudassir code for new delivered order action method */
	public String deliveredOrderLoadPageAction() {
		employee= super.getEmployee();
		this.setDelvORderSearchVO(new DelvOrderSearchVO());
		return SUCCESS;
	}

// mudassir start
public DelvOrderSearchVO getDelvORderSearchVO() {
	return delvORderSearchVO;
}

public void setDelvORderSearchVO(DelvOrderSearchVO delvORderSearchVO) {
	this.delvORderSearchVO = delvORderSearchVO;
}
// mudassir end

	public String deliveredOrderSearchByCustAndOrder() {
		employee= super.getEmployee();
		OrderTransactionSearchCriteriaIfc searchCreteria = new OrderTransactionSearchCriteria();
		DelvOrderSearchVO searchResult = null;
		if(this.activeTab==null){
return SUCCESS;
}
		if (this.activeTab.equalsIgnoreCase("tab_1")) {
			searchCreteria.setSearchByinvoiceNumberOrOrderNumber(true);
			searchCreteria.setDeliveredORderSearchByItem(false);
			searchCreteria.setSearchByCustomerInfo(false);
			searchCreteria.setInvoiceNumberOrOrderNumber(this.orderID.trim());
			searchCreteria.setOrderDateRangeFrom(this.datepicker1);
			searchCreteria.setOrderDateRangeTo(this.datepicker2);
			searchCreteria.setOrderTotalFrom(this.OrderTotalFrom);
			searchCreteria.setOrderTotalTo(this.OrderTotalTo);
			// calling services
			try {
				if (employee != null) {

					OrderDAOBeanRemote orderDao = DKartContext.getOrderDAOBean();
					searchResult = orderDao.deliveredOrderSearchByTab1(searchCreteria, getEmployee());
					LOGGER.info("fetching the default Search Range of Order Search");
					setSearchRange(DKartContext.getParamterBean().fetchXMLParameterValues().getDeliveredOrderSearchRange());
					LOGGER.info("Default Search Range Fetched is :  "+DKartContext.getParamterBean().fetchXMLParameterValues().getDeliveredOrderSearchRange());
					if(searchResult.getOrderList()==null){
						searchResult.setOrderList(new ArrayList<OrderDetailsWithInvoice>() );
					}
					if (searchResult != null) {
						this.setDelvORderSearchVO(searchResult);
					} else {
						this.setDelvORderSearchVO(new DelvOrderSearchVO());
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (this.activeTab.equalsIgnoreCase("tab_3")) {

			searchCreteria.setSearchByinvoiceNumberOrOrderNumber(false);

			if (this.customerInfo != null && !this.customerInfo.isEmpty()) {
				searchCreteria.setDeliveredOrderCustIdOrDes(this.customerInfo.toUpperCase().trim());
				searchCreteria.setSearchByCustomerInfo(true);
			} else {
				searchCreteria.setSearchByCustomerInfo(false);
			}

			if (this.InvItemId != null && !this.InvItemId.isEmpty()) {
				searchCreteria.setItemIdOrDescription(this.InvItemId.toUpperCase().trim());
				searchCreteria.setDeliveredORderSearchByItem(true);

			} else {
				searchCreteria.setDeliveredORderSearchByItem(false);
			}
			// calling services
			try {
				if (getEmployee() != null) {
					OrderDAOBeanRemote orderDao = DKartContext.getOrderDAOBean();
					searchResult = orderDao.deliveredOrderSearchByTab1(searchCreteria, getEmployee());
					if (searchResult != null) {
						this.setDelvORderSearchVO(searchResult);
					} else {
						this.setDelvORderSearchVO(new DelvOrderSearchVO());
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return SUCCESS;
	}
	
	
	
	
	
	

	public List<OrdInvShpQtySrlno> getSerialNoList() {
		return serialNoList;
	}

	public void setSerialNoList(List<OrdInvShpQtySrlno> serialNoList) {
		this.serialNoList = serialNoList;
	}

	public OrdInvShpQtySrlno getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(OrdInvShpQtySrlno serialNo) {
		this.serialNo = serialNo;
	}
	
	public BigDecimal getDeliveredQuantity() {
		return this.deliveredQuantity;
	}

	public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
		this.deliveredQuantity = deliveredQuantity;
	}
	

	public boolean isShowDeliveredQuantity() {
		return showDeliveredQuantity;
	}


	public void setShowDeliveredQuantity(boolean showDeliveredQuantity) {
		this.showDeliveredQuantity = showDeliveredQuantity;
	}

	public Map<String, StringBuffer> getSlnoMap() {
		return slnoMap;
	}

	public void setSlnoMap(Map<String, StringBuffer> slnoMap) {
		this.slnoMap = slnoMap;
	}

	public boolean isEnableRecordPaymentButton() {
		return enableRecordPaymentButton;
	}

	public void setEnableRecordPaymentButton(boolean enableRecordPaymentButton) {
		this.enableRecordPaymentButton = enableRecordPaymentButton;
	}

	/**
	 * @return the openInvoiceSearchRange
	 */
	public String getOpenInvoiceSearchRange() {
		return openInvoiceSearchRange;
	}

	/**
	 * @param openInvoiceSearchRange the openInvoiceSearchRange to set
	 */
	public void setOpenInvoiceSearchRange(String openInvoiceSearchRange) {
		this.openInvoiceSearchRange = openInvoiceSearchRange;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
