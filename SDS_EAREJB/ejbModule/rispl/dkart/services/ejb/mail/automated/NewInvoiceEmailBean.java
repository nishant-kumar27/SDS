package rispl.dkart.services.ejb.mail.automated;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import rispl.dkart.invoice.lookup.dao.InvoiceDAOBean;
import rispl.dkart.services.ejb.audit.MailAuditBean;

/**
 * Session Bean implementation class NewInvoiceEmail
 */
@Stateless(mappedName = "newInvoiceEmailBean")
@LocalBean
public class NewInvoiceEmailBean implements NewInvoiceEmailRemote {

	@EJB
	InvoiceDAOBean invoiceDao;

	@EJB
	MailAuditBean mailAudit;

	public NewInvoiceEmailBean() {
	}

	@Override
	public Map<String, String> getNewInvoiceToEmail(InvoiceDateRangeEnum invoiceDateRange) {

		Map<String, String> ret = new HashMap<>();

		// Get Invoices List
		List<List<String>> invoicesList = invoiceDao.getInvoicesList(InvoiceStatusEnum.ANY, invoiceDateRange);
		//System.out.println(invoicesList);

		// Get Email Audits List
		List<String> invoicesAuditList = mailAudit.getInvoicesList(MailRecipientTypeEnum.DEPARTMENT_HEAD);
		//System.out.println(invoicesAuditList);

		// Compare the two and get invoices that were not emailed
		if (invoicesList != null && invoicesAuditList != null) {
			//System.out.println(invoicesList.size());
			//Remove already audited invoices
			Iterator<List<String>> invoiceListIterator = invoicesList.iterator();

			while (invoiceListIterator.hasNext()) {
				Object invoiceWithDiv = invoiceListIterator.next();
				Object[] invoiceWithDivArr = (Object[]) invoiceWithDiv;
				String invoiceNo = (String) invoiceWithDivArr[0];
				if (invoicesAuditList.contains(invoiceNo))
					invoiceListIterator.remove();
			}
			//System.out.println(invoicesList.size());
		}

		// Return as map of [invoice,division]
		if (invoicesList != null && invoicesList.size() > 0) {
			for (Object invoice : invoicesList) {
				Object[] invoiceArr = (Object[]) invoice;
				if (invoiceArr[1] != null)
					ret.put(invoiceArr[0].toString(), invoiceArr[1].toString());
			}

		}

		return ret;
	}
}
