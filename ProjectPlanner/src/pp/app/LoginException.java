package pp.app;

/**
 * @author Pelle
 */
@SuppressWarnings("serial")
public class LoginException extends RuntimeException {
	
private String operation;
	
	public LoginException(String operation){
		super("Login operation not allowed. " + operation);
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
}
