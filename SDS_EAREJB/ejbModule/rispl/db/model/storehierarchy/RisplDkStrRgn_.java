package rispl.db.model.storehierarchy;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.614+0530")
@StaticMetamodel(RisplDkStrRgn.class)
public class RisplDkStrRgn_ {
	public static volatile SingularAttribute<RisplDkStrRgn, RisplDkStrRgnPK> id;
	public static volatile SingularAttribute<RisplDkStrRgn, String> nmStrRgn;
	public static volatile SingularAttribute<RisplDkStrRgn, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkStrRgn, Timestamp> tsMdfRcrd;
}
