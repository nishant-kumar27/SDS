package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_STRGP_ASCTN database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_STRGP_ASCTN")
@NamedQuery(name="RisplDkStrgpAsctn.findAll", query="SELECT r FROM RisplDkStrgpAsctn r")
public class RisplDkStrgpAsctn implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkStrgpAsctnPK id;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkStrgpAsctn() {
	}

	public RisplDkStrgpAsctnPK getId() {
		return this.id;
	}

	public void setId(RisplDkStrgpAsctnPK id) {
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