package rispl.db.model.customer;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_V")
@NamedQuery(name = "CustomerV.findAll", query = "SELECT e FROM CustomerV e")
public class CustomerV implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "DIVISION")
	private String division;

	@Column(name = "SEGMENT")
	private String segment;
	
	@Id
	@Column(name = "CUSTOMER_ID")
	private String customerId;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "LINKED_EMP_ID")
	private String linkedEmpId;

	@Column(name = "CREDIT_LIMIT")
	private BigDecimal creditLimit;

	@Column(name = "AVAIL_CREDIT_LIMIT")
	private BigDecimal availCreditLimit;

	public CustomerV() {
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLinkedEmpId() {
		return linkedEmpId;
	}

	public void setLinkedEmpId(String linkedEmpId) {
		this.linkedEmpId = linkedEmpId;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getAvailCreditLimit() {
		return availCreditLimit;
	}

	public void setAvailCreditLimit(BigDecimal availCreditLimit) {
		this.availCreditLimit = availCreditLimit;
	}

}