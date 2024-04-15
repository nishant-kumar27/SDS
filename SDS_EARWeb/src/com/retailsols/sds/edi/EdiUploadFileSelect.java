package com.retailsols.sds.edi;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import rispl.ds.DSAction;

public class EdiUploadFileSelect extends DSAction {

	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = LogManager.getLogger(EdiUploadFileSelect.class);
	private String customerID;
	
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	@Override
	public String execute() throws Exception {
		String status="success";
		try {
			
		} catch (Exception e) {
			LOGGER.error("",e);
			status = "error";
		}
		return status;
	}

}
