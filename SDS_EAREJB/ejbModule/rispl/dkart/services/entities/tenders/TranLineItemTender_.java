package rispl.dkart.services.entities.tenders;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:34.850+0530")
@StaticMetamodel(TranLineItemTender.class)
public class TranLineItemTender_ {
	public static volatile SingularAttribute<TranLineItemTender, TranLineItemTenderPK> id;
	public static volatile SingularAttribute<TranLineItemTender, String> dkDeCnyLcl;
	public static volatile SingularAttribute<TranLineItemTender, String> dkFrgCnctCny;
	public static volatile SingularAttribute<TranLineItemTender, String> dkFrgDeCny;
	public static volatile SingularAttribute<TranLineItemTender, BigDecimal> dkRtToBuy;
	public static volatile SingularAttribute<TranLineItemTender, Date> dkTsCrtRcrd;
	public static volatile SingularAttribute<TranLineItemTender, String> dkTsMdfRcrd;
	public static volatile SingularAttribute<TranLineItemTender, BigDecimal> frgMoItmLnTnd;
	public static volatile SingularAttribute<TranLineItemTender, String> idAcntNmb;
	public static volatile SingularAttribute<TranLineItemTender, BigDecimal> idAcntTnd;
	public static volatile SingularAttribute<TranLineItemTender, BigDecimal> idIcdCny;
	public static volatile SingularAttribute<TranLineItemTender, String> idOrd;
	public static volatile SingularAttribute<TranLineItemTender, BigDecimal> moItmLnTnd;
	public static volatile SingularAttribute<TranLineItemTender, String> tyTnd;
	public static volatile ListAttribute<TranLineItemTender, TranCheckTender> dkTranChkTnds;
	public static volatile ListAttribute<TranLineItemTender, TranVoucherTender> dkTranLtmVchrTnds;
}
