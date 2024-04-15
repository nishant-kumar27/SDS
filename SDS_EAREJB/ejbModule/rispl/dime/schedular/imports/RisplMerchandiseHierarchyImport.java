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

import rispl.db.model.merchandisehierarchy.RisplDkMrchAssn;
import rispl.db.model.merchandisehierarchy.RisplDkMrchAssnPK;
import rispl.db.model.merchandisehierarchy.RisplDkMrchDpt;
import rispl.db.model.merchandisehierarchy.RisplDkMrchDptPK;
import rispl.db.model.merchandisehierarchy.RisplDkMrchGrp;
import rispl.db.model.merchandisehierarchy.RisplDkMrchGrpPK;
import rispl.db.model.merchandisehierarchy.RisplDkMrchHier;
import rispl.db.model.merchandisehierarchy.RisplDkMrchHierPK;
import rispl.db.model.merchandisehierarchy.RisplDkMrchLvl;
import rispl.db.model.merchandisehierarchy.RisplDkMrchLvlPK;
import rispl.db.model.merchandisehierarchy.RisplDkMrchPosDpt;
import rispl.db.model.merchandisehierarchy.RisplDkMrchPosDptPK;
import rispl.jaxb.merchandisehierarchy.HierarchyListType;
import rispl.jaxb.merchandisehierarchy.HierarchyType;
import rispl.jaxb.merchandisehierarchy.LevelListType;
import rispl.jaxb.merchandisehierarchy.LevelType;
import rispl.jaxb.merchandisehierarchy.LocalizedNameDescriptionType;
import rispl.jaxb.merchandisehierarchy.LocalizedPOSDepartmentNameType;
import rispl.jaxb.merchandisehierarchy.MerchandiseGroupType;
import rispl.jaxb.merchandisehierarchy.MerchandiseHierarchy;
import rispl.jaxb.merchandisehierarchy.NodeListType;
import rispl.jaxb.merchandisehierarchy.NodeType;
import rispl.jaxb.merchandisehierarchy.POSDepartmentType;
import rispl.jaxb.merchandisehierarchy.PreloadDataType;
import rispl.jaxb.merchandisehierarchy.RetailStorePOSDepartmentType;

public class RisplMerchandiseHierarchyImport {

	private static final Logger LOGGER = LogManager.getLogger(RisplMerchandiseHierarchyImport.class);

	public static void readXmlAndPersist(InputStream inputStream, EntityManager em) throws Exception {
		try {
			LOGGER.info("Processing Merchandise XML File...........");
			em.getTransaction().begin();

			JAXBContext jaxbContext = JAXBContext.newInstance(MerchandiseHierarchy.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			MerchandiseHierarchy merchandiseHierarchy = (MerchandiseHierarchy) jaxbUnmarshaller.unmarshal(inputStream);

			List<Object> removeEntities = new ArrayList<Object>();
			List<Object> mergeEntities = new ArrayList<Object>();

			// Preload Data START

			PreloadDataType preloadDataType = merchandiseHierarchy.getPreloadData();
			List<MerchandiseGroupType> merchandiseGroupTypes = preloadDataType.getMerchandiseGroup();
			List<POSDepartmentType> posDepartmentTypes = preloadDataType.getPOSDepartment();

			for (POSDepartmentType posDepartmentType : posDepartmentTypes) {

				List<LocalizedPOSDepartmentNameType> localizedPOSDepartmentNameTypes = posDepartmentType
						.getPOSDepartmentName();
				if (localizedPOSDepartmentNameTypes != null && localizedPOSDepartmentNameTypes.size() > 0) {
					for (LocalizedPOSDepartmentNameType localizedPOSDepartmentNameType : localizedPOSDepartmentNameTypes) {

						// Insert to RISPL_DK_MRCH_DPT table - START
						RisplDkMrchDpt risplDkMrchDpt = new RisplDkMrchDpt();
						RisplDkMrchDptPK risplDkMrchDptPK = new RisplDkMrchDptPK();

						risplDkMrchDptPK.setIdDptPos(posDepartmentType.getPOSDepartmentID());
						if (localizedPOSDepartmentNameType.getLanguageCode() != null) {
							risplDkMrchDptPK.setLcl(localizedPOSDepartmentNameType.getLanguageCode().value());
						} else {
							risplDkMrchDptPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);
						}

						risplDkMrchDpt.setId(risplDkMrchDptPK);
						risplDkMrchDpt.setIdDptPosPrnt(posDepartmentType.getParentPOSDepartmentID());
						risplDkMrchDpt.setIdGpTx(posDepartmentType.getDepartmentDefaultTaxGroup() + "");
						risplDkMrchDpt.setNmDptPos(localizedPOSDepartmentNameType.getText());

						if (posDepartmentType.getChangeType() != null
								&& posDepartmentType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							removeEntities.add(risplDkMrchDpt);
						} else {
							mergeEntities.add(risplDkMrchDpt);
						}

						// Insert to RISPL_DK_MRCH_DPT table - END

					}
				}

				// Insert to RISPL_DK_MRCH_POS_DPT table START

				List<RetailStorePOSDepartmentType> retailStorePOSDepartmentTypes = posDepartmentType
						.getRetailStorePOSDepartment();
				for (RetailStorePOSDepartmentType retailStorePOSDepartmentType : retailStorePOSDepartmentTypes) {
					RisplDkMrchPosDpt risplDkMrchPosDpt = new RisplDkMrchPosDpt();
					RisplDkMrchPosDptPK risplDkMrchPosDptPK = new RisplDkMrchPosDptPK();

					risplDkMrchPosDptPK.setPosDptId(posDepartmentType.getPOSDepartmentID());
					risplDkMrchPosDptPK.setRtStrId(retailStorePOSDepartmentType.getRetailStoreId());

					risplDkMrchPosDpt.setCdEntSrt(new BigDecimal(retailStorePOSDepartmentType.getListSortIndex()));
					if (retailStorePOSDepartmentType.getDefaultEntryCode().equalsIgnoreCase("true")) {
						risplDkMrchPosDpt.setDfltEntCdFlg("1");
					} else {
						risplDkMrchPosDpt.setDfltEntCdFlg("0");
					}

					if (retailStorePOSDepartmentType.isEnabledFlag()) {
						risplDkMrchPosDpt.setFlCdEntEnab("1");
					} else {
						risplDkMrchPosDpt.setFlCdEntEnab("0");
					}
					risplDkMrchPosDpt.setId(risplDkMrchPosDptPK);

					if (posDepartmentType.getChangeType() != null
							&& posDepartmentType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						removeEntities.add(risplDkMrchPosDpt);
					} else {
						mergeEntities.add(risplDkMrchPosDpt);
					}
				}

				// Insert to RISPL_DK_MRCH_POS_DPT table END

			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			// Insert to RISPL_DK_MRCH_GRP table START
			for (MerchandiseGroupType merchandiseGroupType : merchandiseGroupTypes) {

				List<LocalizedNameDescriptionType> localizedNameDescriptionTypes = merchandiseGroupType
						.getLocalizedNameDescription();

				if (localizedNameDescriptionTypes != null && localizedNameDescriptionTypes.size() > 0) {
					for (LocalizedNameDescriptionType localizedNameDescriptionType : localizedNameDescriptionTypes) {
						RisplDkMrchGrp risplDkMrchGrp = new RisplDkMrchGrp();
						RisplDkMrchGrpPK risplDkMrchGrpPK = new RisplDkMrchGrpPK();

						risplDkMrchGrpPK.setIdMrhrcGp(merchandiseGroupType.getID());
						risplDkMrchGrpPK.setLcl(localizedNameDescriptionType.getLanguage().value());

						risplDkMrchGrp.setDeMrhrcGp(merchandiseGroupType.getDescription());
						risplDkMrchGrp.setId(risplDkMrchGrpPK);
						if (merchandiseGroupType.getID() != null
								&& !merchandiseGroupType.getID().equalsIgnoreCase("")) {
							risplDkMrchGrp.setIdPst(new BigDecimal(merchandiseGroupType.getID()));
						}
						risplDkMrchGrp.setNmMrhrcGp(merchandiseGroupType.getName());
						risplDkMrchGrp.setTsCrtRcrd(timestamp);
						risplDkMrchGrp.setTsMdfRcrd(timestamp);

						if (merchandiseGroupType.getChangeType() != null && merchandiseGroupType.getChangeType().value()
								.equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							removeEntities.add(risplDkMrchGrp);
						} else {
							mergeEntities.add(risplDkMrchGrp);
						}
					}
				} else {
					RisplDkMrchGrp risplDkMrchGrp = new RisplDkMrchGrp();
					RisplDkMrchGrpPK risplDkMrchGrpPK = new RisplDkMrchGrpPK();

					risplDkMrchGrpPK.setIdMrhrcGp(merchandiseGroupType.getID());
					risplDkMrchGrpPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

					risplDkMrchGrp.setDeMrhrcGp(merchandiseGroupType.getDescription());
					risplDkMrchGrp.setId(risplDkMrchGrpPK);
					if (merchandiseGroupType.getMerchantID() != null) {
						risplDkMrchGrp.setIdPst(new BigDecimal(merchandiseGroupType.getMerchantID()));
					}
					risplDkMrchGrp.setNmMrhrcGp(merchandiseGroupType.getName());
					risplDkMrchGrp.setTsCrtRcrd(timestamp);
					risplDkMrchGrp.setTsMdfRcrd(timestamp);

					if (merchandiseGroupType.getChangeType() != null
							&& merchandiseGroupType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						removeEntities.add(risplDkMrchGrp);
					} else {
						mergeEntities.add(risplDkMrchGrp);
					}
				}
			}
			// Insert to RISPL_DK_MRCH_GRP table END

			// Preload Data END

			timestamp = new Timestamp(System.currentTimeMillis());
			List<HierarchyListType> hierarchyListTypes = merchandiseHierarchy.getHierarchyList();
			for (HierarchyListType hierarchyListType : hierarchyListTypes) {
				List<HierarchyType> hierarchyTypes = hierarchyListType.getHierarchy();
				for (HierarchyType hierarchyType : hierarchyTypes) {

					// Insert to RISPL_DK_MRCH_HIER table - START

					List<LocalizedNameDescriptionType> localizedNameDescriptionTypes = hierarchyType.getLocalizedName();
					if (localizedNameDescriptionTypes != null && localizedNameDescriptionTypes.size() > 0) {
						for (LocalizedNameDescriptionType localizedNameDescriptionType : localizedNameDescriptionTypes) {
							RisplDkMrchHier risplDkMrchHier = new RisplDkMrchHier();
							RisplDkMrchHierPK risplDkMrchHierPK = new RisplDkMrchHierPK();

							risplDkMrchHierPK.setIdMrhrcFnc(hierarchyType.getFunctionID());
							risplDkMrchHierPK.setLcl(localizedNameDescriptionType.getLanguage().value());

							risplDkMrchHier.setId(risplDkMrchHierPK);
							risplDkMrchHier.setNmMrhrcFnc(localizedNameDescriptionType.getName());
							risplDkMrchHier.setTsCrtRcrd(timestamp);
							risplDkMrchHier.setTsMdfRcrd(timestamp);
							mergeEntities.add(risplDkMrchHier);
						}

					} else {
						RisplDkMrchHier risplDkMrchHier = new RisplDkMrchHier();
						RisplDkMrchHierPK risplDkMrchHierPK = new RisplDkMrchHierPK();

						risplDkMrchHierPK.setIdMrhrcFnc(hierarchyType.getFunctionID());
						risplDkMrchHierPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

						risplDkMrchHier.setId(risplDkMrchHierPK);
						risplDkMrchHier.setNmMrhrcFnc(hierarchyType.getName());
						risplDkMrchHier.setTsCrtRcrd(timestamp);
						risplDkMrchHier.setTsMdfRcrd(timestamp);
						mergeEntities.add(risplDkMrchHier);
					}
					// Insert to RISPL_DK_MRCH_HIER table - END

					// Insert to RISPL_DK_MRCH_LVL table - START
					timestamp = new Timestamp(System.currentTimeMillis());
					LevelListType levelListType = hierarchyType.getLevelList();
					List<LevelType> levelTypes = levelListType.getLevel();
					for (LevelType levelType : levelTypes) {
						List<LocalizedNameDescriptionType> localizedNameDescriptionTypes2 = levelType
								.getLocalizedName();
						if (localizedNameDescriptionTypes2 != null && localizedNameDescriptionTypes2.size() > 0) {
							for (LocalizedNameDescriptionType localizedNameDescriptionType2 : localizedNameDescriptionTypes2) {
								RisplDkMrchLvl risplDkMrchLvl = new RisplDkMrchLvl();
								RisplDkMrchLvlPK risplDkMrchLvlPK = new RisplDkMrchLvlPK();

								risplDkMrchLvlPK.setIdMrhrcLv(levelType.getID());
								risplDkMrchLvlPK.setLcl(localizedNameDescriptionType2.getLanguage().value());

								risplDkMrchLvl.setId(risplDkMrchLvlPK);
								risplDkMrchLvl.setIdMrhrcFnc(new BigDecimal(hierarchyType.getFunctionID()));
								if (levelType.getParentID() != null) {
									risplDkMrchLvl.setIdMrhrcLvPrnt(new BigDecimal(levelType.getParentID()));
								}

								risplDkMrchLvl.setNmMrhrcLv(localizedNameDescriptionType2.getName());
								risplDkMrchLvl.setTsCrtRcrd(timestamp);
								risplDkMrchLvl.setTsMdfRcrd(timestamp);

								mergeEntities.add(risplDkMrchLvl);
							}
						} else {

							RisplDkMrchLvl risplDkMrchLvl = new RisplDkMrchLvl();
							RisplDkMrchLvlPK risplDkMrchLvlPK = new RisplDkMrchLvlPK();

							risplDkMrchLvlPK.setIdMrhrcLv(levelType.getID());
							risplDkMrchLvlPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

							risplDkMrchLvl.setId(risplDkMrchLvlPK);
							risplDkMrchLvl.setIdMrhrcFnc(new BigDecimal(hierarchyType.getFunctionID()));
							if (levelType.getParentID() != null) {
								risplDkMrchLvl.setIdMrhrcLvPrnt(new BigDecimal(levelType.getParentID()));
							}

							risplDkMrchLvl.setNmMrhrcLv(levelType.getName());
							risplDkMrchLvl.setTsCrtRcrd(timestamp);
							risplDkMrchLvl.setTsMdfRcrd(timestamp);

							mergeEntities.add(risplDkMrchLvl);
						}
					}
					// Insert to RISPL_DK_MRCH_LVL table - END

					// Insert to RISPL_DK_MRCH_ASSN table - START
					timestamp = new Timestamp(System.currentTimeMillis());
					NodeListType nodeListType = hierarchyType.getNodeList();
					List<NodeType> nodeTypes = nodeListType.getNode();
					for (NodeType nodeType : nodeTypes) {

						RisplDkMrchAssn risplDkMrchAssn = new RisplDkMrchAssn();
						RisplDkMrchAssnPK risplDkMrchAssnPK = new RisplDkMrchAssnPK();

						risplDkMrchAssnPK.setIdMrhrcGpChld(nodeType.getID());
						if (nodeType.getParentNodeID() == null) {
							risplDkMrchAssnPK.setIdMrhrcGpPrnt(nodeType.getID());
						} else {
							risplDkMrchAssnPK.setIdMrhrcGpPrnt(nodeType.getParentNodeID());
						}

						risplDkMrchAssn.setId(risplDkMrchAssnPK);
						risplDkMrchAssn.setIdMrhrcFnc(new BigDecimal(hierarchyType.getFunctionID()));
						risplDkMrchAssn.setIdMrhrcLv(new BigDecimal(nodeType.getLevelID()));
						risplDkMrchAssn.setTsCrtRcrd(timestamp);
						risplDkMrchAssn.setTsMdfRcrd(timestamp);

						mergeEntities.add(risplDkMrchAssn);
					}
					// Insert to RISPL_DK_MRCH_ASSN table - START

				}
			}

			// Perform DB Operations
			if (merchandiseHierarchy.getFillType() != null
					&& merchandiseHierarchy.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkMrchPosDpt e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkMrchGrp e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkMrchDpt e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkMrchHier e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkMrchLvl e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkMrchAssn e").executeUpdate();
			}

			// Remove Entities
			if (removeEntities.size() > 0 && merchandiseHierarchy.getFillType() != null
					&& !merchandiseHierarchy.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object remObj : removeEntities) {
					if (remObj instanceof RisplDkMrchPosDpt) {
						RisplDkMrchPosDpt risplDkMrchPosDpt = (RisplDkMrchPosDpt) remObj;
						risplDkMrchPosDpt = em.find(RisplDkMrchPosDpt.class, risplDkMrchPosDpt.getId());
						if (risplDkMrchPosDpt != null) {
							em.remove(risplDkMrchPosDpt);
						}
					} else if (remObj instanceof RisplDkMrchGrp) {
						RisplDkMrchGrp risplDkMrchGrp = (RisplDkMrchGrp) remObj;
						risplDkMrchGrp = em.find(RisplDkMrchGrp.class, risplDkMrchGrp.getId());
						if (risplDkMrchGrp != null) {
							em.remove(risplDkMrchGrp);
						}
					} else if (remObj instanceof RisplDkMrchDpt) {
						RisplDkMrchDpt risplDkMrchDpt = (RisplDkMrchDpt) remObj;
						risplDkMrchDpt = em.find(RisplDkMrchDpt.class, risplDkMrchDpt.getId());
						if (risplDkMrchDpt != null) {
							em.remove(risplDkMrchDpt);
						}
					} else if (remObj instanceof RisplDkMrchHier) {
						RisplDkMrchHier risplDkMrchHier = (RisplDkMrchHier) remObj;
						risplDkMrchHier = em.find(RisplDkMrchHier.class, risplDkMrchHier.getId());
						if (risplDkMrchHier != null) {
							em.remove(risplDkMrchHier);
						}
					} else if (remObj instanceof RisplDkMrchLvl) {
						RisplDkMrchLvl risplDkMrchLvl = (RisplDkMrchLvl) remObj;
						risplDkMrchLvl = em.find(RisplDkMrchLvl.class, risplDkMrchLvl.getId());
						if (risplDkMrchLvl != null) {
							em.remove(risplDkMrchLvl);
						}
					} else if (remObj instanceof RisplDkMrchAssn) {
						RisplDkMrchAssn risplDkMrchAssn = (RisplDkMrchAssn) remObj;
						risplDkMrchAssn = em.find(RisplDkMrchAssn.class, risplDkMrchAssn.getId());
						if (risplDkMrchAssn != null) {
							em.remove(risplDkMrchAssn);
						}
					}
				}
			}

			// Merge Entities
			if (mergeEntities.size() > 0 && merchandiseHierarchy.getFillType() != null
					&& !merchandiseHierarchy.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object mrgObj : mergeEntities) {
					if (mrgObj instanceof RisplDkMrchPosDpt) {
						RisplDkMrchPosDpt risplDkMrchPosDpt = (RisplDkMrchPosDpt) mrgObj;
						risplDkMrchPosDpt = em.find(RisplDkMrchPosDpt.class, risplDkMrchPosDpt.getId());
						if (risplDkMrchPosDpt != null) {
							risplDkMrchPosDpt = (RisplDkMrchPosDpt) mrgObj;
							em.merge(risplDkMrchPosDpt);
						} else {
							risplDkMrchPosDpt = (RisplDkMrchPosDpt) mrgObj;
							em.persist(risplDkMrchPosDpt);
						}
					} else if (mrgObj instanceof RisplDkMrchGrp) {
						RisplDkMrchGrp risplDkMrchGrp = (RisplDkMrchGrp) mrgObj;
						risplDkMrchGrp = em.find(RisplDkMrchGrp.class, risplDkMrchGrp.getId());
						if (risplDkMrchGrp != null) {
							risplDkMrchGrp = (RisplDkMrchGrp) mrgObj;
							em.merge(risplDkMrchGrp);
						} else {
							risplDkMrchGrp = (RisplDkMrchGrp) mrgObj;
							em.persist(risplDkMrchGrp);
						}
					} else if (mrgObj instanceof RisplDkMrchDpt) {
						RisplDkMrchDpt risplDkMrchDpt = (RisplDkMrchDpt) mrgObj;
						risplDkMrchDpt = em.find(RisplDkMrchDpt.class, risplDkMrchDpt.getId());
						if (risplDkMrchDpt != null) {
							risplDkMrchDpt = (RisplDkMrchDpt) mrgObj;
							em.merge(risplDkMrchDpt);
						} else {
							risplDkMrchDpt = (RisplDkMrchDpt) mrgObj;
							em.persist(risplDkMrchDpt);
						}
					} else if (mrgObj instanceof RisplDkMrchHier) {
						RisplDkMrchHier risplDkMrchHier = (RisplDkMrchHier) mrgObj;
						risplDkMrchHier = em.find(RisplDkMrchHier.class, risplDkMrchHier.getId());
						if (risplDkMrchHier != null) {
							risplDkMrchHier = (RisplDkMrchHier) mrgObj;
							em.merge(risplDkMrchHier);
						} else {
							risplDkMrchHier = (RisplDkMrchHier) mrgObj;
							em.persist(risplDkMrchHier);
						}
					} else if (mrgObj instanceof RisplDkMrchLvl) {
						RisplDkMrchLvl risplDkMrchLvl = (RisplDkMrchLvl) mrgObj;
						risplDkMrchLvl = em.find(RisplDkMrchLvl.class, risplDkMrchLvl.getId());
						if (risplDkMrchLvl != null) {
							risplDkMrchLvl = (RisplDkMrchLvl) mrgObj;
							em.merge(risplDkMrchLvl);
						} else {
							risplDkMrchLvl = (RisplDkMrchLvl) mrgObj;
							em.persist(risplDkMrchLvl);
						}
					} else if (mrgObj instanceof RisplDkMrchAssn) {
						RisplDkMrchAssn risplDkMrchAssn = (RisplDkMrchAssn) mrgObj;
						risplDkMrchAssn = em.find(RisplDkMrchAssn.class, risplDkMrchAssn.getId());
						if (risplDkMrchAssn != null) {
							risplDkMrchAssn = (RisplDkMrchAssn) mrgObj;
							em.merge(risplDkMrchAssn);
						} else {
							risplDkMrchAssn = (RisplDkMrchAssn) mrgObj;
							em.persist(risplDkMrchAssn);
						}
					}
				}
			}else if(mergeEntities.size() > 0 && merchandiseHierarchy.getFillType() != null
					&& merchandiseHierarchy.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)){
				for (Object mrgObj : mergeEntities) {
					em.persist(mrgObj);
				}
			}
			em.getTransaction().commit();
			LOGGER.info("Merchandise XML File Processed Successfully ...........");
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

	}

	public static void readXmlAndPersist2(InputStream inputStream, EntityManager em) throws Exception {
		try {
			LOGGER.info("Processing Merchandise XML File...........");
			em.getTransaction().begin();

			JAXBContext jaxbContext = JAXBContext.newInstance(MerchandiseHierarchy.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			MerchandiseHierarchy merchandiseHierarchy = (MerchandiseHierarchy) jaxbUnmarshaller.unmarshal(inputStream);

			if (merchandiseHierarchy.getFillType() != null
					&& merchandiseHierarchy.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkMrchPosDpt e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkMrchGrp e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkMrchDpt e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkMrchHier e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkMrchLvl e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkMrchAssn e").executeUpdate();
				em.getTransaction().commit();
				em.getTransaction().begin();
			}

			// Preload Data START

			PreloadDataType preloadDataType = merchandiseHierarchy.getPreloadData();
			List<MerchandiseGroupType> merchandiseGroupTypes = preloadDataType.getMerchandiseGroup();
			List<POSDepartmentType> posDepartmentTypes = preloadDataType.getPOSDepartment();

			for (POSDepartmentType posDepartmentType : posDepartmentTypes) {

				List<LocalizedPOSDepartmentNameType> localizedPOSDepartmentNameTypes = posDepartmentType
						.getPOSDepartmentName();
				if (localizedPOSDepartmentNameTypes != null && localizedPOSDepartmentNameTypes.size() > 0) {
					for (LocalizedPOSDepartmentNameType localizedPOSDepartmentNameType : localizedPOSDepartmentNameTypes) {

						// Insert to RISPL_DK_MRCH_DPT table - START
						RisplDkMrchDpt risplDkMrchDpt = new RisplDkMrchDpt();
						RisplDkMrchDptPK risplDkMrchDptPK = new RisplDkMrchDptPK();

						risplDkMrchDptPK.setIdDptPos(posDepartmentType.getPOSDepartmentID());
						if (localizedPOSDepartmentNameType.getLanguageCode() != null) {
							risplDkMrchDptPK.setLcl(localizedPOSDepartmentNameType.getLanguageCode().value());
						} else {
							risplDkMrchDptPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);
						}

						risplDkMrchDpt.setId(risplDkMrchDptPK);
						risplDkMrchDpt.setIdDptPosPrnt(posDepartmentType.getParentPOSDepartmentID());
						risplDkMrchDpt.setIdGpTx(posDepartmentType.getDepartmentDefaultTaxGroup() + "");
						risplDkMrchDpt.setNmDptPos(localizedPOSDepartmentNameType.getText());

						if (posDepartmentType.getChangeType() != null
								&& posDepartmentType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							risplDkMrchDpt = em.find(RisplDkMrchDpt.class, risplDkMrchDpt.getId());
							if (risplDkMrchDpt != null) {
								em.remove(risplDkMrchDpt);
							}
						} else {
							em.merge(risplDkMrchDpt);
						}

						// Insert to RISPL_DK_MRCH_DPT table - END

					}
				}

				// Insert to RISPL_DK_MRCH_POS_DPT table START

				List<RetailStorePOSDepartmentType> retailStorePOSDepartmentTypes = posDepartmentType
						.getRetailStorePOSDepartment();
				for (RetailStorePOSDepartmentType retailStorePOSDepartmentType : retailStorePOSDepartmentTypes) {
					RisplDkMrchPosDpt risplDkMrchPosDpt = new RisplDkMrchPosDpt();
					RisplDkMrchPosDptPK risplDkMrchPosDptPK = new RisplDkMrchPosDptPK();

					risplDkMrchPosDptPK.setPosDptId(posDepartmentType.getPOSDepartmentID());
					risplDkMrchPosDptPK.setRtStrId(retailStorePOSDepartmentType.getRetailStoreId());

					risplDkMrchPosDpt.setCdEntSrt(new BigDecimal(retailStorePOSDepartmentType.getListSortIndex()));
					if (retailStorePOSDepartmentType.getDefaultEntryCode().equalsIgnoreCase("true")) {
						risplDkMrchPosDpt.setDfltEntCdFlg("1");
					} else {
						risplDkMrchPosDpt.setDfltEntCdFlg("0");
					}

					if (retailStorePOSDepartmentType.isEnabledFlag()) {
						risplDkMrchPosDpt.setFlCdEntEnab("1");
					} else {
						risplDkMrchPosDpt.setFlCdEntEnab("0");
					}
					risplDkMrchPosDpt.setId(risplDkMrchPosDptPK);

					if (posDepartmentType.getChangeType() != null
							&& posDepartmentType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						risplDkMrchPosDpt = em.find(RisplDkMrchPosDpt.class, risplDkMrchPosDpt.getId());
						if (risplDkMrchPosDpt != null) {
							em.remove(risplDkMrchPosDpt);
						}
					} else {
						em.merge(risplDkMrchPosDpt);
					}
				}

				// Insert to RISPL_DK_MRCH_POS_DPT table END

			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			// Insert to RISPL_DK_MRCH_GRP table START
			for (MerchandiseGroupType merchandiseGroupType : merchandiseGroupTypes) {

				List<LocalizedNameDescriptionType> localizedNameDescriptionTypes = merchandiseGroupType
						.getLocalizedNameDescription();

				if (localizedNameDescriptionTypes != null && localizedNameDescriptionTypes.size() > 0) {
					for (LocalizedNameDescriptionType localizedNameDescriptionType : localizedNameDescriptionTypes) {
						RisplDkMrchGrp risplDkMrchGrp = new RisplDkMrchGrp();
						RisplDkMrchGrpPK risplDkMrchGrpPK = new RisplDkMrchGrpPK();

						risplDkMrchGrpPK.setIdMrhrcGp(merchandiseGroupType.getID());
						risplDkMrchGrpPK.setLcl(localizedNameDescriptionType.getLanguage().value());

						risplDkMrchGrp.setDeMrhrcGp(merchandiseGroupType.getDescription());
						risplDkMrchGrp.setId(risplDkMrchGrpPK);
						if (merchandiseGroupType.getID() != null
								&& !merchandiseGroupType.getID().equalsIgnoreCase("")) {
							risplDkMrchGrp.setIdPst(new BigDecimal(merchandiseGroupType.getID()));
						}
						risplDkMrchGrp.setNmMrhrcGp(merchandiseGroupType.getName());
						risplDkMrchGrp.setTsCrtRcrd(timestamp);
						risplDkMrchGrp.setTsMdfRcrd(timestamp);

						if (merchandiseGroupType.getChangeType() != null && merchandiseGroupType.getChangeType().value()
								.equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							risplDkMrchGrp = em.find(RisplDkMrchGrp.class, risplDkMrchGrp.getId());
							if (risplDkMrchGrp != null) {
								em.remove(risplDkMrchGrp);
							}
						} else {
							em.merge(risplDkMrchGrp);
						}
					}
				} else {
					RisplDkMrchGrp risplDkMrchGrp = new RisplDkMrchGrp();
					RisplDkMrchGrpPK risplDkMrchGrpPK = new RisplDkMrchGrpPK();

					risplDkMrchGrpPK.setIdMrhrcGp(merchandiseGroupType.getID());
					risplDkMrchGrpPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

					risplDkMrchGrp.setDeMrhrcGp(merchandiseGroupType.getDescription());
					risplDkMrchGrp.setId(risplDkMrchGrpPK);
					if (merchandiseGroupType.getMerchantID() != null) {
						risplDkMrchGrp.setIdPst(new BigDecimal(merchandiseGroupType.getMerchantID()));
					}
					risplDkMrchGrp.setNmMrhrcGp(merchandiseGroupType.getName());
					risplDkMrchGrp.setTsCrtRcrd(timestamp);
					risplDkMrchGrp.setTsMdfRcrd(timestamp);

					if (merchandiseGroupType.getChangeType() != null
							&& merchandiseGroupType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						risplDkMrchGrp = em.find(RisplDkMrchGrp.class, risplDkMrchGrp.getId());
						if (risplDkMrchGrp != null) {
							em.remove(risplDkMrchGrp);
						}
					} else {
						em.merge(risplDkMrchGrp);
					}
				}
			}
			// Insert to RISPL_DK_MRCH_GRP table END

			// Preload Data END

			timestamp = new Timestamp(System.currentTimeMillis());
			List<HierarchyListType> hierarchyListTypes = merchandiseHierarchy.getHierarchyList();
			for (HierarchyListType hierarchyListType : hierarchyListTypes) {
				List<HierarchyType> hierarchyTypes = hierarchyListType.getHierarchy();
				for (HierarchyType hierarchyType : hierarchyTypes) {

					// Insert to RISPL_DK_MRCH_HIER table - START

					List<LocalizedNameDescriptionType> localizedNameDescriptionTypes = hierarchyType.getLocalizedName();
					if (localizedNameDescriptionTypes != null && localizedNameDescriptionTypes.size() > 0) {
						for (LocalizedNameDescriptionType localizedNameDescriptionType : localizedNameDescriptionTypes) {
							RisplDkMrchHier risplDkMrchHier = new RisplDkMrchHier();
							RisplDkMrchHierPK risplDkMrchHierPK = new RisplDkMrchHierPK();

							risplDkMrchHierPK.setIdMrhrcFnc(hierarchyType.getFunctionID());
							risplDkMrchHierPK.setLcl(localizedNameDescriptionType.getLanguage().value());

							risplDkMrchHier.setId(risplDkMrchHierPK);
							risplDkMrchHier.setNmMrhrcFnc(localizedNameDescriptionType.getName());
							risplDkMrchHier.setTsCrtRcrd(timestamp);
							risplDkMrchHier.setTsMdfRcrd(timestamp);

							em.merge(risplDkMrchHier);

						}

					} else {
						RisplDkMrchHier risplDkMrchHier = new RisplDkMrchHier();
						RisplDkMrchHierPK risplDkMrchHierPK = new RisplDkMrchHierPK();

						risplDkMrchHierPK.setIdMrhrcFnc(hierarchyType.getFunctionID());
						risplDkMrchHierPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

						risplDkMrchHier.setId(risplDkMrchHierPK);
						risplDkMrchHier.setNmMrhrcFnc(hierarchyType.getName());
						risplDkMrchHier.setTsCrtRcrd(timestamp);
						risplDkMrchHier.setTsMdfRcrd(timestamp);

						em.merge(risplDkMrchHier);
					}

					// Insert to RISPL_DK_MRCH_HIER table - END

					// Insert to RISPL_DK_MRCH_LVL table - START
					timestamp = new Timestamp(System.currentTimeMillis());
					LevelListType levelListType = hierarchyType.getLevelList();
					List<LevelType> levelTypes = levelListType.getLevel();
					for (LevelType levelType : levelTypes) {

						List<LocalizedNameDescriptionType> localizedNameDescriptionTypes2 = levelType
								.getLocalizedName();
						if (localizedNameDescriptionTypes2 != null && localizedNameDescriptionTypes2.size() > 0) {
							for (LocalizedNameDescriptionType localizedNameDescriptionType2 : localizedNameDescriptionTypes2) {
								RisplDkMrchLvl risplDkMrchLvl = new RisplDkMrchLvl();
								RisplDkMrchLvlPK risplDkMrchLvlPK = new RisplDkMrchLvlPK();

								risplDkMrchLvlPK.setIdMrhrcLv(levelType.getID());
								risplDkMrchLvlPK.setLcl(localizedNameDescriptionType2.getLanguage().value());

								risplDkMrchLvl.setId(risplDkMrchLvlPK);
								risplDkMrchLvl.setIdMrhrcFnc(new BigDecimal(hierarchyType.getFunctionID()));
								if (levelType.getParentID() != null) {
									risplDkMrchLvl.setIdMrhrcLvPrnt(new BigDecimal(levelType.getParentID()));
								}

								risplDkMrchLvl.setNmMrhrcLv(localizedNameDescriptionType2.getName());
								risplDkMrchLvl.setTsCrtRcrd(timestamp);
								risplDkMrchLvl.setTsMdfRcrd(timestamp);

								em.merge(risplDkMrchLvl);

							}
						} else {

							RisplDkMrchLvl risplDkMrchLvl = new RisplDkMrchLvl();
							RisplDkMrchLvlPK risplDkMrchLvlPK = new RisplDkMrchLvlPK();

							risplDkMrchLvlPK.setIdMrhrcLv(levelType.getID());
							risplDkMrchLvlPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

							risplDkMrchLvl.setId(risplDkMrchLvlPK);
							risplDkMrchLvl.setIdMrhrcFnc(new BigDecimal(hierarchyType.getFunctionID()));
							if (levelType.getParentID() != null) {
								risplDkMrchLvl.setIdMrhrcLvPrnt(new BigDecimal(levelType.getParentID()));
							}

							risplDkMrchLvl.setNmMrhrcLv(levelType.getName());
							risplDkMrchLvl.setTsCrtRcrd(timestamp);
							risplDkMrchLvl.setTsMdfRcrd(timestamp);

							em.merge(risplDkMrchLvl);
						}

					}
					// Insert to RISPL_DK_MRCH_LVL table - END

					// Insert to RISPL_DK_MRCH_ASSN table - START
					timestamp = new Timestamp(System.currentTimeMillis());
					NodeListType nodeListType = hierarchyType.getNodeList();
					List<NodeType> nodeTypes = nodeListType.getNode();
					for (NodeType nodeType : nodeTypes) {

						RisplDkMrchAssn risplDkMrchAssn = new RisplDkMrchAssn();
						RisplDkMrchAssnPK risplDkMrchAssnPK = new RisplDkMrchAssnPK();

						risplDkMrchAssnPK.setIdMrhrcGpChld(nodeType.getID());
						if (nodeType.getParentNodeID() == null) {
							risplDkMrchAssnPK.setIdMrhrcGpPrnt(nodeType.getID());
						} else {
							risplDkMrchAssnPK.setIdMrhrcGpPrnt(nodeType.getParentNodeID());
						}

						risplDkMrchAssn.setId(risplDkMrchAssnPK);
						risplDkMrchAssn.setIdMrhrcFnc(new BigDecimal(hierarchyType.getFunctionID()));
						risplDkMrchAssn.setIdMrhrcLv(new BigDecimal(nodeType.getLevelID()));
						risplDkMrchAssn.setTsCrtRcrd(timestamp);
						risplDkMrchAssn.setTsMdfRcrd(timestamp);

						em.merge(risplDkMrchAssn);

					}
					// Insert to RISPL_DK_MRCH_ASSN table - START

				}
			}

			em.getTransaction().commit();
			// em.close();
			LOGGER.info("Merchandise XML File Processed Successfully ...........");
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

	}
}
