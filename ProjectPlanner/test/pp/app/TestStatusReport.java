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
	private Project project1;
	private User user1;
	private StatusReport statusReport;

	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		project1 = new Project(ppApp);
		statusReport = new StatusReport(project1);
		user1 = makeUser("John", "Nielsen");
		ppApp.registerUser(user1);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	
	@Test
	public void generateStatusReport_title () {
			project1.setTitle("Newton");
			ppApp.addProject(project1);
			
			assertEquals("Newton", statusReport.title());		
	}
	
	@Test
	public void generateStatusReport_isNotProjectleader ()  {
		thrown.expect(ProjectException.class);
		thrown.expectMessage("User is not project leader.");
		
		ppApp.addProject(project1);
		User user2 = makeUser("Andreas", "Ustrup");
		ppApp.registerUser(user2);
		project1.generateStatusReport(user2);
	
	}
	
	@Test
	public void generateStatusReport_workProgress() throws Exception {
		ppApp.addProject(project1);
		project1.addActivity(makeActivity("Gravity", "Is it real?", LocalDate.of(2017, Month.JANUARY, 1), 10));

		DateServer dateServer = mock(DateServer.class);
		ppApp.setDateServer(dateServer);
		project1.getActivities().get(0).startWork();
		
		System.out.println(project1.getActivities().size());
//		System.out.println(LocalTime.now());
		System.out.println(ppApp.getDateServer().getTime());
		System.out.println(project1.getActivities().get(0).getStartTime());
		
		LocalTime newTime = dateServer.getTime().plusMinutes(479);
		when(dateServer.getTime()).thenReturn(project1.getActivities().get(0).getStartTime().plusMinutes(479));

		project1.getActivities().get(0).endWork();		
		
		String expected = "List of activities" + "\n" + "\t" + "Gravity - (8/10)" + "\n";
		assertEquals(expected, statusReport.listOfActivities(project1.getActivities()));
	}
	
	/**
	 * Helper methods. 
	 */
	private Activity makeActivity(String title, String description, LocalDate startDate, int estimatedTime) {
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
