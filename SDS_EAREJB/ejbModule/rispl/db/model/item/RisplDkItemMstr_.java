package rispl.db.model.item;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.869+0530")
@StaticMetamodel(RisplDkItemMstr.class)
public class RisplDkItemMstr_ {
	public static volatile SingularAttribute<RisplDkItemMstr, RisplDkItemMstrPK> id;
	public static volatile SingularAttribute<RisplDkItemMstr, BigDecimal> dispMsgId;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmBrnNm;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmClrCd;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmDesc;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmDmgDscFlg;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmDscFlg;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmImgLoc;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmKtId;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmKtStCd;
	public static volatile SingularAttribute<RisplDkItemMstr, BigDecimal> itmMfId;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmMrcHrcLvCd;
	public static volatile SingularAttribute<RisplDkItemMstr, BigDecimal> itmMrcStrcId;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmMrhrcGpId;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmPosDptId;
	public static volatile SingularAttribute<RisplDkItemMstr, Timestamp> itmRcrdCrtTs;
	public static volatile SingularAttribute<RisplDkItemMstr, Timestamp> itmRcrdMdfTs;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmRgstryFl;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmSbstIdnFlg;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmShrtDesc;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmSlsAznFlg;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmSrlzdFlg;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmSrzCptTmCd;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmSzCd;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmSzReqFlg;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmTxExmCd;
	public static volatile SingularAttribute<RisplDkItemMstr, BigDecimal> itmTxGpId;
	public static volatile SingularAttribute<RisplDkItemMstr, String> itmTyCd;
}
