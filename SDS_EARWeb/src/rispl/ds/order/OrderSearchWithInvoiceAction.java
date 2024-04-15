package rispl.ds.order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.test.entities.OrderDetailsWithInvoice;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.order.lookup.dao.OrderDAOBeanRemote;
import rispl.dkart.services.detail.claim.ClaimDetailTable;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;
import rispl.ds.invoice.Invoice;

public class OrderSearchWithInvoiceAction extends DSAction {

	static Logger LOGGER = LogManager.getLogger(OrderSearchWithInvoiceAction.class);
	private static final long serialVersionUID = 1L;
	
	private String invoice_id;
	private String invoice_date_from, invoice_date_to;
	private String invoice_total_from, invoice_total_to;
	private final DateFormat DATETIME_FORMAT = new SimpleDateFormat(getTextCustom("format.date"));
	private List<OrderDetailsWithInvoice> orders;
	private EmployeeIfc employee;
	OrderDAOBeanRemote dao;
	private String activeTab;
	/**
	 * range of the search
	 */
	private String searchRange;
	// displaying 0 orders
	public ClaimDetailTable[] rejectClaim_List;
	ClaimDetailTable[] newRejectClaimList = null;
		
	public void setRejecClaimList(ClaimDetailTable[] newAcceptClaimList) {
		this.rejectClaim_List = newAcceptClaimList;
	}

	public ClaimDetailTable[] getRejecClaimList() {
		return rejectClaim_List;
	}
	

	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getInvoice_date_from() {
		return invoice_date_from;
	}

	public void setInvoice_date_from(String invoice_date_from) {
		this.invoice_date_from = invoice_date_from;
	}

	public String getInvoice_date_to() {
		return invoice_date_to;
	}

	public void setInvoice_date_to(String invoice_date_to) {
		this.invoice_date_to = invoice_date_to;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public String getInvoice_total_from() {
		return invoice_total_from;
	}

	public void setInvoice_total_from(String invoice_total_from) {
		this.invoice_total_from = invoice_total_from;
	}

	public String getInvoice_total_to() {
		return invoice_total_to;
	}

	public void setInvoice_total_to(String invoice_total_to) {
		this.invoice_total_to = invoice_total_to;
	}

	public String getSearchRange() {
		return searchRange;
	}

	public void setSearchRange(String searchRange) {
		this.searchRange = searchRange;
	}

	public List<OrderDetailsWithInvoice> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetailsWithInvoice> orders) {
		this.orders = orders;
	}

	public OrderDAOBeanRemote getDao() {
		return dao;
	}

	public void setDao(OrderDAOBeanRemote dao) {
		this.dao = dao;
	}

	public DateFormat getDatetimeFormat() {
		return DATETIME_FORMAT;
	}

	public Date convertStringToDate(String string) {
		if (string == null) {
			return null;
		}
		try {
			return DATETIME_FORMAT.parse(string);
		} catch (ParseException e) {
			return null;
		}
	}

	public String execute() {
		List<OrderDetailsWithInvoice> orders_from_totals = null, orders_from_date = null, order_from_invoice_id = null;
		employee = super.getEmployee();
		Map<Integer, List<OrderDetailsWithInvoice> >resultMap=new HashMap<>();
		Date parsed_to, parsed_from;
		parsed_from = convertStringToDate(invoice_date_from);
		parsed_to = convertStringToDate(invoice_date_to);
		try {
			dao = DKartContext.getOrderDAOBean();
			order_from_invoice_id = dao.getOrderByInvoiceId(employee, invoice_id.trim());
			resultMap.put(1, order_from_invoice_id);
			orders_from_date = dao.getOrdersByInvoiceDate(employee, parsed_from, parsed_to);
			resultMap.put(2, orders_from_date);
			orders_from_totals = dao.getOrdersByInvoiceTotals(employee, invoice_total_from,invoice_total_to);
			resultMap.put(3, orders_from_totals);
			LOGGER.info("fetching the default Search Range of Order Search");
			setSearchRange(DKartContext.getParamterBean().fetchXMLParameterValues().getOrderSearchRange());
			LOGGER.info("Default Search Range Fetched is :  "+DKartContext.getParamterBean().fetchXMLParameterValues().getOrderSearchRange());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/*if (order_from_invoice_id != null) {
			orders = order_from_invoice_id;

		} else if (orders_from_date != null && orders_from_totals != null) {
			orders = orders_from_date;
			orders.retainAll(orders_from_totals);
		} else {
			orders = (orders_from_date != null) ? orders_from_date : orders_from_totals;

		}*/
		orders=OrderSearchWithOrderAction.formatResultListOfOrderSearch(resultMap);
		if(orders == null || orders != null && orders.isEmpty()){
			addActionError("No Orders found");
		}

		if(orders!=null)
		{
			
		}else
		{
			int count=0;
			newRejectClaimList = new ClaimDetailTable[count];		
			setRejecClaimList(newRejectClaimList);
			
		}
		return "success";
	}

	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}

}
