package timer;

import javax.ejb.Timer;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rispl.sds.cancel.order.service.CancelOrderServiceImpl;

public class SendCancelOrderToWMS {

	private static final Logger LOGGER = LogManager.getLogger(SendCancelOrderToWMS.class);
	@Inject
	CancelOrderServiceImpl cancelOrderService;

	//chiranjibee comments for sending the cancel Order to rwms
	public void sendCancelOrderToWMS(Timer time) {
		try {
			LOGGER.info("Calling Cancel Order Transactions to Send WMS");
			cancelOrderService.sendCancelOrderDetailsToWMS();
			LOGGER.info("Completed Calling Cancel Order Transactions Send to WMS");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
