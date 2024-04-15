package rispl.dkart.services.entities.customer;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:31.018+0530")
@StaticMetamodel(CustomerSegment.class)
public class CustomerSegment_ {
	public static volatile SingularAttribute<CustomerSegment, BigDecimal> prcngGrpId;
	public static volatile SingularAttribute<CustomerSegment, String> lcl;
	public static volatile SingularAttribute<CustomerSegment, String> prcngGrpNme;
	public static volatile SingularAttribute<CustomerSegment, String> prcngGrpDes;
	public static volatile ListAttribute<CustomerSegment, CustomerHeader> customerHeaderList;
}
