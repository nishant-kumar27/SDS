package rispl.ds.order.cancel;

import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class CancelOrderAction extends DSAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6200784088754810141L;
	private String transComment;
	private OrderTranHeader orderTran;
	private String orderId;
	private String orderHeadStatus;
	private String errorMessage;
	Logger LOG=LogManager.getLogger(CancelOrderAction.class);
	private String reasonCode;

	
	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String cancelOrder()
	{
		String letter=SUCCESS;
		try 
		{
			orderHeadStatus="Order Cancelled";
			String empId = super.getEmployee().getEmployeeId();
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			Hashtable<String, Object> result = ordTrn.cancelSalesOrder(orderId,transComment, empId,reasonCode);
			boolean orderPresist=(boolean) (result.get("PERSIST")!=null?result.get("PERSIST"):false);
			LOG.info("Cancel Order Result : Is Order("+orderId+")Persist :"+orderPresist);
			LOG.info("CancelOrder ERROR Status : "+(String) result.get("ERROR"));
			if(orderPresist)
			{
				orderTran=(OrderTranHeader) result.get("OrderTranHeader");
			}else
			{
			    setErrorMessage((String) result.get("ERROR"));
				addActionError(getErrorMessage());
				letter=ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error("An error occured in cancelOrder", e);
			addActionError("An error occured in cancelOrder "+ e);
			letter=ERROR;
		}
		return letter;
		
	}

	public String getTransComment() {
		return transComment;
	}

	public void setTransComment(String transComment) {
		this.transComment = transComment;
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
	public String getOrderHeadStatus() {
		return orderHeadStatus;
	}
	public void setOrderHeadStatus(String orderHeadStatus) {
		this.orderHeadStatus = orderHeadStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	


}
