package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestUsers {
	
	PpApp ppApp;
	User u; 
	
	@Before
	public void setUp() {
		ppApp = new PpApp();
		u = new User("Andreas", "Ustrup");
	}
	
	@Test
	public void registerUsers() throws Exception {
		ppApp.registerUser(u);
		assertEquals(1, ppApp.getUsers().size());
	}
	
	@Test
	public void registerUsers_checkNames() throws Exception {
		ppApp.registerUser(u);
		assertEquals(u.getFirstname(), "Andreas");
		assertEquals(u.getLastname(), "Ustrup");
	}

	@Test
	public void deregisterUser() throws Exception {
		ppApp.registerUser(u);
		ppApp.deregisterUser(u);
		assertEquals(0, ppApp.getUsers().size());
	}
	
	@Test
	public void invalidUserRegistation_UserExists() throws Exception {
		ppApp.registerUser(u);
		try {
			ppApp.registerUser(u);
			fail("Expected RegistrationException");
		}
		catch(RegistrationException e) {
			assertEquals("UserId already in use", e.getMessage());
			assertEquals("Register user", e.getOperation());
		}
	}
	
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
