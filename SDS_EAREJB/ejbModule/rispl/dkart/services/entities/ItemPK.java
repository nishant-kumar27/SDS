package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_MSTR database table.
 * 
 */
@Embeddable
public class ItemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITM_ID",insertable=false, updatable=false)
	private String itmId;

	@Column(name="ID_ITM_POS",insertable=false, updatable=false)
	private String idItmPos;

	@Column(name="RT_STR_ID",insertable=false, updatable=false)
	private String rtStrId;
	
	@Column(name="ITM_TY_CD",insertable=false, updatable=false)
	private String itmTyCd;

	public ItemPK() {
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

	public String getItmTyCd() {
		return this.itmTyCd;
	}
	public void setItmTyCd(String itmTyCd) {
		this.itmTyCd = itmTyCd;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemPK)) {
			return false;
		}
		ItemPK castOther = (ItemPK)other;
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