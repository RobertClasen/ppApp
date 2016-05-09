package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

/**
 * @author Marco
 */

public class TestDateServer {
	
	PpApp ppApp = new PpApp();
	
	
	/*
	 * successful creation of the dateServer and that it hold the current date.
	 */
	@Test
	public void dateServer_getDate() {
		LocalDate date = LocalDate.now();
		DateServer dateServer = new DateServer();
		assertEquals(date, dateServer.getDate());
	}
	
	@Test
	public void dateServer_getTime() {
		LocalTime time = LocalTime.now();
		DateServer dateServer = new DateServer();
		assertEquals(time.getSecond(), dateServer.getTime().getSecond());
	}
	
	@Test
	public void dateServer_getDateTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateServer dateServer = new DateServer();
		assertEquals(dateTime.getSecond(), dateServer.getDateTime().getSecond());
		assertEquals(dateTime.getDayOfYear(), dateServer.getDateTime().getDayOfYear());
	}
	
	@Test
	public void ppApp_setDateServer_GetDate(){
		DateServer dateServer = new DateServer();
		ppApp.setDateServer(dateServer);
		LocalDate date = LocalDate.now();
		assertEquals(date, ppApp.getDate());
	}

}
