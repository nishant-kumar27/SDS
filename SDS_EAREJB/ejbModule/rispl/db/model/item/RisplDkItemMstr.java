package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_ITEM_MSTR database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_MSTR")
@NamedQuery(name="RisplDkItemMstr.findAll", query="SELECT r FROM RisplDkItemMstr r")
public class RisplDkItemMstr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkItemMstrPK id;

	@Column(name="DISP_MSG_ID", precision=22)
	private BigDecimal dispMsgId;

	@Column(name="ITM_BRN_NM", length=120)
	private String itmBrnNm;

	@Column(name="ITM_CLR_CD", length=20)
	private String itmClrCd;

	@Column(name="ITM_DESC", length=250)
	private String itmDesc;

	@Column(name="ITM_DMG_DSC_FLG", length=1)
	private String itmDmgDscFlg;

	@Column(name="ITM_DSC_FLG", length=1)
	private String itmDscFlg;

	@Column(name="ITM_IMG_LOC", length=150)
	private String itmImgLoc;

	@Column(name="ITM_KT_ID", length=20)
	private String itmKtId;

	@Column(name="ITM_KT_ST_CD", length=20)
	private String itmKtStCd;

	@Column(name="ITM_MF_ID", precision=22)
	private BigDecimal itmMfId;

	@Column(name="ITM_MRC_HRC_LV_CD", length=4)
	private String itmMrcHrcLvCd;

	@Column(name="ITM_MRC_STRC_ID", precision=22)
	private BigDecimal itmMrcStrcId;

	@Column(name="ITM_MRHRC_GP_ID", length=14)
	private String itmMrhrcGpId;

	@Column(name="ITM_POS_DPT_ID", length=25)
	private String itmPosDptId;

	@Column(name="ITM_RCRD_CRT_TS", nullable=false)
	private Timestamp itmRcrdCrtTs;

	@Column(name="ITM_RCRD_MDF_TS", nullable=false)
	private Timestamp itmRcrdMdfTs;

	@Column(name="ITM_RGSTRY_FL", length=1)
	private String itmRgstryFl;

	@Column(name="ITM_SBST_IDN_FLG", length=1)
	private String itmSbstIdnFlg;

	@Column(name="ITM_SHRT_DESC", length=120)
	private String itmShrtDesc;

	@Column(name="ITM_SLS_AZN_FLG", length=1)
	private String itmSlsAznFlg;

	@Column(name="ITM_SRLZD_FLG", length=1)
	private String itmSrlzdFlg;

	@Column(name="ITM_SRZ_CPT_TM_CD", length=20)
	private String itmSrzCptTmCd;

	@Column(name="ITM_SZ_CD", length=10)
	private String itmSzCd;

	@Column(name="ITM_SZ_REQ_FLG", length=1)
	private String itmSzReqFlg;

	@Column(name="ITM_TX_EXM_CD", length=20)
	private String itmTxExmCd;

	@Column(name="ITM_TX_GP_ID", precision=22)
	private BigDecimal itmTxGpId;

	@Column(name="ITM_TY_CD", length=20)
	private String itmTyCd;

	public RisplDkItemMstr() {
	}

	public RisplDkItemMstrPK getId() {
		return this.id;
	}

	public void setId(RisplDkItemMstrPK id) {
		this.id = id;
	}

	public BigDecimal getDispMsgId() {
		return this.dispMsgId;
	}

	public void setDispMsgId(BigDecimal dispMsgId) {
		this.dispMsgId = dispMsgId;
	}

	public String getItmBrnNm() {
		return this.itmBrnNm;
	}

	public void setItmBrnNm(String itmBrnNm) {
		this.itmBrnNm = itmBrnNm;
	}

	public String getItmClrCd() {
		return this.itmClrCd;
	}

	public void setItmClrCd(String itmClrCd) {
		this.itmClrCd = itmClrCd;
	}

	public String getItmDesc() {
		return this.itmDesc;
	}

	public void setItmDesc(String itmDesc) {
		this.itmDesc = itmDesc;
	}

	public String getItmDmgDscFlg() {
		return this.itmDmgDscFlg;
	}

	public void setItmDmgDscFlg(String itmDmgDscFlg) {
		this.itmDmgDscFlg = itmDmgDscFlg;
	}

	public String getItmDscFlg() {
		return this.itmDscFlg;
	}

	public void setItmDscFlg(String itmDscFlg) {
		this.itmDscFlg = itmDscFlg;
	}

	public String getItmImgLoc() {
		return this.itmImgLoc;
	}

	public void setItmImgLoc(String itmImgLoc) {
		this.itmImgLoc = itmImgLoc;
	}

	public String getItmKtId() {
		return this.itmKtId;
	}

	public void setItmKtId(String itmKtId) {
		this.itmKtId = itmKtId;
	}

	public String getItmKtStCd() {
		return this.itmKtStCd;
	}

	public void setItmKtStCd(String itmKtStCd) {
		this.itmKtStCd = itmKtStCd;
	}

	public BigDecimal getItmMfId() {
		return this.itmMfId;
	}

	public void setItmMfId(BigDecimal itmMfId) {
		this.itmMfId = itmMfId;
	}

	public String getItmMrcHrcLvCd() {
		return this.itmMrcHrcLvCd;
	}

	public void setItmMrcHrcLvCd(String itmMrcHrcLvCd) {
		this.itmMrcHrcLvCd = itmMrcHrcLvCd;
	}

	public BigDecimal getItmMrcStrcId() {
		return this.itmMrcStrcId;
	}

	public void setItmMrcStrcId(BigDecimal itmMrcStrcId) {
		this.itmMrcStrcId = itmMrcStrcId;
	}

	public String getItmMrhrcGpId() {
		return this.itmMrhrcGpId;
	}

	public void setItmMrhrcGpId(String itmMrhrcGpId) {
		this.itmMrhrcGpId = itmMrhrcGpId;
	}

	public String getItmPosDptId() {
		return this.itmPosDptId;
	}

	public void setItmPosDptId(String itmPosDptId) {
		this.itmPosDptId = itmPosDptId;
	}

	public Timestamp getItmRcrdCrtTs() {
		return this.itmRcrdCrtTs;
	}

	public void setItmRcrdCrtTs(Timestamp itmRcrdCrtTs) {
		this.itmRcrdCrtTs = itmRcrdCrtTs;
	}

	public Timestamp getItmRcrdMdfTs() {
		return this.itmRcrdMdfTs;
	}

	public void setItmRcrdMdfTs(Timestamp itmRcrdMdfTs) {
		this.itmRcrdMdfTs = itmRcrdMdfTs;
	}

	public String getItmRgstryFl() {
		return this.itmRgstryFl;
	}

	public void setItmRgstryFl(String itmRgstryFl) {
		this.itmRgstryFl = itmRgstryFl;
	}

	public String getItmSbstIdnFlg() {
		return this.itmSbstIdnFlg;
	}

	public void setItmSbstIdnFlg(String itmSbstIdnFlg) {
		this.itmSbstIdnFlg = itmSbstIdnFlg;
	}

	public String getItmShrtDesc() {
		return this.itmShrtDesc;
	}

	public void setItmShrtDesc(String itmShrtDesc) {
		this.itmShrtDesc = itmShrtDesc;
	}

	public String getItmSlsAznFlg() {
		return this.itmSlsAznFlg;
	}

	public void setItmSlsAznFlg(String itmSlsAznFlg) {
		this.itmSlsAznFlg = itmSlsAznFlg;
	}

	public String getItmSrlzdFlg() {
		return this.itmSrlzdFlg;
	}

	public void setItmSrlzdFlg(String itmSrlzdFlg) {
		this.itmSrlzdFlg = itmSrlzdFlg;
	}

	public String getItmSrzCptTmCd() {
		return this.itmSrzCptTmCd;
	}

	public void setItmSrzCptTmCd(String itmSrzCptTmCd) {
		this.itmSrzCptTmCd = itmSrzCptTmCd;
	}

	public String getItmSzCd() {
		return this.itmSzCd;
	}

	public void setItmSzCd(String itmSzCd) {
		this.itmSzCd = itmSzCd;
	}

	public String getItmSzReqFlg() {
		return this.itmSzReqFlg;
	}

	public void setItmSzReqFlg(String itmSzReqFlg) {
		this.itmSzReqFlg = itmSzReqFlg;
	}

	public String getItmTxExmCd() {
		return this.itmTxExmCd;
	}

	public void setItmTxExmCd(String itmTxExmCd) {
		this.itmTxExmCd = itmTxExmCd;
	}

	public BigDecimal getItmTxGpId() {
		return this.itmTxGpId;
	}

	public void setItmTxGpId(BigDecimal itmTxGpId) {
		this.itmTxGpId = itmTxGpId;
	}

	public String getItmTyCd() {
		return this.itmTyCd;
	}

	public void setItmTyCd(String itmTyCd) {
		this.itmTyCd = itmTyCd;
	}

}