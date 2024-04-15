package timer;

import javax.ejb.Timer;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rispl.sds.offline.invoice.SDSOfflineInvoiceServiceImpl;

public class GenerateOfflineInvoice {

	private static final Logger LOGGER = LogManager.getLogger(GenerateOfflineInvoice.class);
	@Inject
	SDSOfflineInvoiceServiceImpl invoiceService;

	//chiranjibee comments for sending the cancel Order to rwms
	public void generateOfflineInvoices(Timer time) {
		try {
			LOGGER.info("Calling Generate Offline Invoices");
			invoiceService.generateOfflineInvoice();
			LOGGER.info("Completed Calling Generate Offline Invoices");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
