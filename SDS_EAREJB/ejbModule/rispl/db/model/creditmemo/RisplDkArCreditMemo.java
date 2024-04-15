package rispl.db.model.creditmemo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the RISPL_DK_AR_CREDIT_MEMO database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_AR_CREDIT_MEMO")
@NamedQuery(name="RisplDkArCreditMemo.findAll", query="SELECT r FROM RisplDkArCreditMemo r")
public class RisplDkArCreditMemo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkArCreditMemoPK id;

	@Column(name="CR_MEMO_AMOUNT")
	private BigDecimal crMemoAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="CR_MEMO_DATE")
	private Date crMemoDate;
	
	@Column(name="CLAIM_ID")
	private String claimID;

	public String getClaimID() {
		return claimID;
	}

	public void setClaimID(String claimID) {
		this.claimID = claimID;
	}

	public RisplDkArCreditMemo() {
	}

	public RisplDkArCreditMemoPK getId() {
		return this.id;
	}

	public void setId(RisplDkArCreditMemoPK id) {
		this.id = id;
	}

	public BigDecimal getCrMemoAmount() {
		return this.crMemoAmount;
	}

	public void setCrMemoAmount(BigDecimal crMemoAmount) {
		this.crMemoAmount = crMemoAmount;
	}

	public Date getCrMemoDate() {
		return this.crMemoDate;
	}

	public void setCrMemoDate(Date crMemoDate) {
		this.crMemoDate = crMemoDate;
	}

}