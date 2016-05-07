package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.rules.ExpectedException;

public class TestAvailableUsers extends UsersForTesting {
	private Activity activity1;
	private Project project1;
	private final static LocalDate DATE = LocalDate.of(2016, Month.JUNE, 1);
	
	@Before
	public void setUp() throws RegistrationException {
		project1 = new Project(ppApp);
		activity1 = new Activity(ppApp, project1);
		ppApp.addProject(project1);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	//Data set A
	@Test
	public void allUsersAvailable() {
		List<User> availableUsers = ppApp.availableUsers(DATE);
		assertEquals(50, availableUsers.size());
	}
	
	//Data set B
	@Test
	public void noAvailableUsers_DueToActivities()throws Exception{
		thrown.expect(AvailabilityException.class);
		thrown.expectMessage("No available users at this date.");
		
		for (int i = 0; i<10; i++){
			Activity a = new Activity(ppApp, project1);
			a.setStartDate(DATE.plusDays(5));
			project1.addActivity(a);
			for (User u : testUsers) {
				a.assignUserToActivity(u);
			} 
		}
		ppApp.availableUsers(DATE);
	}
	
	@Test
	public void noAvailableUsers_DueToAbsence()throws Exception{
		thrown.expect(AvailabilityException.class);
		thrown.expectMessage("No available users at this date.");
		
		Absence ab = new Absence(DATE.minusMonths(3), DATE.plusMonths(3));
		for (User u : testUsers){
			u.registerAbsence(ab);
		}
		
		ppApp.availableUsers(DATE);
	}
	
	@Test
	public void someAvailableUsers_TypicalScenario(){
		List<User> unAvailable = new ArrayList<>();
		Absence ab = new Absence(DATE, DATE.plusWeeks(2));

		
		for (int i = 0; i<30; i++){
			unAvailable.add(testUsers.get(i));
			unAvailable.get(i).registerAbsence(ab);
		}
		
		for (int i = 0; i<8; i++){
			Activity a = new Activity(ppApp, project1);
			a.setStartDate(DATE.plusDays(5));
			project1.addActivity(a);
			for (User u : unAvailable) {
				a.assignUserToActivity(u);
			} 
		}

		List<User> availableUsers = ppApp.availableUsers(DATE);
		assertEquals(20, availableUsers.size());
	}
}
