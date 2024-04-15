package rispl.dkart.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.item.RisplDkItmClssMod;
import rispl.db.model.item.pricing.RisplDkPrdvnItmNelg;
import rispl.db.model.item.pricing.RisplDkPrdvnMmitm;
import rispl.db.model.item.pricing.RisplDkPrdvnRule;
import rispl.db.model.item.pricing.RisplDkPrdvnRuleDisc;
import rispl.db.model.item.pricing.RisplDkPrdvnRuleElg;
import rispl.db.model.item.pricing.RisplDkPrdvnRulePK;
import rispl.db.model.item.pricing.RisplDkPrdvnTrshldElg;
import rispl.dime.schedular.imports.DKartConstantsIfc;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.entities.customer.CustomerSegment;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeaderPK;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkart.services.entities.transaction.OrderTranLineItemPK;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import rispl.dkart.services.entities.transaction.OrderTranSumPK;
import rispl.services.item.AbstractItemService;
import utility.ConfigUtils;

@ManagedBean
public class PromotionsService extends AbstractItemService {

	/*
	 * HashMap to store Discount rule name(Key) and boolean value(value) to check if target items are mandatory for the rule 
	 */
	final static Map<String, Boolean> targetsRequired = new HashMap<String, Boolean>();
	static {
		targetsRequired.put(DKartConstantsIfc.BUY_NOF_XGET_YAT_Z_OFF, true);
		targetsRequired.put(DKartConstantsIfc.BUY_NOF_XGET_YAT_Z_$_OFF, true);
		targetsRequired.put(DKartConstantsIfc.BUY_NOF_XGET_YAT_Z_$, true);
		targetsRequired.put(DKartConstantsIfc.BUY_NOF_XGET_HIGHEST_PRICED_XAT_Z_OFF, false);
		targetsRequired.put(DKartConstantsIfc.BUY_NOF_XGET_LOWEST_PRICED_XAT_Z_OFF, false);
		targetsRequired.put(DKartConstantsIfc.BUY_$_NOR_MORE_OF_XGET_YAT_Z_$_OFF, true);
		targetsRequired.put(DKartConstantsIfc.BUY_$_NOR_MORE_OF_XGET_YAT_Z_OFF, true);
		targetsRequired.put(DKartConstantsIfc.BUY_$_NOR_MORE_OF_XGET_YAT_Z_$, true);
		targetsRequired.put(DKartConstantsIfc.BUY_NOF_XFOR_Z_$, false);
		targetsRequired.put(DKartConstantsIfc.BUY_NOF_XFOR_Z_OFF, false);
		targetsRequired.put(DKartConstantsIfc.BUY_NOF_XFOR_Z_$_OFF, false);
		targetsRequired.put(DKartConstantsIfc.BUY_NOR_MORE_OF_XFOR_Z_OFF, false);
		targetsRequired.put(DKartConstantsIfc.BUY_NOR_MORE_OF_XFOR_Z_$_EACH, false);
		targetsRequired.put(DKartConstantsIfc.BUY_$_NOF_XFOR_Z_$_OFF, false);
		targetsRequired.put(DKartConstantsIfc.BUY_$_NOF_XFOR_Z_OFF, false);
	}

	private static final Logger LOGGER = LogManager.getLogger(PromotionsService.class);

	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	
	Map<RisplDkPrdvnRulePK, BigDecimal> appldRuleIdsDisAmt = new HashMap<RisplDkPrdvnRulePK, BigDecimal>();
	Map<String, BigDecimal> itemClassificationTotals = null;
	private final String format = "format.currency";

	public boolean anyActivePromotions(String storeId){
		boolean promExists = false;
		try{
			LOGGER.info("Checking if any Active Promotions Exists for Store : "+storeId);
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			EntityManager entityManager = getEntityManager();			
			Query chkPromQry = entityManager.createNativeQuery(DKartConstantsIfc.PROM_EXIST_CHK_QUERY);
			chkPromQry.setParameter(1, storeId);
			chkPromQry.setParameter(2, timeStamp);
			chkPromQry.setParameter(3, timeStamp);
			List reslt = chkPromQry.getResultList();
			if(reslt!=null && reslt.size()>0){
				promExists = true;
			}
			LOGGER.info("Active Promotions Exists for Store : "+promExists);
		}catch(Exception e){
			LOGGER.error(e);
		}
			
		return promExists;
	}
	
	public void applyPromotions(OrderTranHeader transaction) {
		if(anyActivePromotions(transaction.getId().getRtStrId())){
			try {
				LOGGER.info("In applyPromotions method");
				List<OrderTranLineItem> orderTranLineItems = transaction.getOrdTranLineItems();
				Map<String, BigDecimal> seqItmQty = new HashMap<String, BigDecimal>();
				Map<String, String> seqItmId = new HashMap<String, String>();
				for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
					seqItmId.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", orderTranLineItem.getItemId());
					if(seqItmQty.containsKey(orderTranLineItem.getId().getOrdLnItmSeq() + "")){
						seqItmQty.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", seqItmQty.get(orderTranLineItem.getId().getOrdLnItmSeq()+"").add(orderTranLineItem.getLineQnt()));
					}else{
						seqItmQty.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", orderTranLineItem.getLineQnt());
					}
				}

				// clear existing discount rules
				clearAppliedDiscountRules(orderTranLineItems, transaction);
				LOGGER.info("Cleared all existing Advanced Promotion Rules");

				EntityManager entityManager = getEntityManager();

				Query advPrcngRules = entityManager.createQuery(
						"SELECT risplDkPrdvnRuleElg from RisplDkPrdvnRuleElg risplDkPrdvnRuleElg where risplDkPrdvnRuleElg.itmId in :idval and risplDkPrdvnRuleElg.tsRuDrvnEf <= :effctDate and risplDkPrdvnRuleElg.tsRuDrvnEp >= :endDate and risplDkPrdvnRuleElg.risplDkPrdvnRule.id.idStrRt in :stores",
						RisplDkPrdvnRuleElg.class);

				Query itmClassModQuery = entityManager.createQuery(
						"SELECT risplDkItmClssMod from RisplDkItmClssMod risplDkItmClssMod where risplDkItmClssMod.itmId in :idval",
						RisplDkItmClssMod.class);

				List<String> stores = new ArrayList<String>();
				stores.add(transaction.getId().getRtStrId());
				// stores.add("40402");

				List<String> vals = new ArrayList<String>();
				// vals.add("*");
				for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {

					if (!vals.contains(orderTranLineItem.getItemId())) {
						vals.add(orderTranLineItem.getItemId());
					}
				}

				itmClassModQuery.setParameter("idval", vals);
				List<RisplDkItmClssMod> risplDkItmClssMods = itmClassModQuery.getResultList();

				for (RisplDkItmClssMod risplDkItmClssMod : risplDkItmClssMods) {
					if (!vals.contains(risplDkItmClssMod.getClssFctnId())) {
						vals.add(risplDkItmClssMod.getClssFctnId());
					}
				}

				itmClassModQuery = entityManager.createQuery(
						"SELECT risplDkItmClssMod from RisplDkItmClssMod risplDkItmClssMod where risplDkItmClssMod.clssFctnId in :idval",
						RisplDkItmClssMod.class);
				itmClassModQuery.setParameter("idval", vals);
				risplDkItmClssMods = itmClassModQuery.getResultList();
				Map<String, Set<String>> classModItms = new HashMap<String, Set<String>>();

				for (RisplDkItmClssMod risplDkItmClssMod : risplDkItmClssMods) {
					if (!classModItms.containsKey(risplDkItmClssMod.getClssFctnId())) {
						Set<String> items = new HashSet<String>();
						items.add(risplDkItmClssMod.getItmId());
						classModItms.put(risplDkItmClssMod.getClssFctnId(), items);
					} else {
						Set<String> items = classModItms.get(risplDkItmClssMod.getClssFctnId());
						items.add(risplDkItmClssMod.getItmId());
						classModItms.put(risplDkItmClssMod.getClssFctnId(), items);
					}
				}

				Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
				advPrcngRules.setParameter("idval", vals);
				advPrcngRules.setParameter("effctDate", timeStamp);
				advPrcngRules.setParameter("endDate", timeStamp);
				advPrcngRules.setParameter("stores", stores);

				List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs = advPrcngRules.getResultList();
				LOGGER.info("Retrieved Current Discount Rules = " + risplDkPrdvnRuleElgs.size());

				// Splitting Line Items
				List<OrderTranLineItem> splttdOrdLineItms = splitTranLineItems(orderTranLineItems);
				LOGGER.info("Splitted Line Items Successfully !");
				
				List<OrderTranLineItem> splttdOrdLineItmsBkup = new ArrayList<OrderTranLineItem>();
				splttdOrdLineItmsBkup.addAll(splttdOrdLineItms);
				/*
				 * calculate item classification totals and store in map key = item
				 * classification code value = item classification totals
				 */
				itemClassificationTotals = calculateItemClassTotals(transaction, classModItms, risplDkPrdvnRuleElgs);
				LOGGER.info("Item Classification Totals Calculated Successfully");

				List<RisplDkPrdvnRule> validDiscountRules = getValidEligibleRules(risplDkPrdvnRuleElgs, orderTranLineItems,
						classModItms, splttdOrdLineItms, transaction);
				splttdOrdLineItms.clear();
				splttdOrdLineItms.addAll(splttdOrdLineItmsBkup);
				LOGGER.info("Filtered Valid Discount Rules");

				// Get Store wide promotions
				vals.clear();
				vals.add("*");
				timeStamp = new Timestamp(System.currentTimeMillis());
				advPrcngRules.setParameter("idval", vals);
				advPrcngRules.setParameter("effctDate", timeStamp);
				advPrcngRules.setParameter("endDate", timeStamp);
				advPrcngRules.setParameter("stores", stores);
				risplDkPrdvnRuleElgs = advPrcngRules.getResultList();
				List<RisplDkPrdvnRule> validStoreWideDiscountRules = getValidEligibleRules(risplDkPrdvnRuleElgs,
						orderTranLineItems, classModItms, splttdOrdLineItms, transaction);
				splttdOrdLineItms.clear();
				splttdOrdLineItms.addAll(splttdOrdLineItmsBkup);
				LOGGER.info("Retrieved Store Wide Promotions = " + validStoreWideDiscountRules.size());

				// If same source and targets available in multiple rule filter the
				// best one among them
				LOGGER.info("Calling swapBestDiscountRuleOutOfMultiple Logic");
				swapBestDiscountRuleOutOfMultiple(validDiscountRules, splttdOrdLineItms, classModItms, transaction);
				splttdOrdLineItms.clear();
				splttdOrdLineItms.addAll(splttdOrdLineItmsBkup);
				LOGGER.info("Swapped retrieved Discount Rules with the best rule");

				// Swap best valid store wide discount rules
				LOGGER.info("Calling swapBestDiscountRuleOutOfMultiple Logic");
				swapBestDiscountRuleOutOfMultiple(validStoreWideDiscountRules, splttdOrdLineItms, classModItms,
						transaction);
				splttdOrdLineItms.clear();
				splttdOrdLineItms.addAll(splttdOrdLineItmsBkup);
				LOGGER.info("Swapped retrieved Discount Rules with the best store wide discount rule");

				// Swap store wide and other discount rules
				if (validStoreWideDiscountRules.size() > 0) {
					LOGGER.info("Calling swapBestDiscountRuleOutOfMultiple Logic");
					swapStoreWideDiscountRule(validStoreWideDiscountRules, validDiscountRules, classModItms,
							splttdOrdLineItms, transaction);
					splttdOrdLineItms.clear();
					splttdOrdLineItms.addAll(splttdOrdLineItmsBkup);
					LOGGER.info("Swapped store wide and other discount rules for best discount rule");
				}

				/*
				 * Check and remove discount rules with customer segment specific
				 */
				LOGGER.info("Check and remove discount rules with customer segment specific");
				filterCustSegmntRules(transaction, validDiscountRules);

				// Iterate over all rules and apply
				// List<RisplDkPrdvnRule> currDisRules =
				// transaction.getAllCurrentDiscountRules();
				LOGGER.info("Applying Discounts for the line item");
				splttdOrdLineItms.clear();
				splttdOrdLineItms.addAll(splttdOrdLineItmsBkup);
				for (RisplDkPrdvnRule currDisRule : validDiscountRules) {

					// if rule = Buy$NofXforZ%off
					if (currDisRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_$_NOF_XFOR_Z_OFF)
							|| currDisRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_$_NOF_XFOR_Z_$_OFF)) {
						boolean isPercentOff = false;
						if (currDisRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_$_NOF_XFOR_Z_OFF)) {
							isPercentOff = true;
						}
						buyDollarNofXforZPrcntoff(currDisRule, splttdOrdLineItms, transaction, isPercentOff, classModItms);
					} else {
						getRuleDiscountAmount(currDisRule, splttdOrdLineItms, true, classModItms, transaction);
					}
				}
				LOGGER.info("Applied Discounts for the line items successfully");

				LOGGER.info("Grouping splitted line items based on item id, applied discount rule id");
				// transaction.setOrdTranLineItems(splttdOrdLineItms);
				if(transaction.isGroupDiscLinItms()){
					splttdOrdLineItms = groupLineItems(splttdOrdLineItms);
				}else{
					splttdOrdLineItms = groupNDLineItems(splttdOrdLineItms);
				}
				LOGGER.info("Grouped splitted line items successfully");

				// re-assign line sequence numbers
				LOGGER.info("Re-Assigning Line sequence numbers for all line items");
				List<OrderTranLineItem> reAssignedLines = reAssignLineSequenceNumbers(splttdOrdLineItms,seqItmId, seqItmQty);
		
				
				//Vishal--Updating item type(Service/Stock) and VPN number data
				for(int i=0;i<reAssignedLines.size();i++){
					OrderTranLineItem newItem=reAssignedLines.get(i);
					for(OrderTranLineItem oldItem:orderTranLineItems){
						if(newItem.getItemId().equals(oldItem.getItemId())){
							if(oldItem.getItmTy()!=null)
								newItem.setItmTy(oldItem.getItmTy());
							if(oldItem.getRegistryId()!=null)
								newItem.setRegistryId(oldItem.getRegistryId());
							if(oldItem.getRcRtnMr()!=null)
								newItem.setRcRtnMr(oldItem.getRcRtnMr());
							reAssignedLines.set(i, newItem);
						}
					}
				}
				transaction.setOrdTranLineItems(reAssignedLines);
				LOGGER.info("Re-Assigned Line sequence numbers for all line items");
			} catch (Exception e) {
				LOGGER.error(e.getMessage(),e);
			}finally{
				//saveSuspendedOrder(transaction);
			}
		}else{
			try{
				if(!transaction.isGroupDiscLinItms()){
			// Splitting Line Items
			List<OrderTranLineItem> orderTranLineItems = transaction.getOrdTranLineItems();
			Map<String, BigDecimal> seqItmQty = new HashMap<String, BigDecimal>();
			Map<String, String> seqItmId = new HashMap<String, String>();
			for (OrderTranLineItem orderTranLineItem : orderTranLineItems){
				seqItmId.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", orderTranLineItem.getItemId());
				if(seqItmQty.containsKey(orderTranLineItem.getId().getOrdLnItmSeq() + "")){
					seqItmQty.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", seqItmQty.get(orderTranLineItem.getId().getOrdLnItmSeq()+"").add(orderTranLineItem.getLineQnt()));
				}else{
					seqItmQty.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", orderTranLineItem.getLineQnt());
				}
			}
			List<OrderTranLineItem> splttdOrdLineItms = splitTranLineItems(orderTranLineItems);
			LOGGER.info("Splitted Line Items Successfully !");
			
			LOGGER.info("Grouping splitted line items based on item id, applied discount rule id");
			// transaction.setOrdTranLineItems(splttdOrdLineItms);
			
			splttdOrdLineItms = groupNDLineItems(splttdOrdLineItms);
			LOGGER.info("Grouped splitted line items successfully");

			// re-assign line sequence numbers
			LOGGER.info("Re-Assigning Line sequence numbers for all line items");
			List<OrderTranLineItem> reAssignedLines = reAssignLineSequenceNumbers(splttdOrdLineItms,seqItmId, seqItmQty);
			
			//Vishal--Updating item type(Service/Stock) and VPN number data
			for(int i=0;i<reAssignedLines.size();i++){
				OrderTranLineItem newItem=reAssignedLines.get(i);
				for(OrderTranLineItem oldItem:orderTranLineItems){
					if(newItem.getItemId().equals(oldItem.getItemId())){
						if(oldItem.getItmTy()!=null)
							newItem.setItmTy(oldItem.getItmTy());
						if(oldItem.getRegistryId()!=null)
							newItem.setRegistryId(oldItem.getRegistryId());
						if(oldItem.getRcRtnMr()!=null)
							newItem.setRcRtnMr(oldItem.getRcRtnMr());
						reAssignedLines.set(i, newItem);
					}
				}
			}
			transaction.setOrdTranLineItems(reAssignedLines);
			LOGGER.info("Re-Assigned Line sequence numbers for all line items");
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
	}
}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

		//method to save suspended transaction
		public void saveSuspendedOrder(OrderTranHeader transaction){
			EntityManager em = getEntityManager();
			if(!em.getTransaction().isActive()){
				em.getTransaction().begin();
			}
			
			transaction.setTransactionStatus(new BigDecimal(4));
			//Remove Already Existing Transaction
			removeSuspendedTransaction(transaction);
			
			List<OrderTranLineItem> orderTranLineItems = transaction.getOrdTranLineItems();
			for(OrderTranLineItem lineitem : orderTranLineItems){
				lineitem.getId().setDcDyOrd(transaction.getId().getDcDyOrd());
				lineitem.getId().setOrdWs(transaction.getId().getOrdWs());
				lineitem.getId().setRtStrId(transaction.getId().getRtStrId());
				lineitem.getId().setTrnSeq(transaction.getId().getTrnSeq());
			}
			
			OrderTranSumPK sumPk = new OrderTranSumPK();
			sumPk.setDcDyOrd(transaction.getId().getDcDyOrd());
			sumPk.setOrdWs(transaction.getId().getOrdWs());
			sumPk.setRtStrId(transaction.getId().getRtStrId());
			sumPk.setTrnSeq(transaction.getId().getTrnSeq());
			transaction.getOrdTranSum().setId(sumPk);

			OrderTranSum sum = transaction.getOrdTranSum();

			transaction.setOrdTranSum(null);
			transaction.setOrdTranLineItems(null);
			transaction.setTsOrdEnd(new Timestamp(new Date().getTime()));
			transaction.setTsCrtRcrd(new Timestamp(new Date().getTime()));
			transaction.setIdTlogBtch(new BigDecimal("-1"));
			sum.setOrdTranHeader(transaction);
			
			if (transaction.getCustomer() != null) {
				sum.setOrdIdCt(transaction.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId());
			}
			transaction.setOrdTranLineItems(orderTranLineItems);
			List<OrderTranSum> tranSums = new ArrayList<OrderTranSum>();
			tranSums.add(sum);
			transaction.setOrdTranSum(sum);
			em.persist(transaction);
			em.getTransaction().commit();
			
		}
		
		//method to check and remove suspended quotation transaction
		public void removeSuspendedTransaction(OrderTranHeader transaction){
			LOGGER.info("Executing removeSuspendedTransaction method");
			try{
				EntityManager em = getEntityManager();
				if(!em.getTransaction().isActive()){
					em.getTransaction().begin();
				}
				
				Query query = em.createNamedQuery("RMV_SUS_TRAN",OrderTranHeader.class);
				query.setParameter("tran",transaction.getId());
				int dltdRows = query.executeUpdate();
				if(dltdRows > 0){
					LOGGER.info("Suspended Transaction Found and Removed");
				}
				em.getTransaction().commit();
				
			}catch(Exception e){
				LOGGER.error(e.getMessage());
			}
		}
	
	/*
	 * Method to check if target items are required or not for a Discount Rule
	 */
	public boolean isTargetsRequired(String discountRuleName) {
		boolean required = false;
		required = targetsRequired.get(discountRuleName);
		return required;
	}

	/*
	 * Check and remove discount rules with customer segment specific
	 */
	public void filterCustSegmntRules(OrderTranHeader transaction, List<RisplDkPrdvnRule> validDiscountRules) {
		LOGGER.info("Executing filterCustSegmntRules Logic");
		CustomerSegment customerSegment = null;
		BigDecimal custPrcgpId = null;
		if (transaction.getCustomer() != null) {
			customerSegment = transaction.getCustomer().getCustomerHeader().getPrcngGrpId();
			custPrcgpId = customerSegment.getPrcngGrpId();
		}

		if (custPrcgpId != null) {
			List<RisplDkPrdvnRule> rulesTobeRemoved = new ArrayList<RisplDkPrdvnRule>();
			for (RisplDkPrdvnRule currDisRule : validDiscountRules) {
				if (currDisRule.getIdPrcgp() != null && currDisRule.getIdPrcgp().compareTo(custPrcgpId) != 0) {
					rulesTobeRemoved.add(currDisRule);
				}
			}

			if (rulesTobeRemoved.size() > 0) {
				validDiscountRules.removeAll(rulesTobeRemoved);
			}
		}
		LOGGER.info("Completed filterCustSegmntRules Logic");
	}

	
	public void testing(){
		OrderTranHeader transaction = new OrderTranHeader();
		OrderTranHeaderPK transactionpk = new OrderTranHeaderPK();
		transactionpk.setDcDyOrd("2016-10-16");
		transactionpk.setOrdWs("001");
		transactionpk.setRtStrId("50051");
		transactionpk.setTrnSeq(1);

		transaction.setId(transactionpk);
		
		List<OrderTranLineItem> lineItems = new ArrayList<OrderTranLineItem>();
		OrderTranLineItem line = new OrderTranLineItem();
		OrderTranLineItemPK linepk = new OrderTranLineItemPK();
		linepk.setDcDyOrd("2016-10-16");
		linepk.setOrdWs("001");
		linepk.setRtStrId("50051");
		linepk.setTrnSeq(1);
		linepk.setOrdLnItmSeq(0);
		
		line.setId(linepk);
		lineItems.add(line);
		
		List<OrderTranSum> tranSums = new ArrayList<OrderTranSum>();
		OrderTranSum tranSum = new OrderTranSum();
		OrderTranSumPK tranSumpk = new OrderTranSumPK();
		tranSumpk.setDcDyOrd("2016-10-16");
		tranSumpk.setOrdWs("001");
		tranSumpk.setRtStrId("50051");
		tranSumpk.setTrnSeq(1);
		
		tranSum.setId(tranSumpk);
		tranSums.add(tranSum);
		
		transaction.setOrdTranLineItems(lineItems);
		transaction.setOrdTranSum(tranSum);
		
		
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(transaction);
		em.getTransaction().commit();
		em.getTransaction().begin();
		
		em.remove(transaction);
	}

	@SuppressWarnings("unchecked")
	public static void process(OrderTranHeader transaction) {
		PromotionsService promotionsTesting = new PromotionsService();
		try {
			/*
			 * Store initial line item sequence number and quantity to
			 * re-arrange after split and grouping
			 */
			List<OrderTranLineItem> orderTranLineItems = transaction.getOrdTranLineItems();
			Map<String, BigDecimal> seqItmQty = new HashMap<String, BigDecimal>();
			Map<String, String> seqItmId = new HashMap<String, String>();
			for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
				seqItmId.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", orderTranLineItem.getItemId());
				seqItmQty.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", orderTranLineItem.getLineQnt());
			}

			// clear existing discount rules
			promotionsTesting.clearAppliedDiscountRules(orderTranLineItems, transaction);

			EntityManager entityManager = promotionsTesting.getEntityManager();

			Query advPrcngRules = entityManager.createQuery(
					"SELECT risplDkPrdvnRuleElg from RisplDkPrdvnRuleElg risplDkPrdvnRuleElg where risplDkPrdvnRuleElg.itmId in :idval and risplDkPrdvnRuleElg.tsRuDrvnEf <= :effctDate and risplDkPrdvnRuleElg.tsRuDrvnEp >= :endDate and risplDkPrdvnRuleElg.risplDkPrdvnRule.id.idStrRt in :stores",
					RisplDkPrdvnRuleElg.class);

			Query itmClassModQuery = entityManager.createQuery(
					"SELECT risplDkItmClssMod from RisplDkItmClssMod risplDkItmClssMod where risplDkItmClssMod.itmId in :idval",
					RisplDkItmClssMod.class);

			List<String> stores = new ArrayList<String>();
			stores.add("10151");

			List<String> vals = new ArrayList<String>();
			for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {

				if (!vals.contains(orderTranLineItem.getItemId())) {
					vals.add(orderTranLineItem.getItemId());
				}
			}

			itmClassModQuery.setParameter("idval", vals);
			List<RisplDkItmClssMod> risplDkItmClssMods = itmClassModQuery.getResultList();

			for (RisplDkItmClssMod risplDkItmClssMod : risplDkItmClssMods) {
				if (!vals.contains(risplDkItmClssMod.getClssFctnId())) {
					vals.add(risplDkItmClssMod.getClssFctnId());
				}
			}

			itmClassModQuery = entityManager.createQuery(
					"SELECT risplDkItmClssMod from RisplDkItmClssMod risplDkItmClssMod where risplDkItmClssMod.clssFctnId in :idval",
					RisplDkItmClssMod.class);
			itmClassModQuery.setParameter("idval", vals);
			risplDkItmClssMods = itmClassModQuery.getResultList();
			Map<String, Set<String>> classModItms = new HashMap<String, Set<String>>();

			for (RisplDkItmClssMod risplDkItmClssMod : risplDkItmClssMods) {
				if (!classModItms.containsKey(risplDkItmClssMod.getClssFctnId())) {
					Set<String> items = new HashSet<String>();
					items.add(risplDkItmClssMod.getItmId());
					classModItms.put(risplDkItmClssMod.getClssFctnId(), items);
				} else {
					Set<String> items = classModItms.get(risplDkItmClssMod.getClssFctnId());
					items.add(risplDkItmClssMod.getItmId());
					classModItms.put(risplDkItmClssMod.getClssFctnId(), items);
				}
			}

			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			advPrcngRules.setParameter("idval", vals);
			advPrcngRules.setParameter("effctDate", timeStamp);
			advPrcngRules.setParameter("endDate", timeStamp);
			advPrcngRules.setParameter("stores", stores);

			List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs = advPrcngRules.getResultList();

			// Splitting Line Items
			List<OrderTranLineItem> splttdOrdLineItms = promotionsTesting.splitTranLineItems(orderTranLineItems);

			/*
			 * calculate item classification totals and store in map key = item
			 * classification code value = item classification totals
			 */
			promotionsTesting.itemClassificationTotals = promotionsTesting.calculateItemClassTotals(transaction,
					classModItms, risplDkPrdvnRuleElgs);

			List<RisplDkPrdvnRule> validDiscountRules = promotionsTesting.getValidEligibleRules(risplDkPrdvnRuleElgs,
					orderTranLineItems, classModItms, splttdOrdLineItms, transaction);

			// Get Store wide promotions
			vals.clear();
			vals.add("*");
			timeStamp = new Timestamp(System.currentTimeMillis());
			advPrcngRules.setParameter("idval", vals);
			advPrcngRules.setParameter("effctDate", timeStamp);
			advPrcngRules.setParameter("endDate", timeStamp);
			advPrcngRules.setParameter("stores", stores);
			risplDkPrdvnRuleElgs = advPrcngRules.getResultList();
			List<RisplDkPrdvnRule> validStoreWideDiscountRules = promotionsTesting.getValidEligibleRules(
					risplDkPrdvnRuleElgs, orderTranLineItems, classModItms, splttdOrdLineItms, transaction);

			// If same source and targets available in multiple rule filter the
			// best one among them
			promotionsTesting.swapBestDiscountRuleOutOfMultiple(validDiscountRules, splttdOrdLineItms, classModItms,
					transaction);

			// Swap best valid store wide discount rules
			promotionsTesting.swapBestDiscountRuleOutOfMultiple(validStoreWideDiscountRules, splttdOrdLineItms,
					classModItms, transaction);

			// Swap store wide and other discount rules
			if (validStoreWideDiscountRules.size() > 0) {
				promotionsTesting.swapStoreWideDiscountRule(validStoreWideDiscountRules, validDiscountRules,
						classModItms, splttdOrdLineItms, transaction);
			}

			/*
			 * Check and remove discount rules with customer segment specific
			 */
			promotionsTesting.filterCustSegmntRules(transaction, validDiscountRules);

			// Iterate over all rules and apply
			// List<RisplDkPrdvnRule> currDisRules =
			// transaction.getAllCurrentDiscountRules();
			for (RisplDkPrdvnRule currDisRule : validDiscountRules) {

				// if rule = Buy$NofXforZ%off
				if (currDisRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_$_NOF_XFOR_Z_OFF)
						|| currDisRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_$_NOF_XFOR_Z_$_OFF)) {
					boolean isPercentOff = false;
					if (currDisRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_$_NOF_XFOR_Z_OFF)) {
						isPercentOff = true;
					}
					promotionsTesting.buyDollarNofXforZPrcntoff(currDisRule, splttdOrdLineItms, transaction,
							isPercentOff, classModItms);
				} else {
					promotionsTesting.getRuleDiscountAmount(currDisRule, splttdOrdLineItms, true, classModItms,
							transaction);
				}

				System.out.println(splttdOrdLineItms.size());
			}

			List<OrderTranLineItem> groupedLineItems = promotionsTesting.groupLineItems(splttdOrdLineItms);

			// re-assign line sequence numbers
			List<OrderTranLineItem> reAssignedLines = promotionsTesting
					.reAssignLineSequenceNumbers(groupedLineItems, seqItmId, seqItmQty);
			transaction.setOrdTranLineItems(reAssignedLines);
			System.out.println(
					" ************************** Returned Lines ********************" + reAssignedLines.size());

			if (entityManager != null) {
				// entityManager.close();
			}
		} catch (Exception e) {
			promotionsTesting.LOGGER.error(e.getMessage());
		}
	}

	/*
	 * Method to re-assign item line sequence numbers
	 */
	public List<OrderTranLineItem> reAssignLineSequenceNumbers(List<OrderTranLineItem> grpdOrdLineItms,
			Map<String, String> seqItmId, Map<String, BigDecimal> seqItmQty) throws Exception{
		LOGGER.info("Executing reAssignLineSequenceNumbers Logic");
		List<OrderTranLineItem> reAssignedLines = new ArrayList<OrderTranLineItem>();
		int maxIndxVal = grpdOrdLineItms.size();
		int newSeq = 1;
		boolean itemsAvlbl = true;
		int indx = 1;
		while (itemsAvlbl) {
			String itemId = seqItmId.get(indx + "");
			if(itemId!=null){
			BigDecimal lineQty = seqItmQty.get(indx + "");
			BigDecimal tranLineQty = BigDecimal.ZERO;
			for (OrderTranLineItem grpdOrdLineItm : grpdOrdLineItms) {

				if (grpdOrdLineItm.getItemId().equalsIgnoreCase(itemId)) {
					OrderTranLineItemPK orderTranLineItemPk = new OrderTranLineItemPK();
					orderTranLineItemPk.setDcDyOrd(grpdOrdLineItm.getId().getDcDyOrd());
					orderTranLineItemPk.setOrdLnItmSeq(newSeq);
					orderTranLineItemPk.setOrdWs(grpdOrdLineItm.getId().getOrdWs());
					orderTranLineItemPk.setRtStrId(grpdOrdLineItm.getId().getRtStrId());
					orderTranLineItemPk.setTrnSeq(grpdOrdLineItm.getId().getTrnSeq());

					grpdOrdLineItm.setId(orderTranLineItemPk);

					reAssignedLines.add(grpdOrdLineItm);
					tranLineQty = tranLineQty.add(grpdOrdLineItm.getLineQnt());
					newSeq++;
				}

				if (tranLineQty != null && lineQty != null && tranLineQty.compareTo(lineQty) != -1) {
					break;
				}
			}

			grpdOrdLineItms.removeAll(reAssignedLines);
			
		}
			if (grpdOrdLineItms.size() == 0) {
				itemsAvlbl = false;
			}
			indx++;
	}
		LOGGER.info("Completed reAssignLineSequenceNumbers Logic");
		return reAssignedLines;
	}

	/*
	 * Method to compare store wide discount rule discount amount and other
	 * discount rules discount amount and set the best one
	 */
	public void swapStoreWideDiscountRule(List<RisplDkPrdvnRule> validStoreWideDiscountRules,
			List<RisplDkPrdvnRule> validDiscountRules, Map<String, Set<String>> classModItms,
			List<OrderTranLineItem> splttdOrdLineItms, OrderTranHeader transaction) {
		LOGGER.info("Executing swapStoreWideDiscountRule Logic");
		BigDecimal storeWideDisAmt = getTotalDiscountAmount(validStoreWideDiscountRules, classModItms,
				splttdOrdLineItms, transaction);
		BigDecimal otherRulesDisAmt = getTotalDiscountAmount(validDiscountRules, classModItms, splttdOrdLineItms,
				transaction);

		if (storeWideDisAmt.compareTo(otherRulesDisAmt) == 1) {
			validDiscountRules.clear();
			validDiscountRules.addAll(validStoreWideDiscountRules);
		}
		LOGGER.info("Completed swapStoreWideDiscountRule Logic");
	}

	/*
	 * Method to get total discount amount of valid rules
	 */
	public BigDecimal getTotalDiscountAmount(List<RisplDkPrdvnRule> discountRules,
			Map<String, Set<String>> classModItms, List<OrderTranLineItem> splttdOrdLineItms,
			OrderTranHeader transaction) {
		BigDecimal totDisAmt = BigDecimal.ZERO;
		for (RisplDkPrdvnRule discountRule : discountRules) {
			totDisAmt = totDisAmt
					.add(getRuleDiscountAmount(discountRule, splttdOrdLineItms, false, classModItms, transaction));
		}
		return totDisAmt;
	}

	/*
	 * Method to get valid and eligible discount rules
	 */
	public List<RisplDkPrdvnRule> getValidEligibleRules(List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs,
			List<OrderTranLineItem> orderTranLineItems, Map<String, Set<String>> classModItms,
			List<OrderTranLineItem> splttdOrdLineItms, OrderTranHeader transaction) throws Exception {
		LOGGER.info("Executing getValidEligibleRules Logic");
		LOGGER.info("Checking Transaction Items to validate with Discount Rules Source and Targets");
		List<RisplDkPrdvnRule> validDiscountRules = new ArrayList<RisplDkPrdvnRule>();

		List<RisplDkPrdvnRulePK> validDiscountRulespk = new ArrayList<RisplDkPrdvnRulePK>();
		for (RisplDkPrdvnRuleElg risplDkPrdvnRuleElg : risplDkPrdvnRuleElgs) {

			boolean storeWideProm = false;
			if (risplDkPrdvnRuleElg.getItmId().equals(DKartConstantsIfc.STAR)) {
				storeWideProm = true;
			}

			RisplDkPrdvnRule risplDkPrdvnRule = risplDkPrdvnRuleElg.getRisplDkPrdvnRule();

			boolean validRule = false;
			if (storeWideProm) {
				if (transaction.getOrdTranSum().getDkartNetTot()
						.compareTo(risplDkPrdvnRuleElg.getMoTh()) != -1) {
					validRule = true;
				}
			} else {
				validRule = isSourceTargetEligible(risplDkPrdvnRule, orderTranLineItems, classModItms,
						splttdOrdLineItms);
			}
			if (validRule && !validDiscountRulespk.contains(risplDkPrdvnRule.getId())) {
				validDiscountRules.add(risplDkPrdvnRule);
				validDiscountRulespk.add(risplDkPrdvnRule.getId());
			}

		}
		LOGGER.info("Completed getValidEligibleRules Logic");
		return validDiscountRules;

	}

	/*
	 * Method to clear applied discount rules
	 */
	public void clearAppliedDiscountRules(List<OrderTranLineItem> orderTranLineItems, OrderTranHeader transaction)
			throws Exception {
		LOGGER.info("Executing clearAppliedDiscountRules Logic");
		BigDecimal removedDiscountAmount = BigDecimal.ZERO;
		for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
			if (orderTranLineItem.getOrdTranDscItms() != null) {
				List<OrderTranDiscountItem> disItems = orderTranLineItem.getOrdTranDscItms();
				List<OrderTranDiscountItem> removeRules = new ArrayList<OrderTranDiscountItem>();
				for (OrderTranDiscountItem disItem : disItems) {
					if (disItem.getTyDsc() != null
							&& disItem.getTyDsc().compareTo(DKartConstantsIfc.DIS_PROM_AUTO) == 0 && disItem.getDiscReasonCode()==null) {
						removeRules.add(disItem);

						// Added discount amount to extended line item price
						removedDiscountAmount = removedDiscountAmount.add(disItem.getDscAmt());
						orderTranLineItem.setExtnDscLnItm(orderTranLineItem.getExtnDscLnItm().add(disItem.getDscAmt()));

					}
				}
				disItems.removeAll(removeRules);
			}
		}

		BigDecimal netTot = transaction.getOrdTranSum().getDkartNetTot();
		netTot = netTot.add(removedDiscountAmount);
		transaction.getOrdTranSum().setDkartNetTot(netTot);
		LOGGER.info("Removed all applied Discount Rules on Line Items");
		LOGGER.info("Completed clearAppliedDiscountRules Logic");
	}
	
	//method to group items that don't have manual and promotion discounts
	
	public List<OrderTranLineItem> groupNDLineItems(List<OrderTranLineItem> splttdOrdLineItms) throws Exception {
		LOGGER.info("Executing groupLineItems Logic");
		List<OrderTranLineItem> groupedLines = new ArrayList<OrderTranLineItem>();

		for (OrderTranLineItem splttdLine : splttdOrdLineItms) {
			boolean found = false;
			String spltdItmId = splttdLine.getItemId();
			boolean spltdMD = isDiscountApplied(splttdLine, false);
			boolean spltdAD = isDiscountApplied(splttdLine, true);

			for (OrderTranLineItem grpdLine : groupedLines) {

				String grpdItmId = grpdLine.getItemId();
				boolean grpdMD = isDiscountApplied(grpdLine, false);
				boolean grpdAD = isDiscountApplied(grpdLine, true);

				// if same item id, manual and discount rule are not applied
				if (grpdItmId.equalsIgnoreCase(spltdItmId) && (!grpdMD && !spltdMD) && (!grpdAD && !spltdAD)) {
					grpdLine.setLineQnt(grpdLine.getLineQnt().add(splttdLine.getLineQnt()));
					
					if(grpdLine.getPriceOverRideFlag()!=null && grpdLine.getPriceOverRideFlag().equals("1"))
					{
						grpdLine.setExtnLnItmRtn(grpdLine.getOverRidePrice().multiply(grpdLine.getLineQnt()));
						grpdLine.setExtnDscLnItm(grpdLine.getOverRidePrice().multiply(grpdLine.getLineQnt()));
					}
					else
					{
						grpdLine.setExtnLnItmRtn(grpdLine.getItmPrnPrc().multiply(grpdLine.getLineQnt()));
						grpdLine.setExtnDscLnItm(grpdLine.getItmPrnPrc().multiply(grpdLine.getLineQnt()));
					}
					
					//grpdLine.setExtnLnItmRtn(grpdLine.getLineQnt().multiply(grpdLine.getItmPrnPrc()));

					found = true;
					break;
				}
			}

			if (!found) {
				groupedLines.add(splttdLine);
			}
		}

		// call method to calculate discounted line item price
		LOGGER.info("Calling calculateLineItemTotals Logic");
		calculateLineItemTotals(groupedLines);
		LOGGER.info("calculateLineItemTotals Returned");
		LOGGER.info("Completed groupLineItems Logic");
		return groupedLines;
	}

	/*
	 * Method to group all splitted items based on Item number, promotion
	 * details
	 */
	public List<OrderTranLineItem> groupLineItems(List<OrderTranLineItem> splttdOrdLineItms) throws Exception {
		LOGGER.info("Executing groupLineItems Logic");
		List<OrderTranLineItem> groupedLines = new ArrayList<OrderTranLineItem>();

		for (OrderTranLineItem splttdLine : splttdOrdLineItms) {
			boolean found = false;
			String spltdItmId = splttdLine.getItemId();
			Long splitOrdLnItmSeq = splttdLine.getId().getOrdLnItmSeq();
			boolean spltdMD = isDiscountApplied(splttdLine, false);
			boolean spltdAD = isDiscountApplied(splttdLine, true);

			for (OrderTranLineItem grpdLine : groupedLines) {

				String grpdItmId = grpdLine.getItemId();
				Long grpdOrdLnItmSeq = grpdLine.getId().getOrdLnItmSeq();
				boolean grpdMD = isDiscountApplied(grpdLine, false);
				boolean grpdAD = isDiscountApplied(grpdLine, true);

				// if same item id, manual and discount rule are not applied
				if (grpdItmId.equalsIgnoreCase(spltdItmId) && grpdOrdLnItmSeq.equals(splitOrdLnItmSeq) && (!grpdMD && !spltdMD) && (!grpdAD && !spltdAD)) {
					grpdLine.setLineQnt(grpdLine.getLineQnt().add(splttdLine.getLineQnt()));
					
					if(grpdLine.getPriceOverRideFlag()!=null && grpdLine.getPriceOverRideFlag().equals("1"))
					{
						grpdLine.setExtnLnItmRtn(grpdLine.getOverRidePrice().multiply(grpdLine.getLineQnt()));
						grpdLine.setExtnDscLnItm(grpdLine.getOverRidePrice().multiply(grpdLine.getLineQnt()));
					}
					else
					{
						grpdLine.setExtnLnItmRtn(grpdLine.getItmPrnPrc().multiply(grpdLine.getLineQnt()));
						grpdLine.setExtnDscLnItm(grpdLine.getItmPrnPrc().multiply(grpdLine.getLineQnt()));
					}
					
					//grpdLine.setExtnLnItmRtn(grpdLine.getLineQnt().multiply(grpdLine.getItmPrnPrc()));

					found = true;
					break;
				}
				// if same item id, no manual discount applied and discount rule
				// applied
				else if (grpdItmId.equalsIgnoreCase(spltdItmId) && grpdOrdLnItmSeq.equals(splitOrdLnItmSeq) &&  (!grpdMD && !spltdMD) && (grpdAD && spltdAD)) {
					List<OrderTranDiscountItem> spltdLineDiscs = getDiscountLines(splttdLine, false);
					List<OrderTranDiscountItem> grpdLineDiscs = getDiscountLines(grpdLine, false);

					if (spltdLineDiscs.size() == grpdLineDiscs.size()) {

						for (OrderTranDiscountItem spltdLineDisc : spltdLineDiscs) {

							for (OrderTranDiscountItem grpdLineDisc : grpdLineDiscs) {

								if (spltdLineDisc.getPrmCmpDtlid().compareTo(grpdLineDisc.getPrmCmpDtlid()) == 0) {
									grpdLineDisc.setDscAmt(grpdLineDisc.getDscAmt().add(spltdLineDisc.getDscAmt()));
									grpdLine.setLineQnt(grpdLine.getLineQnt().add(splttdLine.getLineQnt()));
									
									if(grpdLine.getPriceOverRideFlag()!=null && grpdLine.getPriceOverRideFlag().equals("1"))
									{
										grpdLine.setExtnLnItmRtn(grpdLine.getOverRidePrice().multiply(grpdLine.getLineQnt()));
										grpdLine.setExtnDscLnItm(grpdLine.getOverRidePrice().multiply(grpdLine.getLineQnt()));
									}
									else
									{
										grpdLine.setExtnLnItmRtn(grpdLine.getItmPrnPrc().multiply(grpdLine.getLineQnt()));
										grpdLine.setExtnDscLnItm(grpdLine.getItmPrnPrc().multiply(grpdLine.getLineQnt()));
									}
									//grpdLine.setExtnLnItmRtn(grpdLine.getLineQnt().multiply(grpdLine.getItmPrnPrc()));

									found = true;
									break;
								}
							}
						}
					}
				}
				// if same item id, manual discount and discount rule applied
				else if (grpdItmId.equalsIgnoreCase(spltdItmId) && grpdOrdLnItmSeq.equals(splitOrdLnItmSeq) && (grpdMD && spltdMD) && (grpdAD && spltdAD)) {
					List<OrderTranDiscountItem> spltdLineDiscsMD = getDiscountLines(splttdLine, true);
					List<OrderTranDiscountItem> grpdLineDiscsMD = getDiscountLines(grpdLine, true);

					List<OrderTranDiscountItem> spltdLineDiscsAD = getDiscountLines(splttdLine, false);
					List<OrderTranDiscountItem> grpdLineDiscsAD = getDiscountLines(grpdLine, false);

					if (spltdLineDiscsMD.size() == grpdLineDiscsMD.size()
							&& spltdLineDiscsAD.size() == grpdLineDiscsAD.size()) {

						// grouping promotions
						for (OrderTranDiscountItem spltdLineDisc : spltdLineDiscsAD) {

							for (OrderTranDiscountItem grpdLineDisc : grpdLineDiscsAD) {

								if (spltdLineDisc.getPrmCmpDtlid().compareTo(grpdLineDisc.getPrmCmpDtlid()) == 0) {
									grpdLineDisc.setDscAmt(grpdLineDisc.getDscAmt().add(spltdLineDisc.getDscAmt()));
									grpdLine.setLineQnt(grpdLine.getLineQnt().add(splttdLine.getLineQnt()));
									
									//grpdLine.setExtnLnItmRtn(grpdLine.getLineQnt().multiply(grpdLine.getItmPrnPrc()));
									if(grpdLine.getPriceOverRideFlag()!=null && grpdLine.getPriceOverRideFlag().equals("1"))
									{
										grpdLine.setExtnLnItmRtn(grpdLine.getOverRidePrice().multiply(grpdLine.getLineQnt()));
										grpdLine.setExtnDscLnItm(grpdLine.getOverRidePrice().multiply(grpdLine.getLineQnt()));
									}
									else
									{
										grpdLine.setExtnLnItmRtn(grpdLine.getItmPrnPrc().multiply(grpdLine.getLineQnt()));
										grpdLine.setExtnDscLnItm(grpdLine.getItmPrnPrc().multiply(grpdLine.getLineQnt()));
									}

									found = true;
									break;
								}
							}
						}

						// grouping manual discounts
						for (OrderTranDiscountItem spltdLineDisc : spltdLineDiscsMD) {

							for (OrderTranDiscountItem grpdLineDisc : grpdLineDiscsMD) {

								if ((spltdLineDisc.getDiscReasonCode()!=null && grpdLineDisc.getDiscReasonCode()!=null && spltdLineDisc.getDiscReasonCode().equalsIgnoreCase(grpdLineDisc.getDiscReasonCode())) || hasSameRuleIds(spltdLineDisc, grpdLineDisc)) {
									grpdLineDisc.setDscAmt(grpdLineDisc.getDscAmt().add(spltdLineDisc.getDscAmt()));
									/*
									 * grpdLine.setLineQnt(grpdLine.getLineQnt()
									 * .add(splttdLine.getLineQnt()));
									 * grpdLine.setExtnLnItmRtn(grpdLine.
									 * getLineQnt().multiply(grpdLine.
									 * getItmPrnPrc()));
									 */

									found = true;
									break;
								}
							}
						}

					}
				}
				// if same item id, manual discount and discount rule applied
				else if (grpdItmId.equalsIgnoreCase(spltdItmId) && grpdOrdLnItmSeq.equals(splitOrdLnItmSeq) && (grpdMD && spltdMD) && (!grpdAD && !spltdAD)) {
					List<OrderTranDiscountItem> spltdLineDiscsMD = getDiscountLines(splttdLine, true);
					List<OrderTranDiscountItem> grpdLineDiscsMD = getDiscountLines(grpdLine, true);
					// grouping manual discounts
					for (OrderTranDiscountItem spltdLineDisc : spltdLineDiscsMD) {

						for (OrderTranDiscountItem grpdLineDisc : grpdLineDiscsMD) {

							if ((spltdLineDisc.getDiscReasonCode()!=null && grpdLineDisc.getDiscReasonCode()!=null && spltdLineDisc.getDiscReasonCode().equalsIgnoreCase(grpdLineDisc.getDiscReasonCode())) || hasSameRuleIds(spltdLineDisc,grpdLineDisc)) {
								grpdLineDisc.setDscAmt(grpdLineDisc.getDscAmt().add(spltdLineDisc.getDscAmt()));
								grpdLine.setLineQnt(grpdLine.getLineQnt().add(splttdLine.getLineQnt()));
								//grpdLine.setExtnLnItmRtn(grpdLine.getLineQnt().multiply(grpdLine.getItmPrnPrc()));
								if(grpdLine.getPriceOverRideFlag()!=null && grpdLine.getPriceOverRideFlag().equals("1"))
								{
									grpdLine.setExtnLnItmRtn(grpdLine.getOverRidePrice().multiply(grpdLine.getLineQnt()));
									grpdLine.setExtnDscLnItm(grpdLine.getOverRidePrice().multiply(grpdLine.getLineQnt()));
								}
								else
								{
									grpdLine.setExtnLnItmRtn(grpdLine.getItmPrnPrc().multiply(grpdLine.getLineQnt()));
									grpdLine.setExtnDscLnItm(grpdLine.getItmPrnPrc().multiply(grpdLine.getLineQnt()));
								}

								found = true;
								break;
							}
						}
					}
				}

			}

			if (!found) {
				groupedLines.add(splttdLine);
			}
		}

		// call method to calculate discounted line item price
		LOGGER.info("Calling calculateLineItemTotals Logic");
		calculateLineItemTotals(groupedLines);
		LOGGER.info("calculateLineItemTotals Returned");
		LOGGER.info("Completed groupLineItems Logic");
		return groupedLines;
	}

	/*
	 * Method to calculate item extended selling price
	 */
	public void calculateLineItemTotals(List<OrderTranLineItem> groupedLines) throws Exception {
		LOGGER.info("Executing calculateLineItemTotals Logic");
		LOGGER.info(
				"Re-Calculating Line Item Price by Subtracting Manual and Promotional Discount amount from Initial Price");
		for (OrderTranLineItem groupedLine : groupedLines) {
			BigDecimal manualDis = getDiscountTotals(getDiscountLines(groupedLine, true));
			BigDecimal promoDis = getDiscountTotals(getDiscountLines(groupedLine, false));

			groupedLine.setExtnDscLnItm(groupedLine.getExtnLnItmRtn().subtract(manualDis.add(promoDis)));

		}
		LOGGER.info("Completed calculateLineItemTotals Logic");
	}

	/*
	 * Method to calculate Discount totals
	 */
	public BigDecimal getDiscountTotals(List<OrderTranDiscountItem> discountLines) throws Exception {

		BigDecimal discount = BigDecimal.ZERO;
		for (OrderTranDiscountItem discountLine : discountLines) {
			if (discountLine.getDscAmt() != null) {
				discount = discount.add(discountLine.getDscAmt());
			}
		}
		//discount = discount.setScale(currencyDecimal, RoundingMode.DOWN);
		discount = ConfigUtils.getInstance().createBigDecimal(discount, format);
		return discount;
	}

	/*
	 * Method to get discount lines
	 */
	public List<OrderTranDiscountItem> getDiscountLines(OrderTranLineItem orderTranLineItem, boolean isManual) {
		List<OrderTranDiscountItem> discountLines = new ArrayList<OrderTranDiscountItem>();
		List<OrderTranDiscountItem> orderTranDiscountItems = orderTranLineItem.getOrdTranDscItms();
		if (orderTranDiscountItems != null) {
			for (OrderTranDiscountItem orderTranDiscountItem : orderTranDiscountItems) {
				if (isManual && orderTranDiscountItem.getTyDsc() != null
						&& orderTranDiscountItem.getTyDsc().compareTo(DKartConstantsIfc.DIS_PROM_MNUL) == 0) {
					discountLines.add(orderTranDiscountItem);
				} else if (!isManual && orderTranDiscountItem.getTyDsc() != null
						&& orderTranDiscountItem.getTyDsc().compareTo(DKartConstantsIfc.DIS_PROM_AUTO) == 0) {
					discountLines.add(orderTranDiscountItem);
				}
			}
		}
		return discountLines;
	}

	/*
	 * Method to check if there are any promotions applied on item
	 */
	public boolean isDiscountApplied(OrderTranLineItem orderTranLineItem, boolean isAuto) throws Exception {
		boolean isApplied = false;
		List<OrderTranDiscountItem> tranDiscountItems = orderTranLineItem.getOrdTranDscItms();
		if (tranDiscountItems != null && tranDiscountItems.size() > 0) {

			for (OrderTranDiscountItem tranDiscountItem : tranDiscountItems) {
				if (tranDiscountItem.getTyDsc() != null
						&& tranDiscountItem.getTyDsc().compareTo(DKartConstantsIfc.DIS_PROM_AUTO) == 0 && isAuto) {
					isApplied = true;
					break;
				} else if (tranDiscountItem.getTyDsc() != null
						&& tranDiscountItem.getTyDsc().compareTo(DKartConstantsIfc.DIS_PROM_MNUL) == 0 && !isAuto) {
					isApplied = true;
					break;
				}
			}
		}
		return isApplied;
	}

	/*
	 * Method to Calculate Item Classification totals calculate item
	 * classification totals and store in map key = item classification code
	 * value = item classification totals
	 */
	public Map<String, BigDecimal> calculateItemClassTotals(OrderTranHeader transaction,
			Map<String, Set<String>> classModItms, List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs) {
		LOGGER.info("Executing calculateItemClassTotals Logic");
		Map<String, BigDecimal> itemClassTotals = new HashMap<String, BigDecimal>();

		// calculate item totals
		Map<String, BigDecimal> itemTotals = new HashMap<String, BigDecimal>();
		List<OrderTranLineItem> orderTranLineItems = transaction.getOrdTranLineItems();
		for (OrderTranLineItem mainLoop : orderTranLineItems) {
			if (!itemTotals.containsKey(mainLoop.getItemId())) {
				BigDecimal itemTotal = BigDecimal.ZERO;
				for (OrderTranLineItem innerLoop : orderTranLineItems) {
					if (innerLoop.getItemId().equalsIgnoreCase(mainLoop.getItemId())) {
						itemTotal = itemTotal.add(innerLoop.getExtnDscLnItm());
					}
				}
				//itemTotal = itemTotal.setScale(currencyDecimal, RoundingMode.DOWN);
				itemTotal = ConfigUtils.getInstance().createBigDecimal(itemTotal, format);
				itemTotals.put(mainLoop.getItemId(), itemTotal);
			}
		}

		// populate itemclassification totals to respective map
		Set<String> keys = classModItms.keySet();
		for (RisplDkPrdvnRuleElg risplDkPrdvnRuleElg : risplDkPrdvnRuleElgs) {
			Set<String> items = classModItms.get(risplDkPrdvnRuleElg.getItmId());
			BigDecimal classTotals = BigDecimal.ZERO;
			if (items != null) {
				for (String item : items) {
					if (itemTotals.containsKey(item)) {
						classTotals = classTotals.add(itemTotals.get(item));
					}
				}
				//classTotals = classTotals.setScale(currencyDecimal, RoundingMode.DOWN);
				classTotals = ConfigUtils.getInstance().createBigDecimal(classTotals, format);
				if (classTotals.compareTo(risplDkPrdvnRuleElg.getMoTh()) != -1) {
					itemClassTotals.put(risplDkPrdvnRuleElg.getItmId(), classTotals);
				}
			}

		}
		LOGGER.info("Completed calculateItemClassTotals Logic");
		return itemClassTotals;
	}

	/*
	 * Method to Split Line Items in the transaction
	 */
	public List<OrderTranLineItem> splitTranLineItems(List<OrderTranLineItem> ordLineItms) throws Exception {
		LOGGER.info("Executing splitTranLineItems Logic");
		LOGGER.info("Splitting Line Items based on Line Item Quantity");
		BigDecimal oneQty = new BigDecimal(1);
		ConfigUtils config = ConfigUtils.getInstance();
		
		List<OrderTranLineItem> splttdOrdLineItms = new ArrayList<OrderTranLineItem>();
		//int lineSeq = 1;
		for (OrderTranLineItem ordLineItm : ordLineItms) {
			if (ordLineItm.getLineQnt().compareTo(oneQty) == 0) {
				OrderTranLineItemPK pk = new OrderTranLineItemPK();
				pk.setDcDyOrd(ordLineItm.getId().getDcDyOrd());
				//pk.setOrdLnItmSeq(lineSeq);
				pk.setOrdLnItmSeq(ordLineItm.getId().getOrdLnItmSeq());
				pk.setOrdWs(ordLineItm.getId().getOrdWs());
				pk.setRtStrId(ordLineItm.getId().getRtStrId());
				pk.setTrnSeq(ordLineItm.getId().getTrnSeq());
				ordLineItm.setId(pk);
				//lineSeq++;
				/*if(ordLineItm.getPriceOverRideFlag()!=null && ordLineItm.getPriceOverRideFlag().equals("1"))
				{
					ordLineItm.setExtnLnItmRtn(config.createBigDecimal(ordLineItm.getOverRidePrice().multiply(ordLineItm.getLineQnt()), format));
					ordLineItm.setExtnDscLnItm(config.createBigDecimal(ordLineItm.getOverRidePrice().multiply(ordLineItm.getLineQnt()), format));
				}
				else
				{
				ordLineItm.setExtnLnItmRtn(config.createBigDecimal(ordLineItm.getItmPrnPrc().multiply(ordLineItm.getLineQnt()), format));
				ordLineItm.setExtnDscLnItm(config.createBigDecimal(ordLineItm.getItmPrnPrc().multiply(ordLineItm.getLineQnt()), format));
				}*/
				splttdOrdLineItms.add(ordLineItm);
			} else {
				// split line items based on item qty
				List<OrderTranDiscountItem> lineDiscounts = ordLineItm.getOrdTranDscItms();
				List<OrderTranLineItem> localSplittedLines = new ArrayList<OrderTranLineItem>();
				BigDecimal spltdQty = BigDecimal.ZERO;
				BigDecimal lineQuantity = BigDecimal.ZERO;
				if(ordLineItm.getReturnQtyFlag() != null){
					lineQuantity = ordLineItm.getLineQntRtn();
				}else{
					lineQuantity = ordLineItm.getLineQnt();
				}
				for (int qty = 1; qty <= lineQuantity.intValue(); qty++) {
					// manual cloning.... need to have a clone method
					OrderTranLineItem temp = ordLineItm.clone();//new OrderTranLineItem();
					temp.setOrdTranDscItms(null);
					temp.setItmTy(ordLineItm.getItmTy());
					temp.setDeItmShrtRcpt(ordLineItm.getDeItmShrtRcpt());
					temp.setUomSls(ordLineItm.getUomSls());
					temp.setId(ordLineItm.getId());
					temp.setEmpId(ordLineItm.getEmpId()); // added by lucky 
					temp.setPluItem(ordLineItm.getPluItem());
					temp.setItemId(ordLineItm.getItemId());
					temp.setIdStrRtOrg(ordLineItm.getIdStrRtOrg());
					temp.setItmPrnPrc(config.createBigDecimal(ordLineItm.getItmPrnPrc(), format));
					temp.setLineQnt(oneQty);
					temp.setPriceOverRideFlag(ordLineItm.getPriceOverRideFlag());
					temp.setOverRidePrice(config.createBigDecimal(ordLineItm.getOverRidePrice(), format));
					temp.setPriceOvrrRsnCode(ordLineItm.getPriceOvrrRsnCode());
					temp.setRegistryId(ordLineItm.getRegistryId());//Saideep : set the same vpn
					if(temp.getPriceOverRideFlag()!=null && temp.getPriceOverRideFlag().equals("1"))
					{
						temp.setExtnLnItmRtn(config.createBigDecimal(ordLineItm.getOverRidePrice().multiply(temp.getLineQnt()), format));
						temp.setExtnDscLnItm(config.createBigDecimal(ordLineItm.getOverRidePrice().multiply(temp.getLineQnt()), format));
					}
					else
					{
						temp.setExtnLnItmRtn(config.createBigDecimal(ordLineItm.getItmPrnPrc().multiply(temp.getLineQnt()), format));
						temp.setExtnDscLnItm(config.createBigDecimal(ordLineItm.getItmPrnPrc().multiply(temp.getLineQnt()), format));
					}
					OrderTranLineItemPK pk = new OrderTranLineItemPK();
					pk.setDcDyOrd(temp.getId().getDcDyOrd());
					pk.setOrdLnItmSeq(temp.getId().getOrdLnItmSeq());
					pk.setOrdWs(temp.getId().getOrdWs());
					pk.setRtStrId(temp.getId().getRtStrId());
					pk.setTrnSeq(temp.getId().getTrnSeq());
					temp.setId(pk);
					
					//lineSeq++;
					localSplittedLines.add(temp);
					spltdQty = spltdQty.add(oneQty);
				}

				if (lineQuantity.compareTo(spltdQty) == 1) {// Handling
																		// Decimal
																		// Qty
																		// Lines
					OrderTranLineItem temp = new OrderTranLineItem();
					temp.setItmTy(ordLineItm.getItmTy()); //Saideep : set the item type
					temp.setDeItmShrtRcpt(ordLineItm.getDeItmShrtRcpt());
					temp.setUomSls(ordLineItm.getUomSls());
					temp.setId(ordLineItm.getId());
					temp.setEmpId(ordLineItm.getEmpId()); // added by lucky
					temp.setPluItem(ordLineItm.getPluItem());
					temp.setItemId(ordLineItm.getItemId());
					temp.setIdStrRtOrg(ordLineItm.getIdStrRtOrg());
					temp.setItmPrnPrc(config.createBigDecimal(ordLineItm.getItmPrnPrc().multiply(temp.getLineQnt()), format));
					temp.setLineQnt(lineQuantity.subtract(spltdQty));
					temp.setPriceOverRideFlag(ordLineItm.getPriceOverRideFlag());
					temp.setOverRidePrice(config.createBigDecimal(ordLineItm.getOverRidePrice(), format));
					temp.setPriceOvrrRsnCode(ordLineItm.getPriceOvrrRsnCode());
					temp.setRegistryId(ordLineItm.getRegistryId());//Saideep : set the same vpn
					if(temp.getPriceOverRideFlag()!=null && temp.getPriceOverRideFlag().equals("1"))
					{
						temp.setExtnLnItmRtn(config.createBigDecimal(ordLineItm.getOverRidePrice().multiply(temp.getLineQnt()), format));
						temp.setExtnDscLnItm(config.createBigDecimal(ordLineItm.getOverRidePrice().multiply(temp.getLineQnt()), format));
					}
					else
					{
						temp.setExtnLnItmRtn(config.createBigDecimal(ordLineItm.getItmPrnPrc().multiply(temp.getLineQnt()), format));
						temp.setExtnDscLnItm(config.createBigDecimal(ordLineItm.getItmPrnPrc().multiply(temp.getLineQnt()), format));
					}					
					temp.getId().setOrdLnItmSeq(ordLineItm.getId().getOrdLnItmSeq());
					//lineSeq++;
					localSplittedLines.add(temp);
				}

				// split discount line items on splittedLines
				if (lineDiscounts != null) {
					for (OrderTranDiscountItem discountLine : lineDiscounts) {
						prorateManualDiscounts(discountLine, localSplittedLines);
					}
				}

				// call method to calculate line item discounted price
				calculateLineItemTotals(localSplittedLines);

				splttdOrdLineItms.addAll(localSplittedLines);
			}
		}
		LOGGER.info("Completed splitTranLineItems Logic");
		return splttdOrdLineItms;

	}

	/*
	 * Method to prorate manual discounts amount on splitted line items
	 */
	public void prorateManualDiscounts(OrderTranDiscountItem discountLine, List<OrderTranLineItem> localSplittedLines) {
		LOGGER.info("Executing prorateManualDiscounts Logic");
		BigDecimal discountAmount = ConfigUtils.getInstance().createBigDecimal(discountLine.getDscAmt(), format);
		BigDecimal lineItmsTtl = BigDecimal.ZERO;
		for (OrderTranLineItem lineItm : localSplittedLines) {
			if (lineItm.getExtnDscLnItm() != null) {
				lineItmsTtl = lineItmsTtl.add(lineItm.getExtnDscLnItm());
			} else {
				lineItmsTtl = lineItmsTtl.add(lineItm.getExtnLnItmRtn());
			}
		}
		BigDecimal zero = ConfigUtils.getInstance().createBigDecimal(0, format); // GET FORMATTED VALUE
		BigDecimal appliedDiscount = BigDecimal.ZERO;
		for (OrderTranLineItem tempItemLine : localSplittedLines) {
			BigDecimal lineTot = BigDecimal.ZERO;
			if (tempItemLine.getExtnDscLnItm() != null) {
				lineTot = tempItemLine.getExtnDscLnItm();
			} else {
				lineTot = tempItemLine.getExtnLnItmRtn();
			}
			
			BigDecimal lineDisAmt = lineTot.divide(lineItmsTtl, 4, RoundingMode.DOWN);
			lineDisAmt = lineDisAmt.multiply(discountAmount);
			lineDisAmt = lineDisAmt.setScale(zero.scale(), RoundingMode.DOWN);
			
			OrderTranDiscountItem newDis = new OrderTranDiscountItem();
			newDis.setDscAmt(lineDisAmt);
			newDis.setDscPer(ConfigUtils.getInstance().createBigDecimal(discountLine.getDscPer(), format));
			newDis.setId(discountLine.getId());
			newDis.setPrmDesc(discountLine.getPrmDesc());
			newDis.setPrmCmpDtlid(discountLine.getPrmCmpDtlid());
			newDis.setPrmType(discountLine.getPrmType());
			newDis.setSrcTrgList(discountLine.getSrcTrgList());
			newDis.setTyDsc(discountLine.getTyDsc());
			newDis.setOrdTranLineItem(discountLine.getOrdTranLineItem());
			newDis.setPrmCmpId(discountLine.getPrmCmpId());
			newDis.setPrmId(discountLine.getPrmId());
			newDis.setDiscReasonCode(discountLine.getDiscReasonCode());
			
			appliedDiscount = appliedDiscount.add(lineDisAmt);
			List<OrderTranDiscountItem> discountItems = tempItemLine.getOrdTranDscItms();
			if (discountItems == null) {
				discountItems = new ArrayList<OrderTranDiscountItem>();
			} else {
				discountItems.clear();
			}
			
			if(appliedDiscount.compareTo(discountAmount)==1){
				BigDecimal diff = appliedDiscount.subtract(discountAmount);
				appliedDiscount = appliedDiscount.subtract(lineDisAmt);
				lineDisAmt = lineDisAmt.subtract(diff);
				appliedDiscount = appliedDiscount.add(lineDisAmt);
				newDis.setDscAmt(lineDisAmt);
				discountItems.add(newDis);
				tempItemLine.setOrdTranDscItms(discountItems);
				return;
			}else{
				discountItems.add(newDis);
				tempItemLine.setOrdTranDscItms(discountItems);
			}
		}
		
		if(appliedDiscount.compareTo(discountAmount)==-1){
			BigDecimal balAmt = discountAmount.subtract(appliedDiscount);
			BigDecimal addtnlDiscEachLine = balAmt.divide(new BigDecimal(localSplittedLines.size()),zero.scale(),RoundingMode.UP);
			for (OrderTranLineItem tempItemLine : localSplittedLines) {
				
				List<OrderTranDiscountItem> discountItems = tempItemLine.getOrdTranDscItms();
				if (discountItems == null) {
					discountItems = new ArrayList<OrderTranDiscountItem>();
				} 
				
				appliedDiscount = appliedDiscount.add(addtnlDiscEachLine);
				boolean returnVal = false;
				if(appliedDiscount.compareTo(discountAmount)==1){
					BigDecimal diff = appliedDiscount.subtract(discountAmount);
					appliedDiscount = appliedDiscount.subtract(addtnlDiscEachLine);
					addtnlDiscEachLine = addtnlDiscEachLine.subtract(diff);
					appliedDiscount = appliedDiscount.add(addtnlDiscEachLine);
					returnVal = true;
				}
				
				for(OrderTranDiscountItem discLine : discountItems){
					if(discLine.getDiscReasonCode().equalsIgnoreCase(discountLine.getDiscReasonCode())){
						discLine.setDscAmt(discLine.getDscAmt().add(addtnlDiscEachLine));
						break;
					}
				}
				
				if(returnVal || appliedDiscount.compareTo(discountAmount)==0){
					return;
				}
				
			}
		}
	}
	public void prorateManualDiscounts_BackUP(OrderTranDiscountItem discountLine, List<OrderTranLineItem> localSplittedLines) {
		LOGGER.info("Executing prorateManualDiscounts Logic");
		BigDecimal discountAmount = ConfigUtils.getInstance().createBigDecimal(discountLine.getDscAmt(), format);
		BigDecimal lineItmsTtl = BigDecimal.ZERO;
		for (OrderTranLineItem lineItm : localSplittedLines) {
			if (lineItm.getExtnDscLnItm() != null) {
				lineItmsTtl = lineItmsTtl.add(lineItm.getExtnDscLnItm());
			} else {
				lineItmsTtl = lineItmsTtl.add(lineItm.getExtnLnItmRtn());
			}
		}

		BigDecimal appliedDiscount = BigDecimal.ZERO;
		boolean breakLoop = false;
		//int lineCount = 0;
		for (OrderTranLineItem tempItemLine : localSplittedLines) {
			BigDecimal lineTot = BigDecimal.ZERO;
			if (tempItemLine.getExtnDscLnItm() != null) {
				lineTot = tempItemLine.getExtnDscLnItm();
			} else {
				lineTot = tempItemLine.getExtnLnItmRtn();
			}
			BigDecimal lineDisAmt = lineTot.divide(lineItmsTtl, MathContext.DECIMAL64).multiply(discountAmount);
			BigDecimal zero = ConfigUtils.getInstance().createBigDecimal(0, format); // GET FORMATTED VALUE
			lineDisAmt = lineDisAmt.setScale(zero.scale(), RoundingMode.UP);
			//lineDisAmt = ConfigUtils.getInstance().createBigDecimal(lineDisAmt, format); // COMMENTED BY LAXMIKANTH, FOR PROPER RESULTS
			
			//Check additional Condition
			BigDecimal appldDisc = ConfigUtils.getInstance().createBigDecimal(appliedDiscount.toString(), format);
			appldDisc = appldDisc.add(lineDisAmt);
			if(appldDisc.compareTo(discountAmount)==1){
				BigDecimal extraAmt = appldDisc.subtract(discountAmount);
				lineDisAmt = lineDisAmt.subtract(extraAmt);
				breakLoop = true;
			}
			
			appliedDiscount = appliedDiscount.add(lineDisAmt);
			List<OrderTranDiscountItem> discountItems = tempItemLine.getOrdTranDscItms();
			if (discountItems == null) {
				discountItems = new ArrayList<OrderTranDiscountItem>();
			} else {
				discountItems.clear();
			}

			//lineCount++;
			/*if (lineCount == localSplittedLines.size() && appliedDiscount.compareTo(discountAmount) == -1) {
				lineDisAmt = lineDisAmt.add(discountAmount.subtract(appliedDiscount));
			} else if (lineCount == localSplittedLines.size() && appliedDiscount.compareTo(discountAmount) == 1) {
				lineDisAmt = lineDisAmt.subtract(appliedDiscount.subtract(discountAmount));
			}*/

			OrderTranDiscountItem newDis = new OrderTranDiscountItem();
			newDis.setDscAmt(lineDisAmt);
			newDis.setDscPer(ConfigUtils.getInstance().createBigDecimal(discountLine.getDscPer(), format));
			newDis.setId(discountLine.getId());
			newDis.setPrmDesc(discountLine.getPrmDesc());
			newDis.setPrmCmpDtlid(discountLine.getPrmCmpDtlid());
			newDis.setPrmType(discountLine.getPrmType());
			newDis.setSrcTrgList(discountLine.getSrcTrgList());
			newDis.setTyDsc(discountLine.getTyDsc());
			newDis.setOrdTranLineItem(discountLine.getOrdTranLineItem());
			newDis.setPrmCmpId(discountLine.getPrmCmpId());
			newDis.setPrmId(discountLine.getPrmId());
			newDis.setDiscReasonCode(discountLine.getDiscReasonCode());
			
			discountItems.add(newDis);
			tempItemLine.setOrdTranDscItms(discountItems);
			
			if(breakLoop){
				return;
			}
		}
		LOGGER.info("Completed prorateManualDiscounts Logic");
	}

	/*
	 * If same source and targets available in multiple rule filter the best one
	 * among them Method to swap the best rule if multiple rules has same source
	 * and targets with same discount type
	 */
	public void swapBestDiscountRuleOutOfMultiple(List<RisplDkPrdvnRule> validDiscountRules,
			List<OrderTranLineItem> splttdOrdLineItms, Map<String, Set<String>> classModItms,
			OrderTranHeader transaction) throws Exception {
		LOGGER.info("Executing swapBestDiscountRuleOutOfMultiple Logic");
		LOGGER.info("Swapping Discount Rules Based on the Best Deal Amount");
		List<RisplDkPrdvnRule> removeRules = new ArrayList<RisplDkPrdvnRule>();
		List<RisplDkPrdvnRule> fltrdMultiRules = new ArrayList<RisplDkPrdvnRule>();

		for (RisplDkPrdvnRule rule1 : validDiscountRules) {
			RisplDkPrdvnRule temp = rule1;
			boolean swapped = false;
			if (fltrdMultiRules.size() > 0) {
				for (RisplDkPrdvnRule rule2 : fltrdMultiRules) {
					if (!rule1.getId().equals(rule2.getId())) {
						List<OrderTranLineItem> rule1SrcTrgts = new ArrayList<OrderTranLineItem>();
						List<OrderTranLineItem> rule2SrcTrgts = new ArrayList<OrderTranLineItem>();

						boolean isSrcItmLvl = false;
						if (rule1.getCdBasCmpSrc().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_ITM)) {
							isSrcItmLvl = true;
						}
						//if (rule1.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_NOR_MORE_OF_XFOR_Z_OFF)) {
						if(!targetsRequired.get(rule1.getDeRuPrdv()) && rule1.getRisplDkPrdvnTrshldElgs()!=null && rule1.getRisplDkPrdvnTrshldElgs().size()>0){
							rule1SrcTrgts = getThrshldSourcesFromTran(rule1, splttdOrdLineItms, classModItms, false);
						} else {
							rule1SrcTrgts = getSourceTargetFromTran(rule1, splttdOrdLineItms, isSrcItmLvl, true,
									classModItms, false);
						}

						if (rule2.getCdBasCmpSrc().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_ITM)) {
							isSrcItmLvl = true;
						}
						//if (rule1.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_NOR_MORE_OF_XFOR_Z_OFF)) {
						if(!targetsRequired.get(rule2.getDeRuPrdv()) && rule2.getRisplDkPrdvnTrshldElgs()!=null && rule2.getRisplDkPrdvnTrshldElgs().size()>0){
							rule2SrcTrgts = getThrshldSourcesFromTran(rule2, rule1SrcTrgts, classModItms, false);
						} else {
							rule2SrcTrgts = getSourceTargetFromTran(rule2, rule1SrcTrgts, isSrcItmLvl, true,
									classModItms, false);
						}
						
						boolean hasConflict = false;
						if(rule2SrcTrgts==null || (rule2SrcTrgts!=null && rule2SrcTrgts.size()==0)){
							if(!targetsRequired.get(rule2.getDeRuPrdv()) && rule2.getRisplDkPrdvnTrshldElgs()!=null && rule2.getRisplDkPrdvnTrshldElgs().size()>0){
								rule2SrcTrgts = getThrshldSourcesFromTran(rule2, splttdOrdLineItms, classModItms, false);
							} else {
								rule2SrcTrgts = getSourceTargetFromTran(rule2, splttdOrdLineItms, isSrcItmLvl, true,
										classModItms, false);
							}
							
							
						}
						
						if(rule2SrcTrgts!=null && rule2SrcTrgts.size()>0){
							for(OrderTranLineItem rule1Itm : rule1SrcTrgts){
								for(OrderTranLineItem rule2Itm : rule2SrcTrgts){
									if(rule1Itm.getItemId().equalsIgnoreCase(rule2Itm.getItemId())){
										hasConflict=true;
										break;
									}
								}
							}
						}

						boolean isSameSource = false;// sameSourcesTargets(rule1SrcTrgts,
						/*								// rule2SrcTrgts);
						if ((rule2SrcTrgts.size() > 0) || hasConflict) {
							isSameSource = true;
						}*/
						
						if (hasConflict) {
							isSameSource = true;
						}

						if (isSameSource) {

							boolean isSameTargets = true;// sameSourcesTargets(rule1Trgts,
															// rule2Trgts);

							if (isSameTargets) {
								List<OrderTranLineItem> splttdOrdLineItmsBkup = new ArrayList<OrderTranLineItem>();
								splttdOrdLineItmsBkup.addAll(splttdOrdLineItms);
								BigDecimal rule1Dis = getRuleDiscountAmount(rule1, splttdOrdLineItms, false,
										classModItms, transaction);
								
								splttdOrdLineItms.clear();
								splttdOrdLineItms.addAll(splttdOrdLineItmsBkup);
								BigDecimal rule2Dis = getRuleDiscountAmount(rule2, splttdOrdLineItms, false,
										classModItms, transaction);

								splttdOrdLineItms.clear();
								splttdOrdLineItms.addAll(splttdOrdLineItmsBkup);
								
								if (rule1Dis.compareTo(rule2Dis) == 1) {
									removeRules.add(rule2);
								} else {
									removeRules.add(rule1);
									swapped = true;
								}

							}

						}

					} else {
						if (fltrdMultiRules.size() == 1) {
							swapped = true;
						}
					}

				}
			}

			if (!swapped) {
				fltrdMultiRules.add(temp);
			}
		}

		validDiscountRules.removeAll(removeRules);
		LOGGER.info("Completed swapBestDiscountRuleOutOfMultiple Logic");
	}

	// method to calculate Discount amount of a rule
	public BigDecimal getRuleDiscountAmount(RisplDkPrdvnRule discountRule, List<OrderTranLineItem> splttdOrdLineItms,
			boolean applyDiscount, Map<String, Set<String>> classModItms, OrderTranHeader transaction) {
		LOGGER.info("Executing getRuleDiscountAmount Logic");
		boolean targetsMandatory = targetsRequired.get(discountRule.getDeRuPrdv());
		boolean storeWideProm = false;
		boolean discValuesNull = false;
		List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs = discountRule.getRisplDkPrdvnRuleElgs();
		if (risplDkPrdvnRuleElgs != null && risplDkPrdvnRuleElgs.size() == 1
				&& risplDkPrdvnRuleElgs.get(0).getItmId().equals(DKartConstantsIfc.STAR)) {
			storeWideProm = true;
		}

		BigDecimal discountAmount = BigDecimal.ZERO;

		int numOfTmsPrTran = discountRule.getQuLmAply().intValue();

		List<RisplDkPrdvnRuleDisc> ruleDiscount = discountRule.getRisplDkPrdvnRuleDiscs();

		boolean isSrcItmLvl = false;
		if (discountRule.getCdBasCmpSrc().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_ITM)) {
			isSrcItmLvl = true;
		}

		boolean isTrgtItmLvl = false;
		if (discountRule.getCdBasCmpTgt().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_ITM)) {
			isTrgtItmLvl = true;
		}

		List<OrderTranLineItem> discountAppliedLineItems = new ArrayList<OrderTranLineItem>();

		// Deal Distribution 1=SourceTarget, 0=Target
		String dealDistribution = discountRule.getFlDlDst();

		RisplDkPrdvnRuleDisc ruleDiscountVal = null;
		if (ruleDiscount != null && ruleDiscount.size() > 0) {
			ruleDiscountVal = ruleDiscount.get(0);
			
			if(ruleDiscountVal.getMoUnItmPrdvSls()==null && ruleDiscountVal.getPeUnItmPrdvSls()==null && ruleDiscountVal.getPntPrcUnItmPrdvSls()==null){
				discValuesNull = true;
				List<RisplDkPrdvnTrshldElg> thrshldElgs = discountRule.getRisplDkPrdvnTrshldElgs();
				if(thrshldElgs!=null && thrshldElgs.size()>0){
					RisplDkPrdvnTrshldElg thrshld = thrshldElgs.get(0);
					ruleDiscountVal.setMoUnItmPrdvSls(thrshld.getMoUnThPrdvSls());
					ruleDiscountVal.setPeUnItmPrdvSls(thrshld.getPeUnThPrdvSls());
					ruleDiscountVal.setPntPrcUnItmPrdvSls(thrshld.getPtPrcThPrdvSls());
				}
			}
		}

		if (numOfTmsPrTran == -1) {
			numOfTmsPrTran = splttdOrdLineItms.size();
		}

		int sourceQualifier = discountRule.getQuAnSrc().intValue();
		Integer targetQualifier = null;
		if (discountRule.getQuAnTgt() != null) {
			targetQualifier = discountRule.getQuAnTgt().intValue();
		}
		while (numOfTmsPrTran > 0 && splttdOrdLineItms.size() != 0) {

			List<OrderTranLineItem> srcItems = null;
			List<OrderTranLineItem> targtItems = null;

			//if (discountRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_NOR_MORE_OF_XFOR_Z_OFF)) {// BuyNorMoreOfXforZ%off
			if(!targetsRequired.get(discountRule.getDeRuPrdv()) && discountRule.getRisplDkPrdvnTrshldElgs()!=null && discountRule.getRisplDkPrdvnTrshldElgs().size()>0){
				srcItems = getThrshldSourcesFromTran(discountRule, splttdOrdLineItms, classModItms, true);
				targtItems = new ArrayList<OrderTranLineItem>();
			}else{

				srcItems = getSourceTargetFromTran(discountRule, splttdOrdLineItms, isSrcItmLvl, true, classModItms,
						true);
				if (srcItems.size() > 0 && targetsMandatory) {
					targtItems = getSourceTargetFromTran(discountRule, splttdOrdLineItms, isTrgtItmLvl, false,
							classModItms, true);
				}

				if (targtItems != null && targtItems.size() == 0 && targetsMandatory && splttdOrdLineItms.size()!=0) {
					if (applyDiscount) {
						splttdOrdLineItms.addAll(srcItems);
					}
					targtItems = getSourceTargetFromTran(discountRule, splttdOrdLineItms, isTrgtItmLvl, false,
							classModItms, true);
					srcItems = getSourceTargetFromTran(discountRule, splttdOrdLineItms, isSrcItmLvl, true, classModItms,
							true);
				}

			}

			BigDecimal currDisAmt = BigDecimal.ZERO;

			BigDecimal srcTrgtItmsTtl = BigDecimal.ZERO;

			List<OrderTranLineItem> validLinItms = new ArrayList<OrderTranLineItem>();

			boolean appliedSrc = false;

			if (targetsMandatory && targtItems != null && targtItems.size() > 0 && srcItems!=null && srcItems.size()>0) {
				validLinItms.addAll(targtItems);
			}

			if ((dealDistribution.equalsIgnoreCase(DKartConstantsIfc.DEAL_DIST_SRCTRGT) && targtItems != null && targtItems.size() > 0) || (validLinItms.size()==0 && !targetsRequired.get(discountRule.getDeRuPrdv()))) {
				validLinItms.addAll(srcItems);
				appliedSrc = true;
			}
			
			boolean isThrshldRule = false;
			if(validLinItms.size()==0){
				List<RisplDkPrdvnTrshldElg> thrshldEkgs = discountRule.getRisplDkPrdvnTrshldElgs();
				if(thrshldEkgs!=null && thrshldEkgs.size()>0 && discValuesNull){
					isThrshldRule=true;
				}
			}
			
			if(dealDistribution.equalsIgnoreCase(DKartConstantsIfc.DEAL_DIST_SRCTRGT) && isThrshldRule && validLinItms.size()==0){
				validLinItms.addAll(srcItems);
				appliedSrc = true;
			}

			// calculating source and target items total
			if (storeWideProm) {
				srcTrgtItmsTtl = transaction.getOrdTranSum().getDkartNetTot();
			} else {
				for (OrderTranLineItem trgtItm : validLinItms) {
					if (trgtItm.getExtnDscLnItm() != null) {
						srcTrgtItmsTtl = srcTrgtItmsTtl.add(trgtItm.getExtnDscLnItm());
					} else {
						srcTrgtItmsTtl = srcTrgtItmsTtl.add(trgtItm.getExtnLnItmRtn());
					}

				}
			}
			//srcTrgtItmsTtl = srcTrgtItmsTtl.setScale(currencyDecimal, RoundingMode.DOWN);
			srcTrgtItmsTtl = ConfigUtils.getInstance().createBigDecimal(srcTrgtItmsTtl, format);
			if (validLinItms.size() > 0) {
				if (discountRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_NOR_MORE_OF_XFOR_Z_OFF)) {// BuyNorMoreOfXforZ%off

					List<RisplDkPrdvnTrshldElg> risplDkPrdvnTrshldElgs = discountRule.getRisplDkPrdvnTrshldElgs();
					Map<BigDecimal, BigDecimal> qtyPrcntg = new HashMap<BigDecimal, BigDecimal>();
					for (RisplDkPrdvnTrshldElg risplDkPrdvnTrshldElg : risplDkPrdvnTrshldElgs) {
						qtyPrcntg.put(risplDkPrdvnTrshldElg.getThVal(), risplDkPrdvnTrshldElg.getPtPrcThPrdvSls());
					}

					BigDecimal trshldQty = BigDecimal.ZERO;
					for (OrderTranLineItem vldLine : validLinItms) {
						for (OrderTranLineItem vldLineItm : validLinItms) {
							if (vldLineItm.getItemId().equalsIgnoreCase(vldLine.getItemId())) {
								trshldQty = trshldQty.add(vldLineItm.getLineQnt());
							}
						}

						break;
					}

					BigDecimal discountPrcntge = BigDecimal.ZERO;
					if (validLinItms.size() > 0) {
						discountPrcntge = qtyPrcntg.get(trshldQty);

						if (discountPrcntge != null) {
							discountPrcntge = discountPrcntge.divide(new BigDecimal(100));//.setScale(currencyDecimal,RoundingMode.UP);
							discountPrcntge = ConfigUtils.getInstance().createBigDecimal(discountPrcntge, format);
							currDisAmt = currDisAmt.add(srcTrgtItmsTtl.multiply(discountPrcntge));
						}
					}

				} else if (discountRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_NOF_XFOR_Z_$_OFF)
						|| discountRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_NOF_XFOR_Z_OFF)) {
					// for Rule BuyNofXforZ$off and BuyNofXforZ%off
					if (validLinItms.size() > 0) {

						if (ruleDiscountVal.getMoUnItmPrdvSls() != null) {
							// Amount off on targets/sourcetargets
							currDisAmt = currDisAmt.add(ruleDiscountVal.getMoUnItmPrdvSls());

						} else if (ruleDiscountVal.getPntPrcUnItmPrdvSls() != null) {
							// percent off on items total
							BigDecimal disPercnt = ruleDiscountVal.getPntPrcUnItmPrdvSls().divide(new BigDecimal(100));
									//.setScale(currencyDecimal);
							disPercnt = ConfigUtils.getInstance().createBigDecimal(disPercnt, format);
							currDisAmt = currDisAmt.add(srcTrgtItmsTtl.multiply(disPercnt));
						}
					}

				} else if (discountRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_NOF_XFOR_Z_$)) {
					// for BuyNofXforZ$ rule
					BigDecimal fixedValue = ruleDiscountVal.getPeUnItmPrdvSls();
					currDisAmt = srcTrgtItmsTtl.subtract(fixedValue);

				} else if (discountRule.getDeRuPrdv()
						.equalsIgnoreCase(DKartConstantsIfc.BUY_NOF_XGET_LOWEST_PRICED_XAT_Z_OFF)
						|| discountRule.getDeRuPrdv()
								.equalsIgnoreCase(DKartConstantsIfc.BUY_NOF_XGET_HIGHEST_PRICED_XAT_Z_OFF)) {
					// for BuyNofXgetLowestPricedXatZ%off and
					// BuyNofXgetHighestPricedXatZ%off rules
					boolean isLowest = false;
					if (discountRule.getDeRuPrdv()
							.equalsIgnoreCase(DKartConstantsIfc.BUY_NOF_XGET_LOWEST_PRICED_XAT_Z_OFF)) {
						isLowest = true;
					}
					boolean modified = false;
					if (targetsMandatory && targtItems != null && targtItems.size() > 0) {
						validLinItms.removeAll(srcItems);
						modified = true;
					}
					OrderTranLineItem lowestHighestItem = getLowstHighestItem(validLinItms, isLowest);
					if (modified) {
						validLinItms.addAll(srcItems);
					}
					BigDecimal disPercnt = ruleDiscountVal.getPntPrcUnItmPrdvSls().divide(new BigDecimal(100));
							//.setScale(currencyDecimal);
					disPercnt = ConfigUtils.getInstance().createBigDecimal(disPercnt, format);
					currDisAmt = currDisAmt.add(lowestHighestItem.getItmPrnPrc().multiply(disPercnt));

					if (dealDistribution.equalsIgnoreCase(DKartConstantsIfc.DEAL_DIST_TRGT)
							&& lowestHighestItem != null) {
						validLinItms.clear();
						validLinItms.add(lowestHighestItem);
						srcTrgtItmsTtl = lowestHighestItem.getItmPrnPrc();
					}

				} else {
					for (OrderTranLineItem trgtItm : validLinItms) {
						BigDecimal itemExtPrc = BigDecimal.ZERO;
						if (trgtItm.getExtnDscLnItm() != null) {
							itemExtPrc = trgtItm.getExtnDscLnItm();
						} else {
							itemExtPrc = trgtItm.getExtnLnItmRtn();
						}

						if (ruleDiscountVal.getMoUnItmPrdvSls() != null) {
							// Amount off on Item Price
							currDisAmt = currDisAmt.add(ruleDiscountVal.getMoUnItmPrdvSls());

						} else if (ruleDiscountVal.getPeUnItmPrdvSls() != null) {
							// New price of the item
							currDisAmt = currDisAmt.add(itemExtPrc.subtract(ruleDiscountVal.getPeUnItmPrdvSls()));

						} else if (ruleDiscountVal.getPntPrcUnItmPrdvSls() != null) {
							// percent off on item selling price
							BigDecimal disPercnt = ruleDiscountVal.getPntPrcUnItmPrdvSls().divide(new BigDecimal(100));
									//.setScale(currencyDecimal);
							disPercnt = ConfigUtils.getInstance().createBigDecimal(disPercnt, format);
							currDisAmt = currDisAmt.add(itemExtPrc.multiply(disPercnt));
						}
					}
				}
			}

			//currDisAmt = currDisAmt.setScale(currencyDecimal, RoundingMode.DOWN);
			currDisAmt = ConfigUtils.getInstance().createBigDecimal(currDisAmt, format);
			
			if (applyDiscount && validLinItms.size() > 0) {

				prorateDiscountOnItems(discountRule, validLinItms, currDisAmt, srcTrgtItmsTtl);
			}

			discountAmount = discountAmount.add(currDisAmt);

			if(!appliedSrc) {
				validLinItms.addAll(srcItems);
				if (srcItems.size() == 0 && targetsMandatory && targtItems != null && targtItems.size() > 0) {
					validLinItms.addAll(targtItems);
				}
			}
			discountAppliedLineItems.addAll(validLinItms);

			numOfTmsPrTran--;

			if (splttdOrdLineItms.size() == 0 || validLinItms.size() == 0
					|| (storeWideProm && !applyDiscount && srcItems.size() == splttdOrdLineItms.size())) {
				break;
			}

		}
		if (applyDiscount) {
			splttdOrdLineItms.addAll(discountAppliedLineItems);
		}
		//discountAmount = discountAmount.setScale(currencyDecimal, RoundingMode.DOWN);
		discountAmount = ConfigUtils.getInstance().createBigDecimal(discountAmount, format);
		
		LOGGER.info("Completed getRuleDiscountAmount Logic");
		return discountAmount;
	}

	// method to return Lowest or Highest item price
	public OrderTranLineItem getLowstHighestItem(List<OrderTranLineItem> sourceItems, boolean isLowest) {
		LOGGER.info("Executing getLowstHighestItem Logic");
		BigDecimal lowestHighestPrice = BigDecimal.ZERO;
		OrderTranLineItem lowestHighestItem = null;
		for (OrderTranLineItem srcItm : sourceItems) {

			if (lowestHighestPrice.compareTo(BigDecimal.ZERO) == 0) {
				lowestHighestPrice = srcItm.getItmPrnPrc();
				lowestHighestItem = srcItm;
			} else {

				if (isLowest) {
					if (srcItm.getItmPrnPrc().compareTo(lowestHighestPrice) == -1) {
						lowestHighestPrice = srcItm.getItmPrnPrc();
						lowestHighestItem = srcItm;
					}
				} else {
					if (srcItm.getItmPrnPrc().compareTo(lowestHighestPrice) == 1) {
						lowestHighestPrice = srcItm.getItmPrnPrc();
						lowestHighestItem = srcItm;
					}
				}

			}
		}
		LOGGER.info("Completed getLowstHighestItem Logic");
		return lowestHighestItem;
	}

	// get Sources and Targets to apply discount
	public List<OrderTranLineItem> getSourceTargetFromTran(RisplDkPrdvnRule discountRule,
			List<OrderTranLineItem> splttdOrdLineItms, boolean isItemLevel, boolean isSource,
			Map<String, Set<String>> classModItms, boolean remove) {
		List<OrderTranLineItem> eligibleLineItems = new ArrayList<OrderTranLineItem>();

		int srcTrgtAnyQlfr = 0;

		boolean amtBsdSrc = false;

		List<OrderTranLineItem> srcTrgts = new ArrayList<OrderTranLineItem>();

		String startItem = "";

		if (isSource) {
			List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs = discountRule.getRisplDkPrdvnRuleElgs();
			for (RisplDkPrdvnRuleElg ruleSource : risplDkPrdvnRuleElgs) {
				OrderTranLineItem ruleItem = new OrderTranLineItem();
				ruleItem.setItemId(ruleSource.getItmId());
				if (ruleSource.getItmId().equalsIgnoreCase(DKartConstantsIfc.STAR)) {
					startItem = ruleSource.getItmId();
				}
				ruleItem.setLineQnt(ruleSource.getQuTh());
				ruleItem.setExtnDscLnItm(ruleSource.getMoTh());
				if (ruleSource.getMoTh() != null) {
					amtBsdSrc = true;
				}
				srcTrgts.add(ruleItem);
			}

			/*
			 * (0) - ALL entries in the source list are required, (1 or more) -
			 * ANY <1 or more> entries from the source list are required. (-1) -
			 * AnyCombo
			 */

			int qlfr = discountRule.getQuAnSrc().intValue();
			if (qlfr == 0) {
				srcTrgtAnyQlfr = srcTrgts.size();
			} else if (qlfr == -1) {
				srcTrgtAnyQlfr = 2;
			} else {
				srcTrgtAnyQlfr = qlfr;
			}

			// To filter the sources with highest qty so that the rule can be
			// applied multiple times when same items are available in source
			// and targets
			if (isSource && discountRule.getQuAnSrc().intValue() != 0) {
				Map<String, BigDecimal> itmIdQty = new HashMap<String, BigDecimal>();
				for (OrderTranLineItem splttdOrdLineItm : splttdOrdLineItms) {

					if (itmIdQty.containsKey(splttdOrdLineItm.getItemId())) {
						itmIdQty.put(splttdOrdLineItm.getItemId(),
								itmIdQty.get(splttdOrdLineItm.getItemId()).add(splttdOrdLineItm.getLineQnt()));
					} else {
						itmIdQty.put(splttdOrdLineItm.getItemId(), splttdOrdLineItm.getLineQnt());
					}
				}

				List<OrderTranLineItem> fltrdSrcTrgts = new ArrayList<OrderTranLineItem>();
				for (int startIndx = 1; startIndx <= srcTrgtAnyQlfr; startIndx++) {

					BigDecimal lineQty = BigDecimal.ZERO;
					OrderTranLineItem temp = null;

					for (OrderTranLineItem ruleSrc : srcTrgts) {

						if (itmIdQty.containsKey(ruleSrc.getItemId())
								&& itmIdQty.get(ruleSrc.getItemId()).compareTo(lineQty) == 1) {
							temp = ruleSrc;
							lineQty = itmIdQty.get(ruleSrc.getItemId());
						}
					}
					if (temp != null) {
						fltrdSrcTrgts.add(temp);
						srcTrgts.removeAll(fltrdSrcTrgts);
					}
				}
				if (fltrdSrcTrgts.size() > 0) {
					srcTrgts.clear();
					srcTrgts.addAll(fltrdSrcTrgts);
				}

			}

		} else {

			List<RisplDkPrdvnMmitm> risplDkPrdvnMmitms = discountRule.getRisplDkPrdvnMmitms();
			for (RisplDkPrdvnMmitm ruleTarget : risplDkPrdvnMmitms) {
				OrderTranLineItem ruleItem = new OrderTranLineItem();
				ruleItem.setItemId(ruleTarget.getIdPrmPrd());
				ruleItem.setLineQnt(ruleTarget.getQuLmMxmh());

				srcTrgts.add(ruleItem);
			}

			// (Null) - Any/All condition does not apply to target,
			// (0) - ALL entries in the target list are required,
			// (1 or more) - ANY <1 or more>

			int qlfr = -1;

			if (discountRule.getQuAnTgt() != null) {
				qlfr = discountRule.getQuAnTgt().intValue();
			}

			if (qlfr == 0) {
				srcTrgtAnyQlfr = srcTrgts.size();
			} else if (qlfr == -1) {
				srcTrgtAnyQlfr = 1;
			} else {
				srcTrgtAnyQlfr = qlfr;
			}
		}

		List<OrderTranLineItem> linItmsPool = new ArrayList<OrderTranLineItem>();
		int count = 0;
		if (startItem.equalsIgnoreCase(DKartConstantsIfc.STAR)) {
			linItmsPool.addAll(splttdOrdLineItms);
			eligibleLineItems.addAll(linItmsPool);
		} else if (isItemLevel) {
			// If source or targets are Item level

			if (amtBsdSrc) {
				// for Amount based sources

				for (OrderTranLineItem rulSrc : srcTrgts) {

					BigDecimal tranLinAmt = BigDecimal.ZERO;

					List<OrderTranLineItem> lineLevelPool = new ArrayList<OrderTranLineItem>();

					for (OrderTranLineItem splttdOrdLineItm : splttdOrdLineItms) {

						if (splttdOrdLineItm.getItemId().equalsIgnoreCase(rulSrc.getItemId())) {

							tranLinAmt = tranLinAmt.add(splttdOrdLineItm.getExtnDscLnItm());
							lineLevelPool.add(splttdOrdLineItm);
						}

						if (tranLinAmt.compareTo(rulSrc.getExtnDscLnItm()) != -1) {
							count++;
							linItmsPool.addAll(lineLevelPool);
							break;
						}

					}

					if (count == srcTrgtAnyQlfr) {
						break;
					}
				}

			} else {
				// for Quantity based sources
				boolean alternateCheck = true;
				for (OrderTranLineItem rulSrc : srcTrgts) {

					BigDecimal ruleQty = rulSrc.getLineQnt();
					if (srcTrgts.size() == 1) {
						ruleQty = new BigDecimal(srcTrgtAnyQlfr);
					}else if(isSource && discountRule.getQuAnSrc()!=null && discountRule.getQuAnSrc().intValue() > 0){
						ruleQty = discountRule.getQuAnSrc();
					}else if(!isSource && discountRule.getQuAnTgt()!=null && discountRule.getQuAnTgt().intValue() > 0){
						ruleQty = discountRule.getQuAnTgt();
					}

					BigDecimal tranLinQt = BigDecimal.ZERO;

					List<OrderTranLineItem> lineLevelPool = new ArrayList<OrderTranLineItem>();
					int lineCount=0;
					for (OrderTranLineItem splttdOrdLineItm : splttdOrdLineItms) {

						if (splttdOrdLineItm.getItemId().equalsIgnoreCase(rulSrc.getItemId()) && !linItmsPool.contains(splttdOrdLineItm)) {

							tranLinQt = tranLinQt.add(splttdOrdLineItm.getLineQnt());
							lineLevelPool.add(splttdOrdLineItm);
							lineCount++;
						}

						if (tranLinQt.compareTo(ruleQty) == 0) {
							count=lineCount;
							if (srcTrgts.size() == 1) {
								count = ruleQty.intValue();
							}
							linItmsPool.addAll(lineLevelPool);
							break;
						}

					}
					
					if (count == srcTrgtAnyQlfr) {
						alternateCheck=false;
						break;
					}
				}
			
			if(alternateCheck){	
					count=0;
					linItmsPool.clear();
					
					for (OrderTranLineItem rulSrc : srcTrgts) {

						BigDecimal ruleQty = rulSrc.getLineQnt();
						if (srcTrgts.size() == 1) {
							ruleQty = new BigDecimal(srcTrgtAnyQlfr);
						}

						BigDecimal tranLinQt = BigDecimal.ZERO;

						List<OrderTranLineItem> lineLevelPool = new ArrayList<OrderTranLineItem>();

						for (OrderTranLineItem splttdOrdLineItm : splttdOrdLineItms) {

							if (splttdOrdLineItm.getItemId().equalsIgnoreCase(rulSrc.getItemId()) && !linItmsPool.contains(splttdOrdLineItm)) {

								tranLinQt = tranLinQt.add(splttdOrdLineItm.getLineQnt());
								lineLevelPool.add(splttdOrdLineItm);
							}

							if (tranLinQt.compareTo(ruleQty) == 0) {
								count++;
								if (srcTrgts.size() == 1) {
									count = ruleQty.intValue();
								}
								linItmsPool.addAll(lineLevelPool);
								break;
							}

						}
						
						if (count == srcTrgtAnyQlfr) {
							break;
						}
					}
			}
				
			}

		} else {
			/*
			 * If source or targets are Class level Targets will be only
			 * Quantity based Check Source any qualifier Check allow source to
			 * repeat There will be amount based targets - only quantity based
			 */

			if (amtBsdSrc) {

				for (OrderTranLineItem rulSrc : srcTrgts) {

					BigDecimal tranLinAmt = BigDecimal.ZERO;

					Set<String> classItems = classModItms.get(rulSrc.getItemId());

					List<OrderTranLineItem> lineLevelPool = new ArrayList<OrderTranLineItem>();

					for (String clsItm : classItems) {

						for (OrderTranLineItem splttdOrdLineItm : splttdOrdLineItms) {

							if (splttdOrdLineItm.getItemId().equalsIgnoreCase(clsItm)) {

								tranLinAmt = tranLinAmt.add(splttdOrdLineItm.getExtnDscLnItm());
								lineLevelPool.add(splttdOrdLineItm);
							}

							if (tranLinAmt.compareTo(rulSrc.getExtnDscLnItm()) != -1) {
								count++;
								linItmsPool.addAll(lineLevelPool);
								break;
							}

						}

						if (count == srcTrgtAnyQlfr) {
							break;
						}
					}

					if (count == srcTrgtAnyQlfr) {
						break;
					}
				}

			} else {

				for (OrderTranLineItem rulSrc : srcTrgts) {

					BigDecimal tranLinQt = BigDecimal.ZERO;

					Set<String> classItems = classModItms.get(rulSrc.getItemId());

					List<OrderTranLineItem> lineLevelPool = new ArrayList<OrderTranLineItem>();

					for (String clsItm : classItems) {

						for (OrderTranLineItem splttdOrdLineItm : splttdOrdLineItms) {

							if (splttdOrdLineItm.getItemId().equalsIgnoreCase(clsItm)) {

								tranLinQt = tranLinQt.add(splttdOrdLineItm.getLineQnt());
								lineLevelPool.add(splttdOrdLineItm);
							}

							if (tranLinQt.compareTo(rulSrc.getLineQnt()) == 0) {
								count++;
								linItmsPool.addAll(lineLevelPool);
								break;
							}

						}

						if (count == srcTrgtAnyQlfr) {
							break;
						}
					}

					if (count == srcTrgtAnyQlfr) {
						break;
					}
				}

			}

		}

		if (count == srcTrgtAnyQlfr) {
			eligibleLineItems.addAll(linItmsPool);
		}

		if ((count == srcTrgtAnyQlfr) && remove) {
			splttdOrdLineItms.removeAll(linItmsPool);
		}

		return eligibleLineItems;
	}

	// method to check if source and target items are same
	public boolean sameSourcesTargets(List<OrderTranLineItem> rule1SrcTrgts, List<OrderTranLineItem> rule2SrcTrgts) {
		boolean isEqual = true;
		for (OrderTranLineItem rule1 : rule1SrcTrgts) {
			boolean sameRuleCrit = false;
			for (OrderTranLineItem rule2 : rule2SrcTrgts) {
				if (rule1.getItemId().equalsIgnoreCase(rule2.getItemId())) {// check
																			// if
																			// same
																			// item
																			// ids
					// check if same qty
					if (rule1.getLineQnt() != null && rule2.getLineQnt() != null
							&& rule1.getLineQnt().compareTo(rule2.getLineQnt()) == 0) {
						sameRuleCrit = true;
					} else {
						if (rule1.getExtnDscLnItm() != null && rule2.getExtnDscLnItm() != null
								&& rule1.getExtnDscLnItm().compareTo(rule2.getExtnDscLnItm()) == 0) {
							sameRuleCrit = true;
						}
					}

				}
			}

			if (!sameRuleCrit) {
				isEqual = false;
				break;
			}
		}
		return isEqual;
	}

	// method to process and apply discount if rule = Buy$NofXforZ%off and
	// Buy$NofXforZ$off
	public void buyDollarNofXforZPrcntoff(RisplDkPrdvnRule risplDkPrdvnRule, List<OrderTranLineItem> splttdOrdLineItms,
			OrderTranHeader transaction, boolean isPercentOff, Map<String, Set<String>> classModItms) {
		/*
		 * If Transaction Level = Yes, then all items in the store are eligible
		 * for this rule If Store Level = Yes, then all items in the store are
		 * eligible for this rule
		 * 
		 * If Transaction Level = No, then Source can be Class or Department
		 * 
		 * If Transaction Level = Yes If Store Level = No, then Source can be
		 * Class or Department
		 * 
		 * There will be no Targets for this rule There will be include any
		 * qualifier if Source is Class/Department
		 */

		/*
		 * Source details
		 */
		List<RisplDkPrdvnRuleElg> sourceDetails = risplDkPrdvnRule.getRisplDkPrdvnRuleElgs();

		/*
		 * Discount Amount or Percentage details
		 */
		List<RisplDkPrdvnRuleDisc> discountDetails = risplDkPrdvnRule.getRisplDkPrdvnRuleDiscs();

		/*
		 * Cancelled Item Details
		 */
		List<RisplDkPrdvnItmNelg> cnclldItmDetails = risplDkPrdvnRule.getRisplDkPrdvnItmNelgs();

		// Get Promotion High Level Data
		String promotionName = risplDkPrdvnRule.getNmRuPrdv();
		String promotionDesc = risplDkPrdvnRule.getDeRuPrdv();

		// Deal Distribution 1=SourceTarget, 0=Target
		String dealDistribution = risplDkPrdvnRule.getFlDlDst();

		// Allow Source to repeat 0= false, 1= true
		boolean alwSrcRepeat = getBooleanValue(risplDkPrdvnRule.getFlAlwRptSrc());

		// Source type Item,Department,Class,Coupon
		String sourceType = risplDkPrdvnRule.getCdBasCmpSrc();

		// Target type Item,Department,Class
		String targetType = risplDkPrdvnRule.getCdBasCmpTgt();

		// (0) - ALL entries in the source list are required,
		// (1 or more) - ANY <1 or more> entries from the source list are
		// required.
		// (-1) - AnyCombo
		int sourceQualifier = risplDkPrdvnRule.getQuAnSrc().intValue();

		// Number of times per transaction
		// -1 = multiple times, 1 or more multiple times as the number defines
		int numOfTimes = risplDkPrdvnRule.getQuLmAply().intValue();

		// Transaction = 0, Item = 1, Group = 2
		Integer scope = null;
		if (risplDkPrdvnRule.getCdScpPrdv() != null) {
			scope = risplDkPrdvnRule.getCdScpPrdv().intValue();
		}

		boolean isStoreLevel = false;
		if (sourceDetails != null && sourceDetails.size() == 1) {
			if (sourceDetails.get(0).getItmId().equalsIgnoreCase("*")) {
				isStoreLevel = true;
			}
		}

		// if transaction level and store level
		if (scope != null && scope == 0 && isStoreLevel) {
			if (sourceType.equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_ITM)) {// Item
																						// ID
																						// will
																						// be
																						// *
				/*
				 * Get Source Criteria from Rule Check number of times per
				 * transaction Check if transaction meets critera get discount
				 * details apply discount based on number of times per
				 * transaction
				 */
				// Get Source Criteria from Rule
				BigDecimal sourceAmount = BigDecimal.ZERO;
				if (sourceDetails != null && sourceDetails.size() > 0) {
					sourceAmount = sourceDetails.get(0).getMoTh();
				}

				// Check if transaction meets critera
				BigDecimal tranTotal = transaction.getOrdTranSum().getDkartNetTot();
				/*
				 * for(OrderTranLineItem splttdOrdLineItm : splttdOrdLineItms){
				 * tranTotal =
				 * tranTotal.add(splttdOrdLineItm.getExtnDscLnItm()); }
				 */

				// check how many times transaction is eligible for this rule
				boolean tranElg = false;
				if (tranTotal.compareTo(sourceAmount) != -1) {
					tranElg = true;
				}

				// get discount details = percentage
				BigDecimal disPercnt = BigDecimal.ZERO;
				if (discountDetails != null && discountDetails.size() > 0) {
					disPercnt = (discountDetails.get(0).getPntPrcUnItmPrdvSls().divide(new BigDecimal(100)));
							//.setScale(currencyDecimal));
					disPercnt = ConfigUtils.getInstance().createBigDecimal(disPercnt, format);
				}

				BigDecimal tranDisAmount = BigDecimal.ZERO;

				if (isPercentOff) {
					tranDisAmount = tranTotal.multiply(disPercnt);
				} else {
					tranDisAmount = discountDetails.get(0).getMoUnItmPrdvSls();
				}

				if (tranElg) {
					prorateDiscountOnItems(risplDkPrdvnRule, splttdOrdLineItms, tranDisAmount, tranTotal);
					appldRuleIdsDisAmt.put(risplDkPrdvnRule.getId(), tranDisAmount);
				}
			}
		}

		/*
		 * if transaction level Source can be either Class or Department If
		 * Source is department XML will be populated with class id as it is in
		 * itemclassificationmod xml file
		 * 
		 * Need to check AnyQualifier
		 * 
		 * Number of times per transaction needs to be checked
		 */
		int nOftmsprTran = 0;

		if (numOfTimes == -1) {
			nOftmsprTran = splttdOrdLineItms.size();
		} else {
			nOftmsprTran = numOfTimes;
		}

		boolean sourceQualified = false;

		int srcAnyQlfier = 0;

		if ((scope != null && scope == 0 && !isStoreLevel)
				|| /* if not transaction level */ ((scope == null && !isStoreLevel)
						|| (scope != null && scope != 0 && !isStoreLevel))) {
			if (itemClassificationTotals != null) {
				if (sourceQualifier == 0) {// All sources needs to be there in
											// transaction
					if (itemClassificationTotals.size() == sourceDetails.size()) {
						srcAnyQlfier = itemClassificationTotals.size();
						sourceQualified = true;
					}
				} else if (sourceQualifier == -1) {// Any Combo

					if (itemClassificationTotals.size() >= 2) {
						srcAnyQlfier = 2;
						sourceQualified = true;
					}

				} else if (sourceQualifier >= 1) {// Any one or more as the
													// number indicates

					if (itemClassificationTotals.size() >= sourceQualifier) {
						srcAnyQlfier = sourceQualifier;
						sourceQualified = true;
					}
				}
			}
		}

		/*
		 * if not transaction level Source can be either Class or Department If
		 * Source is department XML will be populated with class id as it is in
		 * itemclassificationmod xml file
		 * 
		 * Need to check AnyQualifier
		 * 
		 * Number of times per transaction needs to be checked
		 */

		if ((scope == null && !isStoreLevel) || (scope != null && scope != 0 && !isStoreLevel)) {

		}

		// Commom for above two scenarios
		// process order line items to calculate discount and apply
		if (sourceQualified) {
			List<OrderTranLineItem> disAppldLinItms = new ArrayList<OrderTranLineItem>();
			while (nOftmsprTran > 0) {
				List<OrderTranLineItem> linItmsToApplyDis = new ArrayList<OrderTranLineItem>();

				int srcAnyCount = 0;

				BigDecimal tranTotal = BigDecimal.ZERO;

				for (RisplDkPrdvnRuleElg sourceCrit : sourceDetails) {

					BigDecimal srcClssTttl = sourceCrit.getMoTh();

					List<OrderTranLineItem> localPool = new ArrayList<OrderTranLineItem>();

					Set<String> classItems = classModItms.get(sourceCrit.getItmId());

					BigDecimal clssItmsTtl = BigDecimal.ZERO;

					for (String classItem : classItems) {

						for (OrderTranLineItem splttdOrdLineItm : splttdOrdLineItms) {
							if (splttdOrdLineItm.getItemId().equalsIgnoreCase(classItem)) {
								clssItmsTtl = clssItmsTtl.add(splttdOrdLineItm.getExtnDscLnItm());
								localPool.add(splttdOrdLineItm);
							}

							if (clssItmsTtl.compareTo(srcClssTttl) != -1) {
								break;
							}
						}

						if (clssItmsTtl.compareTo(srcClssTttl) != -1) {
							tranTotal = tranTotal.add(clssItmsTtl);
							srcAnyCount++;
							break;
						}
					}

					if (srcAnyCount == srcAnyQlfier) {
						linItmsToApplyDis.addAll(localPool);
						break;
					}
				}

				if (srcAnyCount == srcAnyQlfier) {

					// Apply Discount
					// get discount details = percentage
					BigDecimal disPercnt = BigDecimal.ZERO;

					BigDecimal tranDisAmount = BigDecimal.ZERO;

					if (isPercentOff) {
						if (discountDetails != null && discountDetails.size() > 0) {
							disPercnt = (discountDetails.get(0).getPntPrcUnItmPrdvSls().divide(new BigDecimal(100)));
									//.setScale(currencyDecimal));
							disPercnt = ConfigUtils.getInstance().createBigDecimal(disPercnt, format);
						}
						tranDisAmount = tranTotal.multiply(disPercnt);
					} else {
						tranDisAmount = discountDetails.get(0).getMoUnItmPrdvSls();
					}

					prorateDiscountOnItems(risplDkPrdvnRule, linItmsToApplyDis, tranDisAmount, tranTotal);

					if (appldRuleIdsDisAmt.containsKey(risplDkPrdvnRule.getId())) {
						appldRuleIdsDisAmt.put(risplDkPrdvnRule.getId(),
								appldRuleIdsDisAmt.get(risplDkPrdvnRule.getId()).add(tranDisAmount));
					} else {
						appldRuleIdsDisAmt.put(risplDkPrdvnRule.getId(), tranDisAmount);
					}

					disAppldLinItms.addAll(linItmsToApplyDis);
					splttdOrdLineItms.removeAll(linItmsToApplyDis);
					nOftmsprTran--;
				}

				if (splttdOrdLineItms.size() == 0) {
					break;
				}
			}

			splttdOrdLineItms.addAll(disAppldLinItms);
		}

	}

	/*
	 * Method to calculate discounts on line items if the source is class
	 */
	public void calculateApplyDiscountItemClassificationAmount(int sourceAnyQualifier, int noOfTimes,
			List<OrderTranLineItem> orderTranLineItems, Map<String, Set<String>> classModItms) {

	}

	// method to prorate discount amount on line items
	public void prorateDiscountOnItems(RisplDkPrdvnRule risplDkPrdvnRule, List<OrderTranLineItem> tempItemLines,
			BigDecimal discountAmount, BigDecimal tranTotal) {
		BigDecimal appliedDiscount = BigDecimal.ZERO;
		BigDecimal prmId = new BigDecimal(risplDkPrdvnRule.getId().getIdRuPrdv());
		BigDecimal prmCmpId = new BigDecimal(risplDkPrdvnRule.getId().getIdPrmCmp());
		BigDecimal prmCmpDtl = new BigDecimal(risplDkPrdvnRule.getId().getIdPrmCmpDtl());
		BigDecimal zero = ConfigUtils.getInstance().createBigDecimal(0, format); // GET FORMATTED VALUE
		for (OrderTranLineItem tempItemLine : tempItemLines) {
			BigDecimal lineExtPrc = BigDecimal.ZERO;
			if (tempItemLine.getExtnDscLnItm() != null && tempItemLine.getExtnDscLnItm().compareTo(BigDecimal.ZERO)!=0) {
				lineExtPrc = tempItemLine.getExtnDscLnItm();
			} else if(tempItemLine.getExtnLnItmRtn() != null && tempItemLine.getExtnLnItmRtn().compareTo(BigDecimal.ZERO)!=0){
				lineExtPrc = tempItemLine.getExtnLnItmRtn();
			}
			
			BigDecimal lineDisAmt = lineExtPrc.divide(tranTotal, 4, RoundingMode.DOWN);
			lineDisAmt = lineDisAmt.multiply(discountAmount);
			lineDisAmt = lineDisAmt.setScale(zero.scale(), RoundingMode.DOWN);
			
			appliedDiscount = appliedDiscount.add(lineDisAmt);
			List<OrderTranDiscountItem> discountItems = tempItemLine.getOrdTranDscItms();
			if (discountItems == null) {
				discountItems = new ArrayList<OrderTranDiscountItem>();
			}
			
			OrderTranDiscountItem newDis = new OrderTranDiscountItem();
			newDis.setTyDsc(DKartConstantsIfc.DIS_PROM_AUTO);
			newDis.setPrmType(DKartConstantsIfc.DIS_PROM_AUTO);
			newDis.setDscPer(BigDecimal.ZERO);
			newDis.setPrmDesc(risplDkPrdvnRule.getNmRuPrdv());
			newDis.setPrmId(prmId);
			newDis.setPrmCmpId(prmCmpId);
			newDis.setPrmCmpDtlid(prmCmpDtl);
			newDis.setDscAmt(lineDisAmt);
			
			if(appliedDiscount.compareTo(discountAmount)==1){
				BigDecimal diff = appliedDiscount.subtract(discountAmount);
				appliedDiscount = appliedDiscount.subtract(lineDisAmt);
				lineDisAmt = lineDisAmt.subtract(diff);
				appliedDiscount = appliedDiscount.add(lineDisAmt);
				newDis.setDscAmt(lineDisAmt);
				discountItems.add(newDis);
				tempItemLine.setOrdTranDscItms(discountItems);
				return;
			}else{
				discountItems.add(newDis);
				tempItemLine.setOrdTranDscItms(discountItems);
			}
			
		}
		
		if(appliedDiscount.compareTo(discountAmount)==-1){
			BigDecimal balAmt = discountAmount.subtract(appliedDiscount);
			BigDecimal addtnlDiscEachLine = balAmt.divide(new BigDecimal(tempItemLines.size()),zero.scale(),RoundingMode.UP);
			for (OrderTranLineItem tempItemLine : tempItemLines) {
				
				List<OrderTranDiscountItem> discountItems = tempItemLine.getOrdTranDscItms();
				if (discountItems == null) {
					discountItems = new ArrayList<OrderTranDiscountItem>();
				} 
				
				appliedDiscount = appliedDiscount.add(addtnlDiscEachLine);
				boolean returnVal = false;
				if(appliedDiscount.compareTo(discountAmount)==1){
					BigDecimal diff = appliedDiscount.subtract(discountAmount);
					appliedDiscount = appliedDiscount.subtract(addtnlDiscEachLine);
					addtnlDiscEachLine = addtnlDiscEachLine.subtract(diff);
					appliedDiscount = appliedDiscount.add(addtnlDiscEachLine);
					returnVal = true;
				}
				
				for(OrderTranDiscountItem discLine : discountItems){
					if(discLine.getPrmId().compareTo(prmId)==0 && discLine.getPrmCmpId().compareTo(prmCmpId)==0 && discLine.getPrmCmpDtlid().compareTo(prmCmpDtl)==0){
						discLine.setDscAmt(discLine.getDscAmt().add(addtnlDiscEachLine));
						break;
					}
				}
				
				if(returnVal || appliedDiscount.compareTo(discountAmount)==0){
					return;
				}
				
			}
		}
		
		
	}
	public void prorateDiscountOnItems_Backup(RisplDkPrdvnRule risplDkPrdvnRule, List<OrderTranLineItem> tempItemLines,
			BigDecimal discountAmount, BigDecimal tranTotal) {
		BigDecimal appliedDiscount = BigDecimal.ZERO;
		BigDecimal prmId = new BigDecimal(risplDkPrdvnRule.getId().getIdRuPrdv());
		BigDecimal prmCmpId = new BigDecimal(risplDkPrdvnRule.getId().getIdPrmCmp());
		BigDecimal prmCmpDtl = new BigDecimal(risplDkPrdvnRule.getId().getIdPrmCmpDtl());
		int lineCount = 0;
		for (OrderTranLineItem tempItemLine : tempItemLines) {
			BigDecimal lineExtPrc = BigDecimal.ZERO;
			if (tempItemLine.getExtnDscLnItm() != null && tempItemLine.getExtnDscLnItm().compareTo(BigDecimal.ZERO)!=0) {
				lineExtPrc = tempItemLine.getExtnDscLnItm();
			} else if(tempItemLine.getExtnLnItmRtn() != null && tempItemLine.getExtnLnItmRtn().compareTo(BigDecimal.ZERO)!=0){
				lineExtPrc = tempItemLine.getExtnLnItmRtn();
			}
			BigDecimal lineDisAmt = lineExtPrc.divide(tranTotal, MathContext.DECIMAL32).multiply(discountAmount);
			//lineDisAmt = lineDisAmt.setScale(currencyDecimal, RoundingMode.UP);
			lineDisAmt = ConfigUtils.getInstance().createBigDecimal(lineDisAmt, format);
			appliedDiscount = appliedDiscount.add(lineDisAmt);
			List<OrderTranDiscountItem> discountItems = tempItemLine.getOrdTranDscItms();
			if (discountItems == null) {
				discountItems = new ArrayList<OrderTranDiscountItem>();
			}

			lineCount++;
			if (lineCount == tempItemLines.size() && appliedDiscount.compareTo(discountAmount) == -1) {
				lineDisAmt = lineDisAmt.add(discountAmount.subtract(appliedDiscount));
			} else if (lineCount == tempItemLines.size() && appliedDiscount.compareTo(discountAmount) == 1) {
				lineDisAmt = lineDisAmt.subtract(appliedDiscount.subtract(discountAmount));
			}

			OrderTranDiscountItem newDis = new OrderTranDiscountItem();
			newDis.setTyDsc(DKartConstantsIfc.DIS_PROM_AUTO);
			newDis.setPrmType(DKartConstantsIfc.DIS_PROM_AUTO);
			newDis.setDscPer(BigDecimal.ZERO);
			newDis.setPrmDesc(risplDkPrdvnRule.getNmRuPrdv());
			newDis.setPrmId(prmId);
			newDis.setPrmCmpId(prmCmpId);
			newDis.setPrmCmpDtlid(prmCmpDtl);
			newDis.setDscAmt(lineDisAmt);
			discountItems.add(newDis);

			tempItemLine.setOrdTranDscItms(discountItems);
			tempItemLine.setDRApplied(true);
		}
	}

	// method to get threshold sources from transaction
	public List<OrderTranLineItem> getThrshldSourcesFromTran(RisplDkPrdvnRule risplDkPrdvnRule,
			List<OrderTranLineItem> orderTranLineItems, Map<String, Set<String>> classModItms, boolean remove) {
		List<OrderTranLineItem> validItems = new ArrayList<OrderTranLineItem>();

		boolean satisfied = false;

		List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs = risplDkPrdvnRule.getRisplDkPrdvnRuleElgs();
		List<RisplDkPrdvnTrshldElg> risplDkPrdvnTrshldElgs = risplDkPrdvnRule.getRisplDkPrdvnTrshldElgs();

		// (0) - ALL entries in the source list are required,
		// (1 or more) - ANY <1 or more> entries from the source list are
		// required.
		// (-1) - AnyCombo
		int sourceQualifier = risplDkPrdvnRule.getQuAnSrc().intValue();

		List<OrderTranLineItem> ruleItems = new ArrayList<OrderTranLineItem>();

		if (risplDkPrdvnRule.getCdBasCmpSrc().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_ITM)) {
			for (RisplDkPrdvnRuleElg sourceCrit : risplDkPrdvnRuleElgs) {
				OrderTranLineItem ruleCrit = new OrderTranLineItem();
				ruleCrit.setItemId(sourceCrit.getItmId());
				ruleItems.add(ruleCrit);
			}

			List<BigDecimal> thrshldQty = new ArrayList<BigDecimal>();

			for (RisplDkPrdvnTrshldElg risplDkPrdvnTrshldElg : risplDkPrdvnTrshldElgs) {
				thrshldQty.add(risplDkPrdvnTrshldElg.getThVal());
			}

			Collections.sort(thrshldQty, Collections.reverseOrder());

			if (sourceQualifier == 0) {
				sourceQualifier = ruleItems.size();
			} else if (sourceQualifier == -1) {
				sourceQualifier = 2;
			}

			int srcCount = 0;
			if (orderTranLineItems.size() > 0) {
				for (BigDecimal thQty : thrshldQty) {
					List<OrderTranLineItem> outerPool = new ArrayList<OrderTranLineItem>();
					for (OrderTranLineItem ruleItem : ruleItems) {
						BigDecimal ordLineQty = BigDecimal.ZERO;
						List<OrderTranLineItem> localPool = new ArrayList<OrderTranLineItem>();
						for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
							if (orderTranLineItem.getItemId().equalsIgnoreCase(ruleItem.getItemId())) {
								ordLineQty = ordLineQty.add(orderTranLineItem.getLineQnt());
								localPool.add(orderTranLineItem);
							}

							if (ordLineQty.compareTo(thQty) == 0) {
								outerPool.addAll(localPool);
								break;
							}
						}

						if (ordLineQty.compareTo(thQty) == 0) {
							srcCount++;
						}

						if (srcCount >= sourceQualifier) {
							satisfied = true;
							break;
						}
					}

					if (satisfied) {
						validItems.addAll(outerPool);
						if (remove) {
							orderTranLineItems.removeAll(outerPool);
						}
						break;
					}
				}
			}

		} else if (risplDkPrdvnRule.getCdBasCmpSrc().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_CLS)) {

			for (RisplDkPrdvnRuleElg sourceCrit : risplDkPrdvnRuleElgs) {
				OrderTranLineItem ruleCrit = new OrderTranLineItem();
				ruleCrit.setItemId(sourceCrit.getItmId());
				ruleItems.add(ruleCrit);
			}

			List<BigDecimal> thrshldQty = new ArrayList<BigDecimal>();

			for (RisplDkPrdvnTrshldElg risplDkPrdvnTrshldElg : risplDkPrdvnTrshldElgs) {
				thrshldQty.add(risplDkPrdvnTrshldElg.getThVal());
			}

			Collections.sort(thrshldQty, Collections.reverseOrder());

			if (sourceQualifier == 0) {
				sourceQualifier = ruleItems.size();
			} else if (sourceQualifier == -1) {
				sourceQualifier = 2;
			}

			int srcCount = 0;
			if (orderTranLineItems.size() > 0) {
				for (BigDecimal thQty : thrshldQty) {
					List<OrderTranLineItem> outerPool = new ArrayList<OrderTranLineItem>();
					for (OrderTranLineItem ruleItem : ruleItems) {
						BigDecimal ordLineQty = BigDecimal.ZERO;
						List<OrderTranLineItem> localPool = new ArrayList<OrderTranLineItem>();
						Set<String> classificItems = classModItms.get(ruleItem.getItemId());
						for (String classficItm : classificItems) {
							for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
								if (orderTranLineItem.getItemId().equalsIgnoreCase(classficItm)) {
									ordLineQty = ordLineQty.add(BigDecimal.ONE);
									localPool.add(orderTranLineItem);
								}

								if (ordLineQty.compareTo(thQty) == 0) {
									outerPool.addAll(localPool);
									break;
								}
							}

							if (ordLineQty.compareTo(thQty) == 0) {
								break;
							}
						}
						if (ordLineQty.compareTo(thQty) == 0) {
							srcCount++;
						}

						if (srcCount >= sourceQualifier) {
							satisfied = true;
							break;
						}
					}

					if (satisfied) {
						validItems.addAll(outerPool);
						if (remove) {
							orderTranLineItems.removeAll(outerPool);
						}
						break;
					}
				}
			}

		}

		return validItems;
	}

	// methods to check if all source and target threshold values are available
	// in the transaction - START
	public boolean isSourceTargetThresholdEligible(RisplDkPrdvnRule risplDkPrdvnRule,
			List<OrderTranLineItem> orderTranLineItems, Map<String, Set<String>> classModItms,
			List<OrderTranLineItem> splttdOrdLineItms) {
		boolean satisfied = false;

		List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs = risplDkPrdvnRule.getRisplDkPrdvnRuleElgs();
		List<RisplDkPrdvnTrshldElg> risplDkPrdvnTrshldElgs = risplDkPrdvnRule.getRisplDkPrdvnTrshldElgs();

		// (0) - ALL entries in the source list are required,
		// (1 or more) - ANY <1 or more> entries from the source list are
		// required.
		// (-1) - AnyCombo
		int sourceQualifier = risplDkPrdvnRule.getQuAnSrc().intValue();

		List<OrderTranLineItem> ruleItems = new ArrayList<OrderTranLineItem>();

		if (risplDkPrdvnRule.getCdBasCmpSrc().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_ITM)) {
			for (RisplDkPrdvnRuleElg sourceCrit : risplDkPrdvnRuleElgs) {
				OrderTranLineItem ruleCrit = new OrderTranLineItem();
				ruleCrit.setItemId(sourceCrit.getItmId());
				ruleItems.add(ruleCrit);
			}

			Vector<BigDecimal> thrshldQty = new Vector<BigDecimal>();

			for (RisplDkPrdvnTrshldElg risplDkPrdvnTrshldElg : risplDkPrdvnTrshldElgs) {
				thrshldQty.add(risplDkPrdvnTrshldElg.getThVal());
			}

			if (sourceQualifier == 0) {
				sourceQualifier = ruleItems.size();
			} else if (sourceQualifier == -1) {
				sourceQualifier = 2;
			}

			int srcCount = 0;
			for (OrderTranLineItem ruleItem : ruleItems) {
				BigDecimal ordLineQty = BigDecimal.ZERO;
				for (OrderTranLineItem orderTranLineItem : splttdOrdLineItms) {
					if (orderTranLineItem.getItemId().equalsIgnoreCase(ruleItem.getItemId())) {
						ordLineQty = ordLineQty.add(orderTranLineItem.getLineQnt());
					}

					if (thrshldQty.contains(ordLineQty)) {
						break;
					}
				}

				if (thrshldQty.contains(ordLineQty)) {
					srcCount++;
				}

				if (srcCount >= sourceQualifier) {
					satisfied = true;
					break;
				}
			}

		} else if (risplDkPrdvnRule.getCdBasCmpSrc().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_CLS)) {

			for (RisplDkPrdvnRuleElg sourceCrit : risplDkPrdvnRuleElgs) {
				OrderTranLineItem ruleCrit = new OrderTranLineItem();
				ruleCrit.setItemId(sourceCrit.getItmId());
				ruleItems.add(ruleCrit);
			}

			Vector<BigDecimal> thrshldQty = new Vector<BigDecimal>();

			for (RisplDkPrdvnTrshldElg risplDkPrdvnTrshldElg : risplDkPrdvnTrshldElgs) {
				thrshldQty.add(risplDkPrdvnTrshldElg.getThVal());
			}

			if (sourceQualifier == 0) {
				sourceQualifier = ruleItems.size();
			} else if (sourceQualifier == -1) {
				sourceQualifier = 2;
			}

			int srcCount = 0;
			for (OrderTranLineItem ruleItem : ruleItems) {
				Set<String> classificItems = classModItms.get(ruleItem.getItemId());
				BigDecimal ruleClsQty = BigDecimal.ZERO;
				for (String classiItm : classificItems) {
					for (OrderTranLineItem orderTranLineItem : splttdOrdLineItms) {
						if (orderTranLineItem.getItemId().equalsIgnoreCase(classiItm)) {
							ruleClsQty = ruleClsQty.add(BigDecimal.ONE);
						}

						if (thrshldQty.contains(ruleClsQty)) {
							break;
						}
					}

					if (thrshldQty.contains(ruleClsQty)) {
						srcCount++;
					}

					if (srcCount >= sourceQualifier) {
						break;
					}
				}
				if (srcCount >= sourceQualifier) {
					satisfied = true;
					break;
				}
			}
		}

		return satisfied;
	}
	// methods to check if all source and target threshold values are available
	// in the transaction - END

	// methods to check if all source and target are available in the
	// transaction - START
	public boolean isSourceTargetEligible(RisplDkPrdvnRule risplDkPrdvnRule, List<OrderTranLineItem> orderTranLineItems,
			Map<String, Set<String>> classModItms, List<OrderTranLineItem> splttdOrdLineItms) {
		boolean satisfied = false;
		boolean targetRequired = targetsRequired.get(risplDkPrdvnRule.getDeRuPrdv());
		if (risplDkPrdvnRule.getDeRuPrdv().equalsIgnoreCase(DKartConstantsIfc.BUY_NOR_MORE_OF_XFOR_Z_OFF)) {

			satisfied = isSourceTargetThresholdEligible(risplDkPrdvnRule, orderTranLineItems, classModItms,
					splttdOrdLineItms);

		} else {

			// Sources
			if (risplDkPrdvnRule.getCdBasCmpSrc().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_ITM)) {
				// satisfied =
				// isSourceTargetItemsEligible(risplDkPrdvnRule,orderTranLineItems,true,false);
				satisfied = isSourceTargetItemsEligible(risplDkPrdvnRule, splttdOrdLineItms, true, false);
			} else if (risplDkPrdvnRule.getCdBasCmpSrc().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_CLS)) {
				// satisfied =
				// isSourceTargetClassEligible(risplDkPrdvnRule,orderTranLineItems,true,classModItms);
				satisfied = isSourceTargetClassEligible(risplDkPrdvnRule, splttdOrdLineItms, true, classModItms);
			} else if (risplDkPrdvnRule.getCdBasCmpSrc().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_DPT)) {
				// satisfied =
				// isSourceTargetDepartmentEligible(risplDkPrdvnRule,orderTranLineItems,true,classModItms);
				satisfied = isSourceTargetDepartmentEligible(risplDkPrdvnRule, splttdOrdLineItms, true, classModItms);
			}

			// Targets
			if (satisfied && targetRequired) {
				if (risplDkPrdvnRule.getCdBasCmpTgt().equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_ITM)) {
					// satisfied =
					// isSourceTargetItemsEligible(risplDkPrdvnRule,orderTranLineItems,false,targetRequired);
					satisfied = isSourceTargetItemsEligible(risplDkPrdvnRule, splttdOrdLineItms, false, targetRequired);
				} else if (risplDkPrdvnRule.getCdBasCmpTgt()
						.equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_CLS)) {
					// satisfied =
					// isSourceTargetClassEligible(risplDkPrdvnRule,orderTranLineItems,false,classModItms);
					satisfied = isSourceTargetClassEligible(risplDkPrdvnRule, splttdOrdLineItms, false, classModItms);
				} else if (risplDkPrdvnRule.getCdBasCmpTgt()
						.equalsIgnoreCase(DKartConstantsIfc.PROM_SRC_TRGT_TYPS_DPT)) {
					// satisfied =
					// isSourceTargetDepartmentEligible(risplDkPrdvnRule,orderTranLineItems,false,classModItms);
					satisfied = isSourceTargetDepartmentEligible(risplDkPrdvnRule, splttdOrdLineItms, false,
							classModItms);
				}
			}
		}
		return satisfied;
	}

	public boolean isSourceTargetItemsEligible(RisplDkPrdvnRule risplDkPrdvnRule,
			List<OrderTranLineItem> orderTranLineItems, boolean isSource, boolean targetsMandatory) {

		boolean allwSrcRpt = getBooleanValue(risplDkPrdvnRule.getFlAlwRptSrc());
		// (0) - ALL entries in the source list are required,
		// (1 or more) - ANY <1 or more> entries from the source list are
		// required.
		// (-1) - AnyCombo
		int sourceQualifier = risplDkPrdvnRule.getQuAnSrc().intValue();

		// (Null) - Any/All condition does not apply to target,
		// (0) - ALL entries in the target list are required,
		// (1 or more) - ANY <1 or more>
		int targetQualifier = risplDkPrdvnRule.getQuAnTgt().intValue();

		boolean satisfied = false;
		int srcTrgtQualifier = 0;

		List<OrderTranLineItem> ruleItems = new ArrayList<OrderTranLineItem>();
		if (isSource) {
			List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleEllgs = risplDkPrdvnRule.getRisplDkPrdvnRuleElgs();
			for (RisplDkPrdvnRuleElg sourceCrit : risplDkPrdvnRuleEllgs) {
				OrderTranLineItem ruleCrit = new OrderTranLineItem();
				ruleCrit.setLineQnt(sourceCrit.getQuTh());
				ruleCrit.setExtnLnItmRtn(sourceCrit.getMoTh());
				ruleCrit.setItemId(sourceCrit.getItmId());

				ruleItems.add(ruleCrit);
			}

			srcTrgtQualifier = sourceQualifier;
		} else {
			List<RisplDkPrdvnMmitm> risplDkPrdvnMmitms = risplDkPrdvnRule.getRisplDkPrdvnMmitms();
			for (RisplDkPrdvnMmitm targetCrit : risplDkPrdvnMmitms) {
				OrderTranLineItem ruleCrit = new OrderTranLineItem();
				ruleCrit.setLineQnt(targetCrit.getQuLmMxmh());
				ruleCrit.setItemId(targetCrit.getIdPrmPrd());

				ruleItems.add(ruleCrit);
			}

			srcTrgtQualifier = targetQualifier;
		}
		if (isSource && ruleItems.size() == 1 && ruleItems.get(0).getItemId().equalsIgnoreCase("*")) {
			satisfied = true;
		} else if (srcTrgtQualifier == 0) {
			for (OrderTranLineItem ruleItem : ruleItems) {
				boolean sourceFound = false;
				for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
					if (orderTranLineItem.getItemId().equalsIgnoreCase(ruleItem.getItemId())) {
						if (ruleItem.getExtnLnItmRtn() != null) {
							BigDecimal lineTot = orderTranLineItem.getLineQnt()
									.multiply(orderTranLineItem.getItmPrnPrc());
							if (lineTot.compareTo(ruleItem.getExtnLnItmRtn()) != -1) {
								sourceFound = true;
							}
						} else if (orderTranLineItem.getLineQnt().compareTo(ruleItem.getLineQnt()) != -1) {
							sourceFound = true;
						}
					}

					if (sourceFound) {
						break;
					}
				}

				if (!sourceFound) {
					satisfied = false;
					break;
				}

			}
		} else if (srcTrgtQualifier == -1) {// Any Combo - check if any two
											// source items are there in the
											// transaction
			int count = 0;
			for (OrderTranLineItem ruleItem : ruleItems) {
				boolean sourceFound = false;
				for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
					if (orderTranLineItem.getItemId().equalsIgnoreCase(ruleItem.getItemId())) {
						if (ruleItem.getExtnLnItmRtn() != null) {
							BigDecimal lineTot = orderTranLineItem.getLineQnt()
									.multiply(orderTranLineItem.getItmPrnPrc());
							if (lineTot.compareTo(ruleItem.getExtnLnItmRtn()) != -1) {
								count++;
							}
						} else if (orderTranLineItem.getLineQnt().compareTo(ruleItem.getLineQnt()) != -1) {
							count++;
						}
					}

					if (sourceFound) {
						break;
					}
				}

				if (count == 2 || count > 2) {
					satisfied = true;
					break;
				}
			}

			if (count == 2 || count > 2) {
				satisfied = true;
			}

		} else {// 1 or more

			int count = 0;
			for (OrderTranLineItem ruleItem : ruleItems) {
				BigDecimal ruleQty = ruleItem.getLineQnt();
				if (ruleItems.size() == 1) {
					ruleQty = new BigDecimal(srcTrgtQualifier);
				}
				boolean sourceFound = false;
				for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
					if (orderTranLineItem.getItemId().equalsIgnoreCase(ruleItem.getItemId())) {
						if (ruleItem.getExtnLnItmRtn() != null) {
							BigDecimal lineTot = orderTranLineItem.getLineQnt()
									.multiply(orderTranLineItem.getItmPrnPrc());
							if (lineTot.compareTo(ruleItem.getExtnLnItmRtn()) != -1) {
								count++;
							}
						} else {

							if (ruleItems.size() == 1 && srcTrgtQualifier > 1 && orderTranLineItem.getLineQnt()
									.compareTo(new BigDecimal(srcTrgtQualifier)) != -1) {
								count = srcTrgtQualifier;
								sourceFound = true;
							} else {
								sourceFound = true;
								count++;
							}

						}
					}

					if (sourceFound && !allwSrcRpt) {
						break;
					}

					if (count >= srcTrgtQualifier) {
						satisfied = true;
						break;
					}
				}

				if (count >= srcTrgtQualifier) {
					satisfied = true;
					break;
				}
			}

		}

		// Deal Distribution 1=SourceTarget, 0=Target
		if (!isSource && risplDkPrdvnRule.getFlDlDst().equalsIgnoreCase(DKartConstantsIfc.DEAL_DIST_SRCTRGT)
				&& ruleItems.size() == 0 && !targetsMandatory) {
			satisfied = true;
		}

		return satisfied;
	}

	public boolean isSourceTargetClassEligible(RisplDkPrdvnRule risplDkPrdvnRule,
			List<OrderTranLineItem> orderTranLineItems, boolean isSource, Map<String, Set<String>> classModItms) {

		// (0) - ALL entries in the source list are required,
		// (1 or more) - ANY <1 or more> entries from the source list are
		// required.
		// (-1) - AnyCombo
		int sourceQualifier = risplDkPrdvnRule.getQuAnSrc().intValue();

		// (Null) - Any/All condition does not apply to target,
		// (0) - ALL entries in the target list are required,
		// (1 or more) - ANY <1 or more>
		int targetQualifier = risplDkPrdvnRule.getQuAnTgt().intValue();

		boolean satisfied = false;
		int srcTrgtQualifier = 0;

		List<OrderTranLineItem> ruleClassificIds = new ArrayList<OrderTranLineItem>();
		if (isSource) {
			List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleEllgs = risplDkPrdvnRule.getRisplDkPrdvnRuleElgs();
			for (RisplDkPrdvnRuleElg sourceCrit : risplDkPrdvnRuleEllgs) {
				OrderTranLineItem ruleCrit = new OrderTranLineItem();
				ruleCrit.setLineQnt(sourceCrit.getQuTh());
				ruleCrit.setExtnDscLnItm(sourceCrit.getMoTh());
				ruleCrit.setItemId(sourceCrit.getItmId());

				ruleClassificIds.add(ruleCrit);
			}

			srcTrgtQualifier = sourceQualifier;
		} else {
			List<RisplDkPrdvnMmitm> risplDkPrdvnMmitms = risplDkPrdvnRule.getRisplDkPrdvnMmitms();
			for (RisplDkPrdvnMmitm targetCrit : risplDkPrdvnMmitms) {
				OrderTranLineItem ruleCrit = new OrderTranLineItem();
				ruleCrit.setLineQnt(targetCrit.getQuLmMxmh());
				ruleCrit.setItemId(targetCrit.getIdPrmPrd());

				ruleClassificIds.add(ruleCrit);
			}

			srcTrgtQualifier = targetQualifier;
		}
		if (isSource && ruleClassificIds.size() == 1 && ruleClassificIds.get(0).getItemId().equalsIgnoreCase("*")) {
			satisfied = true;
		} else if (srcTrgtQualifier == 0) {// there should be all items in the
											// transaction which are defined in
											// the rule criteria

			for (OrderTranLineItem ruleClassification : ruleClassificIds) {
				boolean anyFailed = false;
				Set<String> ruleItems = classModItms.get(ruleClassification.getItemId());

				for (String ruleItem : ruleItems) {
					BigDecimal ruleTargetAmount = ruleClassification.getExtnDscLnItm();
					BigDecimal tranLineItmsAmount = BigDecimal.ZERO;

					for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
						if (orderTranLineItem.getItemId().equalsIgnoreCase(ruleItem)) {
							tranLineItmsAmount = tranLineItmsAmount.add(orderTranLineItem.getExtnDscLnItm());
						}
					}

					if (tranLineItmsAmount.compareTo(ruleTargetAmount) == -1) {
						anyFailed = true;
						break;
					}

				}
				if (anyFailed) {
					satisfied = false;
					break;
				} else {
					satisfied = true;
				}

			}

		} else if (srcTrgtQualifier == -1) {// Any Combo - check if any two
											// source items are there in the
											// transaction
			int anyCount = 0;
			for (OrderTranLineItem ruleClassification : ruleClassificIds) {

				Set<String> ruleItems = classModItms.get(ruleClassification.getItemId());

				for (String ruleItem : ruleItems) {
					BigDecimal ruleTargetAmount = ruleClassification.getExtnDscLnItm();
					BigDecimal tranLineItmsAmount = BigDecimal.ZERO;

					for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
						if (orderTranLineItem.getItemId().equalsIgnoreCase(ruleItem)) {
							tranLineItmsAmount = tranLineItmsAmount.add(orderTranLineItem.getExtnDscLnItm());
						}
					}

					if (tranLineItmsAmount.compareTo(ruleTargetAmount) != -1) {
						anyCount++;
					}

					if (anyCount == 2) {
						break;
					}
				}

				if (anyCount == 2) {
					satisfied = true;
					break;
				}
			}

		} else {// 1 or more

			int anyCount = 0;
			for (OrderTranLineItem ruleClassification : ruleClassificIds) {
				Set<String> ruleItems = classModItms.get(ruleClassification.getItemId());
				BigDecimal ruleTargetAmount = ruleClassification.getExtnDscLnItm();

				BigDecimal tranLineItmsAmount = BigDecimal.ZERO;

				for (String ruleItem : ruleItems) {

					for (OrderTranLineItem orderTranLineItem : orderTranLineItems) {
						if (orderTranLineItem.getItemId().equalsIgnoreCase(ruleItem)) {
							tranLineItmsAmount = tranLineItmsAmount.add(orderTranLineItem.getExtnDscLnItm());
						}
					}

					if (tranLineItmsAmount.compareTo(ruleTargetAmount) != -1) {
						anyCount++;
					}

					if (anyCount == srcTrgtQualifier) {
						break;
					}
				}

				if (anyCount == srcTrgtQualifier) {
					satisfied = true;
					break;
				}
			}
		}

		// Deal Distribution 1=SourceTarget, 0=Target
		if (!isSource && risplDkPrdvnRule.getFlDlDst().equalsIgnoreCase(DKartConstantsIfc.DEAL_DIST_SRCTRGT)
				&& ruleClassificIds.size() == 0) {
			satisfied = true;
		}

		return satisfied;
	}

	public boolean isSourceTargetDepartmentEligible(RisplDkPrdvnRule risplDkPrdvnRule,
			List<OrderTranLineItem> orderTranLineItems, boolean isSource, Map<String, Set<String>> deptModItms) {
		boolean satisfied = false;

		return satisfied;
	}

	public boolean getBooleanValue(String val) {
		boolean value = false;
		if (val.equalsIgnoreCase(DKartConstantsIfc.TRUE_IND)) {
			value = true;
		}

		return value;
	}

	// methods to check if all source and target are available in the
	// transaction - END
	
	public BigDecimal getItemTotals(List<OrderTranLineItem> lineLevelPool){
		BigDecimal total = BigDecimal.ZERO;
		for(OrderTranLineItem lineItem : lineLevelPool){
			total = total.add(lineItem.getExtnDscLnItm());
		}
		//total = total.setScale(currencyDecimal);
		total = ConfigUtils.getInstance().createBigDecimal(total, format);
		return total;
	}
	
	public boolean hasSameRuleIds(OrderTranDiscountItem spltdLineDisc,OrderTranDiscountItem grpdLineDisc){
		boolean isSame = false;
		if(spltdLineDisc.getPrmId()!=null && spltdLineDisc.getPrmCmpId()!=null && spltdLineDisc.getPrmCmpDtlid()!=null &&
		   grpdLineDisc.getPrmId()!=null && grpdLineDisc.getPrmCmpId()!=null && grpdLineDisc.getPrmCmpDtlid()!=null &&
		   spltdLineDisc.getPrmId().compareTo(grpdLineDisc.getPrmId())==0 && spltdLineDisc.getPrmCmpId().compareTo(grpdLineDisc.getPrmCmpId())==0 &&
		   spltdLineDisc.getPrmCmpDtlid().compareTo(grpdLineDisc.getPrmCmpDtlid())==0){
			isSame=true;
		}
		return isSame;
	}

}
