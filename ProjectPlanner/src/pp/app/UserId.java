package pp.app;

import java.util.List;


/**
 * 
 * A user id is a four-letter code unique to each user.
 * It is automatically generated and assigned to the user when she is registered to the system.
 * It is a concatenation of the first pairs of letters of the user's first and last name.
 * 
 * If the initially generated user id is a duplicate, i.e. it is already assigned to an existing user,
 * then the second part of the new user id is altered such that instead of letters 0 and 1,
 * letters 1 and 2 is used. This procedure goes on recursively until the new user id is unique. 
 * 
 */
public class UserId {
	private User user;
	private List<User> listOfUsers;
	private String firstHalf;
	private String secondHalf;
	private String userId;
	private int a; // alteration counter for secondHalf.
	private int b; // alteration counter for firstHalf.
	
	public UserId(PpApp ppApp, User user) throws RegistrationException {
		this.user = user;
		listOfUsers = ppApp.getUsers();
		
		makeInitialUserId();
		checkForDuplicates();
		assignUserId();
	}

	private void makeInitialUserId() {
		firstHalf = user.getFirstname().substring(0, 2).toLowerCase();
		secondHalf = user.getLastname().substring(0, 2).toLowerCase();
		assignUserId();
	}
	
	private void checkForDuplicates() throws RegistrationException {
		for (User otherUser : listOfUsers) {
			if (otherUser.getUserId().equals(userId)) {
				alterSecondHalf();
				checkForDuplicates();
			}
		}
	}
	
	private void alterSecondHalf() throws RegistrationException {
		if(a < user.getLastname().length()-2) {
			a++;
			secondHalf = user.getLastname().substring(0+a, 2+a).toLowerCase();
			assignUserId();
		} else {
			alterFirstHalf();
		}
	}
	
	private void alterFirstHalf() throws RegistrationException {
		if(b < user.getFirstname().length()-2) {
			String firstLetter = user.getFirstname().substring(0, 1).toLowerCase();
			String secondLetter = user.getFirstname().substring(2+b, 3+b).toLowerCase();
			firstHalf = firstLetter + secondLetter;
			assignUserId();
			b++;
		} else {
			throw new RegistrationException("Enter user ID manually.");
		}
	}

	private void assignUserId() {
		userId = firstHalf + secondHalf;
	}
	
	@Override
	public String toString() {
		return userId;
	}

}
