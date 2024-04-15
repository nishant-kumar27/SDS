/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rispl.dkart.services.entities.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "RISPL_DK_CUST_SEG")
@XmlRootElement

public class CustomerSegment implements Serializable {
	private static final long serialVersionUID = 1L;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "PRCNG_GRP_ID")
	private BigDecimal prcngGrpId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "LCL")
	private String lcl;
	@Size(max = 120)
	@Column(name = "PRCNG_GRP_NME")
	private String prcngGrpNme;
	@Size(max = 250)
	@Column(name = "PRCNG_GRP_DES")
	private String prcngGrpDes;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "prcngGrpId", fetch = FetchType.EAGER)
	private List<CustomerHeader> customerHeaderList;

	public CustomerSegment() {
	}

	public CustomerSegment(BigDecimal prcngGrpId) {
		this.prcngGrpId = prcngGrpId;
	}

	public CustomerSegment(BigDecimal prcngGrpId, String lcl) {
		this.prcngGrpId = prcngGrpId;
		this.lcl = lcl;
	}

	public BigDecimal getPrcngGrpId() {
		return prcngGrpId;
	}

	public void setPrcngGrpId(BigDecimal prcngGrpId) {
		this.prcngGrpId = prcngGrpId;
	}

	public String getLcl() {
		return lcl;
	}

	public void setLcl(String lcl) {
		this.lcl = lcl;
	}

	public String getPrcngGrpNme() {
		return prcngGrpNme;
	}

	public void setPrcngGrpNme(String prcngGrpNme) {
		this.prcngGrpNme = prcngGrpNme;
	}

	public String getPrcngGrpDes() {
		return prcngGrpDes;
	}

	public void setPrcngGrpDes(String prcngGrpDes) {
		this.prcngGrpDes = prcngGrpDes;
	}

	@XmlTransient
	/*
	 * as we dont need the customer header along with the customer group id
	 * because we are not going to search with price group id
	 */
	public List<CustomerHeader> getCustomerHeaderList() {

		return null;
	}

	public void setCustomerHeaderList(List<CustomerHeader> customerHeaderList) {
		this.customerHeaderList = customerHeaderList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (prcngGrpId != null ? prcngGrpId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CustomerSegment)) {
			return false;
		}
		CustomerSegment other = (CustomerSegment) object;
		if ((this.prcngGrpId == null && other.prcngGrpId != null)
				|| (this.prcngGrpId != null && !this.prcngGrpId.equals(other.prcngGrpId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.CustomerSegment[ prcngGrpId=" + prcngGrpId + " ]";
	}

}
