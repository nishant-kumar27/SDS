package rispl.dkart.claim.lookup.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import com.test.entities.ReceivablesDetail;

import rispl.db.model.claim.ClaimDetail;

public class ClaimDAO {
	EntityManager entitymanager;
	public ClaimDAO(){
		
	}
	
	public ClaimDAO(EntityManager em) {
		this.entitymanager = em;
	}

	public int[] getClaimsCount(String empid,String range){
		//entitymanager=getEntityManager();
		Date from=getDateFromRange(range);
		Date to=new Date();
		Query query=entitymanager.createNamedQuery("DB_CLAIM_BY_EMPID",ClaimDetail.class);
		query.setParameter("empid",empid);
		query.setParameter("from",from,TemporalType.DATE);
		query.setParameter("to", to,TemporalType.DATE);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ClaimDetail> claims=query.getResultList();
		int[] count=new int[4];
		count[0]=0;
		count[1]=0;
		count[2]=0;
		count[3]=0;
		
		for(ClaimDetail claim:claims){
			if(claim.getStatus().compareTo(BigDecimal.valueOf(1))==0)
				count[0]++;
			else if(claim.getStatus().compareTo(BigDecimal.valueOf(2))==0 )
				count[1]++;
			else if(claim.getStatus().compareTo(BigDecimal.valueOf(3))==0 ||claim.getStatus().compareTo(BigDecimal.valueOf(4))==0)
				count[2]++;
			else if(claim.getStatus().compareTo(BigDecimal.valueOf(5))==0)
				count[3]++;
		}
		return count;
		
	}

	/*public int[] getClaimsCountByDivision(int  division,String range){*/
	public int[] getClaimsCountByDivision(List<Integer>  division,String range){
		//entitymanager=getEntityManager();
		Date from=getDateFromRange(range);
		Date to=new Date();
		Query query=entitymanager.createNamedQuery("DB_CLAIM_BY_DIVISION",ClaimDetail.class);
		query.setParameter("id",division);
		query.setParameter("from",from,TemporalType.DATE);
		query.setParameter("to", to,TemporalType.DATE);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ClaimDetail> claims=query.getResultList();
		int[] count=new int[4];
		count[0]=0;
		count[1]=0;
		count[2]=0;
		count[3]=0;
		
		for(ClaimDetail claim:claims){
			if(claim.getStatus().compareTo(BigDecimal.valueOf(1))==0)
				count[0]++;
			else if(claim.getStatus().compareTo(BigDecimal.valueOf(2))==0 )
				count[1]++;
			else if(claim.getStatus().compareTo(BigDecimal.valueOf(3))==0 ||claim.getStatus().compareTo(BigDecimal.valueOf(4))==0)
				count[2]++;
			else if(claim.getStatus().compareTo(BigDecimal.valueOf(5))==0)
				count[3]++;
		}
		return count;
		
	}
	
	public List<ClaimDetail> getClaimDetailByEmpId(String empid, String range){
		//entitymanager=getEntityManager();
		Date from_date=getDateFromRange(range);
		Date to_date=new Date();
		Query query=entitymanager.createNamedQuery("DB_CLAIM_BY_EMPID",ClaimDetail.class);
		query.setParameter("empid",empid);
		query.setParameter("from",from_date,TemporalType.DATE);
		query.setParameter("to", to_date,TemporalType.DATE);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ClaimDetail> claims=query.getResultList();
		return claims;
	}
	
	/*public List<ClaimDetail> getClaimDetailByDivision(int division, String range){*/
	public List<ClaimDetail> getClaimDetailByDivision(List<Integer> division, String range){
		//entitymanager=getEntityManager();
		Date from_date=getDateFromRange(range);
		Date to_date=new Date();
		Query query=entitymanager.createNamedQuery("DB_CLAIM_BY_DIVISION",ClaimDetail.class);
		query.setParameter("id",division);
		query.setParameter("from",from_date,TemporalType.DATE);
		query.setParameter("to", to_date,TemporalType.DATE);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ClaimDetail> claims=query.getResultList();
		return claims;
	}
	
	public List<ClaimDetail> getClaimDetailByEmpId(String empid, String range,String status){
		//entitymanager=getEntityManager();
		Date from_date=getDateFromRange(range);
		Date to_date=new Date();
		String querystr="SELECT claim FROM ClaimDetail claim where  claim.empId=:empid  AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		
		if(status.equals(ClaimStatus.REGISTERED)){
			querystr="SELECT claim FROM ClaimDetail claim where  claim.empId=:empid AND claim.status=1 AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		}
		else if(status.equals(ClaimStatus.APPROVED)){
			querystr="SELECT claim FROM ClaimDetail claim where  claim.empId=:empid AND claim.status=2 AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		}
		else if(status.equals(ClaimStatus.IN_PROGRESS)){
			querystr="SELECT claim FROM ClaimDetail claim where  claim.empId=:empid AND (claim.status=3 OR claim.status=4) AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
				
		}
		else if(status.equals(ClaimStatus.ACCEPTED)){
			querystr="SELECT claim FROM ClaimDetail claim where  claim.empId=:empid AND claim.status=5 AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
			
		}
		Query query=entitymanager.createQuery(querystr);
		query.setParameter("empid", empid);
		query.setParameter("from", from_date,TemporalType.DATE);
		query.setParameter("to", to_date,TemporalType.DATE);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ClaimDetail> claims=query.getResultList();
		return claims;
	}
	
	/*public List<ClaimDetail> getClaimDetailByDivision(int division, String range,String status){*/
	public List<ClaimDetail> getClaimDetailByDivision(List<Integer> division, String range,String status){
		//entitymanager=getEntityManager();
		Date from_date=getDateFromRange(range);
		Date to_date=new Date();
		//String querystr="SELECT claim FROM ClaimDetail claim where  claim.divisionId=:id  AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		String querystr="SELECT claim FROM ClaimDetail claim where  claim.divisionId IN :id  AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		if(status.equals(ClaimStatus.REGISTERED)){
			querystr="SELECT claim FROM ClaimDetail claim where  claim.divisionId IN :id AND claim.status=1 AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		}
		else if(status.equals(ClaimStatus.APPROVED)){
			querystr="SELECT claim FROM ClaimDetail claim where  claim.divisionId IN :id AND claim.status=2 AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		}
		else if(status.equals(ClaimStatus.IN_PROGRESS)){
			querystr="SELECT claim FROM ClaimDetail claim where  claim.divisionId IN :id AND (claim.status=3 OR claim.status=4) AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
				
		}
		else if(status.equals(ClaimStatus.ACCEPTED)){
			querystr="SELECT claim FROM ClaimDetail claim where  claim.divisionId IN :id AND claim.status=5 AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
			
		}
		Query query=entitymanager.createQuery(querystr);
		query.setParameter("id", division);
		query.setParameter("from", from_date,TemporalType.DATE);
		query.setParameter("to", to_date,TemporalType.DATE);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ClaimDetail> claims=query.getResultList();
		return claims;
	}
	
	public Date getDateFromRange(String range) {
		Date from_date = new Date();
		Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);  
        c.set(Calendar.MINUTE, 0);  
        c.set(Calendar.SECOND, 0);  
        c.set(Calendar.MILLISECOND, 0);  
		switch (range) {
			case "week":
				c.set(Calendar.DAY_OF_WEEK, 1);
				from_date = c.getTime();
				break;
			case "month":
				c.set(Calendar.DAY_OF_MONTH, 1);
				from_date = c.getTime();
				break;
			case "quarter":
				//c.add(Calendar.MONTH, -2);
				//c.set(Calendar.DAY_OF_MONTH, 1);
				from_date = getFirstDayOfQuarter(from_date);
				break;
			case "year":
				c.set(Calendar.DAY_OF_YEAR, 1);
				from_date = c.getTime();
				break;
			case "NONE":
				from_date = FromDate(range);
				break;
			default:
				c.add(Calendar.MONTH, -2);
				c.set(Calendar.DAY_OF_MONTH, 1);
				from_date = c.getTime();
				break;
		}
		return from_date;
	}
	
	public Date FromDate(String range){
		Date from_date = null;
		if(range.equals("NONE"))
		{
			try{
				Date fromDateDBString = (Date) entitymanager.createQuery("SELECT MIN(CD.id.claimDate) FROM ClaimDetail CD").getSingleResult();
				from_date = fromDateDBString;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return from_date;
		} 
	
	private static Date getFirstDayOfQuarter(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    return cal.getTime();
	}
	
	/* sharanya Start */
	public int[] getClaimsCountByAllDiv(String range) {
		Date from=getDateFromRange(range);
		Date to=new Date();
		String querystr = "SELECT claim FROM ClaimDetail claim where claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		Query query = entitymanager.createQuery(querystr, ClaimDetail.class);
		query.setParameter("from",from,TemporalType.DATE);
		query.setParameter("to", to,TemporalType.DATE);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ClaimDetail> claims=query.getResultList();
		int[] count=new int[4];
		count[0]=0;
		count[1]=0;
		count[2]=0;
		count[3]=0;
		
		for(ClaimDetail claim:claims){
			if(claim.getStatus().compareTo(BigDecimal.valueOf(1))==0)
				count[0]++;
			else if(claim.getStatus().compareTo(BigDecimal.valueOf(2))==0 )
				count[1]++;
			else if(claim.getStatus().compareTo(BigDecimal.valueOf(3))==0 ||claim.getStatus().compareTo(BigDecimal.valueOf(4))==0)
				count[2]++;
			else if(claim.getStatus().compareTo(BigDecimal.valueOf(5))==0)
				count[3]++;
		}
		return count;
		
	}

	public List<ClaimDetail> getClaimDetailByAllDiv(String range, String status) {
		Date from_date=getDateFromRange(range);
		Date to_date=new Date();
		String querystr="SELECT claim FROM ClaimDetail claim where AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		
		if(status.equals(ClaimStatus.REGISTERED)){
			querystr="SELECT claim FROM ClaimDetail claim where claim.status=1 AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		}
		else if(status.equals(ClaimStatus.APPROVED)){
			querystr="SELECT claim FROM ClaimDetail claim where claim.status=2 AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		}
		else if(status.equals(ClaimStatus.IN_PROGRESS)){
			querystr="SELECT claim FROM ClaimDetail claim where (claim.status=3 OR claim.status=4) AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
				
		}
		else if(status.equals(ClaimStatus.ACCEPTED)){
			querystr="SELECT claim FROM ClaimDetail claim where claim.status=5 AND claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
			
		}
		Query query=entitymanager.createQuery(querystr);
		query.setParameter("from", from_date,TemporalType.DATE);
		query.setParameter("to", to_date,TemporalType.DATE);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ClaimDetail> claims=query.getResultList();
		return claims;
	}

	public List<ClaimDetail> getClaimDetailByAllDiv(String range) {
		Date from_date=getDateFromRange(range);
		Date to_date=new Date();
		String querystr = "SELECT claim FROM ClaimDetail claim where claim.id.claimDate BETWEEN :from AND :to ORDER BY claim.id.claimDate DESC";
		Query query = entitymanager.createQuery(querystr, ClaimDetail.class);
		query.setParameter("from",from_date,TemporalType.DATE);
		query.setParameter("to", to_date,TemporalType.DATE);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ClaimDetail> claims=query.getResultList();
		return claims;
	}
	/* sharanya End */
}
