package rispl.dkart.services.entities.transaction;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ORD_INV_SHP_QTY_SRLNO database table.
 * 
 */
@Embeddable
public class OrdInvShpQtySrlnoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_ORD")
	private String idOrd;

	@Column(name="DK_WMS_BTCH")
	private String dkWmsBtch;

	@Column(name="ITEM_ID")
	private String itemId;

	@Column(name="SHP_QTY")
	private long shpQty;

	@Column(name="SERIAL_NO")
	private String serialNo;

	@Column(name="PACK_INDICATOR")
	private String packIndicator;

	public OrdInvShpQtySrlnoPK() {
	}
	public String getIdOrd() {
		return this.idOrd;
	}
	public void setIdOrd(String idOrd) {
		this.idOrd = idOrd;
	}
	public String getDkWmsBtch() {
		return this.dkWmsBtch;
	}
	public void setDkWmsBtch(String dkWmsBtch) {
		this.dkWmsBtch = dkWmsBtch;
	}
	public String getItemId() {
		return this.itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public long getShpQty() {
		return this.shpQty;
	}
	public void setShpQty(long shpQty) {
		this.shpQty = shpQty;
	}
	public String getSerialNo() {
		return this.serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getPackIndicator() {
		return this.packIndicator;
	}
	public void setPackIndicator(String packIndicator) {
		this.packIndicator = packIndicator;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrdInvShpQtySrlnoPK)) {
			return false;
		}
		OrdInvShpQtySrlnoPK castOther = (OrdInvShpQtySrlnoPK)other;
		return 
			this.idOrd.equals(castOther.idOrd)
			&& this.dkWmsBtch.equals(castOther.dkWmsBtch)
			&& this.itemId.equals(castOther.itemId)
			&& (this.shpQty == castOther.shpQty)
			&& this.serialNo.equals(castOther.serialNo)
			&& this.packIndicator.equals(castOther.packIndicator);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idOrd.hashCode();
		hash = hash * prime + this.dkWmsBtch.hashCode();
		hash = hash * prime + this.itemId.hashCode();
		hash = hash * prime + ((int) (this.shpQty ^ (this.shpQty >>> 32)));
		hash = hash * prime + this.serialNo.hashCode();
		hash = hash * prime + this.packIndicator.hashCode();
		
		return hash;
	}
}