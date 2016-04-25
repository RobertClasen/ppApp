package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestUsers {
	
	PpApp ppApp;
	User user1; 
	
	@Before
	public void setUp() {
		ppApp = new PpApp();
		user1 = new User("John", "Nielsen");
	}
	
	@Test
	public void registerUsers() throws Exception {
		ppApp.registerUser(user1);
		assertEquals(1, ppApp.getUsers().size());
	}
	
	@Test
	public void registerUsers_checkNames() throws Exception {
		assertEquals(user1.getFirstname(), "John");
		assertEquals(user1.getLastname(), "Nielsen");
	}
	
	@Test
	public void registerUsers_checkUserId() throws Exception {
		ppApp.registerUser(user1);
		assertEquals(user1.getUserId(), "joni");
	}
	
	@Test
	public void registerUsers_checkUserId_DuplicateIds() throws Exception {
		ppApp.registerUser(user1);
		User user2 = new User("Jonni", "Nissen");
		ppApp.registerUser(user2);
		assertEquals(user1.getUserId(), "joni");
		assertEquals(user2.getUserId(), "jois");
	}

	@Test
	public void deregisterUser() throws Exception {
		ppApp.registerUser(user1);
		ppApp.deregisterUser(user1);
		assertEquals(0, ppApp.getUsers().size());
	}
	
//	@Test
//	public void invalidUserRegistation_UserExists() throws Exception {
//		ppApp.registerUser(user1);
//		try {
//			ppApp.registerUser(user1);
//			fail("Expected RegistrationException");
//		}
//		catch(RegistrationException e) {
//			assertEquals("UserId already in use", e.getMessage());
//			assertEquals("Register user", e.getOperation());
//		}
//	}
	
	@Test
	public void invalidUserDeregistation_UserDoesNotExist() throws Exception {
		
		//ppApp.registerUser(u);
		User u1 = new User("Andreas", "Ustrup");
		
		try {
			ppApp.deregisterUser(u1);
			fail("Expected RegistrationException");
		}
		catch(RegistrationException e) {
			assertEquals("UserId does not exist", e.getMessage());
			assertEquals("Deregister user", e.getOperation());
		}
	}
	
	
}
