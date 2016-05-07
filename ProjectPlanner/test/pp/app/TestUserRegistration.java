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
		user1 = new User(ppApp);
		user1.setFirstName("John");
		user1.setLastName("Nielsen");
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	/**
	 * Tests for validity of name lengths.
	 */
	
	//Input data set A 
	@Test
	public void newUser_tooLongFirstName() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		User user2 = new User(ppApp);
		user2.setFirstName("Jooooooooooooohn");
	}
	
	//Input data set B
	@Test
	public void newUser_tooLongLastName() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		User user2 = new User(ppApp);
		user2.setLastName("Niiiiiiiiiielsen");
	}
	
	//Input data set C
	@Test
	public void newUser_tooShortFirstName() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		User user2 = new User(ppApp);
		user2.setFirstName("J");
	}
	
	@Test
	public void newUser_tooShortLastName() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");

		User user2 = new User(ppApp);
		user2.setLastName("N");
	}
	
	/**
	 * Tests for illegal characters.
	 */
	//Input data set D
	@Test
	public void newUser_firstNameIsNotLettersOnly() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Name contains illegal character(s).");
		
		User user2 = new User(ppApp);
		user2.setFirstName("John9");
	}
	
	//Input data set E
	@Test
	public void newUser_lastNameIsNotLettersOnly() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Name contains illegal character(s).");
		
		User user2 = new User(ppApp);
		user2.setLastName("9elsen");
	}
	
	//Input data set F
	@Test
	public void newUser_firstNameConsistsOfTwoNames() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Name contains illegal character(s).");
		
		User user2 = new User(ppApp);
		user2.setFirstName("Ulla Brit");
	}
	
	//Input data set G
	@Test
	public void newUser_lastNameConsistsOfTwoNames() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Name contains illegal character(s).");
		
		User user2 = new User(ppApp);
		user2.setLastName("Brit Hansen");
	}
	
	
	/**
	 * Tests for the (de)registration of users. 
	 */
	
	//Input data set H
	@Test
	public void registerUser_validNames() {
		ppApp.registerUser(user1);
		assertEquals(1, ppApp.getUsers().size());
	}
	
	//Input data set I
	@Test
	public void registerUser_validNames_getNames() {
		ppApp.registerUser(user1);
		assertEquals("John", ppApp.getUsers().get(0).getFirstname());
		assertEquals("Nielsen", ppApp.getUsers().get(0).getLastname());
	}
	
	@Test
	public void deregisterUser_userIsRegistered() {
		ppApp.registerUser(user1);
		ppApp.deregisterUser(user1);
		assertEquals(0, ppApp.getUsers().size());
	}
	
	@Test
	public void deregisterUser_UserIsNotRegistered() {
		thrown.expect(RegistrationException.class);
		thrown.expectMessage("User is not registered.");
		
		User user2 = makeUser("Andreas", "Hansen");
		ppApp.deregisterUser(user2);
	}
	
	@Test
	public void deregisterUser_userIsDeleted() throws Exception {
		User user1 = makeUser("John", "Nielsen");		
		User user2 = makeUser("Andreas", "Hansen");
		User user3 = makeUser("Ulla", "Brit");
		ppApp.registerUser(user1);
		ppApp.registerUser(user2);
		ppApp.registerUser(user3);
		
		ppApp.addProject(new Project(ppApp));
		assertEquals("joni", ppApp.getProjects().get(0).getProjectLeader().getUserId());

		ppApp.deregisterUser(user2);
		
		ppApp.addProject(new Project(ppApp));
		assertEquals("ulbr", ppApp.getProjects().get(1).getProjectLeader().getUserId());
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
