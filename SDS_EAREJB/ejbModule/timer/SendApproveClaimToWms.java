package timer;

import javax.ejb.Timer;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rsi.dk.claim.approve.service.ClaimApproveStatusService;

public class SendApproveClaimToWms {

	private static final Logger LOGGER = LogManager.getLogger(SendApproveClaimToWms.class);
	@Inject
	ClaimApproveStatusService approveStatus;

	//chiranjibee comments for sending the approve claim to rwms
	public void sendApproveClaimToWms(Timer time) {
		try {
			LOGGER.info("Calling Approve Claim Transactions to WMS");
			approveStatus.sendClaimApproveDetailsToWMS();
			LOGGER.info("Completed Approve Claim Transactions to WMS");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	
}