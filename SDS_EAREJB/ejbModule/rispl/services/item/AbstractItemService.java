package rispl.services.item;

import rispl.dkart.services.entities.RisplShippmentMethodEntity;
import java.util.Iterator;
import java.math.BigInteger;
import java.util.ArrayList;
import rispl.dkart.services.entities.ItemMsg;
import rispl.dkart.services.entities.ItemColor;
import rispl.dkart.services.entities.ItemSize;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import rispl.dkart.services.entities.ItemSimpProm;
import rispl.dkart.services.entities.ItemPrice;
import java.util.List;
import javax.persistence.Query;
import rispl.dkart.services.entities.Item;
import javax.persistence.NoResultException;
import rispl.dk.itemLookUp.PLUItem;
import rispl.dk.itemLookUp.PLUItemIfc;
import rispl.dkservices.common.SearchCriteriaIfc;
import org.apache.logging.log4j.LogManager;
import rispl.services.Customer.AbstractCustomerService;
import javax.persistence.EntityManager;
import org.apache.logging.log4j.Logger;

public class AbstractItemService implements DatabaseSourceIfc
{
    private static final Logger LOGGER;
    protected EntityManager em;
    
    static {
        LOGGER = LogManager.getLogger((Class)AbstractCustomerService.class);
    }
    
    public PLUItemIfc lookUpItem(final SearchCriteriaIfc search) throws Exception {
        PLUItemIfc pluItem = (PLUItemIfc)new PLUItem();
        try {
            if (search.getItemIdOrUPC() != null && !search.getItemIdOrUPC().equalsIgnoreCase("")) {
                AbstractItemService.LOGGER.info("looking up by itemId or UPC from AbstractItemService");
                pluItem = this.lookUpItemByIdOrUpc(pluItem, search.getItemIdOrUPC(), search.getStoreID());
            }
            else if (search.getItemID() != null && !search.getItemID().equalsIgnoreCase("")) {
                AbstractItemService.LOGGER.info("looking up by item id from AbstractItemService");
                pluItem = this.lookUpItemById(pluItem, search.getItemID(), search.getStoreID());
            }
            else if (search.getUpc() != null && !search.getUpc().equalsIgnoreCase("")) {
                AbstractItemService.LOGGER.info("looking up by UPC from AbstractItemService");
                pluItem = this.lookUpItemByUPC(pluItem, search.getUpc(), search.getStoreID());
            }
            else if (search.getDepartmentID() != null && !search.getDepartmentID().equalsIgnoreCase("")) {
                AbstractItemService.LOGGER.info("looking up by deparment from AbstractItemService");
                pluItem = this.lookUpItemByDepartment(pluItem);
            }
            else if (search.getItemIdOrUPC() != null && !search.getItemIdOrUPC().equalsIgnoreCase("")) {
                AbstractItemService.LOGGER.info("looking up by itemId or UPC from AbstractItemService");
                pluItem = this.lookUpItemByIdOrUpc(pluItem, search.getItemIdOrUPC(), search.getStoreID());
            }
            if (pluItem == null) {
                return null;
            }
            this.lookupItemAllDetails(pluItem);
        }
        catch (NoResultException ne) {
            AbstractItemService.LOGGER.error("Exception while looking up for the item.." + ne.getMessage());
            throw ne;
        }
        catch (Exception e) {
            AbstractItemService.LOGGER.error("Exception while looking up for the item.." + e.getMessage());
            throw new Exception(e);
        }
        return pluItem;
    }
    
    private PLUItemIfc lookupItemAllDetails(PLUItemIfc pluItem) throws Exception {
        this.lookUpItemPrice(pluItem);
        if (pluItem != null && pluItem.getItem().getItmSzCd() != null && !pluItem.getItem().getItmSzCd().equalsIgnoreCase("")) {
            pluItem = this.lookUpItemSize(pluItem);
        }
        if (pluItem != null && pluItem.getItem().getItmClrCd() != null && !pluItem.getItem().getItmClrCd().equalsIgnoreCase("")) {
            pluItem = this.lookUpItemColor(pluItem);
        }
        if (pluItem != null && pluItem.getItem().getDispMsgId() != null && !pluItem.getItem().getDispMsgId().toString().equalsIgnoreCase("")) {
            pluItem = this.lookUpItemMessages(pluItem);
        }
        this.lookUpItemPromo(pluItem);
        return pluItem;
    }
    
    public PLUItemIfc lookUpItemById(final PLUItemIfc pluItem, final String itemId, final String storeId) throws NoResultException {
        final Query query = (Query)this.em.createNamedQuery("ITEM_MASTER_ITEM_ID", (Class)Item.class).setParameter("itemId", (Object)itemId).setParameter("storeId", (Object)storeId);
        final List<Item> itemDetails = (List<Item>)query.getResultList();
        if (itemDetails != null && itemDetails.size() > 0) {
            final Item item = itemDetails.get(0);
            pluItem.setItem(item);
            return pluItem;
        }
        return null;
    }
    
    public PLUItemIfc lookUpItemByIdOrUpc(final PLUItemIfc pluItem, final String itemId, final String storeId) throws NoResultException {
        if (storeId == null) {
            final Query query = (Query)this.em.createNamedQuery("ITEM_MASTER_FIND_BY_ITEMID_POSITEMID_NSTR", (Class)Item.class).setParameter("itemId", (Object)itemId).setParameter("upcItemId", (Object)itemId);
            final List<Item> itemDetails = (List<Item>)query.getResultList();
            if (itemDetails == null || itemDetails.size() <= 0) {
                throw new NoResultException();
            }
            final Item item = itemDetails.get(0);
            pluItem.setItem(item);
        }
        else {
            final Query query = (Query)this.em.createNamedQuery("ITEM_MASTER_FIND_BY_ITEMID_POSITEMID", (Class)Item.class).setParameter("itemId", (Object)itemId).setParameter("storeId", (Object)storeId).setParameter("upcItemId", (Object)itemId);
            final List<Item> itemDetails = (List<Item>)query.getResultList();
            if (itemDetails == null || itemDetails.size() <= 0) {
                throw new NoResultException();
            }
            final Item item = itemDetails.get(0);
            pluItem.setItem(item);
        }
        return pluItem;
    }
    
    public PLUItemIfc lookUpItemByUPC(final PLUItemIfc pluItem, final String barcode, final String storeId) {
        final Query query = (Query)this.em.createNamedQuery("ITEM_MASTER_UPC", (Class)Item.class).setParameter("upcItemId", (Object)barcode).setParameter("storeId", (Object)storeId);
        final List<Item> itemDetails = (List<Item>)query.getResultList();
        if (itemDetails != null && itemDetails.size() > 0) {
            final Item item = itemDetails.get(0);
            pluItem.setItem(item);
            return pluItem;
        }
        return null;
    }
    
    public PLUItemIfc lookUpItemPrice(final PLUItemIfc pluItem) throws NoResultException {
        AbstractItemService.LOGGER.info("Looking up for the item price");
        final Query price = this.em.createNativeQuery("SELECT * FROM RISPL_DK_ITEM_PRICE itemPrice WHERE ITM_ID = ? AND RT_STR_ID = ? AND EV_EF_TMP <= SYSDATE ORDER BY EV_EF_TMP DESC", (Class)ItemPrice.class);
        price.setHint("eclipselink.refresh", (Object)true);
        price.setParameter(1, (Object)pluItem.getItem().getId().getItmId());
        price.setParameter(2, (Object)pluItem.getItem().getId().getRtStrId());
        final List<ItemPrice> prices = (List<ItemPrice>)price.getResultList();
        if (prices.size() > 0) {
            pluItem.setItemPrice((ItemPrice)prices.get(0));
        }
        else {
            pluItem.setItemPrice((ItemPrice)null);
        }
        return pluItem;
    }
    
    public PLUItemIfc lookUpItemPromo(final PLUItemIfc pluItem) throws Exception {
        AbstractItemService.LOGGER.info("Looking up for the item promotions");
        final Query promoPrice = this.em.createNativeQuery("SELECT * FROM RISPL_DK_ITEM_SIMP_PROM itemPrice WHERE itemPrice.PROM_OVRD_PRC IN   (SELECT MIN(PROM_OVRD_PRC)   FROM RISPL_DK_ITEM_SIMP_PROM   WHERE PROM_EFCT_DT_TM <=? AND PROM_EXP_DT_TM >= ?  AND ITM_ID       =?   AND RT_STR_ID    =?   )", (Class)ItemSimpProm.class);
        final GregorianCalendar cal = (GregorianCalendar)Calendar.getInstance();
        final Timestamp stamp = new Timestamp(cal.getTime().getTime());
        promoPrice.setParameter(1, (Object)stamp);
        promoPrice.setParameter(2, (Object)stamp);
        promoPrice.setParameter(3, (Object)pluItem.getItem().getId().getItmId());
        promoPrice.setParameter(4, (Object)pluItem.getItem().getId().getRtStrId());
        final List<ItemSimpProm> promoPrices = (List<ItemSimpProm>)promoPrice.getResultList();
        if (promoPrices.size() > 0) {
            pluItem.setItemSimplePromotions((ItemSimpProm)promoPrices.get(0));
        }
        else {
            pluItem.setItemSimplePromotions((ItemSimpProm)null);
        }
        return pluItem;
    }
    
    public PLUItemIfc lookUpItemSize(final PLUItemIfc pluItem) throws Exception {
        final Query itemSize = this.em.createNativeQuery("select * from RISPL_DK_ITEM_SIZE itemSize where itemSize.ITM_SZ_CD=?", (Class)ItemSize.class);
        final String sizeCode = pluItem.getItem().getItmSzCd();
        if (sizeCode != null && !sizeCode.equalsIgnoreCase("")) {
            itemSize.setParameter(1, (Object)sizeCode);
            final List<ItemSize> sizes = (List<ItemSize>)itemSize.getResultList();
            if (sizes.size() > 0) {
                final ItemSize size = sizes.get(0);
                pluItem.setItemSize(size);
            }
            else {
                pluItem.setItemSize((ItemSize)null);
            }
        }
        else {
            pluItem.setItemSize((ItemSize)null);
        }
        return pluItem;
    }
    
    public PLUItemIfc lookUpItemColor(final PLUItemIfc pluItem) throws Exception {
        final Query itmColor = this.em.createNativeQuery("select * from RISPL_DK_ITEM_COLOR itemColor where itemColor.ITM_CLR_CD=?", (Class)ItemColor.class);
        final String colorCode = pluItem.getItem().getItmSzCd();
        if (colorCode != null && !colorCode.equalsIgnoreCase("")) {
            itmColor.setParameter(1, (Object)colorCode);
            final List<ItemColor> colors = (List<ItemColor>)itmColor.getResultList();
            if (colors.size() > 0) {
                final ItemColor color = colors.get(0);
                pluItem.setItemColor(color);
            }
            else {
                pluItem.setItemColor((ItemColor)null);
            }
        }
        else {
            pluItem.setItemColor((ItemColor)null);
        }
        return pluItem;
    }
    
    public PLUItemIfc lookUpItemMessages(final PLUItemIfc pluItem) throws Exception {
        final Query itemMessages = this.em.createNativeQuery("select * from RISPL_DK_ITEM_MSGS itemMsg where itemMsg.DISP_MSG_ID=?", (Class)ItemMsg.class);
        final String displyCode = pluItem.getItem().getDispMsgId().toString();
        if (displyCode != null && !displyCode.equalsIgnoreCase("")) {
            itemMessages.setParameter(1, (Object)displyCode);
            final List<ItemMsg> msgs = (List<ItemMsg>)itemMessages.getResultList();
            if (msgs.size() > 0) {
                pluItem.setItemMsg((List)msgs);
            }
            else {
                pluItem.setItemMsg((List)null);
            }
        }
        else {
            pluItem.setItemMsg((List)null);
        }
        return pluItem;
    }
    
    public PLUItemIfc lookUpItemByDepartment(final PLUItemIfc plu) {
        return plu;
    }
    
    public PLUItemIfc lookUpItemByIdorUPC(final PLUItemIfc plu) {
        return plu;
    }
    
    public static void main(final String[] args) {
    }
    
    public ArrayList<String> getItemByIdsOrDesc(final String itemIdOrDesc, final int maximumResults, final String storeId) throws NoResultException {
        final ArrayList<String> itemIdsAndDiscriptions = new ArrayList<String>();
        try {
            new BigInteger(itemIdOrDesc);
            AbstractItemService.LOGGER.info("Searching with item id:" + itemIdOrDesc);
            final Query query = this.em.createNamedQuery("ITEM_MASTER_FOR_DISPLAY_POS_ID").setMaxResults(maximumResults).setParameter("storeId", (Object)storeId).setParameter("upcItemId", (Object)("%" + itemIdOrDesc + "%"));
            List<String[]> itemDetails = (List<String[]>)query.getResultList();
            if (itemDetails.size() == 0) {
                final Query queryForItemID = this.em.createNamedQuery("ITEM_MASTER_FOR_DISPLAY_LOV_ITEM_ID").setMaxResults(maximumResults).setParameter("storeId", (Object)storeId).setParameter("itemId", (Object)("%" + itemIdOrDesc + "%"));
                itemDetails = (List<String[]>)queryForItemID.getResultList();
            }
            if (itemDetails == null || itemDetails.size() <= 0) {
                return null;
            }
            for (final Object[] obj : itemDetails) {
                StringBuffer sb = new StringBuffer();
                sb = sb.append((String)obj[0]);
                sb = sb.append("---");
                sb = sb.append((String)obj[1]);
                itemIdsAndDiscriptions.add(sb.toString());
            }
        }
        catch (Exception e) {
            AbstractItemService.LOGGER.info("Searching with item description:" + itemIdOrDesc);
            final Query query2 = this.em.createNamedQuery("ITEM_MASTER_FOR_DISPLAY_LOV_DESC").setParameter("storeId", (Object)storeId).setMaxResults(maximumResults).setParameter("itemDesc", (Object)("%" + itemIdOrDesc.toUpperCase() + "%")).setParameter("idItmPos", (Object)("%" + itemIdOrDesc + "%"));
            final List<String[]> itemDetails2 = (List<String[]>)query2.getResultList();
            if (itemDetails2 != null) {
                for (final Object[] obj2 : itemDetails2) {
                    StringBuffer sb2 = new StringBuffer();
                    sb2 = sb2.append((String)obj2[1]);
                    sb2 = sb2.append("---");
                    sb2 = sb2.append((String)obj2[0]);
                    itemIdsAndDiscriptions.add(sb2.toString());
                }
            }
            if (itemDetails2 == null || itemDetails2.size() <= 0) {
                return null;
            }
            for (int i = 0; i < itemDetails2.size(); ++i) {}
        }
        return itemIdsAndDiscriptions;
    }
    
    public String getItemVpnById(final String term) throws NoResultException {
        Object itemVpn = null;
        String itmVp = null;
        AbstractItemService.LOGGER.info("Festching Item VPN in Abstract Item service");
        try {
            final String sqlQry = "select VPN from rispl_dk_item_attr_vpn where itm_id='" + term + "'";
            final Query qe = this.em.createNativeQuery(sqlQry);
            itemVpn = qe.getSingleResult();
            itmVp = itemVpn.toString();
        }
        catch (NoResultException nre) {
            AbstractItemService.LOGGER.error("NoResultException ");
            return "0";
        }
        catch (Exception e) {
            AbstractItemService.LOGGER.error("Exception while taking Item VPN");
            e.printStackTrace();
        }
        return itmVp;
    	}
    
    public ArrayList<PLUItemIfc> getServiceItems(final String storeId) {
        final ArrayList<PLUItemIfc> pluItem = new ArrayList<PLUItemIfc>();
        try {
            final Query query = (Query)this.em.createNamedQuery("ITEM_MASTER_FOR_SERVICE_ITEMS", (Class)Item.class).setParameter("storeId", (Object)storeId).setParameter("itemType", (Object)"Service");
            final List<Item> itemDetails = (List<Item>)query.getResultList();
            int i = 0;
            for (final Item items : itemDetails) {
                pluItem.add(i, (PLUItemIfc)new PLUItem());
                pluItem.get(i).setItem(items);
                this.lookupItemAllDetails(pluItem.get(i));
                ++i;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return pluItem;
    }
    
    public List<RisplShippmentMethodEntity> getShipping_Methods() {
        List<RisplShippmentMethodEntity> shippingMethod = new ArrayList<RisplShippmentMethodEntity>();
        try {
            final Query query = (Query)this.em.createNamedQuery("ITEM_FOR_SHIPPING_METHODS", (Class)RisplShippmentMethodEntity.class).setParameter("status", (Object)"ACTIVE");
            shippingMethod = (List<RisplShippmentMethodEntity>)query.getResultList();
            System.out.println(shippingMethod.get(0).getCarrierName());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return shippingMethod;
    }
}