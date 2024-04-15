package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_PRICE database table.
 * 
 */
@Embeddable
public class ItemPricePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="EVNT_ID")
	private long evntId;

	@Column(name="RT_STR_ID")
	private String rtStrId;

	public ItemPricePK() {
	}
	public long getEvntId() {
		return this.evntId;
	}
	public void setEvntId(long evntId) {
		this.evntId = evntId;
	}
	public String getRtStrId() {
		return this.rtStrId;
	}
	public void setRtStrId(String rtStrId) {
		this.rtStrId = rtStrId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemPricePK)) {
			return false;
		}
		ItemPricePK castOther = (ItemPricePK)other;
		return 
			(this.evntId == castOther.evntId)
			&& this.rtStrId.equals(castOther.rtStrId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.evntId ^ (this.evntId >>> 32)));
		hash = hash * prime + this.rtStrId.hashCode();
		
		return hash;
	}
}