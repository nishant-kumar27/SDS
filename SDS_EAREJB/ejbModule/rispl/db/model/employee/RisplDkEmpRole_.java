package rispl.db.model.employee;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.062+0530")
@StaticMetamodel(RisplDkEmpRole.class)
public class RisplDkEmpRole_ {
	public static volatile SingularAttribute<RisplDkEmpRole, Long> roleId;
	public static volatile SingularAttribute<RisplDkEmpRole, BigDecimal> createdByUserId;
	public static volatile SingularAttribute<RisplDkEmpRole, Timestamp> createdDatetime;
	public static volatile SingularAttribute<RisplDkEmpRole, Timestamp> effectiveDatetime;
	public static volatile SingularAttribute<RisplDkEmpRole, Timestamp> endDatetime;
	public static volatile SingularAttribute<RisplDkEmpRole, String> roleDesc;
	public static volatile SingularAttribute<RisplDkEmpRole, String> homePage;
	public static volatile SingularAttribute<RisplDkEmpRole, String> searchCriteria;
	public static volatile ListAttribute<RisplDkEmpRole, RisplDkEmpMstr> risplDkEmpMstrs;
	public static volatile ListAttribute<RisplDkEmpRole, RisplDkEmpRoleAccess> risplDkEmpRoleAccesses;
}
