package rispl.db.model.payment;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_AR_PAYM database table.
 * 
 */
@Embeddable
public class RisplDkArPaymPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PAYM_ID")
	private long paymId;

	@Column(name="AR_INV_NUM")
	private String arInvNum;

	public RisplDkArPaymPK() {
	}
	public long getPaymId() {
		return this.paymId;
	}
	public void setPaymId(long paymId) {
		this.paymId = paymId;
	}
	public String getArInvNum() {
		return this.arInvNum;
	}
	public void setArInvNum(String arInvNum) {
		this.arInvNum = arInvNum;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkArPaymPK)) {
			return false;
		}
		RisplDkArPaymPK castOther = (RisplDkArPaymPK)other;
		return 
			(this.paymId == castOther.paymId)
			&& this.arInvNum.equals(castOther.arInvNum);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.paymId ^ (this.paymId >>> 32)));
		hash = hash * prime + this.arInvNum.hashCode();
		
		return hash;
	}
}