package rispl.db.model.tax;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_TAX_RTE_RULE database table.
 * 
 */
@Embeddable
public class RisplDkTaxRteRulePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TAX_AUTH_ID", unique=true, nullable=false, precision=22)
	private long taxAuthId;

	@Column(name="ID_GP_TX", unique=true, nullable=false, precision=22)
	private long idGpTx;

	@Column(name="TAX_TYP_ID", unique=true, nullable=false, precision=22)
	private long taxTypId;

	@Column(name="TAX_HLDY_FLG", unique=true, nullable=false, length=1)
	private String taxHldyFlg;

	@Column(name="AI_TX_RT_RU", unique=true, nullable=false, precision=22)
	private long aiTxRtRu;

	public RisplDkTaxRteRulePK() {
	}
	public long getTaxAuthId() {
		return this.taxAuthId;
	}
	public void setTaxAuthId(long taxAuthId) {
		this.taxAuthId = taxAuthId;
	}
	public long getIdGpTx() {
		return this.idGpTx;
	}
	public void setIdGpTx(long idGpTx) {
		this.idGpTx = idGpTx;
	}
	public long getTaxTypId() {
		return this.taxTypId;
	}
	public void setTaxTypId(long taxTypId) {
		this.taxTypId = taxTypId;
	}
	public String getTaxHldyFlg() {
		return this.taxHldyFlg;
	}
	public void setTaxHldyFlg(String taxHldyFlg) {
		this.taxHldyFlg = taxHldyFlg;
	}
	public long getAiTxRtRu() {
		return this.aiTxRtRu;
	}
	public void setAiTxRtRu(long aiTxRtRu) {
		this.aiTxRtRu = aiTxRtRu;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkTaxRteRulePK)) {
			return false;
		}
		RisplDkTaxRteRulePK castOther = (RisplDkTaxRteRulePK)other;
		return 
			(this.taxAuthId == castOther.taxAuthId)
			&& (this.idGpTx == castOther.idGpTx)
			&& (this.taxTypId == castOther.taxTypId)
			&& this.taxHldyFlg.equals(castOther.taxHldyFlg)
			&& (this.aiTxRtRu == castOther.aiTxRtRu);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.taxAuthId ^ (this.taxAuthId >>> 32)));
		hash = hash * prime + ((int) (this.idGpTx ^ (this.idGpTx >>> 32)));
		hash = hash * prime + ((int) (this.taxTypId ^ (this.taxTypId >>> 32)));
		hash = hash * prime + this.taxHldyFlg.hashCode();
		hash = hash * prime + ((int) (this.aiTxRtRu ^ (this.aiTxRtRu >>> 32)));
		
		return hash;
	}
}