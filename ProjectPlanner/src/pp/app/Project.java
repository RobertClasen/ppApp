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
	protected List<User> users = new ArrayList<>();
	private StatusReport statusReport;

	public Project(PpApp ppApp) {
		this.ppApp = ppApp;
	}
	
	public void addActivity(Activity a) {
		this.activities.add(a);
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
	
	public void generateStatusReport(User u) {
		if (!u.equals(projectLeader))
			throw new ProjectException("User is not project leader.");
		
		statusReport = new StatusReport(this);
	}
	
	@Override
	public String toString() {
		String s = "Title:" + "\n\t" + getTitle() + "\n\n"
				+ "Running number:" + "\n\t" + getRunningNumber() + "\n\n"
				+ "Description:" +"\n\t" + getDescription() + "\n\n" 
				+ "Project leader:" + "\n\t" + getProjectLeader().getFirstname() + " "  + getProjectLeader().getLastname() + "\n\n"
				+ "Start date:" +"\n\t" + getStartDate().toString();
		return s;
	}
	
	/*
	 * Getters and setters
	 */
	public void setTitle(String title) {
		if (ppApp.getInputValidation().stringLength(title, 2, 35))
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

}
