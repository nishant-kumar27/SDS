package com.rispl.sds.cancel.order.service;

import java.util.List;

import javax.ejb.Remote;

import com.rispl.cancel.order.dao.RisplDkCancelOrderSearchV;

import rispl.dk.Employee.EmployeeIfc;


@Remote
public interface CancelOrderServiceIfc {
	
	public void sendCancelOrderDetailsToWMS() throws Exception;
	
	public List<RisplDkCancelOrderSearchV> getCancelOrderByOrderIdTab(String orderNo,String fromDate,String toDate,String fromTotal,String toTotal,String customer,String item,EmployeeIfc employee);
		
	
}
