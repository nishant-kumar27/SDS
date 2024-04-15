package util.email;
/**
* The SendClaimEmail program is used to send 
* the email related to claims
* 
* @author  Sharanya M

*/
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.retailsols.sds.creditmemo.CreditMemoBean;
import com.retailsols.sds.creditmemo.LookUpCreditMemoIfc;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.claim.ClaimRemote;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class SendClaimEmail extends DSAction{
	static final Logger LOGGER = LogManager.getLogger(SendClaimEmail.class);
	 private String claimDate;
	 private boolean loginEmp;
	 private boolean salesAgent; 
	 private boolean customer;
	 private boolean departmentHead;
	 private boolean DataEntryOptr;
	 private String custommail;
	 private String claimId;
	 private String crmemoId;
	 private CreditMemoBean crMemoBean;
	 private OrderTranHeader claimTransaction;
	 private String custName;

	 
	//method to return html content in the form of string for Approve,Accept,Register,Reject of claims
 public	String constructHtmlBodyForCustomer(ClaimTranHeader claimTranHeader,CustomerHeader customerHeader) throws TemplateException, IOException {
	 LOGGER.info("inside the method of constucting html body for customer in ClaimEmail class");
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		Template template = null;
		//cfg.setDirectoryForTemplateLoading(new File("E:/Saideep/eclipse_mars/workspace/SDS_EARWeb/WebContent/freemarker"));
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "freemarker"); //to load the freemarker template
		if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("1") || claimTranHeader.getScOrd().compareTo(new BigDecimal(6))==0 ){
			 template = cfg.getTemplate("email-claim-customer.ftl"); //freemarker template
				}else
					if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("2") || claimTranHeader.getScOrd().toString().equalsIgnoreCase("3") || claimTranHeader.getScOrd().toString().equalsIgnoreCase("4")){
						 template = cfg.getTemplate("email-approveClaim-customer.ftl"); //freemarker template for approve
					}
					else
						if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("5")){
							template = cfg.getTemplate("email-acceptClaim-customer.ftl"); //freemarker template for accept
						}
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("ClaimTranItemList",claimTranHeader.getClaimTranLineItems()); //adding claim Line Items
		rootMap.put("claimId", claimTranHeader.getClaimId()); //adding claimId
		rootMap.put("claimDate", getRequiredDate(claimTranHeader.getClaimTranSum().getId().getDcDyOrd())); //adding claimDate
		rootMap.put("customerId", claimTranHeader.getClaimTranSum().getOrdIdCt()); //adding customerId
		rootMap.put("customerName",customerHeader.getCtNm()); //adding customerName
		rootMap.put("netTotal", claimTranHeader.getClaimTranSum().getDkartNetTot()); //adding net total
		Writer out = new StringWriter();
		template.process(rootMap, out);
		return out.toString();
	}
 
 
 ////method to return html content in the form of string for Approve,Accept,Register,Reject of claims for sales agent
 public	String constructHtmlBodyForSalesAgentandDeptHead(ClaimTranHeader claimTranHeader,CustomerHeader customerHeader) throws TemplateException, IOException {
	 LOGGER.info("inside the method of constucting html body for SalesAgentandDeptHead in ClaimEmail class");
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		Template template =null;
		//cfg.setDirectoryForTemplateLoading(new File("E:/Saideep/eclipse_mars/workspace/SDS_EARWeb/WebContent/freemarker"));
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "freemarker"); //to load the freemarker template
		if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("1")  || claimTranHeader.getScOrd().compareTo(new BigDecimal(6))==0){
	 template = cfg.getTemplate("email-claim-salesAgentandDeptHead.ftl"); //freemarker template
		}else
			if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("2") || claimTranHeader.getScOrd().toString().equalsIgnoreCase("3") || claimTranHeader.getScOrd().toString().equalsIgnoreCase("4")){
				 template = cfg.getTemplate("email-approveClaim-salesAgentandDeptHead.ftl"); //freemarker template for approve
			}
			else
				if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("5")){
					template = cfg.getTemplate("email-acceptClaim-salesAgentandDeptHead.ftl"); //freemarker template for accept
				}
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("ClaimTranItemList",claimTranHeader.getClaimTranLineItems()); //adding claim Line Items
		rootMap.put("claimId", claimTranHeader.getClaimId()); //adding claimId
		rootMap.put("claimDate", getRequiredDate(claimTranHeader.getClaimTranSum().getId().getDcDyOrd())); //adding claimDate
		rootMap.put("customerId", claimTranHeader.getClaimTranSum().getOrdIdCt()); //adding customerId
		rootMap.put("customerName",customerHeader.getCtNm()); //adding customerName
		rootMap.put("netTotal", claimTranHeader.getClaimTranSum().getDkartNetTot()); //adding net total
		Writer out = new StringWriter();
		template.process(rootMap, out);
		return out.toString();
	}
 
 
 ////method to return html content in the form of string for CreditMemo for all roles
 public	String constructHtmlBodyForCrMemo(ClaimTranHeader claimTranHeader,CreditMemoBean crMemoBean) throws TemplateException, IOException {
	 LOGGER.info("inside the method of constucting html body for customer in ClaimEmail class");
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		Template template = null;
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "freemarker"); //to load the freemarker template
		if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("1") || claimTranHeader.getScOrd().compareTo(new BigDecimal(6))==0 ){
			 template = cfg.getTemplate("email-creditmemo.ftl"); //freemarker template
		}
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("ClaimTranItemList",claimTranHeader.getClaimTranLineItems()); //adding claim Line Items
		rootMap.put("claimId", claimTranHeader.getClaimId()); //adding claimId
		rootMap.put("creditMemoId",crMemoBean.getCrediMemoId());
		rootMap.put("creditMemoCreationDate",getRequiredDate(crMemoBean.getCrMemoDate()));
		rootMap.put("customerId", claimTranHeader.getClaimTranSum().getOrdIdCt()); //adding customerId
		rootMap.put("customerName",getCustName()); //adding customerName
		rootMap.put("creditMemoAmt", crMemoBean.getCrMemoAmount()); //adding net total
		Writer out = new StringWriter();
		template.process(rootMap, out);
		return out.toString();
	}
 //method to send claimAcceptEmail
 public String sendClaimAcceptEmail(){
	 LOGGER.info("inside sendClaimAcceptEmail()");
	 //for accept claim
	 String acceptClaimEmailEnableForCustomer;
	 String acceptClaimEmailEnableForSalesAgent;
	 String acceptClaimEmailEnableForDeptHeadr;
	 String acceptClaimEmailEnableForDataEntryOptr;
	 String LoginEmployee;
	 
	 String[] splitcustomMail = custommail.split(",");
	 MailBeanRemote mailBeanRemote;
	 String emailSubject;
	 ParameterConfigurationServiceIfc parameterBeanRemote;
	 String result="success";
	 try {
		 OrderTransactionsIfc dao = DKartContext.getLookupOrder();
		 ClaimTranHeader claimHeader = dao.getClaimTranHeader(claimId, null);
		 CustomerHeader header=getCustomerInfo(claimHeader);
		 String[] deptHeadEmails = getDepartmentHeadEmail(claimHeader);
		 String dataEntryOptrEmail=getDataEntryOptrMailId(claimHeader);
		 LoginEmployee=getEmployee().getEmail();
		 mailBeanRemote = DKartContext.getMailBean();
		 parameterBeanRemote=DKartContext.getParamterBean();
		 
		 emailSubject = "Claim Details For Your Claim ID " + claimHeader.getClaimId();
		 //getting parameters for sending mails
		 acceptClaimEmailEnableForCustomer=parameterBeanRemote.fetchXMLParameterValues().getAcceptClaimEnableSendingMailToCustomer();
		 acceptClaimEmailEnableForSalesAgent=parameterBeanRemote.fetchXMLParameterValues().getAcceptClaimEnableSendingMailToSalesAgent();
		 acceptClaimEmailEnableForDeptHeadr=parameterBeanRemote.fetchXMLParameterValues().getAcceptClaimEnableSendingMailToDeptHead();
		 acceptClaimEmailEnableForDataEntryOptr=parameterBeanRemote.fetchXMLParameterValues().getAcceptClaimEnableSendingMailToDataEntryOperator();
		 
		 if(splitcustomMail!=null && !splitcustomMail[0].isEmpty()){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_ACCEPT, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.CUSTOM, 
					 emailSubject,constructHtmlBodyForCustomer(claimHeader,header), splitcustomMail);
			 LOGGER.info("AcceptClaimEmail has been sent successfully");
		 }
		 if(LoginEmployee!=null && loginEmp==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_ACCEPT, claimHeader.getClaimId(), null, 
					 emailSubject,constructHtmlBodyForCustomer(claimHeader,header), LoginEmployee);
			 LOGGER.info("AcceptClaimEmail sent to Login Employee");
		 }
		 if(acceptClaimEmailEnableForCustomer.equalsIgnoreCase("YES") && customer==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_ACCEPT, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, 
					 emailSubject,constructHtmlBodyForCustomer(claimHeader,header), header.getCtEmlId());
			 LOGGER.info("AcceptClaimEmail sent to customer");
		 }
		 if(acceptClaimEmailEnableForSalesAgent.equalsIgnoreCase("YES") && salesAgent==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_ACCEPT, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, 
					 emailSubject,constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), getSalesAgentEmail(claimHeader));
			 LOGGER.info("AcceptClaimEmail sent to sales agent");
		 }
		 if(acceptClaimEmailEnableForDeptHeadr.equalsIgnoreCase("YES") && departmentHead==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_ACCEPT, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, 
					 emailSubject,constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), deptHeadEmails);
			 LOGGER.info("AcceptClaimEmail sent to Department Head");
		 }
		 if(acceptClaimEmailEnableForDataEntryOptr.equalsIgnoreCase("YES") && DataEntryOptr==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_ACCEPT, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.DATA_ENTRY_OPERATOR, 
					 emailSubject,constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), dataEntryOptrEmail);
			 LOGGER.info("AcceptClaimEmail sent to DataEntry Operator");
		 }
	} catch (Exception e) {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		result="failure";
	}
	 return result;
 }
 
 //method to send claimApprovedEmail
 public String sendClaimAproveEmail(){
	 LOGGER.info("inside sendClaimApproveEmail()");
	 //for approve claim
	 String approveClaimEmailEnableForCustomer; //customer
	 String approveClaimEmailEnableForSalesAgent; //SalesAgent
	 String approveClaimEmailEnableForDeptHead; //DeptHead
	 String approveClaimEmailEnableForDataEntryOptr;//DataEntryOperator
	 String LoginEmployee;
	 
	 String[] splitcustomMail = custommail.split(",");
	 MailBeanRemote mailBeanRemote;
	 String emailSubject;
	 ParameterConfigurationServiceIfc parameterBeanRemote;
	 String result="success";
	 try {
		 OrderTransactionsIfc dao = DKartContext.getLookupOrder();
		 ClaimTranHeader claimHeader = dao.getClaimTranHeader(claimId, null);
		 CustomerHeader header=getCustomerInfo(claimHeader);
		 String[] deptHeadEmails = getDepartmentHeadEmail(claimHeader);
		 String dataEntryOptrEmail=getDataEntryOptrMailId(claimHeader);
		 LoginEmployee=getEmployee().getEmail();
		 mailBeanRemote = DKartContext.getMailBean();
		 parameterBeanRemote=DKartContext.getParamterBean();
		 emailSubject = "Claim Details For Your Claim ID " + claimHeader.getClaimId();
		 //enable mail for approve Claim
		 approveClaimEmailEnableForCustomer=parameterBeanRemote.fetchXMLParameterValues().getApproveClaimEnableSendingMailToCustomer();
		 approveClaimEmailEnableForSalesAgent=parameterBeanRemote.fetchXMLParameterValues().getApproveClaimEnableSendingMailToSalesAgent();
		 approveClaimEmailEnableForDeptHead=parameterBeanRemote.fetchXMLParameterValues().getApproveClaimEnableSendingMailToDeptHead();
		 approveClaimEmailEnableForDataEntryOptr=parameterBeanRemote.fetchXMLParameterValues().getApproveClaimEnableSendingMailToDataEntryOperator();
		 
		 if(splitcustomMail!=null && !splitcustomMail[0].isEmpty()){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_APPROVE, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.CUSTOM, 
					 emailSubject,constructHtmlBodyForCustomer(claimHeader,header), splitcustomMail);
			 LOGGER.info("ApproveClaimMail has been sent successfully");
		 }
		 if(LoginEmployee!=null && loginEmp==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_APPROVE, claimHeader.getClaimId(), null, 
					 emailSubject,constructHtmlBodyForCustomer(claimHeader,header),LoginEmployee);
			 LOGGER.info("ApproveClaimMail sent to Login Employee");
		 }
		 if(approveClaimEmailEnableForCustomer.equalsIgnoreCase("YES") && customer==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_APPROVE, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, 
					 emailSubject,constructHtmlBodyForCustomer(claimHeader,header), header.getCtEmlId());
			 LOGGER.info("ApproveClaimMail sent to customer");
		 }
		 if(approveClaimEmailEnableForSalesAgent.equalsIgnoreCase("YES") && salesAgent==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_APPROVE, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, 
					 emailSubject,constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), getSalesAgentEmail(claimHeader));
			 LOGGER.info("ApproveClaimMail sent to sales agent");
		 }
		 if(approveClaimEmailEnableForDeptHead.equalsIgnoreCase("YES") && departmentHead==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_APPROVE, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, 
					 emailSubject,constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), deptHeadEmails);
			 LOGGER.info("ApproveClaimMail sent to Department Head");
		 }
		 if(approveClaimEmailEnableForDataEntryOptr.equalsIgnoreCase("YES") && DataEntryOptr==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_APPROVE, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.DATA_ENTRY_OPERATOR, 
					 emailSubject,constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), dataEntryOptrEmail);
			 LOGGER.info("ApproveClaimMail sent to DataEntry Operator");
		 }
	} catch (Exception e) {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		result="failure";
	}
	 return result;
 }

//method to send claim registerEmail
public String sendClaimRegisterEmail(){
	
	 LOGGER.info("inside sendClaimRegisterEmail()");
	
	 String registerClaimEmailEnableForCustomer; //customer
	 String registerClaimEmailEnableForSalesAgent; //SalesAgent
	 String registerClaimEmailEnableForDeptHead; //DeptHead
	 String registerClaimEmailEnableForDataEntryOptr;//DataEntry Operator
	 String LoginEmployee;
	 
	 String[] splitcustomMail = custommail.split(",");
	 MailBeanRemote mailBeanRemote;
	 ParameterConfigurationServiceIfc parameterBeanRemote;
	 String emailSubject;
	 String result="success";
	 try {
		 OrderTransactionsIfc dao = DKartContext.getLookupOrder();
		 ClaimTranHeader claimHeader = dao.getClaimTranHeader(claimId, null);
		 CustomerHeader header=getCustomerInfo(claimHeader);
		 String[] deptHeadEmails = getDepartmentHeadEmail(claimHeader);
		 String dataEntryOptrEmail=getDataEntryOptrMailId(claimHeader);
		 LoginEmployee=getEmployee().getEmail();
		 mailBeanRemote = DKartContext.getMailBean();
		 parameterBeanRemote=DKartContext.getParamterBean();
		 
			emailSubject = "Claim Details For Your Claim ID " + claimHeader.getClaimId();
		 //enable mail for register Claim
		 registerClaimEmailEnableForCustomer=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToCustomer();
		 registerClaimEmailEnableForSalesAgent=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToSalesAgent();
		 registerClaimEmailEnableForDeptHead=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToDeptHead();
		 registerClaimEmailEnableForDataEntryOptr=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToDataEntryOperator();
		 
		 if(splitcustomMail!=null && !splitcustomMail[0].isEmpty()){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_REGISTER, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.CUSTOM, 
					 emailSubject,constructHtmlBodyForCustomer(claimHeader,header), splitcustomMail);
			 LOGGER.info("RegisterClaimEmail has been sent successfully");
		 }
		 if(LoginEmployee!=null && loginEmp==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_REGISTER, claimHeader.getClaimId(), null, 
					 emailSubject,constructHtmlBodyForCustomer(claimHeader,header),LoginEmployee);
			 LOGGER.info("RegisterClaimEmail sent to customer");
		 }
		 if(registerClaimEmailEnableForCustomer.equalsIgnoreCase("YES") && customer==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_REGISTER, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, 
					 emailSubject,constructHtmlBodyForCustomer(claimHeader,header), header.getCtEmlId());
			 LOGGER.info("RegisterClaimEmail sent to customer");
		 }
		 if(registerClaimEmailEnableForSalesAgent.equalsIgnoreCase("YES") && salesAgent==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_REGISTER, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, 
					 emailSubject,constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), getSalesAgentEmail(claimHeader));
			 LOGGER.info("RegisterClaimEmail sent to sales agent");
		 }
		 if(registerClaimEmailEnableForDeptHead.equalsIgnoreCase("YES") && departmentHead==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_REGISTER, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, 
					 emailSubject,constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), deptHeadEmails);
			 LOGGER.info("RegisterClaimEmail sent to departmentHead");
		 }
		 if(registerClaimEmailEnableForDataEntryOptr.equalsIgnoreCase("YES") && DataEntryOptr==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_REGISTER, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.DATA_ENTRY_OPERATOR, 
					 emailSubject,constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), dataEntryOptrEmail);
			 LOGGER.info("RegisterClaimMail sent to DataEntry Operator");
		 }
	} catch (Exception e) {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		result="failure";
	}
	 return result;	
	
}



public String getValidateStatus(){
	try{
		OrderTransactionsIfc dao = DKartContext.getLookupOrder();
		ClaimTranHeader claimHeader = dao.getClaimTranHeader(claimId, null);
		
		if(claimHeader.getScOrd().compareTo(new BigDecimal(1))==0 || claimHeader.getScOrd().compareTo(new BigDecimal(6))==0) {
		sendClaimRegisterEmail();
		}else if(claimHeader.getScOrd().compareTo(new BigDecimal(2))==0 || claimHeader.getScOrd().compareTo(new BigDecimal(3))==0 || claimHeader.getScOrd().compareTo(new BigDecimal(4))==0){
		sendClaimAproveEmail();
	    }else if(claimHeader.getScOrd().compareTo(new BigDecimal(5))==0){
	   sendClaimAcceptEmail();
	   }
	}catch(Exception e){
		e.printStackTrace();
		return ERROR;
	}
	return SUCCESS;
	
}


 //method to get customer header
 public CustomerHeader getCustomerInfo(ClaimTranHeader claimHeader){
	 LOGGER.info("getting customer info in ClaimEmail class");
	 String customerId=claimHeader.getClaimTranSum().getOrdIdCt();
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
 
 
 //method to get employee master
 /*public EmployeeIfc getSalesAgentInfo(ClaimTranHeader claimHeader)
 {
	 LOGGER.info("getting sales agent info in ClaimEmail class");
	 EmployeeIfc emp=null;
	 try {
	 String orderId=claimHeader.getClaimTranSum().getIdOrd();
		OrderTransactionsIfc ordtrn;
		ordtrn = DKartContext.getLookupOrder();
		OrderTransactionSearchCriteria searchCriteria=new OrderTransactionSearchCriteria();
		searchCriteria.setInvoiceNumberOrOrderNumber(orderId);
		OrderTranHeader[] ord=ordtrn.getCustomerSiteInvoices(searchCriteria);
		String salesAgentId=ord[0].getEmId();
		LookUpEmployeeIfc remotebean=DKartContext.getLookupEmployee();
		EmployeeSearchCriteria employeeSearchCriteria = new EmployeeSearchCriteria();
		employeeSearchCriteria.setEmployeeId(salesAgentId);
		emp= remotebean.lookupEmployee(employeeSearchCriteria);
 } catch (Exception e) {
	 e.printStackTrace();
	 LOGGER.error(e.getMessage());
	}
	return emp;
 }
 */
 
 //to get the sales agent mail id
public String getSalesAgentEmail(ClaimTranHeader claimTranHeader)
{
	String orderId=claimTranHeader.getClaimTranSum().getIdOrd();
	ClaimRemote claimBean=null;
	String email = null;
	try{
		claimBean = DKartContext.getClaimBean();
		email=claimBean.getSalesAgentEmailId(orderId);		
	}catch(Exception e){
		LOGGER.error(e.getMessage());
		return email;
	}
	return email;
}
 

//get department head mailIds
public String[] getDepartmentHeadEmail(ClaimTranHeader claimTranHeader)
{
	String custId=claimTranHeader.getClaimTranSum().getOrdIdCt();
	ClaimRemote claimBean=null;
	String[] emails = null;
	List<String> emailsList = new ArrayList<String>();
	try{
		claimBean = DKartContext.getClaimBean();
		emailsList=claimBean.getDepartmentHeadEmail(custId);
		emails = new String[emailsList.size()];
		emails = emailsList.toArray(emails);
	}catch(Exception e){
		LOGGER.error(e.getMessage());
		return emails;
	}
	return emails;
}

//to get the dataEntryOprtr Email id
public String getDataEntryOptrMailId(ClaimTranHeader claimTranHeader) {			
	String claimId=claimTranHeader.getClaimId();
	ClaimRemote claimBean=null;
	String email = null;
	try{
		claimBean = DKartContext.getClaimBean();
		email=claimBean.getDataEntryOprtrEmailId(claimId);		
	}catch(Exception e){
		LOGGER.error(e.getMessage());
		return email;
	}
	return email;
}

//to get the required date format
public String getRequiredDate(String date){
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	//SimpleDateFormat sdfNew = new SimpleDateFormat(getText("format.date"));
	try {
		//claimDate = sdfNew.format(sdf.parse(date));
		date= sdf.format(new Date());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		LOGGER.error(e.getMessage());
	}
	return date;
}

//method to send CreditMemo Email @sharanya
public String sendCreditMemoEmail(){
	
	 LOGGER.info("inside sendClaimRegisterEmail()");
	
	 String creditMemoEmailEnableForCustomer; //customer
	 String creditMemoEmailEnableForSalesAgent; //SalesAgent
	 String creditMemoEmailEnableForDeptHead; //DeptHead
	 String creditMemoEmailEnableForDataEntryOptr;//DataEntry Operator
	 String LoginEmployee;
	 
	 String[] splitcustomMail = custommail.split(",");
	 MailBeanRemote mailBeanRemote;
	 ParameterConfigurationServiceIfc parameterBeanRemote;
	 String emailSubject;
	 String result="success";

	 try {
		 LookUpCreditMemoIfc trans = DKartContext.getCreditMemoDetails();
		 setBean(trans.getCreditMemoDetails(crmemoId));// get the credit memo details
		 //setClaimTransaction(trans.getClaimDetailsByClaimID(crMemoBean.getClaimId()));// now get the claim details by claim id 
		 OrderTransactionsIfc dao = DKartContext.getLookupOrder();
		 ClaimTranHeader claimHeader = dao.getClaimTranHeader(claimId, null);
		 CustomerHeader header=getCustomerInfo(claimHeader);
		 setCustName(header.getCtNm());
		 String[] deptHeadEmails = getDepartmentHeadEmail(claimHeader);
		 String dataEntryOptrEmail=getDataEntryOptrMailId(claimHeader);
		 LoginEmployee=getEmployee().getEmail();
		 mailBeanRemote = DKartContext.getMailBean();
		 parameterBeanRemote=DKartContext.getParamterBean();
		 
		emailSubject = "Credit Memo Details For Your Claim ID " + claimId;
		 //enable mail for register Claim
		creditMemoEmailEnableForCustomer=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToCustomer();
		creditMemoEmailEnableForSalesAgent=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToSalesAgent();
		creditMemoEmailEnableForDeptHead=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToDeptHead();
		creditMemoEmailEnableForDataEntryOptr=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToDataEntryOperator();
		 
		 if(splitcustomMail!=null && !splitcustomMail[0].isEmpty()){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CRMEMO,claimId, MailBeanRemote.MailRecipientTypeEnum.CUSTOM, 
					 emailSubject,constructHtmlBodyForCrMemo(claimHeader,crMemoBean), splitcustomMail);
			 LOGGER.info("Credit Memo Email has been sent successfully");
		 }
		 if(LoginEmployee!=null && loginEmp==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CRMEMO, claimId, null, 
					 emailSubject,constructHtmlBodyForCrMemo(claimHeader,crMemoBean),LoginEmployee);
			 LOGGER.info("Credit Memo Email sent to LoginEmployee");
		 }
		 if(creditMemoEmailEnableForCustomer.equalsIgnoreCase("YES") && customer==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CRMEMO,claimId, MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, 
					 emailSubject,constructHtmlBodyForCrMemo(claimHeader,crMemoBean), header.getCtEmlId());
			 LOGGER.info("Credit Memo Email sent to customer");
		 }
		 if(creditMemoEmailEnableForSalesAgent.equalsIgnoreCase("YES") && salesAgent==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CRMEMO, claimId, MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, 
					 emailSubject,constructHtmlBodyForCrMemo(claimHeader,crMemoBean), getSalesAgentEmail(claimHeader));
			 LOGGER.info("Credit Memo Email sent to sales agent");
		 }
		 if(creditMemoEmailEnableForDeptHead.equalsIgnoreCase("YES") && departmentHead==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CRMEMO,claimId, MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, 
					 emailSubject,constructHtmlBodyForCrMemo(claimHeader,crMemoBean), deptHeadEmails);
			 LOGGER.info("Credit Memo Email sent to departmentHead");
		 }
		 if(creditMemoEmailEnableForDataEntryOptr.equalsIgnoreCase("YES") && DataEntryOptr==true){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CRMEMO, claimId, MailBeanRemote.MailRecipientTypeEnum.DATA_ENTRY_OPERATOR, 
					 emailSubject,constructHtmlBodyForCrMemo(claimHeader,crMemoBean), dataEntryOptrEmail);
			 LOGGER.info("Credit Memo Email sent to DataEntry Operator");
		 }
	} catch (Exception e) {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		result="failure";
	}
	 return result;	
	
}

public String getclaimDate() {
	return claimDate;
}


public void setclaimDate(String claimDate) {
	this.claimDate = claimDate;
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

public String getClaimId() {
	return claimId;
}


public void setClaimId(String claimId) {
	this.claimId = claimId;
}


public String getCrmemoId() {
	return crmemoId;
}

public void setCrmemoId(String crmemoId) {
	this.crmemoId = crmemoId;
}
public CreditMemoBean getBean() {
	return crMemoBean;
}
public void setBean(CreditMemoBean bean) {
	this.crMemoBean = bean;
}

public OrderTranHeader getClaimTransaction() {
	return claimTransaction;
}
public void setClaimTransaction(OrderTranHeader claimTransaction) {
	this.claimTransaction = claimTransaction;
}


public String getCustName() {
	return custName;
}


public void setCustName(String custName) {
	this.custName = custName;
}

}
