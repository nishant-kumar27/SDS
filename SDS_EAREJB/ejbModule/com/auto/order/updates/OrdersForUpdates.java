package com.auto.order.updates;

import java.util.ArrayList;
import java.util.HashMap;

public class OrdersForUpdates {

	
	String orderId;
	
	HashMap<String, String> itemIdsAndQnty= new HashMap<>();
	
	ArrayList<String> qunatity;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public HashMap<String, String> getItemIds() {
		return itemIdsAndQnty;
	}

	public void setItemIds(HashMap<String, String> itemIds) {
		this.itemIdsAndQnty = itemIds;
	}

	public ArrayList<String> getQunatity() {
		return qunatity;
	}

	public void setQunatity(ArrayList<String> qunatity) {
		this.qunatity = qunatity;
	}
	
}
