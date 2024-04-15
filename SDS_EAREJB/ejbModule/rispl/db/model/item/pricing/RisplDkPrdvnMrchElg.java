package rispl.db.model.item.pricing;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_PRDVN_MRCH_ELG database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_PRDVN_MRCH_ELG")
@NamedQuery(name="RisplDkPrdvnMrchElg.findAll", query="SELECT r FROM RisplDkPrdvnMrchElg r")
public class RisplDkPrdvnMrchElg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ID_STRC_MR_CD", length=10)
	private String idStrcMrCd;

	@Column(name="MO_TH", precision=15, scale=2)
	private BigDecimal moTh;

	@Column(name="QU_TH", precision=22)
	private BigDecimal quTh;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	@Column(name="TS_RU_MRST_EF")
	private Timestamp tsRuMrstEf;

	@Column(name="TS_RU_MRST_EP")
	private Timestamp tsRuMrstEp;

	@Id
	@SequenceGenerator(name="RISPL_DK_PRDVN_MRCH_ELG_UNQID_GENERATOR", sequenceName="MRCH_ELG_SEQUENCE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RISPL_DK_PRDVN_MRCH_ELG_UNQID_GENERATOR")
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

	public RisplDkPrdvnMrchElg() {
	}

	public String getIdStrcMrCd() {
		return this.idStrcMrCd;
	}

	public void setIdStrcMrCd(String idStrcMrCd) {
		this.idStrcMrCd = idStrcMrCd;
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