package rispl.dkservices.common;

import java.io.Serializable;

public interface SearchCriteriaIfc extends Serializable {

	public String getItemID();

	public void setItemID(String itemID);

	public String getStoreID();

	public void setStoreID(String storeID);

	public String getDiscription();

	public void setDiscription(String discription);

	public String getDepartmentID();

	public void setDepartmentID(String departmentID);

	public String getItemIdOrUPC();

	public void setItemIdOrUPC(String itemIdOrUPC);

	public String getUpc();

	public void setUpc(String upc);

	public boolean isCheckInventory();

	public void setCheckInventory(boolean checkInventory);

}
