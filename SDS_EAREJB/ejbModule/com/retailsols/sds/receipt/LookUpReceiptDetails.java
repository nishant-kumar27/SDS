package com.retailsols.sds.receipt;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.entities.OrderDetailsWithInvoice;

import rispl.db.model.payment.RisplDkArPaym;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;

@Stateless(mappedName="lookUpReceiptDetails")
@LocalBean
public class LookUpReceiptDetails implements LookUpReceiptDetailsIfc{

	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	
	private static final Logger LOGGER = LogManager.getLogger(LookUpReceiptDetails.class);
	
	@Override
	public List<RisplDkArPaym> getReceiptDetails(String reciptID) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("SEARCH_CUSTOMER_RECEIPT", String.class);
		query.setParameter("arPaymNum", reciptID);
		List<RisplDkArPaym> list = query.getResultList();
		
		return list;
	}

	@Override
	public OrderDetailsWithInvoice getCustomerInvoiceDetails(String invoiceID) {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("GET_CUSOTMER_INVOICE",String.class);
		query.setParameter("invoiceId", invoiceID);
		OrderDetailsWithInvoice inv = (OrderDetailsWithInvoice) query.getResultList().get(0);
		
		return inv;
	}

	@Override
	public List<String> getAppliedInvoicesForReceipt(String receiptId) {
		try {
			EntityManager em = emf.createEntityManager();
		
		} catch (Exception e) {
			LOGGER.error("Error Occured in Loading the Applied Invoices for the Receipt",e);
		}
		return null;
	}
}
