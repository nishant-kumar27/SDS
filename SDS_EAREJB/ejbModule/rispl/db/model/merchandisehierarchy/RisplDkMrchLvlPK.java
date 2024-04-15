package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_MRCH_LVL database table.
 * 
 */
@Embeddable
public class RisplDkMrchLvlPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_MRHRC_LV", unique=true, nullable=false, precision=22)
	private long idMrhrcLv;

	@Column(unique=true, nullable=false, length=10)
	private String lcl;

	public RisplDkMrchLvlPK() {
	}
	public long getIdMrhrcLv() {
		return this.idMrhrcLv;
	}
	public void setIdMrhrcLv(long idMrhrcLv) {
		this.idMrhrcLv = idMrhrcLv;
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
		if (!(other instanceof RisplDkMrchLvlPK)) {
			return false;
		}
		RisplDkMrchLvlPK castOther = (RisplDkMrchLvlPK)other;
		return 
			(this.idMrhrcLv == castOther.idMrhrcLv)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idMrhrcLv ^ (this.idMrhrcLv >>> 32)));
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}