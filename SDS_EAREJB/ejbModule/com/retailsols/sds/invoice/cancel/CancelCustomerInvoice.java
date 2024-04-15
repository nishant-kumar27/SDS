package com.retailsols.sds.invoice.cancel;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.retailsols.sds.creditmemo.LookUpCreditMemo;

import rispl.db.model.claim.ClaimTranHeader;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;

@Stateless(mappedName="cancelCustomerInvoice")
@LocalBean
public class CancelCustomerInvoice implements CancelCustomerInvoiceIfc {

	private static final Logger LOGGER = LogManager.getLogger(CancelCustomerInvoice.class);
	
	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	

	@Override
	public boolean isInvoiceAlreadyCancelled(String invoiceId, String orderId) {
		boolean flag = false;
		try {
			EntityManager em = emf.createEntityManager();
			Query query = em.createNamedQuery("GET_CLAIM_BY_ORDERID", String.class);
			query.setParameter("idOrd", orderId);
			@SuppressWarnings("unchecked")
			List<ClaimTranHeader> list = query.getResultList();
			if(list.size() > 0){
				flag = true;
			}
		} catch (Exception e) {
			LOGGER.error("Error Occured in Checking the Retrun Transaction for a invoice", e);
		}
		return flag;
	}

	@Override
	public boolean isOfflineInvoice(String invoiceId) {
		boolean flag = false;
		try {
			if(invoiceId.contains("OFFLINE")){
				flag = true;
				LOGGER.info("Invoice ID : "+invoiceId+" is a Offline Invoice and Offline Invoice cannnot be Cancelled" );
			}
		} catch (Exception e) {
			LOGGER.error("Error Occured in Checking weather the Invoice is Offline or Not", e);
		}
		return flag;
	}

	@Override
	public boolean isPaymentRecevied(String invoiceId) {
		boolean flag = false;
		try {
			EntityManager em = emf.createEntityManager();
			Query query = em.createNamedQuery("GET_INVOICE_DETAILS_BY_INVOICEID", String.class);
			query.setParameter("arInvNum", invoiceId);
			@SuppressWarnings("unchecked")
			List<CustomerSiteInvoice> list = query.getResultList();
			CustomerSiteInvoice inv = list.get(0);
			if(inv.getInvAmount().compareTo(inv.getInvPendAmount())==0){
				flag = false;
			}
		} catch (Exception e) {
			LOGGER.error("Error Occured Checking the Payment Status for the Invoice", e);
		}
		return flag;
	}

	@Override
	public boolean isMarkedAsDelivered(String orderId) {
		boolean flag = false;
		try {
			EntityManager em = emf.createEntityManager();
			Query query = em.createNamedQuery("CHECK_IF_ORDER_DELIVERED", String.class);
			query.setParameter("idOrd", orderId);
			@SuppressWarnings("unchecked")
			List<BigDecimal> list = query.getResultList();
			BigDecimal sts = list.get(0);
			String status = sts.toString();
			if(status.equalsIgnoreCase("7")){
				flag = true;
			}else{
				flag = false;
			}
		} catch (Exception e) {
			LOGGER.error("Error Occured Checking weather the order is marked as Delivered or Not", e);
		}
		return flag;
	}

}
