package rispl.ds.inventory;


import freemarker.log.Logger;
import rispl.dk.itemLookUp.PLUItem;
import rispl.dk.itemLookUp.PLUItemIfc;
import rispl.dkart.services.ejb.LookUpItemIfc;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.SearchCriteria;
import rispl.dkservices.common.SearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;
import utility.ConfigUtils;

/*sharanya code*/
public class InventoryAction extends DSAction {
	
	private String itemID;
	private String Desc;
	private String Price;
	private String AvailInv; 
	private static final long serialVersionUID = 1L;
    private OrderTranHeader orderTran;
	
	private PLUItemIfc pl;
	Logger logger=Logger.getLogger(InventoryAction.SUCCESS);
	
	//execute method
	public String execute() {
		return SUCCESS;
	}
	
	//setter & getter methods
	
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getAvailInv() {
		return AvailInv;
	}
	public void setAvailInv(String availInv) {
		AvailInv = availInv;
	}

	
	public String inventoryLookup(){
		try{
		LookUpItemIfc remoteBean = DKartContext.getLookupItem();
		SearchCriteriaIfc sc = new SearchCriteria();
		sc.setCheckInventory(true);
		
		/*sc.setStoreID(ConfigUtils.getInstance().getSDSStoreID());*/
		
		/*sc.setStoreID(orderTran.getId().getRtStrId());*/
		sc.setStoreID(null);
		if(sc.getStoreID()==null)
		
		{
		sc.setItemIdOrUPC(itemDetails());
	    
	    sc.setItemID(itemDetails());
	    pl=remoteBean.lookUpItemById(sc);
	    if(pl==null){
	    	super.addActionError("No item found with this information");
	    }
		}
		}
		catch(Exception e){
			super.addActionError("No item found with this information");
			/*logger.info(e);*/
		}
		   return SUCCESS;
        }
	

	public String itemDetails() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ajax callS  ItemDetails : "+itemID);
			try
        	{
        		Long.parseLong(itemID);
        		String []itmIdOrDes=itemID.split("---");
        		itemID = itmIdOrDes[0];//setting item ID
        	}
        	catch(NumberFormatException ne)
        	{   
        		
        		String []itmIdOrDes=itemID.split("---");
	        		try
	        		{
	        			itemID = itmIdOrDes[0];//setting item ID
		        		Long.parseLong(itemID);
	        		}
	        		catch(NumberFormatException nfe)
	            	{   
	        			itemID = itmIdOrDes[1];//setting item ID
	            	}
	        }
			
		  return itemID;
	   }

	public PLUItemIfc getPl() {
		return pl;
	}


	public void setPl(PLUItemIfc pl) {
		this.pl = pl;
	}
}