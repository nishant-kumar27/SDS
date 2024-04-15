package rispl.dkart.collection.lookup.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.test.entities.SdsCollectionsDashboard;

public class CollectionDAO {
	EntityManager entitymanager;
	public static SimpleDateFormat ft = new SimpleDateFormat("dd-MMM-yy");
	public static final String WEEK = "week";
	public static final String MONTH = "month";
	public static final String QUARTER = "quarter";
	public static final String YEAR = "year";
	public static final String NONE = "NONE";
	
	public CollectionDAO(EntityManager em) {
		entitymanager = em;
	}

	public List<SdsCollectionsDashboard> getCollectionsByEmpId(String empid, String range) {
		Date from = getDateFromRange(range);
		Date to = new Date();
		Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_EMPID", SdsCollectionsDashboard.class);
		query.setParameter("empid", empid);
		query.setParameter("from", from);
		query.setParameter("to", to);
		List<SdsCollectionsDashboard> collections = query.getResultList();
		return collections;
	}
	
	/*public List<SdsCollectionsDashboard> getCollectionsByDivision(int division, String range) {*/
	public List<SdsCollectionsDashboard> getCollectionsByDivision(List<Integer> division, String range) {
		Date from = getDateFromRange(range);
		Date to = new Date();
		Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_DIVISION", SdsCollectionsDashboard.class);
		query.setParameter("id", division);
		query.setParameter("from", from);
		query.setParameter("to", to);
		List<SdsCollectionsDashboard> collections = query.getResultList();
		return collections;
	}
	
	public List<SdsCollectionsDashboard> getCollectionsByEmpId(String empid, CollectionData collection_data) {
		Date from = collection_data.getFrom();
		Date to = collection_data.getTo();
		Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_EMPID", SdsCollectionsDashboard.class);
		query.setParameter("empid", empid);
		query.setParameter("from", from);
		query.setParameter("to", to);
		List<SdsCollectionsDashboard> collections = query.getResultList();
		return collections;
	}

	/*public List<SdsCollectionsDashboard> getCollectionsByDivision(int division, CollectionData collection_data) {*/
	public List<SdsCollectionsDashboard> getCollectionsByDivision(List<Integer> division, CollectionData collection_data) {
		Date from = collection_data.getFrom();
		Date to = collection_data.getTo();
		Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_DIVISION", SdsCollectionsDashboard.class);
		query.setParameter("id", division);
		query.setParameter("from", from);
		query.setParameter("to", to);
		List<SdsCollectionsDashboard> collections = query.getResultList();
		return collections;
	}
	
	public ArrayList<CollectionData> getCollectionChartData(String empid, String range) {
		ArrayList<CollectionData> collection_data;
		switch (range) {
		case WEEK:
			collection_data=getDailyCollectionData(empid);
			break;
		case MONTH:
			collection_data=getWeeklyCollectionData(empid);
			break;
		case QUARTER:
			collection_data=getQuaterlyCollectionData(empid);
			break;
		case YEAR:
			collection_data=getYearlyCollectionData(empid);
			break;
		case NONE:
			collection_data=getYearlyCollectionData(empid);
			break;
		default:
			collection_data=getQuaterlyCollectionData(empid);
			break;
		}
		return collection_data;
	}
	
	/*public ArrayList<CollectionData> getCollectionChartDataByDivision(int division, String range) {*/
	public ArrayList<CollectionData> getCollectionChartDataByDivision(List<Integer> division, String range) {
		ArrayList<CollectionData> collection_data;
		switch (range) {
		case WEEK:
			collection_data=getDailyCollectionDataByDivision(division);
			break;
		case MONTH:
			collection_data=getWeeklyCollectionDataByDivision(division);
			break;
		case QUARTER:
			collection_data=getQuaterlyCollectionDataByDivision(division);
			break;
		case YEAR:
			collection_data=getYearlyCollectionDataByDivision(division);
			break;
		case NONE:
			collection_data=getYearlyCollectionDataByDivision(division);
			break;
		default:
			collection_data=getQuaterlyCollectionDataByDivision(division);
			break;
		}
		return collection_data;
	}
	
	
	public ArrayList<CollectionData> getQuaterlyCollectionData(String empid) {
		Date from, to;
		Calendar temp = Calendar.getInstance();
		Date current = getLastDayOfQuarter(new Date());
		ArrayList<CollectionData> collection_data= new ArrayList<CollectionData>();
		BigDecimal sum = new BigDecimal(0);
		temp.setTime(current);
		for (int i = -2; i <= 0; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			temp.setTime(current);
			temp.add(Calendar.MONTH, i);
			temp.getTime();
			temp.set(Calendar.DAY_OF_MONTH, 1);
			col.setLabel(temp.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
			from = temp.getTime();
			col.setFrom(from);
			temp.set(Calendar.DAY_OF_MONTH, temp.getActualMaximum(Calendar.DAY_OF_MONTH));
			to = temp.getTime();
			col.setTo(to);
			Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_EMPID", SdsCollectionsDashboard.class);
			query.setParameter("empid", empid);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
			
		}
		return collection_data;
	}
	
	/*public ArrayList<CollectionData> getQuaterlyCollectionDataByDivision(int division) {*/
	public ArrayList<CollectionData> getQuaterlyCollectionDataByDivision(List<Integer> division) {
		Date from, to;
		Calendar temp = Calendar.getInstance();
		Date current = getLastDayOfQuarter(new Date());
		ArrayList<CollectionData> collection_data= new ArrayList<CollectionData>();
		BigDecimal sum = new BigDecimal(0);
		temp.setTime(current);
		for (int i = -2; i <= 0; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			temp.setTime(current);
			temp.add(Calendar.MONTH, i);
			temp.getTime();
			temp.set(Calendar.DAY_OF_MONTH, 1);
			col.setLabel(temp.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
			from = temp.getTime();
			col.setFrom(from);
			temp.set(Calendar.DAY_OF_MONTH, temp.getActualMaximum(Calendar.DAY_OF_MONTH));
			to = temp.getTime();
			col.setTo(to);
			Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_DIVISION", SdsCollectionsDashboard.class);
			query.setParameter("id", division);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
			
		}
		return collection_data;
	}
	
	public ArrayList<CollectionData> getYearlyCollectionData(String empid) {
		Date from, to;
		ArrayList<CollectionData> collection_data=new ArrayList<>();
		BigDecimal sum = new BigDecimal(0);
		Calendar temp = Calendar.getInstance();
		Date current = new Date();
		temp.setTime(current);
		int no_of_months = temp.get(Calendar.MONTH);
		for (int i = 0; i <= no_of_months; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			temp.set(Calendar.MONTH, i);
			temp.set(Calendar.DAY_OF_MONTH, 1);
			col.setLabel(temp.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
			from = temp.getTime();
			col.setFrom(from);
			temp.set(Calendar.DAY_OF_MONTH, temp.getActualMaximum(Calendar.DAY_OF_MONTH));
			to = temp.getTime();
			col.setTo(to);
			Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_EMPID", SdsCollectionsDashboard.class);
			query.setParameter("empid", empid);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
		}
		return collection_data;
	}
	
	/*public ArrayList<CollectionData> getYearlyCollectionDataByDivision(int division) {*/
	public ArrayList<CollectionData> getYearlyCollectionDataByDivision(List<Integer> division) {
		Date from, to;
		ArrayList<CollectionData> collection_data=new ArrayList<>();
		BigDecimal sum = new BigDecimal(0);
		Calendar temp = Calendar.getInstance();
		Date current = new Date();
		temp.setTime(current);
		int no_of_months = temp.get(Calendar.MONTH);
		for (int i = 0; i <= no_of_months; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			temp.set(Calendar.MONTH, i);
			temp.set(Calendar.DAY_OF_MONTH, 1);
			col.setLabel(temp.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
			from = temp.getTime();
			col.setFrom(from);
			temp.set(Calendar.DAY_OF_MONTH, temp.getActualMaximum(Calendar.DAY_OF_MONTH));
			to = temp.getTime();
			col.setTo(to);
			Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_DIVISION", SdsCollectionsDashboard.class);
			query.setParameter("id", division);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
		}
		return collection_data;
	}
	
	public ArrayList<CollectionData> getWeeklyCollectionData(String empid) {
		Date from, to;
		ArrayList<CollectionData> collection_data=new ArrayList<>();
		BigDecimal sum = new BigDecimal(0);
		Calendar temp = Calendar.getInstance();
		Date current = new Date();
		temp.setTime(current);
		int no_of_weeks = temp.get(Calendar.WEEK_OF_MONTH);
		int maxWeeknumber = temp.getActualMaximum(Calendar.WEEK_OF_MONTH);
		
		int count =0;
		int firstDate =0;
		int lastDate =0;
		for (int i = 1; i <= maxWeeknumber; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			if(i == 1){
			temp.set(Calendar.DAY_OF_MONTH,1);
			temp.getTime();
			//temp.set(Calendar.DAY_OF_WEEK, temp.getActualMinimum(Calendar.DAY_OF_WEEK));
			col.setLabel("Week " + i);
			from = temp.getTime();
			col.setFrom(from);
			firstDate = temp.getTime().getDate();
			
			}else{
				count =firstDate+lastDate;
				temp.set(Calendar.DAY_OF_MONTH,count);
				temp.getTime();
				temp.set(Calendar.DAY_OF_WEEK, temp.getActualMinimum(Calendar.DAY_OF_WEEK));
				col.setLabel("Week " + i);
				from = temp.getTime();
				col.setFrom(from);
				count =count+7;
			}
			temp.set(Calendar.DAY_OF_WEEK, temp.getActualMaximum(Calendar.DAY_OF_WEEK));
			to = temp.getTime();
			lastDate = temp.getTime().getDate();
			col.setTo(to);
			Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_EMPID", SdsCollectionsDashboard.class);
			query.setParameter("empid", empid);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
		}
		return collection_data;
	}
	
	
	/*public ArrayList<CollectionData> getWeeklyCollectionDataByDivision(int division) {*/
	public ArrayList<CollectionData> getWeeklyCollectionDataByDivision(List<Integer> division) {
		Date from, to;

		ArrayList<CollectionData> collection_data=new ArrayList<>();
		BigDecimal sum = new BigDecimal(0);
		Calendar temp = Calendar.getInstance();
		Date current = new Date();
		temp.setTime(current);
		int no_of_weeks = temp.get(Calendar.WEEK_OF_MONTH);
		int maxWeeknumber = temp.getActualMaximum(Calendar.WEEK_OF_MONTH);
		
		int count =0;
		int firstDate =0;
		int lastDate =0;
		for (int i = 1; i <= maxWeeknumber; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			if(i == 1){
			temp.set(Calendar.DAY_OF_MONTH,1);
			temp.getTime();
			//temp.set(Calendar.DAY_OF_WEEK, temp.getActualMinimum(Calendar.DAY_OF_WEEK));
			col.setLabel("Week " + i);
			from = temp.getTime();
			col.setFrom(from);
			firstDate = temp.getTime().getDate();
			
			}else{
				count =firstDate+lastDate;
				temp.set(Calendar.DAY_OF_MONTH,count);
				temp.getTime();
				temp.set(Calendar.DAY_OF_WEEK, temp.getActualMinimum(Calendar.DAY_OF_WEEK));
				col.setLabel("Week " + i);
				from = temp.getTime();
				col.setFrom(from);
				count =count+7;
			}
			temp.set(Calendar.DAY_OF_WEEK, temp.getActualMaximum(Calendar.DAY_OF_WEEK));
			to = temp.getTime();
			lastDate = temp.getTime().getDate();
			col.setTo(to);
			Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_DIVISION", SdsCollectionsDashboard.class);
			query.setParameter("id", division);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
		}
		return collection_data;
	}
	
	public ArrayList<CollectionData> getWeeklyCollectionDataByAllDiv(String range) {
		Date from, to;

		ArrayList<CollectionData> collection_data=new ArrayList<>();
		BigDecimal sum = new BigDecimal(0);
		Calendar temp = Calendar.getInstance();
		Date current = new Date();
		temp.setTime(current);
		int no_of_weeks = temp.get(Calendar.WEEK_OF_MONTH);
		int maxWeeknumber = temp.getActualMaximum(Calendar.WEEK_OF_MONTH);
		
		int count =0;
		int firstDate =0;
		int lastDate =0;
		for (int i = 1; i <= maxWeeknumber; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			if(i == 1){
			temp.set(Calendar.DAY_OF_MONTH,1);
			temp.getTime();
			//temp.set(Calendar.DAY_OF_WEEK, temp.getActualMinimum(Calendar.DAY_OF_WEEK));
			col.setLabel("Week " + i);
			from = temp.getTime();
			col.setFrom(from);
			firstDate = temp.getTime().getDate();
			
			}else{
				count =firstDate+lastDate;
				temp.set(Calendar.DAY_OF_MONTH,count);
				temp.getTime();
				temp.set(Calendar.DAY_OF_WEEK, temp.getActualMinimum(Calendar.DAY_OF_WEEK));
				col.setLabel("Week " + i);
				from = temp.getTime();
				col.setFrom(from);
				count =count+7;
			}
			temp.set(Calendar.DAY_OF_WEEK, temp.getActualMaximum(Calendar.DAY_OF_WEEK));
			to = temp.getTime();
			lastDate = temp.getTime().getDate();
			col.setTo(to);
			Query query = entitymanager.createQuery("Select col from SdsCollectionsDashboard col WHERE col.id.paymentDate BETWEEN :from AND :to ORDER BY col.id.paymentDate DESC", SdsCollectionsDashboard.class);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
		}
		return collection_data;
	}
	
	public ArrayList<CollectionData> getYearlyCollectionDataByAllDiv(String range) {
		Date from, to;
		ArrayList<CollectionData> collection_data=new ArrayList<>();
		BigDecimal sum = new BigDecimal(0);
		Calendar temp = Calendar.getInstance();
		Date current = new Date();
		temp.setTime(current);
		int no_of_months = temp.get(Calendar.MONTH);
		for (int i = 0; i <= no_of_months; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			temp.set(Calendar.MONTH, i);
			temp.set(Calendar.DAY_OF_MONTH, 1);
			col.setLabel(temp.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
			from = temp.getTime();
			col.setFrom(from);
			temp.set(Calendar.DAY_OF_MONTH, temp.getActualMaximum(Calendar.DAY_OF_MONTH));
			to = temp.getTime();
			col.setTo(to);
			Query query = entitymanager.createQuery("Select col from SdsCollectionsDashboard col WHERE col.id.paymentDate BETWEEN :from AND :to ORDER BY col.id.paymentDate DESC", SdsCollectionsDashboard.class);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
		}
		return collection_data;
	}
	public ArrayList<CollectionData> getQuaterlyCollectionDataByAllDiv(String range) {
		Date from, to;
		Calendar temp = Calendar.getInstance();
		Date current = getLastDayOfQuarter(new Date());
		ArrayList<CollectionData> collection_data= new ArrayList<CollectionData>();
		BigDecimal sum = new BigDecimal(0);
		temp.setTime(current);
		for (int i = -2; i <= 0; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			temp.setTime(current);
			temp.add(Calendar.MONTH, i);
			temp.getTime();
			temp.set(Calendar.DAY_OF_MONTH, 1);
			col.setLabel(temp.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
			from = temp.getTime();
			col.setFrom(from);
			temp.set(Calendar.DAY_OF_MONTH, temp.getActualMaximum(Calendar.DAY_OF_MONTH));
			to = temp.getTime();
			col.setTo(to);
			String querystr = "Select col from SdsCollectionsDashboard col WHERE col.id.paymentDate BETWEEN :from AND :to ORDER BY col.id.paymentDate DESC";
			Query query = entitymanager.createQuery(querystr, CollectionDAO.class);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
			
		}
		return collection_data;
	}
	
	
	public ArrayList<CollectionData> getDailyCollectionData(String empid) {
		Date from,to;
		ArrayList<BigDecimal> totals = new ArrayList<BigDecimal>();
		ArrayList<CollectionData> collection_data=new ArrayList<>();
		BigDecimal sum = new BigDecimal(0);
		Calendar temp = Calendar.getInstance();
		Date current = new Date();
		temp.setTime(current);
		temp.setTime(current);
		int no_of_days = temp.get(Calendar.DAY_OF_WEEK);
		for (int i = 1; i <= no_of_days; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			temp.set(Calendar.DAY_OF_WEEK, i);
			col.setLabel(temp.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
			from = temp.getTime();
			to=temp.getTime();
			col.setFrom(from);
			col.setTo(to);
			Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_EMPID", SdsCollectionsDashboard.class);
			query.setParameter("empid", empid);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
		}
		return collection_data;
	}
	
	
	/*public ArrayList<CollectionData> getDailyCollectionDataByDivision(int division) {*/
	public ArrayList<CollectionData> getDailyCollectionDataByDivision(List<Integer> division) {
		Date from,to;
		ArrayList<BigDecimal> totals = new ArrayList<BigDecimal>();
		ArrayList<CollectionData> collection_data=new ArrayList<>();
		BigDecimal sum = new BigDecimal(0);
		Calendar temp = Calendar.getInstance();
		Date current = new Date();
		temp.setTime(current);
		temp.setTime(current);
		int no_of_days = temp.get(Calendar.DAY_OF_WEEK);
		for (int i = 1; i <= no_of_days; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			temp.set(Calendar.DAY_OF_WEEK, i);
			col.setLabel(temp.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
			from = temp.getTime();
			to=temp.getTime();
			col.setFrom(from);
			col.setTo(to);
			Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_DIVISION", SdsCollectionsDashboard.class);
			query.setParameter("id", division);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
		}
		return collection_data;
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
			from_date =  getFirstDayOfQuarter(from_date);
			break;
		case "year":
			c.set(Calendar.DAY_OF_YEAR, 1);
			from_date = c.getTime();
			break;
		case "NONE":
			c.set(Calendar.DAY_OF_YEAR, 1);
			from_date = c.getTime();
			break;
		default:
			c.add(Calendar.MONTH, -2);
			c.set(Calendar.DAY_OF_MONTH, 1);
			from_date = c.getTime();
			break;
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

	
	public ArrayList<CollectionData> getDailyCollectionDataByAllDiv(String range) {
		Date from,to;
		ArrayList<BigDecimal> totals = new ArrayList<BigDecimal>();
		ArrayList<CollectionData> collection_data=new ArrayList<>();
		BigDecimal sum = new BigDecimal(0);
		Calendar temp = Calendar.getInstance();
		Date current = new Date();
		temp.setTime(current);
		temp.setTime(current);
		int no_of_days = temp.get(Calendar.DAY_OF_WEEK);
		for (int i = 1; i <= no_of_days; i++) {
			CollectionData col=new CollectionData();
			sum = BigDecimal.ZERO;
			temp.set(Calendar.DAY_OF_WEEK, i);
			col.setLabel(temp.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
			from = temp.getTime();
			to=temp.getTime();
			col.setFrom(from);
			col.setTo(to);
			Query query = entitymanager.createQuery("Select col from SdsCollectionsDashboard col WHERE col.id.paymentDate BETWEEN :from AND :to ORDER BY col.id.paymentDate DESC", SdsCollectionsDashboard.class);
			query.setParameter("from", from);
			query.setParameter("to", to);
			List<SdsCollectionsDashboard> collections = query.getResultList();
			if (collections != null) {
				for (SdsCollectionsDashboard each : collections) {
					sum = sum.add(each.getPaymentAmount());
				}
			}
			col.setTotal(sum);
			collection_data.add(col);
		}
		return collection_data;
	}

	public List<SdsCollectionsDashboard> getCollectionsByAllDiv(String range) {
		// TODO Auto-generated method stub
		Date from = getDateFromRange(range);
		Date to = new Date();
		//Query query = entitymanager.createNamedQuery("DB_COLLECTION_BY_DIVISION", SdsCollectionsDashboard.class);
		String querystr = "Select col from SdsCollectionsDashboard col WHERE col.id.paymentDate BETWEEN :from AND :to ORDER BY col.id.paymentDate DESC";
		Query query = entitymanager.createQuery(querystr, SdsCollectionsDashboard.class);
		query.setParameter("from", from);
		query.setParameter("to", to);
		List<SdsCollectionsDashboard> collections = query.getResultList();
		return collections;
	}

	public List<SdsCollectionsDashboard> getCollectionsByAllDiv(CollectionData collection_data) {
		// TODO Auto-generated method stub
		Date from = collection_data.getFrom();
		Date to = collection_data.getTo();
		String querystr = "Select col from SdsCollectionsDashboard col WHERE col.id.paymentDate BETWEEN :from AND :to ORDER BY col.id.paymentDate DESC";
		Query query = entitymanager.createQuery(querystr, SdsCollectionsDashboard.class);
		query.setParameter("from", from);
		query.setParameter("to", to);
		List<SdsCollectionsDashboard> collections = query.getResultList();
		return collections;
	}

	public ArrayList<CollectionData> getCollectionChartDataByAllDiv(String range) {
		// TODO Auto-generated method stub
		ArrayList<CollectionData> collection_data;
		switch (range) {
		case WEEK:
			collection_data=getDailyCollectionDataByAllDiv(range);
			break;
		case MONTH:
			collection_data=getWeeklyCollectionDataByAllDiv(range);
			break;
		case QUARTER:
			collection_data=getQuaterlyCollectionDataByAllDiv(range);
			break;
		case YEAR:
			collection_data=getYearlyCollectionDataByAllDiv(range);
			break;
		case NONE:
			collection_data=getYearlyCollectionDataByAllDiv(range);
			break;
		default:
			collection_data=getQuaterlyCollectionDataByAllDiv(range);
			break;
		}
		return collection_data;
	}
	
	private static Date getLastDayOfQuarter(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3 + 2);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    return cal.getTime();
	}
	
	
}
