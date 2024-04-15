package rispl.db.model.item.pricing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.630+0530")
@StaticMetamodel(RisplDkPrdvnRuleElg.class)
public class RisplDkPrdvnRuleElg_ {
	public static volatile SingularAttribute<RisplDkPrdvnRuleElg, String> itmId;
	public static volatile SingularAttribute<RisplDkPrdvnRuleElg, BigDecimal> moTh;
	public static volatile SingularAttribute<RisplDkPrdvnRuleElg, BigDecimal> quTh;
	public static volatile SingularAttribute<RisplDkPrdvnRuleElg, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnRuleElg, Timestamp> tsMdfRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnRuleElg, Timestamp> tsRuDrvnEf;
	public static volatile SingularAttribute<RisplDkPrdvnRuleElg, Timestamp> tsRuDrvnEp;
	public static volatile SingularAttribute<RisplDkPrdvnRuleElg, BigDecimal> unqId;
	public static volatile SingularAttribute<RisplDkPrdvnRuleElg, RisplDkPrdvnRule> risplDkPrdvnRule;
}
