package pp.app;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author Rasmus
 */
public class Absence {
	protected LocalDate startDate;
	protected LocalDate endDate;

	public Absence() {
		
	}
	
	public Absence(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int calcWorkDaysInTimePeriod(LocalDate firstDay, LocalDate lastDay) {
		LocalDate currentDate = firstDay;
		int workDays = 0;
		
		while(!currentDate.isAfter(lastDay) ){
			if(!(currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) && !(currentDate.getDayOfWeek() == (DayOfWeek.SUNDAY))) {
				workDays++;
			}
			currentDate = currentDate.plusDays(1L); 
		}
		return workDays;
	}
}
