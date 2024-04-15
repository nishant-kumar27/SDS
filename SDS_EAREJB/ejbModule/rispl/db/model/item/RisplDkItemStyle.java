package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_ITEM_STYLE database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_STYLE")
@NamedQuery(name="RisplDkItemStyle.findAll", query="SELECT r FROM RisplDkItemStyle r")
public class RisplDkItemStyle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkItemStylePK id;

	@Column(name="STYL_DESC", length=250)
	private String stylDesc;

	@Column(name="STYL_NM", length=120)
	private String stylNm;

	public RisplDkItemStyle() {
	}

	public RisplDkItemStylePK getId() {
		return this.id;
	}

	public void setId(RisplDkItemStylePK id) {
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