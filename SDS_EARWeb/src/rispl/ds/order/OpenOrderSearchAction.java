package rispl.ds.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.entities.OrderDetailsWithInvoice;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.order.lookup.dao.OpenOrderSearchVo;
import rispl.dkart.order.lookup.dao.OrderDAOBeanRemote;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class OpenOrderSearchAction extends DSAction {
	private String openOrderStatus;
	private String activeTab;
	private String orderID;
	private String orderFromDate;
	private String ordertoDate;
	private String OrderTotalFrom;
	private String OrderTotalTo;
	private String customerInfo;
	private String InvItemId;
	private List<OrderDetailsWithInvoice> result;
	/**
	 * range of the search
	 */
	private String searchRange;
	private static final Logger openOrdersLog = LogManager.getLogger(OpenOrderSearchAction.class);
	public String getOpenOrderStatus() {
		return openOrderStatus;
	}

	
	public List<OrderDetailsWithInvoice> getResult() {
		return result;
	}


	public void setResult(List<OrderDetailsWithInvoice> result) {
		this.result = result;
	}


	public void setOpenOrderStatus(String openOrderStatus) {
		this.openOrderStatus = openOrderStatus;
	}

	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getOrderFromDate() {
		return orderFromDate;
	}

	public void setOrderFromDate(String orderFromDate) {
		this.orderFromDate = orderFromDate;
	}

	public String getOrdertoDate() {
		return ordertoDate;
	}

	public void setOrdertoDate(String ordertoDate) {
		this.ordertoDate = ordertoDate;
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

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getInvItemId() {
		return InvItemId;
	}
	public String getSearchRange() {
		return searchRange;
	}

	public void setSearchRange(String searchRange) {
		this.searchRange = searchRange;
	}
	public void setInvItemId(String invItemId) {
		if(invItemId!=null){
		if(invItemId.contains("---")){
		String [] itm=invItemId.split("---");
		try{
		Long tempId=Long.parseLong(itm[1]);
		this.InvItemId = itm[1];
		}catch(Exception e){
			System.out.println(e);
			this.InvItemId = itm[0];
		}
		}else{
			this.InvItemId =invItemId;
		}
		}
		
	}

	//Employee Session
	private EmployeeIfc getEmployeeSession(){
		return super.getEmployee();
	}

	@Override
	public String toString() {
		return "OpenOrderSearchAction  [openOrderStatus=" + openOrderStatus + ", activeTab=" + activeTab + ", orderID="
				+ orderID + ", orderFromDate=" + orderFromDate + ", ordertoDate=" + ordertoDate + ", OrderTotalFrom="
				+ OrderTotalFrom + ", OrderTotalTo=" + OrderTotalTo + ", customerInfo=" + customerInfo + ", InvItemId="
				+ InvItemId + "]";
	}
	//open orders search with tab1 and tab 3
	
	public String openOrderSearchFotTab1and2(){
		OpenOrderSearchVo search=new OpenOrderSearchVo();
		if(this.getActiveTab()!=null && this.getActiveTab().equalsIgnoreCase("tab_1")){
			if(getEmployeeSession()!=null){
			openOrdersLog.info( this.toString());
			//validating date if wrong date entered manually
			if(this.getOrderFromDate()!=null && !this.getOrderFromDate().isEmpty()){
			if(!isDateValid(this.getOrderFromDate())){
				addActionError("Please provide a valid  date");
				return ERROR;
			}
			}
			else if (this.getOrdertoDate()!=null && !this.getOrdertoDate().isEmpty()){
				if(!isDateValid(this.getOrdertoDate())){
					addActionError("Please provide a valid  date");
					return ERROR;
				}
			}
			//date valdidation end
		//search open orders with status
			try {
				
				Long start=System.currentTimeMillis();
				OrderDAOBeanRemote orderDao = DKartContext.getOrderDAOBean();
			Long end=System.currentTimeMillis();
			openOrdersLog.info("Look up for orderDAobean took "+(end-start)+" ms to complete");
		openOrdersLog.info( " Invoked Open order Search Action to search "+this.getOpenOrderStatus()+"  open orders");
				search.setOpenOrderStatus(this.getOpenOrderStatus());
				search.setOrderID(this.getOrderID().trim());
				search.setOrderFromDate(this.getOrderFromDate());
				search.setOrdertoDate(this.getOrdertoDate());
				if(this.getOrderTotalFrom()!=null && !this.getOrderTotalFrom().isEmpty()){
				search.setOrderTotalFrom(this.getOrderTotalFrom());
				}else{
					search.setOrderTotalFrom("");
				}
				if(this.getOrderTotalTo()!=null && !this.getOrderTotalTo().isEmpty()){
				search.setOrderTotalTo(this.getOrderTotalTo());
				}
				else{
					search.setOrderTotalTo("");
				}
			search.setSearchByOrderTab(true);
		this.setResult(	orderDao.getOpenOrdersWithTab1andTab2(search, getEmployeeSession()));
		openOrdersLog.info("fetching the default Search Range of Order Search");
		setSearchRange(DKartContext.getParamterBean().fetchXMLParameterValues().getOpenOrderSearchRange());
		openOrdersLog.info("Default Search Range Fetched is :  "+DKartContext.getParamterBean().fetchXMLParameterValues().getOpenOrderSearchRange());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else{
				addActionError("Employee Session not Found or Expired");
				openOrdersLog.info("Employee Session got null while looking up for open order's with order tab details");
				return ERROR;
			}
		}
		else if (this.getActiveTab()!=null && this.getActiveTab().equalsIgnoreCase("tab_3")){
			if(getEmployeeSession()!=null){
				
				openOrdersLog.info( this.toString());
			try{
				Long start=System.currentTimeMillis();
				OrderDAOBeanRemote orderDao = DKartContext.getOrderDAOBean();
			Long end=System.currentTimeMillis();
			openOrdersLog.info("Look up for orderDAobean took "+(end-start)+" ms to complete");
				//search open orders with status
				openOrdersLog.info( " Invoked Open order Search Action to search all open orders");
				if(this.getInvItemId()!=null && !this.getInvItemId().isEmpty() ){
					search.setSearchByItemTab(true);
				}
				else{
					this.setInvItemId("");
				}
				if(this.getCustomerInfo()!=null && !this.getCustomerInfo().isEmpty()){
					search.setSearchByCustomerTab(true);
				}
				else{
					this.setCustomerInfo("");
				}

					search.setCustomerInfo(this.getCustomerInfo().toUpperCase().trim());
					search.setInvItemId(this.getInvItemId().toUpperCase().trim());
				
					search.setSearchByCustomerTab(true);
					search.setOpenOrderStatus(this.getOpenOrderStatus());
					this.setResult(orderDao.getOpenOrdersWithTab1andTab2(search, getEmployeeSession()));
			}
			catch(Exception e){
				e.printStackTrace();
			}
			}
			else{
				addActionError("Employee Session not Found or Expired");
				openOrdersLog.info( "Employee Session got null while looking up for open order's with customer tab details");
				return ERROR;
			}
		}
		if(this.result==null)
				{
			setResult(new ArrayList<OrderDetailsWithInvoice>());
				}
		return SUCCESS;
	}
	
	//for validating date 
	public  boolean isDateValid(String pDateToValidate)
	{
	    SimpleDateFormat orderDate = new SimpleDateFormat(getText("datepicker.defaults.format"));
	    try
	    {
	      Date OrderDate = orderDate.parse(pDateToValidate);
	      System.out.println(OrderDate);
	    }
	    catch (ParseException e)
	    {
	      e.printStackTrace();
	      openOrdersLog.info( "In correct date given");
	      return false;
	    }

	    return true;
	}
	
	
}
