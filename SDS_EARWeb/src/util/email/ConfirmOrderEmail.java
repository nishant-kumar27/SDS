package util.email;

/**
* The ConfirmOrderEmail program is used to send 
* the email
* 
* @author  Srinivas Reddy G
* @version 1.0
* @since   2017-01-01 
*/
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.dkart.services.ejb.claim.ClaimRemote;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.OrderTransactionSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class ConfirmOrderEmail extends DSAction {
	static final Logger LOGGER = LogManager.getLogger(ConfirmOrderEmail.class);

	private String orderDate;
	private boolean loginEmp;
	private boolean salesAgent; 
	private boolean customer;
	private boolean departmentHead;
	private boolean DataEntryOptr;
	private String custommail;
	private OrderTranHeader ordtrn;
	private String ordrId;
	//private EmployeeIfc employee;
	//private SessionMap<String, Object> sessionMap;

	//method to return html body from freemarker template in the string format
	public String constructHtmlBodyForCustomer(OrderTranHeader orderTran, CustomerHeader customerHeader)
			throws TemplateException, IOException {
		LOGGER.info("inside the constructHtmlBodyForCustomer()");
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		//cfg.setDirectoryForTemplateLoading(new File("D:/SDS_Salam/SDS_EARWeb/WebContent/freemarker"));
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "freemarker"); //for template loading
		Template template = cfg.getTemplate("email-neworder-customer.ftl"); //template name
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("OrderTranLineItemsList", orderTran.getOrdTranLineItems()); //setting orderId 
		rootMap.put("orderId", orderTran.getOrdTranSum().getIdOrd()); //setting orderId 
		rootMap.put("orderDate", getRequiredDate(orderTran.getId().getDcDyOrd())); //Setting OrderCreationDate
		rootMap.put("deliveryDate", getRequiredDate(orderTran.getOrdTranSum().getOrdDlvrDate())); //Setting OrderDelivereyDate
		try{
			if(orderTran.getOrdTranLpo()==null || orderTran.getOrdTranLpo()!=null ){
		    if(orderTran.getOrdTranLpo()==null || orderTran.getOrdTranLpo().getLpoNumber()==null){
		    String LpoNo1="-";
			rootMap.put("LpoNo", LpoNo1); //Setting OrderLPONo
		}else{
			rootMap.put("LpoNo", orderTran.getOrdTranLpo().getLpoNumber()); //Setting OrderLPONo
		}
		if(orderTran.getOrdTranLpo()==null || orderTran.getOrdTranLpo().getLpoDate()==null){
			 String LpoDate="-";
			 rootMap.put("LpoDate", LpoDate); //Setting OrderLPODate
		}else{
			rootMap.put("LpoDate", orderTran.getOrdTranLpo().getLpoDate()); //Setting OrderLPODate
		}
		}
		else{
			rootMap.put("LpoNo", orderTran.getOrdTranLpo().getLpoNumber()); //Setting OrderLPONo
			rootMap.put("LpoDate", orderTran.getOrdTranLpo().getLpoDate()); //Setting OrderLPODate
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		rootMap.put("customerId", customerHeader.getCustomerHeaderPK().getCustId()); //setting CustomerId
		rootMap.put("customerName", customerHeader.getCtNm()); //setting customerName
		rootMap.put("netTotal", orderTran.getOrdTranSum().getDkartNetTot()); //setting OrderTotal
		Writer out = new StringWriter();
		template.process(rootMap, out);
		return out.toString();
	}

	//method to return html body from freemarker template in the string format
	public String constructHtmlBodyForSalesAgentAndDeptHead(OrderTranHeader orderTran, CustomerHeader customerHeader)
			throws TemplateException, IOException {
		LOGGER.info("inside the constructHtmlBodyForSalesAgentAndDeptHead()");
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		//cfg.setDirectoryForTemplateLoading(new File("E:/Saideep/eclipse_mars/workspace/SDS_EARWeb/WebContent/freemarker"));
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "freemarker"); //for template loading
		Template template = cfg.getTemplate("email-neworder-salesAgentAndDeptHead.ftl"); //template name
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("OrderTranLineItemsList", orderTran.getOrdTranLineItems()); //setting orderId 
		rootMap.put("orderId", orderTran.getOrdTranSum().getIdOrd()); //setting orderId 
		rootMap.put("orderDate", getRequiredDate(orderTran.getId().getDcDyOrd())); //Setting OrderCreationDate
		rootMap.put("customerId", customerHeader.getCustomerHeaderPK().getCustId()); //setting CustomerId
		rootMap.put("customerName", customerHeader.getCtNm()); //setting customerName
		rootMap.put("netTotal", orderTran.getOrdTranSum().getDkartNetTot()); //setting OrderTotal
		Writer out = new StringWriter();
		template.process(rootMap, out);
		return out.toString();
	}
	
	//method to return html body from freemarker template in the string format for Return order
	public String constructHtmlBodyForReturnOrder(OrderTranHeader orderTran, CustomerHeader customerHeader,ClaimTranHeader claim_tran_header)
			throws TemplateException, IOException {
		LOGGER.info("inside the constructHtmlBodyForCustomer()");
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		//cfg.setDirectoryForTemplateLoading(new File("D:/SDS_Salam/SDS_EARWeb/WebContent/freemarker"));
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "freemarker"); //for template loading
		Template template = cfg.getTemplate("email-returnOrder.ftl"); //template name
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("OrderTranLineItemsList", orderTran.getOrdTranLineItems()); 
		rootMap.put("ClaimTranItemList", claim_tran_header.getClaimTranLineItems()); 
		//rootMap.put("claimDate", getRequiredDate(claim_tran_header.getClaimTranSum().getId().getDcDyOrd())); //adding claimDate
		rootMap.put("orderId", orderTran.getOrdTranSum().getIdOrd()); //setting orderId 
		rootMap.put("orderDate", getRequiredDate(orderTran.getId().getDcDyOrd())); //Setting OrderCreationDate
		rootMap.put("customerId", customerHeader.getCustomerHeaderPK().getCustId()); //setting CustomerId
		rootMap.put("customerName", customerHeader.getCtNm()); //setting customerName
		rootMap.put("netTotal", orderTran.getOrdTranSum().getDkartNetTot()); //setting OrderTotal
		Writer out = new StringWriter();
		template.process(rootMap, out);
		return out.toString();
	}

	//method to send confirm order email
	public String sendConfirmOrderEmail(OrderTranHeader ordtrn) {

		//for book order
		String confirmOrderEmailEnableForCustomer;
		String confirmOrderEmailEnableForSalesAgent;
		String confirmOrderEmailEnableForDeptHeadr;
		MailBeanRemote mailBeanRemote;
		ParameterConfigurationServiceIfc parameterBeanRemote;
		String emailOrderSubject;
		String result = "success";
		try {
			mailBeanRemote = DKartContext.getMailBean();
			parameterBeanRemote = DKartContext.getParamterBean();
			CustomerHeader header = ordtrn.getCustomer().getCustomerHeader();
			String[] deptHeadEmails = getDepartmentHeadEmail(ordtrn);
			//EmployeeIfc salesAgent=getSalesAgentInfo(ordtrn);
			emailOrderSubject = "Your Order " + ordtrn.getOrdTranSum().getIdOrd() + " has been Successfully Placed";
			//getting parameters values for sending mails
			confirmOrderEmailEnableForCustomer = parameterBeanRemote.fetchXMLParameterValues()
					.getBookOrderEnableSendingMailToCustomer();
			confirmOrderEmailEnableForSalesAgent = parameterBeanRemote.fetchXMLParameterValues()
					.getBookOrderEnableSendingMailToSalesAgent();
			confirmOrderEmailEnableForDeptHeadr = parameterBeanRemote.fetchXMLParameterValues()
					.getBookOrderEnableSendingMailToDeptHead();
			if (confirmOrderEmailEnableForSalesAgent.equalsIgnoreCase("YES")) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, ordtrn.getOrdTranSum().getIdOrd(),
						MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, emailOrderSubject,
						constructHtmlBodyForSalesAgentAndDeptHead(ordtrn, header), getSalesAgentEmail(ordtrn));
				LOGGER.info("Order Placed mail has been delivered to sales agent");
			}
			if (confirmOrderEmailEnableForCustomer.equalsIgnoreCase("YES")) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, ordtrn.getOrdTranSum().getIdOrd(),
						MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, emailOrderSubject, constructHtmlBodyForCustomer(ordtrn, header),
						header.getCtEmlId());
				LOGGER.info("Order Placed mail has been delivered to Customer");
			}
			if (confirmOrderEmailEnableForDeptHeadr.equalsIgnoreCase("YES")) {
				mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, ordtrn.getOrdTranSum().getIdOrd(),
						MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, emailOrderSubject,
						constructHtmlBodyForSalesAgentAndDeptHead(ordtrn, header), deptHeadEmails);
				LOGGER.info("Order Placed mail has been delivered to Department Heads");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			result = "failure";
		}
		return result;
	}
	
	
	//method to send  email for order (manually) @sharanya
		public String sendOrderEmail() {

			//for book order
			String OrderEmailEnableForCustomer;
			String OrderEmailEnableForSalesAgent;
			String OrderEmailEnableForDeptHeadr;
			String OrderEmailEnableForDataEntryOptr;
			String LoginEmployee;
	
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
				search.setInvoiceNumberOrOrderNumber(ordrId.trim());
				OrderTranHeader[] orders1 = trans.getTransactionsInvoices(search);
				ordtrn = orders1[0];
				CustomerHeader header = ordtrn.getCustomer().getCustomerHeader();
				String[] deptHeadEmails = getDepartmentHeadEmail(ordtrn);
				String dataEntryOprtrEmail =trans.getDataEntryOptrMailId(ordrId);
				LoginEmployee=getEmployee().getEmail();
				emailOrderSubject = "Order Details For Your Order ID " + ordtrn.getOrdTranSum().getIdOrd();
				
				//getting parameters values for sending mails
				OrderEmailEnableForCustomer = parameterBeanRemote.fetchXMLParameterValues().getBookOrderEnableSendingMailToCustomer();
				OrderEmailEnableForSalesAgent = parameterBeanRemote.fetchXMLParameterValues().getBookOrderEnableSendingMailToSalesAgent();
				OrderEmailEnableForDeptHeadr = parameterBeanRemote.fetchXMLParameterValues().getBookOrderEnableSendingMailToDeptHead();
				OrderEmailEnableForDataEntryOptr = parameterBeanRemote.fetchXMLParameterValues().getBookOrderEnableSendingMailToDataEntryOperator();
				
				if(splitcustomMail!=null && !splitcustomMail[0].isEmpty()){
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, ordtrn.getOrdTranSum().getIdOrd(),
							MailBeanRemote.MailRecipientTypeEnum.CUSTOM, emailOrderSubject,constructHtmlBodyForSalesAgentAndDeptHead(ordtrn, header),splitcustomMail );
					LOGGER.info("Order Detail mail has been sent successfully");
				} 
				
				if(LoginEmployee!=null && loginEmp==true){
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, ordtrn.getOrdTranSum().getIdOrd(),
						 null, emailOrderSubject,constructHtmlBodyForSalesAgentAndDeptHead(ordtrn, header),LoginEmployee );
					LOGGER.info("Order Detail mail has been delivered to Login Employee");
				}
				if (OrderEmailEnableForSalesAgent.equalsIgnoreCase("YES") && salesAgent==true) {
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, ordtrn.getOrdTranSum().getIdOrd(),MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, emailOrderSubject,
							constructHtmlBodyForSalesAgentAndDeptHead(ordtrn, header), getSalesAgentEmail(ordtrn));
					LOGGER.info("Order Detail mail has been delivered to sales agent");
				}
				if (OrderEmailEnableForCustomer.equalsIgnoreCase("YES") && customer==true) {
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, ordtrn.getOrdTranSum().getIdOrd(),MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, emailOrderSubject, constructHtmlBodyForCustomer(ordtrn, header),
							header.getCtEmlId());
					LOGGER.info("Order Detail mail has been delivered to Customer");
				}
				if (OrderEmailEnableForDeptHeadr.equalsIgnoreCase("YES") && departmentHead==true) {
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, ordtrn.getOrdTranSum().getIdOrd(),MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, emailOrderSubject,
							constructHtmlBodyForSalesAgentAndDeptHead(ordtrn, header), deptHeadEmails);
					LOGGER.info("Order Detail mail has been delivered to Department Heads");
				}
				if (OrderEmailEnableForDataEntryOptr.equalsIgnoreCase("YES") && DataEntryOptr==true) {
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.SALE, ordtrn.getOrdTranSum().getIdOrd(),MailBeanRemote.MailRecipientTypeEnum.DATA_ENTRY_OPERATOR, emailOrderSubject,
							constructHtmlBodyForSalesAgentAndDeptHead(ordtrn, header), dataEntryOprtrEmail);
					LOGGER.info("Order Detail mail has been delivered to DataEntry Operator");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				result = "failure";
			}
			return result;
		}
		
		//method to send  email for Return order (manually) @sharanya
		public String sendReturnOrderEmail() {

			//for book order
			String OrderEmailEnableForCustomer;
			String OrderEmailEnableForSalesAgent;
			String OrderEmailEnableForDeptHeadr;
			String OrderEmailEnableForDataEntryOptr;
			String LoginEmployee;
	
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
				search.setInvoiceNumberOrOrderNumber(ordrId.trim());
				OrderTranHeader[] orders1 = trans.getTransactionsInvoices(search);
				ordtrn = orders1[0];
				ClaimTranHeader claim_tran_header = trans.getClaimTranHeader(ordtrn.getAcceptClaimId(), null);
				CustomerHeader header = ordtrn.getCustomer().getCustomerHeader();
				String[] deptHeadEmails = getDepartmentHeadEmail(ordtrn);
				LoginEmployee=getEmployee().getEmail();
				emailOrderSubject = "Order Details For Your Order ID " + ordtrn.getOrdTranSum().getIdOrd();
				
				//getting parameters values for sending mails
				OrderEmailEnableForCustomer = parameterBeanRemote.fetchXMLParameterValues().getBookOrderEnableSendingMailToCustomer();
				OrderEmailEnableForSalesAgent = parameterBeanRemote.fetchXMLParameterValues().getBookOrderEnableSendingMailToSalesAgent();
				OrderEmailEnableForDeptHeadr = parameterBeanRemote.fetchXMLParameterValues().getBookOrderEnableSendingMailToDeptHead();
				OrderEmailEnableForDataEntryOptr = parameterBeanRemote.fetchXMLParameterValues().getBookOrderEnableSendingMailToDataEntryOperator();
				
				if(splitcustomMail!=null && !splitcustomMail[0].isEmpty()){
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.RETURN, ordtrn.getOrdTranSum().getIdOrd(),
							MailBeanRemote.MailRecipientTypeEnum.CUSTOM, emailOrderSubject,constructHtmlBodyForReturnOrder(ordtrn, header,claim_tran_header),splitcustomMail );
					LOGGER.info("Return Order mail has been sent successfully");
				} 
				
				if(LoginEmployee!=null && loginEmp==true){
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.RETURN, ordtrn.getOrdTranSum().getIdOrd(),
						 null, emailOrderSubject,constructHtmlBodyForReturnOrder(ordtrn, header,claim_tran_header),LoginEmployee );
					LOGGER.info("Return Order mail has been delivered to Login Employee");
				}
				if (OrderEmailEnableForSalesAgent.equalsIgnoreCase("YES") && salesAgent==true) {
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.RETURN, ordtrn.getOrdTranSum().getIdOrd(),MailBeanRemote.MailRecipientTypeEnum.SALES_AGENT, emailOrderSubject,
							constructHtmlBodyForReturnOrder(ordtrn, header,claim_tran_header), getSalesAgentEmail(ordtrn));
					LOGGER.info("Return Order mail has been delivered to sales agent");
				}
				if (OrderEmailEnableForCustomer.equalsIgnoreCase("YES") && customer==true) {
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.RETURN, ordtrn.getOrdTranSum().getIdOrd(),MailBeanRemote.MailRecipientTypeEnum.CUSTOMER, emailOrderSubject, constructHtmlBodyForReturnOrder(ordtrn, header,claim_tran_header),
							header.getCtEmlId());
					LOGGER.info("Return Order mail has been delivered to Customer");
				}
				if (OrderEmailEnableForDeptHeadr.equalsIgnoreCase("YES") && departmentHead==true) {
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.RETURN, ordtrn.getOrdTranSum().getIdOrd(),MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD, emailOrderSubject,
							constructHtmlBodyForReturnOrder(ordtrn, header,claim_tran_header), deptHeadEmails);
					LOGGER.info("Return Order mail has been delivered to Department Heads");
				}
				if (OrderEmailEnableForDataEntryOptr.equalsIgnoreCase("YES") && DataEntryOptr==true) {
					mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.RETURN, ordtrn.getOrdTranSum().getIdOrd(),MailBeanRemote.MailRecipientTypeEnum.DATA_ENTRY_OPERATOR, emailOrderSubject,
							constructHtmlBodyForReturnOrder(ordtrn, header,claim_tran_header), ordtrn.getIdOpr());
					LOGGER.info("Return Order mail has been delivered to DataEntry Operator");
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
	public String getRequiredDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfNew = new SimpleDateFormat(getTextCustom("format.date"));
		try {
			date = sdfNew.format(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return date;
	}
	
	
	public String getRequiredDate(Date date) {
		Format sdf = new SimpleDateFormat("dd-MMM-yyyy");
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

	//to get the sales agent mail id
	public String getSalesAgentEmail(OrderTranHeader ordtrn) {
		String orderId = ordtrn.getOrdTranSum().getIdOrd();
		ClaimRemote claimBean = null;
		String salesAgentEmail = null;
		try {
			claimBean = DKartContext.getClaimBean();
			salesAgentEmail = claimBean.getSalesAgentEmailId(orderId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return salesAgentEmail;
		}
		return salesAgentEmail;
	}


	//get department head mailIds
	public String[] getDepartmentHeadEmail(OrderTranHeader ordtrn) {
		String custId = ordtrn.getOrdTranSum().getOrdIdCt();
		ClaimRemote claimBean = null;
		String[] deptHeadEmails = null;
		List<String> emailsList = new ArrayList<String>();
		try {
			claimBean = DKartContext.getClaimBean();
			emailsList = claimBean.getDepartmentHeadEmail(custId);
			deptHeadEmails = new String[emailsList.size()];
			deptHeadEmails = emailsList.toArray(deptHeadEmails);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return deptHeadEmails;
		}
		return deptHeadEmails;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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

	public OrderTranHeader getordtrn() {
		return ordtrn;
	}

	public void setordtrn(OrderTranHeader ordtrn) {
		this.ordtrn = ordtrn;
	}

	/*public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}
	public SessionMap<String, Object> getSessionmap() {
		return sessionMap;
	}

	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap)map;
		
	}*/

	public String getCustommail() {
		return custommail;
	}

	public void setCustommail(String custommail) {
		this.custommail = custommail;
	}

	public String getOrdrId() {
		return ordrId;
	}

	public void setOrdrId(String ordrId) {
		this.ordrId = ordrId;
	}
}
