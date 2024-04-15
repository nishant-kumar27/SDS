package rispl.dk.customer;

import java.io.Serializable;

public interface CustomerOrderDetailsIfc extends Serializable {

	public String getOrderId();

	public void setOrderId(String orderId);

	public String getOrderDate();

	public void setOrderDate(String orderDate);

	public String getOrderStatus();

	public void setOrderStatus(String orderStatus);

	public String getOrderTotal();

	public void setOrderTotal(String orderNumber);
}
