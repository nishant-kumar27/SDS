package rispl.db.model.dime.status;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.811+0530")
@StaticMetamodel(RisplDkDimeBndlStt.class)
public class RisplDkDimeBndlStt_ {
	public static volatile SingularAttribute<RisplDkDimeBndlStt, String> nmBndlImp;
	public static volatile SingularAttribute<RisplDkDimeBndlStt, String> scStsBndl;
	public static volatile SingularAttribute<RisplDkDimeBndlStt, Timestamp> tsEndImpPrc;
	public static volatile SingularAttribute<RisplDkDimeBndlStt, Timestamp> tsStrImpPrc;
	public static volatile SingularAttribute<RisplDkDimeBndlStt, String> errMsg;
}
