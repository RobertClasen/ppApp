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

	public void registerUser(User u) throws RegistationException {
		for (User user : users) {
			if(u.getUserId().equals(user.getUserId())){
				throw new RegistationException("UserId already in use", "Register user");
			}
		}
		
		users.add(u);
	}

	public List<User> getUsers() {
		return users;
	}

	public void deregisterUser(User u) {
		users.remove(u);
	}

}
