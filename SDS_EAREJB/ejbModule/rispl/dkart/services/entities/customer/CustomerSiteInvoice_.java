package rispl.dkart.services.entities.customer;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rispl.dkart.services.entities.transaction.OrdInvQtyShp;

@Generated(value="Dali", date="2018-01-09T13:30:31.081+0530")
@StaticMetamodel(CustomerSiteInvoice.class)
public class CustomerSiteInvoice_ {
	public static volatile SingularAttribute<CustomerSiteInvoice, BigDecimal> invId;
	public static volatile SingularAttribute<CustomerSiteInvoice, String> arInvNum;
	public static volatile SingularAttribute<CustomerSiteInvoice, Date> arInvDate;
	public static volatile SingularAttribute<CustomerSiteInvoice, String> orderNum;
	public static volatile SingularAttribute<CustomerSiteInvoice, Date> orderDate;
	public static volatile SingularAttribute<CustomerSiteInvoice, Character> invStatus;
	public static volatile SingularAttribute<CustomerSiteInvoice, BigDecimal> invAmount;
	public static volatile SingularAttribute<CustomerSiteInvoice, BigDecimal> invPendAmount;
	public static volatile SingularAttribute<CustomerSiteInvoice, Date> invCloseDate;
	public static volatile SingularAttribute<CustomerSiteInvoice, String> storeId;
	public static volatile SingularAttribute<CustomerSiteInvoice, String> refCustId;
	public static volatile SingularAttribute<CustomerSiteInvoice, CustomerSite> customerSite;
	public static volatile ListAttribute<CustomerSiteInvoice, OrdInvQtyShp> invQtyShp;
}
