package rispl.db.model.tax;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_TAX_ITM database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_TAX_ITM")
@NamedQuery(name="RisplDkTaxItm.findAll", query="SELECT r FROM RisplDkTaxItm r")
public class RisplDkTaxItm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkTaxItmPK id;

	@Column(name="CD_RCV_PRT")
	private BigDecimal cdRcvPrt;

	@Column(name="DE_GP_TX")
	private String deGpTx;

	@Column(name="NM_GP_TX")
	private String nmGpTx;

	@Column(name="TS_CRT_RCRD")
	private Timestamp tsCrtRcrd;

	@Column(name="TS_MDF_RCRD")
	private Timestamp tsMdfRcrd;

	public RisplDkTaxItm() {
	}

	public RisplDkTaxItmPK getId() {
		return this.id;
	}

	public void setId(RisplDkTaxItmPK id) {
		this.id = id;
	}

	public BigDecimal getCdRcvPrt() {
		return this.cdRcvPrt;
	}

	public void setCdRcvPrt(BigDecimal cdRcvPrt) {
		this.cdRcvPrt = cdRcvPrt;
	}

	public String getDeGpTx() {
		return this.deGpTx;
	}

	public void setDeGpTx(String deGpTx) {
		this.deGpTx = deGpTx;
	}

	public String getNmGpTx() {
		return this.nmGpTx;
	}

	public void setNmGpTx(String nmGpTx) {
		this.nmGpTx = nmGpTx;
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

}