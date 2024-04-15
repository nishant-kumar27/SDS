package rispl.db.model.employee;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the EMP_V database table.
 * 
 */
@Entity
@Table(name="EMP_V")
@NamedQuery(name="EmployeeV.findAll", query="SELECT e FROM EmployeeV e")
public class EmployeeV implements Serializable {
	private static final long serialVersionUID = 1L;

	private String division;

	private String email;

	@Id
	@Column(name="EMP_ID")
	private String empId;

	@Column(name="EMP_NME")
	private String empNme;

	@Column(name="LOGIN_ID")
	private String loginId;

	@Column(name="ROLE_ID")
	private BigDecimal roleId;

	@Column(name="ROLE_NAME")
	private String roleName;

	@Column(name="SEARCH_CRITERIA")
	private String searchCriteria;
	
	private String status;

	public EmployeeV() {
	}

	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpNme() {
		return this.empNme;
	}

	public void setEmpNme(String empNme) {
		this.empNme = empNme;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public BigDecimal getRoleId() {
		return this.roleId;
	}

	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}