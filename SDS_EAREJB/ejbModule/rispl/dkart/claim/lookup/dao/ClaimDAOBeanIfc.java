package rispl.dkart.claim.lookup.dao;

import java.util.List;

import javax.ejb.Remote;

import rispl.db.model.claim.ClaimDetail;

@Remote
public interface ClaimDAOBeanIfc {
	public void beanTest(String empid);
	
	public List<ClaimDetail> getClaimDetailByEmpId(String empid,String range);
	public List<ClaimDetail> getClaimDetailByEmpId(String empid, String range,String status);
	
	/*public List<ClaimDetail> getClaimDetailByDivision(int division, String range);*/
	public List<ClaimDetail> getClaimDetailByDivision(List<Integer> division, String range);
	/*public List<ClaimDetail> getClaimDetailByDivision(int division, String range,String status);*/
	public List<ClaimDetail> getClaimDetailByDivision(List<Integer> division, String range,String status);
	
	public int[] getClaimsCount(String empid,String range);
	//changed by mudassir for multiple divisions
	/*public int[] getClaimsCountByDivision(int  division,String range);*/
	public int[] getClaimsCountByDivision(List<Integer>  division,String range);
/*sharanya for dashboard*/
	public int[] getClaimsCountByAllDiv(String range);

	public List<ClaimDetail> getClaimDetailByAllDiv(String range);

	public List<ClaimDetail> getClaimDetailByAllDiv(String range, String status);
}
