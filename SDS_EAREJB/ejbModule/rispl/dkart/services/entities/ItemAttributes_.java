package rispl.dkart.services.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:31.541+0530")
@StaticMetamodel(ItemAttributes.class)
public class ItemAttributes_ {
	public static volatile SingularAttribute<ItemAttributes, ItemAttributesPK> id;
	public static volatile SingularAttribute<ItemAttributes, String> empDisAlwFlg;
	public static volatile SingularAttribute<ItemAttributes, BigDecimal> itmMnmSlQnt;
	public static volatile SingularAttribute<ItemAttributes, BigDecimal> itmMxmSlQnt;
	public static volatile SingularAttribute<ItemAttributes, String> itmRstkFeFlg;
	public static volatile SingularAttribute<ItemAttributes, String> itmUomCd;
	public static volatile SingularAttribute<ItemAttributes, String> lbTmpltId;
	public static volatile SingularAttribute<ItemAttributes, String> prcEntrRqFlg;
	public static volatile SingularAttribute<ItemAttributes, String> prhRtnFl;
	public static volatile SingularAttribute<ItemAttributes, BigDecimal> rpPrcCmprAtSls;
	public static volatile SingularAttribute<ItemAttributes, String> rtPrcMdfrFlg;
	public static volatile SingularAttribute<ItemAttributes, BigDecimal> slsAgRstIdn;
	public static volatile SingularAttribute<ItemAttributes, String> slsUomCd;
	public static volatile SingularAttribute<ItemAttributes, String> spoElgFlg;
	public static volatile SingularAttribute<ItemAttributes, BigDecimal> txGpId;
	public static volatile SingularAttribute<ItemAttributes, String> whtEntrRqFlg;
	public static volatile SingularAttribute<ItemAttributes, Item> itm;
}
