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
public class CustomerSitePaymentTermsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUST_SITE_ID")
    private BigInteger custSiteId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "CUST_ID")
    private String custId;

    public CustomerSitePaymentTermsPK() {
    }

    public CustomerSitePaymentTermsPK(BigInteger custSiteId, String custId) {
        this.custSiteId = custSiteId;
        this.custId = custId;
    }

    public BigInteger getCustSiteId() {
        return custSiteId;
    }

    public void setCustSiteId(BigInteger custSiteId) {
        this.custSiteId = custSiteId;
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
        hash += (custSiteId != null ? custSiteId.hashCode() : 0);
        hash += (custId != null ? custId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerSitePaymentTermsPK)) {
            return false;
        }
        CustomerSitePaymentTermsPK other = (CustomerSitePaymentTermsPK) object;
        if ((this.custSiteId == null && other.custSiteId != null) || (this.custSiteId != null && !this.custSiteId.equals(other.custSiteId))) {
            return false;
        }
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerSitePaymentTermsPK[ custSiteId=" + custSiteId + ", custId=" + custId + " ]";
    }
    
}
