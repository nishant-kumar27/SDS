package rispl.ds.claim;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rispl.dk.claim.model.DiffClaimsList;

import rispl.db.model.claim.ClaimTranDscItm;
import rispl.db.model.claim.ClaimTranDscItmPK;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.detail.claim.ClaimDetailTable;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.ejb.claim.ClaimRemote;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkservices.common.EmployeeSearchCriteria;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;
import rispl.ds.DSAction;
import rispl.ds.ReasonCodes;
import rispl.ds.context.DKartContext;
import util.email.ClaimEmail;

public class AcceptClaim extends DSAction{ 

	private static final long serialVersionUID = 1L;
	
	public ClaimDetailTable[] acceptClaim_List;
	public DiffClaimsList acceptClaim_List1;
    String claimId,custId,customerName,claimDate,invoicenNo,claimStatus, site_address;	
    private Date claimedDate;
	List<ClaimTranLineItem> claimTranLineItems;
   /* private SessionMap<String, Object> sessionmap;*/
    public BigDecimal dkartSlsTot,dkartNetTot,dkartTaxTot,dkartDscTot;
    public Map<String,String> returnReasonCodeMap;
    private List<BigDecimal> acceptedPriceList,netPriceList;
	private BigDecimal dkartSlsTot2,dkartNetTot2;
    /**
	 * This acceptClaimRange range to search Accept Claims.
	 */
	private String acceptClaimRange;
	/**
	 * This autoAcceptClaimRange range to search Auto Accept Claims.
	 */
	private String autoAcceptClaimRange;
	private String invoiceCancelFlag;
	
	private List<BigDecimal> discountsList;

	/**
	 * Sales Agent Name
	 */
	private String salesAgentName;
	
	public String getAcceptClaims() throws Exception{
		List<ClaimDetailTable> acceptClaimList = new ArrayList<ClaimDetailTable>();
		OrderTransactionsIfc trans = DKartContext.getLookupOrder();
		acceptClaimList = trans.getClaimTableDetails("4");
		int count=0;
		for(int i=0;i<acceptClaimList.size();i++){
			if(acceptClaimList.get(i)!=null){
			count=count+1;
			}		
		}
		
		ClaimDetailTable[] newAcceptClaimList=new ClaimDetailTable[count];
		for(int j=0;j<count;j++){		
		
			newAcceptClaimList[j]=acceptClaimList.get(j);
		}
		
		
		setClaimList(newAcceptClaimList);
		return SUCCESS;
		
	}
		
	
	// to show 2 diffnt list (needToBeAccepted,autoAccepted) @Sharanya 
	public String getAcceptClaimsList() throws Exception{
		
		//LookUpEmployeeIfc lookUpEmployeeIfc =  DKartContext.getLookupEmployee();
		//List<Integer> divisionIds = lookUpEmployeeIfc.getEmployeeDivisions(getEmployee());
		
		ClaimRemote claimRemote = DKartContext.getClaimBean();
		DiffClaimsList acceptClaimList =claimRemote.getAcceptClaimDetails(getEmployee());
		setAcceptClaim_List1(acceptClaimList);
		setAutoAcceptClaimRange(DKartContext.getParamterBean().fetchXMLParameterValues().getClaimAutoAcceptedRange());
		setAcceptClaimRange(DKartContext.getParamterBean().fetchXMLParameterValues().getClaimNeedToBeAcceptedRange());
		return SUCCESS;
		
	}
	

	public String readyToAcceptClaimInfo()
	{	
		
		try {
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			LookUpEmployeeIfc lkupemp = DKartContext.getLookupEmployee();
			EmployeeSearchCriteriaIfc empcrt = new EmployeeSearchCriteria();
			ClaimTranHeader claimTranHeader = ordTrn.getClaimTranHeader(claimId, "4");
			ClaimRemote claimRemote = DKartContext.getClaimBean();
			claimTranHeader = claimRemote.getUpdatedReceivedQty(claimTranHeader); // Splits claim lines based on disposition codes if necessary
			claimTranHeader = claimRemote.getUpdatedTotals(claimTranHeader); // updating the totals based on the warehouse received qty and approve price
			 claimId = claimTranHeader.getClaimId();
			 custId = claimTranHeader.getClaimTranSum().getOrdIdCt();//customer ID
			 CustomerHeader customerHeader = ordTrn.getCustomerHeader(custId);
			 if(claimTranHeader.getCtDvrInf()!=null){
				 site_address = claimTranHeader.getCtDvrInf();
			 }else if(claimTranHeader.getScTran()!=null){
					site_address = ordTrn.getCustomerSiteAddrss(custId, claimTranHeader.getScTran().toString());
				}
			 customerName = customerHeader.getCtNm();
			 returnReasonCodeMap = ReasonCodes.getReturnReasonCodes();
			 claimStatus=ApproveClaimAction.getStatusOfClaim(claimTranHeader.getScOrd());
			 claimDate = claimTranHeader.getClaimTranSum().getId().getDcDyOrd();
			 claimedDate=getRequiredDate(claimDate);
			 claimTranLineItems = claimTranHeader.getClaimTranLineItems();
			 dkartSlsTot = claimTranHeader.getClaimTranSum().getDkartSlsTot();
			 dkartNetTot = claimTranHeader.getClaimTranSum().getDkartNetTot();
			 dkartDscTot = claimTranHeader.getClaimTranSum().getDkartDscTot();
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
		
		return SUCCESS;
	}

	
	

	//addedby hanu
	public String  saveAcceptClaimDetails(){
		try {
			ClaimTranHeader claimTranHeader= (ClaimTranHeader)getFromSession(SESSION.READY_TO_CLAIM_TRANHEADER);
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			claimTranHeader.setScOrd(new BigDecimal(5));
			claimTranHeader.setAcceptType("0");
			int i=0;
			for (ClaimTranLineItem claimTranLineItem : claimTranHeader.getClaimTranLineItems()) 
			{				
				claimTranLineItem.setAccptClaimPrice(acceptedPriceList.get(i));			
				/*if((discountsList != null) && (discountsList.size() != 0)){
					claimTranLineItem.setExtnLnItmRtn(netPriceList.get(i).add(discountsList.get(i)));
				}else
					claimTranLineItem.setExtnLnItmRtn(netPriceList.get(i));*/
				if(claimTranLineItem.getClaimTranDscItms() != null ){
					/*BigDecimal discAmt = BigDecimal.ZERO;
					for(ClaimTranDscItm dsc : claimTranLineItem.getClaimTranDscItms()){
						discAmt.add(dsc.getDscAmt());
					}*/
					claimTranLineItem.setExtnLnItmRtn(netPriceList.get(i).add(claimTranLineItem.getExtnLnItmRtn().subtract(claimTranLineItem.getExtnDscLnItm())));
				}else{
					claimTranLineItem.setExtnLnItmRtn(netPriceList.get(i));
				}
				claimTranLineItem.setExtnDscLnItm(netPriceList.get(i));
				i++;
			}
			claimTranHeader.getClaimTranSum().setDkartSlsTot(dkartSlsTot2);
			claimTranHeader.getClaimTranSum().setDkartNetTot(dkartNetTot2);
			ordTrn.persistClaimTransaction(claimTranHeader);
			ordTrn.SaveClaimTransaction(claimTranHeader);
			custId=claimTranHeader.getClaimTranSum().getOrdIdCt();
			CustomerHeader customerHeader = ordTrn.getCustomerHeader(custId);
			if(claimTranHeader.getCtDvrInf()!=null){
				 site_address = claimTranHeader.getCtDvrInf();
			}else if(claimTranHeader.getScTran()!=null){
				site_address = ordTrn.getCustomerSiteAddrss(custId, claimTranHeader.getScTran().toString());
			}
			new ClaimEmail().sendClaimAcceptEmail(claimTranHeader);
			 putInSession(SESSION.READY_TO_CLAIM_TRANHEADER, claimTranHeader);
			 claimId=claimTranHeader.getClaimId();
				claimDate = claimTranHeader.getClaimTranSum().getId().getDcDyOrd();	
				 claimedDate=getRequiredDate(claimDate);
				customerName = customerHeader.getCtNm();
				dkartNetTot2=claimTranHeader.getClaimTranSum().getDkartNetTot();
				removeFromSession(SESSION.READY_TO_CLAIM_TRANHEADER);
			 
			
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
			
	//addedby hanu
/*	public String rejectClaim() {
		try {

			ClaimTranHeader claimTranHeader = (ClaimTranHeader) sessionmap.get("ReadyToClaimTranHeader");
			OrderTransactionsIfc ordTrn = DKartContext.getLookupOrder();
			claimTranHeader.setScOrd(new BigDecimal(6));
			ordTrn.persistClaimTransaction(claimTranHeader);
			sessionmap.put("ReadyToClaimTranHeader", null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}
	public void setSession(Map<String, Object> session)
	{
		this.sessionmap = (SessionMap<String, Object>) session;
		employee = (EmployeeIfc) sessionmap.get("employee");
		
	}

	

	public SessionMap<String, Object> getSessionmap()
	{
		return sessionmap;
	}	*/

	@SuppressWarnings("unused")
	private ClaimDetailTable[] getClaimList() {
			return acceptClaim_List;
		
	}
	private void setClaimList(ClaimDetailTable[] acceptClaimList) {
		this.acceptClaim_List=acceptClaimList;
		
	}

	public DiffClaimsList getAcceptClaim_List1() {
		return acceptClaim_List1;
	}


	public void setAcceptClaim_List1(DiffClaimsList acceptClaim_List1) {
		this.acceptClaim_List1 = acceptClaim_List1;
	}


	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
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


	public List<BigDecimal> getAcceptedPriceList() {
		return acceptedPriceList;
	}


	public void setAcceptedPriceList(List<BigDecimal> acceptedPriceList) {
		this.acceptedPriceList = acceptedPriceList;
	}


	public List<BigDecimal> getNetPriceList() {
		return netPriceList;
	}


	public void setNetPriceList(List<BigDecimal> netPriceList) {
		this.netPriceList = netPriceList;
	}
 public Date getClaimedDate() {
			return claimedDate;
		}

public void setClaimedDate(Date claimedDate) {
			this.claimedDate = claimedDate;
		}


public String getClaimStatus() {
	return claimStatus;
}


public void setClaimStatus(String claimStatus) {
	this.claimStatus = claimStatus;
}


public String getAcceptClaimRange() {
	return acceptClaimRange;
}


public void setAcceptClaimRange(String acceptClaimRange) {
	this.acceptClaimRange = acceptClaimRange;
}


public String getAutoAcceptClaimRange() {
	return autoAcceptClaimRange;
}


public void setAutoAcceptClaimRange(String autoAcceptClaimRange) {
	this.autoAcceptClaimRange = autoAcceptClaimRange;
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


public String getInvoiceCancelFlag() {
	return invoiceCancelFlag;
}


public void setInvoiceCancelFlag(String invoiceCancelFlag) {
	this.invoiceCancelFlag = invoiceCancelFlag;
}


public BigDecimal getDkartTaxTot() {
	return dkartTaxTot;
}


public void setDkartTaxTot(BigDecimal dkartTaxTot) {
	this.dkartTaxTot = dkartTaxTot;
}
public BigDecimal getDkartDscTot() {
	return dkartDscTot;
}
public void setDkartDscTot(BigDecimal dkartDscTot) {
	this.dkartDscTot = dkartDscTot;
}
public List<BigDecimal> getDiscountsList() {
	return discountsList;
}
public void setDiscountsList(List<BigDecimal> discountsList) {
	this.discountsList = discountsList;
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


public String getSite_address() {
	return site_address;
}


public void setSite_address(String site_address) {
	this.site_address = site_address;
}

}

