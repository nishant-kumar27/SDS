package rispl.dkart.services.entities.transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.entities.transaction.lpo.OrderTransactionLpo;

/**
 * The persistent class for the ORD_TRAN_HEADER database table.
 * 
 */
@Entity
@Table(name = "ORD_TRAN_HEADER")
@NamedQueries({
@NamedQuery(name = "OrderTranHeader.findAll", query = "SELECT o FROM OrderTranHeader o"),
//mudassir
@NamedQuery(name="markAsDelivered" , query="update OrderTranHeader oth set  oth.ordTy=:orty,oth.scOrd=:sco where oth.id in :othlidt")})
public class OrderTranHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderTranHeaderPK id;

	@Lob
	@Column(name = "CT_DVR_INF")
	private String ctDvrInf;

	@Column(name = "CT_DVR_INFO_INS")
	private String ctDvrInfoIns;

	@Column(name = "EM_ID")
	private String emId;

	@Column(name = "FL_KY_OFL")
	private String flKyOfl;

	@Column(name = "FL_SLS_ASSC_MDF")
	private String flSlsAsscMdf;

	@Column(name = "FL_TRE_ORD")
	private String flTreOrd;

	@Column(name = "FL_TRG_ORD")
	private String flTrgOrd;

	@Column(name = "ID_BTCH_ARCH")
	private String idBtchArch;

	@Column(name = "ID_BTCH_INV_RESV")
	private String idBtchInvResv;

	@Column(name = "ID_CNY_ICD")
	private BigDecimal idCnyIcd;

	@Column(name = "ID_OPR")
	private String idOpr;

	@Column(name = "ID_RPSTY_TND")
	private String idRpstyTnd;

	@Column(name = "ID_TLOG_BTCH")
	private BigDecimal idTlogBtch;

	@Column(name = "ID_TRLOG_BTCH")
	private String idTrlogBtch;

	@Column(name = "ID_WMS_TLOG_BTCH")
	private String idWmsTlogBtch;

	@Column(name = "ORD_TY")
	private String ordTy;

	@Column(name = "SC_ORD")
	private BigDecimal scOrd;

	@Column(name = "SC_PST_PRCS")
	private BigDecimal scPstPrcs;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_CRT_RCRD")
	private Date tsCrtRcrd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_MDF_RCRD")
	private Date tsMdfRcrd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_ORD_BGN")
	private Date tsOrdBgn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_ORD_END")
	private Date tsOrdEnd;
	
	@Column(name = "SC_CLM")
	private BigDecimal claimStatusCode;
	
	@Column(name = "ADS_ID")
	private int deliveryAddressID;
	
	//The field is used to store transaction level comments
	//TODO Need to change annotation Transient to @Column(name="TransComment")
	@Transient
	private String transComment;
	
	@Transient
	public Date businessDate=null;

	// bi-directional many-to-one association to OrderTranLineItem
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "ordTranHeader", fetch = FetchType.EAGER)
	private List<OrderTranLineItem> ordTranLineItems;

	// bi-directional many-to-one association to OrderTranSum
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "ordTranHeader", fetch = FetchType.EAGER)
	private OrderTranSum ordTranSum;
	
	// bi-directional many-to-one association to OrderTranSum
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "ordTranHeader", fetch = FetchType.EAGER)
	private OrderTransactionLpo ordTranLpo;

	// to hold Reason Codes
	@Transient
	private Map<String, Map<String, String>> reasonCodes;

	//// to hold the customer information in the header level
	@Transient
	private CustomerIfc customer;

	@Column(name = "RC_RTN_MR")
	private String returnReasonCode;
	
	@Column(name = "SC_TRAN")
	private BigDecimal transactionStatus;
	//mudassir
	@Column(name="DELIVERY_COMMENT")
	private String deliveryComment;
	
////to hold the transaction level Discounts
	@Transient
	private boolean tranDiscAmtFlag;
	
	@Column(name = "FL_TRN_DISC_PER")
	private boolean tranDiscPerFlag;
	
	@Column(name = "TRN_DISC_AMT")
	private String tranDiscAmt;
	
	@Column(name = "TRN_DISC_PER")
	private String tranDiscPer;
	
	@Column(name = "TRN_DISC_REASON_CODE")
	private String tranDiscReasonCode;
	
	@Column(name = "FL_INV_CNCL")
	private String flInvCncl;
	
	@Transient
	private boolean anyItemLevelManualDiscuntsApplyed;
	
	@Transient
	private boolean anyTranLevelManualDiscuntsApplyed;
	

	@Transient
	private String shipmentMethod;
	
	@Transient
	private Map<String,String> salesAgentsMap;
	
	@Column(name="ACCEPT_CLAIM_ID")
	private String acceptClaimId;
	
	@Transient //Used to maintain running credit limit of the transaction
	private BigDecimal availCrdtLimit;
	
	@Transient //Used to maintain credit limit of the transaction
	private BigDecimal transCrdtLimit;
	
	@Column(name="CREDIT_LIMIT_OVERRIDE")
	private BigDecimal creditLimitOverride;
	
	@Column(name="CREDIT_LIMIT_OVERRIDEN_BY")
	private String creditLimitOverridenBy;
	
	@Transient //Used to maintain Transaction total during Credit Limit override
	private BigDecimal ovrrdTranTotal=BigDecimal.ZERO;
	
	public String getAcceptClaimId() {
		return acceptClaimId;
	}

	public void setAcceptClaimId(String acceptClaimId) {
		this.acceptClaimId = acceptClaimId;
	}

	public Map<String, String> getSalesAgentsMap() {
		return salesAgentsMap;
	}

	public void setSalesAgentsMap(Map<String, String> salesAgentsMap) {
		this.salesAgentsMap = salesAgentsMap;
	}

	public String getReturnReasonCode() {
		return returnReasonCode;
	}

	public void setReturnReasonCode(String returnReasonCode) {
		this.returnReasonCode = returnReasonCode;
	}

	public CustomerIfc getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerIfc customer) {
		this.customer = customer;
	}

	public Map<String, Map<String, String>> getReasonCodes() {
		return reasonCodes;
	}

	public void setReasonCodes(Map<String, Map<String, String>> reasonCodes) {
		this.reasonCodes = reasonCodes;
	}

	public OrderTranHeader() {
	}

	public OrderTranHeaderPK getId() {
		return this.id;
	}

	public void setId(OrderTranHeaderPK id) {
		this.id = id;
	}

	public String getCtDvrInf() {
		return this.ctDvrInf;
	}

	public void setCtDvrInf(String ctDvrInf) {
		this.ctDvrInf = ctDvrInf;
	}

	public String getCtDvrInfoIns() {
		return this.ctDvrInfoIns;
	}

	public void setCtDvrInfoIns(String ctDvrInfoIns) {
		this.ctDvrInfoIns = ctDvrInfoIns;
	}

	public String getEmId() {
		return this.emId;
	}

	public void setEmId(String emId) {
		this.emId = emId;
	}

	public String getFlKyOfl() {
		return this.flKyOfl;
	}

	public void setFlKyOfl(String flKyOfl) {
		this.flKyOfl = flKyOfl;
	}

	//mudassir
	public String getDeliveryComment() {
		return deliveryComment;
	}

	public void setDeliveryComment(String deliveryComment) {
		this.deliveryComment = deliveryComment;
	}

	public String getFlSlsAsscMdf() {
		return this.flSlsAsscMdf;
	}

	public void setFlSlsAsscMdf(String flSlsAsscMdf) {
		this.flSlsAsscMdf = flSlsAsscMdf;
	}

	public String getFlTreOrd() {
		return this.flTreOrd;
	}

	public void setFlTreOrd(String flTreOrd) {
		this.flTreOrd = flTreOrd;
	}

	public String getFlTrgOrd() {
		return this.flTrgOrd;
	}

	public void setFlTrgOrd(String flTrgOrd) {
		this.flTrgOrd = flTrgOrd;
	}

	public String getIdBtchArch() {
		return this.idBtchArch;
	}

	public void setIdBtchArch(String idBtchArch) {
		this.idBtchArch = idBtchArch;
	}

	public String getIdBtchInvResv() {
		return this.idBtchInvResv;
	}

	public void setIdBtchInvResv(String idBtchInvResv) {
		this.idBtchInvResv = idBtchInvResv;
	}

	public BigDecimal getIdCnyIcd() {
		return this.idCnyIcd;
	}

	public void setIdCnyIcd(BigDecimal idCnyIcd) {
		this.idCnyIcd = idCnyIcd;
	}

	public String getIdOpr() {
		return this.idOpr;
	}

	public void setIdOpr(String idOpr) {
		this.idOpr = idOpr;
	}

	public String getIdRpstyTnd() {
		return this.idRpstyTnd;
	}

	public void setIdRpstyTnd(String idRpstyTnd) {
		this.idRpstyTnd = idRpstyTnd;
	}

	public BigDecimal getIdTlogBtch() {
		return this.idTlogBtch;
	}

	public void setIdTlogBtch(BigDecimal idTlogBtch) {
		this.idTlogBtch = idTlogBtch;
	}

	public String getIdTrlogBtch() {
		return this.idTrlogBtch;
	}

	public void setIdTrlogBtch(String idTrlogBtch) {
		this.idTrlogBtch = idTrlogBtch;
	}

	public String getIdWmsTlogBtch() {
		return this.idWmsTlogBtch;
	}

	public void setIdWmsTlogBtch(String idWmsTlogBtch) {
		this.idWmsTlogBtch = idWmsTlogBtch;
	}

	public String getOrdTy() {
		return this.ordTy;
	}

	public void setOrdTy(String ordTy) {
		this.ordTy = ordTy;
	}

	public BigDecimal getScOrd() {
		return this.scOrd;
	}

	public void setScOrd(BigDecimal scOrd) {
		this.scOrd = scOrd;
	}

	public BigDecimal getScPstPrcs() {
		return this.scPstPrcs;
	}

	public void setScPstPrcs(BigDecimal scPstPrcs) {
		this.scPstPrcs = scPstPrcs;
	}

	public Date getTsCrtRcrd() {
		return this.tsCrtRcrd;
	}

	public void setTsCrtRcrd(Date tsCrtRcrd) {
		this.tsCrtRcrd = tsCrtRcrd;
	}

	public Date getTsMdfRcrd() {
		return this.tsMdfRcrd;
	}

	public void setTsMdfRcrd(Date tsMdfRcrd) {
		this.tsMdfRcrd = tsMdfRcrd;
	}

	public Date getTsOrdBgn() {
		return this.tsOrdBgn;
	}

	public void setTsOrdBgn(Date tsOrdBgn) {
		this.tsOrdBgn = tsOrdBgn;
	}

	public Date getTsOrdEnd() {
		return this.tsOrdEnd;
	}

	public void setTsOrdEnd(Date tsOrdEnd) {
		this.tsOrdEnd = tsOrdEnd;
	}

	public List<OrderTranLineItem> getOrdTranLineItems() {
		return this.ordTranLineItems;
	}

	public void setOrdTranLineItems(List<OrderTranLineItem> ordTranLineItems) {
		this.ordTranLineItems = ordTranLineItems;
	}

	public OrderTranLineItem addOrdTranLineItem(OrderTranLineItem ordTranLineItem) {
		getOrdTranLineItems().add(ordTranLineItem);
		ordTranLineItem.setOrdTranHeader(this);

		return ordTranLineItem;
	}

	public OrderTranLineItem removeOrdTranLineItem(OrderTranLineItem ordTranLineItem) {
		getOrdTranLineItems().remove(ordTranLineItem);
		ordTranLineItem.setOrdTranHeader(null);

		return ordTranLineItem;
	}

	public OrderTranSum getOrdTranSum() {
		return this.ordTranSum;
	}

	public void setOrdTranSum(OrderTranSum ordTranSum) {
		this.ordTranSum = ordTranSum;
	}

	public BigDecimal getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(BigDecimal transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public BigDecimal getClaimStatusCode() {
		return claimStatusCode;
	}

	public void setClaimStatusCode(BigDecimal claimStatusCode) {
		this.claimStatusCode = claimStatusCode;
	}

	public boolean isTranDiscAmtFlag() {
		return tranDiscAmtFlag;
	}

	public void setTranDiscAmtFlag(boolean tranDiscAmtFlag) {
		this.tranDiscAmtFlag = tranDiscAmtFlag;
	}

	public boolean isTranDiscPerFlag() {
		return tranDiscPerFlag;
	}

	public void setTranDiscPerFlag(boolean tranDiscPerFlag) {
		this.tranDiscPerFlag = tranDiscPerFlag;
	}

	public String getTranDiscAmt() {
		return tranDiscAmt;
	}

	public void setTranDiscAmt(String tranDiscAmt) {
		this.tranDiscAmt = tranDiscAmt;
	}

	public String getTranDiscPer() {
		return tranDiscPer;
	}

	public void setTranDiscPer(String tranDiscPer) {
		this.tranDiscPer = tranDiscPer;
	}

	public String getTranDiscReasonCode() {
		return tranDiscReasonCode;
	}

	public void setTranDiscReasonCode(String tranDiscReasonCode) {
		this.tranDiscReasonCode = tranDiscReasonCode;
	}

	public int getDeliveryAddressID() {
		return deliveryAddressID;
	}

	public void setDeliveryAddressID(int deliveryAddressID) {
		this.deliveryAddressID = deliveryAddressID;
	}
	
	public Date businessDate()
	{
		try {
			
			businessDate = null;
			
			String dateStr = (id != null) ? id.getDcDyOrd():"";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			businessDate = dateStr=="" ? new Date() : (Date) format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return businessDate;
	}

	public Date getBusinessDate() {
		return businessDate=businessDate();
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	//to retrieve the LPO attached
	public OrderTransactionLpo getOrdTranLpo() {
		return ordTranLpo;
	}

	public void setOrdTranLpo(OrderTransactionLpo ordTranLpo) {
		this.ordTranLpo = ordTranLpo;
	}

	public String getTransComment() {
		return transComment;
	}

	public void setTransComment(String transComment) {
		this.transComment = transComment;
	}

	

	public boolean isAnyItemLevelManualDiscuntsApplyed() {
		return anyItemLevelManualDiscuntsApplyed;
	}

	public void setAnyItemLevelManualDiscuntsApplyed(boolean anyItemLevelManualDiscuntsApplyed) {
		this.anyItemLevelManualDiscuntsApplyed = anyItemLevelManualDiscuntsApplyed;
	}

	public boolean isAnyTranLevelManualDiscuntsApplyed() {
		return anyTranLevelManualDiscuntsApplyed;
	}

	public void setAnyTranLevelManualDiscuntsApplyed(boolean anyTranLevelManualDiscuntsApplyed) {
		this.anyTranLevelManualDiscuntsApplyed = anyTranLevelManualDiscuntsApplyed;
	}

	public String getShipmentMethod() {
		return shipmentMethod;
	}

	public void setShipmentMethod(String shipmentMethod) {
		this.shipmentMethod = shipmentMethod;
	}

	@Transient
	private boolean groupDiscLinItms;

	public boolean isGroupDiscLinItms() {
		return groupDiscLinItms;
	}

	public void setGroupDiscLinItms(boolean groupDiscLinItms) {
		this.groupDiscLinItms = groupDiscLinItms;
	}

	public String getFlInvCncl() {
		return flInvCncl;
	}

	public void setFlInvCncl(String flInvCncl) {
		this.flInvCncl = flInvCncl;
	}

	public BigDecimal getAvailCrdtLimit() {
		return availCrdtLimit;
	}

	public void setAvailCrdtLimit(BigDecimal availCrdtLimit) {
		this.availCrdtLimit = availCrdtLimit;
	}

	public BigDecimal getTransCrdtLimit() {
		return transCrdtLimit;
	}

	public void setTransCrdtLimit(BigDecimal transCrdtLimit) {
		this.transCrdtLimit = transCrdtLimit;
	}

	public BigDecimal getCreditLimitOverride() {
		return creditLimitOverride;
	}

	public void setCreditLimitOverride(BigDecimal creditLimitOverride) {
		this.creditLimitOverride = creditLimitOverride;
	}

	public String getCreditLimitOverridenBy() {
		return creditLimitOverridenBy;
	}

	public void setCreditLimitOverridenBy(String creditLimitOverridenBy) {
		this.creditLimitOverridenBy = creditLimitOverridenBy;
	}

	public BigDecimal getOvrrdTranTotal() {
		return ovrrdTranTotal;
	}

	public void setOvrrdTranTotal(BigDecimal ovrrdTranTotal) {
		this.ovrrdTranTotal = ovrrdTranTotal;
	}
	
	@Transient
	private Map<String,String> custSites;

	public Map<String, String> getCustSites() {
		return custSites;
	}

	public void setCustSites(Map<String, String> custSites) {
		this.custSites = custSites;
	}
}