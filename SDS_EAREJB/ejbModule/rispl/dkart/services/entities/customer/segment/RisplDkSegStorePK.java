package rispl.dkart.services.entities.customer.segment;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_SEG_STORE database table.
 * 
 */
@Embeddable
public class RisplDkSegStorePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SEGMENT_ID")
	private String segmentId;

	@Column(name="DIVISION_ID")
	private String divisionId;

	public RisplDkSegStorePK() {
	}
	public String getSegmentId() {
		return this.segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}
	public String getDivisionId() {
		return this.divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkSegStorePK)) {
			return false;
		}
		RisplDkSegStorePK castOther = (RisplDkSegStorePK)other;
		return 
			this.segmentId.equals(castOther.segmentId)
			&& this.divisionId.equals(castOther.divisionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.segmentId.hashCode();
		hash = hash * prime + this.divisionId.hashCode();
		
		return hash;
	}
}