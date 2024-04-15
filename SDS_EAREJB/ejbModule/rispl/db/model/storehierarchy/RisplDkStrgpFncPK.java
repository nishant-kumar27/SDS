package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_STRGP_FNC database table.
 * 
 */
@Embeddable
public class RisplDkStrgpFncPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_STRGP_FNC")
	private long idStrgpFnc;

	private String lcl;

	public RisplDkStrgpFncPK() {
	}
	public long getIdStrgpFnc() {
		return this.idStrgpFnc;
	}
	public void setIdStrgpFnc(long idStrgpFnc) {
		this.idStrgpFnc = idStrgpFnc;
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
		if (!(other instanceof RisplDkStrgpFncPK)) {
			return false;
		}
		RisplDkStrgpFncPK castOther = (RisplDkStrgpFncPK)other;
		return 
			(this.idStrgpFnc == castOther.idStrgpFnc)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idStrgpFnc ^ (this.idStrgpFnc >>> 32)));
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}