package rispl.dkart.services.entities.transaction;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:35.516+0530")
@StaticMetamodel(OrdInvQtyShp.class)
public class OrdInvQtyShp_ {
	public static volatile SingularAttribute<OrdInvQtyShp, OrdInvQtyShpPK> id;
	public static volatile SingularAttribute<OrdInvQtyShp, String> itemId;
	public static volatile SingularAttribute<OrdInvQtyShp, BigDecimal> ordLnItmSeq;
	public static volatile SingularAttribute<OrdInvQtyShp, BigDecimal> shpQty;
}
