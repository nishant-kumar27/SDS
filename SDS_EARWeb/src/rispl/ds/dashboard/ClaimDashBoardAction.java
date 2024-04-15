package rispl.ds.dashboard;

import java.util.ArrayList;
import java.util.List;

import com.rispl.sds.parameter.service.ParameterException;

import rispl.db.model.claim.ClaimDetail;
import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.claim.lookup.dao.ClaimDAOBeanIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class ClaimDashBoardAction extends DSAction {
	/**
	 * 
	 */
	/* private static final long DIVISION_HEAD=7; */

	private static final long serialVersionUID = 1L;
	public EmployeeIfc employee;
	public String range;
	public List<ClaimDetail> claims;
	public String status;

	public ClaimDashBoardAction() {
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

	public List<ClaimDetail> getClaims() {
		return claims;
	}

	public void setClaims(List<ClaimDetail> claims) {
		this.claims = claims;
	}

	public String execute() {
		employee = super.getEmployee();

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
		try {
			ClaimDAOBeanIfc dao = DKartContext.getClaimDAOBean();
			Long DIVISION_HEAD = Long
					.valueOf(DKartContext.getParamterBean().fetchXMLParameterValues().getDivisionHeadRoleID());
			/* if(employee.getRoleId()==DIVISION_HEAD && division!=-1){ */
			if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
				if (status != null && !status.equals("")) {
					claims = dao.getClaimDetailByDivision(division, range, status);
				} else {
					claims = dao.getClaimDetailByDivision(division, range);
				}

			} else if(employee.getRoleAccess().equalsIgnoreCase("All")){
				if (status != null && !status.equals("")) {
					claims = dao.getClaimDetailByAllDiv(range, status);
				} else {
					claims = dao.getClaimDetailByAllDiv(range);
				}

			}
			else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")){
				if (status != null && !status.equals("")) {
					claims = dao.getClaimDetailByEmpId(employee.getEmployeeId(), range, status);
				} else {
					claims = dao.getClaimDetailByEmpId(employee.getEmployeeId(), range);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		System.out.println(claims);
		return SUCCESS;
	}
}
