package rispl.db.model.payment;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the RISPL_DK_AR_PAYM database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_AR_PAYM")
@NamedQuery(name="RisplDkArPaym.findAll", query="SELECT r FROM RisplDkArPaym r")
public class RisplDkArPaym implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkArPaymPK id;

	@Column(name="AR_PAYM_AMOUNT")
	private BigDecimal arPaymAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="AR_PAYM_DATE")
	private Date arPaymDate;

	@Column(name="AR_PAYM_NUM")
	private String arPaymNum;

	@Column(name="CURRENCY_CODE")
	private String currencyCode;

	@Column(name="CUST_ID")
	private String custId;

	@Column(name="CUST_SITE_ID")
	private BigDecimal custSiteId;

	@Column(name="PAYM_MODE")
	private String paymMode;

	@Column(name="UNUSED_1")
	private String unused1;

	@Column(name="UNUSED_2")
	private String unused2;

	public RisplDkArPaym() {
	}

	public RisplDkArPaymPK getId() {
		return this.id;
	}

	public void setId(RisplDkArPaymPK id) {
		this.id = id;
	}

	public BigDecimal getArPaymAmount() {
		return this.arPaymAmount;
	}

	public void setArPaymAmount(BigDecimal arPaymAmount) {
		this.arPaymAmount = arPaymAmount;
	}

	public Date getArPaymDate() {
		return this.arPaymDate;
	}

	public void setArPaymDate(Date arPaymDate) {
		this.arPaymDate = arPaymDate;
	}

	public String getArPaymNum() {
		return this.arPaymNum;
	}

	public void setArPaymNum(String arPaymNum) {
		this.arPaymNum = arPaymNum;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public BigDecimal getCustSiteId() {
		return this.custSiteId;
	}

	public void setCustSiteId(BigDecimal custSiteId) {
		this.custSiteId = custSiteId;
	}

	public String getPaymMode() {
		return this.paymMode;
	}

	public void setPaymMode(String paymMode) {
		this.paymMode = paymMode;
	}

	public String getUnused1() {
		return this.unused1;
	}

	public void setUnused1(String unused1) {
		this.unused1 = unused1;
	}

	public String getUnused2() {
		return this.unused2;
	}

	public void setUnused2(String unused2) {
		this.unused2 = unused2;
	}

}