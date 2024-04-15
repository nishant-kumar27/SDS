package rispl.services.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.retailsols.dkart.login.BaseKeyStoreEncryptionService;

import rispl.db.model.employee.RisplDkEmpMerchAssoc;
import rispl.db.model.employee.RisplDkEmpMstr;
import rispl.db.model.merchandisehierarchy.RisplDkMrchGrp;
import rispl.db.model.merchandisehierarchy.RisplDkMrchGrpPK;
import rispl.dk.Employee.EmpMerchAssociation;
import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.Employee;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkservices.common.EmployeeSearchCriteriaIfc;

public class AbstractEmployeeService {

	protected EntityManager em;
	/*
	 * class is used to perform all the DAO actions for employees
	 */
	private static final Logger LOGGER = LogManager.getLogger(AbstractEmployeeService.class);

	/**
	 * Set roleId*, employeeId, salesAgent
	 * 
	 * @return EmployeeIfc[]
	 * @throws EmployeeFindException
	 */
	public EmployeeIfc[] lookupEmployeeByRoleId(EmployeeSearchCriteriaIfc search) throws EmployeeFindException {

		String empId = null;
		if (search.getEmployeeId() != null && search.getEmployeeId().trim().length() > 0) {
			empId = search.getEmployeeId();
		}
		String empName = null;
		if (search.getSalesAgent() != null && search.getSalesAgent().trim().length() > 0) {
			empName = search.getSalesAgent();
		}
		Vector<EmployeeIfc> employee = null;
		EmployeeIfc[] employeeList = null;
		try {
			TypedQuery<RisplDkEmpMstr> qe = em.createNamedQuery("FIND_EMPLOYEE_BY_ROLE", RisplDkEmpMstr.class)
					.setParameter("agentId", empId)
					.setParameter("agentName", empName)
					.setParameter("roleId", search.getRoleId());
			LOGGER.info("FIND_EMPLOYEE_BY_ROLE query execeuted........");
			List<RisplDkEmpMstr> list = qe.getResultList();
			employee = new Vector<EmployeeIfc>();
			for (int i = 0; i < list.size(); i++) {
				RisplDkEmpMstr master = list.get(i);
				EmployeeIfc emp = new Employee();
				/*
				 * only limited information required for the sales agent to link
				 */
				emp.setAlternateId(master.getAltId());
				emp.setEmployeeFirstName(master.getEmpFstNme());
				emp.setEmployeeId(master.getId().getEmpId());
				emp.setLoginId(master.getLoginId());
				emp.setEmployeeMiddleName(master.getEmpMdlNme());
				emp.setEmployeeName(master.getEmpNme());
				emp.setEmail(master.getEmail());
				emp.setMerchAssoc(setEmpMerchAssoc(master.getRisplDkEmpMerchAssocs()));

				employee.add(emp);

			}
			employeeList = new EmployeeIfc[employee.size()];
			employee.copyInto(employeeList);
		} catch (Exception e) {
			LOGGER.error("Exception in lookupSalesAgents....");
			throw new EmployeeFindException("lookUpSalesAgent : Unable to find the employee " + e.getMessage());
		} finally {
			em.close();
		}
		return employeeList;
	}

	public EmployeeIfc lookupEmployeeLogIn(EmployeeSearchCriteriaIfc search) throws EmployeeFindException {
		////password is required
		EmployeeIfc emp = null;
		try {
			Query qe = em.createNamedQuery("FIND_EMPLOYEE", RisplDkEmpMstr.class).setParameter("employeeLogInId",
					search.getLoginId());
			LOGGER.info("FIND_EMPLOYEE query execeuted........");
			List<RisplDkEmpMstr> list = qe.getResultList();

			if (list.size() > 0) {
				RisplDkEmpMstr master = list.get(0);
				emp = castEmpMstrToEmpIfc(master);
				/*emp.setAccessPassword(master.getEmpAcsPwd());
				emp.setSaltPassword(master.getEmpPwdSlt());
				emp.setAlternateId(master.getAltId());
				emp.setEmployeeFirstName(master.getEmpFstNme());
				emp.setEmployeeId(master.getId().getEmpId());
				emp.setLoginId(master.getLoginId());
				emp.setEmployeeLocale(master.getEmpLcl());
				emp.setEmployeeMiddleName(master.getEmpMdlNme());
				emp.setEmployeeName(master.getEmpNme());
				emp.setEmployeeStatusCode(master.getEmpStsCde());
				emp.setEmployeeType(master.getEmpType().intValue());
				emp.setGroupId(master.getGpId());
				emp.setStoreGroupFunction(master.getIdStrgpFnc().intValue());
				emp.setStoreId(master.getId().getIdStrRt());
				emp.setGroupType(master.getGpType());
				//Set Role ID and Role Name
				emp.setRoleId(master.getRisplDkEmpRole().getRoleId());
				emp.setRoleName(master.getRisplDkEmpRole().getRoleDesc());
				// Set Division ID and Division Name
				emp.setMerchAssoc(setEmpMerchAssoc(master.getRisplDkEmpMerchAssocs()));

				if (master.getFlPwNwReq().equalsIgnoreCase("1")) {
					emp.setNewPasswordRequired(true);
				} else {
					emp.setNewPasswordRequired(false);
				}
				///emp.setLastLogin(master.getTsLoginLst().toString());*/
			}
		} catch (Exception e) {
			LOGGER.error("Exception in lookupEmployeeLogIn....throws EmployeeFindException");
			throw new EmployeeFindException(
					"caught exception will finding the employee in lookUpEmployee  " + e.getMessage());
		} finally {
			em.close();
		}
		return emp;
	}

	public EmployeeIfc lookupEmployeeLogInOrEmpId(EmployeeSearchCriteriaIfc search) throws EmployeeFindException {
		////password is required
		EmployeeIfc emp = null;
		try {
			TypedQuery<RisplDkEmpMstr> qe = em.createNamedQuery("FIND_EMPLOYEE_BY_LOGINID_OR_EMPID", RisplDkEmpMstr.class)
					.setParameter("employeeLogInId", search.getLoginId())
					.setParameter("employeeId", search.getEmployeeId());
			LOGGER.info("FIND_EMPLOYEE_BY_LOGINID_OR_EMPID query execeuted........");
			List<RisplDkEmpMstr> list = qe.getResultList();

			if (list.size() > 0) {
				RisplDkEmpMstr master = list.get(0);
				emp = castEmpMstrToEmpIfc(master);
				/*emp.setAccessPassword(master.getEmpAcsPwd());
				emp.setSaltPassword(master.getEmpPwdSlt());
				emp.setAlternateId(master.getAltId());
				emp.setEmployeeFirstName(master.getEmpFstNme());
				emp.setEmployeeId(master.getId().getEmpId());
				emp.setLoginId(master.getLoginId());
				emp.setEmployeeLocale(master.getEmpLcl());
				emp.setEmployeeMiddleName(master.getEmpMdlNme());
				emp.setEmployeeName(master.getEmpNme());
				emp.setEmployeeStatusCode(master.getEmpStsCde());
				emp.setEmployeeType(master.getEmpType().intValue());
				emp.setGroupId(master.getGpId());
				emp.setStoreGroupFunction(master.getIdStrgpFnc().intValue());
				emp.setStoreId(master.getId().getIdStrRt());
				emp.setGroupType(master.getGpType());
				//Set Role ID and Role Name
				emp.setRoleId(master.getRisplDkEmpRole().getRoleId());
				emp.setRoleName(master.getRisplDkEmpRole().getRoleDesc());
				// Set Division ID and Division Name
				emp.setMerchAssoc(setEmpMerchAssoc(master.getRisplDkEmpMerchAssocs()));

				if (master.getFlPwNwReq().equalsIgnoreCase("1")) {
					emp.setNewPasswordRequired(true);
				} else {
					emp.setNewPasswordRequired(false);
				}
				///emp.setLastLogin(master.getTsLoginLst().toString());*/
			}
		} catch (Exception e) {
			LOGGER.error("Exception in lookupEmployeeLogInOrEmpId....throws EmployeeFindException");
			throw new EmployeeFindException(
					"caught exception will finding the employee in lookupEmployeeLogInOrEmpId  " + e.getMessage());
		} finally {
			em.close();
		}
		return emp;
	}
	
	private List<EmpMerchAssociationIfc> setEmpMerchAssoc(List<RisplDkEmpMerchAssoc> merchAssoc) {
		// Handle typecasting based on list instance of RisplDkEmpMerchAssoc
		if (!merchAssoc.isEmpty()) {

			List<EmpMerchAssociationIfc> empMerchAssocList = new ArrayList<EmpMerchAssociationIfc>();

			for (RisplDkEmpMerchAssoc eachMerchAssoc : merchAssoc) {
				if (eachMerchAssoc.getId() != null) {
					EmpMerchAssociationIfc merchAssocNew = new EmpMerchAssociation();
					merchAssocNew.setMerchId(eachMerchAssoc.getId().getMerchId());
					//TOOD Determine merch name and type
					merchAssocNew.setMerchName(getMerchNameById(eachMerchAssoc.getId().getMerchId()));
					merchAssocNew.setMerchType(null);
					empMerchAssocList.add(merchAssocNew);
				}
			}

			return empMerchAssocList;
		}

		return null;
	}

	//Find division name
	protected String getMerchNameById(String merchGroupId) {
		String merchGroupName = null;
		if (merchGroupId != null) {
			RisplDkMrchGrpPK mrchGrpPK = new RisplDkMrchGrpPK();
			mrchGrpPK.setIdMrhrcGp(merchGroupId);
			mrchGrpPK.setLcl("en");
			RisplDkMrchGrp mrchGrp = em.find(RisplDkMrchGrp.class, mrchGrpPK);
			if (mrchGrp!=null && mrchGrp.getNmMrhrcGp() != null)//mrchGrp!=null &&  added by hanu to add sales agents
				merchGroupName = mrchGrp.getNmMrhrcGp();
		}
		return merchGroupName;
	}

	//to get the sales agent name @mallikarjun
	public ArrayList<String> retrivSalesAgents(String[] emids) throws EmployeeFindException {
		ArrayList<String> agntList = new ArrayList<String>();

		try {/*
				 * String xy = ""; int cnt=0; while(cnt<emids.length){ String x;
				 * if(cnt<emids.length-1){ x="'"+emids[cnt]+"',"; }else{
				 * x="'"+emids[cnt]+"'"; } xy=xy.concat(x); cnt=cnt+1; }
				 */
			for (int i = 0; i < emids.length; i++) {
				String sqlQry = "select e.emp_fst_nme from rispl_dk_emp_mstr e where e.emp_id=" + emids[i];
				Query qe = em.createNativeQuery(sqlQry);
				@SuppressWarnings("unchecked")
				List<String> agntName = qe.getResultList();
				agntList.addAll(agntName);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in retrivSalesAgents....throws EmployeeFindException");
			throw new EmployeeFindException("caught exception will finding the Sales Agents " + e.getMessage());
		} finally {
			em.close();
		}

		return agntList;

	}
	
	public EmployeeIfc castEmpMstrToEmpIfc(RisplDkEmpMstr empMstr) {
		if (empMstr == null)
			return null;
		EmployeeIfc empIfc = new Employee();
		empIfc.setEmail(empMstr.getEmail());
		empIfc.setAccessPassword(empMstr.getEmpAcsPwd());
		empIfc.setSaltPassword(empMstr.getEmpPwdSlt());
		empIfc.setAlternateId(empMstr.getAltId());
		empIfc.setEmployeeFirstName(empMstr.getEmpFstNme());
		if(empMstr.getId()!=null)
			empIfc.setEmployeeId(empMstr.getId().getEmpId());
		empIfc.setLoginId(empMstr.getLoginId());
		empIfc.setEmployeeLocale(empMstr.getEmpLcl());
		empIfc.setEmployeeMiddleName(empMstr.getEmpMdlNme());
		empIfc.setEmployeeName(empMstr.getEmpNme());
		empIfc.setEmployeeStatusCode(empMstr.getEmpStsCde());
		empIfc.setEmployeeType(empMstr.getEmpType().intValue());
		empIfc.setGroupId(empMstr.getGpId());
		empIfc.setStoreGroupFunction(empMstr.getIdStrgpFnc().intValue());
		empIfc.setStoreId(empMstr.getId().getIdStrRt());
		empIfc.setGroupType(empMstr.getGpType());
		//Set Role ID and Role Name
		empIfc.setRoleId(empMstr.getRisplDkEmpRole().getRoleId());
		empIfc.setRoleName(empMstr.getRisplDkEmpRole().getRoleDesc());
		// Set Division ID and Division Name
		empIfc.setMerchAssoc(setEmpMerchAssoc(empMstr.getRisplDkEmpMerchAssocs()));
		//set employee role access
		
		empIfc.setRoleAccess(empMstr.getRisplDkEmpRole().getSearchCriteria());
		if (empMstr.getFlPwNwReq().equalsIgnoreCase("1")) {
			empIfc.setNewPasswordRequired(true);
		} else {
			empIfc.setNewPasswordRequired(false);
		}
		empIfc.setPswdCreateTime(empMstr.getTsCrtPw());
		return empIfc;
	}

	protected String getEncryptedPassword(String password, String salt)
	{
		try {
			byte[] encBytes = new BaseKeyStoreEncryptionService().superHash(password.getBytes(),salt, false);
			String enc_password=new BaseKeyStoreEncryptionService().getBase64encode(encBytes);
			return enc_password;
		}catch(Exception e)
		{
			LOGGER.error("Error during hashing of password", e.getCause());
		}
		return null;
	}
}
