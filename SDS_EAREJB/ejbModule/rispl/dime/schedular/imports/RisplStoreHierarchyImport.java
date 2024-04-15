package rispl.dime.schedular.imports;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.storehierarchy.RisplDkStoreGroup;
import rispl.db.model.storehierarchy.RisplDkStrDstrct;
import rispl.db.model.storehierarchy.RisplDkStrDstrctPK;
import rispl.db.model.storehierarchy.RisplDkStrRgn;
import rispl.db.model.storehierarchy.RisplDkStrRgnPK;
import rispl.db.model.storehierarchy.RisplDkStrRtl;
import rispl.db.model.storehierarchy.RisplDkStrRtlPK;
import rispl.db.model.storehierarchy.RisplDkStrgpAsctn;
import rispl.db.model.storehierarchy.RisplDkStrgpAsctnPK;
import rispl.db.model.storehierarchy.RisplDkStrgpAsctnStr;
import rispl.db.model.storehierarchy.RisplDkStrgpAsctnStrPK;
import rispl.db.model.storehierarchy.RisplDkStrgpFnc;
import rispl.db.model.storehierarchy.RisplDkStrgpFncPK;
import rispl.db.model.storehierarchy.RisplDkStrgpLvl;
import rispl.db.model.storehierarchy.RisplDkStrgpLvlPK;
import rispl.jaxb.storehierarchy.AddressType;
import rispl.jaxb.storehierarchy.HierarchyListType;
import rispl.jaxb.storehierarchy.HierarchyType;
import rispl.jaxb.storehierarchy.LevelListType;
import rispl.jaxb.storehierarchy.LevelType;
import rispl.jaxb.storehierarchy.LocalizedNameDescriptionType;
import rispl.jaxb.storehierarchy.NodeListType;
import rispl.jaxb.storehierarchy.NodeType;
import rispl.jaxb.storehierarchy.PreloadDataType;
import rispl.jaxb.storehierarchy.RetailStoreType;
import rispl.jaxb.storehierarchy.StoreDistrictType;
import rispl.jaxb.storehierarchy.StoreHierarchy;
import rispl.jaxb.storehierarchy.StoreRegionType;

public class RisplStoreHierarchyImport {

	private static final Logger LOGGER = LogManager.getLogger(RisplStoreHierarchyImport.class);

	public static void readXmlAndPersist(InputStream inputStream, EntityManager em) throws Exception {

		try {
			LOGGER.info("Processing Store Hierarchy XML File.....");
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}

			JAXBContext jaxbContext = JAXBContext.newInstance(StoreHierarchy.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StoreHierarchy storeHierarchies = (StoreHierarchy) jaxbUnmarshaller.unmarshal(inputStream);

			List<Object> removeEntities = new ArrayList<Object>();
			List<Object> mergeEntities = new ArrayList<Object>();

			// Preload Data START
			PreloadDataType preloaddata = storeHierarchies.getPreloadData();
			List<StoreRegionType> storeRegionTypes = preloaddata.getStoreRegion();
			List<StoreDistrictType> storeDistrictTypes = preloaddata.getStoreDistrict();
			List<RetailStoreType> retailStoreTypes = preloaddata.getRetailStore();

			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

			// Inserting to RISPL_DK_STR_RGN table - START

			for (StoreRegionType storeRegionType : storeRegionTypes) {
				RisplDkStrRgn risplDkStrRgn = new RisplDkStrRgn();
				RisplDkStrRgnPK risplDkStrRgnPK = new RisplDkStrRgnPK();

				List<LocalizedNameDescriptionType> localized = storeRegionType.getLocalizedRegionName();
				if (localized != null && localized.size() > 0) {
					for (LocalizedNameDescriptionType local : localized) {
						setStoreRegionData(local.getLanguage().value(), storeRegionType.getRegionID(), local.getName(),
								timeStamp, risplDkStrRgn, risplDkStrRgnPK, storeRegionType, removeEntities,
								mergeEntities);
					}
				} else {
					setStoreRegionData(DKartConstantsIfc.DEFAULT_LOCALE, storeRegionType.getRegionID(),
							storeRegionType.getRegionName(), timeStamp, risplDkStrRgn, risplDkStrRgnPK, storeRegionType,
							removeEntities, mergeEntities);
				}
			}

			// Inserting to RISPL_DK_STR_RGN table - END

			timeStamp = new Timestamp(System.currentTimeMillis());
			// Inserting to RISPL_DK_STR_DSTRCT table - START
			for (StoreDistrictType storeDistrictType : storeDistrictTypes) {
				RisplDkStrDstrct risplDkStrDstrct = new RisplDkStrDstrct();
				RisplDkStrDstrctPK risplDkStrDstrctPK = new RisplDkStrDstrctPK();

				List<LocalizedNameDescriptionType> localized = storeDistrictType.getLocalizedDistrictName();
				if (localized != null && localized.size() > 0) {
					for (LocalizedNameDescriptionType local : localized) {
						setStoreDistrictData(local.getLanguage().value(), storeDistrictType.getDistrictID(),
								local.getName(), timeStamp, risplDkStrDstrct, risplDkStrDstrctPK, storeDistrictType,
								removeEntities, mergeEntities);
					}
				} else {
					setStoreDistrictData(DKartConstantsIfc.DEFAULT_LOCALE, storeDistrictType.getDistrictID(),
							storeDistrictType.getDistrictName(), timeStamp, risplDkStrDstrct, risplDkStrDstrctPK,
							storeDistrictType, removeEntities, mergeEntities);
				}
			}
			// Inserting to RISPL_DK_STR_DSTRCT table - END

			timeStamp = new Timestamp(System.currentTimeMillis());
			// Inserting to RISPL_DK_STR_RTL table - START
			for (RetailStoreType retailStoreType : retailStoreTypes) {
				AddressType address = retailStoreType.getAddress();
				RisplDkStrRtl risplDkStrRtl = new RisplDkStrRtl();
				RisplDkStrRtlPK risplDkStrRtlPK = new RisplDkStrRtlPK();

				List<LocalizedNameDescriptionType> localized = retailStoreType.getLocalizedLocationName();
				risplDkStrRtlPK.setRtStrId(retailStoreType.getRetailStoreID());
				if(address!=null && address.getCountry()!=null && !address.getCountry().equals("")){
					risplDkStrRtl.setIdCdGeo(address.getCountry());
				}else{
					risplDkStrRtl.setIdCdGeo(retailStoreType.getGeoCode());
				}
				risplDkStrRtl.setIdStrDstrct(retailStoreType.getDistrictID());
				risplDkStrRtl.setIdStrRgn(retailStoreType.getRegionID());
				risplDkStrRtl.setTsCrtRcrd(timeStamp);
				risplDkStrRtl.setTsMdfRcrd(timeStamp);

				if (localized != null && localized.size() > 0) {
					for (LocalizedNameDescriptionType local : localized) {
						risplDkStrRtlPK.setLcl(local.getLanguage().value());
						risplDkStrRtl.setId(risplDkStrRtlPK);
						risplDkStrRtl.setNmLoc(local.getName());

						if (retailStoreType.getChangeType() != null
								&& retailStoreType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							risplDkStrRtl = em.find(RisplDkStrRtl.class, risplDkStrRtlPK);
							if (risplDkStrRtl != null) {
								removeEntities.add(risplDkStrRtl);
							} else {
								break;
							}
						} else {
							mergeEntities.add(risplDkStrRtl);
						}
					}
				} else {
					risplDkStrRtlPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);
					risplDkStrRtl.setId(risplDkStrRtlPK);
					risplDkStrRtl.setNmLoc(retailStoreType.getLocationName());

					if (retailStoreType.getChangeType() != null
							&& retailStoreType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						risplDkStrRtl = em.find(RisplDkStrRtl.class, risplDkStrRtlPK);
						if (risplDkStrRtl != null) {
							removeEntities.add(risplDkStrRtl);
						}
					} else {
						mergeEntities.add(risplDkStrRtl);
					}
				}

			}

			// Inserting to RISPL_DK_STR_RTL table - END
			// Preload Data END

			// Hierarchy List START
			timeStamp = new Timestamp(System.currentTimeMillis());
			List<HierarchyListType> hierarchyListTypes = storeHierarchies.getHierarchyList();
			for (HierarchyListType hierarchyListType : hierarchyListTypes) {

				List<HierarchyType> hierarchyTypes = hierarchyListType.getHierarchy();
				// Insert into RISPL_DK_STRGP_FNC table START
				for (HierarchyType hierarchyType : hierarchyTypes) {

					RisplDkStrgpFnc risplDkStrgpFnc = new RisplDkStrgpFnc();
					RisplDkStrgpFncPK risplDkStrgpFncPK = new RisplDkStrgpFncPK();

					risplDkStrgpFncPK.setIdStrgpFnc(hierarchyType.getFunctionID());

					List<LocalizedNameDescriptionType> localizedNameDescriptionTypes = hierarchyType.getLocalizedName();
					risplDkStrgpFnc.setTsCrtRcrd(timeStamp);
					risplDkStrgpFnc.setTsMdfRcrd(timeStamp);
					if (localizedNameDescriptionTypes != null && localizedNameDescriptionTypes.size() > 0) {
						for (LocalizedNameDescriptionType localizedNameDescriptionType : localizedNameDescriptionTypes) {
							risplDkStrgpFnc.setNmStrgpFnc(localizedNameDescriptionType.getName());
							risplDkStrgpFncPK.setLcl(localizedNameDescriptionType.getLanguage().value());
							risplDkStrgpFnc.setId(risplDkStrgpFncPK);
							mergeEntities.add(risplDkStrgpFnc);
						}
					} else {
						risplDkStrgpFnc.setNmStrgpFnc(hierarchyType.getName());
						risplDkStrgpFncPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);
						risplDkStrgpFnc.setId(risplDkStrgpFncPK);
						mergeEntities.add(risplDkStrgpFnc);
					}

				}
				// Insert into RISPL_DK_STRGP_FNC table END
			}

			// Insert into RISPL_DK_STRGP_LVL table START

			for (HierarchyListType hierarchyListType : hierarchyListTypes) {

				List<HierarchyType> hierarchyTypes = hierarchyListType.getHierarchy();
				for (HierarchyType hierarchyType : hierarchyTypes) {

					LevelListType levelListType = hierarchyType.getLevelList();
					List<LevelType> levelTypes = levelListType.getLevel();
					for (LevelType levelType : levelTypes) {
						RisplDkStrgpLvl risplDkStrgpLvl = new RisplDkStrgpLvl();
						RisplDkStrgpLvlPK risplDkStrgpLvlPK = new RisplDkStrgpLvlPK();

						risplDkStrgpLvlPK.setIdStrgpFnc(hierarchyType.getFunctionID());
						risplDkStrgpLvlPK.setIdStrgpLv(levelType.getID());

						risplDkStrgpLvl.setId(risplDkStrgpLvlPK);
						if (levelType.getParentID() != null) {
							risplDkStrgpLvl.setIdStrgpLvPrnt(new BigDecimal(levelType.getParentID()));
						}
						risplDkStrgpLvl.setNmStrgpLv(levelType.getName());

						risplDkStrgpLvl.setTsCrtRcrd(timeStamp);
						risplDkStrgpLvl.setTsMdfRcrd(timeStamp);
						mergeEntities.add(risplDkStrgpLvl);
					}

					//
					NodeListType nodeListType = hierarchyType.getNodeList();
					List<NodeType> nodeTypes = nodeListType.getNode();
					timeStamp = new Timestamp(System.currentTimeMillis());
					for (NodeType nodeType : nodeTypes) {
						// Insert to RISPL_DK_STRGP_ASCTN Table START

						RisplDkStrgpAsctn risplDkStrgpAsctn = new RisplDkStrgpAsctn();
						RisplDkStrgpAsctnPK risplDkStrgpAsctnPK = new RisplDkStrgpAsctnPK();

						risplDkStrgpAsctnPK.setIdStrgpChld(nodeType.getID() + DKartConstantsIfc.EMPTY_VAL);
						risplDkStrgpAsctnPK.setIdStrgpFnc(hierarchyType.getFunctionID());
						if (nodeType.getParentNodeID() != null) {
							risplDkStrgpAsctnPK.setIdStrgpPrnt(nodeType.getParentNodeID().toString());
						} else {
							risplDkStrgpAsctnPK.setIdStrgpPrnt(risplDkStrgpAsctnPK.getIdStrgpChld());
						}

						risplDkStrgpAsctn.setId(risplDkStrgpAsctnPK);
						risplDkStrgpAsctn.setTsCrtRcrd(timeStamp);
						risplDkStrgpAsctn.setTsMdfRcrd(timeStamp);

						mergeEntities.add(risplDkStrgpAsctn);
						// Insert to RISPL_DK_STRGP_ASCTN Table END

						timeStamp = new Timestamp(System.currentTimeMillis());

						List<String> stores = nodeType.getRetailStoreId();
						
						// Insert to RISPL_DK_STORE_GROUP Table START
						RisplDkStoreGroup risplDkStoreGroup = new RisplDkStoreGroup();
						risplDkStoreGroup.setRcrdCrtTs(timeStamp);
						risplDkStoreGroup.setRcrdMdfTs(timeStamp);
						risplDkStoreGroup.setStrgpDesc(nodeType.getDescription());
						risplDkStoreGroup
								.setStrgpFncId(hierarchyType.getFunctionID() + DKartConstantsIfc.EMPTY_VAL);
						risplDkStoreGroup.setStrgpId(nodeType.getID() + DKartConstantsIfc.EMPTY_VAL);
						risplDkStoreGroup.setStrgpLvId(new BigDecimal(nodeType.getLevelID()));
						risplDkStoreGroup.setStrgpNm(nodeType.getName());
						if (nodeType.getTypeID() != null) {
							risplDkStoreGroup.setStrgpTyp(nodeType.getTypeID().toString());
						}
						mergeEntities.add(risplDkStoreGroup);
						// Insert to RISPL_DK_STORE_GROUP Table END

						for (String store : stores) {

							// Insert to RISPL_DK_STRGP_ASCTN_STR Table START
							RisplDkStrgpAsctnStr risplDkStrgpAsctnStr = new RisplDkStrgpAsctnStr();
							RisplDkStrgpAsctnStrPK risplDkStrgpAsctnStrPK = new RisplDkStrgpAsctnStrPK();

							risplDkStrgpAsctnStrPK.setIdStrgp(nodeType.getID() + DKartConstantsIfc.EMPTY_VAL);
							risplDkStrgpAsctnStrPK.setIdStrgpFnc(hierarchyType.getFunctionID());
							risplDkStrgpAsctnStrPK.setRtStrId(store);
							risplDkStrgpAsctnStr.setId(risplDkStrgpAsctnStrPK);

							risplDkStrgpAsctnStr.setTsCrtRcrd(timeStamp);
							risplDkStrgpAsctnStr.setTsMdfRcrd(timeStamp);
							mergeEntities.add(risplDkStrgpAsctnStr);
							// Insert to RISPL_DK_STRGP_ASCTN_STR Table END
						}
					}
				}
			}
			// Insert into RISPL_DK_STRGP_LVL table START
			// Hierarchy List END

			// Performing DB Operations
			if (storeHierarchies.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkStrRgn e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStrDstrct e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStrRtl e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStrgpFnc e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStrgpLvl e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStrgpAsctn e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStrgpAsctnStr e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStoreGroup e").executeUpdate();
			}

			// Remove Entities
			if (removeEntities.size() > 0 && !storeHierarchies.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object remObj : removeEntities) {
					if (remObj instanceof RisplDkStrRgn) {
						RisplDkStrRgn risplDkStrRgn = (RisplDkStrRgn) remObj;
						risplDkStrRgn = em.find(RisplDkStrRgn.class, risplDkStrRgn.getId());
						if (risplDkStrRgn != null) {
							em.remove(risplDkStrRgn);
						}
					} else if (remObj instanceof RisplDkStrDstrct) {
						RisplDkStrDstrct risplDkStrDstrct = (RisplDkStrDstrct) remObj;
						risplDkStrDstrct = em.find(RisplDkStrDstrct.class, risplDkStrDstrct.getId());
						if (risplDkStrDstrct != null) {
							em.remove(risplDkStrDstrct);
						}
					} else if (remObj instanceof RisplDkStrRtl) {
						RisplDkStrRtl risplDkStrRtl = (RisplDkStrRtl) remObj;
						risplDkStrRtl = em.find(RisplDkStrRtl.class, risplDkStrRtl.getId());
						if (risplDkStrRtl != null) {
							em.remove(risplDkStrRtl);
						}
					} else if (remObj instanceof RisplDkStrgpFnc) {
						RisplDkStrgpFnc risplDkStrgpFnc = (RisplDkStrgpFnc) remObj;
						risplDkStrgpFnc = em.find(RisplDkStrgpFnc.class, risplDkStrgpFnc.getId());
						if (risplDkStrgpFnc != null) {
							em.remove(risplDkStrgpFnc);
						}
					} else if (remObj instanceof RisplDkStrgpLvl) {
						RisplDkStrgpLvl risplDkStrgpLvl = (RisplDkStrgpLvl) remObj;
						risplDkStrgpLvl = em.find(RisplDkStrgpLvl.class, risplDkStrgpLvl.getId());
						if (risplDkStrgpLvl != null) {
							em.remove(risplDkStrgpLvl);
						}
					} else if (remObj instanceof RisplDkStrgpAsctn) {
						RisplDkStrgpAsctn risplDkStrgpAsctn = (RisplDkStrgpAsctn) remObj;
						risplDkStrgpAsctn = em.find(RisplDkStrgpAsctn.class, risplDkStrgpAsctn.getId());
						if (risplDkStrgpAsctn != null) {
							em.remove(risplDkStrgpAsctn);
						}
					} else if (remObj instanceof RisplDkStrgpAsctnStr) {
						RisplDkStrgpAsctnStr risplDkStrgpAsctnStr = (RisplDkStrgpAsctnStr) remObj;
						risplDkStrgpAsctnStr = em.find(RisplDkStrgpAsctnStr.class, risplDkStrgpAsctnStr.getId());
						if (risplDkStrgpAsctnStr != null) {
							em.remove(risplDkStrgpAsctnStr);
						}
					} else if (remObj instanceof RisplDkStoreGroup) {
						RisplDkStoreGroup risplDkStoreGroup = (RisplDkStoreGroup) remObj;
						risplDkStoreGroup = em.find(RisplDkStoreGroup.class, risplDkStoreGroup.getStrgpId());
						if (risplDkStoreGroup != null) {
							em.remove(risplDkStoreGroup);
						}
					}
				}
			}

			// Merge Entities
			if (mergeEntities.size() > 0 && !storeHierarchies.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object mrgObj : mergeEntities) {
					if (mrgObj instanceof RisplDkStrRgn) {
						RisplDkStrRgn risplDkStrRgn = (RisplDkStrRgn) mrgObj;
						risplDkStrRgn = em.find(RisplDkStrRgn.class, risplDkStrRgn.getId());
						if (risplDkStrRgn != null) {
							risplDkStrRgn = (RisplDkStrRgn) mrgObj;
							em.merge(risplDkStrRgn);
						} else {
							risplDkStrRgn = (RisplDkStrRgn) mrgObj;
							em.persist(risplDkStrRgn);
						}
					} else if (mrgObj instanceof RisplDkStrDstrct) {
						RisplDkStrDstrct risplDkStrDstrct = (RisplDkStrDstrct) mrgObj;
						risplDkStrDstrct = em.find(RisplDkStrDstrct.class, risplDkStrDstrct.getId());
						if (risplDkStrDstrct != null) {
							risplDkStrDstrct = (RisplDkStrDstrct) mrgObj;
							em.merge(risplDkStrDstrct);
						} else {
							risplDkStrDstrct = (RisplDkStrDstrct) mrgObj;
							em.persist(risplDkStrDstrct);
						}
					} else if (mrgObj instanceof RisplDkStrRtl) {
						RisplDkStrRtl risplDkStrRtl = (RisplDkStrRtl) mrgObj;
						risplDkStrRtl = em.find(RisplDkStrRtl.class, risplDkStrRtl.getId());
						if (risplDkStrRtl != null) {
							risplDkStrRtl = (RisplDkStrRtl) mrgObj;
							em.merge(risplDkStrRtl);
						} else {
							risplDkStrRtl = (RisplDkStrRtl) mrgObj;
							em.persist(risplDkStrRtl);
						}
					} else if (mrgObj instanceof RisplDkStrgpFnc) {
						RisplDkStrgpFnc risplDkStrgpFnc = (RisplDkStrgpFnc) mrgObj;
						risplDkStrgpFnc = em.find(RisplDkStrgpFnc.class, risplDkStrgpFnc.getId());
						if (risplDkStrgpFnc != null) {
							risplDkStrgpFnc = (RisplDkStrgpFnc) mrgObj;
							em.merge(risplDkStrgpFnc);
						} else {
							risplDkStrgpFnc = (RisplDkStrgpFnc) mrgObj;
							em.persist(risplDkStrgpFnc);
						}
					} else if (mrgObj instanceof RisplDkStrgpLvl) {
						RisplDkStrgpLvl risplDkStrgpLvl = (RisplDkStrgpLvl) mrgObj;
						risplDkStrgpLvl = em.find(RisplDkStrgpLvl.class, risplDkStrgpLvl.getId());
						if (risplDkStrgpLvl != null) {
							risplDkStrgpLvl = (RisplDkStrgpLvl) mrgObj;
							em.merge(risplDkStrgpLvl);
						} else {
							risplDkStrgpLvl = (RisplDkStrgpLvl) mrgObj;
							em.persist(risplDkStrgpLvl);
						}
					} else if (mrgObj instanceof RisplDkStrgpAsctn) {
						RisplDkStrgpAsctn risplDkStrgpAsctn = (RisplDkStrgpAsctn) mrgObj;
						risplDkStrgpAsctn = em.find(RisplDkStrgpAsctn.class, risplDkStrgpAsctn.getId());
						if (risplDkStrgpAsctn != null) {
							risplDkStrgpAsctn = (RisplDkStrgpAsctn) mrgObj;
							em.merge(risplDkStrgpAsctn);
						} else {
							risplDkStrgpAsctn = (RisplDkStrgpAsctn) mrgObj;
							em.persist(risplDkStrgpAsctn);
						}
					} else if (mrgObj instanceof RisplDkStrgpAsctnStr) {
						RisplDkStrgpAsctnStr risplDkStrgpAsctnStr = (RisplDkStrgpAsctnStr) mrgObj;
						risplDkStrgpAsctnStr = em.find(RisplDkStrgpAsctnStr.class, risplDkStrgpAsctnStr.getId());
						if (risplDkStrgpAsctnStr != null) {
							risplDkStrgpAsctnStr = (RisplDkStrgpAsctnStr) mrgObj;
							em.merge(risplDkStrgpAsctnStr);
						} else {
							risplDkStrgpAsctnStr = (RisplDkStrgpAsctnStr) mrgObj;
							em.persist(risplDkStrgpAsctnStr);
						}
					} else if (mrgObj instanceof RisplDkStoreGroup) {
						RisplDkStoreGroup risplDkStoreGroup = (RisplDkStoreGroup) mrgObj;
						risplDkStoreGroup = em.find(RisplDkStoreGroup.class, risplDkStoreGroup.getStrgpId());
						if (risplDkStoreGroup != null) {
							risplDkStoreGroup = (RisplDkStoreGroup) mrgObj;
							em.merge(risplDkStoreGroup);
						} else {
							risplDkStoreGroup = (RisplDkStoreGroup) mrgObj;
							em.persist(risplDkStoreGroup);
						}
					}
				}
			}else if(mergeEntities.size() > 0 && storeHierarchies.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object mrgObj : mergeEntities) {
					em.persist(mrgObj);
				}
			}
			em.getTransaction().commit();
			LOGGER.info("Store Hierarchy XML File Processed Successfully .....");
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

	}

	public static void readXmlAndPersist2(InputStream inputStream, EntityManager em) throws Exception {

		try {
			LOGGER.info("Processing Store Hierarchy XML File.....");
			em.getTransaction().begin();

			JAXBContext jaxbContext = JAXBContext.newInstance(StoreHierarchy.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StoreHierarchy storeHierarchies = (StoreHierarchy) jaxbUnmarshaller.unmarshal(inputStream);

			// Preload Data START
			PreloadDataType preloaddata = storeHierarchies.getPreloadData();
			List<StoreRegionType> storeRegionTypes = preloaddata.getStoreRegion();
			List<StoreDistrictType> storeDistrictTypes = preloaddata.getStoreDistrict();
			List<RetailStoreType> retailStoreTypes = preloaddata.getRetailStore();

			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			if (storeHierarchies.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkStrRgn e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStrDstrct e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStrRtl e").executeUpdate();
				em.getTransaction().commit();
				em.getTransaction().begin();
			}
			// Inserting to RISPL_DK_STR_RGN table - START

			for (StoreRegionType storeRegionType : storeRegionTypes) {
				RisplDkStrRgn risplDkStrRgn = new RisplDkStrRgn();
				RisplDkStrRgnPK risplDkStrRgnPK = new RisplDkStrRgnPK();

				List<LocalizedNameDescriptionType> localized = storeRegionType.getLocalizedRegionName();
				if (localized != null && localized.size() > 0) {
					for (LocalizedNameDescriptionType local : localized) {
						setStoreRegionData(local.getLanguage().value(), storeRegionType.getRegionID(), local.getName(),
								timeStamp, risplDkStrRgn, risplDkStrRgnPK, storeRegionType, em);
					}
				} else {
					setStoreRegionData(DKartConstantsIfc.DEFAULT_LOCALE, storeRegionType.getRegionID(),
							storeRegionType.getRegionName(), timeStamp, risplDkStrRgn, risplDkStrRgnPK, storeRegionType,
							em);
				}
			}

			// Inserting to RISPL_DK_STR_RGN table - END

			timeStamp = new Timestamp(System.currentTimeMillis());
			// Inserting to RISPL_DK_STR_DSTRCT table - START
			for (StoreDistrictType storeDistrictType : storeDistrictTypes) {
				RisplDkStrDstrct risplDkStrDstrct = new RisplDkStrDstrct();
				RisplDkStrDstrctPK risplDkStrDstrctPK = new RisplDkStrDstrctPK();

				List<LocalizedNameDescriptionType> localized = storeDistrictType.getLocalizedDistrictName();
				if (localized != null && localized.size() > 0) {
					for (LocalizedNameDescriptionType local : localized) {
						setStoreDistrictData(local.getLanguage().value(), storeDistrictType.getDistrictID(),
								local.getName(), timeStamp, risplDkStrDstrct, risplDkStrDstrctPK, storeDistrictType,
								em);
					}
				} else {
					setStoreDistrictData(DKartConstantsIfc.DEFAULT_LOCALE, storeDistrictType.getDistrictID(),
							storeDistrictType.getDistrictName(), timeStamp, risplDkStrDstrct, risplDkStrDstrctPK,
							storeDistrictType, em);
				}
			}
			// Inserting to RISPL_DK_STR_DSTRCT table - END

			timeStamp = new Timestamp(System.currentTimeMillis());
			// Inserting to RISPL_DK_STR_RTL table - START
			for (RetailStoreType retailStoreType : retailStoreTypes) {
				RisplDkStrRtl risplDkStrRtl = new RisplDkStrRtl();
				RisplDkStrRtlPK risplDkStrRtlPK = new RisplDkStrRtlPK();

				List<LocalizedNameDescriptionType> localized = retailStoreType.getLocalizedLocationName();

				risplDkStrRtlPK.setRtStrId(retailStoreType.getRetailStoreID());
				risplDkStrRtl.setIdCdGeo(retailStoreType.getGeoCode());
				risplDkStrRtl.setIdStrDstrct(retailStoreType.getDistrictID());
				risplDkStrRtl.setIdStrRgn(retailStoreType.getRegionID());
				risplDkStrRtl.setTsCrtRcrd(timeStamp);
				risplDkStrRtl.setTsMdfRcrd(timeStamp);

				if (localized != null && localized.size() > 0) {
					for (LocalizedNameDescriptionType local : localized) {
						risplDkStrRtlPK.setLcl(local.getLanguage().value());
						risplDkStrRtl.setId(risplDkStrRtlPK);
						risplDkStrRtl.setNmLoc(local.getName());

						if (retailStoreType.getChangeType() != null
								&& retailStoreType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							risplDkStrRtl = em.find(RisplDkStrRtl.class, risplDkStrRtlPK);
							if (risplDkStrRtl != null) {
								em.remove(risplDkStrRtl);
							} else {
								break;
							}
						} else {
							em.merge(risplDkStrRtl);
						}
					}
				} else {
					risplDkStrRtlPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);
					risplDkStrRtl.setId(risplDkStrRtlPK);
					risplDkStrRtl.setNmLoc(retailStoreType.getLocationName());

					if (retailStoreType.getChangeType() != null
							&& retailStoreType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						risplDkStrRtl = em.find(RisplDkStrRtl.class, risplDkStrRtlPK);
						if (risplDkStrRtl != null) {
							em.remove(risplDkStrRtl);
						}
					} else {
						em.merge(risplDkStrRtl);
					}
				}

			}

			// Inserting to RISPL_DK_STR_RTL table - END
			// Preload Data END

			// Hierarchy List START
			timeStamp = new Timestamp(System.currentTimeMillis());
			List<HierarchyListType> hierarchyListTypes = storeHierarchies.getHierarchyList();
			try {
				if (storeHierarchies.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
					em.createQuery("DELETE FROM RisplDkStrgpFnc e").executeUpdate();
					em.getTransaction().commit();
					em.getTransaction().begin();
				}
				for (HierarchyListType hierarchyListType : hierarchyListTypes) {

					List<HierarchyType> hierarchyTypes = hierarchyListType.getHierarchy();
					// Insert into RISPL_DK_STRGP_FNC table START
					for (HierarchyType hierarchyType : hierarchyTypes) {

						RisplDkStrgpFnc risplDkStrgpFnc = new RisplDkStrgpFnc();
						RisplDkStrgpFncPK risplDkStrgpFncPK = new RisplDkStrgpFncPK();

						risplDkStrgpFncPK.setIdStrgpFnc(hierarchyType.getFunctionID());

						List<LocalizedNameDescriptionType> localizedNameDescriptionTypes = hierarchyType
								.getLocalizedName();
						risplDkStrgpFnc.setTsCrtRcrd(timeStamp);
						risplDkStrgpFnc.setTsMdfRcrd(timeStamp);
						if (localizedNameDescriptionTypes != null && localizedNameDescriptionTypes.size() > 0) {
							for (LocalizedNameDescriptionType localizedNameDescriptionType : localizedNameDescriptionTypes) {
								risplDkStrgpFnc.setNmStrgpFnc(localizedNameDescriptionType.getName());
								risplDkStrgpFncPK.setLcl(localizedNameDescriptionType.getLanguage().value());
								risplDkStrgpFnc.setId(risplDkStrgpFncPK);
								em.merge(risplDkStrgpFnc);

							}
						} else {
							risplDkStrgpFnc.setNmStrgpFnc(hierarchyType.getName());
							risplDkStrgpFncPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);
							risplDkStrgpFnc.setId(risplDkStrgpFncPK);
							em.merge(risplDkStrgpFnc);
						}

					}
					// Insert into RISPL_DK_STRGP_FNC table END

				}
			} catch (Exception e) {
				LOGGER.error(e);
				em.getTransaction().rollback();
			}

			if (storeHierarchies.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkStrgpLvl e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStrgpAsctn e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStrgpAsctnStr e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkStoreGroup e").executeUpdate();
				em.getTransaction().commit();
				em.getTransaction().begin();
			}

			// Insert into RISPL_DK_STRGP_LVL table START

			try {
				for (HierarchyListType hierarchyListType : hierarchyListTypes) {

					List<HierarchyType> hierarchyTypes = hierarchyListType.getHierarchy();
					for (HierarchyType hierarchyType : hierarchyTypes) {

						LevelListType levelListType = hierarchyType.getLevelList();
						List<LevelType> levelTypes = levelListType.getLevel();
						for (LevelType levelType : levelTypes) {
							RisplDkStrgpLvl risplDkStrgpLvl = new RisplDkStrgpLvl();
							RisplDkStrgpLvlPK risplDkStrgpLvlPK = new RisplDkStrgpLvlPK();

							risplDkStrgpLvlPK.setIdStrgpFnc(hierarchyType.getFunctionID());
							risplDkStrgpLvlPK.setIdStrgpLv(levelType.getID());

							risplDkStrgpLvl.setId(risplDkStrgpLvlPK);
							if (levelType.getParentID() != null) {
								risplDkStrgpLvl.setIdStrgpLvPrnt(new BigDecimal(levelType.getParentID()));
							}
							risplDkStrgpLvl.setNmStrgpLv(levelType.getName());

							risplDkStrgpLvl.setTsCrtRcrd(timeStamp);
							risplDkStrgpLvl.setTsMdfRcrd(timeStamp);
							em.merge(risplDkStrgpLvl);

						}

						//
						NodeListType nodeListType = hierarchyType.getNodeList();
						List<NodeType> nodeTypes = nodeListType.getNode();
						timeStamp = new Timestamp(System.currentTimeMillis());
						for (NodeType nodeType : nodeTypes) {
							// Insert to RISPL_DK_STRGP_ASCTN Table START

							RisplDkStrgpAsctn risplDkStrgpAsctn = new RisplDkStrgpAsctn();
							RisplDkStrgpAsctnPK risplDkStrgpAsctnPK = new RisplDkStrgpAsctnPK();

							risplDkStrgpAsctnPK.setIdStrgpChld(nodeType.getID() + DKartConstantsIfc.EMPTY_VAL);
							risplDkStrgpAsctnPK.setIdStrgpFnc(hierarchyType.getFunctionID());
							if (nodeType.getParentNodeID() != null) {
								risplDkStrgpAsctnPK.setIdStrgpPrnt(nodeType.getParentNodeID().toString());
							} else {
								risplDkStrgpAsctnPK.setIdStrgpPrnt(risplDkStrgpAsctnPK.getIdStrgpChld());
							}

							risplDkStrgpAsctn.setId(risplDkStrgpAsctnPK);
							risplDkStrgpAsctn.setTsCrtRcrd(timeStamp);
							risplDkStrgpAsctn.setTsMdfRcrd(timeStamp);

							em.merge(risplDkStrgpAsctn);

							// Insert to RISPL_DK_STRGP_ASCTN Table END

							timeStamp = new Timestamp(System.currentTimeMillis());

							List<String> stores = nodeType.getRetailStoreId();

							for (String store : stores) {

								// Insert to RISPL_DK_STRGP_ASCTN_STR Table
								// START
								RisplDkStrgpAsctnStr risplDkStrgpAsctnStr = new RisplDkStrgpAsctnStr();
								RisplDkStrgpAsctnStrPK risplDkStrgpAsctnStrPK = new RisplDkStrgpAsctnStrPK();

								risplDkStrgpAsctnStrPK.setIdStrgp(nodeType.getID() + DKartConstantsIfc.EMPTY_VAL);
								risplDkStrgpAsctnStrPK.setIdStrgpFnc(hierarchyType.getFunctionID());
								risplDkStrgpAsctnStrPK.setRtStrId(store);
								risplDkStrgpAsctnStr.setId(risplDkStrgpAsctnStrPK);

								risplDkStrgpAsctnStr.setTsCrtRcrd(timeStamp);
								risplDkStrgpAsctnStr.setTsMdfRcrd(timeStamp);
								em.merge(risplDkStrgpAsctnStr);

								// Insert to RISPL_DK_STRGP_ASCTN_STR Table END

								// Insert to RISPL_DK_STORE_GROUP Table START
								RisplDkStoreGroup risplDkStoreGroup = new RisplDkStoreGroup();
								risplDkStoreGroup.setRcrdCrtTs(timeStamp);
								risplDkStoreGroup.setRcrdMdfTs(timeStamp);
								risplDkStoreGroup.setStrgpDesc(nodeType.getDescription());
								risplDkStoreGroup
										.setStrgpFncId(hierarchyType.getFunctionID() + DKartConstantsIfc.EMPTY_VAL);
								risplDkStoreGroup.setStrgpId(nodeType.getID() + DKartConstantsIfc.EMPTY_VAL);
								risplDkStoreGroup.setStrgpLvId(new BigDecimal(nodeType.getLevelID()));
								risplDkStoreGroup.setStrgpNm(nodeType.getName());
								if (nodeType.getTypeID() != null) {
									risplDkStoreGroup.setStrgpTyp(nodeType.getTypeID().toString());
								}
								em.merge(risplDkStoreGroup);
								// Insert to RISPL_DK_STORE_GROUP Table END

							}

						}

					}
				}
			} catch (Exception e) {
				LOGGER.error(e);
				em.getTransaction().rollback();
				throw e;
			}
			// Insert into RISPL_DK_STRGP_LVL table START

			// Hierarchy List END

			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}
			// em.close();
			LOGGER.info("Store Hierarchy XML File Processed Successfully .....");
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

	}

	public static void setStoreDistrictData(String locale, String regId, String distName, Timestamp timeStamp,
			RisplDkStrDstrct risplDkStrDist, RisplDkStrDstrctPK risplDkStrDistPK, StoreDistrictType storeDistrictType,
			List<Object> removeEntities, List<Object> mergeEntities) {
		risplDkStrDistPK.setLcl(locale);
		risplDkStrDistPK.setIdStrDstrct(storeDistrictType.getDistrictID());
		risplDkStrDist.setIdStrRgn(storeDistrictType.getRegionID());
		risplDkStrDist.setId(risplDkStrDistPK);
		risplDkStrDist.setNmStrDstrct(distName);
		risplDkStrDist.setTsCrtRcrd(timeStamp);
		risplDkStrDist.setTsMdfRcrd(timeStamp);

		if (storeDistrictType.getChangeType() != null
				&& storeDistrictType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
			removeEntities.add(risplDkStrDist);
		} else {
			mergeEntities.add(risplDkStrDist);
		}

	}

	public static void setStoreDistrictData(String locale, String regId, String distName, Timestamp timeStamp,
			RisplDkStrDstrct risplDkStrDist, RisplDkStrDstrctPK risplDkStrDistPK, StoreDistrictType storeDistrictType,
			EntityManager em) {
		risplDkStrDistPK.setLcl(locale);
		risplDkStrDistPK.setIdStrDstrct(storeDistrictType.getDistrictID());
		risplDkStrDist.setIdStrRgn(storeDistrictType.getRegionID());
		risplDkStrDist.setId(risplDkStrDistPK);
		risplDkStrDist.setNmStrDstrct(distName);
		risplDkStrDist.setTsCrtRcrd(timeStamp);
		risplDkStrDist.setTsMdfRcrd(timeStamp);

		if (storeDistrictType.getChangeType() != null
				&& storeDistrictType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
			risplDkStrDist = em.find(RisplDkStrDstrct.class, risplDkStrDistPK);
			if (risplDkStrDist != null) {
				em.remove(risplDkStrDist);
			}
		} else {
			em.merge(risplDkStrDist);
		}

	}

	public static void setStoreRegionData(String locale, String regId, String regName, Timestamp timeStamp,
			RisplDkStrRgn risplDkStrRgn, RisplDkStrRgnPK risplDkStrRgnPK, StoreRegionType storeRegionType,
			List<Object> removeEntities, List<Object> mergeEntities) {
		risplDkStrRgnPK.setLcl(locale);
		risplDkStrRgnPK.setIdStrRgn(storeRegionType.getRegionID());

		risplDkStrRgn.setId(risplDkStrRgnPK);
		risplDkStrRgn.setNmStrRgn(regName);
		risplDkStrRgn.setTsCrtRcrd(timeStamp);
		risplDkStrRgn.setTsMdfRcrd(timeStamp);

		if (storeRegionType.getChangeType() != null
				&& storeRegionType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
			removeEntities.add(risplDkStrRgn);
		} else {
			mergeEntities.add(risplDkStrRgn);
		}

	}

	public static void setStoreRegionData(String locale, String regId, String regName, Timestamp timeStamp,
			RisplDkStrRgn risplDkStrRgn, RisplDkStrRgnPK risplDkStrRgnPK, StoreRegionType storeRegionType,
			EntityManager em) {
		risplDkStrRgnPK.setLcl(locale);
		risplDkStrRgnPK.setIdStrRgn(storeRegionType.getRegionID());

		risplDkStrRgn.setId(risplDkStrRgnPK);
		risplDkStrRgn.setNmStrRgn(regName);
		risplDkStrRgn.setTsCrtRcrd(timeStamp);
		risplDkStrRgn.setTsMdfRcrd(timeStamp);

		if (storeRegionType.getChangeType() != null
				&& storeRegionType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
			risplDkStrRgn = em.find(RisplDkStrRgn.class, risplDkStrRgnPK);
			if (risplDkStrRgn != null) {
				em.remove(risplDkStrRgn);
			}
		} else {
			em.merge(risplDkStrRgn);
		}

	}
}
