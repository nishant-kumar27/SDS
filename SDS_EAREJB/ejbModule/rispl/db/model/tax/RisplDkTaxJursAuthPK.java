package rispl.db.model.tax;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_TAX_JURS_AUTH database table.
 * 
 */
@Embeddable
public class RisplDkTaxJursAuthPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="GEO_CDE_ID")
	private String geoCdeId;

	@Column(name="TAX_AUTH_ID")
	private long taxAuthId;

	public RisplDkTaxJursAuthPK() {
	}
	public String getGeoCdeId() {
		return this.geoCdeId;
	}
	public void setGeoCdeId(String geoCdeId) {
		this.geoCdeId = geoCdeId;
	}
	public long getTaxAuthId() {
		return this.taxAuthId;
	}
	public void setTaxAuthId(long taxAuthId) {
		this.taxAuthId = taxAuthId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkTaxJursAuthPK)) {
			return false;
		}
		RisplDkTaxJursAuthPK castOther = (RisplDkTaxJursAuthPK)other;
		return 
			this.geoCdeId.equals(castOther.geoCdeId)
			&& (this.taxAuthId == castOther.taxAuthId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.geoCdeId.hashCode();
		hash = hash * prime + ((int) (this.taxAuthId ^ (this.taxAuthId >>> 32)));
		
		return hash;
	}
}