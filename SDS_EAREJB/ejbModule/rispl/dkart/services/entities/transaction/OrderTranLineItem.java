package rispl.dkart.services.entities.transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import rispl.dk.itemLookUp.PLUItemIfc;

/**
 * The persistent class for the ORD_TRAN_LINE_ITEM database table.
 * 
 */
@Entity
@Table(name = "ORD_TRAN_LINE_ITEM")
@NamedQuery(name = "OrderTranLineItem.findAll", query = "SELECT o FROM OrderTranLineItem o")
public class OrderTranLineItem implements Serializable {
	public OrderTranLineItem clone() {
		OrderTranLineItem cloned = new OrderTranLineItem();
		cloned.id = this.id;
		cloned.aiLnItmOrg = this.aiLnItmOrg;
		cloned.aiLnItmRltd = this.aiLnItmRltd;
		cloned.dcDyBsnOrg = this.dcDyBsnOrg;
		cloned.deItmLcl = this.deItmLcl;
		cloned.deItmShrtRcpt = this.deItmShrtRcpt;
		cloned.dkDptId = this.dkDptId;
		cloned.dkItmId = this.dkItmId;
		cloned.edSz = this.edSz;
		cloned.extnDscLnItm = this.extnDscLnItm;
		cloned.extnLnItmRtn = this.extnLnItmRtn;
		cloned.feRstk = this.feRstk;
		cloned.flClrnc = this.flClrnc;
		cloned.flDscEmAlw = this.flDscEmAlw;
		cloned.flFeRstk = this.flFeRstk;
		cloned.flItmDsc = this.flItmDsc;
		cloned.flItmDscDmg = this.flItmDscDmg;
		cloned.flItmPrcAdj = this.flItmPrcAdj;
		cloned.flItmSzReq = this.flItmSzReq;
		cloned.flMdfrPrc = this.flMdfrPrc;
		cloned.flRfdSv = this.flRfdSv;
		cloned.flRltdItmRm = this.flRltdItmRm;
		cloned.flRltdItmRtn = this.flRltdItmRtn;
		cloned.flRtnMr = this.flRtnMr;
		cloned.flRtnPrh = this.flRtnPrh;
		cloned.flRtrvdTrn = this.flRtrvdTrn;
		cloned.flShpChg = this.flShpChg;
		cloned.flSlsAsscMdf = this.flSlsAsscMdf;
		cloned.flTx = this.flTx;
		cloned.flVdLnItm = this.flVdLnItm;
		cloned.flVldSrzItm = this.flVldSrzItm;
		cloned.flVldSrzItmExt = this.flVldSrzItmExt;
		cloned.idCln = this.idCln;
		cloned.idItmMfUpc = this.idItmMfUpc;
		cloned.idMrhrcGp = this.idMrhrcGp;
		cloned.idNmbSrz = this.idNmbSrz;
		cloned.idNonRtvdOrgRcpt = this.idNonRtvdOrgRcpt;
		cloned.idStrRtOrg = this.idStrRtOrg;
		cloned.idnSlsAgRst = this.idnSlsAgRst;
		cloned.itemId = this.itemId;
		cloned.itmPrnPrc = this.itmPrnPrc;
		cloned.itmTy = this.itmTy;
		cloned.lineQnt = this.lineQnt;
		cloned.lineQntRtn = this.lineQntRtn;
		cloned.luEntrRtPrc = this.luEntrRtPrc;
		cloned.luKtHdrRfnId = this.luKtHdrRfnId;
		cloned.luKtSt = this.luKtSt;
		cloned.luMthIdEnr = this.luMthIdEnr;
		cloned.luPrcAdjRfnId = this.luPrcAdjRfnId;
		cloned.luPrcRtDrvn = this.luPrcRtDrvn;
		cloned.mrLvHrc = this.mrLvHrc;
		cloned.orId = this.orId;
		cloned.ordIdTrnOrg = this.ordIdTrnOrg;
		cloned.ordLnItmSts = this.ordLnItmSts;
		cloned.rcItmCndRtnMr = this.rcItmCndRtnMr;
		cloned.rcRfdSv = this.rcRfdSv;
		cloned.rcRtnMr = this.rcRtnMr;
		cloned.registryId = this.registryId;
		cloned.taxIncLnItmRtn = this.taxIncLnItmRtn;
		cloned.tsCrtRcrd = this.tsCrtRcrd;
		cloned.tsMdfRcrd = this.tsMdfRcrd;
		cloned.txGpId = this.txGpId;
		cloned.uomSls = this.uomSls;
		cloned.vatLnItmRtn = this.vatLnItmRtn;
		cloned.dispostionCode = this.dispostionCode;
		cloned.priceOverRideFlag = this.priceOverRideFlag;
		cloned.priceOvrrRsnCode = this.priceOvrrRsnCode;
		cloned.overRidePrice = this.overRidePrice;
		cloned.returnQtyFlag=this.returnQtyFlag;
		
		cloned.rprice = this.rprice;

		List<OrderTranDiscountItem> discountLines = new ArrayList<OrderTranDiscountItem>();
		if (this.ordTranDscItms != null) {
			for (OrderTranDiscountItem disc : ordTranDscItms) {
				OrderTranDiscountItem disItm = new OrderTranDiscountItem();
				disItm.setDscAmt(disc.getDscAmt());
				disItm.setDscPer(disc.getDscPer());
				disItm.setId(disc.getId());
				disItm.setOrdTranLineItem(cloned);
				disItm.setPrmCmpDtlid(disc.getPrmCmpDtlid());
				disItm.setPrmCmpId(disc.getPrmCmpId());
				disItm.setPrmDesc(disc.getPrmDesc());
				disItm.setPrmId(disc.getPrmId());
				disItm.setPrmType(disc.getPrmType());
				disItm.setSrcTrgList(disc.getSrcTrgList());
				disItm.setTyDsc(disc.getTyDsc());
				discountLines.add(disItm);
			}
		}
		cloned.ordTranDscItms = discountLines;

		cloned.ordTranHeader = this.ordTranHeader;
		cloned.isDRApplied = this.isDRApplied;
		cloned.pluItem = this.pluItem;

		return cloned;
	}

	

	private static final long serialVersionUID = 1L;

	@Transient
	private String rprice; 
	
	@EmbeddedId
	private OrderTranLineItemPK id;

	@Column(name = "AI_LN_ITM_ORG")
	private BigDecimal aiLnItmOrg;

	@Column(name = "AI_LN_ITM_RLTD")
	private BigDecimal aiLnItmRltd;

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

	@Column(name = "RC_ITM_CND_RTN_MR")
	private String rcItmCndRtnMr;

	@Column(name = "RC_RFD_SV")
	private String rcRfdSv;

	@Column(name = "RC_RTN_MR")
	private String rcRtnMr;

	@Column(name = "REGISTRY_ID")
	private String registryId;
	
	

	@Column(name = "TAX_INC_LN_ITM_RTN")
	private BigDecimal taxIncLnItmRtn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_CRT_RCRD")
	private Date tsCrtRcrd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_MDF_RCRD")
	private Date tsMdfRcrd;

	@Column(name = "TX_GP_ID")
	private BigDecimal txGpId;

	@Column(name = "UOM_SLS")
	private String uomSls;

	@Column(name = "VAT_LN_ITM_RTN")
	private BigDecimal vatLnItmRtn = BigDecimal.ZERO;
	

	// bi-directional many-to-one association to OrderTranDiscountItem
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ordTranLineItem", fetch = FetchType.EAGER)
	private List<OrderTranDiscountItem> ordTranDscItms;

	// bi-directional many-to-one association to OrderTranHeader
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "DC_DY_ORD", referencedColumnName = "DC_DY_ORD", insertable = false, updatable = false),
			@JoinColumn(name = "ORD_WS", referencedColumnName = "ORD_WS", insertable = false, updatable = false),
			@JoinColumn(name = "RT_STR_ID", referencedColumnName = "RT_STR_ID", insertable = false, updatable = false),
			@JoinColumn(name = "TRN_SEQ", referencedColumnName = "TRN_SEQ", insertable = false, updatable = false) })
	private OrderTranHeader ordTranHeader;

	@Transient
	private boolean isDRApplied;

	@Transient
	private PLUItemIfc pluItem;
	
	@Transient
	private BigDecimal totalApplyedDiscountAmtOnItem;

	@Column(name = "RC_PRC_OVRR")
	private String priceOvrrRsnCode;
	
	@Column(name= "OVRD_PRC")
	private BigDecimal overRidePrice;
	
	@Column(name ="FL_ITM_PRC_OVRD")
	private String priceOverRideFlag;
	
	@Column(name ="FL_ITM_RTN_QTY")
	private String returnQtyFlag;
	
	@Column(name = "EMP_ID")
	private String empId;
	
	@Column(name="WH_DISPSTN_CODE")
	private String dispostionCode;
	
	@Transient
	private Long shippedQty;
	
	public String getDispostionCode() {
		return dispostionCode;
	}

	public void setDispostionCode(String dispostionCode) {
		this.dispostionCode = dispostionCode;
	}
	
	
	public String getPriceOvrrRsnCode() {
		return priceOvrrRsnCode;
	}

	public void setPriceOvrrRsnCode(String priceOvrrRsnCode) {
		this.priceOvrrRsnCode = priceOvrrRsnCode;
	}

	public boolean isDRApplied() {
		return isDRApplied;
	}

	public void setDRApplied(boolean isDRApplied) {
		this.isDRApplied = isDRApplied;
	}

	public PLUItemIfc getPluItem() {
		return pluItem;
	}

	public void setPluItem(PLUItemIfc pluItem) {
		this.pluItem = pluItem;
	}

	public OrderTranLineItem() {
	}

	public OrderTranLineItemPK getId() {
		return this.id;
	}

	public void setId(OrderTranLineItemPK id) {
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

	public String getRcItmCndRtnMr() {
		return this.rcItmCndRtnMr;
	}

	public void setRcItmCndRtnMr(String rcItmCndRtnMr) {
		this.rcItmCndRtnMr = rcItmCndRtnMr;
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

	public List<OrderTranDiscountItem> getOrdTranDscItms() {
		return this.ordTranDscItms;
	}

	public void setOrdTranDscItms(List<OrderTranDiscountItem> ordTranDscItms) {
		this.ordTranDscItms = ordTranDscItms;
	}

	public OrderTranDiscountItem addOrdTranDscItm(OrderTranDiscountItem ordTranDscItm) {
		getOrdTranDscItms().add(ordTranDscItm);

		return ordTranDscItm;
	}

	public OrderTranDiscountItem removeOrdTranDscItm(OrderTranDiscountItem ordTranDscItm) {
		getOrdTranDscItms().remove(ordTranDscItm);
		ordTranDscItm.setOrdTranLineItem(null);

		return ordTranDscItm;
	}

	public OrderTranHeader getOrdTranHeader() {
		return this.ordTranHeader;
	}

	public void setOrdTranHeader(OrderTranHeader ordTranHeader) {
		this.ordTranHeader = ordTranHeader;
	}

	public BigDecimal getOverRidePrice() {
		return overRidePrice;
	}

	public void setOverRidePrice(BigDecimal overRidePrice) {
		this.overRidePrice = overRidePrice;
	}

	public String getPriceOverRideFlag() {
		return priceOverRideFlag;
	}

	public void setPriceOverRideFlag(String priceOverRideFlag) {
		this.priceOverRideFlag = priceOverRideFlag;
	}

	public String getReturnQtyFlag() {
		return returnQtyFlag;
	}

	public void setReturnQtyFlag(String returnQtyFlag) {
		this.returnQtyFlag = returnQtyFlag;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public BigDecimal getTotalApplyedDiscountAmtOnItem() {
		return totalApplyedDiscountAmtOnItem;
	}

	public void setTotalApplyedDiscountAmtOnItem(BigDecimal totalApplyedDiscountAmtOnItem) {
		this.totalApplyedDiscountAmtOnItem = totalApplyedDiscountAmtOnItem;
	}

	public Long getShippedQty() {
		return shippedQty;
	}

	public void setShippedQty(Long shippedQty) {
		this.shippedQty = shippedQty;
	}
	

	public String getRprice() {
		return rprice;
	}

	public void setRprice(String rprice) {
		this.rprice = rprice;
	}
}