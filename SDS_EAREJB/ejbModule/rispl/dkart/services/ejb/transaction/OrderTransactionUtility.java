package rispl.dkart.services.ejb.transaction;


import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oracle.sql.DATE;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.employee.RisplDkEmpMstr;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.ejb.LookUpEmployee;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkservices.common.EmployeeSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;

public class OrderTransactionUtility {
	private static final Logger LOGGER = LogManager.getLogger(OrderTransactionUtility.class);
	Date formDate,toDate;

	
	// claim search by claim ID, order ID & Customer Info 
	public Query getClaimQueryByClaimID(String custInfo,String empRoleAcc,List<Integer> divIds,String emplID,String orderID,String claimID, String itemID, String status, EntityManager em) 
	{
			Query query = null;
			String claimQuery = null;
			
			String div = divIds.toString(); 
			char a=div.charAt(1);
			String finaldiv=String.valueOf(a);
			BigDecimal divisionid=new BigDecimal(finaldiv);
			
			claimQuery="SELECT cth FROM ClaimTranHeader cth,ClaimTranSum ctm,ClaimDetail cd where cth.claimId = cd.id.claimId AND"
					+" ctm.id.rtStrId=cth.id.rtStrId AND ctm.id.ordWs=cth.id.ordWs AND ctm.id.trnSeq=cth.id.trnSeq AND"
					+" ctm.id.dcDyOrd=cth.id.dcDyOrd";
			
			if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
			{
				claimQuery=claimQuery+" AND cd.empId=:emplID";
			}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
			{
				claimQuery=claimQuery+" AND cd.divisionId=:divisionid";
			}
			
			 if(claimID!=null && !claimID.equalsIgnoreCase(""))
			 {
				 claimQuery=claimQuery+" AND cd.id.claimId=:claimID"; 
			 }
			 if(orderID!=null && !orderID.equalsIgnoreCase(""))
			 {
				 claimQuery=claimQuery+" AND ctm.idOrd=:orderID"; 
			 }
			 claimQuery=claimQuery+" ORDER BY cd.id.claimDate DESC";
			 	
			 query = em.createQuery(claimQuery,ClaimTranHeader.class);
			
			 if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
				{
				 query.setParameter("emplID", emplID);
				}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
				{
				query.setParameter("divisionid", divisionid);
				}
			
			 	if(claimID!=null && !claimID.equalsIgnoreCase(""))
			 	{
				 query.setParameter("claimID", claimID); 
			 	}
			 	if(orderID!=null && !orderID.equalsIgnoreCase(""))
			 	{
				 query.setParameter("orderID", orderID); 
			 	}
			 	return query;
	}

	// claim search & Reject claim search by Item Info and customer info
	public Query getClaimQueryByItemIfo(String itemID,String custInfo, String status, EntityManager em,String empRoleAcc,List<Integer> divIds,String emplID) {
		Query query = null;
		String claimQuery = null;
		
		String div = divIds.toString(); 
		char a=div.charAt(1);
		String finaldiv=String.valueOf(a);
		BigDecimal divisionid=new BigDecimal(finaldiv);
		BigDecimal bigstatus=null;
		if(status!=null && !status.equalsIgnoreCase(""))
		{
		bigstatus=new BigDecimal(status);
		}
		/*SELECT cth.* FROM CLAIM_TRAN_HEADER cth,CLAIM_TRAN_SUM cts,CLAIM_TRAN_LINE_ITEM ctli,CLAIM_DETAILS cd,RISPL_DK_ITEM_MSTR im WHERE 
		cd.CLAIM_ID=cth.CLAIM_ID AND ctli.ITEM_ID=im.ITM_ID AND
		cts.RT_STR_ID = ctli.RT_STR_ID AND cts.DC_DY_ORD = ctli.DC_DY_ORD AND cts.ORD_WS = ctli.ORD_WS AND cts.TRN_SEQ = ctli.TRN_SEQ AND 
		cth.RT_STR_ID = ctli.RT_STR_ID AND cth.DC_DY_ORD = ctli.DC_DY_ORD AND cth.ORD_WS = ctli.ORD_WS AND cth.TRN_SEQ = ctli.TRN_SEQ AND
		--cd.EMP_ID='1534' AND cd.DIVISION_ID='2' AND --cd.CUSTOMER_NAME='' AND --cd.CUSTOMER_ID='1028' AND
		--im.ITM_ID='102208401' AND im.ID_ITM_POS='1234563149596' AND --im.ITM_DESC=''
		 */
		
		claimQuery="SELECT cth FROM ClaimTranHeader cth,ClaimTranSum cts,ClaimTranLineItem ctli,ClaimDetail cd, RisplDkItemMstr rdim where"
				  +" cth.claimId = cd.id.claimId AND ctli.itemId=rdim.id.itmId AND"
				  +" ctli.id.rtStrId=cth.id.rtStrId AND ctli.id.ordWs=cth.id.ordWs AND ctli.id.trnSeq=cth.id.trnSeq AND ctli.id.dcDyOrd=cth.id.dcDyOrd AND"
				  +" ctli.id.rtStrId=cts.id.rtStrId AND ctli.id.ordWs=cts.id.ordWs AND ctli.id.trnSeq=cts.id.trnSeq AND ctli.id.dcDyOrd=cts.id.dcDyOrd";
		
		if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
		{
			claimQuery=claimQuery+" AND cd.empId=:emplID";
		}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
		{
			claimQuery=claimQuery+" AND cd.divisionId=:divisionid";
		}
		if(status!=null && !status.equalsIgnoreCase(""))
		{
			if(status.equalsIgnoreCase("6"))
			{
				claimQuery=claimQuery+" AND cth.scOrd=:bigstatus";
			}
		}
		
		 if(custInfo!=null && !custInfo.equalsIgnoreCase(""))
		 {
			 claimQuery=claimQuery+" AND (cd.customerId=:custInfo OR cd.customerName=:custInfo)";
		 }
		 if(itemID!=null && !itemID.equalsIgnoreCase(""))
		 {
			 claimQuery=claimQuery+" AND (rdim.id.itmId=:itemID OR UPPER(rdim.itmDesc)=:itemID OR rdim.id.idItmPos=:itemID)";
		 }
		
		claimQuery=claimQuery+" ORDER BY cd.id.claimDate DESC";
		 	
		 query = em.createQuery(claimQuery,ClaimTranHeader.class);
		
		 if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
			{
			 query.setParameter("emplID", emplID);
			}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
			{
			query.setParameter("divisionid", divisionid);
			}
		
		 	if(custInfo!=null && !custInfo.equalsIgnoreCase(""))
		 	{
		 		query.setParameter("custInfo", custInfo);
		 	}
		 	if(itemID!=null && !itemID.equalsIgnoreCase(""))
		 	{
		 		query.setParameter("itemID", itemID);
		 	}
		 	if(status!=null && !status.equalsIgnoreCase(""))
			{
		 		if(status.equalsIgnoreCase("6"))
				{
			 		query.setParameter("bigstatus", bigstatus);
			 	}
			}
		 	
		
		/*
		 *  safe method to commit 
		 * // Select from ClaimTranHeader,ClaimTranLineItem
		claimQuery = "SELECT cth FROM ClaimTranHeader cth,ClaimTranLineItem ctli WHERE";
		// Append condition to match transaction id
		claimQuery += " cth.id.rtStrId=ctli.id.rtStrId" + " AND cth.id.ordWs=ctli.id.ordWs"
				+ " AND cth.id.trnSeq=ctli.id.trnSeq" + " AND cth.id.dcDyOrd=ctli.id.dcDyOrd"
				+ " AND (ctli.itemId LIKE :itemID OR UPPER(ctli.deItmShrtRcpt) LIKE :itemID)";
		// If status is required then add condition
		if (isStatusRequired(status)) {
			claimQuery += " AND cth.scOrd LIKE :status";
		}
		claimQuery += " ORDER BY cth.id.dcDyOrd DESC"; //To get the records sorting by claim date @Srinivas Reddy G
		// Build the query
		query = em.createQuery(claimQuery, ClaimTranHeader.class).setParameter("itemID", "%" + itemID.toUpperCase() + "%");*/

		return query;

	}

	public boolean isStatusRequired(String status) {
		boolean isStatusRequired = true;
		if (status == null || (status != null && status.equalsIgnoreCase(""))) {
			isStatusRequired = false;
		}
		return isStatusRequired;
	}

	public String getEmployee(String emId) {
		String employee = emId;
		try {
			
			//fix me here I am getting the InitialContext instead of @INJECT or @EJB
			/*LookUpEmployee lue = new LookUpEmployee();*/
			InitialContext initialContext = new InitialContext();
			LookUpEmployeeIfc lue = (LookUpEmployeeIfc)initialContext.lookup("lookUpEmployee#rispl.dkart.services.ejb.LookUpEmployeeIfc");
			EmployeeSearchCriteriaIfc criteria = new EmployeeSearchCriteria();
			criteria.setEmployeeId(emId);
			EmployeeIfc[] salesAgent = lue.lookupSalesAgent(criteria);
			if (salesAgent.length > 0) {
				
				employee = salesAgent[0].getEmployeeFirstName();
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return employee;
	}


//jagadish for RejectClaim Search by Claims Criteries 

	public Query getRejectClaimByClaim(String stats, String empRoleAcc, List<Integer> divIds, String emplID,
			String claimID, Date parsed_from, Date parsed_to, String claimTotalFrom, String claimTotalTo, String itemId,
			EntityManager em) 
	{
		Query query = null;
		String claimQuery = null;
		
		String div = divIds.toString(); 
		char a=div.charAt(1);
		String finaldiv=String.valueOf(a);
		BigDecimal divisionid=new BigDecimal(finaldiv);
		BigDecimal status=new BigDecimal(stats);
		BigDecimal bigclaimtotalfrom=null;
		BigDecimal bigclaimtotalto=null;
		
		if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
		{
			System.out.println("reject linked agnet");
			claimQuery="SELECT cth FROM ClaimTranHeader cth, ClaimDetail cd where cth.claimId = cd.id.claimId AND"
								 +" cd.status=:status AND cd.empId=:emplID AND cd.divisionId=:divisionid";
			
		}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
		{
			System.out.println("reject within deivision");
			claimQuery="SELECT cth FROM ClaimTranHeader cth, ClaimDetail cd where cth.claimId = cd.id.claimId AND"
								 +" cd.status=:status AND cd.divisionId=:divisionid";
		
		}else if(empRoleAcc.equalsIgnoreCase("All"))
		{
			System.out.println("reject All");
			claimQuery="SELECT cth FROM ClaimTranHeader cth, ClaimDetail cd where cth.claimId = cd.id.claimId AND"
					 			 +" cd.status=:status";
		}
		
			if(claimID!=null && !claimID.equalsIgnoreCase(""))
			{
			 claimQuery=claimQuery+" AND cd.id.claimId=:claimID"; 
			}
			if(parsed_from!=null && !parsed_from.equals(""))
			{
			 claimQuery=claimQuery+" AND cd.id.claimDate >=:parsed_from";
			}
			if(parsed_to!=null && !parsed_to.equals(""))
			{
			 claimQuery=claimQuery+" AND cd.id.claimDate <=:parsed_to";
			}
			if(claimTotalFrom!=null && !claimTotalFrom.equalsIgnoreCase(""))
			{
			 bigclaimtotalfrom = new BigDecimal(claimTotalFrom);
			 claimQuery=claimQuery+" AND cd.claimTotal >=:bigclaimtotalfrom";
			}
			if(claimTotalTo!=null && !claimTotalTo.equalsIgnoreCase(""))
			{
			 bigclaimtotalto = new BigDecimal(claimTotalTo);
			 claimQuery=claimQuery+" AND cd.claimTotal <=:bigclaimtotalto";
			}
			claimQuery=claimQuery+" ORDER BY cd.id.claimDate DESC";
			query = em.createQuery(claimQuery, ClaimTranHeader.class);
			
			if(empRoleAcc.equalsIgnoreCase("Linked Agent")){
				query.setParameter("emplID", emplID);
				query.setParameter("divisionid", divisionid);
				query.setParameter("status", status);
			}else if(empRoleAcc.equalsIgnoreCase("Within Division")){
				query.setParameter("divisionid", divisionid);
 				query.setParameter("status", status);
			}else if(empRoleAcc.equalsIgnoreCase("All")){
				query.setParameter("status", status);
			}
			if(claimID!=null && !claimID.equalsIgnoreCase("")){
				query.setParameter("claimID", claimID);
			}
			if(parsed_from!=null && !parsed_from.equals("")){
				query.setParameter("parsed_from", parsed_from);
			}
			if(parsed_to!=null && !parsed_to.equals("")){
				query.setParameter("parsed_to", parsed_to);
			}
			if(claimTotalFrom!=null && !claimTotalFrom.equalsIgnoreCase("")){
				query.setParameter("bigclaimtotalfrom", bigclaimtotalfrom);
			}
			if(claimTotalTo!=null && !claimTotalTo.equalsIgnoreCase("")){
				query.setParameter("bigclaimtotalto", bigclaimtotalto);
			}
		return query;
	}
	
	// RejectClaim Search by Orders Criterai modifying....
	public Query getRejectClaimByOrder(String stats, String empRoleAcc, List<Integer> divIds, String emplID,
			String orderID, Date parsed_from, Date parsed_to, String order_total_from, String order_total_to,
			String itemId, EntityManager em) 
	{
		Query query = null;
		String claimQuery = null;
		  
		String div = divIds.toString(); 
		char a=div.charAt(1);
		String finaldiv=String.valueOf(a);
		BigDecimal divisionid=new BigDecimal(finaldiv);
		BigDecimal status=new BigDecimal(stats);
		BigDecimal bigordertotalfrom=null;
		BigDecimal bigordertotalto=null;
		
		/*select cth.* from CLAIM_TRAN_HEADER cth,CLAIM_DETAILS cd where cth.CLAIM_ID = cd.CLAIM_ID AND 
		cd.STATUS='6' AND cd.EMP_ID='1050' AND cd.DIVISION_ID='2' AND cd.CLAIM_DATE <='14-May-2017';
		*/
		claimQuery="SELECT cth FROM ClaimTranHeader cth,ClaimTranSum ctm,OrderDetail od where"
				 +" od.id.orderId=ctm.idOrd AND ctm.id.dcDyOrd=cth.id.dcDyOrd AND ctm.id.rtStrId=cth.id.rtStrId AND"
				 +" ctm.id.ordWs=cth.id.ordWs AND ctm.id.trnSeq=cth.id.trnSeq AND cth.scOrd=:status";
				
		
		if(empRoleAcc.equalsIgnoreCase("Linked Agent")){
			claimQuery=claimQuery+" AND od.empId=:emplID";
			
		}else if(empRoleAcc.equalsIgnoreCase("Within Division")){
			claimQuery=claimQuery+" AND od.divisionId=:divisionid";
		}
		
		 if(orderID!=null && !orderID.equalsIgnoreCase("")){
			 claimQuery=claimQuery+" AND od.id.orderId=:orderID"; 
		 }
		 if(parsed_from!=null && !parsed_from.equals("")){
			 claimQuery=claimQuery+" AND od.id.orderDate >=:parsed_from";
		 }
		 if(parsed_to!=null && !parsed_to.equals("")){
			 claimQuery=claimQuery+" AND od.id.orderDate <=:parsed_to";
		 }
		 if(order_total_from!=null && !order_total_from.equalsIgnoreCase(""))
		 {
			 bigordertotalfrom =new BigDecimal(order_total_from);
			claimQuery=claimQuery+" AND od.orderTotal >=:bigordertotalfrom";
		 }
		 if(order_total_to!=null && !order_total_to.equalsIgnoreCase(""))
		 {
			 bigordertotalto =new BigDecimal(order_total_to);
			 claimQuery=claimQuery+" AND od.orderTotal <=:bigordertotalto";
		 }
		 claimQuery=claimQuery+" ORDER BY od.id.orderDate DESC";
		 System.out.println("finale query: "+claimQuery);
		 	
		 query = em.createQuery(claimQuery,ClaimTranHeader.class).setParameter("status", status);
		
		 if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
			{
			 query.setParameter("emplID", emplID);
			}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
			{
			query.setParameter("divisionid", divisionid);
			}
		 if(orderID!=null && !orderID.equalsIgnoreCase(""))
		 	{
			 query.setParameter("orderID", orderID); 
		 	}
		 	if(parsed_from!=null && !parsed_from.equals(""))
		 	{
			 query.setParameter("parsed_from", parsed_from);
		 	}
		 	if(parsed_to!=null && !parsed_to.equals(""))
		 	{
		 		query.setParameter("parsed_to", parsed_to);
		 	}
		 	if(order_total_from!=null && !order_total_from.equalsIgnoreCase(""))
		 	{
			 query.setParameter("bigordertotalfrom", bigordertotalfrom);
		 	}
		 	if(order_total_to!=null && !order_total_to.equalsIgnoreCase(""))
		 	{
			 query.setParameter("bigordertotalto", bigordertotalto); 
		 	}
		return query;
	}



	// reject claim search by Order ID,Claim ID & customer info
	public Query getRejectClaimByCustomerInfo(String stats, String empRoleAcc, List<Integer> divIds, String emplID,
			String orderID, String claimID, String customerInfo, String itemId, EntityManager em) {
		
		Query query = null;
		String claimQuery = null;
		
		String div = divIds.toString(); 
		char a=div.charAt(1);
		String finaldiv=String.valueOf(a);
		BigDecimal divisionid=new BigDecimal(finaldiv);
		BigDecimal status=new BigDecimal(stats);
		
		claimQuery="SELECT cth FROM ClaimTranHeader cth,ClaimTranSum ctm,ClaimDetail cd where cth.claimId = cd.id.claimId AND"
				+" ctm.id.rtStrId=cth.id.rtStrId AND ctm.id.ordWs=cth.id.ordWs AND ctm.id.trnSeq=cth.id.trnSeq AND"
				+" ctm.id.dcDyOrd=cth.id.dcDyOrd ANd cth.scOrd=:status";
	
		if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
		{
			claimQuery=claimQuery+" AND cd.empId=:emplID";
			
		}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
		{
			claimQuery=claimQuery+" AND cd.divisionId=:divisionid";
		}
			if(orderID!=null && !orderID.equalsIgnoreCase(""))
			{
			 claimQuery=claimQuery+" AND ctm.idOrd =:orderID"; 
			}
			if(claimID!=null && !claimID.equalsIgnoreCase(""))
			{
			 claimQuery=claimQuery+" AND cd.id.claimId=:claimID";
			}
			
			claimQuery=claimQuery+" ORDER BY cd.id.claimDate DESC";
		query = em.createQuery(claimQuery, ClaimTranHeader.class).setParameter("status", status);
		
			if(empRoleAcc.equalsIgnoreCase("Linked Agent")){
				query.setParameter("emplID", emplID);
				
			}else if(empRoleAcc.equalsIgnoreCase("Within Division")){
				query.setParameter("divisionid", divisionid);
 			
			}			
			if(orderID!=null && !orderID.equalsIgnoreCase("")){
				query.setParameter("orderID", orderID);
			}
			if(claimID!=null && !claimID.equalsIgnoreCase("")){
				query.setParameter("claimID", claimID);
			}
			return query;
	}
	
//get all sales agent names ok
	
	public Query getClaimSalesAgentNames(List<Integer> divIds, String empRoleAcc, String emplID, String salesAgnetName,EntityManager em) 
	{
		Query query = null;
		String claimQuery = null;
		
		claimQuery="SELECT risp FROM RisplDkEmpMstr risp where risp.id.empId=:salesAgnetName";
		//Select * from RISPL_DK_EMP_MSTR where EMP_ID='0032';
		query = em.createQuery(claimQuery,RisplDkEmpMstr.class).setParameter("salesAgnetName", salesAgnetName);
		
		return query;
	}

	//claim search by order criteria Modifying...
	public Query getClaimQueryByClaimOrderDetails(List<Integer> divIds, String empRoleAcc, String emplID,
			String orderID, Date parsed_from, Date parsed_to, String ordertotalfrom, String ordertotalto,
			EntityManager em) {
		Query query = null;
		try{
		String claimQuery = null;
		
		String div = divIds.toString(); 
		char a=div.charAt(1);
		String finaldiv=String.valueOf(a);
		BigDecimal divisionid=new BigDecimal(finaldiv);
		
		BigDecimal bigordertotalfrom=null;
		BigDecimal bigordertotalto=null;
		
		
		claimQuery="SELECT cth FROM ClaimTranHeader cth,ClaimTranSum ctm,OrderDetail od where"
				 +" od.id.orderId=ctm.idOrd AND ctm.id.dcDyOrd=cth.id.dcDyOrd AND"
				 +" ctm.id.rtStrId=cth.id.rtStrId AND ctm.id.ordWs=cth.id.ordWs AND ctm.id.trnSeq=cth.id.trnSeq";
				
		
		if(empRoleAcc.equalsIgnoreCase("Linked Agent")){
			claimQuery=claimQuery+" AND od.empId=:emplID";
			
		}else if(empRoleAcc.equalsIgnoreCase("Within Division")){
			claimQuery=claimQuery+" AND od.divisionId=:divisionid";
		}
		
		 if(orderID!=null && !orderID.equalsIgnoreCase("")){
			 claimQuery=claimQuery+" AND od.id.orderId=:orderID"; 
		 }
		 if(parsed_from!=null && !parsed_from.equals("")){
			 claimQuery=claimQuery+" AND od.id.orderDate >=:parsed_from";
		 }
		 if(parsed_to!=null && !parsed_to.equals("")){
			 claimQuery=claimQuery+" AND od.id.orderDate <=:parsed_to";
		 }
		 if(ordertotalfrom!=null && !ordertotalfrom.equalsIgnoreCase(""))
		 {
			 bigordertotalfrom =new BigDecimal(ordertotalfrom);
			claimQuery=claimQuery+" AND od.orderTotal >=:bigordertotalfrom";
		 }
		 if(ordertotalto!=null && !ordertotalto.equalsIgnoreCase(""))
		 {
			 bigordertotalto =new BigDecimal(ordertotalto);
			 claimQuery=claimQuery+" AND od.orderTotal <=:bigordertotalto";
		 }
		 claimQuery=claimQuery+" ORDER BY od.id.orderDate DESC";
		 System.out.println("finale query: "+claimQuery);
		 	
		 query = em.createQuery(claimQuery,ClaimTranHeader.class);
		
		 if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
			{
			 query.setParameter("emplID", emplID);
			}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
			{
			query.setParameter("divisionid", divisionid);
			}
		 if(orderID!=null && !orderID.equalsIgnoreCase(""))
		 	{
			 query.setParameter("orderID", orderID); 
		 	}
		 	if(parsed_from!=null && !parsed_from.equals(""))
		 	{
			 query.setParameter("parsed_from", parsed_from);
		 	}
		 	if(parsed_to!=null && !parsed_to.equals(""))
		 	{
		 		query.setParameter("parsed_to", parsed_to);
		 	}
		 	if(ordertotalfrom!=null && !ordertotalfrom.equalsIgnoreCase(""))
		 	{
			 query.setParameter("bigordertotalfrom", bigordertotalfrom);
		 	}
		 	if(ordertotalto!=null && !ordertotalto.equalsIgnoreCase(""))
		 	{
			 query.setParameter("bigordertotalto", bigordertotalto); 
		 	}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
			
		return query;
	}
	
	//claim search by claim Criteria jagadish
		public Query getClaimQueryByClaimDateTotalFromTo(List<Integer> divIds, String emplName,String empRoleAcc, String emplID,String claimID, Date from, Date to, String claimTotalFrom, String claimTotalTo, EntityManager em) 
		{
				Query query = null;
				String claimQuery = null;
				
				String div = divIds.toString(); 
				char a=div.charAt(1);
				String finaldiv=String.valueOf(a);
				BigDecimal divisionid=new BigDecimal(finaldiv);
				
				BigDecimal bigclaimtotalfrom=null;
				BigDecimal bigclaimtotalto=null;
				
				claimQuery="SELECT cth FROM ClaimTranHeader cth,ClaimTranSum ctm,ClaimDetail cd where cth.claimId = cd.id.claimId AND"
						+" ctm.id.rtStrId=cth.id.rtStrId AND ctm.id.ordWs=cth.id.ordWs AND ctm.id.trnSeq=cth.id.trnSeq AND"
						+" ctm.id.dcDyOrd=cth.id.dcDyOrd";
				
				if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
				{
					claimQuery=claimQuery+" AND cd.empId=:emplID";
				}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
				{
					claimQuery=claimQuery+" AND cd.divisionId=:divisionid";
				}
				if(claimID!=null && !claimID.equalsIgnoreCase(""))
				 {
					 claimQuery=claimQuery+" AND cd.id.claimId=:claimID"; 
				 }
				 if(from!=null && !from.equals(""))
				 {
					 claimQuery=claimQuery+" AND cd.id.claimDate >=:from";
				 }
				 if(to!=null && !to.equals(""))
				 {
					 claimQuery=claimQuery+" AND cd.id.claimDate <=:to";
				 }
				 if(claimTotalFrom!=null && !claimTotalFrom.equalsIgnoreCase(""))
				 {
					 bigclaimtotalfrom =new BigDecimal(claimTotalFrom);
					claimQuery=claimQuery+" AND cd.claimTotal >=:bigclaimtotalfrom";
				 }
				 if(claimTotalTo!=null && !claimTotalTo.equalsIgnoreCase(""))
				 {
					 bigclaimtotalto =new BigDecimal(claimTotalTo);
					 claimQuery=claimQuery+" AND cd.claimTotal <=:bigclaimtotalto";
				 }
				
				 claimQuery=claimQuery+" ORDER BY cd.id.claimDate DESC";
				 	
				 query = em.createQuery(claimQuery,ClaimTranHeader.class);
				
				 if(empRoleAcc.equalsIgnoreCase("Linked Agent"))
					{
					 query.setParameter("emplID", emplID);
					}else if(empRoleAcc.equalsIgnoreCase("Within Division"))
					{
					query.setParameter("divisionid", divisionid);
					}
				
				 	if(claimID!=null && !claimID.equalsIgnoreCase(""))
				 	{
					 query.setParameter("claimID", claimID); 
				 	}
				 	if(from!=null && !from.equals(""))
				 	{
					 query.setParameter("from", from);
				 	}
				 	if(to!=null && !to.equals(""))
				 	{
				 		query.setParameter("to", to);
				 	}
				 	if(claimTotalFrom!=null && !claimTotalFrom.equalsIgnoreCase(""))
				 	{
					 query.setParameter("bigclaimtotalfrom", bigclaimtotalfrom);
				 	}
				 	if(claimTotalTo!=null && !claimTotalTo.equalsIgnoreCase(""))
				 	{
					 query.setParameter("bigclaimtotalto", bigclaimtotalto); 
				 	}
			return query;
		}

		
}
