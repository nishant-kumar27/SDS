package rispl.dkart.services.entities.customer.segment;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_SEG_STORE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_SEG_STORE")
@NamedQuery(name="RisplDkSegStore.findAll", query="SELECT r FROM RisplDkSegStore r")
public class RisplDkSegStore implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkSegStorePK id;

	private String store;

	public RisplDkSegStore() {
	}

	public RisplDkSegStorePK getId() {
		return this.id;
	}

	public void setId(RisplDkSegStorePK id) {
		this.id = id;
	}

	public String getStore() {
		return this.store;
	}

	public void setStore(String store) {
		this.store = store;
	}

}