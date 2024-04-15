package utility;

import java.util.ArrayList;
import java.util.List;

public class LowInventoryDetails {
	String orderId;
	List<Object[]> inventoryDetailsList = new ArrayList<>();

	public LowInventoryDetails() {

	}

	public LowInventoryDetails(String orderId, List<Object[]> inventoryDetailsList) {
		this.orderId = orderId;
		setInventoryDetailsList(inventoryDetailsList);
	}

	public void addInventoryDetails(Object[] inventoryDetail) {
		inventoryDetailsList.add(inventoryDetail);
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<Object[]> getInventoryDetailsList() {
		return inventoryDetailsList;
	}

	public void setInventoryDetailsList(List<Object[]> inventoryDetails) {
		this.inventoryDetailsList = inventoryDetails;
	}
}
