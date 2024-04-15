package com.test.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-17T10:42:26.413+0530")
@StaticMetamodel(OrderDetailsWithInvoicePK.class)
public class OrderDetailsWithInvoicePK_ {
	public static volatile SingularAttribute<OrderDetailsWithInvoicePK, Date> orderDate;
	public static volatile SingularAttribute<OrderDetailsWithInvoicePK, String> orderId;
	public static volatile SingularAttribute<OrderDetailsWithInvoicePK, String> rtStrId;
	public static volatile SingularAttribute<OrderDetailsWithInvoicePK, String> ordWs;
	public static volatile SingularAttribute<OrderDetailsWithInvoicePK, Long> trnSeq;
	public static volatile SingularAttribute<OrderDetailsWithInvoicePK, String> dcDyOrd;
}
