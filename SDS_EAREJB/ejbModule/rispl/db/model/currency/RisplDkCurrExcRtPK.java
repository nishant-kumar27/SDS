package rispl.db.model.currency;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_CURR_EXC_RT database table.
 * 
 */
@Embeddable
public class RisplDkCurrExcRtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="DC_RT_EXC_EF", unique=true, nullable=false)
	private java.util.Date dcRtExcEf;

	@Temporal(TemporalType.DATE)
	@Column(name="DC_RT_EXC_EP", unique=true, nullable=false)
	private java.util.Date dcRtExcEp;

	@Column(name="CURR_ISO_CDE", unique=true, nullable=false, length=10)
	private String currIsoCde;

	public RisplDkCurrExcRtPK() {
	}
	public java.util.Date getDcRtExcEf() {
		return this.dcRtExcEf;
	}
	public void setDcRtExcEf(java.util.Date dcRtExcEf) {
		this.dcRtExcEf = dcRtExcEf;
	}
	public java.util.Date getDcRtExcEp() {
		return this.dcRtExcEp;
	}
	public void setDcRtExcEp(java.util.Date dcRtExcEp) {
		this.dcRtExcEp = dcRtExcEp;
	}
	public String getCurrIsoCde() {
		return this.currIsoCde;
	}
	public void setCurrIsoCde(String currIsoCde) {
		this.currIsoCde = currIsoCde;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkCurrExcRtPK)) {
			return false;
		}
		RisplDkCurrExcRtPK castOther = (RisplDkCurrExcRtPK)other;
		return 
			this.dcRtExcEf.equals(castOther.dcRtExcEf)
			&& this.dcRtExcEp.equals(castOther.dcRtExcEp)
			&& this.currIsoCde.equals(castOther.currIsoCde);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.dcRtExcEf.hashCode();
		hash = hash * prime + this.dcRtExcEp.hashCode();
		hash = hash * prime + this.currIsoCde.hashCode();
		
		return hash;
	}
}