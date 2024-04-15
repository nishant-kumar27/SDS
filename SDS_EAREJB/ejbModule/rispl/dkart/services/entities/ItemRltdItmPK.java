package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_RLTD_ITMS database table.
 * 
 */
@Embeddable
public class ItemRltdItmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITM_ID")
	private String itmId;

	@Column(name="RLTD_ITM_ID")
	private String rltdItmId;

	@Column(name="RLTD_ITM_TYP_CD")
	private String rltdItmTypCd;

	public ItemRltdItmPK() {
	}
	public String getItmId() {
		return this.itmId;
	}
	public void setItmId(String itmId) {
		this.itmId = itmId;
	}
	public String getRltdItmId() {
		return this.rltdItmId;
	}
	public void setRltdItmId(String rltdItmId) {
		this.rltdItmId = rltdItmId;
	}
	public String getRltdItmTypCd() {
		return this.rltdItmTypCd;
	}
	public void setRltdItmTypCd(String rltdItmTypCd) {
		this.rltdItmTypCd = rltdItmTypCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemRltdItmPK)) {
			return false;
		}
		ItemRltdItmPK castOther = (ItemRltdItmPK)other;
		return 
			this.itmId.equals(castOther.itmId)
			&& this.rltdItmId.equals(castOther.rltdItmId)
			&& this.rltdItmTypCd.equals(castOther.rltdItmTypCd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.itmId.hashCode();
		hash = hash * prime + this.rltdItmId.hashCode();
		hash = hash * prime + this.rltdItmTypCd.hashCode();
		
		return hash;
	}
}