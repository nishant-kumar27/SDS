package rispl.dkart.services.ejb.utils;

import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface UtilsBeanRemote {
	public String getHomePageForRole(long roleID);

	public Map<String, String> getEJBConfig();
}
