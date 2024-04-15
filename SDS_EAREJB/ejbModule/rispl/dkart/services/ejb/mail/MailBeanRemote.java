package rispl.dkart.services.ejb.mail;

import javax.ejb.Remote;

import rispl.dkart.ConstantsIfc;

@Remote
public interface MailBeanRemote extends ConstantsIfc {

	public void sendEmailText(TransTypeEnum transtypeEnum, String transRefNo, MailRecipientTypeEnum recipientTypeEnum,
			String subject, String text, String to);

	public void sendEmailText(TransTypeEnum transtypeEnum, String transRefNo, MailRecipientTypeEnum recipientTypeEnum,
			String subject, String text, String... tos);

	public void sendEmailHtml(TransTypeEnum transtypeEnum, String transRefNo, MailRecipientTypeEnum recipientTypeEnum,
			String subject, String content, String to);

	public void sendEmailHtml(TransTypeEnum transtypeEnum, String transRefNo, MailRecipientTypeEnum recipientTypeEnum,
			String subject, String content, String... tos);

}
