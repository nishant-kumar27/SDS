package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_ITEM_MSGS database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_MSGS")
@NamedQuery(name="RisplDkItemMsg.findAll", query="SELECT r FROM RisplDkItemMsg r")
public class RisplDkItemMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RisplDkItemMsgPK id;

	@Column(name="DPLY_LOC_TYP", precision=22)
	private BigDecimal dplyLocTyp;

	@Lob
	@Column(name="NA_MSG_DPLY")
	private String naMsgDply;

	@Column(name="NM_MSG_DPLY", length=20)
	private String nmMsgDply;

	@Column(name="USG_TRN_TYP", precision=22)
	private BigDecimal usgTrnTyp;

	public RisplDkItemMsg() {
	}

	public RisplDkItemMsgPK getId() {
		return this.id;
	}

	public void setId(RisplDkItemMsgPK id) {
		this.id = id;
	}

	public BigDecimal getDplyLocTyp() {
		return this.dplyLocTyp;
	}

	public void setDplyLocTyp(BigDecimal dplyLocTyp) {
		this.dplyLocTyp = dplyLocTyp;
	}

	public String getNaMsgDply() {
		return this.naMsgDply;
	}

	public void setNaMsgDply(String naMsgDply) {
		this.naMsgDply = naMsgDply;
	}

	public String getNmMsgDply() {
		return this.nmMsgDply;
	}

	public void setNmMsgDply(String nmMsgDply) {
		this.nmMsgDply = nmMsgDply;
	}

	public BigDecimal getUsgTrnTyp() {
		return this.usgTrnTyp;
	}

	public void setUsgTrnTyp(BigDecimal usgTrnTyp) {
		this.usgTrnTyp = usgTrnTyp;
	}

}