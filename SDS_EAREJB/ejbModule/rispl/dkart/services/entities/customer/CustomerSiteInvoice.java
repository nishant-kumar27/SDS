/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rispl.dkart.services.entities.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import rispl.dkart.services.entities.transaction.OrdInvQtyShp;
import rispl.dkart.services.entities.transaction.OrderTranSum;

@Entity
@Table(name = "RISPL_DK_CUST_SITE_INV")
@XmlRootElement
@NamedQuery(name="CustomerSiteInvoice.findInvoiceByInvOrOrder",query="SELECT invoice FROM CustomerSiteInvoice invoice WHERE invoice.arInvNum LIKE :id OR invoice.orderNum LIKE :id")
public class CustomerSiteInvoice implements Serializable {
	private static final long serialVersionUID = 1L;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "INV_ID")
	private BigDecimal invId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "AR_INV_NUM")
	private String arInvNum;
	@Basic(optional = false)
	@NotNull
	@Column(name = "AR_INV_DATE")
	@Temporal(TemporalType.DATE)
	private Date arInvDate;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "ORDER_NUM")
	private String orderNum;
	@Basic(optional = false)
	@NotNull
	@Column(name = "ORDER_DATE")
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Basic(optional = false)
	@NotNull
	@Column(name = "INV_STATUS")
	private char invStatus;
	@Basic(optional = false)
	@NotNull
	@Column(name = "INV_AMOUNT")
	private BigDecimal invAmount;
	@Column(name = "INV_PEND_AMOUNT")
	private BigDecimal invPendAmount;
	@Column(name = "INV_CLOSE_DATE")
	@Temporal(TemporalType.DATE)
	private Date invCloseDate;
	@Size(max = 5)
	@Column(name = "STORE_ID")
	private String storeId;
	@Size(max = 14)
	@Column(name = "REF_CUST_ID")
	private String refCustId;
	@JoinColumns({ @JoinColumn(name = "CUST_SITE_ID", referencedColumnName = "CUST_SITE_ID"),
			@JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID") })
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private CustomerSite customerSite;
	
	@JoinColumn(name = "ID_ORD_AR", referencedColumnName = "AR_INV_NUM")
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<OrdInvQtyShp> invQtyShp;
	
	public CustomerSiteInvoice() {
	}

	public CustomerSiteInvoice(BigDecimal invId) {
		this.invId = invId;
	}

	public CustomerSiteInvoice(BigDecimal invId, String arInvNum, Date arInvDate, String orderNum, Date orderDate,
			char invStatus, BigDecimal invAmount) {
		this.invId = invId;
		this.arInvNum = arInvNum;
		this.arInvDate = arInvDate;
		this.orderNum = orderNum;
		this.orderDate = orderDate;
		this.invStatus = invStatus;
		this.invAmount = invAmount;
	}

	public BigDecimal getInvId() {
		return invId;
	}

	public void setInvId(BigDecimal invId) {
		this.invId = invId;
	}

	public String getArInvNum() {
		return arInvNum;
	}

	public void setArInvNum(String arInvNum) {
		this.arInvNum = arInvNum;
	}

	public Date getArInvDate() {
		return arInvDate;
	}

	public void setArInvDate(Date arInvDate) {
		this.arInvDate = arInvDate;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public char getInvStatus() {
		return invStatus;
	}

	public void setInvStatus(char invStatus) {
		this.invStatus = invStatus;
	}

	public BigDecimal getInvAmount() {
		return invAmount;
	}

	public void setInvAmount(BigDecimal invAmount) {
		this.invAmount = invAmount;
	}

	public BigDecimal getInvPendAmount() {
		return invPendAmount;
	}

	public void setInvPendAmount(BigDecimal invPendAmount) {
		this.invPendAmount = invPendAmount;
	}

	public Date getInvCloseDate() {
		return invCloseDate;
	}

	public void setInvCloseDate(Date invCloseDate) {
		this.invCloseDate = invCloseDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getRefCustId() {
		return refCustId;
	}

	public void setRefCustId(String refCustId) {
		this.refCustId = refCustId;
	}

	public CustomerSite getCustomerSite() {
		return customerSite;
	}

	public void setCustomerSite(CustomerSite customerSite) {
		this.customerSite = customerSite;
	}

	public List<OrdInvQtyShp> getInvQtyShp() {
		return invQtyShp;
	}

	public void setInvQtyShp(List<OrdInvQtyShp> invQtyShp) {
		this.invQtyShp = invQtyShp;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (invId != null ? invId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CustomerSiteInvoice)) {
			return false;
		}
		CustomerSiteInvoice other = (CustomerSiteInvoice) object;
		if ((this.invId == null && other.invId != null) || (this.invId != null && !this.invId.equals(other.invId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.CustomerSiteInvoice[ invId=" + invId + " ]";
	}

}
