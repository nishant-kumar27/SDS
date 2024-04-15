package rispl.db.model.item.pricing;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_ITEM_SIMP_PROM database table.
 * 
 */
@Embeddable
public class RisplDkItemSimpPromPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_EV", unique=true, nullable=false, precision=38)
	private long idEv;

	@Column(name="RT_STR_ID", unique=true, nullable=false, length=5)
	private String rtStrId;

	@Column(name="ITM_ID", unique=true, nullable=false, length=14)
	private String itmId;

	public RisplDkItemSimpPromPK() {
	}
	public long getIdEv() {
		return this.idEv;
	}
	public void setIdEv(long idEv) {
		this.idEv = idEv;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkItemSimpPromPK)) {
			return false;
		}
		RisplDkItemSimpPromPK castOther = (RisplDkItemSimpPromPK)other;
		return 
			(this.idEv == castOther.idEv)
			&& this.rtStrId.equals(castOther.rtStrId)
			&& this.itmId.equals(castOther.itmId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idEv ^ (this.idEv >>> 32)));
		hash = hash * prime + this.rtStrId.hashCode();
		hash = hash * prime + this.itmId.hashCode();
		
		return hash;
	}
}