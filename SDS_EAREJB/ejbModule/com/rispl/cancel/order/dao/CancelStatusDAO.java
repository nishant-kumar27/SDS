package com.rispl.cancel.order.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rsi.dk.claim.approve.dao.factory.DateConvertor;

import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import wms.dk.rsi.customerdetails.CustomerOrderCancelStatus;

public class CancelStatusDAO implements CancelStatusDAOI {
	
	private DateFormat DATETIME_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
	
	private static final Logger LOGGER = LogManager.getLogger(CancelStatusDAO.class);
	EntityManagerFactory emf;

	public CancelStatusDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderTranHeader> fetchCancelOrderHeaderDetails() throws Exception {
		EntityManager entitymanager = null;
		List<OrderTranHeader> cancelHeaderList = new ArrayList<OrderTranHeader>();
		try {
			LOGGER.info("fetchCancelOrderHeaderDetails Method Started Executing");
			entitymanager = getEntityManager();
			if (entitymanager != null) {
				Query query = entitymanager.createQuery(CANCELORDER, OrderTranHeader.class);
				cancelHeaderList = query.getResultList();
			} else {
				LOGGER.error("Please Check The Connection " + "Entity :" + entitymanager);
				throw new Exception("Please Check The Connection Details");
			}
			LOGGER.info("fetchCancelOrderHeaderDetails Method Completed Successfully ");
		} catch (Exception e) {
			LOGGER.error("Unable To Find The Cancel Order Status Avilable In The System " + e);
		}
		return cancelHeaderList;
	}

	@Override
	public boolean updateProcessedCancelReqToWms(List<CustomerOrderCancelStatus> cancelOrderStatusList)
			throws Exception {
		EntityManager entitymanager = null;
		boolean flag = false;
		try {
			if (cancelOrderStatusList.size() > 0) {
				LOGGER.info("updateProcessedCancelReqToWms Method Started Executing");
				entitymanager = getEntityManager();
				if (entitymanager != null) {
					String timestamp = "";
					try {
						timestamp = DateConvertor.getString(new java.util.Date());
					} catch (Exception e) {
						LOGGER.error(e);
						timestamp = "1";
					}

					for (CustomerOrderCancelStatus cancelOrderStatus : cancelOrderStatusList) {
						String cancelOrderNo = cancelOrderStatus.getCustomerOrderNo();
						int i = 0;
						if (cancelOrderStatus.getOrderStatus() != null
								&& cancelOrderStatus.getCustomerOrderNo() != null) {
							if (cancelOrderStatus.getOrderStatus().equalsIgnoreCase("CANCEL-CREATED")) {
								entitymanager.getTransaction().begin();
								try{
								Query updHdrQuery = entitymanager.createNativeQuery(UPDPROCESSEDRECORDS);
								updHdrQuery.setParameter(1, timestamp);
								updHdrQuery.setParameter(2, cancelOrderNo);
									i = updHdrQuery.executeUpdate();
									entitymanager.getTransaction().commit();
									LOGGER.info("Cancel Order Status Updated Scuccessfully - " + cancelOrderNo);
									LOGGER.info(i + "No Of Records Updated In SDS, Which Has Been Send To RWMS");
									flag = true;
								} catch (Exception e1) {
									try {
										entitymanager.getTransaction().rollback();
									} catch (Exception e2) {
										LOGGER.error("Uanble To Rollback the Transaction" + e2);
									}
									flag = false;
									LOGGER.error("Unable To Update The Claim Transaction Details " + e1);
								}
								i = 0;
								entitymanager.getTransaction().begin();
								try{
								Query updtAvCustLmt = entitymanager.createNativeQuery(updateCustomerAvaialbleLimit);
								updtAvCustLmt.setParameter(1, cancelOrderNo);
								updtAvCustLmt.setParameter(2, cancelOrderNo);
									i = updtAvCustLmt.executeUpdate();
									entitymanager.getTransaction().commit();
									LOGGER.info("Customer Available Limit Increased For " + cancelOrderNo + " Order");
									LOGGER.info(i + "No Of Records Updated In SDS, Which Has Been Send To RWMS");
									flag = true;
								} catch (Exception e1) {
									try {
										entitymanager.getTransaction().rollback();
									} catch (Exception e2) {
										LOGGER.error("Uanble To Rollback the Transaction" + e2);
									}
									flag = false;
									LOGGER.error("Unable To Update The Claim Transaction Details " + e1);
								}

							} else {

								LOGGER.warn("Check The Cancel Order Status Message:"
										+ cancelOrderStatus.getCustomerOrderNo() + " - "
										+ cancelOrderStatus.getOrderStatus() + " - "
										+ cancelOrderStatus.getOrderMessage());
							}
						} else {
							LOGGER.warn("Cancel Order Status Null Or Cancel Order Number Is Null:"
									+ cancelOrderStatus.getCustomerOrderNo() + " - "
									+ cancelOrderStatus.getOrderStatus() + " - " + cancelOrderStatus.getOrderMessage());

						}
					}
				} else {
					LOGGER.error("Please Check The Connection " + "Entity :" + entitymanager);
					throw new Exception("Please Check The Connection Details");
				}
			} else {
				LOGGER.warn("The Number Of CancelOrder Status Response Received From Web Service is 0");

			}
		} catch (Exception e) {
			LOGGER.error("Unbale To Update The Cancel Record Status In Database" + e);
		}
		return flag;
	}

	private EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emf.createEntityManager();
	}
	
	public Date convertStringToDate(String string) {
		if (string == null) {
			return null;
		}
		try {
			return DATETIME_FORMAT.parse(string);
		} catch (ParseException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RisplDkCancelOrderSearchV> fetchCancelOrderByOrderTab(String orderNo, String fromDate, String toDate,
			String fromTotal, String toTotal,String customer,String item,EmployeeIfc employee) {
		Query cancelOrderQuery = null;
		EntityManager entitymanager = null;
		List<RisplDkCancelOrderSearchV> cancelOrderList = new ArrayList<RisplDkCancelOrderSearchV>();
		boolean flag = false;
		try{
			entitymanager = getEntityManager();
			flag = true;
		}
		catch(Exception e)
		{
			flag  = false;
		}
		
		if(flag)
		{
		Date fromDateDB = null;
		Date toDateDB = null;
		BigDecimal fromTotalDB = null;
		BigDecimal toTotalDB = null;
		String parsedCustomer = "";
		String parsedItem = "";
		if(orderNo.equals(""))
		{
			orderNo = "%";
		}
		else{
			orderNo= orderNo.trim();
		}
		if(fromDate.equals(""))
		{
			try{
				fromDateDB = (Date) entitymanager.createQuery(fetchCancelOrderMinmumDate).getSingleResult();
			}
			catch(NoResultException e)
			{
				try {
					fromDateDB = DateConvertor.getDate("01-JAN-01");
				} catch (Exception e1) {
				}
			}
		}
		else{
			fromDate = fromDate.trim();
			fromDateDB = convertStringToDate(fromDate);
		}
		if(toDate.equals(""))
		{
			toDateDB = new java.util.Date();
		}
		else{
			toDate = toDate.trim();
			toDateDB = convertStringToDate(toDate);
		}
		if(fromTotal.equals(""))
		{
			fromTotalDB  = new BigDecimal(0);
		}
		else{
			fromTotal = fromTotal.trim();
			fromTotalDB = new BigDecimal(fromTotal);
		}
		if(toTotal.equals(""))
		{
			try{
				toTotalDB = (BigDecimal) entitymanager.createQuery(fetchCancelOrderMaxAmount).getSingleResult();
			}
			catch(NoResultException e)
			{
				try {
					toTotalDB = new BigDecimal("99999999999999999999");
				} catch (Exception e1) {
				}
			}
		}
		else{
			toTotal = toTotal.trim();
			toTotalDB = new BigDecimal(toTotal);
		}
		if(customer.equals(""))
		{
			parsedCustomer = "%";
		}
		else{
			customer = customer.trim();
			customer = customer.toUpperCase();
			/*String[] splitData = customer.split(" ");
			for (String str : splitData) {
				parsedCustomer += str + "%";
			}*/
			parsedCustomer = customer;
		}
		if(item.equals(""))
		{
			parsedItem = "%";
		}
		else{
			item = item.trim();
			item = item.toUpperCase();
			String[] splitData = item.split("---");
			int lastElement = splitData.length - 1;
			parsedItem = "%";
			String itemSearch = splitData[lastElement];	
			
			try{
			Query itemIDQuery = entitymanager.createNativeQuery(fetchItemIDBasedOnItemIdBarcodeDescriptions);
			itemIDQuery.setParameter(1, itemSearch);
			itemIDQuery.setParameter(2, itemSearch);
			itemIDQuery.setParameter(3, itemSearch);
			parsedItem = (String) itemIDQuery.getSingleResult();
			}
			catch(Exception e)
			{
				parsedItem = itemSearch;
			}
		}
		try{
		String searchQuery = "";
		if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent"))
		{
			searchQuery = fetchCancelOrderBasedOnOrderID + " AND cancel.salesAgent = :empID ORDER BY cancel.orderDate DESC";
			cancelOrderQuery = entitymanager.createQuery(searchQuery);
			cancelOrderQuery.setParameter("fromDate", fromDateDB,TemporalType.DATE);
			cancelOrderQuery.setParameter("toDate", toDateDB,TemporalType.DATE);
			cancelOrderQuery.setParameter("orderNo",orderNo);
			cancelOrderQuery.setParameter("fromTotal", fromTotalDB);
			cancelOrderQuery.setParameter("toTotal", toTotalDB);
			cancelOrderQuery.setParameter("empID", employee.getEmployeeId());
			cancelOrderQuery.setParameter("itemid", parsedItem);
			cancelOrderQuery.setParameter("customer", parsedCustomer);
			
		}
		if(employee.getRoleAccess().equalsIgnoreCase("Within Division"))
		{
			List<EmpMerchAssociationIfc> merchandiseList = employee.getMerchAssoc();
			List<String> merchGroupList = new ArrayList<String>();
			for(EmpMerchAssociationIfc merchHieararchy : merchandiseList)
			{
				if(merchHieararchy.getMerchId().startsWith("1:"))
				{
				merchGroupList.add(merchHieararchy.getMerchId().trim());
				}
			}
			searchQuery = fetchCancelOrderBasedOnOrderID + " AND cancel.divisionID in :divisionID ORDER BY cancel.orderDate DESC";
			cancelOrderQuery = entitymanager.createQuery(searchQuery);
			cancelOrderQuery.setParameter("fromDate", fromDateDB,TemporalType.DATE);
			cancelOrderQuery.setParameter("toDate", toDateDB,TemporalType.DATE);
			cancelOrderQuery.setParameter("orderNo",orderNo);
			cancelOrderQuery.setParameter("fromTotal", fromTotalDB);
			cancelOrderQuery.setParameter("toTotal", toTotalDB);
			cancelOrderQuery.setParameter("divisionID", merchGroupList);
			cancelOrderQuery.setParameter("itemid", parsedItem);
			cancelOrderQuery.setParameter("customer", parsedCustomer);
		}
		if(employee.getRoleAccess().equalsIgnoreCase("All"))
		{
			searchQuery = fetchCancelOrderBasedOnOrderID + " ORDER BY cancel.orderDate DESC";
			cancelOrderQuery = entitymanager.createQuery(searchQuery);
			cancelOrderQuery.setParameter("fromDate", fromDateDB,TemporalType.DATE);
			cancelOrderQuery.setParameter("toDate", toDateDB,TemporalType.DATE);
			cancelOrderQuery.setParameter("orderNo",orderNo);
			cancelOrderQuery.setParameter("fromTotal", fromTotalDB);
			cancelOrderQuery.setParameter("toTotal", toTotalDB);
			cancelOrderQuery.setParameter("itemid", parsedItem);
			cancelOrderQuery.setParameter("customer", parsedCustomer);
		}
		cancelOrderList = cancelOrderQuery.getResultList();
		}
		catch(Exception e)
		{
			LOGGER.error("Error Occured While Finding Canceled Order Detais From Database=> "+e);
		}
		}
		return cancelOrderList;
	}
		

}
