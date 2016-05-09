package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Kristian
 */

public class TestUserRegistration {
	private PpApp ppApp;
	private User user1; 
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		user1 = new User(ppApp);
		user1.setFirstName("John");
		user1.setLastName("Nielsen");
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Tests for registration and deregistration of users. 
	 */
	
	//Input data set A
	@Test
	public void registerUser_validNames() {
		ppApp.registerUser(user1);
		assertEquals(1, ppApp.getUsers().size());
	}
	
	@Test
	public void registerUser_validNames_getNames() {
		ppApp.registerUser(user1);
		assertEquals("John", ppApp.getUsers().get(0).getFirstname());
		assertEquals("Nielsen", ppApp.getUsers().get(0).getLastname());
	}
	
	//Input data set B
	@Test
	public void deregisterUser_userIsRegistered() {
		ppApp.registerUser(user1);
		ppApp.deregisterUser(user1);
		assertEquals(0, ppApp.getUsers().size());
	}
	
	//Input data set C
	@Test
	public void deregisterUser_UserIsNotRegistered() {
		thrown.expect(RegistrationException.class);
		thrown.expectMessage("User is not registered.");
		
		User user2 = makeUser("Andreas", "Hansen");
		ppApp.deregisterUser(user2);
	}
	
	
	/**
	 *  Helper method.
	 *  Creates a new User object. Sets the firstName and lastName fields as dictated
	 *  by the functional test tables. 
	 */
	private User makeUser(String firstName, String lastName) {
		User user = new User(ppApp);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return user;
	}
}
