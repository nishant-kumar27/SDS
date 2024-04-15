package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_MRCH_HIER database table.
 * 
 */
@Embeddable
public class RisplDkMrchHierPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_MRHRC_FNC", unique=true, nullable=false, precision=22)
	private long idMrhrcFnc;

	@Column(unique=true, nullable=false, length=10)
	private String lcl;

	public RisplDkMrchHierPK() {
	}
	public long getIdMrhrcFnc() {
		return this.idMrhrcFnc;
	}
	public void setIdMrhrcFnc(long idMrhrcFnc) {
		this.idMrhrcFnc = idMrhrcFnc;
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
		if (!(other instanceof RisplDkMrchHierPK)) {
			return false;
		}
		RisplDkMrchHierPK castOther = (RisplDkMrchHierPK)other;
		return 
			(this.idMrhrcFnc == castOther.idMrhrcFnc)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idMrhrcFnc ^ (this.idMrhrcFnc >>> 32)));
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}