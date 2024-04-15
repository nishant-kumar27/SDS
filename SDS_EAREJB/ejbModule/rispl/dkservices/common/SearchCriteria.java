package rispl.dkservices.common;

public class SearchCriteria implements SearchCriteriaIfc{

	
	String itemID;
	
	String StoreID;
	
	String discription;
	
	String departmentID;
	
	String itemIdOrUPC;
	
	String upc;
	
	boolean search_ServiceItems;
	
	boolean wildCardSearch;
	
	private boolean checkInventory;

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getStoreID() {
		return StoreID;
	}

	public void setStoreID(String storeID) {
		StoreID = storeID;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public String getItemIdOrUPC() {
		return itemIdOrUPC;
	}

	public void setItemIdOrUPC(String itemIdOrUPC) {
		this.itemIdOrUPC = itemIdOrUPC;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public boolean isCheckInventory() {
		return checkInventory;
	}

	public void setCheckInventory(boolean checkInventory) {
		this.checkInventory = checkInventory;
	}

	public boolean isSearch_ServiceItems() {
		return search_ServiceItems;
	}

	public void setSearch_ServiceItems(boolean search_ServiceItems) {
		this.search_ServiceItems = search_ServiceItems;
	}
	
	
	
	
}
