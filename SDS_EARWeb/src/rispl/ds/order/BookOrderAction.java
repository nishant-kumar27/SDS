package rispl.ds.order;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class BookOrderAction  extends DSAction {
	
	private static final long serialVersionUID = 1L;
	/*final DateTimeFormatter formatter =DateTimeFormatter.ofPattern("MM/dd/yyyy");*/
	//DefaultTextProvider dtp = new DefaultTextProvider();
	//final DateFormat formatter = new SimpleDateFormat(dtp.getText("format.date"));
	//private SessionMap<String,Object> sessionMap; 
	private OrderTranHeader orderTran;
	private String siteId,deleveryAddr,lpoNum,lpoDate,deliveryNotes,shipmentMethod,deliveryDate,deliveryType,effectiveDate,orderId;
	private int deliveryTime;
	/*@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap)map;
		
	}*/

	@Override
	public String execute() throws Exception {
		DateFormat formatter = new SimpleDateFormat(getText("format.date"));
		String status="failure";
		orderTran = (OrderTranHeader) getFromSession(SESSION.ORDER_TRANSACTION);
		if(orderTran != null)
		{
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			try{
				orderTran.setIdBtchInvResv(siteId);
				
				orderTran.getOrdTranSum().setOrdLvlDvr(deliveryType);
				orderTran.getOrdTranSum().setCustLpoNum(lpoNum);
				orderTran.setShipmentMethod(shipmentMethod);
				
				/*ZonedDateTime zonedDateTime = ZonedDateTime.parse(lpoDate, formatter);*/
               /* Date date = Date.from(zonedDateTime.toInstant()); */
                //System.out.println(date);
				Date date = formatter.parse(lpoDate);
				orderTran.getOrdTranSum().setCustLpoDate(date);
				
				if(deliveryType.equals("1"))//SCHEDULED DELIVERY
				{
					/*ZonedDateTime zonedDeliveryDateTime = ZonedDateTime.parse(deliveryDate, formatter);*/
					/*Date delDate = Date.from(zonedDeliveryDateTime.toInstant()); */
					Date delDate = formatter.parse(deliveryDate);
					//orderTran.getOrdTranSums().get(0).setOrdEfDate(delDate);
					orderTran.getOrdTranSum().setOrdDlvrDate(delDate);
					orderTran.getOrdTranSum().setOrdDlvrTimePeriod(deliveryTime);
				}
				else
				{
					/*Date delDate =  Date.from(ZonedDateTime.now().toInstant()); */
					Date delDate = new Date();
					orderTran.getOrdTranSum().setOrdDlvrDate(delDate);
					//orderTran.getOrdTranSums().get(0).setOrdEfDate(delDate);
				}
				try{
                Date efDate = formatter.parse(effectiveDate);
				
				orderTran.getOrdTranSum().setOrdEfDate(efDate);
				}
				catch(Exception e){
					System.out.println(e);
				}
				
				EmployeeIfc employee = (EmployeeIfc)getFromSession(SESSION.EMPLOYEE);
				orderTran.setEmId(employee.getEmployeeId());
				orderTran.setIdOpr(employee.getEmployeeId());
				orderTran.setCtDvrInfoIns(deliveryNotes);
				orderTran.setScOrd(new BigDecimal(0));//new order
				orderTran.setTransactionStatus(new BigDecimal(2));//transaction completed
				
				setOrderId(orderTran.getOrdTranSum().getIdOrd());
				if(ordTrn.saveOrderTransaction(orderTran))
				{
					status= "success";
					putInSession(SESSION.ORDER_TRANSACTION, null);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
		System.out.println("siteId : "+siteId +"deleveryAddr : "+deleveryAddr+"lpoNum : "+lpoNum+"lpoDate : "+lpoDate+"deliveryNotes : "+deliveryNotes+"shipmentMethod : "+shipmentMethod+"deliveryDate : "+deliveryDate+"deliveryType : "+deliveryType);
		return status;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getDeleveryAddr() {
		return deleveryAddr;
	}

	public void setDeleveryAddr(String deleveryAddr) {
		this.deleveryAddr = deleveryAddr;
	}

	public String getLpoNum() {
		return lpoNum;
	}

	public void setLpoNum(String lpoNum) {
		this.lpoNum = lpoNum;
	}

	public String getLpoDate() {
		return lpoDate;
	}

	public void setLpoDate(String lpoDate) {
		this.lpoDate = lpoDate;
	}

	public String getDeliveryNotes() {
		return deliveryNotes;
	}

	public void setDeliveryNotes(String deliveryNotes) {
		this.deliveryNotes = deliveryNotes;
	}

	public String getShipmentMethod() {
		return shipmentMethod;
	}

	public void setShipmentMethod(String shipmentMethod) {
		this.shipmentMethod = shipmentMethod;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
