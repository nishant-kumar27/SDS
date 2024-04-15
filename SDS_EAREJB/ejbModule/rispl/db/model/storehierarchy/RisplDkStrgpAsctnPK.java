package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_STRGP_ASCTN database table.
 * 
 */
@Embeddable
public class RisplDkStrgpAsctnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_STRGP_FNC", unique=true, nullable=false, precision=22)
	private long idStrgpFnc;

	@Column(name="ID_STRGP_PRNT", unique=true, nullable=false, length=14)
	private String idStrgpPrnt;

	@Column(name="ID_STRGP_CHLD", unique=true, nullable=false, length=14)
	private String idStrgpChld;

	public RisplDkStrgpAsctnPK() {
	}
	public long getIdStrgpFnc() {
		return this.idStrgpFnc;
	}
	public void setIdStrgpFnc(long idStrgpFnc) {
		this.idStrgpFnc = idStrgpFnc;
	}
	public String getIdStrgpPrnt() {
		return this.idStrgpPrnt;
	}
	public void setIdStrgpPrnt(String idStrgpPrnt) {
		this.idStrgpPrnt = idStrgpPrnt;
	}
	public String getIdStrgpChld() {
		return this.idStrgpChld;
	}
	public void setIdStrgpChld(String idStrgpChld) {
		this.idStrgpChld = idStrgpChld;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkStrgpAsctnPK)) {
			return false;
		}
		RisplDkStrgpAsctnPK castOther = (RisplDkStrgpAsctnPK)other;
		return 
			(this.idStrgpFnc == castOther.idStrgpFnc)
			&& this.idStrgpPrnt.equals(castOther.idStrgpPrnt)
			&& this.idStrgpChld.equals(castOther.idStrgpChld);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idStrgpFnc ^ (this.idStrgpFnc >>> 32)));
		hash = hash * prime + this.idStrgpPrnt.hashCode();
		hash = hash * prime + this.idStrgpChld.hashCode();
		
		return hash;
	}
}