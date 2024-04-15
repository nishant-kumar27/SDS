package com.rispl.sds.struts.admin.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.SegmentStoreMap;
import rispl.dkart.services.entities.customer.segment.RisplDkSegment;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.CustomerSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class CustomerConfigAction extends DSAction {

	private static final long serialVersionUID = 1L;
	private String custInfo;
	private CustomerIfc[] customers;
	private String customerName;
	private String customerId;
	private String currentPriority;
	private String classification;
	private String division;
	private String segment;
	private String priority;
	private String selectSegment;
	private List<String> segmentList = new ArrayList<String>();
	private ArrayList<String> priorityList = new ArrayList<String>();

	private List<RisplDkSegment> segmentDetails = new ArrayList<RisplDkSegment>();
	private String newSegmentID;
	private String newStoreId;
	private String newDivisionId;
	private List<String> divisionList = new ArrayList<String>();
	
	List<CustomerIfc> customerList = new ArrayList<>();

	public List<CustomerIfc> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerIfc> customerList) {
		this.customerList = customerList;
	}

	private String newSegmentError;
	private String assignPriorityError;
	private String assignSegmentError;
	
	private Map<String, String> storeDetails = new HashMap<String, String>();
	
	private SegmentStoreMap segStore =  new SegmentStoreMap();

	private ArrayList<SegmentStoreMap> segmentStoreMapList = new ArrayList<SegmentStoreMap>();
	
	private boolean permission;
	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public SegmentStoreMap getSegStore() {
		return segStore;
	}

	public void setSegStore(SegmentStoreMap segStore) {
		this.segStore = segStore;
	}

	public ArrayList<SegmentStoreMap> getSegmentStoreMapList() {
		return segmentStoreMapList;
	}

	public void setSegmentStoreMapList(ArrayList<SegmentStoreMap> segmentStoreMapList) {
		this.segmentStoreMapList = segmentStoreMapList;
	}
	
	public Map<String, String> getStoreDetails() {
		return storeDetails;
	}

	public void setStoreDetails(Map<String, String> storeDetails) {
		this.storeDetails = storeDetails;
	}

	public String getNewSegmentError() {
		return newSegmentError;
	}

	public void setNewSegmentError(String newSegmentError) {
		this.newSegmentError = newSegmentError;
	}

	public String getAssignPriorityError() {
		return assignPriorityError;
	}

	public void setAssignPriorityError(String assignPriorityError) {
		this.assignPriorityError = assignPriorityError;
	}

	public String getAssignSegmentError() {
		return assignSegmentError;
	}

	public void setAssignSegmentError(String assignSegmentError) {
		this.assignSegmentError = assignSegmentError;
	}

	public String getNewStoreId() {
		return newStoreId;
	}

	public void setNewStoreId(String newStoreId) {
		this.newStoreId = newStoreId;
	}

	public String getNewDivisionId() {
		return newDivisionId;
	}

	public void setNewDivisionId(String newDivisionId) {
		this.newDivisionId = newDivisionId;
	}

	public List<String> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(List<String> divisionList) {
		this.divisionList = divisionList;
	}

	public String getNewSegmentID() {
		return newSegmentID;
	}

	public void setNewSegmentID(String newSegmentID) {
		this.newSegmentID = newSegmentID;
	}

	public List<RisplDkSegment> getSegmentDetails() {
		return segmentDetails;
	}

	public void setSegmentDetails(List<RisplDkSegment> segmentDetails) {
		this.segmentDetails = segmentDetails;
	}
	
	public String getSelectSegment() {
		return selectSegment;
	}

	public List<String> getSegmentList() {
		return segmentList;
	}

	public void setSegmentList(List<String> segmentList) {
		this.segmentList = segmentList;
	}

	public void setSelectSegment(String selectSegment) {
		this.selectSegment = selectSegment;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public ArrayList<String> getPriorityList() {
		return priorityList;
	}

	public void setPriorityList(ArrayList<String> priorityList) {
		this.priorityList = priorityList;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public CustomerIfc[] getCustomers() {
		return customers;
	}

	public void setCustomers(CustomerIfc[] customers) {
		this.customers = customers;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCurrentPriority() {
		return currentPriority;
	}

	public void setCurrentPriority(String currentPriority) {
		this.currentPriority = currentPriority;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(String custInfo) {
		this.custInfo = custInfo;
	}

	public String customerlookup() throws Exception {
		
		if(custInfo!=null && !custInfo.equalsIgnoreCase(""))
		{
			custInfo=custInfo.trim();
		}
		
		if (custInfo == null || custInfo.equals("")) {
			assignPriorityError = "TRUE";
			fetchSegmentDetails();
			addActionError("Please Enter The Customer ID/Name");
			return ERROR;
		}

		else {

			try {
				LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();

				CustomerSearchCriteriaIfc customerSearchCriteria = new CustomerSearchCriteria();
				try {
					//Long.parseLong(custInfo);
					customerSearchCriteria.setCustomerId(custInfo);
					customerSearchCriteria.setFirstName(custInfo);
				} catch (Exception e) {
					e.printStackTrace();
				}

				customers = lookUpCustomer.lookUpCust(customerSearchCriteria);
				if (customers != null && customers.length > 0) {
					Collections.addAll(customerList, customers);
				if (customers.length == 1) {
					permission=true;
					CustomerIfc customer = customers[0];
					customerName = customer.getCustomerHeader().getCtNm();
					classification = customer.getCustomerHeader().getPrcngGrpId().getPrcngGrpNme();
					currentPriority = customer.getCustomerHeader().getPriority();
					customerId = customer.getCustomerHeader().getCustomerHeaderPK().getCustId();
					segment = customer.getCustomerSegmentID();
					if (segment.length() >= 2) {
						segment = segment.substring(1);
					}
					try{
						if(customer.getCustomerHeader().getDivisionId() != null){
						division = lookUpCustomer.fetchDivisionName(customer.getCustomerHeader().getDivisionId());
						}
						else{
							division="";
						}
					}
					catch(Exception e)
					{
						division ="";
					}
					
					fetchSegmentDetails();

				} else {
					permission=false;
				} 
				}else {
					permission=false;
					assignPriorityError = "TRUE";
					fetchSegmentDetails();
					addActionError("No Customer Were Found With This Information!");
					return ERROR;
				}

			} catch (Exception e) {
				assignPriorityError = "TRUE";
				fetchSegmentDetails();
				addActionError("No Customer Were Found With This Information!");
				return ERROR;
			}
		}
		return SUCCESS;
	}

	public String execute() {
		fetchSegmentDetails();
		return SUCCESS;
	}

	public String updateCustomerPriorityAndSegment() {
		if (customerId == null || customerId.equals("")) {
			assignPriorityError = "TRUE";
			fetchSegmentDetails();
			addActionError("Please Enter The Customer ID/Name And Press Search Button");
			return ERROR;
		}

		else {
			try {
				custInfo = customerId;
				LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();
				String result = lookUpCustomer.updateCustomerPriority(customerId, priority);
				result = lookUpCustomer.updateCustomerSegment(customerId, selectSegment);
				if (!result.equalsIgnoreCase("SUCCESS")) {
					assignPriorityError = "TRUE";
					fetchSegmentDetails();
					addActionError("Unable to Update Priority/Segment Of a Customer.Please try After Some Time");
				} else {
					customerlookup();
					assignPriorityError = "FALSE";
					addActionMessage("Customer Priority/Segment Has Been Updated Successfully");
				}
			} catch (Exception e) {
				assignPriorityError = "TRUE";
				fetchSegmentDetails();
				addActionError("Unable to Update Priority/Segment Of a Customer.Please try After Some Time");
			}
		}
		return SUCCESS;
	}

	public String fetchSegmentDetails() {
		try {
			LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();
			segmentDetails = lookUpCustomer.segmentDetails();
			segmentList =  lookUpCustomer.fetchSegmentDetails();
			divisionList = lookUpCustomer.fetchAllDivisionDetails();
			storeDetails = lookUpCustomer.fetchAllStoreDetails();
			priorityList.add("1");
			priorityList.add("2");
			priorityList.add("3");
			priorityList.add("4");
			priorityList.add("5");
			segmentStoreMapList =  lookUpCustomer.showSegmentStoreMapping();
		} catch (Exception e) {
			addActionError("Unable to Fetch Segment Details.Please try After Some Time");
		}
		return SUCCESS;
	}
	
	public String assignSegment() {
		boolean flag1 = false;
		if (newSegmentID == null || newSegmentID.equals("")) {
			flag1 = true;
		} else if (newDivisionId == null || newDivisionId.equals("")) {
			flag1 = true;
		} else if (newStoreId == null || newStoreId.equals("")) {
			flag1 = true;
		}
		if (flag1) {
			newSegmentError = "TRUE";
			fetchSegmentDetails();
			addActionError("All Fields Are Mandatory. Please Enter All The Details And Try Again");
			return ERROR;
		} else {
			try {
				LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();
				boolean flag = lookUpCustomer.saveNewSegment(newSegmentID, newDivisionId, newStoreId);
				if (flag) {
					fetchSegmentDetails();
					newSegmentError = "FALSE";
					addActionMessage("Segmengt-Division Mapping Has Been Updated Successfully");
				} else {
					newSegmentError = "TRUE";
					fetchSegmentDetails();
					addActionError("Unable to To Create A New Segment.Please try After Some Time");
				}
			} catch (Exception e) {
				newSegmentError = "TRUE";
				fetchSegmentDetails();
				addActionError("Unable to To Create A New Segment.Please try After Some Time");
			}
		}
		return SUCCESS;
	}

}