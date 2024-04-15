package rispl.services.Customer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.retailsols.sds.transaction.ejb.TransactionServiceIfc;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;
import com.rispl.sds.parameter.service.ParameterException;
import com.test.entities.OrderDetailsWithInvoice;

import rispl.db.model.creditmemo.RisplDkArCreditMemo;
import rispl.db.model.employee.RisplDkEmpMerchAssoc;
import rispl.db.model.payment.RisplDkArPaym;
import rispl.db.model.storehierarchy.RisplDkStrRtl;
import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dk.customer.Customer;
import rispl.dk.customer.CustomerIfc;
import rispl.dk.customer.CustomerOrderDetails;
import rispl.dk.customer.CustomerOrderDetailsIfc;
import rispl.dkart.ConstantsEnum;
import rispl.dkart.services.ejb.SegmentStoreMap;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.customer.CustomerLimit;
import rispl.dkart.services.entities.customer.CustomerPaymentTerm;
import rispl.dkart.services.entities.customer.CustomerSite;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;
import rispl.dkart.services.entities.customer.segment.RISPLDKCustomerSegment;
import rispl.dkart.services.entities.customer.segment.RisplDkSegStore;
import rispl.dkart.services.entities.customer.segment.RisplDkSegStorePK;
import rispl.dkart.services.entities.customer.segment.RisplDkSegment;
import rispl.dkservices.common.CustomerSearchCriteriaIfc;
import rispl.services.item.DatabaseSourceIfc;

public class AbstractCustomerService implements DatabaseSourceIfc {

	private static final Logger LOGGER = LogManager.getLogger(AbstractCustomerService.class);
	protected EntityManagerFactory emf;

	protected ParameterConfigurationServiceIfc paramService;
	
	@EJB
	public TransactionServiceIfc transactionService;
	
	private int defaultMaxCustomers = 10;
	
	public CustomerIfc[] newLookUpCustomer(CustomerSearchCriteriaIfc CSearchCriteria) throws CustomerException {

		CustomerIfc[] customer = null;// new Customer[1];
		List<CustomerIfc> consolidatedCustomerList = new ArrayList<>();

		List<CustomerIfc> searchByPhoneList = new ArrayList<>();
		try {
			String customerId = CSearchCriteria.getCustomerId();
			if (customerId != null && !customerId.equalsIgnoreCase("")) {
				LOGGER.info("lookup by customer Id.......");
				CustomerIfc[] customers = lookupCustomerByIdName(CSearchCriteria);
				if (customers != null)
					consolidatedCustomerList.addAll(Arrays.asList(customers));

			} 
			
			if (CSearchCriteria.getTelephoneNumber() != null
					&& !CSearchCriteria.getTelephoneNumber().equalsIgnoreCase("")) {
				LOGGER.info("lookup by telephone/mobile number.......");
				CustomerIfc[] customers = lookUpCustomerByPhone(CSearchCriteria);
				if (customers != null)
					searchByPhoneList.addAll(Arrays.asList(customers));
			}

			//// wild card search also need to be included, tuned query is need
			//// for performance
			customer = consolidatedCustomerList.toArray(new CustomerIfc[consolidatedCustomerList.size()]);
			
		} catch (Exception e) {
			LOGGER.error("lookUpCustomer from AbstarctCustomerService unable to get the customer information"
					+ e.getMessage());
			throw new CustomerException(
					"lookUpCustomer from AbstarctCustomerService unable to get the customer information "
							+ e.getMessage());
		} finally {
			//em.close();
		}
		return customer;
	
		
	}
	
	public CustomerIfc[] lookUpCustomer(CustomerSearchCriteriaIfc CSearchCriteria) throws CustomerException {

		CustomerIfc[] customer = null;// new Customer[1];
		
		List<CustomerIfc> consolidatedCustomerList = new ArrayList<>();
		
		//List<CustomerIfc> searchByIdResultList = new ArrayList<>();
		List<CustomerIfc> searchByNameList = new ArrayList<>();
		List<CustomerIfc> searchByPhoneList = new ArrayList<>();
		try {
			String customerId = CSearchCriteria.getCustomerId();

			if (customerId != null && !customerId.equalsIgnoreCase("")) {
				LOGGER.info("lookup by customer Id.......");
				CustomerIfc[] customers = lookupCustomerById(CSearchCriteria);
				if (customers != null)
					consolidatedCustomerList.addAll(Arrays.asList(customers));

			} 
			if ((CSearchCriteria.getFirstName() != null && !CSearchCriteria.getFirstName().equalsIgnoreCase(""))
					|| (CSearchCriteria.getLastName() != null && !CSearchCriteria.getLastName().equalsIgnoreCase(""))) {
				LOGGER.info("lookup by customer name.......");
				CustomerIfc[] customers = lookUpCustomerByName(CSearchCriteria);
				if (customers != null){
					searchByNameList.addAll(Arrays.asList(customers));
					List<String> existingCustomerList = consolidatedCustomerList.stream().map(cus -> cus.getCustomerHeader().getCustomerHeaderPK().getCustId()).collect(Collectors.toList());
					searchByNameList = searchByNameList.stream()
					.filter(cust -> !existingCustomerList.contains(cust.getCustomerHeader().getCustomerHeaderPK().getCustId()))
					.collect(Collectors.toList());
					
					consolidatedCustomerList.addAll(searchByNameList);
				}

			} 
			if (CSearchCriteria.getTelephoneNumber() != null
					&& !CSearchCriteria.getTelephoneNumber().equalsIgnoreCase("")) {
				LOGGER.info("lookup by telephone/mobile number.......");
				CustomerIfc[] customers = lookUpCustomerByPhone(CSearchCriteria);
				if (customers != null)
					searchByPhoneList.addAll(Arrays.asList(customers));
			}

			//// wild card search also need to be included, tuned query is need
			//// for performance
			customer = consolidatedCustomerList.toArray(new CustomerIfc[consolidatedCustomerList.size()]);
			
		} catch (Exception e) {
			LOGGER.error("lookUpCustomer from AbstarctCustomerService unable to get the customer information"
					+ e.getMessage());
			throw new CustomerException(
					"lookUpCustomer from AbstarctCustomerService unable to get the customer information "
							+ e.getMessage());
		} finally {
			//em.close();
		}
		return customer;
	}

	protected List<String> getMerchIds(CustomerSearchCriteriaIfc custSearchCriteria) {
		if (custSearchCriteria != null && custSearchCriteria.getDivisionIdFilter() != null) {
			ArrayList<String> merchIdList = new ArrayList<>();
			for (Object empMerchAssoc : custSearchCriteria.getDivisionIdFilter()) {
				merchIdList.add(((RisplDkEmpMerchAssoc) empMerchAssoc).getId().getMerchId());
			}
			return merchIdList;
		}
		return null;
	}

	public CustomerIfc[] lookupCustomerByIdName(CustomerSearchCriteriaIfc custSearchCriteria) throws NoResultException {
		Vector<CustomerIfc> cust = new Vector<CustomerIfc>();
		CustomerIfc[] foundCustomers = new Customer[0];
		EntityManager em = getEntityManager();
		try {
			StringBuffer custId = new StringBuffer();
			if(custSearchCriteria.isWildCardSearch()){
				custId = custId.append("%");
				custId = custId.append(custSearchCriteria.getCustomerId());
				custId = custId.append("%");
			}else{
				custId = custId.append(custSearchCriteria.getCustomerId());
			}
			
			String customerName = "";
			if(custSearchCriteria.getFirstName()!=null){
				customerName = custSearchCriteria.getFirstName();
			}else{
				customerName = custSearchCriteria.getCustomerId();
			}
			
			StringBuffer custName = new StringBuffer();
			if(custSearchCriteria.isWildCardSearch()){
				custName = custName.append("%");
				custName = custName.append(customerName);
				custName = custName.append("%");
			}else{
				custName = custName.append(customerName);
			}
			
			EmployeeIfc emp = custSearchCriteria.getEmployee();
			int maxCustomers = (custSearchCriteria.getMaxCustomers()!=null)? 
					custSearchCriteria.getMaxCustomers().intValue():defaultMaxCustomers;
			TypedQuery<CustomerHeader> qe = null;
			if(emp!=null){
			String roleAccess = emp.getRoleAccess();
			if(roleAccess.contains(ConstantsEnum.EMP_SEARCHCRITERIA_DIVISION.getValue())){
				List<String> empDivisions = emp.getMerchAssoc().stream().map(EmpMerchAssociationIfc::getMerchId)
						.collect(Collectors.toList());
						
						qe = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_EMP_DIV", CustomerHeader.class)
						.setParameter("customerId", custId.toString().toUpperCase())
						.setParameter("custName", custName.toString().toUpperCase())
						.setParameter("divIds", empDivisions).setMaxResults(maxCustomers);
			}
			else if(roleAccess.contains(ConstantsEnum.EMP_SEARCHCRITERIA_LINKED.getValue())){
				qe = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_EMP_LINKD", CustomerHeader.class)
						.setParameter("customerId", custId.toString().toUpperCase())
						.setParameter("custName", custName.toString().toUpperCase())
						.setParameter("empId", emp.getEmployeeId()).setMaxResults(maxCustomers);
			}
			else if(roleAccess.contains(ConstantsEnum.EMP_SEARCHCRITERIA_ALL.getValue())){
				// no need to filter
				qe = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID_NAME", CustomerHeader.class)
						.setParameter("customerId", custId.toString().toUpperCase())
						.setParameter("custName", custName.toString().toUpperCase()).setMaxResults(maxCustomers);
			}
		}else{
			qe = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID_NAME", CustomerHeader.class)
					.setParameter("customerId", custId.toString().toUpperCase())
					.setParameter("custName", custName.toString().toUpperCase()).setMaxResults(maxCustomers);
		}
			
			List<CustomerHeader> customersFound = qe.getResultList();

			// Search by employee search criteria
			//customersFound = filterByEmployeeRoleSearchCriteria(customersFound, custSearchCriteria);
			
			if (customersFound.size() > 0) {

				for (CustomerHeader header : customersFound) {

					CustomerIfc customers = new Customer();

					customers.setCustomerHeader(header);

					//header.getPrcngGrpId().getCustomerHeaderList().size(); ///////////as to fix non instantiated issue
					header.getPrcngGrpId().setCustomerHeaderList(null);

					cust.add(customers);

				}
				foundCustomers = new Customer[cust.size()];

				cust.copyInto(foundCustomers);
			}
		} catch (Exception e) {
			throw new NoResultException(e.getMessage());
		}

		if (foundCustomers.length == 1) {///if only one customer found
			try {
				foundCustomers[0] = lookUpCustomerInfo(foundCustomers[0]);
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				throw new NoResultException(e.getMessage());
			}
		}

		return foundCustomers;
	
	}
	public CustomerIfc[] lookupCustomerById(CustomerSearchCriteriaIfc custSearchCriteria) throws NoResultException {

		Vector<CustomerIfc> cust = new Vector<CustomerIfc>();
		CustomerIfc[] foundCustomers = new Customer[0];
		EntityManager em = getEntityManager();
		try {
			StringBuffer sb = new StringBuffer();
			if(custSearchCriteria.isWildCardSearch()){
				sb = sb.append("%");
				sb = sb.append(custSearchCriteria.getCustomerId());
				sb = sb.append("%");}
			else
				sb = sb.append(custSearchCriteria.getCustomerId());
			int maxCustomers = (custSearchCriteria.getMaxCustomers()!=null)? 
					custSearchCriteria.getMaxCustomers().intValue():defaultMaxCustomers;
			TypedQuery<CustomerHeader> qe = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID_ALL", CustomerHeader.class)
					.setParameter("customerId", sb.toString()).setMaxResults(maxCustomers);
			// Filter by employee division id
			/*if (custSearchCriteria.getDivisionIdFilter() != null) {
				List<String> merchIds = custSearchCriteria.getDivisionIdFilter();
				qe = em.createNamedQuery("CUSTOMER_LOOKUP_BY_CUSTOMERID_AND_DIVISIONID", CustomerHeader.class)
						.setParameter("customerId", sb.toString()).setParameter("merchId", merchIds);
				LOGGER.info("Searching for Customer ID:" + sb.toString() + " in Merch ID:" + merchIds);
			}*/

			List<CustomerHeader> customersFound = qe.getResultList();

			// Search by employee search criteria
			customersFound = filterByEmployeeRoleSearchCriteria(customersFound, custSearchCriteria);
			
			if (customersFound.size() > 0) {

				for (CustomerHeader header : customersFound) {

					CustomerIfc customers = new Customer();

					customers.setCustomerHeader(header);

					//header.getPrcngGrpId().getCustomerHeaderList().size(); ///////////as to fix non instantiated issue
					header.getPrcngGrpId().setCustomerHeaderList(null);

					cust.add(customers);

				}
				foundCustomers = new Customer[cust.size()];

				cust.copyInto(foundCustomers);
			}
		} catch (Exception e) {
			throw new NoResultException(e.getMessage());
		}

		if (foundCustomers.length == 1) {///if only one customer found
			try {
				foundCustomers[0] = lookUpCustomerInfo(foundCustomers[0]);
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				throw new NoResultException(e.getMessage());
			}
		}

		return foundCustomers;
	}

	private List<CustomerHeader> filterByEmployeeRoleSearchCriteriaQuery(List<CustomerHeader> customersFound,
			CustomerSearchCriteriaIfc custSearchCriteria) {
		if(customersFound!=null && customersFound.size()>0 &&
				custSearchCriteria.getEmployee()!=null && custSearchCriteria.getEmployee().getRoleAccess()!=null) 
		{
			EmployeeIfc emp = custSearchCriteria.getEmployee();
			String roleAccess = emp.getRoleAccess();
			if(roleAccess.contains(ConstantsEnum.EMP_SEARCHCRITERIA_DIVISION.getValue())){
				
				Set<String> empDivisions = emp.getMerchAssoc().stream().map(EmpMerchAssociationIfc::getMerchId)
						.collect(Collectors.toSet());

				customersFound = customersFound.stream().filter(c -> empDivisions.contains(c.getDivisionId()))
						.collect(Collectors.toList());
			}
			else if(roleAccess.contains(ConstantsEnum.EMP_SEARCHCRITERIA_LINKED.getValue())){
				customersFound = customersFound.stream().filter(c -> c.getEmId().equalsIgnoreCase(emp.getEmployeeId()))
						.collect(Collectors.toList());
			}
			else if(roleAccess.contains(ConstantsEnum.EMP_SEARCHCRITERIA_ALL.getValue())){
				// no need to filter
			}
		}
		return customersFound;
	}
	
	private List<CustomerHeader> filterByEmployeeRoleSearchCriteria(List<CustomerHeader> customersFound,
			CustomerSearchCriteriaIfc custSearchCriteria) {
		if(customersFound!=null && customersFound.size()>0 &&
				custSearchCriteria.getEmployee()!=null && custSearchCriteria.getEmployee().getRoleAccess()!=null) 
		{
			EmployeeIfc emp = custSearchCriteria.getEmployee();
			String roleAccess = emp.getRoleAccess();
			if(roleAccess.contains(ConstantsEnum.EMP_SEARCHCRITERIA_DIVISION.getValue())){
				
				Set<String> empDivisions = emp.getMerchAssoc().stream().map(EmpMerchAssociationIfc::getMerchId)
						.collect(Collectors.toSet());

				customersFound = customersFound.stream().filter(c -> empDivisions.contains(c.getDivisionId()))
						.collect(Collectors.toList());
			}
			else if(roleAccess.contains(ConstantsEnum.EMP_SEARCHCRITERIA_LINKED.getValue())){
				customersFound = customersFound.stream().filter(c -> c.getEmId().equalsIgnoreCase(emp.getEmployeeId()))
						.collect(Collectors.toList());
			}
			else if(roleAccess.contains(ConstantsEnum.EMP_SEARCHCRITERIA_ALL.getValue())){
				// no need to filter
			}
		}
		return customersFound;
	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public CustomerIfc[] lookUpCustomerByName(CustomerSearchCriteriaIfc custSearchCriteria) throws NoResultException {
		EntityManager em = getEntityManager();
		CustomerHeader custm = null;
		Vector<CustomerIfc> cust = new Vector<CustomerIfc>();
		CustomerIfc[] foundCustomers = new Customer[0];
		try {
			StringBuffer sb = new StringBuffer();
			//if(custSearchCriteria.isWildCardSearch()){
				sb = sb.append("%");
				sb = sb.append(custSearchCriteria.getFirstName());
				sb = sb.append("%");
			//}
			//else
			//	sb = sb.append(custSearchCriteria.getFirstName());
			int maxCustomers = (custSearchCriteria.getMaxCustomers()!=null)? 
					custSearchCriteria.getMaxCustomers().intValue():defaultMaxCustomers;
			TypedQuery<CustomerHeader> qe = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_NAME", CustomerHeader.class)
					.setParameter("customerName", sb.toString().toUpperCase()).setMaxResults(maxCustomers);

			// Filter by employee division id
			if (custSearchCriteria.getDivisionIdFilter() != null) {
				List<String> merchIds = custSearchCriteria.getDivisionIdFilter();
				qe = em.createNamedQuery("CUSTOMER_LOOKUP_BY_CUSTOMERNAME_AND_DIVISIONID", CustomerHeader.class)
						.setParameter("customerName", sb.toString().toUpperCase())
						.setParameter("merchId", merchIds);
				LOGGER.info("Searching for Customer Name:" + sb.toString().toUpperCase() + " in Merch ID:" + merchIds);
			}

			List<CustomerHeader> customersFound = qe.getResultList();
			
			// Search by employee search criteria
			customersFound = filterByEmployeeRoleSearchCriteria(customersFound, custSearchCriteria);
						
			if (customersFound.size() > 0) {

				for (int i = 0; i < customersFound.size(); i++) {

					CustomerIfc customers = new Customer();

					custm = customersFound.get(i);

					//header.get(i).getPrcngGrpId().getCustomerHeaderList().size();///////////as to fix non instantiated issue
					customersFound.get(i).getPrcngGrpId().setCustomerHeaderList(null);

					customers.setCustomerHeader(custm);

					cust.add(customers);
				}

				foundCustomers = new Customer[cust.size()];

				cust.copyInto(foundCustomers);
			}
		} catch (Exception e) {
			throw new NoResultException(e.getMessage());
		}

		//////////// customer=lookUpPaymentTerms(customer);

		/*
		 * if(foundCustomers.length==1){///if only one customer found try {
		 * foundCustomers[0]=lookUpCustomerInfo(foundCustomers[0]); } catch
		 * (CustomerException e) { // TODO Auto-generated catch block throw new
		 * NoResultException(e.getMessage()); } }
		 */
		int j = 1;
		while (j <= foundCustomers.length) {///if only one customer found
			try {
				foundCustomers[j - 1] = lookUpCustomerInfo(foundCustomers[j - 1]);
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				throw new NoResultException(e.getMessage());
			}
			j++;
		}
		return foundCustomers;
	}

	//////// this method still need to be modified
	public CustomerIfc[] lookUpCustomerByPhone(CustomerSearchCriteriaIfc CSearchCriteria) throws NoResultException {
		EntityManager em = getEntityManager();
		StringBuffer sb = new StringBuffer();
		sb = sb.append("%");
		sb = sb.append(CSearchCriteria.getTelephoneNumber());
		sb = sb.append("%");
		CustomerIfc[] foundCustomers = null;
		try {
			Query qe = em.createNamedQuery("CUSTOMER_LOOKUP_BY_PHONENUMBER", CustomerHeader.class)
					.setParameter("customerContact", sb.toString());

			Vector<CustomerIfc> cust = new Vector<CustomerIfc>();

			@SuppressWarnings("unchecked")
			List<CustomerHeader> header = qe.getResultList();

			CustomerHeader custm = null;
			if (header.size() > 0) {
				// custm=header.get(0);
				// cust.setSize(header.size());
				for (int i = 0; i <= header.size(); i++) {
					CustomerIfc customers = new Customer();
					custm = header.get(i);

					//header.get(i).getPrcngGrpId().getCustomerHeaderList().size();///////////as to fix non instantiated issue
					header.get(i).getPrcngGrpId().setCustomerHeaderList(null);

					// customers.setCustomerSite(custm.getRisplDkCustSites());
					customers.setCustomerHeader(custm);
					// customers.setCustomerSiteAddress(custm.getRisplDkCustSiteAdds());
					cust.add(customers);
				}
				foundCustomers = new Customer[cust.size()];

				cust.copyInto(foundCustomers);
			}
		} catch (Exception e) {
			throw new NoResultException(e.getMessage());
		}

		//////////// customer=lookUpPaymentTerms(customer);

		if (foundCustomers.length == 1) {///if only one customer found
			try {
				foundCustomers[0] = lookUpCustomerInfo(foundCustomers[0]);
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				throw new NoResultException(e.getMessage());
			}
		}

		return foundCustomers;
	}

	//process for get oreder status
	public String lookUpOrderStatus(String ordid)
	{
		EntityManager em = getEntityManager();
		String ordsta=null;
		Query query = null;
		String claimQuery = null;
		List<OrderDetailsWithInvoice> OrderDetailsWithInvoiceList=new ArrayList<>();
//		String ordertype="23";
		claimQuery="SELECT odwi FROM OrderDetailsWithInvoice odwi where odwi.id.orderId=:ordid";// AND odwi.ordTy=:ordertype";
		
		query = em.createQuery(claimQuery,OrderDetailsWithInvoice.class).setParameter("ordid", ordid);//.setParameter("ordertype", ordertype);
		try {
			if(query!=null)
			{
				OrderDetailsWithInvoiceList=query.getResultList();
			}else
			{
				LOGGER.info("Query is empty: "+ query);
			}
			
			for(OrderDetailsWithInvoice OrderDetailsWithInvoice:OrderDetailsWithInvoiceList)
			{
				BigDecimal bigordsta=OrderDetailsWithInvoice.getScOrd();
				ordsta=bigordsta.toString();
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ordsta;
	}
	
	////as we dont need all the information at a time keep the customer info in a different method all the sites info
	public CustomerIfc lookUpCustomerInfo(CustomerIfc customer) throws CustomerException{

		EntityManager em = getEntityManager();
		if (customer != null) {
			try {
				Query qe = em.createNamedQuery("CUSTOMER_ADDITIONAL_INFO", CustomerSite.class)
						.setParameter("customerId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

				// krishna: 
				String segId = "";
				segId = lookupCustomerSegmentID(customer.getCustomerHeader().getCustomerHeaderPK().getCustId());
				if (segId.equalsIgnoreCase("")) {
					customer.setCustomerSegmentID("D");
				} else {
					customer.setCustomerSegmentID(segId);
				}
				// Krishna End

				@SuppressWarnings("unchecked")
				List<CustomerSite> customerSite = qe.getResultList();
				customer.setCustomerSite(customerSite);
				
				Query limits = em.createNamedQuery("CUSTOMER_LIMIT_INFO", CustomerLimit.class).setParameter("custId",
						customer.getCustomerHeader().getCustomerHeaderPK().getCustId());
				///as a customer can have only one limit 
				List<CustomerLimit> limit = limits.getResultList();
				if (limit.size() > 0) {
					customer.setCustomerLimits(limit.get(0));
				}
				//Update Customer pending due from customer invoice table
				{
					customer.getCustomerLimits().setPendDue(BigDecimal.ZERO);

					Query invoices = em.createNamedQuery("CUSTOMER_PENDING_DUE_FROM_INVOICES").setParameter(1,
							customer.getCustomerHeader().getCustomerHeaderPK().getCustId());
					Object pendAmount = invoices.getSingleResult();
					if (pendAmount != null && (pendAmount instanceof BigDecimal)) {
						//System.out.println(pendAmount);
						customer.getCustomerLimits().setPendDue((BigDecimal) pendAmount);
					}
				}

				///get the customer payment terms
				Query payments = em.createNamedQuery("CUSTOMER_PAYMENT_TERMS", CustomerPaymentTerm.class)
						.setParameter("custId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

				List<CustomerPaymentTerm> payment = payments.getResultList();
				if (payment.size() > 0) {
					customer.setPaymentTerms(payment.get(0));
				}

				//////////////////////////////////////to show on the customer display screen
				Integer showMaxReslts = new Integer(5);
				Query orderDetails = em.createNamedQuery("CUSTOMER_OPEN_ORDERS").setParameter("1",
						customer.getCustomerHeader().getCustomerHeaderPK().getCustId());
				orderDetails.setMaxResults(showMaxReslts);
				List<String[]> obj = orderDetails.getResultList();
				List<CustomerOrderDetailsIfc> details = new ArrayList<CustomerOrderDetailsIfc>();
				for (Object[] ob : obj) {
					CustomerOrderDetailsIfc orderObj = new CustomerOrderDetails();

					String ordids=(String)ob[0];
					String ordsta=lookUpOrderStatus(ordids);
					int intordsta=Integer.parseInt(ordsta);
					if(intordsta<=3)
					{	
						orderObj.setOrderId((String) ob[0]);
						orderObj.setOrderDate((String) ob[2]);
						orderObj.setOrderTotal(ob[1].toString());
						orderObj.setOrderStatus(ob[3].toString());
						details.add(orderObj);
					}/*else
					{
						LOGGER.info(intordsta+"its more then 3");
					}*/
				}
				customer.setCustomerOrderDetails(details);

				// Add customer totals to Customer Info
				Map customerTotals = getCustomerTotals(customer);
				customer.setCustomerTotals(customerTotals);

				// Add Customer Receipts to Customer Info
				Integer noOfReceipts = new Integer(5);
				try {
					noOfReceipts = new Integer(paramService.fetchXMLParameterValues().getCustomerNoOfReceiptDisplay());
				} catch (NumberFormatException | ParameterException e) {
					LOGGER.error("Error getting CustomerNoOfReceiptDisplay from Parameter service", e);
					LOGGER.info("Setting default value for no. of customer receipts to retreive");
				}
				List<RisplDkArPaym> customerReceipts = getCustomerReceipts(customer, noOfReceipts);
				customer.setCustomerReceipts(customerReceipts);

				// Add Customer Credit Memos to Customer Info
				Integer noOfCreditMemos = new Integer(5);
				try {
					noOfCreditMemos = new Integer(
							paramService.fetchXMLParameterValues().getCustomerNoOfCreditNoteDisplay());
				} catch (NumberFormatException | ParameterException e) {
					LOGGER.error("Error getting CustomerNoOfCreditNoteDisplay from Parameter service", e);
					LOGGER.info("Setting default value for no. of customer credit memos to retreive");
				}
				List<RisplDkArCreditMemo> customerCreditMemos = getCustomerCreditMemos(customer, noOfCreditMemos);
				customer.setCustomerCreditMemos(customerCreditMemos);

			} catch (Exception e) {
				throw new CustomerException(e.getMessage());
			}
		} else {
			customer = null;
		}
		return customer;
	}
	public CustomerIfc lookUpCustomerInfo_Backup(CustomerIfc customer) throws CustomerException {
		EntityManager em = getEntityManager();
		if (customer != null) {
			try {
				Query qe = em.createNamedQuery("CUSTOMER_ADDITIONAL_INFO", CustomerSite.class)
						.setParameter("customerId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

				// krishna: 
				String segId = "";
				segId = lookupCustomerSegmentID(customer.getCustomerHeader().getCustomerHeaderPK().getCustId());
				if (segId.equalsIgnoreCase("")) {
					customer.setCustomerSegmentID("D");
				} else {
					customer.setCustomerSegmentID(segId);
				}
				// Krishna End

				@SuppressWarnings("unchecked")
				List<CustomerSite> customerSite = qe.getResultList();

				CustomerSite custm = null;
				if (customerSite.size() > 0) {
					// custm=header.get(0);
					// cust.setSize(header.size());
					for (int i = 0; i < customerSite.size(); i++) {
						customerSite.get(i).getCustomerSiteAddressList().size();///////////as to fix non instantiated issue
						customerSite.get(i).getCustomerSiteInvoiceList().size();///////////as to fix non instantiated issue
						customerSite.get(i).getCustomerSiteLimitList().size();///////////as to fix non instantiated issue
						customerSite.get(i).getCustomerSitePhoneList().size();///////////as to fix non instantiated issue
						customerSite.get(i).getCustomerSiteStoreList().size();///as to fix non instantiated issue

					}
					customer.setCustomerSite(customerSite);
				}
				Query limits = em.createNamedQuery("CUSTOMER_LIMIT_INFO", CustomerLimit.class).setParameter("custId",
						customer.getCustomerHeader().getCustomerHeaderPK().getCustId());
				///as a customer can have only one limit 
				List<CustomerLimit> limit = limits.getResultList();
				if (limit.size() > 0) {
					customer.setCustomerLimits(limit.get(0));
				}
				//Update Customer pending due from customer invoice table
				{
					customer.getCustomerLimits().setPendDue(BigDecimal.ZERO);

					Query invoices = em.createNamedQuery("CUSTOMER_PENDING_DUE_FROM_INVOICES").setParameter(1,
							customer.getCustomerHeader().getCustomerHeaderPK().getCustId());
					Object pendAmount = invoices.getSingleResult();
					if (pendAmount != null && (pendAmount instanceof BigDecimal)) {
						//System.out.println(pendAmount);
						customer.getCustomerLimits().setPendDue((BigDecimal) pendAmount);
					}
				}

				///get the customer payment terms
				Query payments = em.createNamedQuery("CUSTOMER_PAYMENT_TERMS", CustomerPaymentTerm.class)
						.setParameter("custId", customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

				List<CustomerPaymentTerm> payment = payments.getResultList();
				if (payment.size() > 0) {
					customer.setPaymentTerms(payment.get(0));
				}

				//////////////////////////////////////to show on the customer display screen
				Query orderDetails = em.createNamedQuery("CUSTOMER_OPEN_ORDERS").setParameter("1",
						customer.getCustomerHeader().getCustomerHeaderPK().getCustId());

				List<String[]> obj = orderDetails.getResultList();
				List<CustomerOrderDetailsIfc> details = new ArrayList<CustomerOrderDetailsIfc>();
				for (Object[] ob : obj) {
					CustomerOrderDetailsIfc orderObj = new CustomerOrderDetails();

					String ordids=(String)ob[0];
					String ordsta=lookUpOrderStatus(ordids);
					int intordsta=Integer.parseInt(ordsta);
					if(intordsta<=3)
					{	
						orderObj.setOrderId((String) ob[0]);
						orderObj.setOrderDate((String) ob[2]);
						orderObj.setOrderTotal(ob[1].toString());
						orderObj.setOrderStatus(ob[3].toString());
						details.add(orderObj);
					}/*else
					{
						LOGGER.info(intordsta+"its more then 3");
					}*/
				}
				customer.setCustomerOrderDetails(details);

				// Add customer totals to Customer Info
				Map customerTotals = getCustomerTotals(customer);
				customer.setCustomerTotals(customerTotals);

				// Add Customer Receipts to Customer Info
				Integer noOfReceipts = new Integer(5);
				try {
					noOfReceipts = new Integer(paramService.fetchXMLParameterValues().getCustomerNoOfReceiptDisplay());
				} catch (NumberFormatException | ParameterException e) {
					LOGGER.error("Error getting CustomerNoOfReceiptDisplay from Parameter service", e);
					LOGGER.info("Setting default value for no. of customer receipts to retreive");
				}
				List<RisplDkArPaym> customerReceipts = getCustomerReceipts(customer, noOfReceipts);
				customer.setCustomerReceipts(customerReceipts);

				// Add Customer Credit Memos to Customer Info
				Integer noOfCreditMemos = new Integer(5);
				try {
					noOfCreditMemos = new Integer(
							paramService.fetchXMLParameterValues().getCustomerNoOfCreditNoteDisplay());
				} catch (NumberFormatException | ParameterException e) {
					LOGGER.error("Error getting CustomerNoOfCreditNoteDisplay from Parameter service", e);
					LOGGER.info("Setting default value for no. of customer credit memos to retreive");
				}
				List<RisplDkArCreditMemo> customerCreditMemos = getCustomerCreditMemos(customer, noOfCreditMemos);
				customer.setCustomerCreditMemos(customerCreditMemos);

			} catch (Exception e) {
				throw new CustomerException(e.getMessage());
			}
		} else {
			customer = null;
		}

		return customer;

	}

	private List<RisplDkArCreditMemo> getCustomerCreditMemos(CustomerIfc customer, Integer maxRecords) {
		List<RisplDkArCreditMemo> customerCreditMemos = new ArrayList<>();
		if (customer == null)
			return null;

		String customerID = customer.getCustomerHeader().getCustomerHeaderPK().getCustId();

		EntityManager em = getEntityManager();
		TypedQuery<RisplDkArCreditMemo> query = em.createNamedQuery("CUSTOMER_CREDIT_MEMOS_LATEST", RisplDkArCreditMemo.class);
		if (maxRecords != null)
			query.setMaxResults(maxRecords);
		query.setParameter(1, customerID.toUpperCase());

		customerCreditMemos = query.getResultList();

		return customerCreditMemos;
	}

	public List<RisplDkArPaym> getCustomerReceipts(CustomerIfc customer, Integer maxRecords) {
		List<RisplDkArPaym> customerReceipts = new ArrayList<>();
		if (customer == null)
			return null;

		String customerID = customer.getCustomerHeader().getCustomerHeaderPK().getCustId();

		EntityManager em = getEntityManager();
		TypedQuery<RisplDkArPaym> query = em.createNamedQuery("CUSTOMER_RECEIPTS_LATEST", RisplDkArPaym.class);
		if (maxRecords != null)
			query.setMaxResults(maxRecords);
		query.setParameter(1, customerID.toUpperCase());

		customerReceipts = query.getResultList();

		return customerReceipts;
	}

	public Map getCustomerTotals(CustomerIfc customer) {
		Map<Integer, Object> customerTotals = new HashMap<>();
		if (customer == null)
			return null;
		String customerID = customer.getCustomerHeader().getCustomerHeaderPK().getCustId();

		EntityManager em = getEntityManager();
		// NEW ORDERS TOTALS
		Query query = em.createNamedQuery("CUSTOMER_NEW_ORDERS_TOTALS");
		query.setParameter(1, customerID.toUpperCase());

		Object newOrdersTotObj = query.getSingleResult();
		BigDecimal newOrdersTotals = newOrdersTotObj == null ? BigDecimal.ZERO : (BigDecimal) newOrdersTotObj;
		customerTotals.put(1, newOrdersTotals);

		// OPEN ORDERS TOTALS

		query = em.createNamedQuery("CUSTOMER_OPEN_ORDERS_TOTALS");
		query.setParameter(1, customerID.toUpperCase());
		Object openOrdersTotObj = query.getSingleResult();
		BigDecimal openOrdersTotals = openOrdersTotObj == null ? BigDecimal.ZERO : (BigDecimal) openOrdersTotObj;
		customerTotals.put(2, openOrdersTotals);

		// INPROGRESS ORDERS TOTALS
		query = em.createNamedQuery("CUSTOMER_INPROG_ORDERS_TOTALS");
		query.setParameter(1, customerID.toUpperCase());
		Object inprogOrdersTotObj = query.getSingleResult();
		BigDecimal inprogOrdersTotals = inprogOrdersTotObj == null ? BigDecimal.ZERO : (BigDecimal) inprogOrdersTotObj;
		customerTotals.put(3, inprogOrdersTotals);

		// CURRENT INVOCES TOTALS
		query = em.createNamedQuery("CUSTOMER_CURRENT_INVOCES_TOTALS");
		query.setParameter(1, customerID.toUpperCase());
		Object currentInvTotObj = query.getSingleResult();
		BigDecimal currentInvTotals = currentInvTotObj == null ? BigDecimal.ZERO : (BigDecimal) currentInvTotObj;
		customerTotals.put(4, currentInvTotals);

		// OVERDUE INVOCES TOTALS
		query = em.createNamedQuery("CUSTOMER_OVERDUE_INVOCES_TOTALS");
		query.setParameter(1, customerID.toUpperCase());
		Object overdueInvTotObj = query.getSingleResult();
		BigDecimal overdueInvTotals = overdueInvTotObj == null ? BigDecimal.ZERO : (BigDecimal) overdueInvTotObj;
		customerTotals.put(5, overdueInvTotals);

		return customerTotals;
	}

	/// call this method for the selected customers only not for all the
	/// customers falls under the search criteria
	public CustomerIfc[] lookUpSiteInvoice(CustomerIfc[] customer) {
		EntityManager em = getEntityManager();
		for (int i = 0; i <= customer.length; i++) {
			Query qe = em.createNamedQuery("CUSTOMER_PENDING_INVOICES", CustomerSiteInvoice.class)
					.setParameter("customerId", customer[i].getCustomerHeader().getCustomerHeaderPK().getCustId());

			@SuppressWarnings("unchecked")
			List<CustomerSiteInvoice> siteInvoice = qe.getResultList();

			/// customer[i].setCustomerSiteInvoice(siteInvoice);
		}
		return customer;
	}

	public CustomerSite lookUpSiteAddress(String siteId) throws CustomerException {
		EntityManager em = getEntityManager();
		CustomerSite customerSite = null;
		try {
			Query qe = em.createNamedQuery("CUSTOMER_SITE_INFO", CustomerSite.class).setParameter("siteId",
					new BigInteger(siteId));

			@SuppressWarnings("unchecked")
			List<CustomerSite> customerSites = qe.getResultList();

			if (customerSites.size() > 0) {

				// custm=header.get(0);
				// cust.setSize(header.size());
				/*
				 * initiate only the required fields
				 */

				customerSites.get(0).getCustomerSiteAddressList().size();///////////as to fix non instantiated issue
				customerSites.get(0).getCustomerSitePhoneList().size();///////////as to fix non instantiated issue
			}

		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		}

		return customerSite;
	}

	protected String lookupCustomerStoreID(String customerID) {
		/*String segmentID = lookupCustomerSegmentID(customerID);
		EntityManager em = getEntityManager();
		"SELECT STORE FROM RISPL_DK_CUST_SEGMENT SEG, RISPL_DK_SEG_STORE ST WHERE SEG.SEGMENT_ID = ST.SEGMENT_ID AND SEG.DIVISION_ID = ST.DIVISION_ID AND SEG.CUST_ID = ? "
		Query query = em.createQuery("SELECT r FROM  RisplDkSegStore r WHERE r.segmentId=?1", RisplDkSegStore.class);
		query.setParameter(1, segmentID);
		List<RisplDkSegStore> store = query.getResultList();
		if (store.size() > 0)
			result = store.get(0).getStore();

		return result;*/
		/*EntityManager em = getEntityManager();
		Query query = em.createNativeQuery("SELECT STORE FROM RISPL_DK_CUST_SEGMENT SEG, RISPL_DK_SEG_STORE ST WHERE SEG.SEGMENT_ID = ST.SEGMENT_ID AND SEG.DIVISION_ID = ST.DIVISION_ID AND SEG.CUST_ID = ? ");
		query.setParameter(1, customerID);		
		String storeID = (String) query.getSingleResult();
		return storeID;*/
		
		return transactionService.getStoreId(customerID);

	}

	protected String lookupCustomerSegmentID(String customerID) {
		String result = "";
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT r FROM RISPLDKCustomerSegment r WHERE r.custId =?1",
				RISPLDKCustomerSegment.class);
		query.setParameter(1, customerID);
		List<RISPLDKCustomerSegment> Qresult = query.getResultList();
		if (Qresult.size() > 0) {
			result = Qresult.get(0).getSegmentId();
		}
		return result;
	}

	public CustomerLimit lookUpCustomerlimits(String customerId) {

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

	// update customer priority based on customer id/ customer name
	// Chiranjibee
	public String updateCustPriorityByCustId(String custID, String priority) {
		int i = 0;
		try {
			EntityManager em = getEntityManager();
			em.getTransaction().begin();
			Query updateCustPriority = em.createQuery(
					"UPDATE CustomerHeader a SET a.priority = :priority WHERE a.customerHeaderPK.custId =:custID");
			updateCustPriority.setParameter("priority", priority);
			updateCustPriority.setParameter("custID", custID);
			i = updateCustPriority.executeUpdate();
			em.getTransaction().commit();
			if (i >= 1) {
				LOGGER.info("Customer Priority Has Been Update Successfully. Customer ID =>" + custID);
				return "SUCCESS";
			} else {
				LOGGER.info("Unable To Update Customer Priority. Customer ID =>" + custID);
				return "FAILURE";
			}
		} catch (Exception e) {
			LOGGER.info("Unable To Update Customer Priority. Customer ID =>" + custID);
			return "FAILURE";
		}

	}
	
	// update customer Segment based on customer id/ customer name
		// Chiranjibee
		public String updateCustSegmentByCustId(String custID, String segment) {
			int i = 0;
			try {
				EntityManager em = getEntityManager();
				em.getTransaction().begin();
				Query updateCustSegment = em.createQuery(
						"UPDATE RISPLDKCustomerSegment a SET a.segmentId = :segmentId WHERE a.custId =:custId");
				updateCustSegment.setParameter("segmentId", segment);
				updateCustSegment.setParameter("custId", custID);
				i = updateCustSegment.executeUpdate();
				em.getTransaction().commit();
				if (i >= 1) {
					LOGGER.info("Customer Segment Has Been Update Successfully. Customer ID =>" + custID);
					return "SUCCESS";
				} else {
					LOGGER.info("Unable To Update Customer Segment. Customer ID =>" + custID);
					return "FAILURE";
				}
			} catch (Exception e) {
				LOGGER.info("Unable To Update Customer Segment. Customer ID =>" + custID);
				return "FAILURE";
			}

		}
		
		// fetch customer segment details
		// Chiranjibee
		public List<String> fetchSegmentDescriptions()
		{
			List<String> segmentDetails = new ArrayList<String>();
			try {
				EntityManager em = getEntityManager();
				Query fetchSegmentDetails = em.createQuery(
						"SELECT segment.segmentId FROM RisplDkSegment segment");
				segmentDetails = fetchSegmentDetails.getResultList();
			} catch (Exception e) {
				LOGGER.info("Unable To Fetch Segment Descriptions");
			}
			return segmentDetails;
		}
		
		public List<RisplDkSegment> fetchSegmentsDetails()
		{
			List<RisplDkSegment> segmentDetails = new ArrayList<RisplDkSegment>();
			try {
				EntityManager em = getEntityManager();
				Query fetchSegmentDetails = em.createQuery(
						"SELECT segment FROM RisplDkSegment segment order by segment.segmentId asc");
				segmentDetails = fetchSegmentDetails.getResultList();
			} catch (Exception e) {
				LOGGER.info("Unable To Fetch Segment Details");
			}
			return segmentDetails;
		}
		
		public List<String> fetchDivisionDetails()
		{
			List<String> segmentDetails = new ArrayList<String>();
			try {
				EntityManager em = getEntityManager();
				Query fetchDivisionDetails = em.createQuery(
						"SELECT division.nmMrhrcGp FROM RisplDkMrchGrp division WHERE division.id.idMrhrcGp LIKE :divisionIdList ");
				fetchDivisionDetails.setParameter("divisionIdList", "1:%");
				segmentDetails = fetchDivisionDetails.getResultList();
			} catch (Exception e) {
				LOGGER.info("Unable To Fetch Division Details");
			}
			return segmentDetails;
		}
		
		public String fetchDivisionIDBasedByName(String name)
		{
			String divisionID = "";
			try {
				EntityManager em = getEntityManager();
				Query fetchDivisionID = em.createQuery(
						"SELECT division.id.idMrhrcGp FROM RisplDkMrchGrp division WHERE division.nmMrhrcGp LIKE :divisionName AND division.id.idMrhrcGp LIKE :divisionId");
				fetchDivisionID.setParameter("divisionName", name);
				fetchDivisionID.setParameter("divisionId", "1:%");
				divisionID = (String) fetchDivisionID.getSingleResult();
			} catch (Exception e) {
				LOGGER.info("Unable To Fetch Division Details");
			}
			return divisionID;
		}
		
		public boolean persistSegmentStore(String segmentID,String divisionName,String store)
		{
			boolean flag = false;
			try {
				String division = fetchDivisionIDBasedByName(divisionName);
				EntityManager em = getEntityManager();
				em.getTransaction().begin();
				RisplDkSegStorePK segStorePK = new RisplDkSegStorePK();
				segStorePK.setSegmentId(segmentID);
				segStorePK.setDivisionId(division);
				RisplDkSegStore segStore = new RisplDkSegStore();
				segStore.setId(segStorePK);
				segStore.setStore(store);
				em.merge(segStore);
				em.getTransaction().commit();
				flag = true;
			} catch (Exception e) {
				flag = false;
				LOGGER.error("Unable To Save Data in Segment Store Table"+e);
			}
			return flag;
			
		}
		
		
		public String fetchCustomerDivisionName(String divisionID)
		{
			String divisionName = "";
			try {
				EntityManager entityManager = getEntityManager();
				Query divisionNameQuery = entityManager.createNativeQuery("SELECT NM_MRHRC_GP FROM RISPL_DK_MRCH_GRP WHERE ID_MRHRC_GP = ?");
				divisionNameQuery.setParameter(1, divisionID);
				divisionName = (String) divisionNameQuery.getSingleResult();
			} catch (Exception e) {
				LOGGER.error("Unable To Find Division Name For The Specified Divsionid "+e);
			}
			return divisionName;
			
		}
       // store name has to display in bookorder screen @sharanya
		public Object lookupCustomerStoreName(String storeID) {
			// TODO Auto-generated method stub
			Object storeName = "";
			try {
				EntityManager entityManager = getEntityManager();
				Query storeNameQuery = entityManager.createNamedQuery("CUSTOMER_STORE_NAME",RisplDkStrRtl.class).setParameter("rtStrId", storeID);
				storeName =storeNameQuery.getSingleResult().toString();
			} catch (Exception e) {
				LOGGER.error("Unable To Find Store Name For The Specified Storeid "+e);
			}
			return storeName;
		}
		
		
		public Map<String, String> loadStoreDetails() {
			TreeMap<String, String> sortedMap = new TreeMap<String,String>();
			try{
			EntityManager entityManager = getEntityManager();
			Query storeDetailsQuery = entityManager.createNativeQuery("SELECT DISTINCT RTL.RT_STR_ID,RTL.NM_LOC from RISPL_DK_STR_RTL RTL,RISPL_DK_CSW_SETUP SETUP"+
                                                                      " WHERE UPPER(SUBSTR(RTL.NM_LOC,0,5)) = UPPER(SUBSTR(SETUP.STORE_NAME,0,5)) ORDER BY RTL.RT_STR_ID ASC");
			@SuppressWarnings("unchecked")
			List<Object[]> storeList = storeDetailsQuery.getResultList();
			
			for (Object[] store : storeList) {
				sortedMap.put(store[0].toString(), store[1].toString());
			}
			}
			catch(Exception e) {
				LOGGER.error("Unable To Fetch The Store Detasils From Database =>" + e);
			}
			return sortedMap;
		}
		
		@SuppressWarnings("unchecked")
		public ArrayList<SegmentStoreMap> displaySegmentStoreMappings()
		{
			ArrayList<SegmentStoreMap> sortedMap = new ArrayList<SegmentStoreMap>();
			try{
			EntityManager entityManager = getEntityManager();
			Query segmentStoreMapQuery = entityManager.createNativeQuery("SELECT SEGMENT.SEGMENT_ID,MERCH.NM_MRHRC_GP,STORE.NM_LOC,MERCH.ID_MRHRC_GP,STORE.RT_STR_ID" 
                                                                      +" FROM RISPL_DK_MRCH_GRP MERCH,RISPL_DK_SEG_STORE DIVISION,"
                                                                      +" RISPL_DK_STR_RTL STORE,RISPL_DK_SEGMENT SEGMENT WHERE MERCH.ID_MRHRC_GP = DIVISION.DIVISION_ID"
                                                                      +" AND DIVISION.STORE = STORE.RT_STR_ID AND DIVISION.SEGMENT_ID = SEGMENT.SEGMENT_ID"
                                                                      +" AND MERCH.ID_MRHRC_GP LIKE '1:%'"
                                                                      +" ORDER BY MERCH.ID_MRHRC_GP,SEGMENT.SEGMENT_ID ASC");
			List<Object[]> segmentStoreQueryList = new ArrayList<Object[]>();
			segmentStoreQueryList = segmentStoreMapQuery.getResultList();
			if(!segmentStoreQueryList.isEmpty())
			{
			for (Object[] segmentStoreMap : segmentStoreQueryList) {
				SegmentStoreMap storeMap = new SegmentStoreMap();
				storeMap.setSegment(segmentStoreMap[0].toString());
				storeMap.setDivision(segmentStoreMap[1].toString());
				storeMap.setStore(segmentStoreMap[2].toString());
				storeMap.setStoreId(segmentStoreMap[4].toString());
				sortedMap.add(storeMap);
				}
			}
			}
			catch(Exception e) {
				LOGGER.error("Unable To Fetch The Segment Store Mapping Details From Database =>" + e);
			}
			return sortedMap;
		}
}

