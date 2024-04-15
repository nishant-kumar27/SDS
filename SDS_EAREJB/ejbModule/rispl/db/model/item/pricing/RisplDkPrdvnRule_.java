package rispl.db.model.item.pricing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:29.560+0530")
@StaticMetamodel(RisplDkPrdvnRule.class)
public class RisplDkPrdvnRule_ {
	public static volatile SingularAttribute<RisplDkPrdvnRule, RisplDkPrdvnRulePK> id;
	public static volatile SingularAttribute<RisplDkPrdvnRule, String> cdBasCmpSrc;
	public static volatile SingularAttribute<RisplDkPrdvnRule, String> cdBasCmpTgt;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> cdBasPrdv;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> cdScpPrdv;
	public static volatile SingularAttribute<RisplDkPrdvnRule, Timestamp> dcRuPrdvEf;
	public static volatile SingularAttribute<RisplDkPrdvnRule, Timestamp> dcRuPrdvEp;
	public static volatile SingularAttribute<RisplDkPrdvnRule, String> deRuPrdv;
	public static volatile SingularAttribute<RisplDkPrdvnRule, String> dpLdgStkMdfr;
	public static volatile SingularAttribute<RisplDkPrdvnRule, String> flAlwRptSrc;
	public static volatile SingularAttribute<RisplDkPrdvnRule, String> flDlDst;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> idPrcgp;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> idPrm;
	public static volatile SingularAttribute<RisplDkPrdvnRule, String> itmPrcCtgySrc;
	public static volatile SingularAttribute<RisplDkPrdvnRule, String> itmPrcCtgyTgt;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> moLmSrc;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> moLmTgt;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> moThSrc;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> moThTgt;
	public static volatile SingularAttribute<RisplDkPrdvnRule, String> nmRuPrdv;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> quAnSrc;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> quAnTgt;
	public static volatile SingularAttribute<RisplDkPrdvnRule, BigDecimal> quLmAply;
	public static volatile SingularAttribute<RisplDkPrdvnRule, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkPrdvnRule, Timestamp> tsMdfRcrd;
	public static volatile ListAttribute<RisplDkPrdvnRule, RisplDkPrdvnDptElg> risplDkPrdvnDptElgs;
	public static volatile ListAttribute<RisplDkPrdvnRule, RisplDkPrdvnItmNelg> risplDkPrdvnItmNelgs;
	public static volatile ListAttribute<RisplDkPrdvnRule, RisplDkPrdvnMmitm> risplDkPrdvnMmitms;
	public static volatile ListAttribute<RisplDkPrdvnRule, RisplDkPrdvnMrchElg> risplDkPrdvnMrchElgs;
	public static volatile ListAttribute<RisplDkPrdvnRule, RisplDkPrdvnRuleDisc> risplDkPrdvnRuleDiscs;
	public static volatile ListAttribute<RisplDkPrdvnRule, RisplDkPrdvnRuleElg> risplDkPrdvnRuleElgs;
	public static volatile ListAttribute<RisplDkPrdvnRule, RisplDkPrdvnTrshldElg> risplDkPrdvnTrshldElgs;
}
