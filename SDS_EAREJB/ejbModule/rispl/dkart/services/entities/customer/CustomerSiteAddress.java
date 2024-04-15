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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "RISPL_DK_CUST_SITE_ADD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerSiteAddress.findAll", query = "SELECT c FROM CustomerSiteAddress c"),
    @NamedQuery(name = "CustomerSiteAddress.findByAdsId", query = "SELECT c FROM CustomerSiteAddress c WHERE c.customerSiteAddressPK.adsId = :adsId"),
    @NamedQuery(name = "CustomerSiteAddress.findByCustSiteId", query = "SELECT c FROM CustomerSiteAddress c WHERE c.customerSiteAddressPK.custSiteId = :custSiteId"),
    @NamedQuery(name = "CustomerSiteAddress.findByCustId", query = "SELECT c FROM CustomerSiteAddress c WHERE c.customerSiteAddressPK.custId = :custId"),
    @NamedQuery(name = "CustomerSiteAddress.findByTyAds", query = "SELECT c FROM CustomerSiteAddress c WHERE c.tyAds = :tyAds"),
    @NamedQuery(name = "CustomerSiteAddress.findByA1Cnct", query = "SELECT c FROM CustomerSiteAddress c WHERE c.a1Cnct = :a1Cnct"),
    @NamedQuery(name = "CustomerSiteAddress.findByA2Cnct", query = "SELECT c FROM CustomerSiteAddress c WHERE c.a2Cnct = :a2Cnct"),
    @NamedQuery(name = "CustomerSiteAddress.findByA3Cnct", query = "SELECT c FROM CustomerSiteAddress c WHERE c.a3Cnct = :a3Cnct"),
    @NamedQuery(name = "CustomerSiteAddress.findByA4Cnct", query = "SELECT c FROM CustomerSiteAddress c WHERE c.a4Cnct = :a4Cnct"),
    @NamedQuery(name = "CustomerSiteAddress.findByCiCnct", query = "SELECT c FROM CustomerSiteAddress c WHERE c.ciCnct = :ciCnct"),
    @NamedQuery(name = "CustomerSiteAddress.findByStCnct", query = "SELECT c FROM CustomerSiteAddress c WHERE c.stCnct = :stCnct"),
    @NamedQuery(name = "CustomerSiteAddress.findByPcCnct", query = "SELECT c FROM CustomerSiteAddress c WHERE c.pcCnct = :pcCnct"),
    @NamedQuery(name = "CustomerSiteAddress.findByTeCnct", query = "SELECT c FROM CustomerSiteAddress c WHERE c.teCnct = :teCnct"),
    @NamedQuery(name = "CustomerSiteAddress.findByCoCnct", query = "SELECT c FROM CustomerSiteAddress c WHERE c.coCnct = :coCnct")})
public class CustomerSiteAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomerSiteAddressPK customerSiteAddressPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADS_ID")
    private String adsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TY_ADS")
    private char tyAds;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "A1_CNCT")
    private String a1Cnct;
    @Size(max = 240)
    @Column(name = "A2_CNCT")
    private String a2Cnct;
    @Size(max = 240)
    @Column(name = "A3_CNCT")
    private String a3Cnct;
    @Size(max = 240)
    @Column(name = "A4_CNCT")
    private String a4Cnct;
    @Size(max = 120)
    @Column(name = "CI_CNCT")
    private String ciCnct;
    @Size(max = 30)
    @Column(name = "ST_CNCT")
    private String stCnct;
    @Size(max = 30)
    @Column(name = "PC_CNCT")
    private String pcCnct;
    @Size(max = 120)
    @Column(name = "TE_CNCT")
    private String teCnct;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CO_CNCT")
    private String coCnct;
    @JoinColumns({
        @JoinColumn(name = "CUST_SITE_ID", referencedColumnName = "CUST_SITE_ID", insertable = false, updatable = false),
        @JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CustomerSite customerSite;

    public CustomerSiteAddress() {
    }

    public CustomerSiteAddress(CustomerSiteAddressPK customerSiteAddressPK) {
        this.customerSiteAddressPK = customerSiteAddressPK;
    }

    public CustomerSiteAddress(CustomerSiteAddressPK customerSiteAddressPK, char tyAds, String a1Cnct, String coCnct) {
        this.customerSiteAddressPK = customerSiteAddressPK;
        this.tyAds = tyAds;
        this.a1Cnct = a1Cnct;
        this.coCnct = coCnct;
    }

    public CustomerSiteAddress(BigInteger adsId, BigInteger custSiteId, String custId) {
        this.customerSiteAddressPK = new CustomerSiteAddressPK(adsId, custSiteId, custId);
    }

    public CustomerSiteAddressPK getCustomerSiteAddressPK() {
        return customerSiteAddressPK;
    }

    public void setCustomerSiteAddressPK(CustomerSiteAddressPK customerSiteAddressPK) {
        this.customerSiteAddressPK = customerSiteAddressPK;
    }

    public char getTyAds() {
        return tyAds;
    }

    public void setTyAds(char tyAds) {
        this.tyAds = tyAds;
    }
    public String getAdsId() {
        return adsId;
    }

    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }
    
    public String getA1Cnct() {
        return a1Cnct;
    }

    public void setA1Cnct(String a1Cnct) {
        this.a1Cnct = a1Cnct;
    }

    public String getA2Cnct() {
        return a2Cnct;
    }

    public void setA2Cnct(String a2Cnct) {
        this.a2Cnct = a2Cnct;
    }

    public String getA3Cnct() {
        return a3Cnct;
    }

    public void setA3Cnct(String a3Cnct) {
        this.a3Cnct = a3Cnct;
    }

    public String getA4Cnct() {
        return a4Cnct;
    }

    public void setA4Cnct(String a4Cnct) {
        this.a4Cnct = a4Cnct;
    }

    public String getCiCnct() {
        return ciCnct;
    }

    public void setCiCnct(String ciCnct) {
        this.ciCnct = ciCnct;
    }

    public String getStCnct() {
        return stCnct;
    }

    public void setStCnct(String stCnct) {
        this.stCnct = stCnct;
    }

    public String getPcCnct() {
        return pcCnct;
    }

    public void setPcCnct(String pcCnct) {
        this.pcCnct = pcCnct;
    }

    public String getTeCnct() {
        return teCnct;
    }

    public void setTeCnct(String teCnct) {
        this.teCnct = teCnct;
    }

    public String getCoCnct() {
        return coCnct;
    }

    public void setCoCnct(String coCnct) {
        this.coCnct = coCnct;
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
        hash += (customerSiteAddressPK != null ? customerSiteAddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerSiteAddress)) {
            return false;
        }
        CustomerSiteAddress other = (CustomerSiteAddress) object;
        if ((this.customerSiteAddressPK == null && other.customerSiteAddressPK != null) || (this.customerSiteAddressPK != null && !this.customerSiteAddressPK.equals(other.customerSiteAddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerSiteAddress[ customerSiteAddressPK=" + customerSiteAddressPK + " ]";
    }
    
}
