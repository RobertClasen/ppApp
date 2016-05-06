package pp.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

public class TestRegisterAbsence {
	private PpApp ppApp;
	private Project project;
	private Activity activity;
	private User user1;
	private User user2;
	
	
	//slet måske dateserver setup
	@Before
	public void setup() {
		ppApp = new PpApp();
		project = new Project(ppApp);
		
		activity = new Activity(ppApp, project);
		ppApp.addProject(project);
		project.addActivity(activity);
		
		user1 = new User(ppApp);
		user1.setFirstName("Johnny");
		user1.setLastName("Nielsen");
		user2 = new User(ppApp);
		user2.setFirstName("Harry");
		user2.setLastName("Potter");
		
		ppApp.registerUser(user1);
		ppApp.registerUser(user2);
	}
	
	@Test
	public void userRegisterAbsence_oneDay() {
		LocalDate startDate = LocalDate.of(2016, Month.JULY, 22);
		LocalDate endDate = startDate;
		
		user1.registerAbsence(startDate, endDate);
		List<Absence> absence = user1.getAbsence();
		
		assertEquals(1, absence.size());
		assertEquals(startDate, absence.get(0).startDate);
		assertEquals(endDate, absence.get(0).endDate);
		assertEquals(1, absence.get(0).calcWork(startDate.minusDays(1L), startDate.plusDays(1L)));
	}
	
	@Test
	public void userRegisterAbsence_oneWeek() {
		LocalDate startDate = LocalDate.of(2016, Month.JULY, 22);
		LocalDate endDate = startDate.plusDays(6L);
		
		user1.registerAbsence(startDate, endDate);
		List<Absence> absence = user1.getAbsence();
		
		assertEquals(7, absence.get(0).calcWork(startDate.minusDays(1L), startDate.plusDays(10L)));
	}
	
	@Test
	public void userRegisterAbsence_twoMonths() {
		LocalDate startDate = LocalDate.of(2016, Month.JULY, 22);
		LocalDate endDate = startDate.plusMonths(2L);
		
		user1.registerAbsence(startDate, endDate);
		List<Absence> absence = user1.getAbsence();
		
		assertEquals(1, absence.get(0).calcWork(startDate.minusDays(1L), startDate.plusDays(1L)));
	}

}
