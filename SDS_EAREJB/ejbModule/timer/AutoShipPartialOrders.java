package timer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Timer;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.auto.order.updates.AutoUpdateOrders;
import com.test.entities.OrderDetail;

import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.ejb.transaction.OrderTransactions;

public class AutoShipPartialOrders {
	


	private static final Logger LOGGER = LogManager.getLogger(AutoShipPartialOrders.class);
	@Inject
	OrderTransactions orderTransactions;
	@Inject
	AutoUpdateOrders autoUpdateOrders;
	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	@SuppressWarnings("unchecked")
	public void autoShipPartialOrders(Timer time) {
		LOGGER.info("Auto ship partial orders");
		try {			
			EntityManager em = emf.createEntityManager();
			
			
				em.getTransaction().begin();
				
				/// update the order shipment table for the duplicated items
					LOGGER.info("trying to take partial orders.... ");
					Query  updatePartialOrders = em.createNamedQuery("UPD_PARTIAL_ORD", OrderDetail.class);
							
							/*createNativeQuery("SELECT * FROM order_details "
							+ "WHERE ORD_TY=23 AND (SC_ORD BETWEEN 2 AND 3) AND SC_TRAN=2 and to_date(DC_DY_ORD,'YYYY-MM-DD')"
							+ " BETWEEN  to_date('2018-04-25','YYYY-MM-DD' ) and to_date ('2018-04-25', 'YYYY-MM-DD')");*/
					updatePartialOrders.setParameter("from", ("2018-04-16"));	
					updatePartialOrders.setParameter("to", ("2018-04-16"));
				//	.setParameter("from", startDate).setParameter("to", endDate);
					List<String>OrdersList =updatePartialOrders.getResultList();
					

			LOGGER.info("Calling pre Partial order processing orderTransactions");
			
			String orderId = "";
					
			for (String order : OrdersList) { 		      	
		           orderId = order;
					if (OrdersList.size() > 0) {
			
			//orderTransactions.processPartialShipping(orderId,"Auto","Auto");
			LOGGER.info("Completed Post PartialShippingProcessing orderTransactions after 1 order");
			}
			}
			LOGGER.info("Completed Post PartialShippingProcessing orderTransactions after all order");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

}
