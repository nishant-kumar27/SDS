package rispl.dkart.services.ejb.audit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import rispl.db.model.audit.RisplDkAuditEmail;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;

/**
 * Session Bean implementation class EmailAuditBean
 */
@Stateless(name = "mailAuditBean")
@LocalBean
public class MailAuditBean implements MailAuditRemote {

	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	public MailAuditBean() {

	}

	@Override
	public List<String> getInvoicesList(MailRecipientTypeEnum recipientTypeEnum) {
		EntityManager em = emf.createEntityManager();
		List<String> invoicesList = new ArrayList<String>();

		invoicesList = em.createNamedQuery("AUDIT_EMAIL_INVOICE_ALL", String.class)
				.setParameter(1, TransTypeEnum.INVOICE.toString()).setParameter(2, recipientTypeEnum.toString())
				.getResultList();

		return invoicesList;
	}

	@Override
	public void saveEmail(TransTypeEnum transtypeEnum, String transRefNo, MailRecipientTypeEnum recipientTypeEnum,
			String subject, String text, String to) {
		try {
			EntityManager em = emf.createEntityManager();

			if (to != null) {
				String[] tos = to.split(",");

				RisplDkAuditEmail auditEmail;
				for (String email : tos) {
					auditEmail = new RisplDkAuditEmail();
					auditEmail.setType(transtypeEnum.toString());
					auditEmail.setRefNo(transRefNo);
					auditEmail.setRecipient(recipientTypeEnum.toString());
					auditEmail.setEmailSubj(subject);
					auditEmail.setEmailBody(text);
					auditEmail.setEmailAddr(email);
					auditEmail.setTimestamp(new Date());
					em.getTransaction().begin();
					em.persist(auditEmail);
					em.getTransaction().commit();
				}
			}
		} catch (Exception e) {

		}

	}

}
