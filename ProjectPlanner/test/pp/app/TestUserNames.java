package pp.app;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Pelle
 */

public class TestUserNames {
	private PpApp ppApp;
	private User user1;
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
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
	
	//Input data set D
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
	//Input data set E
	@Test
	public void newUser_firstNameIsNotLettersOnly() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Name contains illegal character(s).");
		
		User user2 = new User(ppApp);
		user2.setFirstName("John9");
	}
	
	//Input data set F
	@Test
	public void newUser_lastNameIsNotLettersOnly() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Name contains illegal character(s).");
		
		User user2 = new User(ppApp);
		user2.setLastName("9elsen");
	}
	
	//Input data set G
	@Test
	public void newUser_firstNameConsistsOfTwoNames() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Name contains illegal character(s).");
		
		User user2 = new User(ppApp);
		user2.setFirstName("Ulla Brit");
	}
	
	//Input data set H
	@Test
	public void newUser_lastNameConsistsOfTwoNames() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Name contains illegal character(s).");
		
		User user2 = new User(ppApp);
		user2.setLastName("Brit Hansen");
	}
	
	//Input data set I
	@Test
	public void newUser_validNames() throws Exception {
		user1 = new User(ppApp);
		user1.setFirstName("John");
		user1.setLastName("Nielsen");
		
		assertEquals("John", user1.getFirstname());
		assertEquals("Nielsen", user1.getLastname());
	}

}
