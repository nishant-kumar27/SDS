package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_STRGP_LVL database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_STRGP_LVL")
@NamedQuery(name="RisplDkStrgpLvl.findAll", query="SELECT r FROM RisplDkStrgpLvl r")
public class RisplDkStrgpLvl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkStrgpLvlPK id;

	@Column(name="ID_STRGP_LV_PRNT")
	private BigDecimal idStrgpLvPrnt;

	@Column(name="NM_STRGP_LV")
	private String nmStrgpLv;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkStrgpLvl() {
	}

	public RisplDkStrgpLvlPK getId() {
		return this.id;
	}

	public void setId(RisplDkStrgpLvlPK id) {
		this.id = id;
	}

	public BigDecimal getIdStrgpLvPrnt() {
		return this.idStrgpLvPrnt;
	}

	public void setIdStrgpLvPrnt(BigDecimal idStrgpLvPrnt) {
		this.idStrgpLvPrnt = idStrgpLvPrnt;
	}

	public String getNmStrgpLv() {
		return this.nmStrgpLv;
	}

	public void setNmStrgpLv(String nmStrgpLv) {
		this.nmStrgpLv = nmStrgpLv;
	}

	public Timestamp getTsCrtRcrd() {
		return this.tsCrtRcrd;
	}

	public void setTsCrtRcrd(Timestamp tsCrtRcrd) {
		this.tsCrtRcrd = tsCrtRcrd;
	}

	public Timestamp getTsMdfRcrd() {
		return this.tsMdfRcrd;
	}

	public void setTsMdfRcrd(Timestamp tsMdfRcrd) {
		this.tsMdfRcrd = tsMdfRcrd;
	}

}