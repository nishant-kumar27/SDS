package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_MSGS database table.
 * 
 */
@Embeddable
public class RisplDkItemMsgPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DISP_MSG_ID", unique=true, nullable=false, precision=22)
	private long dispMsgId;

	@Column(unique=true, nullable=false, length=10)
	private String lcl;

	public RisplDkItemMsgPK() {
	}
	public long getDispMsgId() {
		return this.dispMsgId;
	}
	public void setDispMsgId(long dispMsgId) {
		this.dispMsgId = dispMsgId;
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
		if (!(other instanceof RisplDkItemMsgPK)) {
			return false;
		}
		RisplDkItemMsgPK castOther = (RisplDkItemMsgPK)other;
		return 
			(this.dispMsgId == castOther.dispMsgId)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.dispMsgId ^ (this.dispMsgId >>> 32)));
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}