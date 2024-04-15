package com.rispl.roles.access.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.function.RisplDkFunc;
import rispl.db.model.employee.RisplDkEmpRole;
import rispl.db.model.employee.RisplDkEmpRoleAccess;
import rispl.db.model.employee.RisplDkEmpRoleAccessPK;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;

@Stateless(mappedName = "rolesSecurities")
public class EmployeeRoleAccessServiceImpl implements EmployeeRolesAccessRemote {

	private static final Logger LOGGER = LogManager.getLogger(EmployeeRoleAccessServiceImpl.class);

	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	@Override
	public List<EmployeeRoleAccessDTO> fetchEmployeeRolePermission(String roleName) {
		List<EmployeeRoleAccessDTO> roleAccessList = new ArrayList<EmployeeRoleAccessDTO>();
		try {
			EntityManager entityManager = getEntityManager();
			Query roleAccess = entityManager.createNativeQuery(fetchRolePermisssion);
			roleAccess.setParameter(1, roleName.trim());
			@SuppressWarnings("unchecked")
			List<Object[]> permissionList = roleAccess.getResultList();
			for (Object[] permission : permissionList) {
				EmployeeRoleAccessDTO empRoleAccess = new EmployeeRoleAccessDTO();
				empRoleAccess.setRoleID(permission[0].toString());
				empRoleAccess.setRoleName(permission[1].toString());
				empRoleAccess.setFunctionName(permission[2].toString());
				empRoleAccess.setFunctionDescription(permission[3].toString());
				String flag = "";
				if (permission[4] == null) {
					flag = "false";
				} else {
					if (permission[4].toString().equalsIgnoreCase("N")) {
						flag = "false";
					} else if (permission[4].toString().equalsIgnoreCase("Y")) {
						flag = "true";
					}
				}
				empRoleAccess.setHasAccess(flag);
				roleAccessList.add(empRoleAccess);
			}
		} catch (Exception e) {
			LOGGER.error("Unable To Find Employee Role Access Permission Based On Emp Roles=>" + e);
		}
		return roleAccessList;

	}

	@Override
	public boolean assignRoleSpecificPermission(ArrayList<UpdateEmployeeRoleAccess> employeeRoleAcsList,
			String employeeID) {
		boolean flag = false;
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			for (UpdateEmployeeRoleAccess access : employeeRoleAcsList) {
				
				RisplDkEmpRoleAccessPK accessPK = new RisplDkEmpRoleAccessPK();
				accessPK.setRoleId(Long.parseLong(access.getRoleID()));
				accessPK.setFunctionId(access.getFunctionName());

				RisplDkEmpRoleAccess roleAccess = new RisplDkEmpRoleAccess();
				roleAccess.setId(accessPK);
				roleAccess.setCreatedByUserId(employeeID);
				roleAccess.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
				roleAccess.setEffectiveDatetime(new Timestamp(System.currentTimeMillis()));
				String hasAccess = "";
				if(access.getHasAccess().equalsIgnoreCase("false"))
				{
					hasAccess ="N";
				}
				else if(access.getHasAccess().equalsIgnoreCase("true"))
				{
					hasAccess="Y";
				}
				roleAccess.setHasAccess(hasAccess);
				
				RisplDkFunc func = entityManager.find(RisplDkFunc.class, access.getFunctionName());
				
				RisplDkEmpRole empRole = entityManager.find(RisplDkEmpRole.class, Long.parseLong(access.getRoleID()));
				
				roleAccess.setRisplDkEmpRole(empRole);
				roleAccess.setRisplDkFunc(func);

				entityManager.merge(roleAccess);

			}
			entityManager.getTransaction().commit();
			flag = true;

		}

		catch (Exception e) {
			flag = false;
			LOGGER.error("Unable To Update Roles Based Permission In Daabase =>" + e);
		}
		return flag;
	}

	@Override
	public String fetchMaxRoleID() {
		String maxRoleID = "";
		try {
			EntityManager entityManager = getEntityManager();
			Query maxRoleIDQuery = entityManager.createQuery(fetchmaxRoleID);
			Long maxRoleId = (Long) maxRoleIDQuery.getSingleResult();
			maxRoleId = maxRoleId + 1;
			maxRoleID = maxRoleId.toString();
		} catch (Exception e) {
			LOGGER.error("Unable To Fetch The Max Role Id From Database=>" + e);
		}
		return maxRoleID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkSameRoleExistOrNot(String roleName) {
		try{
		EntityManager entityManager = getEntityManager();
		Query roleExist = entityManager.createNativeQuery(checkSameRoleExistOrNot,RisplDkEmpRole.class);
		roleExist.setParameter(1, roleName.trim().toUpperCase());
		List<RisplDkEmpRole> role = new ArrayList<RisplDkEmpRole>();
		role = roleExist.getResultList();
		if(!role.isEmpty())
		{
			return true;
		}
		else{
			return false;
		}
		}
		catch(Exception e)
		{
			LOGGER.error("Unable To Fetch Role Specific Information From Database=>"+e);
			return false;
		}
	}

	@Override
	public boolean createRole(String roleId, String roleName, String homePage,String searchCriteria, String empID) {
		boolean flag = false;
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			RisplDkEmpRole role = new RisplDkEmpRole();
			role.setRoleId(Long.parseLong(roleId));
			role.setCreatedByUserId(new BigDecimal(empID));
			role.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
			role.setEffectiveDatetime(new Timestamp(System.currentTimeMillis()));
			role.setHomePage(homePage);
			role.setRoleDesc(roleName);
			role.setSearchCriteria(searchCriteria);
			entityManager.persist(role);
			entityManager.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			LOGGER.error("Unable To Insert New Role Into RisplDkEmpRole Table=>" + e);
		}
		return flag;
	}

	@Override
	public boolean deleteRole(List<String> roleName) {
		boolean flag = false;
		try {
			EntityManager entityManager = getEntityManager();
			Query rolesDelete = entityManager.createQuery(deleteRoles);
			entityManager.getTransaction().begin();
			int i = rolesDelete.executeUpdate();
			entityManager.getTransaction().commit();
			if (i > 0) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			LOGGER.error("Unable To Delete New Roles From RisplDkEmpRole Table=>" + e);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeRole> fetchAllEmployeeRoles() {
		List<RisplDkEmpRole> emplRoleList = new ArrayList<RisplDkEmpRole>();
		List<EmployeeRole> roleList = new ArrayList<EmployeeRole>();
		try {
			EntityManager entityManager = getEntityManager();
			Query rolesListQuery = entityManager.createQuery(fetchAllRoles);
			emplRoleList = rolesListQuery.getResultList();
			if(!emplRoleList.isEmpty())
			{
				for(RisplDkEmpRole role : emplRoleList)
				{
					EmployeeRole roleDet = new EmployeeRole();
					
					roleDet.setCreatedByUserId(role.getCreatedByUserId());
					roleDet.setCreatedDatetime(role.getCreatedDatetime());
					roleDet.setEffectiveDatetime(role.getEffectiveDatetime());
					roleDet.setEndDatetime(role.getEndDatetime());
					roleDet.setHomePage(role.getHomePage());
					roleDet.setRoleDesc(role.getRoleDesc());
					roleDet.setRoleId(role.getRoleId());
					roleDet.setSearchCriteria(role.getSearchCriteria());
					roleList.add(roleDet);
					
				}
			}
		} catch (Exception e) {
			LOGGER.error("Unable To Feth Roles From RisplDkEmpRole Table=>" + e);
		}
		return roleList;

	}

	@Override
	public boolean updateHomePageBasedEmpRole(ArrayList<UpdateHomePage> homePageList, String employeeID) {
		boolean flag = false;
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			for (UpdateHomePage homePage : homePageList) {
				RisplDkEmpRole empRole = new RisplDkEmpRole();
				empRole.setRoleId(Long.parseLong(homePage.getNewRoleID()));
				empRole.setCreatedByUserId(new BigDecimal(employeeID));
				empRole.setHomePage(homePage.getNewRedirectPage());
				empRole.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
				empRole.setEffectiveDatetime(new Timestamp(System.currentTimeMillis()));
				empRole.setRoleDesc(homePage.getNewRoleDesc());
				empRole.setSearchCriteria(homePage.getFilterCriteria());
				entityManager.merge(empRole);
			}
			entityManager.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			LOGGER.error("Unable To Updaet Home Page For Employee Roles=>" + e);
		}
		return flag;

	}

	private EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emf.createEntityManager();
	}
}
