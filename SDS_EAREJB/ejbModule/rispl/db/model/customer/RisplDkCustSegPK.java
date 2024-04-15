package rispl.db.model.customer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_CUST_SEG database table.
 * 
 */
@Embeddable
public class RisplDkCustSegPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PRCNG_GRP_ID")
	private long prcngGrpId;

	private String lcl;

	public RisplDkCustSegPK() {
	}
	public long getPrcngGrpId() {
		return this.prcngGrpId;
	}
	public void setPrcngGrpId(long prcngGrpId) {
		this.prcngGrpId = prcngGrpId;
	}
	public String getLcl() {
		return this.lcl;
	}
	public void setLcl(String lcl) {
		this.lcl = lcl;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkCustSegPK)) {
			return false;
		}
		RisplDkCustSegPK castOther = (RisplDkCustSegPK)other;
		return 
			(this.prcngGrpId == castOther.prcngGrpId)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.prcngGrpId ^ (this.prcngGrpId >>> 32)));
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}