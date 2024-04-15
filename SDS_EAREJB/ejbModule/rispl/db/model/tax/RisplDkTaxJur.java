package rispl.db.model.tax;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_TAX_JUR database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_TAX_JUR")
@NamedQuery(name="RisplDkTaxJur.findAll", query="SELECT r FROM RisplDkTaxJur r")
public class RisplDkTaxJur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="GEO_CDE_ID")
	private String geoCdeId;

	@Column(name="POST_CODE")
	private String postCode;

	public RisplDkTaxJur() {
	}

	public String getGeoCdeId() {
		return this.geoCdeId;
	}

	public void setGeoCdeId(String geoCdeId) {
		this.geoCdeId = geoCdeId;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}