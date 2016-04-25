package pp.app;

import java.util.ArrayList;
import java.util.List;

public class PpApp {
	private boolean isLoggedIn;
	private List<User> users = new ArrayList<>();

	public boolean logIn(String userId) {
		userId = userId.toLowerCase();
		if ("kris".equals(userId)) {
			isLoggedIn = true;
		}
		return isLoggedIn;
	}

	public void registerUser(User u) throws RegistrationException {
		
		for (User user : users) {
			if(u.getUserId().equals(user.getUserId())){
				throw new RegistrationException("UserId already in use", "Register user");
			}
		}
		
		users.add(u);
	}

	public List<User> getUsers() {
		return users;
	}

	public void deregisterUser(User u) throws RegistrationException {		
		if(users.contains(u)) {			
			users.remove(u);
		} else {
			throw new RegistrationException("UserId does not exist", "Deregister user");
		}
	}

}
