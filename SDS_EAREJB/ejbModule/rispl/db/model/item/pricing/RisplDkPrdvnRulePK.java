package rispl.db.model.item.pricing;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_PRDVN_RULE database table.
 * 
 */
@Embeddable
public class RisplDkPrdvnRulePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_STR_RT", unique=true, nullable=false, length=5)
	private String idStrRt;

	@Column(name="ID_RU_PRDV", unique=true, nullable=false, precision=22)
	private long idRuPrdv;

	@Column(name="ID_PRM_CMP", unique=true, nullable=false, precision=22)
	private long idPrmCmp;
	
	@Column(name="ID_PRM_CMP_DTL", unique=true, nullable=false, precision=22)
	private long idPrmCmpDtl;
	

	public long getIdPrmCmpDtl() {
		return idPrmCmpDtl;
	}
	public void setIdPrmCmpDtl(long idPrmCmpDtl) {
		this.idPrmCmpDtl = idPrmCmpDtl;
	}
	public RisplDkPrdvnRulePK() {
	}
	public String getIdStrRt() {
		return this.idStrRt;
	}
	public void setIdStrRt(String idStrRt) {
		this.idStrRt = idStrRt;
	}
	public long getIdRuPrdv() {
		return this.idRuPrdv;
	}
	public void setIdRuPrdv(long idRuPrdv) {
		this.idRuPrdv = idRuPrdv;
	}
	public long getIdPrmCmp() {
		return this.idPrmCmp;
	}
	public void setIdPrmCmp(long idPrmCmp) {
		this.idPrmCmp = idPrmCmp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkPrdvnRulePK)) {
			return false;
		}
		RisplDkPrdvnRulePK castOther = (RisplDkPrdvnRulePK)other;
		return 
			this.idStrRt.equals(castOther.idStrRt)
			&& (this.idRuPrdv == castOther.idRuPrdv)
			&& (this.idPrmCmp == castOther.idPrmCmp)
			&& (this.idPrmCmpDtl == castOther.idPrmCmpDtl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idStrRt.hashCode();
		hash = hash * prime + ((int) (this.idRuPrdv ^ (this.idRuPrdv >>> 32)));
		hash = hash * prime + ((int) (this.idPrmCmp ^ (this.idPrmCmp >>> 32)));
		hash = hash * prime + ((int) (this.idPrmCmpDtl ^ (this.idPrmCmpDtl >>> 32)));
		
		return hash;
	}
}