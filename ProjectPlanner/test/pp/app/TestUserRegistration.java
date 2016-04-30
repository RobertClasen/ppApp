package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestUserRegistration {
	private PpApp ppApp;
	private User user1; 
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		user1 = new User("John", "Nielsen");
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	/**
	 * Tests for valid name lengths.
	 */
	@Test
	public void registerUsers_tooLongFirstName() throws RegistrationException {
		thrown.expect(RegistrationException.class);
		thrown.expectMessage("Invalid name length");
		User user2 = new User("Jooooooooooooohn", "Nielsen");
		ppApp.registerUser(user2);
	}
	@Test
	public void registerUsers_tooLongLastName() throws RegistrationException {
		thrown.expect(RegistrationException.class);
		thrown.expectMessage("Invalid name length");
		User user2 = new User("John", "Niiiiiiiiiielsen");
		ppApp.registerUser(user2);
	}
	@Test
	public void registerUsers_tooShortFirstName() throws RegistrationException {
		thrown.expect(RegistrationException.class);
		thrown.expectMessage("Invalid name length");
		User user2 = new User("J", "Nielsen");
		ppApp.registerUser(user2);
	}
	@Test
	public void registerUsers_tooShortLastName() throws RegistrationException {
		thrown.expect(RegistrationException.class);
		thrown.expectMessage("Invalid name length");
		
		User user2 = new User("John", "N");
		ppApp.registerUser(user2);
	}
	
	/**
	 * Tests for illegal characters.
	 */
	@Test
	public void registerUsers_firstNameIsNotLettersOnly() throws RegistrationException {
		thrown.expect(RegistrationException.class);
		thrown.expectMessage("Name contains illegal character(s).");
		
		User user2 = new User("John5", "Nielsen");
		ppApp.registerUser(user2);
	}
	@Test
	public void registerUsers_lastNameIsNotLettersOnly() throws RegistrationException {
		thrown.expect(RegistrationException.class);
		thrown.expectMessage("Name contains illegal character(s).");
		
		User user2 = new User("John", "Nielsen?");
		ppApp.registerUser(user2);
	}
	
	
	/**
	 * Other tests.
	 */
	@Test
	public void registerUsers() throws RegistrationException {
		ppApp.registerUser(user1);
		assertEquals(1, ppApp.getUsers().size());
	}
	
	@Test
	public void registerUsers_checkNames() throws RegistrationException {
		assertEquals("John", user1.getFirstname());
		assertEquals("Nielsen", user1.getLastname());
	}
	
	
	/**
	 * Tests for the generating of user ID's.
	 */
	@Test
	public void registerUsers_checkUserId() throws Exception {
		ppApp.registerUser(user1);
		assertEquals("joni", ppApp.getUsers().get(0).getUserId());
	}
	@Test
	public void registerUsers_checkUserId_DuplicateIds() throws RegistrationException {
		ppApp.registerUser(user1);
		User user2 = new User("Jones", "Nissen");
		ppApp.registerUser(user2);
		assertEquals("joni", ppApp.getUsers().get(0).getUserId());
		assertEquals("jois", ppApp.getUsers().get(1).getUserId());
	}
	@Test
	public void registerUsers_DuplicateIds_ShortLastname1() throws RegistrationException {
		ppApp.registerUser(user1);
		User user2 = new User("Joan", "Ni");
		ppApp.registerUser(user2);
		assertEquals("joni",ppApp.getUsers().get(0).getUserId());
		assertEquals("jani",ppApp.getUsers().get(1).getUserId());
	}
	@Test 
	public void registerUsers_DuplicateIds_ShortLastname2() throws RegistrationException {
		User user2 = new User("Johnny", "Ni");
		User user3 = new User("Johnny", "Ni");
		ppApp.registerUser(user1);
		ppApp.registerUser(user2);
		ppApp.registerUser(user3);
		assertEquals("joni",ppApp.getUsers().get(0).getUserId());
		assertEquals("jhni",ppApp.getUsers().get(1).getUserId());
		assertEquals("jnni",ppApp.getUsers().get(2).getUserId());
	}
	
	@Test
	public void registerUsers_DuplicateIds_BothNamesShort() throws RegistrationException {
		thrown.expect(RegistrationException.class);
		thrown.expectMessage("Enter user ID manually.");
		
		ppApp.registerUser(user1);
		User user2 = new User("Jo", "Ni");
		ppApp.registerUser(user2);
	}

	
//	@Test //TODO
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

	/**
	 * Tests for the deregistration of users. 
	 */
	@Test
	public void deregisterUser() throws RegistrationException {
		ppApp.registerUser(user1);
		ppApp.deregisterUser(user1);
		assertEquals(0, ppApp.getUsers().size());
	}
	@Test
	public void invalidUserDeregistation_UserDoesNotExist() throws Exception {
		
		User u1 = new User("Andreas", "Ustrup");
		
		try {
			ppApp.deregisterUser(u1);
			fail("Expected RegistrationException");
		}
		catch(RegistrationException e) {
			assertEquals("Registration operation not allowed. " + "UserId does not exist.", e.getMessage());
//			assertEquals("Deregister user", e.getOperation());
		}
	}
	
	
}
