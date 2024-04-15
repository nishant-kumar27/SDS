package rispl.db.model.tax;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_TAX_JURS_AUTH database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_TAX_JURS_AUTH")
@NamedQuery(name="RisplDkTaxJursAuth.findAll", query="SELECT r FROM RisplDkTaxJursAuth r")
public class RisplDkTaxJursAuth implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkTaxJursAuthPK id;

	public RisplDkTaxJursAuth() {
	}

	public RisplDkTaxJursAuthPK getId() {
		return this.id;
	}

	public void setId(RisplDkTaxJursAuthPK id) {
		this.id = id;
	}

}