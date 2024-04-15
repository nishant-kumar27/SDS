package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_MRCH_GRP database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_MRCH_GRP")
@NamedQuery(name="RisplDkMrchGrp.findAll", query="SELECT r FROM RisplDkMrchGrp r")
public class RisplDkMrchGrp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkMrchGrpPK id;

	@Column(name="DE_MRHRC_GP")
	private String deMrhrcGp;

	@Column(name="ID_PST")
	private BigDecimal idPst;

	@Column(name="NM_MRHRC_GP")
	private String nmMrhrcGp;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkMrchGrp() {
	}

	public RisplDkMrchGrpPK getId() {
		return this.id;
	}

	public void setId(RisplDkMrchGrpPK id) {
		this.id = id;
	}

	public String getDeMrhrcGp() {
		return this.deMrhrcGp;
	}

	public void setDeMrhrcGp(String deMrhrcGp) {
		this.deMrhrcGp = deMrhrcGp;
	}

	public BigDecimal getIdPst() {
		return this.idPst;
	}

	public void setIdPst(BigDecimal idPst) {
		this.idPst = idPst;
	}

	public String getNmMrhrcGp() {
		return this.nmMrhrcGp;
	}

	public void setNmMrhrcGp(String nmMrhrcGp) {
		this.nmMrhrcGp = nmMrhrcGp;
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