package rispl.dkart.services;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dkart.services.entities.customer.segment.RISPLDKCustomerSegment;
import rispl.dkart.services.entities.customer.segment.RisplDkSegStore;

@Dependent
public class DkartServices {

	private static final Logger LOGGER = LogManager.getLogger(DkartServices.class);

	private static final String PERSISTENCE_UNIT_NAME = "DkartNewBusinesslogic";

	@Produces
	@PersistenceUnit(unitName = PERSISTENCE_UNIT_NAME)
	@SdsEntityManagerFactory
	private EntityManagerFactory factory;// = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	// @PersistenceContext(unitName="XstoreDatabase")
	//private static DkartServices service = null;

	@Produces
	@SdsEntityManager
	protected EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public @interface SdsEntityManager {

	}

	public @interface SdsEntityManagerFactory {

	}

	public void releaseResources() {
		if (factory != null) {
			factory.close();
			LOGGER.info("Persistance " + PERSISTENCE_UNIT_NAME + " released.");
			System.out.println("Persistance " + PERSISTENCE_UNIT_NAME + " released.");
		}
	}

	//Krishna: temp work for Demo
	// TODO: Move outside this class
	public String getStoreID(String customerID) {
		String result = "";
		String segmentID = getSegmentId(customerID);
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT r FROM  RisplDkSegStore r WHERE r.segmentId=?1", RisplDkSegStore.class);
		query.setParameter(1, segmentID);
		List<RisplDkSegStore> store = query.getResultList();
		if (store.size() > 0)
			result = store.get(0).getStore();

		return result;

	}

	//TODO: Move outside this class
	protected String getSegmentId(String customerID) {
		String result = "";
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT r FROM RISPLDKCustomerSegment r WHERE r.custId =?1",
				RISPLDKCustomerSegment.class);
		query.setParameter(1, customerID);
		List<RISPLDKCustomerSegment> Qresult = query.getResultList();
		if (Qresult.size() > 0) {
			result = Qresult.get(0).getSegmentId();
		}
		return result;
	}

	/*
	 * public static void main(String args[]) { try { //DkartServices services =
	 * new DkartServices();
	 * 
	 * AbstractItemService abstractItemService = new AbstractItemService();
	 * SearchCriteriaIfc searchCrit = new SearchCriteria();
	 * searchCrit.setItemID("100602789"); searchCrit.setStoreID("40410");
	 * PLUItemIfc plu = abstractItemService.lookUpItem(searchCrit);
	 * System.out.println(plu.getItemSimplePromotions().getId().getIdEv());
	 * 
	 * //Query qe=services.getEntityManager().createNamedQuery(
	 * "ITEM_MASTER_FIND_BY_ITEMID_POSITEMID",
	 * Item.class).setParameter("itemId","100000016").setParameter("storeId",
	 * "4040").setParameter("upcItemId", "100000016");
	 * 
	 * //Query
	 * qe=services.getEntityManager().createNamedQuery("FIND_SALES_AGENT",
	 * EmployeeMaster.class).setParameter("agentId","%1301028%").setParameter(
	 * "agentName", "%1301028%"); Query qe1 =
	 * services.getEntityManager().createNamedQuery(
	 * "ITEM_MASTER_FIND_BY_ITEMID_POSITEMID") .setParameter("itemId",
	 * "100000016").setParameter("storeId", "40410") .setParameter("upcItemId",
	 * "100000016"); List<Item> item = qe1.getResultList();
	 * 
	 * System.out.println(item.size()); } catch (Exception se) {
	 * se.printStackTrace(); } }
	 */
}
