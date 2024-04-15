package com.test.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-17T10:42:26.366+0530")
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ {
	public static volatile SingularAttribute<OrderDetail, OrderDetailPk> id;
	public static volatile SingularAttribute<OrderDetail, String> customerName;
	public static volatile SingularAttribute<OrderDetail, BigDecimal> divisionId;
	public static volatile SingularAttribute<OrderDetail, Date> effectiveDate;
	public static volatile SingularAttribute<OrderDetail, String> empId;
	public static volatile SingularAttribute<OrderDetail, String> lpoNumber;
	public static volatile SingularAttribute<OrderDetail, String> ordTy;
	public static volatile SingularAttribute<OrderDetail, BigDecimal> orderTotal;
	public static volatile SingularAttribute<OrderDetail, String> salesAgent;
	public static volatile SingularAttribute<OrderDetail, BigDecimal> scOrd;
	public static volatile SingularAttribute<OrderDetail, BigDecimal> scTran;
	public static volatile SingularAttribute<OrderDetail, BigDecimal> qty;
	public static volatile SingularAttribute<OrderDetail, String> businessDate;
	public static volatile SingularAttribute<OrderDetail, BigDecimal> tlog;
}
