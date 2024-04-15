package rispl.dkart.services.ejb;

import rispl.dkart.services.entities.RisplShippmentMethodEntity;
import java.util.List;
import java.util.ArrayList;
import rispl.rms.inventory.lookup.RMSInventoryDetailService;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import rispl.dk.itemLookUp.PLUItemIfc;
import rispl.dkservices.common.SearchCriteriaIfc;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import rispl.dkart.services.DkartServices;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import org.apache.logging.log4j.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import rispl.services.item.AbstractItemService;

@Stateless(mappedName = "lookUpItem")
@LocalBean
public class LookUpItem extends AbstractItemService implements LookUpItemIfc
{
    private static final Logger LOGGER;
    @Inject
    @DkartServices.SdsEntityManagerFactory
    EntityManagerFactory emf;
    
    static {
        LOGGER = LogManager.getLogger((Class)LookUpItem.class);
    }
    
    @PostConstruct
    void init() {
        this.em = this.emf.createEntityManager();
    }
    
    public PLUItemIfc lookUpItemById(final SearchCriteriaIfc criteria) {
        PLUItemIfc item = null;
        BigDecimal inventoryAvailable = BigDecimal.ZERO;
        try {
            LookUpItem.LOGGER.info("looking up the plu item from local database...");
            item = this.lookUpItem(criteria);
        }
        catch (NoResultException ne) {
            LookUpItem.LOGGER.warn("item :" + criteria.getItemIdOrUPC() + " not found in DB for store :" + criteria.getStoreID());
            throw ne;
        }
        catch (Exception e) {
            LookUpItem.LOGGER.warn((Object)e);
        }
        try {
            if (criteria.isCheckInventory()) {
                LookUpItem.LOGGER.info("looking up for inventory....");
                final RMSInventoryDetailService inventoryService = new RMSInventoryDetailService();
                final String itemId = item.getItem().getId().getItmId();
                inventoryAvailable = inventoryService.lookupInventory(itemId);
            }
            item.setInventory(inventoryAvailable);
        }
        catch (Exception e) {
            LookUpItem.LOGGER.error("error occured while looking up for the inventory service...." + e.getMessage());
            e.printStackTrace();
        }
        return item;
    }
    
    public ArrayList<String> lookForItemIdsAndDesc(final String ItemIdOrDesc, final int maximumValues, final String storeId) {
        ArrayList<String> values = null;
        try {
            LookUpItem.LOGGER.info("lookup item with item id or description.......");
            values = (ArrayList<String>)this.getItemByIdsOrDesc(ItemIdOrDesc, maximumValues, storeId);
        }
        catch (Exception e) {
            LookUpItem.LOGGER.error("exception while looking for item id or description....." + e.getMessage());
            e.printStackTrace();
        }
        return values;
    }
    
    public String lookForItemVpn(final String term) {
        String itemVpn = null;
        try {
            LookUpItem.LOGGER.info("lookup item vpn with item id .....");
            itemVpn = this.getItemVpnById(term);
        }
        catch (Exception e) {
            LookUpItem.LOGGER.error("exception while looking for item id or description....." + e.getMessage());
            e.printStackTrace();
        }
        return itemVpn;
    }
    
    public ArrayList<PLUItemIfc> lookForServiceItem(final String storeId) {
        ArrayList<PLUItemIfc> values = null;
        try {
            LookUpItem.LOGGER.info("lookup item with item id or description.......");
            values = (ArrayList<PLUItemIfc>)this.getServiceItems(storeId);
        }
        catch (Exception e) {
            LookUpItem.LOGGER.error("exception while looking for item id or description....." + e.getMessage());
            e.printStackTrace();
        }
        return values;
    }
    
    public List<RisplShippmentMethodEntity> lookForServiceMethods() {
        List<RisplShippmentMethodEntity> values = null;
        try {
            LookUpItem.LOGGER.info("lookup item with item id or description.......");
            values = (List<RisplShippmentMethodEntity>)this.getShipping_Methods();
        }
        catch (Exception e) {
            LookUpItem.LOGGER.error("exception while looking for item id or description....." + e.getMessage());
            e.printStackTrace();
        }
        return values;
    }

	@Override
	public String lookForItemExciseTax(String term) {
		// TODO Auto-generated method stub
		return null;
	}
}