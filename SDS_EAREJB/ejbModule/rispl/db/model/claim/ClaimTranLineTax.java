package rispl.db.model.claim;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CLAIM_TRAN_LINE_TAX database table.
 * 
 */
@Entity
@Table(name="CLAIM_TRAN_LINE_TAX")
@NamedQuery(name="ClaimTranLineTax.findAll", query="SELECT c FROM ClaimTranLineTax c")
public class ClaimTranLineTax implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TRAN_LINE_ID")
	private long tranLineId;

	@Column(name="DC_DY_ORD")
	private String dcDyOrd;

	@Column(name="ORD_LN_ITM_SEQ")
	private BigDecimal ordLnItmSeq;

	@Column(name="ORD_WS")
	private String ordWs;

	@Column(name="RT_STR_ID")
	private String rtStrId;

	@Column(name="TAX_AUTH")
	private String taxAuth;

	@Column(name="TAX_CODE")
	private String taxCode;

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	@Column(name="TRN_SEQ")
	private BigDecimal trnSeq;

	@Column(name="VAT_AMT_LN_ITM")
	private BigDecimal vatAmtLnItm;

	public ClaimTranLineTax() {
	}

	public long getTranLineId() {
		return this.tranLineId;
	}

	public void setTranLineId(long tranLineId) {
		this.tranLineId = tranLineId;
	}

	public String getDcDyOrd() {
		return this.dcDyOrd;
	}

	public void setDcDyOrd(String dcDyOrd) {
		this.dcDyOrd = dcDyOrd;
	}

	public BigDecimal getOrdLnItmSeq() {
		return this.ordLnItmSeq;
	}

	public void setOrdLnItmSeq(BigDecimal ordLnItmSeq) {
		this.ordLnItmSeq = ordLnItmSeq;
	}

	public String getOrdWs() {
		return this.ordWs;
	}

	public void setOrdWs(String ordWs) {
		this.ordWs = ordWs;
	}

	public String getRtStrId() {
		return this.rtStrId;
	}

	public void setRtStrId(String rtStrId) {
		this.rtStrId = rtStrId;
	}

	public String getTaxAuth() {
		return this.taxAuth;
	}

	public void setTaxAuth(String taxAuth) {
		this.taxAuth = taxAuth;
	}

	public String getTaxCode() {
		return this.taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTrnSeq() {
		return this.trnSeq;
	}

	public void setTrnSeq(BigDecimal trnSeq) {
		this.trnSeq = trnSeq;
	}

	public BigDecimal getVatAmtLnItm() {
		return this.vatAmtLnItm;
	}

	public void setVatAmtLnItm(BigDecimal vatAmtLnItm) {
		this.vatAmtLnItm = vatAmtLnItm;
	}

}