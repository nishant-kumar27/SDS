package util.email;
/**
* The ClaimEmail program is used to send 
* the email related to claims
* 
* @author  Srinivas Reddy G
* @version 1.0
* @since   2017-01-01 
*/
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.claim.ClaimRemote;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class ClaimEmail extends DSAction{
	static final Logger LOGGER = LogManager.getLogger(ClaimEmail.class);
	 private String orderDate;
	//method to return html content in the form of string for Approve,Accept,Register,Reject of claims
 public	String constructHtmlBodyForCustomer(ClaimTranHeader claimTranHeader,CustomerHeader customerHeader) throws TemplateException, IOException {
	 LOGGER.info("inside the method of constucting html body for customer in ClaimEmail class");
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		Template template = null;
		//cfg.setDirectoryForTemplateLoading(new File("E:/Saideep/eclipse_mars/workspace/SDS_EARWeb/WebContent/freemarker"));
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "freemarker"); //to load the freemarker template
		if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("1")){
			 template = cfg.getTemplate("email-claim-customer.ftl"); //freemarker template
				}else
					if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("2")){
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
		if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("1")){
	 template = cfg.getTemplate("email-claim-salesAgentandDeptHead.ftl"); //freemarker template
		}else
			if(claimTranHeader.getScOrd().toString().equalsIgnoreCase("2")){
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
 
 //method to send claimAcceptEmail
 public String sendClaimAcceptEmail(ClaimTranHeader claimHeader){
	 LOGGER.info("inside sendClaimAcceptEmail()");
	 //for accept claim
	 String acceptClaimEmailEnableForCustomer;
	 String acceptClaimEmailEnableForSalesAgent;
	 String acceptClaimEmailEnableForDeptHeadr;
	 MailBeanRemote mailBeanRemote;
	 ParameterConfigurationServiceIfc parameterBeanRemote;
	 String result="success";
	 try {
		 CustomerHeader header=getCustomerInfo(claimHeader);
		 String[] deptHeadEmails = getDepartmentHeadEmail(claimHeader);
		// EmployeeIfc salesAgent=getSalesAgentInfo(claimHeader);
		 mailBeanRemote = DKartContext.getMailBean();
		 parameterBeanRemote=DKartContext.getParamterBean();
		 //getting parameters for sending mails
		 acceptClaimEmailEnableForCustomer=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToCustomer();
		 acceptClaimEmailEnableForSalesAgent=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToSalesAgent();
		 acceptClaimEmailEnableForDeptHeadr=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToDeptHead();
		 if(acceptClaimEmailEnableForCustomer.equalsIgnoreCase("YES")){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_ACCEPT, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, 
					 emailSubject(claimHeader),constructHtmlBodyForCustomer(claimHeader,header), header.getCtEmlId());
			 LOGGER.info("AcceptClaimEmail sent to customer");
		 }
		 if(acceptClaimEmailEnableForSalesAgent.equalsIgnoreCase("YES")){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_ACCEPT, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, 
					 emailSubject(claimHeader),constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), getSalesAgentEmail(claimHeader));
			 LOGGER.info("AcceptClaimEmail sent to sales agent");
		 }
		 if(acceptClaimEmailEnableForDeptHeadr.equalsIgnoreCase("YES")){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_ACCEPT, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, 
					 emailSubject(claimHeader),constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), deptHeadEmails);
			 LOGGER.info("AcceptClaimEmail sent to Department Head");
		 }
	} catch (Exception e) {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		result="failure";
	}
	 return result;
 }
 
 //method to send claimApprovedEmail
 public String sendClaimAproveEmail(ClaimTranHeader claimHeader){
	 LOGGER.info("inside sendClaimApproveEmail()");
	 //for approve claim
	 String approveClaimEmailEnableForCustomer; //customer
	 String approveClaimEmailEnableForSalesAgent; //SalesAgent
	 String approveClaimEmailEnableForDeptHead; //DeptHead
	 MailBeanRemote mailBeanRemote;
	 ParameterConfigurationServiceIfc parameterBeanRemote;
	 String result="success";
	 try {
		 CustomerHeader header=getCustomerInfo(claimHeader);
		 String[] deptHeadEmails = getDepartmentHeadEmail(claimHeader);
		// EmployeeIfc salesAgent=getSalesAgentInfo(claimHeader);
		 mailBeanRemote = DKartContext.getMailBean();
		 parameterBeanRemote=DKartContext.getParamterBean();
		 //enable mail for register Claim
		 approveClaimEmailEnableForCustomer=parameterBeanRemote.fetchXMLParameterValues().getApproveClaimEnableSendingMailToCustomer();
		 approveClaimEmailEnableForSalesAgent=parameterBeanRemote.fetchXMLParameterValues().getApproveClaimEnableSendingMailToSalesAgent();
		 approveClaimEmailEnableForDeptHead=parameterBeanRemote.fetchXMLParameterValues().getApproveClaimEnableSendingMailToDeptHead();
		 if(approveClaimEmailEnableForCustomer.equalsIgnoreCase("YES")){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_APPROVE, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, 
					 emailSubject(claimHeader),constructHtmlBodyForCustomer(claimHeader,header), header.getCtEmlId());
			 LOGGER.info("ApproveClaimMail sent to customer");
		 }
		 if(approveClaimEmailEnableForSalesAgent.equalsIgnoreCase("YES")){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_APPROVE, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, 
					 emailSubject(claimHeader),constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), getSalesAgentEmail(claimHeader));
			 LOGGER.info("ApproveClaimMail sent to sales agent");
		 }
		 if(approveClaimEmailEnableForDeptHead.equalsIgnoreCase("YES")){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_APPROVE, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, 
					 emailSubject(claimHeader),constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), deptHeadEmails);
			 LOGGER.info("ApproveClaimMail sent to Department Head");
		 }
	} catch (Exception e) {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		result="failure";
	}
	 return result;
 }
 
/* still parameter is not added for sending mails for rejecting
 *  //method to send claim rejectMail
public String sendClaimRejectEmail(ClaimTranHeader claimHeader){
	 //for approve claim
	 String rejectClaimEmailEnableForCustomer; //customer
	 String rejectClaimEmailEnableForSalesAgent; //SalesAgent
	 String rejectClaimEmailEnableForDeptHead; //DeptHead
	 MailBeanRemote mailBeanRemote;
	 ParametereConfigurationServiceIfc parameterBeanRemote;
	 try {
		 CustomerHeader header=getCustomerInfo(claimHeader);
		 mailBeanRemote = DKartContext.getMailBean();
		 parameterBeanRemote=DKartContext.getParamterBean();

	} catch (Exception e) {
		// TODO: handle exception
	}
	 return null;
}*/

//method to send claim registerEmail
public String sendClaimRegisterEmail(ClaimTranHeader claimHeader){
	
	 LOGGER.info("inside sendClaimRegisterEmail()");
	
	 String registerClaimEmailEnableForCustomer; //customer
	 String registerClaimEmailEnableForSalesAgent; //SalesAgent
	 String registerClaimEmailEnableForDeptHead; //DeptHead
	 MailBeanRemote mailBeanRemote;
	 ParameterConfigurationServiceIfc parameterBeanRemote;
	 String result="success";
	 try {
		 CustomerHeader header=getCustomerInfo(claimHeader);
		//EmployeeIfc salesAgent=getSalesAgentInfo(claimHeader);
		String[] deptHeadEmails = getDepartmentHeadEmail(claimHeader);
		 mailBeanRemote = DKartContext.getMailBean();
		 parameterBeanRemote=DKartContext.getParamterBean();
		 //enable mail for register Claim
		 registerClaimEmailEnableForCustomer=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToCustomer();
		 registerClaimEmailEnableForSalesAgent=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToSalesAgent();
		 registerClaimEmailEnableForDeptHead=parameterBeanRemote.fetchXMLParameterValues().getRegisterClaimEnableSendingMailToDeptHead();
		 if(registerClaimEmailEnableForCustomer.equalsIgnoreCase("YES")){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_REGISTER, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, 
					 emailSubject(claimHeader),constructHtmlBodyForCustomer(claimHeader,header), header.getCtEmlId());
			 LOGGER.info("RegisterClaimEmail sent to customer");
		 }
		 if(registerClaimEmailEnableForSalesAgent.equalsIgnoreCase("YES")){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_REGISTER, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, 
					 emailSubject(claimHeader),constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), getSalesAgentEmail(claimHeader));
			 LOGGER.info("RegisterClaimEmail sent to sales agent");
		 }
		 if(registerClaimEmailEnableForDeptHead.equalsIgnoreCase("YES")){
			 mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.CLAIM_REGISTER, claimHeader.getClaimId(), MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, 
					 emailSubject(claimHeader),constructHtmlBodyForSalesAgentandDeptHead(claimHeader,header), deptHeadEmails);
			 LOGGER.info("RegisterClaimEmail sent to departmentHead");
		 }
	} catch (Exception e) {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		result="failure";
	}
	 return result;	
	
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

 //method to return the emailSubject based on the order Status
 public String emailSubject(ClaimTranHeader claimHeader){
			// TODO Auto-generated method stub
	 LOGGER.info("inside emailSubject() in ClaimEmail class");
			String mailSubject=null;
			if(claimHeader.getScOrd().toString().equalsIgnoreCase("1")){
				mailSubject="Claim Id "+claimHeader.getClaimId()+" has been registered";
			}else
				if(claimHeader.getScOrd().toString().equalsIgnoreCase("2")){
					mailSubject="Claim Id "+claimHeader.getClaimId()+" has been approved";
					}
				else
					if(claimHeader.getScOrd().toString().equalsIgnoreCase("5")){
						mailSubject="Claim Id "+claimHeader.getClaimId()+" has been accepted";
						}
					else
						if(claimHeader.getScOrd().toString().equalsIgnoreCase("6")){
							mailSubject="Claim Id "+claimHeader.getClaimId()+" has been rejected";
							}else
							{
								mailSubject= "none";
							}
	 return mailSubject;
 }
//to get the required date format
public String getRequiredDate(String date){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfNew = new SimpleDateFormat(getTextCustom("format.date"));
	try {
		orderDate = sdfNew.format(sdf.parse(date));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		LOGGER.error(e.getMessage());
	}
	return orderDate;
}


public String getOrderDate() {
	return orderDate;
}


public void setOrderDate(String orderDate) {
	this.orderDate = orderDate;
}
}
