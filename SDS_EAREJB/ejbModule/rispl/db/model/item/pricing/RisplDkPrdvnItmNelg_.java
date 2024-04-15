package rispl.db.model.item.pricing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.460+0530")
@StaticMetamodel(RisplDkPrdvnItmNelg.class)
public class RisplDkPrdvnItmNelg_ {
	public static volatile SingularAttribute<RisplDkPrdvnItmNelg, String> itmId;
	public static volatile SingularAttribute<RisplDkPrdvnItmNelg, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnItmNelg, Timestamp> tsMdfRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnItmNelg, Timestamp> tsNelEf;
	public static volatile SingularAttribute<RisplDkPrdvnItmNelg, BigDecimal> unqId;
	public static volatile SingularAttribute<RisplDkPrdvnItmNelg, RisplDkPrdvnRule> risplDkPrdvnRule;
}
