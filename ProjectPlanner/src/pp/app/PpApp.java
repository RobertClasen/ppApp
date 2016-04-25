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

	public void registerUser(User u) {
		//users.add(u);
	}

	public List<User> getUsers() {
		return users;
	}

}
