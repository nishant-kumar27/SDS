package rispl.db.model.item.pricing;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_ITEM_PRICE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_PRICE")
@NamedQuery(name="RisplDkItemPrice.findAll", query="SELECT r FROM RisplDkItemPrice r")
public class RisplDkItemPrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkItemPricePK id;

	@Column(name="EV_EF_TMP")
	private Timestamp evEfTmp;

	@Column(name="EV_PRI", precision=22)
	private BigDecimal evPri;

	@Column(name="EVNT_TYP", length=20)
	private String evntTyp;

	@Column(name="ITM_ID", length=25)
	private String itmId;

	@Column(name="MO_OVRD_PRC", precision=15, scale=2)
	private BigDecimal moOvrdPrc;

	@Column(name="SL_UN_AMT", precision=14, scale=4)
	private BigDecimal slUnAmt;

	@Column(name="SL_UN_AMT_TYP_CD", length=20)
	private String slUnAmtTypCd;

	public RisplDkItemPrice() {
	}

	public RisplDkItemPricePK getId() {
		return this.id;
	}

	public void setId(RisplDkItemPricePK id) {
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