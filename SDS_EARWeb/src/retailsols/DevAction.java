package retailsols;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.ServletActionContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.dkart.services.ejb.utils.UtilsBeanRemote;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class DevAction extends DSAction {

	private static final long serialVersionUID = 1L;

	List<List<String>> empList;
	List<List<String>> custList;
	private InputStream textStream;

	String styleHtml = "<style>html{-webkit-font-smoothing: antialiased !important;}table{font-size:small;}table {border-collapse: collapse;}th, td {border: 1px solid #e0e0e0;padding-left:4px;padding-right:4px;}tr:nth-child(odd) {background-color: #e0e0e0}</style>";

	public InputStream getTextStream() {
		return textStream;
	}

	public List<List<String>> getEmpList() {
		return empList;
	}

	public void setEmpList(List<List<String>> empList) {
		this.empList = empList;
	}

	public List<List<String>> getCustList() {
		return custList;
	}

	public void setCustList(List<List<String>> custList) {
		this.custList = custList;
	}

	public String getEmp() {
		try {
			LookUpEmployeeIfc lookUpEmployee = DKartContext.getLookupEmployee();

			empList = lookUpEmployee.getAllEmp();
			if (empList != null) {
				StringBuffer buf = new StringBuffer();
				buf.append(styleHtml);
				buf.append("<table>");
				buf.append("<thead><tr><th>Login Id</th><th>Status</th><th>Emp Id</th><th>Emp Name</th>"
						+ "<th>Role</th><th>Security</th><th>Email</th><th>Division</th></tr></thead>");
				buf.append("<tbody>");
				for (List<String> list : empList) {
					String str = "<tr>";
					for (String string : list) {
						str += "<td>" + string + "</td>";
					}
					str += "</tr>";
					buf.append(str);
				}
				buf.append("</tbody></table>");

				inputStream = new ByteArrayInputStream(buf.toString().getBytes("UTF-8"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String getCust() {
		try {
			LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();

			custList = lookUpCustomer.getAllCust();
			if (custList != null) {
				StringBuffer buf = new StringBuffer();
				buf.append(styleHtml);
				buf.append("<table>");
				buf.append("<thead><tr><th>Cust Id</th><th>Cust Name</th>"
						+ "<th>Linked Emp Id</th><th>Total Credit Limit</th><th>Av Credit Limit</th>"
						+ "<th>Division</th><th>Segment</th></tr></thead>");
				buf.append("<tbody>");
				for (List<String> list : custList) {
					String str = "<tr>";
					for (String string : list) {
						str += "<td>" + string + "</td>";
					}
					str += "</tr>";
					buf.append(str);
				}
				buf.append("</tbody></table>");

				inputStream = new ByteArrayInputStream(buf.toString().getBytes("UTF-8"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String getConfig() {
		try {
			UtilsBeanRemote utilsBeanRemote = DKartContext.getUtilsBean();

			Map<String, String> ejbConfig = utilsBeanRemote.getEJBConfig();

			if (ejbConfig != null) {
				StringBuffer buf = new StringBuffer();
				buf.append(styleHtml);
				buf.append("<table><tbody>");
				for (Entry<String, String> entry : ejbConfig.entrySet()) {
					String str = "<tr>";
					str += "<td style=\"font-weight: bold;\">" + entry.getKey() + "</td>";
					str += "<td>" + entry.getValue() + "</td>";
					str += "</tr>";
					buf.append(str);
				}
				buf.append("</tbody></table>");
				inputStream = new ByteArrayInputStream(buf.toString().getBytes("UTF-8"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String sendEmail() throws Exception {
		MailBeanRemote mailBeanRemote = DKartContext.getMailBean();
		mailBeanRemote.sendEmailHtml(MailBeanRemote.TransTypeEnum.TEST, null, MailBeanRemote.MailRecipientTypeEnum.CUSTOM, "Testing Javamail API",
				constructHtmlBodyForCustomer(), "saideepm@retailsols.com");

		return SUCCESS;
	}

	String constructHtmlBodyForCustomer() throws TemplateException, IOException {
		String authors[] = { "Paul L. Marino", "Sol Yurick", "Anonymous" };
		Configuration cfg = new Configuration();
		// cfg.setDirectoryForTemplateLoading(new
		// File("E:/Saideep/eclipse_mars/workspace/SDS_EARWeb/WebContent/freemarker"));
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "freemarker");
		Template template = cfg.getTemplate("email-test.ftl");
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("bookName", "The Warriors");
		rootMap.put("bookDetail",
				"Cyrus, the leader of the most powerful gang in New York City, the Gramercy Riffs, calls a midnight summit for all the area gangs, with all asked to send nine unarmed representatives for the conclave. A gang called The Warriors are blamed for killing Cyrus as he gives his speech.");
		rootMap.put("writers", authors);
		Writer out = new StringWriter();
		template.process(rootMap, out);
		return out.toString();
	}

	// --------------------------------------------------------------------------------------
	private String contentType;
	private String filename;
	private InputStream inputStream;
	private String contentDisposition;

	public String testEmbed() {
		contentType = "image/jpeg";// "application/pdf";
		filename = "Desert.jpg";
		contentDisposition = "inline;filename=\"" + filename + "\"";

		File file = new File("C:/Users/Public/Pictures/Sample Pictures/" + filename);
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
}
