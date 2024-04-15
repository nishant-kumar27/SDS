package rispl.ds.order;

import java.util.ArrayList;
import java.util.List;

import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class MarkOrderAsDelivered  extends DSAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1347327448996567198L;
	private String[] ordersMarked=null;


	public String Execute(){
		
		return SUCCESS;
	}
	
	
	public String markOrderToDelivered(){
		try {
			
			OrderTransactionsIfc order=	DKartContext.getLookupOrder();
			order.markOrderAsDelivered(getOrderList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ordersMarked: "+getOrdersMarked().toString());
		
		return SUCCESS;
		
	}
	
	//mudassir
	private List<String> getOrderList(){
		List<String> ordlis=new ArrayList();
		if(getOrdersMarked()!=null && getOrdersMarked()[0]!=null){
			String [] ordarr=getOrdersMarked()[0].split(",");
			for(int i=0;i<ordarr.length;i++){
				if(!ordarr[i].equalsIgnoreCase("")){
					ordlis.add(ordarr[i]);
				}
			}
			
		}
		return ordlis;
	}


	public String[] getOrdersMarked() {
		return ordersMarked;
	}


	public void setOrdersMarked(String[] ordersMarked) {
		this.ordersMarked = ordersMarked;
	}


	
}
