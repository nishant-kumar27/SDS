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
@Table(name = "RISPL_DK_CUST_SITE_STR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerSiteStore.findAll", query = "SELECT c FROM CustomerSiteStore c"),
    @NamedQuery(name = "CustomerSiteStore.findById", query = "SELECT c FROM CustomerSiteStore c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerSiteStore.findByStoreId", query = "SELECT c FROM CustomerSiteStore c WHERE c.storeId = :storeId")})
public class CustomerSiteStore implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 5)
    @Column(name = "STORE_ID")
    private String storeId;
    @JoinColumns({
        @JoinColumn(name = "CUST_SITE_ID", referencedColumnName = "CUST_SITE_ID"),
        @JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID")})
    @ManyToOne(optional = false)
    private CustomerSite customerSite;

    public CustomerSiteStore() {
    }

    public CustomerSiteStore(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerSiteStore)) {
            return false;
        }
        CustomerSiteStore other = (CustomerSiteStore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerSiteStore[ id=" + id + " ]";
    }
    
}
