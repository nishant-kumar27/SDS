package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_ITEM_COLOR database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_COLOR")
@NamedQuery(name="RisplDkItemColor.findAll", query="SELECT r FROM RisplDkItemColor r")
public class RisplDkItemColor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkItemColorPK id;

	@Column(name="CLR_DESC", length=20)
	private String clrDesc;

	@Column(name="CLR_NM", length=20)
	private String clrNm;

	public RisplDkItemColor() {
	}

	public RisplDkItemColorPK getId() {
		return this.id;
	}

	public void setId(RisplDkItemColorPK id) {
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