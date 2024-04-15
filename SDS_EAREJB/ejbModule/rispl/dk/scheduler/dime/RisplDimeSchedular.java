package rispl.dk.scheduler.dime;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dime.schedular.imports.RisplDimeUtility;
import utility.ConfigUtils;

@ManagedBean
public class RisplDimeSchedular{ //implements Serializable {

	//private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(RisplDimeSchedular.class);

	@Inject
	RisplDimeUtility dimeUtility;
	
	public synchronized void callDimeSchedular() {

		StringBuffer incomingLoc = null;
		StringBuffer backupLoc = null;

		try {

			incomingLoc = new StringBuffer(ConfigUtils.getInstance().getDIMEIncomingPath());
			backupLoc = new StringBuffer(ConfigUtils.getInstance().getDIMEBackupPath());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		if (incomingLoc != null && backupLoc != null) {
			//RisplDimeUtilityIfc dimeUtilityIfc = new RisplDimeUtility();
			dimeUtility.processMOMFiles(incomingLoc, backupLoc);
		}
	}
}