package rispl.dkart.order.shipped;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ORD_INV_SHP_QTY_DK database table.
 * 
 */
@Entity
@Table(name="ORD_INV_SHP_QTY_DK")
@NamedQuery(name="OrderShipmentDetail.findAll", query="SELECT o FROM OrderShipmentDetail o")
public class OrderShipmentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderShipmentDetailPK id;

	@Column(name="CONTAINER_STATUS")
	private String containerStatus;

	@Column(name="PICK_QTY")
	private BigDecimal pickQty;

	@Column(name="RTLOGBATCH")
	private String rtlogbatch;

	public OrderShipmentDetail() {
	}

	public OrderShipmentDetailPK getId() {
		return this.id;
	}

	public void setId(OrderShipmentDetailPK id) {
		this.id = id;
	}

	public String getContainerStatus() {
		return this.containerStatus;
	}

	public void setContainerStatus(String containerStatus) {
		this.containerStatus = containerStatus;
	}

	public BigDecimal getPickQty() {
		return this.pickQty;
	}

	public void setPickQty(BigDecimal pickQty) {
		this.pickQty = pickQty;
	}

	public String getRtlogbatch() {
		return this.rtlogbatch;
	}

	public void setRtlogbatch(String rtlogbatch) {
		this.rtlogbatch = rtlogbatch;
	}

}