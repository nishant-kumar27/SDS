package rispl.db.model.claim;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.374+0530")
@StaticMetamodel(ClaimDetail.class)
public class ClaimDetail_ {
	public static volatile SingularAttribute<ClaimDetail, ClaimDetailPK> id;
	public static volatile SingularAttribute<ClaimDetail, BigDecimal> claimTotal;
	public static volatile SingularAttribute<ClaimDetail, String> customerId;
	public static volatile SingularAttribute<ClaimDetail, String> customerName;
	public static volatile SingularAttribute<ClaimDetail, String> empId;
	public static volatile SingularAttribute<ClaimDetail, String> empName;
	public static volatile SingularAttribute<ClaimDetail, String> reasonCode;
	public static volatile SingularAttribute<ClaimDetail, String> totalQuantityReturn;
	public static volatile SingularAttribute<ClaimDetail, BigDecimal> divisionId;
	public static volatile SingularAttribute<ClaimDetail, BigDecimal> status;
}
