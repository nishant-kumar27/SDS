package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_SHPMNT_MTHD database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_SHPMNT_MTHD")
@NamedQuery(name="RisplShippmentMethodEntity.findAll", query="SELECT r FROM RisplShippmentMethodEntity r")
public class RisplShippmentMethodEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplShippmentMethodEntityPK id;

	@Column(name="CARRIER_NAME")
	private String carrierName;

	@Column(name="ROUTE_STATUS")
	private String routeStatus;
	
	@Column(name="CARRIER_PRICE")
	private String carrierPrice;

	public RisplShippmentMethodEntity() {
	}

	public RisplShippmentMethodEntityPK getId() {
		return this.id;
	}

	public void setId(RisplShippmentMethodEntityPK id) {
		this.id = id;
	}

	public String getCarrierName() {
		return this.carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getRouteStatus() {
		return this.routeStatus;
	}

	public void setRouteStatus(String routeStatus) {
		this.routeStatus = routeStatus;
	}
	
	public String getCarrierPrice() {
		return this.carrierPrice;
	}

	public void setCarrierPrice(String carrierPrice) {
		this.carrierPrice = carrierPrice;
	}

}