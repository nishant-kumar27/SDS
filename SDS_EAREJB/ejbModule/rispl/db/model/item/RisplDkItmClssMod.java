package rispl.db.model.item;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RISPL_DK_ITM_CLSS_MOD database table.
 * 
 */
@Entity
@Table(name="RISPL_DK_ITM_CLSS_MOD")
@NamedQuery(name="RisplDkItmClssMod.findAll", query="SELECT r FROM RisplDkItmClssMod r")
public class RisplDkItmClssMod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CLSS_FCTN_ID", length=25)
	private String clssFctnId;

	@Column(name="ITM_ID", length=25)
	private String itmId;

	@Id
	@SequenceGenerator(name="RISPL_DK_ITM_CLSS_MOD_SEQID_GENERATOR", sequenceName="ITM_CLS_MOD_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RISPL_DK_ITM_CLSS_MOD_SEQID_GENERATOR")
	@Column(name="SEQ_ID", precision=22)
	private BigDecimal seqId;

	public RisplDkItmClssMod() {
	}

	public String getClssFctnId() {
		return this.clssFctnId;
	}

	public void setClssFctnId(String clssFctnId) {
		this.clssFctnId = clssFctnId;
	}

	public String getItmId() {
		return this.itmId;
	}

	public void setItmId(String itmId) {
		this.itmId = itmId;
	}

	public BigDecimal getSeqId() {
		return this.seqId;
	}

	public void setSeqId(BigDecimal seqId) {
		this.seqId = seqId;
	}

}