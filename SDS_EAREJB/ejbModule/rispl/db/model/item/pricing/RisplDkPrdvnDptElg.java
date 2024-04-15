package rispl.db.model.item.pricing;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_PRDVN_DPT_ELG database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_PRDVN_DPT_ELG")
@NamedQuery(name="RisplDkPrdvnDptElg.findAll", query="SELECT r FROM RisplDkPrdvnDptElg r")
public class RisplDkPrdvnDptElg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ID_DPT_POS", nullable=false, length=14)
	private String idDptPos;

	@Column(name="MO_TH", precision=22)
	private BigDecimal moTh;

	@Column(name="QU_TH", precision=22)
	private BigDecimal quTh;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	@Column(name="TS_RU_MRST_EF", nullable=false)
	private Timestamp tsRuMrstEf;

	@Column(name="TS_RU_MRST_EP", nullable=false)
	private Timestamp tsRuMrstEp;

	@Id
	@SequenceGenerator(name="RISPL_DK_PRDVN_DPT_ELG_UNQID_GENERATOR", sequenceName="DPT_ELG_SEQUENCE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RISPL_DK_PRDVN_DPT_ELG_UNQID_GENERATOR")
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

	public RisplDkPrdvnDptElg() {
	}

	public String getIdDptPos() {
		return this.idDptPos;
	}

	public void setIdDptPos(String idDptPos) {
		this.idDptPos = idDptPos;
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

	public Timestamp getTsRuMrstEf() {
		return this.tsRuMrstEf;
	}

	public void setTsRuMrstEf(Timestamp tsRuMrstEf) {
		this.tsRuMrstEf = tsRuMrstEf;
	}

	public Timestamp getTsRuMrstEp() {
		return this.tsRuMrstEp;
	}

	public void setTsRuMrstEp(Timestamp tsRuMrstEp) {
		this.tsRuMrstEp = tsRuMrstEp;
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