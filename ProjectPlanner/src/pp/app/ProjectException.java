package pp.app;

/**
 * @author Robert
 */
@SuppressWarnings("serial")
public class ProjectException extends RuntimeException {

	private String operation;
	
	public ProjectException(String operation) {
		super("Project operation not allowed. " + operation);
		this.operation = operation;
	}
}
