package rispl.db.model.employee;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the RISPL_DK_EMP_ROLE database table.
 * 
 */
@Entity
@Table(name = "RISPL_DK_EMP_ROLE")
@NamedQuery(name = "RisplDkEmpRole.findAll", query = "SELECT r FROM RisplDkEmpRole r")
public class RisplDkEmpRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROLE_ID")
	private long roleId;

	@Column(name = "CREATED_BY_USER_ID")
	private BigDecimal createdByUserId;

	@Column(name = "CREATED_DATETIME")
	private Timestamp createdDatetime;

	@Column(name = "EFFECTIVE_DATETIME")
	private Timestamp effectiveDatetime;

	@Column(name = "END_DATETIME")
	private Timestamp endDatetime;

	@Column(name = "ROLE_DESC")
	private String roleDesc;

	@Column(name = "HOME_PAGE")
	private String homePage;
	
	@Column(name = "SEARCH_CRITERIA")
	private String searchCriteria;
	

	//bi-directional many-to-one association to RisplDkEmpMstr
	@OneToMany(mappedBy = "risplDkEmpRole", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<RisplDkEmpMstr> risplDkEmpMstrs;

	//bi-directional many-to-one association to RisplDkEmpRoleAccess
	@OneToMany(mappedBy = "risplDkEmpRole", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<RisplDkEmpRoleAccess> risplDkEmpRoleAccesses;

	public RisplDkEmpRole() {
	}

	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public BigDecimal getCreatedByUserId() {
		return this.createdByUserId;
	}

	public void setCreatedByUserId(BigDecimal createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Timestamp getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getEffectiveDatetime() {
		return this.effectiveDatetime;
	}

	public void setEffectiveDatetime(Timestamp effectiveDatetime) {
		this.effectiveDatetime = effectiveDatetime;
	}

	public Timestamp getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<RisplDkEmpMstr> getRisplDkEmpMstrs() {
		return this.risplDkEmpMstrs;
	}

	public void setRisplDkEmpMstrs(List<RisplDkEmpMstr> risplDkEmpMstrs) {
		this.risplDkEmpMstrs = risplDkEmpMstrs;
	}

	public RisplDkEmpMstr addRisplDkEmpMstr(RisplDkEmpMstr risplDkEmpMstr) {
		getRisplDkEmpMstrs().add(risplDkEmpMstr);
		risplDkEmpMstr.setRisplDkEmpRole(this);

		return risplDkEmpMstr;
	}

	public RisplDkEmpMstr removeRisplDkEmpMstr(RisplDkEmpMstr risplDkEmpMstr) {
		getRisplDkEmpMstrs().remove(risplDkEmpMstr);
		risplDkEmpMstr.setRisplDkEmpRole(null);

		return risplDkEmpMstr;
	}

	public List<RisplDkEmpRoleAccess> getRisplDkEmpRoleAccesses() {
		return this.risplDkEmpRoleAccesses;
	}

	public void setRisplDkEmpRoleAccesses(List<RisplDkEmpRoleAccess> risplDkEmpRoleAccesses) {
		this.risplDkEmpRoleAccesses = risplDkEmpRoleAccesses;
	}

	public RisplDkEmpRoleAccess addRisplDkEmpRoleAccess(RisplDkEmpRoleAccess risplDkEmpRoleAccess) {
		getRisplDkEmpRoleAccesses().add(risplDkEmpRoleAccess);
		risplDkEmpRoleAccess.setRisplDkEmpRole(this);

		return risplDkEmpRoleAccess;
	}

	public RisplDkEmpRoleAccess removeRisplDkEmpRoleAccess(RisplDkEmpRoleAccess risplDkEmpRoleAccess) {
		getRisplDkEmpRoleAccesses().remove(risplDkEmpRoleAccess);
		risplDkEmpRoleAccess.setRisplDkEmpRole(null);

		return risplDkEmpRoleAccess;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	

	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public String toString() {
		return "RisplDkEmpRole [roleId=" + roleId + ", createdByUserId=" + createdByUserId + ", createdDatetime="
				+ createdDatetime + ", effectiveDatetime=" + effectiveDatetime + ", endDatetime=" + endDatetime
				+ ", roleDesc=" + roleDesc + ", homePage=" + homePage + ", risplDkEmpMstrs=" + risplDkEmpMstrs
				+ ", risplDkEmpRoleAccesses=" + risplDkEmpRoleAccesses + "]";
	}

}