package rispl.ds.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.test.entities.OrderDetailsWithInvoice;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.order.lookup.dao.OrderDAOBeanRemote;
import rispl.dkart.services.detail.claim.ClaimDetailTable;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class OrderSearchWithCustomerAction extends DSAction {

	private static final long serialVersionUID = 1L;
	
	private String customer_data, item_data;
	private List<OrderDetailsWithInvoice> orders;
	private OrderDAOBeanRemote dao;
	private EmployeeIfc employee;
	private static final Logger LOG = LogManager.getLogger(OrderSearchWithCustomerAction.class);
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


	public String getCustomer_data() {
		return customer_data;
	}

	public void setCustomer_data(String customer_data) {
		this.customer_data = customer_data;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public String getItem_data() {
		return item_data;
	}

	public void setItem_data(String item_data) {
		this.item_data = item_data;
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
	
	public String execute() {
		List<OrderDetailsWithInvoice> orders_from_customer = null, orders_from_item = null;
		employee = super.getEmployee();
		Map<Integer, List<OrderDetailsWithInvoice> >resultMap=new HashMap<>();
		Context ctx;
		try {

			dao = DKartContext.getOrderDAOBean();
			orders_from_customer = dao.getOrderByCustomer(employee, customer_data);
			resultMap.put(2, orders_from_customer);
			orders_from_item = dao.getOrderByItem(employee, item_data);
			resultMap.put(1, orders_from_item);
			LOG.info("fetching the default Search Range of Order Search");
			setSearchRange(DKartContext.getParamterBean().fetchXMLParameterValues().getOrderSearchRange());
			LOG.info("Default Search Range Fetched is :  "+DKartContext.getParamterBean().fetchXMLParameterValues().getOrderSearchRange());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			LOG.error("Exception While Order Search With Customer Data", e);
		} catch (NullPointerException e) {
			// TODO: handle exception
			LOG.error("Exception While Order Search With Customer Data", e);
		} catch (Exception e) {
			// TODO: handle exception
			LOG.error("Exception While Order Search With Customer Data", e);
		}

		orders = null;
		/*if (orders_from_customer != null && orders_from_item != null) {
			orders = orders_from_customer;
			orders.retainAll(orders_from_item);
		} else {
			orders = (orders_from_customer != null) ? orders_from_customer : orders_from_item;
			
		}*/
		orders=formatListForOrderSearchWithCustomer(resultMap);
		if(orders == null || orders != null && orders.isEmpty()){
				addActionError("No Orders found");
		}
		if(orders!=null)
		{
			
		}else
		{
			int count=0;
			newRejectClaimList=new ClaimDetailTable[count];
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
	private List<OrderDetailsWithInvoice> formatListForOrderSearchWithCustomer(Map<Integer, List<OrderDetailsWithInvoice>> combined) {
		List<OrderDetailsWithInvoice> tab1List = null;
		if (combined.size() > 0) {
		if(combined.get(1)!=null && combined.get(2)!=null && !combined.get(1).isEmpty() && !combined.get(2).isEmpty()){
			combined.get(1).retainAll(combined.get(2));
			tab1List=combined.get(1);
		}
		else {
			if(combined.get(1)!=null && !combined.get(1).isEmpty()){
				if(combined.get(2)!=null && combined.get(1).isEmpty()){
					return new ArrayList<OrderDetailsWithInvoice>();
				}
				else{
				tab1List=combined.get(1);
				}
			}
			if(combined.get(2)!=null && !combined.get(2).isEmpty()){
				if(combined.get(1)!=null && combined.get(1).isEmpty()){
					return new ArrayList<OrderDetailsWithInvoice>();
				}
				else{
				tab1List=combined.get(2);
				}
			}
		}
		
		} else {
			return new ArrayList<OrderDetailsWithInvoice>();
		}
		return tab1List;
	}
}
