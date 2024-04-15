package rispl.db.model.dime.status;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_DIME_BNDL_STTS database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_DIME_BNDL_STTS")
@NamedQuery(name="RisplDkDimeBndlStt.findAll", query="SELECT r FROM RisplDkDimeBndlStt r")
public class RisplDkDimeBndlStt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NM_BNDL_IMP")
	private String nmBndlImp;

	@Column(name="SC_STS_BNDL")
	private String scStsBndl;

	@Column(name="TS_END_IMP_PRC")
	private Timestamp tsEndImpPrc;

	@Column(name="TS_STR_IMP_PRC")
	private Timestamp tsStrImpPrc;

	@Column(name="ERR_MSG")
	private String errMsg;
	
	public RisplDkDimeBndlStt() {
	}

	public String getNmBndlImp() {
		return this.nmBndlImp;
	}

	public void setNmBndlImp(String nmBndlImp) {
		this.nmBndlImp = nmBndlImp;
	}

	public String getScStsBndl() {
		return this.scStsBndl;
	}

	public void setScStsBndl(String scStsBndl) {
		this.scStsBndl = scStsBndl;
	}

	public Timestamp getTsEndImpPrc() {
		return this.tsEndImpPrc;
	}

	public void setTsEndImpPrc(Timestamp tsEndImpPrc) {
		this.tsEndImpPrc = tsEndImpPrc;
	}

	public Timestamp getTsStrImpPrc() {
		return this.tsStrImpPrc;
	}

	public void setTsStrImpPrc(Timestamp tsStrImpPrc) {
		this.tsStrImpPrc = tsStrImpPrc;
	}
	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}