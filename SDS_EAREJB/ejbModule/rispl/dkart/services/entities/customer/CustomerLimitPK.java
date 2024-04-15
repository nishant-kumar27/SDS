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

@Embeddable
public class CustomerLimitPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "CUST_LMT_ID")
    private BigInteger custLmtId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "CUST_ID")
    private String custId;

    public CustomerLimitPK() {
    }

    public CustomerLimitPK(BigInteger custLmtId, String custId) {
        this.custLmtId = custLmtId;
        this.custId = custId;
    }

    public BigInteger getCustLmtId() {
        return custLmtId;
    }

    public void setCustLmtId(BigInteger custLmtId) {
        this.custLmtId = custLmtId;
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
        hash += (custLmtId != null ? custLmtId.hashCode() : 0);
        hash += (custId != null ? custId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerLimitPK)) {
            return false;
        }
        CustomerLimitPK other = (CustomerLimitPK) object;
        if ((this.custLmtId == null && other.custLmtId != null) || (this.custLmtId != null && !this.custLmtId.equals(other.custLmtId))) {
            return false;
        }
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerLimitPK[ custLmtId=" + custLmtId + ", custId=" + custId + " ]";
    }
    
}
