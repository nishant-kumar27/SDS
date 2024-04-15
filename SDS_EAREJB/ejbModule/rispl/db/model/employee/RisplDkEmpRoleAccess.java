package rispl.db.model.employee;

import java.io.Serializable;
import javax.persistence.*;

import model.function.RisplDkFunc;

import java.sql.Timestamp;

/**
 * The persistent class for the RISPL_DK_EMP_ROLE_ACCESS database table.
 * 
 */
@Entity
@Table(name = "RISPL_DK_EMP_ROLE_ACCESS")
@NamedQuery(name = "RisplDkEmpRoleAccess.findAll", query = "SELECT r FROM RisplDkEmpRoleAccess r")
public class RisplDkEmpRoleAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkEmpRoleAccessPK id;

	@Column(name = "CREATED_BY_USER_ID")
	private String createdByUserId;

	@Column(name = "CREATED_DATETIME")
	private Timestamp createdDatetime;

	@Column(name = "EFFECTIVE_DATETIME")
	private Timestamp effectiveDatetime;

	@Column(name = "END_DATETIME")
	private Timestamp endDatetime;

	@Column(name = "HAS_ACCESS")
	private String hasAccess;

	//bi-directional many-to-one association to RisplDkFunc
	@ManyToOne
	@JoinColumn(name = "FUNCTION_ID")
	private RisplDkFunc risplDkFunc;

	//bi-directional many-to-one association to RisplDkEmpRole
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private RisplDkEmpRole risplDkEmpRole;

	public RisplDkEmpRoleAccess() {
	}

	public RisplDkEmpRoleAccessPK getId() {
		return this.id;
	}

	public void setId(RisplDkEmpRoleAccessPK id) {
		this.id = id;
	}

	public String getCreatedByUserId() {
		return this.createdByUserId;
	}

	public void setCreatedByUserId(String createdByUserId) {
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

	public String getHasAccess() {
		return this.hasAccess;
	}

	public void setHasAccess(String hasAccess) {
		this.hasAccess = hasAccess;
	}

	public RisplDkFunc getRisplDkFunc() {
		return this.risplDkFunc;
	}

	public void setRisplDkFunc(RisplDkFunc risplDkFunc) {
		this.risplDkFunc = risplDkFunc;
	}

	public RisplDkEmpRole getRisplDkEmpRole() {
		return this.risplDkEmpRole;
	}

	public void setRisplDkEmpRole(RisplDkEmpRole risplDkEmpRole) {
		this.risplDkEmpRole = risplDkEmpRole;
	}

	@Override
	public String toString() {
		return "RisplDkEmpRoleAccess [id=" + id + ", createdByUserId=" + createdByUserId + ", createdDatetime="
				+ createdDatetime + ", effectiveDatetime=" + effectiveDatetime + ", endDatetime=" + endDatetime
				+ ", hasAccess=" + hasAccess + ", risplDkFunc=" + risplDkFunc + ", risplDkEmpRole=" + risplDkEmpRole
				+ "]";
	}

}