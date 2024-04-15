package rispl.dkart.services.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the RISPL_DK_ITEM_SIZE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_SIZE")
@NamedQuery(name="ItemSize.findAll", query="SELECT i FROM ItemSize i")
public class ItemSize implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemSizePK id;

	@Column(name="ACT_SZ_CD")
	private String actSzCd;

	@Column(name="ACT_SZ_PRPTN_DESC")
	private String actSzPrptnDesc;

	@Column(name="ACT_SZ_TYP_DESC")
	private String actSzTypDesc;
	
	public ItemSize() {
	}

	public ItemSizePK getId() {
		return this.id;
	}

	public void setId(ItemSizePK id) {
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