package pp.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Marco
 */

public class TestClockedTime {
	
	private PpApp ppApp;
	private DateServer dateServer;
	private Project project1;
	private User user1;
	private User user2;
	private User user3;
	private LocalDateTime startDateTime;
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		dateServer = mock(DateServer.class);
		ppApp.setDateServer(dateServer);
		
		project1 = new Project(ppApp);
		project1.setTitle("Rejsekortet");
		ppApp.addProject(project1);
		user1 = makeUser("John", "Nielsen");
		ppApp.registerUser(user1);
		user2 = makeUser("John", "Olsen");
		ppApp.registerUser(user2);
		user3 = makeUser("John","Christiansen");
		ppApp.registerUser(user3);
		
		LocalTime startTime = LocalTime.of(10, 0);
		LocalDate startDate = LocalDate.of(2017, Month.MARCH, 1);
		
		startDateTime = LocalDateTime.of(startDate, startTime);
		
		when(dateServer.getDateTime()).thenReturn(startDateTime);
	}
	
	//Input data set A
	@Test
	public void workOnActivity_LessThan15MinutesOfWorkDoesNotCount() {
		project1.addActivity(makeActivity("Design", "The apple doesn't fall far from the tree.", LocalDate.of(2017, Month.MARCH, 1), 38L));
		
		user1.startWork(project1.getActivities().get(0));
		
		when(dateServer.getDateTime()).thenReturn(startDateTime.plusMinutes(14L));
		user1.endWork();

		assertEquals(0, project1.getActivities().get(0).getClockedTime());
	}
	
	//Input data set B
	@Test
	public void workOnActivity_MoreThan15MinutesOfWorkDoesCount() {
		project1.addActivity(makeActivity("Design", "The apple doesn't fall far from the tree.", LocalDate.of(2017, Month.MARCH, 1), 38L));
		
		user1.startWork(project1.getActivities().get(0));
		
		when(dateServer.getDateTime()).thenReturn(startDateTime.plusMinutes(15L));
		user1.endWork();

		assertEquals(15, project1.getActivities().get(0).getClockedTime());
	}
	
	
	//Input data set C
	@Test
	public void workOnActivity_MoreThan24HoursOfWork() {
		project1.addActivity(makeActivity("Design", "The apple doesn't fall far from the tree.", LocalDate.of(2017, Month.MARCH, 1), 38L));
		
		user1.startWork(project1.getActivities().get(0));
		
		when(dateServer.getDateTime()).thenReturn(startDateTime.plusHours(25L));
		user1.endWork();

		assertEquals(1500, project1.getActivities().get(0).getClockedTime());
	}
	
	//Input data set D
	@Test
	public void multipleUsersWorkingOnActivity(){
		project1.addActivity(makeActivity("Design", "Design af rejsekort", LocalDate.of(2017, Month.MARCH, 1), 70L));
		
		when(dateServer.getDateTime()).thenReturn(startDateTime);
		user1.startWork(project1.getActivities().get(0));
		user2.startWork(project1.getActivities().get(0));
		user3.startWork(project1.getActivities().get(0));
		
		when(dateServer.getDateTime()).thenReturn(startDateTime.plusMinutes(20L));
		user1.endWork();
		user2.endWork();
		user3.endWork();
		
		assertEquals(60, project1.getActivities().get(0).clockedTime);
	}
	
	/*
	 * Helper methods
	 */
	
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
