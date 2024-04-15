package rispl.dkart.services.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.entities.customer.CustomerLimit;
import rispl.dkart.services.entities.customer.segment.RisplDkSegment;
import rispl.dkservices.common.CustomerSearchCriteriaIfc;

@Remote
public interface LookUpCustomerIfc {

	///used to look up a customer using search criteria
	public CustomerIfc[] lookUpCust(CustomerSearchCriteriaIfc criteria);

	public CustomerIfc getCustomerInfo(CustomerIfc customer);
	/*
	 * additional methods like invoice search related to customer comes in here
	 */
	public String getCustomerStoreID(String customerID);
	
	public String getCustomerSegmentID(String customerID);

	public CustomerLimit getCustomerlimits(String customerId);
	
	//update customer priority
	public String updateCustomerPriority(String customerId,String priority);
	
	//update customer segment
	public String updateCustomerSegment(String customerId, String segment);
	
	//fetch segment details
	public List<String> fetchSegmentDetails();
	
	//fetch segment details
	public List<RisplDkSegment> segmentDetails();
	//fetch all division details
	public List<String> fetchAllDivisionDetails();
	
	public boolean saveNewSegment(String segmentID,String division,String store);
	
	public String fetchDivisionName(String divisionID);

	public String getCustomerStoreName(String storeID);
	
	public Map<String,String> fetchAllStoreDetails();
	
	public List<List<String>> getAllCust();
	
	public ArrayList<SegmentStoreMap> showSegmentStoreMapping();
}
