package com.retailsols.sds.transaction.ejb;

import java.math.BigDecimal;
import java.util.Date;

import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeaderPK;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import rispl.dkart.services.entities.transaction.OrderTranSumPK;

/**
 * <H1>This is factory class for SDS Entity like OrderTranHeader,OrderTranSum</H1>
 * 
 * @author Shri Krishna Mohan Singh
 *
 */
public class SDSEntityBuilder {

	/**
	 * 
	 * @param storeID
	 * @param workStationID
	 * @param businessDay
	 * @param tranSeq
	 * @param empId
	 * @param tillId
	 * @param tranType
	 * @return OrderTranHeader
	 */
	public static OrderTranHeader getOrderTranHeader(String storeID, String workStationID, String businessDay,
			long tranSeq, String empId, String tillId, String tranType) {
		OrderTranHeader orderTranHeader = new OrderTranHeader();
		OrderTranHeaderPK orderTranHeaderPK = getOrderTranHeaderPK(storeID, workStationID, businessDay, tranSeq);
		orderTranHeader.setId(orderTranHeaderPK);
		orderTranHeader.setTsOrdBgn(new Date());
		orderTranHeader.setIdOpr(empId);
		orderTranHeader.setEmId(empId);
		orderTranHeader.setIdRpstyTnd(tillId);
		orderTranHeader.setIdBtchArch("-1");
		orderTranHeader.setIdBtchInvResv("-1");
		orderTranHeader.setIdTlogBtch(new BigDecimal("-1"));
		orderTranHeader.setIdTrlogBtch("-1");
		orderTranHeader.setIdWmsTlogBtch("-1");
		orderTranHeader.setOrdTy(tranType);
		orderTranHeader.setTransactionStatus(new BigDecimal("1"));
		return orderTranHeader;

	}

	/**
	 * 
	 * @param storeID
	 * @param workStationID
	 * @param businessDay
	 * @param tranSeq
	 * @return OrderTranHeaderPK
	 */
	public static OrderTranHeaderPK getOrderTranHeaderPK(String storeID, String workStationID, String businessDay,
			long tranSeq) {
		OrderTranHeaderPK orderTranHeaderPK = new OrderTranHeaderPK();
		orderTranHeaderPK.setRtStrId(storeID);
		orderTranHeaderPK.setOrdWs(workStationID);
		orderTranHeaderPK.setDcDyOrd(businessDay);
		orderTranHeaderPK.setTrnSeq(tranSeq);
		return orderTranHeaderPK;
	}

	/**
	 * This method returns OrderTranSum by copying primary key from orderTranHeader and setting 
	 * orderID to OrderTranSum
	 * @param orderTranHeader
	 * @param orderId
	 * @return OrderTranSum
	 */
	public static OrderTranSum getOrderTranSum(OrderTranHeader orderTranHeader, String orderId) {
		OrderTranSum orderTranSum = new OrderTranSum();
		OrderTranSumPK orderTranSumPK = getOrderTranSumPK(orderTranHeader.getId());
		orderTranSum.setId(orderTranSumPK);
		orderTranSum.setOrdTranHeader(orderTranHeader);
		orderTranHeader.setOrdTranSum(orderTranSum);
		orderTranSum.setDkartNetTot(new BigDecimal("0"));
		orderTranSum.setDkartSlsTot(new BigDecimal("0"));
		orderTranSum.setDkartTaxIncTot(new BigDecimal("0"));
		orderTranSum.setDkartTaxTot(new BigDecimal("0"));
		orderTranSum.setDkartTndTot(new BigDecimal("0"));
		orderTranSum.setIdOrd(orderId);
		return orderTranSum;
	}
	
	/**
	 * 
	 * @param orderTranHeaderPK
	 * @return
	 */
	public static OrderTranSumPK getOrderTranSumPK(OrderTranHeaderPK orderTranHeaderPK)
	{
		OrderTranSumPK orderTranSumPK=new OrderTranSumPK();
		orderTranSumPK.setRtStrId(orderTranHeaderPK.getRtStrId());
		orderTranSumPK.setOrdWs(orderTranHeaderPK.getOrdWs());
		orderTranSumPK.setDcDyOrd(orderTranHeaderPK.getDcDyOrd());
		orderTranSumPK.setTrnSeq(orderTranHeaderPK.getTrnSeq());
		
		return orderTranSumPK;
	}

	public static void cloneOrderTranSum(OrderTranSum originalOrdTranSum, OrderTranSum newOrdTranSum) {
		newOrdTranSum.setCdCnyIso(originalOrdTranSum.getCdCnyIso());
		newOrdTranSum.setCdCoIso(originalOrdTranSum.getCdCnyIso());
		newOrdTranSum.setCntSndPkg(originalOrdTranSum.getCntSndPkg());
		newOrdTranSum.setCoPrsl(originalOrdTranSum.getCoPrsl());
		newOrdTranSum.setCustLpoDate(originalOrdTranSum.getCustLpoDate());
		newOrdTranSum.setCustLpoNum(originalOrdTranSum.getCustLpoNum());
		newOrdTranSum.setDkartDsApld(originalOrdTranSum.getDkartDsApld());
		newOrdTranSum.setDkartDscTot(originalOrdTranSum.getDkartDscTot());
		newOrdTranSum.setDkartExpenses(originalOrdTranSum.getDkartExpenses());
		newOrdTranSum.setDkartNetTot(originalOrdTranSum.getDkartNetTot());
		newOrdTranSum.setDkartSlsTot(originalOrdTranSum.getDkartSlsTot());
		newOrdTranSum.setDkartTaxIncTot(originalOrdTranSum.getDkartTaxIncTot());
		newOrdTranSum.setDkartTaxTot(originalOrdTranSum.getDkartTaxTot());
		newOrdTranSum.setDkartTndTot(originalOrdTranSum.getDkartTndTot());
		newOrdTranSum.setDtAgRstDob(originalOrdTranSum.getDtAgRstDob());
		newOrdTranSum.setFlCtAznRq(originalOrdTranSum.getFlCtAznRq());
		newOrdTranSum.setFlRcpGfTrn(originalOrdTranSum.getFlRcpGfTrn());
		newOrdTranSum.setFlSndCtPhy(originalOrdTranSum.getFlSndCtPhy());
		newOrdTranSum.setIdEm(originalOrdTranSum.getIdEm());
		newOrdTranSum.setIdIrsCt(originalOrdTranSum.getIdIrsCt());
		newOrdTranSum.setIdMskPrsl(originalOrdTranSum.getIdMskPrsl());
		newOrdTranSum.setIdOrd(originalOrdTranSum.getIdOrd());
		newOrdTranSum.setIdOrdAr(originalOrdTranSum.getIdOrdAr());
		newOrdTranSum.setIdOrdArNmb(originalOrdTranSum.getIdOrdArNmb());
		newOrdTranSum.setInElpsdIdl(originalOrdTranSum.getInElpsdIdl());
		newOrdTranSum.setInLckElpsd(originalOrdTranSum.getInLckElpsd());
		newOrdTranSum.setInRngElpsd(originalOrdTranSum.getInRngElpsd());
		newOrdTranSum.setInTndElpsd(originalOrdTranSum.getInTndElpsd());
		newOrdTranSum.setOrdDlvrDate(originalOrdTranSum.getOrdDlvrDate());
		newOrdTranSum.setOrdDlvrTimePeriod(originalOrdTranSum.getOrdDlvrTimePeriod());
		newOrdTranSum.setOrdEfDate(originalOrdTranSum.getOrdEfDate());
		newOrdTranSum.setOrdIdCt(originalOrdTranSum.getOrdIdCt());
		newOrdTranSum.setOrdIdLy(originalOrdTranSum.getOrdIdLy());
		newOrdTranSum.setOrdLvlDvr(originalOrdTranSum.getOrdLvlDvr());
		newOrdTranSum.setPeItmLnKy(originalOrdTranSum.getPeItmLnKy());
		newOrdTranSum.setPeItmLnSc(originalOrdTranSum.getPeItmLnSc());
		newOrdTranSum.setQuDptKy(originalOrdTranSum.getQuDptKy());
		newOrdTranSum.setQuItmLnKy(originalOrdTranSum.getQuItmLnKy());
		newOrdTranSum.setQuItmLnSc(originalOrdTranSum.getQuItmLnSc());
		newOrdTranSum.setRcRsnSpn(originalOrdTranSum.getRcRsnSpn());
		newOrdTranSum.setRtnTktNo(originalOrdTranSum.getRtnTktNo());
		newOrdTranSum.setStPrsl(originalOrdTranSum.getStPrsl());
		newOrdTranSum.setTySndCt(originalOrdTranSum.getTySndCt());
		newOrdTranSum.setTySndCt(originalOrdTranSum.getTySndCt());
	}

}
