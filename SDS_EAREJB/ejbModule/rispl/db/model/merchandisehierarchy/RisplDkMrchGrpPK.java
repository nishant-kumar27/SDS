package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_MRCH_GRP database table.
 * 
 */
@Embeddable
public class RisplDkMrchGrpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_MRHRC_GP")
	private String idMrhrcGp;

	private String lcl;

	public RisplDkMrchGrpPK() {
	}
	public String getIdMrhrcGp() {
		return this.idMrhrcGp;
	}
	public void setIdMrhrcGp(String idMrhrcGp) {
		this.idMrhrcGp = idMrhrcGp;
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
		if (!(other instanceof RisplDkMrchGrpPK)) {
			return false;
		}
		RisplDkMrchGrpPK castOther = (RisplDkMrchGrpPK)other;
		return 
			this.idMrhrcGp.equals(castOther.idMrhrcGp)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idMrhrcGp.hashCode();
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}