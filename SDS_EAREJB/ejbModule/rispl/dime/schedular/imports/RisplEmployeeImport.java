package rispl.dime.schedular.imports;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.employee.RisplDkEmpMerchAssoc;
import rispl.db.model.employee.RisplDkEmpMerchAssocPK;
import rispl.db.model.employee.RisplDkEmpMstr;
import rispl.db.model.employee.RisplDkEmpMstrPK;
import rispl.db.model.employee.RisplDkEmpPwdHist;
import rispl.db.model.employee.RisplDkEmpPwdHistPK;
import rispl.db.model.employee.RisplDkEmpRole;
import rispl.jaxb.employee.EmployeeAccessType;
import rispl.jaxb.employee.EmployeeHierarchyAssnType;
import rispl.jaxb.employee.EmployeeImportType;
import rispl.jaxb.employee.EmployeeStoreOrHierarchyAssnType;
import rispl.jaxb.employee.EmployeeType;
import rispl.jaxb.employee.MerchIDType;

public class RisplEmployeeImport {
	private static final Logger LOGGER = LogManager.getLogger(RisplEmployeeImport.class);
    private static SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
    public static void readXmlAndPersist(InputStream inputStream,EntityManager em) throws Exception{

		try {  
			LOGGER.info("Processing Employee MOM Data");
	        em.getTransaction().begin();
	        
	        JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeImportType.class);  
	 	   
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
	        EmployeeImportType employeeImportType = (EmployeeImportType) jaxbUnmarshaller.unmarshal(inputStream);
	        
	        if(employeeImportType.getFillType()!=null && employeeImportType.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)){
	        	em.createNamedQuery("DEL_EMP_MERCH_ASSOC").executeUpdate();
	        	em.createNamedQuery("DEL_EMP_PWD_HST").executeUpdate();
	        	em.createNamedQuery("DEL_EMP_MSTR").executeUpdate();
	        	em.getTransaction().commit();
   				em.getTransaction().begin();
	        }
	        
	        List<EmployeeType> employeeTypes = employeeImportType.getEmployee();
	        for(EmployeeType employeeType : employeeTypes){
	        
	        //Insert into RISPL_DK_EMP_MSTR table - START
	        	List<EmployeeStoreOrHierarchyAssnType> employeeStoreOrHierarchyAssnTypes = employeeType.getEmployeeStoreOrHierarchyAssn();
	        	for(EmployeeStoreOrHierarchyAssnType employeeStoreOrHierarchyAssnType : employeeStoreOrHierarchyAssnTypes){
	        		
	        		EmployeeAccessType employeeAccessType = employeeType.getEmployeeAccess();
		        	
		        	RisplDkEmpMstr risplDkEmpMstr = new RisplDkEmpMstr();
		        	
		        	//Set email id
		        	risplDkEmpMstr.setEmail(employeeType.getEmail());
		        	
		        	RisplDkEmpMstrPK risplDkEmpMstrPK = new RisplDkEmpMstrPK();
		        	
		        	risplDkEmpMstr.setAltId(employeeAccessType.getEmployeeAltID());
		        	if(employeeType.getTempEmployeeExpirationDate()!=null){
		        		risplDkEmpMstr.setDcExpTmp(new Date(employeeType.getTempEmployeeExpirationDate().toGregorianCalendar().getTimeInMillis()));
	        		}
		        	
		        	risplDkEmpMstr.setEmpFstNme(employeeType.getEmployeeFirstName());
		        	risplDkEmpMstr.setEmpLcl(employeeType.getLocale());
		        	risplDkEmpMstr.setEmpLstNme(employeeType.getEmployeeLastName());
		        	risplDkEmpMstr.setEmpMdlNme(employeeType.getEmployeeMiddleName());
		        	risplDkEmpMstr.setEmpNme(employeeType.getEmployeeFullName());
		        	risplDkEmpMstr.setEmpStsCde(employeeType.getStatusCode());
		        	if(employeeType.getEmployeeType()!=null){
		        		risplDkEmpMstr.setEmpType(new BigDecimal(employeeType.getEmployeeType()));
		        	}
	        		
	        		risplDkEmpMstrPK.setIdStrRt(employeeStoreOrHierarchyAssnType.getEmployeeStoreID());
	        		risplDkEmpMstrPK.setEmpId(employeeType.getEmployeeID());
	        		
	        		EmployeeHierarchyAssnType employeeHierarchyAssnType = employeeStoreOrHierarchyAssnType.getEmployeeHierarchyAssn();
	        		
	        		risplDkEmpMstr.setGpId(employeeHierarchyAssnType.getNodeID()+DKartConstantsIfc.EMPTY_VAL);
	        		risplDkEmpMstr.setGpType(employeeHierarchyAssnType.getNodeType());
	        		risplDkEmpMstr.setIdStrgpFnc(new BigDecimal(employeeHierarchyAssnType.getStoreGroupFunctionID()));
	        		
	        		risplDkEmpMstr.setLoginId(employeeAccessType.getEmployeeLoginID());
	        		if(employeeType.getNumberDaysValid()!=null){
	        			risplDkEmpMstr.setNumbDysVld(new BigDecimal(employeeType.getNumberDaysValid()));
	        		}
	        		
	        		risplDkEmpMstr.setNumbFldPw(new BigDecimal(0));
	        		if(employeeType.getPartyID()!=null){
	        			risplDkEmpMstr.setPrtyId(new BigDecimal(employeeType.getPartyID()));
	        		}
	        		risplDkEmpMstr.setTsCrtPw(convertXmlDateToTimestamp(employeeAccessType.getPasswordCreationDate().toString()));
	        		
	        		RisplDkEmpRole risplDkEmpRole = em.getReference(RisplDkEmpRole.class, (long)employeeAccessType.getWorkGroupID());
	        		risplDkEmpMstr.setRisplDkEmpRole(risplDkEmpRole);
	        		
	        		risplDkEmpMstr.setId(risplDkEmpMstrPK);
	        	//Insert into RISPL_DK_EMP_MSTR table - END
	        	
	        		//Insert into RISPL_DK_EMP_MERCH_ASSOC table -START  insert after 'Insert into RISPL_DK_EMP_PWD_HIST table - END'
	        		List<MerchIDType> merchIds=employeeType.getMerchandiseAssociation().getMerchID();
	        		List<RisplDkEmpMerchAssoc> mercAssocList=new ArrayList<>();
		        	if(merchIds != null && merchIds.size()>0 ){
		        		for(MerchIDType merchId:merchIds){
		        			RisplDkEmpMerchAssoc parentAssoc=new RisplDkEmpMerchAssoc();
		        			RisplDkEmpMerchAssocPK parentAssocPK=new RisplDkEmpMerchAssocPK();
		        			parentAssocPK.setEmpId(employeeType.getEmployeeID());
		        			parentAssocPK.setMerchId(merchId.getParentLevelId());
		        			parentAssocPK.setStoreId(employeeStoreOrHierarchyAssnType.getEmployeeStoreID());
		        			parentAssoc.setId(parentAssocPK);
		        			parentAssoc.setRisplDkEmpMstr(risplDkEmpMstr);
		        			mercAssocList.add(parentAssoc);
		        			
			        			List<String> childIds=merchId.getChildID();
			        			if(childIds != null && childIds.size()>0 ){
			        				for(String childId:childIds){
			        					if(childId!=null && !childId.equalsIgnoreCase("")){
			        					RisplDkEmpMerchAssoc mercAssoc=new RisplDkEmpMerchAssoc();
			    	        			RisplDkEmpMerchAssocPK mercAssocPK=new RisplDkEmpMerchAssocPK();
			    	        			
			    	        			mercAssocPK.setEmpId(employeeType.getEmployeeID());
			    	        			mercAssocPK.setMerchId(childId);
			    	        			mercAssocPK.setStoreId(employeeStoreOrHierarchyAssnType.getEmployeeStoreID());
			    	        			
			    	        			mercAssoc.setId(mercAssocPK);
			    	        			mercAssoc.setRisplDkEmpMstr(risplDkEmpMstr);
			    	        			mercAssocList.add(mercAssoc);
			        					}
			        				}
			        			}
			        		
		        		}
		        		
		        		
		        	}
		        	
		        	risplDkEmpMstr.setRisplDkEmpMerchAssocs(mercAssocList);
//Insert into RISPL_DK_EMP_MERCH_ASSOC table -END
	        	
		        	if(employeeType.getChangeType()!=null && (employeeType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL) || employeeType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.UPD))){
		        		//RisplDkEmpMstr exstdRisplDkEmpMstr = em.find(RisplDkEmpMstr.class, risplDkEmpMstr.getId());
		        		//if(exstdRisplDkEmpMstr!=null && employeeType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)){
		        		if(employeeType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)){
		        			//exstdRisplDkEmpMstr = setRequiredData(risplDkEmpMstr,employeeType,employeeStoreOrHierarchyAssnType,employeeAccessType);
		        			//em.remove(exstdRisplDkEmpMstr);
		        			deleteEmp(risplDkEmpMstr.getId().getEmpId(),em);
		        		}else if(employeeType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.UPD)){
		        			RisplDkEmpMstr exstdRisplDkEmpMstr = em.find(RisplDkEmpMstr.class, risplDkEmpMstr.getId());
		        			if(exstdRisplDkEmpMstr!=null){
		        			if(employeeAccessType.isNewPasswordRequired()){
				        		risplDkEmpMstr.setFlPwNwReq("1");
				        	}else{
				        		risplDkEmpMstr.setFlPwNwReq("0");
				        	}
		        			risplDkEmpMstr.setEmpAcsPwd(exstdRisplDkEmpMstr.getEmpAcsPwd());
				        	risplDkEmpMstr.setEmpPwdSlt(exstdRisplDkEmpMstr.getEmpPwdSlt());
		        			em.merge(risplDkEmpMstr);
		        		}
		        		}
	        		}else{
	        			
	        			risplDkEmpMstr = setRequiredData(risplDkEmpMstr,employeeType,employeeStoreOrHierarchyAssnType,employeeAccessType);
	        			em.merge(risplDkEmpMstr);
	        		}
	        	}
	        	
	        }
	      em.getTransaction().commit();
	      LOGGER.info("Processed Employee MOM Data");
		}catch(Exception e){
			LOGGER.error(e);
			throw e;
		}
	
    }

    public static void deleteEmp(String empId, EntityManager entityManager){
    	try{
    		LOGGER.info("Deleting Employee : "+empId);
		Query query = entityManager.createNativeQuery("DELETE FROM RISPL_DK_EMP_PWD_HIST WHERE EMP_ID=?");
		query.setParameter(1, empId);
		query.executeUpdate();
		
		query = entityManager.createNativeQuery("DELETE FROM RISPL_DK_EMP_MERCH_ASSOC WHERE EMP_ID=?");
		query.setParameter(1, empId);
		query.executeUpdate();
		
		query = entityManager.createNativeQuery("DELETE FROM RISPL_DK_EMP_MSTR WHERE EMP_ID=?");
		query.setParameter(1, empId);
		query.executeUpdate();
		
		LOGGER.info("Deleted Employee : "+empId);
    }catch(Exception e){
    	LOGGER.error(e);
    }
		
   }
    
    public static RisplDkEmpMstr setRequiredData(RisplDkEmpMstr risplDkEmpMstr, EmployeeType employeeType, EmployeeStoreOrHierarchyAssnType employeeStoreOrHierarchyAssnType, EmployeeAccessType employeeAccessType){
    	//Insert into RISPL_DK_EMP_PWD_HIST table - START
		RisplDkEmpPwdHist risplDkEmpPwdHist = new RisplDkEmpPwdHist();
		RisplDkEmpPwdHistPK risplDkEmpPwdHistPK = new RisplDkEmpPwdHistPK();
		
		risplDkEmpPwdHistPK.setIdStrRt(employeeStoreOrHierarchyAssnType.getEmployeeStoreID());
		risplDkEmpPwdHistPK.setEmpId(employeeType.getEmployeeID());
		
		risplDkEmpPwdHist.setId(risplDkEmpPwdHistPK);
		risplDkEmpPwdHist.setPwAcsEm(employeeAccessType.getAccessPassword());
		risplDkEmpPwdHist.setPwSltEm(DKartConstantsIfc.EMPLOYEE_PWD_SALT);
		risplDkEmpPwdHist.setTsCrtPw(convertXmlDateToTimestamp(employeeAccessType.getPasswordCreationDate().toString()));
		risplDkEmpPwdHist.setRisplDkEmpMstr(risplDkEmpMstr);
		
		risplDkEmpMstr.setRisplDkEmpPwdHist(risplDkEmpPwdHist);
		
		risplDkEmpMstr.setEmpAcsPwd(employeeAccessType.getAccessPassword());
    	risplDkEmpMstr.setEmpPwdSlt(DKartConstantsIfc.EMPLOYEE_PWD_SALT);
    	
    	if(employeeAccessType.isNewPasswordRequired()){
    		risplDkEmpMstr.setFlPwNwReq("1");
    	}else{
    		risplDkEmpMstr.setFlPwNwReq("0");
    	}
    	return risplDkEmpMstr;
    }
	@SuppressWarnings("finally")
	public static Timestamp convertXmlDateToTimestamp(String xmlValue){
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(formatInput.parse(xmlValue).getTime());
		} catch (ParseException e) {
		// TODO Auto-generated catch block
			LOGGER.error(e);
		}finally{
			return timestamp;
		}
	}
}
