package rispl.dkart.services.entities.customer;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:31.233+0530")
@StaticMetamodel(CustomerSiteStore.class)
public class CustomerSiteStore_ {
	public static volatile SingularAttribute<CustomerSiteStore, BigDecimal> id;
	public static volatile SingularAttribute<CustomerSiteStore, String> storeId;
	public static volatile SingularAttribute<CustomerSiteStore, CustomerSite> customerSite;
}
