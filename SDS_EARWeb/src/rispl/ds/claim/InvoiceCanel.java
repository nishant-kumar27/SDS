
package rispl.ds.claim;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONWriter;

import rispl.db.model.claim.ClaimTranDscItm;
import rispl.db.model.claim.ClaimTranDscItmPK;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.ClaimTranSum;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.ejb.claim.ClaimRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.ds.DSAction;
import rispl.ds.ReasonCodes;
import rispl.ds.context.DKartContext;

public class InvoiceCanel extends DSAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5878088397080084971L;
	private String qty,reasonCode,globalReasonCode,restockingFeeServiceitemId,transportationFeeServiceitemId,returnReasoncodes;
	private int itemIndex,returnFlag;
	private OrderTranHeader orderTran;
	private ClaimTranHeader claimTran;
	private BigDecimal returnPrice;
	private Map<String, String> returntReasonCodeMap;
	public String invNum;
	
	public InvoiceCanel()
    {
    	restockingFeeServiceitemId = getText("restockingFee.serviceitem.id");
    	transportationFeeServiceitemId = getText("transportationFee.serviceitem.id");
    	   	
    }
	public String execute()
	{	
		try {
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			
			JSONWriter writer = new JSONWriter();
			returntReasonCodeMap = ordTrn.getAllReasonCodes().get(getText("returns.reasonCode.group.name"));
			returnReasoncodes = writer.write(returntReasonCodeMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/*
	public String returnQtyUpdate()
	{
		System.out.println("ajax callS returnQtyUpdate : "+itemIndex);
		
		if(qty!=null)
		{
		try {
			
			orderTran = (OrderTranHeader) getSessionmap().get("OrderTransaction");
			OrderTranLineItem orderTranLineItem = orderTran.getOrdTranLineItems().get(itemIndex);
			//setting line Qty
			BigDecimal lineQty = new BigDecimal(qty);
			orderTranLineItem.setLineQntRtn(lineQty);
			orderTranLineItem.setReturnQtyFlag("1");
			
			//setting EXT Price
			//The extended total monetary value of this LineItem, calculated by multiplying the Quantity by the lookup price.
			BigDecimal extPrice= new BigDecimal(0);
			if(orderTranLineItem.getPriceOverRideFlag()!=null && orderTranLineItem.getPriceOverRideFlag().equals("1"))//if price override takes overridden price
			{
				extPrice = lineQty.multiply(orderTranLineItem.getOverRidePrice());
			}
			else
			{
				extPrice = lineQty.multiply(orderTranLineItem.getItmPrnPrc());
			}
					
			
			orderTranLineItem.setExtnLnItmRtn(extPrice);
			
			//setting EXTN_DSC_LN_ITM Price
			orderTranLineItem.setExtnDscLnItm(extPrice);
			
			NewOrderAction newOrderAction = new NewOrderAction(2,restockingFeeServiceitemId,transportationFeeServiceitemId);
			newOrderAction.updateTotals(orderTran);
			getSessionmap().put("OrderTransaction", orderTran);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

		return SUCCESS;
	}
	
	public String returnPriceUpdate()
	{
		System.out.println("ajax callS returnPriceUpdate : "+itemIndex);
		
		try {
			orderTran = (OrderTranHeader) getSessionmap().get("OrderTransaction");
			OrderTranLineItem orderTranLineItem = orderTran.getOrdTranLineItems().get(itemIndex);
			//getting line Qty
			BigDecimal lineQty = new BigDecimal(0);
			if(orderTranLineItem.getReturnQtyFlag()!=null && orderTranLineItem.getReturnQtyFlag().equals("1"))
			{
				lineQty=orderTranLineItem.getLineQntRtn();
			}
			else
			{
				lineQty=orderTranLineItem.getLineQnt();
			}
					
			BigDecimal extPrice= null;
			//setting overridden price for single item
			//setting RestockingFee reason Code  
			if(returnFlag==1)
			{
	    		orderTranLineItem.setPriceOvrrRsnCode((ReasonCodes.getPriceOrrReasonCode()).keySet().iterator().next());
	    		orderTranLineItem.setOverRidePrice(returnPrice);//setting to line item
	    		orderTranLineItem.setPriceOverRideFlag("1");
	    		extPrice= lineQty.multiply(returnPrice);
			}
			else
			{
				orderTranLineItem.setPriceOvrrRsnCode(null);
	    		orderTranLineItem.setOverRidePrice(returnPrice);//setting to line item
	    		orderTranLineItem.setPriceOverRideFlag("0");
	    		extPrice= lineQty.multiply(orderTranLineItem.getItmPrnPrc());
			}
			
			//setting EXT Price
			//The extended total monetary value of this LineItem, calculated by multiplying the Quantity by the lookup price.
			
			orderTranLineItem.setExtnLnItmRtn(extPrice);
						
			//setting EXTN_DSC_LN_ITM Price
			orderTranLineItem.setExtnDscLnItm(extPrice);
			
			NewOrderAction newOrderAction = new NewOrderAction(2,restockingFeeServiceitemId,transportationFeeServiceitemId);
			newOrderAction.updateTotals(orderTran);
			getSessionmap().put("OrderTransaction", orderTran);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}*/
	
public String approveInvCanel() throws Exception
{
	 
	String status="failure";
	/*orderTran = (OrderTranHeader) getSessionmap().get("OrderTransaction");*/
	OrderTranHeader[] orders = (OrderTranHeader[])getFromSession(SESSION.INVOICE_ORDER_TRAN);
	orderTran= orders[0];
	
	EmployeeIfc employee = (EmployeeIfc) getFromSession(SESSION.EMPLOYEE);
			//saving claim
			String empId = employee.getEmployeeId();
			ClaimRemote claim = null;
			ClaimTranHeader claimHeader = new ClaimTranHeader();
			claimHeader.setEmId(empId); //EMPOLYEE ID
			claimHeader.setRcRtnMr(globalReasonCode); //CLAIM REASON CODE
			claimHeader.setTsMdfRcrd(new Timestamp(new Date().getTime()));
			
			ClaimTranSum claimSum = new ClaimTranSum();
			claimSum.setOrdIdCt(orderTran.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId()); //CUSTOMER ID
			claimSum.setIdOrd(orderTran.getOrdTranSum().getIdOrd()); //ORIGINAL ORDER ID
			claimSum.setIdEm(empId); //EMPOLYEE ID
			claimSum.setIdOrdArNmb(invNum);
			
			claimHeader.setClaimTranSum(claimSum);
			//int counter = 1;
			 List<OrderTranLineItem> OrderTranLineItemList = orderTran.getOrdTranLineItems();
			List<ClaimTranLineItem> claimLineItems = new ArrayList<>();
			for (OrderTranLineItem orderTranLineItem : OrderTranLineItemList) {
				
					ClaimTranLineItem claimTranLineItem = new ClaimTranLineItem();
					claimTranLineItem.setItemId(orderTranLineItem.getItemId()); //ITEM ID
					claimTranLineItem.setDeItmShrtRcpt(orderTranLineItem.getDeItmShrtRcpt()); //ITEM DESCRIPTION
					claimTranLineItem.setItmTy(orderTranLineItem.getItmTy());
					claimTranLineItem.setLineQnt(BigDecimal.ZERO); //QUANTITY SOLD
					claimTranLineItem.setItmPrnPrc(orderTranLineItem.getItmPrnPrc()); //ITEM SELLING PRICE
					claimTranLineItem.setApprClaimPrice(orderTranLineItem.getItmPrnPrc());//Approved item price
					BigDecimal qty = orderTranLineItem.getLineQnt();
					if(orderTranLineItem.getReturnQtyFlag()!=null && orderTranLineItem.getReturnQtyFlag().equals("1"))
					{
						qty = orderTranLineItem.getLineQntRtn();
					}
					claimTranLineItem.setLineQntRtn(qty); //QUANTITY REGISTERED FOR RETURN
					claimTranLineItem.setApprClaimQty(qty); //QUANTITY APPROVED FOR RETURN
					
					claimTranLineItem.setExtnLnItmRtn(orderTranLineItem.getExtnLnItmRtn());//Extended Price
					claimTranLineItem.setExtnDscLnItm(orderTranLineItem.getExtnDscLnItm());//Discounted Price
					claimTranLineItem.setRcRtnMr(orderTranLineItem.getRcRtnMr()); //ITEM LEVEL REASON CODE
					claimTranLineItem.setItmTy(orderTranLineItem.getItmTy()); //STOCK ITEM OR SERVICE ITEM
					
					if (orderTranLineItem.getPriceOverRideFlag()!=null && orderTranLineItem.getPriceOverRideFlag().equals("1")) {
						claimTranLineItem.setOvrdPrc(orderTranLineItem.getOverRidePrice()); //PRICE AFTER PRICE CHANGE
						claimTranLineItem.setRcPrcOvrr(orderTranLineItem.getPriceOvrrRsnCode()); //PRICE CHANGE REASON CODE
						claimTranLineItem.setApprClaimPrice(claimTranLineItem.getOvrdPrc());//Approved PRICE AFTER PRICE CHANGE
					}
					claimLineItems.add(claimTranLineItem);
			}
			
			claimHeader.setClaimTranLineItems(claimLineItems);
			try {
				claim = DKartContext.getClaimBean();
				
				//timestamp and reasoncode has to be added
				claimHeader.setScOrd(BigDecimal.valueOf(2)); // Claim Registered
				
				claimTran= claim.saveClaimTranHeader(claimHeader);
				claimTran.setReturnReasonCodes(ReasonCodes.getReturnReasonCodes());
			
				status= "success";
			} catch (Exception e) {
				e.printStackTrace();			
				
			}
	return status;
}

public String returnReasonCodeUpdate()
{
	System.out.println("ajax callS reasonCodeUpdate : "+reasonCode);
	
	try {
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		
		orderTran.getOrdTranLineItems().get(itemIndex).setRcRtnMr(reasonCode);
			
		putInSession(SESSION.ORDER_TRANSACTION, orderTran);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return SUCCESS;
}

public String doneRegisterClaim()
{
	putInSession(SESSION.ORDER_TRANSACTION, null);
	return SUCCESS;
}


	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}


	public OrderTranHeader getOrderTran() {
		return orderTran;
	}

	public void setOrderTran(OrderTranHeader orderTran) {
		this.orderTran = orderTran;
	}

	public BigDecimal getReturnPrice() {
		return returnPrice;
	}

	public void setReturnPrice(BigDecimal returnPrice) {
		this.returnPrice = returnPrice;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getGlobalReasonCode() {
		return globalReasonCode;
	}

	public void setGlobalReasonCode(String globalReasonCode) {
		this.globalReasonCode = globalReasonCode;
	}
	public String getRestockingFeeServiceitemId() {
		return restockingFeeServiceitemId;
	}
	public void setRestockingFeeServiceitemId(String restockingFeeServiceitemId) {
		this.restockingFeeServiceitemId = restockingFeeServiceitemId;
	}
	public String getTransportationFeeServiceitemId() {
		return transportationFeeServiceitemId;
	}
	public void setTransportationFeeServiceitemId(String transportationFeeServiceitemId) {
		this.transportationFeeServiceitemId = transportationFeeServiceitemId;
	}
	public int getReturnFlag() {
		return returnFlag;
	}
	public void setReturnFlag(int returnFlag) {
		this.returnFlag = returnFlag;
	}
	
	public String getReturnReasoncodes() {
		return returnReasoncodes;
	}
	public void setReturnReasoncodes(String returnReasoncodes) {
		this.returnReasoncodes = returnReasoncodes;
	}
	public Map<String, String> getReturntReasonCodeMap() {
		return returntReasonCodeMap;
	}
	public void setReturntReasonCodeMap(Map<String, String> returntReasonCodeMap) {
		this.returntReasonCodeMap = returntReasonCodeMap;
	}
	public int getItemIndex() {
		return itemIndex;
	}
	public void setItemIndex(int itemIndex) {
		this.itemIndex = itemIndex;
	}
	public ClaimTranHeader getClaimTran() {
		return claimTran;
	}
	public void setClaimTran(ClaimTranHeader claimTran) {
		this.claimTran = claimTran;
	}
	
	
	
}
