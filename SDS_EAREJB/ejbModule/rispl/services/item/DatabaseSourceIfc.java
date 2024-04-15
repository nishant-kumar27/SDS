package rispl.services.item;

public interface DatabaseSourceIfc
{
    public static final String lookUpItemById = "SELECT * from RISPL_DK_ITEM_MSTR itm where itm.ITM_ID=? and itm.RT_STR_ID=?";
    public static final String findItemPrice = "SELECT * FROM RISPL_DK_ITEM_PRICE itemPrice WHERE ITM_ID = ? AND RT_STR_ID = ? AND EV_EF_TMP <= SYSDATE ORDER BY EV_EF_TMP DESC";
    public static final String lookupItemSize = "select * from RISPL_DK_ITEM_SIZE itemSize where itemSize.ITM_SZ_CD=?";
    public static final String lookupItemColor = "select * from RISPL_DK_ITEM_COLOR itemColor where itemColor.ITM_CLR_CD=?";
    public static final String lookupItemMessages = "select * from RISPL_DK_ITEM_MSGS itemMsg where itemMsg.DISP_MSG_ID=?";
    public static final String lookupItemSimplePromotions = "SELECT * FROM RISPL_DK_ITEM_SIMP_PROM itemPrice WHERE itemPrice.PROM_OVRD_PRC IN   (SELECT MIN(PROM_OVRD_PRC)   FROM RISPL_DK_ITEM_SIMP_PROM   WHERE PROM_EFCT_DT_TM <=? AND PROM_EXP_DT_TM >= ?  AND ITM_ID       =?   AND RT_STR_ID    =?   )";
    public static final String lookUpCustomerById = "select * from RISPL_DK_CUST_HDR cust where CUST_ID=?";
    public static final String lookUpCustomerByName = "select * from RISPL_DK_CUST_HDR cust where CT_NM like '%?%'";
    public static final String lookUpCustomerByPhone = "select * from RISPL_DK_CUST_SITE_PHN cust where CC_CNCT like '%?%'";
    public static final String lookUpCustomerByPartyId = "select * from RISPL_DK_CUST_HDR cust where CUST_PRTY_ID=?";
    public static final String lookUpCustomerPaymentTerms = "select * from RISPL_DK_CUST_PYMT_TRMS payments where ID_PRTY=?";
    public static final String lookUpCustomerSite = "select * from RISPL_DK_CUST_SITE site where CUST_PRTY_ID=?";
    public static final String lookUpCustomerSiteAdd = "select * from RISPL_DK_CUST_SITE_ADD siteAdd where CUST_PRTY_ID=?";
    public static final String lookUpCustomerSiteInvoice = "select * from RISPL_DK_CUST_SITE_INV invocie where CUST_ID=?";
    public static final String lookUpCustomerSiteLimits = "select * from RISPL_DK_CUST_SITE_LMT limit where CUST_PRTY_ID=?";
    public static final String lookUpCustomerPhone = "select * from RISPL_DK_CUST_SITE_PHN phone where CUST_PRTY_ID=?";
    public static final String lookUpCustomerOrders = "select DISTINCT(sum.ID_ORD),sum.DKART_NET_TOT,sum.DC_DY_ORD,header.SC_ORD from ORD_TRAN_SUM sum,ORD_TRAN_HEADER header where sum.ORD_ID_CT=? and header.ORD_TY=23   AND sum.RT_STR_ID = HEADER.RT_STR_ID AND sum.ORD_WS    = HEADER.ORD_WS AND sum.DC_DY_ORD =HEADER.DC_DY_ORD AND  sum.trn_seq = HEADER.trn_seq";
    public static final String updateTranHeader = "UPDATE ORD_TRAN_HEADER SET ID_TLOG_BTCH=? WHERE (TRN_SEQ||DC_DY_ORD||ORD_WS||RT_STR_ID) in (SELECT (T1.TRN_SEQ||T1.DC_DY_ORD||T1.ORD_WS||T1.RT_STR_ID)   FROM ORD_TRAN_SUM T2,   ORD_TRAN_HEADER T1   WHERE T1.RT_STR_ID = T2.RT_STR_ID   AND T1.ORD_WS      = T2.ORD_WS   AND T1.TRN_SEQ     = T2.TRN_SEQ   AND T1.DC_DY_ORD   = T2.DC_DY_ORD   AND T2.ID_ORD in (?)  )";
    public static final String autoUpdateOrders = "SELECT ID_ORD,ITEM_ID,SHP_QTY from ORD_INV_SHP_QTY_DK where RTLOGBATCH='-1'";
    public static final String alreadyDuplicatedOrder = "SELECT ITEM_ID,SHP_QTY from ORD_INV_SHP_QTY_DK where id_ord=? and RTLOGBATCH<>-1";
}