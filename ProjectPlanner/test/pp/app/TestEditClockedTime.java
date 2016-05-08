package pp.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

public class TestEditClockedTime {
	
	private PpApp ppApp;
	private DateServer dateServer;
	private Project project1;
	private User user1;
	private Activity activity1;
//	private User user2;
//	private User user3;
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		dateServer = mock(DateServer.class);
		ppApp.setDateServer(dateServer);
		
		project1 = new Project(ppApp);
		ppApp.addProject(project1);
		user1 = makeUser("John", "Nielsen");
		ppApp.registerUser(user1);
//		user2 = makeUser("John", "Olsen");
//		ppApp.registerUser(user2);
//		user3 = makeUser("John","Christiansen");
//		ppApp.registerUser(user3);
	}

	//Input data sæt A
	@Test
	public void editClockedTime_removeTime() {
		activity1 = makeActivity("Design", "Design af rejsekort", LocalDate.of(2017, Month.MARCH, 1), 70L);
		project1.addActivity(activity1);
		
		LocalTime startTime = LocalTime.of(10, 0);
		LocalDate startDate = LocalDate.now();
		when(dateServer.getTime()).thenReturn(startTime);
		when(dateServer.getDate()).thenReturn(startDate);
		user1.startWork(project1.getActivities().get(0));

		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(78L));
		user1.endWork();
		user1.editClockedTime(activity1,-8, ppApp.getDate());

		assertEquals(70, project1.getActivities().get(0).getClockedTime());
	}
	
	//Input data sæt B
	@Test
	public void editClocketTime_addTime() {
		activity1 = makeActivity("Design", "Design af rejsekort", LocalDate.of(2017, Month.MARCH, 1), 70L);
		project1.addActivity(activity1);
		
		LocalTime startTime = LocalTime.of(10, 0);
		LocalDate startDate = LocalDate.now();
		when(dateServer.getTime()).thenReturn(startTime);
		when(dateServer.getDate()).thenReturn(startDate);
		user1.startWork(project1.getActivities().get(0));

		when(dateServer.getTime()).thenReturn(startTime.plusMinutes(78L));
		user1.endWork();
		user1.editClockedTime(activity1, 2, ppApp.getDate());

		assertEquals(80, project1.getActivities().get(0).getClockedTime());
	}
	
	/*
	 * helper methods
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
