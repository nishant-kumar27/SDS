package rispl.db.model.merchandisehierarchy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.218+0530")
@StaticMetamodel(RisplDkMrchLvl.class)
public class RisplDkMrchLvl_ {
	public static volatile SingularAttribute<RisplDkMrchLvl, RisplDkMrchLvlPK> id;
	public static volatile SingularAttribute<RisplDkMrchLvl, BigDecimal> idMrhrcFnc;
	public static volatile SingularAttribute<RisplDkMrchLvl, BigDecimal> idMrhrcLvPrnt;
	public static volatile SingularAttribute<RisplDkMrchLvl, String> nmMrhrcLv;
	public static volatile SingularAttribute<RisplDkMrchLvl, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkMrchLvl, Timestamp> tsMdfRcrd;
}
