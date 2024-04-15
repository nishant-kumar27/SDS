package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_MSGS database table.
 * 
 */
@Embeddable
public class ItemMsgPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DISP_MSG_ID")
	private long dispMsgId;

	@Column(name="USG_TRN_TYP")
	private long usgTrnTyp;

	@Column(name="DPLY_LOC_TYP")
	private long dplyLocTyp;

	private String lcl;

	public ItemMsgPK() {
	}
	public long getDispMsgId() {
		return this.dispMsgId;
	}
	public void setDispMsgId(long dispMsgId) {
		this.dispMsgId = dispMsgId;
	}
	public long getUsgTrnTyp() {
		return this.usgTrnTyp;
	}
	public void setUsgTrnTyp(long usgTrnTyp) {
		this.usgTrnTyp = usgTrnTyp;
	}
	public long getDplyLocTyp() {
		return this.dplyLocTyp;
	}
	public void setDplyLocTyp(long dplyLocTyp) {
		this.dplyLocTyp = dplyLocTyp;
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
		if (!(other instanceof ItemMsgPK)) {
			return false;
		}
		ItemMsgPK castOther = (ItemMsgPK)other;
		return 
			(this.dispMsgId == castOther.dispMsgId)
			&& (this.usgTrnTyp == castOther.usgTrnTyp)
			&& (this.dplyLocTyp == castOther.dplyLocTyp)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.dispMsgId ^ (this.dispMsgId >>> 32)));
		hash = hash * prime + ((int) (this.usgTrnTyp ^ (this.usgTrnTyp >>> 32)));
		hash = hash * prime + ((int) (this.dplyLocTyp ^ (this.dplyLocTyp >>> 32)));
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}