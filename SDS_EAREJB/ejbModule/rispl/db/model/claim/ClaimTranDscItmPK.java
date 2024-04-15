package rispl.db.model.claim;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CLAIM_TRAN_DSC_ITM database table.
 * 
 */
@Embeddable
public class ClaimTranDscItmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RT_STR_ID", insertable=false, updatable=false)
	private String rtStrId;

	@Column(name="ORD_WS", insertable=false, updatable=false)
	private String ordWs;

	@Column(name="TRN_SEQ", insertable=false, updatable=false)
	private long trnSeq;

	@Column(name="ORD_LN_ITM_SEQ", insertable=false, updatable=false)
	private long ordLnItmSeq;

	@Column(name="DISC_SEQ_NUM")
	private long discSeqNum;

	@Column(name="DC_DY_ORD", insertable=false, updatable=false)
	private String dcDyOrd;

	public ClaimTranDscItmPK() {
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
	public long getDiscSeqNum() {
		return this.discSeqNum;
	}
	public void setDiscSeqNum(long discSeqNum) {
		this.discSeqNum = discSeqNum;
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
		if (!(other instanceof ClaimTranDscItmPK)) {
			return false;
		}
		ClaimTranDscItmPK castOther = (ClaimTranDscItmPK)other;
		return 
			this.rtStrId.equals(castOther.rtStrId)
			&& this.ordWs.equals(castOther.ordWs)
			&& (this.trnSeq == castOther.trnSeq)
			&& (this.ordLnItmSeq == castOther.ordLnItmSeq)
			&& (this.discSeqNum == castOther.discSeqNum)
			&& this.dcDyOrd.equals(castOther.dcDyOrd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rtStrId.hashCode();
		hash = hash * prime + this.ordWs.hashCode();
		hash = hash * prime + ((int) (this.trnSeq ^ (this.trnSeq >>> 32)));
		hash = hash * prime + ((int) (this.ordLnItmSeq ^ (this.ordLnItmSeq >>> 32)));
		hash = hash * prime + ((int) (this.discSeqNum ^ (this.discSeqNum >>> 32)));
		hash = hash * prime + this.dcDyOrd.hashCode();
		
		return hash;
	}
}