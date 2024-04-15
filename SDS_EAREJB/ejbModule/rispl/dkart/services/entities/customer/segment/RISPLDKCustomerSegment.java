package rispl.dkart.services.entities.customer.segment;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_CUST_SEGMENT database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_CUST_SEGMENT")
@NamedQuery(name="RISPLDKCustomerSegment.findAll", query="SELECT r FROM RISPLDKCustomerSegment r")
public class RISPLDKCustomerSegment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PARTY_ID")
	private long partyId;

	@Column(name="CUST_ID")
	private String custId;

	@Column(name="SEGMENT_ID")
	private String segmentId;
	
	@Column(name="DIVISION_ID")
	private String divisionId;
	

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public RISPLDKCustomerSegment() {
	}

	public long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getSegmentId() {
		return this.segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

}