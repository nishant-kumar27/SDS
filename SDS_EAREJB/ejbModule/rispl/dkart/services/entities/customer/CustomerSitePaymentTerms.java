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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "RISPL_DK_CUST_SITE_PYMT_TRMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerSitePaymentTerms.findAll", query = "SELECT c FROM CustomerSitePaymentTerms c"),
    @NamedQuery(name = "CustomerSitePaymentTerms.findByCustSiteId", query = "SELECT c FROM CustomerSitePaymentTerms c WHERE c.customerSitePaymentTermsPK.custSiteId = :custSiteId"),
    @NamedQuery(name = "CustomerSitePaymentTerms.findByCustId", query = "SELECT c FROM CustomerSitePaymentTerms c WHERE c.customerSitePaymentTermsPK.custId = :custId"),
    @NamedQuery(name = "CustomerSitePaymentTerms.findByPymtPcnt", query = "SELECT c FROM CustomerSitePaymentTerms c WHERE c.pymtPcnt = :pymtPcnt"),
    @NamedQuery(name = "CustomerSitePaymentTerms.findByPayIn", query = "SELECT c FROM CustomerSitePaymentTerms c WHERE c.payIn = :payIn"),
    @NamedQuery(name = "CustomerSitePaymentTerms.findByTolerance", query = "SELECT c FROM CustomerSitePaymentTerms c WHERE c.tolerance = :tolerance")})
public class CustomerSitePaymentTerms implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomerSitePaymentTermsPK customerSitePaymentTermsPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PYMT_PCNT")
    private String pymtPcnt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAY_IN")
    private BigInteger payIn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOLERANCE")
    private BigInteger tolerance;
    @JoinColumns({
        @JoinColumn(name = "CUST_SITE_ID", referencedColumnName = "CUST_SITE_ID", insertable = false, updatable = false),
        @JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private CustomerSite customerSite;

    public CustomerSitePaymentTerms() {
    }

    public CustomerSitePaymentTerms(CustomerSitePaymentTermsPK customerSitePaymentTermsPK) {
        this.customerSitePaymentTermsPK = customerSitePaymentTermsPK;
    }

    public CustomerSitePaymentTerms(CustomerSitePaymentTermsPK customerSitePaymentTermsPK, String pymtPcnt, BigInteger payIn, BigInteger tolerance) {
        this.customerSitePaymentTermsPK = customerSitePaymentTermsPK;
        this.pymtPcnt = pymtPcnt;
        this.payIn = payIn;
        this.tolerance = tolerance;
    }

    public CustomerSitePaymentTerms(BigInteger custSiteId, String custId) {
        this.customerSitePaymentTermsPK = new CustomerSitePaymentTermsPK(custSiteId, custId);
    }

    public CustomerSitePaymentTermsPK getCustomerSitePaymentTermsPK() {
        return customerSitePaymentTermsPK;
    }

    public void setCustomerSitePaymentTermsPK(CustomerSitePaymentTermsPK customerSitePaymentTermsPK) {
        this.customerSitePaymentTermsPK = customerSitePaymentTermsPK;
    }

    public String getPymtPcnt() {
        return pymtPcnt;
    }

    public void setPymtPcnt(String pymtPcnt) {
        this.pymtPcnt = pymtPcnt;
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

    public CustomerSite getCustomerSite() {
        return customerSite;
    }

    public void setCustomerSite(CustomerSite customerSite) {
        this.customerSite = customerSite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerSitePaymentTermsPK != null ? customerSitePaymentTermsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerSitePaymentTerms)) {
            return false;
        }
        CustomerSitePaymentTerms other = (CustomerSitePaymentTerms) object;
        if ((this.customerSitePaymentTermsPK == null && other.customerSitePaymentTermsPK != null) || (this.customerSitePaymentTermsPK != null && !this.customerSitePaymentTermsPK.equals(other.customerSitePaymentTermsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerSitePaymentTerms[ customerSitePaymentTermsPK=" + customerSitePaymentTermsPK + " ]";
    }
    
}
