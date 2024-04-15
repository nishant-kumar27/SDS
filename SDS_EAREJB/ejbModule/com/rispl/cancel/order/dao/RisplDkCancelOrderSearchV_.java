package com.rispl.cancel.order.dao;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-17T10:42:54.539+0530")
@StaticMetamodel(RisplDkCancelOrderSearchV.class)
public class RisplDkCancelOrderSearchV_ {
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> custId;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> customerName;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> deliveryComment;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> empName;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> lpoNumber;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, Date> ordEfDate;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> ordWs;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, Date> orderDate;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> orderId;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, BigDecimal> orderTotal;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> rtStrId;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> salesAgent;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, BigDecimal> scOrd;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, BigDecimal> totalCountItems;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, BigDecimal> trnSeq;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> divisionID;
	public static volatile SingularAttribute<RisplDkCancelOrderSearchV, String> reasonCode;
}
