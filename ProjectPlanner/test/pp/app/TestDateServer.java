package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class TestDateServer {
	
	PpApp ppApp = new PpApp();
	
	
	/*
	 * successful creation of the dateServer and that it hold the current date.
	 */
	@Test
	public void creationOfDateServer() {
		LocalDate date = LocalDate.now();
		DateServer dateServer = new DateServer();
		assertEquals(date, dateServer.getDate());
	}
	
	@Test
	public void ppAppGetDate(){
		DateServer dateServer = new DateServer();
		ppApp.setDateServer(dateServer);
		LocalDate date = LocalDate.now();
		assertEquals(date, ppApp.getDate());
	}

}
