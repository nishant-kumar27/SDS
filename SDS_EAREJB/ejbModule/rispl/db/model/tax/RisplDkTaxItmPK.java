package rispl.db.model.tax;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_TAX_ITM database table.
 * 
 */
@Embeddable
public class RisplDkTaxItmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_GP_TX")
	private long idGpTx;

	private String lcl;

	public RisplDkTaxItmPK() {
	}
	public long getIdGpTx() {
		return this.idGpTx;
	}
	public void setIdGpTx(long idGpTx) {
		this.idGpTx = idGpTx;
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
		if (!(other instanceof RisplDkTaxItmPK)) {
			return false;
		}
		RisplDkTaxItmPK castOther = (RisplDkTaxItmPK)other;
		return 
			(this.idGpTx == castOther.idGpTx)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idGpTx ^ (this.idGpTx >>> 32)));
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}