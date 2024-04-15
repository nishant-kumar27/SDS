package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_ATTR database table.
 * 
 */
@Embeddable
public class ItemAttributesPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RT_STR_ID",insertable=true, updatable=true)
	private String rtStrId;

	@Column(name="ITM_ID",insertable=true, updatable=true)
	private String itmId;

	@Column(name="ID_ITM_POS",insertable=true, updatable=true)
	private String idItmPos;

	public ItemAttributesPK() {
	}
	public String getRtStrId() {
		return this.rtStrId;
	}
	public void setRtStrId(String rtStrId) {
		this.rtStrId = rtStrId;
	}
	public String getItmId() {
		return this.itmId;
	}
	public void setItmId(String itmId) {
		this.itmId = itmId;
	}
	public String getIdItmPos() {
		return this.idItmPos;
	}
	public void setIdItmPos(String idItmPos) {
		this.idItmPos = idItmPos;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemAttributesPK)) {
			return false;
		}
		ItemAttributesPK castOther = (ItemAttributesPK)other;
		return 
			this.rtStrId.equals(castOther.rtStrId)
			&& this.itmId.equals(castOther.itmId)
			&& this.idItmPos.equals(castOther.idItmPos);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rtStrId.hashCode();
		hash = hash * prime + this.itmId.hashCode();
		hash = hash * prime + this.idItmPos.hashCode();
		
		return hash;
	}
}