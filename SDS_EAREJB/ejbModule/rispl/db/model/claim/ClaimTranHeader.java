package rispl.db.model.claim;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The persistent class for the CLAIM_TRAN_HEADER database table.
 * 
 */
@Entity
@Table(name = "CLAIM_TRAN_HEADER")
@NamedQueries(value = { @NamedQuery(name = "ClaimTranHeader.findAll", query = "SELECT c FROM ClaimTranHeader c"),
		@NamedQuery(name = "ClaimTranHeader.findClaimsToApprove", query = "SELECT c FROM ClaimTranHeader c WHERE c.scOrd = :orderStatus ORDER BY c.id.dcDyOrd DESC") })

public class ClaimTranHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClaimTranHeaderPK id;

	@Column(name = "CLAIM_ID")
	private String claimId;

	@Lob
	@Column(name = "CT_DVR_INF")
	private String ctDvrInf;

	@Column(name = "CT_DVR_INFO_INS")
	private String ctDvrInfoIns;

	@Column(name = "EM_ID")
	private String emId;

	@Column(name = "FL_KY_OFL")
	private String flKyOfl;

	@Column(name = "FL_SLS_ASSC_MDF")
	private String flSlsAsscMdf;

	@Column(name = "FL_TRE_ORD")
	private String flTreOrd;

	@Column(name = "FL_TRG_ORD")
	private String flTrgOrd;

	@Column(name = "ID_BTCH_ARCH")
	private String idBtchArch;

	@Column(name = "ID_BTCH_INV_RESV")
	private String idBtchInvResv;

	@Column(name = "ID_CNY_ICD")
	private BigDecimal idCnyIcd;

	@Column(name = "ID_OPR")
	private String idOpr;

	@Column(name = "ID_RPSTY_TND")
	private String idRpstyTnd;

	@Column(name = "ID_TLOG_BTCH")
	private BigDecimal idTlogBtch;

	@Column(name = "ID_TRLOG_BTCH")
	private String idTrlogBtch;

	@Column(name = "ID_WMS_TLOG_BTCH")
	private String idWmsTlogBtch;

	@Column(name = "ORD_TY")
	private String ordTy;

	@Column(name = "RC_RTN_MR")
	private String rcRtnMr;

	@Column(name = "SC_ORD")
	private BigDecimal scOrd;

	@Column(name = "SC_PST_PRCS")
	private BigDecimal scPstPrcs;

	@Column(name = "SC_TRAN")
	private BigDecimal scTran;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_CRT_RCRD")
	private Date tsCrtRcrd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_MDF_RCRD")
	private Date tsMdfRcrd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_ORD_BGN")
	private Date tsOrdBgn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_ORD_END")
	private Date tsOrdEnd;

	@Column(name = "CLM_REJECT_NOTES")
	private String claimRejectNotes;

	@Column(name = "WH_RECEIVED_DATE")
	private Date whReceivedDate;

	@Column(name = "FL_INV_CNCL")
	private String flInvCncl;

	@Column(name = "ACCEPT_TYPE")
	private String acceptType;
	// to hold returnReason Codes
	@Transient
	private Map<String, String> returnReasonCodes;

	// bi-directional many-to-one association to ClaimTranLineItem
	@OneToMany(mappedBy = "claimTranHeader", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ClaimTranLineItem> claimTranLineItems;

	// bi-directional many-to-one association to ClaimTranSum
	@OneToOne(mappedBy = "claimTranHeader", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ClaimTranSum claimTranSum;

	public ClaimTranHeader() {
	}

	public ClaimTranHeaderPK getId() {
		return this.id;
	}

	public void setId(ClaimTranHeaderPK id) {
		this.id = id;
	}

	public String getClaimId() {
		return this.claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getCtDvrInf() {
		return this.ctDvrInf;
	}

	public void setCtDvrInf(String ctDvrInf) {
		this.ctDvrInf = ctDvrInf;
	}

	public String getCtDvrInfoIns() {
		return this.ctDvrInfoIns;
	}

	public void setCtDvrInfoIns(String ctDvrInfoIns) {
		this.ctDvrInfoIns = ctDvrInfoIns;
	}

	public String getEmId() {
		return this.emId;
	}

	public void setEmId(String emId) {
		this.emId = emId;
	}

	public String getFlKyOfl() {
		return this.flKyOfl;
	}

	public void setFlKyOfl(String flKyOfl) {
		this.flKyOfl = flKyOfl;
	}

	public String getFlSlsAsscMdf() {
		return this.flSlsAsscMdf;
	}

	public void setFlSlsAsscMdf(String flSlsAsscMdf) {
		this.flSlsAsscMdf = flSlsAsscMdf;
	}

	public String getFlTreOrd() {
		return this.flTreOrd;
	}

	public void setFlTreOrd(String flTreOrd) {
		this.flTreOrd = flTreOrd;
	}

	public String getFlTrgOrd() {
		return this.flTrgOrd;
	}

	public void setFlTrgOrd(String flTrgOrd) {
		this.flTrgOrd = flTrgOrd;
	}

	public String getIdBtchArch() {
		return this.idBtchArch;
	}

	public void setIdBtchArch(String idBtchArch) {
		this.idBtchArch = idBtchArch;
	}

	public String getIdBtchInvResv() {
		return this.idBtchInvResv;
	}

	public void setIdBtchInvResv(String idBtchInvResv) {
		this.idBtchInvResv = idBtchInvResv;
	}

	public BigDecimal getIdCnyIcd() {
		return this.idCnyIcd;
	}

	public void setIdCnyIcd(BigDecimal idCnyIcd) {
		this.idCnyIcd = idCnyIcd;
	}

	public String getIdOpr() {
		return this.idOpr;
	}

	public void setIdOpr(String idOpr) {
		this.idOpr = idOpr;
	}

	public String getIdRpstyTnd() {
		return this.idRpstyTnd;
	}

	public void setIdRpstyTnd(String idRpstyTnd) {
		this.idRpstyTnd = idRpstyTnd;
	}

	public BigDecimal getIdTlogBtch() {
		return this.idTlogBtch;
	}

	public void setIdTlogBtch(BigDecimal idTlogBtch) {
		this.idTlogBtch = idTlogBtch;
	}

	public String getIdTrlogBtch() {
		return this.idTrlogBtch;
	}

	public void setIdTrlogBtch(String idTrlogBtch) {
		this.idTrlogBtch = idTrlogBtch;
	}

	public String getIdWmsTlogBtch() {
		return this.idWmsTlogBtch;
	}

	public void setIdWmsTlogBtch(String idWmsTlogBtch) {
		this.idWmsTlogBtch = idWmsTlogBtch;
	}

	public String getOrdTy() {
		return this.ordTy;
	}

	public void setOrdTy(String ordTy) {
		this.ordTy = ordTy;
	}

	public String getRcRtnMr() {
		return this.rcRtnMr;
	}

	public void setRcRtnMr(String rcRtnMr) {
		this.rcRtnMr = rcRtnMr;
	}

	public BigDecimal getScOrd() {
		return this.scOrd;
	}

	public void setScOrd(BigDecimal scOrd) {
		this.scOrd = scOrd;
	}

	public BigDecimal getScPstPrcs() {
		return this.scPstPrcs;
	}

	public void setScPstPrcs(BigDecimal scPstPrcs) {
		this.scPstPrcs = scPstPrcs;
	}

	public BigDecimal getScTran() {
		return this.scTran;
	}

	public void setScTran(BigDecimal scTran) {
		this.scTran = scTran;
	}

	public Date getTsCrtRcrd() {
		return this.tsCrtRcrd;
	}

	public void setTsCrtRcrd(Date tsCrtRcrd) {
		this.tsCrtRcrd = tsCrtRcrd;
	}

	public Date getTsMdfRcrd() {
		return this.tsMdfRcrd;
	}

	public void setTsMdfRcrd(Date tsMdfRcrd) {
		this.tsMdfRcrd = tsMdfRcrd;
	}

	public Date getTsOrdBgn() {
		return this.tsOrdBgn;
	}

	public void setTsOrdBgn(Date tsOrdBgn) {
		this.tsOrdBgn = tsOrdBgn;
	}

	public Date getTsOrdEnd() {
		return this.tsOrdEnd;
	}

	public void setTsOrdEnd(Date tsOrdEnd) {
		this.tsOrdEnd = tsOrdEnd;
	}

	public List<ClaimTranLineItem> getClaimTranLineItems() {
		return this.claimTranLineItems;
	}

	public void setClaimTranLineItems(List<ClaimTranLineItem> claimTranLineItems) {
		this.claimTranLineItems = claimTranLineItems;
	}

	public ClaimTranLineItem addClaimTranLineItem(ClaimTranLineItem claimTranLineItem) {
		getClaimTranLineItems().add(claimTranLineItem);
		claimTranLineItem.setClaimTranHeader(this);

		return claimTranLineItem;
	}

	public ClaimTranLineItem removeClaimTranLineItem(ClaimTranLineItem claimTranLineItem) {
		getClaimTranLineItems().remove(claimTranLineItem);
		claimTranLineItem.setClaimTranHeader(null);

		return claimTranLineItem;
	}

	public ClaimTranSum getClaimTranSum() {
		return this.claimTranSum;
	}

	public void setClaimTranSum(ClaimTranSum claimTranSum) {
		this.claimTranSum = claimTranSum;
	}

	public Map<String, String> getReturnReasonCodes() {
		return returnReasonCodes;
	}

	public void setReturnReasonCodes(Map<String, String> returnReasonCodes) {
		this.returnReasonCodes = returnReasonCodes;
	}

	public String getClaimRejectNotes() {
		return claimRejectNotes;
	}

	public void setClaimRejectNotes(String claimRejectNotes) {
		this.claimRejectNotes = claimRejectNotes;
	}

	public Date getWhReceivedDate() {
		return whReceivedDate;
	}

	public void setWhReceivedDate(Date whReceivedDate) {
		this.whReceivedDate = whReceivedDate;
	}

	public String getFlInvCncl() {
		return flInvCncl;
	}

	public void setFlInvCncl(String flInvCncl) {
		this.flInvCncl = flInvCncl;
	}

	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}
	

}