package com.rispl.sds.offline.invoice;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rispl.sds.parameter.service.ParameterException;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;

import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.entities.customer.CustomerSite;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;
import rispl.dkart.services.entities.customer.CustomerSitePK;
import rispl.dkart.services.entities.transaction.OrdInvQtyShp;
import rispl.dkart.services.entities.transaction.OrdInvQtyShpPK;
import utility.ConfigUtils;

@Stateless(mappedName = "offlineInvoice")
@LocalBean
public class SDSOfflineInvoiceServiceImpl implements SDSOfflineInvoiceRemote {

	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	ParameterConfigurationServiceIfc parameterService;

	public ParameterConfigurationServiceIfc getParameterService() {
		return parameterService;
	}

	@EJB(mappedName = "sdsparameterService")
	public void setParameterService(ParameterConfigurationServiceIfc parameterService) {
		this.parameterService = parameterService;
	}
	
	public static Date getDate(String dateString) throws Exception {
		if (dateString != null) {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(dateString);
			return date;
		} else {
			throw new Exception("Date Can Not Be Null");
		}
	}
	
	private static final Logger LOGGER = LogManager.getLogger(SDSOfflineInvoiceServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public void generateOfflineInvoice() {
		if (parameterService != null) {
			try {
				if (parameterService.fetchXMLParameterValues().getEnableSDSOfflineInvoice().equalsIgnoreCase("YES")) {
					EntityManager entityManager = getEntityManager();
					if (entityManager != null) {
						Query offlineInvoiceQuery = entityManager.createQuery(OFFLINE_INVOICE);
						offlineInvoiceQuery.setParameter("TIMEINTERVAL",
								new BigDecimal(parameterService.fetchXMLParameterValues().getOfflineInvoiceGenerationTimeInMinutes()));
						List<Object[]> offlineInvoiceList = offlineInvoiceQuery.getResultList();
						if (!offlineInvoiceList.isEmpty()) {
							for (Object[] offlineInvoice : offlineInvoiceList) {
								
								entityManager.getTransaction().begin();
								Query offlineInvoiceLineItemQuery = entityManager
										.createNativeQuery(OFFLINE_INVOICE_LINE, SdsOfflineInvoiceV.class);
								offlineInvoiceLineItemQuery.setParameter(1, offlineInvoice[3].toString());
								List<SdsOfflineInvoiceV> oflineInvLineItmList = offlineInvoiceLineItemQuery
										.getResultList();
								
								BigDecimal offlineInvHeaderSeq = null;
								try{
								 offlineInvHeaderSeq = (BigDecimal) entityManager.createNativeQuery("SELECT OFFLINE_INV_SEQ.NEXTVAL FROM DUAL").getSingleResult();
								}
								catch(Exception e)
								{
									offlineInvHeaderSeq  = new BigDecimal(offlineInvoice[0].toString());
								}

								CustomerSitePK customerSitePrimary = new CustomerSitePK();
								customerSitePrimary.setCustId(offlineInvoice[1].toString());
								customerSitePrimary.setCustSiteId(new BigInteger(offlineInvoice[5].toString()));

								CustomerSite customerSite = entityManager.find(CustomerSite.class, customerSitePrimary);
								CustomerSiteInvoice siteInvoice = new CustomerSiteInvoice();
								siteInvoice.setInvId(offlineInvHeaderSeq);
								siteInvoice.setCustomerSite(customerSite);
								siteInvoice.setArInvNum(offlineInvHeaderSeq+"-"+"OFFLINE");
								siteInvoice.setArInvDate(new java.util.Date());
								siteInvoice.setOrderNum(offlineInvoice[3].toString());
								siteInvoice.setOrderDate(getDate(offlineInvoice[2].toString()));
								siteInvoice.setInvAmount(ConfigUtils.getInstance().createBigDecimal(offlineInvoice[7].toString(), "format.currency"));
								siteInvoice.setInvPendAmount(ConfigUtils.getInstance().createBigDecimal(offlineInvoice[7].toString(), "format.currency"));
								siteInvoice.setInvStatus('1');
								siteInvoice.setRefCustId("OFFLINE");

								entityManager.persist(siteInvoice);
								LOGGER.info("In rispl_dk_cust_site_inv inserted Successfully");

								for (SdsOfflineInvoiceV offlineLineItem : oflineInvLineItmList) {
									BigDecimal offlineInvLineSeq = null;
									try{
										offlineInvLineSeq = (BigDecimal) entityManager.createNativeQuery("SELECT OFFLINE_INV_SEQ.NEXTVAL FROM DUAL").getSingleResult();
									}
									catch(Exception e)
									{
										offlineInvLineSeq  = new BigDecimal(offlineLineItem.getOrdLnItmSeq().toString());
									}
									
									
									OrdInvQtyShpPK qtyShpPk = new OrdInvQtyShpPK();
									qtyShpPk.setCustId(offlineInvoice[1].toString());
									qtyShpPk.setIdOrd(offlineInvoice[3].toString());
									qtyShpPk.setIdOrdAr(offlineInvHeaderSeq+"-"+"OFFLINE");
									//qtyShpPk.setIdOrdAr(offlineInvHeaderSeq.toString());
									qtyShpPk.setArTrxLineId(offlineInvLineSeq.toString());

									OrdInvQtyShp qtyShp = new OrdInvQtyShp();
									qtyShp.setId(qtyShpPk);
									qtyShp.setItemId(offlineLineItem.getItemId());
									qtyShp.setOrdLnItmSeq(offlineLineItem.getOrdLnItmSeq());
									qtyShp.setShpQty(offlineLineItem.getLineQnt());

									entityManager.persist(qtyShp);
									LOGGER.info("In ORD_INV_QTY_SHP inserted Successfully");
								}

								entityManager.getTransaction().commit();
								LOGGER.info("commit Successfully");

							}
							LOGGER.info(offlineInvoiceList.size()+" No Of Offline Invoices Has Been Generated Successfully");
						}else{
							LOGGER.warn("There is No Offline Invoice Need To Be Created in SDS");
						}

					} else {
						LOGGER.error("Unable To Establish Database Connection");
					}
				} else {
					LOGGER.warn("Please Check The Parameter Configuration Value For SDSOfflineInvoice");
				}
			} catch (ParameterException e) {
				LOGGER.error("Unable To Fetch Parameter Values =>" + e);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.info(e);
				LOGGER.error("Exception =>" +e);
			}
		} else {
			LOGGER.error("Unable To Fetch Parameter Values.");
		}

	}

	private EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emf.createEntityManager();
	}

}
