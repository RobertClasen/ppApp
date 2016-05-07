package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestProject {
	private PpApp ppApp;
	private Project project1;
	
	private final static String VALID_TITLE = "Rejsekortet";
	private final static String INVALID_TITLE_TOO_SHORT = "R";
	private final static String INVALID_TITLE_TOO_LONG = "Reeeeeeeeeeeeeeeejsekortet"; // 26 chars
	
	private final static String VALID_DESCRIPTION = "The apple doesn't fall far from the tree";
	private final static String INVALID_DESCRIPTION_TOO_SHORT = "T";
	private final static String INVALID_DESCRIPTION_TOO_LONG = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
			+ "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis "
			+ "dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, "
			+ "pellentesque eu, pretium quis, s"; // 257 chars
	
	private final static LocalDate VALID_START_DATE = LocalDate.of(2016, Month.JUNE, 1);
	private final static LocalDate INVALID_START_DATE = LocalDate.of(2015, Month.DECEMBER, 24);
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		project1 = new Project(ppApp);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	//Input data set A
	@Test
	public void newProject_allInputsValid() {
		project1.setTitle(VALID_TITLE);
		project1.setDescription(VALID_DESCRIPTION);
		project1.setStartDate(VALID_START_DATE);
		
		ppApp.addProject(project1);
		assertEquals(1, ppApp.getProjects().size());
	}
	
	//Input data set A
	@Test
	public void newProject_getRunningNumber() {
		project1.setTitle(VALID_TITLE);
		project1.setDescription(VALID_DESCRIPTION);
		project1.setStartDate(VALID_START_DATE);
		ppApp.addProject(project1);
		
		assertEquals("00012016", project1.getRunningNumber());
	}
	
	//Input data set B
	@Test
	public void newProject_titleTooShort() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		project1.setTitle(INVALID_TITLE_TOO_SHORT);
	}
	
	//Input data set C
	@Test
	public void newProject_descriptionTooLong() {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		project1.setDescription(INVALID_DESCRIPTION_TOO_LONG);
	}
	//Input data set D
	@Test
	public void project_startDateInPast() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Date is in the past.");
		
		project1.setStartDate(INVALID_START_DATE);
	}
	
	//Input data set E
	@Test
	public void newProject_invalidDescriptionAndStartDate() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		project1.setTitle(VALID_TITLE);
		project1.setDescription(INVALID_DESCRIPTION_TOO_SHORT);
		project1.setStartDate(INVALID_START_DATE);
	}
	
	//Input data set F
	@Test
	public void newProject_invalidTitleAndStartDate() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
			
		project1.setTitle(INVALID_TITLE_TOO_SHORT);
		project1.setDescription(VALID_DESCRIPTION);
		project1.setStartDate(INVALID_START_DATE);
	}
	
	//Input data set G
	@Test
	public void newProject_invalidTitleAndDescription() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
				
		project1.setTitle(INVALID_TITLE_TOO_LONG);
		project1.setDescription(INVALID_DESCRIPTION_TOO_LONG);
		project1.setStartDate(VALID_START_DATE);
	}
	
	//Input data set H
	@Test
	public void newProject_allInvalidInputs() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
				
		project1.setTitle(INVALID_TITLE_TOO_SHORT);
		project1.setDescription(INVALID_DESCRIPTION_TOO_LONG);
		project1.setStartDate(INVALID_START_DATE);
	}
	
}
