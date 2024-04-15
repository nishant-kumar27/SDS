package com.retailsols.sds.edi;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dime.schedular.imports.DKartConstantsIfc;
import rispl.dk.customer.Customer;
import rispl.dk.customer.CustomerIfc;
import rispl.dk.itemLookUp.PLUItemIfc;
import rispl.dkart.services.PromotionsService;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.LookUpItemIfc;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.customer.CustomerHeaderPK;
import rispl.dkart.services.entities.customer.CustomerSite;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dkart.services.entities.tenders.TranLineItemTender;
import rispl.dkart.services.entities.tenders.TranLineItemTenderPK;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.dkart.services.entities.transaction.OrderTranLineItemPK;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import rispl.dkart.services.entities.transaction.OrderTranSumPK;
import rispl.dkart.services.promotions.RisplApplyDiscountRulesIfc;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.SearchCriteria;
import rispl.dkservices.common.SearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.DSAction.SESSION;
import rispl.ds.context.DKartContext;
import utility.ConfigUtils;

public class HandleUploadFile extends DSAction{

	static Logger LOGGER = LogManager.getLogger(HandleUploadFile.class);
	private static final long serialVersionUID = 1L;
    private String ediFileName;
    private String customerID;
    private String deleveryAddr;
    private String siteId;
    private String salesAgent;
    private String orderId;
    private BigDecimal orderTotal;
    private String salesAgentName;
    private Date orderDate;
    private Date deliveryDate;
    final String format = "format.currency";
    
    
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getSalesAgentName() {
		return salesAgentName;
	}

	public void setSalesAgentName(String salesAgentName) {
		this.salesAgentName = salesAgentName;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSalesAgent() {
		return salesAgent;
	}

	public void setSalesAgent(String salesAgent) {
		this.salesAgent = salesAgent;
	}

	public String getEdiFileName() {
		return ediFileName;
	}

	public void setEdiFileName(String ediFileName) {
		this.ediFileName = ediFileName;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getDeleveryAddr() {
		return deleveryAddr;
	}

	public void setDeleveryAddr(String deleveryAddr) {
		this.deleveryAddr = deleveryAddr;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	@Override
    public String execute() throws Exception {
    	FileInputStream fis = null;
    	try {
			
			// get the file Data
			String tempFolder = System.getProperty("java.io.tmpdir");
			File file = new File(tempFolder+"/"+ediFileName);
			fis = new FileInputStream(file);
			int c;StringBuffer data=new StringBuffer();
			while((c=fis.read())!=-1){
				data=data.append((char)c);
			}
			String ediMetaData = data.toString().split("\\n")[0];
			// delivery date validation
			if(!isDeliveryDateValid(ediMetaData.split("\\,")[2].replaceAll("(?:\\n|\\r)", ""))){
				addActionError("Delivery Date not Valid");
				LOGGER.error("Delivery Date for the Order from the EDI File is not valid");
				return ERROR;
			}
			
				OrderTransactionsIfc lookUpOrder = DKartContext.getLookupOrder();
				LookUpCustomerIfc lookUpCustomer = DKartContext.getLookUpCustomer();
				LookUpItemIfc lookUpItem = DKartContext.getLookupItem();
				LookUpEmployeeIfc lookUpEmpl = DKartContext.getLookupEmployee();
				
				OrderTranHeader ordHead = new OrderTranHeader();
				
				CustomerSearchCriteria custCriteria = new CustomerSearchCriteria();
				custCriteria.setCustomerId(customerID);
				//CustomerIfc customer = lookUpCustomer.lookUpCust(custCriteria)[0];
				CustomerIfc customer = (CustomerIfc)getFromSession(SESSION.CUSTOMER);
				CustomerIfc cust=new Customer();
				CustomerHeader custHead= new CustomerHeader();
				CustomerHeaderPK headpk = new CustomerHeaderPK();
				headpk.setCustId(customerID);
				custHead.setCustomerHeaderPK(headpk);
				custHead.setPrcngGrpId(customer.getCustomerHeader().getPrcngGrpId());
				cust.setCustomerHeader(custHead);
			ordHead.setCustomer(cust);
			
			ordHead = lookUpOrder.createNewOrder(ordHead);
			
			List<OrderTranLineItem> lineItems = new ArrayList<OrderTranLineItem>();
			String itemLines[] = data.toString().split("\n");
			if(itemLines.length==1){
				addActionError("Fatal Error Occured while Processing the File");
				return ERROR;
			}
			
			for (int i = 1; i < itemLines.length; i++) {
				try {
					String lineItem[] = itemLines[i].split("\\,");
					SearchCriteriaIfc criteria = new SearchCriteria();
					criteria.setItemID(lineItem[1]);
					criteria.setItemIdOrUPC(lineItem[0]);
					criteria.setStoreID(ordHead.getId().getRtStrId());
					PLUItemIfc item = lookUpItem.lookUpItemById(criteria);
					OrderTranLineItem lineitem = prepareOrderTranLineItem(item,	new BigDecimal(lineItem[2].replaceAll("(?:\\n|\\r)", "")), ordHead);
					lineitem.getId().setOrdLnItmSeq(i);
					lineItems.add(lineitem);
				}
				catch(NoResultException nre){
					nre.printStackTrace();
					addActionError("Items Provided in the Files are not Valid");
					return ERROR;
				}
				/*catch (Exception e) {
					e.printStackTrace();
					addActionError("Fatal Error Occured while Processing the File");
					return ERROR;
				}*/
				catch(EJBException ejbe){

					if(ejbe.getCause() instanceof NoResultException){
					addActionError("Items Provided in the Files are not Valid");
					} else {
					addActionError("Fatal Error Occured while Processing the File");
					}

					return ERROR;	
					}
			}
			
			ordHead.setScOrd(BigDecimal.ZERO);  // order status
			ordHead.setTransactionStatus(new BigDecimal("4")); // transaction status
			ordHead.setIdOpr(getEmployee().getEmployeeId()); // operator id
			ordHead.setEmId(getSalesAgent()); // employee id
			ordHead.setOrdTranLineItems(lineItems);
			//ordHead.setOrdTranSum(prepareOrderTranSum(ordHead));
			boolean groupLines = DKartContext.getParamterBean().fetchXMLParameterValues().getBookOrderEnableGroupingDiscountedLineItems().equalsIgnoreCase("NO")?false:true;
			ordHead.setGroupDiscLinItms(groupLines);
			updateTotals(ordHead);
			
			ordHead = applyComplexPromotions(ordHead);
			ordHead.setIdBtchInvResv(siteId);
			ordHead.setCtDvrInf(getDeliveryAddress(customer, siteId));
			TranLineItemTender[] tenders = new TranLineItemTender[1];
			tenders[0] = prepareTenderLineItem(ordHead);
			lookUpOrder.saveQuote(ordHead);
			lookUpOrder.savePayments(tenders);
			
			// update value stack values
			setSalesAgentName(lookUpEmpl.lookupEmployeeByLoginIdOrEmpId(salesAgent).getEmployeeName());
			setOrderDate(new java.util.Date());  // effective date
			setDeleveryAddr(getDeliveryAddress(customer, siteId));
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Fatal Error Occured while Processing the File");
			return ERROR;
		}
    	finally {
    		{
    			if(fis != null){
    				fis.close();
    			}
    		}
		}
    	return SUCCESS;
    }
	
	private OrderTranLineItem prepareOrderTranLineItem(PLUItemIfc item, BigDecimal itemQty,OrderTranHeader orderHead) throws Exception{
		OrderTranLineItem lineItem = new OrderTranLineItem();
		try {
					OrderTranLineItemPK linePK = new OrderTranLineItemPK();
						linePK.setRtStrId(orderHead.getId().getRtStrId());
						linePK.setOrdWs(orderHead.getId().getOrdWs());
						linePK.setDcDyOrd(orderHead.getId().getDcDyOrd());
						linePK.setTrnSeq(orderHead.getId().getTrnSeq());
				lineItem.setId(linePK);
				
				lineItem.setItemId(item.getItem().getId().getItmId()); // item id
				lineItem.setLineQnt(itemQty);
				lineItem.setItmPrnPrc(item.getItemPrice().getSlUnAmt());
				lineItem.setExtnLnItmRtn(lineItem.getLineQnt().multiply(lineItem.getItmPrnPrc()));
				lineItem.setExtnDscLnItm(lineItem.getLineQnt().multiply(lineItem.getItmPrnPrc()));
				lineItem.setDeItmShrtRcpt(item.getItem().getItmDesc());
				lineItem.setItmTy(new BigDecimal("1"));
				lineItem.setUomSls("UN");
				
				applySimplProm(item, lineItem, null);
				
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lineItem;
	}
	
	private OrderTranSum prepareOrderTranSum(OrderTranHeader head) throws Exception {
		OrderTranSum ordSum = new OrderTranSum();
		try {
			OrderTranSumPK ordSumPK = new OrderTranSumPK();
			ordSumPK.setRtStrId(head.getId().getRtStrId());
			ordSumPK.setOrdWs(head.getId().getOrdWs());
			ordSumPK.setDcDyOrd(head.getId().getDcDyOrd());
			ordSumPK.setTrnSeq(head.getId().getTrnSeq());
			ordSum.setId(ordSumPK);
			BigDecimal orderTotal = getTransactionTotal(head);
			ordSum.setDkartSlsTot(orderTotal);
			ordSum.setDkartNetTot(orderTotal);
			
			ordSum.setDkartTaxIncTot(BigDecimal.ZERO);
			ordSum.setDkartExpenses(BigDecimal.ZERO);
			ordSum.setOrdDlvrDate(getDeliveryDate());
//			int days = Integer.parseInt(DKartContext.getParamterBean().fetchXMLParameterValues().getScheduledDeliveryOrderBeforeNoOfDays());
//			Date effectiveDate = new Date(getDeliveryDate().getTime() - days * 24 * 3600 * 1000);
			ordSum.setOrdEfDate(new java.util.Date());
			String orderid = head.getId().getRtStrId() + head.getId().getOrdWs()+ head.getId().getDcDyOrd().replace("-", "") + head.getId().getTrnSeq();
			ordSum.setIdOrd(orderid);
			setOrderId(orderid);
			setOrderTotal(orderTotal);
			ordSum.setOrdTranHeader(head);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return ordSum;
	}
	
	private TranLineItemTender prepareTenderLineItem(OrderTranHeader ordHead) throws Exception{
		TranLineItemTender lineTender = new TranLineItemTender();
		try{
			TranLineItemTenderPK tenderPk = new TranLineItemTenderPK();
			tenderPk.setRtStrId(ordHead.getId().getRtStrId());
			tenderPk.setOrdWs(ordHead.getId().getOrdWs());
			tenderPk.setDcDyOrd(ordHead.getId().getDcDyOrd());
			tenderPk.setTrnSeq(ordHead.getId().getTrnSeq());
			tenderPk.setTrnSeq(0);
		lineTender.setId(tenderPk);
		lineTender.setTyTnd("CCARD");
		lineTender.setIdOrd("HOUSE");
		lineTender.setMoItmLnTnd(ordHead.getOrdTranSum().getDkartNetTot());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return lineTender;
	}

	private BigDecimal getTransactionTotal(OrderTranHeader ordHead) throws Exception{
		BigDecimal totalAmount = new BigDecimal("0");
		try{ 
			
		List<OrderTranLineItem> lineItems = ordHead.getOrdTranLineItems();
		Iterator<OrderTranLineItem> itr = lineItems.iterator();
		while(itr.hasNext()){
			OrderTranLineItem lineItem = (OrderTranLineItem)itr.next();
			totalAmount = totalAmount.add(lineItem.getExtnLnItmRtn());
		}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return totalAmount;
	}
	
	
	private String getDeliveryAddress(CustomerIfc customer, String idBtchInvResv) {
		String address="";
		for(CustomerSite cs:customer.getCustomerSite())
		{
			if(cs.getCustomerSitePK().getCustSiteId().toString().equalsIgnoreCase(idBtchInvResv))
			{
				for(CustomerSiteAddress addr:cs.getCustomerSiteAddressList())
				{
					if (addr.getTyAds()=='1') 
					{
						address+=addr.getA1Cnct()+" "
								+(addr.getA2Cnct()==null?"":addr.getA2Cnct())+"\n"
								+(addr.getCiCnct()==null?"":addr.getCiCnct()+", ")
								+(addr.getCoCnct()==null?"":addr.getCoCnct()+", ")
								+(addr.getPcCnct()==null?"":addr.getPcCnct()+", ");
					}
				}
				
			}
		}
		return address;
	}
	
	private boolean isDeliveryDateValid(String date){
		boolean flag = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			Date orderDeliveryDate = sdf.parse(date); setDeliveryDate(orderDeliveryDate);
			Date sysDate = new Date();
			String sysDateString = sdf.format(sysDate);
			Date formattedSysDate  = sdf.parse(sysDateString);
			
			if(orderDeliveryDate.compareTo(formattedSysDate) > 0 || orderDeliveryDate.compareTo(formattedSysDate) == 0){
				flag = true;
			}
		} catch (Exception e) {
			LOGGER.error("Error Occured in validating the Delivery Date",e);
		}
		return flag;
	}
	
	/*public static void main(String[] args) {
		HandleUploadFile ff = new HandleUploadFile();
		System.out.println(ff.isDeliveryDateValid("20170711"));
	}*/
	
	public void updateTotals(OrderTranHeader orderTran)
	 {			
		if(orderTran!=null)//If transaction is initiated
		{		
			List<OrderTranLineItem> ordLineList = orderTran.getOrdTranLineItems();
			BigDecimal subTotal = new BigDecimal(0);
			BigDecimal discountedTotal = new BigDecimal(0);
			BigDecimal expenses = new BigDecimal(0);
			if(ordLineList != null){
				for(OrderTranLineItem orderTranLineItem : ordLineList )
				{
						//sub Total
						subTotal = subTotal.add(orderTranLineItem.getExtnLnItmRtn());
				}
			}
			List<OrderTranSum> orderTranSumList = new ArrayList<OrderTranSum>();
			OrderTranSum orderTranSum = orderTran.getOrdTranSum();
			orderTranSum.setDkartSlsTot(subTotal);
			BigDecimal serviceTotal = BigDecimal.ZERO;
			if(ordLineList != null){
				for (OrderTranLineItem orderTranLineItem : ordLineList) {
					// Trans level Extended discount amount
					discountedTotal = discountedTotal.add(orderTranLineItem.getExtnDscLnItm());
		
					// Need service items Extended price for transaction level
					// discount validations
					if (orderTranLineItem.getItmTy().equals(new BigDecimal(2))) {
						serviceTotal = serviceTotal.add(orderTranLineItem.getExtnLnItmRtn());
					}
				}
			}
			BigDecimal totalDisc = subTotal.subtract(discountedTotal);
			BigDecimal total = discountedTotal.add(new BigDecimal(0));//adding TAX once the TAX parameter is enabled
			
		//	BigDecimal total = subTotal;
	/*		if(avCrdtLmt==null){
			avCrdtLmt = orderTran.getCustomer().getCustomerLimits().getAvCrdtLimit();
			}*/
			ConfigUtils config = ConfigUtils.getInstance();
			String currencyFormat = "format.currency";
			
			orderTranSum.setDkartDscTot(config.createBigDecimal(totalDisc, currencyFormat));
			orderTranSum.setDkartNetTot(config.createBigDecimal(total, currencyFormat));
			orderTranSum.setDkartExpenses(config.createBigDecimal(expenses, currencyFormat));
			orderTranSumList.add(orderTranSum);
			BigDecimal totalExcludingServicePrice = total.subtract(serviceTotal);
			orderTranSum.setTotalExcludingServicePrice(totalExcludingServicePrice);//need it for validating transaction level discount amt(should not be grater than totalExcludingServicePrice) while giving in transaction amt popup 
			orderTranSum.setOrdTranHeader(orderTran);
			orderTranSum.getOrdTranHeader().setCustomer(orderTran.getCustomer());
			
			orderTranSum.setDkartTaxTot(config.createBigDecimal("0.00", currencyFormat));
			orderTranSum.setDkartTaxIncTot(config.createBigDecimal("0.00", currencyFormat));
			orderTranSum.setDkartExpenses(config.createBigDecimal("0.00", currencyFormat));
			orderTranSum.setOrdDlvrDate(getDeliveryDate());
			orderTranSum.setOrdEfDate(new java.util.Date());
			String orderid = orderTran.getId().getRtStrId() + orderTran.getId().getOrdWs()+ orderTran.getId().getDcDyOrd().replace("-", "") + orderTran.getId().getTrnSeq();
			orderTranSum.setIdOrd(orderid);
			setOrderId(orderid);
			setOrderTotal(discountedTotal);
			
			orderTranSum.setOrdTranHeader(orderTran);
			orderTran.setOrdTranSum(orderTranSum);
		}
	 
	 }
	
	/**
	 * Added by Srinivas to apply simple promotion - START
	 * @param pluItem
	 * @param orderTranLineItem
	 * @param disLine
	 */
	public void applySimplProm(PLUItemIfc pluItem,OrderTranLineItem orderTranLineItem,OrderTranDiscountItem disLine){
		 
	 	 if(pluItem.getItemSimplePromotions()!=null && pluItem.getItemSimplePromotions().getPromOvrdPrc()!=null){
	 		 OrderTranDiscountItem newDis = null;
	 		 if(disLine ==null){
	 			newDis = new OrderTranDiscountItem();
	 		 }else{
	 			 newDis = disLine;
	 			//orderTranLineItem.setExtnDscLnItm(orderTranLineItem.getExtnLnItmRtn().add(newDis.getDscAmt())); //commented by hanu
	 		 }
	 			newDis.setTyDsc(DKartConstantsIfc.DIS_PROM_MNUL);
	 			newDis.setPrmType(DKartConstantsIfc.DIS_SIMP_PROM_AUTO);
	 			newDis.setDscPer(BigDecimal.ZERO);
	 			newDis.setPrmDesc(pluItem.getItemSimplePromotions().getEvDesc());
	 			newDis.setPrmId(pluItem.getItemSimplePromotions().getPromId());
	 			newDis.setPrmCmpId(pluItem.getItemSimplePromotions().getPromCmpId());
	 			newDis.setPrmCmpDtlid(pluItem.getItemSimplePromotions().getPromCmpDtlId());
	 			
	 			BigDecimal itmInitPrice = ConfigUtils.getInstance().createBigDecimal(pluItem.getItemPrice().getSlUnAmt(), format);
	 			BigDecimal itmPromPrice = ConfigUtils.getInstance().createBigDecimal(pluItem.getItemSimplePromotions().getPromOvrdPrc(), format);
	 			
	 			BigDecimal discountAmount = BigDecimal.ZERO;
	 			if(itmInitPrice.compareTo(itmPromPrice)==0 || itmInitPrice.compareTo(itmPromPrice)==1){
	 				discountAmount = itmInitPrice.subtract(itmPromPrice);
	 			}else{
	 				discountAmount = itmPromPrice.subtract(itmInitPrice);
	 			}
	 			BigDecimal lineQty = orderTranLineItem.getLineQnt();
	 			//Multiply each item discount amount with item quantity
	 			discountAmount = (discountAmount.multiply(lineQty));//.setScale(2, RoundingMode.HALF_DOWN);;
	 			discountAmount = ConfigUtils.getInstance().createBigDecimal(discountAmount, format);
	 			//pluItem.getItemPrice().setSlUnAmt(itmPromPrice);
	 			
	 			newDis.setDscAmt(discountAmount);
	 			if (orderTranLineItem.getOrdTranDscItms() == null) {
	 				orderTranLineItem.setOrdTranDscItms(new ArrayList<OrderTranDiscountItem>());
	 			}
	 			
	 			if(disLine==null){
	 				orderTranLineItem.getOrdTranDscItms().add(newDis);
	  			}
	 			BigDecimal extPrice =lineQty.multiply(orderTranLineItem.getItmPrnPrc());
	 			orderTranLineItem.setExtnLnItmRtn(extPrice);
	 			orderTranLineItem.setExtnDscLnItm(extPrice.subtract(discountAmount));
	 			
	 	 }
	}
	//Added by Srinivas to apply simple promotion - END
	
	
	//Added by Srinivas for Complex promotions checking
	public OrderTranHeader applyComplexPromotions(OrderTranHeader orderTran){
		if (orderTran != null && orderTran.getOrdTranLineItems()!=null && orderTran.getOrdTranLineItems().size()>0)// If transaction is initiated
		{
		try {
			RisplApplyDiscountRulesIfc applyDiscRule=DKartContext.getLookupTransOfQuote();
			
			//Group same items
			PromotionsService promotionsService = applyDiscRule.getPromotionsService();
			Map<String, String> seqItmId = new HashMap<String, String>();
			Map<String, BigDecimal> seqItmQty = new HashMap<String, BigDecimal>();
			for (OrderTranLineItem orderTranLineItem : orderTran.getOrdTranLineItems()) {
				seqItmId.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", orderTranLineItem.getItemId());
				if(seqItmQty.containsKey(orderTranLineItem.getId().getOrdLnItmSeq() + "")){
					seqItmQty.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", seqItmQty.get(orderTranLineItem.getId().getOrdLnItmSeq()+"").add(orderTranLineItem.getLineQnt()));
				}else{
					seqItmQty.put(orderTranLineItem.getId().getOrdLnItmSeq() + "", orderTranLineItem.getLineQnt());
				}
			}
			List<OrderTranLineItem> lineItems = promotionsService.groupNDLineItems(orderTran.getOrdTranLineItems());
			lineItems = promotionsService.reAssignLineSequenceNumbers(lineItems, seqItmId, seqItmQty);
			orderTran.setOrdTranLineItems(lineItems);
			updateTotals(orderTran);
			
			orderTran = applyDiscRule.applyDiscountRules(orderTran);
			updateTotals(orderTran);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Exception While Lookup for JNDI Instance DISCOUNT_RULES or executing applyDiscRule  : ", e);
		}
		}
		
		return orderTran;
	}
	
}
