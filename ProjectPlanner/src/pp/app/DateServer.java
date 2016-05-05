package pp.app;

import java.time.LocalDate;

public class DateServer {
	private LocalDate date;
	
	public DateServer(){
		date = LocalDate.now();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
