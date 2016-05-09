package pp.app;

@SuppressWarnings("serial")
public class ActivityException extends RuntimeException {

	private String operation;
	
	public ActivityException(String operation) {
		super("Activity operation not allowed. " + operation);
		this.operation = operation;
	}

	/*
	 * Getter and setter
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation(){
		return operation;
	}
	
}
