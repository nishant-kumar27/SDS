package rispl.db.model.merchandisehierarchy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.137+0530")
@StaticMetamodel(RisplDkMrchGrp.class)
public class RisplDkMrchGrp_ {
	public static volatile SingularAttribute<RisplDkMrchGrp, RisplDkMrchGrpPK> id;
	public static volatile SingularAttribute<RisplDkMrchGrp, String> deMrhrcGp;
	public static volatile SingularAttribute<RisplDkMrchGrp, BigDecimal> idPst;
	public static volatile SingularAttribute<RisplDkMrchGrp, String> nmMrhrcGp;
	public static volatile SingularAttribute<RisplDkMrchGrp, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkMrchGrp, Timestamp> tsMdfRcrd;
}
