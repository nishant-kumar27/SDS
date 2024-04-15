package rispl.ds.common.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dk.Employee.EmployeeIfc;
import rispl.ds.DSAction.SESSION;

public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;
	Logger LOGGER = LogManager.getLogger(AuthenticationInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Map<String, Object> session = invocation.getInvocationContext().getSession();

		EmployeeIfc employee = (EmployeeIfc) session.get(SESSION.EMPLOYEE.getValue());
		LOGGER.info("Employee ID in the session: {}", (employee != null ? employee.getEmployeeId() : null));
		if (employee == null /* || SecurityUtils.getSubject() == null */) {
			// TODO uncomment after testing
			//HttpServletResponse response = ServletActionContext.getResponse();
			//response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return "logout";
		} else {
			/*String actionCalled = ActionContext.getContext().getName();

			boolean isPermitted = SecurityUtils.getSecurityManager()
					.isPermitted(SecurityUtils.getSubject().getPrincipals(), actionCalled);
			System.out.println(SecurityUtils.getSubject().getPrincipals() + "\n" + isPermitted);
			if (!isPermitted) {
				this.actionCalled = actionCalled;
				return "noaccess";
			}*/
		}
		return invocation.invoke();
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

}
