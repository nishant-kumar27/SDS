package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_MRCH_ASSN database table.
 * 
 */
@Embeddable
public class RisplDkMrchAssnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_MRHRC_GP_PRNT", unique=true, nullable=false, length=14)
	private String idMrhrcGpPrnt;

	@Column(name="ID_MRHRC_GP_CHLD", unique=true, nullable=false, length=14)
	private String idMrhrcGpChld;

	public RisplDkMrchAssnPK() {
	}
	public String getIdMrhrcGpPrnt() {
		return this.idMrhrcGpPrnt;
	}
	public void setIdMrhrcGpPrnt(String idMrhrcGpPrnt) {
		this.idMrhrcGpPrnt = idMrhrcGpPrnt;
	}
	public String getIdMrhrcGpChld() {
		return this.idMrhrcGpChld;
	}
	public void setIdMrhrcGpChld(String idMrhrcGpChld) {
		this.idMrhrcGpChld = idMrhrcGpChld;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkMrchAssnPK)) {
			return false;
		}
		RisplDkMrchAssnPK castOther = (RisplDkMrchAssnPK)other;
		return 
			this.idMrhrcGpPrnt.equals(castOther.idMrhrcGpPrnt)
			&& this.idMrhrcGpChld.equals(castOther.idMrhrcGpChld);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idMrhrcGpPrnt.hashCode();
		hash = hash * prime + this.idMrhrcGpChld.hashCode();
		
		return hash;
	}
}