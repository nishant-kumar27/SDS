
package rispl.dkart.services.entities.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "RISPL_DK_CUST_LMT")
@XmlRootElement

public class CustomerLimit implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomerLimitPK customerLimitPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private char status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CRDT_LIMIT")
    private BigDecimal crdtLimit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AV_CRDT_LIMIT")
    private BigDecimal avCrdtLimit;
    @Column(name = "ORDER_LIMIT")
    private BigDecimal orderLimit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEND_DUE")
    private BigDecimal pendDue;

    public CustomerLimit() {
    }

    public CustomerLimit(CustomerLimitPK customerLimitPK) {
        this.customerLimitPK = customerLimitPK;
    }

    public CustomerLimit(CustomerLimitPK customerLimitPK, char status, BigDecimal crdtLimit, BigDecimal avCrdtLimit, BigDecimal pendDue) {
        this.customerLimitPK = customerLimitPK;
        this.status = status;
        this.crdtLimit = crdtLimit;
        this.avCrdtLimit = avCrdtLimit;
        this.pendDue = pendDue;
    }

    public CustomerLimit(BigInteger custLmtId, String custId) {
        this.customerLimitPK = new CustomerLimitPK(custLmtId, custId);
    }

    public CustomerLimitPK getCustomerLimitPK() {
        return customerLimitPK;
    }

    public void setCustomerLimitPK(CustomerLimitPK customerLimitPK) {
        this.customerLimitPK = customerLimitPK;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public BigDecimal getCrdtLimit() {
        return crdtLimit;
    }

    public void setCrdtLimit(BigDecimal crdtLimit) {
        this.crdtLimit = crdtLimit;
    }

    public BigDecimal getAvCrdtLimit() {
        return avCrdtLimit;
    }

    public void setAvCrdtLimit(BigDecimal avCrdtLimit) {
        this.avCrdtLimit = avCrdtLimit;
    }

    public BigDecimal getOrderLimit() {
        return orderLimit;
    }

    public void setOrderLimit(BigDecimal orderLimit) {
        this.orderLimit = orderLimit;
    }

    public BigDecimal getPendDue() {
        return pendDue;
    }

    public void setPendDue(BigDecimal pendDue) {
        this.pendDue = pendDue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerLimitPK != null ? customerLimitPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerLimit)) {
            return false;
        }
        CustomerLimit other = (CustomerLimit) object;
        if ((this.customerLimitPK == null && other.customerLimitPK != null) || (this.customerLimitPK != null && !this.customerLimitPK.equals(other.customerLimitPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerLimit[ customerLimitPK=" + customerLimitPK + " ]";
    }
    
}
