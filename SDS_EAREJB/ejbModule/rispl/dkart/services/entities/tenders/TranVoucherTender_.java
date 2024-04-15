package rispl.dkart.services.entities.tenders;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:34.877+0530")
@StaticMetamodel(TranVoucherTender.class)
public class TranVoucherTender_ {
	public static volatile SingularAttribute<TranVoucherTender, TranVoucherTenderPK> id;
	public static volatile SingularAttribute<TranVoucherTender, String> fnPrsVchr;
	public static volatile SingularAttribute<TranVoucherTender, BigDecimal> idCnyIcd;
	public static volatile SingularAttribute<TranVoucherTender, String> idIssgVchr;
	public static volatile SingularAttribute<TranVoucherTender, String> idVchr;
	public static volatile SingularAttribute<TranVoucherTender, String> lnPrsVchr;
	public static volatile SingularAttribute<TranVoucherTender, BigDecimal> moCrVchr;
	public static volatile SingularAttribute<TranVoucherTender, BigDecimal> moVlFcVchrCf;
	public static volatile SingularAttribute<TranVoucherTender, String> nmBsn;
	public static volatile SingularAttribute<TranVoucherTender, String> scVchrTnd;
	public static volatile SingularAttribute<TranVoucherTender, String> tyIdVchr;
	public static volatile SingularAttribute<TranVoucherTender, TranLineItemTender> dkTranLtmTnd;
}
