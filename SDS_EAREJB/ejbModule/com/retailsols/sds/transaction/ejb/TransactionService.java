package com.retailsols.sds.transaction.ejb;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dk.customer.Customer;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.ejb.LookUpCustomer;
import rispl.dkart.services.ejb.transaction.OrderTransactions;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranSum;

/**
 * Session Bean implementation class TransactionService
 */
@Stateless(mappedName = "transactionservice")
@LocalBean
public class TransactionService implements TransactionServiceIfc, TransactionConstantsIfc{

	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	
	@Inject
	protected LookUpCustomer lookupCustomer;
	
	private static final Logger LOGGER = LogManager.getLogger(TransactionService.class);
	
	protected EntityManager getEntityManager()
	{
		return emf.createEntityManager();
	}
	
	@Override
	public String getStoreId(String custID) {
		// get the customer segment 
		EntityManager em = getEntityManager();
		Query query = em.createNativeQuery("SELECT STORE FROM RISPL_DK_CUST_SEGMENT SEG, RISPL_DK_SEG_STORE ST WHERE SEG.SEGMENT_ID = ST.SEGMENT_ID AND SEG.DIVISION_ID = ST.DIVISION_ID AND SEG.CUST_ID = ? ");
		query.setParameter(1, custID);		
		String storeID = (String) query.getSingleResult();
		return storeID;
	}

	@Override
	public OrderTranHeader getNewTransaction(String custId, String tranType,String empId) 
	{
		OrderTranHeader header=null;
		try 
		{
			String storeId = getStoreId(custId);
			if(storeId!=null&&!storeId.equalsIgnoreCase("null"))
			{
				String regID = utility.ConfigUtils.getInstance().getSDSWorkstationID();
				long tranSeq = getTranSeq(storeId);
				String bDate= new SimpleDateFormat(BUSINESS_DATE_FORMAT).format(new java.util.Date());
				
				String tillId = "0";
				String orderId = null;
				if(tranType.equalsIgnoreCase(ORD_INIT))
				{
				 orderId  = getOrderId(storeId,regID,tranSeq,bDate);
				}
				
				// need to create a new Transaction header and persist with status in-progress
				header = SDSEntityBuilder.getOrderTranHeader(storeId,regID,bDate,tranSeq,empId,tillId,tranType);
				OrderTranSum tranSum = SDSEntityBuilder.getOrderTranSum(header,orderId);
				header.setOrdTranSum(tranSum);
				header.setCustomer(getCustomer(custId));
				persistOrderTransaction(header);	
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
			LOGGER.error("Could not initiate order transaction ",e);
		}
		return header;
	}

	private CustomerIfc getCustomer(String customerId)
	{
		EntityManager em = getEntityManager();
		Query customerQ = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
				.setParameter("customerId", customerId);
		CustomerHeader customerHeader = (CustomerHeader) customerQ.getSingleResult();
		CustomerIfc customer = new Customer();
		customer.setCustomerHeader(customerHeader);
		customer.setCustomerLimits(lookupCustomer.getCustomerlimits(customerId));
		customer.setCustomerSegmentID(lookupCustomer.getCustomerSegmentID(customerId));
		return customer;
	}
	


	private void persistOrderTransaction(OrderTranHeader header)
	{
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		try
		{
		 header.setTsMdfRcrd(new Date());
		 em.merge(header);
		 em.getTransaction().commit();
		}catch(Exception e)
		{
		  em.persist(header);
		  em.getTransaction().commit();
		}
		
	}

	@Override
	public long getTranSeq(String storeId) {
		BigDecimal seq = null;
		try {
			EntityManager em = getEntityManager();
			Query query = em.createNativeQuery("SELECT TRAN_" + storeId + "_SEQ.NEXTVAL FROM DUAL");
			seq = (BigDecimal) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seq.longValue();
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
	
	public String getOrderId(String storeId,String regID, long tranSeq,String bDate)
	{
		StringBuffer sb = new StringBuffer();
		LOGGER.info("setting up new order Id......");
		try {
			sb.append(storeId);
			sb.append(regID);
			sb.append(bDate.replace("-", ""));
			sb.append(tranSeq);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("error while setting up the order id in getOrderId " + e.getMessage());
		}
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		//new TransactionService().getNewTransaction("1270", HOUSE_PAYMENT, "41");
		new TransactionService().getTranSeq("12345");
	}
	
	public boolean saveTransaction(OrderTranHeader header){
		boolean flag = false;
		try {
			flag = true;
			persistOrderTransaction(header);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Could not persit transaction : "+header.getId().toString(),e);
			flag = false;
		}
		return flag;
	}
}
