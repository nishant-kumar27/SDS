package com.retailsols.sds.edi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;


public class PreProcessUploadHandlerClaim extends DSAction {

	/**
	 * 
	 */
	static Logger LOGGER = LogManager.getLogger(PreProcessUploadHandlerClaim.class);
	 
	private static final long serialVersionUID = 467867523169079112L;
	private File ediFile;
	private String contentType;
	private String ediFileFileName;
	private String customerID;
	private String tempFolder = System.getProperty("java.io.tmpdir");
	private CustomerIfc customer;
	
	

	

	public CustomerIfc getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerIfc customer) {
		this.customer = customer;
	}


	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public File getEdiFile() {
		return ediFile;
	}

	public void setEdiFile(File ediFile) {
		this.ediFile = ediFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getEdiFileFileName() {
		return ediFileFileName;
	}

	public void setEdiFileFileName(String ediFileFileName) {
		this.ediFileFileName = ediFileFileName;
	}

	@Override
	public String execute() throws Exception {
		String status = "success";
		FileInputStream fis = null;

		try {

			if (isFileEmpty(ediFile)) { // check if file content is empty
				addActionError("File Content Empty. Cannot be processed");
				status = ERROR;
			} else {
				fis = new FileInputStream(ediFile);
				int c;
				StringBuffer data = new StringBuffer();
				while ((c = fis.read()) != -1) {
					data = data.append((char) c);
				}
				// get order details
				File file = new File(tempFolder + "/" + ediFileFileName);
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(data.toString().getBytes());
				fos.close();

				String fileData = data.toString().split("\n")[0];
				String customerId = fileData.split("\\,")[0];
				/*if (!customerId.equalsIgnoreCase(customerID)) { // check if customer id is not matched
					// customer id not matched
					addActionError("Customer ID Validation Failed.");
					status = ERROR;
				} else {
*/
					String lineData[] = fileData.split("\\,");
					
				//	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//					Date OrderDate = sdf.parse(lineData[2]);
				//	Date shipDate = sdf.parse(lineData[2].replaceAll("(?:\\n|\\r)", ""));
					/*if(shipDate.before(new java.util.Date())){
						addActionError("Order for the Date less than Current Date cannot be processed");
						return ERROR;
					}*/
					

					/*// now get the customer delivery sites
					LookUpCustomerIfc customer = DKartContext.getLookUpCustomer();
					CustomerSearchCriteria criteria = new CustomerSearchCriteria();
					criteria.setCustomerId(customerID);
					CustomerIfc customer1 = (CustomerIfc)getFromSession(SESSION.CUSTOMER);
 					//CustomerIfc[] custs = customer.lookUpCust(criteria);
					setCustomer(customer1);*/

					/*LookUpEmployeeIfc employeeIfc = DKartContext.getLookupEmployee();
					EmployeeSearchCriteriaIfc empsearchCriteria = new EmployeeSearchCriteria();
					empsearchCriteria.setEmployeeId("%");
					EmployeeIfc[] employees = employeeIfc.lookupSalesAgent(empsearchCriteria);
					LinkedHashMap<String, String> salesAgents = new LinkedHashMap<String, String>();
					for (int i = 0; i < employees.length; i++) {
						salesAgents.put(employees[i].getEmployeeId(), employees[i].getEmployeeName()+" - "+employees[i].getEmployeeId());
					}*/
					/*// as per UAT feedback sorting the salesagents list in alphabetical order @Laxmikanth  
					Map<String, String> sortedSalesAgentsMap = salesAgents.entrySet().stream().sorted(Map.Entry.comparingByValue())
							.collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue,
	                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
					setSalesAgentList(sortedSalesAgentsMap);*/
				//}
			}
		} catch (Exception e) {
			LOGGER.error("",e);
			status = "error";
			addActionError("Fatal Error Occured while Processing the File");
		}
		return status;
	}

	public boolean isFileEmpty(File file) {
		boolean flag = false;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			int b = fis.read();
			if (b == -1) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}
