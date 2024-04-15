package rispl.db.model.storehierarchy;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.485+0530")
@StaticMetamodel(RisplDkStrgpFnc.class)
public class RisplDkStrgpFnc_ {
	public static volatile SingularAttribute<RisplDkStrgpFnc, RisplDkStrgpFncPK> id;
	public static volatile SingularAttribute<RisplDkStrgpFnc, String> nmStrgpFnc;
	public static volatile SingularAttribute<RisplDkStrgpFnc, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkStrgpFnc, Timestamp> tsMdfRcrd;
}
