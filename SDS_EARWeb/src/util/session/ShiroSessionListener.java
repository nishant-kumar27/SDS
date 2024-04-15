package util.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

public class ShiroSessionListener extends SessionListenerAdapter {
	
	private static Logger LOGGER = LogManager.getLogger(ShiroSessionListener.class);
	
	@Override
	public void onStart(Session session) {
		LOGGER.debug("SHIRO SESSION START");
	}

	@Override
	public void onStop(Session session) {
		LOGGER.debug("SHIRO SESSION STOP");
	}

	@Override
	public void onExpiration(Session session) {
		LOGGER.debug("SHIRO SESSION EXP");
	}
	
}
