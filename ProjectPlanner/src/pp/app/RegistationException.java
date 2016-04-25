package pp.app;

@SuppressWarnings("serial")
public class RegistationException extends Exception{

	private String operation;
	
	public RegistationException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
	}
	
	public String getOperation(){
		return operation;
	}
	
}
