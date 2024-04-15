package rispl.db.model.dime.status;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_DIME_FILE_STTS database table.
 * 
 */
@Embeddable
public class RisplDkDimeFileSttPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="NM_BNDL_IMP")
	private String nmBndlImp;

	@Column(name="NM_FL_IMP")
	private String nmFlImp;

	public RisplDkDimeFileSttPK() {
	}
	public String getNmBndlImp() {
		return this.nmBndlImp;
	}
	public void setNmBndlImp(String nmBndlImp) {
		this.nmBndlImp = nmBndlImp;
	}
	public String getNmFlImp() {
		return this.nmFlImp;
	}
	public void setNmFlImp(String nmFlImp) {
		this.nmFlImp = nmFlImp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkDimeFileSttPK)) {
			return false;
		}
		RisplDkDimeFileSttPK castOther = (RisplDkDimeFileSttPK)other;
		return 
			this.nmBndlImp.equals(castOther.nmBndlImp)
			&& this.nmFlImp.equals(castOther.nmFlImp);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nmBndlImp.hashCode();
		hash = hash * prime + this.nmFlImp.hashCode();
		
		return hash;
	}
}