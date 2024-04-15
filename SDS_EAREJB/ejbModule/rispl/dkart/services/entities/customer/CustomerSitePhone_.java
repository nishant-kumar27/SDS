package rispl.dkart.services.entities.customer;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:31.200+0530")
@StaticMetamodel(CustomerSitePhone.class)
public class CustomerSitePhone_ {
	public static volatile SingularAttribute<CustomerSitePhone, BigDecimal> custPhnId;
	public static volatile SingularAttribute<CustomerSitePhone, String> ccCnct;
	public static volatile SingularAttribute<CustomerSitePhone, String> taCnct;
	public static volatile SingularAttribute<CustomerSitePhone, String> tlCnct;
	public static volatile SingularAttribute<CustomerSitePhone, CustomerSite> customerSite;
}
