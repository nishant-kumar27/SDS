/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rispl.dkart.services.entities.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saideep
 */
@Entity
@Table(name = "RISPL_DK_CUST_SITE_LMT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerSiteLimit.findAll", query = "SELECT c FROM CustomerSiteLimit c"),
    @NamedQuery(name = "CustomerSiteLimit.findByLmtId", query = "SELECT c FROM CustomerSiteLimit c WHERE c.lmtId = :lmtId"),
    @NamedQuery(name = "CustomerSiteLimit.findBySiteStatus", query = "SELECT c FROM CustomerSiteLimit c WHERE c.siteStatus = :siteStatus"),
    @NamedQuery(name = "CustomerSiteLimit.findBySiteCrdtLimit", query = "SELECT c FROM CustomerSiteLimit c WHERE c.siteCrdtLimit = :siteCrdtLimit"),
    @NamedQuery(name = "CustomerSiteLimit.findBySiteAvCrdtLimit", query = "SELECT c FROM CustomerSiteLimit c WHERE c.siteAvCrdtLimit = :siteAvCrdtLimit"),
    @NamedQuery(name = "CustomerSiteLimit.findBySiteOrderLimit", query = "SELECT c FROM CustomerSiteLimit c WHERE c.siteOrderLimit = :siteOrderLimit"),
    @NamedQuery(name = "CustomerSiteLimit.findBySitePendDue", query = "SELECT c FROM CustomerSiteLimit c WHERE c.sitePendDue = :sitePendDue")})
public class CustomerSiteLimit implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LMT_ID")
    private BigDecimal lmtId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SITE_STATUS")
    private char siteStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SITE_CRDT_LIMIT")
    private BigDecimal siteCrdtLimit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SITE_AV_CRDT_LIMIT")
    private BigDecimal siteAvCrdtLimit;
    @Column(name = "SITE_ORDER_LIMIT")
    private BigDecimal siteOrderLimit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SITE_PEND_DUE")
    private BigDecimal sitePendDue;
    @JoinColumns({
        @JoinColumn(name = "CUST_SITE_ID", referencedColumnName = "CUST_SITE_ID"),
        @JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID")})
    @ManyToOne(optional = false)
    private CustomerSite customerSite;

    public CustomerSiteLimit() {
    }

    public CustomerSiteLimit(BigDecimal lmtId) {
        this.lmtId = lmtId;
    }

    public CustomerSiteLimit(BigDecimal lmtId, char siteStatus, BigDecimal siteCrdtLimit, BigDecimal siteAvCrdtLimit, BigDecimal sitePendDue) {
        this.lmtId = lmtId;
        this.siteStatus = siteStatus;
        this.siteCrdtLimit = siteCrdtLimit;
        this.siteAvCrdtLimit = siteAvCrdtLimit;
        this.sitePendDue = sitePendDue;
    }

    public BigDecimal getLmtId() {
        return lmtId;
    }

    public void setLmtId(BigDecimal lmtId) {
        this.lmtId = lmtId;
    }

    public char getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(char siteStatus) {
        this.siteStatus = siteStatus;
    }

    public BigDecimal getSiteCrdtLimit() {
        return siteCrdtLimit;
    }

    public void setSiteCrdtLimit(BigDecimal siteCrdtLimit) {
        this.siteCrdtLimit = siteCrdtLimit;
    }

    public BigDecimal getSiteAvCrdtLimit() {
        return siteAvCrdtLimit;
    }

    public void setSiteAvCrdtLimit(BigDecimal siteAvCrdtLimit) {
        this.siteAvCrdtLimit = siteAvCrdtLimit;
    }

    public BigDecimal getSiteOrderLimit() {
        return siteOrderLimit;
    }

    public void setSiteOrderLimit(BigDecimal siteOrderLimit) {
        this.siteOrderLimit = siteOrderLimit;
    }

    public BigDecimal getSitePendDue() {
        return sitePendDue;
    }

    public void setSitePendDue(BigDecimal sitePendDue) {
        this.sitePendDue = sitePendDue;
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
        hash += (lmtId != null ? lmtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerSiteLimit)) {
            return false;
        }
        CustomerSiteLimit other = (CustomerSiteLimit) object;
        if ((this.lmtId == null && other.lmtId != null) || (this.lmtId != null && !this.lmtId.equals(other.lmtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerSiteLimit[ lmtId=" + lmtId + " ]";
    }
    
}
