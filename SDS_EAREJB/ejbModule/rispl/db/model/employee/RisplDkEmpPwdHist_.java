package rispl.db.model.employee;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.997+0530")
@StaticMetamodel(RisplDkEmpPwdHist.class)
public class RisplDkEmpPwdHist_ {
	public static volatile SingularAttribute<RisplDkEmpPwdHist, RisplDkEmpPwdHistPK> id;
	public static volatile SingularAttribute<RisplDkEmpPwdHist, String> pwAcsEm;
	public static volatile SingularAttribute<RisplDkEmpPwdHist, String> pwSltEm;
	public static volatile SingularAttribute<RisplDkEmpPwdHist, Timestamp> tsCrtPw;
	public static volatile SingularAttribute<RisplDkEmpPwdHist, RisplDkEmpMstr> risplDkEmpMstr;
}
