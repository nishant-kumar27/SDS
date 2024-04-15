package rispl.db.model.claim;

import java.io.Serializable;
import javax.persistence.*;

import rispl.dkart.claim.lookup.dao.ClaimStatus;

import java.math.BigDecimal;


/**
 * The persistent class for the CLAIM_DETAILS database table.
 * 
 */
@Entity
@Table(name="CLAIM_DETAILS")
@Cacheable(false)
@NamedQuery(name="ClaimDetail.findAll", query="SELECT c FROM ClaimDetail c")
@NamedQueries({
    @NamedQuery(name="ClaimDetail.findAll", query="SELECT c FROM ClaimDetail c"),
    @NamedQuery(name="ClaimDetail.findByStatus",
                query="SELECT c FROM ClaimDetail c WHERE c.status = :status ORDER BY c.id.claimDate DESC"),
}) 
public class ClaimDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClaimDetailPK id;

	@Column(name="CLAIM_TOTAL")
	private BigDecimal claimTotal;

	@Column(name="CUSTOMER_ID")
	private String customerId;

	@Column(name="CUSTOMER_NAME")
	private String customerName;

	@Column(name="EMP_ID")
	private String empId;

	@Column(name="EMP_NAME")
	private String empName;

	@Column(name="REASON_CODE")
	private String reasonCode;
	
	@Column(name="TOTAL_QUANTITY_RETURN")
	private String totalQuantityReturn;

	@Column(name="DIVISION_ID")
	private BigDecimal divisionId;
	
	private BigDecimal status;
	
	@Transient
	private String StatusName;
	
	public ClaimDetail() {
	}



	public BigDecimal getClaimTotal() {
		return this.claimTotal;
	}

	public void setClaimTotal(BigDecimal claimTotal) {
		this.claimTotal = claimTotal;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getReasonCode() {
		return this.reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}



	public ClaimDetailPK getId() {
		return id;
	}



	public void setId(ClaimDetailPK id) {
		this.id = id;
	}



	public String getStatusName() {
		if(status.compareTo(new BigDecimal(1))<=0)
			StatusName=ClaimStatus.REGISTERED;
		else if(status.compareTo(new BigDecimal(2))<=0)
			StatusName=ClaimStatus.APPROVED;
		else if(status.compareTo(new BigDecimal(4))<=0)
			StatusName=ClaimStatus.IN_PROGRESS;
		else if(status.compareTo(new BigDecimal(5))<=0)
			StatusName=ClaimStatus.ACCEPTED;
		else if(status.compareTo(new BigDecimal(6))==0)
			StatusName=ClaimStatus.REJECTED;
		else 
			StatusName="Unknown";
		return StatusName;
	}



	public void setStatusName(String statusName) {
		StatusName = statusName;
	}



	public String getTotalQuantityReturn() {
		return totalQuantityReturn;
	}

	

	public BigDecimal getDivisionId() {
		return divisionId;
	}



	public void setDivisionId(BigDecimal divisionId) {
		this.divisionId = divisionId;
	}



	public void setTotalQuantityReturn(String totalQuantityReturn) {
		this.totalQuantityReturn = totalQuantityReturn;
	}

	
	
}