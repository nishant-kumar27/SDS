package com.test.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-17T10:42:26.436+0530")
@StaticMetamodel(ReceivablesDetail.class)
public class ReceivablesDetail_ {
	public static volatile SingularAttribute<ReceivablesDetail, Integer> age;
	public static volatile SingularAttribute<ReceivablesDetail, ReceivablesDetailPK> id;
	public static volatile SingularAttribute<ReceivablesDetail, BigDecimal> balanceDue;
	public static volatile SingularAttribute<ReceivablesDetail, String> customerName;
	public static volatile SingularAttribute<ReceivablesDetail, String> invoiceStatus;
	public static volatile SingularAttribute<ReceivablesDetail, BigDecimal> invoiceTotal;
	public static volatile SingularAttribute<ReceivablesDetail, String> orderId;
	public static volatile SingularAttribute<ReceivablesDetail, BigDecimal> paid;
	public static volatile SingularAttribute<ReceivablesDetail, String> paymentStatus;
	public static volatile SingularAttribute<ReceivablesDetail, BigDecimal> paymentTerms;
	public static volatile SingularAttribute<ReceivablesDetail, BigDecimal> quantity;
}
