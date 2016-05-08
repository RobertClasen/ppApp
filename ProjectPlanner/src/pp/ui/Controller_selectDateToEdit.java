package pp.ui;

import java.time.LocalDate;
import java.util.List;

import pp.app.Activity;
import pp.app.WorkSession;

public class Controller_selectDateToEdit extends Controller{
	Activity activity;
	List <WorkSession> workSessions;

	public Controller_selectDateToEdit(View view, Activity activity) {
		super(view);
		this.activity = activity;
		
		String output = "Choose date" + "\n";
		
		long i;
		for(i = 0; i < 7; i++) {
			String weekday = LocalDate.now().minusDays(i).getDayOfWeek().toString();
			String date = LocalDate.now().minusDays(i).toString();
			output += "\t" + i + ") " + weekday + " " + date  + "\n";
		}
		
		screen.appendText(output);

		view.getPpApp().getLoggedInUser().startWork(this.activity);
	}

	@Override
	public void processInput(String input) {
		try {
			int selection = Integer.parseInt(input);
			LocalDate date = LocalDate.now().minusDays(selection);
			workSessions = view.getPpApp().getLoggedInUser().searchWorkSessions_ByDate(date);
			workSessions = view.getPpApp().getLoggedInUser().searchWorkSessions_ByActivity(activity, workSessions);
			view.setController(new Controller_editTime(view, activity, date));
		} catch (NumberFormatException e) {
			screen.appendText("Must be an integer.");
		} catch (IndexOutOfBoundsException e) {
			screen.invalidInput();			
		}
		
	}
	

}
