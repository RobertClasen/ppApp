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
	private User user2;
	private User user3;
	private StatusReport statusReport;

	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		dateServer = mock(DateServer.class);
		ppApp.setDateServer(dateServer);
		
		project1 = new Project(ppApp);
		project1.setTitle("Newton");
		project1.setStartDate(LocalDate.of(2017, Month.MAY, 11));
		user1 = makeUser("John", "Nielsen");
		ppApp.registerUser(user1);
		user2 = makeUser("Harry", "Potter");
		ppApp.registerUser(user2);
		user3 = makeUser("Ulla","Brit");
		ppApp.registerUser(user3);
		ppApp.addProject(project1);
		statusReport = new StatusReport(project1);
	}
	
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	
	
	@Test
	public void generateStatusReport_title () {
			project1.setTitle("Newton");			
			assertEquals("Newton", statusReport.title());		
	}
	
	@Test
	public void generateStatusReport_totalProgress() {
		project1.addActivity(makeActivity("Gravity", "Is it real?", LocalDate.of(2017, Month.JANUARY, 1), 10L));
		project1.addActivity(makeActivity("Apples", "Do they fall?", LocalDate.of(2017, Month.FEBRUARY, 1), 14L));
		project1.addActivity(makeActivity("Muffins", "What are they?", LocalDate.of(2017, Month.MARCH, 1), 38L));

		LocalTime startTime = LocalTime.of(10, 0);
		when(dateServer.getTime()).thenReturn(startTime);
		user1.startWork(project1.getActivities().get(0));
		user2.startWork(project1.getActivities().get(1));
		user3.startWork(project1.getActivities().get(2));

		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(479L));
		user1.endWork();
		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(721L));
		user2.endWork();
		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(61L));
		user3.endWork();

		assertEquals("(20/62)", statusReport.totalProgress());
		
	}
	
	@Test
	public void generateStatusReport_workProgress() throws Exception {
		project1.addActivity(makeActivity("Gravity", "Is it real?", LocalDate.of(2017, Month.JANUARY, 1), 10L));
		project1.addActivity(makeActivity("Apples", "Do they fall?", LocalDate.of(2017, Month.FEBRUARY, 1), 14L));
		project1.addActivity(makeActivity("Muffins", "What are they?", LocalDate.of(2017, Month.MARCH, 1), 38L));

		LocalTime startTime = LocalTime.of(10, 0);
		when(dateServer.getTime()).thenReturn(startTime);
		user1.startWork(project1.getActivities().get(0));
		user2.startWork(project1.getActivities().get(1));
		user3.startWork(project1.getActivities().get(2));

		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(479L));
		user1.endWork();
		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(721L));
		user2.endWork();
		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(61L));
		user3.endWork();
		
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
	
	@Test
	public void generateStatusReport_AssignedWorkers() {
		Activity activity1 = makeActivity("Gravity", "Is it real?", LocalDate.of(2017, Month.JANUARY, 1), 10L);
		Activity activity2 = makeActivity("Apples", "Do they fall?", LocalDate.of(2017, Month.FEBRUARY, 1), 14L);
		Activity activity3 = makeActivity("Muffins", "What are they?", LocalDate.of(2017, Month.MARCH, 1), 38L);
		project1.addActivity(activity1);
		project1.addActivity(activity2);
		project1.addActivity(activity3);
		
		activity1.assignUserToActivity(user1);
		activity2.assignUserToActivity(user2);
		activity3.assignUserToActivity(user3);
		
		
		String expected = "Assigned workers" + "\n" +
				  "\t" + "John Nielsen - joni" + "\n" +
				  "\t" + "Harry Potter - hapo" + "\n" +
				  "\t" + "Ulla Brit - ulbr" + "\n";
		assertEquals(expected, statusReport.assignedWorkers());
		
	}
	
	@Test
	public void generateStatusReport_StartDate() {
		assertEquals("2017-05-11", statusReport.startDate());
	}
	
	@Test
	public void generateStatusReport_ProjectLeader() throws Exception {
		
		assertEquals("John Nielsen", statusReport.projectLeader());
	}
	
	@Test
	public void generateStatusReport_fullReport() {
		Activity activity1 = makeActivity("Gravity", "Is it real?", LocalDate.of(2017, Month.JANUARY, 1), 10L);
		Activity activity2 = makeActivity("Apples", "Do they fall?", LocalDate.of(2017, Month.FEBRUARY, 1), 14L);
		Activity activity3 = makeActivity("Muffins", "What are they?", LocalDate.of(2017, Month.MARCH, 1), 38L);
		project1.addActivity(activity1);
		project1.addActivity(activity2);
		project1.addActivity(activity3);
		
		activity1.assignUserToActivity(user1);
		activity2.assignUserToActivity(user2);
		activity3.assignUserToActivity(user3);
	
		LocalTime startTime = LocalTime.of(10, 0);
		when(dateServer.getTime()).thenReturn(startTime);
		user1.startWork(project1.getActivities().get(0));
		user2.startWork(project1.getActivities().get(1));
		user3.startWork(project1.getActivities().get(2));

		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(479L));
		user1.endWork();
		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(721L));
		user2.endWork();
		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(61L));
		user3.endWork();
		
		String expected = "---- Project Status Report ----" + "\n\n" +
				"Title - Newton" + "\n" + 
				"Start Date - 2017-05-11" + "\n" +
				"Leader - John Nielsen" + "\n" + 
				"Total progress - (20/62)" + "\n\n" +
		 		"Assigned workers" + "\n" +
		 		"\t" + "John Nielsen - joni" + "\n" +
		 		"\t" + "Harry Potter - hapo" + "\n" +
		 		"\t" + "Ulla Brit - ulbr" + "\n\n" +
				"List of activities" + "\n" +
				"\t" + "Gravity - (7/10)" + "\n" +
				"\t" + "Apples - (12/14)" + "\n" +
				"\t" + "Muffins - (1/38)" + "\n\n" +
				"-------------------------------";
		assertEquals(expected, statusReport.generate());
		
		System.out.println(statusReport.generate());
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
