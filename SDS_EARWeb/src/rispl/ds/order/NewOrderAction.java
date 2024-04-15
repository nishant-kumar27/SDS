package rispl.ds.order;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.persistence.NoResultException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.struts2.json.JSONWriter;

import com.opensymphony.xwork2.Preparable;
import com.rispl.sds.parameter.service.ParameterException;

import rispl.dime.schedular.imports.DKartConstantsIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dk.customer.CustomerIfc;
import rispl.dk.itemLookUp.PLUItemIfc;
import rispl.dkart.services.PromotionsService;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.LookUpItem;
import rispl.dkart.services.ejb.LookUpItemIfc;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.RisplShippmentMethodEntity;
import rispl.dkart.services.entities.customer.CustomerSite;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dkart.services.entities.transaction.DkartReasonCodes;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkart.services.entities.transaction.OrderTranLineItemPK;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import rispl.dkart.services.entities.transaction.lpo.OrderTransactionLpo;
import rispl.dkart.services.promotions.RisplApplyDiscountRulesIfc;
import rispl.dkservices.common.EmployeeSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;
import rispl.dkservices.common.SearchCriteria;
import rispl.ds.DSAction;
import rispl.ds.ReasonCodes;
import rispl.ds.context.DKartContext;
import utility.ConfigUtils;

/**
 * @author HANU
 *
 */
public class NewOrderAction extends DSAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FAILURE = "failure";
	private static BigDecimal ITEM_LEVEL_DISC = new BigDecimal(0);
	private static BigDecimal TRAN_LEVEL_DISC = new BigDecimal(1);
	private static String enableDoubleDiscountsParam = "NO";
	private final String format = "format.currency";
	// Received via Ajax request
	private String term;
	// Returned as responce
	private ArrayList<String> list;
	
	private boolean isLPODuplicate;

	private String searchcustomer;

	private String itemIDDesc, qty, availQty, price, registryId, exciseTax;
	private String returnPriceFromExl;

	public String unitprice;

	// private String result;

	// private SessionMap<String,Object> sessionMap;

	private OrderTranHeader orderTran;

	private CustomerIfc customer;

	private String orderID, fee, restockingFeeServiceitemId, transportationFeeServiceitemId,
			itemEditPricOvrrReasonCodes, itemEditDiscPerReasonCodes, itemEditDiscAmtReasonCodes, returntReasonCode,
			discReasonCode, priceOverrdReasonCode;

	// private EmployeeIfc employee;

	private boolean inventoryAvail = true, checkInv;
	private int itemIndex, tranType;
	// this has have the information of last added item
	private int itemActiveIndex;
	private Map<String, String> returntReasonCodeMap;
	private String storeStatId;

	// edit item details proprerties
	private String itemNewQty, itemNewPrice = "", itemNewDiscPer, itemNewDiscAmt, itemNewSalesAgent, tranDiscPer,
			tranDiscAmt, vpn;
	private BigDecimal currLineQty = new BigDecimal(0);
	private BigDecimal currItmPrice;
	// private static BigDecimal avCrdtLmt=null;

	private String siteId, deleveryAddr, lpoNum, lpoDate, deliveryNotes, deliveryDate, deliveryType, effectiveDate;
	ArrayList<String> shipmentMethods;
	private int deliveryTime;
	private String jsonOrderTranResponse;
	// public final DateFormat formatter = new
	// SimpleDateFormat(getText("format.date"));
	private File lpoSlip;
	private String lpoSlipFileName, lpoSlipContentType;
	private Map<String, String> slsAgntLst;
	public String agentId;
	// private String employeeId;
	// private static BigDecimal tempAvlCrdLmt=null;
	private PLUItemIfc priceLookupPLU;
	private boolean editOrder;
	private static final Logger LOG = LogManager.getLogger(NewOrderAction.class);
	private boolean enableDoubleDiscounts;
	private boolean anyItemLevelManualDiscuntsApplyed;
	private boolean anyTranLevelManualDiscuntsApplyed;
	private Map<Integer, String> sheduledDeliverTimePeriodMap;
	private String shipmentMethod;
	private boolean creditLimitOverride;
	protected LookUpCustomerIfc lookupCustomer;
	private String storeName;
	private String Disable_option;
	private boolean allowItemSearch;
	private boolean allowItemDelete;
	private Map<String, String> getAllDiscountReasnCode = new HashMap<String, String>();

	public Map<String, String> getGetAllDiscountReasnCode() {
		return getAllDiscountReasnCode;
	}

	public void setGetAllDiscountReasnCode(Map<String, String> getAllDiscountReasnCode) {
		this.getAllDiscountReasnCode = getAllDiscountReasnCode;
	}

	public String getDisable_option() {
		return Disable_option;
	}

	public void setDisable_option(String disable_option) {
		Disable_option = disable_option;
	}

	private boolean disable_amt;
	private boolean disable_discount;
	private boolean disable_delItm;

	public boolean isDisable_delItm() {
		return disable_delItm;
	}

	public void setDisable_delItm(boolean disable_delItm) {
		this.disable_delItm = disable_delItm;
	}

	public boolean isDisable_amt() {
		return disable_amt;
	}

	public void setDisable_amt(boolean disable_amt) {
		this.disable_amt = disable_amt;
	}

	public boolean isDisable_discount() {
		return disable_discount;
	}

	public void setDisable_discount(boolean disable_discount) {
		this.disable_discount = disable_discount;
	}

	// Added by Srinivas
	/*
	 * To get parameter value for Maximum allowed discount percentage for manual
	 * discount by percentage
	 */
	private String maxDisPerc = "100";

	/*
	 * To get parameter value for Number of days to be considered in Scheduled
	 * orders
	 */
	private String schdldDlvryNofDys = "7";

	/*
	 * To check if access to item search is assigned
	 */
	private String searchItemAction;

	private Long empRoleID;

	private boolean managerOverride;

	public Long getEmpRoleID() {
		return empRoleID;
	}

	public void setEmpRoleID(Long empRoleID) {
		this.empRoleID = empRoleID;
	}

	public NewOrderAction() {
		// Saideep: This is wrong, class is not initialized, accessing actionsupport
		// class is causing nullpointer exception
		// restockingFeeServiceitemId = getText("restockingFee.serviceitem.id");
		// transportationFeeServiceitemId = getText("transportationFee.serviceitem.id");
	}

	@Override
	public void prepare() throws Exception {
		restockingFeeServiceitemId = getText("restockingFee.serviceitem.id");
		transportationFeeServiceitemId = getText("transportationFee.serviceitem.id");
		try {
			String maxDiscPerc = DKartContext.getParamterBean().fetchXMLParameterValues().getDiscountPercentageLimit();
			String schdldDlvryNofDysVal = DKartContext.getParamterBean().fetchXMLParameterValues()
					.getScheduledDeliveryOrderBeforeNoOfDays();
			if (maxDiscPerc != null && !maxDiscPerc.equals("")) {
				this.maxDisPerc = maxDiscPerc;
			}
			if (schdldDlvryNofDysVal != null && !schdldDlvryNofDysVal.equals("")) {
				this.schdldDlvryNofDys = schdldDlvryNofDysVal;
			}
			// To get Itemsearch and item delete permissions
			allowItemSearch = SecurityUtils.getSecurityManager().isPermitted(SecurityUtils.getSubject().getPrincipals(),
					"searchItemAction");
			allowItemDelete = SecurityUtils.getSecurityManager().isPermitted(SecurityUtils.getSubject().getPrincipals(),
					"deleteItem");

			String creditLimitOverrideString = DKartContext.getParamterBean().fetchXMLParameterValues()
					.getEnableExceedingCustomerAvailableLimit();
			if (creditLimitOverrideString != null && creditLimitOverrideString.equalsIgnoreCase("Yes"))
				setCreditLimitOverride(true);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e.getCause(), null);
		}
	}

	public NewOrderAction(int tranType, String restockingFeeServiceitemId, String transportationFeeServiceitemId) {
		this.tranType = tranType;
		this.restockingFeeServiceitemId = restockingFeeServiceitemId;
		this.transportationFeeServiceitemId = transportationFeeServiceitemId;
	}

	/**
	 * To create Order
	 * 
	 * @return
	 */
	public String createOrder() {
		// process for disable option
		empRoleID = getEmployee().getRoleId(); // changed by laxmikanth
		try {
			OrderTransactionsIfc ordTrnifc = DKartContext.getLookupOrder();
			String disAmtFun = "updateTranLevelDiscountByAmt";
			String disPerFun = "updateTranLevelDiscountByPer";
			String disDelItm = "deleteItem";

			String disAmtFunResult = ordTrnifc.disablePermissionForEmpl(empRoleID, disAmtFun);
			String disPerFunResult = ordTrnifc.disablePermissionForEmpl(empRoleID, disPerFun);
			String disDelItmResult = ordTrnifc.disablePermissionForEmpl(empRoleID, disDelItm);

			if (disAmtFunResult.equalsIgnoreCase("Y")) {
				setDisable_amt(true);
			} else {
				setDisable_amt(false);
			}

			if (disPerFunResult.equalsIgnoreCase("Y")) {
				setDisable_discount(true);
			} else {
				setDisable_discount(false);
			}

			if (disDelItmResult.equalsIgnoreCase("Y")) {
				setDisable_delItm(true);
			} else {
				setDisable_delItm(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setPreventBack(true);

		String status = FAILURE;

		sheduledDeliverTimePeriodMap = new LinkedHashMap<Integer, String>();
		sheduledDeliverTimePeriodMap.put(0, "0:00 AM - 2:00 AM");
		sheduledDeliverTimePeriodMap.put(2, "2:00 AM - 4:00 AM");
		sheduledDeliverTimePeriodMap.put(4, "4:00 AM - 6:00 AM");
		sheduledDeliverTimePeriodMap.put(6, "6:00 AM - 8:00 AM");
		sheduledDeliverTimePeriodMap.put(8, "8:00 AM - 10:00 AM");
		sheduledDeliverTimePeriodMap.put(10, "10:00 AM - 12:00 PM");
		sheduledDeliverTimePeriodMap.put(12, "12:00 PM - 2:00 PM");
		sheduledDeliverTimePeriodMap.put(14, "2:00 PM - 4:00 PM");
		sheduledDeliverTimePeriodMap.put(16, "4:00 PM - 6:00 PM");
		sheduledDeliverTimePeriodMap.put(18, "6:00 PM - 8:00 PM");
		sheduledDeliverTimePeriodMap.put(20, "8:00 PM - 10:00 PM");
		sheduledDeliverTimePeriodMap.put(22, "10:00 PM - 00:00 PM");

		boolean groupDiscLinItms = false;

		try {
			enableDoubleDiscountsParam = DKartContext.getParamterBean().fetchXMLParameterValues()
					.getEnableDoubleDiscounts();
			enableDoubleDiscounts = enableDoubleDiscountsParam.equalsIgnoreCase("NO") ? false : true;
			groupDiscLinItms = DKartContext.getParamterBean().fetchXMLParameterValues()
					.getBookOrderEnableGroupingDiscountedLineItems().equalsIgnoreCase("NO") ? false : true;
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			LOG.error("Exception While fetching parameter enableDoubleDiscounts : ", e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Exception While fetching parameter enableDoubleDiscounts : ", e);
		}
		try {
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
			// Get from session and if null create new transaction
			if (orderTran == null)
				return INPUT;
			// String actionFired=ActionContext.getContext().getName();

			/* if(!actionFired.equalsIgnoreCase("editOrder")) */
			if (!isEditOrder() && orderTran.getOrdTranLineItems() == null) {
				// avCrdtLmt=null;
				orderTran.setTransCrdtLimit(orderTran.getCustomer().getCustomerLimits().getAvCrdtLimit());
				orderTran.setAvailCrdtLimit(orderTran.getCustomer().getCustomerLimits().getAvCrdtLimit());
			} else {
				if (orderTran.getTransCrdtLimit() == null && orderTran.getAvailCrdtLimit() == null) {
					orderTran.setTransCrdtLimit(orderTran.getCustomer().getCustomerLimits().getAvCrdtLimit());
					orderTran.setAvailCrdtLimit(orderTran.getCustomer().getCustomerLimits().getAvCrdtLimit());
					updateTotals(orderTran);
				} else if (orderTran.getAvailCrdtLimit() == null && isEditOrder()
						&& (orderTran.getOrdTranSum().getDkartNetTot() != null)) {
					orderTran.setAvailCrdtLimit(new BigDecimal(orderTran.getTransCrdtLimit().longValue()
							- orderTran.getOrdTranSum().getDkartNetTot().longValue()));
				} // while edit order to display the avail. credit limit @Laxmikanth

			}

			// To check order id is already created or not
			// to prevent recreation of order id while relinking customer to transaction
			if (orderTran.getOrdTranSum() == null
					|| (orderTran.getOrdTranSum() != null && orderTran.getOrdTranSum().getIdOrd() == null)) {
				orderTran.setIdOpr(getEmployee().getEmployeeId()); // set operator id by lokesh
				orderTran = ordTrn.createNewOrder(orderTran);
				putInSession(SESSION.ORDER_TRANSACTION, orderTran);
			}
			lookupCustomer = DKartContext.getLookUpCustomer();
			String storeID = orderTran.getId().getRtStrId();
			storeName = lookupCustomer.getCustomerStoreName(storeID);
			orderTran.setGroupDiscLinItms(groupDiscLinItms);
			customer = orderTran.getCustomer();
			/*
			 * customer.getCustomerSite().get(0).getCustomerSiteAddressList().get(0).
			 * getTyAds()
			 */
			orderID = orderTran.getOrdTranSum().getIdOrd();// order ID
			// employee = (EmployeeIfc) getSessionmap().get(SESSION.EMPLOYEE.toString());
			/* orderTran.setEmId(employee.getEmployeeId()); */// commented by hanu setEmId is using to save sales agent
																// id
			orderTran.setIdOpr(getEmployee().getEmployeeId());// setIdOpr is using to save login emp ID
			orderTran.getOrdTranSum().setOrdEfDate(orderTran.getOrdTranSum().getOrdEfDate() == null ? new Date()
					: orderTran.getOrdTranSum().getOrdEfDate());
			JSONWriter writer = new JSONWriter();
			itemEditPricOvrrReasonCodes = writer.write(ReasonCodes.getPriceOrrReasonCode());
			itemEditDiscPerReasonCodes = writer.write(ReasonCodes.getDiscPerReasonCode());
			itemEditDiscAmtReasonCodes = writer.write(ReasonCodes.getDiscAmtReasonCode());
			setJsonOrderTranResponse(writer.write(orderTran));
			status = SUCCESS;

			// to load the shipping methods and sales agents
			loadService_Items();
			load_SalesAgemts();

			if (!isEditOrder() && slsAgntLst != null && slsAgntLst.entrySet().iterator().hasNext()) {
				orderTran.setEmId(slsAgntLst.entrySet().iterator().next().getKey());
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

	/**
	 * To delete the items
	 * 
	 * @return
	 */
	public String deleteItem() {

		System.out.println("ajax callS deleteItem : " + itemIndex);
		try {
			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
			orderTran.getOrdTranLineItems().remove(itemIndex);
			isManualDiscsApplyedBefore(orderTran);
			setItemActiveIndex(itemIndex);
			reAssignLineItemSequencesOnDelete(orderTran);
			updateTotals(orderTran);

		} catch (Exception e) {
			LOG.error("Exception While deleting line items: ", e);
			e.printStackTrace();
		}

		/*
		 * Testing Starts try { list = new DataDao().getItems(term); } catch (Exception
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } Testing Ends
		 */
		return SUCCESS;
	}

	/**
	 * Added a method to re-assign the order line item sequence @LAXMIKANTH
	 * 
	 * @param ordTran
	 * @return
	 */
	private OrderTranHeader reAssignLineItemSequencesOnDelete(OrderTranHeader ordTran) {
		int i = 1;
		for (OrderTranLineItem lineItem : ordTran.getOrdTranLineItems()) {
			lineItem.getId().setOrdLnItmSeq(i);
			i++;
		}
		return ordTran;
	}

	/**
	 * To search the items
	 * 
	 * @return
	 */
	public String searchItem() {

		System.out.println("ajax callS item search : " + term);
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);// real

		try {

			if (term.length() > 2) {

				LookUpItemIfc lookupItem = DKartContext.getLookupItem();
				// condition is just for temporary solution during item search in invoice search
				// screen @mallikarjun
				if (orderTran != null && orderTran.getId() != null) {
					storeStatId = orderTran.getId().getRtStrId();
				} else {
					// storeStatId= ConfigUtils.getInstance().getSDSStoreID();
				}
				list = lookupItem.lookForItemIdsAndDesc(term, 10, storeStatId);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Testing Starts try { list = new DataDao().getItems(term); } catch (Exception
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } Testing Ends
		 */
		return SUCCESS;
	}

	/**
	 * To validate LPO Number duplicate
	 * @author veeresh.pal
	 * @return
	 */
	public String validateLPONumberDuplicate() {

		System.out.println("ajax callS lpo number validation: " + lpoNum);
		LOG.info("ajax callS lpo number validation: " + lpoNum);
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);// real

		try {

			if (lpoNum != null && !lpoNum.isEmpty()) {
				OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();

				isLPODuplicate = ordTrn.isLPONumberDuplicate(orderTran, lpoNum);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Quote to apply promotions
	 * 
	 * @return
	 */
	public String quote() {
		setPreventBack(true);

		DateFormat formatter = new SimpleDateFormat(getText("format.date"));

		String status = "failure";
		LOG.info("Starting Quote for Order Transaction");
		// if quote() is called from deleteItem() then orderTran is initiated at
		// deleteItem().
		if (orderTran == null) {
			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
			// employee=(EmployeeIfc) getSessionmap().get(SESSION.EMPLOYEE.toString());
		}

		try {

			/// saving quote as suspended tran
			if (orderTran != null) {
				OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
				try {

					if (siteId != null && !siteId.equalsIgnoreCase("")) {
						orderTran.setIdBtchInvResv(siteId);
					}

					orderTran.setCtDvrInf(getDeliveryAddress(orderTran.getCustomer(), orderTran.getIdBtchInvResv()));
					orderTran.setShipmentMethod(shipmentMethod);
					if (deliveryType != null && !deliveryType.equalsIgnoreCase("")) {
						orderTran.getOrdTranSum().setOrdLvlDvr(deliveryType);
						if (deliveryType.equals("1"))// SCHEDULED DELIVERY
						{
							/*
							 * ZonedDateTime zonedDeliveryDateTime = ZonedDateTime.parse(deliveryDate,
							 * formatter);
							 */
							/* Date delDate = Date.from(zonedDeliveryDateTime.toInstant()); */
							Date delDate = formatter.parse(deliveryDate);
							// orderTran.getOrdTranSums().get(0).setOrdEfDate(delDate);
							orderTran.getOrdTranSum().setOrdDlvrDate(delDate);
							orderTran.getOrdTranSum().setOrdDlvrTimePeriod(deliveryTime);
						} else {
							/* Date delDate = Date.from(ZonedDateTime.now().toInstant()); */
							Date delDate = new Date();
							orderTran.getOrdTranSum().setOrdDlvrDate(delDate);
							// orderTran.getOrdTranSums().get(0).setOrdEfDate(delDate);
						}
					}

					if (lpoNum != null) {
						orderTran.getOrdTranSum().setCustLpoNum(lpoNum);
					}

					/* ZonedDateTime zonedDateTime = ZonedDateTime.parse(lpoDate, formatter); */
					/* Date date = Date.from(zonedDateTime.toInstant()); */
					// System.out.println(date);
					if (lpoDate != null && !lpoDate.equalsIgnoreCase("")) {
						Date date = formatter.parse(lpoDate);
						orderTran.getOrdTranSum().setCustLpoDate(date);
					}

					if (effectiveDate != null && !effectiveDate.equalsIgnoreCase("")) {
						Date efDate = formatter.parse(effectiveDate);
						orderTran.getOrdTranSum().setOrdEfDate(efDate);
					}
					UploadLpo(orderTran);
					// EmployeeIfc employee = (EmployeeIfc) getFromSession(SESSION.EMPLOYEE);
					// orderTran.setEmId(employee.getEmployeeId());arjun
					orderTran.setIdOpr(getEmployee().getEmployeeId());
					orderTran.setCtDvrInfoIns(deliveryNotes);
					orderTran.setScOrd(new BigDecimal(0));// new order
					orderTran.setTransactionStatus(new BigDecimal(4));// transaction suspended
					orderTran.setTsOrdEnd(new Date());
					orderTran.getOrdTranSum().setOrdTranHeader(orderTran);
					orderTran = ordTrn.saveQuote(orderTran);
					if (orderTran != null)// saving quote
					{
						status = "success";
						setViewObjects();
						LOG.info("SUCCESS: Order Transaction have been successfully Quoted");
						// Testing changing session variable for saved orders
						putInSession(SESSION.QUOTE_DETAILS, orderTran);
						removeFromSession(SESSION.ORDER_TRANSACTION);
					}
					// process start by jagadish for need to display discount description in
					// order_Details page
					List<DkartReasonCodes> getDisResnCodesList = ordTrn.getAllDisRsnCode();
					for (DkartReasonCodes getDisResnCodes : getDisResnCodesList) {
						String resnCodeDes = getDisResnCodes.getRsnDesc();
						long longrsncode = getDisResnCodes.getRsnCode();
						String resnCode = Long.toString(longrsncode);
						getAllDiscountReasnCode.put(resnCode, resnCodeDes);
					} // process has been end
				} catch (Exception e) {
					e.printStackTrace();
					LOG.error("ERROR:Could Not Compleat Quote Transaction \n #0", e.toString());
					putInSession(SESSION.ORDER_TRANSACTION, orderTran);
				}

			} else
				return INPUT;
			// Testing changing session variable for saved orders
			// putInSession(SESSION.ORDER_TRANSACTION, orderTran);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.toString());
			if (orderTran == null) {
				return INPUT;
			}
		}
		return SUCCESS;
	}

	/**
	 * To Upload Lpo
	 * 
	 * @param orderTran
	 */
	private void UploadLpo(OrderTranHeader orderTran) {
		try {
			OrderTransactionLpo orderTranLpo = null;
			if (orderTran.getOrdTranLpo() == null) {
				orderTranLpo = new OrderTransactionLpo(orderTran.getId());
				orderTranLpo.setOrdTranHeader(orderTran);
			} else {
				orderTranLpo = orderTran.getOrdTranLpo();
			}

			if (lpoSlip != null) {
				try {
					orderTranLpo.setLpoSlipContent(FileUtils.readFileToByteArray(lpoSlip));
				} catch (IOException e) {
					e.printStackTrace();
					LOG.error("Error reading LPO Slip", e);
				}

				orderTranLpo.setLpoSlipName(lpoSlipFileName);
				orderTranLpo.setLpoSlipType(lpoSlipContentType);
			}

			if (lpoDate != null && !lpoDate.equalsIgnoreCase("")) {
				orderTranLpo.setLpoDate(lpoDate);
			}

			if (lpoNum != null) {
				orderTranLpo.setLpoNumber(lpoNum);
			}
			orderTran.setOrdTranLpo(orderTranLpo);

		} catch (Exception e) {
			LOG.error("Could Not Upload Lpo Slip", e);
		}
	}

	/**
	 * @param customer
	 * @param idBtchInvResv
	 * @return
	 */
	private String getDeliveryAddress(CustomerIfc customer, String idBtchInvResv) {
		String address = "";
		for (CustomerSite cs : customer.getCustomerSite()) {
			if (cs.getCustomerSitePK().getCustSiteId().toString().equalsIgnoreCase(idBtchInvResv)) {
				for (CustomerSiteAddress addr : cs.getCustomerSiteAddressList()) {
					if (addr.getTyAds() == '1') {
						address += addr.getA1Cnct() + " " + (addr.getA2Cnct() == null ? "" : addr.getA2Cnct()) + "\n"
								+ (addr.getCiCnct() == null ? "" : addr.getCiCnct() + ", ")
								+ (addr.getCoCnct() == null ? "" : addr.getCoCnct() + ", ")
								+ (addr.getPcCnct() == null ? "" : addr.getPcCnct() + ", ");
					}
				}

			}
		}
		return address;
	}

	private void setViewObjects() {
		setCustomer(orderTran.getCustomer());

	}

	/**
	 ** This look of PLU item in transaction if item is not available the check the
	 * item in store and WRMS After getting PLU item and inventory is available then
	 * its add the this item in Transaction NOTE: Do not use this method to just
	 * check PLUItem and Inventory Look UP
	 */
	public String itemDetails() {

		// System.out.println("ajax callS ItemDetails : "+term +"in New order action");

		// Item item = new Item();
		SearchCriteria itemCriteria = new SearchCriteria();
		try {
			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
			LookUpItemIfc lookupItem = DKartContext.getLookupItem();
			if (orderTran != null)// If transaction is initiated
			{

				itemCriteria.setStoreID(orderTran.getId().getRtStrId());
				itemCriteria.setCheckInventory(isCheckInv());
				try {
					new BigInteger(term);
					String[] itmIdOrDes = term.split("---");
					term = itmIdOrDes[0];// setting item ID
					itemCriteria.setItemIdOrUPC(term);
				} catch (NumberFormatException ne) {
					if (fee != null && fee.equals("RestockingFee")) {
						term = restockingFeeServiceitemId;
					} else if (fee != null && fee.equals("TransportFee")) {
						term = transportationFeeServiceitemId;
					} else {
						String[] itmIdOrDes = term.split("---");
						try {
							term = itmIdOrDes[0];// setting item ID
							new BigInteger(term);
						} catch (NumberFormatException nfe) {
							term = itmIdOrDes[1];// setting item ID
						}
					}
					itemCriteria.setItemIdOrUPC(term);
				}

				PLUItemIfc pluItem = null;
				// exciseTax=lookupItem.lookForItemExciseTax(term);
				// To reduce the sending requests to business logic, for already added items
				boolean pluitemFound = false;

				if (orderTran.getOrdTranLineItems() != null) {
					for (OrderTranLineItem ordItem : orderTran.getOrdTranLineItems()) {
						if (ordItem.getPluItem() != null
								&& ordItem.getPluItem().getItem().getId().getItmId().equals(term)) {
							pluItem = ordItem.getPluItem();
							pluitemFound = true;
							break;
						}
					}
				}

				// If PLU item in not found in current transaction
				if (!pluitemFound) {
					pluItem = lookupItem.lookUpItemById(itemCriteria);
					// setExciseTax(exciseTax);
				}

				// inv validation for already existed line in transaction
				BigDecimal exisLineQty = new BigDecimal(0);
				BigDecimal reqPlusExisLineQty = new BigDecimal(0);

				if (orderTran.getOrdTranLineItems() != null) {
					for (OrderTranLineItem ordTrnLine : orderTran.getOrdTranLineItems()) {
						if (ordTrnLine.getPluItem() != null && ordTrnLine.getPluItem().getItem() != null
								&& ordTrnLine.getPluItem().getItem().getId() != null
								&& ordTrnLine.getPluItem().getItem().getId().getIdItmPos() != null
								&& ordTrnLine.getPluItem().getItem().getId().getIdItmPos().equals(term)) {
							exisLineQty = exisLineQty.add(ordTrnLine.getLineQnt());
						}

					}
				}

				reqPlusExisLineQty = exisLineQty.add(new BigDecimal(qty));// added requested Qty + existing line QTY

				if (isCheckInv() && pluItem.getInventory().compareTo(reqPlusExisLineQty) == -1// if inventory is less
																								// than to (requested
																								// Qty + existing line
																								// QTY)
						&& getValidateInventory()) {
					BigDecimal currentAvailInv = pluItem.getInventory().subtract(exisLineQty);
					// Krishna: if line the item inventory is 0 the don't show and set -ve inventory
					if (currentAvailInv.doubleValue() > 0) {
						availQty = currentAvailInv.toString();
					} else {
						availQty = "0";
					}
					// setExciseTax(exciseTax);
					putInSession(SESSION.PLU_ITEM, pluItem);
					inventoryAvail = false;
				} else {
					orderTran = addLineItems(pluItem, orderTran);
					updateTotals(orderTran);
					// setExciseTax(exciseTax);
					clearPluItem();// clear the PLu Item from session once item added
				}

			}

		} catch (EJBException ejb) {
			addActionError(ejb.toString());
		} catch (Exception e) {
			Throwable cause = e.getCause();
			e.printStackTrace();
			if (fee != null && (cause instanceof NoResultException)) {
				String expensesName = "";
				if (fee.equals("RestockingFee")) {
					expensesName = getText("restockingFee.Name");
				} else {
					expensesName = getText("transportationFee.Name");
				}
				String[] itemIdArr = { expensesName, itemCriteria.getItemIdOrUPC(), itemCriteria.getStoreID() };
				addActionError(getText("ApplyingExpensesError", itemIdArr));
			} else {
				addActionError(e.toString());
			}
		}
		return SUCCESS;
	}

	public boolean getValidateInventory() {
		try {
			return DKartContext.getParamterBean().fetchXMLParameterValues().getEnableValidateInventory()
					.equalsIgnoreCase("Yes") ? true : false;
		} catch (Exception e) {
		}
		return true;
	}

	/**
	 * code to display availQty and Price on add Item bar...sharanya//
	 * 
	 * @return
	 */
	public String doPriceLookUp() {
		// Item item = new Item();

		SearchCriteria itemCriteria = new SearchCriteria();
		try {
			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);// real
			LookUpItemIfc lookupItem = DKartContext.getLookupItem();
			if (orderTran != null)// If transaction is initiated
			{
				itemCriteria.setStoreID(orderTran.getId().getRtStrId());
				itemCriteria.setCheckInventory(true);
				try {
					new BigInteger(term);
					String[] itmIdOrDes = term.split("---");
					term = itmIdOrDes[0];// setting item ID
					itemCriteria.setItemIdOrUPC(term);

				} catch (NumberFormatException ne) {
					if (fee != null && fee.equals("RestockingFee")) {
						term = restockingFeeServiceitemId;
					} else if (fee != null && fee.equals("TransportFee")) {
						term = transportationFeeServiceitemId;
					} else {
						String[] itmIdOrDes = term.split("---");
						try {
							term = itmIdOrDes[0];// setting item ID
							new BigInteger(term);
						} catch (NumberFormatException nfe) {
							term = itmIdOrDes[1];// setting item ID
						}
					}
					itemCriteria.setItemIdOrUPC(term);
				}

				PLUItemIfc pluItem = null;
				// exciseTax=lookupItem.lookForItemExciseTax(term);
				if (exciseTax == null) {
					exciseTax = "0";
				}
				LOG.info("Starting registrey Method:::::::::" + registryId);
				LOG.info("term:::::::::" + term);
				registryId = lookupItem.lookForItemVpn(term).toString();
				LOG.info("ending registrey Method:::::::::" + registryId);

				// registryId= "5125B005AA";
				// To reduce the sending requests to business logic, for already added items
				boolean pluitemFound = false;

				if (orderTran.getOrdTranLineItems() != null) {
					for (OrderTranLineItem ordItem : orderTran.getOrdTranLineItems()) {
						if (ordItem.getPluItem().getItem().getId().getItmId().equals(term)
								|| ordItem.getPluItem().getItem().getId().getIdItmPos().equals(term)) {
							pluItem = ordItem.getPluItem();
							pluitemFound = true;
							break;
						}
					}
				}

				// If PLU item in not found in current transaction
				if (!pluitemFound) {
					pluItem = lookupItem.lookUpItemById(itemCriteria);
					// to check inventry and prce befre adding itm to transaction...sharanya

					setRegistryId(registryId.toString());
					setExciseTax(exciseTax.toString());
					setAvailQty(pluItem.getInventory().toString());
					setUnitprice(pluItem.getItemPrice().getSlUnAmt().toString());
				}

				// inv validation for already existed line in transaction
				BigDecimal exisLineQty = new BigDecimal(0);

				if (orderTran.getOrdTranLineItems() != null) {
					for (OrderTranLineItem ordTrnLine : orderTran.getOrdTranLineItems()) {
						if (ordTrnLine.getPluItem() != null && ordTrnLine.getPluItem().getItem() != null
								&& ordTrnLine.getPluItem().getItem().getId() != null
								&& ordTrnLine.getPluItem().getItem().getId().getIdItmPos() != null
								&& (ordTrnLine.getPluItem().getItem().getId().getItmId()
										.equals(pluItem.getItem().getId().getItmId())
										&& pluItem.getItem().getId().getIdItmPos().equals(term))
								|| (ordTrnLine.getPluItem().getItem().getId().getItmId().equals(term)
										|| ordTrnLine.getPluItem().getItem().getId().getIdItmPos().equals(term))) {
							exisLineQty = exisLineQty.add(ordTrnLine.getLineQnt());
						}

					}
				}
				if (pluItem != null && pluItem.getInventory() != null) {
					BigDecimal currentAvailInv = pluItem.getInventory().subtract(exisLineQty);

					if (currentAvailInv.doubleValue() > 0) {
						availQty = currentAvailInv.toString();
					} else {
						availQty = "0";
					}
					setAvailQty(availQty);
					setUnitprice(pluItem.getItemPrice().getSlUnAmt().toString());
					setRegistryId(registryId.toString());
					setExciseTax(exciseTax.toString());

				}
				LOG.info("exciseTax:::" + exciseTax);
				LOG.info("registryId:::" + registryId);
				setPriceLookupPLU(pluItem);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;
	}

	/**
	 * to add available inventory
	 * 
	 * @return
	 */
	public String addInvItem() {

		System.out.println("ajax callS  addInvItem : " + term);
		try {
			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
			if (orderTran != null)// If transaction is initiated
			{
				PLUItemIfc pluItem = (PLUItemIfc) getFromSession(SESSION.PLU_ITEM);
				orderTran = addLineItems(pluItem, orderTran);
				updateTotals(orderTran);
				putInSession(SESSION.ORDER_TRANSACTION, orderTran);
				clearPluItem();// clear PLU Item Once Item added to transaction
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			LOG.error("Exception in addInvItem() method " + e);
		}
		return SUCCESS;
	}

	/**
	 * to clear the pluItem from session
	 * 
	 * @return
	 */
	public String clearPluItem() {

		System.out.println("ajax callS  clearPluItem");
		try {
			removeFromSession(SESSION.PLU_ITEM);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * adding line items
	 * 
	 * @param pluItem
	 * @param orderTran
	 */
	public OrderTranHeader addLineItems(PLUItemIfc pluItem, OrderTranHeader orderTran) {
		if (orderTran.getOrdTranLineItems() == null)// enter for 1st call
		{
			List<OrderTranLineItem> orderTranLineItemList = new ArrayList<OrderTranLineItem>();
			orderTran = addItem(pluItem, orderTranLineItemList, orderTran);

		} else// if already objects are there in table items will be grouped
		{
			// boolean isGrouped = false;
			// List<OrderTranLineItem> ordLineList = orderTran.getOrdTranLineItems();
			// int tempitemIndex=0;
			/*
			 * for(OrderTranLineItem orderTranLineItem : ordLineList ) {
			 */
			// boolean isItemLevelManualDisc =false;
			/*
			 * List<OrderTranDiscountItem> disList = orderTranLineItem.getOrdTranDscItms();
			 * if(disList!=null) { for(OrderTranDiscountItem discItem : disList) {
			 * if(discItem.getTyDsc()!=null && discItem.getTyDsc().compareTo(new
			 * BigDecimal(0)) == 0 //item level Discount && discItem.getPrmId()==null )//if
			 * manual discount { //item level manual discounts are applied previously on
			 * this item isItemLevelManualDisc =true; break; } } }
			 */
			/// Grouping logic by @hanu
			/*
			 * if(orderTranLineItem.getItemId().equals(pluItem.getItem().getId().getItmId())
			 * && !orderTranLineItem.isDRApplied() && !isItemLevelManualDisc )//if already
			 * existed item comes and Discount rule is not applied and item level disc is
			 * not applied { if(orderTranLineItem.getPriceOverRideFlag()!="1")//Krishna: if
			 * PriceOverride then Item should not be grouped {
			 * 
			 * //taking the backup of cusrrent values of added item @Mallikarjun
			 * currLineQty=orderTranLineItem.getLineQnt(); itemIndex=tempitemIndex;
			 * itemNewQty=qty; currItmPrice=orderTranLineItem.getExtnDscLnItm();
			 * //Mallikarjun-end //setting line Qty BigDecimal lineQty = new BigDecimal(0);
			 * if(orderTranLineItem.getReturnQtyFlag() == "1") {
			 * lineQty=orderTranLineItem.getLineQntRtn().add(new BigDecimal(qty));
			 * orderTranLineItem.setLineQntRtn(lineQty); } else { lineQty =
			 * orderTranLineItem.getLineQnt().add(new BigDecimal(qty));
			 * orderTranLineItem.setLineQnt(lineQty); }
			 * 
			 * 
			 * 
			 * //setting EXT Price BigDecimal extPrice= new BigDecimal(0);
			 * if(orderTranLineItem.getPriceOverRideFlag()=="1")//if price overrides, take
			 * overridden price { extPrice =
			 * lineQty.multiply(orderTranLineItem.getOverRidePrice()); } else { extPrice =
			 * lineQty.multiply(orderTranLineItem.getItmPrnPrc()); } extPrice =
			 * extPrice.setScale(2, RoundingMode.HALF_DOWN);
			 * orderTranLineItem.setExtnLnItmRtn(extPrice);
			 * 
			 * BigDecimal extDisPrice = extPrice; List<OrderTranDiscountItem> discList =
			 * orderTranLineItem.getOrdTranDscItms(); if(discList!=null) {
			 * for(OrderTranDiscountItem discItem : discList) {
			 * 
			 * if(discItem.getTyDsc()!=null && discItem.getTyDsc().compareTo(new
			 * BigDecimal(0)) == 0 //item level Discount && discItem.getPrmId()==null )//if
			 * manual discount { //item level manual discounts are applied previously on
			 * this item BigDecimal itemDiscPer = discItem.getDscPer(); BigDecimal
			 * itemDiscAmt = discItem.getDscAmt(); if(itemDiscPer!=null &&
			 * itemDiscPer.compareTo(BigDecimal.ZERO)==1)//if discount by percentage value
			 * is available,that means disc by per is applied on item .if discount by
			 * percentage value is not available, disc by Amt is applied on item {
			 * itemDiscAmt = BigDecimal.ZERO; } BigDecimal calculatedDiscAmt =
			 * settingItemDiscAmtPer(discList,discItem,extPrice,itemDiscAmt,itemDiscPer,
			 * discItem.getDiscReasonCode(),discItem.getTyDsc()); extDisPrice =
			 * extDisPrice.subtract(calculatedDiscAmt); }else if(discItem.getPrmType()!=null
			 * && discItem.getPrmType().compareTo(new BigDecimal(2)) == 0){ //Update simple
			 * promotion - Added by Srinivas BigDecimal calculatedDiscAmt =
			 * settingItemDiscAmtPer(discList,discItem,extPrice,discItem.getDscAmt(),
			 * BigDecimal.ZERO,discItem.getDiscReasonCode(),discItem.getTyDsc());
			 * extDisPrice = extDisPrice.subtract(calculatedDiscAmt); } } }
			 * 
			 * //setting EXTN_DSC_LN_ITM Price //The extended, discounted total monetary
			 * value of this LineItem, calculated by multiplying the Quantity by the lookup
			 * price; and subtracting any applicable discounts. extDisPrice =
			 * extDisPrice.setScale(2, RoundingMode.HALF_DOWN);
			 * orderTranLineItem.setExtnDscLnItm(extDisPrice);
			 * updateExtnDiscLnItemPrice(orderTranLineItem); isGrouped = true;
			 * setItemActiveIndex(tempitemIndex); break; } }
			 * 
			 * tempitemIndex=tempitemIndex+1;
			 * 
			 * }
			 */
			/*
			 * if(!isGrouped) {
			 */
			orderTran = addItem(pluItem, orderTran.getOrdTranLineItems(), orderTran);
			setItemActiveIndex(orderTran.getOrdTranLineItems().size() - 1);
			/*
			 * }else{ }
			 */

		}
		return orderTran;
	}

	/**
	 * To add new ITEM
	 * 
	 * @param pluItem
	 * @param orderTranLineItemList
	 * @param orderTran
	 */
	public OrderTranHeader addItem(PLUItemIfc pluItem, List<OrderTranLineItem> orderTranLineItemList,
			OrderTranHeader orderTran) {
		OrderTranLineItem orderTranLineItem = new OrderTranLineItem();

		// setting PluItem
		orderTranLineItem.setPluItem(pluItem);
		if (pluItem.getItem().getItmTyCd() != null
				&& (pluItem.getItem().getItmTyCd().equals("Stock") || pluItem.getItem().getItmTyCd().equals("1"))) {
			orderTranLineItem.setItmTy(BigDecimal.ONE);
		} else if (pluItem.getItem().getItmTyCd() != null
				&& (pluItem.getItem().getItmTyCd().equals("Service") || pluItem.getItem().getItmTyCd().equals("2"))) {
			orderTranLineItem.setItmTy(new BigDecimal(2));
			qty = "1";// service item quantity should be one
		} else if (pluItem.getItem().getItmTyCd() != null && (pluItem.getItem().getItmTyCd().equals("StoreCoupon")
				|| pluItem.getItem().getItmTyCd().equals("3"))) {
			orderTranLineItem.setItmTy(new BigDecimal(3));
		}

		// setting line Qty
		BigDecimal lineQty = new BigDecimal(qty);
		// BigDecimal rPrice=new BigDecimal(returnPriceFromExl);

		orderTranLineItem.setLineQnt(lineQty);
		orderTranLineItem.setRegistryId(getVpn());
		BigDecimal exTaxm = new BigDecimal("0.00");
		if (getExciseTax() != null && !getExciseTax().isEmpty()) {
			BigDecimal exTax = new BigDecimal(getExciseTax());

			exTaxm = lineQty.multiply(exTax);
		}
		// orderTranLineItem.setExciseTax(exTaxm);

		/*
		 * //setting discounts List<OrderTranDiscountItem> ordTranDisItemList = new
		 * ArrayList<OrderTranDiscountItem>(); OrderTranDiscountItem ordTrnDisItem = new
		 * OrderTranDiscountItem();
		 * 
		 * OrderTranDiscountItemPK ordTrnDisItemPk = new OrderTranDiscountItemPK();
		 * 
		 * BigDecimal discAmt = new BigDecimal(0); BigDecimal discPerc = new
		 * BigDecimal(0); ordTrnDisItem.setDscAmt(discAmt);
		 * ordTrnDisItem.setPrmType(discPerc);
		 * 
		 * ordTrnDisItemPk.setDiscSeqNum(ordTranDisItemList.size()+1);
		 * ordTrnDisItem.setId(ordTrnDisItemPk); ordTranDisItemList.add(ordTrnDisItem);
		 * orderTranLineItem.setOrdTranDscItms(ordTranDisItemList);
		 */

		orderTranLineItem.setItmPrnPrc(pluItem.getItemPrice().getSlUnAmt());// setting permanent price
		// The extended total monetary value of this LineItem, calculated by multiplying
		// the Quantity by the lookup price.
		BigDecimal extPrice = lineQty.multiply(orderTranLineItem.getItmPrnPrc());

		if (tranType == 2)// 2 for return
		{
			if (fee != null)// if condition for expenses
			{
				if (fee.equals("RestockingFee")) {
					try {
						// setting RestockingFee reason Code
						orderTranLineItem.setPriceOvrrRsnCode(
								(ReasonCodes.getRestockingFeeReasonCode()).keySet().iterator().next());
						orderTranLineItem.setOverRidePrice(new BigDecimal(price));
						orderTranLineItem.setPriceOverRideFlag("1");
						extPrice = lineQty.multiply(orderTranLineItem.getOverRidePrice());
					} catch (Exception ex) {

						System.out.println("restockingFee.reasonCode.group.name : " + ex);
					}
				} else if (fee.equals("TransportFee")) {
					try {
						// setting TransportFee reason Code
						orderTranLineItem.setPriceOvrrRsnCode(
								(ReasonCodes.getTransportationFeeReasonCode()).keySet().iterator().next());
						orderTranLineItem.setOverRidePrice(new BigDecimal(price));
						orderTranLineItem.setPriceOverRideFlag("1");
						extPrice = lineQty.multiply(orderTranLineItem.getOverRidePrice());
					} catch (Exception ex) {

						System.out.println("transportationFee.reasonCode.group.name : " + ex);
					}
				}
			}
			orderTranLineItem.setRprice(returnPriceFromExl);

			try {
				// setting return reason Code
				orderTranLineItem.setRcRtnMr((ReasonCodes.getReturnReasonCodes()).keySet().iterator().next());
			} catch (Exception ex) {
				orderTranLineItem.setRcRtnMr("");
				System.out.println("returns.reasonCode.group.name : " + ex);
			}

			extPrice = extPrice.setScale(2, RoundingMode.HALF_DOWN);
			extPrice = ConfigUtils.getInstance().createBigDecimal((extPrice), format);
			if (getReturnPriceFromExl() != null && !getReturnPriceFromExl().isEmpty()) {
				try {
					// setting return reason Code
					orderTranLineItem.setRprice(returnPriceFromExl);
					String rPr = getReturnPriceFromExl();
					String[] words = rPr.split("-");
					String subTotals = words[0];
					BigDecimal subTotalfromexcel = new BigDecimal("0.00");

					subTotalfromexcel = new BigDecimal(subTotals);
					if (rPr != null && !rPr.isEmpty())

					{
						if (orderTranLineItem.getItmPrnPrc() == subTotalfromexcel) {
							orderTranLineItem.setExtnLnItmRtn(lineQty.multiply(subTotalfromexcel));
							// orderTranLineItem.setPriceOvrrRsnCode();
						} else {
							orderTranLineItem.setOverRidePrice(subTotalfromexcel);// setting to line item
							orderTranLineItem.setPriceOverRideFlag("1");
							orderTranLineItem.setExtnLnItmRtn(lineQty.multiply(orderTranLineItem.getOverRidePrice()));
							// extPrice= lineQty.multiply(returnPrice);
						}
					}

				} catch (Exception ex) {

					System.out.println("Set the return price from excel : " + ex);
				}

			}
		}

		orderTranLineItem.setExtnLnItmRtn(extPrice.add(exTaxm));// setting EXT Price

		// setting itemid,description,class id
		orderTranLineItem.setItemId(pluItem.getItem().getId().getItmId());
		orderTranLineItem.setDeItmShrtRcpt(pluItem.getItem().getItmDesc());

		// setting EXTN_DSC_LN_ITM Price
		// The extended, discounted total monetary value of this LineItem, calculated by
		// multiplying the Quantity by the lookup price; and subtracting any applicable
		// discounts.
		/*
		 * BigDecimal discount = discPerc.multiply(extPrice).divide(new
		 * BigDecimal(100));//10%(42)/100 discount = discount.add(discAmt); BigDecimal
		 * extDis = lineQty.multiply(discount); BigDecimal extDiscPrice =
		 * extPrice.subtract(extDis);
		 */ if (orderTranLineItem.getExtnDscLnItm() == null) {
			orderTranLineItem.setExtnDscLnItm(extPrice);
		}
		OrderTranLineItemPK orderTranLineItemPk = new OrderTranLineItemPK();
		orderTranLineItemPk.setOrdLnItmSeq(orderTranLineItemList.size() + 1);
		orderTranLineItem.setId(orderTranLineItemPk);

		orderTranLineItemList.add(orderTranLineItem);

		orderTran.setOrdTranLineItems(orderTranLineItemList);
		itemNewQty = qty;
		itemIndex = orderTran.getOrdTranLineItems().size() - 1;
		// currLineQty=;
		if (tranType != 2) {
			if (!checkCustAvlblCrdtLmt(orderTran, false)) {
				undoItemUpdates(orderTran, pluItem.getItem().getId().getItmId(), new BigDecimal(itemNewQty));
				addActionError("Exceeds Available Credit Limit");
			}
		}
		return orderTran;
	}

	/**
	 * To update Transaction totals
	 * 
	 * @param orderTran
	 */
	public void updateTotals(OrderTranHeader orderTran) {
		if (orderTran != null)// If transaction is initiated
		{
			List<OrderTranLineItem> ordLineList = orderTran.getOrdTranLineItems();
			BigDecimal subTotal = new BigDecimal(0);
			BigDecimal discountedTotal = new BigDecimal(0);
			BigDecimal expenses = new BigDecimal(0);

			if (ordLineList != null) {
				for (OrderTranLineItem orderTranLineItem : ordLineList) {
					if (tranType == 2 && (orderTranLineItem.getItemId().equals(restockingFeeServiceitemId)
							|| orderTranLineItem.getItemId().equals(transportationFeeServiceitemId))) {
						expenses = expenses.add(orderTranLineItem.getExtnLnItmRtn());
					}

					else if (orderTranLineItem.getPriceOverRideFlag() == "1") {
						// sub Total
						subTotal = subTotal.add(orderTranLineItem.getExtnLnItmRtn());
					}

					else {
						// sub Total
						// subTotal = subTotal.add(orderTranLineItem.getExtnDscLnItm());
						subTotal = subTotal.add(orderTranLineItem.getExtnLnItmRtn());

					}

				}
			}
			List<OrderTranSum> orderTranSumList = new ArrayList<OrderTranSum>();
			OrderTranSum orderTranSum = orderTran.getOrdTranSum();
			orderTranSum.setDkartSlsTot(subTotal);
			// transaction level discounts
			updatingTranLevelDiscounts(orderTran);
			BigDecimal serviceTotal = BigDecimal.ZERO;
			if (ordLineList != null) {
				for (OrderTranLineItem orderTranLineItem : ordLineList) {
					// Trans level Extended discount amount

					if (orderTranLineItem.getRprice() != null && returnPriceFromExl != null) {
						BigDecimal aa = orderTranLineItem.getExtnDscLnItm();
						if (orderTranLineItem.getExtnDscLnItm().equals(new BigDecimal(0.00))) {
							BigDecimal extPrice = orderTranLineItem.getExtnLnItmRtn();
							BigDecimal extDisPrice = extPrice.setScale(2, RoundingMode.HALF_DOWN);
							orderTranLineItem.setExtnDscLnItm(extDisPrice);
							discountedTotal = discountedTotal.add(orderTranLineItem.getExtnDscLnItm());
						} else {
							BigDecimal extPrice = orderTranLineItem.getExtnLnItmRtn();
							BigDecimal extDisPrice = extPrice.setScale(2, RoundingMode.HALF_DOWN);
							orderTranLineItem.setExtnDscLnItm(extDisPrice);
							discountedTotal = discountedTotal.add(orderTranLineItem.getExtnDscLnItm());

						}
					} else {
						discountedTotal = discountedTotal.add(orderTranLineItem.getExtnDscLnItm());
					}
					// Need service items Extended price for transaction level
					// discount validations

					if (orderTranLineItem.getItmTy().equals(new BigDecimal(2))) {
						serviceTotal = serviceTotal.add(orderTranLineItem.getExtnLnItmRtn());
					}

					// To display Total discount applied on item as a label on sales screen
					totalApplyedDiscountAmtOnItem(orderTranLineItem);

				}
			}

			BigDecimal totalDisc = subTotal.subtract(discountedTotal);
			BigDecimal total = discountedTotal.add(new BigDecimal(0));// adding TAX once the TAX parameter is enabled

			// BigDecimal total = subTotal;
			/*
			 * if(avCrdtLmt==null){ avCrdtLmt =
			 * orderTran.getCustomer().getCustomerLimits().getAvCrdtLimit(); }
			 */
			ConfigUtils config = ConfigUtils.getInstance();
			String currencyFormat = "format.currency";

			orderTranSum.setDkartDscTot(config.createBigDecimal(totalDisc, currencyFormat));
			orderTranSum.setDkartNetTot(config.createBigDecimal(total, currencyFormat));
			orderTranSum.setDkartTaxTot(config.createBigDecimal(0, currencyFormat));
			orderTranSum.setDkartExpenses(config.createBigDecimal(expenses, currencyFormat));
			orderTranSumList.add(orderTranSum);
			orderTran.setOrdTranSum(orderTranSum);
			BigDecimal totalExcludingServicePrice = total.subtract(serviceTotal);
			orderTranSum.setTotalExcludingServicePrice(totalExcludingServicePrice);// need it for validating transaction
																					// level discount amt(should not be
																					// grater than
																					// totalExcludingServicePrice) while
																					// giving in transaction amt popup
			orderTranSum.setOrdTranHeader(orderTran);
			orderTranSum.getOrdTranHeader().setCustomer(orderTran.getCustomer());
			if (checkCustAvlblCrdtLmt(orderTran, false)) {
				checkCustAvlblCrdtLmt(orderTran, true);
			}
			// orderTran.getCustomer().getCustomerLimits().setAvCrdtLimit(avCrdtLmt);
			// System.out.println(orderTran.getCustomer().getCustomerLimits().getAvCrdtLimit());

			/*
			 * BigDecimal availCreditLimit=orderTran.getTransCrdtLimit(); BigDecimal
			 * overrideCreditLimit=orderTran.getCreditLimitOverride();
			 * 
			 * if(overrideCreditLimit != null) { if (overrideCreditLimit.compareTo(total) >=
			 * 0) { overrideCreditLimit = overrideCreditLimit.subtract(total);
			 * orderTranSum.setOrdTranHeader(orderTran);
			 * orderTranSum.getOrdTranHeader().setCustomer(orderTran.getCustomer());
			 * 
			 * //orderTranSum.getOrdTranHeader().getCustomer().getCustomerLimits().
			 * setAvCrdtLimit(avCrdtLmt1); orderTran.setAvailCrdtLimit(overrideCreditLimit);
			 * } else { //undoAddingItem(orderTran);
			 * addActionError("Exceeds Available Credit Limit"); } } else if
			 * (availCreditLimit != null) { if (availCreditLimit.compareTo(total) >= 0) {
			 * availCreditLimit = availCreditLimit.subtract(total);
			 * orderTranSum.setOrdTranHeader(orderTran);
			 * orderTranSum.getOrdTranHeader().setCustomer(orderTran.getCustomer());
			 * 
			 * //orderTranSum.getOrdTranHeader().getCustomer().getCustomerLimits().
			 * setAvCrdtLimit(avCrdtLmt1); orderTran.setAvailCrdtLimit(availCreditLimit); }
			 * else { //undoAddingItem(orderTran);
			 * addActionError("Exceeds Available Credit Limit"); } }
			 */

		}

	}

	/**
	 * to update item details while edit button is clicked
	 * 
	 * @return
	 */
	public String updateItemDetails() {
		System.out.println("ajax callS updateItemDetails itemIndex : " + itemIndex);

		try {

			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);

			OrderTranLineItem orderTranLineItem = orderTran.getOrdTranLineItems().get(itemIndex);
			currLineQty = orderTran.getOrdTranLineItems().get(itemIndex).getLineQnt();
			if (orderTran.getOrdTranLineItems().get(itemIndex).getPriceOverRideFlag() != null
					&& orderTran.getOrdTranLineItems().get(itemIndex).getPriceOverRideFlag().equalsIgnoreCase("1")) {
				currItmPrice = orderTran.getOrdTranLineItems().get(itemIndex).getOverRidePrice();
			} else {
				currItmPrice = orderTran.getOrdTranLineItems().get(itemIndex).getItmPrnPrc();
			}
			// currItmPrc=orderTran.getOrdTranLineItems().get(itemIndex).get

			// if(compTotalsWithAvlCrdLmt(null,
			// orderTran,itemNewQty,String.valueOf(itemIndex),itemNewQty)){
			boolean isSimplePromApplied = false;
			// setting line Qty
			BigDecimal modifiedQty = BigDecimal.ZERO;
			if (itemNewQty != null && !itemNewQty.equals("")) {
				modifiedQty = ConfigUtils.getInstance().createBigDecimal(itemNewQty, format);
			}
			if (modifiedQty.compareTo(orderTranLineItem.getLineQnt()) == 1) {
				modifiedQty = modifiedQty.subtract(orderTranLineItem.getLineQnt());
			} else {
				modifiedQty = orderTranLineItem.getLineQnt().subtract(modifiedQty);
			}
			if (itemNewQty != null && itemNewQty.length() > 0) {
				BigDecimal lineQty = ConfigUtils.getInstance().createBigDecimal(itemNewQty, format);
				orderTranLineItem.setLineQnt(lineQty);
				// Update simple promotion - @Hanu
				List<OrderTranDiscountItem> discList = orderTranLineItem.getOrdTranDscItms();
				if (discList != null) {
					for (OrderTranDiscountItem discountItem : discList) {
						if (discountItem.getPrmType() != null
								&& discountItem.getPrmType().compareTo(new BigDecimal(2)) == 0)// if simple promotion
																								// applied
						{
							BigDecimal extPrice = orderTranLineItem.getExtnLnItmRtn();
							/* BigDecimal calculatedDiscAmt = */ settingItemDiscAmtPer(discList, discountItem, extPrice,
									discountItem.getDscAmt(), BigDecimal.ZERO, discountItem.getDiscReasonCode(),
									discountItem.getTyDsc());
							/*
							 * BigDecimal extDisPrice = extPrice.subtract(calculatedDiscAmt); extDisPrice =
							 * extDisPrice.setScale(2, RoundingMode.HALF_DOWN);
							 * orderTranLineItem.setExtnDscLnItm(extDisPrice);
							 */
							updateExtnDiscLnItemPrice(orderTranLineItem);
							isSimplePromApplied = true;
						}
					}
				}
			}
			OrderTranLineItem ordLnItmBfrPrcOvrrd = null;
			// setting override Price
			if (itemNewPrice != null && itemNewPrice.length() > 0) {
				ordLnItmBfrPrcOvrrd = orderTranLineItem.clone();
				BigDecimal overrdPrice = ConfigUtils.getInstance().createBigDecimal(itemNewPrice, format);
				orderTranLineItem.setOverRidePrice(overrdPrice);
				orderTranLineItem.setPriceOverRideFlag("1");
				orderTranLineItem.setPriceOvrrRsnCode(priceOverrdReasonCode);
			}

			// setting EXT Price
			// The extended total monetary value of this LineItem, calculated by multiplying
			// the Quantity by the lookup price.
			BigDecimal extPrice = new BigDecimal(0);
			BigDecimal lineQty = orderTranLineItem.getLineQnt();

			if (orderTranLineItem.getPriceOverRideFlag() != null
					&& orderTranLineItem.getPriceOverRideFlag().equalsIgnoreCase("1"))// if price override takes
																						// overridden price
			{
				extPrice = lineQty.multiply(orderTranLineItem.getOverRidePrice());
			} else {
				extPrice = lineQty.multiply(orderTranLineItem.getItmPrnPrc());
			}
			orderTranLineItem.setExtnLnItmRtn(extPrice);
			if (!isSimplePromApplied)
				orderTranLineItem.setExtnDscLnItm(extPrice);

			// setting discount amount or discount percentage
			updateItemLevelDiscounts(orderTranLineItem, itemNewDiscAmt, itemNewDiscPer, discReasonCode,
					ITEM_LEVEL_DISC);
			// setting sales agent
			orderTranLineItem.setEmpId(itemNewSalesAgent);

			setItemActiveIndex(itemIndex);
			updateTotals(orderTran);
			if (!checkCustAvlblCrdtLmt(orderTran, false)) {
				undoItemUpdates(orderTran, orderTranLineItem.getItemId(), modifiedQty);
				addActionError("Exceeds Available Credit Limit");
				if (itemNewPrice != null && itemNewPrice.length() > 0 && ordLnItmBfrPrcOvrrd != null) {
					putInSession(SESSION.LIN_ITM_BFR_PRCOVRD, ordLnItmBfrPrcOvrrd);
					orderTran.getOrdTranLineItems().add(ordLnItmBfrPrcOvrrd);
					updateTotals(orderTran);
				}
			}
			putInSession(SESSION.ORDER_TRANSACTION, orderTran);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * To update Item level discounts
	 * 
	 * @param orderTranLineItem
	 * @param discAmt
	 * @param discPer
	 * @param discReasonCode
	 * @param discType
	 */
	private void updateItemLevelDiscounts(OrderTranLineItem orderTranLineItem, String discAmt, String discPer,
			String discReasonCode, BigDecimal discType) {
		List<OrderTranDiscountItem> discList = orderTranLineItem.getOrdTranDscItms();
		ConfigUtils config = ConfigUtils.getInstance();
		final String format = "format.currency";
		BigDecimal extPrice = config.createBigDecimal(orderTranLineItem.getExtnLnItmRtn(), format);
		/*
		 * BigDecimal extDiscLnItmPrice = extPrice; BigDecimal calculatedDiscAmt = new
		 * BigDecimal(0);
		 */
		BigDecimal itemDiscAmt = null;
		BigDecimal itemDiscPer = null;
		try {
			itemDiscAmt = config.createBigDecimal(discAmt, format);
		} catch (NumberFormatException ex) {
			itemDiscAmt = new BigDecimal(0);
		}
		try {
			itemDiscPer = config.createBigDecimal(discPer, format);
			// Check if requested dis% is greater than max allowed dis%, if yes set max
			// allowed dis% for calculation of discount
			BigDecimal maxAlwdDiscPrcnt = config.createBigDecimal(this.maxDisPerc, format);
			if (itemDiscPer.compareTo(maxAlwdDiscPrcnt) == 1) {
				itemDiscPer = maxAlwdDiscPrcnt;
			}
		} catch (NumberFormatException ex) {
			itemDiscPer = new BigDecimal(0);
		}

		if (itemDiscAmt.compareTo(BigDecimal.ZERO) == 0 && itemDiscPer.compareTo(BigDecimal.ZERO) == 0)// if item level
																										// discount is
																										// giving in 0
																										// amt/0% then
																										// will delete
																										// from discount
																										// list
		{
			if (discList != null) {
				if (discList != null) {
					Iterator<OrderTranDiscountItem> discIterater = discList.iterator();
					while (discIterater.hasNext()) {
						OrderTranDiscountItem discItem = discIterater.next();
						if (discItem.getTyDsc() != null && discItem.getTyDsc().compareTo(new BigDecimal(0)) == 0 // item
																													// level
																													// Discount
								&& discItem.getPrmId() == null)// if manual discount
						{ // item level manual discounts are applied previously on this item

							discIterater.remove();// discList has one item level manual discount item
							setAnyItemLevelManualDiscuntsApplyed(false);
							updateExtnDiscLnItemPrice(orderTranLineItem);
							break;
						}

					}

				}
			}
		} else {
			if (discList == null) {// any type of discounts are not applied before on this item
				discList = new ArrayList<OrderTranDiscountItem>();
				OrderTranDiscountItem disItem = new OrderTranDiscountItem();
				/* calculatedDiscAmt = */settingItemDiscAmtPer(discList, disItem, extPrice, itemDiscAmt, itemDiscPer,
						discReasonCode, discType);
				/* extDiscLnItmPrice=extDiscLnItmPrice.subtract(calculatedDiscAmt); */
				orderTranLineItem.setOrdTranDscItms(discList);

			} else {
				boolean isItemLevelManualDisc = false;
				for (OrderTranDiscountItem discItem : discList) {

					if (discItem.getTyDsc() != null && discItem.getTyDsc().compareTo(new BigDecimal(0)) == 0 // item
																												// level
																												// Discount
							&& discItem.getPrmId() == null)// if manual discount
					{ // item level manual discounts are applied previously on this item

						/* calculatedDiscAmt = */settingItemDiscAmtPer(discList, discItem, extPrice, itemDiscAmt,
								itemDiscPer, discReasonCode, discType);
						// extDiscLnItmPrice=extDiscLnItmPrice.subtract(calculatedDiscAmt);
						isItemLevelManualDisc = true;
					}

				}
				if (!isItemLevelManualDisc)// if item level manual discounts are not applied before
				{
					OrderTranDiscountItem disItem = new OrderTranDiscountItem();
					/* calculatedDiscAmt = */settingItemDiscAmtPer(discList, disItem, extPrice, itemDiscAmt,
							itemDiscPer, discReasonCode, discType);
					/* extDiscLnItmPrice=extDiscLnItmPrice.subtract(calculatedDiscAmt); */
				}

			}
			updateExtnDiscLnItemPrice(orderTranLineItem);
			/*
			 * extDiscLnItmPrice = extDiscLnItmPrice.setScale(2, RoundingMode.HALF_DOWN);
			 * orderTranLineItem.setExtnDscLnItm(extDiscLnItmPrice);
			 */
			setAnyItemLevelManualDiscuntsApplyed(true);
		}

	}

	/**
	 * To set tran level discounts
	 * 
	 * @param orderTranLineItem
	 * @param discAmt
	 * @param discPer
	 * @param discReasonCode
	 * @param discType
	 */
	private void updateTranLevelDiscounts(OrderTranLineItem orderTranLineItem, String discAmt, String discPer,
			String discReasonCode, BigDecimal discType) {
		List<OrderTranDiscountItem> discList = orderTranLineItem.getOrdTranDscItms();
		BigDecimal extPrice = orderTranLineItem.getExtnLnItmRtn();
		BigDecimal itemDiscAmt = null;
		BigDecimal itemDiscPer = null;
		try {
			itemDiscAmt = new BigDecimal(discAmt);
		} catch (NumberFormatException ex) {
			itemDiscAmt = new BigDecimal(0);
		}
		try {
			itemDiscPer = new BigDecimal(discPer);
		} catch (NumberFormatException ex) {
			itemDiscPer = new BigDecimal(0);
		}

		if (itemDiscAmt.compareTo(BigDecimal.ZERO) == 0 && itemDiscPer.compareTo(BigDecimal.ZERO) == 0)// if tran level
																										// discount is
																										// giving in 0
																										// amt/0% then
																										// will delete
																										// from discount
																										// list
		{
			if (discList != null) {
				Iterator<OrderTranDiscountItem> discIterater = discList.iterator();
				while (discIterater.hasNext()) {
					OrderTranDiscountItem discItem = discIterater.next();
					if (discItem.getTyDsc() != null && discItem.getTyDsc().compareTo(new BigDecimal(1)) == 0 // tran
																												// level
																												// Discount
							&& discItem.getPrmId() == null)// if manual discount
					{ // tran level manual discounts are applied previously on this item

						discIterater.remove();// discList has one item level manual discount item
						setAnyTranLevelManualDiscuntsApplyed(false);
						break;
					}

				}

			}
		} else {
			if (discList == null) {// any type of discounts are not applied before on this item
				discList = new ArrayList<OrderTranDiscountItem>();
				OrderTranDiscountItem disItem = new OrderTranDiscountItem();
				settingItemDiscAmtPer(discList, disItem, extPrice, itemDiscAmt, itemDiscPer, discReasonCode, discType);
				orderTranLineItem.setOrdTranDscItms(discList);
			} else {

				boolean isTranLevelManualDisc = false;
				for (OrderTranDiscountItem discItem : discList) {

					if (discItem.getTyDsc() != null && discItem.getTyDsc().compareTo(new BigDecimal(1)) == 0 // tran
																												// level
																												// Discount
							&& discItem.getPrmId() == null)// if manual discount
					{// tran level manual discounts are applied previously on this item
						settingItemDiscAmtPer(discList, discItem, extPrice, itemDiscAmt, itemDiscPer, discReasonCode,
								discType);
						isTranLevelManualDisc = true;
					}
				}
				if (!isTranLevelManualDisc)// if tran level manual discounts are not applied before
				{
					OrderTranDiscountItem disItem = new OrderTranDiscountItem();
					settingItemDiscAmtPer(discList, disItem, extPrice, itemDiscAmt, itemDiscPer, discReasonCode,
							discType);
				}

			}
			setAnyTranLevelManualDiscuntsApplyed(true);
		}

		// updating Extended Discounted Price
		updateExtnDiscLnItemPrice(orderTranLineItem);
	}

	public static BigDecimal percentage(BigDecimal base, BigDecimal pct) {
		return base.multiply(pct).divide(new BigDecimal(100));
	}

	/**
	 * To set line level discounts
	 * 
	 * @param discList
	 * @param disItem
	 * @param extPrice
	 * @param itemNewDiscAmt
	 * @param itemNewDiscPer
	 * @param discReasonCode
	 * @param discType
	 * @return
	 */
	private void settingItemDiscAmtPer(List<OrderTranDiscountItem> discList, OrderTranDiscountItem disItem,
			BigDecimal extPrice, BigDecimal itemNewDiscAmt, BigDecimal itemNewDiscPer, String discReasonCode,
			BigDecimal discType) {
		BigDecimal discAmt = new BigDecimal(0);
		boolean isNewItem = false;
		if (disItem.getTyDsc() == null) {
			isNewItem = true;
		}
		if ((itemNewDiscAmt != null && itemNewDiscAmt.compareTo(BigDecimal.ZERO) != 0)
				|| (itemNewDiscPer != null && itemNewDiscPer.compareTo(BigDecimal.ZERO) != 0)) {
			if (itemNewDiscAmt.compareTo(BigDecimal.ZERO) != 0) {
				// itemNewDiscAmt = itemNewDiscAmt.setScale(2, RoundingMode.HALF_DOWN);
				disItem.setDscAmt(itemNewDiscAmt);
				disItem.setDscPer(itemNewDiscPer);
				disItem.setDiscReasonCode(discReasonCode);// setting reasonCode
				discAmt = itemNewDiscAmt;
			} else {
				disItem.setDscPer(itemNewDiscPer);
				if (discList.size() == 0)// no discounts r applied previously
				{
					discAmt = percentage(extPrice, itemNewDiscPer);
				} else {
					if (isNewItem) {
						for (OrderTranDiscountItem discItem : discList) {
							if (discItem.getPrmId() != null)// promotion is applied previously
							{
								discAmt = discAmt.add(discItem.getDscAmt());
							} else if (discItem.getTyDsc() != null
									&& discItem.getTyDsc().compareTo(new BigDecimal(1)) == 0 // tran level Discount
									&& discItem.getPrmId() == null)// if manual discount
							{// tran level manual discounts are applied previously on this item
								discAmt = discAmt.add(discItem.getDscAmt());
							} else if (discItem.getTyDsc() != null
									&& discItem.getTyDsc().compareTo(new BigDecimal(0)) == 0 // item level Discount
									&& discItem.getPrmId() == null)// if manual discount
							{// item level manual discounts are applied previously on this item
								discAmt = discAmt.add(discItem.getDscAmt());
							}
						}
						extPrice = extPrice.subtract(discAmt);
						discAmt = percentage(extPrice, itemNewDiscPer);
					} else {// already existed disc Item
						for (OrderTranDiscountItem discItem : discList) {
							if (discItem.hashCode() == disItem.hashCode()) {
								break;
							}
							if (discItem.getPrmId() != null)// promotion is applied previously
							{
								discAmt = discAmt.add(discItem.getDscAmt());
							} else if (discItem.getTyDsc() != null
									&& discItem.getTyDsc().compareTo(new BigDecimal(1)) == 0 // tran level Discount
									&& discItem.getPrmId() == null)// if manual discount
							{// tran level manual discounts are applied previously on this item
								discAmt = discAmt.add(discItem.getDscAmt());
							} else if (discItem.getTyDsc() != null
									&& discItem.getTyDsc().compareTo(new BigDecimal(0)) == 0 // item level Discount
									&& discItem.getPrmId() == null)// if manual discount
							{// item level manual discounts are applied previously on this item
								discAmt = discAmt.add(discItem.getDscAmt());
							}
						}
						extPrice = extPrice.subtract(discAmt);
						discAmt = percentage(extPrice, itemNewDiscPer);
					}
				}
				// discAmt = discAmt.setScale(2, RoundingMode.HALF_DOWN);
				discAmt = ConfigUtils.getInstance().createBigDecimal(discAmt, format);
				disItem.setDscAmt(discAmt);
				disItem.setDiscReasonCode(discReasonCode);// setting reasonCode
			}

			if (isNewItem) {
				disItem.setTyDsc(discType);// Item level(0),Transaction level(1)
				disItem.setPrmType(DKartConstantsIfc.DIS_PROM_MNUL); // manual(0) promo type
				discList.add(disItem);
			}
		} else {
			disItem.setDscAmt(BigDecimal.ZERO);
			disItem.setDscPer(BigDecimal.ZERO);
		}

		/* return discAmt; */

	}

	/**
	 * To Update Extended Discounted Line item price.(total price of Line Item)
	 * 
	 * @param orderTranLineItem
	 */
	private void updateExtnDiscLnItemPrice(OrderTranLineItem orderTranLineItem) {
		List<OrderTranDiscountItem> discList = orderTranLineItem.getOrdTranDscItms();
		BigDecimal extnDscLnItmPrice = orderTranLineItem.getExtnLnItmRtn();
		if (discList != null && discList.size() != 0) {
			BigDecimal discAmt = new BigDecimal(0);
			for (OrderTranDiscountItem discItem : discList) {
				discAmt = discAmt.add(discItem.getDscAmt());

			}
			extnDscLnItmPrice = extnDscLnItmPrice.subtract(discAmt);
			// extnDscLnItmPrice = extnDscLnItmPrice.setScale(2,RoundingMode.HALF_DOWN);
			extnDscLnItmPrice = ConfigUtils.getInstance().createBigDecimal(extnDscLnItmPrice, format);
		}
		orderTranLineItem.setExtnDscLnItm(extnDscLnItmPrice);
	}

	/**
	 * @return
	 */
	public String updateTranLevelDiscountByAmt() {
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		// doubleDiscounts(orderTran);

		orderTran.setTranDiscAmtFlag(true);
		orderTran.setTranDiscPerFlag(false);
		orderTran.setTranDiscAmt(tranDiscAmt);
		orderTran.setTranDiscReasonCode(discReasonCode);
		orderTran.setTranDiscPer(null);
		updateTotals(orderTran);
		return SUCCESS;
	}

	/**
	 * @return
	 */
	public String updateTranLevelDiscountByPer() {
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		// doubleDiscounts(orderTran);

		orderTran.setTranDiscAmtFlag(false);
		orderTran.setTranDiscPerFlag(true);
		orderTran.setTranDiscAmt(null);
		orderTran.setTranDiscPer(tranDiscPer);
		orderTran.setTranDiscReasonCode(discReasonCode);
		updateTotals(orderTran);
		return SUCCESS;
	}

	public String disableDoubleDiscs() {
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		orderTran.setTranDiscAmtFlag(false);
		orderTran.setTranDiscPerFlag(false);
		orderTran.setTranDiscAmt(null);
		orderTran.setTranDiscPer(null);
		orderTran.setTranDiscReasonCode(null);
		List<OrderTranLineItem> orderTranLineitemsList = orderTran.getOrdTranLineItems();
		for (OrderTranLineItem orderTranLineItem : orderTranLineitemsList) {
			// orderTranLineItem.setOrdTranDscItms(null);//not to apply previous
			// discounts(double discs)
			List<OrderTranDiscountItem> discList = orderTranLineItem.getOrdTranDscItms();
			if (discList != null) {
				for (Iterator<OrderTranDiscountItem> it = discList.iterator(); it.hasNext();) {
					OrderTranDiscountItem discountItem = it.next();
					if (discountItem.getPrmId() == null)// not a promotion
					{
						it.remove();
						/*
						 * orderTranLineItem.setOrdTranDscItms(null);//not to apply previous
						 * discounts(double discs) break;
						 */
					}

				}
			}
			updateExtnDiscLnItemPrice(orderTranLineItem);

		}
		orderTran.setAnyItemLevelManualDiscuntsApplyed(false);
		orderTran.setAnyTranLevelManualDiscuntsApplyed(false);
		updateTotals(orderTran);
		return SUCCESS;

	}

	/**
	 * @param orderTran
	 */
	public void updatingTranLevelDiscounts(OrderTranHeader orderTran) {
		List<OrderTranLineItem> orderTranLineitemsList = orderTran.getOrdTranLineItems();
		if (orderTranLineitemsList != null && orderTranLineitemsList.size() > 0) {
			BigDecimal serviceTotal = BigDecimal.ZERO, subTotal = null;
			if (orderTran.isTranDiscAmtFlag()) {

				for (OrderTranLineItem orderTranLineItem : orderTranLineitemsList) {
					// to avoid transaction level discount applied on service items
					if (orderTranLineItem.getItmTy().equals(new BigDecimal(2))) {
						serviceTotal = serviceTotal.add(orderTranLineItem.getExtnLnItmRtn());
					}
				}

				BigDecimal allLineItemsDiscAmt = new BigDecimal(0);
				if (serviceTotal != null && serviceTotal.compareTo(BigDecimal.ZERO) > 0) {
					subTotal = orderTran.getOrdTranSum().getDkartSlsTot().subtract(serviceTotal);
				} else {
					subTotal = orderTran.getOrdTranSum().getDkartSlsTot();
				}
				BigDecimal TranDiscAmt;
				try {
					TranDiscAmt = ConfigUtils.getInstance().createBigDecimal(orderTran.getTranDiscAmt(), format);
				} catch (NumberFormatException ne) {
					TranDiscAmt = BigDecimal.ZERO;
				}
				int count = 0;
				boolean isTranDiscAmtLessToallLineItemsDiscAmt = false;
				for (OrderTranLineItem orderTranLineItem : orderTranLineitemsList) {
					if (!orderTranLineItem.getItmTy().equals(new BigDecimal(2))) {// to avoid transaction level discount
																					// applied on service items
						count++;
						BigDecimal itemExtPrice = orderTranLineItem.getExtnLnItmRtn();
						BigDecimal itemLevelDiscAmt = itemExtPrice.multiply(TranDiscAmt).divide(subTotal,
								subTotal.scale(), RoundingMode.HALF_DOWN);
						itemLevelDiscAmt = ConfigUtils.getInstance().createBigDecimal(itemLevelDiscAmt, format);
						allLineItemsDiscAmt = allLineItemsDiscAmt.add(itemLevelDiscAmt);
						if (orderTranLineitemsList.size() == count // at last item,
								&& TranDiscAmt.compareTo(allLineItemsDiscAmt) == 1)// checking whether TranDiscAmt is
																					// equal to allLineItemsDiscAmt. if
																					// TranDiscAmt is grater than to
																					// allLineItemsDiscAmt , will adjust
																					// the remaining discount amount to
																					// last item.
						{
							itemLevelDiscAmt = itemLevelDiscAmt.add(TranDiscAmt.subtract(allLineItemsDiscAmt));
						} else if (orderTranLineitemsList.size() == count // at last item,
								&& TranDiscAmt.compareTo(allLineItemsDiscAmt) == -1)// checking whether TranDiscAmt is
																					// equal to allLineItemsDiscAmt. if
																					// TranDiscAmt is less than to
																					// allLineItemsDiscAmt, will
																					// subtract the extra discount
																					// amount from last item to first.

						{
							isTranDiscAmtLessToallLineItemsDiscAmt = true;
						}
						// updating tranlevel discount percentage
						updateTranLevelDiscounts(orderTranLineItem, itemLevelDiscAmt.toString(), "",
								orderTran.getTranDiscReasonCode(), TRAN_LEVEL_DISC);

					}
					// Issue :: If we give tran dis Amt as 0.63,after prorating of tran disc amt on
					// all items, the calculated allLineItemsDiscAmt is comming as 0.64
					// Fix :: the extra comming 0.01 disc amt is adjustimg from last item to
					// first.if tran level disc amt of line item is grater than to exta comming disc
					// amt then will subtract from already existing tran level disc amt of that
					// item.
					if (isTranDiscAmtLessToallLineItemsDiscAmt) {
						BigDecimal extraCommingDiscAmt = allLineItemsDiscAmt.subtract(TranDiscAmt);
						// extraCommingDiscAmt = extraCommingDiscAmt.setScale(2,
						// RoundingMode.HALF_DOWN);
						extraCommingDiscAmt = ConfigUtils.getInstance().createBigDecimal(extraCommingDiscAmt, format);
						for (int i = count - 1; i > 0; i--) {
							boolean isExtraCommingDiscAmtAdjsted = false;
							List<OrderTranDiscountItem> discList = orderTranLineitemsList.get(i).getOrdTranDscItms();
							for (OrderTranDiscountItem discItem : discList) {
								if (discItem.getTyDsc() != null && discItem.getTyDsc().compareTo(new BigDecimal(1)) == 0 // tran
																															// level
																															// Discount
										&& discItem.getPrmId() == null)// if manual discount
								{// tran level manual discounts are applied previously on this item
									BigDecimal discAmt = discItem.getDscAmt();
									if (discAmt.compareTo(extraCommingDiscAmt) == 1)// if already existing transaction
																					// discount amount is grater than
																					// extra comming discAmt then will
																					// subtract from existing tran level
																					// disc amt.
									{
										discItem.setDscAmt(discAmt.subtract(extraCommingDiscAmt));// extra Comming
																									// discount amt
																									// adjusted
										isExtraCommingDiscAmtAdjsted = true;// to break outer loop
										break;
									}
								}
							}
							if (isExtraCommingDiscAmtAdjsted) {
								updateExtnDiscLnItemPrice(orderTranLineitemsList.get(i));
								break;
							}
						}
					}
				}
			} else if (orderTran.isTranDiscPerFlag()) {
				for (OrderTranLineItem orderTranLineItem : orderTranLineitemsList) {
					// to avoid transaction level discount applied on service items
					if (!orderTranLineItem.getItmTy().equals(new BigDecimal(2))) {
						// updating tranlevel discount percentage
						updateTranLevelDiscounts(orderTranLineItem, "", orderTran.getTranDiscPer(),
								orderTran.getTranDiscReasonCode(), TRAN_LEVEL_DISC);
					}
				}
			}
		}
	}

	/**
	 * As the total increases than the transaction total then reverting the
	 * modification @mallikarjun
	 * 
	 * @param orderTran
	 * @return
	 */
	public String undoAddingItem(OrderTranHeader orderTran) {

		System.out.println("ajax callS deleteItem : " + itemIndex);
		try {
			if (itemNewQty != null && itemNewQty.length() > 0 && itemIndex >= 0
					&& !currLineQty.equals(new BigDecimal(0)) && itemNewPrice.length() <= 0) {
				/*
				 * orderTran = (OrderTranHeader) sessionMap.get("OrderTransaction");
				 * orderTran.getOrdTranLineItems().get(itemIndex).setItmPrnPrc(currItmPrice);
				 * //BigDecimal finQty =
				 * orderTran.getOrdTranLineItems().get(itemIndex).getLineQnt().subtract(new
				 * BigDecimal(itemNewQty));
				 * orderTran.getOrdTranLineItems().get(itemIndex).setLineQnt(currLineQty);
				 */
				itemNewQty = String.valueOf(currLineQty);
				updateItemDetails();

				// quote();
				// popUp("cannot", "not possible", "press enter");
				// updateTotals(orderTran);

				addActionError("Exceeds Available Credit Limit");
			} else if (itemNewQty != null && itemNewQty.length() > 0 && itemIndex >= 0 && itemNewPrice != null
					&& itemNewPrice.length() > 0) {
				/*
				 * orderTran = (OrderTranHeader) sessionMap.get("OrderTransaction");
				 * orderTran.getOrdTranLineItems().get(itemIndex).setLineQnt(currLineQty);
				 * orderTran.getOrdTranLineItems().get(itemIndex).setItmPrnPrc(currItmPrice);
				 * //orderTran.getOrdTranLineItems().get(itemIndex).setItmPrnPrc(basePrice);
				 * orderTran.getOrdTranLineItems().get(itemIndex).setPriceOverRideFlag("0");
				 */
				itemNewQty = String.valueOf(currLineQty);
				itemNewPrice = String.valueOf(currItmPrice);
				updateItemDetails();
				// popUp("cannot", "not possible", "press enter");
				// quote();
				// updateTotals(orderTran);
				addActionError("Exceeds Available Credit Limit");
			} else if (itemIndex >= 0 && itemNewPrice != null && itemNewPrice.length() > 0) {
				/*
				 * orderTran = (OrderTranHeader) sessionMap.get("OrderTransaction");
				 * orderTran.getOrdTranLineItems().get(itemIndex).setItmPrnPrc(currItmPrice);
				 * orderTran.getOrdTranLineItems().get(itemIndex).setPriceOverRideFlag("0");
				 * addActionError("hello im done");
				 */
				itemNewPrice = String.valueOf(currItmPrice);
				// popUp("cannot", "not possible", "press enter");
				updateItemDetails();
				// quote();
				// updateTotals(orderTran);
				addActionError("Exceeds Available Credit Limit");
			} else if (itemIndex >= 0) {
				orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
				orderTran.getOrdTranLineItems().remove(itemIndex);
				addActionError("Exceeds Available Credit Limit");
				// popUp("cannot", "not possible", "press enter");
				// quote();
				updateTotals(orderTran);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		itemNewQty = null;
		itemIndex = 0;
		itemNewPrice = null;
		currItmPrice = null;
		currLineQty = new BigDecimal(0);
		return SUCCESS;
	}

	/**
	 * To reload the transction
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String reloadOrdTran() {
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		// If customer credit limit get an override then, Get items that were not added
		// before override and add them to the transaction and update totals,promotions
		if (getFromSession(SESSION.RELOAD_ORDER_TRANSACTION) != null) {

			try {
				List<OrderTranLineItem> reAdd = null;
				OrderTranLineItem lineBfrPrcOVrd = null;

				if (getFromSession(SESSION.LIN_ITM_BFR_PRCOVRD) != null) {
					lineBfrPrcOVrd = (OrderTranLineItem) getFromSession(SESSION.LIN_ITM_BFR_PRCOVRD);
					removeFromSession(SESSION.LIN_ITM_BFR_PRCOVRD);
					List<OrderTranLineItem> ordLinItms = orderTran.getOrdTranLineItems();
					ordLinItms.remove(new Long(lineBfrPrcOVrd.getId().getOrdLnItmSeq()).intValue() - 1);
					orderTran.setOrdTranLineItems(ordLinItms);
					// orderTran.getOrdTranLineItems().remove(lineBfrPrcOVrd.getId().getOrdLnItmSeq()-1);
				}

				if (getFromSession(SESSION.RELOAD_ORDER_TRANSACTION) != null) {
					reAdd = (List<OrderTranLineItem>) getFromSession(SESSION.RELOAD_ORDER_TRANSACTION);
					removeFromSession(SESSION.RELOAD_ORDER_TRANSACTION);
					orderTran.getOrdTranLineItems().addAll(reAdd);
				}

				updateTotals(orderTran);

				if (!checkCustAvlblCrdtLmt(orderTran, false)) {
					String rmvItemId = null;
					BigDecimal removeQty = BigDecimal.ZERO;
					for (OrderTranLineItem rmvLine : reAdd) {
						rmvItemId = rmvLine.getItemId();
						removeQty = removeQty.add(rmvLine.getLineQnt());
					}
					undoItemUpdates(orderTran, rmvItemId, removeQty);
					if (lineBfrPrcOVrd != null) {
						orderTran.getOrdTranLineItems().add(lineBfrPrcOVrd);
						updateTotals(orderTran);
						putInSession(SESSION.LIN_ITM_BFR_PRCOVRD, lineBfrPrcOVrd);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}

		} else {
			updateTotals(orderTran);
		}

		putInSession(SESSION.ORDER_TRANSACTION, orderTran);
		if (orderTran == null)
			getQuoteDetails();
		return SUCCESS;
	}

	public String getQuoteDetails() {
		orderTran = (OrderTranHeader) getFromSession(SESSION.QUOTE_DETAILS);
		// updateTotals(orderTran);
		// putInSession(SESSION.QUOTE_DETAILS, orderTran);
		return SUCCESS;
	}

	/**
	 * changed by hanu loading the servie items to the screen
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loadService_Items() throws Exception {
		// arjun
		try {
			LookUpItemIfc lookupServiceItem = DKartContext.getLookupItem();
			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
			if (orderTran != null) {
				// storeStatId = ConfigUtils.getInstance().getSDSStoreID();
				storeStatId = orderTran.getId().getRtStrId();
				ArrayList<PLUItemIfc> serviceItemlist = lookupServiceItem.lookForServiceItem(storeStatId);

				if (serviceItemlist != null && serviceItemlist.size() > 0)// if atleast one service item is present then
																			// call for shipping methods
				{
					ArrayList<String> serviceItemsList = new ArrayList<String>();
					LookUpItemIfc lookupServiceMethod = DKartContext.getLookupItem();
					List<RisplShippmentMethodEntity> serviceMethodlist = lookupServiceMethod.lookForServiceMethods();
					System.out.println(serviceMethodlist.size());
					for (int i = 0; i < serviceMethodlist.size(); i++) {
						try {
							serviceItemsList.add(serviceMethodlist.get(i).getCarrierName().toString() + "  ---  "
									+ getText("global.currency") + " " + serviceMethodlist.get(i).getCarrierPrice());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					setShipmentMethods(serviceItemsList);
				} else {

				}
			}
		} catch (Exception e) {
			LOG.error("loadService_Items() has an error", e);
		}
		return SUCCESS;

	}

	/**
	 * Changed by hanu adding the service item to the transaction
	 * 
	 * @return
	 * @throws Exception
	 */
	public String serviceItemDetails() throws Exception {
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		checkServiceItems(orderTran);
		LookUpItemIfc lookupServiceItem = DKartContext.getLookupItem();
		ArrayList<PLUItemIfc> serviceItemlist = lookupServiceItem.lookForServiceItem(storeStatId);
		PLUItemIfc pluItem = serviceItemlist.get(0);
		pluItem.setInventory(BigDecimal.ZERO);

		String srvcPrc[] = term.split(" --- " + getText("global.currency"));
		String servcPrice = "0";
		if (srvcPrc[1] != null) {
			pluItem.getItem().setItmDesc(srvcPrc[0]);
			servcPrice = srvcPrc[1];
			servcPrice = servcPrice.trim();
		}
		setVpn(" ");
		pluItem.getItemPrice().setSlUnAmt(new BigDecimal(servcPrice));
		orderTran = addLineItems(pluItem, orderTran);
		updateTotals(orderTran);

		return SUCCESS;

	}

	/**
	 * deleting the existing service item from the transaction if added already
	 * 
	 * @param orderTran2
	 * @return
	 */
	private String checkServiceItems(OrderTranHeader orderTran2) {
		int index = 0;
		if (orderTran2.getOrdTranLineItems() != null) {
			for (OrderTranLineItem orderItem : orderTran2.getOrdTranLineItems()) {

				if (orderItem.getItmTy().equals(BigDecimal.valueOf(2))) {
					itemIndex = index;
					deleteItem();
					break;
				}
				index = index + 1;
			}
		}
		return SUCCESS;
	}

	/**
	 * To Load Sales Agents
	 * 
	 * @return
	 */
	public String load_SalesAgemts() {

		String salesAgentID = "%";
		try {
			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
			if (orderTran != null) {
				String custLinkedSalesAgentId = orderTran.getCustomer().getCustomerHeader().getEmId();
				LinkedHashMap<String, String> oldSalesAgentMap = new LinkedHashMap<String, String>();
				EmployeeIfc[] employees = AllEmployee(salesAgentID);

				if (employees != null && employees.length > 0) {
					for (int i = 0; i < employees.length; i++) {
						/*
						 * if(employees[i].getEmployeeId().equalsIgnoreCase( employee.getEmployeeId()))
						 */
						if (employees[i].getEmployeeId()
								.equalsIgnoreCase(orderTran.getCustomer().getCustomerHeader().getEmId())) {
							// As per uat feedback from users who want to search by sales agent id
							oldSalesAgentMap.put(employees[i].getEmployeeId(),
									employees[i].getEmployeeName() + " - " + employees[i].getEmployeeId());
							break;
						}
					}

					for (int i = 0; i < employees.length; i++) {
						// As per uat feedback from users who want to search by sales agent id
						oldSalesAgentMap.put(employees[i].getEmployeeId(),
								employees[i].getEmployeeName() + " - " + employees[i].getEmployeeId());
					}
				}
				// as per feedback sorting the salesagents list in alphabetical order
				// @Laxmikanth
				Map<String, String> sortedSalesAgentsMap = oldSalesAgentMap.entrySet().stream()
						.sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey,
								Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
				setSalesAgentList(sortedSalesAgentsMap);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;

	}

	/**
	 * @param salesAgentID
	 * @return
	 * @throws Exception
	 */
	private EmployeeIfc[] AllEmployee(String salesAgentID) throws Exception {

		LookUpEmployeeIfc empLookup = DKartContext.getLookupEmployee();
		EmployeeSearchCriteriaIfc empsearchCriteria = new EmployeeSearchCriteria();
		empsearchCriteria.setEmployeeId(salesAgentID);
		EmployeeIfc[] employees = empLookup.lookupSalesAgent(empsearchCriteria);
		return employees;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String getTranLevelAgentName() throws Exception {
		String tranEmpName = "";
		EmployeeIfc[] employees = null;
		orderTran = (OrderTranHeader) getFromSession(SESSION.QUOTE_DETAILS);
		if (orderTran != null && orderTran.getEmId() != null) {
			employees = AllEmployee(orderTran.getEmId());
			if (employees != null)
				tranEmpName = employees[0].getEmployeeName();
		}

		return tranEmpName;
	}

	/**
	 * Modify Transaction Level SalesAgent
	 * 
	 * @return
	 */
	public String modifyTransactionSalesAgent() {
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		EmployeeIfc[] employees = null;
		if (agentId != null) {
			String emId = agentId;
			orderTran.setEmId(emId);
		}
		return SUCCESS;
	}

	/**
	 * To display Total discount applied on item as a label on sales screen
	 * 
	 * @param orderTranLineItem
	 */
	private void totalApplyedDiscountAmtOnItem(OrderTranLineItem orderTranLineItem) {
		enableDoubleDiscounts = enableDoubleDiscountsParam.equalsIgnoreCase("NO") ? false : true;
		BigDecimal discountAmt = BigDecimal.ZERO, discountPer = BigDecimal.ZERO;
		List<OrderTranDiscountItem> discItemsList = orderTranLineItem.getOrdTranDscItms();
		if (discItemsList != null) {
			for (OrderTranDiscountItem discountItem : discItemsList) {
				BigDecimal dscAmt = discountItem.getDscAmt();
				if (dscAmt != null) {
					discountAmt = discountAmt.add(dscAmt);
				}

			}
		}
		// discountAmt = discountAmt.setScale(2, RoundingMode.HALF_DOWN);
		discountAmt = ConfigUtils.getInstance().createBigDecimal(discountAmt, format);
		orderTranLineItem.setTotalApplyedDiscountAmtOnItem(discountAmt);
	}

	/**
	 * To check any manual disc applied before on whole transaction or any item in
	 * transaction
	 * 
	 * @return
	 */
	private boolean isManualDiscsApplyedBefore(OrderTranHeader ordTrn) {
		for (OrderTranLineItem ordLineItm : ordTrn.getOrdTranLineItems()) {
			List<OrderTranDiscountItem> discItemsList = ordLineItm.getOrdTranDscItms();
			if (discItemsList != null) {

				for (OrderTranDiscountItem discountItem : discItemsList) {
					// To display double discounts warning message dialog while trying to apply
					// manual disc on already applied transaction/item
					if (discountItem.getTyDsc() != null && discountItem.getTyDsc().compareTo(BigDecimal.ZERO) == 0 // itm
																													// level
																													// Discount
							&& discountItem.getPrmId() == null)// if manual discount
					{// item level manual discounts are applied previously on this item

						setAnyItemLevelManualDiscuntsApplyed(true);
						return true;
					} else if (discountItem.getTyDsc() != null
							&& discountItem.getTyDsc().compareTo(new BigDecimal(1)) == 0 // tran level Discount
							&& discountItem.getPrmId() == null)// if manual discount
					{// tran level manual discounts are applied previously on this item

						setAnyTranLevelManualDiscuntsApplyed(true);
						return true;
					}
				}
			}
		}
		setAnyItemLevelManualDiscuntsApplyed(false);
		setAnyTranLevelManualDiscuntsApplyed(false);
		return true;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public String getSearchcustomer() {
		return searchcustomer;
	}

	public void setSearchcustomer(String searchcustomer) {
		this.searchcustomer = searchcustomer;
	}

	public String getItemIDDesc() {
		return itemIDDesc;
	}

	public void setItemIDDesc(String itemIDDesc) {
		this.itemIDDesc = itemIDDesc;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	/*
	 * @Override public void setSession(Map<String, Object> map) { sessionMap =
	 * (SessionMap)map;
	 * 
	 * }
	 */

	public OrderTranHeader getOrderTran() {
		return orderTran;
	}

	public void setOrderTran(OrderTranHeader orderTran) {
		this.orderTran = orderTran;
	}

	public CustomerIfc getCustomer() {
		return customer;
	}

	public String getAvailQty() {
		return availQty;
	}

	public void setAvailQty(String availQty) {
		this.availQty = availQty;
	}

	public void setCustomer(CustomerIfc customer) {
		this.customer = customer;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public int getTranType() {
		return tranType;
	}

	public void setTranType(int tranType) {
		this.tranType = tranType;
	}

	/*
	 * public EmployeeIfc getEmployee() { return employee; }
	 * 
	 * public void setEmployee(EmployeeIfc employee) { this.employee = employee; }
	 */
	public boolean isCheckInv() {
		return checkInv;
	}

	public void setCheckInv(boolean checkInv) {
		this.checkInv = checkInv;
	}

	public boolean isInventoryAvail() {
		return inventoryAvail;
	}

	public void setInventoryAvail(boolean inventoryAvail) {
		this.inventoryAvail = inventoryAvail;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getRestockingFeeServiceitemId() {
		return restockingFeeServiceitemId;
	}

	public void setRestockingFeeServiceitemId(String restockingFeeServiceitemId) {
		this.restockingFeeServiceitemId = restockingFeeServiceitemId;
	}

	public String getTransportationFeeServiceitemId() {
		return transportationFeeServiceitemId;
	}

	public void setTransportationFeeServiceitemId(String transportationFeeServiceitemId) {
		this.transportationFeeServiceitemId = transportationFeeServiceitemId;
	}

	public String getItemEditPricOvrrReasonCodes() {
		return itemEditPricOvrrReasonCodes;
	}

	public void setItemEditPricOvrrReasonCodes(String itemEditPricOvrrReasonCodes) {
		this.itemEditPricOvrrReasonCodes = itemEditPricOvrrReasonCodes;
	}

	public String getItemEditDiscPerReasonCodes() {
		return itemEditDiscPerReasonCodes;
	}

	public void setItemEditDiscPerReasonCodes(String itemEditDiscPerReasonCodes) {
		this.itemEditDiscPerReasonCodes = itemEditDiscPerReasonCodes;
	}

	public String getItemEditDiscAmtReasonCodes() {
		return itemEditDiscAmtReasonCodes;
	}

	public void setItemEditDiscAmtReasonCodes(String itemEditDiscAmtReasonCodes) {
		this.itemEditDiscAmtReasonCodes = itemEditDiscAmtReasonCodes;
	}

	public String getReturntReasonCode() {
		return returntReasonCode;
	}

	public void setReturntReasonCode(String returntReasonCode) {
		this.returntReasonCode = returntReasonCode;
	}

	public Map<String, String> getReturntReasonCodeMap() {
		return returntReasonCodeMap;
	}

	public void setReturntReasonCodeMap(Map<String, String> returntReasonCodeMap) {
		this.returntReasonCodeMap = returntReasonCodeMap;
	}

	public String getItemNewQty() {
		return itemNewQty;
	}

	public void setItemNewQty(String itemNewQty) {
		this.itemNewQty = itemNewQty;
	}

	public String getItemNewPrice() {
		return itemNewPrice;
	}

	public void setItemNewPrice(String itemNewPrice) {
		this.itemNewPrice = itemNewPrice;
	}

	public String getItemNewDiscPer() {
		return itemNewDiscPer;
	}

	public void setItemNewDiscPer(String itemNewDiscPer) {
		this.itemNewDiscPer = itemNewDiscPer;
	}

	public String getItemNewDiscAmt() {
		return itemNewDiscAmt;
	}

	public void setItemNewDiscAmt(String itemNewDiscAmt) {
		this.itemNewDiscAmt = itemNewDiscAmt;
	}

	public String getItemNewSalesAgent() {
		return itemNewSalesAgent;
	}

	public void setItemNewSalesAgent(String itemNewSalesAgent) {
		this.itemNewSalesAgent = itemNewSalesAgent;
	}

	public int getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(int itemIndex) {
		this.itemIndex = itemIndex;
	}

	public String getPriceOverrdReasonCode() {
		return priceOverrdReasonCode;
	}

	public void setPriceOverrdReasonCode(String priceOverrdReasonCode) {
		this.priceOverrdReasonCode = priceOverrdReasonCode;
	}

	public String getTranDiscPer() {
		return tranDiscPer;
	}

	public void setTranDiscPer(String tranDiscPer) {
		this.tranDiscPer = tranDiscPer;
	}

	public String getDiscReasonCode() {
		return discReasonCode;
	}

	public void setDiscReasonCode(String discReasonCode) {
		this.discReasonCode = discReasonCode;
	}

	public String getTranDiscAmt() {
		return tranDiscAmt;
	}

	public void setTranDiscAmt(String tranDiscAmt) {
		this.tranDiscAmt = tranDiscAmt;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getDeleveryAddr() {
		return deleveryAddr;
	}

	public void setDeleveryAddr(String deleveryAddr) {
		this.deleveryAddr = deleveryAddr;
	}

	public String getLpoNum() {
		return lpoNum;
	}

	public void setLpoNum(String lpoNum) {
		this.lpoNum = lpoNum;
	}

	public String getLpoDate() {
		return lpoDate;
	}

	public void setLpoDate(String lpoDate) {
		this.lpoDate = lpoDate;
	}

	public String getDeliveryNotes() {
		return deliveryNotes;
	}

	public void setDeliveryNotes(String deliveryNotes) {
		this.deliveryNotes = deliveryNotes;
	}

	public ArrayList<String> getShipmentMethods() {
		return shipmentMethods;
	}

	public void setShipmentMethods(ArrayList<String> serviceItemsList) {
		this.shipmentMethods = serviceItemsList;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getJsonOrderTranResponse() {
		return jsonOrderTranResponse;
	}

	public void setJsonOrderTranResponse(String jsonOrderTranResponse) {
		this.jsonOrderTranResponse = jsonOrderTranResponse;
	}

	public String getVpn() {
		return vpn;
	}

	public void setVpn(String vpn) {
		this.vpn = vpn;
	}

	public String getLpoSlipFileName() {
		return lpoSlipFileName;
	}

	public void setLpoSlipFileName(String lpoSlipFileName) {
		this.lpoSlipFileName = lpoSlipFileName;
	}

	public File getLpoSlip() {
		return lpoSlip;
	}

	public void setLpoSlip(File lpoSlip) {
		this.lpoSlip = lpoSlip;
	}

	public void setSalesAgentList(Map<String, String> salesAgentName) {
		this.slsAgntLst = salesAgentName;
	}

	public Map<String, String> getSalesAgentList() {
		return slsAgntLst;
	}

	public String getAgentIndex() {
		return agentId;
	}

	public void setAgentIndex(String agentId) {
		this.agentId = agentId;
	}

	public int getItemActiveIndex() {
		return itemActiveIndex;
	}

	public void setItemActiveIndex(int itemActiveIndex) {
		this.itemActiveIndex = itemActiveIndex;
	}

	public boolean isEditOrder() {
		return editOrder;
	}

	public void setEditOrder(boolean editOrder) {
		this.editOrder = editOrder;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getRegistryId() {
		return registryId;
	}

	public void setRegistryId(String registryId) {
		this.registryId = registryId;
	}

	public PLUItemIfc getPriceLookupPLU() {
		return priceLookupPLU;
	}

	public void setPriceLookupPLU(PLUItemIfc priceLookupPLU) {
		this.priceLookupPLU = priceLookupPLU;
	}

	public String getLpoSlipContentType() {
		return lpoSlipContentType;
	}

	public void setLpoSlipContentType(String lpoSlipContentType) {
		this.lpoSlipContentType = lpoSlipContentType;
	}

	public boolean isEnableDoubleDiscounts() {
		return enableDoubleDiscounts;
	}

	public void setEnableDoubleDiscounts(boolean enableDoubleDiscounts) {
		this.enableDoubleDiscounts = enableDoubleDiscounts;
	}

	public boolean isAnyItemLevelManualDiscuntsApplyed() {
		return anyItemLevelManualDiscuntsApplyed;
	}

	public void setAnyItemLevelManualDiscuntsApplyed(boolean anyItemLevelManualDiscuntsApplyed) {
		this.anyItemLevelManualDiscuntsApplyed = anyItemLevelManualDiscuntsApplyed;
		orderTran.setAnyItemLevelManualDiscuntsApplyed(anyItemLevelManualDiscuntsApplyed);
	}

	public boolean isAnyTranLevelManualDiscuntsApplyed() {
		return anyTranLevelManualDiscuntsApplyed;
	}

	public void setAnyTranLevelManualDiscuntsApplyed(boolean anyTranLevelManualDiscuntsApplyed) {
		this.anyTranLevelManualDiscuntsApplyed = anyTranLevelManualDiscuntsApplyed;
		orderTran.setAnyTranLevelManualDiscuntsApplyed(anyTranLevelManualDiscuntsApplyed);
	}

	public Map<Integer, String> getSheduledDeliverTimePeriodMap() {
		return sheduledDeliverTimePeriodMap;
	}

	public void setSheduledDeliverTimePeriodMap(Map<Integer, String> sheduledDeliverTimePeriodMap) {
		this.sheduledDeliverTimePeriodMap = sheduledDeliverTimePeriodMap;
	}

	public String getShipmentMethod() {
		return shipmentMethod;
	}

	public void setShipmentMethod(String shipmentMethod) {
		this.shipmentMethod = shipmentMethod;
	}

	public boolean isCreditLimitOverride() {
		return creditLimitOverride;
	}

	public void setCreditLimitOverride(boolean creditLimitOverride) {
		this.creditLimitOverride = creditLimitOverride;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMaxDisPerc() {
		return maxDisPerc;
	}

	public void setMaxDisPerc(String maxDisPerc) {
		this.maxDisPerc = maxDisPerc;
	}

	public String getSchdldDlvryNofDys() {
		return schdldDlvryNofDys;
	}

	public void setSchdldDlvryNofDys(String schdldDlvryNofDys) {
		this.schdldDlvryNofDys = schdldDlvryNofDys;
	}

	public boolean isAllowItemSearch() {
		return allowItemSearch;
	}

	public void setAllowItemSearch(boolean allowItemSearch) {
		this.allowItemSearch = allowItemSearch;
	}

	public boolean isAllowItemDelete() {
		return allowItemDelete;
	}

	public void setAllowItemDelete(boolean allowItemDelete) {
		this.allowItemDelete = allowItemDelete;
	}

	// Added by Srinivas to check customer available credit limit(includes override
	// balance) and proceed to adding line item
	/*
	 * Method to check if customer has sufficient balance to add items to the
	 * transaction
	 */
	public boolean checkCustAvlblCrdtLmt(OrderTranHeader orderTran, boolean setAvlCrdtLmt) {
		boolean isAvlbl = false;
		BigDecimal custAvlLimt = BigDecimal.ZERO;
		if (orderTran.getCreditLimitOverride() != null) {
			custAvlLimt = orderTran.getCreditLimitOverride();
		} else {
			custAvlLimt = orderTran.getTransCrdtLimit();
		}
		if (custAvlLimt == null) { // while registering a claim without invoice --Sharanya
			custAvlLimt = orderTran.getCustomer().getCustomerLimits().getAvCrdtLimit();
		}

		if (orderTran.getOrdTranSum() != null && orderTran.getOrdTranSum().getDkartNetTot() != null
				&& custAvlLimt.compareTo(orderTran.getOrdTranSum().getDkartNetTot()) >= 0) {
			isAvlbl = true;
		}

		if (orderTran.getOrdTranSum() != null && orderTran.getOrdTranSum().getDkartNetTot() != null && setAvlCrdtLmt) {
			custAvlLimt = custAvlLimt.subtract(orderTran.getOrdTranSum().getDkartNetTot());
			orderTran.setAvailCrdtLimit(custAvlLimt);
		}

		return isAvlbl;
	}

	/*
	 * Method to undo recent item updates
	 */
	public void undoItemUpdates(OrderTranHeader orderTran, String rmvItemId, BigDecimal removeQty) {
		try {
			putInSession(SESSION.ORDER_TRAN_TOT, orderTran.getOrdTranSum().getDkartNetTot());
			int totalLines = orderTran.getOrdTranLineItems().size();
			orderTran.setOvrrdTranTotal(orderTran.getOrdTranSum().getDkartNetTot());
			RisplApplyDiscountRulesIfc applyDiscRule = DKartContext.getLookupTransOfQuote();
			PromotionsService promotionsService = applyDiscRule.getPromotionsService();

			List<OrderTranLineItem> lineItems = orderTran.getOrdTranLineItems();

			Map<String, String> seqItmId = new HashMap<String, String>();
			Map<String, BigDecimal> seqItmQty = new HashMap<String, BigDecimal>();
			for (OrderTranLineItem orderTranLineItem : lineItems) {
				seqItmId.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", orderTranLineItem.getItemId());
				if (seqItmQty.containsKey(orderTranLineItem.getId().getOrdLnItmSeq() + "")) {
					seqItmQty.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", seqItmQty
							.get(orderTranLineItem.getId().getOrdLnItmSeq() + "").add(orderTranLineItem.getLineQnt()));
				} else {
					seqItmQty.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", orderTranLineItem.getLineQnt());
				}
			}

			List<OrderTranLineItem> mdfyLineItem = new ArrayList<OrderTranLineItem>();

			BigDecimal rmvdQty = BigDecimal.ZERO;
			for (OrderTranLineItem line : lineItems) {
				if (line.getItemId().equalsIgnoreCase(rmvItemId) && rmvdQty.compareTo(removeQty) == -1) {
					rmvdQty = rmvdQty.add(line.getLineQnt());
					mdfyLineItem.add(line);
					seqItmId.remove(line.getId().getOrdLnItmSeq() + "");
					seqItmQty.remove(line.getId().getOrdLnItmSeq() + "");
				} else if (rmvdQty.compareTo(removeQty) != -1) {
					break;
				}
			}

			lineItems.removeAll(mdfyLineItem);

			if (rmvdQty.compareTo(removeQty) == 1) {
				List<OrderTranLineItem> rmvSplttdLines = new ArrayList<OrderTranLineItem>();

				List<OrderTranLineItem> splttdLinItms = promotionsService.splitTranLineItems(mdfyLineItem);
				rmvdQty = BigDecimal.ZERO;
				for (OrderTranLineItem splttdLinItm : splttdLinItms) {
					if (rmvdQty.compareTo(removeQty) == -1) {
						rmvdQty = rmvdQty.add(splttdLinItm.getLineQnt());
						rmvSplttdLines.add(splttdLinItm);
					} else if (rmvdQty.compareTo(removeQty) != -1) {
						break;
					}
				}

				splttdLinItms.removeAll(rmvSplttdLines);
				if (orderTran.isGroupDiscLinItms()) {
					splttdLinItms = promotionsService.groupLineItems(splttdLinItms);
				} else {
					splttdLinItms = promotionsService.groupNDLineItems(splttdLinItms);
				}

				for (OrderTranLineItem line : splttdLinItms) {
					for (int i = 1; i <= totalLines; i++) {
						if (!seqItmId.containsKey(i + "")) {
							seqItmId.put(i + "", line.getItemId());
							seqItmQty.put(i + "", line.getLineQnt());
							line.getId().setOrdLnItmSeq(i);
						}
					}
				}
				if (orderTran.isGroupDiscLinItms()) {
					rmvSplttdLines = promotionsService.groupLineItems(rmvSplttdLines);
				} else {
					rmvSplttdLines = promotionsService.groupNDLineItems(rmvSplttdLines);
				}
				for (OrderTranLineItem line : rmvSplttdLines) {
					boolean found = false;
					for (int i = 1; i <= totalLines; i++) {
						if (!seqItmId.containsKey(i + "")) {
							line.getId().setOrdLnItmSeq(i);
							found = true;
						}
					}
					if (!found) {
						totalLines++;
						line.getId().setOrdLnItmSeq(totalLines);
						found = true;
					}
				}
				lineItems.addAll(splttdLinItms);
				mdfyLineItem.clear();
				mdfyLineItem.addAll(rmvSplttdLines);
			}

			// Collections.reverse(lineItems);
			// Collections.reverse(mdfyLineItem);

			if (mdfyLineItem.size() > 0) {

				putInSession(SESSION.RELOAD_ORDER_TRANSACTION, mdfyLineItem);
				if (orderTran.isGroupDiscLinItms()) {
					lineItems = promotionsService.groupLineItems(lineItems);
				} else {
					lineItems = promotionsService.groupNDLineItems(lineItems);
				}
				List<OrderTranLineItem> groupedLineItms = promotionsService.reAssignLineSequenceNumbers(lineItems,
						seqItmId, seqItmQty);
				orderTran.setOrdTranLineItems(groupedLineItms);
				updateTotals(orderTran);
			}
			putInSession(SESSION.ORDER_TRANSACTION, orderTran);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public boolean getManagerOverride() {
		try {
			managerOverride = DKartContext.getParamterBean().fetchXMLParameterValues()
					.getEnableBookOrderManagerOverride().equalsIgnoreCase("YES") ? true : false;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e.getCause());
		}
		return managerOverride;
	}

	public String getExciseTax() {

		return exciseTax;
	}

	public void setExciseTax(String exciseTax) {
		this.exciseTax = exciseTax;
	}

	public String getReturnPriceFromExl() {
		return returnPriceFromExl;
	}

	public void setReturnPriceFromExl(String returnPriceFromExl) {
		this.returnPriceFromExl = returnPriceFromExl;
	}

	public boolean isLPODuplicate() {
		return isLPODuplicate;
	}

	public void setLPODuplicate(boolean isLPODuplicate) {
		this.isLPODuplicate = isLPODuplicate;
	}
	
	
}
