package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestStatusReport {
	private PpApp ppApp;
	private DateServer dateServer;
	private Project project1;
	private User user1;
	private StatusReport statusReport;

	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		dateServer = mock(DateServer.class);
		ppApp.setDateServer(dateServer);
		
		project1 = new Project(ppApp);
		ppApp.addProject(project1);
		statusReport = new StatusReport(project1);
		user1 = makeUser("John", "Nielsen");
		ppApp.registerUser(user1);

	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	@Test
	public void workOnActivity_LessThan15MinutesOfWorkDoesNotCount() {
		project1.addActivity(makeActivity("Muffins", "What are they?", LocalDate.of(2017, Month.MARCH, 1), 38L));
		
		LocalTime startTime = LocalTime.of(10, 0);
		when(dateServer.getTime()).thenReturn(startTime);
		project1.getActivities().get(0).startWork();

		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(14L));
		project1.getActivities().get(0).endWork();

		assertEquals(0, project1.getActivities().get(0).getClockedTime());
	}
	
	@Test
	public void workOnActivity_MoreThan15MinutesOfWorkDoesCount() {
		project1.addActivity(makeActivity("Muffins", "What are they?", LocalDate.of(2017, Month.MARCH, 1), 38L));
		
		LocalTime startTime = LocalTime.of(10, 0);
		when(dateServer.getTime()).thenReturn(startTime);
		project1.getActivities().get(0).startWork();

		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(16L));
		project1.getActivities().get(0).endWork();

		assertEquals(16, project1.getActivities().get(0).getClockedTime());
	}

	@Test
	public void generateStatusReport_title () {
			project1.setTitle("Newton");
			ppApp.addProject(project1);
			
			assertEquals("Newton", statusReport.title());		
	}
	
	@Test
	public void generateStatusReport_workProgress() throws Exception {
		project1.addActivity(makeActivity("Gravity", "Is it real?", LocalDate.of(2017, Month.JANUARY, 1), 10L));
		project1.addActivity(makeActivity("Apples", "Do they fall?", LocalDate.of(2017, Month.FEBRUARY, 1), 14L));
		project1.addActivity(makeActivity("Muffins", "What are they?", LocalDate.of(2017, Month.MARCH, 1), 38L));

		LocalTime startTime = LocalTime.of(10, 0);
		when(dateServer.getTime()).thenReturn(startTime);
		project1.getActivities().get(0).startWork();
		project1.getActivities().get(1).startWork();
		project1.getActivities().get(2).startWork();

		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(479L));
		project1.getActivities().get(0).endWork();
		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(721L));
		project1.getActivities().get(1).endWork();
		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(61L));
		project1.getActivities().get(2).endWork();
		
		String expected = "List of activities" + "\n" +
						  "\t" + "Gravity - (7/10)" + "\n" +
						  "\t" + "Apples - (12/14)" + "\n" +
						  "\t" + "Muffins - (1/38)" + "\n";
		assertEquals(expected, statusReport.listOfActivities());
	}

	@Test
	public void generateStatusReport_userIsNotProjectLeader () {
		thrown.expect(ProjectException.class);
		thrown.expectMessage("User is not project leader.");
		
		ppApp.addProject(project1);
		User user2 = makeUser("Andreas", "Ustrup");
		ppApp.registerUser(user2);
		project1.generateStatusReport(user2);
	}
	
	/**
	 * Helper methods. 
	 */
	private Activity makeActivity(String title, String description, LocalDate startDate, Long estimatedTime) {
		Activity a = new Activity(ppApp, project1);
		a.setTitle(title);
		a.setDescription(description);
		a.setStartDate(startDate);
		a.setEstimatedTime(estimatedTime);
		return a;
	}
	
	private User makeUser(String firstName, String lastName) {
		User user = new User(ppApp);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return user;
	}

}
