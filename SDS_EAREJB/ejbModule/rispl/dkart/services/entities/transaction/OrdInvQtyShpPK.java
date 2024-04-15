package rispl.dkart.services.entities.transaction;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ORD_INV_QTY_SHP database table.
 * 
 */
@Embeddable
public class OrdInvQtyShpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID")
	private String custId;

	@Column(name="ID_ORD_AR")
	private String idOrdAr;

	@Column(name="ID_ORD")
	private String idOrd;

	@Column(name="AR_TRX_LINE_ID")
	private String arTrxLineId;

	public OrdInvQtyShpPK() {
	}
	public String getCustId() {
		return this.custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getIdOrdAr() {
		return this.idOrdAr;
	}
	public void setIdOrdAr(String idOrdAr) {
		this.idOrdAr = idOrdAr;
	}
	public String getIdOrd() {
		return this.idOrd;
	}
	public void setIdOrd(String idOrd) {
		this.idOrd = idOrd;
	}
	public String getArTrxLineId() {
		return this.arTrxLineId;
	}
	public void setArTrxLineId(String arTrxLineId) {
		this.arTrxLineId = arTrxLineId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrdInvQtyShpPK)) {
			return false;
		}
		OrdInvQtyShpPK castOther = (OrdInvQtyShpPK)other;
		return 
			this.custId.equals(castOther.custId)
			&& this.idOrdAr.equals(castOther.idOrdAr)
			&& this.idOrd.equals(castOther.idOrd)
			&& this.arTrxLineId.equals(castOther.arTrxLineId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.custId.hashCode();
		hash = hash * prime + this.idOrdAr.hashCode();
		hash = hash * prime + this.idOrd.hashCode();
		hash = hash * prime + this.arTrxLineId.hashCode();
		
		return hash;
	}
}