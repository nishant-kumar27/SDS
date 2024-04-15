package rispl.dime.schedular.imports;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public interface DKartConstantsIfc {
	
	final static String STAR = "*";
	
	final static String PERSISTENCE_UNIT_NAME = "DkartNewBusinesslogic";
	
	final static String DIME_PROCESSING = "Processing";
	final static String DIME_PROCESSED = "Processed";
	final static String DIME_PARTIALLY_PROCESSED = "Partially Processed";
	final static String DIME_FAILED = "Failed";

	final static String UPS = "UPS";
	final static String ADD = "ADD";
	final static String DEL = "DEL";
	final static String UPD = "UPD";
	
	final static String DEFAULT_LOCALE = "en";
	final static String EMPTY_VAL = "";
	
	final static String TRUE_IND = "1";
	final static String FALSE_IND = "0";
	
	final static String FULL_INCRMNTL = "FullIncremental";
	final static String KILL_AND_FILL = "KillAndFill";
	
	final static String LINE_ITEM="LineItem";
	final static String TRANSACTION = "Transaction";
	
	final static String LINE_ITEM_VAL="1";
	final static String TRANSACTION_VAL = "2";
	
	final static String PRCNT_OR_AMT="PercentageOrAmount";
	final static String PRCNT_OR_AMT_VAL="1";
	
	final static String DRV_FRM_TXBL="DeriveFromTaxTable";
	final static String DRV_FRM_TXBL_VAL="2";
	
	final static String USE_THSLD_AMT = "UseThresholdAmount";
	final static String USE_THSLD_AMT_VAL="3";
	
	final static String PERCENTAGE = "Percentage";
	final static String AMOUNT = "Amount";
	
	final static BigDecimal PERCENTAGE_VALUE = new BigDecimal("1");
	final static BigDecimal AMOUNT_VALUE = new BigDecimal("2");


//Discount Rules - Start
	
	final static String BUY_NOF_XGET_YAT_Z_OFF = "BuyNofXgetYatZ%off";
    
	final static String BUY_NOF_XGET_YAT_Z_$_OFF = "BuyNofXgetYatZ$off";
    
	final static String BUY_NOF_XGET_YAT_Z_$ = "BuyNofXgetYatZ$";
    
	final static String BUY_NOF_XGET_HIGHEST_PRICED_XAT_Z_OFF = "BuyNofXgetHighestPricedXatZ%off";
    
	final static String BUY_NOF_XGET_LOWEST_PRICED_XAT_Z_OFF = "BuyNofXgetLowestPricedXatZ%off";
    
	final static String BUY_$_NOR_MORE_OF_XGET_YAT_Z_$_OFF = "Buy$NorMoreOfXgetYatZ$off";
   
	final static String BUY_$_NOR_MORE_OF_XGET_YAT_Z_OFF = "Buy$NorMoreOfXgetYatZ%off";
   
	final static String BUY_$_NOR_MORE_OF_XGET_YAT_Z_$ = "Buy$NorMoreOfXgetYatZ$";
    
	final static String BUY_NOF_XFOR_Z_$ = "BuyNofXforZ$";
    
	final static String BUY_NOF_XFOR_Z_OFF = "BuyNofXforZ%off";
    
	final static String BUY_NOF_XFOR_Z_$_OFF = "BuyNofXforZ$off";
   
	final static String BUY_NOR_MORE_OF_XFOR_Z_OFF = "BuyNorMoreOfXforZ%off";
 
	final static String BUY_NOR_MORE_OF_XFOR_Z_$_EACH = "BuyNorMoreOfXforZ$Each";
   
	final static String BUY_$_NOF_XFOR_Z_$_OFF = "Buy$NofXforZ$off";
    
	final static String BUY_$_NOF_XFOR_Z_OFF = "Buy$NofXforZ%off";
	
	final static String PROM_SRC_TRGT_TYPS_ITM = "Item";
	final static String PROM_SRC_TRGT_TYPS_CLS = "Class";
	final static String PROM_SRC_TRGT_TYPS_DPT = "Department";
	
	final static String DEAL_DIST_TRGT = "0";
	final static String DEAL_DIST_SRCTRGT="1";
	
	final static BigDecimal DIS_PROM_MNUL=BigDecimal.ZERO;
	final static BigDecimal DIS_PROM_AUTO=BigDecimal.ONE;
	final static BigDecimal DIS_SIMP_PROM_AUTO=new BigDecimal(2);
//Discount Rules - END
	
	//Dime Utility - START
	final static String TAX_IMPORT = "TaxImport";
	final static String MRCH_HIER= "MerchandiseHier_";
	final static String STORE_ORG_HIER = "StoreOrgHier_";
	final static String ITEM_FILE = "ItemExport_";
	final static String CURR_IMP_FILE = "CurrencyImport";
	final static String CUST_PRCGPS = "CustomerExtractCustSeg";
	final static String EMPLOYEE_FILE = "EmployeeImport";
	final static String ITM_CLSS_MOD = "ItemClassificationMod";
	final static String PROMO_FILE = "PricingExtract_";
	
	final static String EMPLOYEE_PWD_SALT = "f068d7fc-1e92-478e-aee1-aeca981ccb1c";
	
	final static SimpleDateFormat SMPL_DTE_FRMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	final static String CUST_SEG_A = "A";
	final static String CUST_SEG_B = "B";
	final static String CUST_SEG_C = "C";
	
	final static String PROM_EXIST_CHK_QUERY = "SELECT UNIQUE(ID_STR_RT) FROM RISPL_DK_PRDVN_RULE WHERE ID_STR_RT=? AND DC_RU_PRDV_EF<=? AND DC_RU_PRDV_EP>=?";
}
