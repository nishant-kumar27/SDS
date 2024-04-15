package rispl.dkart.services.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:33.330+0530")
@StaticMetamodel(ItemPrice.class)
public class ItemPrice_ {
	public static volatile SingularAttribute<ItemPrice, ItemPricePK> id;
	public static volatile SingularAttribute<ItemPrice, Timestamp> evEfTmp;
	public static volatile SingularAttribute<ItemPrice, BigDecimal> evPri;
	public static volatile SingularAttribute<ItemPrice, String> evntTyp;
	public static volatile SingularAttribute<ItemPrice, String> itmId;
	public static volatile SingularAttribute<ItemPrice, BigDecimal> moOvrdPrc;
	public static volatile SingularAttribute<ItemPrice, BigDecimal> slUnAmt;
	public static volatile SingularAttribute<ItemPrice, String> slUnAmtTypCd;
}
