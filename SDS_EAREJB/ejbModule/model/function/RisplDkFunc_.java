package model.function;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rispl.db.model.employee.RisplDkEmpRoleAccess;

@Generated(value="Dali", date="2018-01-09T13:30:28.334+0530")
@StaticMetamodel(RisplDkFunc.class)
public class RisplDkFunc_ {
	public static volatile SingularAttribute<RisplDkFunc, String> functionId;
	public static volatile SingularAttribute<RisplDkFunc, String> createdByUserId;
	public static volatile SingularAttribute<RisplDkFunc, Date> createdDatetime;
	public static volatile SingularAttribute<RisplDkFunc, Date> effectiveDatetime;
	public static volatile SingularAttribute<RisplDkFunc, Date> endDatetime;
	public static volatile SingularAttribute<RisplDkFunc, String> functionDesc;
	public static volatile ListAttribute<RisplDkFunc, RisplDkEmpRoleAccess> risplDkEmpRoleAccesses;
}
