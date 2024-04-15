package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_ITEM_ATTR database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_ATTR")
@NamedQuery(name="RisplDkItemAttr.findAll", query="SELECT r FROM RisplDkItemAttr r")
public class RisplDkItemAttr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkItemAttrPK id;

	@Column(name="EMP_DIS_ALW_FLG", length=1)
	private String empDisAlwFlg;

	@Column(name="ITM_MNM_SL_QNT", precision=7, scale=2)
	private BigDecimal itmMnmSlQnt;

	@Column(name="ITM_MXM_SL_QNT", precision=7, scale=2)
	private BigDecimal itmMxmSlQnt;

	@Column(name="ITM_RSTK_FE_FLG", length=1)
	private String itmRstkFeFlg;

	@Column(name="ITM_UOM_CD", length=5)
	private String itmUomCd;

	@Column(name="LB_TMPLT_ID", length=8)
	private String lbTmpltId;

	@Column(name="PRC_ENTR_RQ_FLG", length=1)
	private String prcEntrRqFlg;

	@Column(name="PRH_RTN_FL", length=1)
	private String prhRtnFl;

	@Column(name="RP_PRC_CMPR_AT_SLS", precision=10, scale=2)
	private BigDecimal rpPrcCmprAtSls;

	@Column(name="RT_PRC_MDFR_FLG", length=1)
	private String rtPrcMdfrFlg;

	@Column(name="SLS_AG_RST_IDN", precision=22)
	private BigDecimal slsAgRstIdn;

	@Column(name="SLS_UOM_CD", length=20)
	private String slsUomCd;

	@Column(name="SPO_ELG_FLG", length=1)
	private String spoElgFlg;

	@Column(name="TX_GP_ID", precision=22)
	private BigDecimal txGpId;

	@Column(name="WHT_ENTR_RQ_FLG", length=1)
	private String whtEntrRqFlg;

	public RisplDkItemAttr() {
	}

	public RisplDkItemAttrPK getId() {
		return this.id;
	}

	public void setId(RisplDkItemAttrPK id) {
		this.id = id;
	}

	public String getEmpDisAlwFlg() {
		return this.empDisAlwFlg;
	}

	public void setEmpDisAlwFlg(String empDisAlwFlg) {
		this.empDisAlwFlg = empDisAlwFlg;
	}

	public BigDecimal getItmMnmSlQnt() {
		return this.itmMnmSlQnt;
	}

	public void setItmMnmSlQnt(BigDecimal itmMnmSlQnt) {
		this.itmMnmSlQnt = itmMnmSlQnt;
	}

	public BigDecimal getItmMxmSlQnt() {
		return this.itmMxmSlQnt;
	}

	public void setItmMxmSlQnt(BigDecimal itmMxmSlQnt) {
		this.itmMxmSlQnt = itmMxmSlQnt;
	}

	public String getItmRstkFeFlg() {
		return this.itmRstkFeFlg;
	}

	public void setItmRstkFeFlg(String itmRstkFeFlg) {
		this.itmRstkFeFlg = itmRstkFeFlg;
	}

	public String getItmUomCd() {
		return this.itmUomCd;
	}

	public void setItmUomCd(String itmUomCd) {
		this.itmUomCd = itmUomCd;
	}

	public String getLbTmpltId() {
		return this.lbTmpltId;
	}

	public void setLbTmpltId(String lbTmpltId) {
		this.lbTmpltId = lbTmpltId;
	}

	public String getPrcEntrRqFlg() {
		return this.prcEntrRqFlg;
	}

	public void setPrcEntrRqFlg(String prcEntrRqFlg) {
		this.prcEntrRqFlg = prcEntrRqFlg;
	}

	public String getPrhRtnFl() {
		return this.prhRtnFl;
	}

	public void setPrhRtnFl(String prhRtnFl) {
		this.prhRtnFl = prhRtnFl;
	}

	public BigDecimal getRpPrcCmprAtSls() {
		return this.rpPrcCmprAtSls;
	}

	public void setRpPrcCmprAtSls(BigDecimal rpPrcCmprAtSls) {
		this.rpPrcCmprAtSls = rpPrcCmprAtSls;
	}

	public String getRtPrcMdfrFlg() {
		return this.rtPrcMdfrFlg;
	}

	public void setRtPrcMdfrFlg(String rtPrcMdfrFlg) {
		this.rtPrcMdfrFlg = rtPrcMdfrFlg;
	}

	public BigDecimal getSlsAgRstIdn() {
		return this.slsAgRstIdn;
	}

	public void setSlsAgRstIdn(BigDecimal slsAgRstIdn) {
		this.slsAgRstIdn = slsAgRstIdn;
	}

	public String getSlsUomCd() {
		return this.slsUomCd;
	}

	public void setSlsUomCd(String slsUomCd) {
		this.slsUomCd = slsUomCd;
	}

	public String getSpoElgFlg() {
		return this.spoElgFlg;
	}

	public void setSpoElgFlg(String spoElgFlg) {
		this.spoElgFlg = spoElgFlg;
	}

	public BigDecimal getTxGpId() {
		return this.txGpId;
	}

	public void setTxGpId(BigDecimal txGpId) {
		this.txGpId = txGpId;
	}

	public String getWhtEntrRqFlg() {
		return this.whtEntrRqFlg;
	}

	public void setWhtEntrRqFlg(String whtEntrRqFlg) {
		this.whtEntrRqFlg = whtEntrRqFlg;
	}

}