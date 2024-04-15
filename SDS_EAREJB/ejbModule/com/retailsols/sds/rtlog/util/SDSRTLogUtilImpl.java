package com.retailsols.sds.rtlog.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.entities.transaction.OrderTranHeader;

@Stateless(mappedName = "rtlogUtil")
@LocalBean
public class SDSRTLogUtilImpl implements SDSRTLogUtilRemote{

	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	private static final Logger LOGGER = LogManager.getLogger(SDSRTLogUtilImpl.class);
	@Override
	public void cancelInvalidOrderTransaction() {
		try {
			
			EntityManager em = getEntityManager();
			// get the trx with sc_ord = null and sc_trn = null and ord_ty = 23 
			if(em != null){
				Query query = em.createNamedQuery("GET_INVALID_TRX",OrderTranHeader.class);
				List<OrderTranHeader> trx = query.getResultList();
				Iterator<OrderTranHeader> tranItr = trx.iterator();
				while(tranItr.hasNext()){
					OrderTranHeader header = (OrderTranHeader)tranItr.next();
					boolean isNotValid = isTransactionExpired(header);
					if(isNotValid){
						// transaction time expired, now cancel the transaction
						markTransactionCancel(header, em);
						
					}else{
						// skip, wait until the transactions is expired
					}
					
				}
			} else {
				// entity manager null
				LOGGER.error("Cannot Obtain Entity Manager");
			}
			
		} catch (Exception e) {
			LOGGER.error("Error Occured in SDSRTLogUtilImpl.cancelInvalidOrderTransaction",e);
		}
		
	}
	private EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emf.createEntityManager();
	}
	
	private boolean isTransactionExpired(OrderTranHeader transaction){
		boolean flag = false;
		try{
			// prepare calender object for order time
			Calendar orderTime = Calendar.getInstance();
			orderTime.setTime(transaction.getTsOrdBgn());
			
			// get current time calender instance
			Calendar currTime = Calendar.getInstance();
			
			// calculate the difference
			long diff = currTime.getTimeInMillis() - orderTime.getTimeInMillis();
			if(diff > 60 * 60 * 1000){  // get the expire time from parameter
			    flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			LOGGER.error("Error Occured in Checking weather the Transaction is Expired or Not in order to mark it as Cancel",e);
		}
		return flag;
	}
	
	private void markTransactionCancel(OrderTranHeader transaction, EntityManager em){
		
		try {
			// set the cancel flags for the transaction
			transaction.setOrdTy("3");  // changing from 23 to 3
			transaction.setScOrd(new BigDecimal(0)); // changing from null to 0
			transaction.setTransactionStatus(new BigDecimal("2")); // changing from null to 2
			transaction.setTsOrdEnd(new java.util.Date());
			transaction.setTsMdfRcrd(new java.util.Date());
			
			transaction.setIdTrlogBtch("-1");
			
			// merge the transaction
			em.getTransaction().begin();
			em.persist(transaction);
			em.getTransaction().commit(); // commit done
			
		} catch (Exception e) {
			LOGGER.error("Error Occured in Updating the InValid Transaction to Cancel Transaction ", e);
		}
		
	}
}
