package com.retailsols.downloadPDF;

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

import com.opensymphony.xwork2.ActionContext;

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
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.ReasonCodes;
import rispl.ds.DSAction.SESSION;
import rispl.ds.claim.ApproveClaimAction;
import rispl.ds.context.DKartContext;
import sds.struts.simpleobjects.claim.Claim;
import util.email.ClaimEmail;
import utility.ConfigUtils;

public class ClaimDetailsDownloadPDFAction extends DSAction{
	
	private static final long serialVersionUID = -2508030433084720118L;
	static final Logger LOGGER = LogManager.getLogger(ApproveClaimAction.class);
    private List<String> list;
    
    List<ClaimDetail> approvalClaimList = new ArrayList<ClaimDetail>();
	String custId, customerName, claimDate, rejClaimDate,invoicenNo, globalReasonCodeName,claimStatus,site_address;
	

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
	
	private String invoiceCancelFlag;
	
	public String getSite_address() {
		return site_address;
	}

	public void setSite_address(String site_address) {
		this.site_address = site_address;
	}
	
	public String getInvoiceCancelFlag() {
		return invoiceCancelFlag;
	}

	public void setInvoiceCancelFlag(String invoiceCancelFlag) {
		this.invoiceCancelFlag = invoiceCancelFlag;
	}

	private BigDecimal dkartSlsTot2;
	private BigDecimal dkartNetTot2;
	private BigDecimal dkartExpenses;
	public BigDecimal getDkartExpenses() {
		return dkartExpenses;
	}

	public void setDkartExpenses(BigDecimal dkartExpenses) {
		this.dkartExpenses = dkartExpenses;
	}

	/**
	 * This rejectClaimRange range to search Reject Claims.
	 */
	private String approveClaimRange;
	/**
	 * Sales Agent Name
	 */
	private String salesAgentName;
	
		//@ jagadish claims ,Rejected claims & Approve Claims search details are save in PDF 
	public String downloadClaimDetails() {
		try {
			list = new ArrayList<>();

			OrderTransactionsIfc dao = DKartContext.getLookupOrder();
			String actionName = ActionContext.getContext().getName();
				if(actionName.equalsIgnoreCase("approveClaimPDFDownload")){
					claimTranHeader = dao.getClaimTranHeader(claimID, new String("1"));
				} else {
					claimTranHeader = dao.getClaimTranHeader(claimID, null);
				}
			ClaimRemote claimRemote = DKartContext.getClaimBean();
			claimTranHeader = claimRemote.getUpdatedReceivedQty(claimTranHeader); // Show disposition codes of the wh received items
			claimTranLineItems = claimTranHeader.getClaimTranLineItems();
			claimID = claimTranHeader.getClaimId();
			custId = claimTranHeader.getClaimTranSum().getOrdIdCt();//customer ID
			if(claimTranHeader.getCtDvrInf()!=null){
				 site_address = claimTranHeader.getCtDvrInf();
			}else if(claimTranHeader.getScTran()!=null){
				 site_address = dao.getCustomerSiteAddrss(custId, claimTranHeader.getScTran().toString());
			}
			CustomerHeader customerHeader = dao.getCustomerHeader(custId);
			customerName = customerHeader.getCtNm();
			claimStatus=getStatusOfClaim(claimTranHeader.getScOrd());
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
			dkartExpenses=claimTranHeader.getClaimTranSum().getDkartExpenses();
			invoicenNo = claimTranHeader.getClaimTranSum().getIdOrdArNmb();
			setSalesAgentName(getSalesAgentNameById(claimTranHeader.getEmId()));
			putInSession(SESSION.CLAIM_TRANHEADER, claimTranHeader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	
	//@ jagadish Accept claim search details save in PDF
	public String downloadAcceptClaimDetails() {
		try {
			
			try {
				OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
				LookUpEmployeeIfc lkupemp = DKartContext.getLookupEmployee();
				EmployeeSearchCriteriaIfc empcrt = new EmployeeSearchCriteria();
				ClaimTranHeader claimTranHeader = ordTrn.getClaimTranHeader(claimID, "4");
				ClaimRemote claimRemote = DKartContext.getClaimBean();
				claimTranHeader = claimRemote.getUpdatedReceivedQty(claimTranHeader); // Splits claim lines based on disposition codes if necessary
				 claimID = claimTranHeader.getClaimId();
				 custId = claimTranHeader.getClaimTranSum().getOrdIdCt();//customer ID
				 if(claimTranHeader.getCtDvrInf()!=null){
					 site_address = claimTranHeader.getCtDvrInf();
				 }else if(claimTranHeader.getScTran()!=null){
					 site_address = ordTrn.getCustomerSiteAddrss(custId, claimTranHeader.getScTran().toString());
				 }
				 CustomerHeader customerHeader = ordTrn.getCustomerHeader(custId);
				 customerName = customerHeader.getCtNm();
				 returnReasonCodeMap = ReasonCodes.getReturnReasonCodes();
				 claimStatus=getStatusOfClaim(claimTranHeader.getScOrd());
				 claimDate = claimTranHeader.getClaimTranSum().getId().getDcDyOrd();
				 claimedDate=getRequiredDate(claimDate);
				 claimTranLineItems = claimTranHeader.getClaimTranLineItems();
				 dkartSlsTot = claimTranHeader.getClaimTranSum().getDkartSlsTot();
				 dkartNetTot = claimTranHeader.getClaimTranSum().getDkartNetTot();
				 dkartDiscTot = claimTranHeader.getClaimTranSum().getDkartDscTot();
				 dkartTaxTot = claimTranHeader.getClaimTranSum().getDkartTaxTot();
				 invoicenNo = claimTranHeader.getClaimTranSum().getIdOrdArNmb();
				 invoiceCancelFlag = claimTranHeader.getFlInvCncl();
				 empcrt.setEmployeeId(claimTranHeader.getEmId());
				 EmployeeIfc[] emp = lkupemp.lookupSalesAgent(empcrt);
				 if(emp!=null&&emp.length>0)
				 {
					setSalesAgentName(emp[0].getEmployeeName());
				 }
				 putInSession(SESSION.READY_TO_CLAIM_TRANHEADER, claimTranHeader);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	//@ jagadish register claim with invoice details save in PDF
		public String registerClaimWithInvDetails() {
			try {
				list = new ArrayList<>();

				OrderTransactionsIfc dao = DKartContext.getLookupOrder();
				claimTranHeader = dao.getClaimTranHeader(claimID, null);
				ClaimRemote claimRemote = DKartContext.getClaimBean();
				claimTranHeader = claimRemote.getUpdatedReceivedQty(claimTranHeader); // Show disposition codes of the wh received items
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
				dkartExpenses=claimTranHeader.getClaimTranSum().getDkartExpenses();
				invoicenNo = claimTranHeader.getClaimTranSum().getIdOrdArNmb();
				setSalesAgentName(getSalesAgentNameById(claimTranHeader.getEmId()));
				putInSession(SESSION.CLAIM_TRANHEADER, claimTranHeader);
			} catch (Exception e) {
				// TODO: handle exception
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
    

    /**
     * @return the list
     */
    public List<String> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<String> list) {
        this.list = list;
    }
    
    
}
