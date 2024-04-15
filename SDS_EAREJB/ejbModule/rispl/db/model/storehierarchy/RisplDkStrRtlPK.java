package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_STR_RTL database table.
 * 
 */
@Embeddable
public class RisplDkStrRtlPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RT_STR_ID")
	private String rtStrId;

	private String lcl;

	public RisplDkStrRtlPK() {
	}
	public String getRtStrId() {
		return this.rtStrId;
	}
	public void setRtStrId(String rtStrId) {
		this.rtStrId = rtStrId;
	}
	public String getLcl() {
		return this.lcl;
	}
	public void setLcl(String lcl) {
		this.lcl = lcl;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkStrRtlPK)) {
			return false;
		}
		RisplDkStrRtlPK castOther = (RisplDkStrRtlPK)other;
		return 
			this.rtStrId.equals(castOther.rtStrId)
			&& this.lcl.equals(castOther.lcl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rtStrId.hashCode();
		hash = hash * prime + this.lcl.hashCode();
		
		return hash;
	}
}