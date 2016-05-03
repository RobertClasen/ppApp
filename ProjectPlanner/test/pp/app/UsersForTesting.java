package pp.app;

import java.util.ArrayList;
import java.util.List;

public class UsersForTesting {
	
	protected PpApp ppApp = new PpApp();
	protected List<User> users = new ArrayList<>();
	
	User user1 = makeUser("John", "Nielsen");
	User user2 = makeUser("John", "Nielsen");
	User user3 = makeUser("John", "Nielsen");
	User user4 = makeUser("John", "Nielsen");
	User user5 = makeUser("John", "Nielsen");
	User user6 = makeUser("Vincent", "Vega");
	User user7 = makeUser("Vincent", "Vega");
	User user8 = makeUser("Vincent", "Vega");
	User user9 = makeUser("Vincent", "Vega");
	User user10 = makeUser("Vincent", "Vega");
	User user11 = makeUser("Marsellus", "Wallace");
	User user12 = makeUser("Marsellus", "Wallace"); 
	User user13 = makeUser("Marsellus", "Wallace"); 
	User user14 = makeUser("Marsellus", "Wallace"); 
	User user15 = makeUser("Marsellus", "Wallace");
	User user16 = makeUser("Mia", "Wallace");
	User user17 = makeUser("Mia", "Wallace");
	User user18 = makeUser("Mia", "Wallace");
	User user19 = makeUser("Mia", "Wallace");
	User user20 = makeUser("Mia", "Wallace");
	User user21 = makeUser("Butch", "Coolidge");
	User user22 = makeUser("Butch", "Coolidge");
	User user23 = makeUser("Butch", "Coolidge");
	User user24 = makeUser("Butch", "Coolidge");
	User user25 = makeUser("Butch", "Coolidge");
	User user26 = makeUser("Harry", "Potter");
	User user27 = makeUser("Harry", "Potter");
	User user28 = makeUser("Harry", "Potter");
	User user29 = makeUser("Harry", "Potter");
	User user30 = makeUser("Harry", "Potter");
	User user31 = makeUser("Rubeus", "Hagrid");
	User user32 = makeUser("Rubeus", "Hagrid");
	User user33 = makeUser("Rubeus", "Hagrid");
	User user34 = makeUser("Rubeus", "Hagrid");
	User user35 = makeUser("Rubeus", "Hagrid");  
	User user36 = makeUser("Albus", "Dumbledore");
	User user37 = makeUser("Albus", "Dumbledore");
	User user38 = makeUser("Albus", "Dumbledore");
	User user39 = makeUser("Albus", "Dumbledore");
	User user40 = makeUser("Albus", "Dumbledore");
	User user41 = makeUser("Tom", "Riddle");
	User user42 = makeUser("Tom", "Riddle");
	User user43 = makeUser("Tom", "Riddle");
	User user44 = makeUser("Tom", "Riddle");
	User user45 = makeUser("Tom", "Riddle");
	User user46 = makeUser("Severus", "Snape");
	User user47 = makeUser("Severus", "Snape");
	User user48 = makeUser("Severus", "Snape");
	User user49 = makeUser("Severus", "Snape");
	User user50 = makeUser("Severus", "Snape");
	
	/**
	 *  Helper method.
	 *  Creates a new User object. Sets the firstName and lastName fields as dictated
	 *  by the functional test tables. 
	 */
	private User makeUser(String firstName, String lastName) {
		User user = new User(ppApp);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		users.add(user);
		
		return user;
	}

}
