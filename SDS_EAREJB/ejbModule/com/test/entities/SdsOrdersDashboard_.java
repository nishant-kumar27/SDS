package com.test.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-17T10:42:26.512+0530")
@StaticMetamodel(SdsOrdersDashboard.class)
public class SdsOrdersDashboard_ {
	public static volatile SingularAttribute<SdsOrdersDashboard, SdsOrdersDashboardPK> id;
	public static volatile SingularAttribute<SdsOrdersDashboard, BigDecimal> cancelledOrders;
	public static volatile SingularAttribute<SdsOrdersDashboard, BigDecimal> completed;
	public static volatile SingularAttribute<SdsOrdersDashboard, BigDecimal> deliveredOrders;
	public static volatile SingularAttribute<SdsOrdersDashboard, BigDecimal> divisionId;
	public static volatile SingularAttribute<SdsOrdersDashboard, BigDecimal> inProgress;
	public static volatile SingularAttribute<SdsOrdersDashboard, BigDecimal> newOrders;
	public static volatile SingularAttribute<SdsOrdersDashboard, BigDecimal> openOrders;
	public static volatile SingularAttribute<SdsOrdersDashboard, BigDecimal> pendingOrders;
	public static volatile SingularAttribute<SdsOrdersDashboard, BigDecimal> returnedOrders;
}
