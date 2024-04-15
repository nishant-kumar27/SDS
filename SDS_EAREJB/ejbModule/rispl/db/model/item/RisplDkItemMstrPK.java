package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_MSTR database table.
 * 
 */
@Embeddable
public class RisplDkItemMstrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITM_ID", unique=true, nullable=false, length=25)
	private String itmId;

	@Column(name="ID_ITM_POS", unique=true, nullable=false, length=25)
	private String idItmPos;

	@Column(name="RT_STR_ID", unique=true, nullable=false, length=5)
	private String rtStrId;

	public RisplDkItemMstrPK() {
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
		if (!(other instanceof RisplDkItemMstrPK)) {
			return false;
		}
		RisplDkItemMstrPK castOther = (RisplDkItemMstrPK)other;
		return 
			this.itmId.equals(castOther.itmId)
			&& this.idItmPos.equals(castOther.idItmPos)
			&& this.rtStrId.equals(castOther.rtStrId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.itmId.hashCode();
		hash = hash * prime + this.idItmPos.hashCode();
		hash = hash * prime + this.rtStrId.hashCode();
		
		return hash;
	}
}