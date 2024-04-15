package com.rispl.sds.cancel.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rispl.cancel.order.dao.CancelStatusDAO;
import com.rispl.cancel.order.dao.CancelStatusDAOI;
import com.rispl.cancel.order.dao.RisplDkCancelOrderSearchV;
import com.rsi.dk.claim.approve.dao.factory.DateConvertor;
import com.rsi.dk.wms.service.CustomerOrder;
import com.rsi.dk.wms.service.CustomerOrderService;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import utility.ConfigUtils;
import wms.dk.rsi.customerdetails.CancelLineItemDetails;
import wms.dk.rsi.customerdetails.CustomerOrderCancelDetails;
import wms.dk.rsi.customerdetails.CustomerOrderCancelStatus;

@Stateless(mappedName = "cancelOrderService")
@LocalBean
public class CancelOrderServiceImpl implements CancelOrderServiceIfc {

	private static final Logger LOGGER = LogManager.getLogger(CancelOrderServiceImpl.class);

	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	@Override
	public List<RisplDkCancelOrderSearchV> getCancelOrderByOrderIdTab(String orderNo, String fromDate, String toDate,
			String fromTotal, String toTotal,String customer,String item, EmployeeIfc employee) {
		CancelStatusDAOI cancelOrderDAO = new CancelStatusDAO(emf);
    	List<RisplDkCancelOrderSearchV> canceledOrders=cancelOrderDAO.fetchCancelOrderByOrderTab(orderNo, fromDate, toDate, fromTotal, toTotal,customer,item, employee);
		return canceledOrders;
	}

	@Override
	public void sendCancelOrderDetailsToWMS() throws Exception {
		try {
			LOGGER.info("sendCancelOrderDetailsToWMS Method Started Executing..");
			postCancelApproveDetails();
			LOGGER.info("sendCancelOrderDetailsToWMS Method Completed Successfully");
		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	public void postCancelApproveDetails() throws Exception {
		LOGGER.info("postCancelApproveDetails Started Executing..");
		LOGGER.info("Calling webservice Method To Post Cancel Details");
		CustomerOrderService orderService = null;
		CustomerOrder port = null;
		List<CustomerOrderCancelDetails> cancelDetailList = new ArrayList<CustomerOrderCancelDetails>();
		List<CustomerOrderCancelStatus> cancelStatusList = null;
		boolean flag = false;
		try {
			orderService = new CustomerOrderService(ConfigUtils.getInstance().getCustomerOrderServiceUrl());
			port = orderService.getCustomerOrderPort();
			flag = true;
		} catch (Exception e) {
			LOGGER.error("Unable To Access The Web Service " + e);
			flag = false;
		}

		CancelStatusDAOI cancelOrder = new CancelStatusDAO(emf);

		if (flag) {
			List<OrderTranHeader> cancelOrderHeaderList = new ArrayList<OrderTranHeader>();
			try {
				cancelOrderHeaderList = cancelOrder.fetchCancelOrderHeaderDetails();
				if (cancelOrderHeaderList.size() > 0) {
					for (OrderTranHeader header : cancelOrderHeaderList) {
						OrderTranSum orderTranSum = header.getOrdTranSum();
						CustomerOrderCancelDetails cancelDetails = new CustomerOrderCancelDetails();
						//cancelDetails.setCustomerOrderNo(orderTranSum.getIdOrd());
						if(orderTranSum.getOrigOrderId()!=null){
						   cancelDetails.setCustomerOrderNo(orderTranSum.getOrigOrderId());
						}else{
						   cancelDetails.setCustomerOrderNo(orderTranSum.getIdOrd());
						}
						Date cancelDate = DateConvertor.getDate(orderTranSum.getId().getDcDyOrd());
						XMLGregorianCalendar cancelXMLGreogDate = DateConvertor.asXMLGregorianCalendar(cancelDate);
						cancelDetails.setCancelationDate(cancelXMLGreogDate);
						List<OrderTranLineItem> canceOrderlLineItem = header.getOrdTranLineItems();
						
						LOGGER.debug("Grouping Line Items. Total Lines before grouping = " + canceOrderlLineItem.size());
						Map<String, OrderTranLineItem> itmIdOrdLineObjs = new HashMap<String, OrderTranLineItem>();
						for (OrderTranLineItem lineItem : canceOrderlLineItem) {
							if (itmIdOrdLineObjs.containsKey(lineItem.getItemId())) {
								OrderTranLineItem tempLine = itmIdOrdLineObjs.get(lineItem.getItemId());
								tempLine.setLineQnt(tempLine.getLineQnt().add(lineItem.getLineQnt()));
								itmIdOrdLineObjs.put(lineItem.getItemId(), tempLine);
							} else {
								itmIdOrdLineObjs.put(lineItem.getItemId(), lineItem);
							}
						}
						LOGGER.debug("Grouping Line Items. Total Lines after grouping = " + itmIdOrdLineObjs.size());
						Collection<OrderTranLineItem> lineItems = itmIdOrdLineObjs.values();
						
						for (OrderTranLineItem lineItem : lineItems) {
							boolean itemTypeFlag = false;
							try {
								if (lineItem.getItmTy().equals(new BigDecimal(1))) {
									itemTypeFlag = true;
								}
							} catch (NullPointerException e) {
								itemTypeFlag = true;
							}
							if (itemTypeFlag) {
								CancelLineItemDetails cancelLineItem = new CancelLineItemDetails();
								//cancelLineItem.setCustomerOrderNo(orderTranSum.getIdOrd());
								if(orderTranSum.getOrigOrderId()!=null){
							    cancelLineItem.setCustomerOrderNo(orderTranSum.getOrigOrderId());
							    }else{
							     cancelLineItem.setCustomerOrderNo(orderTranSum.getIdOrd());
							    }
								cancelLineItem.setItmID(lineItem.getItemId());
								cancelLineItem.setCancelQty(lineItem.getLineQnt());
								cancelLineItem.setDestinationID(lineItem.getId().getRtStrId());
								cancelDetails.getLineDetails().add(cancelLineItem);
							}

						}
						cancelDetailList.add(cancelDetails);

					}
				} else {
					LOGGER.warn("There Are No Cancel Order Available In The System");
				}
			} catch (Exception e) {
				LOGGER.error("Unable To Fetch The Cancel Order Details From The Database " + e);
				flag = false;
			}
			cancelStatusList = new ArrayList<CustomerOrderCancelStatus>();
			try {
				if (cancelDetailList.size() > 0) {
					cancelStatusList = port.cancelCustomerOrder(cancelDetailList);
				}
			} catch (Exception e) {
				LOGGER.error("Unable To Cancel The Order In RWMS :" + e);
			}

			if (cancelStatusList.size() > 0) {
				try {
					cancelOrder.updateProcessedCancelReqToWms(cancelStatusList);
				} catch (Exception e) {
					LOGGER.error("Unable To Update The Status Of A Cancel Order :" + e);
				}
			} else {
				LOGGER.warn("Unable To Get Return Status Of Cancel Order");
			}
		} else {
			LOGGER.error("Web Service Is Down. Please Try After Some Time");
		}
	}

	
}
