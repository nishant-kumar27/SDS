package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ORD_INV_SHP_QTY_DK database table.
 * 
 */
@Embeddable
public class OrdInvShpQtyDkPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_ORD")
	private String idOrd;

	@Column(name="ITEM_ID")
	private String itemId;

	@Column(name="DK_WMS_BTCH")
	private String dkWmsBtch;

	@Column(name="SHP_QTY")
	private long shpQty;

	public OrdInvShpQtyDkPK() {
	}
	public String getIdOrd() {
		return this.idOrd;
	}
	public void setIdOrd(String idOrd) {
		this.idOrd = idOrd;
	}
	public String getItemId() {
		return this.itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getDkWmsBtch() {
		return this.dkWmsBtch;
	}
	public void setDkWmsBtch(String dkWmsBtch) {
		this.dkWmsBtch = dkWmsBtch;
	}
	public long getShpQty() {
		return this.shpQty;
	}
	public void setShpQty(long shpQty) {
		this.shpQty = shpQty;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrdInvShpQtyDkPK)) {
			return false;
		}
		OrdInvShpQtyDkPK castOther = (OrdInvShpQtyDkPK)other;
		return 
			this.idOrd.equals(castOther.idOrd)
			&& this.itemId.equals(castOther.itemId)
			&& this.dkWmsBtch.equals(castOther.dkWmsBtch)
			&& (this.shpQty == castOther.shpQty);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idOrd.hashCode();
		hash = hash * prime + this.itemId.hashCode();
		hash = hash * prime + this.dkWmsBtch.hashCode();
		hash = hash * prime + ((int) (this.shpQty ^ (this.shpQty >>> 32)));
		
		return hash;
	}
}