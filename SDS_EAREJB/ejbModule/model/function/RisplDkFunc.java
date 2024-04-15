package model.function;

import java.io.Serializable;
import javax.persistence.*;

import rispl.db.model.employee.RisplDkEmpRoleAccess;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the RISPL_DK_FUNC database table.
 * 
 */
@Entity
@Table(name = "RISPL_DK_FUNC")
@NamedQuery(name = "RisplDkFunc.findAll", query = "SELECT r FROM RisplDkFunc r")
public class RisplDkFunc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FUNCTION_ID")
	private String functionId;

	@Column(name = "CREATED_BY_USER_ID")
	private String createdByUserId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATETIME")
	private Date createdDatetime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFFECTIVE_DATETIME")
	private Date effectiveDatetime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATETIME")
	private Date endDatetime;

	@Column(name = "FUNCTION_DESC")
	private String functionDesc;

	//bi-directional many-to-one association to RisplDkEmpRoleAccess
	@OneToMany(mappedBy = "risplDkFunc", fetch = FetchType.EAGER)
	private List<RisplDkEmpRoleAccess> risplDkEmpRoleAccesses;

	public RisplDkFunc() {
	}

	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getCreatedByUserId() {
		return this.createdByUserId;
	}

	public void setCreatedByUserId(String createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Date getEffectiveDatetime() {
		return this.effectiveDatetime;
	}

	public void setEffectiveDatetime(Date effectiveDatetime) {
		this.effectiveDatetime = effectiveDatetime;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getFunctionDesc() {
		return this.functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public List<RisplDkEmpRoleAccess> getRisplDkEmpRoleAccesses() {
		return this.risplDkEmpRoleAccesses;
	}

	public void setRisplDkEmpRoleAccesses(List<RisplDkEmpRoleAccess> risplDkEmpRoleAccesses) {
		this.risplDkEmpRoleAccesses = risplDkEmpRoleAccesses;
	}

	public RisplDkEmpRoleAccess addRisplDkEmpRoleAccess(RisplDkEmpRoleAccess risplDkEmpRoleAccess) {
		getRisplDkEmpRoleAccesses().add(risplDkEmpRoleAccess);
		risplDkEmpRoleAccess.setRisplDkFunc(this);

		return risplDkEmpRoleAccess;
	}

	public RisplDkEmpRoleAccess removeRisplDkEmpRoleAccess(RisplDkEmpRoleAccess risplDkEmpRoleAccess) {
		getRisplDkEmpRoleAccesses().remove(risplDkEmpRoleAccess);
		risplDkEmpRoleAccess.setRisplDkFunc(null);

		return risplDkEmpRoleAccess;
	}

}