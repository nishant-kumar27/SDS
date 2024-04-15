package rispl.dkart.services.ejb.mail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dkart.services.ejb.audit.MailAuditBean;
import utility.ConfigUtils;

@Startup
@Singleton(mappedName = "mailBean")
public class MailBean implements MailBeanRemote {
	private static final Logger LOGGER = LogManager.getLogger(MailBean.class);
	Session mailSession;

	@EJB
	MailAuditBean mailAuditBean;

	List<Message> mailsList = Collections.synchronizedList(new ArrayList<Message>());

	@PostConstruct
	public void init() {
		InitialContext ic;
		try {
			ic = new InitialContext();
			mailSession = (Session) ic.lookup(ConfigUtils.getInstance().getEmailJndiServiceName());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
	}

	public MailBean() {
	}
	/*
	 * InitialContext ic; try { ic = new InitialContext(); mailSession =
	 * (Session) ic.lookup(ConfigUtils.getInstance().getEmailJndiServiceName());
	 * } catch (Exception e) { e.printStackTrace(); LOG.error(e.getMessage(),
	 * e); } }
	 */

	protected void pushMail(Message message) {
		mailsList.add(message);
	}

	@Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
	public void sendMail() {
		if (!mailsList.isEmpty()) {
			//access first message 
			Message fifoMessage = mailsList.get(0);
			//Send email
			try {
				Transport.send(fifoMessage);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			//remove sent message
			mailsList.remove(fifoMessage);
		}
	}

	@Override
	@Asynchronous
	public void sendEmailText(TransTypeEnum transtypeEnum, String transRefNo, MailRecipientTypeEnum recipientTypeEnum,
			String subject, String text, String to) {
		try {

			Message message = new MimeMessage(mailSession);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);

			pushMail(message);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		mailAuditBean.saveEmail(transtypeEnum, transRefNo, recipientTypeEnum, subject, text, to);
	}

	@Override
	@Asynchronous
	public void sendEmailHtml(TransTypeEnum transtypeEnum, String transRefNo, MailRecipientTypeEnum recipientTypeEnum,
			String subject, String content, String to) {
		String contentType = "text/html; charset=UTF-8";
		try {

			Message message = new MimeMessage(mailSession);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setContent(content, contentType);

			pushMail(message);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		mailAuditBean.saveEmail(transtypeEnum, transRefNo, recipientTypeEnum, subject, content, to);

	}

	@Override
	@Asynchronous
	public void sendEmailText(TransTypeEnum transtypeEnum, String transRefNo, MailRecipientTypeEnum recipientTypeEnum,
			String subject, String text, String... tos) {
		String to = String.join(",", tos);
		try {
			sendEmailText(transtypeEnum, transRefNo, recipientTypeEnum, subject, text, to);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

	@Override
	@Asynchronous
	public void sendEmailHtml(TransTypeEnum transtypeEnum, String transRefNo, MailRecipientTypeEnum recipientTypeEnum,
			String subject, String content, String... tos) {
		String to = String.join(",", tos);
		try {
			sendEmailHtml(transtypeEnum, transRefNo, recipientTypeEnum, subject, content, to);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
