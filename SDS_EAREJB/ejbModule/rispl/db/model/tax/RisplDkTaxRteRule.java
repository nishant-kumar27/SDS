package rispl.db.model.tax;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_TAX_RTE_RULE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_TAX_RTE_RULE")
@NamedQuery(name="RisplDkTaxRteRule.findAll", query="SELECT r FROM RisplDkTaxRteRule r")
public class RisplDkTaxRteRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkTaxRteRulePK id;

	@Column(name="CD_TYP", precision=22)
	private BigDecimal cdTyp;

	@Column(name="FL_TX_ABV_TH_MO", length=1)
	private String flTxAbvThMo;

	@Column(name="MAX_TXBL_AMT", precision=10, scale=2)
	private BigDecimal maxTxblAmt;

	@Column(name="MIN_TXBL_AMT", precision=10, scale=2)
	private BigDecimal minTxblAmt;

	@Column(name="TAX_AMT", precision=10, scale=2)
	private BigDecimal taxAmt;

	@Column(name="TAX_PERCNT", precision=13, scale=5)
	private BigDecimal taxPercnt;

	@Column(name="TAX_THRSHLD_AMT", precision=10, scale=2)
	private BigDecimal taxThrshldAmt;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	@Column(name="TS_RT_TX_EF")
	private Timestamp tsRtTxEf;

	@Column(name="TS_RT_TX_EP")
	private Timestamp tsRtTxEp;

	public RisplDkTaxRteRule() {
	}

	public RisplDkTaxRteRulePK getId() {
		return this.id;
	}

	public void setId(RisplDkTaxRteRulePK id) {
		this.id = id;
	}

	public BigDecimal getCdTyp() {
		return this.cdTyp;
	}

	public void setCdTyp(BigDecimal cdTyp) {
		this.cdTyp = cdTyp;
	}

	public String getFlTxAbvThMo() {
		return this.flTxAbvThMo;
	}

	public void setFlTxAbvThMo(String flTxAbvThMo) {
		this.flTxAbvThMo = flTxAbvThMo;
	}

	public BigDecimal getMaxTxblAmt() {
		return this.maxTxblAmt;
	}

	public void setMaxTxblAmt(BigDecimal maxTxblAmt) {
		this.maxTxblAmt = maxTxblAmt;
	}

	public BigDecimal getMinTxblAmt() {
		return this.minTxblAmt;
	}

	public void setMinTxblAmt(BigDecimal minTxblAmt) {
		this.minTxblAmt = minTxblAmt;
	}

	public BigDecimal getTaxAmt() {
		return this.taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getTaxPercnt() {
		return this.taxPercnt;
	}

	public void setTaxPercnt(BigDecimal taxPercnt) {
		this.taxPercnt = taxPercnt;
	}

	public BigDecimal getTaxThrshldAmt() {
		return this.taxThrshldAmt;
	}

	public void setTaxThrshldAmt(BigDecimal taxThrshldAmt) {
		this.taxThrshldAmt = taxThrshldAmt;
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

	public Timestamp getTsRtTxEf() {
		return this.tsRtTxEf;
	}

	public void setTsRtTxEf(Timestamp tsRtTxEf) {
		this.tsRtTxEf = tsRtTxEf;
	}

	public Timestamp getTsRtTxEp() {
		return this.tsRtTxEp;
	}

	public void setTsRtTxEp(Timestamp tsRtTxEp) {
		this.tsRtTxEp = tsRtTxEp;
	}

}