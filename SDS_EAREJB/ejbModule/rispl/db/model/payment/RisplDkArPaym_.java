package rispl.db.model.payment;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.293+0530")
@StaticMetamodel(RisplDkArPaym.class)
public class RisplDkArPaym_ {
	public static volatile SingularAttribute<RisplDkArPaym, RisplDkArPaymPK> id;
	public static volatile SingularAttribute<RisplDkArPaym, BigDecimal> arPaymAmount;
	public static volatile SingularAttribute<RisplDkArPaym, Date> arPaymDate;
	public static volatile SingularAttribute<RisplDkArPaym, String> arPaymNum;
	public static volatile SingularAttribute<RisplDkArPaym, String> currencyCode;
	public static volatile SingularAttribute<RisplDkArPaym, String> custId;
	public static volatile SingularAttribute<RisplDkArPaym, BigDecimal> custSiteId;
	public static volatile SingularAttribute<RisplDkArPaym, String> paymMode;
	public static volatile SingularAttribute<RisplDkArPaym, String> unused1;
	public static volatile SingularAttribute<RisplDkArPaym, String> unused2;
}
