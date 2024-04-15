package rispl.services.Customer;

public class CustomerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8205773772886936092L;

	public CustomerException()
	  {
	  }

	  public CustomerException(String msg)
	  {
	    super(msg);
	  }

	  public CustomerException(String msg, Throwable t)
	  {
	    super(msg, t);
	  }

	
}
