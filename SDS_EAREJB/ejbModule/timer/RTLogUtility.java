package timer;

import javax.ejb.Timer;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.retailsols.sds.rtlog.util.SDSRTLogUtilImpl;

public class RTLogUtility {

	private static final Logger LOGGER = LogManager.getLogger(RTLogUtility.class);
	
	@Inject
	SDSRTLogUtilImpl rtlogUtil;
	
	public void cancelInvalidTrx(Timer timer){
		try {
			rtlogUtil.cancelInvalidOrderTransaction();
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}
}