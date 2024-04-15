package com.test.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-17T10:42:26.481+0530")
@StaticMetamodel(SdsCollectionsDashboard.class)
public class SdsCollectionsDashboard_ {
	public static volatile SingularAttribute<SdsCollectionsDashboard, SdsCollectionsDashboardPK> id;
	public static volatile SingularAttribute<SdsCollectionsDashboard, String> customerName;
	public static volatile SingularAttribute<SdsCollectionsDashboard, BigDecimal> paymentAmount;
	public static volatile SingularAttribute<SdsCollectionsDashboard, String> paymentMode;
	public static volatile SingularAttribute<SdsCollectionsDashboard, String> invNum;
	public static volatile SingularAttribute<SdsCollectionsDashboard, String> ordId;
	public static volatile SingularAttribute<SdsCollectionsDashboard, String> emId;
	public static volatile SingularAttribute<SdsCollectionsDashboard, String> empNme;
	public static volatile SingularAttribute<SdsCollectionsDashboard, BigDecimal> divisionId;
}
