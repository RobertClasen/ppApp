package pp.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestAssignProjectLeader {
	private PpApp ppApp;
	private User user1;
	private Project project1;
	private Project project2;
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		project1 = new Project(ppApp);
		project2 = new Project(ppApp);
		user1 = new User(ppApp);
		user1.setFirstName("John");
		user1.setLastName("Nielsen");
		project1.setTitle("Rejsekortet");
		project2.setTitle("Den elektroniske patient journal");
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Tests for the automatic assigning of project leaders to newly created projects. 
	 */
	
	//Input data set A
	@Test
	public void addProject_oneUserOneProject_getProjectLeader() {
		makeAndRegisterUser("John", "Nielsen");
		ppApp.addProject(project1);
		assertEquals("joni", ppApp.getProjects().get(0).getProjectLeader().getUserId());
	}
	
	//Input data set B
	@Test
	public void addProject_threeUsersTwoProjects_getProjectLeaders() {
		makeAndRegisterUser("John", "Nielsen");
		makeAndRegisterUser("Andreas", "Hansen");
		makeAndRegisterUser("Ulla", "Brit");
		ppApp.addProject(project1);
		ppApp.addProject(project2);
		assertEquals("joni", ppApp.getProjects().get(0).getProjectLeader().getUserId());
		assertEquals("anha", ppApp.getProjects().get(1).getProjectLeader().getUserId());
	}
	
	//Input data set C
	@Test
	public void endProject_projectIsSetInactive() {
		makeAndRegisterUser("John", "Nielsen");
		
		ppApp.addProject(project1);
		assertEquals(1, ppApp.getProjects().size());
		
		ppApp.endProject(project1);
		assertEquals(0, ppApp.getProjects().size());
	}
	
	//Input data set C
	@Test
	public void endProject_projectLeaderIsEnqueuedAgian() {
		makeAndRegisterUser("John", "Nielsen");
		ppApp.addProject(project1);
		assertEquals("joni", ppApp.getProjects().get(0).getProjectLeader().getUserId());
		ppApp.endProject(project1);
		ppApp.addProject(new Project(ppApp));
		assertEquals("joni", ppApp.getProjects().get(0).getProjectLeader().getUserId());
	}
	
	@Test
	public void addProject_maxLimitReached() {
		thrown.expect(ProjectException.class);
		thrown.expectMessage("Number of projects reached upper limit.");
		
		ppApp.setProjects(makeProjects(50));
		ppApp.addProject(project1);
	}
	
	/**
	 *  Helper method.
	 *  Creates a new project, and sets the fields "title", "description" and "startDate"
	 *  as the given arguments, dictated by the tests tables.
	 */
	
//	private Project makeProject(String title, String description, LocalDate startDate) {
//		Project project = new Project(ppApp);
//		project.setTitle(title);
//		project.setDescription(description);
//		project.setStartDate(startDate);
//		return project;
//	}
	
	/**
	 *  Helper method.
	 *  Creates and returns a list of i projects.
	 */
	private List<Project> makeProjects(int i) {
		List<Project> projects = new ArrayList<>();
		for (int j = 0; j < i; j++) {
			projects.add(new Project(ppApp));
		}
		return projects;
	}
	
	/**
	 *  Helper method.
	 *  Creates a new User object. Sets the firstName and lastName and then registers the user.
	 */
	private void makeAndRegisterUser(String firstName, String lastName) {
		User user = new User(ppApp);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		ppApp.registerUser(user);		
	}


}
