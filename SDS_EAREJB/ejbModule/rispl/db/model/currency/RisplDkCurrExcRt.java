package rispl.db.model.currency;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_CURR_EXC_RT database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_CURR_EXC_RT")
@NamedQuery(name="RisplDkCurrExcRt.findAll", query="SELECT r FROM RisplDkCurrExcRt r")
public class RisplDkCurrExcRt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkCurrExcRtPK id;

	@Column(name="LL_CNY_EXC", precision=15, scale=2)
	private BigDecimal llCnyExc;

	@Column(name="MO_FE_SV_EXC", precision=15, scale=2)
	private BigDecimal moFeSvExc;

	@Column(name="MO_RT_TO_BUY", precision=19, scale=6)
	private BigDecimal moRtToBuy;

	@Column(name="MO_RT_TO_SL", precision=19, scale=6)
	private BigDecimal moRtToSl;

	public RisplDkCurrExcRt() {
	}

	public RisplDkCurrExcRtPK getId() {
		return this.id;
	}

	public void setId(RisplDkCurrExcRtPK id) {
		this.id = id;
	}

	public BigDecimal getLlCnyExc() {
		return this.llCnyExc;
	}

	public void setLlCnyExc(BigDecimal llCnyExc) {
		this.llCnyExc = llCnyExc;
	}

	public BigDecimal getMoFeSvExc() {
		return this.moFeSvExc;
	}

	public void setMoFeSvExc(BigDecimal moFeSvExc) {
		this.moFeSvExc = moFeSvExc;
	}

	public BigDecimal getMoRtToBuy() {
		return this.moRtToBuy;
	}

	public void setMoRtToBuy(BigDecimal moRtToBuy) {
		this.moRtToBuy = moRtToBuy;
	}

	public BigDecimal getMoRtToSl() {
		return this.moRtToSl;
	}

	public void setMoRtToSl(BigDecimal moRtToSl) {
		this.moRtToSl = moRtToSl;
	}

}