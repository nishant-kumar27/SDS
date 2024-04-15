package rispl.dkart.services.entities.customer;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Cacheable(value = true)
@Table(name = "RISPL_DK_CUST_HDR")
@XmlRootElement

public class CustomerHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected CustomerHeaderPK customerHeaderPK;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "CT_NM")
	private String ctNm;

	@Size(max = 10)
	@Column(name = "EM_ID")
	private String emId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CT_STS_CD")
	private char ctStsCd;

	@Size(max = 10)
	@Column(name = "LCL")
	private String lcl;

	@Size(max = 1000)
	@Column(name = "ID_NCRPT_TAX")
	private String idNcrptTax;

	@Column(name = "CT_BTCH_ID")
	private BigInteger ctBtchId;

	@Column(name = "CT_PHONE")
	private BigInteger ctPhone;

	@Size(max = 50)
	@Column(name = "CT_EML_ID")
	private String ctEmlId;

	@Size(max = 40)
	@Column(name = "CT_URL")
	private String ctUrl;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "CUST_TYP")
	private String custTyp;

	@Column(name = "ITM_RCRD_CRT_TS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date itmRcrdCrtTs;

	@Column(name = "ITM_RCRD_MDF_TS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date itmRcrdMdfTs;

	@JoinColumn(name = "PRCNG_GRP_ID", referencedColumnName = "PRCNG_GRP_ID")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private CustomerSegment prcngGrpId;

	@Size(max = 30)
	@Column(name = "CUST_COUNTRY")
	private String custCountry;

	@Size(max = 30)
	@Column(name = "DIVISION_ID")
	private String divisionId;
	
	@Column(name = "PRIORITY")
	private String priority;

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	
	public String getDivisionId() {
		return divisionId;
	}
		
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	

	public CustomerHeader() {
	}

	public CustomerHeader(CustomerHeaderPK customerHeaderPK) {
		this.customerHeaderPK = customerHeaderPK;
	}

	public CustomerHeader(CustomerHeaderPK customerHeaderPK, String ctNm, char ctStsCd, String custTyp) {
		this.customerHeaderPK = customerHeaderPK;
		this.ctNm = ctNm;
		this.ctStsCd = ctStsCd;
		this.custTyp = custTyp;
	}

	public CustomerHeader(BigInteger custPrtyId, String custId) {
		this.customerHeaderPK = new CustomerHeaderPK(custPrtyId, custId);
	}

	public CustomerHeaderPK getCustomerHeaderPK() {
		return customerHeaderPK;
	}

	public void setCustomerHeaderPK(CustomerHeaderPK customerHeaderPK) {
		this.customerHeaderPK = customerHeaderPK;
	}

	public String getCtNm() {
		return ctNm;
	}

	public void setCtNm(String ctNm) {
		this.ctNm = ctNm;
	}

	public String getEmId() {
		return emId;
	}

	public void setEmId(String emId) {
		this.emId = emId;
	}

	public char getCtStsCd() {
		return ctStsCd;
	}

	public void setCtStsCd(char ctStsCd) {
		this.ctStsCd = ctStsCd;
	}

	public String getLcl() {
		return lcl;
	}

	public void setLcl(String lcl) {
		this.lcl = lcl;
	}

	public String getIdNcrptTax() {
		return idNcrptTax;
	}

	public void setIdNcrptTax(String idNcrptTax) {
		this.idNcrptTax = idNcrptTax;
	}

	public BigInteger getCtBtchId() {
		return ctBtchId;
	}

	public void setCtBtchId(BigInteger ctBtchId) {
		this.ctBtchId = ctBtchId;
	}

	public BigInteger getCtPhone() {
		return ctPhone;
	}

	public void setCtPhone(BigInteger ctPhone) {
		this.ctPhone = ctPhone;
	}

	public String getCtEmlId() {
		return ctEmlId;
	}

	public void setCtEmlId(String ctEmlId) {
		this.ctEmlId = ctEmlId;
	}

	public String getCtUrl() {
		return ctUrl;
	}

	public void setCtUrl(String ctUrl) {
		this.ctUrl = ctUrl;
	}

	public String getCustTyp() {
		return custTyp;
	}

	public void setCustTyp(String custTyp) {
		this.custTyp = custTyp;
	}

	public Date getItmRcrdCrtTs() {
		return itmRcrdCrtTs;
	}

	public void setItmRcrdCrtTs(Date itmRcrdCrtTs) {
		this.itmRcrdCrtTs = itmRcrdCrtTs;
	}

	public Date getItmRcrdMdfTs() {
		return itmRcrdMdfTs;
	}

	public void setItmRcrdMdfTs(Date itmRcrdMdfTs) {
		this.itmRcrdMdfTs = itmRcrdMdfTs;
	}

	public CustomerSegment getPrcngGrpId() {
		return prcngGrpId;
	}

	public void setPrcngGrpId(CustomerSegment prcngGrpId) {
		this.prcngGrpId = prcngGrpId;
	}

	public String getCustCountry() {
		return custCountry;
	}

	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (customerHeaderPK != null ? customerHeaderPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CustomerHeader)) {
			return false;
		}
		CustomerHeader other = (CustomerHeader) object;
		if ((this.customerHeaderPK == null && other.customerHeaderPK != null)
				|| (this.customerHeaderPK != null && !this.customerHeaderPK.equals(other.customerHeaderPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CustomerHeader [customerHeaderPK=" + customerHeaderPK + ", ctNm=" + ctNm + ", emId=" + emId
				+ ", ctStsCd=" + ctStsCd + ", lcl=" + lcl + ", idNcrptTax=" + idNcrptTax + ", ctBtchId=" + ctBtchId
				+ ", ctPhone=" + ctPhone + ", ctEmlId=" + ctEmlId + ", ctUrl=" + ctUrl + ", custTyp=" + custTyp
				+ ", itmRcrdCrtTs=" + itmRcrdCrtTs + ", itmRcrdMdfTs=" + itmRcrdMdfTs + ", prcngGrpId=" + prcngGrpId
				+ ", custCountry=" + custCountry + ", divisionId=" + divisionId + "]";
	}

}
