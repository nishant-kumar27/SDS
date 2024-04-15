package rispl.dkart.services.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RISPL_DK_ITEM_MSGS database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITEM_MSGS")
@NamedQuery(name="ItemMsg.findAll", query="SELECT i FROM ItemMsg i")
public class ItemMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemMsgPK id;

	@Lob
	@Column(name="NA_MSG_DPLY")
	private String naMsgDply;

	@Column(name="NM_MSG_DPLY")
	private String nmMsgDply;

	public ItemMsg() {
	}

	public ItemMsgPK getId() {
		return this.id;
	}

	public void setId(ItemMsgPK id) {
		this.id = id;
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

}