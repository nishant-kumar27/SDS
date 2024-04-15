package rispl.dkart.services.ejb.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.db.model.employee.RisplDkEmpRole;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import utility.ConfigUtils;

@Stateless(mappedName = "utilsBean")
@LocalBean
public class UtilsBean implements UtilsBeanRemote {

	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	private static final Logger LOGGER = LogManager.getLogger(UtilsBean.class);

	public UtilsBean() {
	}

	@Override
	public String getHomePageForRole(long roleID) {
		EntityManager em = emf.createEntityManager();

		RisplDkEmpRole empRole = em.find(RisplDkEmpRole.class, roleID);

		if (empRole != null && empRole.getHomePage() != null)
			return empRole.getHomePage();
		return "";
	}

	@Override
	public Map<String, String> getEJBConfig() {
		Map<String, String> configMap = new LinkedHashMap<String, String>();
		ConfigUtils cu = ConfigUtils.getInstance();

		try {
			configMap.put("RMS Url", cu.getRMSInventoryUrl());
			configMap.put("Warehouse Location Id", cu.getWarehouseLocationID());
			configMap.put("Warehouse Channel Id", cu.getWarehouseChannelID());
			configMap.put("Dime Incoming Path", cu.getDIMEIncomingPath());
			configMap.put("Dime Backup Path", cu.getDIMEBackupPath());
			configMap.put("SDS Store Id", cu.getSDSStoreID());
			configMap.put("SDS Workstation Id", cu.getSDSWorkstationID());
			configMap.put("Customer Order Service Url", cu.getCustomerOrderServiceUrl().toString());
			configMap.put("Email Jndi Name", cu.getEmailJndiServiceName());
			configMap.put("JPA Data Source", cu.getJPADataSource());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e.getCause());
		}

		return configMap;
	}

}
