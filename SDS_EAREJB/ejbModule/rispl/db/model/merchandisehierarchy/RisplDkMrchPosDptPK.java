package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_MRCH_POS_DPT database table.
 * 
 */
@Embeddable
public class RisplDkMrchPosDptPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="POS_DPT_ID")
	private String posDptId;

	@Column(name="RT_STR_ID")
	private String rtStrId;

	public RisplDkMrchPosDptPK() {
	}
	public String getPosDptId() {
		return this.posDptId;
	}
	public void setPosDptId(String posDptId) {
		this.posDptId = posDptId;
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
		if (!(other instanceof RisplDkMrchPosDptPK)) {
			return false;
		}
		RisplDkMrchPosDptPK castOther = (RisplDkMrchPosDptPK)other;
		return 
			this.posDptId.equals(castOther.posDptId)
			&& this.rtStrId.equals(castOther.rtStrId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.posDptId.hashCode();
		hash = hash * prime + this.rtStrId.hashCode();
		
		return hash;
	}
}