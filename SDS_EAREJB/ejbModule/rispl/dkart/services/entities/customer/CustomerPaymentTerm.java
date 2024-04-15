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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "RISPL_DK_CUST_PYMT_TRMS")
@XmlRootElement
public class CustomerPaymentTerm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "CUST_ID")
    private String custId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PYMY_PCNT")
    private String pymyPcnt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAY_IN")
    private BigInteger payIn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOLERANCE")
    private BigInteger tolerance;

    public CustomerPaymentTerm() {
    }

    public CustomerPaymentTerm(String custId) {
        this.custId = custId;
    }

    public CustomerPaymentTerm(String custId, String pymyPcnt, BigInteger payIn, BigInteger tolerance) {
        this.custId = custId;
        this.pymyPcnt = pymyPcnt;
        this.payIn = payIn;
        this.tolerance = tolerance;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getPymyPcnt() {
        return pymyPcnt;
    }

    public void setPymyPcnt(String pymyPcnt) {
        this.pymyPcnt = pymyPcnt;
    }

    public BigInteger getPayIn() {
        return payIn;
    }

    public void setPayIn(BigInteger payIn) {
        this.payIn = payIn;
    }

    public BigInteger getTolerance() {
        return tolerance;
    }

    public void setTolerance(BigInteger tolerance) {
        this.tolerance = tolerance;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custId != null ? custId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerPaymentTerm)) {
            return false;
        }
        CustomerPaymentTerm other = (CustomerPaymentTerm) object;
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerPaymentTerm[ custId=" + custId + " ]";
    }
    
}
