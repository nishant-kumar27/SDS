package rispl.dkart.services.entities.customer;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:31.170+0530")
@StaticMetamodel(CustomerSitePaymentTerms.class)
public class CustomerSitePaymentTerms_ {
	public static volatile SingularAttribute<CustomerSitePaymentTerms, CustomerSitePaymentTermsPK> customerSitePaymentTermsPK;
	public static volatile SingularAttribute<CustomerSitePaymentTerms, String> pymtPcnt;
	public static volatile SingularAttribute<CustomerSitePaymentTerms, BigInteger> payIn;
	public static volatile SingularAttribute<CustomerSitePaymentTerms, BigInteger> tolerance;
	public static volatile SingularAttribute<CustomerSitePaymentTerms, CustomerSite> customerSite;
}
