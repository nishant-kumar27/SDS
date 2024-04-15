package rispl.dkart.invoice.lookup.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.test.entities.ReceivablesDetail;

import rispl.dkart.ConstantsIfc;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;

@Remote
public interface InvoiceDAOBeanInfc extends ConstantsIfc {
	
	public List<ReceivablesDetail> getReceivablesDetailByEmpId(String empId,String range);
	public List<ReceivablesDetail> getReceivablesDetailByEmpId(String empId,String range,String status);
	
	/*public List<ReceivablesDetail> getReceivablesDetailByDivision(int division,String range);*/
	public List<ReceivablesDetail> getReceivablesDetailByDivision(List<Integer> division,String range);
/*	public List<ReceivablesDetail> getReceivablesDetailByDivision(int  division,String range,String status);*/
	public List<ReceivablesDetail> getReceivablesDetailByDivision(List<Integer>  division,String range,String status);
	
	public BigDecimal[] getReceivablesAmountByEmpId(String empId,String range);
	/*public BigDecimal[] getReceivablesAmountByDivision(int division,String range);*/
	public BigDecimal[] getReceivablesAmountByDivision(List<Integer> division,String range);
	
	public List<CustomerSiteInvoice> getInvoiceByInvoiceNum(String invoiceNum);
/*	sharanya for dashboard*/
	public BigDecimal[] getReceivablesAmountByAllDiv(String range);
	public List<ReceivablesDetail> getReceivablesDetailByAllDiv(String range, String status);
	public List<ReceivablesDetail> getReceivablesDetailByAllDiv(String range);
}
