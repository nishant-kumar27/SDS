package rispl.db.model.tax;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.699+0530")
@StaticMetamodel(RisplDkTaxGrpRule.class)
public class RisplDkTaxGrpRule_ {
	public static volatile SingularAttribute<RisplDkTaxGrpRule, RisplDkTaxGrpRulePK> id;
	public static volatile SingularAttribute<RisplDkTaxGrpRule, BigDecimal> aiCmpnd;
	public static volatile SingularAttribute<RisplDkTaxGrpRule, BigDecimal> calMthCd;
	public static volatile SingularAttribute<RisplDkTaxGrpRule, BigDecimal> cdTxRtRuUsg;
	public static volatile SingularAttribute<RisplDkTaxGrpRule, String> incTaxFlg;
	public static volatile SingularAttribute<RisplDkTaxGrpRule, String> taxGsAmtFlg;
	public static volatile SingularAttribute<RisplDkTaxGrpRule, String> taxHldyFlg;
	public static volatile SingularAttribute<RisplDkTaxGrpRule, String> taxRulDes;
	public static volatile SingularAttribute<RisplDkTaxGrpRule, String> taxRulNme;
	public static volatile SingularAttribute<RisplDkTaxGrpRule, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkTaxGrpRule, Timestamp> tsMdfRcrd;
}
