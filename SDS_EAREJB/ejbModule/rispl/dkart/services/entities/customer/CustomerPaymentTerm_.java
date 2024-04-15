package rispl.dkart.services.entities.customer;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:31.006+0530")
@StaticMetamodel(CustomerPaymentTerm.class)
public class CustomerPaymentTerm_ {
	public static volatile SingularAttribute<CustomerPaymentTerm, String> custId;
	public static volatile SingularAttribute<CustomerPaymentTerm, String> pymyPcnt;
	public static volatile SingularAttribute<CustomerPaymentTerm, BigInteger> payIn;
	public static volatile SingularAttribute<CustomerPaymentTerm, BigInteger> tolerance;
}
