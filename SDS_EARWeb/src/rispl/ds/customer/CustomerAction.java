package rispl.ds.customer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dime.schedular.imports.DKartConstantsIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dk.customer.CustomerIfc;
import rispl.dk.customer.CustomerOrderDetailsIfc;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerSite;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.CustomerSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;
import util.customer.CustomerSearchCriteriaHelper;

public class CustomerAction extends DSAction {

	private static final long serialVersionUID = 1L;

	static Logger LOGGER = LogManager.getLogger(CustomerAction.class);

	private String custInfo;

	private Map<String, String> custSiteAddrss = new HashMap<String,String>();
	private HashMap<String, Object> cust = new HashMap<>();
	private Map<String, String> orderSalesAgents = new HashMap<>();
	private Map<String, String> invoiceAge = new HashMap<>();
	
	private CustomerIfc[] customers;

	private CustomerIfc customer;

	private String custId;
	private OrderTranHeader orderTran;
	private static final char CUSTOMER_ACTIVE_1 = '1', CUSTOMER_ACTIVE_A = 'A';

	private int noOfOpenOrdersToDisplay = 5;
	private int noOfOpenInvoicesToDisplay = 5;
	
	private boolean claim;

	// Saideep: 16May
	private String customerId;
	List<CustomerIfc> customerList = new ArrayList<>();
	boolean wildcardSearch;
	boolean customerActive;
	boolean creditLimitAvailable;
	//boolean overrideAllowed;
	

	private Integer maxCustomers = 0;
	
	public boolean isCustomerActive() {
		return customerActive;
	}

	public void setCustomerActive(boolean customerActive) {
		this.customerActive = customerActive;
	}

	public boolean isCreditLimitAvailable() {
		return creditLimitAvailable;
	}

	public void setCreditLimitAvailable(boolean creditLimitAvailable) {
		this.creditLimitAvailable = creditLimitAvailable;
	}

	public boolean isWildcardSearch() {
		return wildcardSearch;
	}

	public void setWildcardSearch(boolean wildcardSearch) {
		this.wildcardSearch = wildcardSearch;
	}

	public List<CustomerIfc> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerIfc> customerList) {
		this.customerList = customerList;
	}

	/**
	 * Customer Lookup used during customer search
	 * @author Saideep
	 * @return "single", "multiple", "success"
	 *
	 */
	public String customerLookup() {
		LOGGER.info("Customer Info:" + custInfo);
		LOGGER.info("Wildcard Search:" + wildcardSearch);
		if (custInfo == null)
			return INPUT;
		try {
			LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();

			CustomerSearchCriteriaIfc customerSearchCriteria = getCustomerSearchCriteria();
			CustomerIfc[] customers = lookUpCustomer.lookUpCust(customerSearchCriteria);

			if (customers==null || (customers!=null && customers.length<=0)){
				addActionError(getText("message_15"));
				return ERROR;
			}

			LOGGER.debug("Customer Lookup result:" + Arrays.asList(customers));
			if (customers != null && customers.length > 0) {
				Collections.addAll(customerList, customers);
				putInSession(SESSION.CUSTOMERS, customerList);
				if (customers.length == 1) {
					customerId = customers[0].getCustomerHeader().getCustomerHeaderPK().getCustId();
					customer = customers[0];
					return "single";
				} else {
					return "multiple";
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String customerInfo(){

		LOGGER.info("Customer Id:" + customerId);
		if(customerId==null)
			return INPUT;
		try {
			
			List<CustomerIfc> customerList = (List<CustomerIfc>) getFromSession(SESSION.CUSTOMERS);
			for(CustomerIfc custmrs : customerList){
				if(custmrs.getCustomerHeader().getCustomerHeaderPK().getCustId().equalsIgnoreCase(customerId)){
					customer = custmrs;
					break;
				}
			}
			LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();
			if(customer==null){
				CustomerSearchCriteriaIfc customerSearchCriteria = new CustomerSearchCriteria();
				customerSearchCriteria.setCustomerId(customerId);
				customerSearchCriteria.setWildCardSearch(false);
				if(getEmployee().getMerchAssoc()!=null)
					customerSearchCriteria
							.setDivisionIdFilter(CustomerSearchCriteriaHelper.getInstance().getDivisionsFromEmp(getEmployee()));
				
				CustomerIfc[] customers = lookUpCustomer.lookUpCust(customerSearchCriteria);

				if(customers==null ||  
						(customers!=null && customers.length < 1))
				{
					addActionError(getText("message_15"));
					return ERROR;
				}
				
				LOGGER.debug("Customer Lookup result:" + Arrays.asList(customers));
				
				customer = customers[0];
			}
			customer = lookUpCustomer.getCustomerInfo(customer);
			LOGGER.info("customerInfo.Service reply: " + customer);

			if (customer != null) { //TODO move temp date formatting to business logic
				if (customer.getCustomerOrderDetails() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdfNew = new SimpleDateFormat(getText("format.date"));
					for (CustomerOrderDetailsIfc cod : customer.getCustomerOrderDetails()) {
						String orderDate = cod.getOrderDate();
						if (orderDate != null) {
							orderDate = sdfNew.format(sdf.parse(orderDate));
							cod.setOrderDate(orderDate);
						}
					}
				}
				//TODO select only open invoices, change in business logic
				if (customer.getCustomerSite() != null && customer.getCustomerSite().size() > 0) {
					for (CustomerSite custSite : customer.getCustomerSite()) {
						if (custSite.getCustomerSiteInvoiceList() != null
								&& custSite.getCustomerSiteInvoiceList().size() > 0) {
							Iterator<CustomerSiteInvoice> invoicesIter = custSite.getCustomerSiteInvoiceList()
									.iterator();
							while (invoicesIter.hasNext()) {
								CustomerSiteInvoice invoice = invoicesIter.next();
								if (invoice.getInvStatus() == '0')
									invoicesIter.remove();
							}
						}
					}
				}

				if (customer.getCustomerSite() != null && customer.getCustomerSite().size() > 0) {
					for (CustomerSite custSite : customer.getCustomerSite()) {
						if (custSite.getCustomerSiteInvoiceList() != null
								&& custSite.getCustomerSiteInvoiceList().size() > 0) {
							LookUpEmployeeIfc employeeRemote = DKartContext.getLookupEmployee();
							for (CustomerSiteInvoice invoice : custSite.getCustomerSiteInvoiceList()) {
								String orderNum = invoice.getOrderNum();
								// process to start for invoice age b jagadish
									Date invDate=invoice.getArInvDate();
									char invStatus=invoice.getInvStatus();
									String invAge=calculateInvoiceAge(invDate,invStatus);
									Date todayDate=new Date();
									int result = (int)( (todayDate.getTime() - invDate.getTime()) 
							                 / (1000 * 60 * 60 * 24) );
									
									if(result<=10){
								try {
									EmployeeIfc employee = employeeRemote.lookupSalesAgentForOrder(orderNum);

									if (employee != null)
										orderSalesAgents.put(orderNum,employee.getEmployeeName());
										invoiceAge.put(orderNum,invAge);
								} catch (Exception e) {
									LOGGER.error("Exception during lookupSalesAgentForOrder", e);
								}
									}
							}
						}
					}
				}

			}
			calculateMaxCrdtLmtOvrrdAllwd(customer);
			putInSession(SESSION.CUSTOMER, customer);
			/*this.customer = customer;

			// TODO move this code to after customer validation
			orderTran = (OrderTranHeader) getSessionmap().get(SESSION.ORDER_TRANSACTION.toString());
			if (orderTran == null) {
				orderTran = new OrderTranHeader();
			}
			orderTran.setCustomer(customer);

			//Code by hanu to load sales agents to generate claim
			if (claim) {
				if (orderTran != null && orderTran.getOrdTranSum() == null) {
					OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
					orderTran = ordTrn.createNewOrderForClaims(orderTran);
				}

			}

			cust.put("orderTran", orderTran);
			getSessionmap().put(SESSION.ORDER_TRANSACTION.toString(), orderTran);

			System.err.println(customer.getCustomerSegmentID());*/
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			addActionError("Internal error, please try again later."); //TODO cleanup action error when exception occurs 
			return ERROR;
		}
		return SUCCESS;
	
	}
	
	public String customerInfo_Backup() {
		LOGGER.info("Customer Id:" + customerId);
		if(customerId==null)
			return INPUT;
		try {
			LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();

			CustomerSearchCriteriaIfc customerSearchCriteria = new CustomerSearchCriteria();
			customerSearchCriteria.setCustomerId(customerId);
			customerSearchCriteria.setWildCardSearch(false);
			if(getEmployee().getMerchAssoc()!=null)
				customerSearchCriteria
						.setDivisionIdFilter(CustomerSearchCriteriaHelper.getInstance().getDivisionsFromEmp(getEmployee()));
			
			CustomerIfc[] customers = lookUpCustomer.lookUpCust(customerSearchCriteria);

			if(customers==null ||  
					(customers!=null && customers.length < 1))
			{
				addActionError(getText("message_15"));
				return ERROR;
			}
			
			LOGGER.debug("Customer Lookup result:" + Arrays.asList(customers));
			
			customer = customers[0];
			
			customer = lookUpCustomer.getCustomerInfo(customer);

			LOGGER.info("customerInfo.Service reply: " + customer);

			if (customer != null) { //TODO move temp date formatting to business logic
				if (customer.getCustomerOrderDetails() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdfNew = new SimpleDateFormat(getText("format.date"));
					for (CustomerOrderDetailsIfc cod : customer.getCustomerOrderDetails()) {
						String orderDate = cod.getOrderDate();
						if (orderDate != null) {
							orderDate = sdfNew.format(sdf.parse(orderDate));
							cod.setOrderDate(orderDate);
						}
					}
				}
				//TODO select only open invoices, change in business logic
				if (customer.getCustomerSite() != null && customer.getCustomerSite().size() > 0) {
					for (CustomerSite custSite : customer.getCustomerSite()) {
						if (custSite.getCustomerSiteInvoiceList() != null
								&& custSite.getCustomerSiteInvoiceList().size() > 0) {
							Iterator<CustomerSiteInvoice> invoicesIter = custSite.getCustomerSiteInvoiceList()
									.iterator();
							while (invoicesIter.hasNext()) {
								CustomerSiteInvoice invoice = invoicesIter.next();
								if (invoice.getInvStatus() == '0')
									invoicesIter.remove();
							}
						}
					}
				}

				if (customer.getCustomerSite() != null && customer.getCustomerSite().size() > 0) {
					for (CustomerSite custSite : customer.getCustomerSite()) {
						if (custSite.getCustomerSiteInvoiceList() != null
								&& custSite.getCustomerSiteInvoiceList().size() > 0) {
							LookUpEmployeeIfc employeeRemote = DKartContext.getLookupEmployee();
							for (CustomerSiteInvoice invoice : custSite.getCustomerSiteInvoiceList()) {
								String orderNum = invoice.getOrderNum();
								// process to start for invoice age b jagadish
									Date invDate=invoice.getArInvDate();
									char invStatus=invoice.getInvStatus();
									String invAge=calculateInvoiceAge(invDate,invStatus);
								try {
									EmployeeIfc employee = employeeRemote.lookupSalesAgentForOrder(orderNum);

									if (employee != null)
										orderSalesAgents.put(orderNum,employee.getEmployeeName());
										invoiceAge.put(orderNum,invAge);
								} catch (Exception e) {
									LOGGER.error("Exception during lookupSalesAgentForOrder", e);
								}
							}
						}
					}
				}

			}
			calculateMaxCrdtLmtOvrrdAllwd(customer);
			putInSession(SESSION.CUSTOMER, customer);
			/*this.customer = customer;

			// TODO move this code to after customer validation
			orderTran = (OrderTranHeader) getSessionmap().get(SESSION.ORDER_TRANSACTION.toString());
			if (orderTran == null) {
				orderTran = new OrderTranHeader();
			}
			orderTran.setCustomer(customer);

			//Code by hanu to load sales agents to generate claim
			if (claim) {
				if (orderTran != null && orderTran.getOrdTranSum() == null) {
					OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
					orderTran = ordTrn.createNewOrderForClaims(orderTran);
				}

			}

			cust.put("orderTran", orderTran);
			getSessionmap().put(SESSION.ORDER_TRANSACTION.toString(), orderTran);

			System.err.println(customer.getCustomerSegmentID());*/
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			addActionError("Internal error, please try again later."); //TODO cleanup action error when exception occurs 
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	/**
	 * Customer validation before creating new order
	 * @author Saideep
	 * @return "error", "success"
	 *
	 */
	public String customerValidate(){

		if(customerId==null)
			return INPUT;
		// Re-use customer lookup using customer id and wildcard off
		custInfo = customerId;
		//TODO remove testing code
		//customer.getCustomerHeader().setCtStsCd('0');
		//customer.getCustomerLimits().setAvCrdtLimit(BigDecimal.ZERO);
		customer = (CustomerIfc) getFromSession(SESSION.CUSTOMER);
		if(customer==null){
			customerInfo();
		}
		if (customer != null) {
			if (customer.getCustomerHeader().getCtStsCd() == CUSTOMER_ACTIVE_1
					|| customer.getCustomerHeader().getCtStsCd() == CUSTOMER_ACTIVE_A) {
				setCustomerActive(true);
			}
			if (customer.getCustomerLimits().getAvCrdtLimit().compareTo(BigDecimal.ZERO) == 1) {
				setCreditLimitAvailable(true);
			}
		}
		
		if (isCustomerActive() && isCreditLimitAvailable()){
			return SUCCESS;
		}

		if (!isCustomerActive())
			addActionError("New orders can be created only for \"Active\" customers");
		
		if (!isCreditLimitAvailable())
			addActionError("New orders can be created only for customers with enough \"Available Credit Limit\".");

		return ERROR;
	
	}
	public String customerValidate_Backup() {
		if(customerId==null)
			return INPUT;
		// Re-use customer lookup using customer id and wildcard off
		custInfo = customerId;
		customerInfo();
		//TODO remove testing code
		//customer.getCustomerHeader().setCtStsCd('0');
		//customer.getCustomerLimits().setAvCrdtLimit(BigDecimal.ZERO);
		
		if (customer != null) {
			if (customer.getCustomerHeader().getCtStsCd() == CUSTOMER_ACTIVE_1
					|| customer.getCustomerHeader().getCtStsCd() == CUSTOMER_ACTIVE_A) {
				setCustomerActive(true);
			}
			if (customer.getCustomerLimits().getAvCrdtLimit().compareTo(BigDecimal.ZERO) == 1) {
				setCreditLimitAvailable(true);
			}
		}
		
		if (isCustomerActive() && isCreditLimitAvailable()){
			return SUCCESS;
		}

		if (!isCustomerActive())
			addActionError("New orders can be created only for \"Active\" customers");
		
		if (!isCreditLimitAvailable())
			addActionError("New orders can be created only for customers with enough \"Available Credit Limit\".");

		return ERROR;
	}
	
	public String createNewOrder() {
		String validateCustomer = customerValidate();
		
		if(validateCustomer.equalsIgnoreCase(INPUT))
			return validateCustomer;
		
		else if (validateCustomer.equalsIgnoreCase(SUCCESS)) {
			//Hanu code

			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
			if (orderTran == null) {
				orderTran = new OrderTranHeader();
			}
			orderTran.setCustomer(customer);
			//Code by hanu to load sales agents to generate claim
			if (claim) {
				if (orderTran != null && orderTran.getOrdTranSum() == null) {
					try {
						OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
						orderTran = ordTrn.createNewOrderForClaims(orderTran);
						orderTran.setCustSites(custSiteAddrss);
					} catch (Exception e) {
						addActionError("Exception creating new claim");
						return ERROR;
					}
				}

			}

			cust.put("orderTran", orderTran);
			putInSession(SESSION.ORDER_TRANSACTION, orderTran);

			return validateCustomer;
		}
		addActionError("Error occured while creating new order");
		return ERROR;

	}
	
	/**
	 * To do customer lookup at claims
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String customerlookupAtClaims() throws Exception {

		customerlookup();
		if (customers != null && customers.length == 1) {
			this.custId = customers[0].getCustomerHeader().getCustomerHeaderPK().getCustId();
			putInSession(SESSION.ORDER_TRANSACTION, orderTran);
			customerinfo();
			
		}
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		if (( orderTran!=null && orderTran.getOrdTranSum() == null)) {
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			orderTran = ordTrn.createNewOrderForClaims(orderTran);
			orderTran.setCustSites(custSiteAddrss);
			putInSession(SESSION.ORDER_TRANSACTION, orderTran);
			
		}
		
		return SUCCESS;

	}
	
	public String customerlookup() throws Exception {
		System.out.println("customerlookup() custInfo: " + custInfo);
		if(custInfo!=null && !custInfo.equalsIgnoreCase(""))
		{
			custInfo=custInfo.trim();
		}
		LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();

		CustomerSearchCriteriaIfc customerSearchCriteria = getCustomerSearchCriteria();

		customers = lookUpCustomer.lookUpCust(customerSearchCriteria);

		// Mock customer data for testing
		// customers = mockCustomer();

		System.out.println("customerlookup.Service reply: " + customers);
		if (customers != null) {
			cust.put("found", customers.length);
			cust.put("customers", customers);
			putInSession(SESSION.CUSTOMERS, customers);
		}
		return SUCCESS;
	}

	public String customerinfo(){
		System.out.println("customerinfo() custId: " + custId);
	try{
		CustomerIfc[] customers = (CustomerIfc[])getFromSession(SESSION.CUSTOMERS);
		removeFromSession(SESSION.CUSTOMERS);
		if(custId!=null && !custId.equalsIgnoreCase(""))
		{
			custId=custId.trim();
		} 
		CustomerIfc customer = findCustomerById(customers, custId);
		// If customer not found in customers list then go back to customers page
		if (customer == null)
			return ERROR;

		LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();
		customer = lookUpCustomer.getCustomerInfo(customer);

		System.out.println("customerinfo.Service reply: " + customer);
		
		if (customer != null) { //TODO move temp date formatting to business logic
			if (customer.getCustomerOrderDetails() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdfNew = new SimpleDateFormat(getText("format.date"));
				for (CustomerOrderDetailsIfc cod : customer.getCustomerOrderDetails()) {
					String orderDate = cod.getOrderDate();
					if (orderDate != null) {
						orderDate = sdfNew.format(sdf.parse(orderDate));
						cod.setOrderDate(orderDate);
					}
				}
			}
			//TODO select only open invoices, change in business logic
			if (customer.getCustomerSite() != null && customer.getCustomerSite().size() > 0) {
				for (CustomerSite custSite : customer.getCustomerSite()) {
					if (custSite.getCustomerSiteInvoiceList() != null
							&& custSite.getCustomerSiteInvoiceList().size() > 0) {
						Iterator<CustomerSiteInvoice> invoicesIter = custSite.getCustomerSiteInvoiceList().iterator();
						while (invoicesIter.hasNext()) {
							CustomerSiteInvoice invoice = invoicesIter.next();
							if (invoice.getInvStatus() == '0')
								invoicesIter.remove();
						}
					}
				}
			}

			if (customer.getCustomerSite() != null && customer.getCustomerSite().size() > 0) {
				for (CustomerSite custSite : customer.getCustomerSite()) {
					if (custSite.getCustomerSiteInvoiceList() != null
							&& custSite.getCustomerSiteInvoiceList().size() > 0) {
						LookUpEmployeeIfc employeeRemote = DKartContext.getLookupEmployee();
						for (CustomerSiteInvoice invoice : custSite.getCustomerSiteInvoiceList()) {
							String orderNum = invoice.getOrderNum();
							Date invDate=invoice.getArInvDate();
							Date todayDate=new Date();
							int result = (int)( (todayDate.getTime() - invDate.getTime()) 
					                 / (1000 * 60 * 60 * 24) );						
							if(result<=10){
							try {
								EmployeeIfc employee = employeeRemote.lookupSalesAgentForOrder(orderNum);

								if (employee != null)
									orderSalesAgents.put(orderNum, employee.getEmployeeName());
							} catch (Exception e) {
								LOGGER.error("Exception during lookupSalesAgentForOrder", e);
							}
							}
						}
					}
				}
			}
			
			List<CustomerSite> customerSites = customers[0].getCustomerSite();
			for(CustomerSite site : customerSites){
				List<CustomerSiteAddress> customerSiteAddresses = site.getCustomerSiteAddressList();
				for(CustomerSiteAddress addrss : customerSiteAddresses){
					if(addrss.getTyAds()=='1'){
						StringBuffer siteAddrss = new StringBuffer();
						if(addrss.getA1Cnct()!=null){
							siteAddrss.append(addrss.getA1Cnct());
						}
						if(addrss.getA2Cnct()!=null){
							siteAddrss.append(", ");
							siteAddrss.append(addrss.getA2Cnct());
						}
						if(addrss.getCiCnct()!=null){
							siteAddrss.append(", ");
							siteAddrss.append(addrss.getCiCnct());
						}	
						if(addrss.getCoCnct()!=null){
							siteAddrss.append(", ");
							siteAddrss.append(addrss.getCoCnct());
						}
						if(addrss.getPcCnct()!=null){
							siteAddrss.append(", ");
							siteAddrss.append(addrss.getPcCnct());
						}
						custSiteAddrss.put(addrss.getCustomerSiteAddressPK().getCustSiteId().toString(),siteAddrss.toString());	
					}
				}
			}

		}
		putInSession(SESSION.CUSTOMER, customer);
		this.customer = customer;

		// TODO move this code to after customer validation
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		if (orderTran == null) {
			orderTran = new OrderTranHeader();
		}
		orderTran.setCustomer(customer);
		
		//Code by hanu to load sales agents to generate claim
		if(claim)
		{
			if(orderTran!=null && orderTran.getOrdTranSum() == null)
			{
				OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
				orderTran = ordTrn.createNewOrderForClaims(orderTran);
				orderTran.setCustSites(custSiteAddrss);
			}
			
		}
	
		cust.put("orderTran", orderTran);
		putInSession(SESSION.ORDER_TRANSACTION, orderTran);

		System.err.println(customer.getCustomerSegmentID());}
		catch(Exception e){}
		return SUCCESS;
	}


	private String calculateInvoiceAge(Date invcdate, char invcage) {
		String age="";
		if(invcdate!=null&&invcage=='1') //Age should be calculated only for open invoice
		{
			Date todayDate=new Date();
			int intAge = (int)( (todayDate.getTime() - invcdate.getTime()) 
	                 / (1000 * 60 * 60 * 24) );
			age=String.valueOf(intAge)+" days";
		}
		return age;
	}

	private CustomerIfc findCustomerById(CustomerIfc[] customers, String custId) {
		if (customers != null) {
			for (CustomerIfc customer : customers) {
				if (customer.getCustomerHeader().getCustomerHeaderPK().getCustId().equalsIgnoreCase(custId))
					return customer;
			}
		}
		return null;
	}

	private CustomerSearchCriteriaIfc getCustomerSearchCriteria() {
		CustomerSearchCriteriaIfc customerSearchCriteria = new CustomerSearchCriteria();
		customerSearchCriteria.setWildCardSearch(wildcardSearch);
		try {
			//Long.parseLong(custInfo);
			customerSearchCriteria.setCustomerId(custInfo);
			customerSearchCriteria.setFirstName(custInfo);
		} catch (Exception e) {
			//customerSearchCriteria.setFirstName(custInfo);
		}
		// Set division id for searching within employee division
		//if(getEmployee().getMerchAssoc()!=null)
		//	customerSearchCriteria
		//			.setDivisionIdFilter(CustomerSearchCriteriaHelper.getInstance().getDivisionsFromEmp(getEmployee()));
		// filter based on employee search criteria
		customerSearchCriteria.setEmployee(getEmployee());	
		// Limit max customers per search
		customerSearchCriteria.setMaxCustomers(maxCustomers);
		return customerSearchCriteria;
	}

	public String getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(String custInfo) {
		this.custInfo = custInfo;
	}

	public CustomerIfc[] getCustomers() {
		return customers;
	}

	public void setCustomers(CustomerIfc[] customers) {
		this.customers = customers;
	}

	public HashMap<String, Object> getCust() {
		return cust;
	}

	public void setCust(HashMap<String, Object> cust) {
		this.cust = cust;
	}

	public CustomerIfc getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerIfc customer) {
		this.customer = customer;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Map<String, String> getInvoiceAge() {
		return invoiceAge;
	}

	public void setInvoiceAge(Map<String, String> invoiceAge) {
		this.invoiceAge = invoiceAge;
	}

	public Map<String, String> getOrderSalesAgents() {
		return orderSalesAgents;
	}

	public void setOrderSalesAgents(Map<String, String> orderSalesAgents) {
		this.orderSalesAgents = orderSalesAgents;
	}

	public int getNoOfOpenOrdersToDisplay() {
		String noOfOpenOrdersString;
		try {
			noOfOpenOrdersString = DKartContext.getParamterBean().fetchXMLParameterValues()
					.getCustomerNoOfOpenOrderDisplay();
			Integer noOfOpenOrdersInteger = new Integer(noOfOpenOrdersString);
			noOfOpenOrdersToDisplay = noOfOpenOrdersInteger;
		} catch (Exception e) {
			LOGGER.error("Error getting CustomerNoOfOpenOrderDisplay from param serivce", e);
		}

		return noOfOpenOrdersToDisplay;
	}

	public void setNoOfOpenOrdersToDisplay(int noOfOpenOrdersToDisplay) {
		this.noOfOpenOrdersToDisplay = noOfOpenOrdersToDisplay;
	}

	public int getNoOfOpenInvoicesToDisplay() {
		String noOfOpenInvoicesString;
		try {
			noOfOpenInvoicesString = DKartContext.getParamterBean().fetchXMLParameterValues()
					.getCustomerNoOfOpenInvoiceDisplay();
			Integer noOfOpenInvoicesInteger = new Integer(noOfOpenInvoicesString);
			noOfOpenInvoicesToDisplay = noOfOpenInvoicesInteger;
		} catch (Exception e) {
			LOGGER.error("Error getting CustomerNoOfOpenOrderDisplay from param serivce", e);
		}
		return noOfOpenInvoicesToDisplay;
	}

	public void setNoOfOpenInvoicesToDisplay(int noOfOpenInvoicesToDisplay) {
		this.noOfOpenInvoicesToDisplay = noOfOpenInvoicesToDisplay;
	}

	public boolean isClaim() {
		return claim;
	}

	public void setClaim(boolean claim) {
		this.claim = claim;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getMaxCustomers() {
		return maxCustomers;
	}

	public void setMaxCustomers(Integer maxCustomers) {
		this.maxCustomers = maxCustomers;
	}


	public void calculateMaxCrdtLmtOvrrdAllwd(CustomerIfc customer){
		String maxCrdtLmtOvrrdAlwwdPrcnt = "0";
	try{
		if(customer.getCustomerSegmentID().equalsIgnoreCase(DKartConstantsIfc.CUST_SEG_A)){
			maxCrdtLmtOvrrdAlwwdPrcnt = DKartContext.getParamterBean().fetchXMLParameterValues().getInceaseAvailLimitPercenatageForSegmentA();
		}else if(customer.getCustomerSegmentID().equalsIgnoreCase(DKartConstantsIfc.CUST_SEG_B)){
			maxCrdtLmtOvrrdAlwwdPrcnt = DKartContext.getParamterBean().fetchXMLParameterValues().getInceaseAvailLimitPercenatageForSegmentB();
		}else if(customer.getCustomerSegmentID().equalsIgnoreCase(DKartConstantsIfc.CUST_SEG_C)){
			maxCrdtLmtOvrrdAlwwdPrcnt = DKartContext.getParamterBean().fetchXMLParameterValues().getInceaseAvailLimitPercenatageForSegmentC();
		}
	}catch(Exception e){
		LOGGER.error(e);
	}finally{
		BigDecimal incrsPrcntg = (new BigDecimal(maxCrdtLmtOvrrdAlwwdPrcnt)).divide(new BigDecimal(100));
		BigDecimal custCrdtLmt = customer.getCustomerLimits().getCrdtLimit();
		BigDecimal maxOvrrdCrdtLmt = custCrdtLmt.add(custCrdtLmt.multiply(incrsPrcntg));
		if(customer.getCustomerLimits().getAvCrdtLimit().compareTo(customer.getCustomerLimits().getCrdtLimit())==1){
			BigDecimal onAcVal = customer.getCustomerLimits().getAvCrdtLimit().subtract(customer.getCustomerLimits().getCrdtLimit());
			maxOvrrdCrdtLmt = maxOvrrdCrdtLmt.add(onAcVal);
		}
		maxOvrrdCrdtLmt = maxOvrrdCrdtLmt.setScale(0,BigDecimal.ROUND_UP);
		customer.setMaxCrdtLmtOvrrdAllwd(maxOvrrdCrdtLmt);
	}
	}
	
	public Map<String, String> getCustSiteAddrss() {
		return custSiteAddrss;
	}

	public void setCustSiteAddrss(Map<String, String> custSiteAddrss) {
		this.custSiteAddrss = custSiteAddrss;
	}
}
