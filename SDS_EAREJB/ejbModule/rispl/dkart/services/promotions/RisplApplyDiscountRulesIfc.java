/**
 * 
 */
package rispl.dkart.services.promotions;

import java.io.Serializable;

import javax.ejb.Remote;

import rispl.dkart.services.PromotionsService;
import rispl.dkart.services.entities.transaction.OrderTranHeader;

/**
 * @author RISPL-L14
 *
 */
@Remote
public interface RisplApplyDiscountRulesIfc extends Serializable {
	public OrderTranHeader applyDiscountRules(OrderTranHeader transaction) throws Exception;
	public PromotionsService getPromotionsService();
}
