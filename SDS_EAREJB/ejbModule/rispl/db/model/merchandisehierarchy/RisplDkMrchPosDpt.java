package rispl.db.model.merchandisehierarchy;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_MRCH_POS_DPT database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_MRCH_POS_DPT")
@NamedQuery(name="RisplDkMrchPosDpt.findAll", query="SELECT r FROM RisplDkMrchPosDpt r")
public class RisplDkMrchPosDpt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkMrchPosDptPK id;

	@Column(name="CD_ENT_SRT")
	private BigDecimal cdEntSrt;

	@Column(name="DFLT_ENT_CD_FLG")
	private String dfltEntCdFlg;

	@Column(name="FL_CD_ENT_ENAB")
	private String flCdEntEnab;

	public RisplDkMrchPosDpt() {
	}

	public RisplDkMrchPosDptPK getId() {
		return this.id;
	}

	public void setId(RisplDkMrchPosDptPK id) {
		this.id = id;
	}

	public BigDecimal getCdEntSrt() {
		return this.cdEntSrt;
	}

	public void setCdEntSrt(BigDecimal cdEntSrt) {
		this.cdEntSrt = cdEntSrt;
	}

	public String getDfltEntCdFlg() {
		return this.dfltEntCdFlg;
	}

	public void setDfltEntCdFlg(String dfltEntCdFlg) {
		this.dfltEntCdFlg = dfltEntCdFlg;
	}

	public String getFlCdEntEnab() {
		return this.flCdEntEnab;
	}

	public void setFlCdEntEnab(String flCdEntEnab) {
		this.flCdEntEnab = flCdEntEnab;
	}

}