package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_STRGP_ASCTN_STR database table.
 * 
 */
@Embeddable
public class RisplDkStrgpAsctnStrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RT_STR_ID", unique=true, nullable=false, length=5)
	private String rtStrId;

	@Column(name="ID_STRGP", unique=true, nullable=false, length=14)
	private String idStrgp;

	@Column(name="ID_STRGP_FNC", unique=true, nullable=false, precision=22)
	private long idStrgpFnc;

	public RisplDkStrgpAsctnStrPK() {
	}
	public String getRtStrId() {
		return this.rtStrId;
	}
	public void setRtStrId(String rtStrId) {
		this.rtStrId = rtStrId;
	}
	public String getIdStrgp() {
		return this.idStrgp;
	}
	public void setIdStrgp(String idStrgp) {
		this.idStrgp = idStrgp;
	}
	public long getIdStrgpFnc() {
		return this.idStrgpFnc;
	}
	public void setIdStrgpFnc(long idStrgpFnc) {
		this.idStrgpFnc = idStrgpFnc;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkStrgpAsctnStrPK)) {
			return false;
		}
		RisplDkStrgpAsctnStrPK castOther = (RisplDkStrgpAsctnStrPK)other;
		return 
			this.rtStrId.equals(castOther.rtStrId)
			&& this.idStrgp.equals(castOther.idStrgp)
			&& (this.idStrgpFnc == castOther.idStrgpFnc);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rtStrId.hashCode();
		hash = hash * prime + this.idStrgp.hashCode();
		hash = hash * prime + ((int) (this.idStrgpFnc ^ (this.idStrgpFnc >>> 32)));
		
		return hash;
	}
}