package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_ATTR database table.
 * 
 */
@Embeddable
public class RisplDkItemAttrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RT_STR_ID", unique=true, nullable=false, length=5)
	private String rtStrId;

	@Column(name="ITM_ID", unique=true, nullable=false, length=25)
	private String itmId;

	@Column(name="ID_ITM_POS", unique=true, nullable=false, length=25)
	private String idItmPos;

	public RisplDkItemAttrPK() {
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
		if (!(other instanceof RisplDkItemAttrPK)) {
			return false;
		}
		RisplDkItemAttrPK castOther = (RisplDkItemAttrPK)other;
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