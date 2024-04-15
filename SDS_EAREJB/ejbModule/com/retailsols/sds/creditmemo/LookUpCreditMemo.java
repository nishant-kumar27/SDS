package com.retailsols.sds.creditmemo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.creditmemo.RisplDkArCreditMemo;
import rispl.dk.customer.Customer;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeader;

@Stateless(mappedName="lookUpCreditMemo")
@LocalBean
public class LookUpCreditMemo implements LookUpCreditMemoIfc{

	private static final Logger LOGGER = LogManager.getLogger(LookUpCreditMemo.class);
	
	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	
	@Override
	public CreditMemoBean getCreditMemoDetails(String crMemoId) {
		
		EntityManager em = emf.createEntityManager();
		CreditMemoBean bean = new CreditMemoBean();
		Query query = em.createNamedQuery("LOOK_UP_CREDIT_MEMO_DETAILS", String.class);
		query.setParameter("crMemoNum", crMemoId);
		List<RisplDkArCreditMemo> list = query.getResultList();
		RisplDkArCreditMemo crmemo = (RisplDkArCreditMemo)list.get(0);
		bean.setCrediMemoId(crmemo.getId().getCrMemoNum());
		bean.setCustId(crmemo.getId().getCustId());
		bean.setCrMemoDate(getRequiredDate(crmemo.getCrMemoDate()));
		bean.setCrMemoAmount(crmemo.getCrMemoAmount());
		bean.setClaimId(crmemo.getClaimID());
		
		// get the claim details for this credit memo using claim id
		
		return bean;
	}
	@Override
	public OrderTranHeader getClaimDetailsByClaimID(String claimId){
		OrderTranHeader ordHead = null;
		try {
			EntityManager em = emf.createEntityManager();
			Query query = em.createNamedQuery("GET_CLAIM_DETAILS_BY_CLAIMID", String.class);
			query.setParameter("acceptClaimId", claimId);
			@SuppressWarnings("unchecked")
			List<OrderTranHeader> list = query.getResultList();
			ordHead = (OrderTranHeader)list.get(0);
			// get customer details
			Query customer = em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class)
					.setParameter("customerId", ordHead.getOrdTranSum().getOrdIdCt());
			List<CustomerHeader> customerList = customer.getResultList();
			CustomerHeader custHead = (CustomerHeader)customerList.get(0);
			Customer cust = new Customer();
			cust.setCustomerHeader(custHead);
			ordHead.setCustomer(cust);
			
		} catch (Exception e) {
			LOGGER.error("Error Occured in Getting the Claim Details by Claim ID", e);
		}
		return ordHead;
	}
	
	public String getRequiredDate(Date date) {
		Format sdf = new SimpleDateFormat("dd-MMM-yy");
		String dateformt = null;
		try {
			dateformt= sdf.format(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return dateformt;
	}

}
