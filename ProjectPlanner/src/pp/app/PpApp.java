package pp.app;

public class PpApp {
	private boolean isLoggedIn;

	public boolean logIn(String userId) {
		userId = userId.toLowerCase();
		if ("kris".equals(userId)) {
			isLoggedIn = true;
		}
		return isLoggedIn;
	}

}
