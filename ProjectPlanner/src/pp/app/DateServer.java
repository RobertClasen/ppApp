package pp.app;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateServer {
//	private LocalDate date;
	
//	public DateServer(){
//		date = LocalDate.now();
//	}

	public LocalDate getDate() { return LocalDate.now(); }

	public LocalTime getTime() {
		System.out.println("foo");
		System.out.println(LocalTime.now());
		return LocalTime.now();
	}

//	public void setDate(LocalDate date) {
//		this.date = date;
//	}
	
	
}
