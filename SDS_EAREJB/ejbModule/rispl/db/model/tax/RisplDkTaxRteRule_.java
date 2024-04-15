package rispl.db.model.tax;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.825+0530")
@StaticMetamodel(RisplDkTaxRteRule.class)
public class RisplDkTaxRteRule_ {
	public static volatile SingularAttribute<RisplDkTaxRteRule, RisplDkTaxRteRulePK> id;
	public static volatile SingularAttribute<RisplDkTaxRteRule, BigDecimal> cdTyp;
	public static volatile SingularAttribute<RisplDkTaxRteRule, String> flTxAbvThMo;
	public static volatile SingularAttribute<RisplDkTaxRteRule, BigDecimal> maxTxblAmt;
	public static volatile SingularAttribute<RisplDkTaxRteRule, BigDecimal> minTxblAmt;
	public static volatile SingularAttribute<RisplDkTaxRteRule, BigDecimal> taxAmt;
	public static volatile SingularAttribute<RisplDkTaxRteRule, BigDecimal> taxPercnt;
	public static volatile SingularAttribute<RisplDkTaxRteRule, BigDecimal> taxThrshldAmt;
	public static volatile SingularAttribute<RisplDkTaxRteRule, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkTaxRteRule, Timestamp> tsMdfRcrd;
	public static volatile SingularAttribute<RisplDkTaxRteRule, Timestamp> tsRtTxEf;
	public static volatile SingularAttribute<RisplDkTaxRteRule, Timestamp> tsRtTxEp;
}
