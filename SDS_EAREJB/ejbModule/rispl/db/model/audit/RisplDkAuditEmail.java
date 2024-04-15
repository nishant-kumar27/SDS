package rispl.db.model.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the RISPL_DK_AUDIT_EMAIL database table.
 * 
 */
@Entity
@Table(name = "RISPL_DK_AUDIT_EMAIL")
@NamedQuery(name = "RisplDkAuditEmail.findAll", query = "SELECT r FROM RisplDkAuditEmail r")
public class RisplDkAuditEmail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auditEmailSeq")
	@SequenceGenerator(name = "auditEmailSeq", sequenceName = "RISPL_DK_AUDIT_EMAIL_SEQ", allocationSize = 1)
	@Column(name = "SEQ_ID", unique = true, nullable = false)
	private long seqId;

	@Column(name = "EMAIL_ADDR", nullable = false, length = 40)
	private String emailAddr;

	@Lob
	@Column(name = "EMAIL_ATTACH")
	private byte[] emailAttach;

	@Lob
	@Column(name = "EMAIL_BODY")
	private String emailBody;

	@Column(name = "EMAIL_SUBJ", nullable = false, length = 80)
	private String emailSubj;

	@Column(nullable = false, length = 40)
	private String recipient;

	@Column(name = "REF_NO", nullable = false, length = 40)
	private String refNo;

	@Column(name = "\"TIMESTAMP\"", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Column(name = "TRANS_TYPE", nullable = false, length = 40)
	private String transType;

	public RisplDkAuditEmail() {
	}

	public long getSeqId() {
		return this.seqId;
	}

	public void setSeqId(long seqId) {
		this.seqId = seqId;
	}

	public String getEmailAddr() {
		return this.emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public byte[] getEmailAttach() {
		return this.emailAttach;
	}

	public void setEmailAttach(byte[] emailAttach) {
		this.emailAttach = emailAttach;
	}

	public String getEmailBody() {
		return this.emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getEmailSubj() {
		return this.emailSubj;
	}

	public void setEmailSubj(String emailSubj) {
		this.emailSubj = emailSubj;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRefNo() {
		return this.refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return this.transType;
	}

	public void setType(String type) {
		this.transType = type;
	}

}