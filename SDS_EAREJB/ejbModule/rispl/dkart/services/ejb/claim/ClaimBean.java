package rispl.dkart.services.ejb.claim;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rispl.dk.claim.model.DiffClaimsList;
import com.rispl.roles.access.service.EmployeeRoleAccessDTO;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;

import rispl.db.model.claim.ClaimDetail;
import rispl.db.model.claim.ClaimTranDscItm;
import rispl.db.model.claim.ClaimTranDscItmPK;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.claim.ClaimTranHeaderPK;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.ClaimTranLineItemPK;
import rispl.db.model.claim.ClaimTranLineTax;
import rispl.db.model.claim.ClaimTranSum;
import rispl.db.model.claim.ClaimTranSumPK;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.ConstantsEnum;
import rispl.dkart.claim.received.ClaimReceivedDetail;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.detail.claim.ClaimDetailTable;
import rispl.dkart.services.ejb.LookUpCustomer;
import rispl.dkart.services.ejb.LookUpEmployee;
import rispl.dkart.services.ejb.transaction.OrderTransactionUtility;
import rispl.dkart.services.ejb.transaction.OrderTransactions;
import rispl.dkart.services.entities.customer.CustomerHeader;
import utility.ConfigUtils;

/**
 * @generated DT_ID=none
 */
@Stateless(name = "ClaimBean", mappedName = "claimBean")
@LocalBean
public class ClaimBean implements ClaimRemote {

	private static final Logger LOGGER = LogManager.getLogger(ClaimBean.class);

	SessionContext sessionContext;

	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	private EntityManager em;

	@Inject
	protected LookUpCustomer lookupCustomer;

	@Inject
	protected LookUpEmployee lookupEmployee;

	@EJB(mappedName = "sdsparameterService")
	ParameterConfigurationServiceIfc parameterService;

	@Inject
	OrderTransactions orderTransactions;

	@PostConstruct
	void init() {
		em = emf.createEntityManager();
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		Query query = em.createQuery(jpqlStmt);
		if (firstResult > 0) {
			query = query.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			query = query.setMaxResults(maxResults);
		}

		return query.getResultList();
	}

	/**
	 * @generated DT_ID=none
	 */
	public ClaimTranLineTax persistClaimTranLineTax(ClaimTranLineTax claimTranLineTax) {
		em.persist(claimTranLineTax);
		return claimTranLineTax;
	}

	/**
	 * @generated DT_ID=none
	 */
	public ClaimTranLineTax mergeClaimTranLineTax(ClaimTranLineTax claimTranLineTax) {
		return em.merge(claimTranLineTax);
	}

	/**
	 * @generated DT_ID=none
	 */
	public void removeClaimTranLineTax(ClaimTranLineTax claimTranLineTax) {
		claimTranLineTax = em.find(ClaimTranLineTax.class, claimTranLineTax.getTranLineId());
		em.remove(claimTranLineTax);
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClaimTranLineTax> getClaimTranLineTaxFindAll() {
		return em.createNamedQuery("ClaimTranLineTax.findAll").getResultList();
	}

	/**
	 * @generated DT_ID=none
	 */
	public ClaimTranSum persistClaimTranSum(ClaimTranSum claimTranSum) {
		em.persist(claimTranSum);
		return claimTranSum;
	}

	/**
	 * @generated DT_ID=none
	 */
	public ClaimTranSum mergeClaimTranSum(ClaimTranSum claimTranSum) {
		return em.merge(claimTranSum);
	}

	/**
	 * @generated DT_ID=none
	 */
	public void removeClaimTranSum(ClaimTranSum claimTranSum) {
		ClaimTranSumPK claimTranSumPK = new ClaimTranSumPK();
		claimTranSumPK.setRtStrId(claimTranSum.getId().getRtStrId());
		claimTranSumPK.setOrdWs(claimTranSum.getId().getOrdWs());
		claimTranSumPK.setTrnSeq(claimTranSum.getId().getTrnSeq());
		claimTranSumPK.setDcDyOrd(claimTranSum.getId().getDcDyOrd());
		claimTranSum = em.find(ClaimTranSum.class, claimTranSumPK);
		em.remove(claimTranSum);
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClaimTranSum> getClaimTranSumFindAll() {
		return em.createNamedQuery("ClaimTranSum.findAll").getResultList();
	}

	/**
	 * @generated DT_ID=none
	 */
	public ClaimTranDscItm persistClaimTranDscItm(ClaimTranDscItm claimTranDscItm) {
		em.persist(claimTranDscItm);
		return claimTranDscItm;
	}

	/**
	 * @generated DT_ID=none
	 */
	public ClaimTranDscItm mergeClaimTranDscItm(ClaimTranDscItm claimTranDscItm) {
		return em.merge(claimTranDscItm);
	}

	/**
	 * @generated DT_ID=none
	 */
	public void removeClaimTranDscItm(ClaimTranDscItm claimTranDscItm) {
		ClaimTranDscItmPK claimTranDscItmPK = new ClaimTranDscItmPK();
		claimTranDscItmPK.setRtStrId(claimTranDscItm.getId().getRtStrId());
		claimTranDscItmPK.setOrdWs(claimTranDscItm.getId().getOrdWs());
		claimTranDscItmPK.setTrnSeq(claimTranDscItm.getId().getTrnSeq());
		claimTranDscItmPK.setOrdLnItmSeq(claimTranDscItm.getId().getOrdLnItmSeq());
		claimTranDscItmPK.setDiscSeqNum(claimTranDscItm.getId().getDiscSeqNum());
		claimTranDscItmPK.setDcDyOrd(claimTranDscItm.getId().getDcDyOrd());
		claimTranDscItm = em.find(ClaimTranDscItm.class, claimTranDscItmPK);
		em.remove(claimTranDscItm);
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClaimTranDscItm> getClaimTranDscItmFindAll() {
		return em.createNamedQuery("ClaimTranDscItm.findAll").getResultList();
	}

	/**
	 * @generated DT_ID=none
	 */
	public ClaimTranLineItem persistClaimTranLineItem(ClaimTranLineItem claimTranLineItem) {
		em.persist(claimTranLineItem);
		return claimTranLineItem;
	}

	/**
	 * @generated DT_ID=none
	 */
	public ClaimTranLineItem mergeClaimTranLineItem(ClaimTranLineItem claimTranLineItem) {
		return em.merge(claimTranLineItem);
	}

	/**
	 * @generated DT_ID=none
	 */
	public void removeClaimTranLineItem(ClaimTranLineItem claimTranLineItem) {
		ClaimTranLineItemPK claimTranLineItemPK = new ClaimTranLineItemPK();
		claimTranLineItemPK.setRtStrId(claimTranLineItem.getId().getRtStrId());
		claimTranLineItemPK.setOrdWs(claimTranLineItem.getId().getOrdWs());
		claimTranLineItemPK.setTrnSeq(claimTranLineItem.getId().getTrnSeq());
		claimTranLineItemPK.setOrdLnItmSeq(claimTranLineItem.getId().getOrdLnItmSeq());
		claimTranLineItemPK.setDcDyOrd(claimTranLineItem.getId().getDcDyOrd());
		claimTranLineItem = em.find(ClaimTranLineItem.class, claimTranLineItemPK);
		em.remove(claimTranLineItem);
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClaimTranLineItem> getClaimTranLineItemFindAll() {
		return em.createNamedQuery("ClaimTranLineItem.findAll").getResultList();
	}

	/**
	 * @generated DT_ID=none
	 */
	public ClaimTranHeader persistClaimTranHeader(ClaimTranHeader claimTranHeader) {
		em.persist(claimTranHeader);
		return claimTranHeader;
	}

	/**
	 * @generated DT_ID=none
	 */
	public ClaimTranHeader mergeClaimTranHeader(ClaimTranHeader claimTranHeader) {
		return em.merge(claimTranHeader);
	}

	/**
	 * @generated DT_ID=none
	 */
	public void removeClaimTranHeader(ClaimTranHeader claimTranHeader) {
		ClaimTranHeaderPK claimTranHeaderPK = new ClaimTranHeaderPK();
		claimTranHeaderPK.setRtStrId(claimTranHeader.getId().getRtStrId());
		claimTranHeaderPK.setOrdWs(claimTranHeader.getId().getOrdWs());
		claimTranHeaderPK.setTrnSeq(claimTranHeader.getId().getTrnSeq());
		claimTranHeaderPK.setDcDyOrd(claimTranHeader.getId().getDcDyOrd());
		claimTranHeader = em.find(ClaimTranHeader.class, claimTranHeaderPK);
		em.remove(claimTranHeader);
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClaimTranHeader> getClaimTranHeaderFindAll() {
		return em.createNamedQuery("ClaimTranHeader.findAll").getResultList();
	}

	@Override
	public ClaimTranHeader saveClaimTranHeader(ClaimTranHeader claimHeader) {

		// Assign primary key to ClaimTransHeader
		ClaimTranHeaderPK claimTranHeaderpk = new ClaimTranHeaderPK();
		try {
			claimTranHeaderpk.setDcDyOrd(ConfigUtils.getInstance().getBusinessDate()); // Set Business Day
			claimTranHeaderpk.setOrdWs(ConfigUtils.getInstance().getSDSWorkstationID()); // Set Workstation ID
			//claimTranHeaderpk.setRtStrId(lookUpCustomer.getCustomerStoreID(claimHeader.getClaimTranSum().getOrdIdCt()));
			String storeID = lookupCustomer.getCustomerStoreID(claimHeader.getClaimTranSum().getOrdIdCt());
			LOGGER.warn("customer Store Id at Claims :"+storeID);
			claimTranHeaderpk.setRtStrId(storeID); // Set Store ID
			//claimTranHeaderpk.setTrnSeq(xyz);									// Set Tran Sequence(handled at entity)
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		claimHeader.setId(claimTranHeaderpk);

		/*claimHeader.setScOrd(BigDecimal.ONE); // Claim Registered
*/		claimHeader.setTsCrtRcrd(new Date()); // Claim creation timestamp

		if (!em.getTransaction().isActive())
			em.getTransaction().begin();
		try {
			em.persist(claimHeader);
			LOGGER.info("DATA GOT PERSISTED INTO DB");
			claimHeader.setClaimId(getClaimId(claimHeader)); // Set Claim ID
			LOGGER.info("CLAIM ID HAS BEEN GENERATED, CLAIM ID : "+claimHeader.getClaimId());
			long lineItemsCount = 1;
			for (ClaimTranLineItem claimTranLineItem : claimHeader.getClaimTranLineItems()) {
				// Assign primary key to ClaimTranLineItem List
				ClaimTranLineItemPK claimTranLineItemPK = new ClaimTranLineItemPK();
				claimTranLineItemPK.setDcDyOrd(claimHeader.getId().getDcDyOrd()); // Set Business Day
				claimTranLineItemPK.setOrdWs(claimHeader.getId().getOrdWs()); // Set Workstation ID
				claimTranLineItemPK.setRtStrId(claimHeader.getId().getRtStrId()); //Set Store ID
				claimTranLineItemPK.setTrnSeq(claimHeader.getId().getTrnSeq()); // Set Tran Sequence
				claimTranLineItemPK.setOrdLnItmSeq(lineItemsCount); // Line Item Sequence
				lineItemsCount += 1;
				claimTranLineItem.setId(claimTranLineItemPK);
				int count = 1;
 				if(claimTranLineItem.getClaimTranDscItms() != null){
					for(ClaimTranDscItm claimTranDscItm : claimTranLineItem.getClaimTranDscItms()){
	 					ClaimTranDscItmPK claimTranDscPk = new ClaimTranDscItmPK();
						claimTranDscPk.setDcDyOrd(claimHeader.getId().getDcDyOrd());
						claimTranDscPk.setDiscSeqNum(count);
						claimTranDscPk.setOrdLnItmSeq(lineItemsCount);
						claimTranDscPk.setOrdWs(claimHeader.getId().getOrdWs());
						claimTranDscPk.setRtStrId(claimHeader.getId().getRtStrId());
						claimTranDscPk.setTrnSeq(claimHeader.getId().getTrnSeq());
						
						count++;
						claimTranDscItm.setId(claimTranDscPk);
						claimTranDscItm.setClaimTranLineItem(claimTranLineItem);
					}
					claimTranLineItem.setClaimTranDscItms(claimTranLineItem.getClaimTranDscItms());
 				}
 				claimTranLineItem.setClaimTranHeader(claimHeader);
				em.merge(claimHeader);
				LOGGER.info("DATA IS BEING MERGED FOR UPDATING THE CLAIM DISCOUNTS");
				//TODO Check if we need to persist item level discounts
			}

			// Update ClaimTranSum
			ClaimTranSum claimTranSum = claimHeader.getClaimTranSum();
			// Assign primary key to ClaimTranSum
			ClaimTranSumPK claimTranSumPK = new ClaimTranSumPK();
			claimTranSumPK.setDcDyOrd(claimHeader.getId().getDcDyOrd()); // Set Business Day
			claimTranSumPK.setOrdWs(claimHeader.getId().getOrdWs()); // Set Workstation ID
			claimTranSumPK.setRtStrId(claimHeader.getId().getRtStrId()); //Set Store ID
			claimTranSumPK.setTrnSeq(claimHeader.getId().getTrnSeq()); // Set Tran Sequence
			claimTranSum.setId(claimTranSumPK);

			claimTranSum.setClaimTranHeader(claimHeader);

			claimTranSum.setDkartDscTot(getDiscountTotals(claimHeader.getClaimTranLineItems())); //TODO Calculate Total Discount
			claimTranSum.setDkartSlsTot(getSalesTotals(claimHeader.getClaimTranLineItems()));
			claimTranSum.setDkartNetTot(
					getNetTotals(claimTranSum.getDkartSlsTot(), claimHeader.getClaimTranLineItems(),claimTranSum.getDkartDscTot()));
			claimTranSum.setDkartTaxTot(BigDecimal.ZERO);
			

			em.getTransaction().commit();
			LOGGER.info("TRANSACTION IS COMMITTED SUCCESSFULLY AND DATA GOT FLUSHED INTO DB");
		} catch (RollbackException e) {
			em.getTransaction().rollback();
			LOGGER.info("Trasaction rollbacked due to :"+e.getMessage());
			LOGGER.error(e);
		}
		return claimHeader;
	}
	// @Laxmikanth: to calculate the total discount
	private BigDecimal getDiscountTotals(List<ClaimTranLineItem> claimTranLineItems){
		BigDecimal claimDiscAmount = BigDecimal.ZERO;
		for(ClaimTranLineItem lineItem : claimTranLineItems){
			if((lineItem.getClaimTranDscItms()) != null){
				if((lineItem.getItmTy().compareTo(BigDecimal.ONE)) == 0){
					for(ClaimTranDscItm disc : lineItem.getClaimTranDscItms()){
						claimDiscAmount = claimDiscAmount.add(disc.getDscAmt());
					}
				}
			}
		}
		LOGGER.info("Discount calculations are completed successfully for claimItems, DISCOUNT TOTAL :"+claimDiscAmount);
		return ConfigUtils.getInstance().createBigDecimal(claimDiscAmount, "format.currency");
	}

	private BigDecimal getSalesTotals(List<ClaimTranLineItem> claimTranLineItems) {

		BigDecimal salesTotal = BigDecimal.ZERO;
		for (ClaimTranLineItem claimTranLineItem : claimTranLineItems) {
			if (claimTranLineItem.getItmTy().compareTo(BigDecimal.ONE) == 0) { // COUNT ONLY STOCK ITEMS
				//BigDecimal claimQty = claimTranLineItem.getLineQntRtn();
				BigDecimal claimPrice = BigDecimal.ZERO;
				if (claimTranLineItem.getOvrdPrc() != null){//&& claimTranLineItem.getOvrdPrc().compareTo(null))
					claimPrice = claimTranLineItem.getExtnDscLnItm();
					if(claimTranLineItem.getClaimTranDscItms() != null){
						BigDecimal lineDiscounts = BigDecimal.ZERO;
						for(ClaimTranDscItm dsc : claimTranLineItem.getClaimTranDscItms()){
							lineDiscounts = lineDiscounts.add(dsc.getDscAmt());
						}
						claimPrice = claimPrice.add(lineDiscounts); // ADDING THE DISCOUNTS TO SUBTOTAL
					}
				}else
					claimPrice = claimTranLineItem.getExtnLnItmRtn();
				salesTotal = salesTotal.add(claimPrice);

			}
		}
		return salesTotal;
	}

	private BigDecimal getNetTotals(BigDecimal salesTotals, List<ClaimTranLineItem> claimTranLineItems,BigDecimal discountTotal) {
		BigDecimal netTotal = salesTotals;
		for (ClaimTranLineItem claimTranLineItem : claimTranLineItems) {
			if (claimTranLineItem.getItmTy().compareTo(BigDecimal.ONE) > 1) { // COUNT ONLY SERVICE ITEMS
				BigDecimal claimQty = claimTranLineItem.getLineQntRtn();
				BigDecimal claimPrice = BigDecimal.ZERO;
				if (claimTranLineItem.getOvrdPrc() != null)//&& claimTranLineItem.getOvrdPrc().compareTo(null))

					claimPrice = claimTranLineItem.getOvrdPrc();

				else
					claimPrice = claimTranLineItem.getItmPrnPrc();
				netTotal = netTotal.add(claimQty.multiply(claimPrice));
			}
		}
		netTotal = netTotal.subtract(discountTotal);
		return netTotal;
	}

	@Override
	public List<ClaimTranSum> findClaimWithInvoiceID(String invoiceID) {
		EntityManager em = null;
		em = this.emf.createEntityManager();
		List<?> resultList = em.createNamedQuery("ClaimTranSum.findByInvoiceID").setParameter("invoiceID", invoiceID)
				.getResultList();
		if (resultList != null && resultList.size() > 0)
			return (List<ClaimTranSum>) resultList;
		return null;
	}
	
	public void addClaimRef(String claimId,String claimRefNo,String claimRefdate){
		//EntityManager em = null;
		try {
	//	em = this.emf.createEntityManager();
		em.getTransaction().begin();
		String sqlQry = "INSERT INTO claim_tran_ref_no(CLAIM_ID,CLAIM_REF_NO,CREATION_DATE)VALUES('" + claimId + "', '" + claimRefNo + "','" + claimRefdate + "')";
		Query qe = em.createNativeQuery(sqlQry);
		qe.executeUpdate();
		LOGGER.info("Inserting ref no in claim_tran_ref_no:" + claimRefNo);	
		em.getTransaction().commit();	
		LOGGER.info("commited all transactions......");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	
	public String calculatPerbyCustId(String custId){
		LOGGER.info("calculatPerbyCustId for the customer:"+custId);	
		String checkValue = "false";
		String TotalSale;
		float TotalSalei=0.0f;
		int totalclaim=0;
		float percentClaim=0.0f;
		try {
		
		//em.getTransaction().begin();
		String sqlQry = "Select ret_per,"
				+ "(SELECT sum(CLAIM_Total) From claim_details where claim_date between TRUNC(SYSDATE, 'month') and "
				+ "TRUNC(SYSDATE) and customer_id='" + custId + "' and status in ('2','3','4','5'))ClaimTotal from cust_clm_per where cust_id='" + custId + "'"; 
		
		Query percentCl = em.createNativeQuery(sqlQry);
		@SuppressWarnings("unchecked")
		List<Object[]> percentList = percentCl.getResultList();
		
			
			for (Object[] permission : percentList) {
				String aa;
				aa=permission[0].toString();
				percentClaim=Float.parseFloat(aa);
				String bb;
				if(permission[1]!=null){
				bb=permission[1].toString();
				totalclaim=Integer.parseInt(bb);
				}
			}
		
		try {
		String sqlQry1 = "SELECT SUM(CASE WHEN OTH.ACCEPT_CLAIM_ID is not Null THEN OTLT.EXTN_DSC_LN_ITM * (-1)"
				        + "ELSE OTLT.EXTN_DSC_LN_ITM END) NET_TOTAL FROM SDSQATAR.ORD_TRAN_HEADER OTH "
						+ "INNER JOIN SDSQATAR.ORD_TRAN_SUM OTS ON OTH.RT_STR_ID = OTS.RT_STR_ID "
						+ "AND OTH.ORD_WS = OTS.ORD_WS AND OTH.TRN_SEQ = OTS.TRN_SEQ AND OTH.DC_DY_ORD = OTS.DC_DY_ORD "
						+ "INNER JOIN SDSQATAR.ORD_TRAN_LINE_ITEM OTLT "
						+ "ON OTH.RT_STR_ID = OTLT.RT_STR_ID AND OTH.ORD_WS = OTLT.ORD_WS AND OTH.TRN_SEQ = OTLT.TRN_SEQ AND "
						+ "OTH.DC_DY_ORD = OTLT.DC_DY_ORD where to_date(OTH.DC_DY_ORD,'YYYY-MM-DD') between TRUNC(SYSDATE, 'month') and TRUNC(SYSDATE)"
						+ "and OTH.SC_TRAN ='2'and ord_ty in ('24','26') AND OTH.ID_TRLOG_BTCH IS NOT NULL and OTS.ORD_ID_CT='" + custId + "'"; 
		 
		 TotalSale = String.valueOf(em.createNativeQuery(sqlQry1).getSingleResult());
		 if (!TotalSale.equals("null")&&!TotalSale.equals("")) {

			  TotalSalei=Float.parseFloat(TotalSale.trim());
			}
		 else
		 {
			 TotalSalei=0.0f;
		 }
		 
		}catch(NoResultException nre){
			LOGGER.error("NoResultException");
			TotalSalei=0.0f;
		}
		
		int perOfSalevalue  = (int)(TotalSalei*(percentClaim/100.00));
	
		if (perOfSalevalue==totalclaim ||perOfSalevalue>totalclaim) {
		     checkValue="true";
		}
		
		LOGGER.info("End of calculatPerbyCustId ......");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		return checkValue;
}
	
	
	
	
	

	@Override
	public List<ClaimTranSum> findClaimWithOrderID(String orderID) {
		List<?> resultList = em.createNamedQuery("ClaimTranSum.findByOrderID").setParameter("orderID", orderID)
				.getResultList();
		if (resultList != null && resultList.size() > 0)
			return (List<ClaimTranSum>) resultList;
		return null;
	}

	public String getClaimId(ClaimTranHeader transaction) {
		StringBuffer sb = new StringBuffer();
		try {
			sb.append(transaction.getId().getRtStrId());
			sb.append(transaction.getId().getOrdWs());
			sb.append(transaction.getId().getDcDyOrd().replace("-", ""));
			sb.append(transaction.getId().getTrnSeq());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("error while setting up the order id in getOrderId " + e.getMessage());
		}
		LOGGER.info("setting up new claim Id:" + sb.toString());
		return sb.toString();
	}

	private List<ClaimDetail> getListOfClaimsToApprove(List<Integer> divisionIds) {
		List<ClaimDetail> resultList = em.createNamedQuery("CLAIM_TO_APPROVE_FILTER_BY_DIVISION", ClaimDetail.class)
				.setParameter("divisionIds", divisionIds).getResultList();
		if (resultList != null && resultList.size() > 0)
			return resultList;

		return null;
	}

	private List<ClaimDetail> getListOfClaimsToApprove(String empId) {
		List<ClaimDetail> resultList = em.createNamedQuery("CLAIM_TO_APPROVE_FILTER_BY_LINKED", ClaimDetail.class)
				.setParameter("empId", empId).getResultList();
		if (resultList != null && resultList.size() > 0)
			return resultList;

		return null;
	}

	private List<ClaimDetail> getListOfClaimsToApprove() {
		List<ClaimDetail> resultList = em.createNamedQuery("CLAIM_TO_APPROVE_FILTER_BY_NONE", ClaimDetail.class)
				.getResultList();
		if (resultList != null && resultList.size() > 0)
			return resultList;

		return null;
	}

	@Override
	public List<ClaimDetail> getClaimsToApprove(EmployeeIfc emp) {

		if (emp != null && emp.getRoleAccess() != null) {
			if (emp.getRoleAccess().contains(ConstantsEnum.EMP_SEARCHCRITERIA_DIVISION.getValue())) {
				List<Integer> empDivisions = lookupEmployee.getEmployeeDivisions(emp);
				return getListOfClaimsToApprove(empDivisions);
			} else if (emp.getRoleAccess().contains(ConstantsEnum.EMP_SEARCHCRITERIA_LINKED.getValue())) {
				return getListOfClaimsToApprove(emp.getEmployeeId());
			} else if (emp.getRoleAccess().contains(ConstantsEnum.EMP_SEARCHCRITERIA_ALL.getValue())) {
				return getListOfClaimsToApprove();
			}
		}
		return null;
	}

	/*@Override
	public List<ClaimTranHeader> getClaimsToApprove() {
		List<?> resultList = em.createNamedQuery("ClaimTranHeader.findClaimsToApprove").setParameter("orderStatus", 1)
				.getResultList();
		if (resultList != null && resultList.size() > 0)
			return (List<ClaimTranHeader>) resultList;
		return null;
	}*/

	@Override
	public String getSalesAgentEmailId(String orderId) {
		Query query = em.createNamedQuery("EMP_MAIL_ID").setParameter("1", orderId);
		String emailId = (String) query.getSingleResult();
		return emailId;
	}

	@Override
	public List<String> getDepartmentHeadEmail(String custId) {

		Query query = em.createNamedQuery("DEPTHEAD_MAIL_ID").setParameter("1", custId);
		List<String> emailsList = query.getResultList();
		emailsList.removeIf(Objects::isNull);
		return emailsList;
	}
	
	@Override
	public String getDataEntryOprtrEmailId(String claimId) {
		Query query = em.createNamedQuery("DataEntryOptr_MAIL_ID").setParameter("1", claimId);
		String emailId = (String) query.getSingleResult();
		return emailId;
	}
	public boolean autoAcceptClaims() {
		boolean result = false;
		try {
			String autoAcceptParamtr = parameterService.fetchXMLParameterValues().getEnableAutoAcceptClaim();
			if (autoAcceptParamtr != null && autoAcceptParamtr.equalsIgnoreCase("yes")) {
				EntityManager em = emf.createEntityManager();
				List<ClaimTranHeader> claimTranHeaderList = new ArrayList<>();
				claimTranHeaderList = em.createNamedQuery("CLAIM_TO_ACCEPT_FILTER_BY_NONE", ClaimTranHeader.class)
						.getResultList();

				for (ClaimTranHeader claimTranHeader : claimTranHeaderList) {
					// Update the warehouse received quantity
					claimTranHeader = getUpdatedReceivedQty(claimTranHeader);
					claimTranHeader = getUpdatedTotals(claimTranHeader); // updating the totals based on the warehouse received qty and approve price
					BigDecimal approvedQty = claimTranHeader.getClaimTranLineItems().stream()
							.filter(line -> line.getItmTy().compareTo(BigDecimal.ONE) == 0)
							.map(line -> line.getApprClaimQty() == null ? BigDecimal.ZERO : line.getApprClaimQty())
							.reduce(BigDecimal.ZERO, BigDecimal::add);
					BigDecimal whReceivedQty = claimTranHeader.getClaimTranLineItems().stream()
							.map(line -> line.getWhReceiveQty() == null ? BigDecimal.ZERO : line.getWhReceiveQty())
							.reduce(BigDecimal.ZERO, BigDecimal::add);
					if (approvedQty.compareTo(whReceivedQty) == 0) {
						for (ClaimTranLineItem ctli : claimTranHeader.getClaimTranLineItems()) {
							ctli.setAccptClaimPrice(ctli.getApprClaimPrice());
						}
						claimTranHeader.setScOrd(new BigDecimal(5));
						claimTranHeader.setAcceptType("1");
						orderTransactions.persistClaimTransaction(claimTranHeader);
						LOGGER.info("Data Persisted in ClaimTranHeader for the claim ID " + claimTranHeader.getClaimId());
						orderTransactions.SaveClaimTransaction(claimTranHeader);
						LOGGER.info("Data is populated in OrderTranHeader for the claim ID " + claimTranHeader.getClaimId());
						result = true;
					} else {
						LOGGER.info("Approve Qty and WhReceived Qty is not equal for the claim ID " + claimTranHeader.getClaimId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e.getCause());
		}
		return result;
	}
	// sharanya end

	@Override
	public ClaimTranHeader getUpdatedReceivedQty(ClaimTranHeader claimTranHeader) {
		LOGGER.info("For Claim Id: {}, updating warehouse received qty and disposition codes for each lineitem...", 
				claimTranHeader != null ? claimTranHeader.getClaimId() : "null");
		ClaimTranHeader claimTranHeaderClone = SerializationUtils.clone(claimTranHeader);
		try {
			List<ClaimTranLineItem> processedClaimTranLineItems = new ArrayList<ClaimTranLineItem>();
			
			//Filter unprocessed lines
			List<ClaimTranLineItem> unprocessedClaimTranLineItems = claimTranHeaderClone.getClaimTranLineItems();
					//.stream().filter(lineItem -> lineItem.getWhReceiveQty() == null).collect(Collectors.toList());
			
			// if nothing to process then return
			if (unprocessedClaimTranLineItems.size() == 0)
				return claimTranHeader;
			
			LOGGER.debug("Unprocessed Claim Line Items:\n\t{}", unprocessedClaimTranLineItems);
	
			String claimId = claimTranHeaderClone.getClaimId();
			List<ClaimReceivedDetail> claimReceivedDetails = em
					.createNamedQuery("CLAIM_RECEIVED_DETAILS_BY_CLAIMID", ClaimReceivedDetail.class)
					.setParameter("claimId", claimId).getResultList();
	
			//Filter only POST records
			//claimReceivedDetails = claimReceivedDetails.stream()
			//		.filter(details -> details.getPostFlag().equalsIgnoreCase("POST")).collect(Collectors.toList());
			LOGGER.debug("For Claim Id: {}, Items Received Details:\n\t{}", claimId, claimReceivedDetails);
			
			if (claimReceivedDetails != null && claimReceivedDetails.size() > 0) {
				// Calculate warehouse received qty with dispositions
				Map<String, Map<String, Long>> itemDispAndQtyMap = claimReceivedDetails.stream()
						.collect(Collectors.groupingBy((ClaimReceivedDetail det) -> det.getId().getItemId(),
								Collectors.toMap((ClaimReceivedDetail det) -> det.getId().getDispCode(),
										(ClaimReceivedDetail det) -> det.getWhRcvQty().longValue())));
				LOGGER.info("For Claim Id: {}, Items recevied at warehouse along with disposition "
						+ "(Item, (Disposition,Qty)):\n\t{}", claimId, itemDispAndQtyMap);
				// Calculate warehouse received qty
				Map<String, Long> recItemsQty = claimReceivedDetails.stream()
						.collect(Collectors.toMap((ClaimReceivedDetail det) -> det.getId().getItemId(),
								(ClaimReceivedDetail det) -> det.getWhRcvQty().longValue(), 
								(det1, det2) -> det1 + det2));
				LOGGER.debug("For Claim Id: {}, Items recevied at warehouse (Item, Qty):\n\t", claimId, recItemsQty);
				
				LOGGER.info("For Claim Id: {}, Applying disposition codes to claim line items...", claimId);
				unprocessedClaimTranLineItems.forEach((ClaimTranLineItem claimLine) -> {
					String itemId = claimLine.getItemId();
					Map<String, Long> dispAndQty = itemDispAndQtyMap.getOrDefault(itemId, new HashMap<String, Long>());
					if(dispAndQty.size() == 0) { 
						// No disposition codes found for the item 
						LOGGER.info("For Claim Id: {}, Item Id: {} has no disposition codes", claimId, itemId);
						claimLine.setWhReceiveQty(BigDecimal.ZERO);
						processedClaimTranLineItems.add(claimLine);
					}
					else if (dispAndQty.size() == 1) { 
						// One disposition codes found for the item //TODO move this to separate function for reusability
						LOGGER.info("For Claim Id: {}, Item Id: {} has single disposition code: {}", claimId, itemId, dispAndQty);
						ClaimTranLineItem claimTranLineItem = SerializationUtils.clone(claimLine);
						Long recQty = dispAndQty.values().iterator().next();
						Long approvedQty = claimTranLineItem.getApprClaimQty().longValue();
						String dispCode = dispAndQty.keySet().iterator().next();
						if (approvedQty >= recQty) {
							claimTranLineItem.setWhReceiveQty(new BigDecimal(recQty));
							claimTranLineItem.setDispostionCode(dispCode);
							dispAndQty.put(dispCode, (long) 0);
						} else if (approvedQty < recQty) {
							claimTranLineItem.setWhReceiveQty(claimTranLineItem.getApprClaimQty());
							claimTranLineItem.setDispostionCode(dispCode);
							dispAndQty.put(dispCode, recQty - approvedQty);
						}
						processedClaimTranLineItems.add(claimTranLineItem);
					}
					else if(dispAndQty.size() > 1){ 
						// Multiple disposition codes found for the item
						// Split claim tran line items based on number of disposition types
						LOGGER.info("For Claim Id: {}, Item Id: {} has multiple disposition codes: {}", claimId, itemId,
								dispAndQty);
						LOGGER.info("For Claim Id: {}, Splitting Item Id: {} into {} line item groups", claimId, itemId,
								dispAndQty.size());
						
						List<ClaimTranLineItem> splitClaimTranLineItems = splitClaimLineAndApplyDisposition(claimLine, dispAndQty);
						processedClaimTranLineItems.addAll(splitClaimTranLineItems);
					}
				});
				LOGGER.info("For Claim Id: {}, after processing - \n\t"
								+ "Claim Recevied at warehouse along with disposition  (Item, (Disposition,Qty)):\n\t{}",
						claimId, itemDispAndQtyMap);
				
				// Reset line item sequence
				ListIterator<ClaimTranLineItem> splitClaimLinesIter = processedClaimTranLineItems.listIterator();
				while (splitClaimLinesIter.hasNext()) {
					splitClaimLinesIter.next().getId().setOrdLnItmSeq(splitClaimLinesIter.nextIndex());
				}
				claimTranHeaderClone.setClaimTranLineItems(processedClaimTranLineItems);
			}
		}
		catch (Exception e) {
			LOGGER.error("An error occured during processing of claim lines and disposition codes\n\t"
					+ "Claim Id: {}, Error: {}", claimTranHeader.getClaimId(), e.getMessage());
			LOGGER.error(e.getCause());
		}
		return claimTranHeaderClone;
	}

	private List<ClaimTranLineItem> splitClaimLineAndApplyDisposition(ClaimTranLineItem claimTranLineItem,
			Map<String, Long> dispAndQtyMap) {
		int noOfDispCodes = dispAndQtyMap.size();
		// Long receivedQty = dispAndQtyMap.values().stream().mapToLong(q->q).sum();
		if (noOfDispCodes > 1) {
			Iterator<Entry<String, Long>> dispAndQtyIterator = dispAndQtyMap.entrySet().iterator();

			List<ClaimTranLineItem> splitClaimLines = new ArrayList<ClaimTranLineItem>();

			IntStream.range(0, noOfDispCodes).forEach(i -> { 
				// TODO move this to separate function for reusability
				ClaimTranLineItem claimLine = SerializationUtils.clone(claimTranLineItem);
				Entry<String, Long> dispAndQty = dispAndQtyIterator.next();
				Long recQty = dispAndQty.getValue();
				Long approvedQty = claimLine.getApprClaimQty().longValue();
				if (approvedQty >= recQty) {
					claimLine.setWhReceiveQty(new BigDecimal(recQty));
					claimLine.setDispostionCode(dispAndQty.getKey());
					dispAndQty.setValue((long) 0);
				} else if (approvedQty < recQty) {
					claimTranLineItem.setWhReceiveQty(claimTranLineItem.getApprClaimQty());
					claimTranLineItem.setDispostionCode(dispAndQty.getKey());
					dispAndQty.setValue(recQty - approvedQty);
				}
				// Apart from first line, make other split lines as logical children
				if (i != 0) {
					claimLine.setLineQnt(null);
					claimLine.setLineQntRtn(null);
					claimLine.setApprClaimQty(null);
				}
				splitClaimLines.add(claimLine);
			});
			return splitClaimLines;
		}
		return Arrays.asList(claimTranLineItem);
	}
	
	//code to show 2 diffrnt list(needToBeAccepted , autoAccepted)  @Sharanya
	public DiffClaimsList getAcceptClaimDetails(EmployeeIfc emp) {
		OrderTransactionUtility util = new OrderTransactionUtility();
		DiffClaimsList claimDetailTableList = new DiffClaimsList();
		Vector<ClaimDetailTable> needToBeAccepted = new Vector<>();
		Vector<ClaimDetailTable> autoAccepted = new Vector<>();

		EntityManager em = emf.createEntityManager();

		List<ClaimTranHeader> claimTranHeaderList = new ArrayList<>();

		if (emp != null && emp.getRoleAccess() != null) {

			if (emp.getRoleAccess().contains(ConstantsEnum.EMP_SEARCHCRITERIA_DIVISION.getValue())) {
				List<Integer> empDivisions = lookupEmployee.getEmployeeDivisions(emp);
				claimTranHeaderList = em.createNamedQuery("CLAIM_TO_ACCEPT_FILTER_BY_DIVISION", ClaimTranHeader.class)
						.setParameter("divisionIds", empDivisions).getResultList();
			} else if (emp.getRoleAccess().contains(ConstantsEnum.EMP_SEARCHCRITERIA_LINKED.getValue())) {
				claimTranHeaderList = em.createNamedQuery("CLAIM_TO_ACCEPT_FILTER_BY_LINKED", ClaimTranHeader.class)
						.setParameter("empId", emp.getEmployeeId()).getResultList();
			} else if (emp.getRoleAccess().contains(ConstantsEnum.EMP_SEARCHCRITERIA_ALL.getValue())) {
				claimTranHeaderList = em.createNamedQuery("CLAIM_TO_ACCEPT_FILTER_BY_NONE", ClaimTranHeader.class)
						.getResultList();
			}

		}
		for (ClaimTranHeader claimTranHeade : claimTranHeaderList) {

			ClaimDetailTable cdt1 = new ClaimDetailTable();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				cdt1.setClaimDate(dateFormat.parse(claimTranHeade.getId().getDcDyOrd()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cdt1.setReceivedDate(claimTranHeade.getWhReceivedDate());
			cdt1.setClaimTotal(claimTranHeade.getClaimTranSum().getDkartNetTot());
			cdt1.setClaimId(claimTranHeade.getClaimId());
			Map<String, String> returnReasonCodesMap = orderTransactions.getAllReasonCodes().get("Returns");
			cdt1.setReasonCode(returnReasonCodesMap.get(claimTranHeade.getRcRtnMr()));
			
				//cdt1.setSalesAgent(util.getEmployee(claimTranHeade.getEmId()));
				
			
			try {
				String customerId = claimTranHeade.getClaimTranSum().getOrdIdCt();
				cdt1.setCustomerId(customerId);
				Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);

				CustomerHeader header = (CustomerHeader) customerQ.getSingleResult();
				cdt1.setCustomerName(header.getCtNm());

			} catch (Exception e) {
				LOGGER.error(e);
			}
			needToBeAccepted.add(cdt1);
		}

		List<ClaimTranHeader> claimTranHeaderList2 = new ArrayList<>();
		if (emp != null && emp.getRoleAccess() != null) {

			if (emp.getRoleAccess().contains(ConstantsEnum.EMP_SEARCHCRITERIA_DIVISION.getValue())) {
				List<Integer> empDivisions = lookupEmployee.getEmployeeDivisions(emp);
				claimTranHeaderList2 = em.createNamedQuery("ACCEPTED_CLAIM_FILTER_BY_DIVISION", ClaimTranHeader.class)
						.setParameter("divisionIds", empDivisions).getResultList();
			} else if (emp.getRoleAccess().contains(ConstantsEnum.EMP_SEARCHCRITERIA_LINKED.getValue())) {
				claimTranHeaderList2 = em.createNamedQuery("ACCEPTED_CLAIM_FILTER_BY_LINKED", ClaimTranHeader.class)
						.setParameter("empId", emp.getEmployeeId()).getResultList();
			} else if (emp.getRoleAccess().contains(ConstantsEnum.EMP_SEARCHCRITERIA_ALL.getValue())) {
				claimTranHeaderList2 = em.createNamedQuery("ACCEPTED_CLAIM_FILTER_BY_NONE", ClaimTranHeader.class)
						.getResultList();
			}

		}
		for (ClaimTranHeader claimTranHeader : claimTranHeaderList2) {

			boolean isAuto = true;
			ClaimDetailTable cdt2 = new ClaimDetailTable();
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				cdt2.setClaimDate(dateFormat2.parse(claimTranHeader.getId().getDcDyOrd()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cdt2.setReceivedDate(claimTranHeader.getWhReceivedDate());
			cdt2.setClaimTotal(claimTranHeader.getClaimTranSum().getDkartNetTot());
			cdt2.setClaimId(claimTranHeader.getClaimId());
			Map<String, String> returnReasonCodesMap2 = orderTransactions.getAllReasonCodes().get("Returns");
			cdt2.setReasonCode(returnReasonCodesMap2.get(claimTranHeader.getRcRtnMr()));
			
				//cdt2.setSalesAgent(util.getEmployee(claimTranHeader.getEmId()));
			
			
			try {
				String customerId = claimTranHeader.getClaimTranSum().getOrdIdCt();
				cdt2.setCustomerId(customerId);
				Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
						.setParameter("customerId", customerId);

				CustomerHeader header = (CustomerHeader) customerQ.getSingleResult();
				cdt2.setCustomerName(header.getCtNm());

			} catch (Exception e) {
				LOGGER.error(e);
			}

			if (isAuto) {
				autoAccepted.add(cdt2);
			}
		}
		claimDetailTableList.setNeedToBeAccepted(needToBeAccepted);
		claimDetailTableList.setAutoAccepted(autoAccepted);

		return claimDetailTableList;

	}
    //sharanya End
	
	
	/**
	 * To update the totals after warehouse received the items
	 * 
	 * @param claimTranHeader
	 * @return "claimHeader" 
	 */
	@Override
	public ClaimTranHeader getUpdatedTotals(ClaimTranHeader claimHeader) {
		
		LOGGER.info("Executing the getUpdateTotals()");
		// TODO Auto-generated method stub
		ClaimTranHeader claimTranHeader = claimHeader;
		ClaimTranSum claimTranSum = claimTranHeader.getClaimTranSum();
		List<ClaimTranLineItem> claimTranLineItemlist = null;
		BigDecimal extrtnamt = BigDecimal.ZERO;
		BigDecimal extdscamt = BigDecimal.ZERO;
		BigDecimal dkartDscTot = BigDecimal.ZERO;
			claimTranLineItemlist = claimTranHeader.getClaimTranLineItems();
			for(ClaimTranLineItem lineItem : claimTranLineItemlist){
				//if(lineItem.getApprClaimQty() != null && lineItem.getApprClaimQty().compareTo(BigDecimal.ZERO) != 0){
				if(lineItem.getWhReceiveQty() !=null && lineItem.getWhReceiveQty().compareTo(BigDecimal.ZERO) != 0){
				BigDecimal dkartDsc = BigDecimal.ZERO;
				BigDecimal extrn = BigDecimal.ZERO;
				BigDecimal exdsc = BigDecimal.ZERO;
				extrn = lineItem.getWhReceiveQty().multiply(lineItem.getApprClaimPrice());
				exdsc = extrn;
				if(lineItem.getClaimTranDscItms()!=null && !lineItem.getClaimTranDscItms().isEmpty()){
					if(lineItem.getClaimTranDscItms().size() == 1){
						lineItem.getClaimTranDscItms().get(0).setDscAmt(lineItem.getClaimTranDscItms().get(0).getUnitDscAmt().multiply(lineItem.getWhReceiveQty()));
						dkartDsc= dkartDsc.add(lineItem.getClaimTranDscItms().get(0).getUnitDscAmt().multiply(lineItem.getWhReceiveQty()));
					}
					if(lineItem.getClaimTranDscItms().size() > 1)
					{
						for(ClaimTranDscItm dsc : lineItem.getClaimTranDscItms()){
							if(dsc.getTyDsc().compareTo(BigDecimal.ZERO) == 0){
								LOGGER.info("Setting the Line Level updated discount");
								dsc.setDscAmt(dsc.getUnitDscAmt().multiply(lineItem.getWhReceiveQty()));
								dkartDsc = dkartDsc.add(dsc.getUnitDscAmt().multiply(lineItem.getWhReceiveQty()));
							}
							else if (dsc.getTyDsc().compareTo(BigDecimal.ONE) == 0) {
								dsc.setDscAmt(dsc.getUnitDscAmt().multiply(lineItem.getWhReceiveQty()));
								dkartDsc = dkartDsc.add(dsc.getUnitDscAmt().multiply(lineItem.getWhReceiveQty()));
							}
						}
					}
				
					dkartDscTot = dkartDscTot.add(dkartDsc);
					extrn = extrn.add(dkartDsc);
				}
				extrtnamt = extrtnamt.add(extrn);
				extdscamt = extdscamt.add(exdsc);
				LOGGER.info("Setting the updated totals in ClaimTranLineItem");
				lineItem.setExtnLnItmRtn(extrn);
				lineItem.setExtnDscLnItm(exdsc);
			}
				else
				{
					if(lineItem.getClaimTranDscItms()!=null && !lineItem.getClaimTranDscItms().isEmpty()){
						if(lineItem.getClaimTranDscItms().size() == 1){
							lineItem.getClaimTranDscItms().get(0).setDscAmt(lineItem.getClaimTranDscItms().get(0).getUnitDscAmt().multiply(lineItem.getWhReceiveQty()));
						}
						if(lineItem.getClaimTranDscItms().size() > 1)
						{
							for(ClaimTranDscItm dsc : lineItem.getClaimTranDscItms()){
								if(dsc.getTyDsc().compareTo(BigDecimal.ZERO) == 0){
									LOGGER.info("Setting the Line Level updated discount");
									dsc.setDscAmt(dsc.getUnitDscAmt().multiply(lineItem.getWhReceiveQty()));
								}
								else if (dsc.getTyDsc().compareTo(BigDecimal.ONE) == 0) {
									dsc.setDscAmt(dsc.getUnitDscAmt().multiply(lineItem.getWhReceiveQty()));
								}
							}
						}
					}
					LOGGER.info("Setting the updated totals in ClaimTranLineItem");
					lineItem.setExtnLnItmRtn(BigDecimal.ZERO);
					lineItem.setExtnDscLnItm(BigDecimal.ZERO);
				}
			}
			LOGGER.info("Setting the updated totals in ClaimTranSum");
			claimTranSum.setDkartSlsTot(extrtnamt);
			claimTranSum.setDkartNetTot(extdscamt);
			claimTranSum.setDkartDscTot(extrtnamt.subtract(extdscamt));
			LOGGER.info("Executing the getUpdateTotals() method is completed successfully");
		return claimTranHeader;
	}//end of getUpdateTotals() method.
	
	/*
	 * @LAXMIKANTH
	 * method to split the lines, if unit discount amount of total quantity and discount amount doesn't match.
	 */
	public List<ClaimTranLineItem> splitLines(ClaimTranLineItem claimTranLineItem,BigDecimal qty){
		LOGGER.info("Started Executing the splitLines method");
		Map<BigDecimal, Map<BigDecimal, BigDecimal>> map = new HashMap<>();
		BigDecimal qtyTot = qty;
		BigDecimal manualDiscountTot = null;
		BigDecimal tranDiscountTot = null;
		try{
			while(qtyTot.intValue() != 0){
				if(claimTranLineItem.getClaimTranDscItms() != null){
					for(ClaimTranDscItm dscItm : claimTranLineItem.getClaimTranDscItms()){
						if(dscItm.getTyDsc().compareTo(BigDecimal.ZERO) == 0){
							if(manualDiscountTot == null)manualDiscountTot = dscItm.getDscAmt();
							if(qtyTot.compareTo(BigDecimal.ONE) == 0){
								map = put(map,BigDecimal.ONE, BigDecimal.ZERO,manualDiscountTot);
								manualDiscountTot = BigDecimal.ZERO;
							}else{
								map = put(map,qty.subtract(BigDecimal.ONE), BigDecimal.ZERO, dscItm.getUnitDscAmt());
								manualDiscountTot = manualDiscountTot.subtract(dscItm.getUnitDscAmt());
							}
						}
						if(dscItm.getTyDsc().compareTo(BigDecimal.ONE) == 0){
							if(tranDiscountTot == null)tranDiscountTot = dscItm.getDscAmt();
							if(qtyTot.compareTo(BigDecimal.ONE) == 0){
								map = put(map,BigDecimal.ONE, BigDecimal.ONE, tranDiscountTot);
								tranDiscountTot = BigDecimal.ZERO;
							}else{
								map = put(map,qty.subtract(BigDecimal.ONE), BigDecimal.ONE, dscItm.getUnitDscAmt());
								tranDiscountTot = tranDiscountTot.subtract(dscItm.getUnitDscAmt());
							}
						}
					}
				}
				qtyTot = qtyTot.subtract(BigDecimal.ONE);
			}
			List<ClaimTranLineItem> splittedClmItems = new ArrayList<>();
			Iterator<Entry<BigDecimal, Map<BigDecimal, BigDecimal>>> it = map.entrySet().iterator();
			while(it.hasNext()){
				ClaimTranLineItem clmItem = SerializationUtils.clone(claimTranLineItem);
				Entry<BigDecimal, Map<BigDecimal, BigDecimal>> entry = it.next();
				BigDecimal quntity = entry.getKey();
				Map<BigDecimal, BigDecimal> discTempMap = entry.getValue();
				
				clmItem.setLineQnt(quntity);
				clmItem.setLineQntRtn(quntity);
				BigDecimal price = clmItem.getOvrdPrc() != null ? clmItem.getOvrdPrc() : clmItem.getItmPrnPrc();
				clmItem.setExtnLnItmRtn(price.multiply(clmItem.getLineQnt()));
				BigDecimal discAmount = BigDecimal.ZERO;
				for(ClaimTranDscItm dscItem : clmItem.getClaimTranDscItms()){
					if(dscItem.getTyDsc().compareTo(BigDecimal.ZERO) == 0){
						dscItem.setUnitDscAmt(discTempMap.get(BigDecimal.ZERO));
						dscItem.setDscAmt(dscItem.getUnitDscAmt().multiply(quntity));
						discAmount = discAmount.add(dscItem.getDscAmt());
					}
					if(dscItem.getTyDsc().compareTo(BigDecimal.ONE) == 0){
						dscItem.setUnitDscAmt(discTempMap.get(BigDecimal.ONE));
						dscItem.setDscAmt(dscItem.getUnitDscAmt().multiply(quntity));
						discAmount = discAmount.add(dscItem.getDscAmt());
					}
				}
				clmItem.setExtnDscLnItm(clmItem.getExtnLnItmRtn().subtract(discAmount));
				splittedClmItems.add(clmItem);
			}
			LOGGER.info("Line Items has been splitted successfully");
			return splittedClmItems;
			
		}catch(Exception e){
			LOGGER.error("Error ouccured while split of the claim line items with MESSAGE : {} ", e.getMessage());
		}
		return null;
	}
	// utility method
	private Map<BigDecimal, Map<BigDecimal, BigDecimal>> put(Map<BigDecimal, Map<BigDecimal, BigDecimal>> map ,BigDecimal qty ,BigDecimal tyDsc,BigDecimal disc){
		if(!map.containsKey(qty)){
			Map<BigDecimal, BigDecimal> tempMap1 = new HashMap<>();
			tempMap1.put(tyDsc, disc);
			map.put(qty, tempMap1);
		}else{
			Map<BigDecimal, BigDecimal> m = map.get(qty);
			m.put(tyDsc, disc);
			map.put(qty, m);
		}
		return map;
	}
	// end laxmikanth
}
