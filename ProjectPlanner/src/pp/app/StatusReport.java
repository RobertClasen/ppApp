package pp.app;

import java.util.ArrayList;
import java.util.List;

public class StatusReport {
	
	private Project project;
	private List<Activity> activities = new ArrayList<>();
	private final static String NEWLINE = "\n";
	
	public StatusReport(Project project) {
		this.project=project;
		this.activities = activities;
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
	
	public String listOfActivities(List<Activity> activities) {
//		String progress = " - (" + clockedTime +  + ")";
		String s = "List of activities" + NEWLINE;
		for (Activity a : activities) {
			String progress = " - (" + a.getClockedTime()/60 + "/" + a.getEstimatedTime() + ")";
			s += "\t" + a.getTitle() + progress + NEWLINE;
		}
		return s;
	}
	
	
	
}
