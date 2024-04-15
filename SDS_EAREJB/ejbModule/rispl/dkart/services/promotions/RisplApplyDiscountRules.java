/**
 * 
 */
package rispl.dkart.services.promotions;

import javax.ejb.Stateless;
import javax.inject.Inject;

import rispl.dkart.services.PromotionsService;
import rispl.dkart.services.entities.transaction.OrderTranHeader;

/**
 * @author RISPL-L14
 *
 */
@Stateless(mappedName="applyValidPromotions")
public class RisplApplyDiscountRules implements RisplApplyDiscountRulesIfc{

	private static final long serialVersionUID = 1L;

	@Inject
	PromotionsService promotionsTesting;
	
	@Override
	public OrderTranHeader applyDiscountRules(OrderTranHeader transaction) throws Exception {
		// TODO Auto-generated method stub
		//PromotionsTesting promotionsTesting = new PromotionsTesting();
		promotionsTesting.applyPromotions(transaction);
		return transaction;
	}

	@Override
	public PromotionsService getPromotionsService() {
		// TODO Auto-generated method stub
		return this.promotionsTesting;
	}

}
