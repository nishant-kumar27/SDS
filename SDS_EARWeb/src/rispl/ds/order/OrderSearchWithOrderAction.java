package rispl.ds.order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class OrderSearchWithOrderAction extends DSAction {

	static Logger LOGGER = LogManager.getLogger(OrderSearchWithOrderAction.class);
	private static final long serialVersionUID = 1L;
	
	private String order_id;
	private String order_date_from, order_date_to;
	private String order_total_from, order_total_to;
	private DateFormat DATETIME_FORMAT = new SimpleDateFormat(DSAction.getTextCustom("format.date"));
	private List<OrderDetailsWithInvoice> orders;
	OrderDAOBeanRemote dao;
	private EmployeeIfc employee;
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
	
	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_date_from() {
		return order_date_from;
	}

	public void setOrder_date_from(String order_date_from) {
		this.order_date_from = order_date_from;
	}

	public String getOrder_date_to() {
		return order_date_to;
	}

	public void setOrder_date_to(String order_date_to) {
		this.order_date_to = order_date_to;
	}

	public String getOrder_total_from() {
		return order_total_from;
	}

	public void setOrder_total_from(String order_total_from) {
		this.order_total_from = order_total_from;
	}

	public String getOrder_total_to() {
		return order_total_to;
	}

	public void setOrder_total_to(String order_total_to) {
		this.order_total_to = order_total_to;
	}

	public List<OrderDetailsWithInvoice> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetailsWithInvoice> orders) {
		this.orders = orders;
	}
	
	public String getSearchRange() {
		return searchRange;
	}

	public void setSearchRange(String searchRange) {
		this.searchRange = searchRange;
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
		List<OrderDetailsWithInvoice> orders_from_totals = null, orders_from_date = null, order_from_order_id = null;
		Map<Integer, List<OrderDetailsWithInvoice> >resultMap=new HashMap<>();
		Date parsed_to, parsed_from;
		parsed_from = convertStringToDate(order_date_from);
		parsed_to = convertStringToDate(order_date_to);
		employee = super.getEmployee();
		try {
			dao = DKartContext.getOrderDAOBean();
			System.out.println("Lookup Complete....");
			orders_from_totals = dao.getOrderByOrderTotals(employee, order_total_from, order_total_to);
			resultMap.put(3, orders_from_totals);
			orders_from_date = dao.getOrderByOrderDate(employee, parsed_from, parsed_to);
			resultMap.put(2, orders_from_date);
			order_from_order_id = dao.getOrderByOrderId(employee, order_id.trim());
			resultMap.put(1, order_from_order_id);
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
		/*if (order_from_order_id != null) {
			orders = order_from_order_id;

		} else if (orders_from_date != null && orders_from_totals != null && orders!=null) {
			orders = orders_from_date;
			orders.retainAll(orders_from_totals);
		} else {
			orders = (orders_from_date != null) ? orders_from_date : orders_from_totals;

		}*/
		orders=formatResultListOfOrderSearch(resultMap);
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
	
	//Added by mudassir to format result list in order search
	public static List<OrderDetailsWithInvoice> formatResultListOfOrderSearch(
			Map<Integer, List<OrderDetailsWithInvoice>> combined) {

		List<OrderDetailsWithInvoice> tab1List = null;
		if (combined.size() > 0) {
			if (combined.get(1) != null && combined.get(2) != null && combined.get(3) != null
					&& !combined.get(1).isEmpty() && !combined.get(2).isEmpty() && !combined.get(3).isEmpty()) {
				combined.get(2).retainAll(combined.get(3));
				combined.get(1).retainAll(combined.get(2));
				tab1List = combined.get(1);
			} else if (combined.get(1) != null && combined.get(2) != null && !combined.get(1).isEmpty()
					&& !combined.get(2).isEmpty()) {
				combined.get(1).retainAll(combined.get(2));
				tab1List = combined.get(1);
			} else if (combined.get(2) != null && combined.get(3) != null && !combined.get(2).isEmpty()
					&& !combined.get(3).isEmpty()) {
				combined.get(2).retainAll(combined.get(3));
				tab1List = combined.get(2);
			} else if (combined.get(1) != null && combined.get(3) != null && !combined.get(1).isEmpty()
					&& !combined.get(3).isEmpty()) {
				combined.get(1).retainAll(combined.get(3));
				tab1List = combined.get(1);
			}

			else {
				if (combined.get(1) != null && !combined.get(1).isEmpty()) {
					if (combined.get(2) != null && combined.get(2).isEmpty() && combined.get(3) != null
							&& combined.get(3).isEmpty()) {
						tab1List = new ArrayList<OrderDetailsWithInvoice>();
					} else if (combined.get(2) != null && combined.get(2).isEmpty()) {
						tab1List = new ArrayList<OrderDetailsWithInvoice>();
					} else if (combined.get(3) != null && combined.get(3).isEmpty()) {
						tab1List = new ArrayList<OrderDetailsWithInvoice>();
					} else {
						tab1List = combined.get(1);
					}

				}
				if (combined.get(2) != null && !combined.get(2).isEmpty()) {
					if (combined.get(3) != null && combined.get(3).isEmpty() && combined.get(1) != null
							&& combined.get(1).isEmpty()) {
						tab1List = new ArrayList<OrderDetailsWithInvoice>();
					} else if (combined.get(3) != null && combined.get(3).isEmpty()) {
						tab1List = new ArrayList<OrderDetailsWithInvoice>();
					} else if (combined.get(1) != null && combined.get(1).isEmpty()) {
						tab1List = new ArrayList<OrderDetailsWithInvoice>();
					} else {
						tab1List = combined.get(2);
					}

				}
				if (combined.get(3) != null && !combined.get(3).isEmpty()) {
					if (combined.get(2) != null && combined.get(2).isEmpty() && combined.get(1) != null
							&& combined.get(1).isEmpty()) {
						tab1List = new ArrayList<OrderDetailsWithInvoice>();
					} else if (combined.get(2) != null && combined.get(2).isEmpty()) {
						tab1List = new ArrayList<OrderDetailsWithInvoice>();
					} else if (combined.get(1) != null && combined.get(1).isEmpty()) {
						tab1List = new ArrayList<OrderDetailsWithInvoice>();
					} else {
						tab1List = combined.get(3);
					}

				}
			}

		} else {
			return new ArrayList<OrderDetailsWithInvoice>();
		}
		return tab1List;

	}

}
