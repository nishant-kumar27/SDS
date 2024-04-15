package rispl.db.model.customer;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_CUST_SEG database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_CUST_SEG")
@NamedQuery(name="RisplDkCustSeg.findAll", query="SELECT r FROM RisplDkCustSeg r")
public class RisplDkCustSeg implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkCustSegPK id;

	@Column(name="PRCNG_GRP_DES")
	private String prcngGrpDes;

	@Column(name="PRCNG_GRP_NME")
	private String prcngGrpNme;

	public RisplDkCustSeg() {
	}

	public RisplDkCustSegPK getId() {
		return this.id;
	}

	public void setId(RisplDkCustSegPK id) {
		this.id = id;
	}

	public String getPrcngGrpDes() {
		return this.prcngGrpDes;
	}

	public void setPrcngGrpDes(String prcngGrpDes) {
		this.prcngGrpDes = prcngGrpDes;
	}

	public String getPrcngGrpNme() {
		return this.prcngGrpNme;
	}

	public void setPrcngGrpNme(String prcngGrpNme) {
		this.prcngGrpNme = prcngGrpNme;
	}

}