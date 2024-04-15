package rispl.ds;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rispl.dk.Employee.EmployeeIfc;
import util.currency.CurrencyUtil;

/**
 * <h1>DSAction</h1>
 * <p>
 * Extend this Class so that your class is SessionAware and Employee object is
 * directly accessible from valueStack
 * 
 *
 * @author M Saideep
 * @version 1.0
 */
public class DSAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	
	static protected Logger LOG = LogManager.getLogger(DSAction.class);

	private SessionMap<String, Object> sessionmap;
	private EmployeeIfc employee;
	// Saideep: Set to true to prevent page unload
	private boolean preventBack;
	private String adminRoleId = "1"; // Default
	//Saideep: to override default function name security
	String overrideSecurityAction;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionmap = (SessionMap<String, Object>) session;
		employee = (EmployeeIfc) getFromSession(SESSION.EMPLOYEE);

	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	
	private SessionMap<String, Object> getSessionmap() { return sessionmap; }
	

	public Object getFromSession(SESSION session) {
		return (sessionmap != null) ? sessionmap.get(session.getValue()) : null;
	}

	public void putInSession(SESSION session, Object data) {
		if (sessionmap != null)
			sessionmap.put(session.getValue(), data);
	}

	public Object removeFromSession(SESSION session) {
		if (sessionmap != null)
			return sessionmap.remove(session.getValue());
		return null;
	}

	public boolean sessionContains(SESSION session) {
		if (sessionmap != null)
			return sessionmap.containsKey(session.getValue());
		return false;
	}

	public void invalidateSession() {
		if (sessionmap != null)
			sessionmap.invalidate();
	}

	public enum SESSION {
		EMPLOYEE("employee"), ORDER_TRANSACTION("OrderTransaction"), PLU_ITEM("pluItem"), 
		RETURN_REASONCODE_MAP("ReturnReasonCodesMap"), COLLECTION_DATA("collection_data"), 
		INVOICE_ORDER_TRAN("invoiceOrdTran"), INVOICE_DETAIL("invoiceDetail"), 
		READY_TO_CLAIM_TRANHEADER("ReadyToClaimTranHeader"), 
		CLAIM_TRANHEADER("ClaimTranHeader"), CUSTOMERS("customers"), CUSTOMER("customer"), 
		ORDER_TRANHEADER("OrdTranHeader"), PAYMENT_SESSION_DATA("payment_details"), 
		AMOUNT_BEING_PAID("amtBeingPaid"), PENDING_AMOUNT("pendingAmount"), SALESAGENT_NAME("salesAgntNme"),
		QUOTE_DETAILS("QUOTE_DETAILS"),RELOAD_ORDER_TRANSACTION("ReloadOrderTransaction"),ORDER_TRAN_TOT("OrdTranTot"),LIN_ITM_BFR_PRCOVRD("LinItmBfrPrcOvrd");

		private String value;

		SESSION(String value) {
			this.setValue(value);
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	public boolean isPreventBack() {
		return preventBack;
	}

	public void setPreventBack(boolean preventBack) {
		this.preventBack = preventBack;
	}

	public String getAdminRoleId() {
		
		//TODO get admin role from parameter
		/*try {
			ParameterConfigurationServiceIfc parameters = DKartContext.getParamterBean();
			adminRoleId = parameters.fetchXMLParameterValues().getAdministratorRoleID();
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e.getCause(),"");
		}*/
			
		return adminRoleId;
	}

	public String getOverrideSecurityAction() {
		return overrideSecurityAction;
	}

	public void setOverrideSecurityAction(String overrideSecurityAction) {
		this.overrideSecurityAction = overrideSecurityAction;
	}
	
	protected static String getTextCustom(String key)
	{
		LocalizedTextUtil.addDefaultResourceBundle("global");
		String value = LocalizedTextUtil.findDefaultText(key, ActionContext.getContext().getLocale());
		return value;
	}
	
	protected int getBigDecimalScale()
	{
		String currencyPattern = getText("format.currency");
		return CurrencyUtil.getScale(currencyPattern);
	}
}