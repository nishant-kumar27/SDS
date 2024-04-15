package rispl.db.model.claim;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.511+0530")
@StaticMetamodel(ClaimTranHeader.class)
public class ClaimTranHeader_ {
	public static volatile SingularAttribute<ClaimTranHeader, ClaimTranHeaderPK> id;
	public static volatile SingularAttribute<ClaimTranHeader, String> claimId;
	public static volatile SingularAttribute<ClaimTranHeader, String> ctDvrInf;
	public static volatile SingularAttribute<ClaimTranHeader, String> ctDvrInfoIns;
	public static volatile SingularAttribute<ClaimTranHeader, String> emId;
	public static volatile SingularAttribute<ClaimTranHeader, String> flKyOfl;
	public static volatile SingularAttribute<ClaimTranHeader, String> flSlsAsscMdf;
	public static volatile SingularAttribute<ClaimTranHeader, String> flTreOrd;
	public static volatile SingularAttribute<ClaimTranHeader, String> flTrgOrd;
	public static volatile SingularAttribute<ClaimTranHeader, String> idBtchArch;
	public static volatile SingularAttribute<ClaimTranHeader, String> idBtchInvResv;
	public static volatile SingularAttribute<ClaimTranHeader, BigDecimal> idCnyIcd;
	public static volatile SingularAttribute<ClaimTranHeader, String> idOpr;
	public static volatile SingularAttribute<ClaimTranHeader, String> idRpstyTnd;
	public static volatile SingularAttribute<ClaimTranHeader, BigDecimal> idTlogBtch;
	public static volatile SingularAttribute<ClaimTranHeader, String> idTrlogBtch;
	public static volatile SingularAttribute<ClaimTranHeader, String> idWmsTlogBtch;
	public static volatile SingularAttribute<ClaimTranHeader, String> ordTy;
	public static volatile SingularAttribute<ClaimTranHeader, String> rcRtnMr;
	public static volatile SingularAttribute<ClaimTranHeader, BigDecimal> scOrd;
	public static volatile SingularAttribute<ClaimTranHeader, BigDecimal> scPstPrcs;
	public static volatile SingularAttribute<ClaimTranHeader, BigDecimal> scTran;
	public static volatile SingularAttribute<ClaimTranHeader, Date> tsCrtRcrd;
	public static volatile SingularAttribute<ClaimTranHeader, Date> tsMdfRcrd;
	public static volatile SingularAttribute<ClaimTranHeader, Date> tsOrdBgn;
	public static volatile SingularAttribute<ClaimTranHeader, Date> tsOrdEnd;
	public static volatile SingularAttribute<ClaimTranHeader, String> claimRejectNotes;
	public static volatile SingularAttribute<ClaimTranHeader, Date> whReceivedDate;
	public static volatile SingularAttribute<ClaimTranHeader, String> flInvCncl;
	public static volatile SingularAttribute<ClaimTranHeader, String> acceptType;
	public static volatile ListAttribute<ClaimTranHeader, ClaimTranLineItem> claimTranLineItems;
	public static volatile SingularAttribute<ClaimTranHeader, ClaimTranSum> claimTranSum;
}
