package rispl.ds.homepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dkart.services.ejb.utils.UtilsBeanRemote;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class HomePageAction extends DSAction {

	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = LogManager.getLogger(HomePageAction.class);
	
	String homePageUrl;

	@Override
	public String execute() throws Exception {
		setDefaultHomePage();
		long empRoleId = getEmployee().getRoleId();
		UtilsBeanRemote utilsRemote = DKartContext.getUtilsBean();
		String url = utilsRemote.getHomePageForRole(empRoleId);
		if (!url.isEmpty())// && !url.equalsIgnoreCase("paramterConfig"))
			homePageUrl = url;
		LOGGER.info("Logged in employee homepage is "+homePageUrl);
		return super.execute();
	}

	void setDefaultHomePage() {
		homePageUrl = getText("default.homepage","empty");
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

}
