package rispl.dk.Employee;

import java.io.Serializable;

public interface EmpMerchAssociationIfc extends Serializable {
	String getMerchId();

	void setMerchId(String merchId);

	String getMerchName();

	void setMerchName(String merchName);

	String getMerchType();

	void setMerchType(String merchType);
}
