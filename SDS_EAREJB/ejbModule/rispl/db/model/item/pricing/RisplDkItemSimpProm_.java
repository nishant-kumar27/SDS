package rispl.db.model.item.pricing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.342+0530")
@StaticMetamodel(RisplDkItemSimpProm.class)
public class RisplDkItemSimpProm_ {
	public static volatile SingularAttribute<RisplDkItemSimpProm, RisplDkItemSimpPromPK> id;
	public static volatile SingularAttribute<RisplDkItemSimpProm, BigDecimal> custPrcgpId;
	public static volatile SingularAttribute<RisplDkItemSimpProm, String> evDesc;
	public static volatile SingularAttribute<RisplDkItemSimpProm, BigDecimal> promAmt;
	public static volatile SingularAttribute<RisplDkItemSimpProm, BigDecimal> promCmpDtlId;
	public static volatile SingularAttribute<RisplDkItemSimpProm, BigDecimal> promCmpId;
	public static volatile SingularAttribute<RisplDkItemSimpProm, Timestamp> promEfctDtTm;
	public static volatile SingularAttribute<RisplDkItemSimpProm, Timestamp> promExpDtTm;
	public static volatile SingularAttribute<RisplDkItemSimpProm, BigDecimal> promId;
	public static volatile SingularAttribute<RisplDkItemSimpProm, BigDecimal> promOvrdPrc;
	public static volatile SingularAttribute<RisplDkItemSimpProm, BigDecimal> promPri;
	public static volatile SingularAttribute<RisplDkItemSimpProm, String> promTypCd;
}
