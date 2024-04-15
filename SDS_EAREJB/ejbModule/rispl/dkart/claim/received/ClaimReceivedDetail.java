package rispl.dkart.claim.received;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the ORD_RET_RCV_QTY_DK database table.
 * 
 */
@Entity
@Table(name = "ORD_RET_RCV_QTY_DK")
@NamedQuery(name = "ClaimReceivedDetail.findAll", query = "SELECT c FROM ClaimReceivedDetail c")
public class ClaimReceivedDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClaimReceivedDetailPK id;

	@Column(name = "DK_WMS_BTCH")
	private String dkWmsBtch;

	@Column(name = "POST_FLAG")
	private String postFlag;

	@Column(name = "WH_RCV_QTY")
	private BigDecimal whRcvQty;

	public ClaimReceivedDetail() {
	}

	public ClaimReceivedDetailPK getId() {
		return this.id;
	}

	public void setId(ClaimReceivedDetailPK id) {
		this.id = id;
	}

	public String getDkWmsBtch() {
		return this.dkWmsBtch;
	}

	public void setDkWmsBtch(String dkWmsBtch) {
		this.dkWmsBtch = dkWmsBtch;
	}

	public String getPostFlag() {
		return this.postFlag;
	}

	public void setPostFlag(String postFlag) {
		this.postFlag = postFlag;
	}

	public BigDecimal getWhRcvQty() {
		return this.whRcvQty;
	}

	public void setWhRcvQty(BigDecimal whRcvQty) {
		this.whRcvQty = whRcvQty;
	}

	@Override
	public String toString() {
		return "ClaimReceivedDetail [id=" + id + ", dkWmsBtch=" + dkWmsBtch + ", postFlag=" + postFlag + ", whRcvQty="
				+ whRcvQty + "]";
	}
}