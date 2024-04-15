package rispl.dk.itemLookUp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public interface PluSimplePromotionsRulesIfc extends Serializable {
	
	public int getPromotionId() ;

	public void setPromotionId(int promotionId);

	public Timestamp getStartDate() ;

	public void setStartDate(Timestamp startDate);

	public Timestamp getEndDate() ;

	public void setEndDate(Timestamp endDate) ;

	public int getComponentId() ;
	

	public void setComponentId(int componentId);

	public int getPromoCompDetailId() ;

	public void setPromoCompDetailId(int promoCompDetailId);

	public BigDecimal getPromotionPrice() ;

	public void setPromotionPrice(BigDecimal promotionPrice) ;

	public int getPriority() ;

	public void setPriority(int priority);

	public int getTypeCode() ;

	public void setTypeCode(int typeCode);
}
