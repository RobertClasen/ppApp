package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestActivity {
	private PpApp ppApp;
	private Project project1;
	private Activity activity1;
	
	private static final String VALID_TITLE = "Design";
	private static final String VALID_DESCRIPTION = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
			+ "Aenean commodo ligula eget dolor. Aenean m";
	private static final LocalDate VALID_DATE = LocalDate.of(2020, Month.JANUARY, 1);
	private static final int VALID_EST_TIME = 20;
	
	private static final String INVALID_TITLE_TOO_SHORT = "D";
	private static final String INVALID_TITLE_TOO_LONG = "Desiiiiiiiiiiiiiiiiiiiiign";
	private static final String INVALID_DESCRIPTION_TOO_LONG = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
			+ "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient "
			+ "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. "
			+ "Nulla consequat massa quis enim. Donec.";
	private static final LocalDate INVALID_DATE = LocalDate.of(2006, Month.JANUARY, 1);
	private static final int INVALID_EST_TIME = 0;
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		project1 = new Project(ppApp);
		activity1 = new Activity(ppApp);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
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
		activity1.setStartDate(INVALID_DATE);
	}
	
	@Test
	public void activity_ValidDate() throws Exception {
		activity1.setStartDate(VALID_DATE);
		assertEquals(VALID_DATE,activity1.getStartDate());
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

}
