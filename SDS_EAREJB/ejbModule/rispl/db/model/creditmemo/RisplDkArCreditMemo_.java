package rispl.db.model.creditmemo;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.606+0530")
@StaticMetamodel(RisplDkArCreditMemo.class)
public class RisplDkArCreditMemo_ {
	public static volatile SingularAttribute<RisplDkArCreditMemo, RisplDkArCreditMemoPK> id;
	public static volatile SingularAttribute<RisplDkArCreditMemo, BigDecimal> crMemoAmount;
	public static volatile SingularAttribute<RisplDkArCreditMemo, Date> crMemoDate;
	public static volatile SingularAttribute<RisplDkArCreditMemo, String> claimID;
}
