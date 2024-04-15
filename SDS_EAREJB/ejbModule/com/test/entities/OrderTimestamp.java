package com.test.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the RISPL_DK_ORD_APRV_TIME database table.
 * 
 */

@Entity 
@Table(name = "RISPL_DK_ORD_APRV_TIME ")
@NamedQuery(name="RisplDkOrdAprvTime.findAll", query="SELECT r FROM OrderTimestamp r")
public class OrderTimestamp implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ORDER_ID")
	private String orderId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "TS_ORD_TIME")
	private Date timestamp;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
