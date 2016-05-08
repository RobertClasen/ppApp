package pp.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import static java.time.temporal.ChronoUnit.MINUTES;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	private PpApp ppApp;
	private Project project;
	private String title;
	private String description;
	private LocalDate startDate;
	private Long estimatedTime; // hours
	protected int clockedTime; // minutes
	private LocalTime startTime;
	protected List<User> assignedUsers = new ArrayList<>();
	
	public Activity(PpApp ppApp, Project project) {
		this.ppApp = ppApp;
		this.project = project;
		this.clockedTime = 0;
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

	public void setEstimatedTime(Long l) {
		if (l > 0){			
			this.estimatedTime = l;
		}else{
			throw new InputException("Input is not equal to or higher than zero.");
		}
	}

	
	public void assignUserToActivity(User user) {
		this.assignedUsers.add(user);
		user.activities.add(this);
		user.addProject(this.project);
	}
	
	
	
	public String getTitle() {return title;}
	public String getDescription() { return description; }
	public LocalDate getStartDate() { return startDate; }
	public Long getEstimatedTime(){ return this.estimatedTime; }
	public int getClockedTime(){ return this.clockedTime; }
	public LocalTime getStartTime() { return this.startTime; }
	public Project getProject() { return this.project; }
	public List<User> getUsers() { return assignedUsers; }

	public User searchUser(String UserId) throws ActivityException {
		
		for (User user : assignedUsers) {
			if (user.getUserId().equals(UserId)) {
				return user;
			} 
		}
		throw new ActivityException("Activity operation not allowed." + "User not assigned to activity"); 
	}
	
	@Override
	public String toString() {
		String s = "Title:" + "\n\t" + getTitle() + "\n\n" 
				+ "Description:" +"\n\t" + getDescription() + "\n\n" 
				+ "Start date:" +"\n\t" + getStartDate().toString() + "\n\n" 
				+ "Clocked time:" +"\n\t" + "Completed hours - " + getClockedTime()/60 + "\n\t" 
				+ "Estimated hours - " + getEstimatedTime();
		return s;
	}
}
