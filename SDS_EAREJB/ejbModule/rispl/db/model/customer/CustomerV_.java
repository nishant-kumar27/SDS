package rispl.db.model.customer;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.781+0530")
@StaticMetamodel(CustomerV.class)
public class CustomerV_ {
	public static volatile SingularAttribute<CustomerV, String> division;
	public static volatile SingularAttribute<CustomerV, String> segment;
	public static volatile SingularAttribute<CustomerV, String> customerId;
	public static volatile SingularAttribute<CustomerV, String> customerName;
	public static volatile SingularAttribute<CustomerV, String> linkedEmpId;
	public static volatile SingularAttribute<CustomerV, BigDecimal> creditLimit;
	public static volatile SingularAttribute<CustomerV, BigDecimal> availCreditLimit;
}
