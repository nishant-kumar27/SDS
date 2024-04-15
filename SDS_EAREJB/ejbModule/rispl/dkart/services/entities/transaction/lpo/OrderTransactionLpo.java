package rispl.dkart.services.entities.transaction.lpo;

import java.io.Serializable;
import javax.persistence.*;

import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeaderPK;


/**
 * The persistent class for the ORD_TRAN_LPO database table.
 * 
 */
@Entity
@Table(name="ORD_TRAN_LPO")
@NamedQuery(name="OrderTransactionLpo.findAll", query="SELECT o FROM OrderTransactionLpo o")
public class OrderTransactionLpo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderTransactionLpoPK id;

	@Column(name="LPO_DATE")
	private String lpoDate;

	@Column(name="LPO_NO")
	private String lpoNumber;

	@Lob
	@Column(name="LPO_SLIP_CONTENT")
	private byte[] lpoSlipContent;

	@Column(name="LPO_SLIP_NAME")
	private String lpoSlipName;

	@Column(name="LPO_SLIP_TYPE")
	private String lpoSlipType;
	
	//bi-directional many-to-one association to OrderTranHeader
	//chiranjibee comments to add insertable and updatable flag
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="DC_DY_ORD", referencedColumnName="DC_DY_ORD", insertable = false, updatable = false),
		@JoinColumn(name="ORD_WS", referencedColumnName="ORD_WS",insertable = false, updatable = false),
		@JoinColumn(name="RT_STR_ID", referencedColumnName="RT_STR_ID",insertable = false, updatable = false),
		@JoinColumn(name="TRN_SEQ", referencedColumnName="TRN_SEQ",insertable = false, updatable = false)
		})
	private OrderTranHeader ordTranHeader;

	public OrderTranHeader getOrdTranHeader() {
		return ordTranHeader;
	}

	public void setOrdTranHeader(OrderTranHeader ordTranHeader) {
		this.ordTranHeader = ordTranHeader;
	}

	public OrderTransactionLpo() {
	}
	
	public OrderTransactionLpo(OrderTranHeaderPK id) 
	{
		this.id=new OrderTransactionLpoPK(id);
	}
	public OrderTransactionLpoPK getId() {
		return this.id;
	}

	public void setId(OrderTransactionLpoPK id) {
		this.id = id;
	}

	public String getLpoDate() {
		return this.lpoDate;
	}

	public void setLpoDate(String lpoDate) {
		this.lpoDate = lpoDate;
	}

	public String getLpoNumber() {
		return this.lpoNumber;
	}

	public void setLpoNumber(String lpoNumber) {
		this.lpoNumber = lpoNumber;
	}

	public byte[] getLpoSlipContent() {
		return this.lpoSlipContent;
	}

	public void setLpoSlipContent(byte[] lpoSlipContent) {
		this.lpoSlipContent = lpoSlipContent;
	}

	public String getLpoSlipName() {
		return this.lpoSlipName;
	}

	public void setLpoSlipName(String lpoSlipName) {
		this.lpoSlipName = lpoSlipName;
	}

	public String getLpoSlipType() {
		return this.lpoSlipType;
	}

	public void setLpoSlipType(String lpoSlipType) {
		this.lpoSlipType = lpoSlipType;
	}

}