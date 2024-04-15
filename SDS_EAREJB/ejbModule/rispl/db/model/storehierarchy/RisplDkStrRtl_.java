package rispl.db.model.storehierarchy;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.652+0530")
@StaticMetamodel(RisplDkStrRtl.class)
public class RisplDkStrRtl_ {
	public static volatile SingularAttribute<RisplDkStrRtl, RisplDkStrRtlPK> id;
	public static volatile SingularAttribute<RisplDkStrRtl, String> idCdGeo;
	public static volatile SingularAttribute<RisplDkStrRtl, String> idStrDstrct;
	public static volatile SingularAttribute<RisplDkStrRtl, String> idStrRgn;
	public static volatile SingularAttribute<RisplDkStrRtl, String> nmLoc;
	public static volatile SingularAttribute<RisplDkStrRtl, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkStrRtl, Timestamp> tsMdfRcrd;
}
