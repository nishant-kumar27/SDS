package rispl.dkart.services.entities.customer;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:31.034+0530")
@StaticMetamodel(CustomerSite.class)
public class CustomerSite_ {
	public static volatile SingularAttribute<CustomerSite, CustomerSitePK> customerSitePK;
	public static volatile SingularAttribute<CustomerSite, String> tyCnct;
	public static volatile SingularAttribute<CustomerSite, BigInteger> prcgpId;
	public static volatile SingularAttribute<CustomerSite, String> fnCnct;
	public static volatile SingularAttribute<CustomerSite, String> mdCnct;
	public static volatile SingularAttribute<CustomerSite, String> lnCnct;
	public static volatile SingularAttribute<CustomerSite, String> nmCnct;
	public static volatile SingularAttribute<CustomerSite, String> luCnctSln;
	public static volatile SingularAttribute<CustomerSite, String> nmCnctSfx;
	public static volatile SingularAttribute<CustomerSite, String> dcCnct;
	public static volatile SingularAttribute<CustomerSite, Character> gndrCnct;
	public static volatile SingularAttribute<CustomerSite, String> coNmCnct;
	public static volatile SingularAttribute<CustomerSite, String> ctEmlId;
	public static volatile SingularAttribute<CustomerSite, Character> noPhnCnct;
	public static volatile SingularAttribute<CustomerSite, Character> noEmlCnct;
	public static volatile SingularAttribute<CustomerSite, Character> cdPrfRcptCnct;
	public static volatile SingularAttribute<CustomerSite, String> uprLnCnct;
	public static volatile SingularAttribute<CustomerSite, String> uprFnCnct;
	public static volatile ListAttribute<CustomerSite, CustomerSiteAddress> customerSiteAddressList;
	public static volatile ListAttribute<CustomerSite, CustomerSiteInvoice> customerSiteInvoiceList;
	public static volatile SingularAttribute<CustomerSite, CustomerSitePaymentTerms> customerPaymentTerms;
	public static volatile ListAttribute<CustomerSite, CustomerSiteLimit> customerSiteLimitList;
	public static volatile ListAttribute<CustomerSite, CustomerSitePhone> customerSitePhoneList;
	public static volatile ListAttribute<CustomerSite, CustomerSiteStore> customerSiteStoreList;
}
