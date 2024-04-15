package rispl.db.model.tax;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_GEO_CODE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_GEO_CODE")
@NamedQuery(name="RisplDkGeoCode.findAll", query="SELECT r FROM RisplDkGeoCode r")
public class RisplDkGeoCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="GEO_CDE_ID")
	private String geoCdeId;

	@Column(name="TAX_JUR_NME")
	private String taxJurNme;

	public RisplDkGeoCode() {
	}

	public String getGeoCdeId() {
		return this.geoCdeId;
	}

	public void setGeoCdeId(String geoCdeId) {
		this.geoCdeId = geoCdeId;
	}

	public String getTaxJurNme() {
		return this.taxJurNme;
	}

	public void setTaxJurNme(String taxJurNme) {
		this.taxJurNme = taxJurNme;
	}

}