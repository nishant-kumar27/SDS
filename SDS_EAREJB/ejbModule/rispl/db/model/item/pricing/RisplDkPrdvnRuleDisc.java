package rispl.db.model.item.pricing;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_PRDVN_RULE_DISC database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_PRDVN_RULE_DISC")
@NamedQuery(name="RisplDkPrdvnRuleDisc.findAll", query="SELECT r FROM RisplDkPrdvnRuleDisc r")
public class RisplDkPrdvnRuleDisc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="MO_UN_ITM_PRDV_SLS", precision=15, scale=2)
	private BigDecimal moUnItmPrdvSls;

	@Column(name="PE_UN_ITM_PRDV_SLS", precision=7, scale=2)
	private BigDecimal peUnItmPrdvSls;

	@Column(name="PNT_PRC_UN_ITM_PRDV_SLS", precision=15, scale=2)
	private BigDecimal pntPrcUnItmPrdvSls;

	@Column(name="TS_CRT_RCRD", nullable=false)
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD", nullable=false)
	private Timestamp tsMdfRcrd;

	@Id
	@SequenceGenerator(name="RISPL_DK_PRDVN_RULE_DISC_UNQID_GENERATOR", sequenceName="RULE_DISC_SEQUENCE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RISPL_DK_PRDVN_RULE_DISC_UNQID_GENERATOR")
	@Column(name="UNQ_ID", precision=22)
	private BigDecimal unqId;

	//bi-directional many-to-one association to RisplDkPrdvnRule
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_PRM_CMP_DTL", referencedColumnName="ID_PRM_CMP_DTL"),
		@JoinColumn(name="ID_PRM_CMP", referencedColumnName="ID_PRM_CMP"),
		@JoinColumn(name="ID_RU_PRDV", referencedColumnName="ID_RU_PRDV", nullable=false),
		@JoinColumn(name="ID_STR_RT", referencedColumnName="ID_STR_RT")
		})
	private RisplDkPrdvnRule risplDkPrdvnRule;

	public RisplDkPrdvnRuleDisc() {
	}

	public BigDecimal getMoUnItmPrdvSls() {
		return this.moUnItmPrdvSls;
	}

	public void setMoUnItmPrdvSls(BigDecimal moUnItmPrdvSls) {
		this.moUnItmPrdvSls = moUnItmPrdvSls;
	}

	public BigDecimal getPeUnItmPrdvSls() {
		return this.peUnItmPrdvSls;
	}

	public void setPeUnItmPrdvSls(BigDecimal peUnItmPrdvSls) {
		this.peUnItmPrdvSls = peUnItmPrdvSls;
	}

	public BigDecimal getPntPrcUnItmPrdvSls() {
		return this.pntPrcUnItmPrdvSls;
	}

	public void setPntPrcUnItmPrdvSls(BigDecimal pntPrcUnItmPrdvSls) {
		this.pntPrcUnItmPrdvSls = pntPrcUnItmPrdvSls;
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