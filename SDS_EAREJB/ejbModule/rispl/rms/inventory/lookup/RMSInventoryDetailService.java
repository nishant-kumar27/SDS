package rispl.rms.inventory.lookup;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import com.oracle.www.retail.integration.base.bo.InvAvailColDesc.v1.InvAvailColDesc;
import com.oracle.www.retail.integration.base.bo.InvAvailCriVo.v1.InvAvailCriVo;
import com.oracle.www.retail.integration.base.bo.InvAvailCriVo.v1.InvLocation;
import com.oracle.www.retail.integration.base.bo.InvAvailCriVo.v1.Loc_type;
import com.oracle.www.retail.integration.base.bo.InvAvailCriVo.v1.Store_pickup_ind;
import com.oracle.www.retail.integration.services.exception.v1.IllegalArgumentWSFaultException;
import com.oracle.www.retail.integration.services.exception.v1.IllegalStateWSFaultException;
import com.oracle.www.retail.integration.services.exception.v1.ValidationWSFaultException;
import com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortBindingStub;
import com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailServiceLocator;

import utility.ConfigUtils;

public class RMSInventoryDetailService {

	public BigDecimal lookupInventory(String itemId) {

		BigDecimal inventoryAvailable = BigDecimal.ZERO;
		try {
			long locationId = Long.parseLong(ConfigUtils.getInstance().getWarehouseLocationID());
			int channelId = Integer.parseInt(ConfigUtils.getInstance().getWarehouseChannelID());
			
			InventoryDetailServiceLocator serviceLocator = new InventoryDetailServiceLocator();
			InventoryDetailPortBindingStub stub = (InventoryDetailPortBindingStub) serviceLocator
					.getInventoryDetailPort();
			InvAvailCriVo invAvailCriVo = new InvAvailCriVo();

			 //String items[] = {"100053636123"};
			invAvailCriVo.setItems(new String[]{itemId});

			InvLocation locations[] = new InvLocation[1];

			InvLocation location = new InvLocation();
		 location.setLocation(1001);/////from property file
			location.setLocation(locationId);

			location.setLoc_type(Loc_type.W);
			location.setChannel_id(channelId);

			locations[0] = location;

			invAvailCriVo.setInvLocation(locations);
			invAvailCriVo.setStore_pickup_ind(Store_pickup_ind.Y);

			InvAvailColDesc colDesc = stub.lookupInvAvailCriVo(invAvailCriVo);
			if (colDesc.getCollection_size() > 0) {
				inventoryAvailable = colDesc.getInvAvailDesc()[0].getAvailable_qty();
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentWSFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateWSFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationWSFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inventoryAvailable;
	}
	
	public List<Object[]> lookupBulkInventory(List<String> itemIdsList) {
		List<Object[]> inventoryDetail = new ArrayList<>();
		
		if (itemIdsList != null && itemIdsList.size() > 0) {
			try {
				long locationId = Long.parseLong(ConfigUtils.getInstance().getWarehouseLocationID());
				int channelId = Integer.parseInt(ConfigUtils.getInstance().getWarehouseChannelID());
				
				String[] items = itemIdsList.toArray(new String[itemIdsList.size()]);
				InventoryDetailServiceLocator serviceLocator = new InventoryDetailServiceLocator();
				InventoryDetailPortBindingStub stub = (InventoryDetailPortBindingStub) serviceLocator
						.getInventoryDetailPort();

				InvAvailCriVo invAvailCriVo = new InvAvailCriVo();
				invAvailCriVo.setItems(items);
				invAvailCriVo.setInvLocation(new InvLocation[] { new InvLocation(locationId, Loc_type.W, channelId) });
				invAvailCriVo.setStore_pickup_ind(Store_pickup_ind.Y);

				InvAvailColDesc colDesc = stub.lookupInvAvailCriVo(invAvailCriVo);

				for (int i = 0; colDesc != null && colDesc.getCollection_size() > 0
						&& i < colDesc.getCollection_size(); i++) {
					inventoryDetail.add(new Object[] { items[i], colDesc.getInvAvailDesc(i).getAvailable_qty() });
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return inventoryDetail;
	}
}

