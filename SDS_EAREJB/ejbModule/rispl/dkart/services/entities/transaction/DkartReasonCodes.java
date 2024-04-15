package rispl.dkart.services.entities.transaction;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_RSN_CDS database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_RSN_CDS")
@NamedQuery(name="RisplDkRsnCd.findAll", query="SELECT r FROM DkartReasonCodes r")
public class DkartReasonCodes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RSN_CODE")
	private long rsnCode;

	@Column(name="RSN_DESC")
	private String rsnDesc;

	@Column(name="RSN_DESC_CODE")
	private String rsnDescCode;

	@Column(name="RSN_GRP_NM")
	private String rsnGrpNm;

	public DkartReasonCodes() {
	}

	public long getRsnCode() {
		return this.rsnCode;
	}

	public void setRsnCode(long rsnCode) {
		this.rsnCode = rsnCode;
	}

	public String getRsnDesc() {
		return this.rsnDesc;
	}

	public void setRsnDesc(String rsnDesc) {
		this.rsnDesc = rsnDesc;
	}

	public String getRsnDescCode() {
		return this.rsnDescCode;
	}

	public void setRsnDescCode(String rsnDescCode) {
		this.rsnDescCode = rsnDescCode;
	}

	public String getRsnGrpNm() {
		return this.rsnGrpNm;
	}

	public void setRsnGrpNm(String rsnGrpNm) {
		this.rsnGrpNm = rsnGrpNm;
	}

}