package rispl.dkart.services.entities.transaction;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ORD_INV_QTY_SHP database table.
 * 
 */
@Entity
@Table(name="ORD_INV_QTY_SHP")
@NamedQuery(name="OrdInvQtyShp.findAll", query="SELECT o FROM OrdInvQtyShp o")
public class OrdInvQtyShp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdInvQtyShpPK id;

	@Column(name="ITEM_ID")
	private String itemId;

	@Column(name="ORD_LN_ITM_SEQ")
	private BigDecimal ordLnItmSeq;

	@Column(name="SHP_QTY")
	private BigDecimal shpQty;

	public OrdInvQtyShp() {
	}

	public OrdInvQtyShpPK getId() {
		return this.id;
	}

	public void setId(OrdInvQtyShpPK id) {
		this.id = id;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getOrdLnItmSeq() {
		return this.ordLnItmSeq;
	}

	public void setOrdLnItmSeq(BigDecimal ordLnItmSeq) {
		this.ordLnItmSeq = ordLnItmSeq;
	}

	public BigDecimal getShpQty() {
		return this.shpQty;
	}

	public void setShpQty(BigDecimal shpQty) {
		this.shpQty = shpQty;
	}

}