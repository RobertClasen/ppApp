package pp.app;

/**
 * @author Kristian
 */
@SuppressWarnings("serial")
public class AvailabilityException extends RuntimeException {

	private String operation;
	
	public AvailabilityException(String operation) {
		super("Availability operation not allowed. " + operation);
		this.operation = operation;
	}
}
