package rispl.db.model.storehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_STORE_GROUP database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_STORE_GROUP")
@NamedQuery(name="RisplDkStoreGroup.findAll", query="SELECT r FROM RisplDkStoreGroup r")
public class RisplDkStoreGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="STRGP_ID", unique=true, nullable=false, length=20)
	private String strgpId;

	@Column(name="RCRD_CRT_TS")
	private Timestamp rcrdCrtTs;

	@Column(name="RCRD_MDF_TS")
	private Timestamp rcrdMdfTs;

	@Column(name="STRGP_DESC", length=250)
	private String strgpDesc;

	@Column(name="STRGP_FNC_ID", length=20)
	private String strgpFncId;

	@Column(name="STRGP_LV_ID", precision=22)
	private BigDecimal strgpLvId;

	@Column(name="STRGP_NM", length=120)
	private String strgpNm;

	@Column(name="STRGP_TYP", length=2)
	private String strgpTyp;

	public RisplDkStoreGroup() {
	}

	public String getStrgpId() {
		return this.strgpId;
	}

	public void setStrgpId(String strgpId) {
		this.strgpId = strgpId;
	}

	public Timestamp getRcrdCrtTs() {
		return this.rcrdCrtTs;
	}

	public void setRcrdCrtTs(Timestamp rcrdCrtTs) {
		this.rcrdCrtTs = rcrdCrtTs;
	}

	public Timestamp getRcrdMdfTs() {
		return this.rcrdMdfTs;
	}

	public void setRcrdMdfTs(Timestamp rcrdMdfTs) {
		this.rcrdMdfTs = rcrdMdfTs;
	}

	public String getStrgpDesc() {
		return this.strgpDesc;
	}

	public void setStrgpDesc(String strgpDesc) {
		this.strgpDesc = strgpDesc;
	}

	public String getStrgpFncId() {
		return this.strgpFncId;
	}

	public void setStrgpFncId(String strgpFncId) {
		this.strgpFncId = strgpFncId;
	}

	public BigDecimal getStrgpLvId() {
		return this.strgpLvId;
	}

	public void setStrgpLvId(BigDecimal strgpLvId) {
		this.strgpLvId = strgpLvId;
	}

	public String getStrgpNm() {
		return this.strgpNm;
	}

	public void setStrgpNm(String strgpNm) {
		this.strgpNm = strgpNm;
	}

	public String getStrgpTyp() {
		return this.strgpTyp;
	}

	public void setStrgpTyp(String strgpTyp) {
		this.strgpTyp = strgpTyp;
	}

}