package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestUserRegistration {
	private PpApp ppApp;
	private User user1; 
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		user1 = new User("John", "Nielsen");
		
	}
	
//	@Test
//	public void registerUsers_tooLongFirstName() throws RegistrationException {
//		User user2 = new User("Jooooooooooooohn", "Nielsen");
//		
//		System.out.println("foo bar");
//		
//		thrown.expect(RegistrationException.class);
//		thrown.expectMessage("Invalid name length");
//		
//		
//		ppApp.registerUser(user2);
//		
////		try {
////			fail("Expected RegistrationException");
////		}
////		catch(RegistrationException e) {
////			System.out.println("foo bar");
////			assertEquals("Invalid name length", e.getMessage());
////			assertEquals("User registration", e.getOperation());
////			
////		}
//	}
//	
//	@Test(expected = RegistrationException.class)
//	public void registerUsers_tooLongLastName() throws RegistrationException {
//		User user2 = new User("John", "Niiiiiiiiiielsen");
//		try {
//			ppApp.registerUser(user2);
//			fail("Expected RegistrationException");
//		}
//		catch(RegistrationException e) {
//			assertEquals("Invalid name length", e.getMessage());
//			assertEquals("User registration", e.getOperation());
//			
//		}
//	}
//	
//	@Test(expected = RegistrationException.class)
//	public void registerUsers_tooShortFirstName() throws RegistrationException {
//		User user2 = new User("J", "Nielsen");
//		try {
//			ppApp.registerUser(user2);
//			fail("Expected RegistrationException");
//		}
//		catch(RegistrationException e) {
//			assertEquals("Invalid name length", e.getMessage());
//			assertEquals("User registration", e.getOperation());
//			
//		}
//	}
//	
//	@Test
//	public void registerUsers_tooShortLastName() throws RegistrationException {
//		User user2 = new User("John", "N");
//		try {
//			ppApp.registerUser(user2);
//			fail("Expected RegistrationException");
//		}
//		catch(RegistrationException e) {
//			assertEquals("Invalid name length", e.getMessage());
//			assertEquals("User registration", e.getOperation());
//			
//		}
//	}
//	
	@Test
	public void registerUsers() throws Exception {
		ppApp.registerUser(user1);
		assertEquals(1, ppApp.getUsers().size());
	}
	
	@Test
	public void registerUsers_checkNames() throws Exception {
		assertEquals("John", user1.getFirstname());
		assertEquals("Nielsen", user1.getLastname());
	}
	
	@Test
	public void registerUsers_checkUserId() throws Exception {
		ppApp.registerUser(user1);
		assertEquals("joni", ppApp.getUsers().get(0).getUserId());
	}
	
	@Test
	public void registerUsers_checkUserId_DuplicateIds() throws Exception {
		ppApp.registerUser(user1);
		User user2 = new User("Jones", "Nissen");
		ppApp.registerUser(user2);
		assertEquals("joni", ppApp.getUsers().get(0).getUserId());
		assertEquals("jois", ppApp.getUsers().get(1).getUserId());
	}
	
	@Test
	public void registerUsers_DuplicateIds_ShortLastname1() throws Exception {
		ppApp.registerUser(user1);
		User user3 = new User("Joan", "Ni");
		ppApp.registerUser(user3);
		assertEquals("joni",ppApp.getUsers().get(0).getUserId());
		assertEquals("jani",ppApp.getUsers().get(1).getUserId());
	}
	
	@Test 
	public void registerUsers_DuplicateIds_ShortLastname2() throws Exception {
		User user2 = new User("Johnny", "Ni");
		User user3 = new User("Johnny", "Ni");
		ppApp.registerUser(user1);
		ppApp.registerUser(user2);
		ppApp.registerUser(user3);
		assertEquals("joni",ppApp.getUsers().get(0).getUserId());
		assertEquals("jhni",ppApp.getUsers().get(1).getUserId());
		assertEquals("jnni",ppApp.getUsers().get(2).getUserId());
	}
	
//	@Test
//	public void registerUsers_DuplicateIds_ShortFirstname() throws Exception {
//		ppApp.registerUser(user1);
//		User user4 = new User("Jo", "Ni");
//		ppApp.registerUser(user4);
//		assertEquals("joni",ppApp.getUsers().get(0).getUserId());
//		assertEquals("jeni",ppApp.getUsers().get(1).getUserId());
//	}

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
