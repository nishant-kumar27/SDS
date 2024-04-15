package rispl.dkart.order.shipped;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ORD_INV_SHP_QTY_DK database table.
 * 
 */
@Embeddable
public class OrderShipmentDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_ORD")
	private String orderId;

	@Column(name="ITEM_ID")
	private String itemId;

	@Column(name="DK_WMS_BTCH")
	private String wmsBatchId;

	@Column(name="SHP_QTY")
	private long shippedQty;

	public OrderShipmentDetailPK() {
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getItemId() {
		return this.itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getWmsBatchId() {
		return this.wmsBatchId;
	}
	public void setWmsBatchId(String wmsBatchId) {
		this.wmsBatchId = wmsBatchId;
	}
	public long getShippedQty() {
		return this.shippedQty;
	}
	public void setShippedQty(long shippedQty) {
		this.shippedQty = shippedQty;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderShipmentDetailPK)) {
			return false;
		}
		OrderShipmentDetailPK castOther = (OrderShipmentDetailPK)other;
		return 
			this.orderId.equals(castOther.orderId)
			&& this.itemId.equals(castOther.itemId)
			&& this.wmsBatchId.equals(castOther.wmsBatchId)
			&& (this.shippedQty == castOther.shippedQty);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId.hashCode();
		hash = hash * prime + this.itemId.hashCode();
		hash = hash * prime + this.wmsBatchId.hashCode();
		hash = hash * prime + ((int) (this.shippedQty ^ (this.shippedQty >>> 32)));
		
		return hash;
	}
}