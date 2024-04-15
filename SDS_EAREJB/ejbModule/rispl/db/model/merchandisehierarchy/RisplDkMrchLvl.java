package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_MRCH_LVL database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_MRCH_LVL")
@NamedQuery(name="RisplDkMrchLvl.findAll", query="SELECT r FROM RisplDkMrchLvl r")
public class RisplDkMrchLvl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkMrchLvlPK id;

	@Column(name="ID_MRHRC_FNC", precision=22)
	private BigDecimal idMrhrcFnc;

	@Column(name="ID_MRHRC_LV_PRNT", precision=22)
	private BigDecimal idMrhrcLvPrnt;

	@Column(name="NM_MRHRC_LV", length=120)
	private String nmMrhrcLv;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkMrchLvl() {
	}

	public RisplDkMrchLvlPK getId() {
		return this.id;
	}

	public void setId(RisplDkMrchLvlPK id) {
		this.id = id;
	}

	public BigDecimal getIdMrhrcFnc() {
		return this.idMrhrcFnc;
	}

	public void setIdMrhrcFnc(BigDecimal idMrhrcFnc) {
		this.idMrhrcFnc = idMrhrcFnc;
	}

	public BigDecimal getIdMrhrcLvPrnt() {
		return this.idMrhrcLvPrnt;
	}

	public void setIdMrhrcLvPrnt(BigDecimal idMrhrcLvPrnt) {
		this.idMrhrcLvPrnt = idMrhrcLvPrnt;
	}

	public String getNmMrhrcLv() {
		return this.nmMrhrcLv;
	}

	public void setNmMrhrcLv(String nmMrhrcLv) {
		this.nmMrhrcLv = nmMrhrcLv;
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