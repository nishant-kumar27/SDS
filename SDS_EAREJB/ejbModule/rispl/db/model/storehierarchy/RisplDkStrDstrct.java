package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_STR_DSTRCT database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_STR_DSTRCT")
@NamedQuery(name="RisplDkStrDstrct.findAll", query="SELECT r FROM RisplDkStrDstrct r")
public class RisplDkStrDstrct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkStrDstrctPK id;

	@Column(name="ID_STR_RGN")
	private String idStrRgn;

	@Column(name="NM_STR_DSTRCT")
	private String nmStrDstrct;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkStrDstrct() {
	}

	public RisplDkStrDstrctPK getId() {
		return this.id;
	}

	public void setId(RisplDkStrDstrctPK id) {
		this.id = id;
	}

	public String getIdStrRgn() {
		return this.idStrRgn;
	}

	public void setIdStrRgn(String idStrRgn) {
		this.idStrRgn = idStrRgn;
	}

	public String getNmStrDstrct() {
		return this.nmStrDstrct;
	}

	public void setNmStrDstrct(String nmStrDstrct) {
		this.nmStrDstrct = nmStrDstrct;
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