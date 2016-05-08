package pp.ui;

import java.time.LocalDate;

import pp.app.Absence;
import pp.app.InputException;

public class Controller_absenceEndDate extends Controller {
	private Absence absence;
	
	public Controller_absenceEndDate(View view, Absence absence) {
		super(view);

		this.absence = absence;
		
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
			absence.setEndDate(LocalDate.of(year, month, day));
			view.getPpApp().getLoggedInUser().registerAbsence(absence);
			
			LocalDate start = absence.getStartDate();
			LocalDate end = absence.getEndDate();
			
			screen.appendText("Absence registered" + "\n"
					+ "\t" + start + " - " + end + "\n"
					+ "\t" + "For a total of " + absence.calcWorkDaysInTimePeriod(start, end)
					+ " workdays.");			
			view.setController(new Controller_main(view));
		} catch (StringIndexOutOfBoundsException e) {
			screen.appendText("Invalid format. Try again.");			
		} catch (InputException e) {
			screen.appendText("Date must be in the future. Try again.");			
		}
	}

}
