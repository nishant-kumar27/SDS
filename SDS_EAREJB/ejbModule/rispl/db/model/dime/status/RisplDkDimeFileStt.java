package rispl.db.model.dime.status;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RISPL_DK_DIME_FILE_STTS database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_DIME_FILE_STTS")
@NamedQuery(name="RisplDkDimeFileStt.findAll", query="SELECT r FROM RisplDkDimeFileStt r")
public class RisplDkDimeFileStt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkDimeFileSttPK id;

	@Column(name="CS_STS_FL")
	private String csStsFl;

	@Column(name="TS_END_IMP_PRC")
	private Timestamp tsEndImpPrc;

	@Column(name="TS_STR_IMP_PRC")
	private Timestamp tsStrImpPrc;
	
	@Column(name="ERR_MSG")
	private String errMsg;

	public RisplDkDimeFileStt() {
	}

	public RisplDkDimeFileSttPK getId() {
		return this.id;
	}

	public void setId(RisplDkDimeFileSttPK id) {
		this.id = id;
	}

	public String getCsStsFl() {
		return this.csStsFl;
	}

	public void setCsStsFl(String csStsFl) {
		this.csStsFl = csStsFl;
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