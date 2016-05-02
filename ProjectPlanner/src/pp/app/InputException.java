package pp.app;

@SuppressWarnings("serial")
public class InputException extends RuntimeException {

	private String operation;
	
	public InputException(String operation){
		super("Input operation not allowed. " + operation);
		this.operation = operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation(){
		return operation;
	}
	
}