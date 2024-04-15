package rispl.dime.schedular.imports;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.item.pricing.RisplDkItemPrice;
import rispl.db.model.item.pricing.RisplDkItemPricePK;
import rispl.db.model.item.pricing.RisplDkItemSimpProm;
import rispl.db.model.item.pricing.RisplDkItemSimpPromPK;
import rispl.db.model.item.pricing.RisplDkPrdvnDptElg;
import rispl.db.model.item.pricing.RisplDkPrdvnItmNelg;
import rispl.db.model.item.pricing.RisplDkPrdvnMmitm;
import rispl.db.model.item.pricing.RisplDkPrdvnMrchElg;
import rispl.db.model.item.pricing.RisplDkPrdvnRule;
import rispl.db.model.item.pricing.RisplDkPrdvnRuleDisc;
import rispl.db.model.item.pricing.RisplDkPrdvnRuleElg;
import rispl.db.model.item.pricing.RisplDkPrdvnRulePK;
import rispl.db.model.item.pricing.RisplDkPrdvnTrshldElg;
import rispl.jaxb.currency.ChangeTypeType;
import rispl.jaxb.item.pricing.CancelItemType;
import rispl.jaxb.item.pricing.CancelItemsType;
import rispl.jaxb.item.pricing.CurrencyAmountType;
import rispl.jaxb.item.pricing.DiscountRuleType;
import rispl.jaxb.item.pricing.ItemAndPriceType;
import rispl.jaxb.item.pricing.LocalizedNameType;
import rispl.jaxb.item.pricing.PriceChangeType;
import rispl.jaxb.item.pricing.PricePromotionType;
import rispl.jaxb.item.pricing.PricingImport;
import rispl.jaxb.item.pricing.PricingRuleType;
import rispl.jaxb.item.pricing.SourcesType;
import rispl.jaxb.item.pricing.SourcesType.Source;
import rispl.jaxb.item.pricing.TargetsType;
import rispl.jaxb.item.pricing.TargetsType.Target;
import rispl.jaxb.item.pricing.ThresholdType;
import rispl.jaxb.item.pricing.ThresholdsType;
import utility.ConfigUtils;

public class RisplPricingImport {

	private static final Logger LOGGER = LogManager.getLogger(RisplPricingImport.class);
    private static SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
    //Method to process Price Change data START
    @SuppressWarnings("finally")
	public static boolean processPriceChangeData(PricingImport pricingImport,EntityManager em,String storeId){
    	boolean status = false;
    	try{
    		LOGGER.info("Executing processPriceChangeData method......");
        List<PriceChangeType> priceChangeList = pricingImport.getPriceChange();
        
        for(PriceChangeType pcType : priceChangeList){
        	
        	rispl.jaxb.item.pricing.ChangeTypeType chngType = pcType.getChangeType();
        	List<ItemAndPriceType> itemPriceTypeList = pcType.getItem();
        	
        	RisplDkItemPrice itemPrice = new RisplDkItemPrice();
        	for(ItemAndPriceType itemPriceType : itemPriceTypeList){
        		
        		itemPrice.setItmId(itemPriceType.getID());
        		
        		List<CurrencyAmountType> currAmtTyp = itemPriceType.getPrice();
        		if(currAmtTyp.size()>0){
        			itemPrice.setSlUnAmt(ConfigUtils.getInstance().createBigDecimal(currAmtTyp.get(0).getValue(), "format.currency"));
        			itemPrice.setMoOvrdPrc(ConfigUtils.getInstance().createBigDecimal(currAmtTyp.get(0).getValue(), "format.currency"));
        		}
        		XMLGregorianCalendar xmlCalendar = null;
        		if(itemPriceType.getStartDateTime()!=null){
        			xmlCalendar = itemPriceType.getStartDateTime();
        			xmlCalendar.setTime(12, 0, 0);
        			itemPrice.setEvEfTmp(convertXmlDateToTimestamp(xmlCalendar.toString()));
        		}
        		
        		if(itemPrice.getEvEfTmp()==null && pcType.getStartDate()!=null){
        			xmlCalendar = pcType.getStartDate();
        			xmlCalendar.setTime(12, 0, 0);
        			itemPrice.setEvEfTmp(convertXmlDateToTimestamp(xmlCalendar.toString()));
        		}
        		
        		itemPrice.setEvntTyp("PC");//check curr and start time to decide IPC or FPC
	        	itemPrice.setEvPri(new BigDecimal(1));//No method available in generated Jaxb class
	        	
	        	//List of Stores
	        	List<String> stores = pcType.getStoreID();
	        	if(stores.size()>0){
	        		for(String store : stores){
		        		RisplDkItemPricePK dkItemPricePK = new RisplDkItemPricePK();
		        		dkItemPricePK.setEvntId(pcType.getID());
		        		dkItemPricePK.setRtStrId(store);
		        		itemPrice.setId(dkItemPricePK);
		        		if(pcType.getChangeType()!=null && pcType.getChangeType().value().equalsIgnoreCase(ChangeTypeType.DEL.value())){
		        			itemPrice=em.find(RisplDkItemPrice.class, itemPrice.getId());
		        			if(itemPrice!=null){
		        				em.remove(itemPrice);
		        			}	
		            	}else{
		            		em.merge(itemPrice);
		            	}
		        	}
	        	}else{
	        		RisplDkItemPricePK dkItemPricePK = new RisplDkItemPricePK();
	        		dkItemPricePK.setEvntId(pcType.getID());
	        		dkItemPricePK.setRtStrId(storeId);
	        		itemPrice.setId(dkItemPricePK);
	        		if(pcType.getChangeType()!=null && pcType.getChangeType().value().equalsIgnoreCase(ChangeTypeType.DEL.value())){
	        			itemPrice=em.find(RisplDkItemPrice.class, itemPrice.getId());
	        			if(itemPrice!=null){
	        				em.remove(itemPrice);
	        			}
	            	}else{
	            		em.merge(itemPrice);
	            	}
	        	}
        	}
        	
        }
        status = true;
        LOGGER.info("processPriceChangeData method Executed Successfully ......");
    }catch(Exception e){
    	LOGGER.error(e);
    }finally{
    	return status;
    }
 }
 //Method to process Price Change data END
    
    public static void readPersistPricingXml(InputStream inputStream,EntityManager em,String storeId) throws Exception{
    	

		try {  
			LOGGER.info("Processing Pricing XML File..............");
	        em.getTransaction().begin();
	          
	        JAXBContext jaxbContext = JAXBContext.newInstance(PricingImport.class);  
	   
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
	        PricingImport pricingImport = (PricingImport) jaxbUnmarshaller.unmarshal(inputStream);
	        
	        //KillAndFill
	        if(pricingImport.getFillType()!=null && pricingImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)){
	        	em.createQuery("DELETE FROM RisplDkItemSimpProm e").executeUpdate();
	        	em.createQuery("DELETE FROM RisplDkPrdvnDptElg e").executeUpdate();
	        	em.createQuery("DELETE FROM RisplDkPrdvnMrchElg e").executeUpdate();
	        	em.createQuery("DELETE FROM RisplDkPrdvnRuleElg e").executeUpdate();
	        	em.createQuery("DELETE FROM RisplDkPrdvnRuleDisc e").executeUpdate();
	        	em.createQuery("DELETE FROM RisplDkPrdvnMmitm e").executeUpdate();
	        	em.createQuery("DELETE FROM RisplDkPrdvnTrshldElg e").executeUpdate();
	        	em.createQuery("DELETE FROM RisplDkPrdvnItmNelg e").executeUpdate();
	        	em.createQuery("DELETE FROM RisplDkPrdvnRule e").executeUpdate();
	        	em.getTransaction().commit();
	        	em.getTransaction().begin();
	        }
	        
	        //Calling method to process Price Change data
	        processPriceChangeData(pricingImport,em,storeId);
	     
	        
	    //Simple Promotions Start
	        List<PricePromotionType> pricePromotions = pricingImport.getPricePromotion();
	        for(PricePromotionType pricePromotion : pricePromotions){
	        	RisplDkItemSimpProm simpProm = new RisplDkItemSimpProm();
	        	if(pricePromotion.getPricingGroupID()!=null){
	        		simpProm.setCustPrcgpId(new BigDecimal(pricePromotion.getPricingGroupID()));
	        	}
	        	
	        	if(pricePromotion.getPromoCompDetlID()!=null){
	        		simpProm.setPromCmpDtlId(new BigDecimal(pricePromotion.getPromoCompDetlID()));
	        	}
	        	if(pricePromotion.getPromoCompID()!=null){
	        		simpProm.setPromCmpId(new BigDecimal(pricePromotion.getPromoCompID()));
	        	}
	        	if(pricePromotion.getStartDateTime()!=null){
	        		simpProm.setPromEfctDtTm(new Timestamp(pricePromotion.getStartDateTime().toGregorianCalendar().getTimeInMillis()));
	        	}
	        	
	        	if(pricePromotion.getEndDateTime()!=null){
	        		simpProm.setPromExpDtTm(new Timestamp(pricePromotion.getEndDateTime().toGregorianCalendar().getTimeInMillis()));
	        	}
	        		        	
	        	simpProm.setPromId(new BigDecimal(pricePromotion.getID()));
	        	
	        	simpProm.setPromPri(new BigDecimal(pricePromotion.getPriority()));
	        	
	        	simpProm.setPromTypCd(pricePromotion.getType().value());
	        	
	        	if(pricePromotion.getType().value().equalsIgnoreCase("AmountOff")){
	        		simpProm.setPromAmt(ConfigUtils.getInstance().createBigDecimal(pricePromotion.getDiscountAmount().getValue(), "format.currency"));
	        	}else if(pricePromotion.getType().value().equalsIgnoreCase("PercentOff")){
	        		simpProm.setPromAmt(ConfigUtils.getInstance().createBigDecimal(pricePromotion.getDiscountPercent(), "format.currency"));
	        	}
	        	
	        	if(pricePromotion.getDescription()!=null && pricePromotion.getDescription().getValue()!=null && !pricePromotion.getDescription().getValue().equals("")){
	        		simpProm.setEvDesc(pricePromotion.getDescription().getValue());
	        	}else{
	        		LocalizedNameType nameType = pricePromotion.getName();
	        		simpProm.setEvDesc(nameType.getValue());
	        	}
	        	
	        	List<String> promStores = pricePromotion.getStoreID();
	        	if(promStores.size()>0){
	        		for(String promStore : promStores){
	        			RisplDkItemSimpPromPK simpPromPK = new RisplDkItemSimpPromPK();
			        	simpPromPK.setIdEv(pricePromotion.getID());
		        		simpPromPK.setRtStrId(promStore);
		        		
		        		List<ItemAndPriceType> itemPrices = pricePromotion.getItem();
		        		for(ItemAndPriceType itemPrice : itemPrices){
		        			simpPromPK.setItmId(itemPrice.getID());
		        			
		        			List<CurrencyAmountType> currAmtTyps = itemPrice.getPrice();
		        			for(CurrencyAmountType currAmtTyp : currAmtTyps){
		        				simpProm.setPromOvrdPrc(ConfigUtils.getInstance().createBigDecimal(currAmtTyp.getValue(), "format.currency"));
		        			}
		        			
		        			//Item Level Start Date
		        			if(itemPrice.getStartDateTime()!=null){
		        				simpProm.setPromEfctDtTm(new Timestamp(itemPrice.getStartDateTime().toGregorianCalendar().getTimeInMillis()));
		        			}
		        		}
		        		simpProm.setId(simpPromPK);
		        		if(pricePromotion.getChangeType()!=null && pricePromotion.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)){
		        			simpProm=em.find(RisplDkItemSimpProm.class, simpProm.getId());
		        			if(simpProm!=null){
		        				em.remove(simpProm);
		        			}
		        		}else{
		        			em.merge(simpProm);
		        		}
		        	}
	        	}else{
	        		
	        		RisplDkItemSimpPromPK simpPromPK = new RisplDkItemSimpPromPK();
		        	simpPromPK.setIdEv(pricePromotion.getID());
		        	
	        		simpPromPK.setRtStrId(storeId);
	        		
	        		List<ItemAndPriceType> itemPrices = pricePromotion.getItem();
	        		for(ItemAndPriceType itemPrice : itemPrices){
	        			simpPromPK.setItmId(itemPrice.getID());
	        			
	        			List<CurrencyAmountType> currAmtTyps = itemPrice.getPrice();
	        			for(CurrencyAmountType currAmtTyp : currAmtTyps){
	        				simpProm.setPromOvrdPrc(ConfigUtils.getInstance().createBigDecimal(currAmtTyp.getValue(), "format.currency"));
	        			}
	        			
	        			//Item Level Start Date
	        			if(itemPrice.getStartDateTime()!=null){
	        				simpProm.setPromEfctDtTm(new Timestamp(itemPrice.getStartDateTime().toGregorianCalendar().getTimeInMillis()));
	        			}
	        		}
	        		simpProm.setId(simpPromPK);
	        		if(pricePromotion.getChangeType()!=null && pricePromotion.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)){
	        			simpProm=em.find(RisplDkItemSimpProm.class, simpProm.getId());
	        			if(simpProm!=null){
	        				em.remove(simpProm);
	        			}
	        		}else{
	        			em.merge(simpProm);
	        		}
	        	}
	        }
	    //Simple Promotions End
	        
	    //Discount Rule Start
	        Timestamp timestamp = new Timestamp(new java.sql.Date(System.currentTimeMillis()).getTime());
	        List<DiscountRuleType> discountRuleTypes = pricingImport.getDiscountRule();
	        for(DiscountRuleType discountRuleType : discountRuleTypes){
	        	
	        	PricingRuleType pricingRuleType = discountRuleType.getPricingRule();
	        	
	        	SourcesType sourcesTypes = discountRuleType.getSources();
	        	TargetsType targetsTypes = discountRuleType.getTargets();
	        	
	   //Insert to RISPL_DK_PRDVN_RULE Table - START
	        	List<String> stores = pricingRuleType.getStoreID();
	        	for(String store : stores){      	
	        	RisplDkPrdvnRule discountRule = new RisplDkPrdvnRule();
	        	
	        	discountRule.setDcRuPrdvEf(convertXmlDateToTimestamp(pricingRuleType.getStartDateTime().toString()));
	        	discountRule.setDcRuPrdvEp(convertXmlDateToTimestamp(pricingRuleType.getEndDateTime().toString()));
	        	discountRule.setDeRuPrdv(pricingRuleType.getType().value());
	        	discountRule.setNmRuPrdv(pricingRuleType.getName().getValue());
	        	if(sourcesTypes!=null && sourcesTypes.getType()!=null){
	        		discountRule.setCdBasPrdv(new BigDecimal(sourcesTypes.getType().ordinal()));
	        	}
	        	if(pricingRuleType.getScope()!=null){
	        		if(pricingRuleType.getScope().value().equalsIgnoreCase("TRANSACTION")){
	        			discountRule.setCdScpPrdv(new BigDecimal(0));
	        		}else if(pricingRuleType.getScope().value().equalsIgnoreCase("ITEM")){
	        			discountRule.setCdScpPrdv(new BigDecimal(1));
	        		}else if(pricingRuleType.getScope().value().equalsIgnoreCase("GROUP")){
	        			discountRule.setCdScpPrdv(new BigDecimal(2));
	        		}
	        	}
	        	discountRule.setDpLdgStkMdfr(pricingRuleType.getAccountingMethod().value());
	        	discountRule.setQuLmAply(new BigDecimal(pricingRuleType.getNbrTimesPerTrans()));
	        	
	        	List<CurrencyAmountType> srcThrshldLmt = pricingRuleType.getSourceThreshold();
	        	if(srcThrshldLmt.size()>0){
	        		discountRule.setMoThSrc(srcThrshldLmt.get(0).getValue());
	        	}
	        	
	        	List<CurrencyAmountType> srcLmt = pricingRuleType.getSourceLimit();
	        	if(srcLmt.size()>0){
	        		discountRule.setMoLmSrc(srcLmt.get(0).getValue());
	        	}
	        	
	        	List<CurrencyAmountType> trgtThrshldLmt = pricingRuleType.getSourceThreshold();
	        	if(srcThrshldLmt.size()>0){
	        		discountRule.setMoThTgt(trgtThrshldLmt.get(0).getValue());
	        	}
	        	
	        	List<CurrencyAmountType> trgtLmt = pricingRuleType.getSourceLimit();
	        	if(srcLmt.size()>0){
	        		discountRule.setMoLmTgt(trgtLmt.get(0).getValue());
	        	}
	        	
	        	discountRule.setFlDlDst(pricingRuleType.getDealDistribution().ordinal()+"");
	        	if(pricingRuleType.isAllowSourceToRepeat()){
	        		discountRule.setFlAlwRptSrc("1");
	        	}else{
	        		discountRule.setFlAlwRptSrc("0");
	        	}
	        	
	        	//quAnSrc
	        	if(sourcesTypes!=null && sourcesTypes.getQualifier().ordinal()==1){
	        		discountRule.setQuAnSrc(new BigDecimal(sourcesTypes.getSource().size()));
	        	}else if(sourcesTypes!=null){
	        		discountRule.setQuAnSrc(new BigDecimal(sourcesTypes.getQty()));
	        	}
	        	
	        	if(targetsTypes!=null && targetsTypes.getQualifier().ordinal()==1){
	        		discountRule.setQuAnTgt(new BigDecimal(targetsTypes.getTarget().size()));
	        	}else if(targetsTypes!=null){
	        		discountRule.setQuAnTgt(new BigDecimal(targetsTypes.getQty()));
	        	}
	        	
	        	
	        	if(sourcesTypes!=null){
	        		discountRule.setCdBasCmpSrc(sourcesTypes.getType().value());
	        	}
	        	if(targetsTypes!=null){
	        		discountRule.setCdBasCmpTgt(targetsTypes.getType().value());
	        	}
	        	
	        	discountRule.setIdPrm(new BigDecimal(pricingRuleType.getID()));
	        	
	        	discountRule.setItmPrcCtgySrc(pricingRuleType.getSourceItemPriceCategory().value());
	        	discountRule.setItmPrcCtgyTgt(pricingRuleType.getTargetItemPriceCategory().value());
	        	
	        	if(pricingRuleType.getPricingGroupID()!=null){
	        		discountRule.setIdPrcgp(new BigDecimal(pricingRuleType.getPricingGroupID()));
	        	}
	        	
	        	discountRule.setTsCrtRcrd(timestamp);
	        	discountRule.setTsMdfRcrd(timestamp);
	        	
	        	RisplDkPrdvnRulePK discountRulepk = new RisplDkPrdvnRulePK();
	        	discountRulepk.setIdRuPrdv(pricingRuleType.getID());
	        	discountRulepk.setIdPrmCmp(pricingRuleType.getPromoCompID());
        		discountRulepk.setIdStrRt(store);
        		discountRulepk.setIdPrmCmpDtl(pricingRuleType.getPromoCompDetlID());
        		        		
        		discountRule.setId(discountRulepk);
        		
        		boolean drRmvd = false;
        		
        		/*if(pricingRuleType.getChangeType()!=null && pricingRuleType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)){
        			discountRule=em.find(RisplDkPrdvnRule.class, discountRule.getId());
        			if(discountRule!=null){
        				//em.remove(discountRule);
        				deleteRule(em, discountRule);
        				drRmvd = true;
        			}
        		}else{
        			em.merge(discountRule);
        		}*/
		     
        		em.merge(discountRule);
        		
        		if(!drRmvd){
		        		List<Source> sourceItems = new ArrayList<Source>();
		        				if(sourcesTypes!=null){
		        					sourceItems = sourcesTypes.getSource();
		        				}
		        		for(Source srcItms : sourceItems){
			        	
		        			if(sourcesTypes.getType().value().equalsIgnoreCase("Department")){
		        				//Insert to RISPL_DK_PRDVN_DPT_ELG table - START
		    	        			RisplDkPrdvnDptElg risplDkPrdvnDptElg = new RisplDkPrdvnDptElg();
		    	        			if(srcItms.getSourceAmount()!=null){
		    	        				if(srcItms.getSourceAmount().size()>0){
		    	        					risplDkPrdvnDptElg.setMoTh(srcItms.getSourceAmount().get(0).getValue());
		    	        				}
		        					
		    	        				risplDkPrdvnDptElg.setQuTh(new BigDecimal(srcItms.getQty()));
		    	        				risplDkPrdvnDptElg.setTsRuMrstEf(convertXmlDateToTimestamp(pricingRuleType.getStartDateTime().toString()));
		    	        				risplDkPrdvnDptElg.setTsRuMrstEp(convertXmlDateToTimestamp(pricingRuleType.getEndDateTime().toString()));
		        					
		    	        				risplDkPrdvnDptElg.setTsCrtRcrd(timestamp);
		    	        				risplDkPrdvnDptElg.setTsMdfRcrd(timestamp);
		    	        				risplDkPrdvnDptElg.setRisplDkPrdvnRule(discountRule);
		        				}
		    	        				em.merge(risplDkPrdvnDptElg);
		        				//Insert to RISPL_DK_PRDVN_DPT_ELG table - END
		        			}else if(sourcesTypes!=null && srcItms.getSourceAmount()!=null && sourcesTypes.getType().value().equalsIgnoreCase("Class")){
		        				//Insert to RISPL_DK_PRDVN_MRCH_ELG table - START
		        				RisplDkPrdvnMrchElg risplDkPrdvnMrchElg = new RisplDkPrdvnMrchElg();
		        				risplDkPrdvnMrchElg.setIdStrcMrCd(srcItms.getID());
		        				if(srcItms.getSourceAmount().size()>0){
		        					risplDkPrdvnMrchElg.setMoTh(srcItms.getSourceAmount().get(0).getValue());
		        				}
	    					
		        				if(srcItms.getQty()!=null){
		        					risplDkPrdvnMrchElg.setQuTh(new BigDecimal(srcItms.getQty()));
		        				}
		        				risplDkPrdvnMrchElg.setTsRuMrstEf(convertXmlDateToTimestamp(pricingRuleType.getStartDateTime().toString()));
		        				risplDkPrdvnMrchElg.setTsRuMrstEp(convertXmlDateToTimestamp(pricingRuleType.getEndDateTime().toString()));
	    					
		        				risplDkPrdvnMrchElg.setTsCrtRcrd(timestamp);
		        				risplDkPrdvnMrchElg.setTsMdfRcrd(timestamp);
		        				risplDkPrdvnMrchElg.setRisplDkPrdvnRule(discountRule);
		        				em.merge(risplDkPrdvnMrchElg);
		        				//Insert to RISPL_DK_PRDVN_MRCH_ELG table - END	
		        			}//else{// if sources type ITEM
		        				//Insert to RISPL_DK_PRDVN_RULE_ELG table - START
			        			RisplDkPrdvnRuleElg risplDkPrdvnRuleElg = new RisplDkPrdvnRuleElg();
			        			risplDkPrdvnRuleElg.setItmId(srcItms.getID());
			        			List<CurrencyAmountType> amtType = srcItms.getSourceAmount();
			        			if(amtType!=null && amtType.size()>0){
			        				risplDkPrdvnRuleElg.setMoTh(amtType.get(0).getValue());
			        			}
			        			
			        			if(srcItms.getQty()!=null){
			        				risplDkPrdvnRuleElg.setQuTh(new BigDecimal(srcItms.getQty()));
			        			}
			        			
			        			risplDkPrdvnRuleElg.setTsRuDrvnEf(convertXmlDateToTimestamp(pricingRuleType.getStartDateTime().toString()));
			        			risplDkPrdvnRuleElg.setTsRuDrvnEp(convertXmlDateToTimestamp(pricingRuleType.getEndDateTime().toString()));
			        			risplDkPrdvnRuleElg.setTsCrtRcrd(timestamp);
			        			risplDkPrdvnRuleElg.setTsMdfRcrd(timestamp);
				        		risplDkPrdvnRuleElg.setRisplDkPrdvnRule(discountRule);
				        		em.merge(risplDkPrdvnRuleElg);
				        		//Insert to RISPL_DK_PRDVN_RULE_ELG table - END	
		        			//}
	    					
		        		}
		        		
		        		//Insert to RISPL_DK_PRDVN_RULE_DISC Table - START
		        			RisplDkPrdvnRuleDisc risplDkPrdvnRuleDisc = new RisplDkPrdvnRuleDisc();
			        			if(targetsTypes!=null && targetsTypes.getDiscountAmount()!=null){
			        				risplDkPrdvnRuleDisc.setMoUnItmPrdvSls(targetsTypes.getDiscountAmount().getValue());
			        			}else if(targetsTypes!=null && targetsTypes.getDiscountPercent()!=null){
			        				risplDkPrdvnRuleDisc.setPntPrcUnItmPrdvSls(targetsTypes.getDiscountPercent());
			        			}else if(targetsTypes!=null && targetsTypes.getNewPrice()!=null){
			        				risplDkPrdvnRuleDisc.setPeUnItmPrdvSls(targetsTypes.getNewPrice().getValue());
			        			}
			        			risplDkPrdvnRuleDisc.setTsCrtRcrd(timestamp);
			        			risplDkPrdvnRuleDisc.setTsMdfRcrd(timestamp);
			        			risplDkPrdvnRuleDisc.setRisplDkPrdvnRule(discountRule);
			        			em.merge(risplDkPrdvnRuleDisc);
			        	//Insert to RISPL_DK_PRDVN_RULE_DISC Table - END
		        		
			        //Insert to RISPL_DK_PRDVN_MMITM table - START
			        			List<Target> targets = new ArrayList<Target>();
			        			if(targetsTypes!=null){
			        				targets = targetsTypes.getTarget();
			        			}
			        			for(Target target : targets){
			        				RisplDkPrdvnMmitm risplDkPrdvnMmitm = new RisplDkPrdvnMmitm();
			        				risplDkPrdvnMmitm.setTsCrtRcrd(timestamp);
				        			risplDkPrdvnMmitm.setTsMdfRcrd(timestamp);
				        			risplDkPrdvnMmitm.setIdPrmPrd(target.getID());
			    	        		
				        			if(targetsTypes.getDiscountAmount()!=null){
				        				risplDkPrdvnMmitm.setMoRdnPrcMxmh(targetsTypes.getDiscountAmount().getValue());
				        			}else if(targetsTypes.getNewPrice()!=null){
				        				risplDkPrdvnMmitm.setPeRdnPrcMxmh(targetsTypes.getNewPrice().getValue());
				        			}else if(targetsTypes.getDiscountPercent()!=null){
				        				risplDkPrdvnMmitm.setPntPrcRdnMxmh(targetsTypes.getDiscountPercent());
				        			}
			        				risplDkPrdvnMmitm.setQuLmMxmh(new BigDecimal(target.getQty()));
			        				risplDkPrdvnMmitm.setRisplDkPrdvnRule(discountRule);
			        				
			        				em.merge(risplDkPrdvnMmitm);
			        			}
			        //Insert to RISPL_DK_PRDVN_MMITM table - END
			        			
			       //Insert to RISPL_DK_PRDVN_TRSHLD_ELG -  START
			    	    			
			    		        		ThresholdsType thresholdsTypes = null;
			    		        		if(sourcesTypes!=null){
			    		        			thresholdsTypes = sourcesTypes.getThresholds();
			    		        		}
			    		        		if(thresholdsTypes!=null){
			    		        		List<ThresholdType> thresholdType = thresholdsTypes.getThreshold();
			    		        		for(ThresholdType thrshld :  thresholdType){
			    		        			RisplDkPrdvnTrshldElg risplDkPrdvnTrshldElg = new RisplDkPrdvnTrshldElg();
			    		        			if(thrshld.getDiscountAmount()!=null){
			    		        				risplDkPrdvnTrshldElg.setMoUnThPrdvSls(thrshld.getDiscountAmount().getValue());
			    		        			}else if(thrshld.getDiscountPercent()!=null){
			    		        				risplDkPrdvnTrshldElg.setPtPrcThPrdvSls(thrshld.getDiscountPercent());
			    		        			}else if(thrshld.getNewPrice()!=null){
			    		        				risplDkPrdvnTrshldElg.setPeUnThPrdvSls(thrshld.getNewPrice().getValue());
			    		        			}
			    		        			risplDkPrdvnTrshldElg.setThVal(new BigDecimal(thrshld.getThreshold()));
			    		        			risplDkPrdvnTrshldElg.setIdPrdvTh(new BigDecimal(thrshld.getID()));
			    		        			risplDkPrdvnTrshldElg.setTsCrtRcrd(timestamp);
			    		        			risplDkPrdvnTrshldElg.setTsMdfRcrd(timestamp);
			    		        			risplDkPrdvnTrshldElg.setRisplDkPrdvnRule(discountRule);
			    		        			em.merge(risplDkPrdvnTrshldElg);
			    		        		}
			    		        		}
			    //Insert to RISPL_DK_PRDVN_TRSHLD_ELG -  END
			    	    			
			    	   //Insert to RISPL_DK_PRDVN_ITM_NELG table - START // for cancell Items
			    	    	       
			    	    	        	List<CancelItemsType> cancelItemsTypes = discountRuleType.getCancelItems();
			    	    	        	for(CancelItemsType cancelItemsType : cancelItemsTypes){
			    	    	        		List<CancelItemType> cancelItemTypes = cancelItemsType.getCancelItem();
			    	    	        		for(CancelItemType cancelItemType :  cancelItemTypes){
			    	    	        			RisplDkPrdvnItmNelg risplDkPrdvnItmNelg =  new RisplDkPrdvnItmNelg();
			    	    	        			risplDkPrdvnItmNelg.setTsNelEf(convertXmlDateToTimestamp(cancelItemsType.getEffectiveDateTime().toString()));// to db format
			    	    	        			risplDkPrdvnItmNelg.setTsCrtRcrd(timestamp);
			    	    	        			risplDkPrdvnItmNelg.setTsMdfRcrd(timestamp);
			    	    	        			
			    	    	        			em.merge(risplDkPrdvnItmNelg);
			    	    	        			
			    	    	        		}
			    	    	        	}
			    	    //Insert to RISPL_DK_PRDVN_ITM_NELG table - END	   
	        	}  	
	        }
//Insert to RISPL_DK_PRDVN_RULE Table - END
}
//Discount Rule End  
	        em.getTransaction().commit();
	        //em.close();
	        //factory.close();
	        LOGGER.info("Pricing XML File Processed Successfully ..............");
	      } catch (Exception e) {  
	    	  LOGGER.error(e);
	        throw e;
	      }  
    }

	@SuppressWarnings("finally")
	public static Timestamp convertXmlDateToTimestamp(String xmlValue){
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(formatInput.parse(xmlValue).getTime());
			
		} catch (ParseException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return timestamp;
		}
	}
	
	public static void deleteRule(EntityManager em, RisplDkPrdvnRule rule){
		try{

			LOGGER.info("Processing deleteRule method......");
			
			Query query = em.createNativeQuery("DELETE FROM RISPL_DK_ITEM_SIMP_PROM WHERE PROM_ID=? and PROM_CMP_ID=? and PROM_CMP_DTL_ID=?");
			query.setParameter(1, rule.getId().getIdRuPrdv());
			query.setParameter(2, rule.getId().getIdPrmCmp());
			query.setParameter(3, rule.getId().getIdPrmCmpDtl());
			query.executeUpdate();

			query = em.createNativeQuery("DELETE FROM RISPL_DK_PRDVN_DPT_ELG WHERE ID_RU_PRDV=? and ID_PRM_CMP=? and ID_PRM_CMP_DTL=?");
			query.setParameter(1, rule.getId().getIdRuPrdv());
			query.setParameter(2, rule.getId().getIdPrmCmp());
			query.setParameter(3, rule.getId().getIdPrmCmpDtl());
			query.executeUpdate();
			
			query = em.createNativeQuery("DELETE FROM RISPL_DK_PRDVN_MRCH_ELG WHERE ID_RU_PRDV=? and ID_PRM_CMP=? and ID_PRM_CMP_DTL=?");
			query.setParameter(1, rule.getId().getIdRuPrdv());
			query.setParameter(2, rule.getId().getIdPrmCmp());
			query.setParameter(3, rule.getId().getIdPrmCmpDtl());
			query.executeUpdate();
			
			query = em.createNativeQuery("DELETE FROM RISPL_DK_PRDVN_RULE_ELG WHERE ID_RU_PRDV=? and ID_PRM_CMP=? and ID_PRM_CMP_DTL=?");
			query.setParameter(1, rule.getId().getIdRuPrdv());
			query.setParameter(2, rule.getId().getIdPrmCmp());
			query.setParameter(3, rule.getId().getIdPrmCmpDtl());
			query.executeUpdate();
			
			query = em.createNativeQuery("DELETE FROM RISPL_DK_PRDVN_RULE_DISC WHERE ID_RU_PRDV=? and ID_PRM_CMP=? and ID_PRM_CMP_DTL=?");
			query.setParameter(1, rule.getId().getIdRuPrdv());
			query.setParameter(2, rule.getId().getIdPrmCmp());
			query.setParameter(3, rule.getId().getIdPrmCmpDtl());
			query.executeUpdate();
			
			query = em.createNativeQuery("DELETE FROM RISPL_DK_PRDVN_MMITM WHERE ID_RU_PRDV=? and ID_PRM_CMP=? and ID_PRM_CMP_DTL=?");
			query.setParameter(1, rule.getId().getIdRuPrdv());
			query.setParameter(2, rule.getId().getIdPrmCmp());
			query.setParameter(3, rule.getId().getIdPrmCmpDtl());
			query.executeUpdate();
			
			query = em.createNativeQuery("DELETE FROM RISPL_DK_PRDVN_TRSHLD_ELG WHERE ID_RU_PRDV=? and ID_PRM_CMP=? and ID_PRM_CMP_DTL=?");
			query.setParameter(1, rule.getId().getIdRuPrdv());
			query.setParameter(2, rule.getId().getIdPrmCmp());
			query.setParameter(3, rule.getId().getIdPrmCmpDtl());
			query.executeUpdate();
			
			query = em.createNativeQuery("DELETE FROM RISPL_DK_PRDVN_ITM_NELG WHERE ID_RU_PRDV=? and ID_PRM_CMP=? and ID_PRM_CMP_DTL=?");
			query.setParameter(1, rule.getId().getIdRuPrdv());
			query.setParameter(2, rule.getId().getIdPrmCmp());
			query.setParameter(3, rule.getId().getIdPrmCmpDtl());
			query.executeUpdate();

			query = em.createNativeQuery("DELETE FROM RISPL_DK_PRDVN_RULE WHERE ID_RU_PRDV=? and ID_PRM_CMP=? and ID_PRM_CMP_DTL=?");
			query.setParameter(1, rule.getId().getIdRuPrdv());
			query.setParameter(2, rule.getId().getIdPrmCmp());
			query.setParameter(3, rule.getId().getIdPrmCmpDtl());
			query.executeUpdate();
			
			em.getTransaction().commit();
        	em.getTransaction().begin();
			
			LOGGER.info("deleteRule method executed successfully......");
			
		}catch(Exception e){
			LOGGER.error(e);
			throw e;
		}
	}
	
}
