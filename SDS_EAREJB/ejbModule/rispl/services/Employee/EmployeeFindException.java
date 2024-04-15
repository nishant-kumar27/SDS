package rispl.services.Employee;

public class EmployeeFindException extends Exception {
	private static final long serialVersionUID = -245558042740052912L;

	public EmployeeFindException() {

	}

	public EmployeeFindException(String s) {
		super(s);

	}

	public EmployeeFindException(String s, Throwable o) {
		super(s, o);
	}
}
