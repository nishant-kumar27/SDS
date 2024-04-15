package rispl.dkart.services.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;

import rispl.db.model.customer.CustomerV;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.entities.customer.CustomerLimit;
import rispl.dkart.services.entities.customer.segment.RisplDkSegment;
import rispl.dkservices.common.CustomerSearchCriteriaIfc;
import rispl.services.Customer.AbstractCustomerService;
import rispl.services.Customer.CustomerException;

@Stateless(mappedName="lookupCustomer")
@LocalBean
public class LookUpCustomer extends AbstractCustomerService implements LookUpCustomerIfc{
	private static final Logger LOGGER = LogManager.getLogger(LookUpCustomer.class);
	
	@Inject @SdsEntityManagerFactory
	EntityManagerFactory emf;
	
	@Override
	public ArrayList<SegmentStoreMap> showSegmentStoreMapping() {
		return displaySegmentStoreMappings();
	}

	@PostConstruct
	void init()
	{
		super.emf = emf;
	}
	
	@EJB(mappedName="sdsparameterService")
	void setParameterService(ParameterConfigurationServiceIfc paramService)
	{
		this.paramService = paramService;
	}
	
	public CustomerIfc[] lookUpCust(CustomerSearchCriteriaIfc criteria){
		CustomerIfc[] customers=null;
		
		try {
			LOGGER.info("looked Up for customer using mappedName=lookupCustomer");
			customers=newLookUpCustomer(criteria);
		} catch (CustomerException e) {
			//e.printStackTrace();
			LOGGER.error("Error occured while looking up for the customer "+e.getMessage());
		}
		if(customers!=null)
			LOGGER.info("looked Up customer Executed Successfully, "+customers.length+" customers found");
		else
			LOGGER.info("looked Up customer Executed Successfully, 0 customers found");
		return customers;
	}
	
	public CustomerIfc getCustomerInfo(CustomerIfc customer){
	
		
		try {
			customer=lookUpCustomerInfo(customer);
			LOGGER.info("looked Up for customerinfo using mappedName=lookupCustomer");
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("Error occured while looking up for the customerinfo "+e.getMessage());
		}
		LOGGER.info("looked Up customerinfo Executed Successfully customer found "+customer);
		return customer;
	}
	@Override
	public String getCustomerStoreID(String customerID) {
		// TODO Auto-generated method stub
		return lookupCustomerStoreID(customerID);
	}
	  // store name has to display in bookorder screen @sharanya
	@Override
	public String getCustomerStoreName(String storeID) {
		// TODO Auto-generated method stub
		return (String) lookupCustomerStoreName(storeID);
	}
	@Override
	public String getCustomerSegmentID(String customerID) {
		// TODO Auto-generated method stub
		return lookupCustomerSegmentID(customerID);
	}
	
	@Override
	public CustomerLimit getCustomerlimits(String customerId) {
		return lookUpCustomerlimits(customerId);
	}
	
	
	@Override
	public String updateCustomerPriority(String customerId, String priority) {
		return updateCustPriorityByCustId(customerId, priority);
	}
	
	@Override
	public String updateCustomerSegment(String customerId, String segment) {
		return updateCustSegmentByCustId(customerId, segment);
	}
	
	@Override
	public List<String> fetchSegmentDetails(){
		return fetchSegmentDescriptions();
	}

	@Override
	public List<RisplDkSegment> segmentDetails() {
		return fetchSegmentsDetails();
	}
	
	@Override
	public String fetchDivisionName(String divisionID) {
		return fetchCustomerDivisionName(divisionID);
	}

	@Override
	public List<String> fetchAllDivisionDetails() {
		return fetchDivisionDetails();
	}

	@Override
	public boolean saveNewSegment(String segmentID,String division,String store) {
		 return persistSegmentStore(segmentID, division, store);
	}
	
	@Override
	public Map<String, String> fetchAllStoreDetails() {
		return loadStoreDetails();
	}

	@Override
	public List<List<String>> getAllCust() {
		List<List<String>> mainList = new ArrayList<>();

		EntityManager em = emf.createEntityManager();
		List<CustomerV> custList = em.createNamedQuery("CustomerV.findAll", CustomerV.class).getResultList();
		if (custList != null && custList.size() > 0) {
			for (CustomerV cust : custList) {
				List<String> subList = new ArrayList<>();
				subList.add(cust.getCustomerId());
				subList.add(cust.getCustomerName());
				subList.add(cust.getLinkedEmpId());
				subList.add(cust.getCreditLimit().toString());
				subList.add(cust.getAvailCreditLimit().toString());
				subList.add(cust.getDivision());
				subList.add(cust.getSegment());
				mainList.add(subList);
			}
		} else
			mainList.add(new ArrayList<String>());
		return mainList;
	}
}
