package pp.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Activity {
	private PpApp ppApp;
	private Project project;
	private String title;
	private String description;
	private LocalDate startDate;
	private int estimatedTime; // hours
	private int clockedTime; // minutes
	private LocalTime startTime;
	protected List<User> assignedUsers= new ArrayList<>();
	
	public Activity(PpApp ppApp, Project project) {
		this.ppApp = ppApp;
		this.project = project;
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

	public void setEstimatedTime(int i) {
		if (i > 0){			
			this.estimatedTime = i;
		}else{
			throw new InputException("Input is not equal to or higher than zero.");
		}
	}

	public void startWork() {
		startTime = ppApp.getDateServer().getTime();
	}
	
	public void endWork() {
		int workedMinutes = ppApp.getDateServer().getTime().getMinute() - startTime.getMinute();
		if (workedMinutes > 15) {
			clockedTime += workedMinutes; 
		}
	}
	
	public String getTitle() {return title;}
	public String getDescription() { return description; }
	public LocalDate getStartDate() { return startDate; }
	public int getEstimatedTime(){ return this.estimatedTime; }
	public int getClockedTime(){ return this.clockedTime; }
	public LocalTime getStartTime() { return this.startTime; }
	public Project getProject() { return this.project; }

	public List<User> getUsers() {
		return assignedUsers;
	}

	public User searchUser(String UserId) throws ActivityException {
		
		for (User user : assignedUsers) {
			if (user.getUserId().equals(UserId)) {
				return user;
			} 
		}
		throw new ActivityException("Activity operation not allowed." + "User not assigned to activity"); 
	}
}
