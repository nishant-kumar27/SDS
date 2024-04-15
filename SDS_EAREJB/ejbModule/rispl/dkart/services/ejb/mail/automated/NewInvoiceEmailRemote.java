package rispl.dkart.services.ejb.mail.automated;

import java.util.Map;

import javax.ejb.Remote;

import rispl.dkart.ConstantsIfc;

@Remote
public interface NewInvoiceEmailRemote extends ConstantsIfc{

	public Map<String, String> getNewInvoiceToEmail(InvoiceDateRangeEnum invoiceDateRange);

}
