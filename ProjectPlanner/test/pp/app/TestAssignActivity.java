package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestAssignActivity extends UsersForTesting {
	private Activity activity1;
	private Project project1;
	
	@Before
	public void setUp() throws RegistrationException {
		project1 = new Project(ppApp);
		activity1 = new Activity(ppApp, project1);
		ppApp.addProject(project1);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	//Extra test to show that UsersForTesting works properly 
	@Test
	public void correctName() {
		ppApp.registerUser(user26);
		assertEquals("Harry Potter", user26.getFirstname() + " " + user26.getLastname());
	}
	
	//Input data set: A
	@Test
	public void assignOneUserToActivity() throws Exception {
		activity1.assignUserToActivity(user1);
		assertEquals("joni", activity1.searchUser("joni").getUserId());
		assertEquals(1, user1.getActivities().size());
	}
	
	//Input data set: B
	@Test
	public void assignFiveUsersToActivity() throws Exception {
		assignUsers(testUsers, activity1, 5);
		assertEquals(5, activity1.getUsers().size());
	}
	
	//Input data set: C
	@Test
	public void assignAllUsersToActivity() throws Exception {
		assignUsers(testUsers, activity1, testUsers.size());
		assertEquals(50, activity1.getUsers().size());
	}
	
	
	/**
	 *  Helper method.
	 *  Assigns a requested number of users ('fictional' users from the class UsersForTesting) to an activity
	 */
	
	private void assignUsers (List<User> users, Activity activity, int numberOfUsers) {
		for(int i = 0; i < numberOfUsers; i++){
			activity1.assignUserToActivity(users.get(i));
		}
	}


}
