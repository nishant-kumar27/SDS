package rispl.db.model.item.pricing;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_ITEM_SIMP_PROM database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_SIMP_PROM")
@NamedQuery(name="RisplDkItemSimpProm.findAll", query="SELECT r FROM RisplDkItemSimpProm r")
public class RisplDkItemSimpProm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkItemSimpPromPK id;

	@Column(name="CUST_PRCGP_ID", precision=10)
	private BigDecimal custPrcgpId;

	@Column(name="EV_DESC", length=250)
	private String evDesc;

	@Column(name="PROM_AMT", precision=10, scale=2)
	private BigDecimal promAmt;

	@Column(name="PROM_CMP_DTL_ID", precision=20)
	private BigDecimal promCmpDtlId;

	@Column(name="PROM_CMP_ID", precision=20)
	private BigDecimal promCmpId;

	@Column(name="PROM_EFCT_DT_TM", nullable=false)
	private Timestamp promEfctDtTm;

	@Column(name="PROM_EXP_DT_TM", nullable=false)
	private Timestamp promExpDtTm;

	@Column(name="PROM_ID", precision=20)
	private BigDecimal promId;

	@Column(name="PROM_OVRD_PRC", precision=10, scale=2)
	private BigDecimal promOvrdPrc;

	@Column(name="PROM_PRI", precision=5)
	private BigDecimal promPri;

	@Column(name="PROM_TYP_CD", length=15)
	private String promTypCd;

	public RisplDkItemSimpProm() {
	}

	public RisplDkItemSimpPromPK getId() {
		return this.id;
	}

	public void setId(RisplDkItemSimpPromPK id) {
		this.id = id;
	}

	public BigDecimal getCustPrcgpId() {
		return this.custPrcgpId;
	}

	public void setCustPrcgpId(BigDecimal custPrcgpId) {
		this.custPrcgpId = custPrcgpId;
	}

	public String getEvDesc() {
		return this.evDesc;
	}

	public void setEvDesc(String evDesc) {
		this.evDesc = evDesc;
	}

	public BigDecimal getPromAmt() {
		return this.promAmt;
	}

	public void setPromAmt(BigDecimal promAmt) {
		this.promAmt = promAmt;
	}

	public BigDecimal getPromCmpDtlId() {
		return this.promCmpDtlId;
	}

	public void setPromCmpDtlId(BigDecimal promCmpDtlId) {
		this.promCmpDtlId = promCmpDtlId;
	}

	public BigDecimal getPromCmpId() {
		return this.promCmpId;
	}

	public void setPromCmpId(BigDecimal promCmpId) {
		this.promCmpId = promCmpId;
	}

	public Timestamp getPromEfctDtTm() {
		return this.promEfctDtTm;
	}

	public void setPromEfctDtTm(Timestamp promEfctDtTm) {
		this.promEfctDtTm = promEfctDtTm;
	}

	public Timestamp getPromExpDtTm() {
		return this.promExpDtTm;
	}

	public void setPromExpDtTm(Timestamp promExpDtTm) {
		this.promExpDtTm = promExpDtTm;
	}

	public BigDecimal getPromId() {
		return this.promId;
	}

	public void setPromId(BigDecimal promId) {
		this.promId = promId;
	}

	public BigDecimal getPromOvrdPrc() {
		return this.promOvrdPrc;
	}

	public void setPromOvrdPrc(BigDecimal promOvrdPrc) {
		this.promOvrdPrc = promOvrdPrc;
	}

	public BigDecimal getPromPri() {
		return this.promPri;
	}

	public void setPromPri(BigDecimal promPri) {
		this.promPri = promPri;
	}

	public String getPromTypCd() {
		return this.promTypCd;
	}

	public void setPromTypCd(String promTypCd) {
		this.promTypCd = promTypCd;
	}

}