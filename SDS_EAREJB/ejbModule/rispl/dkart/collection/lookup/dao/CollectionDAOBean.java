package rispl.dkart.collection.lookup.dao;


import java.util.ArrayList;
import	java.util.List;

import javax.annotation.PostConstruct;
import 	javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import 	com.test.entities.SdsCollectionsDashboard;

import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;

/**
 * Session Bean implementation class CollectionDAOBean
 */
@Stateless(mappedName="collectionDAOBean")
public class CollectionDAOBean implements CollectionDAOBeanIfc {
    /**
     * Default constructor. 
     */
	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	
	CollectionDAO dao;
	
	@PostConstruct
    void init()
    {
    	dao=new CollectionDAO(emf.createEntityManager());
    }
	
    public CollectionDAOBean() {    	
    }

	@Override
	public List<SdsCollectionsDashboard> getCollectionsByEmpId(String empid, String range) {
		// TODO Auto-generated method stub
		
		List<SdsCollectionsDashboard> collections=dao.getCollectionsByEmpId(empid, range);
		return collections;
	}

	@Override
	public ArrayList<CollectionData> getCollectionChartData(String empid, String range) {
		// TODO Auto-generated method stub
		ArrayList<CollectionData> collection=dao.getCollectionChartData(empid, range);
		return collection;
	}

	@Override
	public List<SdsCollectionsDashboard> getCollectionsByEmpId(String empid, CollectionData collection_data) {
		// TODO Auto-generated method stub
		List<SdsCollectionsDashboard> collections=dao.getCollectionsByEmpId(empid, collection_data);
		return collections;
	}

/*	@Override
	public List<SdsCollectionsDashboard> getCollectionsByDivision(int division, String range) {*/
	@Override
	public List<SdsCollectionsDashboard> getCollectionsByDivision(List<Integer> division, String range) {
		// TODO Auto-generated method stub
		List<SdsCollectionsDashboard> collections=dao.getCollectionsByDivision(division, range);
		return collections;
	}

	/*@Override
	public List<SdsCollectionsDashboard> getCollectionsByDivision(int division, CollectionData collection_data) {*/
	@Override
	public List<SdsCollectionsDashboard> getCollectionsByDivision(List<Integer> division, CollectionData collection_data) {
		// TODO Auto-generated method stub
		List<SdsCollectionsDashboard> collections=dao.getCollectionsByDivision(division, collection_data);
		return collections;
	}

	/*@Override
	public ArrayList<CollectionData> getCollectionChartDataByDivision(int division, String range) {*/
	@Override
	public ArrayList<CollectionData> getCollectionChartDataByDivision(List<Integer> division, String range) {
		// TODO Auto-generated method stub
		ArrayList<CollectionData> collections=dao.getCollectionChartDataByDivision(division, range);
		return collections;
	}


	@Override
	public List<SdsCollectionsDashboard> getCollectionsByAllDiv(String range) {
		// TODO Auto-generated method stub
		List<SdsCollectionsDashboard> collections=dao.getCollectionsByAllDiv(range);
		return collections;
	}

	@Override
	public List<SdsCollectionsDashboard> getCollectionsByAllDiv(CollectionData collection_data) {
		// TODO Auto-generated method stub
		List<SdsCollectionsDashboard> collections=dao.getCollectionsByAllDiv(collection_data);
		return collections;
	}

	@Override
	public ArrayList<CollectionData> getCollectionChartDataByAllDiv(String range) {
		// TODO Auto-generated method stub
		ArrayList<CollectionData> collections=dao.getCollectionChartDataByAllDiv(range);
		return collections;
	}
	

}
