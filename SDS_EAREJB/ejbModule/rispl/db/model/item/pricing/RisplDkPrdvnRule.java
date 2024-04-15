package rispl.db.model.item.pricing;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the RISPL_DK_PRDVN_RULE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_PRDVN_RULE")
@NamedQuery(name="RisplDkPrdvnRule.findAll", query="SELECT r FROM RisplDkPrdvnRule r")
public class RisplDkPrdvnRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkPrdvnRulePK id;

	@Column(name="CD_BAS_CMP_SRC", nullable=false, length=20)
	private String cdBasCmpSrc;

	@Column(name="CD_BAS_CMP_TGT", nullable=false, length=20)
	private String cdBasCmpTgt;

	@Column(name="CD_BAS_PRDV", precision=22)
	private BigDecimal cdBasPrdv;

	@Column(name="CD_SCP_PRDV", precision=22)
	private BigDecimal cdScpPrdv;

	@Column(name="DC_RU_PRDV_EF")
	private Timestamp dcRuPrdvEf;

	@Column(name="DC_RU_PRDV_EP")
	private Timestamp dcRuPrdvEp;

	@Column(name="DE_RU_PRDV", length=250)
	private String deRuPrdv;

	@Column(name="DP_LDG_STK_MDFR", length=20)
	private String dpLdgStkMdfr;

	@Column(name="FL_ALW_RPT_SRC", nullable=false, length=1)
	private String flAlwRptSrc;

	@Column(name="FL_DL_DST", nullable=false, length=1)
	private String flDlDst;

	@Column(name="ID_PRCGP", precision=22)
	private BigDecimal idPrcgp;

	@Column(name="ID_PRM", precision=22)
	private BigDecimal idPrm;

	@Column(name="ITM_PRC_CTGY_SRC", nullable=false, length=10)
	private String itmPrcCtgySrc;

	@Column(name="ITM_PRC_CTGY_TGT", nullable=false, length=10)
	private String itmPrcCtgyTgt;

	@Column(name="MO_LM_SRC", precision=15, scale=2)
	private BigDecimal moLmSrc;

	@Column(name="MO_LM_TGT", precision=15, scale=2)
	private BigDecimal moLmTgt;

	@Column(name="MO_TH_SRC", precision=15, scale=2)
	private BigDecimal moThSrc;

	@Column(name="MO_TH_TGT", precision=15, scale=2)
	private BigDecimal moThTgt;

	@Column(name="NM_RU_PRDV", length=120)
	private String nmRuPrdv;

	@Column(name="QU_AN_SRC", nullable=false, precision=22)
	private BigDecimal quAnSrc;

	@Column(name="QU_AN_TGT", nullable=false, precision=22)
	private BigDecimal quAnTgt;

	@Column(name="QU_LM_APLY", precision=22)
	private BigDecimal quLmAply;

	@Column(name="TS_CRT_RCRD", nullable=false)
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD", nullable=false)
	private Timestamp tsMdfRcrd;

	//bi-directional many-to-one association to RisplDkPrdvnDptElg
	@OneToMany(mappedBy="risplDkPrdvnRule")
	private List<RisplDkPrdvnDptElg> risplDkPrdvnDptElgs;

	//bi-directional many-to-one association to RisplDkPrdvnItmNelg
	@OneToMany(mappedBy="risplDkPrdvnRule")
	private List<RisplDkPrdvnItmNelg> risplDkPrdvnItmNelgs;

	//bi-directional many-to-one association to RisplDkPrdvnMmitm
	@OneToMany(mappedBy="risplDkPrdvnRule")
	private List<RisplDkPrdvnMmitm> risplDkPrdvnMmitms;

	//bi-directional many-to-one association to RisplDkPrdvnMrchElg
	@OneToMany(mappedBy="risplDkPrdvnRule")
	private List<RisplDkPrdvnMrchElg> risplDkPrdvnMrchElgs;

	//bi-directional many-to-one association to RisplDkPrdvnRuleDisc
	@OneToMany(mappedBy="risplDkPrdvnRule")
	private List<RisplDkPrdvnRuleDisc> risplDkPrdvnRuleDiscs;

	//bi-directional many-to-one association to RisplDkPrdvnRuleElg
	@OneToMany(mappedBy="risplDkPrdvnRule")
	private List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs;

	//bi-directional many-to-one association to RisplDkPrdvnTrshldElg
	@OneToMany(mappedBy="risplDkPrdvnRule")
	private List<RisplDkPrdvnTrshldElg> risplDkPrdvnTrshldElgs;

	public RisplDkPrdvnRule() {
	}

	public RisplDkPrdvnRulePK getId() {
		return this.id;
	}

	public void setId(RisplDkPrdvnRulePK id) {
		this.id = id;
	}

	public String getCdBasCmpSrc() {
		return this.cdBasCmpSrc;
	}

	public void setCdBasCmpSrc(String cdBasCmpSrc) {
		this.cdBasCmpSrc = cdBasCmpSrc;
	}

	public String getCdBasCmpTgt() {
		return this.cdBasCmpTgt;
	}

	public void setCdBasCmpTgt(String cdBasCmpTgt) {
		this.cdBasCmpTgt = cdBasCmpTgt;
	}

	public BigDecimal getCdBasPrdv() {
		return this.cdBasPrdv;
	}

	public void setCdBasPrdv(BigDecimal cdBasPrdv) {
		this.cdBasPrdv = cdBasPrdv;
	}

	public BigDecimal getCdScpPrdv() {
		return this.cdScpPrdv;
	}

	public void setCdScpPrdv(BigDecimal cdScpPrdv) {
		this.cdScpPrdv = cdScpPrdv;
	}

	public Timestamp getDcRuPrdvEf() {
		return this.dcRuPrdvEf;
	}

	public void setDcRuPrdvEf(Timestamp dcRuPrdvEf) {
		this.dcRuPrdvEf = dcRuPrdvEf;
	}

	public Timestamp getDcRuPrdvEp() {
		return this.dcRuPrdvEp;
	}

	public void setDcRuPrdvEp(Timestamp dcRuPrdvEp) {
		this.dcRuPrdvEp = dcRuPrdvEp;
	}

	public String getDeRuPrdv() {
		return this.deRuPrdv;
	}

	public void setDeRuPrdv(String deRuPrdv) {
		this.deRuPrdv = deRuPrdv;
	}

	public String getDpLdgStkMdfr() {
		return this.dpLdgStkMdfr;
	}

	public void setDpLdgStkMdfr(String dpLdgStkMdfr) {
		this.dpLdgStkMdfr = dpLdgStkMdfr;
	}

	public String getFlAlwRptSrc() {
		return this.flAlwRptSrc;
	}

	public void setFlAlwRptSrc(String flAlwRptSrc) {
		this.flAlwRptSrc = flAlwRptSrc;
	}

	public String getFlDlDst() {
		return this.flDlDst;
	}

	public void setFlDlDst(String flDlDst) {
		this.flDlDst = flDlDst;
	}

	public BigDecimal getIdPrcgp() {
		return this.idPrcgp;
	}

	public void setIdPrcgp(BigDecimal idPrcgp) {
		this.idPrcgp = idPrcgp;
	}

	public BigDecimal getIdPrm() {
		return this.idPrm;
	}

	public void setIdPrm(BigDecimal idPrm) {
		this.idPrm = idPrm;
	}

	public String getItmPrcCtgySrc() {
		return this.itmPrcCtgySrc;
	}

	public void setItmPrcCtgySrc(String itmPrcCtgySrc) {
		this.itmPrcCtgySrc = itmPrcCtgySrc;
	}

	public String getItmPrcCtgyTgt() {
		return this.itmPrcCtgyTgt;
	}

	public void setItmPrcCtgyTgt(String itmPrcCtgyTgt) {
		this.itmPrcCtgyTgt = itmPrcCtgyTgt;
	}

	public BigDecimal getMoLmSrc() {
		return this.moLmSrc;
	}

	public void setMoLmSrc(BigDecimal moLmSrc) {
		this.moLmSrc = moLmSrc;
	}

	public BigDecimal getMoLmTgt() {
		return this.moLmTgt;
	}

	public void setMoLmTgt(BigDecimal moLmTgt) {
		this.moLmTgt = moLmTgt;
	}

	public BigDecimal getMoThSrc() {
		return this.moThSrc;
	}

	public void setMoThSrc(BigDecimal moThSrc) {
		this.moThSrc = moThSrc;
	}

	public BigDecimal getMoThTgt() {
		return this.moThTgt;
	}

	public void setMoThTgt(BigDecimal moThTgt) {
		this.moThTgt = moThTgt;
	}

	public String getNmRuPrdv() {
		return this.nmRuPrdv;
	}

	public void setNmRuPrdv(String nmRuPrdv) {
		this.nmRuPrdv = nmRuPrdv;
	}

	public BigDecimal getQuAnSrc() {
		return this.quAnSrc;
	}

	public void setQuAnSrc(BigDecimal quAnSrc) {
		this.quAnSrc = quAnSrc;
	}

	public BigDecimal getQuAnTgt() {
		return this.quAnTgt;
	}

	public void setQuAnTgt(BigDecimal quAnTgt) {
		this.quAnTgt = quAnTgt;
	}

	public BigDecimal getQuLmAply() {
		return this.quLmAply;
	}

	public void setQuLmAply(BigDecimal quLmAply) {
		this.quLmAply = quLmAply;
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

	public List<RisplDkPrdvnDptElg> getRisplDkPrdvnDptElgs() {
		return this.risplDkPrdvnDptElgs;
	}

	public void setRisplDkPrdvnDptElgs(List<RisplDkPrdvnDptElg> risplDkPrdvnDptElgs) {
		this.risplDkPrdvnDptElgs = risplDkPrdvnDptElgs;
	}

	public RisplDkPrdvnDptElg addRisplDkPrdvnDptElg(RisplDkPrdvnDptElg risplDkPrdvnDptElg) {
		getRisplDkPrdvnDptElgs().add(risplDkPrdvnDptElg);
		risplDkPrdvnDptElg.setRisplDkPrdvnRule(this);

		return risplDkPrdvnDptElg;
	}

	public RisplDkPrdvnDptElg removeRisplDkPrdvnDptElg(RisplDkPrdvnDptElg risplDkPrdvnDptElg) {
		getRisplDkPrdvnDptElgs().remove(risplDkPrdvnDptElg);
		risplDkPrdvnDptElg.setRisplDkPrdvnRule(null);

		return risplDkPrdvnDptElg;
	}

	public List<RisplDkPrdvnItmNelg> getRisplDkPrdvnItmNelgs() {
		return this.risplDkPrdvnItmNelgs;
	}

	public void setRisplDkPrdvnItmNelgs(List<RisplDkPrdvnItmNelg> risplDkPrdvnItmNelgs) {
		this.risplDkPrdvnItmNelgs = risplDkPrdvnItmNelgs;
	}

	public RisplDkPrdvnItmNelg addRisplDkPrdvnItmNelg(RisplDkPrdvnItmNelg risplDkPrdvnItmNelg) {
		getRisplDkPrdvnItmNelgs().add(risplDkPrdvnItmNelg);
		risplDkPrdvnItmNelg.setRisplDkPrdvnRule(this);

		return risplDkPrdvnItmNelg;
	}

	public RisplDkPrdvnItmNelg removeRisplDkPrdvnItmNelg(RisplDkPrdvnItmNelg risplDkPrdvnItmNelg) {
		getRisplDkPrdvnItmNelgs().remove(risplDkPrdvnItmNelg);
		risplDkPrdvnItmNelg.setRisplDkPrdvnRule(null);

		return risplDkPrdvnItmNelg;
	}

	public List<RisplDkPrdvnMmitm> getRisplDkPrdvnMmitms() {
		return this.risplDkPrdvnMmitms;
	}

	public void setRisplDkPrdvnMmitms(List<RisplDkPrdvnMmitm> risplDkPrdvnMmitms) {
		this.risplDkPrdvnMmitms = risplDkPrdvnMmitms;
	}

	public RisplDkPrdvnMmitm addRisplDkPrdvnMmitm(RisplDkPrdvnMmitm risplDkPrdvnMmitm) {
		getRisplDkPrdvnMmitms().add(risplDkPrdvnMmitm);
		risplDkPrdvnMmitm.setRisplDkPrdvnRule(this);

		return risplDkPrdvnMmitm;
	}

	public RisplDkPrdvnMmitm removeRisplDkPrdvnMmitm(RisplDkPrdvnMmitm risplDkPrdvnMmitm) {
		getRisplDkPrdvnMmitms().remove(risplDkPrdvnMmitm);
		risplDkPrdvnMmitm.setRisplDkPrdvnRule(null);

		return risplDkPrdvnMmitm;
	}

	public List<RisplDkPrdvnMrchElg> getRisplDkPrdvnMrchElgs() {
		return this.risplDkPrdvnMrchElgs;
	}

	public void setRisplDkPrdvnMrchElgs(List<RisplDkPrdvnMrchElg> risplDkPrdvnMrchElgs) {
		this.risplDkPrdvnMrchElgs = risplDkPrdvnMrchElgs;
	}

	public RisplDkPrdvnMrchElg addRisplDkPrdvnMrchElg(RisplDkPrdvnMrchElg risplDkPrdvnMrchElg) {
		getRisplDkPrdvnMrchElgs().add(risplDkPrdvnMrchElg);
		risplDkPrdvnMrchElg.setRisplDkPrdvnRule(this);

		return risplDkPrdvnMrchElg;
	}

	public RisplDkPrdvnMrchElg removeRisplDkPrdvnMrchElg(RisplDkPrdvnMrchElg risplDkPrdvnMrchElg) {
		getRisplDkPrdvnMrchElgs().remove(risplDkPrdvnMrchElg);
		risplDkPrdvnMrchElg.setRisplDkPrdvnRule(null);

		return risplDkPrdvnMrchElg;
	}

	public List<RisplDkPrdvnRuleDisc> getRisplDkPrdvnRuleDiscs() {
		return this.risplDkPrdvnRuleDiscs;
	}

	public void setRisplDkPrdvnRuleDiscs(List<RisplDkPrdvnRuleDisc> risplDkPrdvnRuleDiscs) {
		this.risplDkPrdvnRuleDiscs = risplDkPrdvnRuleDiscs;
	}

	public RisplDkPrdvnRuleDisc addRisplDkPrdvnRuleDisc(RisplDkPrdvnRuleDisc risplDkPrdvnRuleDisc) {
		getRisplDkPrdvnRuleDiscs().add(risplDkPrdvnRuleDisc);
		risplDkPrdvnRuleDisc.setRisplDkPrdvnRule(this);

		return risplDkPrdvnRuleDisc;
	}

	public RisplDkPrdvnRuleDisc removeRisplDkPrdvnRuleDisc(RisplDkPrdvnRuleDisc risplDkPrdvnRuleDisc) {
		getRisplDkPrdvnRuleDiscs().remove(risplDkPrdvnRuleDisc);
		risplDkPrdvnRuleDisc.setRisplDkPrdvnRule(null);

		return risplDkPrdvnRuleDisc;
	}

	public List<RisplDkPrdvnRuleElg> getRisplDkPrdvnRuleElgs() {
		return this.risplDkPrdvnRuleElgs;
	}

	public void setRisplDkPrdvnRuleElgs(List<RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs) {
		this.risplDkPrdvnRuleElgs = risplDkPrdvnRuleElgs;
	}

	public RisplDkPrdvnRuleElg addRisplDkPrdvnRuleElg(RisplDkPrdvnRuleElg risplDkPrdvnRuleElg) {
		getRisplDkPrdvnRuleElgs().add(risplDkPrdvnRuleElg);
		risplDkPrdvnRuleElg.setRisplDkPrdvnRule(this);

		return risplDkPrdvnRuleElg;
	}

	public RisplDkPrdvnRuleElg removeRisplDkPrdvnRuleElg(RisplDkPrdvnRuleElg risplDkPrdvnRuleElg) {
		getRisplDkPrdvnRuleElgs().remove(risplDkPrdvnRuleElg);
		risplDkPrdvnRuleElg.setRisplDkPrdvnRule(null);

		return risplDkPrdvnRuleElg;
	}

	public List<RisplDkPrdvnTrshldElg> getRisplDkPrdvnTrshldElgs() {
		return this.risplDkPrdvnTrshldElgs;
	}

	public void setRisplDkPrdvnTrshldElgs(List<RisplDkPrdvnTrshldElg> risplDkPrdvnTrshldElgs) {
		this.risplDkPrdvnTrshldElgs = risplDkPrdvnTrshldElgs;
	}

	public RisplDkPrdvnTrshldElg addRisplDkPrdvnTrshldElg(RisplDkPrdvnTrshldElg risplDkPrdvnTrshldElg) {
		getRisplDkPrdvnTrshldElgs().add(risplDkPrdvnTrshldElg);
		risplDkPrdvnTrshldElg.setRisplDkPrdvnRule(this);

		return risplDkPrdvnTrshldElg;
	}

	public RisplDkPrdvnTrshldElg removeRisplDkPrdvnTrshldElg(RisplDkPrdvnTrshldElg risplDkPrdvnTrshldElg) {
		getRisplDkPrdvnTrshldElgs().remove(risplDkPrdvnTrshldElg);
		risplDkPrdvnTrshldElg.setRisplDkPrdvnRule(null);

		return risplDkPrdvnTrshldElg;
	}

}