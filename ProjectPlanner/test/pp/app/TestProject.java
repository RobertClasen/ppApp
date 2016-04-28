package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestProject {
	private PpApp ppApp;
	private Project project1; 
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		project1 = new Project();
		
	}

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
	
//	@Test
//	public void project_setExpectedStartTime() throws Exception {
//		project1.setExpectedStartTime()
//		
//	}
	
}
