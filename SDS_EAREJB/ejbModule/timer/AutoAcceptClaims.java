package timer;

import javax.ejb.Timer;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dkart.services.ejb.claim.ClaimBean;

public class AutoAcceptClaims {
	private static final Logger LOGGER = LogManager.getLogger(AutoAcceptClaims.class);

	@Inject
	ClaimBean claimBean;

	public void autoAcceptClaims(Timer timer) {
		try {
			LOGGER.info("Checking for Claims which can be auto accepted.");
			claimBean.autoAcceptClaims();
			LOGGER.info("Finished auto accepting of Claims.");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

}
