package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestProject {
	private PpApp ppApp;
	private Project project1;
	
	private final static String VALID_TITLE = "Newton";
	private final static String INVALID_TITLE_TOO_SHORT = "N";
	private final static String INVALID_TITLE_TOO_LONG = "Neeeeeeeeeeeeeeeeeeeeewton"; // 26 chars
	
	private final static String VALID_DESCRIPTION = "The apple doesn't fall far from the tree";
	private final static String INVALID_DESCRIPTION_TOO_SHORT = "T";
	private final static String INVALID_DESCRIPTION_TOO_LONG = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
			+ "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis "
			+ "dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, "
			+ "pellentesque eu, pretium quis, s"; // 257 chars
	
	private final static LocalDate VALID_START_DATE = LocalDate.of(2016, Month.DECEMBER, 24);
	private final static LocalDate INVALID_START_DATE = LocalDate.of(2015, Month.DECEMBER, 24);
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		project1 = new Project(ppApp);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	@Test
	public void newProject_titleTooShort() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		project1.setTitle(INVALID_TITLE_TOO_SHORT);
	}
	@Test
	public void newProject_titleTooLong() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		project1.setTitle(INVALID_TITLE_TOO_LONG);
	}
	@Test
	public void newProject_descriptionTooShort() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");

		project1.setDescription(INVALID_DESCRIPTION_TOO_SHORT);
	}
	@Test
	public void newProject_descriptionTooLong() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		project1.setDescription(INVALID_DESCRIPTION_TOO_LONG);
	}

	@Test
	public void project_startDateInPast() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Date is in the past.");
		
		project1.setStartDate(INVALID_START_DATE);
	}

	@Test
	public void newProject_allInputsValid() {
		project1.setTitle(VALID_TITLE);
		project1.setDescription(VALID_DESCRIPTION);
		project1.setStartDate(VALID_START_DATE);
		
		ppApp.addProject(project1);
		assertEquals(1, ppApp.getProjects().size());
	}
	
	@Test
	public void newProject_getRunningNumber() {
		project1.setTitle(VALID_TITLE);
		project1.setDescription(VALID_DESCRIPTION);
		project1.setStartDate(VALID_START_DATE);
		ppApp.addProject(project1);
		
		assertEquals("00012016", project1.getRunningNumber());
	}

	
	/**
	 * Tests for the automatic assigning of project leaders to newly created projects. 
	 */
	@Test
	public void addProject_oneUserOneProject_getProjectLeader() {
		makeAndRegisterUser("John", "Nielsen");
		ppApp.addProject(project1);
		assertEquals("joni", ppApp.getProjects().get(0).getProjectLeader().getUserId());
	}
	
	@Test
	public void addProject_threeUsersTwoProjects_getProjectLeaders() {
		makeAndRegisterUser("John", "Nielsen");
		makeAndRegisterUser("Andreas", "Ustrup");
		makeAndRegisterUser("Ulla", "Brit");
		ppApp.addProject(project1);
		ppApp.addProject(new Project(ppApp));
		assertEquals("joni", ppApp.getProjects().get(0).getProjectLeader().getUserId());
		assertEquals("anus", ppApp.getProjects().get(1).getProjectLeader().getUserId());
	}
	
	@Test
	public void endProject_projectIsSetInactive() {
		makeAndRegisterUser("John", "Nielsen");
		
		ppApp.addProject(project1);
		assertEquals(1, ppApp.getProjects().size());
		
		ppApp.endProject(project1);
		assertEquals(0, ppApp.getProjects().size());
	}
	
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
		ppApp.addProject(new Project(ppApp));
	}

	
	/**
	 *  Helper method.
	 *  Creates a new project, and sets the fields "title", "description" and "startDate"
	 *  as the given arguments, dictated by the tests tables.
	 */
	private Project makeProject(String title, String description, LocalDate startDate) {
		Project project = new Project(ppApp);
		project.setTitle(title);
		project.setDescription(description);
		project.setStartDate(startDate);
		return project;
	}
	
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
