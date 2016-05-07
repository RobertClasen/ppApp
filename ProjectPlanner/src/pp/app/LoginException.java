package pp.app;

@SuppressWarnings("serial")
public class LoginException extends Exception {
	
private String operation;
	
	public LoginException(String operation){
		super("Login operation not allowed. " + operation);
		this.operation = operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation(){
		return operation;
	}
}
