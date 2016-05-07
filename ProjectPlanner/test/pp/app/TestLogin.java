package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestLogin {
	private static final String VALID_ID = "joni";
	private static final String INVALID_ID_UNKNOWN = "jona";
	
	PpApp ppApp;	

	@Before
	public void setUp() {
		ppApp = new PpApp();
		User user1 = new User(ppApp);
		user1.setFirstName("John");
		user1.setLastName("Nielsen");
		ppApp.registerUser(user1);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	// Input data set A
	@Test
	public void validUserLogin() throws LoginException {
		assertTrue(ppApp.logIn(VALID_ID));
	}
	
	// Input data set B
	@Test
	public void invalidUserLogin_UnknownId() throws LoginException {
		thrown.expect(LoginException.class);
		thrown.expectMessage("User ID does not exist");
		
		ppApp.logIn(INVALID_ID_UNKNOWN);
	}	
	
	

}
