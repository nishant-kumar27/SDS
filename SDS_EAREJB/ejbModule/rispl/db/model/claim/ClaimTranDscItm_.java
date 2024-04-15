package rispl.db.model.claim;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.494+0530")
@StaticMetamodel(ClaimTranDscItm.class)
public class ClaimTranDscItm_ {
	public static volatile SingularAttribute<ClaimTranDscItm, ClaimTranDscItmPK> id;
	public static volatile SingularAttribute<ClaimTranDscItm, BigDecimal> dscAmt;
	public static volatile SingularAttribute<ClaimTranDscItm, BigDecimal> dscPer;
	public static volatile SingularAttribute<ClaimTranDscItm, BigDecimal> prmCmpDtlid;
	public static volatile SingularAttribute<ClaimTranDscItm, BigDecimal> prmCmpId;
	public static volatile SingularAttribute<ClaimTranDscItm, String> prmDesc;
	public static volatile SingularAttribute<ClaimTranDscItm, BigDecimal> prmId;
	public static volatile SingularAttribute<ClaimTranDscItm, BigDecimal> prmType;
	public static volatile SingularAttribute<ClaimTranDscItm, String> srcTrgList;
	public static volatile SingularAttribute<ClaimTranDscItm, BigDecimal> tyDsc;
	public static volatile SingularAttribute<ClaimTranDscItm, BigDecimal> unitDscAmt;
	public static volatile SingularAttribute<ClaimTranDscItm, String> discReasonCode;
	public static volatile SingularAttribute<ClaimTranDscItm, ClaimTranLineItem> claimTranLineItem;
}
