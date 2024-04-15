package rispl.dkart.services.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:31.523+0530")
@StaticMetamodel(Item.class)
public class Item_ {
	public static volatile SingularAttribute<Item, ItemPK> id;
	public static volatile SingularAttribute<Item, BigDecimal> dispMsgId;
	public static volatile SingularAttribute<Item, String> itmBrnNm;
	public static volatile SingularAttribute<Item, String> itmClrCd;
	public static volatile SingularAttribute<Item, String> itmDesc;
	public static volatile SingularAttribute<Item, String> itmDmgDscFlg;
	public static volatile SingularAttribute<Item, String> itmDscFlg;
	public static volatile SingularAttribute<Item, String> itmImgLoc;
	public static volatile SingularAttribute<Item, String> itmKtId;
	public static volatile SingularAttribute<Item, String> itmKtStCd;
	public static volatile SingularAttribute<Item, BigDecimal> itmMfId;
	public static volatile SingularAttribute<Item, String> itmMrcHrcLvCd;
	public static volatile SingularAttribute<Item, BigDecimal> itmMrcStrcId;
	public static volatile SingularAttribute<Item, String> itmMrhrcGpId;
	public static volatile SingularAttribute<Item, String> itmPosDptId;
	public static volatile SingularAttribute<Item, Timestamp> itmRcrdCrtTs;
	public static volatile SingularAttribute<Item, Timestamp> itmRcrdMdfTs;
	public static volatile SingularAttribute<Item, String> itmRgstryFl;
	public static volatile SingularAttribute<Item, String> itmSbstIdnFlg;
	public static volatile SingularAttribute<Item, String> itmShrtDesc;
	public static volatile SingularAttribute<Item, String> itmSlsAznFlg;
	public static volatile SingularAttribute<Item, String> itmSrlzdFlg;
	public static volatile SingularAttribute<Item, String> itmSrzCptTmCd;
	public static volatile SingularAttribute<Item, String> itmSzCd;
	public static volatile SingularAttribute<Item, String> itmSzReqFlg;
	public static volatile SingularAttribute<Item, String> itmTxExmCd;
	public static volatile SingularAttribute<Item, BigDecimal> itmTxGpId;
	public static volatile SingularAttribute<Item, String> itmTyCd;
	public static volatile SingularAttribute<Item, ItemAttributes> itm;
}
