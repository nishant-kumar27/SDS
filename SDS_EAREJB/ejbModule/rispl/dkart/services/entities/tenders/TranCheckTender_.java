package rispl.dkart.services.entities.tenders;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-01-09T13:30:34.813+0530")
@StaticMetamodel(TranCheckTender.class)
public class TranCheckTender_ {
	public static volatile SingularAttribute<TranCheckTender, TranCheckTenderPK> id;
	public static volatile SingularAttribute<TranCheckTender, String> acntMskChk;
	public static volatile SingularAttribute<TranCheckTender, String> acntNcrptChk;
	public static volatile SingularAttribute<TranCheckTender, String> cdAznApvl;
	public static volatile SingularAttribute<TranCheckTender, String> cdCnvAznChk;
	public static volatile SingularAttribute<TranCheckTender, BigDecimal> cdCoMicr;
	public static volatile SingularAttribute<TranCheckTender, String> cdSt;
	public static volatile SingularAttribute<TranCheckTender, String> dkAiChk;
	public static volatile SingularAttribute<TranCheckTender, String> dkBirthDate;
	public static volatile SingularAttribute<TranCheckTender, String> dkBnkId;
	public static volatile SingularAttribute<TranCheckTender, String> dkTaCt;
	public static volatile SingularAttribute<TranCheckTender, String> dkTlCt;
	public static volatile SingularAttribute<TranCheckTender, String> fgCnv;
	public static volatile SingularAttribute<TranCheckTender, String> idAjdChk;
	public static volatile SingularAttribute<TranCheckTender, String> idMskPrslAzn;
	public static volatile SingularAttribute<TranCheckTender, String> idNcrptPrslAzn;
	public static volatile SingularAttribute<TranCheckTender, String> luChkScnKy;
	public static volatile SingularAttribute<TranCheckTender, String> luIdPrslIssr;
	public static volatile SingularAttribute<TranCheckTender, String> luMthAzn;
	public static volatile SingularAttribute<TranCheckTender, String> nrMskMicr;
	public static volatile SingularAttribute<TranCheckTender, String> nrNcrptMicr;
	public static volatile SingularAttribute<TranCheckTender, String> swpId;
	public static volatile SingularAttribute<TranCheckTender, String> trk1Id;
	public static volatile SingularAttribute<TranCheckTender, String> trk2Id;
	public static volatile SingularAttribute<TranCheckTender, String> tyIdPrslRq;
	public static volatile SingularAttribute<TranCheckTender, String> fileType;
	public static volatile SingularAttribute<TranCheckTender, byte[]> checkSlipImage;
	public static volatile SingularAttribute<TranCheckTender, TranLineItemTender> dkTranLtmTnd;
}
