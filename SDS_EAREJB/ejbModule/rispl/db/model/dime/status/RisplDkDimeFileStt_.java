package rispl.db.model.dime.status;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.865+0530")
@StaticMetamodel(RisplDkDimeFileStt.class)
public class RisplDkDimeFileStt_ {
	public static volatile SingularAttribute<RisplDkDimeFileStt, RisplDkDimeFileSttPK> id;
	public static volatile SingularAttribute<RisplDkDimeFileStt, String> csStsFl;
	public static volatile SingularAttribute<RisplDkDimeFileStt, Timestamp> tsEndImpPrc;
	public static volatile SingularAttribute<RisplDkDimeFileStt, Timestamp> tsStrImpPrc;
	public static volatile SingularAttribute<RisplDkDimeFileStt, String> errMsg;
}
