package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_STR_RGN database table.
 * 
 */
@Embeddable
public class RisplDkStrRgnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_STR_RGN")
	private String idStrRgn;

	private String lcl;

	public RisplDkStrRgnPK() {
	}
	public String getIdStrRgn() {
		return this.idStrRgn;
	}
	public void setIdStrRgn(String idStrRgn) {
		this.idStrRgn = idStrRgn;
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
		if (!(other instanceof RisplDkStrRgnPK)) {
			return false;
		}
		RisplDkStrRgnPK castOther = (RisplDkStrRgnPK)other;
		return 
			this.idStrRgn.equals(castOther.idStrRgn)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idStrRgn.hashCode();
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}