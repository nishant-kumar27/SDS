package rispl.dk.itemLookUp;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PluSimplePromotionsRules implements PluSimplePromotionsRulesIfc{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1326930835044200876L;

		int promotionId;
		
		Timestamp startDate;
		
		Timestamp endDate;
		
		int componentId;
		
		
		int PromoCompDetailId;
		
		BigDecimal promotionPrice;
		
		int priority;
		
		int typeCode;

		public int getPromotionId() {
			return promotionId;
		}

		public void setPromotionId(int promotionId) {
			this.promotionId = promotionId;
		}

		public Timestamp getStartDate() {
			return startDate;
		}

		public void setStartDate(Timestamp startDate) {
			this.startDate = startDate;
		}

		public Timestamp getEndDate() {
			return endDate;
		}

		public void setEndDate(Timestamp endDate) {
			this.endDate = endDate;
		}

		public int getComponentId() {
			return componentId;
		}

		public void setComponentId(int componentId) {
			this.componentId = componentId;
		}

		public int getPromoCompDetailId() {
			return PromoCompDetailId;
		}

		public void setPromoCompDetailId(int promoCompDetailId) {
			PromoCompDetailId = promoCompDetailId;
		}

		public BigDecimal getPromotionPrice() {
			return promotionPrice;
		}

		public void setPromotionPrice(BigDecimal promotionPrice) {
			this.promotionPrice = promotionPrice;
		}

		public int getPriority() {
			return priority;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public int getTypeCode() {
			return typeCode;
		}

		public void setTypeCode(int typeCode) {
			this.typeCode = typeCode;
		}
}
