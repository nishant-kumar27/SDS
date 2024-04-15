package rispl.dkart.services.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:34.534+0530")
@StaticMetamodel(RisplShippmentMethodEntity.class)
public class RisplShippmentMethodEntity_ {
	public static volatile SingularAttribute<RisplShippmentMethodEntity, RisplShippmentMethodEntityPK> id;
	public static volatile SingularAttribute<RisplShippmentMethodEntity, String> carrierName;
	public static volatile SingularAttribute<RisplShippmentMethodEntity, String> routeStatus;
	public static volatile SingularAttribute<RisplShippmentMethodEntity, String> carrierPrice;
}
