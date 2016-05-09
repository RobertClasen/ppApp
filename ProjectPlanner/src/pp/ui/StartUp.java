package pp.ui;

import java.time.LocalDate;
import java.time.Month;

import pp.app.Activity;
import pp.app.PpApp;
import pp.app.Project;
import pp.app.User;

/**
 * @author Robert
 */

public class StartUp {
	private PpApp ppApp;
	final LocalDate date1 = LocalDate.of(2016, Month.MAY, 11);
	final LocalDate date2 = LocalDate.of(2017, Month.FEBRUARY, 1);
	final LocalDate date3 = LocalDate.of(2017, Month.MARCH, 1);

	public StartUp (PpApp ppApp) {
		this.ppApp = ppApp;
	}
	
	public Activity makeAndAddActivity(String title, String description, LocalDate startDate, Long estimatedTime, Project p) {
		Activity activity = new Activity(ppApp, p);
		activity.setTitle(title);
		activity.setDescription(description);
		activity.setStartDate(startDate);
		activity.setEstimatedTime(estimatedTime);
		p.addActivity(activity);
		return activity;
	}

	public Project makeAndAddProject(String title, String description, LocalDate startDate) {
		Project project = new Project(ppApp);
		project.setTitle(title);
		project.setDescription(description);
		project.setStartDate(startDate);
		ppApp.addProject(project);
		return project;
	}
	
	public User makeAndRegisterUser(String firstName, String lastName) {
		User user = new User(ppApp);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		ppApp.registerUser(user);
		return user;
	}
	
}
