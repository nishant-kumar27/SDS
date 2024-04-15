package timer;

import javax.ejb.Timer;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.auto.order.updates.AutoUpdateOrders;
import rispl.services.transaction.save.DkartWMSOrderTransactionService;

public class SendOrdersToWms {

	private static final Logger LOGGER = LogManager.getLogger(SendOrdersToWms.class);
	@Inject
	DkartWMSOrderTransactionService dkartWMSOrderTransactionService;
	@Inject
	AutoUpdateOrders autoUpdateOrders;

	public void sendOrdersToWms(Timer time) {
		LOGGER.info("Sending new orders to WMS");
		try {
			LOGGER.info("Calling Post Transactions to WMS");
			dkartWMSOrderTransactionService.postTransactions();
			LOGGER.info("Completed Post Transactions to WMS");

			// for orders update
			LOGGER.info("lookupForOrdersToDuplicate method in AutoUpdateOrders class");
			autoUpdateOrders.lookupForOrdersToDuplicate();
			
			LOGGER.info("Processed lookupForOrdersToDuplicate method");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
