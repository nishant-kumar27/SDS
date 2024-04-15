package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_STR_DSTRCT database table.
 * 
 */
@Embeddable
public class RisplDkStrDstrctPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_STR_DSTRCT")
	private String idStrDstrct;

	private String lcl;

	public RisplDkStrDstrctPK() {
	}
	public String getIdStrDstrct() {
		return this.idStrDstrct;
	}
	public void setIdStrDstrct(String idStrDstrct) {
		this.idStrDstrct = idStrDstrct;
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
		if (!(other instanceof RisplDkStrDstrctPK)) {
			return false;
		}
		RisplDkStrDstrctPK castOther = (RisplDkStrDstrctPK)other;
		return 
			this.idStrDstrct.equals(castOther.idStrDstrct)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idStrDstrct.hashCode();
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}