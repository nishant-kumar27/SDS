package rispl.db.model.item.pricing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.530+0530")
@StaticMetamodel(RisplDkPrdvnMrchElg.class)
public class RisplDkPrdvnMrchElg_ {
	public static volatile SingularAttribute<RisplDkPrdvnMrchElg, String> idStrcMrCd;
	public static volatile SingularAttribute<RisplDkPrdvnMrchElg, BigDecimal> moTh;
	public static volatile SingularAttribute<RisplDkPrdvnMrchElg, BigDecimal> quTh;
	public static volatile SingularAttribute<RisplDkPrdvnMrchElg, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnMrchElg, Timestamp> tsMdfRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnMrchElg, Timestamp> tsRuMrstEf;
	public static volatile SingularAttribute<RisplDkPrdvnMrchElg, Timestamp> tsRuMrstEp;
	public static volatile SingularAttribute<RisplDkPrdvnMrchElg, BigDecimal> unqId;
	public static volatile SingularAttribute<RisplDkPrdvnMrchElg, RisplDkPrdvnRule> risplDkPrdvnRule;
}
