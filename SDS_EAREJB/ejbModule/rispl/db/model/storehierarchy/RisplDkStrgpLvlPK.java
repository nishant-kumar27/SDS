package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_STRGP_LVL database table.
 * 
 */
@Embeddable
public class RisplDkStrgpLvlPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_STRGP_FNC")
	private long idStrgpFnc;

	@Column(name="ID_STRGP_LV")
	private long idStrgpLv;

	public RisplDkStrgpLvlPK() {
	}
	public long getIdStrgpFnc() {
		return this.idStrgpFnc;
	}
	public void setIdStrgpFnc(long idStrgpFnc) {
		this.idStrgpFnc = idStrgpFnc;
	}
	public long getIdStrgpLv() {
		return this.idStrgpLv;
	}
	public void setIdStrgpLv(long idStrgpLv) {
		this.idStrgpLv = idStrgpLv;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkStrgpLvlPK)) {
			return false;
		}
		RisplDkStrgpLvlPK castOther = (RisplDkStrgpLvlPK)other;
		return 
			(this.idStrgpFnc == castOther.idStrgpFnc)
			&& (this.idStrgpLv == castOther.idStrgpLv);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idStrgpFnc ^ (this.idStrgpFnc >>> 32)));
		hash = hash * prime + ((int) (this.idStrgpLv ^ (this.idStrgpLv >>> 32)));
		
		return hash;
	}
}