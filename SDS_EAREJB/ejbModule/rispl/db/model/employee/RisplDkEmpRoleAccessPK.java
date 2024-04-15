package rispl.db.model.employee;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_EMP_ROLE_ACCESS database table.
 * 
 */
@Embeddable
public class RisplDkEmpRoleAccessPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ROLE_ID", insertable = false, updatable = false)
	private long roleId;

	@Column(name = "FUNCTION_ID", insertable = false, updatable = false)
	private String functionId;

	public RisplDkEmpRoleAccessPK() {
	}

	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkEmpRoleAccessPK)) {
			return false;
		}
		RisplDkEmpRoleAccessPK castOther = (RisplDkEmpRoleAccessPK) other;
		return (this.roleId == castOther.roleId) && this.functionId.equals(castOther.functionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.roleId ^ (this.roleId >>> 32)));
		hash = hash * prime + this.functionId.hashCode();

		return hash;
	}

	@Override
	public String toString() {
		return "RisplDkEmpRoleAccessPK [roleId=" + roleId + ", functionId=" + functionId + "]";
	}
}