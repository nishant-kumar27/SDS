package rispl.dkart.services.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import rispl.dk.itemLookUp.PLUItemIfc;
import rispl.dkart.services.entities.RisplShippmentMethodEntity;
import rispl.dkservices.common.SearchCriteriaIfc;

@Remote
public interface LookUpItemIfc {

	
	public PLUItemIfc lookUpItemById(SearchCriteriaIfc criteria);
	
	
	public ArrayList<String> lookForItemIdsAndDesc(String ItemIdOrDesc,int maximumValues,String storeId);
	public String lookForItemVpn(String term);
	public String lookForItemExciseTax(String term);
	
	public ArrayList<PLUItemIfc> lookForServiceItem(String storeId);

	public List<RisplShippmentMethodEntity> lookForServiceMethods();
	
}
