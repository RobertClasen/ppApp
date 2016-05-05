package pp.app;

import java.util.ArrayList;
import java.util.List;

public class UsersForTesting {
	
	protected PpApp ppApp = new PpApp();
	protected List<User> testUsers = new ArrayList<>();
	
	User user1 = makeAndRegisterUser("John", "Nielsen");
	User user2 = makeAndRegisterUser("John", "Nielsen");
	User user3 = makeAndRegisterUser("John", "Nielsen");
	User user4 = makeAndRegisterUser("John", "Nielsen");
	User user5 = makeAndRegisterUser("John", "Nielsen");
	User user6 = makeAndRegisterUser("Vincent", "Vega");
	User user7 = makeAndRegisterUser("Vincent", "Vega");
	User user8 = makeAndRegisterUser("Vincent", "Vega");
	User user9 = makeAndRegisterUser("Vincent", "Vega");
	User user10 = makeAndRegisterUser("Vincent", "Vega");
	User user11 = makeAndRegisterUser("Marsellus", "Wallace");
	User user12 = makeAndRegisterUser("Marsellus", "Wallace"); 
	User user13 = makeAndRegisterUser("Marsellus", "Wallace"); 
	User user14 = makeAndRegisterUser("Marsellus", "Wallace"); 
	User user15 = makeAndRegisterUser("Marsellus", "Wallace");
	User user16 = makeAndRegisterUser("Mia", "Wallace");
	User user17 = makeAndRegisterUser("Mia", "Wallace");
	User user18 = makeAndRegisterUser("Mia", "Wallace");
	User user19 = makeAndRegisterUser("Mia", "Wallace");
	User user20 = makeAndRegisterUser("Mia", "Wallace");
	User user21 = makeAndRegisterUser("Butch", "Coolidge");
	User user22 = makeAndRegisterUser("Butch", "Coolidge");
	User user23 = makeAndRegisterUser("Butch", "Coolidge");
	User user24 = makeAndRegisterUser("Butch", "Coolidge");
	User user25 = makeAndRegisterUser("Butch", "Coolidge");
	User user26 = makeAndRegisterUser("Harry", "Potter");
	User user27 = makeAndRegisterUser("Harry", "Potter");
	User user28 = makeAndRegisterUser("Harry", "Potter");
	User user29 = makeAndRegisterUser("Harry", "Potter");
	User user30 = makeAndRegisterUser("Harry", "Potter");
	User user31 = makeAndRegisterUser("Rubeus", "Hagrid");
	User user32 = makeAndRegisterUser("Rubeus", "Hagrid");
	User user33 = makeAndRegisterUser("Rubeus", "Hagrid");
	User user34 = makeAndRegisterUser("Rubeus", "Hagrid");
	User user35 = makeAndRegisterUser("Rubeus", "Hagrid");  
	User user36 = makeAndRegisterUser("Albus", "Dumbledore");
	User user37 = makeAndRegisterUser("Albus", "Dumbledore");
	User user38 = makeAndRegisterUser("Albus", "Dumbledore");
	User user39 = makeAndRegisterUser("Albus", "Dumbledore");
	User user40 = makeAndRegisterUser("Albus", "Dumbledore");
	User user41 = makeAndRegisterUser("Tom", "Riddle");
	User user42 = makeAndRegisterUser("Tom", "Riddle");
	User user43 = makeAndRegisterUser("Tom", "Riddle");
	User user44 = makeAndRegisterUser("Tom", "Riddle");
	User user45 = makeAndRegisterUser("Tom", "Riddle");
	User user46 = makeAndRegisterUser("Severus", "Snape");
	User user47 = makeAndRegisterUser("Severus", "Snape");
	User user48 = makeAndRegisterUser("Severus", "Snape");
	User user49 = makeAndRegisterUser("Severus", "Snape");
	User user50 = makeAndRegisterUser("Severus", "Snape");
	
	/**
	 *  Helper method.
	 *  Creates a new User object. Sets the firstName and lastName fields as dictated
	 *  by the functional test tables. 
	 */
	private User makeAndRegisterUser(String firstName, String lastName) {
		User user = new User(ppApp);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		ppApp.registerUser(user);
		testUsers.add(user);
		return user;
	}

}
