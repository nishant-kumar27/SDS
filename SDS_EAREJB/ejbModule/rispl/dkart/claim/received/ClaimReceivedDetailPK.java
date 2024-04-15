package rispl.dkart.claim.received;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ORD_RET_RCV_QTY_DK database table.
 * 
 */
@Embeddable
public class ClaimReceivedDetailPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "CLAIM_ID")
	private String claimId;

	@Column(name = "ITEM_ID")
	private String itemId;

	@Column(name = "DISP_CODE")
	private String dispCode;

	public ClaimReceivedDetailPK() {
	}

	public String getClaimId() {
		return this.claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getDispCode() {
		return this.dispCode;
	}

	public void setDispCode(String dispCode) {
		this.dispCode = dispCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((claimId == null) ? 0 : claimId.hashCode());
		result = prime * result + ((dispCode == null) ? 0 : dispCode.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClaimReceivedDetailPK other = (ClaimReceivedDetailPK) obj;
		if (claimId == null) {
			if (other.claimId != null)
				return false;
		} else if (!claimId.equals(other.claimId))
			return false;
		if (dispCode == null) {
			if (other.dispCode != null)
				return false;
		} else if (!dispCode.equals(other.dispCode))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClaimReceivedDetailPK [claimId=" + claimId + ", itemId=" + itemId + ", dispCode=" + dispCode + "]";
	}

}