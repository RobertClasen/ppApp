package pp.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PpApp {
	private InputValidation inputValidation = new InputValidation();
	private boolean isLoggedIn;
	private User loggedInUser;
	private List<User> users = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();
	private ProjectLeaderQueue projectLeaderQueue = new ProjectLeaderQueue();
	private DateServer dateServer = new DateServer();
//	private DateServer dateServer;

	public boolean logIn(String userId) throws LoginException {
		userId = userId.toLowerCase();
		for (User u : users) {
			if(u.getUserId().equals(userId)){
				loggedInUser = u;
				return isLoggedIn = true;
			} 
		}
		throw new LoginException("User ID does not exist");
	}

	public void registerUser(User u) {
		UserId userId = new UserId(this, u);
		u.setUserId(userId.toString());
		users.add(u);
		projectLeaderQueue.enqueue(u);
	}
	
	public void deregisterUser(User u) {		
		if(!users.contains(u))
			throw new RegistrationException("User is not registered.");
		
		projectLeaderQueue.delete(u);
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
	
	public List<User> availableUsers(LocalDate date) throws AvailabilityException {
		List<User> avUsers = new ArrayList<>();
		for (User u : users){
			if(u.isAvailable(date)){
				avUsers.add(u);
			}
		}
		if(avUsers.size() == 0){
			throw new AvailabilityException("No available users at this date.");
		}else{
			return avUsers;
		}
	}
	
	/**
	 * Getters and setters. 
	 */
	public List<User> getUsers() { return users; }
	public List<Project> getProjects() { return projects; }
	public ProjectLeaderQueue getProjectLeaderQueue() { return projectLeaderQueue; }
	public void setProjects(List<Project> projects) { this.projects = projects; }
	public InputValidation getInputValidation() { return inputValidation; }
	public User getLoggedInUser() {	return loggedInUser; }
	public boolean isLoggedIn() { return this.isLoggedIn; }
	
	public void setLoggedIn(boolean b) { this.isLoggedIn = b; }
	public void setDateServer(DateServer dateServer) { this.dateServer = dateServer; }
	public DateServer getDateServer() {	return this.dateServer;	}
	public LocalDate getDate() { return dateServer.getDate(); }
	public LocalTime getTime() { return this.dateServer.getTime(); }

	public LocalDateTime getDateTime() {
		return dateServer.getDateTime();
	}

}
