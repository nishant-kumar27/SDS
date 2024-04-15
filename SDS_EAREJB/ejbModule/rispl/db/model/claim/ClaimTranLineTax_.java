package rispl.db.model.claim;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.558+0530")
@StaticMetamodel(ClaimTranLineTax.class)
public class ClaimTranLineTax_ {
	public static volatile SingularAttribute<ClaimTranLineTax, Long> tranLineId;
	public static volatile SingularAttribute<ClaimTranLineTax, String> dcDyOrd;
	public static volatile SingularAttribute<ClaimTranLineTax, BigDecimal> ordLnItmSeq;
	public static volatile SingularAttribute<ClaimTranLineTax, String> ordWs;
	public static volatile SingularAttribute<ClaimTranLineTax, String> rtStrId;
	public static volatile SingularAttribute<ClaimTranLineTax, String> taxAuth;
	public static volatile SingularAttribute<ClaimTranLineTax, String> taxCode;
	public static volatile SingularAttribute<ClaimTranLineTax, BigDecimal> taxRate;
	public static volatile SingularAttribute<ClaimTranLineTax, BigDecimal> trnSeq;
	public static volatile SingularAttribute<ClaimTranLineTax, BigDecimal> vatAmtLnItm;
}
