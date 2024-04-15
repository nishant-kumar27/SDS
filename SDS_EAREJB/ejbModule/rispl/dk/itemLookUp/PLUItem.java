package rispl.dk.itemLookUp;

import java.math.BigDecimal;
import java.util.List;

import rispl.dkart.services.entities.Item;
import rispl.dkart.services.entities.ItemColor;
import rispl.dkart.services.entities.ItemMsg;
import rispl.dkart.services.entities.ItemPrice;
import rispl.dkart.services.entities.ItemRltdItm;
import rispl.dkart.services.entities.ItemSimpProm;
import rispl.dkart.services.entities.ItemSize;
import rispl.dkart.services.entities.ItemStyle;

public class PLUItem implements PLUItemIfc{

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Item item;
	
	private ItemColor itemColor;
	
	private List<ItemMsg> itemMsg;
	
	private ItemPrice itemPrice;
	
	private ItemRltdItm itemRelatedItem;
	
	private ItemSize itemSize;
	
	private ItemStyle itemStyle;
	
	private ItemSimpProm itemSimplePromotions;
	
	private BigDecimal inventory;
	
	
	
	public void setInventory(BigDecimal inventory){
		this.inventory=inventory;
		
	}
	
	public BigDecimal getInventory()
	{
		return this.inventory;
	}
	
	public ItemColor getItemColor() {
		return itemColor;
	}


	public void setItemColor(ItemColor itemColor) {
		this.itemColor = itemColor;
	}


	public List<ItemMsg> getItemMsg() {
		return itemMsg;
	}


	public void setItemMsg(List<ItemMsg> itemMsg) {
		this.itemMsg = itemMsg;
	}


	public ItemPrice getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(ItemPrice itemPrice) {
		this.itemPrice = itemPrice;
	}


	public ItemRltdItm getItemRelatedItem() {
		return itemRelatedItem;
	}


	public void setItemRelatedItem(ItemRltdItm itemRelatedItem) {
		this.itemRelatedItem = itemRelatedItem;
	}


	public ItemSize getItemSize() {
		return itemSize;
	}


	public void setItemSize(ItemSize itemSize) {
		this.itemSize = itemSize;
	}


	public ItemStyle getItemStyle() {
		return itemStyle;
	}


	public void setItemStyle(ItemStyle itemStyle) {
		this.itemStyle = itemStyle;
	}


	public ItemSimpProm getItemSimplePromotions() {
		return itemSimplePromotions;
	}


	public void setItemSimplePromotions(ItemSimpProm itemSimplePromotions) {
		this.itemSimplePromotions = itemSimplePromotions;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}

}
