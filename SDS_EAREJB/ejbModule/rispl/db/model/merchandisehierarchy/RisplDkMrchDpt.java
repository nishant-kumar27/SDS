package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_MRCH_DPT database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_MRCH_DPT")
@NamedQuery(name="RisplDkMrchDpt.findAll", query="SELECT r FROM RisplDkMrchDpt r")
public class RisplDkMrchDpt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkMrchDptPK id;

	@Column(name="ID_DPT_POS_PRNT", length=20)
	private String idDptPosPrnt;

	@Column(name="ID_GP_TX", length=20)
	private String idGpTx;

	@Column(name="NM_DPT_POS", length=20)
	private String nmDptPos;

	public RisplDkMrchDpt() {
	}

	public RisplDkMrchDptPK getId() {
		return this.id;
	}

	public void setId(RisplDkMrchDptPK id) {
		this.id = id;
	}

	public String getIdDptPosPrnt() {
		return this.idDptPosPrnt;
	}

	public void setIdDptPosPrnt(String idDptPosPrnt) {
		this.idDptPosPrnt = idDptPosPrnt;
	}

	public String getIdGpTx() {
		return this.idGpTx;
	}

	public void setIdGpTx(String idGpTx) {
		this.idGpTx = idGpTx;
	}

	public String getNmDptPos() {
		return this.nmDptPos;
	}

	public void setNmDptPos(String nmDptPos) {
		this.nmDptPos = nmDptPos;
	}

}