package rispl.ds.common.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SecurityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	Logger LOGGER = LogManager.getLogger(SecurityInterceptor.class);
	private String actionName;
	int statusCode = HttpServletResponse.SC_FORBIDDEN;

	public String intercept(ActionInvocation invocation) throws Exception {

		actionName = ActionContext.getContext().getName();

		checkSecurityOverride(invocation);

		boolean isPermitted = SecurityUtils.getSecurityManager().isPermitted(SecurityUtils.getSubject().getPrincipals(),
				actionName);
		LOGGER.info("Employee ID: {}\t Access to: {}\t Permitted: {}", 
				SecurityUtils.getSubject().getPrincipals(), actionName, isPermitted);
		if (!isPermitted) {
			return "noaccess";
		}
		actionName = null;
		
		/*
		 * call this to clear shiro cache
		 * ((RealmSecurityManager)SecurityUtils.getSecurityManager()).getRealms().stream()
					.filter(SdsRealm.class::isInstance)
					.map(SdsRealm.class::cast)
					.forEach(realm -> realm.clearCache(SecurityUtils.getSubject().getPrincipals()));*/
		
		return invocation.invoke();
	}

	private void checkSecurityOverride(ActionInvocation ai) {
		ActionConfig config = ai.getProxy().getConfig();
		Map<String, String> parameters = config.getParams();
		System.out.println(parameters);
		if (parameters.containsKey("overrideSecurityAction"))
			actionName = parameters.get("overrideSecurityAction");
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
