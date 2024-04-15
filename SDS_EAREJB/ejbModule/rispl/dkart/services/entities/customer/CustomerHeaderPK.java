/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rispl.dkart.services.entities.customer;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Saideep
 */
@Embeddable
public class CustomerHeaderPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUST_PRTY_ID")
    private BigInteger custPrtyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "CUST_ID")
    private String custId;

    public CustomerHeaderPK() {
    }

    public CustomerHeaderPK(BigInteger custPrtyId, String custId) {
        this.custPrtyId = custPrtyId;
        this.custId = custId;
    }

    public BigInteger getCustPrtyId() {
        return custPrtyId;
    }

    public void setCustPrtyId(BigInteger custPrtyId) {
        this.custPrtyId = custPrtyId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custPrtyId != null ? custPrtyId.hashCode() : 0);
        hash += (custId != null ? custId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerHeaderPK)) {
            return false;
        }
        CustomerHeaderPK other = (CustomerHeaderPK) object;
        if ((this.custPrtyId == null && other.custPrtyId != null) || (this.custPrtyId != null && !this.custPrtyId.equals(other.custPrtyId))) {
            return false;
        }
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "CustomerHeaderPK [custPrtyId=" + custPrtyId + ", custId=" + custId + "]";
	}

    
}
