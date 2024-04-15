package rispl.dime.schedular.imports;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.item.RisplDkItemAttr;
import rispl.db.model.item.RisplDkItemAttrPK;
import rispl.db.model.item.RisplDkItemColor;
import rispl.db.model.item.RisplDkItemColorPK;
import rispl.db.model.item.RisplDkItemMsg;
import rispl.db.model.item.RisplDkItemMsgPK;
import rispl.db.model.item.RisplDkItemMstr;
import rispl.db.model.item.RisplDkItemMstrPK;
import rispl.db.model.item.RisplDkItemRltdItm;
import rispl.db.model.item.RisplDkItemRltdItmPK;
import rispl.db.model.item.RisplDkItemSize;
import rispl.db.model.item.RisplDkItemSizePK;
import rispl.db.model.item.RisplDkItemStyle;
import rispl.db.model.item.RisplDkItemStylePK;
import rispl.db.model.item.pricing.RisplDkItemPrice;
import rispl.db.model.item.pricing.RisplDkItemPricePK;
import rispl.jaxb.item.ColorType;
import rispl.jaxb.item.DisplayMessageType;
import rispl.jaxb.item.ItemImport;
import rispl.jaxb.item.ItemType;
import rispl.jaxb.item.LocalizedDescriptionType;
import rispl.jaxb.item.LocalizedItemSizeDescriptionType;
import rispl.jaxb.item.LocalizedMessageDescriptionType;
import rispl.jaxb.item.LocalizedNameDescriptionType;
import rispl.jaxb.item.LocalizedNameType;
import rispl.jaxb.item.POSIdentityType;
import rispl.jaxb.item.PreloadDataType;
import rispl.jaxb.item.RegularPriceType;
import rispl.jaxb.item.RelatedItemAssociationType;
import rispl.jaxb.item.RetailStoreItemType;
import rispl.jaxb.item.SizeType;
import rispl.jaxb.item.StyleType;
import utility.ConfigUtils;

public class RisplItemImport {

	private static final Logger LOGGER = LogManager.getLogger(RisplItemImport.class);
	private static final String itemPriceQuery = "SELECT risplDkItemPrice FROM RisplDkItemPrice risplDkItemPrice WHERE risplDkItemPrice.itmId=:itmId AND risplDkItemPrice.evntTyp=:evntTyp";
	private static final String itemPriceQueryIN = "SELECT risplDkItemPrice FROM RisplDkItemPrice risplDkItemPrice WHERE risplDkItemPrice.evntTyp=:evntTyp AND risplDkItemPrice.id.rtStrId=:strid";
	public static void readItemXml(InputStream inputStream, EntityManager em) throws Exception {
	final int maxCommitSize = 1000;
		try {
			LOGGER.info("Processing Item XML file..............");

			JAXBContext jaxbContext = JAXBContext.newInstance(ItemImport.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ItemImport itmImprts = (ItemImport) jaxbUnmarshaller.unmarshal(inputStream);

			List<Object> removeEntities = new ArrayList<Object>();
			List<Object> mergeEntities = new ArrayList<Object>();
			
			List<RisplDkItemPrice> removeEntitiesItmPrc = new ArrayList<RisplDkItemPrice>();
			List<RisplDkItemPrice> mergeEntitiesItmPrc = new ArrayList<RisplDkItemPrice>();
			// Preload Data - Start

			PreloadDataType preloadData = itmImprts.getPreloadData();

			// Color START
			List<ColorType> colors = preloadData.getColor();
			for (ColorType color : colors) {
				RisplDkItemColor risplDkItemColor = new RisplDkItemColor();
				RisplDkItemColorPK risplDkItemColorPK = new RisplDkItemColorPK();
				risplDkItemColorPK.setItmClrCd(color.getCode());

				List<LocalizedNameDescriptionType> localized = color.getLocalizedNameDescription();
				if (localized != null && localized.size() > 0) {
					for (LocalizedNameDescriptionType local : localized) {
						risplDkItemColorPK.setLcl(local.getLanguage().name());
						risplDkItemColor.setId(risplDkItemColorPK);

						risplDkItemColor.setClrNm(local.getName());
						risplDkItemColor.setClrDesc(local.getDescription());

						if (color.getChangeType() != null
								&& color.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							removeEntities.add(risplDkItemColor);
						} else {
							mergeEntities.add(risplDkItemColor);
						}

					}
				} else {

					risplDkItemColorPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);
					risplDkItemColor.setId(risplDkItemColorPK);

					risplDkItemColor.setClrNm(color.getNames());
					risplDkItemColor.setClrDesc(color.getDescription());

					if (color.getChangeType() != null
							&& color.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						// call delet method
						removeEntities.add(risplDkItemColor);
					} else {
						mergeEntities.add(risplDkItemColor);
					}
				}
			}

			// Color END

			// Size START
			List<SizeType> sizes = preloadData.getSize();
			for (SizeType size : sizes) {
				RisplDkItemSize risplDkItemSize = new RisplDkItemSize();
				RisplDkItemSizePK risplDkItemSizePK = new RisplDkItemSizePK();

				risplDkItemSizePK.setItmSzCd(size.getCode());

				List<LocalizedItemSizeDescriptionType> localizedDesc = size.getLocalizedSizeData();
				if (localizedDesc != null && localizedDesc.size() > 0) {

					for (LocalizedItemSizeDescriptionType local : localizedDesc) {
						risplDkItemSizePK.setLcl(local.getLanguage().value());

						risplDkItemSize.setId(risplDkItemSizePK);

						risplDkItemSize.setActSzCd(size.getActualSizeCode());
						risplDkItemSize.setActSzPrptnDesc(size.getProportionDesc());
						risplDkItemSize.setActSzTypDesc(size.getTypeDesc());

						if (size.getChangeType() != null
								&& size.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							removeEntities.add(risplDkItemSize);
						} else {
							mergeEntities.add(risplDkItemSize);
						}

					}

				} else {

					risplDkItemSizePK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

					risplDkItemSize.setId(risplDkItemSizePK);

					risplDkItemSize.setActSzCd(size.getActualSizeCode());
					risplDkItemSize.setActSzPrptnDesc(size.getProportionDesc());
					risplDkItemSize.setActSzTypDesc(size.getTypeDesc());

					if (size.getChangeType() != null
							&& size.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						removeEntities.add(risplDkItemSize);
					} else {
						mergeEntities.add(risplDkItemSize);
					}
				}

			}
			// Size END

			// Style START
			List<StyleType> styleTypes = preloadData.getStyle();
			for (StyleType styleType : styleTypes) {
				RisplDkItemStyle risplDkItemStyle = new RisplDkItemStyle();
				RisplDkItemStylePK risplDkItemStylePK = new RisplDkItemStylePK();

				risplDkItemStylePK.setItmStylCd(styleType.getCode());

				List<LocalizedNameDescriptionType> localized = styleType.getLocalizedNameDescription();
				if (localized != null && localized.size() > 0) {

					for (LocalizedNameDescriptionType local : localized) {

						risplDkItemStylePK.setLcl(local.getLanguage().value());

						risplDkItemStyle.setId(risplDkItemStylePK);
						risplDkItemStyle.setStylNm(styleType.getName());
						risplDkItemStyle.setStylDesc(styleType.getDescription());

						if (styleType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							removeEntities.add(risplDkItemStyle);
						} else {
							mergeEntities.add(risplDkItemStyle);
						}
					}

				} else {

					risplDkItemStylePK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

					risplDkItemStyle.setId(risplDkItemStylePK);
					risplDkItemStyle.setStylNm(styleType.getName());
					risplDkItemStyle.setStylDesc(styleType.getDescription());

					if (styleType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						removeEntities.add(risplDkItemStyle);
					} else {
						mergeEntities.add(risplDkItemStyle);
					}
				}
			}
			// Style END

			// Item Messages START
			List<DisplayMessageType> displayMessageTypes = preloadData.getMessage();
			for (DisplayMessageType displayMessageType : displayMessageTypes) {

				List<LocalizedMessageDescriptionType> localizedmessages = displayMessageType.getMsgText();
				if (localizedmessages != null && localizedmessages.size() > 0) {
					for (LocalizedMessageDescriptionType localizedmessage : localizedmessages) {
						RisplDkItemMsg risplDkItemMsg = new RisplDkItemMsg();
						RisplDkItemMsgPK risplDkItemMsgPK = new RisplDkItemMsgPK();

						risplDkItemMsgPK.setDispMsgId(displayMessageType.getID());
						risplDkItemMsgPK.setLcl(localizedmessage.getLanguage().value());

						risplDkItemMsg.setId(risplDkItemMsgPK);
						risplDkItemMsg.setNmMsgDply(localizedmessage.getName());
						risplDkItemMsg.setNaMsgDply(localizedmessage.getValue());
						if (displayMessageType.getChangeType() != null
								&& displayMessageType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							removeEntities.add(risplDkItemMsg);
						} else {
							mergeEntities.add(risplDkItemMsg);
						}
					}
				}

			}
			// Item Messages END

			// Items START
			List<String> itemIds = new ArrayList<String>();
			String eventId = "";
			List<ItemType> itemTypes = itmImprts.getItem();
			int counter = 0;
			for (ItemType itemType : itemTypes) {
				if(!itemIds.contains(itemType.getID())){
					itemIds.add(itemType.getID());
				}
				// For all stores insert Item details
				List<RetailStoreItemType> retailStoreItemTypes = itemType.getRetailStoreItem();
				for (RetailStoreItemType retailStoreItemType : retailStoreItemTypes) {

					List<String> stores = retailStoreItemType.getRetailStoreID();

					for (String store : stores) {
						// Insert Initial Price of Item in to
						// RISPL_DK_ITEM_PRICE table - START
						List<RegularPriceType> priceTypes = retailStoreItemType.getRegularPrice();
						if (priceTypes != null && priceTypes.size() > 0) {
							counter++;
							RisplDkItemPrice risplDkItemPrice = new RisplDkItemPrice();
							RisplDkItemPricePK risplDkItemPricePK = new RisplDkItemPricePK();
							Date today = new Date();
							eventId = today.getTime()+""+counter;
							risplDkItemPricePK.setEvntId(Long.parseLong(eventId));

							risplDkItemPricePK.setRtStrId(store);

							risplDkItemPrice.setItmId(itemType.getID());
							risplDkItemPrice.setEvntTyp("IIP");
							risplDkItemPrice.setEvPri(new BigDecimal(1));
							risplDkItemPrice.setSlUnAmt(priceTypes.get(0).getValue());
							risplDkItemPrice.setEvEfTmp(new Timestamp(System.currentTimeMillis()));

							risplDkItemPrice.setId(risplDkItemPricePK);

							if (itemType.getChangeType() != null
									&& itemType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
								removeEntitiesItmPrc.add(risplDkItemPrice);
							} else {
								mergeEntitiesItmPrc.add(risplDkItemPrice);
							}
						}
						// Insert Initial Price of Item in to
						// RISPL_DK_ITEM_PRICE table - END

						// Insert to RISPL_DK_ITEM_MSTR table -START
						RisplDkItemMstr risDkItemMstr = new RisplDkItemMstr();
						RisplDkItemMstrPK risDkItemMstrPK = new RisplDkItemMstrPK();

						risDkItemMstrPK.setRtStrId(store);
						risDkItemMstrPK.setItmId(itemType.getID());
						List<POSIdentityType> posIdentityType = retailStoreItemType.getPOSIdentity();
						if (posIdentityType != null && posIdentityType.size() > 0) {
							POSIdentityType identity = posIdentityType.get(0);
							risDkItemMstrPK.setIdItmPos(identity.getPOSItemID());

							// Insert to RISPL_DK_ITEM_ATTR table - START
							RisplDkItemAttr risplDkItemAttr = new RisplDkItemAttr();
							RisplDkItemAttrPK risplDkItemAttrPK = new RisplDkItemAttrPK();

							risplDkItemAttrPK.setRtStrId(store);
							risplDkItemAttrPK.setItmId(itemType.getID());
							risplDkItemAttrPK.setIdItmPos(identity.getPOSItemID());

							risplDkItemAttr.setEmpDisAlwFlg(getFormattedBoolean(identity.isEmployeeDiscountAllowed()));

							risplDkItemAttr.setId(risplDkItemAttrPK);
							risplDkItemAttr.setItmMnmSlQnt(identity.getMinimumSaleUnitCount());
							risplDkItemAttr.setItmMxmSlQnt(identity.getMaximumSaleUnitCount());

							risplDkItemAttr.setItmRstkFeFlg(getFormattedBoolean(itemType.isRestockingFee()));

							risplDkItemAttr.setItmUomCd(itemType.getUOMCode());

							risplDkItemAttr.setPrcEntrRqFlg(getFormattedBoolean(identity.isPriceEntryRequired()));

							risplDkItemAttr.setPrhRtnFl(getFormattedBoolean(identity.isReturnable()));

							risplDkItemAttr.setRtPrcMdfrFlg(getFormattedBoolean(identity.isPriceModifiable()));

							risplDkItemAttr.setSlsUomCd(itemType.getUOMCode());

							risplDkItemAttr.setSpoElgFlg(getFormattedBoolean(identity.isSpecialOrderEligible()));

							/*
							 * if(identity.is){
							 * risplDkItemAttr.setWhtEntrRqFlg(trueInd); }else{
							 * risplDkItemAttr.setWhtEntrRqFlg(falseInd); }
							 */
							if (priceTypes != null && priceTypes.size() > 0) {
								risplDkItemAttr.setRpPrcCmprAtSls(ConfigUtils.getInstance()
										.createBigDecimal(priceTypes.get(0).getCompareAtPrice(), "format.currency"));
							}

							if (retailStoreItemType.getTaxGroup() != null) {
								risplDkItemAttr.setTxGpId(new BigDecimal(retailStoreItemType.getTaxGroup()));
							}

							if (retailStoreItemType.getAgeRestrictionId() != null) {
								risplDkItemAttr
										.setSlsAgRstIdn(new BigDecimal(retailStoreItemType.getAgeRestrictionId()));
							}

							risplDkItemAttr.setLbTmpltId(retailStoreItemType.getTemplateId());

							if (itemType.getChangeType() != null
									&& itemType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
								removeEntities.add(risplDkItemAttr);
							} else {
								mergeEntities.add(risplDkItemAttr);
							}
							// Insert to RISPL_DK_ITEM_ATTR table - END
						}

						risDkItemMstr.setId(risDkItemMstrPK);
						// risDkItemMstr.setDispMsgId(itemType.);
						// risDkItemMstr.setItmBrnNm();
						risDkItemMstr.setItmClrCd(itemType.getColor());

						List<LocalizedDescriptionType> localizedDesc = itemType.getLongDescription();
						if (localizedDesc != null && localizedDesc.size() > 0) {
							risDkItemMstr.setItmDesc(localizedDesc.get(0).getValue());
						}

						risDkItemMstr.setItmDmgDscFlg(getFormattedBoolean(itemType.isDamageDiscountable()));
						risDkItemMstr.setItmDscFlg(getFormattedBoolean(itemType.isDiscountable()));

						// risDkItemMstr.setItmImgLoc();

						// risDkItemMstr.setItmKtId();
						risDkItemMstr.setItmKtStCd(itemType.getKitSetCode());

						// risDkItemMstr.setItmMfId();

						if (itemType.getMerchandiseHierarchy() != null) {
							risDkItemMstr.setItmMrcHrcLvCd(itemType.getMerchandiseHierarchy().getLevel());
							risDkItemMstr.setItmMrhrcGpId(itemType.getMerchandiseHierarchy().getValue());
						}

						if (itemType.getMerchandiseHierarchy() != null
								&& itemType.getMerchandiseHierarchy().getStructureID() != null) {
							risDkItemMstr.setItmMrcStrcId(
									new BigDecimal(itemType.getMerchandiseHierarchy().getStructureID()));
						}

						// risDkItemMstr.setItmMrhrcGpId(itemType.get);

						risDkItemMstr.setItmPosDptId(itemType.getPOSDepartmentID());

						risDkItemMstr.setItmRgstryFl(getFormattedBoolean(itemType.isRegistryEligible()));

						// risDkItemMstr.setItmSbstIdnFlg(itemType.is);

						List<LocalizedNameType> localizedNameDesc = itemType.getShortName();
						if (localizedNameDesc != null && localizedNameDesc.size() > 0) {
							risDkItemMstr.setItmShrtDesc(localizedNameDesc.get(0).getValue());
						}

						risDkItemMstr.setItmSlsAznFlg(getFormattedBoolean(itemType.isAuthorizedForSale()));

						risDkItemMstr.setItmSrlzdFlg(getFormattedBoolean(itemType.isSerializedItem()));

						risDkItemMstr.setItmSrzCptTmCd(itemType.getUINCaptureTime());

						risDkItemMstr.setItmSzCd(itemType.getSize());

						risDkItemMstr.setItmSzReqFlg(getFormattedBoolean(itemType.isSizeRequired()));

						risDkItemMstr.setItmTxExmCd(getFormattedBoolean(itemType.isTaxable()));

						if (itemType.getTaxGroup() != null) {
							risDkItemMstr.setItmTxGpId(new BigDecimal(itemType.getTaxGroup()));
						}

						risDkItemMstr.setItmTyCd(itemType.getType());

						risDkItemMstr.setItmRcrdCrtTs(new Timestamp(System.currentTimeMillis()));
						risDkItemMstr.setItmRcrdMdfTs(new Timestamp(System.currentTimeMillis()));

						if (itemType.getChangeType() != null
								&& itemType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							removeEntities.add(risDkItemMstr);
						} else {
							mergeEntities.add(risDkItemMstr);
						}
						// Insert to RISPL_DK_ITEM_MSTR table -END

					}
				}
				// Related Items insert to RISPL_DK_ITEM_RLTD_ITMS Table - START

				List<RelatedItemAssociationType> relatedItems = itemType.getRelatedItemAssociation();
				if (relatedItems != null) {
					for (RelatedItemAssociationType relatedItem : relatedItems) {
						RisplDkItemRltdItm risplDkItemRltdItm = new RisplDkItemRltdItm();
						RisplDkItemRltdItmPK risplDkItemRltdItmPK = new RisplDkItemRltdItmPK();

						risplDkItemRltdItmPK.setItmId(itemType.getID());
						risplDkItemRltdItmPK.setRltdItmId(relatedItem.getRelatedItemID());
						risplDkItemRltdItmPK.setRltdItmTypCd(relatedItem.getTypeCode());

						risplDkItemRltdItm.setId(risplDkItemRltdItmPK);
						risplDkItemRltdItm.setRmvRltdItmFlg(getFormattedBoolean(relatedItem.isRemoveAllowed()));
						risplDkItemRltdItm.setRtnRltdItmFlg(getFormattedBoolean(relatedItem.isReturnAllowed()));

						if (relatedItem.getChangeType() != null
								&& relatedItem.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							removeEntities.add(risplDkItemRltdItm);
						} else {
							mergeEntities.add(risplDkItemRltdItm);
						}
					}
				}
				// Related Items - END
			}
			// Items END
			
			Query prcquery = em.createQuery(itemPriceQueryIN, RisplDkItemPrice.class);
			prcquery.setParameter("evntTyp","IIP");
			prcquery.setParameter("strid",mergeEntitiesItmPrc.get(0).getId().getRtStrId());
			List<RisplDkItemPrice> priceResults = prcquery.getResultList();
			List<RisplDkItemPrice> filteredList = priceResults.parallelStream().filter(priceResult->itemIds.contains(priceResult.getItmId())).collect(Collectors.toList());
			Map<String,Long> itmEvntid = filteredList.parallelStream().collect(Collectors.toMap(x -> x.getItmId(), x -> x.getId().getEvntId()));
			
			List<RisplDkItemPrice> notPresent = new ArrayList<RisplDkItemPrice>();
			for(RisplDkItemPrice rmv : removeEntitiesItmPrc){
				if(itmEvntid.containsKey(rmv.getItmId())){
					rmv.getId().setEvntId(itmEvntid.get(rmv.getItmId()));
				}else{
					notPresent.add(rmv);
				}
			}
			
			removeEntitiesItmPrc.removeAll(notPresent);
			notPresent.clear();
			
			List<RisplDkItemPrice> persistPrice = new ArrayList<RisplDkItemPrice>();
			for(RisplDkItemPrice mrg : mergeEntitiesItmPrc){
				if(itmEvntid.containsKey(mrg.getItmId())){
					mrg.getId().setEvntId(itmEvntid.get(mrg.getItmId()));
				}else{
					persistPrice.add(mrg);
				}
			}
			mergeEntitiesItmPrc.removeAll(persistPrice);
			
			// Perform DB Operations
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			
			// If KillAndFill Delete data from all tables
			if (itmImprts.getFillType() != null
					&& itmImprts.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkItemColor e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemSize e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemStyle e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemMsg e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemPrice e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemMstr e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemAttr e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemRltdItm e").executeUpdate();
			}

			// Remove Records if not KillAndFill
			int count = 0;
			if (removeEntities.size() > 0 && itmImprts.getFillType() != null
					&& !itmImprts.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object remObj : removeEntities) {
						em.remove(remObj);
						count++;
						if (count >= maxCommitSize) {
							commit(em);
							count=0;
						}
				}
				commit(em);
				count=0;
			}

			// If not KillAndFill if record exists merge or else persist and If
			// KillAndFill persist all records
			if (mergeEntities.size() > 0 && itmImprts.getFillType() != null
					&& !itmImprts.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				int totCount=0;
				for (Object mrgObj : mergeEntities) {
						em.merge(mrgObj);
					count++;
					totCount++;
					if (count >= maxCommitSize) {
						commit(em);
						count=0;
					}
				}

			} else if (mergeEntities.size() > 0 && itmImprts.getFillType() != null
					&& itmImprts.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object prsstObj : mergeEntities) {
					em.persist(prsstObj);
					count++;
					if (count >= maxCommitSize) {
						commit(em);
						count=0;
					}
				}
			}
			
			if (itmImprts.getFillType() != null && itmImprts.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)){
				for(RisplDkItemPrice mrgPrice : mergeEntitiesItmPrc){
					em.persist(mrgPrice);
					count++;
					if (count >= maxCommitSize) {
						commit(em);
						count=0;
					}
				}
			}else{
				for(RisplDkItemPrice rmvPrice : removeEntitiesItmPrc){
					em.remove(rmvPrice);
					count++;
					if (count >= maxCommitSize) {
						commit(em);
						count=0;
					}
				}
				
				for(RisplDkItemPrice mrgPrice : mergeEntitiesItmPrc){
					em.merge(mrgPrice);
					count++;
					if (count >= maxCommitSize) {
						commit(em);
						count=0;
					}
				}
			}
			
			for(RisplDkItemPrice prsstPrc : persistPrice){
				em.persist(prsstPrc);
				count++;
				if (count >= maxCommitSize) {
					commit(em);
					count=0;
				}
			}
			
			em.getTransaction().commit();
			// em.close();
			LOGGER.info("Item XML file Processed Successfully ..............");

		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}
	}

	public static void readItemXml2(InputStream inputStream, EntityManager em) throws Exception {

		try {
			LOGGER.info("Processing Item XML file..............");
			em.getTransaction().begin();

			JAXBContext jaxbContext = JAXBContext.newInstance(ItemImport.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ItemImport itmImprts = (ItemImport) jaxbUnmarshaller.unmarshal(inputStream);

			// If KillAndFill Delete data from all tables
			if (itmImprts.getFillType() != null
					&& itmImprts.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkItemColor e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemSize e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemStyle e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemMsg e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemPrice e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemMstr e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemAttr e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkItemRltdItm e").executeUpdate();
				em.getTransaction().commit();
				em.getTransaction().begin();
			}

			// Preload Data - Start

			PreloadDataType preloadData = itmImprts.getPreloadData();

			// Color START
			List<ColorType> colors = preloadData.getColor();
			for (ColorType color : colors) {
				RisplDkItemColor risplDkItemColor = new RisplDkItemColor();
				RisplDkItemColorPK risplDkItemColorPK = new RisplDkItemColorPK();
				risplDkItemColorPK.setItmClrCd(color.getCode());

				List<LocalizedNameDescriptionType> localized = color.getLocalizedNameDescription();
				if (localized != null && localized.size() > 0) {
					for (LocalizedNameDescriptionType local : localized) {
						risplDkItemColorPK.setLcl(local.getLanguage().name());
						risplDkItemColor.setId(risplDkItemColorPK);

						risplDkItemColor.setClrNm(local.getName());
						risplDkItemColor.setClrDesc(local.getDescription());

						if (color.getChangeType() != null
								&& color.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							// call delet method
							risplDkItemColor = em.find(RisplDkItemColor.class, risplDkItemColor.getId());
							if (risplDkItemColor != null) {
								em.remove(risplDkItemColor);
							}
						} else {
							em.merge(risplDkItemColor);
						}

					}
				} else {

					risplDkItemColorPK.setLcl("en");
					risplDkItemColor.setId(risplDkItemColorPK);

					risplDkItemColor.setClrNm(color.getNames());
					risplDkItemColor.setClrDesc(color.getDescription());

					if (color.getChangeType() != null
							&& color.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						// call delet method
						risplDkItemColor = em.find(RisplDkItemColor.class, risplDkItemColor.getId());
						if (risplDkItemColor != null) {
							em.remove(risplDkItemColor);
						}
					} else {
						em.merge(risplDkItemColor);
					}
				}
			}

			// Color END

			// Size START
			List<SizeType> sizes = preloadData.getSize();
			for (SizeType size : sizes) {
				RisplDkItemSize risplDkItemSize = new RisplDkItemSize();
				RisplDkItemSizePK risplDkItemSizePK = new RisplDkItemSizePK();

				risplDkItemSizePK.setItmSzCd(size.getCode());

				List<LocalizedItemSizeDescriptionType> localizedDesc = size.getLocalizedSizeData();
				if (localizedDesc != null && localizedDesc.size() > 0) {

					for (LocalizedItemSizeDescriptionType local : localizedDesc) {
						risplDkItemSizePK.setLcl(local.getLanguage().value());

						risplDkItemSize.setId(risplDkItemSizePK);

						risplDkItemSize.setActSzCd(size.getActualSizeCode());
						risplDkItemSize.setActSzPrptnDesc(size.getProportionDesc());
						risplDkItemSize.setActSzTypDesc(size.getTypeDesc());

						if (size.getChangeType() != null
								&& size.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							// call delete method
							risplDkItemSize = em.find(RisplDkItemSize.class, risplDkItemSize.getId());
							if (risplDkItemSize != null) {
								em.remove(risplDkItemSize);
							}
						} else {
							em.merge(risplDkItemSize);
						}

					}

				} else {

					risplDkItemSizePK.setLcl("en");

					risplDkItemSize.setId(risplDkItemSizePK);

					risplDkItemSize.setActSzCd(size.getActualSizeCode());
					risplDkItemSize.setActSzPrptnDesc(size.getProportionDesc());
					risplDkItemSize.setActSzTypDesc(size.getTypeDesc());

					if (size.getChangeType() != null
							&& size.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						// call delete method
						risplDkItemSize = em.find(RisplDkItemSize.class, risplDkItemSize.getId());
						if (risplDkItemSize != null) {
							em.remove(risplDkItemSize);
						}
					} else {
						em.merge(risplDkItemSize);
					}
				}

			}
			// Size END

			// Style START
			List<StyleType> styleTypes = preloadData.getStyle();
			for (StyleType styleType : styleTypes) {
				RisplDkItemStyle risplDkItemStyle = new RisplDkItemStyle();
				RisplDkItemStylePK risplDkItemStylePK = new RisplDkItemStylePK();

				risplDkItemStylePK.setItmStylCd(styleType.getCode());

				List<LocalizedNameDescriptionType> localized = styleType.getLocalizedNameDescription();
				if (localized != null && localized.size() > 0) {

					for (LocalizedNameDescriptionType local : localized) {

						risplDkItemStylePK.setLcl(local.getLanguage().value());

						risplDkItemStyle.setId(risplDkItemStylePK);
						risplDkItemStyle.setStylNm(styleType.getName());
						risplDkItemStyle.setStylDesc(styleType.getDescription());

						if (styleType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							// call delete method
							risplDkItemStyle = em.find(RisplDkItemStyle.class, risplDkItemStyle.getId());
							if (risplDkItemStyle != null) {
								em.remove(risplDkItemStyle);
							}
						} else {
							em.merge(risplDkItemStyle);
						}
					}

				} else {

					risplDkItemStylePK.setLcl("en");

					risplDkItemStyle.setId(risplDkItemStylePK);
					risplDkItemStyle.setStylNm(styleType.getName());
					risplDkItemStyle.setStylDesc(styleType.getDescription());

					if (styleType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						// call delete method
						risplDkItemStyle = em.find(RisplDkItemStyle.class, risplDkItemStyle.getId());
						if (risplDkItemStyle != null) {
							em.remove(risplDkItemStyle);
						}
					} else {
						em.merge(risplDkItemStyle);
					}
				}
			}
			// Style END

			// Item Messages START
			List<DisplayMessageType> displayMessageTypes = preloadData.getMessage();
			for (DisplayMessageType displayMessageType : displayMessageTypes) {

				List<LocalizedMessageDescriptionType> localizedmessages = displayMessageType.getMsgText();
				if (localizedmessages != null && localizedmessages.size() > 0) {
					for (LocalizedMessageDescriptionType localizedmessage : localizedmessages) {
						RisplDkItemMsg risplDkItemMsg = new RisplDkItemMsg();
						RisplDkItemMsgPK risplDkItemMsgPK = new RisplDkItemMsgPK();

						risplDkItemMsgPK.setDispMsgId(displayMessageType.getID());
						risplDkItemMsgPK.setLcl(localizedmessage.getLanguage().value());

						risplDkItemMsg.setId(risplDkItemMsgPK);
						risplDkItemMsg.setNmMsgDply(localizedmessage.getName());
						risplDkItemMsg.setNaMsgDply(localizedmessage.getValue());
						if (displayMessageType.getChangeType() != null
								&& displayMessageType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							risplDkItemMsg = em.find(RisplDkItemMsg.class, risplDkItemMsg.getId());
							if (risplDkItemMsg != null) {
								em.remove(risplDkItemMsg);
							}
						} else {
							em.merge(risplDkItemMsg);
						}
					}
				}

			}
			// Item Messages END

			// Items START
			Random random = new Random();
			String eventId = "";
			List<ItemType> itemTypes = itmImprts.getItem();
			int counter = 0;
			for (ItemType itemType : itemTypes) {

				// For all stores insert Item details
				List<RetailStoreItemType> retailStoreItemTypes = itemType.getRetailStoreItem();
				for (RetailStoreItemType retailStoreItemType : retailStoreItemTypes) {

					List<String> stores = retailStoreItemType.getRetailStoreID();

					for (String store : stores) {
						counter++;
						// Insert Initial Price of Item in to
						// RISPL_DK_ITEM_PRICE table - START
						List<RegularPriceType> priceTypes = retailStoreItemType.getRegularPrice();
						if (priceTypes != null && priceTypes.size() > 0) {
							counter++;
							RisplDkItemPrice risplDkItemPrice = new RisplDkItemPrice();
							RisplDkItemPricePK risplDkItemPricePK = new RisplDkItemPricePK();
							Date today = new Date();
							eventId = today.getTime()+""+counter;
							risplDkItemPricePK.setEvntId(Long.parseLong(eventId));

							risplDkItemPricePK.setRtStrId(store);

							risplDkItemPrice.setItmId(itemType.getID());
							risplDkItemPrice.setEvntTyp("IIP");
							risplDkItemPrice.setEvPri(new BigDecimal(1));
							risplDkItemPrice.setSlUnAmt(priceTypes.get(0).getValue());
							risplDkItemPrice.setEvEfTmp(new Timestamp(System.currentTimeMillis()));

							risplDkItemPrice.setId(risplDkItemPricePK);

							em.merge(risplDkItemPrice);

						}
						// Insert Initial Price of Item in to
						// RISPL_DK_ITEM_PRICE table - END

						// Insert to RISPL_DK_ITEM_MSTR table -START
						RisplDkItemMstr risDkItemMstr = new RisplDkItemMstr();
						RisplDkItemMstrPK risDkItemMstrPK = new RisplDkItemMstrPK();

						risDkItemMstrPK.setRtStrId(store);
						risDkItemMstrPK.setItmId(itemType.getID());
						List<POSIdentityType> posIdentityType = retailStoreItemType.getPOSIdentity();
						if (posIdentityType != null && posIdentityType.size() > 0) {
							POSIdentityType identity = posIdentityType.get(0);
							risDkItemMstrPK.setIdItmPos(identity.getPOSItemID());

							// Insert to RISPL_DK_ITEM_ATTR table - START
							RisplDkItemAttr risplDkItemAttr = new RisplDkItemAttr();
							RisplDkItemAttrPK risplDkItemAttrPK = new RisplDkItemAttrPK();

							risplDkItemAttrPK.setRtStrId(store);
							risplDkItemAttrPK.setItmId(itemType.getID());
							risplDkItemAttrPK.setIdItmPos(identity.getPOSItemID());

							risplDkItemAttr.setEmpDisAlwFlg(getFormattedBoolean(identity.isEmployeeDiscountAllowed()));

							risplDkItemAttr.setId(risplDkItemAttrPK);
							risplDkItemAttr.setItmMnmSlQnt(identity.getMinimumSaleUnitCount());
							risplDkItemAttr.setItmMxmSlQnt(identity.getMaximumSaleUnitCount());

							risplDkItemAttr.setItmRstkFeFlg(getFormattedBoolean(itemType.isRestockingFee()));

							risplDkItemAttr.setItmUomCd(itemType.getUOMCode());

							risplDkItemAttr.setPrcEntrRqFlg(getFormattedBoolean(identity.isPriceEntryRequired()));

							risplDkItemAttr.setPrhRtnFl(getFormattedBoolean(identity.isReturnable()));

							risplDkItemAttr.setRtPrcMdfrFlg(getFormattedBoolean(identity.isPriceModifiable()));

							risplDkItemAttr.setSlsUomCd(itemType.getUOMCode());

							risplDkItemAttr.setSpoElgFlg(getFormattedBoolean(identity.isSpecialOrderEligible()));

							/*
							 * if(identity.is){
							 * risplDkItemAttr.setWhtEntrRqFlg(trueInd); }else{
							 * risplDkItemAttr.setWhtEntrRqFlg(falseInd); }
							 */
							if (priceTypes != null && priceTypes.size() > 0) {
								risplDkItemAttr.setRpPrcCmprAtSls(priceTypes.get(0).getCompareAtPrice());
							}

							if (retailStoreItemType.getTaxGroup() != null) {
								risplDkItemAttr.setTxGpId(new BigDecimal(retailStoreItemType.getTaxGroup()));
							}

							if (retailStoreItemType.getAgeRestrictionId() != null) {
								risplDkItemAttr
										.setSlsAgRstIdn(new BigDecimal(retailStoreItemType.getAgeRestrictionId()));
							}

							risplDkItemAttr.setLbTmpltId(retailStoreItemType.getTemplateId());

							em.merge(risplDkItemAttr);
							// Insert to RISPL_DK_ITEM_ATTR table - END
						}

						risDkItemMstr.setId(risDkItemMstrPK);
						// risDkItemMstr.setDispMsgId(itemType.);
						// risDkItemMstr.setItmBrnNm();
						risDkItemMstr.setItmClrCd(itemType.getColor());

						List<LocalizedDescriptionType> localizedDesc = itemType.getLongDescription();
						if (localizedDesc != null && localizedDesc.size() > 0) {
							risDkItemMstr.setItmDesc(localizedDesc.get(0).getValue());
						}

						risDkItemMstr.setItmDmgDscFlg(getFormattedBoolean(itemType.isDamageDiscountable()));
						risDkItemMstr.setItmDscFlg(getFormattedBoolean(itemType.isDiscountable()));

						// risDkItemMstr.setItmImgLoc();

						// risDkItemMstr.setItmKtId();
						risDkItemMstr.setItmKtStCd(itemType.getKitSetCode());

						// risDkItemMstr.setItmMfId();

						if (itemType.getMerchandiseHierarchy() != null) {
							risDkItemMstr.setItmMrcHrcLvCd(itemType.getMerchandiseHierarchy().getLevel());
							risDkItemMstr.setItmMrhrcGpId(itemType.getMerchandiseHierarchy().getValue());
						}

						if (itemType.getMerchandiseHierarchy() != null
								&& itemType.getMerchandiseHierarchy().getStructureID() != null) {
							risDkItemMstr.setItmMrcStrcId(
									new BigDecimal(itemType.getMerchandiseHierarchy().getStructureID()));
						}

						// risDkItemMstr.setItmMrhrcGpId(itemType.get);

						risDkItemMstr.setItmPosDptId(itemType.getPOSDepartmentID());

						risDkItemMstr.setItmRgstryFl(getFormattedBoolean(itemType.isRegistryEligible()));

						// risDkItemMstr.setItmSbstIdnFlg(itemType.is);

						List<LocalizedNameType> localizedNameDesc = itemType.getShortName();
						if (localizedNameDesc != null && localizedNameDesc.size() > 0) {
							risDkItemMstr.setItmShrtDesc(localizedNameDesc.get(0).getValue());
						}

						risDkItemMstr.setItmSlsAznFlg(getFormattedBoolean(itemType.isAuthorizedForSale()));

						risDkItemMstr.setItmSrlzdFlg(getFormattedBoolean(itemType.isSerializedItem()));

						risDkItemMstr.setItmSrzCptTmCd(itemType.getUINCaptureTime());

						risDkItemMstr.setItmSzCd(itemType.getSize());

						risDkItemMstr.setItmSzReqFlg(getFormattedBoolean(itemType.isSizeRequired()));

						risDkItemMstr.setItmTxExmCd(getFormattedBoolean(itemType.isTaxable()));

						if (itemType.getTaxGroup() != null) {
							risDkItemMstr.setItmTxGpId(new BigDecimal(itemType.getTaxGroup()));
						}

						risDkItemMstr.setItmTyCd(itemType.getType());

						risDkItemMstr.setItmRcrdCrtTs(new Timestamp(System.currentTimeMillis()));
						risDkItemMstr.setItmRcrdMdfTs(new Timestamp(System.currentTimeMillis()));

						if (itemType.getChangeType() != null
								&& itemType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							risDkItemMstr = em.find(RisplDkItemMstr.class, risDkItemMstr.getId());
							if (risDkItemMstr != null) {
								em.remove(risDkItemMstr);
							}
						} else {
							em.merge(risDkItemMstr);
						}
						// Insert to RISPL_DK_ITEM_MSTR table -END

					}
				}
				// Related Items insert to RISPL_DK_ITEM_RLTD_ITMS Table - START

				List<RelatedItemAssociationType> relatedItems = itemType.getRelatedItemAssociation();
				if (relatedItems != null) {
					for (RelatedItemAssociationType relatedItem : relatedItems) {
						RisplDkItemRltdItm risplDkItemRltdItm = new RisplDkItemRltdItm();
						RisplDkItemRltdItmPK risplDkItemRltdItmPK = new RisplDkItemRltdItmPK();

						risplDkItemRltdItmPK.setItmId(itemType.getID());
						risplDkItemRltdItmPK.setRltdItmId(relatedItem.getRelatedItemID());
						risplDkItemRltdItmPK.setRltdItmTypCd(relatedItem.getTypeCode());

						risplDkItemRltdItm.setId(risplDkItemRltdItmPK);
						risplDkItemRltdItm.setRmvRltdItmFlg(getFormattedBoolean(relatedItem.isRemoveAllowed()));
						risplDkItemRltdItm.setRtnRltdItmFlg(getFormattedBoolean(relatedItem.isReturnAllowed()));

						if (relatedItem.getChangeType() != null
								&& relatedItem.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							risplDkItemRltdItm = em.find(RisplDkItemRltdItm.class, risplDkItemRltdItm.getId());
							if (risplDkItemRltdItm != null) {
								em.remove(risplDkItemRltdItm);
							}
						} else {
							em.merge(risplDkItemRltdItm);
						}
					}
				}
				// Related Items - END
			}
			// Items END
			em.getTransaction().commit();
			// em.close();
			LOGGER.info("Item XML file Processed Successfully ..............");

		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}
	}

	public static String getFormattedBoolean(Boolean value) {
		if (value != null && value) {
			return DKartConstantsIfc.TRUE_IND;
		}
		return DKartConstantsIfc.FALSE_IND;
	}
	
	public static void commit(EntityManager em){
		try{
			if(em.getTransaction().isActive()){
				em.getTransaction().commit();
				em.getTransaction().begin();
			}
		}catch(Exception e){
			throw e;
		}
	}
}
