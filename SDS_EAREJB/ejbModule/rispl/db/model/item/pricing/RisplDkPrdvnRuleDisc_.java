package rispl.db.model.item.pricing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.600+0530")
@StaticMetamodel(RisplDkPrdvnRuleDisc.class)
public class RisplDkPrdvnRuleDisc_ {
	public static volatile SingularAttribute<RisplDkPrdvnRuleDisc, BigDecimal> moUnItmPrdvSls;
	public static volatile SingularAttribute<RisplDkPrdvnRuleDisc, BigDecimal> peUnItmPrdvSls;
	public static volatile SingularAttribute<RisplDkPrdvnRuleDisc, BigDecimal> pntPrcUnItmPrdvSls;
	public static volatile SingularAttribute<RisplDkPrdvnRuleDisc, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnRuleDisc, Timestamp> tsMdfRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnRuleDisc, BigDecimal> unqId;
	public static volatile SingularAttribute<RisplDkPrdvnRuleDisc, RisplDkPrdvnRule> risplDkPrdvnRule;
}
