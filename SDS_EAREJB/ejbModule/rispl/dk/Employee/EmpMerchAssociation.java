package rispl.dk.Employee;

public class EmpMerchAssociation implements EmpMerchAssociationIfc {

	private static final long serialVersionUID = 1L;

	private String merchId;
	private String merchName;
	private String merchType;

	@Override
	public String getMerchId() {
		return merchId;
	}

	@Override
	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	@Override
	public String getMerchName() {
		return merchName;
	}

	@Override
	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	@Override
	public String getMerchType() {
		return merchType;
	}

	@Override
	public void setMerchType(String merchType) {
		this.merchType = merchType;
	}

	@Override
	public String toString() {
		return "EmpMerchAssociation [merchId=" + merchId + ", merchName=" + merchName + ", merchType=" + merchType
				+ "]";
	}

}
