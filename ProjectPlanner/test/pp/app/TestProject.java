package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestProject {
	private PpApp ppApp;
	private Project project1;
	
	private final static String VALID_TITLE = "Newton";
	private final static String INVALID_TITLE_TOO_SHORT = "N";
	private final static String INVALID_TITLE_TOO_LONG = "Neeeeeeeeeeeeeeeeeeeeeeton"; // 26 chars
	
	private final static String VALID_DESCRIPTION = "The apple doesn't fall far from the tree";
	private final static String INVALID_DESCRIPTION_TOO_SHORT = "T";
	private final static String INVALID_DESCRIPTION_TOO_LONG = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
			+ "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis "
			+ "dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, "
			+ "pellentesque eu, pretium quis, s"; // 257 chars
	
	private final static LocalDate VALID_START_DATE = LocalDate.of(2016, Month.DECEMBER, 24);
	private final static LocalDate INVALID_START_DATE = LocalDate.of(2015, Month.DECEMBER, 24);
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		project1 = new Project(ppApp);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	@Test
	public void newProject_titleTooShort() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		project1.setTitle(INVALID_TITLE_TOO_SHORT);
	}
	@Test
	public void newProject_titleTooLong() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		project1.setTitle(INVALID_TITLE_TOO_LONG);
	}
	@Test
	public void newProject_descriptionTooShort() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");

		project1.setDescription(INVALID_DESCRIPTION_TOO_SHORT);
	}
	@Test
	public void newProject_descriptionTooLong() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		project1.setDescription(INVALID_DESCRIPTION_TOO_LONG);
	}

	@Test
	public void project_startDateInPast() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Date is in the past.");
		
		project1.setStartDate(INVALID_START_DATE);
	}

	@Test
	public void newProject_allInputsValid() {
		project1.setTitle(VALID_TITLE);
		project1.setDescription(VALID_DESCRIPTION);
		project1.setStartDate(VALID_START_DATE);
		
		ppApp.addProject(project1);
		assertEquals(1, ppApp.getProjects().size());
	}
	
}
