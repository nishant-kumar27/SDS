package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_ITEM_COLOR database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_COLOR")
@NamedQuery(name="ItemColor.findAll", query="SELECT i FROM ItemColor i")
public class ItemColor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemColorPK id;

	@Column(name="CLR_DESC")
	private String clrDesc;

	@Column(name="CLR_NM")
	private String clrNm;

	public ItemColor() {
	}

	public ItemColorPK getId() {
		return this.id;
	}

	public void setId(ItemColorPK id) {
		this.id = id;
	}

	public String getClrDesc() {
		return this.clrDesc;
	}

	public void setClrDesc(String clrDesc) {
		this.clrDesc = clrDesc;
	}

	public String getClrNm() {
		return this.clrNm;
	}

	public void setClrNm(String clrNm) {
		this.clrNm = clrNm;
	}

}