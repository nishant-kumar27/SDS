package rispl.dkart.services.entities;

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
///@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPK id;

	@Column(name="DISP_MSG_ID")
	private BigDecimal dispMsgId;

	@Column(name="ITM_BRN_NM")
	private String itmBrnNm;

	@Column(name="ITM_CLR_CD")
	private String itmClrCd;

	@Column(name="ITM_DESC")
	private String itmDesc;

	@Column(name="ITM_DMG_DSC_FLG")
	private String itmDmgDscFlg;

	@Column(name="ITM_DSC_FLG")
	private String itmDscFlg;

	@Column(name="ITM_IMG_LOC")
	private String itmImgLoc;

	@Column(name="ITM_KT_ID")
	private String itmKtId;

	@Column(name="ITM_KT_ST_CD")
	private String itmKtStCd;

	@Column(name="ITM_MF_ID")
	private BigDecimal itmMfId;

	@Column(name="ITM_MRC_HRC_LV_CD")
	private String itmMrcHrcLvCd;

	@Column(name="ITM_MRC_STRC_ID")
	private BigDecimal itmMrcStrcId;

	@Column(name="ITM_MRHRC_GP_ID")
	private String itmMrhrcGpId;

	@Column(name="ITM_POS_DPT_ID")
	private String itmPosDptId;

	@Column(name="ITM_RCRD_CRT_TS")
	private Timestamp itmRcrdCrtTs;

	@Column(name="ITM_RCRD_MDF_TS")
	private Timestamp itmRcrdMdfTs;

	@Column(name="ITM_RGSTRY_FL")
	private String itmRgstryFl;

	@Column(name="ITM_SBST_IDN_FLG")
	private String itmSbstIdnFlg;

	@Column(name="ITM_SHRT_DESC")
	private String itmShrtDesc;

	@Column(name="ITM_SLS_AZN_FLG")
	private String itmSlsAznFlg;

	@Column(name="ITM_SRLZD_FLG")
	private String itmSrlzdFlg;

	@Column(name="ITM_SRZ_CPT_TM_CD")
	private String itmSrzCptTmCd;

	@Column(name="ITM_SZ_CD",insertable=false,updatable=false)
	private String itmSzCd;

	@Column(name="ITM_SZ_REQ_FLG")
	private String itmSzReqFlg;

	@Column(name="ITM_TX_EXM_CD")
	private String itmTxExmCd;

	@Column(name="ITM_TX_GP_ID")
	private BigDecimal itmTxGpId;

	@Column(name="ITM_TY_CD")
	private String itmTyCd;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "ITM_ID", referencedColumnName = "ITM_ID"),
		@JoinColumn(name = "RT_STR_ID", referencedColumnName = "RT_STR_ID"),
		@JoinColumn(name = "ID_ITM_POS", referencedColumnName = "ID_ITM_POS"),
	})
	private ItemAttributes itm;
	
	public ItemAttributes getItm() {
		return itm;
	}

	public void setItm(ItemAttributes itm) {
		this.itm = itm;
	}
	
	public Item() {
	}

	public ItemPK getId() {
		return this.id;
	}

	public void setId(ItemPK id) {
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