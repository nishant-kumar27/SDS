package rispl.db.model.employee;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the RISPL_DK_EMP_MERCH_ASSOC database table.
 * 
 */
@Entity
@Table(name = "RISPL_DK_EMP_MERCH_ASSOC")
@NamedQuery(name = "RisplDkEmpMerchAssoc.findAll", query = "SELECT r FROM RisplDkEmpMerchAssoc r")
public class RisplDkEmpMerchAssoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkEmpMerchAssocPK id;

	//bi-directional many-to-one association to RisplDkEmpMstr
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID"),
			@JoinColumn(name = "STORE_ID", referencedColumnName = "ID_STR_RT") })
	private RisplDkEmpMstr risplDkEmpMstr;

	public RisplDkEmpMerchAssoc() {
	}

	public RisplDkEmpMerchAssocPK getId() {
		return this.id;
	}

	public void setId(RisplDkEmpMerchAssocPK id) {
		this.id = id;
	}

	public RisplDkEmpMstr getRisplDkEmpMstr() {
		return this.risplDkEmpMstr;
	}

	public void setRisplDkEmpMstr(RisplDkEmpMstr risplDkEmpMstr) {
		this.risplDkEmpMstr = risplDkEmpMstr;
	}

	@Override
	public String toString() {
		return "RisplDkEmpMerchAssoc [id=" + id + ", risplDkEmpMstr=" + risplDkEmpMstr + "]";
	}

}