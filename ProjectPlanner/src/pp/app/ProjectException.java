package pp.app;

@SuppressWarnings("serial")
public class ProjectException extends RuntimeException {

	private String operation;
	
	public ProjectException(String operation) {
		super("Project operation not allowed. " + operation);
		this.operation = operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation(){
		return operation;
	}
	
}
