package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Pelle
 */

public class TestCreateUserId {
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
	 * Tests for the generating of user ID's.
	 */
	
	//Input data sæt A
	@Test
	public void registerUser_validNames_getUserId() {
		ppApp.registerUser(user1);
		assertEquals("joni", ppApp.getUsers().get(0).getUserId());
	}
	
	//Input data sæt B
	@Test
	public void registerUsers_DuplicateIds() {
		ppApp.registerUser(user1);
		User user2 = makeUser("Jones", "Nissen");
		ppApp.registerUser(user2);
		assertEquals("joni", ppApp.getUsers().get(0).getUserId());
		assertEquals("jois", ppApp.getUsers().get(1).getUserId());
	}
	
	//Input data sæt C
	@Test
	public void registerUsers_DuplicateIds_ShortLastname1() {
		ppApp.registerUser(user1);
		User user2 = makeUser("Joan", "Ni");
		ppApp.registerUser(user2);
		assertEquals("joni",ppApp.getUsers().get(0).getUserId());
		assertEquals("jani",ppApp.getUsers().get(1).getUserId());
	}
	
	//Input data sæt D
	@Test 
	public void registerUsers_DuplicateIds_ShortLastname2() {
		User user2 = makeUser("Johnny", "Ni");
		User user3 = makeUser("Johnny", "Ni");
		ppApp.registerUser(user1);
		ppApp.registerUser(user2);
		ppApp.registerUser(user3);
		assertEquals("joni",ppApp.getUsers().get(0).getUserId());
		assertEquals("jhni",ppApp.getUsers().get(1).getUserId());
		assertEquals("jnni",ppApp.getUsers().get(2).getUserId());
	}
	
	//Input data sæt E
	@Test
	public void registerUsers_DuplicateIds_BothNamesShort() {
		thrown.expect(RegistrationException.class);
		thrown.expectMessage("Names are too short to generate a new user ID.");
		
		ppApp.registerUser(user1);
		User user2 = makeUser("Jo", "Ni");
		ppApp.registerUser(user2);
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
