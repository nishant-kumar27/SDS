package com.rsi.dk.claim.approve.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rsi.dk.claim.approve.dao.factory.ClaimApproveStatusDAOFactory;
import com.rsi.dk.claim.approve.dao.factory.DateConvertor;

import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.RisplDkClaimApproveV;
import wms.dk.rsi.customerdetails.CustomerOrderReturnDetails;
import wms.dk.rsi.customerdetails.ReturnHeaderDetails;
import wms.dk.rsi.customerdetails.ReturnLineDetails;

public class ClaimApproveListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(ClaimApproveListDTO.class);

	EntityManagerFactory emf;

	public ClaimApproveListDTO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public List<CustomerOrderReturnDetails> getCustomerOrderReturnDetails() {
		List<CustomerOrderReturnDetails> returnDetailList = new ArrayList<CustomerOrderReturnDetails>();
		List<RisplDkClaimApproveV> claimApproveList = null;
		List<ClaimTranLineItem> lineItemList = null;
		try {
			LOGGER.debug("getCustomerOrderReturnDetails method started Executing");
			try {
				claimApproveList = ClaimApproveStatusDAOFactory.getClaimApproveStatus(emf).getApproveStatus();
			} catch (Exception e) {
				LOGGER.error("Unable To Fetch The Approve Claim Request From Database " + e);
			}
			if (!claimApproveList.isEmpty()) {
				for (RisplDkClaimApproveV claimApprove : claimApproveList) {
					ClaimLineItemDTO dto = new ClaimLineItemDTO();
					dto.setRetailStoreId(claimApprove.getRtStrId());
					dto.setWorkStationId(claimApprove.getOrdWs());
					dto.setTranSeqNO(claimApprove.getTrnSeq());
					dto.setBusinessDate(claimApprove.getDcDyOrd());
					try {
						lineItemList = ClaimApproveStatusDAOFactory.getClaimApproveStatus(emf).getLineItemDetails(dto);
					} catch (Exception e) {
						LOGGER.error("Unable To Find The Line Item Details For The Transaction " + e);
					}

					ReturnHeaderDetails headerDetails = new ReturnHeaderDetails();
					headerDetails.setCustomerOrderNO(claimApprove.getIdOrd());
					headerDetails.setReturnReasonCode(claimApprove.getRsnDesc());
					//String uniqueRMANumber = "";
					String uniqueRMANumber = claimApprove.getClaimId();
					/*try{
					uniqueRMANumber = Utils.getInstance().getClaimID()+claimApprove.getClaimId();
					}
					catch(Exception e)
					{
						logger.error("Unable To Find The Claim Id From Properties File.Please Check The Config.properties file"+e);
					}*/
					headerDetails.setUniqueSeqNoForReturn(uniqueRMANumber);
					headerDetails.setBusinessDate(
							DateConvertor.asXMLGregorianCalendar(DateConvertor.getDate(claimApprove.getDcDyOrd())));

					CustomerOrderReturnDetails returnDetails = new CustomerOrderReturnDetails();
					returnDetails.setReturnHeaderDetails(headerDetails);
					if (!lineItemList.isEmpty()) {
						
						LOGGER.debug("Grouping Line Items. Total Lines before grouping = " + lineItemList.size());
						Map<String, ClaimTranLineItem> itmIdOrdLineObjs = new HashMap<String, ClaimTranLineItem>();
						for (ClaimTranLineItem lineItem : lineItemList) {
							if (itmIdOrdLineObjs.containsKey(lineItem.getItemId())) {
								ClaimTranLineItem tempLine = itmIdOrdLineObjs.get(lineItem.getItemId());
								tempLine.setApprClaimQty(tempLine.getApprClaimQty().add(lineItem.getApprClaimQty()));
								itmIdOrdLineObjs.put(lineItem.getItemId(), tempLine);
							} else {
								itmIdOrdLineObjs.put(lineItem.getItemId(), lineItem);
							}
						}
						LOGGER.debug("Grouping Line Items. Total Lines after grouping = " + itmIdOrdLineObjs.size());
						Collection<ClaimTranLineItem> lineItems = itmIdOrdLineObjs.values();
						
						int lineNumber =1;
						for (ClaimTranLineItem lineItem : lineItems) {
							ReturnLineDetails lineDetails = new ReturnLineDetails();
							lineDetails.setUniqueSeqNoForReturn(uniqueRMANumber);
							lineDetails.setExpectedUnitQty(lineItem.getApprClaimQty());
							lineDetails.setItemID(lineItem.getItemId());
							lineDetails.setLineItemNO(new BigDecimal(lineNumber));
							try{
							lineDetails.setReasonCode(lineItem.getReasonCode().getRsnDescCode());
							lineDetails.setReasonCodeDescription(lineItem.getReasonCode().getRsnDesc());
							}
							catch(Exception e)
							{
								lineDetails.setReasonCode("Reason Code");
								lineDetails.setReasonCodeDescription("Reason Code");
							}
							returnDetails.getReturnLineDetails().add(lineDetails);
							lineNumber++;
						}
					}

					returnDetailList.add(returnDetails);

				}
			}
			LOGGER.debug("getCustomerOrderReturnDetails Method Completed Successfully");
		} catch (Exception e) {
			LOGGER.error("Unable To Save The Details In Return Order List" + e);
			return null;
		}
		return returnDetailList;

	}
	
}
