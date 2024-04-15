package util.email;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.retailsols.sds.receipt.LookUpReceiptDetailsIfc;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;
import com.test.entities.OrderDetailsWithInvoice;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.payment.RisplDkArPaym;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class ReceiptEmail extends DSAction{
	
	static final Logger LOGGER = LogManager.getLogger(InvoiceEmail.class);
	private String receiptID;
	private boolean loginEmp;
	private boolean salesAgent; 
	private boolean customer;
	private boolean departmentHead;
	private boolean DataEntryOptr;
	private String custommail;
	private RisplDkArPaym receipt;
	private OrderDetailsWithInvoice invoice;
	private String invoiceDate;
	private String custName;
	
	
	
	

	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public OrderDetailsWithInvoice getInvoice() {
		return invoice;
	}
	public void setInvoice(OrderDetailsWithInvoice invoice) {
		this.invoice = invoice;
	}
	public RisplDkArPaym getReceipt() {
		return receipt;
	}
	public void setReceipt(RisplDkArPaym receipt) {
		this.receipt = receipt;
	}
	public String getReceiptID() {
		return receiptID;
	}
	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
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
	public String constructHtmlBodyForCustomer(List<RisplDkArPaym> pmt, OrderDetailsWithInvoice inv)
			throws TemplateException, IOException {
		LOGGER.info("inside the constructHtmlBodyForCustomer()");
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		//cfg.setDirectoryForTemplateLoading(new File("D:/SDS_Salam/SDS_EARWeb/WebContent/freemarker"));
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "freemarker"); //for template loading
		Template template = cfg.getTemplate("email-receiptdetail-customer.ftl"); //template name
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("RisplDkArPaym", pmt); 
		rootMap.put("OrderDetailsWithInvoice", inv); 
		rootMap.put("customerID", pmt.get(0).getCustId());
		rootMap.put("customerName", getCustName()); 
		rootMap.put("ReceiptID", pmt.get(0).getArPaymNum());
		rootMap.put("PaymentAmount",pmt.get(0).getArPaymAmount());
		//rootMap.put("invDate",getRequiredDate(custinv.getArInvDate()));
		rootMap.put("PaymentDate",getRequiredDate(pmt.get(0).getArPaymDate())); 
		rootMap.put("OrderDate",getRequiredDate(inv.getOrderDate()));
		rootMap.put("InvoiceDate",getRequiredDate(inv.getInvoiceDate()));
		Writer out = new StringWriter();
		template.process(rootMap, out);
		return out.toString();
	}
	
	public String sendReceiptEmail()
	{
		String ReceiptEmailEnableForcustomer;
		String ReceiptEmailEnableForSalesAgent;
		String ReceiptEmailEnableForDeptHeadr;
		String ReceiptEmailEnableForDataEntryOptr;
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
			LookUpReceiptDetailsIfc customr = DKartContext.getCustomerReceiptDetails();
			LookUpCustomerIfc customerIfc = DKartContext.getLookUpCustomer();
			CustomerSearchCriteria custCriteria = new CustomerSearchCriteria();
			// get receipt details
			List<RisplDkArPaym> pmt = customr.getReceiptDetails(receiptID);
			setReceipt(pmt.get(0));
			custCriteria.setCustomerId(receipt.getCustId());
			CustomerIfc customer1 = customerIfc.lookUpCust(custCriteria)[0];
		    setCustName(customer1.getCustomerHeader().getCtNm());
			OrderDetailsWithInvoice inv = customr.getCustomerInvoiceDetails(pmt.get(0).getId().getArInvNum());
			setInvoice(inv);
			CustomerHeader header=getCustomerInfo(inv);;
			String ordId=inv.getId().getOrderId();
			
			Loginemployee=getEmployee().getEmail();
			emailOrderSubject = "Receipt Details for your Invoice";
			//getting parameters values for sending mails
			ReceiptEmailEnableForcustomer = parameterBeanRemote.fetchXMLParameterValues().getInvoiceEnableSendingMailToCustomer();
			ReceiptEmailEnableForSalesAgent = parameterBeanRemote.fetchXMLParameterValues().getInvoiceEnableSendingMailToSalesAgent();
			ReceiptEmailEnableForDeptHeadr= parameterBeanRemote.fetchXMLParameterValues().getInvoiceEnableSendingMailToDeptHead();
			ReceiptEmailEnableForDataEntryOptr= parameterBeanRemote.fetchXMLParameterValues().getInvoiceEnableSendingMailToDataEntryOperator();
			
			if(splitcustomMail!=null && !splitcustomMail[0].isEmpty() ){
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, pmt.get(0).getId().getArInvNum(),
						MailBeanRemote.MailRecipientTypeEnum.CUSTOM, emailOrderSubject,constructHtmlBodyForCustomer(pmt,inv), splitcustomMail );
				LOGGER.info("Receipt mail has been sent successfully");
			}
			
			if(Loginemployee!=null && loginEmp==true){
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, pmt.get(0).getId().getArInvNum(),
					 null, emailOrderSubject,constructHtmlBodyForCustomer(pmt,inv), Loginemployee );
				LOGGER.info("Receipt mail has been delivered to Login Employee");
			}
			if (ReceiptEmailEnableForSalesAgent.equalsIgnoreCase("YES") && salesAgent==true) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE,pmt.get(0).getId().getArInvNum(),
						MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, emailOrderSubject,
						constructHtmlBodyForCustomer(pmt,inv), trans.getSalesAgentMailId(ordId));
				LOGGER.info("Receipt mail has been delivered to sales agent");
			}
			if (ReceiptEmailEnableForcustomer.equalsIgnoreCase("YES") && customer==true) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, pmt.get(0).getId().getArInvNum(),
						MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, emailOrderSubject, constructHtmlBodyForCustomer(pmt,inv),
						header.getCtEmlId());
				LOGGER.info("Receipt mail has been delivered to Customer");
			}
			if (ReceiptEmailEnableForDeptHeadr.equalsIgnoreCase("YES") && departmentHead==true) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, pmt.get(0).getId().getArInvNum(),
						MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, emailOrderSubject, constructHtmlBodyForCustomer(pmt,inv),
						trans.getDepartmentHeadMailId(ordId));
				LOGGER.info("Receipt mail has been delivered to Department Head");
			}
			
			if (ReceiptEmailEnableForDataEntryOptr.equalsIgnoreCase("YES") && DataEntryOptr==true) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE,pmt.get(0).getId().getArInvNum(),
						MailBeanRemote.MailRecipientTypeEnum.DATA_ENTRY_OPERATOR, emailOrderSubject, constructHtmlBodyForCustomer(pmt,inv),
						trans.getDataEntryOptrMailId(ordId));
				LOGGER.info("Receipt mail has been delivered to DataEntry Operator");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			result = "failure";
		}
		return result;
	}
	
	
	 //method to get customer header
	 public CustomerHeader getCustomerInfo(OrderDetailsWithInvoice inv){
		 LOGGER.info("getting customer info in ClaimEmail class");
		 String customerId=inv.getCustId();
			LookUpCustomerIfc lookUpCustomer;
			CustomerHeader customerHeader = null;
			try {
			lookUpCustomer = DKartContext.getLookUpCustomer();
			CustomerSearchCriteria csc=new CustomerSearchCriteria();
			csc.setCustomerId(customerId);
			CustomerIfc[] customer=lookUpCustomer.lookUpCust(csc);
			customerHeader=customer[0].getCustomerHeader();
	 } catch (Exception e) {
		 e.printStackTrace();
		 LOGGER.error(e.getMessage());
		}
		 return customerHeader;
	 }
	
	
	public String getRequiredDate(Date date) {
		Format sdf = new SimpleDateFormat("dd-MMM-yy");
		String Date = null;
		try {
			Date= sdf.format(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return Date;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
}
