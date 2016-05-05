package pp.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {

	private PpApp ppApp;
	private String title;
	private String description;
	private LocalDate startDate;
	private String runningNumber;
	private User projectLeader;
	private List<Activity> activities = new ArrayList<>();
	private StatusReport statusReport;

	public Project(PpApp ppApp) {
		this.ppApp = ppApp;
	}
	
	public void setTitle(String title) {
		if (ppApp.getInputValidation().stringLength(title, 2, 25))
			this.title = title;
	}

	public void setDescription(String description) {
		if (ppApp.getInputValidation().stringLength(description, 2, 256))
			this.description = description;
	}
	
	public void setStartDate(LocalDate startDate) {
		if (ppApp.getInputValidation().dateIsNotInPast(startDate))
			this.startDate = startDate;
	}
	
	public void setRunningNumber() {
		String numberOfProjects = "" + (ppApp.getProjects().size() + 1);
		String year = "" + LocalDate.now().getYear();

		String projectNumber = "";
		for (int i = 0; i < 4 - numberOfProjects.length(); i++) {
			projectNumber += "0";
		}
		projectNumber += numberOfProjects;
		
		this.runningNumber = projectNumber + year;
	}

	public void setProjectLeader() {
		projectLeader = ppApp.getProjectLeaderQueue().dequeue();
	}
	
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public String getTitle() {	return title; }
	public String getDescription() { return description; }
	public LocalDate getStartDate() { return startDate; }
	public String getRunningNumber() { return runningNumber; }
	public User getProjectLeader() { return projectLeader; } 
	
	public List<Activity> getActivities() {return this.activities;}
	

	public void addActivity(Activity a) {
		this.activities.add(a);
	}

	public void assignUserToActivity(User user, Activity activity) {
		activity.assignedUsers.add(user);
	}
	
	public void generateStatusReport() {
		statusReport = new StatusReport(this);
	}

}
