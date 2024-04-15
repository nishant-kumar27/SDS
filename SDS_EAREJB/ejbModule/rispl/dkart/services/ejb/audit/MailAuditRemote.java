package rispl.dkart.services.ejb.audit;

import java.util.List;

import javax.ejb.Remote;

import rispl.dkart.ConstantsIfc;

@Remote
public interface MailAuditRemote extends ConstantsIfc{
	public List<String> getInvoicesList(MailRecipientTypeEnum recipientTypeEnum);

	public void saveEmail(TransTypeEnum transtypeEnum, String transRefNo, MailRecipientTypeEnum recipientTypeEnum, String subject, String text,
			String to);
}
