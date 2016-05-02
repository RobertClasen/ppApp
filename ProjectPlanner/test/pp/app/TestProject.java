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
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		project1 = new Project();
		
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	@Test
	public void createProject() throws Exception {
		ppApp.addProject(project1);
		assertEquals(1, ppApp.getProjects().size());
	}
	
	@Test
	public void project_setTitle() throws Exception {
		project1.setTitle("Rejsekortet");
		assertEquals("Rejsekortet", project1.getTitle());
	}
	
	@Test
	public void project_setDescription() throws Exception {
		project1.setDescription("Lorem ipsum dolor sit amet, aliquam fringilla, vivamus justo suspendisse, "
				+ "morbi mattis et, donec proin platea in mus. Pharetra nonummy per aliquam fusce vitae, "
				+ "eleifend duis cras dolores vestibulum, sollicitudin sit aenean sollicitudin eu ligula orci.");
		assertEquals("Lorem ipsum dolor sit amet, aliquam fringilla, vivamus justo suspendisse, "
				+ "morbi mattis et, donec proin platea in mus. Pharetra nonummy per aliquam fusce vitae, "
				+ "eleifend duis cras dolores vestibulum, sollicitudin sit aenean sollicitudin eu ligula orci.", project1.getDescription());
	}
	
	@Test
	public void project_tooShortTitle() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		ppApp.getInputValidation().stringLength("R",2,25);
	}
	
	@Test
	public void project_tooLongTitle() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		
		ppApp.getInputValidation().stringLength("Rejsekortttttttttttttttttt",2,25);
		
	}
	
	@Test
	public void project_startDateInPast() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Date is in the past.");
		
		ppApp.getInputValidation().dateIsNotInPast(LocalDate.of(2016, Month.JANUARY, 1));
		
	}
	
	
//	@Test
//	public void project_setExpectedStartTime() throws Exception {
//		project1.setExpectedStartTime()
//		
//	}
	
}