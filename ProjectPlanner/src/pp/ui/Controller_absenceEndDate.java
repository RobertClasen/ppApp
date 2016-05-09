package pp.ui;

import java.time.LocalDate;

import pp.app.Absence;
import pp.app.InputException;

public class Controller_absenceEndDate extends Controller {
	private LocalDate startDate;
	
	public Controller_absenceEndDate(View view, LocalDate startDate) {
		super(view);
		this.startDate = startDate;
		
		String output = "Last day of absence? (YYYY-MM-DD)";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		int year, month, day;
		try {
			year = Integer.parseInt(input.substring(0, 4));
			month = Integer.parseInt(input.substring(5, 7));
			day = Integer.parseInt(input.substring(8, 10));
			LocalDate endDate = LocalDate.of(year, month, day);
			Absence absence = new Absence(startDate, endDate);
			view.getPpApp().getLoggedInUser().registerAbsence(absence);
			
			screen.appendText("Absence registered" + "\n"
					+ "\t" + startDate + " - " + endDate + "\n"
					+ "\t" + "For a total of " + absence.calcWorkDaysInTimePeriod(startDate, endDate)
					+ " workdays.");			
			view.setController(new Controller_main(view));
		} catch (StringIndexOutOfBoundsException e) {
			screen.appendText("Invalid format. Try again.");			
		} catch (InputException e) {
			screen.appendText("Date must be in the future. Try again.");			
		}
	}

}
