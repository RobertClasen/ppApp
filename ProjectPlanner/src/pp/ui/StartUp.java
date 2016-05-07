package pp.ui;

import java.time.LocalDate;
import pp.app.Activity;
import pp.app.PpApp;
import pp.app.Project;
import pp.app.User;

public class StartUp {

	private PpApp ppApp;
	

	public StartUp (PpApp ppApp) {
		this.ppApp = ppApp;
	}
	
	private Activity makeAndAddActivity(String title, String description, LocalDate startDate, Long estimatedTime, Project p) {
		Activity activity = new Activity(ppApp, p);
		activity.setTitle(title);
		activity.setDescription(description);
		activity.setStartDate(startDate);
		activity.setEstimatedTime(estimatedTime);
		p.addActivity(activity);
		return activity;
	}
	
	private Project makeAndAddProject(String title, String description, LocalDate startDate) {
		Project project = new Project(ppApp);
		project.setTitle(title);
		project.setDescription(description);
		project.setStartDate(startDate);
		ppApp.addProject(project);
		return project;
	}
	
	private void makeAndRegisterUser(String firstName, String lastName) {
		User user = new User(ppApp);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		ppApp.registerUser(user);		
	}
	
}
