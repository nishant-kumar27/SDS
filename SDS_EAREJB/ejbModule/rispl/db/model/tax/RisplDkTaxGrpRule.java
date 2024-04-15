package rispl.db.model.tax;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_TAX_GRP_RULE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_TAX_GRP_RULE")
@NamedQuery(name="RisplDkTaxGrpRule.findAll", query="SELECT r FROM RisplDkTaxGrpRule r")
public class RisplDkTaxGrpRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkTaxGrpRulePK id;

	@Column(name="AI_CMPND", precision=22)
	private BigDecimal aiCmpnd;

	@Column(name="CAL_MTH_CD", precision=22)
	private BigDecimal calMthCd;

	@Column(name="CD_TX_RT_RU_USG", precision=22)
	private BigDecimal cdTxRtRuUsg;

	@Column(name="INC_TAX_FLG", length=1)
	private String incTaxFlg;

	@Column(name="TAX_GS_AMT_FLG", length=1)
	private String taxGsAmtFlg;

	@Column(name="TAX_HLDY_FLG", length=1)
	private String taxHldyFlg;

	@Column(name="TAX_RUL_DES", length=250)
	private String taxRulDes;

	@Column(name="TAX_RUL_NME", length=120)
	private String taxRulNme;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkTaxGrpRule() {
	}

	public RisplDkTaxGrpRulePK getId() {
		return this.id;
	}

	public void setId(RisplDkTaxGrpRulePK id) {
		this.id = id;
	}

	public BigDecimal getAiCmpnd() {
		return this.aiCmpnd;
	}

	public void setAiCmpnd(BigDecimal aiCmpnd) {
		this.aiCmpnd = aiCmpnd;
	}

	public BigDecimal getCalMthCd() {
		return this.calMthCd;
	}

	public void setCalMthCd(BigDecimal calMthCd) {
		this.calMthCd = calMthCd;
	}

	public BigDecimal getCdTxRtRuUsg() {
		return this.cdTxRtRuUsg;
	}

	public void setCdTxRtRuUsg(BigDecimal cdTxRtRuUsg) {
		this.cdTxRtRuUsg = cdTxRtRuUsg;
	}

	public String getIncTaxFlg() {
		return this.incTaxFlg;
	}

	public void setIncTaxFlg(String incTaxFlg) {
		this.incTaxFlg = incTaxFlg;
	}

	public String getTaxGsAmtFlg() {
		return this.taxGsAmtFlg;
	}

	public void setTaxGsAmtFlg(String taxGsAmtFlg) {
		this.taxGsAmtFlg = taxGsAmtFlg;
	}

	public String getTaxHldyFlg() {
		return this.taxHldyFlg;
	}

	public void setTaxHldyFlg(String taxHldyFlg) {
		this.taxHldyFlg = taxHldyFlg;
	}

	public String getTaxRulDes() {
		return this.taxRulDes;
	}

	public void setTaxRulDes(String taxRulDes) {
		this.taxRulDes = taxRulDes;
	}

	public String getTaxRulNme() {
		return this.taxRulNme;
	}

	public void setTaxRulNme(String taxRulNme) {
		this.taxRulNme = taxRulNme;
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

}