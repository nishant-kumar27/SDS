package rispl.dkart.order.lookup.dao;

public final class OrderStatus{

	public static final String QUOTE="Quoted";				//when ordTy=23, scOrd=0, scTran=4
	public static final String NEW="New";					//when ordTy=23, scOrd=0, scTran=2 
	public static final String OPEN="Open";					//when ordTy=23, scOrd=1, scTran=2
	public static final String CANCELLED="Cancelled";		//when ordTy=25
	public static final String IN_PROGRESS="In-Progress";	//when ordTy=23 or 26, scOrd=(2,3,4), scTran=2 
	public static final String COMPELETED="Documentation Awaiting";		//when ordTy=24, scOrd=(5), scTran=2
	public static final String DELIVERED="Delivered";		//when scOrd=(6,7) 
	public static final String RETURNED="Returned";			//when ordTy=2
	public static final String PAYMENT="Payment";			//when ordTy=18
	public static final String UNKNOWN=""
			+ ""
			+ "";
}