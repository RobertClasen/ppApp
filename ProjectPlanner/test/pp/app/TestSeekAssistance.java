package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSeekAssistance {
	private PpApp ppApp;
	private Project project;
	private Activity activity;
	private User user1;
	private User user2;
	
	@Before
	public void setUp(){
		ppApp = new PpApp();
		project = new Project(ppApp);
		activity = new Activity(ppApp, project);
		ppApp.addProject(project);
		project.addActivity(activity);
		
		user1 = new User(ppApp);
		user1.setFirstName("Johnny");
		user1.setLastName("Nielsen");
		user2 = new User(ppApp);
		user2.setFirstName("Harry");
		user2.setLastName("Potter");
		
		ppApp.registerUser(user1);
		ppApp.registerUser(user2);
		
		activity.assignUserToActivity(user1);
	}
	
	//Input data set A
	@Test
	public void seekAssistance() {
		user1.seekAssistance(user2, activity);
		assertEquals(1, user2.assistanceActivities.size());
	}

}
