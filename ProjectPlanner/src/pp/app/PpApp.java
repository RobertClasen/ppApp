package pp.app;

import java.util.ArrayList;
import java.util.List;

public class PpApp {
	private boolean isLoggedIn;
	private List<User> users = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();

	public boolean logIn(String userId) {
		userId = userId.toLowerCase();
		if ("kris".equals(userId)) {
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
		if(users.contains(u)) {			
			users.remove(u);
		} else {
			throw new RegistrationException("UserId does not exist", "Deregister user");
		}
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void addProject(Project p) {
		projects.add(p);
	}
	
	
}
