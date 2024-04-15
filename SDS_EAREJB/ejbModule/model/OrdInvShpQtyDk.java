package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ORD_INV_SHP_QTY_DK database table.
 * 
 */
@Entity
@Table(name="ORD_INV_SHP_QTY_DK")
@NamedQuery(name="OrdInvShpQtyDk.findAll", query="SELECT o FROM OrdInvShpQtyDk o")
public class OrdInvShpQtyDk implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdInvShpQtyDkPK id;

	private String rtlogbatch;

	public OrdInvShpQtyDk() {
	}

	public OrdInvShpQtyDkPK getId() {
		return this.id;
	}

	public void setId(OrdInvShpQtyDkPK id) {
		this.id = id;
	}

	public String getRtlogbatch() {
		return this.rtlogbatch;
	}

	public void setRtlogbatch(String rtlogbatch) {
		this.rtlogbatch = rtlogbatch;
	}

}