package retailsols.dk;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class DiscountRules implements DiscountRulesIfc{

	
	protected Date effectiveDate = null;

    /**
     * date rule ceases to be effective
     */
    protected Date expirationDate = null;

    /**
     * time rule becomes effective
     */
    protected Time effectiveTime = null;

    /**
     * time rule ceases to be effective
     */
    protected Time expirationTime = null;

    /**
     * description
     */
    protected String description = "";

    /**
     * rule name
     */
    protected String name = "";
    
    /**
     * rule identifier
     */
    protected String ruleID = "";

    
    /**
     * amount of discount
     */
    protected BigDecimal discountAmount = null;

    /**
     * discount rate
     */
    protected BigDecimal discountRate = null;
    
    protected BigDecimal thresholdQuantity=null;
    
    protected BigDecimal thresholdAmount=null;
    
    

    
    public BigDecimal getThresholdQuantity() {
		return thresholdQuantity;
	}

	public void setThresholdQuantity(BigDecimal thresholdQuantity) {
		this.thresholdQuantity = thresholdQuantity;
	}

	public BigDecimal getThresholdAmount() {
		return thresholdAmount;
	}

	public void setThresholdAmount(BigDecimal thresholdAmount) {
		this.thresholdAmount = thresholdAmount;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}





	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}





	public Time getEffectiveTime() {
		return effectiveTime;
	}





	public void setEffectiveTime(Time effectiveTime) {
		this.effectiveTime = effectiveTime;
	}





	public Time getExpirationTime() {
		return expirationTime;
	}





	public void setExpirationTime(Time expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuleID() {
		return ruleID;
	}
	
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}



	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	

	public static void main(String args[]){
		
		
		////pass the list of values and get the information about the promotions
	}
}
