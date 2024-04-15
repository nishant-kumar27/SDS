package com.rsi.dk.claim.approve.dao;

import java.util.List;

import com.rsi.dk.claim.approve.dto.ClaimLineItemDTO;

import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.RisplDkClaimApproveV;
import wms.dk.rsi.customerdetails.CustomerOrderReturnStatus;

public interface ClaimApproveStatusDAOI {

	public static final String APPROVESTATUS = "SELECT * FROM RISPL_DK_CLAIM_APPROVE_V";

	public static final String LINEITEMDETAILS = "SELECT * FROM CLAIM_TRAN_LINE_ITEM WHERE RT_STR_ID = ? AND ORD_WS = ? AND TRN_SEQ = ? AND DC_DY_ORD = ? AND ITM_TY=1 AND APPR_CLAIM_QTY > 0";

	//UPDATE TmpDealer x SET x.approved='no' WHERE x.customerno='XYZ'
			
	public static final String UPDPROCESSEDRECORDS = "UPDATE ClaimTranHeader a SET a.idBtchInvResv = :timestamp , a.scOrd = :scord  WHERE a.claimId in :claimID";

	public List<RisplDkClaimApproveV> getApproveStatus() throws Exception;

	public List<ClaimTranLineItem> getLineItemDetails(ClaimLineItemDTO dto) throws Exception;

	public boolean updateProcessedClaimReqToWms(List<CustomerOrderReturnStatus> retunrStatusList) throws Exception;

}
