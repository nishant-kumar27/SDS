package rispl.dkart.services.entities.transaction;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_RSN_CDS database table.
 * 
 */
@Entity
@Table(name="RISPL_EXICE_TAX_ITM")
@NamedQuery(name="RisplDkExTax.findAll", query="SELECT r FROM ExciseTaxItem r")
public class ExciseTaxItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ITEM_ID")
	private String itemId;

	@Column(name="EXICE")
	private String excise;

	@Column(name="TAX")
	private String tax;



	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getExcise() {
		return excise;
	}

	public void setExcise(String excise) {
		this.excise = excise;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public ExciseTaxItem() {
	}

}
