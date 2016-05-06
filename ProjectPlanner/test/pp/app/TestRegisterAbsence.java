package pp.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.awt.List;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

public class TestRegisterAbsence {
	private PpApp ppApp;
	private Project project;
	private Activity activity;
	private User user1;
	
	//slet m�ske dateserver setup
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
		
		ppApp.registerUser(user1);
	}
	
	@Test
	public void userRegisterAbsence_oneDay() {
		LocalDate startDate = LocalDate.of(2016, Month.JULY, 22);
		LocalDate endDate = startDate;
		
		Absence a = new Absence(startDate, endDate);
		user1.registerAbsence(a);
		
		assertEquals(1, user1.absenceTime.size());
		assertEquals(startDate, user1.absenceTime.get(0).startDate);
		assertEquals(endDate, user1.absenceTime.get(0).endDate);
		assertEquals(1, user1.absenceTime.get(0).calcWorkDaysInTimePeriod(startDate, endDate));
	}
	
	@Test
	public void userRegisterAbsence_oneWeek() {
		LocalDate startDate = LocalDate.of(2016, Month.JULY, 22);
		LocalDate endDate = startDate.plusDays(6L);
		
		Absence a = new Absence(startDate, endDate);
		
		user1.registerAbsence(a);
		
		assertEquals(5, user1.getAbsence().get(0).calcWorkDaysInTimePeriod(startDate, endDate));
	}
	
	@Test
	public void userRegisterAbsence_twoMonths() {
		LocalDate startDate = LocalDate.of(2016, Month.JULY, 22);
		LocalDate endDate = startDate.plusMonths(2L);
		
		Absence a = new Absence(startDate, endDate);
		
		user1.registerAbsence(a);
		
		System.out.println(user1.isAvailable(startDate.plusDays(29)));
		
		assertEquals(45, user1.absenceTime.get(0).calcWorkDaysInTimePeriod(startDate, endDate));
	}

}
