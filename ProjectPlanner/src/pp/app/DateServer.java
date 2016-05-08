package pp.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class DateServer {
//	private LocalDate date;
	
//	public DateServer(){
//		date = LocalDate.now();
//	}

	public LocalDate getDate() {
		return LocalDate.now();
	}

	public LocalTime getTime() {
		return LocalTime.now();
	}

	public LocalDateTime getDateTime() {
		return LocalDateTime.now();
	}

//	public void setDate(LocalDate date) {
//		this.date = date;
//	}
	

	
}
