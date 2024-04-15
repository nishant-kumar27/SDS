package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_MRCH_HIER database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_MRCH_HIER")
@NamedQuery(name="RisplDkMrchHier.findAll", query="SELECT r FROM RisplDkMrchHier r")
public class RisplDkMrchHier implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkMrchHierPK id;

	@Column(name="NM_MRHRC_FNC", length=250)
	private String nmMrhrcFnc;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkMrchHier() {
	}

	public RisplDkMrchHierPK getId() {
		return this.id;
	}

	public void setId(RisplDkMrchHierPK id) {
		this.id = id;
	}

	public String getNmMrhrcFnc() {
		return this.nmMrhrcFnc;
	}

	public void setNmMrhrcFnc(String nmMrhrcFnc) {
		this.nmMrhrcFnc = nmMrhrcFnc;
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