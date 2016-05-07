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
	
	public String generate() {
		String s = "---- Project Status Report ----" + "\n\n" + 
				"Title - " + title() + "\n" + 
				"Start Date - " + startDate() + "\n" + 
				"Leader - " + projectLeader() + "\n" +
				"Total progress - " + totalProgress() + "\n\n" + 
				assignedWorkers() + "\n" + 
				listOfActivities() + "\n" + 
				"-------------------------------";
		return s;
	}
	
	public String title() {
		return project.getTitle();
	}
	
	public String startDate() {
		return project.getStartDate().toString();
	}
	
	public String projectLeader() {
		return project.getProjectLeader().getFirstname() + " " +  project.getProjectLeader().getLastname();
	}
	
	public String totalProgress() {
		int estimate = 0;
		int completedTime = 0;
		for (Activity a : this.activities) {
			estimate += a.getEstimatedTime();
			completedTime += a.getClockedTime()/60;
		}
		return "(" + completedTime + "/" + estimate + ")";
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
		for (Activity a : this.activities) {
			for (User u : a.assignedUsers) {
				s += "\t" + u.getFirstname() + " " + u.getLastname() + " - " + u.getUserId() + NEWLINE;
			}	
		}
		return s;
	}
	
	public StatusReport getStatusReport() { return this; }
	
	
}
