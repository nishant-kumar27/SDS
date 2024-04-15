package rispl.dkart.services.entities.tenders;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the DK_TRAN_LTM_TND database table.
 * 
 */
@Entity
@Table(name="DK_TRAN_LTM_TND")
@NamedQuery(name="TranLineItemTender.findAll", query="SELECT t FROM TranLineItemTender t")
public class TranLineItemTender implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TranLineItemTenderPK id;

	@Column(name="DK_DE_CNY_LCL")
	private String dkDeCnyLcl;

	@Column(name="DK_FRG_CNCT_CNY")
	private String dkFrgCnctCny;

	@Column(name="DK_FRG_DE_CNY")
	private String dkFrgDeCny;

	@Column(name="DK_RT_TO_BUY")
	private BigDecimal dkRtToBuy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DK_TS_CRT_RCRD")
	private Date dkTsCrtRcrd;

	@Column(name="DK_TS_MDF_RCRD")
	private String dkTsMdfRcrd;

	@Column(name="FRG_MO_ITM_LN_TND")
	private BigDecimal frgMoItmLnTnd;

	@Column(name="ID_ACNT_NMB")
	private String idAcntNmb;

	@Column(name="ID_ACNT_TND")
	private BigDecimal idAcntTnd;

	@Column(name="ID_ICD_CNY")
	private BigDecimal idIcdCny;

	@Column(name="ID_ORD")
	private String idOrd;

	@Column(name="MO_ITM_LN_TND")
	private BigDecimal moItmLnTnd;

	@Column(name="TY_TND")
	private String tyTnd;

	//bi-directional many-to-one association to TranCheckTender
	@OneToMany(cascade = CascadeType.ALL, mappedBy="dkTranLtmTnd",fetch = FetchType.EAGER)
	private List<TranCheckTender> dkTranChkTnds;

	//bi-directional many-to-one association to TranVoucherTender
	@OneToMany(cascade = CascadeType.ALL, mappedBy="dkTranLtmTnd",fetch = FetchType.EAGER)
	private List<TranVoucherTender> dkTranLtmVchrTnds;

	public TranLineItemTender() {
	}

	public TranLineItemTenderPK getId() {
		return this.id;
	}

	public void setId(TranLineItemTenderPK id) {
		this.id = id;
	}

	public String getDkDeCnyLcl() {
		return this.dkDeCnyLcl;
	}

	public void setDkDeCnyLcl(String dkDeCnyLcl) {
		this.dkDeCnyLcl = dkDeCnyLcl;
	}

	public String getDkFrgCnctCny() {
		return this.dkFrgCnctCny;
	}

	public void setDkFrgCnctCny(String dkFrgCnctCny) {
		this.dkFrgCnctCny = dkFrgCnctCny;
	}

	public String getDkFrgDeCny() {
		return this.dkFrgDeCny;
	}

	public void setDkFrgDeCny(String dkFrgDeCny) {
		this.dkFrgDeCny = dkFrgDeCny;
	}

	public BigDecimal getDkRtToBuy() {
		return this.dkRtToBuy;
	}

	public void setDkRtToBuy(BigDecimal dkRtToBuy) {
		this.dkRtToBuy = dkRtToBuy;
	}

	public Date getDkTsCrtRcrd() {
		return this.dkTsCrtRcrd;
	}

	public void setDkTsCrtRcrd(Date dkTsCrtRcrd) {
		this.dkTsCrtRcrd = dkTsCrtRcrd;
	}

	public String getDkTsMdfRcrd() {
		return this.dkTsMdfRcrd;
	}

	public void setDkTsMdfRcrd(String dkTsMdfRcrd) {
		this.dkTsMdfRcrd = dkTsMdfRcrd;
	}

	public BigDecimal getFrgMoItmLnTnd() {
		return this.frgMoItmLnTnd;
	}

	public void setFrgMoItmLnTnd(BigDecimal frgMoItmLnTnd) {
		this.frgMoItmLnTnd = frgMoItmLnTnd;
	}

	public BigDecimal getIdAcntTnd() {
		return this.idAcntTnd;
	}

	public void setIdAcntTnd(BigDecimal idAcntTnd) {
		this.idAcntTnd = idAcntTnd;
	}

	public BigDecimal getIdIcdCny() {
		return this.idIcdCny;
	}

	public void setIdIcdCny(BigDecimal idIcdCny) {
		this.idIcdCny = idIcdCny;
	}

	public String getIdOrd() {
		return this.idOrd;
	}

	public void setIdOrd(String idOrd) {
		this.idOrd = idOrd;
	}

	public BigDecimal getMoItmLnTnd() {
		return this.moItmLnTnd;
	}

	public void setMoItmLnTnd(BigDecimal moItmLnTnd) {
		this.moItmLnTnd = moItmLnTnd;
	}

	public String getTyTnd() {
		return this.tyTnd;
	}

	public void setTyTnd(String tyTnd) {
		this.tyTnd = tyTnd;
	}

	public List<TranCheckTender> getDkTranChkTnds() {
		return this.dkTranChkTnds;
	}

	public void setDkTranChkTnds(List<TranCheckTender> dkTranChkTnds) {
		this.dkTranChkTnds = dkTranChkTnds;
	}

	public TranCheckTender addDkTranChkTnd(TranCheckTender dkTranChkTnd) {
		getDkTranChkTnds().add(dkTranChkTnd);
		dkTranChkTnd.setDkTranLtmTnd(this);

		return dkTranChkTnd;
	}

	public TranCheckTender removeDkTranChkTnd(TranCheckTender dkTranChkTnd) {
		getDkTranChkTnds().remove(dkTranChkTnd);
		dkTranChkTnd.setDkTranLtmTnd(null);

		return dkTranChkTnd;
	}

	public List<TranVoucherTender> getDkTranLtmVchrTnds() {
		return this.dkTranLtmVchrTnds;
	}

	public void setDkTranLtmVchrTnds(List<TranVoucherTender> dkTranLtmVchrTnds) {
		this.dkTranLtmVchrTnds = dkTranLtmVchrTnds;
	}

	public TranVoucherTender addDkTranLtmVchrTnd(TranVoucherTender dkTranLtmVchrTnd) {
		getDkTranLtmVchrTnds().add(dkTranLtmVchrTnd);
		dkTranLtmVchrTnd.setDkTranLtmTnd(this);

		return dkTranLtmVchrTnd;
	}

	public TranVoucherTender removeDkTranLtmVchrTnd(TranVoucherTender dkTranLtmVchrTnd) {
		getDkTranLtmVchrTnds().remove(dkTranLtmVchrTnd);
		dkTranLtmVchrTnd.setDkTranLtmTnd(null);

		return dkTranLtmVchrTnd;
	}

	/**
	 * @return the idAcntNmb
	 */
	public String getIdAcntNmb() {
		return idAcntNmb;
	}

	/**
	 * @param idAcntNmb the idAcntNmb to set
	 */
	public void setIdAcntNmb(String idAcntNmb) {
		this.idAcntNmb = idAcntNmb;
	}

}