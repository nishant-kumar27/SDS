package rispl.dkart.invoice.lookup.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import com.test.entities.ReceivablesDetail;

public class InvoiceDAO {
	public static SimpleDateFormat ft = new SimpleDateFormat("dd-MMM-yy");
	EntityManager entitymanager;
	public static final int CURRENT=0;
	public static final int OVERDUE=1;
	public InvoiceDAO() {
		
	}

	public InvoiceDAO(EntityManager em) {
		this.entitymanager = em;
	}

	public List<ReceivablesDetail> getReceivablesDetailByEmpId(String empId,String range){
		/*HashMap<String, BigDecimal> map=getRangePeriod(range);*/
		//entitymanager=getEntityManager();
		Date from=getDateFromRange(range);
		Date to=new Date();
		Query query=entitymanager.createNamedQuery("DB_RECEIVABLE_BY_EMPID",ReceivablesDetail.class);
		query.setParameter("empid",empId);
		query.setParameter("from", from);
		query.setParameter("to", to);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ReceivablesDetail> invoices=query.getResultList();
		return invoices;
	}

	/*public List<ReceivablesDetail> getReceivablesDetailByDivision(int division,String range){*/
	public List<ReceivablesDetail> getReceivablesDetailByDivision(List<Integer> division,String range){
		/*HashMap<String, BigDecimal> map=getRangePeriod(range);*/
		//entitymanager=getEntityManager();
		Date from=getDateFromRange(range);
		Date to=new Date();
		Query query=entitymanager.createNamedQuery("DB_RECEIVABLE_BY_DIVISION",ReceivablesDetail.class);
		query.setParameter("id",division);
		query.setParameter("from", from);
		query.setParameter("to", to);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ReceivablesDetail> invoices=query.getResultList();
		return invoices;
	}
	
	public List<ReceivablesDetail> getReceivablesDetailByEmpid(BigDecimal arg){
		
		return null;
	}

	public List<ReceivablesDetail> getReceivablesDetailByEmpId(String empId,String range,String status){
		//entitymanager=getEntityManager();
		if(status.equals("Current"))
			status="Current";
		else if(status.equals("Overdue"))
			status="Overdue";
		else
			return null;
		/*HashMap<String, BigDecimal> map=getRangePeriod(range);*/
		Date from=getDateFromRange(range);
		Date to=new Date();
		Query query=entitymanager.createNamedQuery("DB_RECEIVABLE_BY_EMPID_AND_STATUS",ReceivablesDetail.class);
		query.setParameter("from", from);
		query.setParameter("to", to);
		query.setParameter("empid",empId);
		query.setParameter("status", status);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ReceivablesDetail> invoices=query.getResultList();
		return invoices;
	}
	
/*	public List<ReceivablesDetail> getReceivablesDetailByDivision(int  division,String range,String status){*/
	public List<ReceivablesDetail> getReceivablesDetailByDivision(List<Integer>  division,String range,String status){
	//entitymanager=getEntityManager();
		if(status.equals("Current"))
			status="Current";
		else if(status.equals("Overdue"))
			status="Overdue";
		else
			return null;
		/*HashMap<String, BigDecimal> map=getRangePeriod(range);*/
		Date from=getDateFromRange(range);
		Date to=new Date();
		Query query=entitymanager.createNamedQuery("DB_RECEIVABLE_BY_DIVISION_AND_STATUS",ReceivablesDetail.class);
		query.setParameter("from", from);
		query.setParameter("to", to);
		query.setParameter("id",division);
		query.setParameter("status", status);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ReceivablesDetail> invoices=query.getResultList();
		return invoices;
	}
	
	public BigDecimal[] getReceivablesAmountByEmpId(String empId,String range){
		/*HashMap<String, BigDecimal> map=getRangePeriod(range);*/
		//entitymanager=getEntityManager();
		Date from =getDateFromRange(range);
		Date to=new Date();
		Query query=entitymanager.createNamedQuery("DB_RECEIVABLE_BY_EMPID",ReceivablesDetail.class);
		query.setParameter("empid",empId);
		query.setParameter("from", from);
		query.setParameter("to", to);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ReceivablesDetail> invoices=query.getResultList();
		BigDecimal[] amounts=new BigDecimal[2];
		
		amounts[CURRENT]=new BigDecimal(0);
		amounts[OVERDUE]=new BigDecimal(0);
		
		if(invoices!=null){
			for(ReceivablesDetail each : invoices ){
				if(each.getInvoiceStatus().equalsIgnoreCase("Current"))
					amounts[CURRENT]=amounts[CURRENT].add(each.getBalanceDue());
				else if(each.getInvoiceStatus().equalsIgnoreCase("Overdue"))
					amounts[OVERDUE]=amounts[OVERDUE].add(each.getBalanceDue());
				
			}
		}
		
		return amounts;
	}
	
/*	public BigDecimal[] getReceivablesAmountByDivision(int division,String range){*/
	public BigDecimal[] getReceivablesAmountByDivision(List<Integer> division,String range){	
	/*HashMap<String, BigDecimal> map=getRangePeriod(range);*/
		//entitymanager=getEntityManager();
		Date from =getDateFromRange(range);
		Date to=new Date();
		Query query=entitymanager.createNamedQuery("DB_RECEIVABLE_BY_DIVISION",ReceivablesDetail.class);
		query.setParameter("id",division);
		query.setParameter("from", from);
		query.setParameter("to", to);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ReceivablesDetail> invoices=query.getResultList();
		BigDecimal[] amounts=new BigDecimal[2];
		
		amounts[CURRENT]=new BigDecimal(0);
		amounts[OVERDUE]=new BigDecimal(0);
		
		if(invoices!=null){
			for(ReceivablesDetail each : invoices ){
				if(each.getInvoiceStatus().equalsIgnoreCase("Current"))
					amounts[CURRENT]=amounts[CURRENT].add(each.getBalanceDue());
				else if(each.getInvoiceStatus().equalsIgnoreCase("Overdue"))
					amounts[OVERDUE]=amounts[OVERDUE].add(each.getBalanceDue());
				
			}
		}
		
		return amounts;
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
			from_date =FromDate(range);
			break;
		default:
			break;
		}
		return from_date;
		
	}
	
	public Date FromDate(String range){
		Date from_date = null;
		//entitymanager=entitymanagerfact.createEntityManager();
		if(range.equals("NONE"))
		{
			try{
				Date fromDateDBString = (Date) entitymanager.createQuery("SELECT MIN(RD.id.invoiceDate) FROM ReceivablesDetail RD").getSingleResult();
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
		
	
	public HashMap<String, BigDecimal> getRangePeriod(String range){
		Calendar cal = new GregorianCalendar();
		HashMap<String, BigDecimal> map=new HashMap<>();
		switch (range) {
		case "week":
			map.put("start",new BigDecimal(cal.getActualMinimum(Calendar.DAY_OF_WEEK)));
			map.put("current",new BigDecimal(cal.get(Calendar.DAY_OF_WEEK)));
			map.put("remaining",new BigDecimal(cal.getActualMaximum(Calendar.DAY_OF_WEEK)-cal.get(Calendar.DAY_OF_WEEK)));
			break;
		case "month":
			map.put("start",new BigDecimal(cal.getActualMinimum(Calendar.DAY_OF_MONTH)));
			map.put("current",new BigDecimal(cal.get(Calendar.DAY_OF_MONTH)));
			map.put("remaining",new BigDecimal(cal.getActualMaximum(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_MONTH)));
			break;
		case "year":
			map.put("start",new BigDecimal(cal.getActualMinimum(Calendar.DAY_OF_YEAR)));
			map.put("current",new BigDecimal(cal.get(Calendar.DAY_OF_YEAR)));
			map.put("remaining",new BigDecimal(cal.getActualMaximum(Calendar.DAY_OF_YEAR)-cal.get(Calendar.DAY_OF_YEAR)));
			break;
		default:
			map.put("start",new BigDecimal(cal.getActualMinimum(Calendar.DAY_OF_WEEK)));
			Calendar temp=Calendar.getInstance();
			int no_of_days=0;
			temp.add(Calendar.MONTH, -1);
			no_of_days+=temp.getMaximum(Calendar.DAY_OF_MONTH);
			temp.add(Calendar.MONTH, -1);
			no_of_days+=temp.getMaximum(Calendar.DAY_OF_MONTH);
			temp=Calendar.getInstance();
			no_of_days+=cal.get(Calendar.DAY_OF_MONTH);
			map.put("current",new BigDecimal(no_of_days));
			map.put("remaining",new BigDecimal(cal.getActualMaximum(Calendar.DAY_OF_MONTH)-cal.get(Calendar.DAY_OF_MONTH)));
			break;
		}
		return map;
	}

	/* sharanya Start */
	public BigDecimal[] getReceivablesAmountByAllDiv(String range){	
		/*HashMap<String, BigDecimal> map=getRangePeriod(range);*/
			//entitymanager=getEntityManager();
			Date from =getDateFromRange(range);
			Date to=new Date();
			String querystr = "SELECT rd FROM ReceivablesDetail rd INNER JOIN OrderDetailsWithInvoice ord ON rd.orderId=ord.id.orderId and rd.id.invoiceDate between :from and :to ORDER BY rd.id.invoiceDate DESC";
			Query query = entitymanager.createQuery(querystr, ReceivablesDetail.class);
			query.setParameter("from", from);
			query.setParameter("to", to);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			List<ReceivablesDetail> invoices=query.getResultList();
			BigDecimal[] amounts=new BigDecimal[2];
			
			amounts[CURRENT]=new BigDecimal(0);
			amounts[OVERDUE]=new BigDecimal(0);
			
			if(invoices!=null){
				for(ReceivablesDetail each : invoices ){
					if(each.getInvoiceStatus().equalsIgnoreCase("Current"))
						amounts[CURRENT]=amounts[CURRENT].add(each.getBalanceDue());
					else if(each.getInvoiceStatus().equalsIgnoreCase("Overdue"))
						amounts[OVERDUE]=amounts[OVERDUE].add(each.getBalanceDue());
					
				}
			}
			
			return amounts;
		}

	public List<ReceivablesDetail> getReceivablesDetailByAllDiv(String range, String status) {
		if(status.equals("Current"))
			status="Current";
		else if(status.equals("Overdue"))
			status="Overdue";
		else
			return null;
		/*HashMap<String, BigDecimal> map=getRangePeriod(range);*/
		Date from=getDateFromRange(range);
		Date to=new Date();
		String querystr = "SELECT rd FROM ReceivablesDetail rd INNER JOIN OrderDetailsWithInvoice ord ON rd.orderId=ord.id.orderId and rd.id.invoiceDate between :from and :to and rd.invoiceStatus=:status ORDER BY rd.id.invoiceDate DESC";
		Query query = entitymanager.createQuery(querystr, ReceivablesDetail.class);
		query.setParameter("from", from);
		query.setParameter("to", to);
		query.setParameter("status", status);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ReceivablesDetail> invoices=query.getResultList();
		return invoices;
	}

	public List<ReceivablesDetail> getReceivablesDetailByAllDiv(String range) {
		Date from=getDateFromRange(range);
		Date to=new Date();
		String querystr = "SELECT rd FROM ReceivablesDetail rd INNER JOIN OrderDetailsWithInvoice ord ON rd.orderId=ord.id.orderId and rd.id.invoiceDate between :from and :to ORDER BY rd.id.invoiceDate DESC";
		Query query = entitymanager.createQuery(querystr, ReceivablesDetail.class);
		query.setParameter("from", from);
		query.setParameter("to", to);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ReceivablesDetail> invoices=query.getResultList();
		return invoices;
	}
	/* sharanya End */
}
