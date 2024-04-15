package rispl.dkart.services.entities.customer;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.977+0530")
@StaticMetamodel(CustomerLimit.class)
public class CustomerLimit_ {
	public static volatile SingularAttribute<CustomerLimit, CustomerLimitPK> customerLimitPK;
	public static volatile SingularAttribute<CustomerLimit, Character> status;
	public static volatile SingularAttribute<CustomerLimit, BigDecimal> crdtLimit;
	public static volatile SingularAttribute<CustomerLimit, BigDecimal> avCrdtLimit;
	public static volatile SingularAttribute<CustomerLimit, BigDecimal> orderLimit;
	public static volatile SingularAttribute<CustomerLimit, BigDecimal> pendDue;
}
