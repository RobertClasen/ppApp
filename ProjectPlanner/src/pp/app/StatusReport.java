package pp.app;

import java.util.ArrayList;
import java.util.List;

public class StatusReport {
	
	private Project project;
	private List<Activity> activities = new ArrayList<>();
	private final static String NEWLINE = "\n";
	
	public StatusReport(Project project) {
		this.project=project;
		this.activities = project.getActivities();
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
	
	public String listOfActivities() {
		String s = "List of activities" + NEWLINE;
		for (Activity a : this.activities) {
			String progress = " - (" + a.getClockedTime()/60 + "/" + a.getEstimatedTime() + ")";
			s += "\t" + a.getTitle() + progress + NEWLINE;
		}
		return s;
	}
	
	public String assignedWorkers() {
		String s = "Assigned workers" + NEWLINE;
		System.out.println("Activities: " + this.activities.size());
		for (Activity a : this.activities) {
			System.out.println("Users: " + a.assignedUsers.size());
			for (User u : a.assignedUsers) {
				s += "\t" + u.getFirstname() + " " + u.getLastname() + " - " + u.getUserId() + NEWLINE;
			}	
		}
		return s;
	}
	
	
	
}
