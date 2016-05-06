package pp.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

public class TestClockedTime {
	
	private PpApp ppApp;
	private DateServer dateServer;
	private Project project1;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		dateServer = mock(DateServer.class);
		ppApp.setDateServer(dateServer);
		
		project1 = new Project(ppApp);
		ppApp.addProject(project1);
		user1 = makeUser("John", "Nielsen");
		ppApp.registerUser(user1);
		user2 = makeUser("John", "Olsen");
		ppApp.registerUser(user2);
		user3 = makeUser("John","Christiansen");
		ppApp.registerUser(user3);
	}
	

	@Test
	public void workOnActivity_LessThan15MinutesOfWorkDoesNotCount() {
		project1.addActivity(makeActivity("Muffins", "What are they?", LocalDate.of(2017, Month.MARCH, 1), 38L));
		
		LocalTime startTime = LocalTime.of(10, 0);
		when(dateServer.getTime()).thenReturn(startTime);
		user1.startWork(project1.getActivities().get(0));

		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(14L));
		user1.endWork();

		assertEquals(0, project1.getActivities().get(0).getClockedTime());
	}
	
	@Test
	public void workOnActivity_MoreThan15MinutesOfWorkDoesCount() {
		project1.addActivity(makeActivity("Muffins", "What are they?", LocalDate.of(2017, Month.MARCH, 1), 38L));
		
		LocalTime startTime = LocalTime.of(10, 0);
		when(dateServer.getTime()).thenReturn(startTime);
		user1.startWork(project1.getActivities().get(0));

		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(15L));
		user1.endWork();

		assertEquals(15, project1.getActivities().get(0).getClockedTime());
	}

	@Test
	public void multipleUsersWorkingOnActivity(){
		project1.addActivity(makeActivity("Design", "Design af rejsekort", LocalDate.of(2017, Month.MARCH, 1), 70L));
		
		LocalTime startTime = LocalTime.of(10, 0);
		when(dateServer.getTime()).thenReturn(startTime);
		user1.startWork(project1.getActivities().get(0));
		user2.startWork(project1.getActivities().get(0));
		user3.startWork(project1.getActivities().get(0));
		
		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(20L));
		user1.endWork();
		user2.endWork();
		user3.endWork();
		
		assertEquals(60, project1.getActivities().get(0).clockedTime);
	}

	private User makeUser(String firstName, String lastName) {
		User user = new User(ppApp);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return user;
	}
	
	private Activity makeActivity(String title, String description, LocalDate startDate, Long estimatedTime) {
		Activity a = new Activity(ppApp, project1);
		a.setTitle(title);
		a.setDescription(description);
		a.setStartDate(startDate);
		a.setEstimatedTime(estimatedTime);
		return a;
	}
}
