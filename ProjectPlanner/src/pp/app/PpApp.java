package pp.app;

import java.util.ArrayList;
import java.util.List;

public class PpApp {
	private InputValidation inputValidation = new InputValidation();
	private boolean isLoggedIn;
	private List<User> users = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();

	public boolean logIn(String userId) {
		userId = userId.toLowerCase();
		if ("joni".equals(userId)) {
			isLoggedIn = true;
		}
		return isLoggedIn;
	}

	public void registerUser(User u) throws RegistrationException {
		UserId userId = new UserId(this, u);
		u.setUserId(userId.toString());
		users.add(u);
	}
	
	public void deregisterUser(User u) throws RegistrationException {		
		if(!users.contains(u)) {
			throw new RegistrationException("User ID does not exist.");
		}
		users.remove(u);
	}

	public void addProject(Project p) {
		projects.add(p);
	}
	
	
	/**
	 * Getters and setters. 
	 */
	public List<User> getUsers() { return users; }
	public List<Project> getProjects() { return projects; }
	public InputValidation getInputValidation() { return inputValidation; }
	
}
