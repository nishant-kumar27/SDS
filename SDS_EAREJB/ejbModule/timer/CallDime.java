package timer;

import javax.ejb.Timer;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dk.scheduler.dime.RisplDimeSchedular;

public class CallDime {
	private static Logger LOGGER = LogManager.getLogger(CallDime.class);

	@Inject
	RisplDimeSchedular risplDimeSchedular;

	public void callDime(Timer time) {
		try {
			LOGGER.info("Calling Dime Scheduler");

			risplDimeSchedular.callDimeSchedular();

			LOGGER.info("Dime Scheduler Completed Successfully");
		} catch (Exception e) {
			LOGGER.error("Unable to Process Files");
			LOGGER.error(e.getMessage());
		}
	}           
}
