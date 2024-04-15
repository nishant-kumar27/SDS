package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_MRCH_DPT database table.
 * 
 */
@Embeddable
public class RisplDkMrchDptPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_DPT_POS", unique=true, nullable=false, length=20)
	private String idDptPos;

	@Column(unique=true, nullable=false, length=20)
	private String lcl;

	public RisplDkMrchDptPK() {
	}
	public String getIdDptPos() {
		return this.idDptPos;
	}
	public void setIdDptPos(String idDptPos) {
		this.idDptPos = idDptPos;
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
		if (!(other instanceof RisplDkMrchDptPK)) {
			return false;
		}
		RisplDkMrchDptPK castOther = (RisplDkMrchDptPK)other;
		return 
			this.idDptPos.equals(castOther.idDptPos)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idDptPos.hashCode();
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}