package rispl.db.model.currency;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_CURR database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_CURR")
@NamedQuery(name="RisplDkCurr.findAll", query="SELECT r FROM RisplDkCurr r")
public class RisplDkCurr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CURR_ID", unique=true, nullable=false, length=10)
	private String currId;

	@Column(name="CURR_BASE_FLG", length=1)
	private String currBaseFlg;

	@Column(name="CURR_CONT_CDE", length=4)
	private String currContCde;

	@Column(name="CURR_DESC", length=250)
	private String currDesc;

	@Column(name="CURR_ISO_CDE", length=3)
	private String currIsoCde;

	@Column(name="CURR_ISSG_CONT_NAT", length=120)
	private String currIssgContNat;

	@Column(name="CURR_PRI", precision=22)
	private BigDecimal currPri;

	@Column(name="CURR_SCLE", precision=22)
	private BigDecimal currScle;

	@Column(name="ISO_CURR_NUMB", length=3)
	private String isoCurrNumb;

	public RisplDkCurr() {
	}

	public String getCurrId() {
		return this.currId;
	}

	public void setCurrId(String currId) {
		this.currId = currId;
	}

	public String getCurrBaseFlg() {
		return this.currBaseFlg;
	}

	public void setCurrBaseFlg(String currBaseFlg) {
		this.currBaseFlg = currBaseFlg;
	}

	public String getCurrContCde() {
		return this.currContCde;
	}

	public void setCurrContCde(String currContCde) {
		this.currContCde = currContCde;
	}

	public String getCurrDesc() {
		return this.currDesc;
	}

	public void setCurrDesc(String currDesc) {
		this.currDesc = currDesc;
	}

	public String getCurrIsoCde() {
		return this.currIsoCde;
	}

	public void setCurrIsoCde(String currIsoCde) {
		this.currIsoCde = currIsoCde;
	}

	public String getCurrIssgContNat() {
		return this.currIssgContNat;
	}

	public void setCurrIssgContNat(String currIssgContNat) {
		this.currIssgContNat = currIssgContNat;
	}

	public BigDecimal getCurrPri() {
		return this.currPri;
	}

	public void setCurrPri(BigDecimal currPri) {
		this.currPri = currPri;
	}

	public BigDecimal getCurrScle() {
		return this.currScle;
	}

	public void setCurrScle(BigDecimal currScle) {
		this.currScle = currScle;
	}

	public String getIsoCurrNumb() {
		return this.isoCurrNumb;
	}

	public void setIsoCurrNumb(String isoCurrNumb) {
		this.isoCurrNumb = isoCurrNumb;
	}

}