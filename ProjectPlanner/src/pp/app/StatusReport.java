package pp.app;

public class StatusReport {
	
	private Project project;

	
	public StatusReport(Project project) {
		this.project=project;
		generate();
	}
	
	public String title() {
		return project.getTitle();
	}
	
	public StatusReport getStatusReport() {
		return this;
	}
	
	public String generate() {
		return title();
	}
}
