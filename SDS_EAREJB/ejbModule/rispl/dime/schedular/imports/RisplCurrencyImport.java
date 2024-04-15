package rispl.dime.schedular.imports;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.currency.RisplDkCurr;
import rispl.db.model.currency.RisplDkCurrExcRt;
import rispl.db.model.currency.RisplDkCurrExcRtPK;
import rispl.jaxb.currency.CurrencyImport;
import rispl.jaxb.currency.CurrencyType;
import rispl.jaxb.currency.ExchangeRateType;

public class RisplCurrencyImport {
	private static final Logger LOGGER = LogManager.getLogger(RisplCurrencyImport.class);

	public static void readXmlAndPersist(InputStream inputStream, EntityManager em) throws Exception {

		try {
			LOGGER.info("Processing Currency XML file..............");
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}

			JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyImport.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CurrencyImport currencyImport = (CurrencyImport) jaxbUnmarshaller.unmarshal(inputStream);

			List<Object> delEntities = new ArrayList<Object>();
			List<Object> addEntities = new ArrayList<Object>();
			List<Object> updEntities = new ArrayList<Object>();
			
			// Insert to RISPL_DK_CURR table START

			List<CurrencyType> currencyTypes = currencyImport.getCurrency();
			for (CurrencyType currencyType : currencyTypes) {
				RisplDkCurr risplDkCurr = new RisplDkCurr();
				if (currencyType.isIsBaseCurrency()) {
					risplDkCurr.setCurrBaseFlg(DKartConstantsIfc.TRUE_IND);
				} else {
					risplDkCurr.setCurrBaseFlg(DKartConstantsIfc.FALSE_IND);
				}

				risplDkCurr.setCurrContCde(currencyType.getIssuingCountryCode());
				risplDkCurr.setCurrDesc(currencyType.getName());
				risplDkCurr.setCurrId(currencyType.getISOCode());
				risplDkCurr.setCurrIsoCde(currencyType.getISOCode());
				risplDkCurr.setCurrIssgContNat(currencyType.getIssuingCountryNationality());
				if (currencyType.getPriority() != null) {
					risplDkCurr.setCurrPri(new BigDecimal(currencyType.getPriority()));
				}
				risplDkCurr.setCurrScle(new BigDecimal(currencyType.getScale()));
				// risplDkCurr.setIsoCurrNumb();

				if (currencyType.getChangeType() != null
						&& currencyType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
					delEntities.add(risplDkCurr);
				}else if (currencyType.getChangeType() != null
						&& currencyType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.UPD)) {
					updEntities.add(risplDkCurr);
				}else {
					addEntities.add(risplDkCurr);
				}
			}
			// Insert to RISPL_DK_CURR table END

			// Insert to RISPL_DK_CURR_EXC_RT table START
			List<ExchangeRateType> exchangeRateTypes = currencyImport.getExchangeRate();
			for (ExchangeRateType exchangeRateType : exchangeRateTypes) {
				RisplDkCurrExcRt risplDkCurrExcRt = new RisplDkCurrExcRt();
				RisplDkCurrExcRtPK risplDkCurrExcRtPK = new RisplDkCurrExcRtPK();

				if (exchangeRateType.getEffectiveDate() != null) {
					risplDkCurrExcRtPK
							.setDcRtExcEf(exchangeRateType.getEffectiveDate().toGregorianCalendar().getTime());
				}
				if (exchangeRateType.getExpirationDate() != null) {
					risplDkCurrExcRtPK
							.setDcRtExcEp(exchangeRateType.getExpirationDate().toGregorianCalendar().getTime());
				}
				risplDkCurrExcRtPK.setCurrIsoCde(exchangeRateType.getCurrencyCode());

				risplDkCurrExcRt.setId(risplDkCurrExcRtPK);
				risplDkCurrExcRt.setLlCnyExc(exchangeRateType.getMinimumAmount());
				risplDkCurrExcRt.setMoFeSvExc(exchangeRateType.getServiceFeeAmount());
				risplDkCurrExcRt.setMoRtToBuy(exchangeRateType.getToBuyAmount());
				risplDkCurrExcRt.setMoRtToSl(exchangeRateType.getToSellAmount());

				if (exchangeRateType.getChangeType() != null
						&& exchangeRateType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
					delEntities.add(risplDkCurrExcRt);
				}else if (exchangeRateType.getChangeType() != null
						&& exchangeRateType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.UPD)) { 
					updEntities.add(risplDkCurrExcRt);
				}else {
					addEntities.add(risplDkCurrExcRt);
				}
			}
			// Insert to RISPL_DK_CURR_EXC_RT table END

			// Perform DB operations
			// If KillAndFill
			if (currencyImport.getFillType() != null
					&& currencyImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkCurr e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkCurrExcRt e").executeUpdate();
			}

			// to remove
			if (delEntities.size() > 0 && currencyImport.getFillType() != null
					&& !currencyImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object rmvObj : delEntities) {
					if (rmvObj instanceof RisplDkCurr) {
						RisplDkCurr risplDkCurr = (RisplDkCurr) rmvObj;
						risplDkCurr = em.find(RisplDkCurr.class, risplDkCurr.getCurrId());
						if (risplDkCurr != null) {
							em.remove(risplDkCurr);
						} 
					} else if (rmvObj instanceof RisplDkCurrExcRt) {
						RisplDkCurrExcRt risplDkCurrExcRt = (RisplDkCurrExcRt) rmvObj;
						risplDkCurrExcRt = em.find(RisplDkCurrExcRt.class, risplDkCurrExcRt.getId());
						if (risplDkCurrExcRt != null) {
							em.remove(risplDkCurrExcRt);
						} 
					}
				}
				em.getTransaction().commit();
				em.getTransaction().begin();
			}

			// to merge or persist
			if (addEntities.size() > 0 && currencyImport.getFillType() != null
					&& !currencyImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object mrgObj : addEntities) {
					if (mrgObj instanceof RisplDkCurr) {
						RisplDkCurr risplDkCurr = (RisplDkCurr) mrgObj;
						if (em.find(RisplDkCurr.class, risplDkCurr.getCurrId()) != null) {
							em.merge(risplDkCurr);
						} else {
							em.persist(risplDkCurr);
						}
					} else if (mrgObj instanceof RisplDkCurrExcRt) {
						RisplDkCurrExcRt risplDkCurrExcRt = (RisplDkCurrExcRt) mrgObj;
						if (em.find(RisplDkCurrExcRt.class, risplDkCurrExcRt.getId()) != null) {
							em.merge(risplDkCurrExcRt);
						} else {
							em.persist(risplDkCurrExcRt);
						}
					}
				}
			} else if (addEntities.size() > 0 && currencyImport.getFillType() != null
					&& currencyImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object prsstObj : addEntities) {
					em.persist(prsstObj);
				}
			}
			
			// to update
			if (updEntities.size() > 0 ) {
				for (Object updObj : updEntities) {
					if (updObj instanceof RisplDkCurr) {
						RisplDkCurr risplDkCurr = (RisplDkCurr) updObj;
						if (em.find(RisplDkCurr.class, risplDkCurr.getCurrId()) != null) {
							em.merge(risplDkCurr);
						} 
					} else if (updObj instanceof RisplDkCurrExcRt) {
						RisplDkCurrExcRt risplDkCurrExcRt = (RisplDkCurrExcRt) updObj;
						if (em.find(RisplDkCurrExcRt.class, risplDkCurrExcRt.getId()) != null) {
							em.merge(risplDkCurrExcRt);
						}
					}
				}
			}
			
			em.getTransaction().commit();
			LOGGER.info("Currency XML File Processed Successfully.........");
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

	}

	public static void readXmlAndPersist2(InputStream inputStream, EntityManager em) throws Exception {

		try {
			LOGGER.info("Processing Currency XML file..............");
			em.getTransaction().begin();

			JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyImport.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CurrencyImport currencyImport = (CurrencyImport) jaxbUnmarshaller.unmarshal(inputStream);

			// If KillAndFill
			if (currencyImport.getFillType() != null
					&& currencyImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkCurr e").executeUpdate();
				em.createQuery("DELETE FROM RisplDkCurrExcRt e").executeUpdate();
				em.getTransaction().commit();
				em.getTransaction().begin();
			}

			// Insert to RISPL_DK_CURR table START

			List<CurrencyType> currencyTypes = currencyImport.getCurrency();
			for (CurrencyType currencyType : currencyTypes) {
				RisplDkCurr risplDkCurr = new RisplDkCurr();
				if (currencyType.isIsBaseCurrency()) {
					risplDkCurr.setCurrBaseFlg(DKartConstantsIfc.TRUE_IND);
				} else {
					risplDkCurr.setCurrBaseFlg(DKartConstantsIfc.FALSE_IND);
				}

				risplDkCurr.setCurrContCde(currencyType.getIssuingCountryCode());
				risplDkCurr.setCurrDesc(currencyType.getName());
				risplDkCurr.setCurrId(currencyType.getISOCode());
				risplDkCurr.setCurrIsoCde(currencyType.getISOCode());
				risplDkCurr.setCurrIssgContNat(currencyType.getIssuingCountryNationality());
				if (currencyType.getPriority() != null) {
					risplDkCurr.setCurrPri(new BigDecimal(currencyType.getPriority()));
				}
				risplDkCurr.setCurrScle(new BigDecimal(currencyType.getScale()));
				// risplDkCurr.setIsoCurrNumb();

				if (currencyType.getChangeType() != null
						&& currencyType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
					risplDkCurr = em.find(RisplDkCurr.class, risplDkCurr.getCurrId());
					if (risplDkCurr != null) {
						em.remove(risplDkCurr);
					}
				} else {
					em.merge(risplDkCurr);
				}

			}
			// Insert to RISPL_DK_CURR table END

			// Insert to RISPL_DK_CURR_EXC_RT table START
			List<ExchangeRateType> exchangeRateTypes = currencyImport.getExchangeRate();
			for (ExchangeRateType exchangeRateType : exchangeRateTypes) {
				RisplDkCurrExcRt risplDkCurrExcRt = new RisplDkCurrExcRt();
				RisplDkCurrExcRtPK risplDkCurrExcRtPK = new RisplDkCurrExcRtPK();

				if (exchangeRateType.getEffectiveDate() != null) {
					risplDkCurrExcRtPK
							.setDcRtExcEf(exchangeRateType.getEffectiveDate().toGregorianCalendar().getTime());
				}
				if (exchangeRateType.getExpirationDate() != null) {
					risplDkCurrExcRtPK
							.setDcRtExcEp(exchangeRateType.getExpirationDate().toGregorianCalendar().getTime());
				}
				risplDkCurrExcRtPK.setCurrIsoCde(exchangeRateType.getCurrencyCode());

				risplDkCurrExcRt.setId(risplDkCurrExcRtPK);
				risplDkCurrExcRt.setLlCnyExc(exchangeRateType.getMinimumAmount());
				risplDkCurrExcRt.setMoFeSvExc(exchangeRateType.getServiceFeeAmount());
				risplDkCurrExcRt.setMoRtToBuy(exchangeRateType.getToBuyAmount());
				risplDkCurrExcRt.setMoRtToSl(exchangeRateType.getToSellAmount());

				if (exchangeRateType.getChangeType() != null
						&& exchangeRateType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
					risplDkCurrExcRt = em.find(RisplDkCurrExcRt.class, risplDkCurrExcRt.getId());
					if (risplDkCurrExcRt != null) {
						em.remove(risplDkCurrExcRt);
					}
				} else {
					em.merge(risplDkCurrExcRt);
				}

			}
			// Insert to RISPL_DK_CURR_EXC_RT table END

			em.getTransaction().commit();
			// em.close();
			LOGGER.info("Currency XML File Processed Successfully.........");
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

	}

	@SuppressWarnings("finally")
	public Timestamp convertXmlDateToTimestamp(String xmlValue) {
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(DKartConstantsIfc.SMPL_DTE_FRMT.parse(xmlValue).getTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return timestamp;
		}
	}

}
