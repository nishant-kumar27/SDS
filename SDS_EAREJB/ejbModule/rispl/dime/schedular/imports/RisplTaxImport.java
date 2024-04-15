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

import rispl.db.model.tax.RisplDkGeoCode;
import rispl.db.model.tax.RisplDkTaxGrpRule;
import rispl.db.model.tax.RisplDkTaxGrpRulePK;
import rispl.db.model.tax.RisplDkTaxItm;
import rispl.db.model.tax.RisplDkTaxItmPK;
import rispl.db.model.tax.RisplDkTaxJur;
import rispl.db.model.tax.RisplDkTaxJursAuth;
import rispl.db.model.tax.RisplDkTaxJursAuthPK;
import rispl.db.model.tax.RisplDkTaxRteRule;
import rispl.db.model.tax.RisplDkTaxRteRulePK;
import rispl.db.model.tax.RisplDkTaxType;
import rispl.jaxb.tax.GEOCodeType;
import rispl.jaxb.tax.GEOTaxJurisdictionType;
import rispl.jaxb.tax.LocalizedNameDescriptionType;
import rispl.jaxb.tax.TaxAuthorityType;
import rispl.jaxb.tax.TaxGroupRuleType;
import rispl.jaxb.tax.TaxImport;
import rispl.jaxb.tax.TaxRateRuleType;
import rispl.jaxb.tax.TaxableGroupType;

public class RisplTaxImport {
	private static final Logger LOGGER = LogManager.getLogger(RisplTaxImport.class);

	public static void readXmlAndPersist(InputStream inputStream, EntityManager em) throws Exception {
		try {
			LOGGER.info("Processing Tax XML File............");
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}

			JAXBContext jaxbContext = JAXBContext.newInstance(TaxImport.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TaxImport taxImport = (TaxImport) jaxbUnmarshaller.unmarshal(inputStream);

			List<Object> mergeEntities = new ArrayList<Object>();

			// Insert into RISPL_DK_GEO_CODE table - START
			List<GEOCodeType> geoCodeTypes = taxImport.getGEOCode();
			for (GEOCodeType geoCodeType : geoCodeTypes) {
				RisplDkGeoCode risplDkGeoCode = new RisplDkGeoCode();
				risplDkGeoCode.setGeoCdeId(geoCodeType.getGeoCodeID());
				risplDkGeoCode.setTaxJurNme(geoCodeType.getTaxJurisdictionName());

				mergeEntities.add(risplDkGeoCode);
			}
			// Insert into RISPL_DK_GEO_CODE table - END

			// Insert into RISPL_DK_TAX_JUR table - START
			List<GEOTaxJurisdictionType> geoTaxJurisdictionTypes = taxImport.getGEOTaxJurisdiction();
			if (geoTaxJurisdictionTypes != null && geoTaxJurisdictionTypes.size() > 0) {
				for (GEOTaxJurisdictionType geoTaxJurisdictionType : geoTaxJurisdictionTypes) {
					RisplDkTaxJur risplDkTaxJur = new RisplDkTaxJur();
					risplDkTaxJur.setGeoCdeId(geoTaxJurisdictionType.getGeoCodeID());
					risplDkTaxJur.setPostCode(geoTaxJurisdictionType.getPostalCode());
					mergeEntities.add(risplDkTaxJur);
				}
			}
			// Insert into RISPL_DK_TAX_JUR table - END

			// Insert into RISPL_DK_TAX_JURS_AUTH table - END
			List<TaxAuthorityType> taxAuthorityTypes = taxImport.getTaxAuthority();
			for (TaxAuthorityType taxAuthorityType : taxAuthorityTypes) {

				List<String> geoCodeTypes2 = taxAuthorityType.getGeoCodeID();
				for (String geoCode : geoCodeTypes2) {
					RisplDkTaxJursAuth risplDkTaxJursAuth = new RisplDkTaxJursAuth();
					RisplDkTaxJursAuthPK risplDkTaxJursAuthPK = new RisplDkTaxJursAuthPK();
					risplDkTaxJursAuthPK.setGeoCdeId(geoCode);
					risplDkTaxJursAuthPK.setTaxAuthId(taxAuthorityType.getTaxAuthorityID().longValue());
					risplDkTaxJursAuth.setId(risplDkTaxJursAuthPK);

					mergeEntities.add(risplDkTaxJursAuth);
				}
			}
			// Insert into RISPL_DK_TAX_JURS_AUTH table - END

			// Insert into RISPL_DK_TAX_ITM table - START
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			List<TaxableGroupType> taxableGroupTypes = taxImport.getTaxableGroup();
			for (TaxableGroupType taxableGroupType : taxableGroupTypes) {

				List<LocalizedNameDescriptionType> localizedNameDescriptionTypes = taxableGroupType
						.getLocalizedTaxGroupNameDescription();
				if (localizedNameDescriptionTypes != null && localizedNameDescriptionTypes.size() > 0) {
					for (LocalizedNameDescriptionType localizedNameDescriptionType : localizedNameDescriptionTypes) {
						RisplDkTaxItm risplDkTaxItm = new RisplDkTaxItm();
						RisplDkTaxItmPK risplDkTaxItmPK = new RisplDkTaxItmPK();

						risplDkTaxItmPK.setIdGpTx(taxableGroupType.getTaxGroupID().longValue());
						risplDkTaxItmPK.setLcl(localizedNameDescriptionType.getLanguage().value());

						if (taxableGroupType.getReceiptPrintCode() != null) {
							risplDkTaxItm.setCdRcvPrt(new BigDecimal(taxableGroupType.getReceiptPrintCode()));
						}
						risplDkTaxItm.setDeGpTx(localizedNameDescriptionType.getDescription());
						risplDkTaxItm.setId(risplDkTaxItmPK);
						risplDkTaxItm.setNmGpTx(localizedNameDescriptionType.getName());
						risplDkTaxItm.setTsCrtRcrd(timestamp);
						risplDkTaxItm.setTsMdfRcrd(timestamp);

						mergeEntities.add(risplDkTaxItm);

					}
				} else {
					RisplDkTaxItm risplDkTaxItm = new RisplDkTaxItm();
					RisplDkTaxItmPK risplDkTaxItmPK = new RisplDkTaxItmPK();

					risplDkTaxItmPK.setIdGpTx(taxableGroupType.getTaxGroupID().longValue());
					risplDkTaxItmPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

					if (taxableGroupType.getReceiptPrintCode() != null) {
						risplDkTaxItm.setCdRcvPrt(new BigDecimal(taxableGroupType.getReceiptPrintCode()));
					}
					risplDkTaxItm.setDeGpTx(taxableGroupType.getTaxGroupDescription());
					risplDkTaxItm.setId(risplDkTaxItmPK);
					risplDkTaxItm.setNmGpTx(taxableGroupType.getTaxGroupName());
					risplDkTaxItm.setTsCrtRcrd(timestamp);
					risplDkTaxItm.setTsMdfRcrd(timestamp);

					mergeEntities.add(risplDkTaxItm);
				}
			}
			// Insert into RISPL_DK_TAX_ITM table - END

			timestamp = new Timestamp(System.currentTimeMillis());
			List<TaxGroupRuleType> taxGroupRuleTypes = taxImport.getTaxGroupRule();
			int indx = 1;
			for (TaxGroupRuleType taxGroupRuleType : taxGroupRuleTypes) {

				// Insert into RISPL_DK_TAX_TYPE table - START
				RisplDkTaxType risplDkTaxType = new RisplDkTaxType();
				risplDkTaxType.setTaxTypId(taxGroupRuleType.getTaxTypeID().longValue());
				risplDkTaxType.setNmTyTx(taxGroupRuleType.getTaxTypeName());
				mergeEntities.add(risplDkTaxType);
				// Insert into RISPL_DK_TAX_TYPE table - END

				// Insert into RISPL_DK_TAX_GRP_RULE table - START
				RisplDkTaxGrpRule risplDkTaxGrpRule = new RisplDkTaxGrpRule();
				RisplDkTaxGrpRulePK risplDkTaxGrpRulePK = new RisplDkTaxGrpRulePK();

				risplDkTaxGrpRulePK.setIdGpTx(Long.valueOf(taxGroupRuleType.getTaxGroupID()));
				risplDkTaxGrpRulePK.setTaxAuthId(taxGroupRuleType.getTaxAuthorityID().longValue());
				risplDkTaxGrpRulePK.setTaxTypId(taxGroupRuleType.getTaxTypeID().longValue());

				if (taxGroupRuleType.getCompoundRateSequenceNumber() != null) {
					risplDkTaxGrpRule.setAiCmpnd(new BigDecimal(taxGroupRuleType.getCompoundRateSequenceNumber()));
				}

				if (taxGroupRuleType.getCalculationMethodCode() != null) {
					if (taxGroupRuleType.getCalculationMethodCode().equalsIgnoreCase(DKartConstantsIfc.LINE_ITEM)) {
						risplDkTaxGrpRule.setCalMthCd(new BigDecimal(DKartConstantsIfc.LINE_ITEM_VAL));
					} else {
						risplDkTaxGrpRule.setCalMthCd(new BigDecimal(DKartConstantsIfc.TRANSACTION_VAL));
					}

				}

				if (taxGroupRuleType.getTaxRateRuleUsageCode() != null) {
					// "PercentageOrAmount"=1, "DeriveFromTaxTable"=2,
					// "UseThresholdAmount"=3

					if (taxGroupRuleType.getTaxRateRuleUsageCode().equalsIgnoreCase(DKartConstantsIfc.PRCNT_OR_AMT)) {
						risplDkTaxGrpRule.setCdTxRtRuUsg(new BigDecimal(DKartConstantsIfc.PRCNT_OR_AMT_VAL));
					} else if (taxGroupRuleType.getTaxRateRuleUsageCode()
							.equalsIgnoreCase(DKartConstantsIfc.DRV_FRM_TXBL)) {
						risplDkTaxGrpRule.setCdTxRtRuUsg(new BigDecimal(DKartConstantsIfc.DRV_FRM_TXBL_VAL));

					} else {
						risplDkTaxGrpRule.setCdTxRtRuUsg(new BigDecimal(DKartConstantsIfc.USE_THSLD_AMT_VAL));
					}
				}

				risplDkTaxGrpRule.setId(risplDkTaxGrpRulePK);

				if (taxGroupRuleType.isInclusiveTaxFlag()) {
					risplDkTaxGrpRule.setIncTaxFlg(DKartConstantsIfc.TRUE_IND);
				} else {
					risplDkTaxGrpRule.setIncTaxFlg(DKartConstantsIfc.FALSE_IND);
				}

				if (taxGroupRuleType.isTaxOnGrossAmountFlag()) {
					risplDkTaxGrpRule.setTaxGsAmtFlg(DKartConstantsIfc.TRUE_IND);
				} else {
					risplDkTaxGrpRule.setTaxGsAmtFlg(DKartConstantsIfc.FALSE_IND);
				}

				if (taxGroupRuleType.isTaxHolidayFlag()) {
					risplDkTaxGrpRule.setTaxHldyFlg(DKartConstantsIfc.TRUE_IND);
				} else {
					risplDkTaxGrpRule.setTaxHldyFlg(DKartConstantsIfc.FALSE_IND);
				}

				risplDkTaxGrpRule.setTaxRulDes(taxGroupRuleType.getTaxRuleDescription());

				risplDkTaxGrpRule.setTaxRulNme(taxGroupRuleType.getTaxRuleName());

				risplDkTaxGrpRule.setTsCrtRcrd(timestamp);

				risplDkTaxGrpRule.setTsMdfRcrd(timestamp);

				mergeEntities.add(risplDkTaxGrpRule);

				// Insert into RISPL_DK_TAX_GRP_RULE table - END

				// Insert into RISPL_DK_TAX_RTE_RULE table - START
				RisplDkTaxRteRule risplDkTaxRteRule = new RisplDkTaxRteRule();
				RisplDkTaxRteRulePK risplDkTaxRteRulePK = new RisplDkTaxRteRulePK();

				risplDkTaxRteRulePK.setAiTxRtRu(Long.valueOf(indx));
				risplDkTaxRteRulePK.setIdGpTx(Long.parseLong(taxGroupRuleType.getTaxGroupID()));
				risplDkTaxRteRulePK.setTaxAuthId(taxGroupRuleType.getTaxAuthorityID().longValue());
				if (taxGroupRuleType.isTaxHolidayFlag()) {
					risplDkTaxRteRulePK.setTaxHldyFlg(DKartConstantsIfc.TRUE_IND);
				} else {
					risplDkTaxRteRulePK.setTaxHldyFlg(DKartConstantsIfc.FALSE_IND);
				}

				risplDkTaxRteRulePK.setTaxTypId(taxGroupRuleType.getTaxTypeID().longValue());

				List<TaxRateRuleType> taxRateRuleTypes = taxGroupRuleType.getTaxRateRule();
				for (TaxRateRuleType taxRateRuleType : taxRateRuleTypes) {

					if (taxRateRuleType.getRateTypeCode() != null) {
						// "Percentage"=1, "Amount"=2
						if (taxRateRuleType.getRateTypeCode().equalsIgnoreCase(DKartConstantsIfc.PERCENTAGE)) {
							risplDkTaxRteRule.setCdTyp(DKartConstantsIfc.PERCENTAGE_VALUE);
						} else {
							risplDkTaxRteRule.setCdTyp(DKartConstantsIfc.AMOUNT_VALUE);
						}
					}

					if (taxRateRuleType.getTaxAboveThresholdAmountFlag() != null
							&& taxRateRuleType.getTaxAboveThresholdAmountFlag().equalsIgnoreCase("true")) {
						risplDkTaxRteRule.setFlTxAbvThMo(DKartConstantsIfc.TRUE_IND);
					} else {
						risplDkTaxRteRule.setFlTxAbvThMo(DKartConstantsIfc.FALSE_IND);
					}

					risplDkTaxRteRule.setId(risplDkTaxRteRulePK);

					risplDkTaxRteRule.setMaxTxblAmt(taxRateRuleType.getMaximumTaxableAmount());
					risplDkTaxRteRule.setMinTxblAmt(taxRateRuleType.getMinimumTaxableAmount());
					risplDkTaxRteRule.setTaxAmt(taxRateRuleType.getTaxAmount());
					risplDkTaxRteRule.setTaxPercnt(taxRateRuleType.getTaxPercentageRate());
					risplDkTaxRteRule.setTaxThrshldAmt(taxRateRuleType.getThresholdAmount());
					if (taxRateRuleType.getTaxRateEffectiveTimestamp() != null) {
						risplDkTaxRteRule.setTsRtTxEf(new Timestamp(taxRateRuleType.getTaxRateEffectiveTimestamp()
								.toGregorianCalendar().getTimeInMillis()));
					}

					if (taxRateRuleType.getTaxRateExpirationTimestamp() != null) {
						risplDkTaxRteRule.setTsRtTxEp(new Timestamp(taxRateRuleType.getTaxRateExpirationTimestamp()
								.toGregorianCalendar().getTimeInMillis()));
					}

					risplDkTaxRteRule.setTsCrtRcrd(timestamp);
					risplDkTaxRteRule.setTsMdfRcrd(timestamp);

					mergeEntities.add(risplDkTaxRteRule);

				}
				// Insert into RISPL_DK_TAX_RTE_RULE table - END
			}

			// Perform DB Operations
			if (taxImport.getFillType() != null
					&& taxImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkGeoCode  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxJur  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxJursAuth  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxItm  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxGrpRule  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxRteRule  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxType  e").executeUpdate();
			}

			if (mergeEntities.size() > 0 && taxImport.getFillType() != null
					&& !taxImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object mrgObj : mergeEntities) {
					if (mrgObj instanceof RisplDkGeoCode) {
						RisplDkGeoCode risplDkGeoCode = (RisplDkGeoCode) mrgObj;
						if (em.find(RisplDkGeoCode.class, risplDkGeoCode.getGeoCdeId()) == null) {
							em.persist(risplDkGeoCode);
						} else {
							em.merge(risplDkGeoCode);
						}
					} else if (mrgObj instanceof RisplDkTaxJur) {
						RisplDkTaxJur risplDkTaxJur = (RisplDkTaxJur) mrgObj;
						if (em.find(RisplDkTaxJur.class, risplDkTaxJur.getGeoCdeId()) == null) {
							em.persist(risplDkTaxJur);
						} else {
							em.merge(risplDkTaxJur);
						}
					} else if (mrgObj instanceof RisplDkTaxJursAuth) {
						RisplDkTaxJursAuth risplDkTaxJursAuth = (RisplDkTaxJursAuth) mrgObj;
						if (em.find(RisplDkTaxJursAuth.class, risplDkTaxJursAuth.getId()) == null) {
							em.persist(risplDkTaxJursAuth);
						} else {
							em.merge(risplDkTaxJursAuth);
						}
					} else if (mrgObj instanceof RisplDkTaxItm) {
						RisplDkTaxItm risplDkTaxItm = (RisplDkTaxItm) mrgObj;
						if (em.find(RisplDkTaxItm.class, risplDkTaxItm.getId()) == null) {
							em.persist(risplDkTaxItm);
						} else {
							em.merge(risplDkTaxItm);
						}
					} else if (mrgObj instanceof RisplDkTaxGrpRule) {
						RisplDkTaxGrpRule risplDkTaxGrpRule = (RisplDkTaxGrpRule) mrgObj;
						if (em.find(RisplDkTaxGrpRule.class, risplDkTaxGrpRule.getId()) == null) {
							em.persist(risplDkTaxGrpRule);
						} else {
							em.merge(risplDkTaxGrpRule);
						}
					} else if (mrgObj instanceof RisplDkTaxRteRule) {
						RisplDkTaxRteRule risplDkTaxRteRule = (RisplDkTaxRteRule) mrgObj;
						if (em.find(RisplDkTaxRteRule.class, risplDkTaxRteRule.getId()) == null) {
							em.persist(risplDkTaxRteRule);
						} else {
							em.merge(risplDkTaxRteRule);
						}
					} else if (mrgObj instanceof RisplDkTaxType) {
						RisplDkTaxType risplDkTaxType = (RisplDkTaxType) mrgObj;
						if (em.find(RisplDkTaxType.class, risplDkTaxType.getTaxTypId()) == null) {
							em.persist(risplDkTaxType);
						} else {
							em.merge(risplDkTaxType);
						}
					}
				}
			} else if (mergeEntities.size() > 0 && taxImport.getFillType() != null
					&& taxImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object prsstObj : mergeEntities) {
					em.persist(prsstObj);
				}
			}

			em.getTransaction().commit();
			LOGGER.info("Tax XML File Processed Successfully ............");
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}
	}

	public static void readXmlAndPersist2(InputStream inputStream, EntityManager em) throws Exception {
		try {
			LOGGER.info("Processing Tax XML File............");
			em.getTransaction().begin();

			JAXBContext jaxbContext = JAXBContext.newInstance(TaxImport.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TaxImport taxImport = (TaxImport) jaxbUnmarshaller.unmarshal(inputStream);

			if (taxImport.getFillType() != null
					&& taxImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkGeoCode  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxJur  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxJursAuth  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxItm  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxGrpRule  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxRteRule  e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkTaxType  e").executeUpdate();
				em.getTransaction().commit();
				em.getTransaction().begin();
			}
			// Insert into RISPL_DK_GEO_CODE table - START
			List<GEOCodeType> geoCodeTypes = taxImport.getGEOCode();
			for (GEOCodeType geoCodeType : geoCodeTypes) {
				RisplDkGeoCode risplDkGeoCode = new RisplDkGeoCode();
				risplDkGeoCode.setGeoCdeId(geoCodeType.getGeoCodeID());
				risplDkGeoCode.setTaxJurNme(geoCodeType.getTaxJurisdictionName());

				em.merge(risplDkGeoCode);

			}
			// Insert into RISPL_DK_GEO_CODE table - END

			// Insert into RISPL_DK_TAX_JUR table - START
			List<GEOTaxJurisdictionType> geoTaxJurisdictionTypes = taxImport.getGEOTaxJurisdiction();
			if (geoTaxJurisdictionTypes != null && geoTaxJurisdictionTypes.size() > 0) {
				em.getTransaction().commit();
				em.getTransaction().begin();
				for (GEOTaxJurisdictionType geoTaxJurisdictionType : geoTaxJurisdictionTypes) {
					RisplDkTaxJur risplDkTaxJur = new RisplDkTaxJur();
					risplDkTaxJur.setGeoCdeId(geoTaxJurisdictionType.getGeoCodeID());
					risplDkTaxJur.setPostCode(geoTaxJurisdictionType.getPostalCode());

					em.merge(risplDkTaxJur);
				}
			}
			// Insert into RISPL_DK_TAX_JUR table - END

			// Insert into RISPL_DK_TAX_JURS_AUTH table - END
			List<TaxAuthorityType> taxAuthorityTypes = taxImport.getTaxAuthority();
			for (TaxAuthorityType taxAuthorityType : taxAuthorityTypes) {

				List<String> geoCodeTypes2 = taxAuthorityType.getGeoCodeID();
				for (String geoCode : geoCodeTypes2) {
					RisplDkTaxJursAuth risplDkTaxJursAuth = new RisplDkTaxJursAuth();
					RisplDkTaxJursAuthPK risplDkTaxJursAuthPK = new RisplDkTaxJursAuthPK();
					risplDkTaxJursAuthPK.setGeoCdeId(geoCode);
					risplDkTaxJursAuthPK.setTaxAuthId(taxAuthorityType.getTaxAuthorityID().longValue());
					risplDkTaxJursAuth.setId(risplDkTaxJursAuthPK);

					em.merge(risplDkTaxJursAuth);
				}

			}
			// Insert into RISPL_DK_TAX_JURS_AUTH table - END

			// Insert into RISPL_DK_TAX_ITM table - START
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			List<TaxableGroupType> taxableGroupTypes = taxImport.getTaxableGroup();
			for (TaxableGroupType taxableGroupType : taxableGroupTypes) {

				List<LocalizedNameDescriptionType> localizedNameDescriptionTypes = taxableGroupType
						.getLocalizedTaxGroupNameDescription();
				if (localizedNameDescriptionTypes != null && localizedNameDescriptionTypes.size() > 0) {
					for (LocalizedNameDescriptionType localizedNameDescriptionType : localizedNameDescriptionTypes) {
						RisplDkTaxItm risplDkTaxItm = new RisplDkTaxItm();
						RisplDkTaxItmPK risplDkTaxItmPK = new RisplDkTaxItmPK();

						risplDkTaxItmPK.setIdGpTx(taxableGroupType.getTaxGroupID().longValue());
						risplDkTaxItmPK.setLcl(localizedNameDescriptionType.getLanguage().value());

						if (taxableGroupType.getReceiptPrintCode() != null) {
							risplDkTaxItm.setCdRcvPrt(new BigDecimal(taxableGroupType.getReceiptPrintCode()));
						}
						risplDkTaxItm.setDeGpTx(localizedNameDescriptionType.getDescription());
						risplDkTaxItm.setId(risplDkTaxItmPK);
						risplDkTaxItm.setNmGpTx(localizedNameDescriptionType.getName());
						risplDkTaxItm.setTsCrtRcrd(timestamp);
						risplDkTaxItm.setTsMdfRcrd(timestamp);

						em.merge(risplDkTaxItm);

					}
				} else {
					RisplDkTaxItm risplDkTaxItm = new RisplDkTaxItm();
					RisplDkTaxItmPK risplDkTaxItmPK = new RisplDkTaxItmPK();

					risplDkTaxItmPK.setIdGpTx(taxableGroupType.getTaxGroupID().longValue());
					risplDkTaxItmPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

					if (taxableGroupType.getReceiptPrintCode() != null) {
						risplDkTaxItm.setCdRcvPrt(new BigDecimal(taxableGroupType.getReceiptPrintCode()));
					}
					risplDkTaxItm.setDeGpTx(taxableGroupType.getTaxGroupDescription());
					risplDkTaxItm.setId(risplDkTaxItmPK);
					risplDkTaxItm.setNmGpTx(taxableGroupType.getTaxGroupName());
					risplDkTaxItm.setTsCrtRcrd(timestamp);
					risplDkTaxItm.setTsMdfRcrd(timestamp);

					em.merge(risplDkTaxItm);
				}

			}
			// Insert into RISPL_DK_TAX_ITM table - END

			timestamp = new Timestamp(System.currentTimeMillis());
			List<TaxGroupRuleType> taxGroupRuleTypes = taxImport.getTaxGroupRule();
			int indx = 1;
			for (TaxGroupRuleType taxGroupRuleType : taxGroupRuleTypes) {

				// Insert into RISPL_DK_TAX_TYPE table - START
				RisplDkTaxType risplDkTaxType = new RisplDkTaxType();
				risplDkTaxType.setTaxTypId(taxGroupRuleType.getTaxTypeID().longValue());
				risplDkTaxType.setNmTyTx(taxGroupRuleType.getTaxTypeName());
				em.merge(risplDkTaxType);
				// Insert into RISPL_DK_TAX_TYPE table - END

				// Insert into RISPL_DK_TAX_GRP_RULE table - START
				RisplDkTaxGrpRule risplDkTaxGrpRule = new RisplDkTaxGrpRule();
				RisplDkTaxGrpRulePK risplDkTaxGrpRulePK = new RisplDkTaxGrpRulePK();

				risplDkTaxGrpRulePK.setIdGpTx(Long.valueOf(taxGroupRuleType.getTaxGroupID()));
				risplDkTaxGrpRulePK.setTaxAuthId(taxGroupRuleType.getTaxAuthorityID().longValue());
				risplDkTaxGrpRulePK.setTaxTypId(taxGroupRuleType.getTaxTypeID().longValue());

				if (taxGroupRuleType.getCompoundRateSequenceNumber() != null) {
					risplDkTaxGrpRule.setAiCmpnd(new BigDecimal(taxGroupRuleType.getCompoundRateSequenceNumber()));
				}

				if (taxGroupRuleType.getCalculationMethodCode() != null) {
					if (taxGroupRuleType.getCalculationMethodCode().equalsIgnoreCase(DKartConstantsIfc.LINE_ITEM)) {
						risplDkTaxGrpRule.setCalMthCd(new BigDecimal(DKartConstantsIfc.LINE_ITEM_VAL));
					} else {
						risplDkTaxGrpRule.setCalMthCd(new BigDecimal(DKartConstantsIfc.TRANSACTION_VAL));
					}

				}

				if (taxGroupRuleType.getTaxRateRuleUsageCode() != null) {
					// "PercentageOrAmount"=1, "DeriveFromTaxTable"=2,
					// "UseThresholdAmount"=3

					if (taxGroupRuleType.getTaxRateRuleUsageCode().equalsIgnoreCase(DKartConstantsIfc.PRCNT_OR_AMT)) {
						risplDkTaxGrpRule.setCdTxRtRuUsg(new BigDecimal(DKartConstantsIfc.PRCNT_OR_AMT_VAL));
					} else if (taxGroupRuleType.getTaxRateRuleUsageCode()
							.equalsIgnoreCase(DKartConstantsIfc.DRV_FRM_TXBL)) {
						risplDkTaxGrpRule.setCdTxRtRuUsg(new BigDecimal(DKartConstantsIfc.DRV_FRM_TXBL_VAL));

					} else {
						risplDkTaxGrpRule.setCdTxRtRuUsg(new BigDecimal(DKartConstantsIfc.USE_THSLD_AMT_VAL));
					}
				}

				risplDkTaxGrpRule.setId(risplDkTaxGrpRulePK);

				if (taxGroupRuleType.isInclusiveTaxFlag()) {
					risplDkTaxGrpRule.setIncTaxFlg(DKartConstantsIfc.TRUE_IND);
				} else {
					risplDkTaxGrpRule.setIncTaxFlg(DKartConstantsIfc.FALSE_IND);
				}

				if (taxGroupRuleType.isTaxOnGrossAmountFlag()) {
					risplDkTaxGrpRule.setTaxGsAmtFlg(DKartConstantsIfc.TRUE_IND);
				} else {
					risplDkTaxGrpRule.setTaxGsAmtFlg(DKartConstantsIfc.FALSE_IND);
				}

				if (taxGroupRuleType.isTaxHolidayFlag()) {
					risplDkTaxGrpRule.setTaxHldyFlg(DKartConstantsIfc.TRUE_IND);
				} else {
					risplDkTaxGrpRule.setTaxHldyFlg(DKartConstantsIfc.FALSE_IND);
				}

				risplDkTaxGrpRule.setTaxRulDes(taxGroupRuleType.getTaxRuleDescription());

				risplDkTaxGrpRule.setTaxRulNme(taxGroupRuleType.getTaxRuleName());

				risplDkTaxGrpRule.setTsCrtRcrd(timestamp);

				risplDkTaxGrpRule.setTsMdfRcrd(timestamp);

				em.merge(risplDkTaxGrpRule);

				// Insert into RISPL_DK_TAX_GRP_RULE table - END

				// Insert into RISPL_DK_TAX_RTE_RULE table - START
				RisplDkTaxRteRule risplDkTaxRteRule = new RisplDkTaxRteRule();
				RisplDkTaxRteRulePK risplDkTaxRteRulePK = new RisplDkTaxRteRulePK();

				risplDkTaxRteRulePK.setAiTxRtRu(Long.valueOf(indx));
				risplDkTaxRteRulePK.setIdGpTx(Long.parseLong(taxGroupRuleType.getTaxGroupID()));
				risplDkTaxRteRulePK.setTaxAuthId(taxGroupRuleType.getTaxAuthorityID().longValue());
				if (taxGroupRuleType.isTaxHolidayFlag()) {
					risplDkTaxRteRulePK.setTaxHldyFlg(DKartConstantsIfc.TRUE_IND);
				} else {
					risplDkTaxRteRulePK.setTaxHldyFlg(DKartConstantsIfc.FALSE_IND);
				}

				risplDkTaxRteRulePK.setTaxTypId(taxGroupRuleType.getTaxTypeID().longValue());

				List<TaxRateRuleType> taxRateRuleTypes = taxGroupRuleType.getTaxRateRule();
				for (TaxRateRuleType taxRateRuleType : taxRateRuleTypes) {

					if (taxRateRuleType.getRateTypeCode() != null) {
						// "Percentage"=1, "Amount"=2
						if (taxRateRuleType.getRateTypeCode().equalsIgnoreCase(DKartConstantsIfc.PERCENTAGE)) {
							risplDkTaxRteRule.setCdTyp(DKartConstantsIfc.PERCENTAGE_VALUE);
						} else {
							risplDkTaxRteRule.setCdTyp(DKartConstantsIfc.AMOUNT_VALUE);
						}
					}

					if (taxRateRuleType.getTaxAboveThresholdAmountFlag() != null
							&& taxRateRuleType.getTaxAboveThresholdAmountFlag().equalsIgnoreCase("true")) {
						risplDkTaxRteRule.setFlTxAbvThMo(DKartConstantsIfc.TRUE_IND);
					} else {
						risplDkTaxRteRule.setFlTxAbvThMo(DKartConstantsIfc.FALSE_IND);
					}

					risplDkTaxRteRule.setId(risplDkTaxRteRulePK);

					risplDkTaxRteRule.setMaxTxblAmt(taxRateRuleType.getMaximumTaxableAmount());
					risplDkTaxRteRule.setMinTxblAmt(taxRateRuleType.getMinimumTaxableAmount());
					risplDkTaxRteRule.setTaxAmt(taxRateRuleType.getTaxAmount());
					risplDkTaxRteRule.setTaxPercnt(taxRateRuleType.getTaxPercentageRate());
					risplDkTaxRteRule.setTaxThrshldAmt(taxRateRuleType.getThresholdAmount());
					if (taxRateRuleType.getTaxRateEffectiveTimestamp() != null) {
						risplDkTaxRteRule.setTsRtTxEf(new Timestamp(taxRateRuleType.getTaxRateEffectiveTimestamp()
								.toGregorianCalendar().getTimeInMillis()));
					}

					if (taxRateRuleType.getTaxRateExpirationTimestamp() != null) {
						risplDkTaxRteRule.setTsRtTxEp(new Timestamp(taxRateRuleType.getTaxRateExpirationTimestamp()
								.toGregorianCalendar().getTimeInMillis()));
					}

					risplDkTaxRteRule.setTsCrtRcrd(timestamp);
					risplDkTaxRteRule.setTsMdfRcrd(timestamp);

					em.merge(risplDkTaxRteRule);

				}

				// Insert into RISPL_DK_TAX_RTE_RULE table - END

			}

			em.getTransaction().commit();
			// em.close();
			LOGGER.info("Tax XML File Processed Successfully ............");
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}
	}
}
