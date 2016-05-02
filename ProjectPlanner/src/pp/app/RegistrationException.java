package pp.app;

@SuppressWarnings("serial")
public class RegistrationException extends RuntimeException {

	private String operation;
	
	public RegistrationException(String operation){
		super("Registration operation not allowed. " + operation);
		this.operation = operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation(){
		return operation;
	}
	
}
