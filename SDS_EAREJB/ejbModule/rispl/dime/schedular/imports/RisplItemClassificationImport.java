package rispl.dime.schedular.imports;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.item.RisplDkItmClssMod;
import rispl.db.model.item.pricing.RisplDkItemPrice;
import rispl.jaxb.item.classification.Classification;
import rispl.jaxb.item.classification.Item;
import rispl.jaxb.item.classification.ItemImport;

public class RisplItemClassificationImport {
	private static final Logger LOGGER = LogManager.getLogger(RisplItemClassificationImport.class);
    public static void readAndPersistItemClassifications(InputStream inputStream,EntityManager em) throws Exception{
		try {  
			LOGGER.info("Processing ItemClassification XML File............");
			if(!em.getTransaction().isActive()){
				em.getTransaction().begin();
			}
	          
	        JAXBContext jaxbContext = JAXBContext.newInstance(ItemImport.class);  
	   
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
	        ItemImport itemClassifications = (ItemImport) jaxbUnmarshaller.unmarshal(inputStream);
	        List<Item> items = itemClassifications.getItem();
	        Map<String, BigDecimal> itmidClssidSeq = null;
	        if(itemClassifications.getFillType()!=null && !itemClassifications.getFillType().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)){
	        	Query queryAll = em.createQuery("SELECT risplDkItmClssMod FROM RisplDkItmClssMod risplDkItmClssMod",RisplDkItmClssMod.class);
	        	List<RisplDkItmClssMod> allRecords = queryAll.getResultList();
	        	itmidClssidSeq = allRecords.stream().collect(Collectors.toMap(obj -> obj.getItmId()+"-"+obj.getClssFctnId(), obj -> obj.getSeqId()));
	        }
	        
	        if(itemClassifications.getFillType()!=null && itemClassifications.getFillType().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)){
	        	em.createQuery("DELETE FROM RisplDkItmClssMod e").executeUpdate();
	        	em.getTransaction().commit();
	        	em.getTransaction().begin();
	        }
	        
	        
	        for(Item item : items){
	        	RisplDkItmClssMod risplDkItemClass = new RisplDkItmClssMod();
	        	risplDkItemClass.setItmId(item.getID().toString());
	        	
	        	Classification classfic = item.getClassification();
	        	risplDkItemClass.setClssFctnId(classfic.getID().toString());
	        	
	        	if(itmidClssidSeq!=null && itmidClssidSeq.containsKey(risplDkItemClass.getItmId()+"-"+risplDkItemClass.getClssFctnId())){
	        		risplDkItemClass.setSeqId(itmidClssidSeq.get(risplDkItemClass.getItmId()+"-"+risplDkItemClass.getClssFctnId()));
	        		if(item.getChangeType()!=null && item.getChangeType().equalsIgnoreCase(DKartConstantsIfc.DEL)){
	        			em.remove(risplDkItemClass);
	        		}else{
	        			em.merge(risplDkItemClass);
	        		}
	        	}else{
	        		em.persist(risplDkItemClass);
	        	}
	        	
	        	/*Query query = em.createQuery("SELECT risplDkItmClssMod FROM RisplDkItmClssMod risplDkItmClssMod WHERE risplDkItmClssMod.itmId=:itmId AND risplDkItmClssMod.clssFctnId=:clssFctnId",RisplDkItmClssMod.class);
	        	query.setParameter("itmId", risplDkItemClass.getItmId());
	        	query.setParameter("clssFctnId", risplDkItemClass.getClssFctnId());
	        	List<RisplDkItmClssMod> exstngRcrds = query.getResultList();
	        	if(exstngRcrds.size()>0 && itemClassifications.getFillType()!=null && !itemClassifications.getFillType().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
	        		risplDkItemClass.setSeqId(exstngRcrds.get(0).getSeqId());
	        		em.merge(risplDkItemClass);
	        	}else{
	        		em.persist(risplDkItemClass);
	        	}*/
	        	
	        }
	        em.getTransaction().commit();
	        LOGGER.info("ItemClassification XML File Processed Successfully............");
		}catch(Exception e){
			LOGGER.error(e);
			throw e;
		}
    }
}
