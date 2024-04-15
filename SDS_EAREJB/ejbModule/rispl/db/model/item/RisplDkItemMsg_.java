package rispl.db.model.item;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.832+0530")
@StaticMetamodel(RisplDkItemMsg.class)
public class RisplDkItemMsg_ {
	public static volatile SingularAttribute<RisplDkItemMsg, RisplDkItemMsgPK> id;
	public static volatile SingularAttribute<RisplDkItemMsg, BigDecimal> dplyLocTyp;
	public static volatile SingularAttribute<RisplDkItemMsg, String> naMsgDply;
	public static volatile SingularAttribute<RisplDkItemMsg, String> nmMsgDply;
	public static volatile SingularAttribute<RisplDkItemMsg, BigDecimal> usgTrnTyp;
}
