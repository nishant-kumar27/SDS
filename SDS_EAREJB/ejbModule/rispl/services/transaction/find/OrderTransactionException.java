package rispl.services.transaction.find;

public class OrderTransactionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8205773772886936092L;

	public OrderTransactionException()
	  {
	  }

	  public OrderTransactionException(String msg)
	  {
	    super(msg);
	  }

	  public OrderTransactionException(String msg, Throwable t)
	  {
	    super(msg, t);
	  }

	
}
