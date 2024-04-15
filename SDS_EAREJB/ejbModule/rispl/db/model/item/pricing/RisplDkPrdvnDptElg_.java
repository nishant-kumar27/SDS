package rispl.db.model.item.pricing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.436+0530")
@StaticMetamodel(RisplDkPrdvnDptElg.class)
public class RisplDkPrdvnDptElg_ {
	public static volatile SingularAttribute<RisplDkPrdvnDptElg, String> idDptPos;
	public static volatile SingularAttribute<RisplDkPrdvnDptElg, BigDecimal> moTh;
	public static volatile SingularAttribute<RisplDkPrdvnDptElg, BigDecimal> quTh;
	public static volatile SingularAttribute<RisplDkPrdvnDptElg, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnDptElg, Timestamp> tsMdfRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnDptElg, Timestamp> tsRuMrstEf;
	public static volatile SingularAttribute<RisplDkPrdvnDptElg, Timestamp> tsRuMrstEp;
	public static volatile SingularAttribute<RisplDkPrdvnDptElg, BigDecimal> unqId;
	public static volatile SingularAttribute<RisplDkPrdvnDptElg, RisplDkPrdvnRule> risplDkPrdvnRule;
}
