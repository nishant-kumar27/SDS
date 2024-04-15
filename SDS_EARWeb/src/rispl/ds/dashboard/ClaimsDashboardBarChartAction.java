package rispl.ds.dashboard;

import java.util.ArrayList;
import java.util.List;

import rispl.db.model.claim.ClaimDetail;
import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.claim.lookup.dao.ClaimDAOBeanIfc;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class ClaimsDashboardBarChartAction extends DSAction {
	/*private static final long DIVISION_HEAD=7;*/
	
	public EmployeeIfc employee;
	public String range;
	public String[] dept;
	public int claim_total_count;
	public List<ClaimDetail> claims;
	public int[] count;

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

	public String[] getDept() {
		return dept;
	}

	public void setDept(String[] dept) {
		this.dept = dept;
	}

	public int[] getCount() {
		return count;
	}

	public void setCount(int[] count) {
		this.count = count;
	}
	

	public int getClaim_total_count() {
		return claim_total_count;
	}

	public void setClaim_total_count(int claim_total_count) {
		this.claim_total_count = claim_total_count;
	}

	public String execute() {
		employee = super.getEmployee();
	//changed by mudassir for multiple divisions
		//	int division=-1;
		List<Integer>  division=new ArrayList();
		if(employee.getMerchAssoc()!=null){
			for(EmpMerchAssociationIfc merchID:employee.getMerchAssoc()){
				String id=merchID.getMerchId();
				String[] splitId=id.split(":");
				if(splitId[0].equals("1"))
					//division=Integer.parseInt(splitId[1]);
					division.add(Integer.parseInt(splitId[1]));
			}
		}
		
		try {
			Long DIVISION_HEAD = Long
					.valueOf(DKartContext.getParamterBean().fetchXMLParameterValues().getDivisionHeadRoleID());
			ClaimDAOBeanIfc dao = DKartContext.getClaimDAOBean();
		//	if(employee.getRoleId()==DIVISION_HEAD && division!=-1){
			if(employee.getRoleAccess().equalsIgnoreCase("Within Division")){
			count=dao.getClaimsCountByDivision(division, range);
				claims=dao.getClaimDetailByDivision(division, range);
			}
			else if(employee.getRoleAccess().equalsIgnoreCase("All")){
				count=dao.getClaimsCountByAllDiv(range);
				claims=dao.getClaimDetailByAllDiv(range);
			}
			else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")){
				count=dao.getClaimsCount(employee.getEmployeeId(),range);
				claims=dao.getClaimDetailByEmpId(employee.getEmployeeId(), range);
			}
			claim_total_count=claims.size();
			dept=new String[]{"Registered","Approved","In-Progress","Accepted"};
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
}