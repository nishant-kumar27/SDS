package rispl.db.model.employee;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.function.RisplDkFunc;

@Generated(value="Dali", date="2018-01-09T13:30:29.195+0530")
@StaticMetamodel(RisplDkEmpRoleAccess.class)
public class RisplDkEmpRoleAccess_ {
	public static volatile SingularAttribute<RisplDkEmpRoleAccess, RisplDkEmpRoleAccessPK> id;
	public static volatile SingularAttribute<RisplDkEmpRoleAccess, String> createdByUserId;
	public static volatile SingularAttribute<RisplDkEmpRoleAccess, Timestamp> createdDatetime;
	public static volatile SingularAttribute<RisplDkEmpRoleAccess, Timestamp> effectiveDatetime;
	public static volatile SingularAttribute<RisplDkEmpRoleAccess, Timestamp> endDatetime;
	public static volatile SingularAttribute<RisplDkEmpRoleAccess, String> hasAccess;
	public static volatile SingularAttribute<RisplDkEmpRoleAccess, RisplDkFunc> risplDkFunc;
	public static volatile SingularAttribute<RisplDkEmpRoleAccess, RisplDkEmpRole> risplDkEmpRole;
}
