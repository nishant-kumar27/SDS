package rispl.dkart.collection.lookup.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import com.test.entities.SdsCollectionsDashboard;

@Remote
public interface CollectionDAOBeanIfc {
	public List<SdsCollectionsDashboard> getCollectionsByEmpId(String empid,String range);
	public List<SdsCollectionsDashboard> getCollectionsByEmpId(String empid, CollectionData collection_data);
	
	/*public List<SdsCollectionsDashboard> getCollectionsByDivision(int division, String range);*/
	public List<SdsCollectionsDashboard> getCollectionsByDivision(List<Integer> division, String range);
/*	public List<SdsCollectionsDashboard> getCollectionsByDivision(int division, CollectionData collection_data);*/
	public List<SdsCollectionsDashboard> getCollectionsByDivision(List<Integer> division, CollectionData collection_data);
	
	public ArrayList<CollectionData> getCollectionChartData(String empid, String range);
	/*public ArrayList<CollectionData> getCollectionChartDataByDivision(int division, String range);*/
	public ArrayList<CollectionData> getCollectionChartDataByDivision(List<Integer> division, String range);
	/*Sharanya For Dashboard*/
	public List<SdsCollectionsDashboard> getCollectionsByAllDiv(String range);
	public List<SdsCollectionsDashboard> getCollectionsByAllDiv(CollectionData each);
	public ArrayList<CollectionData> getCollectionChartDataByAllDiv(String range);
	
}
