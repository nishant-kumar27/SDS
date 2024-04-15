package rispl.dkart.services.entities;

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
@NamedQuery(name="ItemSimpProm.findAll", query="SELECT i FROM ItemSimpProm i")
public class ItemSimpProm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemSimpPromPK id;

	@Column(name="CUST_PRCGP_ID")
	private BigDecimal custPrcgpId;

	@Column(name="EV_DESC")
	private String evDesc;

	@Column(name="PROM_AMT")
	private BigDecimal promAmt;

	@Column(name="PROM_CMP_DTL_ID")
	private BigDecimal promCmpDtlId;

	@Column(name="PROM_CMP_ID")
	private BigDecimal promCmpId;

	@Column(name="PROM_EFCT_DT_TM")
	private Timestamp promEfctDtTm;

	@Column(name="PROM_EXP_DT_TM")
	private Timestamp promExpDtTm;

	@Column(name="PROM_ID")
	private BigDecimal promId;

	@Column(name="PROM_OVRD_PRC")
	private BigDecimal promOvrdPrc;

	@Column(name="PROM_PRI")
	private BigDecimal promPri;

	@Column(name="PROM_TYP_CD")
	private String promTypCd;

	public ItemSimpProm() {
	}

	public ItemSimpPromPK getId() {
		return this.id;
	}

	public void setId(ItemSimpPromPK id) {
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