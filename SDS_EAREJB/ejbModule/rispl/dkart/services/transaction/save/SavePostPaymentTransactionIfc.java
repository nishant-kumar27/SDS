package rispl.dkart.services.transaction.save;

import javax.ejb.Remote;

import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.payment.PaymentDetails;

@Remote
public interface SavePostPaymentTransactionIfc {

	OrderTranHeader initializePaymentTransaction(PaymentDetails pd);

	PaymentDetails saveTransaction(PaymentDetails pd);

}
