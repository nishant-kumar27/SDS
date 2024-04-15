package rispl.ds.order;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.OrderTransactionSearchCriteria;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.ds.context.DKartContext;

public class PrintOrderAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private SessionMap<String, Object> sessionmap;
	private EmployeeIfc employee;
	private String orderId;
	private String tempFolder = System.getProperty("java.io.tmpdir");
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		sessionmap = (SessionMap<String, Object>) session;
		employee = (EmployeeIfc) sessionmap.get("employee");
	}

	public SessionMap<String, Object> getSessionmap() {
		return sessionmap;
	}

	public void setSessionmap(SessionMap<String, Object> sessionmap) {
		this.sessionmap = sessionmap;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}

	public String downloadCustomerOrder(){
		try {
			OrderTransactionsIfc trans = DKartContext.getLookupOrder();
			OrderTransactionSearchCriteriaIfc search = new OrderTransactionSearchCriteria();
			search.setSearchByinvoiceNumberOrOrderNumber(true);
			search.setInvoiceNumberOrOrderNumber(orderId);
			OrderTranHeader[] orders = trans.getTransactionsInvoices(search);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "sucess";
	}
	private File createInvoicePdf(OrderTranHeader order)
	{
		File file = new File(tempFolder + "/invoice.pdf");
		
		return null;
	}

}
