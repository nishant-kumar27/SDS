package rispl.db.model.employee;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RISPL_DK_EMP_MSTR database table.
 * 
 */
@Embeddable
public class RisplDkEmpMstrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "EMP_ID")
	private String empId;

	@Column(name = "ID_STR_RT")
	private String idStrRt;

	public RisplDkEmpMstrPK() {
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getIdStrRt() {
		return this.idStrRt;
	}

	public void setIdStrRt(String idStrRt) {
		this.idStrRt = idStrRt;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RisplDkEmpMstrPK)) {
			return false;
		}
		RisplDkEmpMstrPK castOther = (RisplDkEmpMstrPK) other;
		return this.empId.equals(castOther.empId) && this.idStrRt.equals(castOther.idStrRt);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.empId.hashCode();
		hash = hash * prime + this.idStrRt.hashCode();

		return hash;
	}

	@Override
	public String toString() {
		return "RisplDkEmpMstrPK [empId=" + empId + ", idStrRt=" + idStrRt + "]";
	}
}