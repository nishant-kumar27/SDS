package rispl.db.model.claim;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:28.568+0530")
@StaticMetamodel(ClaimTranSum.class)
public class ClaimTranSum_ {
	public static volatile SingularAttribute<ClaimTranSum, ClaimTranSumPK> id;
	public static volatile SingularAttribute<ClaimTranSum, String> cdCnyIso;
	public static volatile SingularAttribute<ClaimTranSum, String> cdCoIso;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> cntSndPkg;
	public static volatile SingularAttribute<ClaimTranSum, String> coPrsl;
	public static volatile SingularAttribute<ClaimTranSum, Date> custLpoDate;
	public static volatile SingularAttribute<ClaimTranSum, String> custLpoNum;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> dkartTndTot;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> dkartDsApld;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> dkartDscTot;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> dkartExpenses;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> dkartNetTot;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> dkartSlsTot;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> dkartTaxIncTot;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> dkartTaxTot;
	public static volatile SingularAttribute<ClaimTranSum, Date> dtAgRstDob;
	public static volatile SingularAttribute<ClaimTranSum, String> flCtAznRq;
	public static volatile SingularAttribute<ClaimTranSum, String> flRcpGfTrn;
	public static volatile SingularAttribute<ClaimTranSum, String> flSndCtPhy;
	public static volatile SingularAttribute<ClaimTranSum, String> idEm;
	public static volatile SingularAttribute<ClaimTranSum, String> idIrsCt;
	public static volatile SingularAttribute<ClaimTranSum, String> idMskPrsl;
	public static volatile SingularAttribute<ClaimTranSum, String> idOrd;
	public static volatile SingularAttribute<ClaimTranSum, String> idOrdAr;
	public static volatile SingularAttribute<ClaimTranSum, String> idOrdArNmb;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> inElpsdIdl;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> inLckElpsd;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> inRngElpsd;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> inTndElpsd;
	public static volatile SingularAttribute<ClaimTranSum, Date> ordEfDate;
	public static volatile SingularAttribute<ClaimTranSum, String> ordIdCt;
	public static volatile SingularAttribute<ClaimTranSum, String> ordIdLy;
	public static volatile SingularAttribute<ClaimTranSum, String> ordLvlDvr;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> peItmLnKy;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> peItmLnSc;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> quDptKy;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> quItmLnKy;
	public static volatile SingularAttribute<ClaimTranSum, BigDecimal> quItmLnSc;
	public static volatile SingularAttribute<ClaimTranSum, String> rcRsnSpn;
	public static volatile SingularAttribute<ClaimTranSum, String> rtnTktNo;
	public static volatile SingularAttribute<ClaimTranSum, String> stPrsl;
	public static volatile SingularAttribute<ClaimTranSum, String> tySndCt;
	public static volatile SingularAttribute<ClaimTranSum, ClaimTranHeader> claimTranHeader;
}
