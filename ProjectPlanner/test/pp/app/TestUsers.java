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
		u = new User("krra");
	}
	
	@Test
	public void addUsers() {
		ppApp.registerUser(u);
		assertEquals(1, ppApp.getUsers().size());
	}

	@Test
	public void removeUser() {
		ppApp.registerUser(u);
		ppApp.deregisterUser(u);
		assertEquals(0, ppApp.getUsers().size());
	}
	
	
}
