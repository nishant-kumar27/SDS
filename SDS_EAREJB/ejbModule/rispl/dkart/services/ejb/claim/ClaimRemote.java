package rispl.dkart.services.ejb.claim;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.rispl.dk.claim.model.DiffClaimsList;

import rispl.db.model.claim.ClaimDetail;
import rispl.db.model.claim.ClaimTranDscItm;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.ClaimTranLineTax;
import rispl.db.model.claim.ClaimTranSum;
import rispl.dk.Employee.EmployeeIfc;


/**
 * @generated DT_ID=none
 */
@Remote
public interface ClaimRemote
{

	/**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public ClaimTranLineTax persistClaimTranLineTax(ClaimTranLineTax claimTranLineTax);

    /**
     * @generated DT_ID=none
     */
    public ClaimTranLineTax mergeClaimTranLineTax(ClaimTranLineTax claimTranLineTax);

    /**
     * @generated DT_ID=none
     */
    public void removeClaimTranLineTax(ClaimTranLineTax claimTranLineTax);

    /**
     * @generated DT_ID=none
     */
    public List<ClaimTranLineTax> getClaimTranLineTaxFindAll();

    /**
     * @generated DT_ID=none
     */
    public ClaimTranSum persistClaimTranSum(ClaimTranSum claimTranSum);

    /**
     * @generated DT_ID=none
     */
    public ClaimTranSum mergeClaimTranSum(ClaimTranSum claimTranSum);

    /**
     * @generated DT_ID=none
     */
    public void removeClaimTranSum(ClaimTranSum claimTranSum);

    /**
     * @generated DT_ID=none
     */
    public List<ClaimTranSum> getClaimTranSumFindAll();

    /**
     * @generated DT_ID=none
     */
    public ClaimTranDscItm persistClaimTranDscItm(ClaimTranDscItm claimTranDscItm);

    /**
     * @generated DT_ID=none
     */
    public ClaimTranDscItm mergeClaimTranDscItm(ClaimTranDscItm claimTranDscItm);

    /**
     * @generated DT_ID=none
     */
    public void removeClaimTranDscItm(ClaimTranDscItm claimTranDscItm);

    /**
     * @generated DT_ID=none
     */
    public List<ClaimTranDscItm> getClaimTranDscItmFindAll();

    /**
     * @generated DT_ID=none
     */
    public ClaimTranLineItem persistClaimTranLineItem(ClaimTranLineItem claimTranLineItem);

    /**
     * @generated DT_ID=none
     */
    public ClaimTranLineItem mergeClaimTranLineItem(ClaimTranLineItem claimTranLineItem);

    /**
     * @generated DT_ID=none
     */
    public void removeClaimTranLineItem(ClaimTranLineItem claimTranLineItem);

    /**
     * @generated DT_ID=none
     */
    public List<ClaimTranLineItem> getClaimTranLineItemFindAll();

    /**
     * @generated DT_ID=none
     */
    public ClaimTranHeader persistClaimTranHeader(ClaimTranHeader claimTranHeader);

    /**
     * @generated DT_ID=none
     */
    public ClaimTranHeader mergeClaimTranHeader(ClaimTranHeader claimTranHeader);

    /**
     * @generated DT_ID=none
     */
    public void removeClaimTranHeader(ClaimTranHeader claimTranHeader);

    /**
     * @generated DT_ID=none
     */
    public List<ClaimTranHeader> getClaimTranHeaderFindAll();

	public ClaimTranHeader saveClaimTranHeader(ClaimTranHeader claimHeader);

	public List<ClaimTranSum> findClaimWithInvoiceID(String invoiceID);
	
	public void addClaimRef(String claimId,String claimRefNo,String claimRefdate);
	public String calculatPerbyCustId(String custId);
	
	public List<ClaimTranSum> findClaimWithOrderID(String orderID);
	
	/*public List<ClaimTranHeader> getClaimsToApprove();*/
	
	public List<ClaimDetail> getClaimsToApprove(EmployeeIfc emp);
	
	public String getSalesAgentEmailId(String orderId);
	public List<String> getDepartmentHeadEmail(String custId);

	ClaimTranHeader getUpdatedReceivedQty(ClaimTranHeader claimTranHeader);

	public DiffClaimsList getAcceptClaimDetails(EmployeeIfc employee);

	public String getDataEntryOprtrEmailId(String orderId);
	
	//to get the updatedTotals
	ClaimTranHeader getUpdatedTotals(ClaimTranHeader claimTranHeader);
	
	//to SPLIT LineItems, If there is a decimal mis-match like register claim 
	public List<ClaimTranLineItem> splitLines(ClaimTranLineItem claimTranLineItem,BigDecimal qty);
	
}
