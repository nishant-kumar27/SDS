package rispl.dk.customer;

public class CustomerOrderDetails implements CustomerOrderDetailsIfc{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String orderId;
	
	String orderDate;
	
	String orderStatus;
	
	String orderNumber;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderTotal() {
		return orderNumber;
	}

	public void setOrderTotal(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
}
