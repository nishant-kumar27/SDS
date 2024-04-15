package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_MRCH_ASSN database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_MRCH_ASSN")
@NamedQuery(name="RisplDkMrchAssn.findAll", query="SELECT r FROM RisplDkMrchAssn r")
public class RisplDkMrchAssn implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkMrchAssnPK id;

	@Column(name="ID_MRHRC_FNC", precision=22)
	private BigDecimal idMrhrcFnc;

	@Column(name="ID_MRHRC_LV", precision=22)
	private BigDecimal idMrhrcLv;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkMrchAssn() {
	}

	public RisplDkMrchAssnPK getId() {
		return this.id;
	}

	public void setId(RisplDkMrchAssnPK id) {
		this.id = id;
	}

	public BigDecimal getIdMrhrcFnc() {
		return this.idMrhrcFnc;
	}

	public void setIdMrhrcFnc(BigDecimal idMrhrcFnc) {
		this.idMrhrcFnc = idMrhrcFnc;
	}

	public BigDecimal getIdMrhrcLv() {
		return this.idMrhrcLv;
	}

	public void setIdMrhrcLv(BigDecimal idMrhrcLv) {
		this.idMrhrcLv = idMrhrcLv;
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