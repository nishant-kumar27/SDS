package rispl.ds.dashboard;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;
import com.rispl.sds.parameter.service.ParameterException;
import com.test.entities.OrderDetail;

import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.order.lookup.dao.OrderDAOBeanRemote;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class OrdersDashboardAction extends DSAction {
	private static final long serialVersionUID = 1L;

	/*// private static final long DIVISION_HEAD = 7;
*/
	private List<OrderDetail> orders;
	private OrderDAOBeanRemote orderDao;
	private ParameterConfigurationServiceIfc parameterService;


	private String range, status;
	private EmployeeIfc employee;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRange() {
		return range;
	}

	public OrdersDashboardAction() {
		if(this.range==null || this.range.isEmpty()){
			try {
				range=DKartContext.getParamterBean().fetchXMLParameterValues().getDashboardRange();
				if(range.contains("_")){
					range=DKartContext.getParamterBean().fetchXMLParameterValues().getDashboardRange().toLowerCase().split("_")[1];
				}
			} catch (ParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void setRange(String range) {
		this.range = range;
	}

	public List<OrderDetail> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetail> orders) {
		this.orders = orders;
	}

	public OrderDAOBeanRemote getDao() {
		return orderDao;
	}

	public void setDao(OrderDAOBeanRemote dao) {
		this.orderDao = dao;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public String execute() {

		employee = super.getEmployee();

		try {
			Long DIVISION_HEAD = Long
					.valueOf(DKartContext.getParamterBean().fetchXMLParameterValues().getDivisionHeadRoleID());
			orderDao = DKartContext.getOrderDAOBean();
			parameterService = DKartContext.getParamterBean();
			
			List<Integer> division = new ArrayList();
			if (employee.getMerchAssoc() != null) {
				for (EmpMerchAssociationIfc merchID : employee.getMerchAssoc()) {
					String id = merchID.getMerchId();
					String[] splitId = id.split(":");
					if (splitId[0].equals("1"))
						// division=Integer.parseInt(splitId[1]);
						division.add(Integer.parseInt(splitId[1]));
				}
			}
			// if(employee.getRoleId()==DIVISION_HEAD && division!=-1){
			if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
				if (status == null)
					orders = orderDao.getOrdersByDivision(division, range);
				else
					orders = orderDao.getOrdersByDivision(division, range, status);
			}else if(employee.getRoleAccess().equalsIgnoreCase("All")){
				if (status == null)
					orders = orderDao.getOrdersByAllDiv(range);
				else
					orders = orderDao.getOrdersByAllDiv(range, status);
			} else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")){
				if (status == null)
				orders = orderDao.getorders(employee.getEmployeeId(), range);
				else
					orders = orderDao.getorders(employee.getEmployeeId(), range, status);
			} 

			updateStatusi18n(orders);

		} catch (NamingException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Never even ran completely...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}

	private void updateStatusi18n(List<OrderDetail> orders2) {
		if (orders2 != null) {
			for (OrderDetail orderDetail : orders2) {
				switch (orderDetail.getStatusEnum()) {
				case QUOTE:
					orderDetail.setStatus(getText("quoted_status"));
					break;
				case NEW:
					orderDetail.setStatus(getText("new_status"));
					break;
				case OPEN:
					orderDetail.setStatus(getText("open_status"));
					break;
				case CANCELLED:
					orderDetail.setStatus(getText("cancelled_status"));
					break;
				case IN_PROGRESS:
					orderDetail.setStatus(getText("inprogress_status"));
					break;
				case COMPELETED:
					orderDetail.setStatus(getText("completed_status"));
					break;
				case DELIVERED:
					orderDetail.setStatus(getText("delivered_status"));
					break;
				case RETURNED:
					orderDetail.setStatus(getText("returned_status"));
					break;
				case PAYMENT:
					orderDetail.setStatus(getText("payment_status"));
					break;
				case UNKNOWN:
					orderDetail.setStatus(getText("unknown_status"));
					break;
				default:
					orderDetail.setStatus(getText("unknown_status"));
					break;
				}
			}
		}

	}
}
