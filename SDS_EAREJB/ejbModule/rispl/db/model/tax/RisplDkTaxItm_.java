package rispl.db.model.tax;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.732+0530")
@StaticMetamodel(RisplDkTaxItm.class)
public class RisplDkTaxItm_ {
	public static volatile SingularAttribute<RisplDkTaxItm, RisplDkTaxItmPK> id;
	public static volatile SingularAttribute<RisplDkTaxItm, BigDecimal> cdRcvPrt;
	public static volatile SingularAttribute<RisplDkTaxItm, String> deGpTx;
	public static volatile SingularAttribute<RisplDkTaxItm, String> nmGpTx;
	public static volatile SingularAttribute<RisplDkTaxItm, Timestamp> tsCrtRcrd;
	public static volatile SingularAttribute<RisplDkTaxItm, Timestamp> tsMdfRcrd;
}
