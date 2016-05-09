package pp.app;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kristian
 */

public class TestRegisterAbsence {
	private PpApp ppApp;
	private Project project;
	private Activity activity;
	private User user1;
	private static final LocalDate START_DATE = LocalDate.of(2016, Month.JULY, 22); //friday
	
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
	public void newAbsenceRegistration() throws Exception {
		LocalDate endDate = START_DATE;
		Absence a = new Absence(START_DATE, endDate);
		user1.registerAbsence(a);
		
		assertEquals(1, user1.absenceTime.size());
	}
	
	//Input data set A
	@Test
	public void userRegisterAbsence_oneDay() {
		LocalDate endDate = START_DATE;
		Absence a = new Absence(START_DATE, endDate);
		user1.registerAbsence(a);
		
		assertEquals(1, user1.absenceTime.size());
		assertEquals(START_DATE, user1.absenceTime.get(0).startDate);
		assertEquals(endDate, user1.absenceTime.get(0).endDate);
		assertEquals(1, user1.absenceTime.get(0).calcWorkDaysInTimePeriod(START_DATE, endDate));
	}
	
	//Input data set B
	@Test
	public void userRegisterAbsence_oneWeek() {
		LocalDate endDate = START_DATE.plusDays(6L);
		Absence a = new Absence(START_DATE, endDate);
		user1.registerAbsence(a);
		
		assertEquals(5, user1.getAbsence().get(0).calcWorkDaysInTimePeriod(START_DATE, endDate));
	}
	
	//Input data set C
	@Test
	public void userRegisterAbsence_twoMonths() {
		LocalDate endDate = START_DATE.plusMonths(2L);
		Absence a = new Absence(START_DATE, endDate);
		user1.registerAbsence(a);
		
		assertEquals(45, user1.absenceTime.get(0).calcWorkDaysInTimePeriod(START_DATE, endDate));
	}
	
	//Input data set D
	@Test
	public void userRegisterAbsence_weekendDay() {
		LocalDate startDate = START_DATE.plusDays(1);
		LocalDate endDate = startDate;
		Absence a = new Absence(startDate, endDate);
		user1.registerAbsence(a);
		
		assertEquals(0, user1.absenceTime.get(0).calcWorkDaysInTimePeriod(startDate, endDate));
	}
	
	
}
