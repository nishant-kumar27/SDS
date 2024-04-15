package rispl.dkart.services.entities.customer.segment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:31.272+0530")
@StaticMetamodel(RisplDkSegment.class)
public class RisplDkSegment_ {
	public static volatile SingularAttribute<RisplDkSegment, String> segmentDescription;
	public static volatile SingularAttribute<RisplDkSegment, BigDecimal> createdByUserId;
	public static volatile SingularAttribute<RisplDkSegment, Timestamp> createdDatetime;
	public static volatile SingularAttribute<RisplDkSegment, Timestamp> effectiveDatetime;
	public static volatile SingularAttribute<RisplDkSegment, Timestamp> endDatetime;
	public static volatile SingularAttribute<RisplDkSegment, String> segmentId;
}
