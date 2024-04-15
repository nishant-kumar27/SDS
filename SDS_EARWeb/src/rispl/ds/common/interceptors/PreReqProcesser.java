package rispl.ds.common.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.ds.DSAction.SESSION;

public class PreReqProcesser implements Interceptor {

	private static final long serialVersionUID = 1L;
	static Logger LOGGER = LogManager.getLogger(PreReqProcesser.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		handleBackButton();
		
		/* pre-processing */
		String output = "Pre-Processing";
//		System.out.println(output +" "+ invocation.getAction().getClass().getName());
		LOGGER.info(output +" Class: "+ invocation.getAction().getClass().getName());

		Map<String, Object> sessionMap = invocation.getInvocationContext().getSession();
		/* String user = (String)sessionMap.get("login"); */

		/*
		 * if(user == null &&
		 * !invocation.getAction().getClass().getName().equals(
		 * "BookOrderAction") ){ return "login"; }
		 * 
		 * else
		 */
		if (sessionMap != null && invocation.getAction().getClass().getName().equals("rispl.ds.customer.CustomerAction")
			&& invocation.getProxy().getMethod().equals("createNewOrder") ){
			//sessionMap.put(SESSION.ORDER_TRANSACTION.toString(), null);
			sessionMap.remove(SESSION.ORDER_TRANSACTION.getValue());
		}
		LOGGER.info(output +" Method: "+invocation.getProxy().getMethod());
		
		/*if (ServletActionContext.getResponse() != null  &&  ) {
			ServletActionContext.getResponse().setHeader("Cache-control", "no-cache, no-store, must-revalidate");
			ServletActionContext.getResponse().setHeader("Pragma", "no-cache");
			ServletActionContext.getResponse().setHeader("Expires", "0");
			ServletActionContext.getResponse().setHeader("Vary", "*");
		}*/

		/* call action or next interceptor */
		String result = invocation.invoke();

		/* post-processing */

		output = "Post-Processing";
		LOGGER.info(output +" Method: "+invocation.getProxy().getMethod());
		LOGGER.info("Invocation result: " + result);
		
 		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void handleBackButton() {
		Map session = (Map) ActionContext.getContext().get("session");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		if (session.get("CurrentPageURL") != null)
		{
			session.put("PreviousPageURL", session.get("CurrentPageURL"));
		}
		 
		session.put("CurrentPageURL", request.getRequestURL());
		
	}

	public void init() {
	}

	public void destroy() {
	}

}