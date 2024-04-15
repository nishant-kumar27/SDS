package rispl.db.model.currency;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.629+0530")
@StaticMetamodel(RisplDkCurr.class)
public class RisplDkCurr_ {
	public static volatile SingularAttribute<RisplDkCurr, String> currId;
	public static volatile SingularAttribute<RisplDkCurr, String> currBaseFlg;
	public static volatile SingularAttribute<RisplDkCurr, String> currContCde;
	public static volatile SingularAttribute<RisplDkCurr, String> currDesc;
	public static volatile SingularAttribute<RisplDkCurr, String> currIsoCde;
	public static volatile SingularAttribute<RisplDkCurr, String> currIssgContNat;
	public static volatile SingularAttribute<RisplDkCurr, BigDecimal> currPri;
	public static volatile SingularAttribute<RisplDkCurr, BigDecimal> currScle;
	public static volatile SingularAttribute<RisplDkCurr, String> isoCurrNumb;
}
