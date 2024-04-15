package rispl.dime.schedular.imports;

import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.dime.status.RisplDkDimeBndlStt;
import rispl.db.model.dime.status.RisplDkDimeFileStt;
import rispl.db.model.dime.status.RisplDkDimeFileSttPK;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.services.item.AbstractItemService;

@Stateless(mappedName="dimeSchedular")
@LocalBean
public class RisplDimeUtility extends AbstractItemService implements RisplDimeUtilityIfc{


	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	@PostConstruct
	void init() {
		em = emf.createEntityManager();
	}

	private static final Logger LOGGER = LogManager.getLogger(RisplDimeUtility.class);
	private static final long serialVersionUID = 1L;

	SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	private String MOM_FILE_LOC = null;
	private String MOM_FILE_ARCHV_LOC = null;

	public void processMOMFiles(StringBuffer stagingLoc, StringBuffer backupLoc) {
		MOM_FILE_LOC = stagingLoc.toString();
		MOM_FILE_ARCHV_LOC = backupLoc.toString();

		File location = new File(MOM_FILE_LOC);
		File archive = new File(MOM_FILE_ARCHV_LOC);
		if(!location.exists()){
			location.mkdirs();
			location.setWritable(true);
			location.setReadable(true);
			location.setExecutable(true);
		}

		if(!archive.exists()){
			archive.mkdirs();
			archive.setWritable(true);
			archive.setReadable(true);
			archive.setExecutable(true);
		}
		
		File allJars[] = location.listFiles();

		if (allJars != null && allJars.length > 0) {
			if (!em.getTransaction().isActive())
				em.getTransaction().begin();

			for (File eachJar : allJars) {

				RisplDkDimeBndlStt risplDkDimeBndlStt = null;
				try {
					StringBuilder fileErrorMsgs = new StringBuilder();
					boolean jarFleExceptnOccrd = false;
					risplDkDimeBndlStt = new RisplDkDimeBndlStt();
					boolean found = false;
					if (eachJar.getName().startsWith("MOM")) {
						LOGGER.info("Processing " + eachJar.getName());
						found = true;
						// Insert Jar file processing status as Processing
						if (!em.getTransaction().isActive()) {
							em.getTransaction().begin();
						}
						risplDkDimeBndlStt.setNmBndlImp(eachJar.getName());
						risplDkDimeBndlStt.setScStsBndl("Processing");
						risplDkDimeBndlStt.setTsStrImpPrc(new Timestamp(System.currentTimeMillis()));
						em.merge(risplDkDimeBndlStt);
						em.getTransaction().commit();

						JarFile jarFile = null;

						jarFile = new JarFile(eachJar);
						String storeId = "";
						Manifest manifest = jarFile.getManifest();
						if (manifest != null) {
							Attributes attributes = manifest.getMainAttributes();
							if (attributes != null) {
								storeId = attributes.getValue("StoreID");
							}
						}

						Properties props = new Properties();
						Enumeration enumz = jarFile.entries();
						String fileinJarName = null;
						while (enumz.hasMoreElements()) {

							RisplDkDimeFileStt risplDkDimeFileStt = null;
							try {

								JarEntry entry = (JarEntry) enumz.nextElement();
								fileinJarName = entry.getName();
								if ((fileinJarName.endsWith(".xml") || fileinJarName.endsWith(".XML"))
										&& entry.getSize() > 0) {

									risplDkDimeFileStt = new RisplDkDimeFileStt();

									// Insert each file processing status as
									// Processing
									em.getTransaction().begin();

									RisplDkDimeFileSttPK risplDkDimeFileSttPK = new RisplDkDimeFileSttPK();
									risplDkDimeFileSttPK.setNmBndlImp(eachJar.getName());
									risplDkDimeFileSttPK.setNmFlImp(fileinJarName);
									risplDkDimeFileStt.setId(risplDkDimeFileSttPK);

									risplDkDimeFileStt.setCsStsFl(DKartConstantsIfc.DIME_PROCESSING);
									risplDkDimeFileStt.setTsStrImpPrc(new Timestamp(System.currentTimeMillis()));

									em.merge(risplDkDimeFileStt);
									em.getTransaction().commit();

									// Call respective class method to process
									// each xml file - START
									InputStream inputStream = jarFile.getInputStream(entry);
									if (inputStream.available() > 0) {
										if (fileinJarName.contains(DKartConstantsIfc.STORE_ORG_HIER)) {
											RisplStoreHierarchyImport.readXmlAndPersist(inputStream, em);
										} else if (fileinJarName.contains(DKartConstantsIfc.MRCH_HIER)) {
											RisplMerchandiseHierarchyImport.readXmlAndPersist(inputStream, em);
										} else if (fileinJarName.contains(DKartConstantsIfc.ITEM_FILE)) {
											RisplItemImport.readItemXml(inputStream, em);
										} else if (fileinJarName.contains(DKartConstantsIfc.TAX_IMPORT)) {
											RisplTaxImport.readXmlAndPersist(inputStream, em);
										} else if (fileinJarName.contains(DKartConstantsIfc.CURR_IMP_FILE)) {
											RisplCurrencyImport.readXmlAndPersist(inputStream, em);
										} else if (fileinJarName.contains(DKartConstantsIfc.CUST_PRCGPS)) {
											RisplCustomerSegmentImport.readAndPersistCustomerSegment(inputStream, em);
										} else if (fileinJarName.contains(DKartConstantsIfc.EMPLOYEE_FILE)) {
											RisplEmployeeImport.readXmlAndPersist(inputStream, em);
										} else if (fileinJarName.contains(DKartConstantsIfc.ITM_CLSS_MOD)) {
											RisplItemClassificationImport.readAndPersistItemClassifications(inputStream,
													em);
										} else if (fileinJarName.contains(DKartConstantsIfc.PROMO_FILE)) {
											RisplPricingImport.readPersistPricingXml(inputStream, em, storeId);
										}
									}
									// Call respective class method to process
									// each xml file - END

									// Update each File processing status
									em.getTransaction().begin();
									risplDkDimeFileStt.setCsStsFl(DKartConstantsIfc.DIME_PROCESSED);
									risplDkDimeFileStt.setTsEndImpPrc(new Timestamp(System.currentTimeMillis()));

								}

							} catch (Exception e) {
								LOGGER.error(e);
								fileErrorMsgs.append(fileinJarName);
								fileErrorMsgs.append(" \n");
								jarFleExceptnOccrd = true;
								if (!em.getTransaction().isActive()) {
									em.getTransaction().begin();
								}
								String errMsg = null;
								int lstIndx = 250;
								if (e.getCause() != null) {
									if (lstIndx > e.getCause().getMessage().length()) {
										lstIndx = e.getCause().getMessage().length();
									}
									errMsg = e.getCause().getMessage().substring(0, lstIndx);
								} else if (e.getMessage() != null) {
									if (lstIndx > e.getMessage().length()) {
										lstIndx = e.getMessage().length();
									}
									errMsg = e.getMessage().substring(0, lstIndx);
								}
								risplDkDimeFileStt.setErrMsg(errMsg);
								risplDkDimeFileStt.setCsStsFl(DKartConstantsIfc.DIME_FAILED);
								risplDkDimeFileStt.setTsEndImpPrc(new Timestamp(System.currentTimeMillis()));
							} finally {
								if (risplDkDimeFileStt != null) {
									if (!em.getTransaction().isActive()) {
										em.getTransaction().begin();
									}
									em.merge(risplDkDimeFileStt);
									em.getTransaction().commit();
								}

							}

						}
						jarFile.close();
					}

					// Update Jar File processing status
					if (found) {
						LOGGER.info(eachJar.getName() + " Processed and Moved to backup folder");
						if (!em.getTransaction().isActive()) {
							em.getTransaction().begin();
						}
						if (jarFleExceptnOccrd) {
							fileErrorMsgs.append(
									"Mentioned Files Processed Partially. For Error Messages View RISPL_DK_DIME_FILE_STTS Table");
							LOGGER.error(fileErrorMsgs.toString());
							risplDkDimeBndlStt.setErrMsg(fileErrorMsgs.toString());
							risplDkDimeBndlStt.setScStsBndl(DKartConstantsIfc.DIME_PARTIALLY_PROCESSED);
						} else {
							risplDkDimeBndlStt.setScStsBndl(DKartConstantsIfc.DIME_PROCESSED);
						}
						risplDkDimeBndlStt.setTsEndImpPrc(new Timestamp(System.currentTimeMillis()));
						em.merge(risplDkDimeBndlStt);
						em.getTransaction().commit();
					}
				} catch (Exception e) {

					LOGGER.error("Unable to process " + eachJar.getName());
					if (!em.getTransaction().isActive()) {
						em.getTransaction().begin();
					}
					risplDkDimeBndlStt.setErrMsg("MOM File Corrupted or Invalid MOM File !");
					risplDkDimeBndlStt.setScStsBndl(DKartConstantsIfc.DIME_FAILED);
					risplDkDimeBndlStt.setTsEndImpPrc(new Timestamp(System.currentTimeMillis()));
					em.merge(risplDkDimeBndlStt);
					em.getTransaction().commit();
					LOGGER.error(e);
				} finally {
					// move the jar file to backup folder
					eachJar.renameTo(new File(MOM_FILE_ARCHV_LOC + "/" + eachJar.getName()));
				}

			}

		}
	}

}
