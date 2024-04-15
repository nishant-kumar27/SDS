package rispl.ds.order;

import com.opensymphony.xwork2.ActionSupport;

import rispl.dkart.services.entities.transaction.OrderTranHeader;

public class DoneSalesOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7838166454180130677L;
	private OrderTranHeader orderTran;
	
	public String done()
	{
		return SUCCESS;
	}

	public OrderTranHeader getOrderTran() {
		return orderTran;
	}

	public void setOrderTran(OrderTranHeader orderTran) {
		this.orderTran = orderTran;
	}

}
