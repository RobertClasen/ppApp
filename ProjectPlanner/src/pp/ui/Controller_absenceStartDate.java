package pp.ui;

import java.time.LocalDate;

import pp.app.Absence;
import pp.app.InputException;
import pp.app.User;

public class Controller_absenceStartDate extends Controller {
	private Absence absence;
	
	public Controller_absenceStartDate(View view) {
		super(view);

		absence = new Absence();
		
		String output = "First day of absence? (YYYY-MM-DD)";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		int year, month, day;
		try {
			year = Integer.parseInt(input.substring(0, 4));
			month = Integer.parseInt(input.substring(5, 7));
			day = Integer.parseInt(input.substring(8, 10));
			absence.setStartDate(LocalDate.of(year, month, day));
			view.setController(new Controller_absenceEndDate(view, absence));
		} catch (StringIndexOutOfBoundsException e) {
			screen.appendText("Invalid format. Try again.");			
		} catch (InputException e) {
			screen.appendText("Date must be in the future. Try again.");			
		}
	}
	
}
