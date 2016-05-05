package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestStatusReport {
	private PpApp ppApp;
	private Project project1;
	private StatusReport statusReport;

	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		project1 = new Project(ppApp);
		statusReport = new StatusReport(project1);
	}

	
	@Test
	public void generateStatusReport_makeTitle () {
			project1.setTitle("Newton");
			ppApp.addProject(project1);
			
			
			assertEquals("Newton", statusReport.title());
			
	}
	

}
