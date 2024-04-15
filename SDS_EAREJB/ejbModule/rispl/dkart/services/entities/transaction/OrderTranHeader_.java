package rispl.dkart.services.entities.transaction;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rispl.dkart.services.entities.transaction.lpo.OrderTransactionLpo;

@Generated(value="Dali", date="2018-01-09T13:30:35.155+0530")
@StaticMetamodel(OrderTranHeader.class)
public class OrderTranHeader_ {
	public static volatile SingularAttribute<OrderTranHeader, OrderTranHeaderPK> id;
	public static volatile SingularAttribute<OrderTranHeader, String> ctDvrInf;
	public static volatile SingularAttribute<OrderTranHeader, String> ctDvrInfoIns;
	public static volatile SingularAttribute<OrderTranHeader, String> emId;
	public static volatile SingularAttribute<OrderTranHeader, String> flKyOfl;
	public static volatile SingularAttribute<OrderTranHeader, String> flSlsAsscMdf;
	public static volatile SingularAttribute<OrderTranHeader, String> flTreOrd;
	public static volatile SingularAttribute<OrderTranHeader, String> flTrgOrd;
	public static volatile SingularAttribute<OrderTranHeader, String> idBtchArch;
	public static volatile SingularAttribute<OrderTranHeader, String> idBtchInvResv;
	public static volatile SingularAttribute<OrderTranHeader, BigDecimal> idCnyIcd;
	public static volatile SingularAttribute<OrderTranHeader, String> idOpr;
	public static volatile SingularAttribute<OrderTranHeader, String> idRpstyTnd;
	public static volatile SingularAttribute<OrderTranHeader, BigDecimal> idTlogBtch;
	public static volatile SingularAttribute<OrderTranHeader, String> idTrlogBtch;
	public static volatile SingularAttribute<OrderTranHeader, String> idWmsTlogBtch;
	public static volatile SingularAttribute<OrderTranHeader, String> ordTy;
	public static volatile SingularAttribute<OrderTranHeader, BigDecimal> scOrd;
	public static volatile SingularAttribute<OrderTranHeader, BigDecimal> scPstPrcs;
	public static volatile SingularAttribute<OrderTranHeader, Date> tsCrtRcrd;
	public static volatile SingularAttribute<OrderTranHeader, Date> tsMdfRcrd;
	public static volatile SingularAttribute<OrderTranHeader, Date> tsOrdBgn;
	public static volatile SingularAttribute<OrderTranHeader, Date> tsOrdEnd;
	public static volatile SingularAttribute<OrderTranHeader, BigDecimal> claimStatusCode;
	public static volatile SingularAttribute<OrderTranHeader, Integer> deliveryAddressID;
	public static volatile ListAttribute<OrderTranHeader, OrderTranLineItem> ordTranLineItems;
	public static volatile SingularAttribute<OrderTranHeader, OrderTranSum> ordTranSum;
	public static volatile SingularAttribute<OrderTranHeader, OrderTransactionLpo> ordTranLpo;
	public static volatile SingularAttribute<OrderTranHeader, String> returnReasonCode;
	public static volatile SingularAttribute<OrderTranHeader, BigDecimal> transactionStatus;
	public static volatile SingularAttribute<OrderTranHeader, String> deliveryComment;
	public static volatile SingularAttribute<OrderTranHeader, Boolean> tranDiscPerFlag;
	public static volatile SingularAttribute<OrderTranHeader, String> tranDiscAmt;
	public static volatile SingularAttribute<OrderTranHeader, String> tranDiscPer;
	public static volatile SingularAttribute<OrderTranHeader, String> tranDiscReasonCode;
	public static volatile SingularAttribute<OrderTranHeader, String> flInvCncl;
	public static volatile SingularAttribute<OrderTranHeader, String> acceptClaimId;
	public static volatile SingularAttribute<OrderTranHeader, BigDecimal> creditLimitOverride;
	public static volatile SingularAttribute<OrderTranHeader, String> creditLimitOverridenBy;
}
