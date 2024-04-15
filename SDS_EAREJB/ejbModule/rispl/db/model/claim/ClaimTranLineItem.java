package rispl.db.model.claim;

import java.io.Serializable;
import javax.persistence.*;

import rispl.dkart.services.entities.transaction.DkartReasonCodes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the CLAIM_TRAN_LINE_ITEM database table.
 * 
 */
@Entity
@Table(name = "CLAIM_TRAN_LINE_ITEM")
@NamedQuery(name = "ClaimTranLineItem.findAll", query = "SELECT c FROM ClaimTranLineItem c")
public class ClaimTranLineItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClaimTranLineItemPK id;

	@Column(name = "AI_LN_ITM_ORG")
	private BigDecimal aiLnItmOrg;

	@Column(name = "AI_LN_ITM_RLTD")
	private BigDecimal aiLnItmRltd;

	@Column(name = "ACCP_CLAIM_PRICE")
	private BigDecimal accptClaimPrice;

	@Column(name = "APPR_CLAIM_PRICE")
	private BigDecimal apprClaimPrice;

	@Column(name = "APPR_CLAIM_QTY")
	private BigDecimal apprClaimQty;

	@Column(name = "DC_DY_BSN_ORG")
	private String dcDyBsnOrg;

	@Column(name = "DE_ITM_LCL")
	private String deItmLcl;

	@Column(name = "DE_ITM_SHRT_RCPT")
	private String deItmShrtRcpt;

	@Column(name = "DK_DPT_ID")
	private String dkDptId;

	@Column(name = "DK_ITM_ID")
	private String dkItmId;

	@Column(name = "ED_SZ")
	private String edSz;

	@Column(name = "EXTN_DSC_LN_ITM")
	private BigDecimal extnDscLnItm;

	@Column(name = "EXTN_LN_ITM_RTN")
	private BigDecimal extnLnItmRtn;

	@Column(name = "FE_RSTK")
	private BigDecimal feRstk;

	@Column(name = "FL_CLRNC")
	private String flClrnc;

	@Column(name = "FL_DSC_EM_ALW")
	private String flDscEmAlw;

	@Column(name = "FL_FE_RSTK")
	private String flFeRstk;

	@Column(name = "FL_ITM_DSC")
	private String flItmDsc;

	@Column(name = "FL_ITM_DSC_DMG")
	private String flItmDscDmg;

	@Column(name = "FL_ITM_PRC_ADJ")
	private String flItmPrcAdj;

	@Column(name = "FL_ITM_PRC_OVRD")
	private String flItmPrcOvrd;

	@Column(name = "FL_ITM_RTN_QTY")
	private String flItmRtnQty;

	@Column(name = "FL_ITM_SZ_REQ")
	private String flItmSzReq;

	@Column(name = "FL_MDFR_PRC")
	private String flMdfrPrc;

	@Column(name = "FL_RFD_SV")
	private String flRfdSv;

	@Column(name = "FL_RLTD_ITM_RM")
	private String flRltdItmRm;

	@Column(name = "FL_RLTD_ITM_RTN")
	private String flRltdItmRtn;

	@Column(name = "FL_RTN_MR")
	private String flRtnMr;

	@Column(name = "FL_RTN_PRH")
	private String flRtnPrh;

	@Column(name = "FL_RTRVD_TRN")
	private String flRtrvdTrn;

	@Column(name = "FL_SHP_CHG")
	private String flShpChg;

	@Column(name = "FL_SLS_ASSC_MDF")
	private String flSlsAsscMdf;

	@Column(name = "FL_TX")
	private String flTx;

	@Column(name = "FL_VD_LN_ITM")
	private String flVdLnItm;

	@Column(name = "FL_VLD_SRZ_ITM")
	private String flVldSrzItm;

	@Column(name = "FL_VLD_SRZ_ITM_EXT")
	private String flVldSrzItmExt;

	@Column(name = "ID_CLN")
	private String idCln;

	@Column(name = "ID_ITM_MF_UPC")
	private String idItmMfUpc;

	@Column(name = "ID_MRHRC_GP")
	private String idMrhrcGp;

	@Column(name = "ID_NMB_SRZ")
	private String idNmbSrz;

	@Column(name = "ID_NON_RTVD_ORG_RCPT")
	private String idNonRtvdOrgRcpt;

	@Column(name = "ID_STR_RT_ORG")
	private String idStrRtOrg;

	@Column(name = "IDN_SLS_AG_RST")
	private BigDecimal idnSlsAgRst;

	@Column(name = "ITEM_ID")
	private String itemId;

	@Column(name = "ITM_PRN_PRC")
	private BigDecimal itmPrnPrc;

	@Column(name = "ITM_TY")
	private BigDecimal itmTy;

	@Column(name = "LINE_QNT")
	private BigDecimal lineQnt;

	@Column(name = "LINE_QNT_RTN")
	private BigDecimal lineQntRtn;

	@Column(name = "LU_ENTR_RT_PRC")
	private String luEntrRtPrc;

	@Column(name = "LU_KT_HDR_RFN_ID")
	private BigDecimal luKtHdrRfnId;

	@Column(name = "LU_KT_ST")
	private String luKtSt;

	@Column(name = "LU_MTH_ID_ENR")
	private String luMthIdEnr;

	@Column(name = "LU_PRC_ADJ_RFN_ID")
	private BigDecimal luPrcAdjRfnId;

	@Column(name = "LU_PRC_RT_DRVN")
	private String luPrcRtDrvn;

	@Column(name = "MR_LV_HRC")
	private String mrLvHrc;

	@Column(name = "OR_ID")
	private BigDecimal orId;

	@Column(name = "ORD_ID_TRN_ORG")
	private String ordIdTrnOrg;

	@Column(name = "ORD_LN_ITM_STS")
	private BigDecimal ordLnItmSts;

	@Column(name = "OVRD_PRC")
	private BigDecimal ovrdPrc;

	@Column(name = "RC_ITM_CND_RTN_MR")
	private String rcItmCndRtnMr;

	@Column(name = "RC_PRC_OVRR")
	private String rcPrcOvrr;

	@Column(name = "RC_RFD_SV")
	private String rcRfdSv;

	@Column(name = "RC_RTN_MR")
	private String rcRtnMr;

	@Column(name = "REGISTRY_ID")
	private String registryId;

	@Column(name = "TAX_INC_LN_ITM_RTN")
	private BigDecimal taxIncLnItmRtn;

	@Column(name = "TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name = "TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	@Column(name = "TX_GP_ID")
	private BigDecimal txGpId;

	@Column(name = "UOM_SLS")
	private String uomSls;

	@Column(name = "VAT_LN_ITM_RTN")
	private BigDecimal vatLnItmRtn;

	@Column(name = "WH_RECEIVE_QTY")
	private BigDecimal whReceiveQty;

	// bi-directional many-to-one association to ClaimTranDscItm
	@OneToMany(mappedBy = "claimTranLineItem", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ClaimTranDscItm> claimTranDscItms;

	// bi-directional many-to-one association to ClaimTranHeader
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "DC_DY_ORD", referencedColumnName = "DC_DY_ORD"),
			@JoinColumn(name = "ORD_WS", referencedColumnName = "ORD_WS"),
			@JoinColumn(name = "RT_STR_ID", referencedColumnName = "RT_STR_ID"),
			@JoinColumn(name = "TRN_SEQ", referencedColumnName = "TRN_SEQ") })
	private ClaimTranHeader claimTranHeader;

	@OneToOne
	@JoinColumn(name = "RC_RTN_MR", referencedColumnName = "RSN_CODE", insertable = false, updatable = false)
	private DkartReasonCodes reasonCode;

	@Column(name = "WH_DISPSTN_CODE")
	private String dispostionCode;

	public String getDispostionCode() {
		return dispostionCode;
	}

	public void setDispostionCode(String dispostionCode) {
		this.dispostionCode = dispostionCode;
	}

	public DkartReasonCodes getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(DkartReasonCodes reasonCode) {
		this.reasonCode = reasonCode;
	}

	public ClaimTranLineItem() {
	}

	public ClaimTranLineItemPK getId() {
		return this.id;
	}

	public void setId(ClaimTranLineItemPK id) {
		this.id = id;
	}

	public BigDecimal getAiLnItmOrg() {
		return this.aiLnItmOrg;
	}

	public void setAiLnItmOrg(BigDecimal aiLnItmOrg) {
		this.aiLnItmOrg = aiLnItmOrg;
	}

	public BigDecimal getAiLnItmRltd() {
		return this.aiLnItmRltd;
	}

	public void setAiLnItmRltd(BigDecimal aiLnItmRltd) {
		this.aiLnItmRltd = aiLnItmRltd;
	}

	public BigDecimal getApprClaimPrice() {
		return this.apprClaimPrice;
	}

	public void setApprClaimPrice(BigDecimal apprClaimPrice) {
		this.apprClaimPrice = apprClaimPrice;
	}

	public BigDecimal getApprClaimQty() {
		return this.apprClaimQty;
	}

	public void setApprClaimQty(BigDecimal apprClaimQty) {
		this.apprClaimQty = apprClaimQty;
	}

	public String getDcDyBsnOrg() {
		return this.dcDyBsnOrg;
	}

	public void setDcDyBsnOrg(String dcDyBsnOrg) {
		this.dcDyBsnOrg = dcDyBsnOrg;
	}

	public String getDeItmLcl() {
		return this.deItmLcl;
	}

	public void setDeItmLcl(String deItmLcl) {
		this.deItmLcl = deItmLcl;
	}

	public String getDeItmShrtRcpt() {
		return this.deItmShrtRcpt;
	}

	public void setDeItmShrtRcpt(String deItmShrtRcpt) {
		this.deItmShrtRcpt = deItmShrtRcpt;
	}

	public String getDkDptId() {
		return this.dkDptId;
	}

	public void setDkDptId(String dkDptId) {
		this.dkDptId = dkDptId;
	}

	public String getDkItmId() {
		return this.dkItmId;
	}

	public void setDkItmId(String dkItmId) {
		this.dkItmId = dkItmId;
	}

	public String getEdSz() {
		return this.edSz;
	}

	public void setEdSz(String edSz) {
		this.edSz = edSz;
	}

	public BigDecimal getExtnDscLnItm() {
		return this.extnDscLnItm;
	}

	public void setExtnDscLnItm(BigDecimal extnDscLnItm) {
		this.extnDscLnItm = extnDscLnItm;
	}

	public BigDecimal getExtnLnItmRtn() {
		return this.extnLnItmRtn;
	}

	public void setExtnLnItmRtn(BigDecimal extnLnItmRtn) {
		this.extnLnItmRtn = extnLnItmRtn;
	}

	public BigDecimal getFeRstk() {
		return this.feRstk;
	}

	public void setFeRstk(BigDecimal feRstk) {
		this.feRstk = feRstk;
	}

	public String getFlClrnc() {
		return this.flClrnc;
	}

	public void setFlClrnc(String flClrnc) {
		this.flClrnc = flClrnc;
	}

	public String getFlDscEmAlw() {
		return this.flDscEmAlw;
	}

	public void setFlDscEmAlw(String flDscEmAlw) {
		this.flDscEmAlw = flDscEmAlw;
	}

	public String getFlFeRstk() {
		return this.flFeRstk;
	}

	public void setFlFeRstk(String flFeRstk) {
		this.flFeRstk = flFeRstk;
	}

	public String getFlItmDsc() {
		return this.flItmDsc;
	}

	public void setFlItmDsc(String flItmDsc) {
		this.flItmDsc = flItmDsc;
	}

	public String getFlItmDscDmg() {
		return this.flItmDscDmg;
	}

	public void setFlItmDscDmg(String flItmDscDmg) {
		this.flItmDscDmg = flItmDscDmg;
	}

	public String getFlItmPrcAdj() {
		return this.flItmPrcAdj;
	}

	public void setFlItmPrcAdj(String flItmPrcAdj) {
		this.flItmPrcAdj = flItmPrcAdj;
	}

	public String getFlItmPrcOvrd() {
		return this.flItmPrcOvrd;
	}

	public void setFlItmPrcOvrd(String flItmPrcOvrd) {
		this.flItmPrcOvrd = flItmPrcOvrd;
	}

	public String getFlItmRtnQty() {
		return this.flItmRtnQty;
	}

	public void setFlItmRtnQty(String flItmRtnQty) {
		this.flItmRtnQty = flItmRtnQty;
	}

	public String getFlItmSzReq() {
		return this.flItmSzReq;
	}

	public void setFlItmSzReq(String flItmSzReq) {
		this.flItmSzReq = flItmSzReq;
	}

	public String getFlMdfrPrc() {
		return this.flMdfrPrc;
	}

	public void setFlMdfrPrc(String flMdfrPrc) {
		this.flMdfrPrc = flMdfrPrc;
	}

	public String getFlRfdSv() {
		return this.flRfdSv;
	}

	public void setFlRfdSv(String flRfdSv) {
		this.flRfdSv = flRfdSv;
	}

	public String getFlRltdItmRm() {
		return this.flRltdItmRm;
	}

	public void setFlRltdItmRm(String flRltdItmRm) {
		this.flRltdItmRm = flRltdItmRm;
	}

	public String getFlRltdItmRtn() {
		return this.flRltdItmRtn;
	}

	public void setFlRltdItmRtn(String flRltdItmRtn) {
		this.flRltdItmRtn = flRltdItmRtn;
	}

	public String getFlRtnMr() {
		return this.flRtnMr;
	}

	public void setFlRtnMr(String flRtnMr) {
		this.flRtnMr = flRtnMr;
	}

	public String getFlRtnPrh() {
		return this.flRtnPrh;
	}

	public void setFlRtnPrh(String flRtnPrh) {
		this.flRtnPrh = flRtnPrh;
	}

	public String getFlRtrvdTrn() {
		return this.flRtrvdTrn;
	}

	public void setFlRtrvdTrn(String flRtrvdTrn) {
		this.flRtrvdTrn = flRtrvdTrn;
	}

	public String getFlShpChg() {
		return this.flShpChg;
	}

	public void setFlShpChg(String flShpChg) {
		this.flShpChg = flShpChg;
	}

	public String getFlSlsAsscMdf() {
		return this.flSlsAsscMdf;
	}

	public void setFlSlsAsscMdf(String flSlsAsscMdf) {
		this.flSlsAsscMdf = flSlsAsscMdf;
	}

	public String getFlTx() {
		return this.flTx;
	}

	public void setFlTx(String flTx) {
		this.flTx = flTx;
	}

	public String getFlVdLnItm() {
		return this.flVdLnItm;
	}

	public void setFlVdLnItm(String flVdLnItm) {
		this.flVdLnItm = flVdLnItm;
	}

	public String getFlVldSrzItm() {
		return this.flVldSrzItm;
	}

	public void setFlVldSrzItm(String flVldSrzItm) {
		this.flVldSrzItm = flVldSrzItm;
	}

	public String getFlVldSrzItmExt() {
		return this.flVldSrzItmExt;
	}

	public void setFlVldSrzItmExt(String flVldSrzItmExt) {
		this.flVldSrzItmExt = flVldSrzItmExt;
	}

	public String getIdCln() {
		return this.idCln;
	}

	public void setIdCln(String idCln) {
		this.idCln = idCln;
	}

	public String getIdItmMfUpc() {
		return this.idItmMfUpc;
	}

	public void setIdItmMfUpc(String idItmMfUpc) {
		this.idItmMfUpc = idItmMfUpc;
	}

	public String getIdMrhrcGp() {
		return this.idMrhrcGp;
	}

	public void setIdMrhrcGp(String idMrhrcGp) {
		this.idMrhrcGp = idMrhrcGp;
	}

	public String getIdNmbSrz() {
		return this.idNmbSrz;
	}

	public void setIdNmbSrz(String idNmbSrz) {
		this.idNmbSrz = idNmbSrz;
	}

	public String getIdNonRtvdOrgRcpt() {
		return this.idNonRtvdOrgRcpt;
	}

	public void setIdNonRtvdOrgRcpt(String idNonRtvdOrgRcpt) {
		this.idNonRtvdOrgRcpt = idNonRtvdOrgRcpt;
	}

	public String getIdStrRtOrg() {
		return this.idStrRtOrg;
	}

	public void setIdStrRtOrg(String idStrRtOrg) {
		this.idStrRtOrg = idStrRtOrg;
	}

	public BigDecimal getIdnSlsAgRst() {
		return this.idnSlsAgRst;
	}

	public void setIdnSlsAgRst(BigDecimal idnSlsAgRst) {
		this.idnSlsAgRst = idnSlsAgRst;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getItmPrnPrc() {
		return this.itmPrnPrc;
	}

	public void setItmPrnPrc(BigDecimal itmPrnPrc) {
		this.itmPrnPrc = itmPrnPrc;
	}

	public BigDecimal getItmTy() {
		return this.itmTy;
	}

	public void setItmTy(BigDecimal itmTy) {
		this.itmTy = itmTy;
	}

	public BigDecimal getLineQnt() {
		return this.lineQnt;
	}

	public void setLineQnt(BigDecimal lineQnt) {
		this.lineQnt = lineQnt;
	}

	public BigDecimal getLineQntRtn() {
		return this.lineQntRtn;
	}

	public void setLineQntRtn(BigDecimal lineQntRtn) {
		this.lineQntRtn = lineQntRtn;
	}

	public String getLuEntrRtPrc() {
		return this.luEntrRtPrc;
	}

	public void setLuEntrRtPrc(String luEntrRtPrc) {
		this.luEntrRtPrc = luEntrRtPrc;
	}

	public BigDecimal getLuKtHdrRfnId() {
		return this.luKtHdrRfnId;
	}

	public void setLuKtHdrRfnId(BigDecimal luKtHdrRfnId) {
		this.luKtHdrRfnId = luKtHdrRfnId;
	}

	public String getLuKtSt() {
		return this.luKtSt;
	}

	public void setLuKtSt(String luKtSt) {
		this.luKtSt = luKtSt;
	}

	public String getLuMthIdEnr() {
		return this.luMthIdEnr;
	}

	public void setLuMthIdEnr(String luMthIdEnr) {
		this.luMthIdEnr = luMthIdEnr;
	}

	public BigDecimal getLuPrcAdjRfnId() {
		return this.luPrcAdjRfnId;
	}

	public void setLuPrcAdjRfnId(BigDecimal luPrcAdjRfnId) {
		this.luPrcAdjRfnId = luPrcAdjRfnId;
	}

	public String getLuPrcRtDrvn() {
		return this.luPrcRtDrvn;
	}

	public void setLuPrcRtDrvn(String luPrcRtDrvn) {
		this.luPrcRtDrvn = luPrcRtDrvn;
	}

	public String getMrLvHrc() {
		return this.mrLvHrc;
	}

	public void setMrLvHrc(String mrLvHrc) {
		this.mrLvHrc = mrLvHrc;
	}

	public BigDecimal getOrId() {
		return this.orId;
	}

	public void setOrId(BigDecimal orId) {
		this.orId = orId;
	}

	public String getOrdIdTrnOrg() {
		return this.ordIdTrnOrg;
	}

	public void setOrdIdTrnOrg(String ordIdTrnOrg) {
		this.ordIdTrnOrg = ordIdTrnOrg;
	}

	public BigDecimal getOrdLnItmSts() {
		return this.ordLnItmSts;
	}

	public void setOrdLnItmSts(BigDecimal ordLnItmSts) {
		this.ordLnItmSts = ordLnItmSts;
	}

	public BigDecimal getOvrdPrc() {
		return this.ovrdPrc;
	}

	public void setOvrdPrc(BigDecimal ovrdPrc) {
		this.ovrdPrc = ovrdPrc;
	}

	public String getRcItmCndRtnMr() {
		return this.rcItmCndRtnMr;
	}

	public void setRcItmCndRtnMr(String rcItmCndRtnMr) {
		this.rcItmCndRtnMr = rcItmCndRtnMr;
	}

	public String getRcPrcOvrr() {
		return this.rcPrcOvrr;
	}

	public void setRcPrcOvrr(String rcPrcOvrr) {
		this.rcPrcOvrr = rcPrcOvrr;
	}

	public String getRcRfdSv() {
		return this.rcRfdSv;
	}

	public void setRcRfdSv(String rcRfdSv) {
		this.rcRfdSv = rcRfdSv;
	}

	public String getRcRtnMr() {
		return this.rcRtnMr;
	}

	public void setRcRtnMr(String rcRtnMr) {
		this.rcRtnMr = rcRtnMr;
	}

	public String getRegistryId() {
		return this.registryId;
	}

	public void setRegistryId(String registryId) {
		this.registryId = registryId;
	}

	public BigDecimal getTaxIncLnItmRtn() {
		return this.taxIncLnItmRtn;
	}

	public void setTaxIncLnItmRtn(BigDecimal taxIncLnItmRtn) {
		this.taxIncLnItmRtn = taxIncLnItmRtn;
	}

	public Timestamp getTsCrtRcrd() {
		return this.tsCrtRcrd;
	}

	public void setTsCrtRcrd(Timestamp tsCrtRcrd) {
		this.tsCrtRcrd = tsCrtRcrd;
	}

	public Timestamp getTsMdfRcrd() {
		return this.tsMdfRcrd;
	}

	public void setTsMdfRcrd(Timestamp tsMdfRcrd) {
		this.tsMdfRcrd = tsMdfRcrd;
	}

	public BigDecimal getTxGpId() {
		return this.txGpId;
	}

	public void setTxGpId(BigDecimal txGpId) {
		this.txGpId = txGpId;
	}

	public String getUomSls() {
		return this.uomSls;
	}

	public void setUomSls(String uomSls) {
		this.uomSls = uomSls;
	}

	public BigDecimal getVatLnItmRtn() {
		return this.vatLnItmRtn;
	}

	public void setVatLnItmRtn(BigDecimal vatLnItmRtn) {
		this.vatLnItmRtn = vatLnItmRtn;
	}

	public List<ClaimTranDscItm> getClaimTranDscItms() {
		return this.claimTranDscItms;
	}

	public void setClaimTranDscItms(List<ClaimTranDscItm> claimTranDscItms) {
		this.claimTranDscItms = claimTranDscItms;
	}

	public ClaimTranDscItm addClaimTranDscItm(ClaimTranDscItm claimTranDscItm) {
		getClaimTranDscItms().add(claimTranDscItm);
		claimTranDscItm.setClaimTranLineItem(this);

		return claimTranDscItm;
	}

	public ClaimTranDscItm removeClaimTranDscItm(ClaimTranDscItm claimTranDscItm) {
		getClaimTranDscItms().remove(claimTranDscItm);
		claimTranDscItm.setClaimTranLineItem(null);

		return claimTranDscItm;
	}

	public ClaimTranHeader getClaimTranHeader() {
		return this.claimTranHeader;
	}

	public void setClaimTranHeader(ClaimTranHeader claimTranHeader) {
		this.claimTranHeader = claimTranHeader;
	}

	public BigDecimal getWhReceiveQty() {
		return whReceiveQty;
	}

	public void setWhReceiveQty(BigDecimal whReceiveQty) {
		this.whReceiveQty = whReceiveQty;
	}

	public BigDecimal getAccptClaimPrice() {
		return accptClaimPrice;
	}

	public void setAccptClaimPrice(BigDecimal accptClaimPrice) {
		this.accptClaimPrice = accptClaimPrice;
	}

}