package rispl.db.model.tax;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_TAX_TYPE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_TAX_TYPE")
@NamedQuery(name="RisplDkTaxType.findAll", query="SELECT r FROM RisplDkTaxType r")
public class RisplDkTaxType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TAX_TYP_ID", unique=true, nullable=false, precision=22)
	private long taxTypId;

	@Column(name="NM_TY_TX", length=30)
	private String nmTyTx;

	public RisplDkTaxType() {
	}

	public long getTaxTypId() {
		return this.taxTypId;
	}

	public void setTaxTypId(long taxTypId) {
		this.taxTypId = taxTypId;
	}

	public String getNmTyTx() {
		return this.nmTyTx;
	}

	public void setNmTyTx(String nmTyTx) {
		this.nmTyTx = nmTyTx;
	}

}