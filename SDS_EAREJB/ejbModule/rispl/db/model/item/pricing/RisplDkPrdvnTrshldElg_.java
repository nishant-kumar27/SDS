package rispl.db.model.item.pricing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.679+0530")
@StaticMetamodel(RisplDkPrdvnTrshldElg.class)
public class RisplDkPrdvnTrshldElg_ {
	public static volatile SingularAttribute<RisplDkPrdvnTrshldElg, BigDecimal> idPrdvTh;
	public static volatile SingularAttribute<RisplDkPrdvnTrshldElg, BigDecimal> moUnThPrdvSls;
	public static volatile SingularAttribute<RisplDkPrdvnTrshldElg, BigDecimal> peUnThPrdvSls;
	public static volatile SingularAttribute<RisplDkPrdvnTrshldElg, BigDecimal> ptPrcThPrdvSls;
	public static volatile SingularAttribute<RisplDkPrdvnTrshldElg, BigDecimal> thVal;
	public static volatile SingularAttribute<RisplDkPrdvnTrshldElg, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnTrshldElg, Timestamp> tsMdfRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnTrshldElg, BigDecimal> unqId;
	public static volatile SingularAttribute<RisplDkPrdvnTrshldElg, RisplDkPrdvnRule> risplDkPrdvnRule;
}
