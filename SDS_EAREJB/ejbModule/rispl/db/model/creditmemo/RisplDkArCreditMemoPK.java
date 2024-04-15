package rispl.db.model.creditmemo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_AR_CREDIT_MEMO database table.
 * 
 */
@Embeddable
public class RisplDkArCreditMemoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CR_MEMO_NUM")
	private String crMemoNum;

	@Column(name="CUST_ID")
	private String custId;

	public RisplDkArCreditMemoPK() {
	}
	public String getCrMemoNum() {
		return this.crMemoNum;
	}
	public void setCrMemoNum(String crMemoNum) {
		this.crMemoNum = crMemoNum;
	}
	public String getCustId() {
		return this.custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkArCreditMemoPK)) {
			return false;
		}
		RisplDkArCreditMemoPK castOther = (RisplDkArCreditMemoPK)other;
		return 
			this.crMemoNum.equals(castOther.crMemoNum)
			&& this.custId.equals(castOther.custId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.crMemoNum.hashCode();
		hash = hash * prime + this.custId.hashCode();
		
		return hash;
	}
}