package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_COLOR database table.
 * 
 */
@Embeddable
public class ItemColorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITM_CLR_CD")
	private String itmClrCd;

	private String lcl;

	public ItemColorPK() {
	}
	public String getItmClrCd() {
		return this.itmClrCd;
	}
	public void setItmClrCd(String itmClrCd) {
		this.itmClrCd = itmClrCd;
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
		if (!(other instanceof ItemColorPK)) {
			return false;
		}
		ItemColorPK castOther = (ItemColorPK)other;
		return 
			this.itmClrCd.equals(castOther.itmClrCd)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.itmClrCd.hashCode();
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}