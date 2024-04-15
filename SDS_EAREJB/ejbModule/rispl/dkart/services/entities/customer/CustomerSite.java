/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rispl.dkart.services.entities.customer;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Cacheable(value = true)
@Table(name = "RISPL_DK_CUST_SITE")
@XmlRootElement

public class CustomerSite implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected CustomerSitePK customerSitePK;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "TY_CNCT")
	private String tyCnct;
	@Column(name = "PRCGP_ID")
	private BigInteger prcgpId;
	@Size(max = 120)
	@Column(name = "FN_CNCT")
	private String fnCnct;
	@Size(max = 120)
	@Column(name = "MD_CNCT")
	private String mdCnct;
	@Size(max = 120)
	@Column(name = "LN_CNCT")
	private String lnCnct;
	@Size(max = 255)
	@Column(name = "NM_CNCT")
	private String nmCnct;
	@Size(max = 120)
	@Column(name = "LU_CNCT_SLN")
	private String luCnctSln;
	@Size(max = 120)
	@Column(name = "NM_CNCT_SFX")
	private String nmCnctSfx;
	@Size(max = 30)
	@Column(name = "DC_CNCT")
	private String dcCnct;
	@Column(name = "GNDR_CNCT")
	private Character gndrCnct;
	@Size(max = 120)
	@Column(name = "CO_NM_CNCT")
	private String coNmCnct;
	@Size(max = 50)
	@Column(name = "CT_EML_ID")
	private String ctEmlId;
	@Column(name = "NO_PHN_CNCT")
	private Character noPhnCnct;
	@Column(name = "NO_EML_CNCT")
	private Character noEmlCnct;
	@Column(name = "CD_PRF_RCPT_CNCT")
	private Character cdPrfRcptCnct;
	@Size(max = 120)
	@Column(name = "UPR_LN_CNCT")
	private String uprLnCnct;
	@Size(max = 120)
	@Column(name = "UPR_FN_CNCT")
	private String uprFnCnct;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerSite", fetch = FetchType.EAGER)
	private List<CustomerSiteAddress> customerSiteAddressList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerSite", fetch = FetchType.EAGER)
	@OrderBy("arInvDate DESC")
	private List<CustomerSiteInvoice> customerSiteInvoiceList;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customerSite", fetch = FetchType.EAGER)
	private CustomerSitePaymentTerms customerPaymentTerms;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerSite", fetch = FetchType.EAGER)
	private List<CustomerSiteLimit> customerSiteLimitList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerSite", fetch = FetchType.EAGER)
	private List<CustomerSitePhone> customerSitePhoneList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerSite", fetch = FetchType.EAGER)
	private List<CustomerSiteStore> customerSiteStoreList;

	public CustomerSite() {
	}

	public CustomerSite(CustomerSitePK customerSitePK) {
		this.customerSitePK = customerSitePK;
	}

	public CustomerSite(CustomerSitePK customerSitePK, String tyCnct) {
		this.customerSitePK = customerSitePK;
		this.tyCnct = tyCnct;
	}

	public CustomerSite(BigInteger custSiteId, String custId) {
		this.customerSitePK = new CustomerSitePK(custSiteId, custId);
	}

	public CustomerSitePK getCustomerSitePK() {
		return customerSitePK;
	}

	public void setCustomerSitePK(CustomerSitePK customerSitePK) {
		this.customerSitePK = customerSitePK;
	}

	public String getTyCnct() {
		return tyCnct;
	}

	public void setTyCnct(String tyCnct) {
		this.tyCnct = tyCnct;
	}

	public BigInteger getPrcgpId() {
		return prcgpId;
	}

	public void setPrcgpId(BigInteger prcgpId) {
		this.prcgpId = prcgpId;
	}

	public String getFnCnct() {
		return fnCnct;
	}

	public void setFnCnct(String fnCnct) {
		this.fnCnct = fnCnct;
	}

	public String getMdCnct() {
		return mdCnct;
	}

	public void setMdCnct(String mdCnct) {
		this.mdCnct = mdCnct;
	}

	public String getLnCnct() {
		return lnCnct;
	}

	public void setLnCnct(String lnCnct) {
		this.lnCnct = lnCnct;
	}

	public String getNmCnct() {
		return nmCnct;
	}

	public void setNmCnct(String nmCnct) {
		this.nmCnct = nmCnct;
	}

	public String getLuCnctSln() {
		return luCnctSln;
	}

	public void setLuCnctSln(String luCnctSln) {
		this.luCnctSln = luCnctSln;
	}

	public String getNmCnctSfx() {
		return nmCnctSfx;
	}

	public void setNmCnctSfx(String nmCnctSfx) {
		this.nmCnctSfx = nmCnctSfx;
	}

	public String getDcCnct() {
		return dcCnct;
	}

	public void setDcCnct(String dcCnct) {
		this.dcCnct = dcCnct;
	}

	public Character getGndrCnct() {
		return gndrCnct;
	}

	public void setGndrCnct(Character gndrCnct) {
		this.gndrCnct = gndrCnct;
	}

	public String getCoNmCnct() {
		return coNmCnct;
	}

	public void setCoNmCnct(String coNmCnct) {
		this.coNmCnct = coNmCnct;
	}

	public String getCtEmlId() {
		return ctEmlId;
	}

	public void setCtEmlId(String ctEmlId) {
		this.ctEmlId = ctEmlId;
	}

	public Character getNoPhnCnct() {
		return noPhnCnct;
	}

	public void setNoPhnCnct(Character noPhnCnct) {
		this.noPhnCnct = noPhnCnct;
	}

	public Character getNoEmlCnct() {
		return noEmlCnct;
	}

	public void setNoEmlCnct(Character noEmlCnct) {
		this.noEmlCnct = noEmlCnct;
	}

	public Character getCdPrfRcptCnct() {
		return cdPrfRcptCnct;
	}

	public void setCdPrfRcptCnct(Character cdPrfRcptCnct) {
		this.cdPrfRcptCnct = cdPrfRcptCnct;
	}

	public String getUprLnCnct() {
		return uprLnCnct;
	}

	public void setUprLnCnct(String uprLnCnct) {
		this.uprLnCnct = uprLnCnct;
	}

	public String getUprFnCnct() {
		return uprFnCnct;
	}

	public void setUprFnCnct(String uprFnCnct) {
		this.uprFnCnct = uprFnCnct;
	}

	@XmlTransient
	public List<CustomerSiteAddress> getCustomerSiteAddressList() {
		return customerSiteAddressList;
	}

	public void setCustomerSiteAddressList(List<CustomerSiteAddress> customerSiteAddressList) {
		this.customerSiteAddressList = customerSiteAddressList;
	}

	@XmlTransient
	public List<CustomerSiteInvoice> getCustomerSiteInvoiceList() {
		return customerSiteInvoiceList;
	}

	public void setCustomerSiteInvoiceList(List<CustomerSiteInvoice> customerSiteInvoiceList) {
		this.customerSiteInvoiceList = customerSiteInvoiceList;
	}

	public CustomerSitePaymentTerms getCustomerPaymentTerms() {
		return customerPaymentTerms;
	}

	public void setCustomerPaymentTerms(CustomerSitePaymentTerms customerPaymentTerms) {
		this.customerPaymentTerms = customerPaymentTerms;
	}

	@XmlTransient
	public List<CustomerSiteLimit> getCustomerSiteLimitList() {
		return customerSiteLimitList;
	}

	public void setCustomerSiteLimitList(List<CustomerSiteLimit> customerSiteLimitList) {
		this.customerSiteLimitList = customerSiteLimitList;
	}

	@XmlTransient
	public List<CustomerSitePhone> getCustomerSitePhoneList() {
		return customerSitePhoneList;
	}

	public void setCustomerSitePhoneList(List<CustomerSitePhone> customerSitePhoneList) {
		this.customerSitePhoneList = customerSitePhoneList;
	}

	@XmlTransient
	public List<CustomerSiteStore> getCustomerSiteStoreList() {
		return customerSiteStoreList;
	}

	public void setCustomerSiteStoreList(List<CustomerSiteStore> customerSiteStoreList) {
		this.customerSiteStoreList = customerSiteStoreList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (customerSitePK != null ? customerSitePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CustomerSite)) {
			return false;
		}
		CustomerSite other = (CustomerSite) object;
		if ((this.customerSitePK == null && other.customerSitePK != null)
				|| (this.customerSitePK != null && !this.customerSitePK.equals(other.customerSitePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.CustomerSite[ customerSitePK=" + customerSitePK + " ]";
	}

}
