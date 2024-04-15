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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saideep
 */
@Entity
@Table(name = "RISPL_DK_CUST_SITE_PHN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerSitePhone.findAll", query = "SELECT c FROM CustomerSitePhone c"),
    @NamedQuery(name = "CustomerSitePhone.findByCustPhnId", query = "SELECT c FROM CustomerSitePhone c WHERE c.custPhnId = :custPhnId"),
    @NamedQuery(name = "CustomerSitePhone.findByCcCnct", query = "SELECT c FROM CustomerSitePhone c WHERE c.ccCnct = :ccCnct"),
    @NamedQuery(name = "CustomerSitePhone.findByTaCnct", query = "SELECT c FROM CustomerSitePhone c WHERE c.taCnct = :taCnct"),
    @NamedQuery(name = "CustomerSitePhone.findByTlCnct", query = "SELECT c FROM CustomerSitePhone c WHERE c.tlCnct = :tlCnct")})
public class CustomerSitePhone implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUST_PHN_ID")
    private BigDecimal custPhnId;
    @Size(max = 30)
    @Column(name = "CC_CNCT")
    private String ccCnct;
    @Size(max = 3)
    @Column(name = "TA_CNCT")
    private String taCnct;
    @Size(max = 30)
    @Column(name = "TL_CNCT")
    private String tlCnct;
    @JoinColumns({
        @JoinColumn(name = "CUST_SITE_ID", referencedColumnName = "CUST_SITE_ID"),
        @JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID")})
    @ManyToOne(optional = false)
    private CustomerSite customerSite;

    public CustomerSitePhone() {
    }

    public CustomerSitePhone(BigDecimal custPhnId) {
        this.custPhnId = custPhnId;
    }

    public BigDecimal getCustPhnId() {
        return custPhnId;
    }

    public void setCustPhnId(BigDecimal custPhnId) {
        this.custPhnId = custPhnId;
    }

    public String getCcCnct() {
        return ccCnct;
    }

    public void setCcCnct(String ccCnct) {
        this.ccCnct = ccCnct;
    }

    public String getTaCnct() {
        return taCnct;
    }

    public void setTaCnct(String taCnct) {
        this.taCnct = taCnct;
    }

    public String getTlCnct() {
        return tlCnct;
    }

    public void setTlCnct(String tlCnct) {
        this.tlCnct = tlCnct;
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
        hash += (custPhnId != null ? custPhnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerSitePhone)) {
            return false;
        }
        CustomerSitePhone other = (CustomerSitePhone) object;
        if ((this.custPhnId == null && other.custPhnId != null) || (this.custPhnId != null && !this.custPhnId.equals(other.custPhnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerSitePhone[ custPhnId=" + custPhnId + " ]";
    }
    
}
