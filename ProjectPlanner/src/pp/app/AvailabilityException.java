package pp.app;

@SuppressWarnings("serial")
public class AvailabilityException extends RuntimeException {

	private String operation;
	
	public AvailabilityException(String operation) {
		super("Activity operation not allowed. " + operation);
		this.operation = operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation(){
		return operation;
	}
	
}
