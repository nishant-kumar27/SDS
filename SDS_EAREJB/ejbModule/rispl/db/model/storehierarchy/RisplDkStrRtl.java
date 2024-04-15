package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_STR_RTL database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_STR_RTL")
@NamedQuery(name="RisplDkStrRtl.findAll", query="SELECT r FROM RisplDkStrRtl r")
public class RisplDkStrRtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkStrRtlPK id;

	@Column(name="ID_CD_GEO")
	private String idCdGeo;

	@Column(name="ID_STR_DSTRCT")
	private String idStrDstrct;

	@Column(name="ID_STR_RGN")
	private String idStrRgn;

	@Column(name="NM_LOC")
	private String nmLoc;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkStrRtl() {
	}

	public RisplDkStrRtlPK getId() {
		return this.id;
	}

	public void setId(RisplDkStrRtlPK id) {
		this.id = id;
	}

	public String getIdCdGeo() {
		return this.idCdGeo;
	}

	public void setIdCdGeo(String idCdGeo) {
		this.idCdGeo = idCdGeo;
	}

	public String getIdStrDstrct() {
		return this.idStrDstrct;
	}

	public void setIdStrDstrct(String idStrDstrct) {
		this.idStrDstrct = idStrDstrct;
	}

	public String getIdStrRgn() {
		return this.idStrRgn;
	}

	public void setIdStrRgn(String idStrRgn) {
		this.idStrRgn = idStrRgn;
	}

	public String getNmLoc() {
		return this.nmLoc;
	}

	public void setNmLoc(String nmLoc) {
		this.nmLoc = nmLoc;
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