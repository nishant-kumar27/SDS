package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_ITEM_SIZE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_SIZE")
@NamedQuery(name="RisplDkItemSize.findAll", query="SELECT r FROM RisplDkItemSize r")
public class RisplDkItemSize implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkItemSizePK id;

	@Column(name="ACT_SZ_CD", length=20)
	private String actSzCd;

	@Column(name="ACT_SZ_PRPTN_DESC", length=250)
	private String actSzPrptnDesc;

	@Column(name="ACT_SZ_TYP_DESC", length=250)
	private String actSzTypDesc;

	public RisplDkItemSize() {
	}

	public RisplDkItemSizePK getId() {
		return this.id;
	}

	public void setId(RisplDkItemSizePK id) {
		this.id = id;
	}

	public String getActSzCd() {
		return this.actSzCd;
	}

	public void setActSzCd(String actSzCd) {
		this.actSzCd = actSzCd;
	}

	public String getActSzPrptnDesc() {
		return this.actSzPrptnDesc;
	}

	public void setActSzPrptnDesc(String actSzPrptnDesc) {
		this.actSzPrptnDesc = actSzPrptnDesc;
	}

	public String getActSzTypDesc() {
		return this.actSzTypDesc;
	}

	public void setActSzTypDesc(String actSzTypDesc) {
		this.actSzTypDesc = actSzTypDesc;
	}

}