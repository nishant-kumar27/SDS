package rispl.db.model.item.pricing;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_PRDVN_ITM_NELG database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_PRDVN_ITM_NELG")
@NamedQuery(name="RisplDkPrdvnItmNelg.findAll", query="SELECT r FROM RisplDkPrdvnItmNelg r")
public class RisplDkPrdvnItmNelg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ITM_ID", length=25)
	private String itmId;

	@Column(name="TS_CRT_RCRD", nullable=false)
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD", nullable=false)
	private Timestamp tsMdfRcrd;

	@Column(name="TS_NEL_EF")
	private Timestamp tsNelEf;

	@Id
	@SequenceGenerator(name="RISPL_DK_PRDVN_ITM_NELG_UNQID_GENERATOR", sequenceName="ITM_NELG_SEQUENCE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RISPL_DK_PRDVN_ITM_NELG_UNQID_GENERATOR")
	@Column(name="UNQ_ID", precision=22)
	private BigDecimal unqId;

	//bi-directional many-to-one association to RisplDkPrdvnRule
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_PRM_CMP_DTL", referencedColumnName="ID_PRM_CMP_DTL"),
		@JoinColumn(name="ID_PRM_CMP", referencedColumnName="ID_PRM_CMP"),
		@JoinColumn(name="ID_RU_PRDV", referencedColumnName="ID_RU_PRDV"),
		@JoinColumn(name="ID_STR_RT", referencedColumnName="ID_STR_RT")
		})
	private RisplDkPrdvnRule risplDkPrdvnRule;

	public RisplDkPrdvnItmNelg() {
	}

	public String getItmId() {
		return this.itmId;
	}

	public void setItmId(String itmId) {
		this.itmId = itmId;
	}

	public Timestamp getTsCrtRcrd() {
		return this.tsCrtRcrd;
	}

	public void setTsCrtRcrd(Timestamp tsCrtRcrd) {
		this.tsCrtRcrd = tsCrtRcrd;
	}

	public Timestamp getTsMdfRcrd() {
		return this.tsMdfRcrd;
	}

	public void setTsMdfRcrd(Timestamp tsMdfRcrd) {
		this.tsMdfRcrd = tsMdfRcrd;
	}

	public Timestamp getTsNelEf() {
		return this.tsNelEf;
	}

	public void setTsNelEf(Timestamp tsNelEf) {
		this.tsNelEf = tsNelEf;
	}

	public BigDecimal getUnqId() {
		return this.unqId;
	}

	public void setUnqId(BigDecimal unqId) {
		this.unqId = unqId;
	}

	public RisplDkPrdvnRule getRisplDkPrdvnRule() {
		return this.risplDkPrdvnRule;
	}

	public void setRisplDkPrdvnRule(RisplDkPrdvnRule risplDkPrdvnRule) {
		this.risplDkPrdvnRule = risplDkPrdvnRule;
	}

}