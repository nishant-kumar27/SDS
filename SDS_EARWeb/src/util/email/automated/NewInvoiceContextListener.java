package util.email.automated;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.invoice.lookup.dao.InvoiceDAOBeanInfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.mail.MailBeanRemote;
import rispl.dkart.services.ejb.mail.automated.NewInvoiceEmailRemote;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;
import rispl.ds.context.DKartContext;

public class NewInvoiceContextListener implements ServletContextListener {

	private ScheduledExecutorService scheduler;
//	private Log LOGGER = LogFactory.getLog(NewInvoiceContextListener.class);
	Logger LOGGER = LogManager.getLogger(NewInvoiceContextListener.class);
	long DELAY = 5, SCHEDULE = 3;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		scheduler.shutdownNow();

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		scheduler = Executors.newSingleThreadScheduledExecutor();
		LOGGER.info("Scheduling new invoice notification every {} minutes", SCHEDULE);
		scheduler.scheduleAtFixedRate(new NewInvoiceNotificationTask(event.getServletContext()), DELAY, SCHEDULE,
				/*TimeUnit.MINUTES);*/ //For production 
				TimeUnit.DAYS);//For development pupose

	}

	public class NewInvoiceNotificationTask implements Runnable {
		ServletContext servletContext;

		ParameterConfigurationServiceIfc parameterBean;
		NewInvoiceEmailRemote newInvoiceRemote;
		LookUpEmployeeIfc lookUpEmployee;
		MailBeanRemote mailBean;
		InvoiceDAOBeanInfc invoiceBean;

		public NewInvoiceNotificationTask(ServletContext servletContext) {
			this.servletContext = servletContext;
		}

		@Override
		public void run() {
			try {
				mailBean = DKartContext.getMailBean();
				parameterBean = DKartContext.getParamterBean();
				// check parameter to enable new invoice notifications
				if(parameterBean.fetchXMLParameterValues().getEnableNewInvoiceNotification().equalsIgnoreCase("No")) return;
				// filter based on new invoice date range
				String newInvoiceNotificationRange = parameterBean.fetchXMLParameterValues().getNewInvoiceNotificationRange();
				MailBeanRemote.InvoiceDateRangeEnum invoiceDateRange = getInvoiceDateRange(newInvoiceNotificationRange);
				LOGGER.info("New Invoices within this {} will be fetched", invoiceDateRange);
				
				String roleId = parameterBean.fetchXMLParameterValues().getDivisionHeadRoleID(); // Get division head role id
				LOGGER.info("Department Head Role is {}", roleId);
				
				newInvoiceRemote = DKartContext.getNewInvoiceEmailBean();
				Map<String, String> newInvoicesMap = newInvoiceRemote.getNewInvoiceToEmail(invoiceDateRange); // Get invoices to be sent to division heads
				if (newInvoicesMap.size() > 0)
					LOGGER.info("Invoice to send email are as follows:");
				for (Entry<String, String> entry : newInvoicesMap.entrySet()) {
					LOGGER.info("\tInvoice No: {}, Division ID: {}", entry.getKey(), entry.getValue());
				}
				lookUpEmployee = DKartContext.getLookupEmployee();

				if (newInvoicesMap != null && newInvoicesMap.size() > 0) {
					for (Entry<String, String> invoice : newInvoicesMap.entrySet()) {
						String invoiceNo = invoice.getKey();
						String divisionId = invoice.getValue();
						List<EmployeeIfc> divisionHeadByRole = lookUpEmployee
								.getEmployeeByRoleAndDivision(Long.valueOf(roleId), divisionId); // Get division heads based on division id
						if (divisionHeadByRole != null && divisionHeadByRole.size() > 0) {
							for (EmployeeIfc divisionHead : divisionHeadByRole) {
								String divisionHeadEmail = divisionHead.getEmail();
								if (divisionHeadEmail != null) {
									newInvoiceNotification(invoiceNo, MailBeanRemote.MailRecipientTypeEnum.DEPARTMENT_HEAD,
											divisionHeadEmail);
								}
							}
						}
					}
				}

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

		private MailBeanRemote.InvoiceDateRangeEnum getInvoiceDateRange(String newInvoiceNotificationRange) {
			if (newInvoiceNotificationRange == null) // FAILSAFE DEFAULT
				return MailBeanRemote.InvoiceDateRangeEnum.WEEK;
			
			else if (newInvoiceNotificationRange.contains("WEEK"))
				return MailBeanRemote.InvoiceDateRangeEnum.WEEK;
			else if (newInvoiceNotificationRange.contains("MONTH"))
				return MailBeanRemote.InvoiceDateRangeEnum.MONTH;
			else if (newInvoiceNotificationRange.contains("QUARTER"))
				return MailBeanRemote.InvoiceDateRangeEnum.QUARTER;
			else if (newInvoiceNotificationRange.contains("YEAR"))
				return MailBeanRemote.InvoiceDateRangeEnum.YEAR;
			else
				return MailBeanRemote.InvoiceDateRangeEnum.TODAY;
		}

		void newInvoiceNotification(String invoiceNo, MailBeanRemote.MailRecipientTypeEnum recipientTypeEnum, String email) {
			try {
				invoiceBean = DKartContext.getInvoiceDAOBean();

				LOGGER.info("Building email content");
				List<CustomerSiteInvoice> invoiceList = invoiceBean.getInvoiceByInvoiceNum(invoiceNo);
				String subject = "New invoice generated " + invoiceNo;
				String content = subject;
				if (invoiceList != null && invoiceList.size() > 0)
					content = contentForInvoice(invoiceList);
				LOGGER.info("Notifying {} of the New Invoice {}", email, invoiceNo);
				mailBean.sendEmailHtml(MailBeanRemote.TransTypeEnum.INVOICE, invoiceNo, recipientTypeEnum, subject,
						content, email);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

		private String contentForInvoice(List<CustomerSiteInvoice> invoiceList) {
			try {
				CustomerSiteInvoice invoice = invoiceList.get(0);

				Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
				LOGGER.info("Init freemarker configuration");
				cfg.setServletContextForTemplateLoading(servletContext, "freemarker"); //for template loading
				LOGGER.info("Getting freemarker template for new invoice notification email");
				Template template = cfg.getTemplate("email-newinvoice-division.ftl"); //template name

				LOGGER.info("Loading invoice data into template");
				Map<String, Object> rootMap = new HashMap<String, Object>();
				rootMap.put("invoice", invoice);

				//rootMap.put("invoiceNum", invoice.getArInvNum());
				//rootMap.put("invoiceDate", invoice.getArInvDate());
				//rootMap.put("invoiceAmount", invoice.getInvAmount());
				//rootMap.put("customer", invoice.getCustomerSite().getCustomerSitePK().getCustId());
				//rootMap.put("orderNum", invoice.getOrderNum());
				//rootMap.put("orderDate", invoice.getOrderDate());
				//rootMap.put("invoiceNum", "");

				Writer out = new StringWriter();
				LOGGER.info("Processing freemarker template");
				template.process(rootMap, out);
				return out.toString();
			} catch (TemplateException | IOException e) {
				e.printStackTrace();
			}
			return "";
		}

	}
}
