package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_STRGP_FNC database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_STRGP_FNC")
@NamedQuery(name="RisplDkStrgpFnc.findAll", query="SELECT r FROM RisplDkStrgpFnc r")
public class RisplDkStrgpFnc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkStrgpFncPK id;

	@Column(name="NM_STRGP_FNC")
	private String nmStrgpFnc;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkStrgpFnc() {
	}

	public RisplDkStrgpFncPK getId() {
		return this.id;
	}

	public void setId(RisplDkStrgpFncPK id) {
		this.id = id;
	}

	public String getNmStrgpFnc() {
		return this.nmStrgpFnc;
	}

	public void setNmStrgpFnc(String nmStrgpFnc) {
		this.nmStrgpFnc = nmStrgpFnc;
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