package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class TestDateServer {
	
	PpApp ppApp = new PpApp();
	
	
	/*
	 * succesfull creation of the dateServer and that it hold the current date.
	 */
	@Test
	public void creationOfDateServer() {
		LocalDate date = LocalDate.now();
		DateServer dateServer = new DateServer();
		assertEquals(date, dateServer.getDate());
	}
	
	@Test
	public void ppAppGetDate(){
		LocalDate date = LocalDate.now();
		assertEquals(date, ppApp.getDate());
	}

}
