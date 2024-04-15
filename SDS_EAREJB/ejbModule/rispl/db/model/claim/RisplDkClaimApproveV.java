package rispl.db.model.claim;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_CLAIM_APPROVE_V database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_CLAIM_APPROVE_V")
@NamedQuery(name="RisplDkClaimApproveV.findAll", query="SELECT r FROM RisplDkClaimApproveV r")
public class RisplDkClaimApproveV implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CLAIM_ID")
	private String claimId;

	@Column(name="DC_DY_ORD")
	private String dcDyOrd;

	@Column(name="EM_ID")
	private String emId;

	@Column(name="FL_TRG_ORD")
	private String flTrgOrd;

	@Column(name="ID_ORD")
	private String idOrd;

	@Column(name="ID_WMS_TLOG_BTCH")
	private String idWmsTlogBtch;

	@Column(name="ORD_WS")
	private String ordWs;

	@Column(name="RSN_DESC")
	private String rsnDesc;

	@Column(name="RT_STR_ID")
	private String rtStrId;

	@Column(name="SC_CLM")
	private BigDecimal scClm;

	@Column(name="SC_ORD")
	private BigDecimal scOrd;

	@Column(name="SC_TRAN")
	private BigDecimal scTran;

	@Column(name="TRN_SEQ")
	private BigDecimal trnSeq;

	@Id
	@Column(name="UNIQUE_NO")
	private BigDecimal uniqueNo;

	public RisplDkClaimApproveV() {
	}

	public String getClaimId() {
		return this.claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getDcDyOrd() {
		return this.dcDyOrd;
	}

	public void setDcDyOrd(String dcDyOrd) {
		this.dcDyOrd = dcDyOrd;
	}

	public String getEmId() {
		return this.emId;
	}

	public void setEmId(String emId) {
		this.emId = emId;
	}

	public String getFlTrgOrd() {
		return this.flTrgOrd;
	}

	public void setFlTrgOrd(String flTrgOrd) {
		this.flTrgOrd = flTrgOrd;
	}

	public String getIdOrd() {
		return this.idOrd;
	}

	public void setIdOrd(String idOrd) {
		this.idOrd = idOrd;
	}

	public String getIdWmsTlogBtch() {
		return this.idWmsTlogBtch;
	}

	public void setIdWmsTlogBtch(String idWmsTlogBtch) {
		this.idWmsTlogBtch = idWmsTlogBtch;
	}

	public String getOrdWs() {
		return this.ordWs;
	}

	public void setOrdWs(String ordWs) {
		this.ordWs = ordWs;
	}

	public String getRsnDesc() {
		return this.rsnDesc;
	}

	public void setRsnDesc(String rsnDesc) {
		this.rsnDesc = rsnDesc;
	}

	public String getRtStrId() {
		return this.rtStrId;
	}

	public void setRtStrId(String rtStrId) {
		this.rtStrId = rtStrId;
	}

	public BigDecimal getScClm() {
		return this.scClm;
	}

	public void setScClm(BigDecimal scClm) {
		this.scClm = scClm;
	}

	public BigDecimal getScOrd() {
		return this.scOrd;
	}

	public void setScOrd(BigDecimal scOrd) {
		this.scOrd = scOrd;
	}

	public BigDecimal getScTran() {
		return this.scTran;
	}

	public void setScTran(BigDecimal scTran) {
		this.scTran = scTran;
	}

	public BigDecimal getTrnSeq() {
		return this.trnSeq;
	}

	public void setTrnSeq(BigDecimal trnSeq) {
		this.trnSeq = trnSeq;
	}

	public BigDecimal getUniqueNo() {
		return this.uniqueNo;
	}

	public void setUniqueNo(BigDecimal uniqueNo) {
		this.uniqueNo = uniqueNo;
	}

}