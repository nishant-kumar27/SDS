package com.rispl.cancel.order.dao;

import java.util.List;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import wms.dk.rsi.customerdetails.CustomerOrderCancelStatus;

public interface CancelStatusDAOI {

	public static final String CANCELORDER = "SELECT cancel from OrderTranHeader cancel WHERE cancel.ordTy = '25' and cancel.scOrd = 8 and cancel.transactionStatus = 2 and cancel.idTlogBtch = -1 ";

	public static final String UPDPROCESSEDRECORDS = "UPDATE ORD_TRAN_HEADER HEAD SET HEAD.ID_TLOG_BTCH = ?, HEAD.SC_ORD = 9 WHERE (HEAD.RT_STR_ID || '|' || HEAD.ORD_WS || '|' || HEAD.TRN_SEQ || '|' || REPLACE(HEAD.DC_DY_ORD, '-', '')) IN (SELECT (HSUM.RT_STR_ID || '|' || HSUM.ORD_WS || '|' || HSUM.TRN_SEQ || '|' || REPLACE(HSUM.DC_DY_ORD, '-', '')) FROM ORD_TRAN_SUM HSUM WHERE (HEAD.RT_STR_ID || '|' || HEAD.ORD_WS || '|' || HEAD.TRN_SEQ || '|' || HEAD.DC_DY_ORD) = (HSUM.RT_STR_ID || '|' || HSUM.ORD_WS || '|' || HSUM.TRN_SEQ || '|' || HSUM.DC_DY_ORD) AND HEAD.ORD_TY = '25' AND HEAD.SC_ORD = 8 AND HEAD.SC_TRAN = 2 AND HEAD.ID_TLOG_BTCH = '-1'AND NVL(HSUM.ORIG_ID_ORD, HSUM.ID_ORD) = ?)";
			

	public static final String updateCustomerAvaialbleLimit = "UPDATE RISPL_DK_CUST_LMT CUST_LMT SET CUST_LMT.AV_CRDT_LIMIT=CUST_LMT.AV_CRDT_LIMIT+"
                                                              +"(SELECT HSUM.DKART_NET_TOT FROM ORD_TRAN_HEADER HEAD,ORD_TRAN_SUM HSUM WHERE HEAD.RT_STR_ID = HSUM.RT_STR_ID"
                                                              +" AND HEAD.ORD_WS = HSUM.ORD_WS AND HEAD.TRN_SEQ = HSUM.TRN_SEQ AND HEAD.DC_DY_ORD = HSUM.DC_DY_ORD"
                                                              +" AND HEAD.ORD_TY = '25' AND HEAD.SC_ORD = 9 AND HEAD.SC_TRAN = 2 AND HEAD.ID_TLOG_BTCH != '-1' AND NVL(HSUM.ORIG_ID_ORD,HSUM.ID_ORD) = ? AND CUST_LMT.CUST_ID = HSUM.ORD_ID_CT)"
                                                              +" WHERE EXISTS(SELECT HSUM.DKART_NET_TOT FROM ORD_TRAN_HEADER HEAD,ORD_TRAN_SUM HSUM WHERE HEAD.RT_STR_ID = HSUM.RT_STR_ID"
                                                              +" AND HEAD.ORD_WS = HSUM.ORD_WS AND HEAD.TRN_SEQ = HSUM.TRN_SEQ AND HEAD.DC_DY_ORD = HSUM.DC_DY_ORD"
                                                              +" AND HEAD.ORD_TY = '25' AND HEAD.SC_ORD = 9 AND HEAD.SC_TRAN = 2 AND HEAD.ID_TLOG_BTCH != '-1' AND NVL(HSUM.ORIG_ID_ORD,HSUM.ID_ORD) = ? AND CUST_LMT.CUST_ID = HSUM.ORD_ID_CT)";
	
	public static final String fetchCancelOrderBasedOnOrderID = "SELECT CANCEL FROM RisplDkCancelOrderSearchV CANCEL WHERE CANCEL.orderDate BETWEEN :fromDate and :toDate AND CANCEL.orderId LIKE :orderNo and CANCEL.orderTotal BETWEEN :fromTotal AND :toTotal AND (cancel.custId LIKE :customer OR upper(cancel.customerName) LIKE :customer) AND cancel.orderId IN (SELECT distinct ots.idOrd FROM  OrderTranLineItem otli INNER JOIN  OrderTranSum ots ON otli.id.trnSeq=ots.id.trnSeq AND otli.id.rtStrId=ots.id.rtStrId AND otli.id.dcDyOrd=ots.id.dcDyOrd" 
				            +" AND (otli.itemId LIKE :itemid OR upper(otli.deItmShrtRcpt) LIKE :itemid))";
		
	public static final String fetchCancelOrderTransactionDetails = "SELECT HEAD FROM OrderTranHeader HEAD WHERE HEAD.id.rtStrId= :rtStrId and HEAD.id.ordWs = :ordWs AND HEAD.id.trnSeq =:trnSeq AND HEAD.id.dcDyOrd =:dcDyOrd";
	
	public static final String fetchCancelOrderMinmumDate = "SELECT MIN(CANCEL.orderDate) FROM RisplDkCancelOrderSearchV CANCEL";
	
	public static final String fetchCancelOrderMaxAmount = "SELECT MAX(CANCEL.orderTotal) FROM RisplDkCancelOrderSearchV CANCEL";
	
	public static final String fetchItemIDBasedOnItemIdBarcodeDescriptions= "SELECT DISTINCT ITM_ID FROM RISPL_DK_ITEM_MSTR WHERE (ITM_ID = ? OR ID_ITM_POS = ? OR ITM_DESC = ?)";
	
	public List<OrderTranHeader> fetchCancelOrderHeaderDetails() throws Exception;

	public boolean updateProcessedCancelReqToWms(List<CustomerOrderCancelStatus> cancelStatusList) throws Exception;
	
	public List<RisplDkCancelOrderSearchV> fetchCancelOrderByOrderTab(String orderNo,String fromDate,String toDate,String fromTotal,String toTotal,String customer,String item,EmployeeIfc employee);
		
}
