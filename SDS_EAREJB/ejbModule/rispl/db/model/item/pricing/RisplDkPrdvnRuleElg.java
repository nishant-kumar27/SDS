package rispl.db.model.item.pricing;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the RISPL_DK_PRDVN_RULE_ELG database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_PRDVN_RULE_ELG")
@NamedQuery(name="RisplDkPrdvnRuleElg.findAll", query="SELECT r FROM RisplDkPrdvnRuleElg r")
public class RisplDkPrdvnRuleElg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ITM_ID", length=25)
	private String itmId;

	@Column(name="MO_TH", precision=22)
	private BigDecimal moTh;

	@Column(name="QU_TH", precision=22)
	private BigDecimal quTh;

	@Column(name="TS_CRT_RCRD", nullable=false)
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD", nullable=false)
	private Timestamp tsMdfRcrd;

	@Column(name="TS_RU_DRVN_EF", nullable=false)
	private Timestamp tsRuDrvnEf;

	@Column(name="TS_RU_DRVN_EP", nullable=false)
	private Timestamp tsRuDrvnEp;

	@Id
	@SequenceGenerator(name="RISPL_DK_PRDVN_RULE_ELG_UNQID_GENERATOR", sequenceName="RULE_ELG_SEQUENCE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RISPL_DK_PRDVN_RULE_ELG_UNQID_GENERATOR")
	@Column(name="UNQ_ID", precision=22)
	private BigDecimal unqId;

	//bi-directional many-to-one association to RisplDkPrdvnRule
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_PRM_CMP_DTL", referencedColumnName="ID_PRM_CMP_DTL"),
		@JoinColumn(name="ID_PRM_CMP", referencedColumnName="ID_PRM_CMP"),
		@JoinColumn(name="ID_RU_PRDV", referencedColumnName="ID_RU_PRDV", nullable=false),
		@JoinColumn(name="ID_STR_RT", referencedColumnName="ID_STR_RT", nullable=false)
		})
	private RisplDkPrdvnRule risplDkPrdvnRule;

	public RisplDkPrdvnRuleElg() {
	}

	public String getItmId() {
		return this.itmId;
	}

	public void setItmId(String itmId) {
		this.itmId = itmId;
	}

	public BigDecimal getMoTh() {
		return this.moTh;
	}

	public void setMoTh(BigDecimal moTh) {
		this.moTh = moTh;
	}

	public BigDecimal getQuTh() {
		return this.quTh;
	}

	public void setQuTh(BigDecimal quTh) {
		this.quTh = quTh;
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

	public Timestamp getTsRuDrvnEf() {
		return this.tsRuDrvnEf;
	}

	public void setTsRuDrvnEf(Timestamp tsRuDrvnEf) {
		this.tsRuDrvnEf = tsRuDrvnEf;
	}

	public Timestamp getTsRuDrvnEp() {
		return this.tsRuDrvnEp;
	}

	public void setTsRuDrvnEp(Timestamp tsRuDrvnEp) {
		this.tsRuDrvnEp = tsRuDrvnEp;
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