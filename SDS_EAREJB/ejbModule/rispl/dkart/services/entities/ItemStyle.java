package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_ITEM_STYLE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_STYLE")
@NamedQuery(name="ItemStyle.findAll", query="SELECT i FROM ItemStyle i")
public class ItemStyle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemStylePK id;

	@Column(name="STYL_DESC")
	private String stylDesc;

	@Column(name="STYL_NM")
	private String stylNm;

	public ItemStyle() {
	}

	public ItemStylePK getId() {
		return this.id;
	}

	public void setId(ItemStylePK id) {
		this.id = id;
	}

	public String getStylDesc() {
		return this.stylDesc;
	}

	public void setStylDesc(String stylDesc) {
		this.stylDesc = stylDesc;
	}

	public String getStylNm() {
		return this.stylNm;
	}

	public void setStylNm(String stylNm) {
		this.stylNm = stylNm;
	}

}