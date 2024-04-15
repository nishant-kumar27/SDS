package rispl.db.model.item.pricing;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_PRDVN_MMITM database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_PRDVN_MMITM")
@NamedQuery(name="RisplDkPrdvnMmitm.findAll", query="SELECT r FROM RisplDkPrdvnMmitm r")
public class RisplDkPrdvnMmitm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ID_PRM_PRD", length=25)
	private String idPrmPrd;

	@Column(name="MO_RDN_PRC_MXMH", precision=15, scale=2)
	private BigDecimal moRdnPrcMxmh;

	@Column(name="PE_RDN_PRC_MXMH", precision=7, scale=2)
	private BigDecimal peRdnPrcMxmh;

	@Column(name="PNT_PRC_RDN_MXMH", precision=15, scale=2)
	private BigDecimal pntPrcRdnMxmh;

	@Column(name="QU_LM_MXMH", precision=22)
	private BigDecimal quLmMxmh;

	@Column(name="TS_CRT_RCRD", nullable=false)
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD", nullable=false)
	private Timestamp tsMdfRcrd;

	@Id
	@SequenceGenerator(name="RISPL_DK_PRDVN_MMITM_UNQID_GENERATOR", sequenceName="PRDVN_MMITM_SEQUENCE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RISPL_DK_PRDVN_MMITM_UNQID_GENERATOR")
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

	public RisplDkPrdvnMmitm() {
	}

	public String getIdPrmPrd() {
		return this.idPrmPrd;
	}

	public void setIdPrmPrd(String idPrmPrd) {
		this.idPrmPrd = idPrmPrd;
	}

	public BigDecimal getMoRdnPrcMxmh() {
		return this.moRdnPrcMxmh;
	}

	public void setMoRdnPrcMxmh(BigDecimal moRdnPrcMxmh) {
		this.moRdnPrcMxmh = moRdnPrcMxmh;
	}

	public BigDecimal getPeRdnPrcMxmh() {
		return this.peRdnPrcMxmh;
	}

	public void setPeRdnPrcMxmh(BigDecimal peRdnPrcMxmh) {
		this.peRdnPrcMxmh = peRdnPrcMxmh;
	}

	public BigDecimal getPntPrcRdnMxmh() {
		return this.pntPrcRdnMxmh;
	}

	public void setPntPrcRdnMxmh(BigDecimal pntPrcRdnMxmh) {
		this.pntPrcRdnMxmh = pntPrcRdnMxmh;
	}

	public BigDecimal getQuLmMxmh() {
		return this.quLmMxmh;
	}

	public void setQuLmMxmh(BigDecimal quLmMxmh) {
		this.quLmMxmh = quLmMxmh;
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