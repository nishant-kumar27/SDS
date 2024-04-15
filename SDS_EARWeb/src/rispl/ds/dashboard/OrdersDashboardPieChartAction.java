package rispl.ds.dashboard;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;
import com.test.entities.OrderDetail;
import com.test.entities.OrderDetail.OrderStatusEnum;
import com.test.entities.SdsOrdersDashboard;

import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.order.lookup.dao.OrderDAOBeanRemote;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class OrdersDashboardPieChartAction extends DSAction {
	private static final long serialVersionUID = 1L;

	/*private static final long DIVISION_HEAD = 7;*/

	private String empId;
	private String range;
	private List<SdsOrdersDashboard> orders_count;
	private List<OrderDetail> orders;
	private int sale_order_count;
	private int returned_order_count;
	private OrderDAOBeanRemote orderDao;
	private ParameterConfigurationServiceIfc parameterService;
	private EmployeeIfc employee;

	public String execute() {
		employee = super.getEmployee();
		sale_order_count = 0;

		try {
			long DIVISION_HEAD = Long
					.valueOf(DKartContext.getParamterBean().fetchXMLParameterValues().getDivisionHeadRoleID());
			orderDao = DKartContext.getOrderDAOBean();
			
					List<Integer> division=new ArrayList();
			if (employee.getMerchAssoc() != null) {
				for (EmpMerchAssociationIfc merchID : employee.getMerchAssoc()) {
					String id = merchID.getMerchId();
					String[] splitId = id.split(":");
					if (splitId[0].equals("1"))
//						division=Integer.parseInt(splitId[1]);
						division.add(Integer.parseInt(splitId[1]));
				}
			}

			//if(employee.getRoleId()==DIVISION_HEAD && division!=-1){
			if(employee.getRoleAccess().equalsIgnoreCase("Within Division")){
				orders_count = orderDao.getOrdersCountByDivision(division, range);
				orders = orderDao.getOrdersByDivision(division, range);
			} else if( employee.getRoleAccess().equalsIgnoreCase("All")){
				orders_count = orderDao.getOrdersCountByAllDiv(range);
				orders = orderDao.getOrdersByAllDiv(range);
			}else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")){
				orders_count = orderDao.getOrdersCountByEmpId(employee.getEmployeeId(),range);
				orders = orderDao.getorders(employee.getEmployeeId(),range);
			}

			sale_order_count = 0;
			returned_order_count = 0;
			for (OrderDetail orderDetail : orders) {
				if (orderDetail.getStatusEnum() != null) {
					if (orderDetail.getStatusEnum() == OrderStatusEnum.RETURNED)
						returned_order_count++;
					else
						sale_order_count++;
				}
			}
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public List<SdsOrdersDashboard> getOrders_count() {
		return orders_count;
	}

	public void setOrders_count(List<SdsOrdersDashboard> orders_count) {
		this.orders_count = orders_count;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public int getSale_order_count() {
		return sale_order_count;
	}

	public void setSale_order_count(int sale_order_count) {
		this.sale_order_count = sale_order_count;
	}

	public int getReturned_order_count() {
		return returned_order_count;
	}

	public void setReturned_order_count(int returned_order_count) {
		this.returned_order_count = returned_order_count;
	}

}
