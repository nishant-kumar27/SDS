package rispl.ds;

import java.util.Map;

import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.ds.context.DKartContext;

public class ReasonCodes{
	private static Map<String,String> returnReasonCodes,priceOrrReasonCode,discPerReasonCode,discAmtReasonCode,restockingFeeReasonCode,transportationFeeReasonCode;
	
	
	public static Map<String, String> getRestockingFeeReasonCode() {
		//ActionSupport actionSupport = new ActionSupport();
		OrderTransactionsIfc ordTrn = null;
		try {
			ordTrn = DKartContext.getLookupOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		restockingFeeReasonCode = ordTrn.getAllReasonCodes().get(getText("restockingFee.reasonCode.group.name"));
		return restockingFeeReasonCode;
	}

	public static Map<String, String> getTransportationFeeReasonCode() {
		//ActionSupport actionSupport = new ActionSupport();
		OrderTransactionsIfc ordTrn = null;
		try {
			ordTrn = DKartContext.getLookupOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transportationFeeReasonCode = ordTrn.getAllReasonCodes().get(getText("transportationFee.reasonCode.group.name"));
		return transportationFeeReasonCode;
	}

	public static Map<String, String> getReturnReasonCodes() {
		//ActionSupport actionSupport = new ActionSupport();
		OrderTransactionsIfc ordTrn = null;
		try {
			ordTrn = DKartContext.getLookupOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnReasonCodes = ordTrn.getAllReasonCodes().get(getText("returns.reasonCode.group.name"));
		return returnReasonCodes;
	}

	public static Map<String, String> getPriceOrrReasonCode() {
		//ActionSupport actionSupport = new ActionSupport();
		OrderTransactionsIfc ordTrn = null;
		try {
			ordTrn = DKartContext.getLookupOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		priceOrrReasonCode = ordTrn.getAllReasonCodes().get(getText("priceOvrr.reasonCode.group.name"));
		return priceOrrReasonCode;
	}

	public static Map<String, String> getDiscPerReasonCode() {
		//ActionSupport actionSupport = new ActionSupport();
		OrderTransactionsIfc ordTrn = null;
		try {
			ordTrn = DKartContext.getLookupOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discPerReasonCode = ordTrn.getAllReasonCodes().get(getText("discountPer.reasonCode.group.name"));
		return discPerReasonCode;
	}

	public static Map<String, String> getDiscAmtReasonCode() {
		//ActionSupport actionSupport = new ActionSupport();
		OrderTransactionsIfc ordTrn = null;
		try {
			ordTrn = DKartContext.getLookupOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discAmtReasonCode = ordTrn.getAllReasonCodes().get(getText("discountAmt.reasonCode.group.name"));
		return discAmtReasonCode;
	}

	static String getText(String key)
	{
		return DSAction.getTextCustom(key);
	}
	
}
