package rispl.dkart.services.entities.transaction.lpo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rispl.dkart.services.entities.transaction.OrderTranHeader;

@Generated(value="Dali", date="2018-01-09T13:30:34.933+0530")
@StaticMetamodel(OrderTransactionLpo.class)
public class OrderTransactionLpo_ {
	public static volatile SingularAttribute<OrderTransactionLpo, OrderTransactionLpoPK> id;
	public static volatile SingularAttribute<OrderTransactionLpo, String> lpoDate;
	public static volatile SingularAttribute<OrderTransactionLpo, String> lpoNumber;
	public static volatile SingularAttribute<OrderTransactionLpo, byte[]> lpoSlipContent;
	public static volatile SingularAttribute<OrderTransactionLpo, String> lpoSlipName;
	public static volatile SingularAttribute<OrderTransactionLpo, String> lpoSlipType;
	public static volatile SingularAttribute<OrderTransactionLpo, OrderTranHeader> ordTranHeader;
}
