package com.test.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-17T10:42:26.398+0530")
@StaticMetamodel(OrderDetailsWithInvoice.class)
public class OrderDetailsWithInvoice_ {
	public static volatile SingularAttribute<OrderDetailsWithInvoice, OrderDetailsWithInvoicePK> id;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, BigDecimal> orderTotal;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, String> idOpr;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, String> custId;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, String> customerName;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, BigDecimal> divisionId;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, Date> effectiveDate;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, String> employeeId;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, BigDecimal> invoiceAmount;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, Date> invoiceDate;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, String> invoiceId;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, String> lpoNumber;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, Date> orderDate;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, String> ordTy;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, String> salesAgent;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, BigDecimal> scOrd;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, BigDecimal> scTran;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, BigDecimal> tlog;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, BigDecimal> itemCount;
	public static volatile SingularAttribute<OrderDetailsWithInvoice, Date> deliveryDate;
}
