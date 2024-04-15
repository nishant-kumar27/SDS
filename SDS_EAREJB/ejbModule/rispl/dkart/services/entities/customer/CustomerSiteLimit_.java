package rispl.dkart.services.entities.customer;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:31.155+0530")
@StaticMetamodel(CustomerSiteLimit.class)
public class CustomerSiteLimit_ {
	public static volatile SingularAttribute<CustomerSiteLimit, BigDecimal> lmtId;
	public static volatile SingularAttribute<CustomerSiteLimit, Character> siteStatus;
	public static volatile SingularAttribute<CustomerSiteLimit, BigDecimal> siteCrdtLimit;
	public static volatile SingularAttribute<CustomerSiteLimit, BigDecimal> siteAvCrdtLimit;
	public static volatile SingularAttribute<CustomerSiteLimit, BigDecimal> siteOrderLimit;
	public static volatile SingularAttribute<CustomerSiteLimit, BigDecimal> sitePendDue;
	public static volatile SingularAttribute<CustomerSiteLimit, CustomerSite> customerSite;
}
