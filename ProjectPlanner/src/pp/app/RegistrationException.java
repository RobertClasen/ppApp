package pp.app;

/**
 * @author Pelle
 */
@SuppressWarnings("serial")
public class RegistrationException extends RuntimeException {

	private String operation;
	
	public RegistrationException(String operation){
		super("Registration operation not allowed. " + operation);
		this.operation = operation;
	}
}
