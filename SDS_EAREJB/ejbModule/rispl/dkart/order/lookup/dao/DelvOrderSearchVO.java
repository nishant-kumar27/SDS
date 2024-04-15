package rispl.dkart.order.lookup.dao;

import java.util.ArrayList;
import java.util.List;

import com.test.entities.OrderDetailsWithInvoice;

import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;

public class DelvOrderSearchVO {
	private List<OrderDetailsWithInvoice> orderList;
	private OrderTransactionSearchCriteriaIfc searCreteria;
	public List<OrderDetailsWithInvoice> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderDetailsWithInvoice> orderList) {
		this.orderList=(orderList==null)?new ArrayList<OrderDetailsWithInvoice>(): orderList;
		/*this.orderList = orderList;*/
	}
	public OrderTransactionSearchCriteriaIfc getSearCreteria() {
		return searCreteria;
	}
	public void setSearCreteria(OrderTransactionSearchCriteriaIfc searCreteria) {
		this.searCreteria = searCreteria;
	}
	@Override
	public String toString() {
		return "DelvOrderSearchVO [orderList=" + orderList + ", searCreteria=" + searCreteria + "]";
	}
	
	
	

}
