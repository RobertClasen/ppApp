package pp.app;

@SuppressWarnings("serial")
public class AvailabilityException extends RuntimeException {

	private String operation;
	
	public AvailabilityException(String operation) {
		super("Availability operation not allowed. " + operation);
		this.operation = operation;
	}

//	/*
//	 * Getter and setter
//	 */
//	public void setOperation(String operation) {
//		this.operation = operation;
//	}
//	
//	public String getOperation(){
//		return operation;
//	}
//	
}
