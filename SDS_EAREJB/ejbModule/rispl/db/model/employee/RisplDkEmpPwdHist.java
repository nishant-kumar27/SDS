package rispl.db.model.employee;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_EMP_PWD_HIST database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_EMP_PWD_HIST")
@NamedQuery(name="RisplDkEmpPwdHist.findAll", query="SELECT r FROM RisplDkEmpPwdHist r")
public class RisplDkEmpPwdHist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkEmpPwdHistPK id;

	@Column(name="PW_ACS_EM")
	private String pwAcsEm;

	@Column(name="PW_SLT_EM")
	private String pwSltEm;

	@Column(name="TS_CRT_PW")
	private Timestamp tsCrtPw;

	//bi-directional one-to-one association to RisplDkEmpMstr
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name="EMP_ID", referencedColumnName="EMP_ID"),
		@JoinColumn(name="ID_STR_RT", referencedColumnName="ID_STR_RT")
		})
	private RisplDkEmpMstr risplDkEmpMstr;

	public RisplDkEmpPwdHist() {
	}

	public RisplDkEmpPwdHistPK getId() {
		return this.id;
	}

	public void setId(RisplDkEmpPwdHistPK id) {
		this.id = id;
	}

	public String getPwAcsEm() {
		return this.pwAcsEm;
	}

	public void setPwAcsEm(String pwAcsEm) {
		this.pwAcsEm = pwAcsEm;
	}

	public String getPwSltEm() {
		return this.pwSltEm;
	}

	public void setPwSltEm(String pwSltEm) {
		this.pwSltEm = pwSltEm;
	}

	public Timestamp getTsCrtPw() {
		return this.tsCrtPw;
	}

	public void setTsCrtPw(Timestamp tsCrtPw) {
		this.tsCrtPw = tsCrtPw;
	}

	public RisplDkEmpMstr getRisplDkEmpMstr() {
		return this.risplDkEmpMstr;
	}

	public void setRisplDkEmpMstr(RisplDkEmpMstr risplDkEmpMstr) {
		this.risplDkEmpMstr = risplDkEmpMstr;
	}

}