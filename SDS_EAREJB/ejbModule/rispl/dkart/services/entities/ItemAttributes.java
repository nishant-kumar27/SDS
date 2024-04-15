package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;

import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.customer.CustomerSegment;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the RISPL_DK_ITEM_ATTR database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_ATTR")
@NamedQuery(name="ItemAttributes.findAll", query="SELECT i FROM ItemAttributes i")
public class ItemAttributes implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemAttributesPK id;

	@Column(name="EMP_DIS_ALW_FLG")
	private String empDisAlwFlg;

	@Column(name="ITM_MNM_SL_QNT")
	private BigDecimal itmMnmSlQnt;

	@Column(name="ITM_MXM_SL_QNT")
	private BigDecimal itmMxmSlQnt;

	@Column(name="ITM_RSTK_FE_FLG")
	private String itmRstkFeFlg;

	@Column(name="ITM_UOM_CD")
	private String itmUomCd;

	@Column(name="LB_TMPLT_ID")
	private String lbTmpltId;

	@Column(name="PRC_ENTR_RQ_FLG")
	private String prcEntrRqFlg;

	@Column(name="PRH_RTN_FL")
	private String prhRtnFl;

	@Column(name="RP_PRC_CMPR_AT_SLS")
	private BigDecimal rpPrcCmprAtSls;

	@Column(name="RT_PRC_MDFR_FLG")
	private String rtPrcMdfrFlg;

	@Column(name="SLS_AG_RST_IDN")
	private BigDecimal slsAgRstIdn;

	@Column(name="SLS_UOM_CD")
	private String slsUomCd;

	@Column(name="SPO_ELG_FLG")
	private String spoElgFlg;

	@Column(name="TX_GP_ID")
	private BigDecimal txGpId;

	@Column(name="WHT_ENTR_RQ_FLG")
	private String whtEntrRqFlg;
	
	 /* @JoinColumn(name = "ITM_ID", referencedColumnName = "ITM_ID")
	  @ManyToOne
	  private Item itm;*/
	  @OneToOne(cascade = CascadeType.ALL, mappedBy = "itm")
	  private Item itm;

	public Item getItm() {
		return itm;
	}

	public void setItm(Item itm) {
		this.itm = itm;
	}

	public ItemAttributes() {
	}

	public ItemAttributesPK getId() {
		return this.id;
	}

	public void setId(ItemAttributesPK id) {
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