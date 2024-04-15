package rispl.dkart.invoice.lookup.dao;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.entities.ReceivablesDetail;

import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;

/**
 * Session Bean implementation class InvoiceDAOBean
 */
@Stateless(mappedName="invoiceDAOBean")
@LocalBean
public class InvoiceDAOBean implements InvoiceDAOBeanInfc {
	
	private static final Logger LOGGER = LogManager.getLogger(InvoiceDAOBean.class);
	
	InvoiceDAO dao;

	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	@PostConstruct
    void init()
    {
    	dao=new InvoiceDAO(emf.createEntityManager());
    }

    public InvoiceDAOBean() {    	
    }

    @Override
    public List<ReceivablesDetail> getReceivablesDetailByEmpId(String empId,String range){
    	List<ReceivablesDetail> invoices=dao.getReceivablesDetailByEmpId(empId,range);
    	return invoices;
    }

	@Override
	public BigDecimal[] getReceivablesAmountByEmpId(String empId,String range) {
		// TODO Auto-generated method stub
		BigDecimal[] amounts=dao.getReceivablesAmountByEmpId(empId,range);
		return amounts;
	}

	@Override
	public List<ReceivablesDetail> getReceivablesDetailByEmpId(String empId, String range, String status) {
		// TODO Auto-generated method stub
		List<ReceivablesDetail> invoices=dao.getReceivablesDetailByEmpId(empId, range, status);
		return invoices;
	}

	/*@Override
	public List<ReceivablesDetail> getReceivablesDetailByDivision(int division, String range) {*/
		// TODO Auto-generated method stub
	@Override
	public List<ReceivablesDetail> getReceivablesDetailByDivision(List<Integer> division, String range) {
		List<ReceivablesDetail> invoices=dao.getReceivablesDetailByDivision(division, range);
		return invoices;
	}
	
	@Override
	public List<ReceivablesDetail> getReceivablesDetailByAllDiv(String range) {
		List<ReceivablesDetail> invoices=dao.getReceivablesDetailByAllDiv(range);
		return invoices;
	}

	/*@Override
	public List<ReceivablesDetail> getReceivablesDetailByDivision(int division, String range, String status) {*/
		// TODO Auto-generated method stub
	@Override
	public List<ReceivablesDetail> getReceivablesDetailByDivision(List<Integer> division, String range, String status) {
		List<ReceivablesDetail> invoices=dao.getReceivablesDetailByDivision(division, range, status);
		return invoices;
	}

	@Override
	public List<ReceivablesDetail> getReceivablesDetailByAllDiv(String range, String status) {
		List<ReceivablesDetail> invoices=dao.getReceivablesDetailByAllDiv(range, status);
		return invoices;
	}
	
/*	@Override
	public BigDecimal[] getReceivablesAmountByDivision(int division, String range) {*/
	@Override
	public BigDecimal[] getReceivablesAmountByDivision(List<Integer> division, String range) {
		// TODO Auto-generated method stub
		BigDecimal[] amounts=dao.getReceivablesAmountByDivision(division, range);
		return amounts;
	}
	
	@Override
	public BigDecimal[] getReceivablesAmountByAllDiv(String range) {
		// TODO Auto-generated method stub
		BigDecimal[] amounts=dao.getReceivablesAmountByAllDiv(range);
		return amounts;
	}

	@SuppressWarnings("unchecked")
	public List<List<String>> getInvoicesList(InvoiceStatusEnum invoiceStatusEnum, InvoiceDateRangeEnum invoiceDateRange) {
		EntityManager em = emf.createEntityManager();
		List<List<String>> invoiceDivision = new ArrayList<>();
		Date filterDate = getDateFromInvoiceDateRange(invoiceDateRange);
		
		if (invoiceStatusEnum == null || invoiceStatusEnum == InvoiceStatusEnum.ANY) // get all invoice
		{
			invoiceDivision = em.createNamedQuery("ALL_INVOICE_NUMBERS_DIVISION")
					.setParameter("arInvDate", filterDate, TemporalType.DATE).getResultList();
		} else if (invoiceStatusEnum == InvoiceStatusEnum.OPEN) // get open invoices
		{
			invoiceDivision = em.createNamedQuery("OPEN_INVOICE_NUMBERS")
					.setParameter("arInvDate", filterDate, TemporalType.DATE).getResultList();
		} else if (invoiceStatusEnum == InvoiceStatusEnum.CLOSE) // get closed invoices
		{
			invoiceDivision = em.createNamedQuery("CLOSED_INVOICE_NUMBERS")
					.setParameter("arInvDate", filterDate, TemporalType.DATE).getResultList();
		}
		return invoiceDivision;
	}

	private Date getDateFromInvoiceDateRange(InvoiceDateRangeEnum invoiceDateRange) {
		Date filterDate = new Date(); //if (invoiceDateRange == InvoiceDateRangeEnum.WEEK)

		if (invoiceDateRange == InvoiceDateRangeEnum.WEEK) {
			filterDate = getStartDateOfCurrentWeek();
		} else if (invoiceDateRange == InvoiceDateRangeEnum.MONTH) {
			filterDate = getStartDateOfCurrentMonth();
		} else if (invoiceDateRange == InvoiceDateRangeEnum.QUARTER) {
			filterDate = getStartDateOfCurrentQuarter();
		} else if (invoiceDateRange == InvoiceDateRangeEnum.YEAR) {
			filterDate = getStartDateOfCurrentYear();
		}
		return filterDate;
	}

	private Date getStartDateOfCurrentYear() {
		LocalDate firstDateOfTheYear = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
		return Date.from(firstDateOfTheYear.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private Date getStartDateOfCurrentQuarter() {
		Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    return cal.getTime();
	}

	private Date getStartDateOfCurrentMonth() {
		LocalDate firstDateOfTheMonth = LocalDate.now().withDayOfMonth(1);
		return Date.from(firstDateOfTheMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private Date getStartDateOfCurrentWeek() {
		LocalDate today = LocalDate.now();

		LocalDate startDateOfTheWeek = today;
		while (startDateOfTheWeek.getDayOfWeek() != DayOfWeek.SUNDAY) {
			startDateOfTheWeek = startDateOfTheWeek.minusDays(1);
		}
		return Date.from(startDateOfTheWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public List<CustomerSiteInvoice> getInvoiceByInvoiceNum(String invoiceNum) {
		
		EntityManager em = emf.createEntityManager();
		List<CustomerSiteInvoice> invoiceList = new ArrayList<>();
		try{
			invoiceList = em.createNamedQuery("INVOICE_SEARCH_ORDER_INVOICE_NUM", CustomerSiteInvoice.class)
				.setParameter("invNum", null).setParameter("arInvNum", invoiceNum).getResultList();
		}
		catch(Exception e)
		{
			LOGGER.error("Error occured when searching for invoice with invoice id",e);
		}
		return invoiceList;
	}
}
