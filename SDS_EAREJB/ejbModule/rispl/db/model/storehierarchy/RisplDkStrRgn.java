package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_STR_RGN database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_STR_RGN")
@NamedQuery(name="RisplDkStrRgn.findAll", query="SELECT r FROM RisplDkStrRgn r")
public class RisplDkStrRgn implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkStrRgnPK id;

	@Column(name="NM_STR_RGN")
	private String nmStrRgn;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkStrRgn() {
	}

	public RisplDkStrRgnPK getId() {
		return this.id;
	}

	public void setId(RisplDkStrRgnPK id) {
		this.id = id;
	}

	public String getNmStrRgn() {
		return this.nmStrRgn;
	}

	public void setNmStrRgn(String nmStrRgn) {
		this.nmStrRgn = nmStrRgn;
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