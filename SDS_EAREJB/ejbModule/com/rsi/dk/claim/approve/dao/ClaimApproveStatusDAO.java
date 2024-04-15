package com.rsi.dk.claim.approve.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rsi.dk.claim.approve.dao.factory.DateConvertor;
import com.rsi.dk.claim.approve.dto.ClaimLineItemDTO;

import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.RisplDkClaimApproveV;
import wms.dk.rsi.customerdetails.CustomerOrderReturnStatus;

public class ClaimApproveStatusDAO implements ClaimApproveStatusDAOI {

	private static final Logger LOGGER = LogManager.getLogger(ClaimApproveStatusDAO.class);
	EntityManagerFactory emf;

	public ClaimApproveStatusDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RisplDkClaimApproveV> getApproveStatus() throws Exception {
		EntityManager entitymanager = null;
		List<RisplDkClaimApproveV> claimStatusList = null;
		try {
			LOGGER.info("getApproveStatus Method Started Executing");
			entitymanager = getEntityManager();
			if (entitymanager != null) {
				Query query = entitymanager.createNativeQuery(APPROVESTATUS, RisplDkClaimApproveV.class);
				claimStatusList = query.getResultList();
			} else {
				LOGGER.error("Please Check The Connection " + "Entity :" + entitymanager);
				throw new Exception("Please Check The Connection Details");
			}
			LOGGER.info("getApproveStatus Method Completed Successfully ");
		} catch (Exception e) {
			LOGGER.error("Unable To Find The Claim Approve Status Avilable In The System " + e);
			return null;
		}
		return claimStatusList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClaimTranLineItem> getLineItemDetails(ClaimLineItemDTO dto) throws Exception {
		EntityManager entitymanager = null;
		List<ClaimTranLineItem> lineItemList = null;
		try {
			if (dto != null) {
				LOGGER.debug("getLineItemDetails Method Started Executing");
				entitymanager = getEntityManager();
				if (entitymanager != null) {
					Query query = entitymanager.createNativeQuery(LINEITEMDETAILS, ClaimTranLineItem.class);
					query.setParameter(1, dto.getRetailStoreId());
					query.setParameter(2, dto.getWorkStationId());
					query.setParameter(3, dto.getTranSeqNO());
					query.setParameter(4, dto.getBusinessDate());
					lineItemList = query.getResultList();
				} else {
					LOGGER.error("Please Check The Connection " + "Entity :" + entitymanager);
					throw new Exception("Please Check The Connection Details");
				}
			}
			LOGGER.debug("getLineItemDetails Method Completed Successfully");
		} catch (Exception e) {
			LOGGER.error("Unable To Find The Claim Approve Status Avilable In The System " + e);
			return null;
		}
		return lineItemList;
	}

	@Override
	public boolean updateProcessedClaimReqToWms(List<CustomerOrderReturnStatus> retunrStatusList) throws Exception {
		EntityManager entitymanager = null;
		boolean flag = false;
		try {
			if (!retunrStatusList.isEmpty()) {
				LOGGER.info("updateProcessedClaimReqToWms Method Started Executing");
				entitymanager = getEntityManager();
				if (entitymanager != null) {
					List<String> rmaNumberList = new ArrayList<String>();
					for (CustomerOrderReturnStatus returnStatus : retunrStatusList) {
						String claimID = returnStatus.getRmaNumber();
						if (returnStatus.getOrderStatus() != null && returnStatus.getRmaNumber() != null) {
							if (returnStatus.getOrderStatus().equalsIgnoreCase("RETURN-CREATED")) {
								rmaNumberList.add(claimID);
							} else if (returnStatus.getOrderStatus().equalsIgnoreCase("RETURN-FAILED")) {
								LOGGER.error(returnStatus.getCustomerOrderNo() + " " + returnStatus.getRmaNumber() + " "
										+ returnStatus.getOrderMessage() + " " + returnStatus.getOrderStatus() + " "
										+ "Unable To Send To WMS");
							}
						} else {
							LOGGER.error(returnStatus.getCustomerOrderNo() + " " + returnStatus.getRmaNumber() + " "
									+ returnStatus.getOrderMessage() + " " + returnStatus.getOrderStatus() + " "
									+ "Unable To Send To WMS");

						}
					}
					if (!rmaNumberList.isEmpty()) {

						String timestamp = "";
						try {
							timestamp = DateConvertor.getString(new java.util.Date());
						} catch (Exception e) {
							LOGGER.error(e);
							timestamp = "1";

						}
						entitymanager.getTransaction().begin();
						Query query = entitymanager.createQuery(UPDPROCESSEDRECORDS);
						query.setParameter("timestamp", timestamp);
						query.setParameter("scord", 3);
						query.setParameter("claimID", rmaNumberList);
						int i = query.executeUpdate();
						try {
							entitymanager.getTransaction().commit();
							LOGGER.info(i + "No Of Records Updated In SDS, Which Has Been Send To RWMS");
							flag = true;
						} catch (Exception e) {
							try {
								entitymanager.getTransaction().rollback();
							} catch (Exception e1) {
								LOGGER.error("Uanble To Rollback the Transaction" + e1);
							}
							flag = false;
							LOGGER.error("Unable To Update The Claim Transaction Details " + e);
						}
					}
				} else {
					LOGGER.error("Please Check The Connection " + "Entity :" + entitymanager);
					flag = false;
					throw new Exception("Please Check The Connection Details");
				}
			} else {
				LOGGER.error("Unable To Retrieve The Return Status List From RWMS ");
			}
		} catch (Exception e) {
			LOGGER.error("Unable to Update The The WMS Flag which ever transfered to WMS" + e);
			flag = false;
		}
		return flag;
	}

	private EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emf.createEntityManager();
	}
}
