package rispl.db.model.item.pricing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.286+0530")
@StaticMetamodel(RisplDkItemPrice.class)
public class RisplDkItemPrice_ {
	public static volatile SingularAttribute<RisplDkItemPrice, RisplDkItemPricePK> id;
	public static volatile SingularAttribute<RisplDkItemPrice, Timestamp> evEfTmp;
	public static volatile SingularAttribute<RisplDkItemPrice, BigDecimal> evPri;
	public static volatile SingularAttribute<RisplDkItemPrice, String> evntTyp;
	public static volatile SingularAttribute<RisplDkItemPrice, String> itmId;
	public static volatile SingularAttribute<RisplDkItemPrice, BigDecimal> moOvrdPrc;
	public static volatile SingularAttribute<RisplDkItemPrice, BigDecimal> slUnAmt;
	public static volatile SingularAttribute<RisplDkItemPrice, String> slUnAmtTypCd;
}
