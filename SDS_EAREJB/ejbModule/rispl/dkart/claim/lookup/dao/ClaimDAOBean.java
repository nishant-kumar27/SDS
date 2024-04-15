package rispl.dkart.claim.lookup.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import rispl.db.model.claim.ClaimDetail;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;

/**
 * Session Bean implementation class ClaimDAOBean
 */
@Stateless(mappedName="claimDAOBean")
public class ClaimDAOBean implements ClaimDAOBeanIfc {
	
	ClaimDAO dao;

	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	
	@PostConstruct
    void init()
    {
    	dao=new ClaimDAO(emf.createEntityManager());
    }
	
    public ClaimDAOBean() {

    }

    public void beanTest(String empid){
    	System.out.println("In ClaimDAO:"+empid);
    }
    
	@Override
	public List<ClaimDetail> getClaimDetailByEmpId(String empid, String range) {
		// TODO Auto-generated method stub
		List<ClaimDetail> claims=dao.getClaimDetailByEmpId(empid, range);
		return claims;
	}
	
	@Override
	public int[] getClaimsCount(String empid, String range) {
		// TODO Auto-generated method stub
		int [] data=dao.getClaimsCount(empid, range);
		return data;
	}

	@Override
	public List<ClaimDetail> getClaimDetailByEmpId(String empid, String range, String status) {
		// TODO Auto-generated method stub
		List<ClaimDetail> claims=dao.getClaimDetailByEmpId(empid, range, status);
		return claims;
	}

/*	@Override
	public List<ClaimDetail> getClaimDetailByDivision(int division, String range) {*/
		// TODO Auto-generated method stub
	@Override
	public List<ClaimDetail> getClaimDetailByDivision(List<Integer> division, String range) {
		List<ClaimDetail> claims=dao.getClaimDetailByDivision(division, range);
		return claims;
	}

/*	@Override
	public List<ClaimDetail> getClaimDetailByDivision(int division, String range, String status) {*/
		// TODO Auto-generated method stub
	@Override
	public List<ClaimDetail> getClaimDetailByDivision(List<Integer> division, String range, String status) {	 
	List<ClaimDetail> claims=dao.getClaimDetailByDivision(division, range, status);
		return claims;
	}

	/*@Override
	public int[] getClaimsCountByDivision(int division, String range) {*/
	@Override
	public int[] getClaimsCountByDivision(List<Integer> division, String range) {
		// TODO Auto-generated method stub
		int[] count=dao.getClaimsCountByDivision(division, range);
		return count;
	}
	
	@Override
	public int[] getClaimsCountByAllDiv(String range) {
		// TODO Auto-generated method stub
		int[] count=dao.getClaimsCountByAllDiv(range);
		return count;
	}
	@Override
	public List<ClaimDetail> getClaimDetailByAllDiv(String range, String status) {	 
	List<ClaimDetail> claims=dao.getClaimDetailByAllDiv(range, status);
		return claims;
	}

	@Override
	public List<ClaimDetail> getClaimDetailByAllDiv(String range) {
		List<ClaimDetail> claims=dao.getClaimDetailByAllDiv(range);
		return claims;
	}

}
