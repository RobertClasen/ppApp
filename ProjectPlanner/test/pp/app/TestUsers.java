package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestUsers {
	
	PpApp ppApp;
	
	@Before
	public void setUp() {
		ppApp = new PpApp();
	}
	
	@Test
	public void addUsers() {
		User u = new User("krra");
		ppApp.registerUser(u);
		assertEquals(1, ppApp.getUsers().size());
	}

}
