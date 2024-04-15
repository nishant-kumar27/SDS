package com.retailsols.downloadPDF;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Preparable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.test.entities.OrderDetail;

import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.transaction.DkartReasonCodes;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;



public class OrderDetailsDownloadPDFAction extends DSAction implements Preparable{

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LogManager.getLogger(OrderDetailsDownloadPDFAction.class);
	
	private List<OrderDetail> head;
	private List<PendingOrderBO> pboList;
    private String orderId;
    private OrderTranHeader orderTran;
    private String tranLevelAgentName;
    private boolean creditLimitOverride;
    private Date ordDate,ordDeliDate,ordLPODate;
    private Map<String,String> getAllDiscountReasnCode=new HashMap<String,String>();
    public Map<String, String> getGetAllDiscountReasnCode() {
		return getAllDiscountReasnCode;
	}
	public void setGetAllDiscountReasnCode(Map<String, String> getAllDiscountReasnCode) {
		this.getAllDiscountReasnCode = getAllDiscountReasnCode;
	}
    public Date getOrdDeliDate() {
		return ordDeliDate;
	}


	public void setOrdDeliDate(Date ordDeliDate) {
		this.ordDeliDate = ordDeliDate;
	}


	public Date getOrdLPODate() {
		return ordLPODate;
	}


	public void setOrdLPODate(Date ordLPODate) {
		this.ordLPODate = ordLPODate;
	}


	public Date getOrdDate() {
		return ordDate;
	}


	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}


	/**
	 * This pendingOrderRange range to search pending orders.
	 */
	private String pendingOrderRange;
    @Override
    public void prepare() throws Exception {
    	try{
    		String creditLimitOverrideString = DKartContext.getParamterBean().fetchXMLParameterValues().getEnableExceedingCustomerAvailableLimit();
    		if(creditLimitOverrideString!=null && creditLimitOverrideString.equalsIgnoreCase("Yes"))
    			setCreditLimitOverride(true);
    	}catch(Exception e){
    		LOG.error(e.getMessage(), e.getCause(), null);
    	}
    }


	public List<PendingOrderBO> getPboList() {
		return pboList;
	}


	public void setPboList(List<PendingOrderBO> pboList) {
		this.pboList = pboList;
	}


	public String Execute(){
		
		return SUCCESS;
	}
	
	
	public String getPendingOrders(){
		LOG.debug("Executing getPendingOrders() to fetcch pending orders" );
		List<OrderDetail> orderList=null;
		try{
			//setting the saved Order Search Range
			setPendingOrderRange(DKartContext.getParamterBean().fetchXMLParameterValues().getSaveOrderSearchRange());
			LOG.debug(" Look up for  Order Transaction service started" );
			OrderTransactionsIfc order=	DKartContext.getLookupOrder();
			LOG.debug(" Look up for  Order Transaction service completed" );
		
				orderList=	order.getPendingOrders(this.getEmployee());
				if(orderList!=null){
					setHead(orderList);
					setPenddingOrderBO();
				}
				else{
					addActionError("No Records Found");
					LOG.warn("Got pending order list as null " );
				}
			
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public  List<OrderDetail> getHead() {
		return head;
	}


	public void setHead( List<OrderDetail> head) {
		this.head = head;
	}
	
/**
 * Changed by hanu
 */
public void setPenddingOrderBO(){
	
	List<PendingOrderBO> pboList=new ArrayList<>();
	SimpleDateFormat format=new SimpleDateFormat(getText("format.date"));
	try{
		
	if(this.getHead()!=null){
		
		for(OrderDetail orderDetail:this.getHead()){
			PendingOrderBO pbo=new PendingOrderBO();
		
		
		
		
			//for(OrderTranSum sum:trnSum){
				pbo.setOrd_id(orderDetail.getId().getOrderId());
				pbo.setOrdTotal(orderDetail.getOrderTotal());
				pbo.setEffectiveDate(format.format(orderDetail.getEffectiveDate()));
				String dcdy = orderDetail.getBusinessDate(); 
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dcdy);
			    pbo.setOrdDate(format.format(date));				
				pbo.setLpo(orderDetail.getLpoNumber()!=null ? orderDetail.getLpoNumber() : "");
				
				pbo.setCustname(orderDetail.getCustomerName());
				pbo.setSalesAgent(orderDetail.getSalesAgent());
					
				pbo.setQuantity(orderDetail.getQty()!=null ?orderDetail.getQty().toString():"");
			
	
		pbo.setHead(orderDetail);
		pboList.add(pbo);
		}
		
	}
	this.setPboList(pboList);
	
	}
	catch(Exception e){
		e.printStackTrace();
	}
}


class PendingOrderBO{
	String ord_id;
	String ordDate;
	BigDecimal ordTotal;
	String custname;
	
	String quantity;
	String effectiveDate;
	String lpo;
	String salesAgent;
	OrderDetail head;
	
	public OrderDetail getHead() {
		return head;
	}
	public void setHead(OrderDetail head) {
		this.head = head;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getLpo() {
		return lpo;
	}
	public void setLpo(String lpo) {
		this.lpo = lpo;
	}
	public String getSalesAgent() {
		return salesAgent;
	}
	public void setSalesAgent(String salesAgent) {
		this.salesAgent = salesAgent;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(String ord_id) {
		this.ord_id = ord_id;
	}
	public String getOrdDate() {
		return ordDate;
	}
	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}

	public BigDecimal getOrdTotal() {
		return ordTotal;
	}
	public void setOrdTotal(BigDecimal ordTotal) {
		this.ordTotal = ordTotal;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	
	@Override
	public String toString() {
		return "PendingOrderBO [ord_id=" + ord_id + ", ordDate=" + ordDate + ", ordTotal=" + ordTotal + ", custname="
				+ custname + ",  quantity=" + quantity + ", effectiveDate=" + effectiveDate
				+ ", lpo=" + lpo + ", salesAgent=" + salesAgent + "]";
	}
	
	
}


@Override
public String toString() {
	return "PendingOrderAction [head=" + head + ", pboList=" + pboList + "]";
}
/*changed by hanu*/
public String downloadSavedOrdDetails(){
	
	OrderTransactionsIfc order=null;
	try {
		order = DKartContext.getLookupOrder();
		setTranLevelAgentName(tranLevelAgentName);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	orderTran = order.getOrderByOrderID(orderId,"23",BigDecimal.ZERO,new BigDecimal(4));//getting suspended order
	
	ordDate=orderTran.getBusinessDate();
	ordLPODate=orderTran.getOrdTranSum().getCustLpoDate();
	ordDeliDate=orderTran.getOrdTranSum().getOrdDlvrDate();
	
	// process start by jagadish for need to display discount description in Saved order_Details page 
				List<DkartReasonCodes> getDisResnCodesList = order.getAllDisRsnCode();
				for(DkartReasonCodes getDisResnCodes: getDisResnCodesList)
				{
					String resnCodeDes=getDisResnCodes.getRsnDesc();
					long longrsncode=getDisResnCodes.getRsnCode();
					String resnCode=Long.toString(longrsncode);
					getAllDiscountReasnCode.put(resnCode,resnCodeDes);
				} // process has been end 	
	
	putInSession(SESSION.QUOTE_DETAILS, orderTran);	
	return SUCCESS;
}
public String getTranLevelAgentName() throws Exception{
	return tranLevelAgentName;
}
public void setTranLevelAgentName(String tranLevelAgentName) {
	this.tranLevelAgentName = tranLevelAgentName;
}


		//Flag to checck Quoted Order
		public boolean isQuoteOrder(OrderTranHeader head){
			if(head.getOrdTy().equalsIgnoreCase("23") && head.getScOrd().intValue()==0 && head.getTransactionStatus().intValue()==4){
				return true;
			}
			else{
				return false;
			}
		}
//For changing the status of pending order from quoted to Suspended Retrieved

public String changeStatusOfQuoted(){
	OrderTranHeader sessionHead=null;
	OrderTranHeader result=null;
	try{
		if(sessionContains(SESSION.QUOTE_DETAILS)){
			sessionHead=(OrderTranHeader)getFromSession(SESSION.QUOTE_DETAILS);
			//executes if and only if it is quoted
			if(isQuoteOrder(sessionHead)){
				OrderTransactionsIfc order=	DKartContext.getLookupOrder();
				if(order!=null){
					result=	order.markAsSuspendRetrived(sessionHead);
					if(result!=null){
						putInSession(SESSION.ORDER_TRANSACTION, result);
					}
				}
			}
		}
	}
	catch(Exception e){
	e.printStackTrace();	
	}
	
	
	return SUCCESS;
}


public String getOrderId() {
	return orderId;
}


public void setOrderId(String orderId) {
	this.orderId = orderId;
}


public OrderTranHeader getOrderTran() {
	return orderTran;
}


public void setOrderTran(OrderTranHeader orderTran) {
	this.orderTran = orderTran;
}


public boolean isCreditLimitOverride() {
	return creditLimitOverride;
}


public void setCreditLimitOverride(boolean creditLimitOverride) {
	this.creditLimitOverride = creditLimitOverride;
}


public String getPendingOrderRange() {
	return pendingOrderRange;
}


public void setPendingOrderRange(String pendingOrderRange) {
	this.pendingOrderRange = pendingOrderRange;
}

}
