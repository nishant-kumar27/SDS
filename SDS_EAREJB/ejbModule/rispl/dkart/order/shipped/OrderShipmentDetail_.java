package rispl.dkart.order.shipped;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.922+0530")
@StaticMetamodel(OrderShipmentDetail.class)
public class OrderShipmentDetail_ {
	public static volatile SingularAttribute<OrderShipmentDetail, OrderShipmentDetailPK> id;
	public static volatile SingularAttribute<OrderShipmentDetail, String> containerStatus;
	public static volatile SingularAttribute<OrderShipmentDetail, BigDecimal> pickQty;
	public static volatile SingularAttribute<OrderShipmentDetail, String> rtlogbatch;
}
