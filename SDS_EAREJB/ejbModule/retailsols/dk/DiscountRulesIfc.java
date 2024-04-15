package retailsols.dk;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public interface DiscountRulesIfc extends DiscountRuleTypesIfc {

    public BigDecimal getThresholdQuantity() ;
	

	public void setThresholdQuantity(BigDecimal thresholdQuantity);
	

	public BigDecimal getThresholdAmount() ;
	
	public void setThresholdAmount(BigDecimal thresholdAmount);
	
	
    public Date getEffectiveDate() ;
    
	public void setEffectiveDate(Date effectiveDate) ;
	
	public Date getExpirationDate() ;

	public void setExpirationDate(Date expirationDate) ;

	public Time getEffectiveTime() ;

	public void setEffectiveTime(Time effectiveTime) ;

	public Time getExpirationTime() ;
	
	public void setExpirationTime(Time expirationTime) ;

	public String getDescription() ;
	
	public void setDescription(String description) ;

	public String getName() ;
	
	public void setName(String name) ;

	public String getRuleID() ;

	public void setRuleID(String ruleID);

	public BigDecimal getDiscountAmount() ;



	public void setDiscountAmount(BigDecimal discountAmount);

	public BigDecimal getDiscountRate() ;

	public void setDiscountRate(BigDecimal discountRate) ;

	
}
