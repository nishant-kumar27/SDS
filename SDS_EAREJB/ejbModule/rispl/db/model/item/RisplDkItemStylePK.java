package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_STYLE database table.
 * 
 */
@Embeddable
public class RisplDkItemStylePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITM_STYL_CD", unique=true, nullable=false, length=4)
	private String itmStylCd;

	@Column(unique=true, nullable=false, length=10)
	private String lcl;

	public RisplDkItemStylePK() {
	}
	public String getItmStylCd() {
		return this.itmStylCd;
	}
	public void setItmStylCd(String itmStylCd) {
		this.itmStylCd = itmStylCd;
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
		if (!(other instanceof RisplDkItemStylePK)) {
			return false;
		}
		RisplDkItemStylePK castOther = (RisplDkItemStylePK)other;
		return 
			this.itmStylCd.equals(castOther.itmStylCd)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.itmStylCd.hashCode();
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}