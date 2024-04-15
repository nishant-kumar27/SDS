package rispl.dkart.services.ejb.transaction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.retailsols.sds.transaction.ejb.SDSEntityBuilder;
import com.retailsols.sds.transaction.ejb.TransactionService;
import com.rispl.dk.claim.model.DiffClaimsList;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;
import com.test.entities.OrderDetail;
import com.test.entities.OrderDetailsWithInvoice;

import rispl.db.model.claim.ClaimTranDscItm;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.ClaimTranSum;
import rispl.db.model.employee.RisplDkEmpMstr;
import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dk.customer.Customer;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.ConstantsEnum;
import rispl.dkart.order.shipped.OrderShipmentDetail;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.PromotionsService;
import rispl.dkart.services.detail.claim.ClaimDetailTable;
import rispl.dkart.services.ejb.LookUpCustomer;
import rispl.dkart.services.ejb.LookUpEmployee;
import rispl.dkart.services.ejb.LookUpItem;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.customer.CustomerLimit;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dkart.services.entities.tenders.TranLineItemTender;
import rispl.dkart.services.entities.tenders.TranLineItemTenderPK;
import rispl.dkart.services.entities.transaction.DkartReasonCodes;
import rispl.dkart.services.entities.transaction.ExciseTaxItem;
import rispl.dkart.services.entities.transaction.OrdInvShpQtySrlno;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItem;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItemPK;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeaderPK;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkart.services.entities.transaction.OrderTranLineItemPK;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import rispl.dkart.services.entities.transaction.OrderTranSumPK;
import rispl.dkart.services.entities.transaction.lpo.OrderTransactionLpo;
import rispl.dkart.services.entities.transaction.lpo.OrderTransactionLpoPK;
import rispl.dkart.services.payment.PaymentDetails;
import rispl.dkart.services.transaction.save.SavePostPaymentTransaction;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;
import rispl.dkservices.common.OrderTransactionSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.dkservices.common.SearchCriteria;
import rispl.rms.inventory.lookup.RMSInventoryDetailService;
import rispl.services.transaction.find.AbstractOrderTransactionSerivce;
import rispl.services.transaction.find.OrderTransactionException;
import utility.ConfigUtils;

/*
 * 10/11/2016 -Krishna: commented the code in method SaveClaimTransaction for update of customer
 *                      credit limit. As credit limit should be update from AR
 * 14/11/2016 -Krishna: Moved the code for post payment transaction to SavePostPaymentTransaction Class
 * 
 * 05/03/17 -Hanu : 1.Change the code to retrive the suspended transctions from view insted of table to resolve performance issue.
 *                  2.Wrote code to get orderdetails by orderId came from view.
 *                  
 * 20/06/17 -Srinivas G: 1.Added the method to get the serial numbers of items.  
 * 
 * 10/08/17 -Srinivas G: 1.Changed the method to get serial numbers of items from getSerialNos() to getItemSerialNumbers().
 * 
 */

@Stateless(mappedName = "lookUpTransactions")
@LocalBean
public class OrderTransactions extends AbstractOrderTransactionSerivce implements OrderTransactionsIfc {

	private static final Logger LOGGER = LogManager.getLogger(OrderTransactions.class);

	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	@Inject
	protected LookUpCustomer lookupCustomer;

	@Inject
	protected TransactionService transactionService;

	@Inject
	protected LookUpEmployee lookUpEmployee;

	@Inject
	PromotionsService promoService;

	ParameterConfigurationServiceIfc parameterService;

	public ParameterConfigurationServiceIfc getParameterService() {
		return parameterService;
	}

	@EJB(mappedName = "sdsparameterService")
	public void setParameterService(ParameterConfigurationServiceIfc parameterService) {
		this.parameterService = parameterService;
	}

	@PostConstruct
	void init() {
		super.emf = emf;
		super.lookupCustomer = lookupCustomer;
	}

	@EJB
	LookUpItem lookupItem;

	@Override
	public OrderTranHeader[] getTransactionsInvoices(OrderTransactionSearchCriteriaIfc criteria) {

		OrderTranHeader[] transactionHeaders = null;
		try {
			LOGGER.info("lookup for order transactions using the criteria: \n\t" + criteria);
			transactionHeaders = lookUpOrdersOrInvoices(criteria);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception while looking up for the order" + e.getMessage());
		}
		return transactionHeaders;

	}

	// code to dispay Order date in invoice_Details page. @jagadish
	@Override
	public List<OrderDetailsWithInvoice> getOrderDateForOrderId(String orderNum) {
		List<OrderDetailsWithInvoice> OrderDetailsWithInvoice = new ArrayList<>();
		try {
			LOGGER.info("lookup for order Information using the criteria......");
			OrderDetailsWithInvoice = getCompleteOrderInfo(orderNum);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception while looking up for the order" + e.getMessage());
		}
		return OrderDetailsWithInvoice;
	}

	// process start by jagadish for need to display discount Reason code
	// description in orders & invoice details page
	//@Override
	//public List<DkartReasonCodes> getAllDisRsnCode() {
		//List<DkartReasonCodes> DkartReasonCodes = new ArrayList<>();
		//try {
			//LOGGER.info("lookup for order transactions using the criteria......");
			//DkartReasonCodes = getAllDisRsnCodeDiscrpn();

		//} catch (Exception e) {
			//e.printStackTrace();
			//LOGGER.error("Exception while looking up for the order" + e.getMessage());
		//}
		//return DkartReasonCodes;
	//} -->
	
	//kunal
	public List<DkartReasonCodes> getAllDisRsnCode() {
	      Object DkartReasonCodes = new ArrayList();

	      try {
	         LOGGER.info("lookup for order transactions using the criteria......");
	         DkartReasonCodes = this.getAllDisRsnCodeDiscrpn();
	      } catch (Exception var3) {
	         var3.printStackTrace();
	         LOGGER.error("Exception while looking up for the order" + var3.getMessage());
	      }

	      return (List)DkartReasonCodes;
	   }

	// process start by lalit for need to display excise tax in orders & invoice
	// details page
	@Override
	public List<ExciseTaxItem> getExciseTax() {
		List<ExciseTaxItem> ExciseTaxItem = new ArrayList<>();
		try {
			LOGGER.info("lookup for order transactions using the criteria......");
			ExciseTaxItem = getAllExciseTaxItem();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception while looking up for the order" + e.getMessage());
		}
		return ExciseTaxItem;
	}

	// To print comment in cancel print page by jagadish
	@Override
	public String getTransactionsPrintcomment(String criteria) {
		String result = null;
		try {
			LOGGER.info("lookup for order transactions using the criteria......");
			result = lookUpOrdersOrInvoicesForComment(criteria);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception while looking up for the order" + e.getMessage());
		}
		return result;
	}

	// process for disable option
	@Override
	public String disablePermissionForEmpl(Long empRoleId, String functionId) {
		String Disable_option = null;
		try {
			LOGGER.info("lookup for order transactions using the criteria......");
			Disable_option = disablePermissionForAll(empRoleId, functionId);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception while looking up for the order" + e.getMessage());
		}

		return Disable_option;
	}

	// Krishna
	@Override
	public OrderTranHeader[] getCustomerSiteInvoices(OrderTransactionSearchCriteriaIfc criteria) {

		OrderTranHeader[] transactionHeaders = null;
		try {
			LOGGER.info("lookup for order transactions using the criteria: \n\t" + criteria);
			transactionHeaders = lookUpOrderInvoices(criteria);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception while looking up for the order" + e.getMessage());
		}
		return transactionHeaders;
	}

	@Override
	public OrderTranHeader[] getCustomerSiteAllInvoices(OrderTransactionSearchCriteriaIfc criteria, String emplID,
			String empRoleAcc, List<Integer> divIds) {

		OrderTranHeader[] transactionHeaders = null;
		try {
			LOGGER.info("lookup for order transactions using the criteria: \n\t" + criteria);
			transactionHeaders = lookUpOrderAllInvoices(criteria, emplID, empRoleAcc, divIds);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception while looking up for the order" + e.getMessage());
		}
		return transactionHeaders;

	}

	public OrderTranHeader createNewOrder(OrderTranHeader transaction) {

		LOGGER.info("creating new order Id......");
		OrderTranHeaderPK headerPk = new OrderTranHeaderPK();
		String storeID = "";
		try {
			// Krishna
			// storeID = Utils.getInstance().getSDSStoreID("123");
			if (transaction.getCustomer().getCustomerHeader().getCustomerHeaderPK() != null) {
				storeID = lookupCustomer.getCustomerStoreID(
						transaction.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId());
			}

			if (storeID.equalsIgnoreCase("")) {
				storeID = ConfigUtils.getInstance().getSDSStoreID();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				storeID = ConfigUtils.getInstance().getSDSStoreID();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		headerPk.setRtStrId(storeID);// TODO: read from the properties file or get the customer segment mapped store
										// id from properties file
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String tranDate = format.format(new Date());
		headerPk.setDcDyOrd(tranDate);
		try {
			headerPk.setOrdWs(ConfigUtils.getInstance().getSDSWorkstationID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		} // TODO: need to check what could be the workstation id

		long tranSequenceId = transactionService.getTranSeq(storeID);
		headerPk.setTrnSeq(tranSequenceId);
		transaction.setId(headerPk);
		transaction.setTsOrdBgn(new Date());
		OrderTranSum sum = new OrderTranSum();

		sum.setIdOrd(getOrderId(transaction));
		// setting order tran sum pk information
		OrderTranSumPK sumPK = new OrderTranSumPK();
		sumPK.setRtStrId(headerPk.getRtStrId());
		sumPK.setOrdWs(headerPk.getOrdWs());
		sumPK.setTrnSeq(headerPk.getTrnSeq());
		sumPK.setDcDyOrd(headerPk.getDcDyOrd());
		sum.setId(sumPK);
		sum.setOrdTranHeader(transaction);
		transaction.setOrdTy("23");
		transaction.setScOrd(BigDecimal.ZERO); // sc_ord = 0
		transaction.setTransactionStatus(BigDecimal.ONE); // sc_tran = 1
		Date currTime = new java.util.Date();
		transaction.setTsOrdBgn(currTime);
		transaction.setTsCrtRcrd(currTime);

		/*
		 * List<OrderTranSum> ordTranSums = new ArrayList<OrderTranSum>();
		 * ordTranSums.add(sum);
		 */

		transaction.setOrdTranSum(sum);
		LOGGER.info("Fetching all Reason Codes");
		transaction.setReasonCodes(getAllReasonCodes());
		LOGGER.info("Feteched Reason Code" + transaction.getReasonCodes().size());

		// persist the order tran object to avoid missing tran gap in resa
		try {

			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			em.persist(transaction);

			em.getTransaction().commit();

		} catch (Exception e) {
			LOGGER.error("Error Occured in saving the order tran object before quote", e);
		}

		return transaction;
	}

	public String getOrderId(OrderTranHeader transaction) {
		StringBuffer sb = new StringBuffer();
		LOGGER.info("setting up new order Id......");
		try {
			sb.append(transaction.getId().getRtStrId());
			sb.append(transaction.getId().getOrdWs());
			sb.append(transaction.getId().getDcDyOrd().replace("-", ""));
			sb.append(transaction.getId().getTrnSeq());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("error while setting up the order id in getOrderId " + e.getMessage());
		}
		return sb.toString();
	}

	@Override
	public boolean saveOrderTransaction(OrderTranHeader transaction) throws OrderTransactionException {

		LOGGER.info("persisting order transaction");
		boolean transactionSavedSuccessfully = saveCompletedTransactions(transaction);
		LOGGER.info("transaction save " + transactionSavedSuccessfully);
		return transactionSavedSuccessfully;
	}

	@Override
	public OrderTranHeader saveQuote(OrderTranHeader transaction) throws OrderTransactionException {
		EntityManager em = getEntityManager();

		try {
			if (!em.getTransaction().isActive())
				em.getTransaction().begin();
			LOGGER.warn("Quoting the transaction" + transaction);

			transaction.setIdTrlogBtch("-1");
			transaction.setOrdTy("23");
			transaction.setIdTlogBtch(new BigDecimal("-1"));

			/*
			 * //validate lpo number for duplicate if(checkLPONumExist(transaction)) return
			 * null;
			 */

			// Assign primary keys to order_tran_line_item
			long count = 1;
			for (OrderTranLineItem orderTranLineItem : transaction.getOrdTranLineItems()) {
				System.out.println(orderTranLineItem);
				OrderTranLineItemPK orderTranLineItemPK = new OrderTranLineItemPK();
				if (orderTranLineItem.getId() != null) {
					orderTranLineItemPK = orderTranLineItem.getId();
				} else {
					orderTranLineItemPK.setOrdLnItmSeq(count);
					count++;
				}

				orderTranLineItemPK.setDcDyOrd(transaction.getId().getDcDyOrd());
				orderTranLineItemPK.setOrdWs(transaction.getId().getOrdWs());
				orderTranLineItemPK.setRtStrId(transaction.getId().getRtStrId());
				orderTranLineItemPK.setTrnSeq(transaction.getId().getTrnSeq());

				orderTranLineItem.setId(orderTranLineItemPK);
				if (orderTranLineItem.getPluItem() != null
						&& orderTranLineItem.getPluItem().getItem().getItm() != null) {
					orderTranLineItem.setUomSls(orderTranLineItem.getPluItem().getItem().getItm().getSlsUomCd());
				}
				orderTranLineItem.setOrdTranHeader(transaction);

				// Set primary keys for order_tran_discount_items
				List<OrderTranDiscountItem> discountItems = orderTranLineItem.getOrdTranDscItms();
				if (discountItems != null) {
					long count1 = 1;
					for (OrderTranDiscountItem orderTranDiscountItem : orderTranLineItem.getOrdTranDscItms()) {
						OrderTranDiscountItemPK orderTranDiscountItemPK = new OrderTranDiscountItemPK();
						orderTranDiscountItemPK.setDcDyOrd(orderTranLineItem.getId().getDcDyOrd());
						orderTranDiscountItemPK.setDiscSeqNum(count1);
						orderTranDiscountItemPK.setOrdLnItmSeq(orderTranLineItem.getId().getOrdLnItmSeq());
						orderTranDiscountItemPK.setOrdWs(orderTranLineItem.getId().getOrdWs());
						orderTranDiscountItemPK.setRtStrId(orderTranLineItem.getId().getRtStrId());
						orderTranDiscountItemPK.setTrnSeq(orderTranLineItem.getId().getTrnSeq());
						count1++;

						orderTranDiscountItem.setId(orderTranDiscountItemPK);
						orderTranDiscountItem.setOrdTranLineItem(orderTranLineItem);
					}
					if (discountItems.size() > 0) {
						orderTranLineItem.setFlItmDsc("0");
					} else {
						orderTranLineItem.setFlItmDsc("1");
					}

				}

			}

			// Assign primary keys to order_tran_sum
			OrderTranSum orderTranSum = transaction.getOrdTranSum();

			OrderTranSumPK orderTranSumPK = new OrderTranSumPK();

			orderTranSumPK.setDcDyOrd(transaction.getId().getDcDyOrd());
			orderTranSumPK.setOrdWs(transaction.getId().getOrdWs());
			orderTranSumPK.setRtStrId(transaction.getId().getRtStrId());
			orderTranSumPK.setTrnSeq(transaction.getId().getTrnSeq());

			orderTranSum.setId(orderTranSumPK);
			if (transaction.getCustomer() != null) {
				orderTranSum
						.setOrdIdCt(transaction.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId());
			}

			// If transaction is managed and already exists then merge else persist

			if (quoteExists(transaction))
				em.merge(transaction);
			else {
				em.persist(transaction);
			}
			em.getTransaction().commit();
			em.getEntityManagerFactory().getCache().evictAll();
			return transaction;
		} 
		catch (Exception e) 
		{
			return null;

		}

	}

	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	private boolean quoteExists(OrderTranHeader transaction) {
		if (transaction.getOrdTranSum() != null) {
			String orderID = transaction.getOrdTranSum().getIdOrd();
			// check for quote using order id
			Query query = getEntityManager().createNamedQuery("QUOTE_SEARCH_WITH_ORDER_ID", OrderTranSum.class);
			query.setParameter("idOrd", orderID);
			List<OrderTranSum> orderTranSumList = query.getResultList();
			if (orderTranSumList.size() > 0)
				return true;
		}
		return false;
	}

	// Added to check LPO Number Duplicate - Veeresh Singh

	@Override
	public boolean isLPONumberDuplicate(OrderTranHeader transaction, String lpoNum)
			throws OrderTransactionException {
		LOGGER.info("Validating LPO Number for duplicate. Entered LPO Number: " + lpoNum);
		if (transaction.getOrdTranSum() != null) {
			String orderID = transaction.getOrdTranSum().getIdOrd();
			LOGGER.info("Order Id: " + orderID);
			// check for quote using order id
			Query query = getEntityManager().createNamedQuery("QUOTE_SEARCH_WITH_LPO_NUM", OrderTranSum.class);
			query.setParameter("custLpoNum", lpoNum);
			List<OrderTranSum> orderTranSumList = query.getResultList();
			if (orderTranSumList.size() > 0) {
				for (OrderTranSum ots : orderTranSumList) {
					if (orderID != null && !orderID.isEmpty())
						if (!ots.getIdOrd().equals(orderID)) {
							LOGGER.info("LPO Number already exists for Order Number: " + ots.getIdOrd());
							return true;
						}
				}
			}

		}
		return false;
	}

	public boolean updateTrasnactionBatchId(OrderTranHeader transaction) throws OrderTransactionException {

		boolean transactionSavedSuccessfully = saveCompletedTransactions(transaction);

		return transactionSavedSuccessfully;
	}

	/*
	 * Method to Get All reason Codes
	 */
	public Map<String, Map<String, String>> getAllReasonCodes() {
		Map<String, Map<String, String>> reasonCodes = new HashMap<String, Map<String, String>>();
		EntityManager em = getEntityManager();
		Query qe = em.createQuery("select dkartReasonCodes from DkartReasonCodes dkartReasonCodes");
		List<DkartReasonCodes> allReasons = qe.getResultList();
		for (DkartReasonCodes alRsn : allReasons) {
			if (reasonCodes.containsKey(alRsn.getRsnGrpNm())) {
				reasonCodes.get(alRsn.getRsnGrpNm()).put(new Long(alRsn.getRsnCode()).toString(), alRsn.getRsnDesc());
			} else {
				Map<String, String> codeName = new HashMap<String, String>();
				codeName.put(new Long(alRsn.getRsnCode()).toString(), alRsn.getRsnDesc());
				reasonCodes.put(alRsn.getRsnGrpNm(), codeName);
			}
		}
		return reasonCodes;
	}

	public static void main(String[] args) {
		// Map<String, Map<String, String>> reasonCodes = new
		// OrderTransactions().getAllReasonCodes();
		// System.out.println(reasonCodes);
		// ClaimTranHeader ord = new OrderTransactions().getClaimTranHeader("", "1");
		// System.out.println(ord);
	}

	/*
	 * @Override public List<CustomerIfc> getCustInfoAndItemInfo(String Item_Id,
	 * String Customer_Id) { // TODO Auto-generated method stub return
	 * getCustInfoItemId(Item_Id,Customer_Id); }
	 */

	@Override
	public List<CustomerIfc> getOrderIdByItemId(String Item_Id) {
		return getOrdIdListbyItemId(Item_Id);
	}

	/*
	 * Pavan : this method saves all the payments received from the browser/client
	 */
	public boolean savePayments(TranLineItemTender[] tenders) {
		boolean savedSuccessFully = false;
		try {
			saveTenderLineItems(tenders);
			savedSuccessFully = true;
		} catch (Exception e) {
			savedSuccessFully = false;
			e.printStackTrace();
			LOGGER.error("Exception occured while persisting the tender lines " + e.getMessage());
		}
		return savedSuccessFully;
	}

	// TODO: Need to remove this code
	@Override
	@Deprecated
	public PaymentDetails SavePaymentTransaction(PaymentDetails pd) {
		PaymentDetails result = null;
		SavePostPaymentTransaction spt = new SavePostPaymentTransaction();
		result = spt.saveTransaction(pd);
		return result;
	}

	public void updateCustomerCreditLimit(CustomerLimit customerLimits, BigDecimal totalAmountPaid) {
		BigDecimal pendingDue = customerLimits.getPendDue();
		BigDecimal newPendingDue = pendingDue.subtract(totalAmountPaid);

		BigDecimal availableCreditLimit = customerLimits.getAvCrdtLimit();
		BigDecimal newavailableCreditLimit = availableCreditLimit.add(totalAmountPaid);

		customerLimits.setAvCrdtLimit(newavailableCreditLimit);
		customerLimits.setPendDue(newPendingDue);

	}

	// for getting the oders based on custmer info @mallikarjun
	@Override
	public OrderTranHeader[] getOrderbyCustomerInfo(OrderTransactionSearchCriteriaIfc search) {

		OrderTranHeader[] orders = new OrderTranHeader[10];
		orders = lookUpTransactionByCustomerInfo(orders, search);
		return orders;
	}

	// for getting the oders based on custmer id @mallikarjun
	@Override
	public ArrayList<OrderTranHeader[]> getDelOrderbyCustid(String[] searchCustIds,
			CustomerSearchCriteria customerSearchCriteria) {

		ArrayList<OrderTranHeader[]> orders = new ArrayList<OrderTranHeader[]>();
		try {

			orders = lookUpOrdersbyCustIds(searchCustIds);
		} catch (OrderTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	// for getting the oders based on item id @mallikarjun
	public ArrayList<OrderTranHeader[]> getDelOrderIdByItemId(String Item_Id) {
		return getDelOrdIdListbyItemId(Item_Id);
	}

	// Search all ClaimTranHeader on the basis of status
	public Vector<ClaimDetailTable> getClaimTableDetails(String status) {
		OrderTransactionUtility util = new OrderTransactionUtility();
		Vector<ClaimDetailTable> claimDetailTableList = new Vector<>();
		EntityManager em = getEntityManager();

		String claimTranHeaderQuery = "SELECT cth FROM ClaimTranHeader cth WHERE cth.scOrd=?1";
		Query query = em.createQuery(claimTranHeaderQuery, ClaimTranHeader.class);
		query.setParameter(1, new BigDecimal(status));
		List<ClaimTranHeader> claimTranHeaderList = query.getResultList();

		for (ClaimTranHeader claimTranHeade : claimTranHeaderList) {
			ClaimDetailTable cdt = new ClaimDetailTable();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				cdt.setClaimDate(dateFormat.parse(claimTranHeade.getId().getDcDyOrd()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cdt.setClaimTotal(claimTranHeade.getClaimTranSum().getDkartNetTot());
			cdt.setClaimId(claimTranHeade.getClaimId());
			Map<String, String> o = getAllReasonCodes().get("Returns");
			cdt.setReasonCode(o.get(claimTranHeade.getRcRtnMr()));
			cdt.setSalesAgent(util.getEmployee(claimTranHeade.getEmId()));
			try {
				String customerId = claimTranHeade.getClaimTranSum().getOrdIdCt();
				cdt.setCustomerId(customerId);
				Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);

				CustomerHeader header = (CustomerHeader) customerQ.getSingleResult();
				cdt.setCustomerName(header.getCtNm());

			} catch (Exception e) {
				LOGGER.error(e);
			}
			claimDetailTableList.add(cdt);
		}

		return claimDetailTableList;
	}

	// Search claim Search by claim date and totals
	@Override
	public List<ClaimDetailTable> getRjectedClaimDateAndTotalDetails(String status, String emplName,
			List<Integer> divIds, String emplID, String claimID, Date from, Date to, String ClaimTotalFrom,
			String ClaimTotalTo, String itemID, String empRoleAcc) {
		ArrayList<String> claimIdList = new ArrayList<>();
		Vector<ClaimDetailTable> claimDetailTableList = new Vector<>();
		List<ClaimTranHeader> claimTranHeaderList = new ArrayList<>();
		// for display Sales AGnet name in claim
		List<RisplDkEmpMstr> RisplDkEmpMstrList = new ArrayList<>();
		OrderTransactionUtility util = new OrderTransactionUtility();
		EntityManager em = getEntityManager();
		Query query = null;
		Query SalesQuery = null;
		try {
			query = util.getClaimQueryByClaimDateTotalFromTo(divIds, emplName, empRoleAcc, emplID, claimID, from, to,
					ClaimTotalFrom, ClaimTotalTo, em);

			if (query != null) {
				if (util.isStatusRequired(status)) {
					query.setParameter("status", new BigDecimal(status));
				}
				claimTranHeaderList = query.getResultList();
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		for (ClaimTranHeader claimTranHeade : claimTranHeaderList) {
			ClaimDetailTable cdt = new ClaimDetailTable();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				cdt.setClaimDate(dateFormat.parse(claimTranHeade.getId().getDcDyOrd()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				cdt.setClaimTotal(claimTranHeade.getClaimTranSum().getDkartNetTot());
			} catch (Exception e) {
				System.err.println(e);
			}
			cdt.setClaimId(claimTranHeade.getClaimId());
			BigDecimal lineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getLineQntRtn() == null ? BigDecimal.ZERO : line.getLineQntRtn())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal ApprvedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getApprClaimQty() == null ? BigDecimal.ZERO : line.getApprClaimQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal WhreceivedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getWhReceiveQty() == null ? BigDecimal.ZERO : line.getWhReceiveQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("1")) {
				cdt.setQty(lineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("2")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("3")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("4")) {
				cdt.setQty(ApprvedlineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("5")) {
				cdt.setQty(WhreceivedlineQntRtn.toString());
			} else {
				cdt.setQty(lineQntRtn.toString());
			}
			cdt.setStatus(getStatusOfClaim(claimTranHeade.getScOrd()));

			Map<String, String> o = getAllReasonCodes().get("Returns");
			cdt.setReasonCode(o.get(claimTranHeade.getRcRtnMr()));
			cdt.setSalesAgent(claimTranHeade.getEmId());
			try {
				String customerId = claimTranHeade.getClaimTranSum().getOrdIdCt();
				cdt.setCustomerId(customerId);
				Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);

				CustomerHeader header = (CustomerHeader) customerQ.getSingleResult();
				cdt.setCustomerName(header.getCtNm());

			} catch (Exception e) {
				LOGGER.error(e);
			}

			// process for to get sales agent names
			String salesAgnetName = cdt.getSalesAgent();

			SalesQuery = util.getClaimSalesAgentNames(divIds, empRoleAcc, emplID, salesAgnetName, em);

			try {
				if (SalesQuery != null) {
					RisplDkEmpMstrList = SalesQuery.getResultList();
				} else {
					System.out.println("Query is empty: " + SalesQuery);
				}

				for (RisplDkEmpMstr RisplDkEmpMstr : RisplDkEmpMstrList) {
					cdt.setSalesAgent(RisplDkEmpMstr.getEmpFstNme());
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}

			// To avoid Duplicate data in claimDetailTableList
			if (!claimIdList.contains(cdt.getClaimId())) // add only if not present in list
			{
				claimDetailTableList.add(cdt);
				claimIdList.add(cdt.getClaimId());
			}

		}

		return claimDetailTableList;
	}

	// claim search by Claim Id, Order Id , customer Info, itemid
	public Vector<ClaimDetailTable> getRjectedClaimTableDetails(String status, String emplName, List<Integer> divIds,
			String emplID, String orderID, String claimID, String custInfo, String itemID, String empRoleAcc) {
		ArrayList<String> claimIdList = new ArrayList<>();
		Vector<ClaimDetailTable> claimDetailTableList = new Vector<>();
		List<ClaimTranHeader> claimTranHeaderList = new ArrayList<>();
		// for display Sales AGnet name in claim
		List<RisplDkEmpMstr> RisplDkEmpMstrList = new ArrayList<>();
		OrderTransactionUtility util = new OrderTransactionUtility();
		EntityManager em = getEntityManager();
		Query query = null;
		Query SalesQuery = null;

		try {
			if ((custInfo != null && !custInfo.equalsIgnoreCase(""))
					|| (itemID != null && !itemID.equalsIgnoreCase(""))) {
				query = util.getClaimQueryByItemIfo(itemID, custInfo, status, em, empRoleAcc, divIds, emplID);
			} else {
				query = util.getClaimQueryByClaimID(custInfo, empRoleAcc, divIds, emplID, orderID, claimID, itemID,
						status, em);
			}
			if (query != null) {
				if (util.isStatusRequired(status)) {
					query.setParameter("status", new BigDecimal(status));
				}
				claimTranHeaderList = query.getResultList();
			}

		} catch (Exception e) {
			LOGGER.error(e);
			System.err.println(e);
		}

		for (ClaimTranHeader claimTranHeade : claimTranHeaderList) {

			ClaimDetailTable cdt = new ClaimDetailTable();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				cdt.setClaimDate(dateFormat.parse(claimTranHeade.getId().getDcDyOrd()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				cdt.setClaimTotal(claimTranHeade.getClaimTranSum().getDkartNetTot());
			} catch (Exception e) {
				System.err.println(e);
			}
			cdt.setClaimId(claimTranHeade.getClaimId());
			BigDecimal lineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getLineQntRtn() == null ? BigDecimal.ZERO : line.getLineQntRtn())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal ApprvedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getApprClaimQty() == null ? BigDecimal.ZERO : line.getApprClaimQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal WhreceivedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getWhReceiveQty() == null ? BigDecimal.ZERO : line.getWhReceiveQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("1")) {
				cdt.setQty(lineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("2")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("3")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("4")) {
				cdt.setQty(ApprvedlineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("5")) {
				cdt.setQty(WhreceivedlineQntRtn.toString());
			} else {
				cdt.setQty(lineQntRtn.toString());
			}
			cdt.setStatus(getStatusOfClaim(claimTranHeade.getScOrd()));

			Map<String, String> o = getAllReasonCodes().get("Returns");
			cdt.setReasonCode(o.get(claimTranHeade.getRcRtnMr()));
			cdt.setSalesAgent(claimTranHeade.getEmId());
			try {
				String customerId = claimTranHeade.getClaimTranSum().getOrdIdCt();
				cdt.setCustomerId(customerId);
				Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);

				CustomerHeader header = (CustomerHeader) customerQ.getSingleResult();
				cdt.setCustomerName(header.getCtNm());

			} catch (Exception e) {
				LOGGER.error(e);
			}

			// process for to get sales agent names
			String salesAgnetName = cdt.getSalesAgent();

			SalesQuery = util.getClaimSalesAgentNames(divIds, empRoleAcc, emplID, salesAgnetName, em);

			try {
				if (SalesQuery != null) {
					RisplDkEmpMstrList = SalesQuery.getResultList();
				} else {
					System.out.println("Query is empty: " + SalesQuery);
				}

				for (RisplDkEmpMstr RisplDkEmpMstr : RisplDkEmpMstrList) {
					cdt.setSalesAgent(RisplDkEmpMstr.getEmpFstNme());
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}

			// To avoid Duplicate data in claimDetailTableList
			if (!claimIdList.contains(cdt.getClaimId())) // add only if not present in list
			{
				claimDetailTableList.add(cdt);
				claimIdList.add(cdt.getClaimId());
			}

		}

		return claimDetailTableList;
	}

	// jagadish for RejectClaim Search by Claims Criteries
	@Override
	public List<ClaimDetailTable> getRejectedClaimSearchByClaim(String status, String empRoleAcc, List<Integer> divIds,
			String emplID, String claimID, Date parsed_from, Date parsed_to, String claimTotalFrom, String claimTotalTo,
			String itemId) {

		// System.out.println("CID:"+claimID+"CDF:"+parsed_from+"CDT:"+parsed_to+"CTF:"+claimTotalFrom+"CTT:"+claimTotalTo);
		ArrayList<String> claimIdList = new ArrayList<>();
		Vector<ClaimDetailTable> claimDetailTableList = new Vector<>();
		List<ClaimTranHeader> claimTranHeaderList = new ArrayList<>();
		// for display Sales AGnet name in claim
		List<RisplDkEmpMstr> RisplDkEmpMstrList = new ArrayList<>();
		OrderTransactionUtility util = new OrderTransactionUtility();
		EntityManager em = getEntityManager();
		Query query = null;
		Query SalesQuery = null;

		try {
			query = util.getRejectClaimByClaim(status, empRoleAcc, divIds, emplID, claimID, parsed_from, parsed_to,
					claimTotalFrom, claimTotalTo, itemId, em);

			if (query != null) {
				if (util.isStatusRequired(status)) {
					query.setParameter("status", new BigDecimal(status));
				}
				claimTranHeaderList = query.getResultList();
			}

		} catch (Exception e) {
			LOGGER.error(e);
			System.err.println(e);
		}

		for (ClaimTranHeader claimTranHeade : claimTranHeaderList) {

			ClaimDetailTable cdt = new ClaimDetailTable();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				cdt.setClaimDate(dateFormat.parse(claimTranHeade.getId().getDcDyOrd()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				cdt.setClaimTotal(claimTranHeade.getClaimTranSum().getDkartNetTot());
			} catch (Exception e) {
				System.err.println(e);
			}
			cdt.setClaimId(claimTranHeade.getClaimId());
			BigDecimal lineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getLineQntRtn() == null ? BigDecimal.ZERO : line.getLineQntRtn())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal ApprvedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getApprClaimQty() == null ? BigDecimal.ZERO : line.getApprClaimQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal WhreceivedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getWhReceiveQty() == null ? BigDecimal.ZERO : line.getWhReceiveQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("1")) {
				cdt.setQty(lineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("2")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("3")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("4")) {
				cdt.setQty(ApprvedlineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("5")) {
				cdt.setQty(WhreceivedlineQntRtn.toString());
			} else {
				cdt.setQty(lineQntRtn.toString());
			}
			cdt.setStatus(getStatusOfClaim(claimTranHeade.getScOrd()));

			Map<String, String> o = getAllReasonCodes().get("Returns");
			cdt.setReasonCode(o.get(claimTranHeade.getRcRtnMr()));
			cdt.setSalesAgent(claimTranHeade.getEmId());
			try {
				String customerId = claimTranHeade.getClaimTranSum().getOrdIdCt();
				cdt.setCustomerId(customerId);
				Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);

				CustomerHeader header = (CustomerHeader) customerQ.getSingleResult();
				cdt.setCustomerName(header.getCtNm());

			} catch (Exception e) {
				LOGGER.error(e);
			}

			// process for to get sales agent names
			String salesAgnetName = cdt.getSalesAgent();

			SalesQuery = util.getClaimSalesAgentNames(divIds, empRoleAcc, emplID, salesAgnetName, em);

			try {
				if (SalesQuery != null) {
					RisplDkEmpMstrList = SalesQuery.getResultList();
				} else {
					System.out.println("Query is empty: " + SalesQuery);
				}

				for (RisplDkEmpMstr RisplDkEmpMstr : RisplDkEmpMstrList) {
					cdt.setSalesAgent(RisplDkEmpMstr.getEmpFstNme());
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}

			// To avoid Duplicate data in claimDetailTableList
			if (!claimIdList.contains(cdt.getClaimId())) // add only if not present in list
			{
				claimDetailTableList.add(cdt);
				claimIdList.add(cdt.getClaimId());
			}

		}

		return claimDetailTableList;

	}

	@Override
	public List<ClaimDetailTable> getRejectedClaimSearchByOrder(String status, String empRoleAcc, List<Integer> divIds,
			String emplID, String orderID, Date parsed_from, Date parsed_to, String order_total_from,
			String order_total_to, String itemId) {
		ArrayList<String> claimIdList = new ArrayList<>();
		Vector<ClaimDetailTable> claimDetailTableList = new Vector<>();
		List<ClaimTranHeader> claimTranHeaderList = new ArrayList<>();
		// for display Sales AGnet name in claim
		List<RisplDkEmpMstr> RisplDkEmpMstrList = new ArrayList<>();
		OrderTransactionUtility util = new OrderTransactionUtility();
		EntityManager em = getEntityManager();
		Query query = null;
		Query SalesQuery = null;
		try {
			query = util.getRejectClaimByOrder(status, empRoleAcc, divIds, emplID, orderID, parsed_from, parsed_to,
					order_total_from, order_total_to, itemId, em);

			if (query != null) {
				if (util.isStatusRequired(status)) {
					// query.setParameter("status", new BigDecimal(status));
				}
				claimTranHeaderList = query.getResultList();
			}

		} catch (Exception e) {
			LOGGER.error(e);
			System.err.println(e);
		}

		for (ClaimTranHeader claimTranHeade : claimTranHeaderList) {

			ClaimDetailTable cdt = new ClaimDetailTable();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				cdt.setClaimDate(dateFormat.parse(claimTranHeade.getId().getDcDyOrd()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				cdt.setClaimTotal(claimTranHeade.getClaimTranSum().getDkartNetTot());
			} catch (Exception e) {
				System.err.println(e);
			}
			cdt.setClaimId(claimTranHeade.getClaimId());
			BigDecimal lineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getLineQntRtn() == null ? BigDecimal.ZERO : line.getLineQntRtn())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal ApprvedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getApprClaimQty() == null ? BigDecimal.ZERO : line.getApprClaimQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal WhreceivedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getWhReceiveQty() == null ? BigDecimal.ZERO : line.getWhReceiveQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("1")) {
				cdt.setQty(lineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("2")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("3")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("4")) {
				cdt.setQty(ApprvedlineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("5")) {
				cdt.setQty(WhreceivedlineQntRtn.toString());
			} else {
				cdt.setQty(lineQntRtn.toString());
			}
			cdt.setStatus(getStatusOfClaim(claimTranHeade.getScOrd()));

			Map<String, String> o = getAllReasonCodes().get("Returns");
			cdt.setReasonCode(o.get(claimTranHeade.getRcRtnMr()));
			cdt.setSalesAgent(claimTranHeade.getEmId());
			try {
				String customerId = claimTranHeade.getClaimTranSum().getOrdIdCt();
				cdt.setCustomerId(customerId);
				Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);

				CustomerHeader header = (CustomerHeader) customerQ.getSingleResult();
				cdt.setCustomerName(header.getCtNm());

			} catch (Exception e) {
				LOGGER.error(e);
			}

			// process for to get sales agent names
			String salesAgnetName = cdt.getSalesAgent();

			SalesQuery = util.getClaimSalesAgentNames(divIds, empRoleAcc, emplID, salesAgnetName, em);

			try {
				if (SalesQuery != null) {
					RisplDkEmpMstrList = SalesQuery.getResultList();
				} else {
					System.out.println("Query is empty: " + SalesQuery);
				}

				for (RisplDkEmpMstr RisplDkEmpMstr : RisplDkEmpMstrList) {
					cdt.setSalesAgent(RisplDkEmpMstr.getEmpFstNme());
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}

			// To avoid Duplicate data in claimDetailTableList
			if (!claimIdList.contains(cdt.getClaimId())) // add only if not present in list
			{
				claimDetailTableList.add(cdt);
				claimIdList.add(cdt.getClaimId());
			}

		}

		return claimDetailTableList;

	}

	@Override
	public List<ClaimDetailTable> getRejectedClaimSearchByCustomerInfo(String status, String empRoleAcc,
			List<Integer> divIds, String emplID, String orderID, String claimID, String customerInfo, String itemId) {
		ArrayList<String> claimIdList = new ArrayList<>();
		Vector<ClaimDetailTable> claimDetailTableList = new Vector<>();
		List<ClaimTranHeader> claimTranHeaderList = new ArrayList<>();
		// for display Sales AGnet name in claim
		List<RisplDkEmpMstr> RisplDkEmpMstrList = new ArrayList<>();
		OrderTransactionUtility util = new OrderTransactionUtility();
		EntityManager em = getEntityManager();
		Query query = null;
		Query SalesQuery = null;
		try {
			if ((customerInfo != null && !customerInfo.equalsIgnoreCase(""))
					|| (itemId != null && !itemId.equalsIgnoreCase(""))) {
				query = util.getClaimQueryByItemIfo(itemId, customerInfo, status, em, empRoleAcc, divIds, emplID);// Customer
																													// name/id
																													// and
																													// Item
																													// id/Description
			} else {
				query = util.getRejectClaimByCustomerInfo(status, empRoleAcc, divIds, emplID, orderID, claimID,
						customerInfo, itemId, em); // Order Id /cliam Id
			}

			if (query != null) {
				if (util.isStatusRequired(status)) {
					// query.setParameter("status", new BigDecimal(status));
				}
				claimTranHeaderList = query.getResultList();
			}

		} catch (Exception e) {
			LOGGER.error(e);
			System.err.println(e);
		}

		for (ClaimTranHeader claimTranHeade : claimTranHeaderList) {

			ClaimDetailTable cdt = new ClaimDetailTable();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				cdt.setClaimDate(dateFormat.parse(claimTranHeade.getId().getDcDyOrd()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				cdt.setClaimTotal(claimTranHeade.getClaimTranSum().getDkartNetTot());
			} catch (Exception e) {
				System.err.println(e);
			}
			cdt.setClaimId(claimTranHeade.getClaimId());
			BigDecimal lineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getLineQntRtn() == null ? BigDecimal.ZERO : line.getLineQntRtn())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal ApprvedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getApprClaimQty() == null ? BigDecimal.ZERO : line.getApprClaimQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal WhreceivedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getWhReceiveQty() == null ? BigDecimal.ZERO : line.getWhReceiveQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("1")) {
				cdt.setQty(lineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("2")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("3")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("4")) {
				cdt.setQty(ApprvedlineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("5")) {
				cdt.setQty(WhreceivedlineQntRtn.toString());
			} else {
				cdt.setQty(lineQntRtn.toString());
			}
			cdt.setStatus(getStatusOfClaim(claimTranHeade.getScOrd()));

			Map<String, String> o = getAllReasonCodes().get("Returns");
			cdt.setReasonCode(o.get(claimTranHeade.getRcRtnMr()));
			cdt.setSalesAgent(claimTranHeade.getEmId());
			try {
				String customerId = claimTranHeade.getClaimTranSum().getOrdIdCt();
				cdt.setCustomerId(customerId);
				Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);

				CustomerHeader header = (CustomerHeader) customerQ.getSingleResult();
				cdt.setCustomerName(header.getCtNm());

			} catch (Exception e) {
				LOGGER.error(e);
			}

			// process for to get sales agent names
			String salesAgnetName = cdt.getSalesAgent();

			SalesQuery = util.getClaimSalesAgentNames(divIds, empRoleAcc, emplID, salesAgnetName, em);

			try {
				if (SalesQuery != null) {
					RisplDkEmpMstrList = SalesQuery.getResultList();
				} else {
					System.out.println("Query is empty: " + SalesQuery);
				}

				for (RisplDkEmpMstr RisplDkEmpMstr : RisplDkEmpMstrList) {
					cdt.setSalesAgent(RisplDkEmpMstr.getEmpFstNme());
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}

			// To avoid Duplicate data in claimDetailTableList
			if (!claimIdList.contains(cdt.getClaimId())) // add only if not present in list
			{
				claimDetailTableList.add(cdt);
				claimIdList.add(cdt.getClaimId());
			}

		}

		return claimDetailTableList;
	}

	// ========================Krishna: 24-10-2016 Find Claim line details
	// ====================
	@Override
	public ClaimTranHeader getClaimTranHeader(String claimID, String calimStatus) {
		ClaimTranHeader result = null;
		Query query = null;
		EntityManager em = getEntityManager();

		if (calimStatus != null) {
			String claimTranHeaderQuery = "SELECT cth FROM ClaimTranHeader cth WHERE cth.claimId=?1 AND cth.scOrd=?2";
			query = em.createQuery(claimTranHeaderQuery, ClaimTranHeader.class);
			query.setParameter(1, claimID).setParameter(2, new BigDecimal(calimStatus));
		} else {
			String claimTranHeaderQuery = "SELECT cth FROM ClaimTranHeader cth WHERE cth.claimId=?1";
			query = em.createQuery(claimTranHeaderQuery, ClaimTranHeader.class);
			query.setParameter(1, claimID);
		}

		List<ClaimTranHeader> claimTranHeaderList = query.getResultList();
		if (claimTranHeaderList.size() > 0) {
			result = claimTranHeaderList.get(0);
		}
		return result;
	}

	// to persist claims
	public boolean persistClaimTransaction(ClaimTranHeader cth) {
		boolean result = false;
		try {
			cth.setTsMdfRcrd(new Date());
			EntityManager em = getEntityManager();
			em.getTransaction().begin();
			em.merge(cth);
			em.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			try {
				cth.setTsMdfRcrd(new Date());
				EntityManager em = getEntityManager();
				em.getTransaction().begin();
				em.persist(cth);
				em.getTransaction().commit();
				result = true;
			} catch (Exception e1) {
				LOGGER.error(e);
			}
		}

		return result;
	}

	public CustomerHeader getCustomerHeader(String customerId) {
		CustomerHeader header = null;
		try {

			EntityManager em = getEntityManager();
			Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
					.setParameter("customerId", customerId);

			header = (CustomerHeader) customerQ.getSingleResult();

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return header;
	}

	public String SaveClaimTransaction(ClaimTranHeader cth) {
		String result = "Error";
		try {
			EntityManager em = getEntityManager();
			em.getTransaction().begin();
			OrderTranHeader orderTranHeader = null;
			OrderTranHeader oth = new OrderTranHeader();
			OrderTranHeaderPK othpk = new OrderTranHeaderPK();
			String storeID = cth.getId().getRtStrId();
			/*
			 * if(storeID.equalsIgnoreCase("")) { storeID =
			 * ConfigUtils.getInstance().getSDSStoreID();
			 * 
			 * }
			 */
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat trxidFormat = new SimpleDateFormat("yyyyMMdd");
			String tranDate = format.format(new Date());
			long tranSequenceId = transactionService.getTranSeq(storeID);
			String workStationId = ConfigUtils.getInstance().getSDSWorkstationID();
			ClaimTranSum csum = cth.getClaimTranSum();
			String orderId = csum.getIdOrd();
			String custId;

			// Setting Primary key of order header
			othpk.setRtStrId(storeID);
			othpk.setDcDyOrd(tranDate);
			othpk.setOrdWs(workStationId);
			othpk.setTrnSeq(tranSequenceId);
			oth.setId(othpk);
			oth.setTsOrdBgn(new Date());
			oth.setTsOrdEnd(new Date());
			// invoice cancel flag added by lokesh
			oth.setFlInvCncl(cth.getFlInvCncl());
			// chiranjibee comments to add accept claim id to ordertranheader object
			oth.setAcceptClaimId(cth.getClaimId());

			OrderTranSumPK sumpk = new OrderTranSumPK();
			sumpk.setRtStrId(storeID);
			sumpk.setDcDyOrd(tranDate);
			sumpk.setOrdWs(workStationId);
			sumpk.setTrnSeq(tranSequenceId);

			OrderTranSum sum = new OrderTranSum();
			sum.setId(sumpk);
			sum.setIdOrd(storeID + workStationId + trxidFormat.format(new Date()) + tranSequenceId);// changed by hanu,
																									// changed by lokesh
																									// to new order id
			sum.setDkartNetTot(csum.getDkartNetTot());
			sum.setDkartTndTot(csum.getDkartTndTot());
			sum.setDkartDscTot(csum.getDkartDscTot());// added to save the discount total @laxmikanth
			sum.setFlSndCtPhy("0");
			sum.setDkartTaxIncTot(csum.getDkartTaxIncTot());
			sum.setDkartSlsTot(csum.getDkartSlsTot());
			sum.setOrdIdCt(csum.getOrdIdCt());
			sum.setOrdTranHeader(oth);
			sum.setOrigOrderId(orderId);// adde by lokesh in case of return
			/*
			 * List<OrderTranSum> ordTranSums = new ArrayList<OrderTranSum>();
			 * ordTranSums.add(sum);
			 */

			oth.setOrdTranLineItems(convertClaimLineItemtoOrder(cth.getClaimTranLineItems(), oth));

			Customer customer = new Customer();
			customer.setCustomerLimits(getCustomerlimits(csum.getOrdIdCt()));
			oth.setCustomer(customer);

			oth.setOrdTranSum(sum);
			oth.setOrdTy(CLAIM_TRANSACTION_TYPE);
			oth.setIdOpr(cth.getEmId());
			oth.setEmId(cth.getEmId());
			oth.setIdTrlogBtch("-1");
			oth.setIdTlogBtch(new BigDecimal("-1"));
			oth.setScPstPrcs(new BigDecimal("01"));
			oth.setTransactionStatus(new BigDecimal("2"));
			oth.setScOrd(new BigDecimal("2"));
			oth.setReasonCodes(getAllReasonCodes());
			oth.setReturnReasonCode(cth.getRcRtnMr());
			TranLineItemTender tlid = new TranLineItemTender();
			TranLineItemTenderPK pk = new TranLineItemTenderPK();
			pk.setDcDyOrd(oth.getId().getDcDyOrd());
			pk.setOrdWs(oth.getId().getOrdWs());
			pk.setRtStrId(oth.getId().getRtStrId());
			pk.setTrnSeq(oth.getId().getTrnSeq());
			pk.setTrnLnItmSeq(1);
			tlid.setId(pk);
			tlid.setTyTnd("CARD");
			tlid.setIdOrd("HOUSE");
			tlid.setMoItmLnTnd(sum.getDkartNetTot());
			tlid.setIdAcntNmb(csum.getOrdIdCt());
			if (orderId != null) {
				if (cth.getFlInvCncl() != null && cth.getFlInvCncl().equalsIgnoreCase("Y")) {
					LOGGER.info("Fetching the Invoice cancelled related OrderTranHeader");
					orderTranHeader = getOrderByOrderID(orderId, DELIVERED_OR_COMPLET_RTLOG_GEN_ORD_TY,
							new BigDecimal(5), new BigDecimal(2));// getting Documentation Awaited order
					LOGGER.info("Invoice Cancelled Order related OrderTranHeader has been fetched");
					if (orderTranHeader == null) {
						LOGGER.info("Fetching the Invoice cancelled related to partially shipped order");
						orderTranHeader = getOrderByOrderID(orderId, PARTIAL_RTLOG_GEN_ORD_TY, new BigDecimal(4),
								new BigDecimal(2));// getting partially shipped Documentation Awaited order
						LOGGER.info("Invoice Cancelled Order related partially shipped orderr has been fetched");
					}
				} else {
					LOGGER.info("Fetching the Delivered Order related OrderTranHeader");
					orderTranHeader = getOrderByOrderID(orderId, DELIVERED_OR_COMPLET_RTLOG_GEN_ORD_TY,
							new BigDecimal(7), new BigDecimal(2));// getting delivered order
					LOGGER.info("Delivered Order related OrderTranHeader has been fetched");
				}
				// added by hanu to set below details
				oth.setIdBtchInvResv(orderTranHeader.getIdBtchInvResv());// insert customer site id in return order
				oth.setCtDvrInf(orderTranHeader.getCtDvrInf());// DeliveryAddress
				oth.setCtDvrInfoIns(orderTranHeader.getCtDvrInfoIns());// deliveryNotes
				oth.setTsCrtRcrd(new Date());
				oth.getOrdTranSum().setOrdDlvrDate(orderTranHeader.getOrdTranSum().getOrdDlvrDate());// delivery date
				oth.getOrdTranSum().setOrdDlvrTimePeriod(orderTranHeader.getOrdTranSum().getOrdDlvrTimePeriod());// delivery
																													// time
				oth.getOrdTranSum().setOrdEfDate(orderTranHeader.getOrdTranSum().getOrdEfDate());// effective date
				oth.getOrdTranSum().setOrdLvlDvr(orderTranHeader.getOrdTranSum().getOrdLvlDvr());// deliveryType
				// lpo details
				if (orderTranHeader != null && orderTranHeader.getOrdTranLpo() != null) {

					// chiranjibee code to save the transaction in order tran lpo
					OrderTransactionLpoPK lpoPK = new OrderTransactionLpoPK();
					lpoPK.setRtStrId(storeID);
					lpoPK.setDcDyOrd(tranDate);
					lpoPK.setOrdWs(workStationId);
					lpoPK.setTrnSeq(tranSequenceId);

					OrderTransactionLpo orderTransactionLpo = new OrderTransactionLpo();
					orderTransactionLpo.setId(lpoPK);
					orderTransactionLpo.setLpoNumber(orderTranHeader.getOrdTranLpo().getLpoNumber());
					orderTransactionLpo.setLpoDate(orderTranHeader.getOrdTranLpo().getLpoDate());
					orderTransactionLpo.setLpoSlipContent(orderTranHeader.getOrdTranLpo().getLpoSlipContent());
					orderTransactionLpo.setLpoSlipName(orderTranHeader.getOrdTranLpo().getLpoSlipName());
					orderTransactionLpo.setLpoSlipType(orderTranHeader.getOrdTranLpo().getLpoSlipType());

					// orderTransactionLpo.setOrdTranHeader(oth);

					/*
					 * OrderTransactionLpo orderTransactionLpo = orderTranHeader.getOrdTranLpo();
					 * orderTransactionLpo.getId().setDcDyOrd(tranDate);
					 * orderTransactionLpo.getId().setTrnSeq(tranSequenceId);
					 */
					// em.persist(orderTransactionLpo);

					oth.setOrdTranLpo(orderTransactionLpo);
				}
			} else {
				if (cth.getScTran() != null) {
					oth.setIdBtchInvResv(cth.getScTran().toString());
				}

				if (cth.getCtDvrInf() != null) {
					oth.setCtDvrInf(cth.getCtDvrInf());
				}

				// sharanya code for missing Site_Id after accepting claim
				// custId=getCustomerSiteId(csum.getOrdIdCt());
				// oth.setIdBtchInvResv(custId);// insert customer site id in OrderTranheader--
				// when claim without inv

			}

			LOGGER.info("Feteched Reason Code" + oth.getReasonCodes().size());

			// updateCustomerCreditLimit(customer.getCustomerLimits(),csum.getDkartNetTot());

			em.persist(oth);

			saveTenderLineItems(tlid);
			// oth.getOrdTranLineItems();
			// em.merge(customer.getCustomerLimits());
			em.getTransaction().commit();
			result = "SUCCESS";
		} catch (Exception e) {
			LOGGER.error(e);
			System.err.println(e);
		}
		return result;

	}

	/*
	 * commented by hanu
	 */
	/* Uncommented by Sharanya */
	public String getCustomerSiteId(String customerId) {
		String siteId = null;
		Query query = getEntityManager().createNamedQuery("CustomerSiteAddress.findByCustId",
				CustomerSiteAddress.class);
		try {
			query.setParameter("custId", customerId);
			List<CustomerSiteAddress> custSiteAddrList = query.getResultList();
			if (custSiteAddrList == null)
				throw new Exception("No Customer Site Addresses were found for customer: " + customerId);
			BigInteger siteIdBigInt = custSiteAddrList.get(0).getCustomerSiteAddressPK().getCustSiteId();
			siteId = String.valueOf(siteIdBigInt);
		} catch (Exception e) {
			siteId = "";
			LOGGER.error(e);
		}
		return siteId;
	}

	public String getCustomerSiteAddrss(String customerId, String custSiteId) {
		StringBuffer siteAddrss = new StringBuffer();
		Query query = getEntityManager().createNamedQuery("CustomerSiteAddress.findByCustId",
				CustomerSiteAddress.class);
		try {
			query.setParameter("custId", customerId);
			List<CustomerSiteAddress> custSiteAddrList = query.getResultList();
			for (CustomerSiteAddress addrss : custSiteAddrList) {
				if (addrss.getCustomerSiteAddressPK().getCustSiteId().toString().equalsIgnoreCase(custSiteId)
						&& addrss.getTyAds() == '1') {

					if (addrss.getA1Cnct() != null) {
						siteAddrss.append(addrss.getA1Cnct());
					}
					if (addrss.getA2Cnct() != null) {
						siteAddrss.append(", ");
						siteAddrss.append(addrss.getA2Cnct());
					}
					if (addrss.getCiCnct() != null) {
						siteAddrss.append(", ");
						siteAddrss.append(addrss.getCiCnct());
					}
					if (addrss.getCoCnct() != null) {
						siteAddrss.append(", ");
						siteAddrss.append(addrss.getCoCnct());
					}
					if (addrss.getPcCnct() != null) {
						siteAddrss.append(", ");
						siteAddrss.append(addrss.getPcCnct());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return siteAddrss.toString();
	}

	private List<OrderTranLineItem> convertClaimLineItemtoOrder(List<ClaimTranLineItem> claimTranLineItemsList,
			OrderTranHeader oth) {
		List<OrderTranLineItem> ordTranLineItems = new ArrayList<>();
		int lineItemSeq = 1;
		for (ClaimTranLineItem claimTranLineItem : claimTranLineItemsList) {
			if (claimTranLineItem.getWhReceiveQty().compareTo(new BigDecimal(0)) != 0) {
				OrderTranLineItem otln = new OrderTranLineItem();
				OrderTranLineItemPK pk = new OrderTranLineItemPK();
				pk.setDcDyOrd(oth.getId().getDcDyOrd());
				pk.setRtStrId(oth.getId().getRtStrId());
				pk.setOrdWs(oth.getId().getOrdWs());
				pk.setTrnSeq(oth.getId().getTrnSeq());
				pk.setOrdLnItmSeq(lineItemSeq);
				otln.setId(pk);

				otln.setOrdTranHeader(oth);
				otln.setAiLnItmOrg(claimTranLineItem.getAiLnItmOrg());
				otln.setAiLnItmRltd(claimTranLineItem.getAiLnItmRltd());
				otln.setDcDyBsnOrg(claimTranLineItem.getDcDyBsnOrg());
				otln.setDeItmLcl(claimTranLineItem.getDeItmLcl());
				otln.setDeItmShrtRcpt(claimTranLineItem.getDeItmShrtRcpt());
				otln.setDkDptId(claimTranLineItem.getDkDptId());
				otln.setDkItmId(claimTranLineItem.getDkItmId());
				// otln.setDRApplied(claimTranLineItem.getdr);
				otln.setEdSz(claimTranLineItem.getEdSz());
				// otln.setEmpId(claimTranLineItem.geti);
				otln.setExtnDscLnItm(claimTranLineItem.getExtnDscLnItm());
				otln.setExtnLnItmRtn(claimTranLineItem.getExtnLnItmRtn());
				otln.setFeRstk(claimTranLineItem.getFeRstk());
				otln.setFlClrnc(claimTranLineItem.getFlClrnc());
				otln.setFlDscEmAlw(claimTranLineItem.getFlDscEmAlw());
				otln.setFlFeRstk(claimTranLineItem.getFlFeRstk());
				otln.setFlItmDsc(claimTranLineItem.getFlItmDsc());
				otln.setFlItmDscDmg(claimTranLineItem.getFlItmDscDmg());
				otln.setFlItmPrcAdj(claimTranLineItem.getFlItmPrcAdj());
				otln.setFlItmSzReq(claimTranLineItem.getFlItmSzReq());
				otln.setFlMdfrPrc(claimTranLineItem.getFlMdfrPrc());
				otln.setFlRfdSv(claimTranLineItem.getFlRfdSv());
				otln.setFlRltdItmRm(claimTranLineItem.getFlRltdItmRm());
				otln.setFlRltdItmRtn(claimTranLineItem.getFlRltdItmRtn());
				otln.setFlRtnMr(claimTranLineItem.getFlRtnMr());
				otln.setFlRtnPrh(claimTranLineItem.getFlRtnPrh());
				otln.setFlRtrvdTrn(claimTranLineItem.getFlRtrvdTrn());
				otln.setFlShpChg(claimTranLineItem.getFlShpChg());
				otln.setFlSlsAsscMdf(claimTranLineItem.getFlSlsAsscMdf());
				otln.setFlTx(claimTranLineItem.getFlTx());
				otln.setFlVdLnItm(claimTranLineItem.getFlVdLnItm());
				otln.setFlVldSrzItm(claimTranLineItem.getFlVldSrzItm());
				otln.setFlVldSrzItmExt(claimTranLineItem.getFlVldSrzItmExt());
				otln.setIdCln(claimTranLineItem.getIdCln());
				otln.setIdItmMfUpc(claimTranLineItem.getIdItmMfUpc());
				otln.setIdMrhrcGp(claimTranLineItem.getIdMrhrcGp());
				otln.setIdNmbSrz(claimTranLineItem.getIdNmbSrz());
				otln.setIdNonRtvdOrgRcpt(claimTranLineItem.getIdNonRtvdOrgRcpt());
				otln.setIdnSlsAgRst(claimTranLineItem.getIdnSlsAgRst());
				otln.setIdStrRtOrg(claimTranLineItem.getIdStrRtOrg());
				otln.setItemId(claimTranLineItem.getItemId());
				otln.setItmPrnPrc(claimTranLineItem.getItmPrnPrc());
				otln.setItmTy(claimTranLineItem.getItmTy());
				// otln.setLineQnt(claimTranLineItem.getLineQnt());
				otln.setLineQnt(claimTranLineItem.getWhReceiveQty());
				// approve return for wh receive qty, not line qty
				otln.setLineQntRtn(claimTranLineItem.getWhReceiveQty());
				otln.setLuEntrRtPrc(claimTranLineItem.getLuEntrRtPrc());
				otln.setLuKtHdrRfnId(claimTranLineItem.getLuKtHdrRfnId());
				otln.setLuKtSt(claimTranLineItem.getLuKtSt());
				otln.setLuMthIdEnr(claimTranLineItem.getLuMthIdEnr());
				otln.setLuPrcAdjRfnId(claimTranLineItem.getLuPrcAdjRfnId());
				otln.setLuPrcRtDrvn(claimTranLineItem.getLuPrcRtDrvn());
				otln.setMrLvHrc(claimTranLineItem.getMrLvHrc());
				otln.setOrdIdTrnOrg(claimTranLineItem.getOrdIdTrnOrg());
				otln.setOrdLnItmSts(claimTranLineItem.getOrdLnItmSts());

				otln.setRcItmCndRtnMr(claimTranLineItem.getRcItmCndRtnMr());
				otln.setRcRfdSv(claimTranLineItem.getRcRfdSv());
				otln.setRcRtnMr(claimTranLineItem.getRcRtnMr());
				otln.setRcItmCndRtnMr(claimTranLineItem.getRcItmCndRtnMr());
				otln.setRegistryId(claimTranLineItem.getRegistryId());
				// otln.setReturnQtyFlag(returnQtyFlag);(claimTranLineItem.getreturnQtyFlag);
				otln.setTaxIncLnItmRtn(claimTranLineItem.getTaxIncLnItmRtn());

				otln.setTsCrtRcrd(new Date());
				otln.setTsMdfRcrd(new Date());

				otln.setTxGpId(claimTranLineItem.getTxGpId());
				otln.setUomSls(claimTranLineItem.getUomSls());
				otln.setVatLnItmRtn(claimTranLineItem.getVatLnItmRtn());
				// chiranjibee comments to add disposition code
				otln.setDispostionCode(claimTranLineItem.getDispostionCode());
				if (claimTranLineItem.getClaimTranDscItms() != null) {
					List<OrderTranDiscountItem> ordTranDiscItems = new ArrayList<>();
					int discItemSeq = 1;
					for (ClaimTranDscItm clDisc : claimTranLineItem.getClaimTranDscItms()) {
						OrderTranDiscountItem ordDisc = new OrderTranDiscountItem();
						// setting orderTranDisc pk
						OrderTranDiscountItemPK ordDiscPk = new OrderTranDiscountItemPK();
						ordDiscPk.setDcDyOrd(claimTranLineItem.getId().getDcDyOrd());
						ordDiscPk.setOrdLnItmSeq(lineItemSeq);
						ordDiscPk.setOrdWs(claimTranLineItem.getId().getOrdWs());
						ordDiscPk.setRtStrId(claimTranLineItem.getId().getRtStrId());
						ordDiscPk.setTrnSeq(claimTranLineItem.getId().getTrnSeq());
						ordDiscPk.setDiscSeqNum(discItemSeq);
						ordDisc.setId(ordDiscPk);

						ordDisc.setDscAmt(clDisc.getDscAmt()); // DISCOUNT AMOUNT
						ordDisc.setDscPer(clDisc.getDscPer()); // DISCOUNT PERCENT
						ordDisc.setOrdTranLineItem(otln);
						ordDisc.setPrmCmpDtlid(clDisc.getPrmCmpDtlid());
						ordDisc.setPrmCmpId(clDisc.getPrmCmpId());
						ordDisc.setPrmDesc(clDisc.getPrmDesc());
						ordDisc.setPrmId(clDisc.getPrmId());
						ordDisc.setPrmType(clDisc.getPrmType());
						ordDisc.setSrcTrgList(clDisc.getSrcTrgList());
						ordDisc.setTyDsc(clDisc.getTyDsc()); // TYPE OF DISCOUNT
						ordDisc.setDiscReasonCode(clDisc.getDiscReasonCode()); // DISCOUNT REASON CODE

						ordTranDiscItems.add(ordDisc);
						discItemSeq++;
					}
					otln.setOrdTranDscItms(ordTranDiscItems); // ADDING DISCOUNT LINES TO LINEITEM
				}

				ordTranLineItems.add(otln);
				lineItemSeq++;
			}
		}

		return ordTranLineItems;
	}

	public OrderTranHeader createNewOrderForClaims(OrderTranHeader transaction) {

		LOGGER.info("creating new orderForClaims......");
		OrderTranHeaderPK headerPk = new OrderTranHeaderPK();
		String storeID = "";
		try {
			// Krishna
			// storeID = Utils.getInstance().getSDSStoreID("123");
			if (transaction.getCustomer().getCustomerHeader().getCustomerHeaderPK() != null) {
				CustomerHeader customerHeader = transaction.getCustomer().getCustomerHeader();
				storeID = lookupCustomer.getCustomerStoreID(customerHeader.getCustomerHeaderPK().getCustId());

				for (int i = 0; i < 1; i++) {
					transaction.setSalesAgentsMap(loadSalesAgents(customerHeader.getEmId()));
				}
			}

			if (storeID.equalsIgnoreCase("")) {
				storeID = ConfigUtils.getInstance().getSDSStoreID();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				storeID = ConfigUtils.getInstance().getSDSStoreID();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		headerPk.setRtStrId(storeID);// TODO: read from the properties file or get the customer segment mapped store
										// id from properties file
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String tranDate = format.format(new Date());
		headerPk.setDcDyOrd(tranDate);
		try {
			headerPk.setOrdWs(ConfigUtils.getInstance().getSDSWorkstationID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		} // TODO: need to check what could be the workstation id

		/*
		 * long tranSequenceId = getTransactionSequence();
		 * headerPk.setTrnSeq(tranSequenceId);
		 */
		transaction.setId(headerPk);
		transaction.setTsOrdBgn(new Date());
		OrderTranSum sum = new OrderTranSum();

		/* sum.setIdOrd(getOrderId(transaction)); */

		List<OrderTranSum> ordTranSums = new ArrayList<OrderTranSum>();
		ordTranSums.add(sum);
		transaction.setOrdTranSum(sum);
		return transaction;
	}

	/*
	 * loading the sales agents to transaction
	 */

	public LinkedHashMap<String, String> loadSalesAgents(String EmpIdAssociatedToCust) {

		LinkedHashMap<String, String> salesAgentsMap = new LinkedHashMap<String, String>();
		try {

			EmployeeSearchCriteriaIfc empsearchCriteria = new EmployeeSearchCriteria();
			empsearchCriteria.setEmployeeId("%");
			EmployeeIfc[] employees = lookUpEmployee.lookupSalesAgent(empsearchCriteria);

			if (employees != null && employees.length > 0) {
				for (int i = 0; i < employees.length; i++) {
					/*
					 * if(employees[i].getEmployeeId().equalsIgnoreCase(employee .getEmployeeId()))
					 */
					if (employees[i].getEmployeeId().equalsIgnoreCase(EmpIdAssociatedToCust)) {
						// As per uat feedback from users who want to search by sales agent id
						salesAgentsMap.put(employees[i].getEmployeeId(),
								employees[i].getEmployeeName() + " - " + employees[i].getEmployeeId());
						break;
					}
				}

				for (int i = 0; i < employees.length; i++) {
					// As per uat feedback from users who want to search by sales agent id
					salesAgentsMap.put(employees[i].getEmployeeId(),
							employees[i].getEmployeeName() + " - " + employees[i].getEmployeeId());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return salesAgentsMap;

	}

	/* for claim status @mallikarjun */
	private String getStatusOfClaim(BigDecimal scOrd) {
		String stat = null;
		if (scOrd.toString().equalsIgnoreCase("1")) {
			stat = "Registered";
		} else if (scOrd.toString().equalsIgnoreCase("5")) {
			stat = "Accepted";
		} else if (scOrd.toString().equalsIgnoreCase("6")) {
			stat = "Rejected";
		} else if (scOrd.toString().equalsIgnoreCase("2")) {
			stat = "Awaiting for approval";
		} else if (scOrd.toString().equalsIgnoreCase("3")) {
			stat = "Approved";
		} else if (scOrd.toString().equalsIgnoreCase("4")) {
			stat = "Approved";
		} else {
			stat = "none";
		}

		return stat;
	}

	@Override
	public boolean markOrderAsDelivered(List<String> orders) throws Exception {

		EntityManager em = null;

		// List<OrderTranHeader> ord_tran_list=new ArrayList();
		// List<OrderTranHeader> ord_tran_list1=new ArrayList();
		try {
			if (orders != null && !orders.isEmpty()) {
				List<OrderTranHeaderPK> pkid = new ArrayList<>();
				// get delivery status
				// as it is same for all the objects getting it here
				String deliveryComment = orders.get(0).split("@")[orders.get(0).split("@").length - 1];
				if (deliveryComment == null) {
					deliveryComment = "";
				}
				em = getEntityManager();
				em.getTransaction().begin();
				pkid = getTranHeadPkList(orders);
				if (pkid != null) {
					for (OrderTranHeaderPK wrap : pkid) {

						Query q = em.createNamedQuery("DELIVERED_ORDERS_HEADER_UPDATE")
								.setParameter("p5", deliveryComment).setParameter("p2", wrap.getOrdWs())
								.setParameter("p3", wrap.getTrnSeq()).setParameter("p4", wrap.getDcDyOrd())
								.setParameter("param1", "24").setParameter("param2", 7)
								.setParameter("p1", wrap.getRtStrId()).setParameter("para4",
										new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
						int result = q.executeUpdate();
						LOGGER.info("records updated are " + result);
					}
				}
			}
			em.getTransaction().commit();
			LOGGER.info("commited all transactions......");
		}

		catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			;
		} finally {
			LOGGER.info("came out from  markOrderAsDelivered......");
			em.close();
		}
		return false;
	}

	public OrderTranHeaderPK getHeadId(Long seq, String s1, String s2, String s3) {
		OrderTranHeaderPK id = new OrderTranHeaderPK();
		id.setTrnSeq(seq);
		id.setRtStrId(s1);
		id.setOrdWs(s2);
		id.setDcDyOrd(s3);
		return id;
	}

	public List<OrderTranHeaderPK> getTranHeadPkList(List<String> order) {
		List<OrderTranHeaderPK> pkList = new ArrayList();
		if (order != null) {
			for (String wrap : order) {
				String[] data = wrap.split("@");
				pkList.add(getHeadId(Long.parseLong(data[1]), data[2], data[3], data[4]));
			}
		}
		return pkList;
	}

	@Override
	public List<OrderTranHeaderPK> getTranHeadPkForDelivered() throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		List<OrderTranHeaderPK> headp = null;
		try {
			headp = em.createNamedQuery("DELIVERED_ORDERS_HEADER_PK").setParameter("param1", "24")
					.setParameter("param2", 7).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LOGGER.info("came out of .getTranHeadPkForDelivered().....");
			em.close();
		}
		return headp;
	}

	@Override
	public List<OrderDetail> getPendingOrders(EmployeeIfc emp) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> divIds = getEmpMrchAssc(emp);
		EntityManager em = getEntityManager();
		List<OrderDetail> orderList = null;
		StringBuilder pendingOrderQuery = new StringBuilder(
				"select orderDetail from OrderDetail orderDetail where orderDetail.ordTy=23 and orderDetail.scOrd=0 and orderDetail.scTran=4 ");

		try {
			if (emp.getRoleAccess().equalsIgnoreCase("Within Division")) {
				pendingOrderQuery
						.append("and orderDetail.divisionId IN :divIds ORDER BY orderDetail.id.orderDate DESC");
				orderList = em.createQuery(pendingOrderQuery.toString()).setParameter("divIds", divIds).getResultList();
			} else if (emp.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
				pendingOrderQuery.append("and orderDetail.empId = :empid ORDER BY orderDetail.id.orderDate DESC");
				orderList = em.createQuery(pendingOrderQuery.toString()).setParameter("empid", emp.getEmployeeId())
						.getResultList();
			} else if (emp.getRoleAccess().equalsIgnoreCase("All")) {
				orderList = em.createNamedQuery("PENDING_ORDER_GET").setParameter("param1", "23")
						.setParameter("param2", 0).setParameter("param3", new BigDecimal(4)).getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		if (orderList != null && !orderList.isEmpty()) {
			return orderList;
		} else {
			return null;
		}
	}

	// Flag to checck Quoted Order
	public boolean isQuoteOrder(OrderTranHeader head) {
		if (head.getOrdTy().equalsIgnoreCase("23") && head.getScOrd().intValue() == 0
				&& head.getTransactionStatus().intValue() == 4) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Changed By @Hanu
	 */
	@Override
	public OrderTranHeader markAsSuspendRetrived(OrderTranHeader head) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		OrderTranHeader changedHead = null;

		try {
			if (head != null && isQuoteOrder(head)) {
				em = getEntityManager();
				if (em != null) {
					/* TRANSACTION CANCEL VOID CANCEL 3 0 2 */
					/*
					 * Changed By @Mudassir changed to void cancel from suspend retrived
					 */
					OrderTranHeader temp = head;

					em.getTransaction().begin();
					temp.setOrdTy("23");
					temp.setScOrd(new BigDecimal(0));
					temp.setTransactionStatus(new BigDecimal(6));
					temp.setTsOrdEnd(new java.util.Date()); // added transaction end timestamp
					em.merge(temp);
					em.getTransaction().commit();

					changedHead = createNewRecordsForSuspendAndRetriveTran(temp);
					if (changedHead == null) {
						return null;
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		} finally {
			em.close();
		}
		return changedHead;
	}

	/*
	 * changed by @Hanu
	 */
	private OrderTranHeader createNewRecordsForSuspendAndRetriveTran(OrderTranHeader suspendAndRetriveTra) {
		EntityManager em = null;
		Long seq = null;
		String businessDate = null;
		List<OrderTranLineItem> lineItems = null;
		OrderTranSum trnSum = null;
		try {
			em = getEntityManager();
			if (suspendAndRetriveTra != null) {
				trnSum = suspendAndRetriveTra.getOrdTranSum();
				String oldOrderId = trnSum.getIdOrd();
				seq = transactionService.getTranSeq(suspendAndRetriveTra.getId().getRtStrId());
				businessDate = getDateForOrderHead();
				if (seq != null) {
					em.getTransaction().begin();
					suspendAndRetriveTra.getId().setTrnSeq(seq);
					/*
					 * changed by @Mudassir
					 */
					/* suspendAndRetriveTra.setTransactionStatus(new BigDecimal(2)); */
					/*
					 * Changed by @Laxmikanth from 1 to 4 maintain data when suspend retrieved, If
					 * any interruption in sales screen(timeout/window close)
					 */
					suspendAndRetriveTra.setTransactionStatus(new BigDecimal(4));

					suspendAndRetriveTra.getId().setDcDyOrd(businessDate);
					lineItems = suspendAndRetriveTra.getOrdTranLineItems();
					if (lineItems != null && lineItems.size() > 0) {
						for (OrderTranLineItem item : lineItems) {
							item.getId().setTrnSeq(seq);
							item.getId().setDcDyOrd(businessDate);
						}
						suspendAndRetriveTra.setOrdTranLineItems(lineItems);
					}
					// Saideep: added missing update to lpo details
					if (suspendAndRetriveTra.getOrdTranLpo() != null) {
						suspendAndRetriveTra.getOrdTranLpo().getId().setTrnSeq(seq);
						suspendAndRetriveTra.getOrdTranLpo().getId().setDcDyOrd(businessDate);
					}

					trnSum.getId().setTrnSeq(seq);
					trnSum.getId().setDcDyOrd(businessDate);
					trnSum.setIdOrd(oldOrderId);// changed by hanu
					// K.Srinivas: Fix for issue #412
					trnSum.setOrdDlvrDate(suspendAndRetriveTra.getOrdTranSum().getOrdDlvrDate());
					trnSum.setOrdDlvrTimePeriod(suspendAndRetriveTra.getOrdTranSum().getOrdDlvrTimePeriod());
					trnSum.setOrdEfDate(suspendAndRetriveTra.getOrdTranSum().getOrdEfDate());
					trnSum.setOrdLvlDvr(suspendAndRetriveTra.getOrdTranSum().getOrdLvlDvr());

					suspendAndRetriveTra.setOrdTranSum(trnSum);

					em.persist(suspendAndRetriveTra);
					em.getTransaction().commit();
				}

			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		} finally {
			em.close();
		}
		return suspendAndRetriveTra;
	}

	private String getDateForOrderHead() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	@Override
	public LinkedHashMap<String, String> validateInventory(OrderTranHeader head) throws Exception {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> result = new LinkedHashMap<>();
		String whId = new String();
		String chanelId = new String();
		LinkedHashMap<String, BigDecimal> itemQtyMap = new LinkedHashMap<>();
		if (head != null) {

			chanelId = ConfigUtils.getInstance().getWarehouseChannelID();
			whId = ConfigUtils.getInstance().getWarehouseLocationID();
			for (OrderTranLineItem line : head.getOrdTranLineItems()) {
				if (line.getItmTy().compareTo(new BigDecimal(2)) != 0) {// condition added to uncheck inventory for
																		// service items
					if (itemQtyMap.containsKey(line.getItemId())) {

						itemQtyMap.put(line.getItemId(), line.getLineQnt().add(itemQtyMap.get(line.getItemId())));
					} else {
						itemQtyMap.put(line.getItemId(), line.getLineQnt());
					}
				}

			}
			if (itemQtyMap.size() > 0) {
				result = getItemQty(itemQtyMap, whId, chanelId);
			}

		}

		return result;
	}

	private LinkedHashMap<String, String> getItemQty(LinkedHashMap<String, BigDecimal> itemap, String whid,
			String chid) {
		RMSInventoryDetailService webCall = null;
		LinkedHashMap<String, String> result = new LinkedHashMap<>();
		BigDecimal qun = null;
		try {
			webCall = new RMSInventoryDetailService();
			if (itemap != null && itemap.size() > 0) {
				Set<String> keys = itemap.keySet();
				for (String item : keys) {
					qun = webCall.lookupInventory(item);
					if (qun != null) {
						if (itemap.get(item).compareTo(qun) == 1) {
							result.put(item, qun.toString() + "#" + itemap.get(item));
						}
					} else {
						result.put(item, new BigDecimal("0").toString() + "#" + itemap.get(item));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param orderId
	 * @param transComment
	 * @param empId
	 * @return Hashtable <br>
	 *         key1="PERSIST" value1=boolean <br>
	 *         key2="OrderTranHeader" value2=OrderTranHeader <br>
	 *         key3="ERROR" value3=String
	 */
	@Override
	public Hashtable<String, Object> cancelSalesOrder(String orderId, String transComment, String empId,
			String reasonCode) {
		Hashtable<String, Object> result = new Hashtable<>();
		List<OrderTranSum> otmList = getOrderTransactionSum(orderId);

		// At this point the order transaction sum must have once record
		if (otmList.size() == 1) {
			// At this Point Item Must not Shipped from WMS to cancel the Order
			if (!isOrderShipped(orderId)) {
				// The Retrieved order must be NEW, OPEN, PARTIALLY PICKED or COMPLETELY PICKED
				OrderTranHeader oth = otmList.get(0).getOrdTranHeader();
				oth.setTransComment(transComment);
				oth.setReturnReasonCode(reasonCode);
				if (oth.getOrdTy().equalsIgnoreCase("23")) {
					try {
						OrderTranHeader cancledOth = saveCancelOrder(oth, empId, result);
						cancledOth.setOrdTranLpo(oth.getOrdTranLpo());
						result.put("OrderTranHeader", cancledOth);
					} catch (Exception e) {
						result.put("ERROR", "COULD_NOT_PERSIT");
					}
				}
			} else {
				result.put("ERROR", "ITEM_SHIPPED_FROM_WMS");
			}
		} else {
			result.put("ERROR", otmList.size() + " ORDER_FOUND");
		}

		return result;
	}

	/**
	 * 
	 * @param oth
	 * @param empId
	 * @param result
	 * @return OrderTranHeader and Hashtable<String, Object> Key1=PERSIST
	 *         value1=boolean
	 * 
	 */
	private OrderTranHeader saveCancelOrder(OrderTranHeader oth, String empId, Hashtable<String, Object> result) {
		boolean status = false;
		result.put("PERSIST", status);
		String custId = oth.getOrdTranSum().getOrdIdCt();
		OrderTranHeader cancelTranHeader = null;
		try {
			cancelTranHeader = transactionService.getNewTransaction(custId, TransactionService.ORD_CNCL, empId);
			OrderTranSumPK cancelTranHeaderPk = cancelTranHeader.getOrdTranSum().getId();
			// cancelTranHeader.setTransComment(oth.getTransComment());
			cancelTranHeader.setTransactionStatus(new BigDecimal("2"));
			// chiranjibee commnets update the cancel order status sc_ord = 8 as per shared
			// sheet
			// Add comments for order cancellation
			cancelTranHeader.setScOrd(new BigDecimal(8));
			cancelTranHeader.setDeliveryComment(oth.getTransComment());
			cancelTranHeader.setCtDvrInf(oth.getCtDvrInf());
			cancelTranHeader.setReturnReasonCode(oth.getReturnReasonCode());
			cancelTranHeader.setEmId(oth.getEmId());
			cancelTranHeader.setIdOpr(empId);

			// Converting Original transactionSum object for cancelTransaction
			SDSEntityBuilder.cloneOrderTranSum(oth.getOrdTranSum(), cancelTranHeader.getOrdTranSum());

			// Converting Original order line item for cancel Transaction
			List<OrderTranLineItem> orderLineItems = new ArrayList<OrderTranLineItem>();
			for (OrderTranLineItem ltm : oth.getOrdTranLineItems()) {
				// OrderTranLineItem lineItem = ltm.clone();
				OrderTranLineItem lineItem = SerializationUtils.clone(ltm);
				// Save the original data
				lineItem.setOrdIdTrnOrg(lineItem.getId().getDcDyOrd());
				lineItem.setDcDyBsnOrg(lineItem.getId().getDcDyOrd());
				lineItem.setIdStrRtOrg(lineItem.getId().getRtStrId());
				lineItem.setAiLnItmOrg(new BigDecimal(lineItem.getId().getOrdLnItmSeq()));
				// change the as per new Transaction Header
				lineItem.getId().setDcDyOrd(cancelTranHeaderPk.getDcDyOrd());
				lineItem.getId().setOrdWs(cancelTranHeaderPk.getOrdWs());
				lineItem.getId().setRtStrId(cancelTranHeaderPk.getRtStrId());
				lineItem.getId().setTrnSeq(cancelTranHeaderPk.getTrnSeq());
				lineItem.setOrdTranHeader(cancelTranHeader);

				// Add Discount Line Items For Cancel Order
				List<OrderTranDiscountItem> discountItems = lineItem.getOrdTranDscItms();

				if (discountItems != null && discountItems.size() > 0) {
					lineItem.setFlItmDsc("0");
				} else {
					lineItem.setFlItmDsc("1");
				}
				int disLineSeq = 1;
				if (discountItems != null) {
					for (OrderTranDiscountItem discountItem : discountItems) {
						OrderTranDiscountItemPK orderTranDiscountItemPK = new OrderTranDiscountItemPK();
						orderTranDiscountItemPK.setDiscSeqNum(disLineSeq);
						orderTranDiscountItemPK.setDcDyOrd(cancelTranHeaderPk.getDcDyOrd());
						orderTranDiscountItemPK.setOrdWs(cancelTranHeaderPk.getOrdWs());
						orderTranDiscountItemPK.setRtStrId(cancelTranHeaderPk.getRtStrId());
						orderTranDiscountItemPK.setTrnSeq(cancelTranHeaderPk.getTrnSeq());
						orderTranDiscountItemPK.setOrdLnItmSeq(lineItem.getId().getOrdLnItmSeq());
						discountItem.setId(orderTranDiscountItemPK);
						discountItem.setOrdTranLineItem(lineItem);
						disLineSeq++;
					}
					lineItem.setOrdTranDscItms(discountItems);
				}
				orderLineItems.add(lineItem);

			}
			cancelTranHeader.setOrdTranLineItems(orderLineItems);
			cancelTranHeader.setTsOrdEnd(new java.util.Date()); // added transaction end timestamp
			status = transactionService.saveTransaction(cancelTranHeader);
			// Add Tender Line Items For Cancel Order
			if (oth.getOrdTy().equalsIgnoreCase("23")) {
				TranLineItemTender tenderLineItem = new TranLineItemTender();
				TranLineItemTenderPK tenderLineItempk = new TranLineItemTenderPK();

				tenderLineItempk.setRtStrId(cancelTranHeader.getId().getRtStrId());
				tenderLineItempk.setOrdWs(cancelTranHeader.getId().getOrdWs());
				tenderLineItempk.setTrnSeq(cancelTranHeader.getId().getTrnSeq());
				tenderLineItempk.setTrnLnItmSeq(Long.parseLong("1"));//// set hard coded for now as no payment is
																		//// accepted at the time of payment
				tenderLineItempk.setDcDyOrd(cancelTranHeader.getId().getDcDyOrd());

				tenderLineItem.setId(tenderLineItempk);
				tenderLineItem.setMoItmLnTnd(cancelTranHeader.getOrdTranSum().getDkartNetTot());
				tenderLineItem.setTyTnd("CARD");/// payment type as house account
				tenderLineItem.setIdOrd("HOUSE");//// using unused column to store sub tender type
				// tenderLineItem.setIdAcntNmb(new
				// BigDecimal(cancelTranHeader.getOrdTranSum().getOrdIdCt()));
				tenderLineItem.setIdAcntNmb(cancelTranHeader.getOrdTranSum().getOrdIdCt());
				tenderLineItem.setDkTsCrtRcrd(new Date());
				saveTenderLineItems(tenderLineItem);
			}
			result.put("PERSIST", status);
		} catch (Exception e) {
			System.err.println(e);
			LOGGER.error(e);
			e.printStackTrace();
		}
		return cancelTranHeader;
	}

	@Override
	public boolean isOrderShipped(String idOrd) {
		boolean result = false;
		EntityManager em = emf.createEntityManager();
		List<OrderShipmentDetail> orderDetail = em
				.createNamedQuery("SHIPMENT_DETAILS_BY_ORDERID", OrderShipmentDetail.class)
				.setParameter("orderId", idOrd).getResultList();
		if (orderDetail.size() > 0) {
			result = true;
		}

		return result;
	}

	/*
	 * Author@Hanu To get transaction details by order ID orderType orderstatus
	 * tranStatus - State of Order 23 0 4 - suspended 23 0 6 - suspendAndRetrieved
	 * 23 0 2 - New Order
	 */
	@Override
	public OrderTranHeader getOrderByOrderID(String orderID, String orderType, BigDecimal orderstatus,
			BigDecimal tranStatus) {
		OrderTranHeader orderTranHeader = null;
		Query query = getEntityManager().createNamedQuery("GET_ORDER_BY_ORDER_ID", OrderTranHeader.class);
		query.setParameter("idOrd", orderID).setParameter("ordTy", orderType).setParameter("scOrd", orderstatus)
				.setParameter("transactionStatus", tranStatus);
		try {
			orderTranHeader = (OrderTranHeader) query.getSingleResult();
			CustomerSearchCriteria customerSearchCriteria = new CustomerSearchCriteria();
			customerSearchCriteria.setCustomerId(orderTranHeader.getOrdTranSum().getOrdIdCt());
			CustomerIfc[] customers = lookupCustomer.lookUpCust(customerSearchCriteria);
			orderTranHeader.setCustomer(customers[0]);
			orderTranHeader.setReasonCodes(getAllReasonCodes());
			List<OrderTranLineItem> lineItmsList = orderTranHeader.getOrdTranLineItems();
			Iterator<OrderTranLineItem> itr = lineItmsList.iterator();
			SearchCriteria itemCriteria = new SearchCriteria();
			itemCriteria.setStoreID(orderTranHeader.getId().getRtStrId());
			itemCriteria.setCheckInventory(true);
			while (itr.hasNext()) {
				OrderTranLineItem ordLineitm = itr.next();
				itemCriteria.setItemIdOrUPC(ordLineitm.getItemId());
				ordLineitm.setPluItem(lookupItem.lookUpItemById(itemCriteria));
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error ocuured while fetching the OrderTranHeader from the database for the Order ID:  " + orderID);
			return orderTranHeader;
		}
		return orderTranHeader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rispl.dkart.services.ejb.transaction.OrderTransactionsIfc#getOrderByInvoiceID
	 * (java.lang.String)
	 */

	public String getSalesAgentMailId(String ordId) {
		String Email = null;
		try {
			EntityManager em = getEntityManager();
			Query query = em.createNamedQuery("GET_SALESAGENT_MAILID").setParameter("1", ordId);
			Email = (String) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Email;
	}

	public String getDataEntryOptrMailId(String ordId) {
		String Email = null;
		try {
			EntityManager em = getEntityManager();
			Query query = em.createNamedQuery("GET_DATAENTRYOP_MAILID").setParameter("1", ordId);
			Email = (String) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Email;
	}

	public String[] getDepartmentHeadMailId(String ordId) {
		String[] Email = null;
		List<String> emailsList = new ArrayList<String>();
		try {
			EntityManager em = getEntityManager();
			Query query = em.createNamedQuery("GET_DepartmentHead_MAILID").setParameter("1", ordId);
			emailsList = query.getResultList();
			emailsList.removeIf(Objects::isNull);
			Email = new String[emailsList.size()];
			Email = emailsList.toArray(Email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Email;
	}

	public List<OrderTranLineItem> getDeliveredQty(String ordId) {
		List<OrderTranLineItem> DeliveredQty = null;
		try {
			EntityManager em = getEntityManager();
			Query query = em.createNamedQuery("GET_LINE_QNT").setParameter("ordId", ordId);
			DeliveredQty = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DeliveredQty;

	}

//to get the division assigned to employee
	public List<Integer> getEmpMrchAssc(EmployeeIfc employee) {
		List<EmpMerchAssociationIfc> empMrchAss = employee.getMerchAssoc();
		List<Integer> divIds = new ArrayList<Integer>();
		if (empMrchAss != null) {
			for (EmpMerchAssociationIfc empMrchAsc : empMrchAss) {
				if (empMrchAsc.getMerchId().startsWith("1:")) {
					int merchId = Integer.parseInt(empMrchAsc.getMerchId().split("1:")[1]);
					divIds.add(merchId);
				}
			}
		}
		return divIds;
	}

	@Override
	public List<ClaimDetailTable> getRjectedClaimOrderDetails(String Status, String empRoleAcc, String emplName,
			List<Integer> divIds, String emplID, String orderID, Date parsed_from, Date parsed_to,
			String ordertotalfrom, String ordertotalto) {
		ArrayList<String> claimIdList = new ArrayList<>();
		Vector<ClaimDetailTable> claimDetailTableList = new Vector<>();
		List<ClaimTranHeader> claimTranHeaderList = new ArrayList<>();
		// for display Sales AGnet name in claim
		List<RisplDkEmpMstr> RisplDkEmpMstrList = new ArrayList<>();
		OrderTransactionUtility util = new OrderTransactionUtility();
		EntityManager em = getEntityManager();
		Query query = null;
		Query SalesQuery = null;

		query = util.getClaimQueryByClaimOrderDetails(divIds, empRoleAcc, emplID, orderID, parsed_from, parsed_to,
				ordertotalfrom, ordertotalto, em);

		if (query != null) {
			try {
				claimTranHeaderList = query.getResultList();
				// claimDetailsList=query.getResultList();
			} catch (Exception ex) { // handle your exception
				System.out.println(ex);
			}
		}

		for (ClaimTranHeader claimTranHeade : claimTranHeaderList) {

			ClaimDetailTable cdt = new ClaimDetailTable();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				cdt.setClaimDate(dateFormat.parse(claimTranHeade.getId().getDcDyOrd()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				cdt.setClaimTotal(claimTranHeade.getClaimTranSum().getDkartNetTot());
			} catch (Exception e) {
				System.err.println(e);
			}
			cdt.setClaimId(claimTranHeade.getClaimId());
			BigDecimal lineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getLineQntRtn() == null ? BigDecimal.ZERO : line.getLineQntRtn())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal ApprvedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getApprClaimQty() == null ? BigDecimal.ZERO : line.getApprClaimQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal WhreceivedlineQntRtn = claimTranHeade.getClaimTranLineItems().stream()
					.map(line -> line.getWhReceiveQty() == null ? BigDecimal.ZERO : line.getWhReceiveQty())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("1")) {
				cdt.setQty(lineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("2")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("3")
					|| claimTranHeade.getScOrd().toString().equalsIgnoreCase("4")) {
				cdt.setQty(ApprvedlineQntRtn.toString());
			} else if (claimTranHeade.getScOrd().toString().equalsIgnoreCase("5")) {
				cdt.setQty(WhreceivedlineQntRtn.toString());
			} else {
				cdt.setQty(lineQntRtn.toString());
			}
			cdt.setStatus(getStatusOfClaim(claimTranHeade.getScOrd()));

			Map<String, String> o = getAllReasonCodes().get("Returns");
			cdt.setReasonCode(o.get(claimTranHeade.getRcRtnMr()));
			cdt.setSalesAgent(claimTranHeade.getEmId());// util.getEmployee()
			try {
				String customerId = claimTranHeade.getClaimTranSum().getOrdIdCt();
				cdt.setCustomerId(customerId);
				Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);

				CustomerHeader header = (CustomerHeader) customerQ.getSingleResult();
				cdt.setCustomerName(header.getCtNm());

			} catch (Exception e) {
				LOGGER.error(e);
			}

			// process for to get sales agent names
			String salesAgnetName = cdt.getSalesAgent();

			SalesQuery = util.getClaimSalesAgentNames(divIds, empRoleAcc, emplID, salesAgnetName, em);

			try {
				if (SalesQuery != null) {
					RisplDkEmpMstrList = SalesQuery.getResultList();
				} else {
					System.out.println("Query is empty: " + SalesQuery);
				}

				for (RisplDkEmpMstr RisplDkEmpMstr : RisplDkEmpMstrList) {
					cdt.setSalesAgent(RisplDkEmpMstr.getEmpNme());
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}

			// To avoid Duplicate data in claimDetailTableList
			if (!claimIdList.contains(cdt.getClaimId())) // add only if not present in list
			{
				claimDetailTableList.add(cdt);
				claimIdList.add(cdt.getClaimId());
			}
		}

		return claimDetailTableList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rispl.dkart.services.ejb.transaction.OrderTransactionsIfc#getSerialNos(rispl.
	 * dkart.services.entities.transaction.OrderTranHeader)
	 */
	/*
	 * @Override public List<OrdInvShpQtySrlno> getSerialNos(OrderTranHeader oth) {
	 * EntityManager em =null; Query query = null; List<OrdInvShpQtySrlno>
	 * serialNoList = null; try{ em=getEntityManager(); query =
	 * em.createNamedQuery("SEARCH_SERIAL_NO"); query.setParameter("ordId",
	 * oth.getOrdTranSum().getIdOrd()); serialNoList = query.getResultList();
	 * }catch(Exception e){ LOGGER.error("Error while fetching serial numers");
	 * return serialNoList; } return serialNoList; }
	 */

	/**
	 * This method is used to get the serial numbers of items
	 * 
	 * @author Srinivas Reddy G
	 * @param oth This is the OrderTranHeader reference
	 * @return Map<String,StringBuffer> This returns serial Numbers in the form of
	 *         Map
	 * @see Map
	 */
	@Override
	public Map<String, StringBuffer> getItemSerialNumbers(OrderTranHeader oth) {
		EntityManager em = null;
		Query query = null;
		List<OrdInvShpQtySrlno> serialNoList = null;
		Map<String, StringBuffer> slnoMap = new HashMap<String, StringBuffer>();
		StringBuffer sb = null;
		try {
			em = getEntityManager();
			query = em.createNamedQuery("SEARCH_SERIAL_NO");
			query.setParameter("ordId", oth.getOrdTranSum().getIdOrd());
			serialNoList = query.getResultList();
			for (OrdInvShpQtySrlno slno : serialNoList) {
				if (slnoMap.containsKey(slno.getId().getItemId())) {
					sb = slnoMap.get(slno.getId().getItemId());
					sb = sb.append("," + slno.getId().getSerialNo());
					slnoMap.put(slno.getId().getItemId(), sb);
				} else {
					slnoMap.put(slno.getId().getItemId(), new StringBuffer(slno.getId().getSerialNo()));
				}

			}

		} catch (Exception e) {
			LOGGER.error("Error while fetching serial numers");
			return slnoMap;
		}
		return slnoMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isPartialShippingAvailable(String orderId) {
		if (orderId != null) {

			OrderTranHeader ord_tran_header; // changes for Partial shipment 27/09/18
			boolean atleastOneItemShipped = false, areItemsPicked = false, isOrderInProgress = false;
			boolean allItemsShipped = false;

			orderId = orderId.trim();
			// Map itemsShipped = null;//changes for Partial shipment 27/09/18
			Map itemsNotShipped = null;// changes for Partial shipment 27/09/18
			Map<String, List<String>> itemsShipped = null;
			Map<String, Long> itemsPicked = null;
			Map<String, Map> shippingDetailsMap = getShippingDetails(orderId);
			if (!shippingDetailsMap.isEmpty()) {
				itemsShipped = shippingDetailsMap.get("itemsShipped");
				itemsNotShipped = shippingDetailsMap.get("itemsNotShipped");
			}

			// Check atleast one item is shipped

			if (itemsShipped != null && itemsShipped.size() > 0) {
				atleastOneItemShipped = itemsShipped.entrySet().stream()
						.filter(i -> (new BigDecimal(i.getValue().get(2)).compareTo(BigDecimal.ZERO) > 0)).findFirst()
						.isPresent();
				/*
				 * atleastOneItemShipped = itemsShipped.entrySet().stream() .filter(i -> new
				 * BigDecimal((String)((List)i.getValue()).get(2)).compareTo(BigDecimal.ZERO) >
				 * 0).findFirst() .isPresent();
				 */

				/*
				 * Map<Integer, String> hmap = new HashMap<Integer, String>(); hmap.put(11,
				 * "Apple"); Map<Integer, String> result = hmap.entrySet().stream() .filter(map
				 * -> "Orange".equals(map.getValue())) .collect(Collectors.toMap(map ->
				 * map.getKey(), map -> map.getValue())); System.out.println("Result: " +
				 * result);
				 */

			}
			LOGGER.info("Is atleast one item shipped: " + atleastOneItemShipped);

			List<Object> pickedItemsQty = getPickedQty(orderId);
			if (pickedItemsQty != null && pickedItemsQty.size() > 0) {
				itemsPicked = pickedItemsQty.stream().collect(Collectors.toMap(item -> ((Object[]) item)[1].toString(),
						item -> Long.valueOf(((Object[]) item)[2].toString())));
			}
			// Check atleast one item is picked
			areItemsPicked = (itemsPicked != null)
					? itemsPicked.values().stream().filter(pickedQty -> pickedQty > 0L).findFirst().isPresent()
					: false;
			LOGGER.info("Are any items picked: " + areItemsPicked);

			// Check for existing partial RTlog records
			// if (true) {
			EntityManager em = emf.createEntityManager();
			TypedQuery<OrderTranHeader> orderPartialQuery = em
					.createNamedQuery("INVOICE_DETAILS_ORDERID_TYPE", OrderTranHeader.class)
					.setParameter("orderId", orderId).setParameter("ordTyp", "26")
					.setParameter("scOrd", new BigDecimal(4)).setParameter("tranStatus", new BigDecimal(2));

			List<OrderTranHeader> orderPartialRecords = orderPartialQuery.getResultList();

			if (orderPartialRecords != null && orderPartialRecords.size() > 0) {
				LOGGER.info("Do order partial records already exist: true");
				return false;
			}
			LOGGER.info("Do order partial records already exist: false");
			// }

			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setMaximumResults(5);
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(orderId);
			// OrderTranHeader[] orders = getTransactionsInvoices(search);
			OrderTranHeader[] orders = this.getTransactionsInvoices((OrderTransactionSearchCriteriaIfc) search);
			ord_tran_header = (orders != null && orders.length > 0) ? orders[0] : null;

			if (ord_tran_header != null && ord_tran_header.getOrdTy().equalsIgnoreCase("23") /*
																								 * Order not yet
																								 * complete
																								 */
					&& ord_tran_header.getTransactionStatus().compareTo(new BigDecimal(2)) == 0 /*
																								 * Transaction complete
																								 */
					&& ord_tran_header.getScOrd().compareTo(new BigDecimal(2)) >= 0 /* Order that are in-progress */
					&& ord_tran_header.getScOrd().compareTo(new BigDecimal(3)) <= 0) {
				isOrderInProgress = true;
			}
			LOGGER.info("For Order Id: " + orderId + ", In-Progress status: " + isOrderInProgress);

			// Check if not all items are shipped
			/*
			 * Predicate<Entry<String, List<String>>> orderQtyGreaterThanShippedQty = (
			 * Entry<String, List<String>> i) -> new BigDecimal(i.getValue().get(1))
			 * //get(1) - order qty, get(2) - ship qty .compareTo(new
			 * BigDecimal(i.getValue().get(2))) > 0; // checking order qty > ship qty
			 */ Predicate<Map.Entry> orderQtyGreaterThanShippedQty = i -> new BigDecimal(
					(String) ((List) i.getValue()).get(1))
							.compareTo(new BigDecimal((String) ((List) i.getValue()).get(2))) > 0;

			/*
			 * if(itemsShipped != null) allItemsShipped =
			 * !itemsShipped.entrySet().stream().filter(orderQtyGreaterThanShippedQty)
			 * .findFirst().isPresent();
			 */
			if (itemsShipped != null && itemsNotShipped.size() == 0) {
				allItemsShipped = true;
			}
			LOGGER.info("For Order Id: " + orderId + ", All items shipped: " + allItemsShipped);

			if (isOrderInProgress /* Order is in-progress */
					&& atleastOneItemShipped /* Atleast one item is shipped */
					&& !areItemsPicked /* No item(s) are being picked */
					&& !allItemsShipped) /* Not all Items shipped */
			{
				LOGGER.debug("For Order Id: " + orderId + " partial shipping available: true");
				return true;
			}
		}
		LOGGER.debug("For Order Id: " + orderId + " partial shipping available: false");
		return false;
	}

	/**
	 * @return true and false
	 */

	@SuppressWarnings("unchecked")
	@Override
	public boolean inProgressOrder(String orderId) {
		if (orderId != null) {

			OrderTranHeader ord_tran_header; // changes for Partial shipment 27/09/18
			boolean isOrderInProgress = false;
			orderId = orderId.trim();
			EntityManager em = emf.createEntityManager();

			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setMaximumResults(5);
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(orderId);

			OrderTranHeader[] orders = this.getTransactionsInvoices((OrderTransactionSearchCriteriaIfc) search);
			ord_tran_header = (orders != null && orders.length > 0) ? orders[0] : null;
			if (ord_tran_header != null && ord_tran_header.getOrdTy().equalsIgnoreCase("23") /*
																								 * Order not yet
																								 * complete
																								 */
					&& ord_tran_header.getTransactionStatus().compareTo(new BigDecimal(2)) == 0 /*
																								 * Transaction complete
																								 */
					&& ord_tran_header.getScOrd().compareTo(new BigDecimal(2)) >= 0 /* Order that are in-progress */
					&& ord_tran_header.getScOrd().compareTo(new BigDecimal(3)) <= 0) {
				try {
					em.getTransaction().begin();
					String sqlQry = "update ORD_TRAN_HEADER header set sc_ord='1' where (header.TRN_SEQ,header.DC_DY_ORD,header.ORD_WS,header.RT_STR_ID)="
							+ "(SELECT MAX(T1.TRN_SEQ), T1.DC_DY_ORD,T1.ORD_WS,	 T1.RT_STR_ID	 FROM ord_tran_sum T2,	 ORD_TRAN_HEADER T1 WHERE T1.RT_STR_ID = T2.RT_STR_ID "
							+ " AND T1.ORD_WS  = T2.ORD_WS	AND T1.TRN_SEQ  = T2.TRN_SEQ AND T1.DC_DY_ORD = T2.DC_DY_ORD AND T2.id_Ord ='"
							+ orderId + "' group by " + "T1.RT_STR_ID,T1.DC_DY_ORD,T1.ORD_WS)";

					LOGGER.info("trying to update the ORD_TRN_head TABLE table for the In progress orders.... ");

					Query qe = em.createNativeQuery(sqlQry);
					int countUpdated = qe.executeUpdate();
					em.getTransaction().commit();
					LOGGER.info("updated the ORD_TRN_Head TABLE table for the In progress orders.... " + countUpdated);

				} catch (Exception e1) {
					LOGGER.error(e1.getMessage(), e1.getCause());
				}
				isOrderInProgress = true;
			}
			LOGGER.info("For Order Id: " + orderId + ", In-Progress status: " + isOrderInProgress);
			return true;
		}
		LOGGER.debug("For Order Id: " + orderId + " not able to open IN WMS: false");
		return false;
	}

	/**
	 * @return Map("DetailType", Map("ItemId", List("ItemDescription", "OrderQty",
	 *         "ShipQty")))
	 */
	@Override
	public Map<String, Map> getShippingDetails(String orderId) {
		Map<String, Map> shippingDetailMap = new LinkedHashMap<String, Map>();
		Map<String, List<String>> itemsShipped, itemsNotShipped;

		if (orderId != null) {
			orderId = orderId.trim();
			itemsShipped = new LinkedHashMap<String, List<String>>();
			itemsNotShipped = new LinkedHashMap<String, List<String>>();
			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setMaximumResults(5);
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(orderId);
			OrderTranHeader[] orders = getTransactionsInvoices(search);
			OrderTranHeader ord_tran_header = (orders != null && orders.length > 0) ? orders[0] : null;
			List<Object> shippedItemsQty = getShippedQty(orderId);
			if (shippedItemsQty != null && shippedItemsQty.size() > 0 && ord_tran_header != null) {
				// Shipped
				String itemId;
				for (Object shippedItem : shippedItemsQty) {
					List<OrderTranLineItem> shippedLineItems = ord_tran_header.getOrdTranLineItems().stream()
							.filter(line -> line.getItemId().equalsIgnoreCase(((Object[]) shippedItem)[1].toString()))
							.collect(Collectors.toList());
					BigDecimal lineQty = shippedLineItems.stream().map(line -> line.getLineQnt())
							.reduce(BigDecimal.ZERO, BigDecimal::add);
					List<String> shippedItemDetail = new ArrayList<String>();
					itemId = (((Object[]) shippedItem)[1].toString()); // Item ID
					if (lineQty.compareTo(BigDecimal.ZERO) > 0) {
						shippedItemDetail.add(shippedLineItems.get(0).getDeItmShrtRcpt()); // Item Description
						shippedItemDetail.add(lineQty.toString()); // Item Qty
						shippedItemDetail.add(((Object[]) shippedItem)[2].toString()); // Item Shipped Qty
						itemsShipped.put(itemId, shippedItemDetail);
					}
					// Partially Shipped
					// if (lineQty.compareTo(new BigDecimal(((Object[]) shippedItem)[2].toString()))
					// > 0) {
					if (lineQty.compareTo(new BigDecimal(((Object[]) shippedItem)[2].toString())) <= 0)
						continue;
					List<String> partiallyShippedDetails = new ArrayList<String>();
					// String itemId1 = itemId; // Item ID
					partiallyShippedDetails.add(shippedItemDetail.get(0)); // Item Description
					partiallyShippedDetails.add(shippedItemDetail.get(1)); // Item Qty
					partiallyShippedDetails
							.add(lineQty.subtract(new BigDecimal(((Object[]) shippedItem)[2].toString())).toString()); // Item
																														// Not
																														// Shipped
																														// Qty
					itemsNotShipped.put(itemId, partiallyShippedDetails);
				}
				// }
				// Not Shipped
				// if (true) {
				Set<String> shippedItems = shippedItemsQty.stream().map(s -> ((Object[]) s)[1].toString())
						.collect(Collectors.toSet());
				List<OrderTranLineItem> notShippedLineItems = ord_tran_header.getOrdTranLineItems().stream()
						.filter(line -> !shippedItems.contains(line.getItemId())).collect(Collectors.toList());
				for (OrderTranLineItem notShippedLineItem : notShippedLineItems) {
					List<String> notShippedDetails = new ArrayList<String>();
					itemId = notShippedLineItem.getItemId(); // Item ID
					notShippedDetails.add(notShippedLineItem.getDeItmShrtRcpt()); // Item Description
					notShippedDetails.add(notShippedLineItem.getLineQnt().toString()); // Item Qty
					notShippedDetails.add(notShippedLineItem.getLineQnt().toString()); // Item Shipped Qty
					itemsNotShipped.put(itemId, notShippedDetails);
				}

				shippingDetailMap.put("itemsShipped", itemsShipped);
				shippingDetailMap.put("itemsNotShipped", itemsNotShipped);

				LOGGER.debug("For Order Id: " + orderId + " Items shipped are: \n\t" + itemsShipped);
				LOGGER.debug("For Order Id: " + orderId + " Items not shipped are: \n\t" + itemsNotShipped);
			} /*
				 * else { shippingDetailMap.put("itemsShipped", null);
				 * shippingDetailMap.put("itemsNotShipped", null); }
				 */
		}
		/*
		 * else{ shippingDetailMap.put("itemsShipped", null);
		 * shippingDetailMap.put("itemsNotShipped", null); }
		 */

		return shippingDetailMap;
	}

	@Override
	public Map<String, OrderTranHeader> processPartialShipping(String orderId, String partialShippingReasonCode,
			String employeeId) {
		Map<String, OrderTranHeader> processedOrder = new LinkedHashMap<String, OrderTranHeader>();
		if (orderId != null && partialShippingReasonCode != null) {
			orderId = orderId.trim();

			// Order Init Record
			// ---------------------------------------------------------------------------------------------------------------------
			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setMaximumResults(5);
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(orderId);
			OrderTranHeader[] orders = getTransactionsInvoices(search);
			OrderTranHeader orderInit = (orders != null && orders.length > 0) ? orders[0] : null;
			processedOrder.put("orderInit", orderInit);

			// Get Shipping details
			Map<String, List<String>> itemsShipped = null;

			Map<String, Map> shippingDetailsMap = getShippingDetails(orderId);

			/*
			 * if (shippingDetailsMap != null && !shippingDetailsMap.isEmpty()) { if
			 * (shippingDetailsMap.containsKey("itemsShipped")) itemsShipped =
			 * shippingDetailsMap.get("itemsShipped"); // if
			 * (shippingDetailsMap.containsKey("itemsNotShipped")) // itemsNotShipped =
			 * shippingDetailsMap.get("itemsNotShipped"); }
			 */

			if (shippingDetailsMap != null && !shippingDetailsMap.isEmpty()
					&& shippingDetailsMap.containsKey("itemsShipped")) {
				itemsShipped = shippingDetailsMap.get("itemsShipped");
			}

			// Store Line Items and their original line item sequence
			Map<String, String> orgSeqItmIdMap = new HashMap<String, String>();
			for (OrderTranLineItem orderTranLineItem : orderInit.getOrdTranLineItems()) {
				// orgSeqItmIdMap.put(orderTranLineItem.getId().getOrdLnItmSeq() + "",
				// orderTranLineItem.getItemId());
				orgSeqItmIdMap.put(String.valueOf(orderTranLineItem.getId().getOrdLnItmSeq()),
						orderTranLineItem.getItemId());
			}

			// Order Partial Record
			// ---------------------------------------------------------------------------------------------------------------------
			List<OrderTranLineItem> orderPartialSplitLineItems = new ArrayList<OrderTranLineItem>();
			List<OrderTranLineItem> orderPartialGroupedLineItems = new ArrayList<OrderTranLineItem>();

			OrderTranHeader orderPartial = null;
			orderPartial = SerializationUtils.clone(orderInit);
			// orderPartial =
			// (OrderTranHeader)SerializationUtils.clone((Serializable)orderInit);
			List<OrderTranLineItem> orderInitTempLineItems = orderPartial.getOrdTranLineItems();
			// Split line items
			try {
				orderInitTempLineItems = promoService.splitTranLineItems(orderInitTempLineItems);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e.getCause());
			}
			// Pick best deal items for shipping
			for (String itemId : itemsShipped.keySet()) {
				long shippedQty = Long.parseLong(itemsShipped.get(itemId).get(2));
				List<OrderTranLineItem> shippedItemsList = orderInitTempLineItems.stream()
						.filter(item -> item.getItemId().equalsIgnoreCase(itemId)) // Filter by Item Id
						// .filter(item -> item.getItmTy() != null &&
						// item.getItmTy().compareTo(BigDecimal.ONE) == 0) // Filter stock items
						.sorted((item1, item2) -> item1.getExtnDscLnItm().compareTo(item2.getExtnDscLnItm())) // Sort in
																												// ascending
																												// order
																												// of
																												// final
																												// price
						.limit(shippedQty) // Limit to shipped quantity
						.collect(Collectors.toList()); // Collect to List
				orderPartialSplitLineItems.addAll(shippedItemsList);
			}

			// Also include all service items
			List<OrderTranLineItem> serviceItemsList = orderInitTempLineItems.stream()
					.filter(item -> item.getItmTy() != null && item.getItmTy().compareTo(new BigDecimal(2)) == 0) // Filter
																													// by
																													// Service
																													// Items
					.collect(Collectors.toList()); // Collect to List
			orderPartialSplitLineItems.addAll(serviceItemsList);

			LOGGER.debug("Order Partial Split Line Items: \n\t" + orderPartialSplitLineItems);

			// Group the Line items
			try {
				// OrderTranHeader tranHeaderTemp = new OrderTranHeader();
				// promoService.groupLineItems(orderPartialSplitLineItems, tranHeaderTemp,
				// orgSeqItmIdMap);
				// orderPartialGroupedLineItems = tranHeaderTemp.getOrdTranLineItems();
				orderPartialGroupedLineItems = promoService.groupLineItems(orderPartialSplitLineItems);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e.getCause());
			}

			// Reset the line item sequence
			orderPartialGroupedLineItems = resetLineItemSequence(orderPartialGroupedLineItems);
			LOGGER.debug("Order Partial Grouped Line Items: \n\t" + orderPartialGroupedLineItems);
			// Set line items to Order Header
			orderPartial.setOrdTranLineItems(orderPartialGroupedLineItems);
			// Update header information
			orderPartial = getUpdatedPartialHeader(orderPartial, employeeId);
			// Calculate and attach orderTranSum to Order Header
			orderPartial = getCalculatedOrderTranSum(orderPartial);
			// Clone orderPartial
			OrderTranHeader orderPartialClone = SerializationUtils.clone(orderPartial);
			// Persist the Partial Order
			EntityManager em = emf.createEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(orderPartialClone);

				/// update the order shipment table for the duplicated items
				LOGGER.info("trying to update the shippment table for the duplicated orders.... ");
				Date dt = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				String value = format.format(dt);
				Query updateShipQtyTbl = em.createNamedQuery("UPD_ORDINV_SHPQTY").setParameter("rtlogbatch", value)
						.setParameter("orderId", orderId);
				updateShipQtyTbl.executeUpdate();
				LOGGER.info("updated the shippment table for the duplicated orders.... ");

			} catch (Exception e1) {
				LOGGER.error(e1.getMessage(), e1.getCause());
			}

			// Order Cancel Record
			// ---------------------------------------------------------------------------------------------------------------------
			List<OrderTranLineItem> orderCancelSplitLineItems = new ArrayList<OrderTranLineItem>();
			List<OrderTranLineItem> orderCancelGroupedLineItems = new ArrayList<OrderTranLineItem>();

			OrderTranHeader orderCancel = null;
			orderCancel = SerializationUtils.clone(orderInit);

			if (!orderPartialSplitLineItems.isEmpty()) {
				orderCancelSplitLineItems = new ArrayList<OrderTranLineItem>(orderInitTempLineItems);
				orderCancelSplitLineItems.removeAll(orderPartialSplitLineItems);
				LOGGER.debug(orderCancelSplitLineItems);
			}

			try {
				// OrderTranHeader tranHeaderTemp = new OrderTranHeader();
				// promoService.groupLineItems(orderCancelSplitLineItems, tranHeaderTemp,
				// orgSeqItmIdMap);
				// orderCancelGroupedLineItems = tranHeaderTemp.getOrdTranLineItems();
				orderCancelGroupedLineItems = promoService.groupLineItems(orderCancelSplitLineItems);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e.getCause());
			}

			// Reset the line item sequence
			orderCancelGroupedLineItems = resetLineItemSequence(orderCancelGroupedLineItems);
			// Set line items to Order Header
			orderCancel.setOrdTranLineItems(orderCancelGroupedLineItems);
			// Update header information
			orderCancel = getUpdatedCancelHeader(orderCancel, employeeId);
			// Calculate and attach orderTranSum to Order Header
			orderCancel = getCalculatedOrderTranSum(orderCancel);
			// Set cancel comments and reason code
			orderCancel.setTransComment(partialShippingReasonCode);
			orderCancel.setReturnReasonCode(partialShippingReasonCode);
			// Clone orderCancel
			OrderTranHeader orderCancelClone = SerializationUtils.clone(orderCancel);
			// Persist the cancelled order

			try {
				// The below if condition to the persist method is added to avoid persist the
				// cancel in case if line items are empty - prod issue - unable to recreate in
				// offshore so as a workaround this condition is added
				if (orderCancelClone.getOrdTranLineItems() != null
						&& orderCancelClone.getOrdTranLineItems().size() > 0) {
					em.persist(orderCancelClone);
				}
				em.getTransaction().commit();

				processedOrder.put("orderPartial", orderPartialClone);
				processedOrder.put("orderCancel", orderCancelClone);

				// The below if condition to the persist method is added to avoid
				// saveTenderLineForPartialOrderCancel in case if line items are empty - prod
				// issue - unable to recreate in offshore so as a workaround this condition is
				// added
				if (orderCancelClone.getOrdTranLineItems() != null
						&& orderCancelClone.getOrdTranLineItems().size() > 0) {
					saveTenderLineForPartialOrderCancel(orderCancelClone);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e.getCause());
			}

		}
		return processedOrder;
	}

	private void saveTenderLineForPartialOrderCancel(OrderTranHeader orderCancel) throws OrderTransactionException {
		TranLineItemTender tenderLineItem = new TranLineItemTender();
		TranLineItemTenderPK tenderLineItempk = new TranLineItemTenderPK();

		tenderLineItempk.setRtStrId(orderCancel.getId().getRtStrId());
		tenderLineItempk.setOrdWs(orderCancel.getId().getOrdWs());
		tenderLineItempk.setTrnSeq(orderCancel.getId().getTrnSeq());
		tenderLineItempk.setTrnLnItmSeq(1);//// set hard coded for now as no payment is accepted at the time of payment
		tenderLineItempk.setDcDyOrd(orderCancel.getId().getDcDyOrd());

		tenderLineItem.setId(tenderLineItempk);
		tenderLineItem.setMoItmLnTnd(orderCancel.getOrdTranSum().getDkartNetTot());
		tenderLineItem.setTyTnd("CARD");/// payment type as house account
		tenderLineItem.setIdOrd("HOUSE");//// using unused column to store sub tender type
		// tenderLineItem.setIdAcntNmb(new
		// BigDecimal(orderCancel.getOrdTranSum().getOrdIdCt()));
		tenderLineItem.setIdAcntNmb(orderCancel.getOrdTranSum().getOrdIdCt());
		tenderLineItem.setDkTsCrtRcrd(new Date());

		saveTenderLineItems(tenderLineItem);

	}

	private OrderTranHeader getUpdatedPartialHeader(OrderTranHeader orderPartial, String employeeId) {
		if (orderPartial != null) {
			orderPartial.setIdOpr(employeeId);

			long trnSeq = transactionService.getTranSeq(orderPartial.getId().getRtStrId());
			String businessDate = getDateForOrderHead();

			// Header primary key update
			orderPartial.getId().setTrnSeq(trnSeq);
			orderPartial.getId().setDcDyOrd(businessDate);

			// Line items primary key update
			orderPartial.getOrdTranLineItems().forEach(lineItem -> {
				lineItem.getId().setTrnSeq(trnSeq);
				lineItem.getId().setDcDyOrd(businessDate);
				if (lineItem.getOrdTranDscItms() != null) // Discount line items primary key update
					lineItem.getOrdTranDscItms().forEach(discItem -> {
						discItem.getId().setTrnSeq(trnSeq);
						discItem.getId().setDcDyOrd(businessDate);
						discItem.setOrdTranLineItem(lineItem);
					});
				lineItem.setOrdTranHeader(orderPartial);
			});

			// Sum primary key update
			orderPartial.getOrdTranSum().getId().setTrnSeq(trnSeq);
			orderPartial.getOrdTranSum().getId().setDcDyOrd(businessDate);
			orderPartial.getOrdTranSum().setOrdTranHeader(orderPartial);

			// lpo primary key update
			if (orderPartial.getOrdTranLpo() != null) {
				orderPartial.getOrdTranLpo().getId().setTrnSeq(trnSeq);
				orderPartial.getOrdTranLpo().getId().setDcDyOrd(businessDate);
				orderPartial.getOrdTranLpo().setOrdTranHeader(orderPartial);
			}
			// TODO update tax line

			// Header status updates
			orderPartial.setOrdTy("26");
			orderPartial.setScOrd(new BigDecimal(4));
			orderPartial.setTransactionStatus(new BigDecimal(2));
			orderPartial.setIdTlogBtch(new BigDecimal(-1));
			orderPartial.setIdTrlogBtch("-1");

			// Header timestamp updates
			Date date = new Date();
			orderPartial.setTsCrtRcrd(date);
			orderPartial.setTsMdfRcrd(date);
			orderPartial.setTsOrdBgn(date);
			orderPartial.setTsOrdEnd(date);

		}
		return orderPartial;
	}

	private OrderTranHeader getUpdatedCancelHeader(OrderTranHeader orderCancel, String employeeId) {
		if (orderCancel != null) {
			orderCancel.setIdOpr(employeeId);

			long trnSeq = transactionService.getTranSeq(orderCancel.getId().getRtStrId());
			String businessDate = getDateForOrderHead();

			// Header primary key update
			orderCancel.getId().setTrnSeq(trnSeq);
			orderCancel.getId().setDcDyOrd(businessDate);

			// Line items primary key update
			orderCancel.getOrdTranLineItems().forEach(lineItem -> {
				lineItem.getId().setTrnSeq(trnSeq);
				lineItem.getId().setDcDyOrd(businessDate);
				if (lineItem.getOrdTranDscItms() != null) // Discount line items primary key update
					lineItem.getOrdTranDscItms().forEach(discItem -> {
						discItem.getId().setTrnSeq(trnSeq);
						discItem.getId().setDcDyOrd(businessDate);
						discItem.setOrdTranLineItem(lineItem);
					});
				lineItem.setOrdTranHeader(orderCancel);
			});

			// Sum primary key update
			orderCancel.getOrdTranSum().getId().setTrnSeq(trnSeq);
			orderCancel.getOrdTranSum().getId().setDcDyOrd(businessDate);
			orderCancel.getOrdTranSum().setOrdTranHeader(orderCancel);

			// Set original order id for future reference
			orderCancel.getOrdTranSum().setOrigOrderId(orderCancel.getOrdTranSum().getIdOrd());
			orderCancel.getOrdTranSum().setIdOrd(getOrderId(orderCancel));

			// lpo primary key update
			if (orderCancel.getOrdTranLpo() != null) {
				orderCancel.getOrdTranLpo().getId().setTrnSeq(trnSeq);
				orderCancel.getOrdTranLpo().getId().setDcDyOrd(businessDate);
				orderCancel.getOrdTranLpo().setOrdTranHeader(orderCancel);
			}

			// TODO update tax line

			// Header status updates
			orderCancel.setOrdTy("25");
			orderCancel.setScOrd(new BigDecimal(8));
			orderCancel.setTransactionStatus(new BigDecimal(2));
			orderCancel.setIdTlogBtch(new BigDecimal(-1));
			orderCancel.setIdTrlogBtch("-1");

			// Header timestamp updates
			Date date = new Date();
			orderCancel.setTsCrtRcrd(date);
			orderCancel.setTsMdfRcrd(date);
			orderCancel.setTsOrdBgn(date);
			orderCancel.setTsOrdEnd(date);

		}
		return orderCancel;
	}

	/**
	 * Logic copied from {@link NewOrderAction#updateTotals()}
	 * 
	 * @param orderTranHeader
	 * @return
	 */
	private OrderTranHeader getCalculatedOrderTranSum(OrderTranHeader orderTranHeader) {
		if (orderTranHeader != null)// If transaction is initiated
		{
			List<OrderTranLineItem> ordLineList = orderTranHeader.getOrdTranLineItems();
			BigDecimal subTotal = BigDecimal.ZERO;
			BigDecimal discountedTotal = BigDecimal.ZERO;
			BigDecimal expenses = BigDecimal.ZERO;
			if (ordLineList != null) {
				for (OrderTranLineItem orderTranLineItem : ordLineList) {
					if (orderTranLineItem.getItmTy() != null && orderTranLineItem.getItmTy().intValue() == 2) {
						expenses = expenses.add(orderTranLineItem.getExtnLnItmRtn());
					} else {
						// sub Total
						subTotal = subTotal.add(orderTranLineItem.getExtnLnItmRtn());
					}
				}
			}
			// List<OrderTranSum> orderTranSumList = new ArrayList<OrderTranSum>();
			OrderTranSum orderTranSum = orderTranHeader.getOrdTranSum();
			orderTranSum.setDkartSlsTot(subTotal);
			// transaction level discounts
			// updatingTranLevelDiscounts(orderTranHeader);
			BigDecimal serviceTotal = BigDecimal.ZERO;
			if (ordLineList != null) {
				for (OrderTranLineItem orderTranLineItem : ordLineList) {
					// Trans level Extended discount amount
					discountedTotal = discountedTotal.add(orderTranLineItem.getExtnDscLnItm());

					// Need service items Extended price for transaction level
					// discount validations
					if (orderTranLineItem.getItmTy() != null
							&& orderTranLineItem.getItmTy().equals(new BigDecimal(2))) {
						serviceTotal = serviceTotal.add(orderTranLineItem.getExtnLnItmRtn());
					}

					// To display Total discount applied on item as a label on sales screen
					// totalApplyedDiscountAmtOnItem(orderTranLineItem);

				}
			}
			BigDecimal totalDisc = subTotal.subtract(discountedTotal);
			BigDecimal total = discountedTotal.add(new BigDecimal(0));// adding TAX once the TAX parameter is enabled

			orderTranSum.setDkartDscTot(totalDisc);
			orderTranSum.setDkartNetTot(total);
			orderTranSum.setDkartTaxTot(new BigDecimal(0));
			orderTranSum.setDkartExpenses(expenses);

			BigDecimal totalExcludingServicePrice = total.subtract(serviceTotal);
			orderTranSum.setTotalExcludingServicePrice(totalExcludingServicePrice);// need it for validating transaction
																					// level discount amt(should not be
																					// grater than
																					// totalExcludingServicePrice) while
																					// giving in transaction amt popup

			orderTranHeader.setOrdTranSum(orderTranSum);

			return orderTranHeader;
		}
		return null;
	}

	private List<OrderTranLineItem> resetLineItemSequence(List<OrderTranLineItem> orderTranLineItemsList) {
		if (orderTranLineItemsList != null) {
			int i = 1;
			ListIterator<OrderTranLineItem> listIter = orderTranLineItemsList.listIterator();
			while (listIter.hasNext()) {
				listIter.next().getId().setOrdLnItmSeq(i++);
			}
			return orderTranLineItemsList;
		}
		return null;
	}
}