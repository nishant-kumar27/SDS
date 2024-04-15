package rispl.db.model.item.pricing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.494+0530")
@StaticMetamodel(RisplDkPrdvnMmitm.class)
public class RisplDkPrdvnMmitm_ {
	public static volatile SingularAttribute<RisplDkPrdvnMmitm, String> idPrmPrd;
	public static volatile SingularAttribute<RisplDkPrdvnMmitm, BigDecimal> moRdnPrcMxmh;
	public static volatile SingularAttribute<RisplDkPrdvnMmitm, BigDecimal> peRdnPrcMxmh;
	public static volatile SingularAttribute<RisplDkPrdvnMmitm, BigDecimal> pntPrcRdnMxmh;
	public static volatile SingularAttribute<RisplDkPrdvnMmitm, BigDecimal> quLmMxmh;
	public static volatile SingularAttribute<RisplDkPrdvnMmitm, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnMmitm, Timestamp> tsMdfRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnMmitm, BigDecimal> unqId;
	public static volatile SingularAttribute<RisplDkPrdvnMmitm, RisplDkPrdvnRule> risplDkPrdvnRule;
}
