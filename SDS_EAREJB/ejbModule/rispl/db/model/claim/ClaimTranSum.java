package rispl.db.model.claim;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the CLAIM_TRAN_SUM database table.
 * 
 */
@Entity
@Table(name = "CLAIM_TRAN_SUM")
@NamedQueries(value = { @NamedQuery(name = "ClaimTranSum.findAll", query = "SELECT c FROM ClaimTranSum c"),
		@NamedQuery(name = "ClaimTranSum.findByInvoiceID", query = "SELECT c FROM ClaimTranSum c where c.idOrdArNmb LIKE :invoiceID"),
		@NamedQuery(name = "ClaimTranSum.findByOrderID", query = "SELECT c FROM ClaimTranSum c where c.idOrd LIKE :orderID") })
public class ClaimTranSum implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClaimTranSumPK id;

	@Column(name = "CD_CNY_ISO")
	private String cdCnyIso;

	@Column(name = "CD_CO_ISO")
	private String cdCoIso;

	@Column(name = "CNT_SND_PKG")
	private BigDecimal cntSndPkg;

	@Column(name = "CO_PRSL")
	private String coPrsl;

	@Temporal(TemporalType.DATE)
	@Column(name = "CUST_LPO_DATE")
	private Date custLpoDate;

	@Column(name = "CUST_LPO_NUM")
	private String custLpoNum;

	@Column(name = "DKART__TND_TOT")
	private BigDecimal dkartTndTot;

	@Column(name = "DKART_DS_APLD")
	private BigDecimal dkartDsApld;

	@Column(name = "DKART_DSC_TOT")
	private BigDecimal dkartDscTot;

	@Column(name = "DKART_EXPENSES")
	private BigDecimal dkartExpenses;

	@Column(name = "DKART_NET_TOT")
	private BigDecimal dkartNetTot;

	@Column(name = "DKART_SLS_TOT")
	private BigDecimal dkartSlsTot;

	@Column(name = "DKART_TAX_INC_TOT")
	private BigDecimal dkartTaxIncTot;

	@Column(name = "DKART_TAX_TOT")
	private BigDecimal dkartTaxTot;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_AG_RST_DOB")
	private Date dtAgRstDob;

	@Column(name = "FL_CT_AZN_RQ")
	private String flCtAznRq;

	@Column(name = "FL_RCP_GF_TRN")
	private String flRcpGfTrn;

	@Column(name = "FL_SND_CT_PHY")
	private String flSndCtPhy;

	@Column(name = "ID_EM")
	private String idEm;

	@Column(name = "ID_IRS_CT")
	private String idIrsCt;

	@Column(name = "ID_MSK_PRSL")
	private String idMskPrsl;

	@Column(name = "ID_ORD")
	private String idOrd;

	@Column(name = "ID_ORD_AR")
	private String idOrdAr;

	@Column(name = "ID_ORD_AR_NMB")
	private String idOrdArNmb;

	@Column(name = "IN_ELPSD_IDL")
	private BigDecimal inElpsdIdl;

	@Column(name = "IN_LCK_ELPSD")
	private BigDecimal inLckElpsd;

	@Column(name = "IN_RNG_ELPSD")
	private BigDecimal inRngElpsd;

	@Column(name = "IN_TND_ELPSD")
	private BigDecimal inTndElpsd;

	@Temporal(TemporalType.DATE)
	@Column(name = "ORD_EF_DATE")
	private Date ordEfDate;

	@Column(name = "ORD_ID_CT")
	private String ordIdCt;

	@Column(name = "ORD_ID_LY")
	private String ordIdLy;

	@Column(name = "ORD_LVL_DVR")
	private String ordLvlDvr;

	@Column(name = "PE_ITM_LN_KY")
	private BigDecimal peItmLnKy;

	@Column(name = "PE_ITM_LN_SC")
	private BigDecimal peItmLnSc;

	@Column(name = "QU_DPT_KY")
	private BigDecimal quDptKy;

	@Column(name = "QU_ITM_LN_KY")
	private BigDecimal quItmLnKy;

	@Column(name = "QU_ITM_LN_SC")
	private BigDecimal quItmLnSc;

	@Column(name = "RC_RSN_SPN")
	private String rcRsnSpn;

	@Column(name = "RTN_TKT_NO")
	private String rtnTktNo;

	@Column(name = "ST_PRSL")
	private String stPrsl;

	@Column(name = "TY_SND_CT")
	private String tySndCt;

	//bi-directional many-to-one association to ClaimTranHeader
	@OneToOne
	@JoinColumns({ @JoinColumn(name = "DC_DY_ORD", referencedColumnName = "DC_DY_ORD"),
			@JoinColumn(name = "ORD_WS", referencedColumnName = "ORD_WS"),
			@JoinColumn(name = "RT_STR_ID", referencedColumnName = "RT_STR_ID"),
			@JoinColumn(name = "TRN_SEQ", referencedColumnName = "TRN_SEQ") })
	private ClaimTranHeader claimTranHeader;

	public ClaimTranSum() {
	}

	public ClaimTranSumPK getId() {
		return this.id;
	}

	public void setId(ClaimTranSumPK id) {
		this.id = id;
	}

	public String getCdCnyIso() {
		return this.cdCnyIso;
	}

	public void setCdCnyIso(String cdCnyIso) {
		this.cdCnyIso = cdCnyIso;
	}

	public String getCdCoIso() {
		return this.cdCoIso;
	}

	public void setCdCoIso(String cdCoIso) {
		this.cdCoIso = cdCoIso;
	}

	public BigDecimal getCntSndPkg() {
		return this.cntSndPkg;
	}

	public void setCntSndPkg(BigDecimal cntSndPkg) {
		this.cntSndPkg = cntSndPkg;
	}

	public String getCoPrsl() {
		return this.coPrsl;
	}

	public void setCoPrsl(String coPrsl) {
		this.coPrsl = coPrsl;
	}

	public Date getCustLpoDate() {
		return this.custLpoDate;
	}

	public void setCustLpoDate(Date custLpoDate) {
		this.custLpoDate = custLpoDate;
	}

	public String getCustLpoNum() {
		return this.custLpoNum;
	}

	public void setCustLpoNum(String custLpoNum) {
		this.custLpoNum = custLpoNum;
	}

	public BigDecimal getDkartTndTot() {
		return this.dkartTndTot;
	}

	public void setDkartTndTot(BigDecimal dkartTndTot) {
		this.dkartTndTot = dkartTndTot;
	}

	public BigDecimal getDkartDsApld() {
		return this.dkartDsApld;
	}

	public void setDkartDsApld(BigDecimal dkartDsApld) {
		this.dkartDsApld = dkartDsApld;
	}

	public BigDecimal getDkartDscTot() {
		return this.dkartDscTot;
	}

	public void setDkartDscTot(BigDecimal dkartDscTot) {
		this.dkartDscTot = dkartDscTot;
	}

	public BigDecimal getDkartExpenses() {
		return this.dkartExpenses;
	}

	public void setDkartExpenses(BigDecimal dkartExpenses) {
		this.dkartExpenses = dkartExpenses;
	}

	public BigDecimal getDkartNetTot() {
		return this.dkartNetTot;
	}

	public void setDkartNetTot(BigDecimal dkartNetTot) {
		this.dkartNetTot = dkartNetTot;
	}

	public BigDecimal getDkartSlsTot() {
		return this.dkartSlsTot;
	}

	public void setDkartSlsTot(BigDecimal dkartSlsTot) {
		this.dkartSlsTot = dkartSlsTot;
	}

	public BigDecimal getDkartTaxIncTot() {
		return this.dkartTaxIncTot;
	}

	public void setDkartTaxIncTot(BigDecimal dkartTaxIncTot) {
		this.dkartTaxIncTot = dkartTaxIncTot;
	}

	public BigDecimal getDkartTaxTot() {
		return this.dkartTaxTot;
	}

	public void setDkartTaxTot(BigDecimal dkartTaxTot) {
		this.dkartTaxTot = dkartTaxTot;
	}

	public Date getDtAgRstDob() {
		return this.dtAgRstDob;
	}

	public void setDtAgRstDob(Date dtAgRstDob) {
		this.dtAgRstDob = dtAgRstDob;
	}

	public String getFlCtAznRq() {
		return this.flCtAznRq;
	}

	public void setFlCtAznRq(String flCtAznRq) {
		this.flCtAznRq = flCtAznRq;
	}

	public String getFlRcpGfTrn() {
		return this.flRcpGfTrn;
	}

	public void setFlRcpGfTrn(String flRcpGfTrn) {
		this.flRcpGfTrn = flRcpGfTrn;
	}

	public String getFlSndCtPhy() {
		return this.flSndCtPhy;
	}

	public void setFlSndCtPhy(String flSndCtPhy) {
		this.flSndCtPhy = flSndCtPhy;
	}

	public String getIdEm() {
		return this.idEm;
	}

	public void setIdEm(String idEm) {
		this.idEm = idEm;
	}

	public String getIdIrsCt() {
		return this.idIrsCt;
	}

	public void setIdIrsCt(String idIrsCt) {
		this.idIrsCt = idIrsCt;
	}

	public String getIdMskPrsl() {
		return this.idMskPrsl;
	}

	public void setIdMskPrsl(String idMskPrsl) {
		this.idMskPrsl = idMskPrsl;
	}

	public String getIdOrd() {
		return this.idOrd;
	}

	public void setIdOrd(String idOrd) {
		this.idOrd = idOrd;
	}

	public String getIdOrdAr() {
		return this.idOrdAr;
	}

	public void setIdOrdAr(String idOrdAr) {
		this.idOrdAr = idOrdAr;
	}

	public String getIdOrdArNmb() {
		return this.idOrdArNmb;
	}

	public void setIdOrdArNmb(String idOrdArNmb) {
		this.idOrdArNmb = idOrdArNmb;
	}

	public BigDecimal getInElpsdIdl() {
		return this.inElpsdIdl;
	}

	public void setInElpsdIdl(BigDecimal inElpsdIdl) {
		this.inElpsdIdl = inElpsdIdl;
	}

	public BigDecimal getInLckElpsd() {
		return this.inLckElpsd;
	}

	public void setInLckElpsd(BigDecimal inLckElpsd) {
		this.inLckElpsd = inLckElpsd;
	}

	public BigDecimal getInRngElpsd() {
		return this.inRngElpsd;
	}

	public void setInRngElpsd(BigDecimal inRngElpsd) {
		this.inRngElpsd = inRngElpsd;
	}

	public BigDecimal getInTndElpsd() {
		return this.inTndElpsd;
	}

	public void setInTndElpsd(BigDecimal inTndElpsd) {
		this.inTndElpsd = inTndElpsd;
	}

	public Date getOrdEfDate() {
		return this.ordEfDate;
	}

	public void setOrdEfDate(Date ordEfDate) {
		this.ordEfDate = ordEfDate;
	}

	public String getOrdIdCt() {
		return this.ordIdCt;
	}

	public void setOrdIdCt(String ordIdCt) {
		this.ordIdCt = ordIdCt;
	}

	public String getOrdIdLy() {
		return this.ordIdLy;
	}

	public void setOrdIdLy(String ordIdLy) {
		this.ordIdLy = ordIdLy;
	}

	public String getOrdLvlDvr() {
		return this.ordLvlDvr;
	}

	public void setOrdLvlDvr(String ordLvlDvr) {
		this.ordLvlDvr = ordLvlDvr;
	}

	public BigDecimal getPeItmLnKy() {
		return this.peItmLnKy;
	}

	public void setPeItmLnKy(BigDecimal peItmLnKy) {
		this.peItmLnKy = peItmLnKy;
	}

	public BigDecimal getPeItmLnSc() {
		return this.peItmLnSc;
	}

	public void setPeItmLnSc(BigDecimal peItmLnSc) {
		this.peItmLnSc = peItmLnSc;
	}

	public BigDecimal getQuDptKy() {
		return this.quDptKy;
	}

	public void setQuDptKy(BigDecimal quDptKy) {
		this.quDptKy = quDptKy;
	}

	public BigDecimal getQuItmLnKy() {
		return this.quItmLnKy;
	}

	public void setQuItmLnKy(BigDecimal quItmLnKy) {
		this.quItmLnKy = quItmLnKy;
	}

	public BigDecimal getQuItmLnSc() {
		return this.quItmLnSc;
	}

	public void setQuItmLnSc(BigDecimal quItmLnSc) {
		this.quItmLnSc = quItmLnSc;
	}

	public String getRcRsnSpn() {
		return this.rcRsnSpn;
	}

	public void setRcRsnSpn(String rcRsnSpn) {
		this.rcRsnSpn = rcRsnSpn;
	}

	public String getRtnTktNo() {
		return this.rtnTktNo;
	}

	public void setRtnTktNo(String rtnTktNo) {
		this.rtnTktNo = rtnTktNo;
	}

	public String getStPrsl() {
		return this.stPrsl;
	}

	public void setStPrsl(String stPrsl) {
		this.stPrsl = stPrsl;
	}

	public String getTySndCt() {
		return this.tySndCt;
	}

	public void setTySndCt(String tySndCt) {
		this.tySndCt = tySndCt;
	}

	public ClaimTranHeader getClaimTranHeader() {
		return this.claimTranHeader;
	}

	public void setClaimTranHeader(ClaimTranHeader claimTranHeader) {
		this.claimTranHeader = claimTranHeader;
	}

}