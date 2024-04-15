package rispl.db.model.merchandisehierarchy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.062+0530")
@StaticMetamodel(RisplDkMrchAssn.class)
public class RisplDkMrchAssn_ {
	public static volatile SingularAttribute<RisplDkMrchAssn, RisplDkMrchAssnPK> id;
	public static volatile SingularAttribute<RisplDkMrchAssn, BigDecimal> idMrhrcFnc;
	public static volatile SingularAttribute<RisplDkMrchAssn, BigDecimal> idMrhrcLv;
	public static volatile SingularAttribute<RisplDkMrchAssn, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkMrchAssn, Timestamp> tsMdfRcrd;
}
