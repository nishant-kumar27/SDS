package rispl.dk.itemLookUp;

import java.io.Serializable;
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

public interface PLUItemIfc extends Serializable{

	
	
	public Item getItem() ;

	public void setItem(Item item) ;
	
	public ItemColor getItemColor();


	public void setItemColor(ItemColor itemColor) ;


	public List<ItemMsg> getItemMsg() ;


	public void setItemMsg(List<ItemMsg> itemMsg);


	public ItemPrice getItemPrice() ;

	public void setItemPrice(ItemPrice itemPrice) ;


	public ItemRltdItm getItemRelatedItem();

	public void setItemRelatedItem(ItemRltdItm itemRelatedItem) ;


	public ItemSize getItemSize() ;


	public void setItemSize(ItemSize itemSize);


	public ItemStyle getItemStyle() ;


	public void setItemStyle(ItemStyle itemStyle) ;


	public ItemSimpProm getItemSimplePromotions();


	public void setItemSimplePromotions(ItemSimpProm itemSimplePromotions) ;
	
	public void setInventory(BigDecimal inventory);
	
	public BigDecimal getInventory();
	/*public String getItemId() ;
	
	public void setItemId(String itmId);
	
	public String getIdItemPos() ;
	
	public void setIdItemPos(String idItmPos) ;
	
	public String getRetailStrId();
	
	public void setRetailStrId(String rtStrId) ;
		
	public String getItemBrandName();

	public void setItemBrandName(String itmBrnNm) ;
	
	public String getItemDesc();

	public void setItemDesc(String itmDesc);

	public String getItemDamageDscFlg() ;

	public void setItemDmgDscFlg(String itmDmgDscFlg);

	public String getItemDscFlg();

	public void setItemDscFlg(String itmDscFlg) ;

	public String getItemImgLoc();

	public void setItemImgLoc(String itmImgLoc) ;

	public String getItmKtId() ;

	public void setItmKtId(String itmKtId);

	public String getItmKtStCd() ;

	public void setItmKtStCd(String itmKtStCd) ;

	public BigDecimal getItmMfId() ;

	public void setItmMfId(BigDecimal itmMfId) ;

	public String getItmMrcHrcLvCd() ;
	
	public void setItmMrcHrcLvCd(String itmMrcHrcLvCd);
	
	public int getItmMrcStrcId();

	public void setItmMrcStrcId(int itmMrcStrcId) ;
	

	public String getItmMrhrcGpId() ;

	public void setItmMrhrcGpId(String itmMrhrcGpId);

	public String getItmPosDptId() ;
	
	public void setItmPosDptId(String itmPosDptId);

	public Object getItmRcrdCrtTs();

	public void setItmRcrdCrtTs(Object itmRcrdCrtTs) ;

	public Object getItmRcrdMdfTs() ;
	

	public void setItmRcrdMdfTs(Object itmRcrdMdfTs) ;
	
	
	public String getItmRgstryFl() ;

	public void setItmRgstryFl(String itmRgstryFl) ;

	public String getItmSbstIdnFlg() ;

	public void setItmSbstIdnFlg(String itmSbstIdnFlg) ;

	public String getItmShrtDesc() ;

	public void setItmShrtDesc(String itmShrtDesc) ;

	public String getItmSlsAznFlg() ;

	public void setItmSlsAznFlg(String itmSlsAznFlg) ;

	public String getItmSrlzdFlg() ;

	public void setItmSrlzdFlg(String itmSrlzdFlg) ;

	public String getItmSrzCptTmCd() ;

	public void setItmSrzCptTmCd(String itmSrzCptTmCd);

	public String getItmSzReqFlg() ;

	public void setItmSzReqFlg(String itmSzReqFlg) ;

	public String getItmTxExmCd() ;

	public void setItmTxExmCd(String itmTxExmCd) ;
	

	public BigDecimal getItmTxGpId() ;

	public void setItmTxGpId(BigDecimal itmTxGpId);

	public String getItmTyCd();

	public void setItmTyCd(String itmTyCd);
	
	
	public String getItmClrCd() ;
	
	public void setItmClrCd(String itmClrCd) ;
	
	public String getLcl() ;
	
	public void setLcl(String lcl) ;

	public String getClrDesc() ;
	
	public void setClrDesc(String clrDesc);

	public String getClrNm() ;
	public void setClrNm(String clrNm) ;
	
	
	public void setItemMessage(String s);
	
	public String getItemMessage();
	
	public void setItemMessageDisplay(int value);
	
	public int getItemMessageDisplay();
	//////price and promotions
	
	public void  setItemPrice(BigDecimal price);
	
	public BigDecimal getItemPrice();
	
	public void  setItemSellingPrice(BigDecimal price);
	
	public BigDecimal getItemSellingPrice();
	
	
	public void setSimplePromotionRules(PluSimplePromotionsRulesIfc rules);
	
	
	public PluSimplePromotionsRulesIfc getSimplePromotionRules();
	
	
	public void setItemSize(String name);
	
	public String getItemSize();*/
	
}
