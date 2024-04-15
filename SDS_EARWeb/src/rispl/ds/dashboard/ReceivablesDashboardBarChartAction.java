package rispl.ds.dashboard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionContext;

import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.invoice.lookup.dao.InvoiceDAOBeanInfc;
import rispl.ds.DSAction.SESSION;
import rispl.ds.context.DKartContext;

public class ReceivablesDashboardBarChartAction {
	/* private static final long DIVISION_HEAD=7; */

	BigDecimal[] amounts;
	private InvoiceDAOBeanInfc dao;
	private EmployeeIfc employee;
	private String range;

	public ReceivablesDashboardBarChartAction() {
		range = "quarter";
		amounts = new BigDecimal[2];
		amounts[0] = new BigDecimal(0);
		amounts[1] = new BigDecimal(0);
	}

	public BigDecimal[] getAmounts() {
		return amounts;
	}

	public void setAmounts(BigDecimal[] amounts) {
		this.amounts = amounts;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String execute() {
		Map session = (Map) ActionContext.getContext().get("session"); //$NON-NLS-1$
		employee = (EmployeeIfc) session.get(SESSION.EMPLOYEE.getValue()); //FIXME Get from session using getEmployee by extending DSAction
		/* int division=-1; */
		List<Integer> division = new ArrayList();
		if (employee.getMerchAssoc() != null) {
			for (EmpMerchAssociationIfc merchID : employee.getMerchAssoc()) {
				String id = merchID.getMerchId();
				String[] splitId = id.split(":");
				if (splitId[0].equals("1"))
					/* division=Integer.parseInt(splitId[1]); */
					division.add(Integer.parseInt(splitId[1]));
			}
		}

		try {
			dao = DKartContext.getInvoiceDAOBean();
			Long DIVISION_HEAD = Long
					.valueOf(DKartContext.getParamterBean().fetchXMLParameterValues().getDivisionHeadRoleID());
			/* if(employee.getRoleId()==DIVISION_HEAD && division!=-1){ */
			if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
				amounts = dao.getReceivablesAmountByDivision(division, range);
			} else if(employee.getRoleAccess().equalsIgnoreCase("All")) {
				amounts = dao.getReceivablesAmountByAllDiv(range);
			}else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")){
				amounts = dao.getReceivablesAmountByEmpId(employee.getEmployeeId(), range);
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "success";
	}
}
