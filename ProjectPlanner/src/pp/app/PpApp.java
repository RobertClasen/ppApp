package pp.app;

import java.util.ArrayList;
import java.util.List;

public class PpApp {
	private InputValidation inputValidation = new InputValidation();
	private boolean isLoggedIn;
	private List<User> users = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();
	private ProjectLeaderQueue projectLeaderQueue = new ProjectLeaderQueue();

	public boolean logIn(String userId) {
		userId = userId.toLowerCase();
		if ("joni".equals(userId)) {
			isLoggedIn = true;
		}
		return isLoggedIn;
	}

	public void registerUser(User u) {
		UserId userId = new UserId(this, u);
		u.setUserId(userId.toString());
		users.add(u);
		projectLeaderQueue.enqueue(u);
	}
	
	public void deregisterUser(User u) {		
		if(!users.contains(u)) {
			throw new RegistrationException("User is not registered.");
		}
		users.remove(u);
	}

	public void addProject(Project p) {
		if (projects.size() >= 50)
			throw new ProjectException("Number of projects reached upper limit.");
		
		p.setRunningNumber();
		p.setProjectLeader();
		projects.add(p);
	}
	
	public void endProject(Project p) {
		projectLeaderQueue.enqueue(p.getProjectLeader());
		projects.remove(p);
	}
	
	
	/**
	 * Getters and setters. 
	 */
	public List<User> getUsers() { return users; }
	public List<Project> getProjects() { return projects; }
	public ProjectLeaderQueue getProjectLeaderQueue() { return projectLeaderQueue; }
	public void setProjects(List<Project> projects) { this.projects = projects; }
	public InputValidation getInputValidation() { return inputValidation; }
}
