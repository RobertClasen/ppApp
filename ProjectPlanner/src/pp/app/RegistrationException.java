package pp.app;

@SuppressWarnings("serial")
public class RegistrationException extends Exception{

	private String operation;
	
	public RegistrationException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
	}
	
	public String getOperation(){
		return operation;
	}
	
}
