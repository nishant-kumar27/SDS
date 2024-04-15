package rispl.db.model.storehierarchy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.542+0530")
@StaticMetamodel(RisplDkStrgpLvl.class)
public class RisplDkStrgpLvl_ {
	public static volatile SingularAttribute<RisplDkStrgpLvl, RisplDkStrgpLvlPK> id;
	public static volatile SingularAttribute<RisplDkStrgpLvl, BigDecimal> idStrgpLvPrnt;
	public static volatile SingularAttribute<RisplDkStrgpLvl, String> nmStrgpLv;
	public static volatile SingularAttribute<RisplDkStrgpLvl, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkStrgpLvl, Timestamp> tsMdfRcrd;
}
