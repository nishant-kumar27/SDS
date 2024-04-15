package com.rispl.sds.offline.invoice;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SDS_OFFLINE_INVOICE_V database table.
 * 
 */
@Entity
@Table(name="SDS_OFFLINE_INVOICE_V")
@NamedQuery(name="SdsOfflineInvoiceV.findAll", query="SELECT s FROM SdsOfflineInvoiceV s")
public class SdsOfflineInvoiceV implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AR_INV_NUM")
	private String arInvNum;

	private String currenttime;

	@Column(name="CUST_ID")
	private String custId;

	@Column(name="DC_DY_ORD")
	private String dcDyOrd;

	@Column(name="ID_ORD")
	private String idOrd;

	@Column(name="INV_ID")
	private String invId;

	@Column(name="ITEM_ID")
	private String itemId;

	@Column(name="LINE_QNT")
	private BigDecimal lineQnt;

	@Column(name="ORD_LN_ITM_SEQ")
	private BigDecimal ordLnItmSeq;

	@Column(name="ORD_WS")
	private String ordWs;

	private String ordertime;

	@Column(name="RT_STR_ID")
	private String rtStrId;

	@Column(name="SITE_ID")
	private String siteId;

	@Column(name="TIME_INTERVAL")
	private BigDecimal timeInterval;

	private BigDecimal total;

	@Column(name="TRN_SEQ")
	private BigDecimal trnSeq;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Id
	@Column(name="UNIQUE_NO")
	private BigDecimal uniqueNo;

	public SdsOfflineInvoiceV() {
	}

	public String getArInvNum() {
		return this.arInvNum;
	}

	public void setArInvNum(String arInvNum) {
		this.arInvNum = arInvNum;
	}

	public String getCurrenttime() {
		return this.currenttime;
	}

	public void setCurrenttime(String currenttime) {
		this.currenttime = currenttime;
	}

	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getDcDyOrd() {
		return this.dcDyOrd;
	}

	public void setDcDyOrd(String dcDyOrd) {
		this.dcDyOrd = dcDyOrd;
	}

	public String getIdOrd() {
		return this.idOrd;
	}

	public void setIdOrd(String idOrd) {
		this.idOrd = idOrd;
	}

	public String getInvId() {
		return this.invId;
	}

	public void setInvId(String invId) {
		this.invId = invId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getLineQnt() {
		return this.lineQnt;
	}

	public void setLineQnt(BigDecimal lineQnt) {
		this.lineQnt = lineQnt;
	}

	public BigDecimal getOrdLnItmSeq() {
		return this.ordLnItmSeq;
	}

	public void setOrdLnItmSeq(BigDecimal ordLnItmSeq) {
		this.ordLnItmSeq = ordLnItmSeq;
	}

	public String getOrdWs() {
		return this.ordWs;
	}

	public void setOrdWs(String ordWs) {
		this.ordWs = ordWs;
	}

	public String getOrdertime() {
		return this.ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public String getRtStrId() {
		return this.rtStrId;
	}

	public void setRtStrId(String rtStrId) {
		this.rtStrId = rtStrId;
	}

	public String getSiteId() {
		return this.siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public BigDecimal getTimeInterval() {
		return this.timeInterval;
	}

	public void setTimeInterval(BigDecimal timeInterval) {
		this.timeInterval = timeInterval;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTrnSeq() {
		return this.trnSeq;
	}

	public void setTrnSeq(BigDecimal trnSeq) {
		this.trnSeq = trnSeq;
	}

	public Timestamp getTsCrtRcrd() {
		return this.tsCrtRcrd;
	}

	public void setTsCrtRcrd(Timestamp tsCrtRcrd) {
		this.tsCrtRcrd = tsCrtRcrd;
	}

	public BigDecimal getUniqueNo() {
		return this.uniqueNo;
	}

	public void setUniqueNo(BigDecimal uniqueNo) {
		this.uniqueNo = uniqueNo;
	}

}