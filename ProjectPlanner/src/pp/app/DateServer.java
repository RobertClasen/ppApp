package pp.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class DateServer {

	/*
	 * Getters
	 */
	public LocalDate getDate() {
		return LocalDate.now();
	}

	public LocalTime getTime() {
		return LocalTime.now();
	}

	public LocalDateTime getDateTime() {
		return LocalDateTime.now();
	}	
}
