package rispl.dkart.services.ejb.claim;

import java.util.List;
import javax.ejb.Local;
import rispl.db.model.claim.ClaimTranDscItm;
import rispl.db.model.claim.ClaimTranDscItmPK;
import rispl.db.model.claim.ClaimTranHeader;
import rispl.db.model.claim.ClaimTranHeaderPK;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.db.model.claim.ClaimTranLineItemPK;
import rispl.db.model.claim.ClaimTranLineTax;
import rispl.db.model.claim.ClaimTranSum;
import rispl.db.model.claim.ClaimTranSumPK;

//FIXME: Delete this class
/**
 * @generated DT_ID=none
 */
@Local
public interface ClaimLocal
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

}
