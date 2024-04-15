package rispl.db.model.storehierarchy;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.345+0530")
@StaticMetamodel(RisplDkStrDstrct.class)
public class RisplDkStrDstrct_ {
	public static volatile SingularAttribute<RisplDkStrDstrct, RisplDkStrDstrctPK> id;
	public static volatile SingularAttribute<RisplDkStrDstrct, String> idStrRgn;
	public static volatile SingularAttribute<RisplDkStrDstrct, String> nmStrDstrct;
	public static volatile SingularAttribute<RisplDkStrDstrct, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkStrDstrct, Timestamp> tsMdfRcrd;
}
