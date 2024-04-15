package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_SIZE database table.
 * 
 */
@Embeddable
public class ItemSizePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITM_SZ_CD")
	private String itmSzCd;

	private String lcl;

	public ItemSizePK() {
	}
	public String getItmSzCd() {
		return this.itmSzCd;
	}
	public void setItmSzCd(String itmSzCd) {
		this.itmSzCd = itmSzCd;
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
		if (!(other instanceof ItemSizePK)) {
			return false;
		}
		ItemSizePK castOther = (ItemSizePK)other;
		return 
			this.itmSzCd.equals(castOther.itmSzCd)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.itmSzCd.hashCode();
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}