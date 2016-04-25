package pp.app;

import java.util.List;

public class UserId {
	private User user;
	private List<User> listOfUsers;
	private String firstHalf;
	private String secondHalf;
	private String userId;
	private int a; // alteration counter 
	
	public UserId(PpApp ppApp, User user) {
		this.user = user;
		listOfUsers = ppApp.getUsers();
		
		makeInitialUserId();
		checkForDuplicates();
		makeFinalUserId();
	}

	private void makeInitialUserId() {
		firstHalf = user.getFirstname().substring(0, 2).toLowerCase();
		secondHalf = user.getLastname().substring(0, 2).toLowerCase();
	}
	
	private void checkForDuplicates() {
		for (User otherUser : listOfUsers) {
			if (otherUser.getLastname().substring(0, 2).toLowerCase().equals(secondHalf)) {
				alterSecondHalf();
				checkForDuplicates();
			}
		}
	}
	
	private void alterSecondHalf() {
		a++;
		secondHalf = user.getLastname().substring(0+a, 2+a).toLowerCase();
	}
	
	private void makeFinalUserId() {
		userId = firstHalf + secondHalf;
	}
	
	@Override
	public String toString() {
		return userId;
	}

}
