package rispl.dkart.services.entities.customer;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:30.949+0530")
@StaticMetamodel(CustomerHeader.class)
public class CustomerHeader_ {
	public static volatile SingularAttribute<CustomerHeader, CustomerHeaderPK> customerHeaderPK;
	public static volatile SingularAttribute<CustomerHeader, String> ctNm;
	public static volatile SingularAttribute<CustomerHeader, String> emId;
	public static volatile SingularAttribute<CustomerHeader, Character> ctStsCd;
	public static volatile SingularAttribute<CustomerHeader, String> lcl;
	public static volatile SingularAttribute<CustomerHeader, String> idNcrptTax;
	public static volatile SingularAttribute<CustomerHeader, BigInteger> ctBtchId;
	public static volatile SingularAttribute<CustomerHeader, BigInteger> ctPhone;
	public static volatile SingularAttribute<CustomerHeader, String> ctEmlId;
	public static volatile SingularAttribute<CustomerHeader, String> ctUrl;
	public static volatile SingularAttribute<CustomerHeader, String> custTyp;
	public static volatile SingularAttribute<CustomerHeader, Date> itmRcrdCrtTs;
	public static volatile SingularAttribute<CustomerHeader, Date> itmRcrdMdfTs;
	public static volatile SingularAttribute<CustomerHeader, CustomerSegment> prcngGrpId;
	public static volatile SingularAttribute<CustomerHeader, String> custCountry;
	public static volatile SingularAttribute<CustomerHeader, String> divisionId;
	public static volatile SingularAttribute<CustomerHeader, String> priority;
}
