package rispl.ds.dashboard;


import rispl.dk.Employee.EmployeeIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class DashboardAction extends DSAction {

	private static final long serialVersionUID = 1L;
	private static final String DEFAUL_REFRESH_INTERVAL="60000";
	
	//EmployeeIfc employee;
	String default_range,refresh_interval;
	
	public String execute() throws Exception {
		//employee = super.getEmployee();
		default_range=DKartContext.getParamterBean().fetchXMLParameterValues().getDashboardRange();
		if(default_range.contains("_")){
			default_range=DKartContext.getParamterBean().fetchXMLParameterValues().getDashboardRange().toLowerCase().split("_")[1];
		}
		refresh_interval=DEFAUL_REFRESH_INTERVAL;
		return SUCCESS;
	}

/*	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}*/

	public String getDefault_range() {
		return default_range;
	}

	public void setDefault_range(String default_range) {
		this.default_range = default_range;
	}

	public String getRefresh_interval() {
		return refresh_interval;
	}

	public void setRefresh_interval(String refersh_interval) {
		this.refresh_interval = refersh_interval;
	}
	
	
	
	
}
