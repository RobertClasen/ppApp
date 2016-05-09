package pp.app;

/**
 * @author Rasmus
 */
@SuppressWarnings("serial")
public class InputException extends RuntimeException {

	private String operation;
	
	public InputException(String operation){
		super("Input operation not allowed. " + operation);
		this.operation = operation;
	}

}