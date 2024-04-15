package rispl.ds.claim;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.claim.ClaimDetail;
import rispl.db.model.claim.ClaimTranDscItm;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.claim.ClaimRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.customer.CustomerLimit;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.ReasonCodes;
import rispl.ds.context.DKartContext;
import sds.struts.simpleobjects.claim.Claim;
import util.email.ClaimEmail;
import utility.ConfigUtils;

public class ApproveClaimAction extends DSAction {
	
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = LogManager.getLogger(ApproveClaimAction.class);
	/*List<Claim> approvalList = new ArrayList<>();*/
	List<ClaimDetail> approvalClaimList = new ArrayList<ClaimDetail>();
	String custId, customerName, claimDate, rejClaimDate,invoicenNo, globalReasonCodeName,claimStatus, site_address,claimPerStatus;
	

	private Date claimedDate,rejectedClaimDate;
	public BigDecimal dkartSlsTot, dkartNetTot,dkartTaxTot,dkartDiscTot;
	private String claimID,rejectClaimNotes;
	private Claim claim;
	public ClaimTranHeader claimTranHeader;
	public List<ClaimTranLineItem> claimTranLineItems;
	//private SessionMap<String, Object> sessionmap;
	public Map<String,String> returnReasonCodeMap;
	private List<BigDecimal> approvedQtyList;
	private List<BigDecimal> approvedPriceList;
	private List<BigDecimal> netPriceList;
	private List<BigDecimal> discountsList;
	/*
	 * To get the updated discounts while approve
	 */
	private List<BigDecimal> itemLevelDiscountList;
	private List<BigDecimal> tranLevelDiscountList;
	
	private BigDecimal dkartSlsTot2;
	private BigDecimal dkartNetTot2;

	/**
	 * This rejectClaimRange range to search Reject Claims.
	 */
	private String approveClaimRange;
	/**
	 * Sales Agent Name
	 */
	private String salesAgentName;
	
	@Override
	public String execute() throws Exception {

		/*ClaimRemote claimRemote = DKartContext.getClaimBean();
		List<ClaimTranHeader> claimsToApproveList = claimRemote.getClaimsToApprove();
		System.out.println("Claims to Approve: " + claimsToApproveList);
		if (claimsToApproveList == null)
			return SUCCESS;
		for (ClaimTranHeader claimTran : claimsToApproveList) {
			Claim claim = setClaim(claimTran);

			approvalList.add(claim);
		}
		return SUCCESS;*/

		//LookUpEmployeeIfc lookUpEmployeeIfc = DKartContext.getLookupEmployee();
		//List<Integer> empDivisions = lookUpEmployeeIfc.getEmployeeDivisions(getEmployee());

		ClaimRemote claimRemote = DKartContext.getClaimBean();
		approvalClaimList = claimRemote.getClaimsToApprove(getEmployee());
		setApproveClaimRange(DKartContext.getParamterBean().fetchXMLParameterValues().getApproveClaimSearchRange());
		return SUCCESS;
	}

	public String approveClaimDetails() {
		try {
			OrderTransactionsIfc dao = DKartContext.getLookupOrder();
			claimTranHeader = dao.getClaimTranHeader(claimID, new String("1"));
			claimTranLineItems = claimTranHeader.getClaimTranLineItems();
			claimID = claimTranHeader.getClaimId();
			custId = claimTranHeader.getClaimTranSum().getOrdIdCt();//customer ID
			CustomerHeader customerHeader = dao.getCustomerHeader(custId);
			customerName = customerHeader.getCtNm();
			if(claimTranHeader.getCtDvrInf()!=null){
				 site_address = claimTranHeader.getCtDvrInf();
			}else if(claimTranHeader.getScTran()!=null){
				site_address = dao.getCustomerSiteAddrss(custId, claimTranHeader.getScTran().toString());
			}
			// customerName = claimTranHeader.getClaimTranSums().get(0).get 
			claimDate = claimTranHeader.getClaimTranSum().getId().getDcDyOrd();
			claimedDate=getRequiredDate(claimDate);
			claimTranLineItems = claimTranHeader.getClaimTranLineItems();
			returnReasonCodeMap = ReasonCodes.getReturnReasonCodes();
			globalReasonCodeName = returnReasonCodeMap.get(claimTranHeader.getRcRtnMr());
			dkartSlsTot = claimTranHeader.getClaimTranSum().getDkartSlsTot();
			dkartDiscTot = claimTranHeader.getClaimTranSum().getDkartDscTot();
			dkartNetTot = claimTranHeader.getClaimTranSum().getDkartNetTot();
			dkartTaxTot = claimTranHeader.getClaimTranSum().getDkartTaxTot();
			invoicenNo = claimTranHeader.getClaimTranSum().getIdOrdArNmb();
			claimStatus=getStatusOfClaim(claimTranHeader.getScOrd());
			claimPerStatus=calculatPerbyCustId(custId);
			setSalesAgentName(getSalesAgentNameById(claimTranHeader.getEmId()));
			putInSession(SESSION.CLAIM_TRANHEADER, claimTranHeader);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;
	}
	
	
	//@mallikarjun
	public String getClaimDetails() {
		try {
			OrderTransactionsIfc dao = DKartContext.getLookupOrder();
			claimTranHeader = dao.getClaimTranHeader(claimID, null);
			ClaimRemote claimRemote = DKartContext.getClaimBean();
			claimTranHeader = claimRemote.getUpdatedReceivedQty(claimTranHeader); // Show disposition codes of the wh received items
			if(claimTranHeader.getScOrd().compareTo(new BigDecimal(4))==0){
			claimTranHeader = claimRemote.getUpdatedTotals(claimTranHeader); // updating the totals based on the warehouse received qty and approve price
			}
			claimTranLineItems = claimTranHeader.getClaimTranLineItems();
			claimID = claimTranHeader.getClaimId();
			custId = claimTranHeader.getClaimTranSum().getOrdIdCt();//customer ID
			CustomerHeader customerHeader = dao.getCustomerHeader(custId);
			if(claimTranHeader.getCtDvrInf()!=null){
				 site_address = claimTranHeader.getCtDvrInf();
			}else if(claimTranHeader.getScTran()!=null){
				site_address = dao.getCustomerSiteAddrss(custId, claimTranHeader.getScTran().toString());
			}
			customerName = customerHeader.getCtNm();
			claimStatus=getStatusOfClaim(claimTranHeader.getScOrd());
			// customerName = claimTranHeader.getClaimTranSum().get 
			claimDate = claimTranHeader.getClaimTranSum().getId().getDcDyOrd();
			claimedDate=getRequiredDate(claimDate);
			if(claimTranHeader.getTsMdfRcrd()!=null){
			rejClaimDate=claimTranHeader.getTsMdfRcrd().toString().substring(0, 10);
			rejectedClaimDate=getRequiredRejClaimDate(rejClaimDate);
			}
			rejectClaimNotes=claimTranHeader.getClaimRejectNotes();
			claimTranLineItems = claimTranHeader.getClaimTranLineItems();
			returnReasonCodeMap = ReasonCodes.getReturnReasonCodes();
			globalReasonCodeName = returnReasonCodeMap.get(claimTranHeader.getRcRtnMr());
			dkartSlsTot = claimTranHeader.getClaimTranSum().getDkartSlsTot();
			dkartDiscTot = claimTranHeader.getClaimTranSum().getDkartDscTot();
			dkartNetTot = claimTranHeader.getClaimTranSum().getDkartNetTot();
			dkartTaxTot = claimTranHeader.getClaimTranSum().getDkartTaxTot();
			invoicenNo = claimTranHeader.getClaimTranSum().getIdOrdArNmb();
			setSalesAgentName(getSalesAgentNameById(claimTranHeader.getEmId()));
			putInSession(SESSION.CLAIM_TRANHEADER, claimTranHeader);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}
	

	public String approveClaimButton() {

		return SUCCESS;
	}

	public String rejectClaimButton() {
		return SUCCESS;
	}

	private Claim setClaim(ClaimTranHeader claimTran) {
		Claim claim = new Claim();
		claim.setClaimID(claimTran.getClaimId());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			claim.setClaimDate(dateFormat.parse(claimTran.getId().getDcDyOrd()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		claim.setClaimTotal(claimTran.getClaimTranSum().getDkartNetTot());
		claim.setCustomerID(claimTran.getClaimTranSum().getOrdIdCt());
		LookUpCustomerIfc customerLookup = null;
		try {
			customerLookup = DKartContext.getLookUpCustomer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		CustomerSearchCriteria custSearchCriteria = new CustomerSearchCriteria();
		custSearchCriteria.setCustomerId(claim.getCustomerID());
		CustomerIfc[] customer = customerLookup.lookUpCust(custSearchCriteria);
		if (customer != null && customer.length > 0)
			claim.setCustomerName(customer[0].getCustomerHeader().getCtNm());

		claim.setClaimQty(getClaimQty(claimTran));

		claim.setSalesAgent(getSalesAgent(claimTran));
		claim.setClaimReasonCodeDesc(getReasonCodeName(claimTran));
		return claim;
	}

	private String getReasonCodeName(ClaimTranHeader claimTran) {
		String reasonCode = claimTran.getRcRtnMr();
		Map<String, String> returnReasonCodes = ReasonCodes.getReturnReasonCodes();
		System.out.println(returnReasonCodes);
		if (returnReasonCodes != null && returnReasonCodes.containsKey(reasonCode))
			return returnReasonCodes.get(reasonCode);
		return null;

	}

	private String getSalesAgent(ClaimTranHeader claimTran) {
		String salesAgentName = null;
		String salesAgentID = claimTran.getEmId();
		try {
			LookUpEmployeeIfc empLookup = DKartContext.getLookupEmployee();
			EmployeeSearchCriteriaIfc empsearchCriteria = new EmployeeSearchCriteria();
			empsearchCriteria.setEmployeeId(salesAgentID);
			EmployeeIfc[] employees = empLookup.lookupSalesAgent(empsearchCriteria);
			if (employees != null && employees.length > 0) {
				salesAgentName = employees[0].getEmployeeName();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return salesAgentName;
	}

	private String getClaimQty(ClaimTranHeader claimTran) {
		BigDecimal claimQty = null;
		if (claimTran != null && claimTran.getClaimTranLineItems() != null) {
			claimQty = new BigDecimal(0);
			for (ClaimTranLineItem claimTranLineItem : claimTran.getClaimTranLineItems()) {
				if (claimTranLineItem.getItmTy().compareTo(BigDecimal.ONE) == 0) // Stock Item (not a Service Item)
					claimQty = claimQty.add(claimTranLineItem.getLineQntRtn());
			}
		}
		return claimQty.toPlainString();
	}

	/*public List<Claim> getApprovalList() {
		return approvalList;
	}*/

	public String getClaimID() {
		return claimID;
	}

	public void setClaimID(String claimID) {
		this.claimID = claimID;
	}

	public List<ClaimDetail> getApprovalClaimList() {
		return approvalClaimList;
	}

	public void setApprovalClaimList(List<ClaimDetail> approvalClaimList) {
		this.approvalClaimList = approvalClaimList;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	//addedby hanu
	public String saveApproveClaimDetails() {
		try {
			CustomerHeader customerHeader=null;
			ClaimTranHeader claimTranHeader = (ClaimTranHeader) getFromSession(SESSION.CLAIM_TRANHEADER);
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			DecimalFormat df = new DecimalFormat("#.###");
			df.setRoundingMode(RoundingMode.FLOOR);
			claimTranHeader.setScOrd(new BigDecimal(2));
			int i=0;
			BigDecimal dkartDscTot = BigDecimal.ZERO;
			for (ClaimTranLineItem claimTranLineItem : claimTranHeader.getClaimTranLineItems()) 
			{				
				BigDecimal lineDiscAmt = BigDecimal.ZERO;
				BigDecimal discAmt = BigDecimal.ZERO;
				claimTranLineItem.setApprClaimQty(approvedQtyList.get(i));			
				claimTranLineItem.setApprClaimPrice(approvedPriceList.get(i));
				claimTranLineItem.setExtnDscLnItm(netPriceList.get(i));
				if(claimTranLineItem.getClaimTranDscItms() != null && claimTranLineItem.getClaimTranDscItms().size() > 0 ){ // CHECK WHETHER DISCOUNTS ARE AVAILABLE 
					for(ClaimTranDscItm dsc : claimTranLineItem.getClaimTranDscItms()){
						discAmt = discAmt.add(dsc.getDscAmt());
					}
					BigDecimal formatDecimal = ConfigUtils.getInstance().createBigDecimal(0, "format.currency"); //GETTING THE FORMAT
					lineDiscAmt = discAmt.divide(claimTranLineItem.getLineQntRtn(),6,RoundingMode.HALF_DOWN); // DISCOUNT FOR SINGLE QTY
					dkartDscTot = dkartDscTot.add(lineDiscAmt.multiply(claimTranLineItem.getApprClaimQty())); // CALCULATE DISCOUNT TOTAL
					String num = df.format(netPriceList.get(i).add(lineDiscAmt.multiply(claimTranLineItem.getApprClaimQty())));
					claimTranLineItem.setExtnLnItmRtn(new BigDecimal(num));
					// UPDATING THE DISCOUNTS
					if((discountsList.get(i).doubleValue() == discAmt.doubleValue()) == false){ //CHECK DISCOUNT CHANGE[false=change,true=no change] 
						if(claimTranLineItem.getClaimTranDscItms().size() == 1){ // SINGLE DISCOUNT
							claimTranLineItem.getClaimTranDscItms().get(0).setDscAmt(ConfigUtils.getInstance().createBigDecimal(lineDiscAmt.multiply(claimTranLineItem.getApprClaimQty()), "format.currency"));
						}else{ // MULTIPLE DISCOUNTS
							for(ClaimTranDscItm dsc : claimTranLineItem.getClaimTranDscItms()){
								if(dsc.getTyDsc().compareTo(BigDecimal.ZERO) == 0){
									dsc.setDscAmt(itemLevelDiscountList.get(i));
								}
								if(dsc.getTyDsc().compareTo(BigDecimal.ONE) == 0){
									dsc.setDscAmt(tranLevelDiscountList.get(i));
								}
							}
						}
						LOGGER.info("Discounts are updated for LineItem, sequence : "+claimTranLineItem.getId().getTrnSeq());
					}
				}else{
					claimTranLineItem.setExtnLnItmRtn(netPriceList.get(i));
				}
				i++;
			}
			claimTranHeader.getClaimTranSum().setDkartNetTot(dkartNetTot2);
			claimTranHeader.getClaimTranSum().setDkartSlsTot(dkartSlsTot2);
			claimTranHeader.getClaimTranSum().setDkartDscTot(new BigDecimal(df.format(dkartDscTot)));
			ordTrn.persistClaimTransaction(claimTranHeader);
			custId=claimTranHeader.getClaimTranSum().getOrdIdCt();
			customerHeader = ordTrn.getCustomerHeader(custId);
			putInSession(SESSION.CLAIM_TRANHEADER, null);
			claimID=claimTranHeader.getClaimId();
			claimDate = claimTranHeader.getClaimTranSum().getId().getDcDyOrd();
			claimedDate=getRequiredDate(claimDate);
			customerName = customerHeader.getCtNm();
			if(claimTranHeader.getCtDvrInf()!=null){
				 site_address = claimTranHeader.getCtDvrInf();
			}else if(claimTranHeader.getScTran()!=null){
				site_address = ordTrn.getCustomerSiteAddrss(custId, claimTranHeader.getScTran().toString());
			}
			dkartNetTot2=claimTranHeader.getClaimTranSum().getDkartNetTot();
			new ClaimEmail().sendClaimAproveEmail(claimTranHeader);
			removeFromSession(SESSION.CLAIM_TRANHEADER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return ERROR;
		}

		return SUCCESS;
	}
	

		//addedby hanu
		public String rejectClaim() {
			try {
				ClaimTranHeader claimTranHeader = (ClaimTranHeader) getFromSession(SESSION.CLAIM_TRANHEADER);
				OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
				claimTranHeader.setScOrd(new BigDecimal(6));
				claimTranHeader.setClaimRejectNotes(rejectClaimNotes);
				ordTrn.persistClaimTransaction(claimTranHeader);
				putInSession(SESSION.CLAIM_TRANHEADER, null);
				custId=claimTranHeader.getClaimTranSum().getOrdIdCt();
				CustomerHeader customerHeader = ordTrn.getCustomerHeader(custId);
				if(claimTranHeader.getCtDvrInf()!=null){
					 site_address = claimTranHeader.getCtDvrInf();
				}else if(claimTranHeader.getScTran()!=null){
					site_address = ordTrn.getCustomerSiteAddrss(custId, claimTranHeader.getScTran().toString());
				}
				//new ClaimEmail().sendClaimAproveEmail(claimTranHeader);
				claimID=claimTranHeader.getClaimId();
				claimDate = claimTranHeader.getClaimTranSum().getId().getDcDyOrd();
				claimedDate=getRequiredDate(claimDate);
				customerName = customerHeader.getCtNm();
				dkartNetTot=claimTranHeader.getClaimTranSum().getDkartNetTot();
				removeFromSession(SESSION.CLAIM_TRANHEADER);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ERROR;
			}

			return SUCCESS;
		}
		
		
		//to get the required date format
		public Date getRequiredDate(String claimDate){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				claimedDate = sdf.parse(claimDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return claimedDate;
		}
		
		public Date getRequiredRejClaimDate(String claimDate){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				rejectedClaimDate = sdf.parse(claimDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rejectedClaimDate;
		}
		
		//to get customerHeader
		public CustomerHeader getCustomerHeader(ClaimTranHeader claimTranHeader) throws Exception{
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			CustomerHeader customerHeader=ordTrn.getCustomerHeader(claimTranHeader.getClaimTranSum().getOrdIdCt());
			return customerHeader;
		}
		
		/*for claim status @mallikarjun*/
		static String getStatusOfClaim(BigDecimal scOrd) {
			String stat=null;
			if(scOrd.toString().equalsIgnoreCase("1")){
				stat= "Registered";
			}else
				if(scOrd.toString().equalsIgnoreCase("5")){
					stat= "Accepted";
					}else
						if(scOrd.toString().equalsIgnoreCase("6")){
							stat= "Rejected";
							}else
								if(scOrd.toString().equalsIgnoreCase("2")){
									stat= "Approved";
								}else
									if(scOrd.toString().equalsIgnoreCase("3")){
										stat= "In-Progress";
									}else
										if(scOrd.toString().equalsIgnoreCase("4")){
											stat= "Received";
										}else
											{
												stat= "none";
											}
				
			return stat;
		}
		
		/**
		 * To calculate return percent based on the custID
		 * 
		 * @author Lalit Tomar
		 * @param CustId
		 * @return true/false
		 * 
		 */
		public String calculatPerbyCustId(String custId)
		{		
		    String PerStatus=null;
			ClaimRemote claim = null;			
				try{
					claim = DKartContext.getClaimBean();
					PerStatus= claim.calculatPerbyCustId(custId);	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return PerStatus;
		
		}
		
		/**
		 * To return the salesAgentName based on the ID
		 * 
		 * @author Srinivas Reddy G
		 * @param employeeId
		 * @return salesAgentName
		 * 
		 */
		public String getSalesAgentNameById(String empId){
			LOGGER.info("Exceuting the getSalesAgentName() method with empId : "+empId);
			LookUpEmployeeIfc lkupemp = null;
			EmployeeSearchCriteriaIfc empcrt = null;
			salesAgentName = null;
			try{
			lkupemp = DKartContext.getLookupEmployee();
			empcrt = new EmployeeSearchCriteria();
			empcrt.setEmployeeId(empId);
			EmployeeIfc[] emp = lkupemp.lookupSalesAgent(empcrt);
			if(emp!=null&&emp.length>0)
			{
				setSalesAgentName(emp[0].getEmployeeName());
				LOGGER.info("Employee found with EmpId : "+empId+" and EmpName : "+emp[0].getEmployeeName());
			}
			}//end of try block
			catch (Exception e) {
			LOGGER.error("Employee not found with empID : "+empId);
			e.printStackTrace();
			return salesAgentName;
			}//end of catch block
			LOGGER.info("getSalesAgentName() method executed successfully");
			return salesAgentName;
		} //end of method

/*
	public void setSession(Map<String, Object> session) {
		this.sessionmap = (SessionMap<String, Object>) session;

	}

	public SessionMap<String, Object> getSessionmap() {
		return sessionmap;
	}
*/
	public ClaimTranHeader getClaimTranHeader() {
		return claimTranHeader;
	}

	public void setClaimTranHeader(ClaimTranHeader claimTranHeader) {
		this.claimTranHeader = claimTranHeader;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}
	
	public String getRejClaimDate() {
		return rejClaimDate;
	}

	public void setRejClaimDate(String rejClaimDate) {
		this.rejClaimDate = rejClaimDate;
	}
	
	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getClaimPerStatus() {
		return claimPerStatus;
	}

	public void setClaimPerStatus(String claimPerStatus) {
		this.claimPerStatus = claimPerStatus;
	}

	public String getInvoicenNo() {
		return invoicenNo;
	}

	public void setInvoicenNo(String invoicenNo) {
		this.invoicenNo = invoicenNo;
	}

	public List<ClaimTranLineItem> getClaimTranLineItems() {
		return claimTranLineItems;
	}

	public void setClaimTranLineItems(List<ClaimTranLineItem> claimTranLineItems) {
		this.claimTranLineItems = claimTranLineItems;
	}

	public BigDecimal getDkartSlsTot() {
		return dkartSlsTot;
	}

	public void setDkartSlsTot(BigDecimal dkartSlsTot) {
		this.dkartSlsTot = dkartSlsTot;
	}

	public BigDecimal getDkartNetTot() {
		return dkartNetTot;
	}

	public void setDkartNetTot(BigDecimal dkartNetTot) {
		this.dkartNetTot = dkartNetTot;
	}


	

	public Map<String, String> getReturnReasonCodeMap() {
		return returnReasonCodeMap;
	}

	public void setReturnReasonCodeMap(Map<String, String> returnReasonCodeMap) {
		this.returnReasonCodeMap = returnReasonCodeMap;
	}

	public String getGlobalReasonCodeName() {
		return globalReasonCodeName;
	}

	public void setGlobalReasonCodeName(String globalReasonCodeName) {
		this.globalReasonCodeName = globalReasonCodeName;
	}

	public List<BigDecimal> getApprovedQtyList() {
		return approvedQtyList;
	}

	public void setApprovedQtyList(List<BigDecimal> approvedQtyList) {
		this.approvedQtyList = approvedQtyList;
	}

	public List<BigDecimal> getApprovedPriceList() {
		return approvedPriceList;
	}

	public void setApprovedPriceList(List<BigDecimal> approvedPriceList) {
		this.approvedPriceList = approvedPriceList;
	}

	public List<BigDecimal> getNetPriceList() {
		return netPriceList;
	}

	public void setNetPriceList(List<BigDecimal> netPriceList) {
		this.netPriceList = netPriceList;
	}

	public String getRejectClaimNotes() {
		return rejectClaimNotes;
	}

	public void setRejectClaimNotes(String rejectClaimNotes) {
		this.rejectClaimNotes = rejectClaimNotes;
	}

	public Date getClaimedDate() {
		return claimedDate;
	}

	public void setClaimedDate(Date claimedDate) {
		this.claimedDate = claimedDate;
	}

	public Date getRejectedClaimDate() {
		return rejectedClaimDate;
	}

	public void setRejectedClaimDate(Date rejectedClaimDate) {
		this.rejectedClaimDate = rejectedClaimDate;
	}
	public BigDecimal getDkartSlsTot2() {
		return dkartSlsTot2;
	}
	public void setDkartSlsTot2(BigDecimal dkartSlsTot2) {
		this.dkartSlsTot2 = dkartSlsTot2;
	}
	public BigDecimal getDkartNetTot2() {
		return dkartNetTot2;
	}
	public void setDkartNetTot2(BigDecimal dkartNetTot2) {
		this.dkartNetTot2 = dkartNetTot2;
	}

	public String getApproveClaimRange() {
		return approveClaimRange;
	}

	public void setApproveClaimRange(String approveClaimRange) {
		this.approveClaimRange = approveClaimRange;
	}

	public BigDecimal getDkartTaxTot() {
		return dkartTaxTot;
	}

	public void setDkartTaxTot(BigDecimal dkartTaxTot) {
		this.dkartTaxTot = dkartTaxTot;
	}

	public List<BigDecimal> getDiscountsList() {
		return discountsList;
	}
	
	public void setDiscountsList(List<BigDecimal> discountsList) {
		this.discountsList = discountsList;
	}

	public void setDkartDiscTot(BigDecimal dkartDiscTot) {
		this.dkartDiscTot = dkartDiscTot;
	}

	/**
	 * @return the salesAgentName
	 */
	public String getSalesAgentName() {
		return salesAgentName;
	}

	/**
	 * @param salesAgentName the salesAgentName to set
	 */
	public void setSalesAgentName(String salesAgentName) {
		this.salesAgentName = salesAgentName;
	}

	public List<BigDecimal> getItemLevelDiscountList() {
		return itemLevelDiscountList;
	}

	public void setItemLevelDiscountList(List<BigDecimal> itemLevelDiscountList) {
		this.itemLevelDiscountList = itemLevelDiscountList;
	}

	public List<BigDecimal> getTranLevelDiscountList() {
		return tranLevelDiscountList;
	}
	
	public void setTranLevelDiscountList(List<BigDecimal> tranLevelDiscountList) {
		this.tranLevelDiscountList = tranLevelDiscountList;
	}

	public String getSite_address() {
		return site_address;
	}

	public void setSite_address(String site_address) {
		this.site_address = site_address;
	}
	
}
