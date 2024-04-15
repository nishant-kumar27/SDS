package rispl.ds.dashboard;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.rispl.sds.parameter.service.ParameterException;
import com.test.entities.ReceivablesDetail;

import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.invoice.lookup.dao.InvoiceDAOBeanInfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class InvoiceDashBoardAction extends DSAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CURRENT = "Current";
	private static final String OVERDUE = "Overdue";
	/* private static final long DIVISION_HEAD=7; */

	private List<ReceivablesDetail> invoices;
	private InvoiceDAOBeanInfc dao;
	private EmployeeIfc employee;
	private String range;
	private String status;


	public InvoiceDashBoardAction() {
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

	public List<ReceivablesDetail> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<ReceivablesDetail> invoices) {
		this.invoices = invoices;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String execute() {
		employee = super.getEmployee();
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
				if (status != null && !status.equals("")) {
					if (status.equals(CURRENT) || status.equals(OVERDUE))
						invoices = dao.getReceivablesDetailByDivision(division, range, status);

				} else
					invoices = dao.getReceivablesDetailByDivision(division, range);

			}
			else if(employee.getRoleAccess().equalsIgnoreCase("All")){
				if (status != null && !status.equals("")) {
					if (status.equals(CURRENT) || status.equals(OVERDUE))
						invoices = dao.getReceivablesDetailByAllDiv(range, status);

				} else
					invoices = dao.getReceivablesDetailByAllDiv(range);

			}
			
				else if (employee.getRoleAccess().equalsIgnoreCase("Linked Agent")){
				if (status != null && !status.equals("")) {
					if (status.equals(CURRENT) || status.equals(OVERDUE))
						invoices = dao.getReceivablesDetailByEmpId(employee.getEmployeeId(), range, status);

				} else
					invoices = dao.getReceivablesDetailByEmpId(employee.getEmployeeId(), range);
			}

			// System.out.println("Invoice Data:"+invoices);

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
