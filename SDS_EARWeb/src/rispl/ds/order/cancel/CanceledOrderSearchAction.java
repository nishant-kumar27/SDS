package rispl.ds.order.cancel;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import com.rispl.cancel.order.dao.RisplDkCancelOrderSearchV;
import com.rispl.sds.cancel.order.service.CancelOrderServiceIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class CanceledOrderSearchAction extends DSAction {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	private String orderID;
	private String datepicker1, datepicker2;
	private String OrderTotalFrom, OrderTotalTo;
	private List<RisplDkCancelOrderSearchV> orders = new ArrayList<RisplDkCancelOrderSearchV>();
	CancelOrderServiceIfc dao;
	private EmployeeIfc employee;
	private String customerInfo;
	private String InvItemId;
	/**
	 * range of the search
	 */
	private String searchRange;
	
	public String getInvItemId() {
		return InvItemId;
	}

	public void setInvItemId(String invItemId) {
		InvItemId = invItemId;
	}

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getDatepicker1() {
		return datepicker1;
	}

	public void setDatepicker1(String datepicker1) {
		this.datepicker1 = datepicker1;
	}

	public String getDatepicker2() {
		return datepicker2;
	}

	public void setDatepicker2(String datepicker2) {
		this.datepicker2 = datepicker2;
	}

	public String getOrderTotalFrom() {
		return OrderTotalFrom;
	}

	public void setOrderTotalFrom(String orderTotalFrom) {
		OrderTotalFrom = orderTotalFrom;
	}

	public String getOrderTotalTo() {
		return OrderTotalTo;
	}

	public void setOrderTotalTo(String orderTotalTo) {
		OrderTotalTo = orderTotalTo;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	
	public List<RisplDkCancelOrderSearchV> getOrders() {
		return orders;
	}

	public void setOrders(List<RisplDkCancelOrderSearchV> orders) {
		this.orders = orders;
	}


	public String fetchCancelOrderDetailsByOrderID() {
			List<RisplDkCancelOrderSearchV> ordersFromOrderTab = new ArrayList<RisplDkCancelOrderSearchV>();
			employee = super.getEmployee();
			try {
				dao = DKartContext.getCancelOrderBean();
				ordersFromOrderTab = dao.getCancelOrderByOrderIdTab(orderID, datepicker1, datepicker2, OrderTotalFrom, OrderTotalTo,customerInfo,InvItemId,employee);
				if(!ordersFromOrderTab.isEmpty())
				{
					orders = ordersFromOrderTab;
				}
				LOG.info("fetching the default Search Range of Cancel Orders");
				setSearchRange(DKartContext.getParamterBean().fetchXMLParameterValues().getCancelledOrderSearchRange());
				LOG.info("Default Search Range Fetched is :  "+DKartContext.getParamterBean().fetchXMLParameterValues().getCancelledOrderSearchRange());
			} catch (NamingException e) {
				addActionError("Unable To Look up Cancel Order Bean");
			} catch (Exception e) {
				addActionError("Please Try After Some Time");
			}
		return "success";
	}
	
	public String fetchCancelOrderDetailsByCustomer() {
		{
			List<RisplDkCancelOrderSearchV> ordersFromCustomerTab = null;
			employee = super.getEmployee();
			try {

				dao = DKartContext.getCancelOrderBean();
				ordersFromCustomerTab = dao.getCancelOrderByOrderIdTab(orderID, datepicker1, datepicker2, OrderTotalFrom, OrderTotalTo, customerInfo,InvItemId,employee);
				if(!ordersFromCustomerTab.isEmpty())
				{
					orders = ordersFromCustomerTab;
				}
			} catch (NamingException e) {
				addActionError("Unable To Look up Cancel Order Bean");
			} catch (Exception e) {
				addActionError("Please Try After Some Time");
			}

			return "success";
		
			
		}
	}

	public String getSearchRange() {
		return searchRange;
	}

	public void setSearchRange(String searchRange) {
		this.searchRange = searchRange;
	}

}
