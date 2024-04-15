package rispl.db.model.employee;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_EMP_MERCH_ASSOC database table.
 * 
 */
@Embeddable
public class RisplDkEmpMerchAssocPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "EMP_ID", insertable = false, updatable = false)
	private String empId;

	@Column(name = "STORE_ID", insertable = false, updatable = false)
	private String storeId;

	@Column(name = "MERCH_ID")
	private String merchId;

	public RisplDkEmpMerchAssocPK() {
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getMerchId() {
		return this.merchId;
	}

	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkEmpMerchAssocPK)) {
			return false;
		}
		RisplDkEmpMerchAssocPK castOther = (RisplDkEmpMerchAssocPK) other;
		return this.empId.equals(castOther.empId) && this.storeId.equals(castOther.storeId)
				&& this.merchId.equals(castOther.merchId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.empId.hashCode();
		hash = hash * prime + this.storeId.hashCode();
		hash = hash * prime + this.merchId.hashCode();

		return hash;
	}

	@Override
	public String toString() {
		return "RisplDkEmpMerchAssocPK [empId=" + empId + ", storeId=" + storeId + ", merchId=" + merchId + "]";
	}
}