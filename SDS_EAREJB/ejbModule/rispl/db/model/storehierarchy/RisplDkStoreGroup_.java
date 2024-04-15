package rispl.db.model.storehierarchy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.330+0530")
@StaticMetamodel(RisplDkStoreGroup.class)
public class RisplDkStoreGroup_ {
	public static volatile SingularAttribute<RisplDkStoreGroup, String> strgpId;
	public static volatile SingularAttribute<RisplDkStoreGroup, Timestamp> rcrdCrtTs;
	public static volatile SingularAttribute<RisplDkStoreGroup, Timestamp> rcrdMdfTs;
	public static volatile SingularAttribute<RisplDkStoreGroup, String> strgpDesc;
	public static volatile SingularAttribute<RisplDkStoreGroup, String> strgpFncId;
	public static volatile SingularAttribute<RisplDkStoreGroup, BigDecimal> strgpLvId;
	public static volatile SingularAttribute<RisplDkStoreGroup, String> strgpNm;
	public static volatile SingularAttribute<RisplDkStoreGroup, String> strgpTyp;
}
