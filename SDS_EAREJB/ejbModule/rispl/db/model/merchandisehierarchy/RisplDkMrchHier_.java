package rispl.db.model.merchandisehierarchy;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.177+0530")
@StaticMetamodel(RisplDkMrchHier.class)
public class RisplDkMrchHier_ {
	public static volatile SingularAttribute<RisplDkMrchHier, RisplDkMrchHierPK> id;
	public static volatile SingularAttribute<RisplDkMrchHier, String> nmMrhrcFnc;
	public static volatile SingularAttribute<RisplDkMrchHier, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkMrchHier, Timestamp> tsMdfRcrd;
}
