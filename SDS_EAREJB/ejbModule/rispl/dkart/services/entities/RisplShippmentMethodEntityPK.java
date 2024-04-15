package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_SHPMNT_MTHD database table.
 * 
 */
@Embeddable
public class RisplShippmentMethodEntityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FACILITY_ID")
	private String facilityId;

	@Column(name="CARRIER_CODE")
	private String carrierCode;

	@Column(name="SERVICE_CODE")
	private String serviceCode;

	private String route;

	public RisplShippmentMethodEntityPK() {
	}
	public String getFacilityId() {
		return this.facilityId;
	}
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	public String getCarrierCode() {
		return this.carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	public String getServiceCode() {
		return this.serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getRoute() {
		return this.route;
	}
	public void setRoute(String route) {
		this.route = route;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplShippmentMethodEntityPK)) {
			return false;
		}
		RisplShippmentMethodEntityPK castOther = (RisplShippmentMethodEntityPK)other;
		return 
			this.facilityId.equals(castOther.facilityId)
			&& this.carrierCode.equals(castOther.carrierCode)
			&& this.serviceCode.equals(castOther.serviceCode)
			&& this.route.equals(castOther.route);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.facilityId.hashCode();
		hash = hash * prime + this.carrierCode.hashCode();
		hash = hash * prime + this.serviceCode.hashCode();
		hash = hash * prime + this.route.hashCode();
		
		return hash;
	}
}