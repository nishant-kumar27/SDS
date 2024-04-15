package rispl.services.transaction.save;

import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;
import com.rispl.sds.parameter.service.ParameterException;
//import com.rispl.sds.parameter.service.ParametereConfigurationServiceIfc;
import com.rsi.dk.wms.service.CustomerOrder;
import com.rsi.dk.wms.service.CustomerOrderService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.dkart.services.entities.customer.CustomerSite;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dkart.services.entities.customer.CustomerSitePhone;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkservices.common.DateConvertor;
import rispl.rms.inventory.lookup.RMSInventoryDetailService;
import rispl.services.Customer.CustomerException;
import rispl.services.transaction.find.AbstractOrderTransactionSerivce;
import rispl.services.transaction.find.OrderTransactionException;
import utility.ConfigUtils;
import utility.LowInventoryDetails;
import wms.dk.rsi.customerdetails.CreateHeaderDetails;
import wms.dk.rsi.customerdetails.CreateLineDetails;
import wms.dk.rsi.customerdetails.CustomerOrderCreationDetails;
import wms.dk.rsi.customerdetails.CustomerOrderCreationStatus;

@Stateless(mappedName = "sendTransactionToWMS")
@LocalBean
public class DkartWMSOrderTransactionService extends AbstractOrderTransactionSerivce
		implements DkartWMSOrderTransactionServiceIfc {

	private static final Logger LOGGER = LogManager.getLogger(DkartWMSOrderTransactionService.class);

	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	
	@EJB(mappedName = "sdsparameterService")
	ParameterConfigurationServiceIfc parameterService;

	@PostConstruct
	void init() {
		super.emf = emf;
	}

	@EJB
	MailBeanRemote mailBean;
	
	public static void main(String args[]) {
		try {
			new DkartWMSOrderTransactionService().postTransactions();
		} catch (OrderTransactionException e) {
			//e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}

	public void postTransactions() throws OrderTransactionException {

		//OrderTranHeader[] tranHeaders = lookUpTransactionToProcess();
		List<OrderTranHeader> tranHeaders = lookUpTransactionToProcess();

		///get the transactions and post the values to the wms
		if (tranHeaders != null && tranHeaders.size() > 0) {
			LOGGER.info("Filtering out transactions whose order effective date is older than or equal to today");
			tranHeaders = filterTransactions(tranHeaders);

			LOGGER.info("Calling saveOrderTo_WMS method with " + tranHeaders.size() + " Transaction Details");
			saveOrderTo_WMS(tranHeaders);
		} else {
			LOGGER.info("No Transactions Found");
		}
	}

	//private void filterTransactions(OrderTranHeader[] tranHeaders) {
	private List<OrderTranHeader> filterTransactions(List<OrderTranHeader> tranHeadersList) {
		try {
			//List<OrderTranHeader> tranHeadersList = new ArrayList<>(Arrays.asList(tranHeaders));
			
			Iterator<OrderTranHeader> iter = tranHeadersList.iterator();
			while (iter.hasNext()) {
				OrderTranHeader header = iter.next();
				if (header.getOrdTranSum() != null) {
					Date orderEffDate = header.getOrdTranSum().getOrdEfDate();
					Date todaysDate = new Date();
					if (orderEffDate!=null && orderEffDate.after(todaysDate)) { // Remove orders whose effective date if 
						iter.remove();
					}
				}
			}
			//tranHeaders = tranHeadersList.toArray(new OrderTranHeader[tranHeadersList.size()]);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
		return tranHeadersList;
	}

	//public void saveOrderTo_WMS(OrderTranHeader[] tranHeaders) {
	public void saveOrderTo_WMS(List<OrderTranHeader> tranHeaders) {
		LOGGER.info("Executing saveOrderTo_WMS method");
		CustomerOrderService orderService = null;
		CustomerOrder port = null;
		try {
			orderService = new CustomerOrderService(ConfigUtils.getInstance().getCustomerOrderServiceUrl());
			port = orderService.getCustomerOrderPort();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return;
		}

		List<CustomerOrderCreationDetails> customerOrderDetails = new ArrayList<CustomerOrderCreationDetails>();

		// Moving exception handling inside the loop to handle orders not flowing to WMS due to 
		// order data null exceptions
		for (OrderTranHeader tranHeader : tranHeaders) {
			try {  
				////////////////////create customer order for each transaction we are posting to WMS
				CustomerOrderCreationDetails details = new CustomerOrderCreationDetails();
				CreateHeaderDetails headerDetails = getTransactionHeaderTransformed(tranHeader);

				details.setHeaderDetails(headerDetails);

				List<CreateLineDetails> lineDetails = getTransactionLineItemTransformed(tranHeader);
				for (CreateLineDetails lineDetail : lineDetails) {
					details.getLineDetails().add(lineDetail);
				}
				customerOrderDetails.add(details);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage());
			}
		}

		try {
			if (customerOrderDetails.size() > 0) {
				LOGGER.info("Calling WMS Webservice");
				List<CustomerOrderCreationStatus> statuss = port.createCustomerOrder(customerOrderDetails);

				StringBuilder successList = new StringBuilder();
				int loopedValues = 0;
				//////////////////////////////////////based on the response from the warehouse status set the status in SDS DB 

				LOGGER.info("WMS Webservice Call Returned");
				for (CustomerOrderCreationStatus status : statuss) {
					LOGGER.info(status.getCustomerOrderNo());
					LOGGER.info(status.getOrderMessage());
					LOGGER.info(status.getOrderStatus());
					if (status.getOrderStatus().equalsIgnoreCase("TRANSFER-CREATED")) {///check the order statuses sent from the WMS and update the DB
						loopedValues++;
						////jndi lookup to update the transaction by passing the list
						if (loopedValues == statuss.size()) {
							////dont append the , here 
							successList.append(status.getCustomerOrderNo());
						} else {
							///apppend , here 
							successList.append(status.getCustomerOrderNo() + ",");
						}
					} else {
						LOGGER.error(status.getOrderMessage());
					}
				}
				LOGGER.info("SuccessList = " + successList.toString());
				if (!successList.toString().equalsIgnoreCase("")) {
					////jndi lookup has to be done to pass the orders list
					try {
						updatePostedWMSTransactions(successList);
						
						// Saideep: Notify WMS if low inventory
						checkAndNotifyLessInventory(successList.toString(), customerOrderDetails);
					} catch (OrderTransactionException e) {
						// TODO Auto-generated catch block
						LOGGER.error(e.getMessage());
					}
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
		}

	}
	
	//@Schedule(hour = "*", minute = "*/2")
	/*@SuppressWarnings("unused")
	private void test() {
		List<CustomerOrderCreationDetails> customerOrderCreationDetailsList = new ArrayList<>();

		String[] success = { "98765432101234", "12345678901234","56789012345678" };
		// MOCK 1
		customerOrderCreationDetailsList.add(mock(success[0], "100700477", "100700426", "100451044","100153055"));

		// MOCK 2
		customerOrderCreationDetailsList.add(mock(success[1], "100700451", "100700469", "100100092", "100451044","100153055"));

		// MOCK 3
		customerOrderCreationDetailsList.add(mock("56789012345678", "100100181", "100100631", "100100105","100153055"));

		checkAndNotifyLessInventory(String.join(",", success), customerOrderCreationDetailsList);
	}
	
	CustomerOrderCreationDetails mock(String orderId, String... itemId) {
		CustomerOrderCreationDetails customerOrderCreationDetails = new CustomerOrderCreationDetails();

		CreateHeaderDetails headerDet = new CreateHeaderDetails();
		headerDet.setTranNo(orderId);

		customerOrderCreationDetails.setHeaderDetails(headerDet);

		for (int i = 0; i < itemId.length; i++) {
			CreateLineDetails lineDetails = new CreateLineDetails();
			lineDetails.setItmID(itemId[i]);
			customerOrderCreationDetails.getLineDetails().add(lineDetails);
		}

		return customerOrderCreationDetails;

	}*/
	
	/**
	 * Look for inventory of orders sent to WMS and notify if inventory less than 
	 * LowInventoryNotificationThreshold parameter
	 * @param commaSeparatedSuccessList
	 * @param customerOrderDetails
	 * @author Saideep
	 */
	private void checkAndNotifyLessInventory(String commaSeparatedSuccessList,
			List<CustomerOrderCreationDetails> customerOrderDetails) {
		BigDecimal lowInventoryThreshold = BigDecimal.ZERO;
		try {
			String lowInventoryNotificationString = parameterService.fetchXMLParameterValues().getEnableLowInventoryNotification();
			if(lowInventoryNotificationString.equalsIgnoreCase("NO")) return;
			
			String lowInventoryThresholdString = parameterService.fetchXMLParameterValues().getLowInventoryNotificationThreshold();
			lowInventoryThreshold = BigDecimal.valueOf(Long.valueOf(lowInventoryThresholdString));
		} catch (ParameterException e) {
			LOGGER.error(e);
			return;
		}
		
		if (commaSeparatedSuccessList != null && customerOrderDetails != null && customerOrderDetails.size() > 0) {
			try {
				String[] successOrders = commaSeparatedSuccessList.split(",");
				List<String> successOrdersList = new ArrayList<>(Arrays.asList(successOrders));

				ListIterator<CustomerOrderCreationDetails> listIterator = customerOrderDetails.listIterator();
				while (listIterator.hasNext()) {
					CustomerOrderCreationDetails customerOrder = listIterator.next();
					if (!successOrdersList.contains(customerOrder.getHeaderDetails().getTranNo())) // Filter orders that were sent to WMS
						listIterator.remove();
				}
				
				// Low inventory list to be notified by email
				List<LowInventoryDetails> lowInventoryDetails = new ArrayList<>();
				
				// Get the order inventory
				for (CustomerOrderCreationDetails customerOrder : customerOrderDetails) {
					String orderId = customerOrder.getHeaderDetails().getTranNo();
					
					List<String> itemIdsList = customerOrder.getLineDetails().stream().map(line -> line.getItmID()).collect(Collectors.toList());
					
					RMSInventoryDetailService inventoryService = new RMSInventoryDetailService();
					List<Object[]> inventoryDetailsList = inventoryService.lookupBulkInventory(itemIdsList);

					// Check inventory and remove items which have inventory greater than threshold
					ListIterator<Object[]> inventoryDetailsIter = inventoryDetailsList.listIterator();
					while (inventoryDetailsIter.hasNext()) {
						Object[] inventoryDetails = inventoryDetailsIter.next();
						BigDecimal inventory = (BigDecimal) inventoryDetails[1];
						if (inventory.compareTo(lowInventoryThreshold) > 0) {
							inventoryDetailsIter.remove();
						}
					}
					// Prepare email for items which don't have inventory
					if (inventoryDetailsList.size() > 0) {
						lowInventoryDetails.add(new LowInventoryDetails(orderId, inventoryDetailsList));
					}
				}
				
				if(lowInventoryDetails.size() > 0)
				{
					notifyWarehouseOfLowInventory(lowInventoryDetails);
				}

				//
			} catch (Exception e) {
				LOGGER.error("Exception occured during checking of inventory and notification of low inventory");
				LOGGER.error(e);
			}
		}

	}
	
	private void notifyWarehouseOfLowInventory(List<LowInventoryDetails> lowInventoryDetailsList) {

		try {
			
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
			//cfg.setServletContextForTemplateLoading(servletContext, "freemarker"); //for template loading
			
			cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "freemarker");
			Template template = cfg.getTemplate("email-lowinventory-warehouse.ftl"); //template name
			
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("lowInventoryDetails", lowInventoryDetailsList);

			Writer content = new StringWriter();
			template.process(rootMap, content);
			
			if(content.toString().length() > 0)
			{
				String warehouseNotificationAddress = parameterService.fetchXMLParameterValues().getLowInventoryNotificationEmail();
				mailBean.sendEmailHtml(MailBeanRemote.TransTypeEnum.INVENTORY_LOW, "Multiple", MailBeanRemote.MailRecipientTypeEnum.WAREHOUSE, "Low inventory Notification",
						content.toString(), warehouseNotificationAddress);
			}
		} catch (Exception e) {
			LOGGER.error("Exception occured during creating email for low inventory");
			LOGGER.error(e);
		}

	}

	public CreateHeaderDetails getTransactionHeaderTransformed(OrderTranHeader tranHeader) {

		CreateHeaderDetails headerDetails = new CreateHeaderDetails();
		if (tranHeader.getOrdTranSum() != null) {

			CustomerSite customerSite = null;
			CustomerIfc customer = null;
			try {
				customer = lookUpCustomerInfo(tranHeader.getOrdTranSum().getOrdIdCt(), tranHeader.getIdBtchInvResv());
				customerSite = customer.getCustomerSite().get(0);//////get the customer site from a DB
			} catch (CustomerException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			List<CustomerSitePhone> phone = customerSite.getCustomerSitePhoneList();

			List<CustomerSiteAddress> addresses = customerSite.getCustomerSiteAddressList();

			for (CustomerSiteAddress addresse : addresses) {

				if (addresse.getTyAds() == '0') {//////////////0 as primary address
					headerDetails.setBillAddress1(addresse.getA1Cnct());
					headerDetails.setBillCity(addresse.getCiCnct());////city part of address
					headerDetails.setBillCountry(addresse.getCoCnct());////country part of address
					headerDetails.setBillCountryCode("");
					headerDetails.setBillFirstName(customerSite.getFnCnct());
					headerDetails.setBillLastName(customerSite.getLnCnct());

					if (phone.size() > 0) {
						headerDetails.setBillPhone(phone.get(0).getTlCnct());////set the first number comes as billing number
					}
					headerDetails.setBillPreferedName(customerSite.getNmCnct());
					headerDetails.setBillState(addresse.getStCnct());
					headerDetails.setBillZip(addresse.getPcCnct());
					headerDetails.setBllCompanyName(customerSite.getNmCnct());

				} else if (addresse.getTyAds() == '1') {/////shipping adddress
					//CHIRANJIBEE comments to send customer site details to RWMS
					headerDetails.setShipAddress2(addresse.getA1Cnct());
					headerDetails.setShipAddress3(addresse.getA2Cnct());
					headerDetails.setShipAddress4(addresse.getA3Cnct());
					headerDetails.setShipCity(addresse.getCiCnct());
					headerDetails.setShipCountry(addresse.getCoCnct());

					headerDetails.setShipFirstName(customerSite.getFnCnct());
					headerDetails.setShipLastName(customerSite.getLnCnct());
					if (phone.size() > 2) {
						headerDetails.setShipPhone(phone.get(1).getTlCnct());////set the first number comes as billing number
					} else {
						headerDetails.setShipPhone(phone.get(0).getTlCnct());
					}

					headerDetails.setShipPreferedName(customerSite.getNmCnct());
					headerDetails.setShipState(addresse.getStCnct());
					headerDetails.setShipZip(addresse.getPcCnct());
					headerDetails.setShpCompanyName(customerSite.getNmCnct());
				}

			}

			//chiranjibee comments sending the customer name details to warehouse statrts
			LOGGER.debug("Customer Name Added");
			headerDetails.setBillAddress1(customer.getCustomerHeader().getCtNm());
			headerDetails.setShipAddress1(customer.getCustomerHeader().getCtNm());
			//send the customer priority to warehouse
			try {
				headerDetails.setPriority(new BigDecimal(customer.getCustomerHeader().getPriority()));
			} catch (Exception e) {
				headerDetails.setPriority(new BigDecimal(1));
			}
			LOGGER.debug("Customer info Added Completed");
			//chiranjibee comments sending the customer name details to warehouse ends

			headerDetails.setShipDate(DateConvertor.asXMLGregorianCalendar(new java.util.Date()));
			headerDetails.setCustomerOrderNO(tranHeader.getOrdTranSum().getIdOrd());
			////additionla data
			headerDetails.setDlComments(tranHeader.getCtDvrInfoIns());
			headerDetails.setLpoNumber(tranHeader.getOrdTranSum().getCustLpoNum());
			if (tranHeader.getOrdTranSum().getCustLpoDate() != null) {
				SimpleDateFormat sm = new SimpleDateFormat("dd-MMM-yy");
				String date = sm.format(tranHeader.getOrdTranSum().getCustLpoDate());
				headerDetails.setAdditionalField1(date); // Set LPO date in Additional Field 1 		
			}
			headerDetails.setShipAddressDescriptions(tranHeader.getOrdTranSum().getOrdIdCt());
			//Delivery Type
			//0 Immediate,1 scheduled
			headerDetails.setAdditionalField2(tranHeader.getOrdTranSum().getOrdLvlDvr());
			headerDetails.setAdditionalField3(tranHeader.getOrdTranSum().getOrdLvlDvr());
			/////end 
			headerDetails.setDeliveryCharge(new BigDecimal("0.00"));
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
				String earliestPickDate = format.format(tranHeader.getOrdTranSum().getOrdEfDate());
				headerDetails.setEarliestPickDate(DateConvertor.asXMLGregorianCalendar(format.parse(earliestPickDate))); // Set Order Effective Date in Earliest Pick Date
				Date orderEffDate = tranHeader.getOrdTranSum().getOrdEfDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(orderEffDate);
				//cal.add(Calendar.MONTH, 1);
				//Chiranjibee comments to check the parameter for order expiry in days
				int orderExpiryDays =0;
				try{
					orderExpiryDays = Integer.parseInt(parameterService.fetchXMLParameterValues().getOrderExpiryDays());
				}
				catch(Exception e)
				{
					orderExpiryDays =  30;
				}
				cal.add(Calendar.DATE, orderExpiryDays);
				String lastPickDate = format.format(cal.getTime());
				headerDetails.setLastPickedDate(DateConvertor.asXMLGregorianCalendar(format.parse(lastPickDate))); // Set last pick date to one month from earliest pick date
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			headerDetails.setExtraCharge(new BigDecimal("0.00"));
			///headerDetails.setLastPickedDate(tranHeader.getOrdTranSums().get(0).getCustLpoDate());
			headerDetails.setPackingCharge(new BigDecimal("0.00"));

			headerDetails.setTranNo(tranHeader.getOrdTranSum().getIdOrd());

			/*
			 * headerDetails.setBillAddress1(value);
			 * headerDetails.setBillCity(value);
			 * headerDetails.setBillCountry(value);
			 * headerDetails.setBillCountryCode(value);
			 * headerDetails.setBillFirstName(value);
			 * headerDetails.setBillLastName(value);
			 * headerDetails.setBillPhone(value);
			 * headerDetails.setBillPreferedName(value);
			 * headerDetails.setBillState(value);
			 * headerDetails.setBillZip(value);
			 * headerDetails.setBllCompanyName(value);
			 * headerDetails.setCustomerOrderNO(value);
			 * headerDetails.setDeliveryCharge(value);
			 * headerDetails.setEarliestPickDate(value);
			 * headerDetails.setExtraCharge(value);
			 * headerDetails.setLastPickedDate(value);
			 * headerDetails.setPackingCharge(value);
			 * headerDetails.setShipAddress1(value);
			 * headerDetails.setShipCity(value);
			 * headerDetails.setShipCountry(value);
			 * headerDetails.setShipDate(value);
			 * headerDetails.setShipFirstName(value);
			 * headerDetails.setShipLastName(value);
			 * headerDetails.setShipPhone(value);
			 * headerDetails.setShipPreferedName(value);
			 * headerDetails.setShipState(value);
			 * headerDetails.setShipZip(value);
			 * headerDetails.setShpCompanyName(value);
			 * headerDetails.setTranNo(value);
			 */
		}
		return headerDetails;
	}

	public List<CreateLineDetails> getTransactionLineItemTransformed(OrderTranHeader tranHeader) {
		List<OrderTranLineItem> tranLineItems = tranHeader.getOrdTranLineItems();
		//List<OrderTranLineItem> lineItems=tranHeader.getOrdTranLineItems();
		List<CreateLineDetails> orderLineDetails = new ArrayList<CreateLineDetails>();
		/*
		 * CreateLineDetails lineDetails=null; for(OrderTranLineItem
		 * lineItem:lineItems){ lineDetails=new CreateLineDetails();
		 * lineDetails.setCustomerOrderNo(tranHeader.getOrdTranSums().get(0).
		 * getIdOrd()); lineDetails.setItmID(lineItem.getItemId());
		 * lineDetails.setRequestedUnitQunatity(lineItem.getLineQnt());
		 * lineDetails.setSellingUOM(lineItem.getUomSls());
		 * lineDetails.setTranNo(tranHeader.getOrdTranSums().get(0).getIdOrd());
		 * lineDetails.setDestinationID(tranHeader.getId().getRtStrId());
		 * orderLineDetails.add(lineDetails); }
		 */

		//Added by Srinivas to group line items while posting order to WMS - Start
		LOGGER.debug("Grouping Line Items. Total Lines before grouping = " + tranLineItems.size());
		Map<String, OrderTranLineItem> itmIdOrdLineObjs = new HashMap<String, OrderTranLineItem>();
		for (OrderTranLineItem lineItem : tranLineItems) {
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
			// CHIRANJIBEE comments not to send service items to warehouse
			boolean flag = false;
			try {
				if (lineItem.getItmTy().equals(new BigDecimal(1))) {
					flag = true;
				}
			} catch (NullPointerException e) {
				flag = true;
			}
			if (flag) {

				CreateLineDetails lineDetails = new CreateLineDetails();
				lineDetails.setCustomerOrderNo(tranHeader.getOrdTranSum().getIdOrd());
				lineDetails.setItmID(lineItem.getItemId());
				lineDetails.setRequestedUnitQunatity(lineItem.getLineQnt());
				lineDetails.setSellingUOM(lineItem.getUomSls());
				//chiranjibee commnets to send VPN number to warehouse
				lineDetails.setTranNo(lineItem.getRegistryId());
				lineDetails.setDestinationID(tranHeader.getId().getRtStrId());
				orderLineDetails.add(lineDetails);
			}
		}
		//Added by Srinivas to group line items while posting order to WMS - End

		return orderLineDetails;

	}

}