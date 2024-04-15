package rispl.dkart.services.entities.transaction;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ORD_TRAN_LINE_ITEM database table.
 * 
 */
@Embeddable
public class OrderTranLineItemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RT_STR_ID")
	private String rtStrId;

	@Column(name="ORD_WS")
	private String ordWs;

	@Column(name="TRN_SEQ",insertable=true, updatable=true)
	private long trnSeq;

	@Column(name="ORD_LN_ITM_SEQ")
	private long ordLnItmSeq;

	@Column(name="DC_DY_ORD")
	private String dcDyOrd;

	public OrderTranLineItemPK() {
	}
	public String getRtStrId() {
		return this.rtStrId;
	}
	public void setRtStrId(String rtStrId) {
		this.rtStrId = rtStrId;
	}
	public String getOrdWs() {
		return this.ordWs;
	}
	public void setOrdWs(String ordWs) {
		this.ordWs = ordWs;
	}
	public long getTrnSeq() {
		return this.trnSeq;
	}
	public void setTrnSeq(long trnSeq) {
		this.trnSeq = trnSeq;
	}
	public long getOrdLnItmSeq() {
		return this.ordLnItmSeq;
	}
	public void setOrdLnItmSeq(long ordLnItmSeq) {
		this.ordLnItmSeq = ordLnItmSeq;
	}
	public String getDcDyOrd() {
		return this.dcDyOrd;
	}
	public void setDcDyOrd(String dcDyOrd) {
		this.dcDyOrd = dcDyOrd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderTranLineItemPK)) {
			return false;
		}
		OrderTranLineItemPK castOther = (OrderTranLineItemPK)other;
		return 
			this.rtStrId.equals(castOther.rtStrId)
			&& this.ordWs.equals(castOther.ordWs)
			&& (this.trnSeq == castOther.trnSeq)
			&& (this.ordLnItmSeq == castOther.ordLnItmSeq)
			&& this.dcDyOrd.equals(castOther.dcDyOrd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rtStrId.hashCode();
		hash = hash * prime + this.ordWs.hashCode();
		hash = hash * prime + ((int) (this.trnSeq ^ (this.trnSeq >>> 32)));
		hash = hash * prime + ((int) (this.ordLnItmSeq ^ (this.ordLnItmSeq >>> 32)));
		hash = hash * prime + this.dcDyOrd.hashCode();
		
		return hash;
	}
}