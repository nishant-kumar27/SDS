package rispl.services.transaction.save;

import java.util.List;

import javax.ejb.Local;

import rispl.dkart.services.entities.transaction.OrderTranHeader;

@Local
public interface DkartWMSOrderTransactionServiceIfc {

	public void saveOrderTo_WMS(List<OrderTranHeader> tranHeaders);
	
	
}
