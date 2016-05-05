package pp.app;

import java.util.ArrayList;
import java.util.List;

public class User {
	private PpApp ppApp;
	private String firstName;
	private String lastName;
	private String userId;
	protected List<Activity> activities = new ArrayList<>();
	protected List<Activity> assistanceActivities = new ArrayList<>(); 
	
	private final static int NAME_MAX_LENGTH = 15;
	private final static int NAME_MIN_LENGTH = 2;
	
	public User(PpApp ppApp) {
		this.ppApp = ppApp;
	}
	
	public void setFirstName(String firstName) {
		if (ppApp.getInputValidation().stringLength(firstName, NAME_MIN_LENGTH, NAME_MAX_LENGTH) &&
				ppApp.getInputValidation().legalCharacters(firstName)) {
			this.firstName = firstName;
		}
	}
	
	public void setLastName(String lastName) {
		if (ppApp.getInputValidation().stringLength(lastName, NAME_MIN_LENGTH, NAME_MAX_LENGTH) &&
				ppApp.getInputValidation().legalCharacters(lastName)) {
			this.lastName = lastName;
		}
	}
	
	/**
	 * Getters and setters
	 */
	public String getFirstname() { return firstName; }
	public String getLastname() { return lastName; }
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }

	public List<Activity> getActivities() {
		return this.activities;
	}

	public void seekAssistance(User u, Activity a) {
		u.assistanceActivities .add(a);
	}
	
	
}
