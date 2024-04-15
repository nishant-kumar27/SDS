package rispl.db.model.employee;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.928+0530")
@StaticMetamodel(RisplDkEmpMstr.class)
public class RisplDkEmpMstr_ {
	public static volatile SingularAttribute<RisplDkEmpMstr, RisplDkEmpMstrPK> id;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> altId;
	public static volatile SingularAttribute<RisplDkEmpMstr, Date> dcExpTmp;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> empAcsPwd;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> empFstNme;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> empLcl;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> empLstNme;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> empMdlNme;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> empNme;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> empPwdSlt;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> empStsCde;
	public static volatile SingularAttribute<RisplDkEmpMstr, BigDecimal> empType;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> flPwNwReq;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> gpId;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> gpType;
	public static volatile SingularAttribute<RisplDkEmpMstr, BigDecimal> idStrgpFnc;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> loginId;
	public static volatile SingularAttribute<RisplDkEmpMstr, BigDecimal> numbDysVld;
	public static volatile SingularAttribute<RisplDkEmpMstr, BigDecimal> numbFldPw;
	public static volatile SingularAttribute<RisplDkEmpMstr, BigDecimal> prtyId;
	public static volatile SingularAttribute<RisplDkEmpMstr, Date> tsCrtPw;
	public static volatile SingularAttribute<RisplDkEmpMstr, Date> tsLoginLst;
	public static volatile SingularAttribute<RisplDkEmpMstr, String> email;
	public static volatile SingularAttribute<RisplDkEmpMstr, RisplDkEmpRole> risplDkEmpRole;
	public static volatile SingularAttribute<RisplDkEmpMstr, RisplDkEmpPwdHist> risplDkEmpPwdHist;
	public static volatile ListAttribute<RisplDkEmpMstr, RisplDkEmpMerchAssoc> risplDkEmpMerchAssocs;
}
