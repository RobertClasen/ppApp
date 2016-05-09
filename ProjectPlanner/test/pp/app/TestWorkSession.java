package pp.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Rasmus
 */

public class TestWorkSession {
	PpApp ppApp;
	DateServer dateServer;
	Project project1;
	Activity activity1;
	User user1;
	
	@Before
	public void setUp(){
		ppApp = new PpApp();
		dateServer = mock(DateServer.class);
		ppApp.setDateServer(dateServer);
		
		project1 = new Project(ppApp);
		ppApp.addProject(project1);
		activity1 = new Activity(ppApp, project1);
		project1.addActivity(activity1);
		
		user1 = new User(ppApp);
		user1.setFirstName("Johnny"); 	user1.setLastName("Nielsen");
		ppApp.registerUser(user1);
	}
	
	@Test
	public void addWorkSession() {
		WorkSession workSes = new WorkSession(LocalDate.now(), activity1, 20L);
		user1.addWorkSession(workSes);
		
		assertEquals(1, user1.workSessions.size());
		Long l = user1.workSessions.get(0).workTime;		Long ll = 20L;
		assertEquals(ll, l);
	}

	@Test
	public void getWorkSession_ByDate(){
		WorkSession workSes = new WorkSession(LocalDate.now().plusDays(1), activity1, 20L);
		user1.addWorkSession(workSes);
		
		assertEquals(0, user1.searchWorkSessions_ByDate(LocalDate.now()).size());
		assertEquals(1, user1.searchWorkSessions_ByDate(LocalDate.now().plusDays(1)).size());
	}
	
	@Test
	public void getWorkSession_ByActivity(){
		Activity activity2 = new Activity(ppApp, project1);
		project1.addActivity(activity2);
		
		WorkSession workSes = new WorkSession(LocalDate.now(), activity1, 20L);
		user1.addWorkSession(workSes);
		
		assertEquals(1, user1.searchWorkSessions_ByActivity(activity1, user1.workSessions).size());
		assertEquals(0, user1.searchWorkSessions_ByActivity(activity2, user1.workSessions).size());
	}
}
