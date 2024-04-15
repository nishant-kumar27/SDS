package rispl.dkart.services.entities.tenders;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DK_TRAN_LTM_VCHR_TND database table.
 * 
 */
@Entity
@Table(name="DK_TRAN_LTM_VCHR_TND")
@NamedQuery(name="TranVoucherTender.findAll", query="SELECT t FROM TranVoucherTender t")
public class TranVoucherTender implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TranVoucherTenderPK id;

	@Column(name="FN_PRS_VCHR")
	private String fnPrsVchr;

	@Column(name="ID_CNY_ICD")
	private BigDecimal idCnyIcd;

	@Column(name="ID_ISSG_VCHR")
	private String idIssgVchr;

	@Column(name="ID_VCHR")
	private String idVchr;

	@Column(name="LN_PRS_VCHR")
	private String lnPrsVchr;

	@Column(name="MO_CR_VCHR")
	private BigDecimal moCrVchr;

	@Column(name="MO_VL_FC_VCHR_CF")
	private BigDecimal moVlFcVchrCf;

	@Column(name="NM_BSN")
	private String nmBsn;

	@Column(name="SC_VCHR_TND")
	private String scVchrTnd;

	@Column(name="TY_ID_VCHR")
	private String tyIdVchr;

	//bi-directional many-to-one association to TranLineItemTender
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="DC_DY_ORD", referencedColumnName="DC_DY_ORD"),
		@JoinColumn(name="ORD_WS", referencedColumnName="ORD_WS"),
		@JoinColumn(name="RT_STR_ID", referencedColumnName="RT_STR_ID"),
		@JoinColumn(name="TRN_LN_ITM_SEQ", referencedColumnName="TRN_LN_ITM_SEQ"),
		@JoinColumn(name="TRN_SEQ", referencedColumnName="TRN_SEQ")
		})
	private TranLineItemTender dkTranLtmTnd;

	public TranVoucherTender() {
	}

	public TranVoucherTenderPK getId() {
		return this.id;
	}

	public void setId(TranVoucherTenderPK id) {
		this.id = id;
	}

	public String getFnPrsVchr() {
		return this.fnPrsVchr;
	}

	public void setFnPrsVchr(String fnPrsVchr) {
		this.fnPrsVchr = fnPrsVchr;
	}

	public BigDecimal getIdCnyIcd() {
		return this.idCnyIcd;
	}

	public void setIdCnyIcd(BigDecimal idCnyIcd) {
		this.idCnyIcd = idCnyIcd;
	}

	public String getIdIssgVchr() {
		return this.idIssgVchr;
	}

	public void setIdIssgVchr(String idIssgVchr) {
		this.idIssgVchr = idIssgVchr;
	}

	public String getIdVchr() {
		return this.idVchr;
	}

	public void setIdVchr(String idVchr) {
		this.idVchr = idVchr;
	}

	public String getLnPrsVchr() {
		return this.lnPrsVchr;
	}

	public void setLnPrsVchr(String lnPrsVchr) {
		this.lnPrsVchr = lnPrsVchr;
	}

	public BigDecimal getMoCrVchr() {
		return this.moCrVchr;
	}

	public void setMoCrVchr(BigDecimal moCrVchr) {
		this.moCrVchr = moCrVchr;
	}

	public BigDecimal getMoVlFcVchrCf() {
		return this.moVlFcVchrCf;
	}

	public void setMoVlFcVchrCf(BigDecimal moVlFcVchrCf) {
		this.moVlFcVchrCf = moVlFcVchrCf;
	}

	public String getNmBsn() {
		return this.nmBsn;
	}

	public void setNmBsn(String nmBsn) {
		this.nmBsn = nmBsn;
	}

	public String getScVchrTnd() {
		return this.scVchrTnd;
	}

	public void setScVchrTnd(String scVchrTnd) {
		this.scVchrTnd = scVchrTnd;
	}

	public String getTyIdVchr() {
		return this.tyIdVchr;
	}

	public void setTyIdVchr(String tyIdVchr) {
		this.tyIdVchr = tyIdVchr;
	}

	public TranLineItemTender getDkTranLtmTnd() {
		return this.dkTranLtmTnd;
	}

	public void setDkTranLtmTnd(TranLineItemTender dkTranLtmTnd) {
		this.dkTranLtmTnd = dkTranLtmTnd;
	}

}