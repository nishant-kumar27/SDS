package util.email;
/**
* The InvoiceEmail program is used to send 
* the email related to Invoice
* 
* @author  Sharanya M
*/

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.OrderTransactionSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class InvoiceEmail extends DSAction {
	static final Logger LOGGER = LogManager.getLogger(InvoiceEmail.class);
	private String invoiceDate;
	private boolean loginEmp;
	private boolean salesAgent; 
	private boolean customer;
	private boolean departmentHead;
	private boolean DataEntryOptr;
	private String custommail;
	private OrderTranHeader order;
	private String invoiceID;
	private String custName;
	
	public String constructHtmlBodyForCustomer(OrderTranHeader order)
			throws TemplateException, IOException {
		LOGGER.info("inside the constructHtmlBodyForCustomer()");
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		//cfg.setDirectoryForTemplateLoading(new File("D:/SDS_Salam/SDS_EARWeb/WebContent/freemarker"));
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "freemarker"); //for template loading
		Template template = cfg.getTemplate("email-invoicedetail-customer.ftl"); //template name
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("OrderTranLineItemsList", order.getOrdTranLineItems());
		rootMap.put("customerID", order.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId()); 
		rootMap.put("customerName", getCustName()); 
		rootMap.put("orderID", order.getOrdTranSum().getIdOrd());//setting orderId 
		rootMap.put("deliveryDate", getRequiredDate(order.getOrdTranSum().getOrdDlvrDate())); //Setting OrderDelivereyDate
		try{
			if(order.getOrdTranLpo()==null || order.getOrdTranLpo()!=null ){
		    if(order.getOrdTranLpo()==null || order.getOrdTranLpo().getLpoNumber()==null){
		    String LpoNo="-";
			rootMap.put("LpoNo", LpoNo); //Setting OrderLPONo
		}else{
			rootMap.put("LpoNo", order.getOrdTranLpo().getLpoNumber()); //Setting OrderLPONo
		}
		if(order.getOrdTranLpo()==null || order.getOrdTranLpo().getLpoDate()==null){
			 String LpoDate="-";
			 rootMap.put("LpoDate", LpoDate); //Setting OrderLPODate
		}else{
			rootMap.put("LpoDate", order.getOrdTranLpo().getLpoDate()); //Setting OrderLPODate
		}
		}
		else{
			rootMap.put("LpoNo", order.getOrdTranLpo().getLpoNumber()); //Setting OrderLPONo
			rootMap.put("LpoDate", order.getOrdTranLpo().getLpoDate()); //Setting OrderLPODate
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		rootMap.put("invId",order.getCustomer().getSiteInvoices().get(0).getArInvNum());
		rootMap.put("invDate",getRequiredDate(order.getCustomer().getSiteInvoices().get(0).getArInvNum()));
		rootMap.put("netTotal",order.getCustomer().getSiteInvoices().get(0).getInvAmount());  
		Writer out = new StringWriter();
		template.process(rootMap, out);
		return out.toString();
	}

	
	//method to send email for invoice @sharanya
	public String sendInvoiceEmail() {

		String InvoiceEmailEnableForCustomer;
		String InvoiceEmailEnableForSalesAgent;
		String InvoiceEmailEnableForDeptHeadr;
		String InvoiceEmailEnableForDataEntryOptr;
		String Loginemployee;
		
		String[] splitcustomMail = custommail.split(",");
		MailBeanRemote mailBeanRemote;
		ParameterConfigurationServiceIfc parameterBeanRemote;
		String emailOrderSubject;
		String result = "success";
		try {
			mailBeanRemote = DKartContext.getMailBean();
			parameterBeanRemote = DKartContext.getParamterBean();
			OrderTransactionsIfc trans = DKartContext.getLookupOrder();
			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(invoiceID);
			OrderTranHeader[] orders1 = trans.getTransactionsInvoices(search); 
			orders1 = trans.getTransactionsInvoices(search);
			order = orders1[0];
			CustomerHeader header = order.getCustomer().getCustomerHeader();
			setCustName(header.getCtNm());
			String ordId=order.getOrdTranSum().getIdOrd();
			Loginemployee=getEmployee().getEmail();
			emailOrderSubject = "Invoice Details for your Order ID " + order.getOrdTranSum().getIdOrd();
			//getting parameters values for sending mails
			InvoiceEmailEnableForCustomer = parameterBeanRemote.fetchXMLParameterValues().getInvoiceEnableSendingMailToCustomer();
			InvoiceEmailEnableForSalesAgent = parameterBeanRemote.fetchXMLParameterValues().getInvoiceEnableSendingMailToSalesAgent();
			InvoiceEmailEnableForDeptHeadr= parameterBeanRemote.fetchXMLParameterValues().getInvoiceEnableSendingMailToDeptHead();
			InvoiceEmailEnableForDataEntryOptr= parameterBeanRemote.fetchXMLParameterValues().getInvoiceEnableSendingMailToDataEntryOperator();
			
			if(splitcustomMail!=null && !splitcustomMail[0].isEmpty() ){
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.INVOICE, order.getCustomer().getSiteInvoices().get(0).getArInvNum(),
						MailBeanRemote.MailRecipientTypeEnum.CUSTOM, emailOrderSubject,constructHtmlBodyForCustomer(order), splitcustomMail );
				LOGGER.info("Invoice mail has been sent successfully");
			}
			
			if(Loginemployee!=null && loginEmp==true){
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.INVOICE, order.getCustomer().getSiteInvoices().get(0).getArInvNum(),
					 null, emailOrderSubject,constructHtmlBodyForCustomer(order), Loginemployee );
				LOGGER.info("Invoice mail has been delivered to Login Employee");
			}
			if (InvoiceEmailEnableForSalesAgent.equalsIgnoreCase("YES") && salesAgent==true) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.INVOICE, order.getCustomer().getSiteInvoices().get(0).getArInvNum(),
						MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, emailOrderSubject,
						constructHtmlBodyForCustomer(order), trans.getSalesAgentMailId(ordId));
				LOGGER.info("Invoice mail has been delivered to sales agent");
			}
			if (InvoiceEmailEnableForCustomer.equalsIgnoreCase("YES") && customer==true) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.INVOICE, order.getCustomer().getSiteInvoices().get(0).getArInvNum(),
						MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, emailOrderSubject, constructHtmlBodyForCustomer(order),
						header.getCtEmlId());
				LOGGER.info("Invoice mail has been delivered to Customer");
			}
			if (InvoiceEmailEnableForDeptHeadr.equalsIgnoreCase("YES") && departmentHead==true) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.INVOICE, order.getCustomer().getSiteInvoices().get(0).getArInvNum(),
						MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, emailOrderSubject, constructHtmlBodyForCustomer(order),
						trans.getDepartmentHeadMailId(ordId));
				LOGGER.info("Invoice mail has been delivered to Department Head");
			}
			
			if (InvoiceEmailEnableForDataEntryOptr.equalsIgnoreCase("YES") && DataEntryOptr==true) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.INVOICE, order.getCustomer().getSiteInvoices().get(0).getArInvNum(),
						MailBeanRemote.MailRecipientTypeEnum.DATA_ENTRY_OPERATOR, emailOrderSubject, constructHtmlBodyForCustomer(order),
						trans.getDataEntryOptrMailId(ordId));
				LOGGER.info("Invoice mail has been delivered to DataEntry Operator");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			result = "failure";
		}
		return result;
	}
	
	
	//to get the required date format
	public String getRequiredDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		try {
			invoiceDate= sdf.format(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return invoiceDate;
	}
	
	public String getRequiredDate(Date date) {
		Format sdf = new SimpleDateFormat("dd-MMM-yy");
		String dateformt = null;
		try {
			dateformt= sdf.format(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return dateformt;
	}

		public String getInvoiceDate() {
			return invoiceDate;
		}


		public void setInvoiceDate(String invoiceDate) {
			this.invoiceDate = invoiceDate;
		}
		
		public boolean isLoginEmp() {
			return loginEmp;
		}

		public void setLoginEmp(boolean loginEmp) {
			this.loginEmp = loginEmp;
		}

		public boolean isSalesAgent() {
			return salesAgent;
		}

		public void setSalesAgent(boolean salesAgent) {
			this.salesAgent = salesAgent;
		}

		public boolean isCustomer() {
			return customer;
		}

		public void setCustomer(boolean customer) {
			this.customer = customer;
		}

		public boolean isDepartmentHead() {
			return departmentHead;
		}

		public void setDepartmentHead(boolean departmentHead) {
			this.departmentHead = departmentHead;
		}

		public boolean isDataEntryOptr() {
			return DataEntryOptr;
		}

		public void setDataEntryOptr(boolean dataEntryOptr) {
			DataEntryOptr = dataEntryOptr;
		}

		public String getCustommail() {
			return custommail;
		}


		public void setCustommail(String custommail) {
			this.custommail = custommail;
		}


		public OrderTranHeader getOrder() {
			return order;
		}


		public void setOrder(OrderTranHeader order) {
			this.order = order;
		}


		public String getInvoiceID() {
			return invoiceID;
		}


		public void setInvoiceID(String invoiceID) {
			this.invoiceID = invoiceID;
		}


		public String getCustName() {
			return custName;
		}


		public void setCustName(String custName) {
			this.custName = custName;
		}
}
