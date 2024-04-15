package rispl.dkart.services.entities.transaction;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:35.438+0530")
@StaticMetamodel(OrderTranSum.class)
public class OrderTranSum_ {
	public static volatile SingularAttribute<OrderTranSum, OrderTranSumPK> id;
	public static volatile SingularAttribute<OrderTranSum, String> cdCnyIso;
	public static volatile SingularAttribute<OrderTranSum, String> cdCoIso;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> cntSndPkg;
	public static volatile SingularAttribute<OrderTranSum, String> coPrsl;
	public static volatile SingularAttribute<OrderTranSum, Date> custLpoDate;
	public static volatile SingularAttribute<OrderTranSum, String> custLpoNum;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> dkartTndTot;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> dkartDsApld;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> dkartDscTot;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> dkartNetTot;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> dkartSlsTot;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> dkartTaxIncTot;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> dkartTaxTot;
	public static volatile SingularAttribute<OrderTranSum, Date> dtAgRstDob;
	public static volatile SingularAttribute<OrderTranSum, String> flCtAznRq;
	public static volatile SingularAttribute<OrderTranSum, String> flRcpGfTrn;
	public static volatile SingularAttribute<OrderTranSum, String> flSndCtPhy;
	public static volatile SingularAttribute<OrderTranSum, String> idEm;
	public static volatile SingularAttribute<OrderTranSum, String> idIrsCt;
	public static volatile SingularAttribute<OrderTranSum, String> idMskPrsl;
	public static volatile SingularAttribute<OrderTranSum, String> idOrd;
	public static volatile SingularAttribute<OrderTranSum, String> idOrdAr;
	public static volatile SingularAttribute<OrderTranSum, String> idOrdArNmb;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> inElpsdIdl;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> inLckElpsd;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> inRngElpsd;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> inTndElpsd;
	public static volatile SingularAttribute<OrderTranSum, Date> ordEfDate;
	public static volatile SingularAttribute<OrderTranSum, Date> ordDlvrDate;
	public static volatile SingularAttribute<OrderTranSum, Integer> ordDlvrTimePeriod;
	public static volatile SingularAttribute<OrderTranSum, String> ordIdCt;
	public static volatile SingularAttribute<OrderTranSum, String> ordIdLy;
	public static volatile SingularAttribute<OrderTranSum, String> ordLvlDvr;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> peItmLnKy;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> peItmLnSc;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> quDptKy;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> quItmLnKy;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> quItmLnSc;
	public static volatile SingularAttribute<OrderTranSum, String> rcRsnSpn;
	public static volatile SingularAttribute<OrderTranSum, String> rtnTktNo;
	public static volatile SingularAttribute<OrderTranSum, String> stPrsl;
	public static volatile SingularAttribute<OrderTranSum, String> tySndCt;
	public static volatile SingularAttribute<OrderTranSum, BigDecimal> dkartExpenses;
	public static volatile SingularAttribute<OrderTranSum, String> origOrderId;
	public static volatile SingularAttribute<OrderTranSum, OrderTranHeader> ordTranHeader;
}
