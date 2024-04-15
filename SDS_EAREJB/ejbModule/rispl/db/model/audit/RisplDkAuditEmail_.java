package rispl.db.model.audit;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.364+0530")
@StaticMetamodel(RisplDkAuditEmail.class)
public class RisplDkAuditEmail_ {
	public static volatile SingularAttribute<RisplDkAuditEmail, Long> seqId;
	public static volatile SingularAttribute<RisplDkAuditEmail, String> emailAddr;
	public static volatile SingularAttribute<RisplDkAuditEmail, byte[]> emailAttach;
	public static volatile SingularAttribute<RisplDkAuditEmail, String> emailBody;
	public static volatile SingularAttribute<RisplDkAuditEmail, String> emailSubj;
	public static volatile SingularAttribute<RisplDkAuditEmail, String> recipient;
	public static volatile SingularAttribute<RisplDkAuditEmail, String> refNo;
	public static volatile SingularAttribute<RisplDkAuditEmail, Date> timestamp;
	public static volatile SingularAttribute<RisplDkAuditEmail, String> transType;
}
