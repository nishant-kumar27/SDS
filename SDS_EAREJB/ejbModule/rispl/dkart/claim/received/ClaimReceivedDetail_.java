package rispl.dkart.claim.received;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.894+0530")
@StaticMetamodel(ClaimReceivedDetail.class)
public class ClaimReceivedDetail_ {
	public static volatile SingularAttribute<ClaimReceivedDetail, ClaimReceivedDetailPK> id;
	public static volatile SingularAttribute<ClaimReceivedDetail, String> dkWmsBtch;
	public static volatile SingularAttribute<ClaimReceivedDetail, String> postFlag;
	public static volatile SingularAttribute<ClaimReceivedDetail, BigDecimal> whRcvQty;
}
