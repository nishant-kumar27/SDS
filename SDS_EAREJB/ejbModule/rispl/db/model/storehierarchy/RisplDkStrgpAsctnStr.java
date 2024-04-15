package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_STRGP_ASCTN_STR database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_STRGP_ASCTN_STR")
@NamedQuery(name="RisplDkStrgpAsctnStr.findAll", query="SELECT r FROM RisplDkStrgpAsctnStr r")
public class RisplDkStrgpAsctnStr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkStrgpAsctnStrPK id;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkStrgpAsctnStr() {
	}

	public RisplDkStrgpAsctnStrPK getId() {
		return this.id;
	}

	public void setId(RisplDkStrgpAsctnStrPK id) {
		this.id = id;
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