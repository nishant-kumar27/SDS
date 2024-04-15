package rispl.db.model.item.pricing;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_PRDVN_TRSHLD_ELG database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_PRDVN_TRSHLD_ELG")
@NamedQuery(name="RisplDkPrdvnTrshldElg.findAll", query="SELECT r FROM RisplDkPrdvnTrshldElg r")
public class RisplDkPrdvnTrshldElg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ID_PRDV_TH", precision=22)
	private BigDecimal idPrdvTh;

	@Column(name="MO_UN_TH_PRDV_SLS", precision=15, scale=2)
	private BigDecimal moUnThPrdvSls;

	@Column(name="PE_UN_TH_PRDV_SLS", precision=7, scale=2)
	private BigDecimal peUnThPrdvSls;

	@Column(name="PT_PRC_TH_PRDV_SLS", precision=15, scale=2)
	private BigDecimal ptPrcThPrdvSls;

	@Column(name="TH_VAL", precision=22)
	private BigDecimal thVal;

	@Column(name="TS_CRT_RCRD", nullable=false)
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD", nullable=false)
	private Timestamp tsMdfRcrd;

	@Id
	@SequenceGenerator(name="RISPL_DK_PRDVN_TRSHLD_ELG_UNQID_GENERATOR", sequenceName="TRSHLD_ELG_SEQUENCE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RISPL_DK_PRDVN_TRSHLD_ELG_UNQID_GENERATOR")
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

	public RisplDkPrdvnTrshldElg() {
	}

	public BigDecimal getIdPrdvTh() {
		return this.idPrdvTh;
	}

	public void setIdPrdvTh(BigDecimal idPrdvTh) {
		this.idPrdvTh = idPrdvTh;
	}

	public BigDecimal getMoUnThPrdvSls() {
		return this.moUnThPrdvSls;
	}

	public void setMoUnThPrdvSls(BigDecimal moUnThPrdvSls) {
		this.moUnThPrdvSls = moUnThPrdvSls;
	}

	public BigDecimal getPeUnThPrdvSls() {
		return this.peUnThPrdvSls;
	}

	public void setPeUnThPrdvSls(BigDecimal peUnThPrdvSls) {
		this.peUnThPrdvSls = peUnThPrdvSls;
	}

	public BigDecimal getPtPrcThPrdvSls() {
		return this.ptPrcThPrdvSls;
	}

	public void setPtPrcThPrdvSls(BigDecimal ptPrcThPrdvSls) {
		this.ptPrcThPrdvSls = ptPrcThPrdvSls;
	}

	public BigDecimal getThVal() {
		return this.thVal;
	}

	public void setThVal(BigDecimal thVal) {
		this.thVal = thVal;
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