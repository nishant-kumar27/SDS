package com.rispl.sds.offline.invoice;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-17T10:43:09.635+0530")
@StaticMetamodel(SdsOfflineInvoiceV.class)
public class SdsOfflineInvoiceV_ {
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> arInvNum;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> currenttime;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> custId;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> dcDyOrd;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> idOrd;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> invId;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> itemId;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, BigDecimal> lineQnt;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, BigDecimal> ordLnItmSeq;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> ordWs;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> ordertime;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> rtStrId;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, String> siteId;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, BigDecimal> timeInterval;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, BigDecimal> total;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, BigDecimal> trnSeq;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<SdsOfflineInvoiceV, BigDecimal> uniqueNo;
}
