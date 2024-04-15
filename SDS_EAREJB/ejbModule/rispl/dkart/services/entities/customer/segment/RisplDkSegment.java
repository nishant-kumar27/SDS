package rispl.dkart.services.entities.customer.segment;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_SEGMENT database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_SEGMENT")
@NamedQuery(name="RisplDkSegment.findAll", query="SELECT r FROM RisplDkSegment r")
public class RisplDkSegment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SEGMENT_DESCRIPTION")
	private String segmentDescription;

	@Column(name="CREATED_BY_USER_ID")
	private BigDecimal createdByUserId;

	@Column(name="CREATED_DATETIME")
	private Timestamp createdDatetime;

	@Column(name="EFFECTIVE_DATETIME")
	private Timestamp effectiveDatetime;

	@Column(name="END_DATETIME")
	private Timestamp endDatetime;

	@Column(name="SEGMENT_ID")
	private String segmentId;

	public RisplDkSegment() {
	}

	public String getSegmentDescription() {
		return this.segmentDescription;
	}

	public void setSegmentDescription(String segmentDescription) {
		this.segmentDescription = segmentDescription;
	}

	public BigDecimal getCreatedByUserId() {
		return this.createdByUserId;
	}

	public void setCreatedByUserId(BigDecimal createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Timestamp getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getEffectiveDatetime() {
		return this.effectiveDatetime;
	}

	public void setEffectiveDatetime(Timestamp effectiveDatetime) {
		this.effectiveDatetime = effectiveDatetime;
	}

	public Timestamp getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getSegmentId() {
		return this.segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

}