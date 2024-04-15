package rispl.dkart.services.entities.transaction;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:35.128+0530")
@StaticMetamodel(OrderTranDiscountItem.class)
public class OrderTranDiscountItem_ {
	public static volatile SingularAttribute<OrderTranDiscountItem, OrderTranDiscountItemPK> id;
	public static volatile SingularAttribute<OrderTranDiscountItem, BigDecimal> dscAmt;
	public static volatile SingularAttribute<OrderTranDiscountItem, BigDecimal> dscPer;
	public static volatile SingularAttribute<OrderTranDiscountItem, BigDecimal> prmCmpDtlid;
	public static volatile SingularAttribute<OrderTranDiscountItem, BigDecimal> prmCmpId;
	public static volatile SingularAttribute<OrderTranDiscountItem, String> prmDesc;
	public static volatile SingularAttribute<OrderTranDiscountItem, BigDecimal> prmId;
	public static volatile SingularAttribute<OrderTranDiscountItem, BigDecimal> prmType;
	public static volatile SingularAttribute<OrderTranDiscountItem, String> srcTrgList;
	public static volatile SingularAttribute<OrderTranDiscountItem, BigDecimal> tyDsc;
	public static volatile SingularAttribute<OrderTranDiscountItem, String> discReasonCode;
	public static volatile SingularAttribute<OrderTranDiscountItem, OrderTranLineItem> ordTranLineItem;
}
