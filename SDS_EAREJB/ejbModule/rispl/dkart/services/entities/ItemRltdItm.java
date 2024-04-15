package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_ITEM_RLTD_ITMS database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_RLTD_ITMS")
@NamedQuery(name="ItemRltdItm.findAll", query="SELECT i FROM ItemRltdItm i")
public class ItemRltdItm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemRltdItmPK id;

	@Column(name="RMV_RLTD_ITM_FLG")
	private String rmvRltdItmFlg;

	@Column(name="RTN_RLTD_ITM_FLG")
	private String rtnRltdItmFlg;

	public ItemRltdItm() {
	}

	public ItemRltdItmPK getId() {
		return this.id;
	}

	public void setId(ItemRltdItmPK id) {
		this.id = id;
	}

	public String getRmvRltdItmFlg() {
		return this.rmvRltdItmFlg;
	}

	public void setRmvRltdItmFlg(String rmvRltdItmFlg) {
		this.rmvRltdItmFlg = rmvRltdItmFlg;
	}

	public String getRtnRltdItmFlg() {
		return this.rtnRltdItmFlg;
	}

	public void setRtnRltdItmFlg(String rtnRltdItmFlg) {
		this.rtnRltdItmFlg = rtnRltdItmFlg;
	}

}