package rispl.db.model.claim;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CLAIM_TRAN_DSC_ITM database table.
 * 
 */
@Entity
@Table(name="CLAIM_TRAN_DSC_ITM")
@NamedQuery(name="ClaimTranDscItm.findAll", query="SELECT c FROM ClaimTranDscItm c")
public class ClaimTranDscItm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClaimTranDscItmPK id;

	@Column(name="DSC_AMT")
	private BigDecimal dscAmt;

	@Column(name="DSC_PER")
	private BigDecimal dscPer;

	@Column(name="PRM_CMP_DTLID")
	private BigDecimal prmCmpDtlid;

	@Column(name="PRM_CMP_ID")
	private BigDecimal prmCmpId;

	@Column(name="PRM_DESC")
	private String prmDesc;

	@Column(name="PRM_ID")
	private BigDecimal prmId;

	@Column(name="PRM_TYPE")
	private BigDecimal prmType;

	@Lob
	@Column(name="SRC_TRG_LIST")
	private String srcTrgList;

	@Column(name="TY_DSC")
	private BigDecimal tyDsc;
	
	@Column(name = "UNIT_DSC_AMT")
	private BigDecimal unitDscAmt;
	
	@Column(name = "RC_DSC")
	private String discReasonCode;

	//bi-directional many-to-one association to ClaimTranLineItem
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="DC_DY_ORD", referencedColumnName="DC_DY_ORD"),
		@JoinColumn(name="ORD_LN_ITM_SEQ", referencedColumnName="ORD_LN_ITM_SEQ"),
		@JoinColumn(name="ORD_WS", referencedColumnName="ORD_WS"),
		@JoinColumn(name="RT_STR_ID", referencedColumnName="RT_STR_ID"),
		@JoinColumn(name="TRN_SEQ", referencedColumnName="TRN_SEQ")
		})
	private ClaimTranLineItem claimTranLineItem;

	public ClaimTranDscItm() {
	}

	public ClaimTranDscItmPK getId() {
		return this.id;
	}

	public void setId(ClaimTranDscItmPK id) {
		this.id = id;
	}

	public BigDecimal getDscAmt() {
		return this.dscAmt;
	}

	public void setDscAmt(BigDecimal dscAmt) {
		this.dscAmt = dscAmt;
	}

	public BigDecimal getDscPer() {
		return this.dscPer;
	}

	public void setDscPer(BigDecimal dscPer) {
		this.dscPer = dscPer;
	}

	public BigDecimal getPrmCmpDtlid() {
		return this.prmCmpDtlid;
	}

	public void setPrmCmpDtlid(BigDecimal prmCmpDtlid) {
		this.prmCmpDtlid = prmCmpDtlid;
	}

	public BigDecimal getPrmCmpId() {
		return this.prmCmpId;
	}

	public void setPrmCmpId(BigDecimal prmCmpId) {
		this.prmCmpId = prmCmpId;
	}

	public String getPrmDesc() {
		return this.prmDesc;
	}

	public void setPrmDesc(String prmDesc) {
		this.prmDesc = prmDesc;
	}

	public BigDecimal getPrmId() {
		return this.prmId;
	}

	public void setPrmId(BigDecimal prmId) {
		this.prmId = prmId;
	}

	public BigDecimal getPrmType() {
		return this.prmType;
	}

	public void setPrmType(BigDecimal prmType) {
		this.prmType = prmType;
	}

	public String getSrcTrgList() {
		return this.srcTrgList;
	}

	public void setSrcTrgList(String srcTrgList) {
		this.srcTrgList = srcTrgList;
	}

	public BigDecimal getTyDsc() {
		return this.tyDsc;
	}

	public void setTyDsc(BigDecimal tyDsc) {
		this.tyDsc = tyDsc;
	}

	public ClaimTranLineItem getClaimTranLineItem() {
		return this.claimTranLineItem;
	}

	public void setClaimTranLineItem(ClaimTranLineItem claimTranLineItem) {
		this.claimTranLineItem = claimTranLineItem;
	}

	public BigDecimal getUnitDscAmt() {
		return unitDscAmt;
	}

	public void setUnitDscAmt(BigDecimal unitDscAmt) {
		this.unitDscAmt = unitDscAmt;
	}

	public String getDiscReasonCode() {
		return discReasonCode;
	}

	public void setDiscReasonCode(String discReasonCode) {
		this.discReasonCode = discReasonCode;
	}
}