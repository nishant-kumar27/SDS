package rispl.ds.claim;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.json.JSONWriter;

import rispl.db.model.claim.ClaimTranDscItm;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.ClaimTranSum;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.claim.ClaimRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkservices.common.EmployeeSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.ReasonCodes;
import rispl.ds.context.DKartContext;
import rispl.ds.order.NewOrderAction;
import util.email.ClaimEmail;

public class GenerateClaim extends DSAction{
	static final Logger LOGGER = LogManager.getLogger(GenerateClaim.class);
	
	private String qty,reasonCode,globalReasonCode,restockingFeeServiceitemId,transportationFeeServiceitemId,returnReasoncodes;
	private int itemIndex,returnFlag;
	private String priceChangeReasonCode;
	private OrderTranHeader orderTran;
	private ClaimTranHeader claimTran;
	private BigDecimal returnPrice;
	private Map<String, String> returntReasonCodeMap, priceChangeReasonCodeMap;
	private Date claimDate;
	private String sales_Agent,salesAgentName,reasnCode,pickUpAdd;
	private String claimId,claimRefNo,claimRefdate;
	
	public String getReasnCode() {
		return reasnCode;
	}
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	public String getClaimRefNo() {
		return claimRefNo;
	}
	public void setClaimRefNo(String claimRefNo) {
		this.claimRefNo = claimRefNo;
	}
	public String getClaimRefdate() {
		return claimRefdate;
	}
	public void setClaimRefdate(String claimRefdate) {
		this.claimRefdate = claimRefdate;
	}
	public void setReasnCode(String reasnCode) {
		this.reasnCode = reasnCode;
	}
	public String getPickUpAdd() {
		return pickUpAdd;
	}
	public void setPickUpAdd(String pickUpAdd) {
		this.pickUpAdd = pickUpAdd;
	}
	public String getSalesAgentName() {
		return salesAgentName;
	}
	public void setSalesAgentName(String salesAgentName) {
		this.salesAgentName = salesAgentName;
	}
	private String cust_site;
	private String cust_site_addrss;
	private Boolean RegClaimPerWthOutInv;
	private boolean managerOverride;
	
	public GenerateClaim()
    {
    	restockingFeeServiceitemId = getTextCustom("restockingFee.serviceitem.id");
    	transportationFeeServiceitemId = getTextCustom("transportationFee.serviceitem.id");
    	   	
    }
	public String execute()
	{	
		try {
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			String ClaimPerWthOutInv=DKartContext.getParamterBean().fetchXMLParameterValues().getEnableClaimWithOutInvoice();
			if(ClaimPerWthOutInv != null && ClaimPerWthOutInv.equalsIgnoreCase("yes")){
				setRegClaimPerWthOutInv(true);
			}
			JSONWriter writer = new JSONWriter();
			returntReasonCodeMap = ordTrn.getAllReasonCodes().get(getText("returns.reasonCode.group.name"));
			returnReasoncodes = writer.write(returntReasonCodeMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	public String returnQtyUpdate()
	{
		System.out.println("ajax callS returnQtyUpdate for itemindex: "+itemIndex);
		
		if(qty!=null)
		{
		try {
			
			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
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
			putInSession(SESSION.ORDER_TRANSACTION, orderTran);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

		return SUCCESS;
	}
	
	public String returnPriceUpdate()
	{
		LOGGER.debug("ajax callS returnPriceUpdate : "+itemIndex);
		
		try {
			orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
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
	    		orderTranLineItem.setPriceOvrrRsnCode(getPriceChangeReasonCode());
	    		orderTranLineItem.setOverRidePrice(returnPrice);//setting to line item
	    		orderTranLineItem.setPriceOverRideFlag("1");
	    		extPrice= lineQty.multiply(returnPrice);
			}
			else
			{
				orderTranLineItem.setPriceOvrrRsnCode(null);
	    		orderTranLineItem.setOverRidePrice(null);//setting to line item
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
			putInSession(SESSION.ORDER_TRANSACTION, orderTran);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
public String registerClaim() throws Exception
{
	 
	String status="failure";
	orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
	EmployeeIfc employee = (EmployeeIfc) getFromSession(SESSION.EMPLOYEE);
			//saving claim
			String empId = employee.getEmployeeId();
			ClaimRemote claim = null;
			ClaimTranHeader claimHeader = new ClaimTranHeader();
			claimHeader.setIdOpr(empId); //Operater ID
			claimHeader.setScTran(new BigDecimal(cust_site));//Customer Site id
			if(orderTran.getCtDvrInf()!=null){
				setCust_site_addrss(orderTran.getCtDvrInf());
			}else if(orderTran.getCustSites()!=null){
				setCust_site_addrss(orderTran.getCustSites().get(cust_site));
			}
			claimHeader.setCtDvrInf(getCust_site_addrss());
			claimHeader.setEmId(sales_Agent); //EMPOLYEE ID
			claimHeader.setRcRtnMr(globalReasonCode); //CLAIM REASON CODE
			ClaimTranSum claimSum = new ClaimTranSum();
			
			claimSum.setOrdIdCt(orderTran.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId()); //CUSTOMER ID
			
			/*claimSum.setIdOrd(orderTran.getOrdTranSums().get(0).getIdOrd()); //ORIGINAL ORDER ID
*/			claimSum.setIdEm(sales_Agent); //EMPOLYEE ID
			claimHeader.setClaimTranSum(claimSum);
			List<OrderTranLineItem> OrderTranLineItemList = orderTran.getOrdTranLineItems();
			List<ClaimTranLineItem> claimLineItems = new ArrayList<>();
			if(OrderTranLineItemList != null){ // CHECK LINEITEMS EXISTENCE
				LOGGER.info("TRANLINEITEMS FOUND : "+OrderTranLineItemList.size());
				for (OrderTranLineItem orderTranLineItem : OrderTranLineItemList) {
					
						ClaimTranLineItem claimTranLineItem = new ClaimTranLineItem();
						claimTranLineItem.setItemId(orderTranLineItem.getItemId()); //ITEM ID
						claimTranLineItem.setDeItmShrtRcpt(orderTranLineItem.getPluItem().getItem().getItmDesc()); //ITEM DESCRIPTION
						claimTranLineItem.setLineQnt(BigDecimal.ZERO); //QUANTITY SOLD
						claimTranLineItem.setItmPrnPrc(orderTranLineItem.getItmPrnPrc()); //ITEM SELLING PRICE
						BigDecimal qty = orderTranLineItem.getLineQnt();
						if(orderTranLineItem.getReturnQtyFlag()!=null && orderTranLineItem.getReturnQtyFlag().equals("1"))
						{
							qty = orderTranLineItem.getLineQntRtn();
						}
						claimTranLineItem.setLineQntRtn(qty); //QUANTITY REGISTERED FOR RETURN
						claimTranLineItem.setExtnLnItmRtn(orderTranLineItem.getExtnLnItmRtn());//Extended Price
						claimTranLineItem.setExtnDscLnItm(orderTranLineItem.getExtnDscLnItm());//Discounted Price
						claimTranLineItem.setRcRtnMr(orderTranLineItem.getRcRtnMr()); //ITEM LEVEL REASON CODE
						claimTranLineItem.setItmTy(orderTranLineItem.getItmTy()); //STOCK ITEM OR SERVICE ITEM
						
						if (orderTranLineItem.getPriceOverRideFlag()!=null && orderTranLineItem.getPriceOverRideFlag().equals("1")) {
							claimTranLineItem.setOvrdPrc(orderTranLineItem.getOverRidePrice()); //PRICE AFTER PRICE CHANGE
							claimTranLineItem.setRcPrcOvrr(orderTranLineItem.getPriceOvrrRsnCode()); //PRICE CHANGE REASON CODE
						}
						// ADDED BY LAXMIKANTH TO UPDATE THE DISCOUNTS
						if(orderTranLineItem.getOrdTranDscItms() != null){
							LOGGER.info("Promotions found for the item and adding them to claimLineItem");
							List<ClaimTranDscItm> discountList = new ArrayList<>();
							for(OrderTranDiscountItem dsc : orderTranLineItem.getOrdTranDscItms()){
								ClaimTranDscItm clDisc = new ClaimTranDscItm();
								
								clDisc.setDscAmt(dsc.getDscAmt());
								clDisc.setDscPer(dsc.getDscPer());
								clDisc.setPrmCmpDtlid(dsc.getPrmCmpDtlid());
								clDisc.setPrmCmpId(dsc.getPrmCmpId());
								clDisc.setPrmDesc(dsc.getPrmDesc());
								clDisc.setPrmId(dsc.getPrmId());
								clDisc.setPrmType(dsc.getPrmType());
								clDisc.setSrcTrgList(dsc.getSrcTrgList());
								clDisc.setTyDsc(dsc.getTyDsc());
								clDisc.setClaimTranLineItem(claimTranLineItem);
								
								discountList.add(clDisc);
							}
							claimTranLineItem.setClaimTranDscItms(discountList); // ADDING THE PROMOTIONS
						}
						claimLineItems.add(claimTranLineItem);
					
				}
			}else{
				LOGGER.info("NO TRAN LINEITEMS AVAILABLE IN ORDERTRAN, "+OrderTranLineItemList );
			}
			claimHeader.setClaimTranLineItems(claimLineItems);
			try {
				claim = DKartContext.getClaimBean();
				LookUpEmployeeIfc lkupemp = DKartContext.getLookupEmployee();
				EmployeeSearchCriteriaIfc empcrt = new EmployeeSearchCriteria();
				claimHeader.setScOrd(BigDecimal.ONE); // Claim Registered
				claimTran= claim.saveClaimTranHeader(claimHeader);
				claimTran.setReturnReasonCodes(ReasonCodes.getReturnReasonCodes());
				claimDate =getRequiredDate(claimTran.getId().getDcDyOrd());
				empcrt.setEmployeeId(claimTran.getEmId());
				EmployeeIfc[] emp = lkupemp.lookupSalesAgent(empcrt);
				if(emp!=null&&emp.length>0)
				{
					setSales_Agent(emp[0].getEmployeeName());
				}
				status= "success";
				new ClaimEmail().sendClaimRegisterEmail(claimTran);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e);
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



public String addClaimRefNo()
{
	System.out.println("ajax callS : "+claimId +"'"+claimRefNo+"'"+claimRefdate);
	ClaimRemote claim = null;
	try {
		claim = DKartContext.getClaimBean();
		//orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		//orderTran.getOrdTranLineItems().get(itemIndex).setRcRtnMr(reasonCode);
		claim.addClaimRef(claimId,claimRefNo,claimRefdate);	
		
		LOGGER.info("Given ClaimRefNO '"+claimRefNo+"', Successfully inserted in tabel");
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return SUCCESS;
}
public String doneRegisterClaim()
{
	//System.out.println("doneRegisterClaim"+claimTran.getClaimId());
	
	putInSession(SESSION.ORDER_TRANSACTION, null);
	return SUCCESS;
	
}


//to get the required date format
public Date getRequiredDate(String claimDate){
	Date claimedDate = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	try {
	claimedDate = sdf.parse(claimDate);
	LOGGER.info("Given Date '"+claimDate+"', Successfully parsed to Date : "+claimedDate);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		LOGGER.info("Exception caused while parsing the Date:"+claimDate);
	}
	return claimedDate;
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
	public String getSales_Agent() {
		return sales_Agent;
	}
	public void setSales_Agent(String sales_Agent) {
		this.sales_Agent = sales_Agent;
	}
	public Date getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
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
	public Boolean getRegClaimPerWthOutInv() {
		return RegClaimPerWthOutInv;
	}
	public void setRegClaimPerWthOutInv(Boolean regClaimPerWthOutInv) {
		RegClaimPerWthOutInv = regClaimPerWthOutInv;
	}
	public Map<String, String> getPriceChangeReasonCodeMap() {
		priceChangeReasonCodeMap = ReasonCodes.getPriceOrrReasonCode();
		return priceChangeReasonCodeMap;
	}
	public String getPriceChangeReasonCodeMapAsOptions() {
		String ret = null;
		ret = getPriceChangeReasonCodeMap().entrySet().stream().map(entry -> {
			return new String("<option value=\""+entry.getKey()+"\">"+entry.getValue()+"</option>");
			}).collect(Collectors.joining());
		return ret;
	}
	public boolean getManagerOverride() {
		try {
			managerOverride = DKartContext.getParamterBean().fetchXMLParameterValues().getEnableRegisterClaimManagerOveride()
					.equalsIgnoreCase("YES") ? true : false;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e.getCause());
		} 
		return managerOverride;
	}
	public String getPriceChangeReasonCode() {
		return priceChangeReasonCode;
	}
	public void setPriceChangeReasonCode(String priceChangeReasonCode) {
		this.priceChangeReasonCode = priceChangeReasonCode;
	}
	public String getCust_site() {
		return cust_site;
	}
	public void setCust_site(String cust_site) {
		this.cust_site = cust_site;
	}
	public String getCust_site_addrss() {
		return cust_site_addrss;
	}
	public void setCust_site_addrss(String cust_site_addrss) {
		this.cust_site_addrss = cust_site_addrss;
	}
	
}
