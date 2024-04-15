package rispl.db.model.currency;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.641+0530")
@StaticMetamodel(RisplDkCurrExcRt.class)
public class RisplDkCurrExcRt_ {
	public static volatile SingularAttribute<RisplDkCurrExcRt, RisplDkCurrExcRtPK> id;
	public static volatile SingularAttribute<RisplDkCurrExcRt, BigDecimal> llCnyExc;
	public static volatile SingularAttribute<RisplDkCurrExcRt, BigDecimal> moFeSvExc;
	public static volatile SingularAttribute<RisplDkCurrExcRt, BigDecimal> moRtToBuy;
	public static volatile SingularAttribute<RisplDkCurrExcRt, BigDecimal> moRtToSl;
}
