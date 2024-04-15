package rispl.dkart.services.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the RISPL_DK_ITEM_PRICE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_PRICE")
@NamedQuery(name="ItemPrice.findAll", query="SELECT i FROM ItemPrice i")
public class ItemPrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPricePK id;

	@Column(name="EV_EF_TMP")
	private Timestamp evEfTmp;

	@Column(name="EV_PRI")
	private BigDecimal evPri;

	@Column(name="EVNT_TYP")
	private String evntTyp;

	@Column(name="ITM_ID")
	private String itmId;

	@Column(name="MO_OVRD_PRC")
	private BigDecimal moOvrdPrc;

	@Column(name="SL_UN_AMT")
	private BigDecimal slUnAmt;

	@Column(name="SL_UN_AMT_TYP_CD")
	private String slUnAmtTypCd;

/*	@OneToOne
	@JoinColumns({
		@JoinColumn(name="EVNT_ID",referencedColumnName="ITM_ID", updatable=false,insertable=false),
		@JoinColumn(name="RT_STR_ID",referencedColumnName="RT_STR_ID", updatable=false,insertable=false)
	})
	private Item item;
	
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}*/

	public ItemPrice() {
	}

	public ItemPricePK getId() {
		return this.id;
	}

	public void setId(ItemPricePK id) {
		this.id = id;
	}

	public Timestamp getEvEfTmp() {
		return this.evEfTmp;
	}

	public void setEvEfTmp(Timestamp evEfTmp) {
		this.evEfTmp = evEfTmp;
	}

	public BigDecimal getEvPri() {
		return this.evPri;
	}

	public void setEvPri(BigDecimal evPri) {
		this.evPri = evPri;
	}

	public String getEvntTyp() {
		return this.evntTyp;
	}

	public void setEvntTyp(String evntTyp) {
		this.evntTyp = evntTyp;
	}

	public String getItmId() {
		return this.itmId;
	}

	public void setItmId(String itmId) {
		this.itmId = itmId;
	}

	public BigDecimal getMoOvrdPrc() {
		return this.moOvrdPrc;
	}

	public void setMoOvrdPrc(BigDecimal moOvrdPrc) {
		this.moOvrdPrc = moOvrdPrc;
	}

	public BigDecimal getSlUnAmt() {
		return this.slUnAmt;
	}

	public void setSlUnAmt(BigDecimal slUnAmt) {
		this.slUnAmt = slUnAmt;
	}

	public String getSlUnAmtTypCd() {
		return this.slUnAmtTypCd;
	}

	public void setSlUnAmtTypCd(String slUnAmtTypCd) {
		this.slUnAmtTypCd = slUnAmtTypCd;
	}

}