package rispl.dkart;

public enum ConstantsEnum {
	
	EMP_SEARCHCRITERIA_ALL("All"), EMP_SEARCHCRITERIA_LINKED("Linked"), EMP_SEARCHCRITERIA_DIVISION("Division");
	
	String value;
	ConstantsEnum(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
}
