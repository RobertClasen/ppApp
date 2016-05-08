package pp.ui;

import java.time.LocalDate;

import pp.app.Activity;
import pp.app.InputException;

public class Controller_activityStartDate extends Controller {
	private Activity activity;
	
	public Controller_activityStartDate(View view, Activity activity) {
		super(view);
		this.activity = activity;
		
		String output = "Activity start date? (YYYY-MM-DD)";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		int year, month, day;
		try {
			year = Integer.parseInt(input.substring(0, 4));
			month = Integer.parseInt(input.substring(5, 7));
			day = Integer.parseInt(input.substring(8, 10));
			activity.setStartDate(LocalDate.of(year, month, day));
			view.setController(new Controller_activityEstHours(view, activity));
		} catch (StringIndexOutOfBoundsException e) {
			screen.appendText("Invalid format. Try again.");			
		} catch (InputException e) {
			screen.appendText("Date must be in the future. Try again.");			
		}
	}
	
}
