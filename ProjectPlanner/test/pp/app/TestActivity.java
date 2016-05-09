package pp.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import pp.ui.StartUp;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Marco
 */

public class TestActivity {
	private PpApp ppApp;
	private DateServer dateServer;
	private StatusReport statusReport;
	private Project project1;
	private Activity activity1;
	private Activity activity2;
	private StartUp startUp;
	
	private static final String VALID_TITLE = "Design";
	private static final String VALID_DESCRIPTION = "The apple doesn't fall far from the tree.";
	private static final LocalDate VALID_START_DATE = LocalDate.of(2020, Month.JANUARY, 1);
	private static final Long VALID_EST_TIME = 20L;
	
	private static final String INVALID_TITLE_TOO_SHORT = "D";
	private static final String INVALID_TITLE_TOO_LONG = "Desiiiiiiiiiiiiiiiiiiiiign";
	private static final String INVALID_DESCRIPTION_TOO_LONG = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
			+ "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient "
			+ "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. "
			+ "Nulla consequat massa quis enim. Donec."; //300 char
	private static final LocalDate INVALID_START_DATE = LocalDate.of(2006, Month.JANUARY, 1);
	private static final Long INVALID_EST_TIME = 0L;
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		dateServer = mock(DateServer.class);
		ppApp.setDateServer(dateServer);
		project1 = new Project(ppApp);
		activity1 = new Activity(ppApp, project1);
		activity2 = new Activity(ppApp, project1);
		startUp = new StartUp(ppApp);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	/*
	 * Unit tests. Testing smaller parts of the functionality of activity. Leads up to the larger 
	 * functional test combining several functionalities. 
	 */
	
	@Test
	public void addActivityToProject() throws Exception {
		project1.addActivity(activity1);
		assertEquals(1, project1.getActivities().size());
	}
	
	@Test
	public void activity_setTitle() throws Exception {
		activity1.setTitle(VALID_TITLE);
		assertEquals(VALID_TITLE, activity1.getTitle());
	}
	
	@Test
	public void activity_setDescription() throws Exception {
		activity1.setDescription(VALID_DESCRIPTION);
		assertEquals(VALID_DESCRIPTION, activity1.getDescription());
	}
	
	@Test
	public void activity_tooShortTitle() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		activity1.setTitle(INVALID_TITLE_TOO_SHORT);
	}
	
	@Test
	public void activity_tooLongTitle() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		activity1.setTitle(INVALID_TITLE_TOO_LONG);
	}
	
	@Test
	public void activity_tooLongDescription() throws Exception{
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		activity1.setDescription(INVALID_DESCRIPTION_TOO_LONG);
	}
	
	@Test
	public void activity_DateInThePast() throws Exception{
		thrown.expect(InputException.class);
		thrown.expectMessage("Date is in the past.");
		activity1.setStartDate(INVALID_START_DATE);
	}
	
	@Test
	public void activity_ValidDate() throws Exception {
		activity1.setStartDate(VALID_START_DATE);
		assertEquals(VALID_START_DATE,activity1.getStartDate());
	}
	
	@Test
	public void activity_Valid_EstimatedTime() throws Exception {
		activity1.setEstimatedTime(VALID_EST_TIME);
		assertEquals(VALID_EST_TIME, activity1.getEstimatedTime());
	}
	
	@Test
	public void activity_isZero_EstimatedTime() throws Exception{
		thrown.expect(InputException.class);
		thrown.expectMessage("Input is not equal to or higher than zero.");
		activity1.setEstimatedTime(INVALID_EST_TIME);
	}
	
	@Test
	public void activity_toString() {
		makeAndAddActivity("Design", "Design af brugergrænseflade", LocalDate.of(2017, Month.JUNE, 12), 100L, project1);
		
		LocalDate startDate = LocalDate.of(2017, 6, 12);
		LocalTime startTime = LocalTime.of(10, 0);
				
		LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
		when(dateServer.getDateTime()).thenReturn(startDateTime);
		User user1 = makeAndRegisterUser("John", "Nielsen");
		user1.startWork(project1.getActivities().get(0));

		when(dateServer.getDateTime()).thenReturn(startDateTime.plusMinutes(479L)); // 7 hours
		
		user1.endWork();
		
		String expected =
				"Title:"+"\n\t" + "Design" + "\n\n" + 
				"Description:" + "\n\t" + "Design af brugergrænseflade" + "\n\n" +
				"Start date:" + "\n\t" + "2017-06-12" + "\n\n" + 
				"Clocked time:" + "\n\t" + "Completed hours - 7"
				+ "\n\t" + "Estimated hours - 100";
		assertEquals(expected, project1.getActivities().get(0).toString());
	}
	
	/**
	 * Following tests are the functional test as documented in the tables in the report
	 */
	
	//Input data set: A
	@Test
	public void activityCreation_validInputs() throws Exception {
		Activity activity1 = makeAndAddActivity(VALID_TITLE, VALID_DESCRIPTION, VALID_START_DATE, VALID_EST_TIME, project1);
		assertEquals(1,project1.getActivities().size());
	}
	
	//Input data set: B
	@Test
	public void activityCreation_onlyInvalidInputs() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		Activity activity1 = makeAndAddActivity(INVALID_TITLE_TOO_SHORT, INVALID_DESCRIPTION_TOO_LONG, INVALID_START_DATE, INVALID_EST_TIME, project1);
	}
	
	//Input data set: C
	@Test
	public void activityCreation_invalidInput() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		Activity activity1 = makeAndAddActivity(INVALID_TITLE_TOO_SHORT, VALID_DESCRIPTION, VALID_START_DATE, VALID_EST_TIME, project1);
	}
	//Input data set: D
	@Test
	public void activityCreation_invalidInput_DuplicateTitle() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid input.");
		Activity activity1 = makeAndAddActivity(VALID_TITLE, VALID_DESCRIPTION, VALID_START_DATE, VALID_EST_TIME, project1);
		Activity activity2 = makeAndAddActivity(VALID_TITLE, VALID_DESCRIPTION, VALID_START_DATE, VALID_EST_TIME, project1);
	}
	
	/**
	 * Helper methods
	 */
	private Activity makeAndAddActivity(String title, String description, LocalDate startDate, Long estimatedTime, Project p) {
		Activity activity = new Activity(ppApp, p);
		activity.setTitle(title);
		activity.setDescription(description);
		activity.setStartDate(startDate);
		activity.setEstimatedTime(estimatedTime);
		p.addActivity(activity);
		return activity;
	}
	
	private User makeAndRegisterUser(String firstName, String lastName) {
		User user = new User(ppApp);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		ppApp.registerUser(user);
		return user;
	}
}
