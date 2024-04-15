package rispl.dkart.services.entities.transaction;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ORD_INV_SHP_QTY_SRLNO database table.
 * 
 */
@Entity
@Table(name="ORD_INV_SHP_QTY_SRLNO")
@NamedQuery(name="OrdInvShpQtySrlno.findAll", query="SELECT o FROM OrdInvShpQtySrlno o")
public class OrdInvShpQtySrlno implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdInvShpQtySrlnoPK id;

	@Column(name="CHILD_ITEM_ID")
	private String childItemId;

	public OrdInvShpQtySrlno() {
	}

	public OrdInvShpQtySrlnoPK getId() {
		return this.id;
	}

	public void setId(OrdInvShpQtySrlnoPK id) {
		this.id = id;
	}

	public String getChildItemId() {
		return this.childItemId;
	}

	public void setChildItemId(String childItemId) {
		this.childItemId = childItemId;
	}

}