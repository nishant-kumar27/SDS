package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_ITEM_RLTD_ITMS database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_RLTD_ITMS")
@NamedQuery(name="RisplDkItemRltdItm.findAll", query="SELECT r FROM RisplDkItemRltdItm r")
public class RisplDkItemRltdItm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkItemRltdItmPK id;

	@Column(name="RMV_RLTD_ITM_FLG", length=20)
	private String rmvRltdItmFlg;

	@Column(name="RTN_RLTD_ITM_FLG", length=20)
	private String rtnRltdItmFlg;

	public RisplDkItemRltdItm() {
	}

	public RisplDkItemRltdItmPK getId() {
		return this.id;
	}

	public void setId(RisplDkItemRltdItmPK id) {
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