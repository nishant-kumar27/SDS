package rispl.dime.schedular.imports;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.customer.RisplDkCustSeg;
import rispl.db.model.customer.RisplDkCustSegPK;
import rispl.jaxb.customer.CustomerImport;
import rispl.jaxb.customer.LocalizedNameDescriptionType;
import rispl.jaxb.customer.NameDescriptionType;
import rispl.jaxb.customer.PricingGroup;

public class RisplCustomerSegmentImport {
	private static final Logger LOGGER = LogManager.getLogger(RisplCustomerSegmentImport.class);

	public static void readAndPersistCustomerSegment(InputStream inputStream, EntityManager em) throws Exception {
		try {
			LOGGER.info("Processing CustomerSegment XML File.....................");
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}

			JAXBContext jaxbContext = JAXBContext.newInstance(CustomerImport.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CustomerImport customerImport = (CustomerImport) jaxbUnmarshaller.unmarshal(inputStream);

			List<Object> removeEntities = new ArrayList<Object>();
			List<Object> mergeEntities = new ArrayList<Object>();

			List<PricingGroup> pricingGroupTypes = customerImport.getPricingGroup();
			for (PricingGroup pricingGroupType : pricingGroupTypes) {
				List<LocalizedNameDescriptionType> localizedDescriptionTypes = pricingGroupType.getLocalizedName();
				if (localizedDescriptionTypes != null && localizedDescriptionTypes.size() > 0) {
					for (LocalizedNameDescriptionType localizedDescriptionType : localizedDescriptionTypes) {
						RisplDkCustSeg risplDkCustSeg = new RisplDkCustSeg();
						RisplDkCustSegPK risplDkCustSegPK = new RisplDkCustSegPK();

						risplDkCustSegPK.setPrcngGrpId(pricingGroupType.getID());
						risplDkCustSegPK.setLcl(localizedDescriptionType.getLanguage().value());

						risplDkCustSeg.setId(risplDkCustSegPK);
						risplDkCustSeg.setPrcngGrpNme(localizedDescriptionType.getName());
						risplDkCustSeg.setPrcngGrpDes(localizedDescriptionType.getDescription());
						if (pricingGroupType.getChangeType() != null
								&& pricingGroupType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							removeEntities.add(risplDkCustSeg);
						} else {
							mergeEntities.add(risplDkCustSeg);
						}
					}
				} else {
					NameDescriptionType nameDescriptionType = pricingGroupType.getName();
					RisplDkCustSeg risplDkCustSeg = new RisplDkCustSeg();
					RisplDkCustSegPK risplDkCustSegPK = new RisplDkCustSegPK();

					risplDkCustSegPK.setPrcngGrpId(pricingGroupType.getID());
					risplDkCustSegPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

					risplDkCustSeg.setId(risplDkCustSegPK);
					risplDkCustSeg.setPrcngGrpNme(nameDescriptionType.getName());
					risplDkCustSeg.setPrcngGrpDes(nameDescriptionType.getDescription());

					if (pricingGroupType.getChangeType() != null
							&& pricingGroupType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						removeEntities.add(risplDkCustSeg);
					} else {
						mergeEntities.add(risplDkCustSeg);
					}
				}
			}

			// Perform DB operations
			if (customerImport.getFillType() != null
					&& customerImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkCustSeg e").executeUpdate();
			}

			// to remove
			if (removeEntities.size() > 0 && customerImport.getFillType() != null
					&& !customerImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object rmvObj : removeEntities) {
					em.remove(rmvObj);
				}
			}

			// to merge or persist
			if (mergeEntities.size() > 0 && customerImport.getFillType() != null
					&& !customerImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object mrgObj : mergeEntities) {
					RisplDkCustSeg risplDkCustSeg = (RisplDkCustSeg) mrgObj;
					if (em.find(RisplDkCustSeg.class, risplDkCustSeg.getId()) == null) {
						em.persist(risplDkCustSeg);
					} else {
						em.merge(risplDkCustSeg);
					}
				}
			} else if (mergeEntities.size() > 0 && customerImport.getFillType() != null
					&& customerImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				for (Object prsstObj : mergeEntities) {
					em.persist(prsstObj);
				}
			}

			em.getTransaction().commit();
			LOGGER.info("CustomerSegment XML File Processed Successfully .....................");
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}
	}

	public static void readAndPersistCustomerSegment2(InputStream inputStream, EntityManager em) throws Exception {
		try {
			LOGGER.info("Processing CustomerSegment XML File.....................");
			em.getTransaction().begin();

			JAXBContext jaxbContext = JAXBContext.newInstance(CustomerImport.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CustomerImport customerImport = (CustomerImport) jaxbUnmarshaller.unmarshal(inputStream);

			if (customerImport.getFillType() != null
					&& customerImport.getFillType().value().equalsIgnoreCase(DKartConstantsIfc.KILL_AND_FILL)) {
				em.createQuery("DELETE FROM RisplDkCustSeg e").executeUpdate();
				em.getTransaction().commit();
				em.getTransaction().begin();
			}
			List<PricingGroup> pricingGroupTypes = customerImport.getPricingGroup();
			for (PricingGroup pricingGroupType : pricingGroupTypes) {
				List<LocalizedNameDescriptionType> localizedDescriptionTypes = pricingGroupType.getLocalizedName();
				if (localizedDescriptionTypes != null && localizedDescriptionTypes.size() > 0) {
					for (LocalizedNameDescriptionType localizedDescriptionType : localizedDescriptionTypes) {
						RisplDkCustSeg risplDkCustSeg = new RisplDkCustSeg();
						RisplDkCustSegPK risplDkCustSegPK = new RisplDkCustSegPK();

						risplDkCustSegPK.setPrcngGrpId(pricingGroupType.getID());
						risplDkCustSegPK.setLcl(localizedDescriptionType.getLanguage().value());

						risplDkCustSeg.setId(risplDkCustSegPK);
						risplDkCustSeg.setPrcngGrpNme(localizedDescriptionType.getName());
						risplDkCustSeg.setPrcngGrpDes(localizedDescriptionType.getDescription());
						if (pricingGroupType.getChangeType() != null
								&& pricingGroupType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
							risplDkCustSeg = em.find(RisplDkCustSeg.class, risplDkCustSeg.getId());
							if (risplDkCustSeg != null) {
								em.remove(risplDkCustSeg);
							}
						} else {
							em.merge(risplDkCustSeg);
						}
					}
				} else {
					NameDescriptionType nameDescriptionType = pricingGroupType.getName();
					RisplDkCustSeg risplDkCustSeg = new RisplDkCustSeg();
					RisplDkCustSegPK risplDkCustSegPK = new RisplDkCustSegPK();

					risplDkCustSegPK.setPrcngGrpId(pricingGroupType.getID());
					risplDkCustSegPK.setLcl(DKartConstantsIfc.DEFAULT_LOCALE);

					risplDkCustSeg.setId(risplDkCustSegPK);
					risplDkCustSeg.setPrcngGrpNme(nameDescriptionType.getName());
					risplDkCustSeg.setPrcngGrpDes(nameDescriptionType.getDescription());

					if (pricingGroupType.getChangeType() != null
							&& pricingGroupType.getChangeType().value().equalsIgnoreCase(DKartConstantsIfc.DEL)) {
						risplDkCustSeg = em.find(RisplDkCustSeg.class, risplDkCustSeg.getId());
						if (risplDkCustSeg != null) {
							em.remove(risplDkCustSeg);
						}
					} else {
						em.merge(risplDkCustSeg);
					}
				}
			}

			LOGGER.info("CustomerSegment XML File Processed Successfully .....................");
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}
		}

	}
}
