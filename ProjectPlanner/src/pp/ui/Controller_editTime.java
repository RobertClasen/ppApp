package pp.ui;

import java.time.LocalDate;
import java.util.List;

import pp.app.Activity;
import pp.app.InputException;
import pp.app.WorkSession;

public class Controller_editTime extends Controller {
	private Activity activity;
	private LocalDate date;
	
	public Controller_editTime(View view, Activity activity, LocalDate date) {
		super(view);
		this.activity = activity;
		this.date = date;
		
		String output = "Input minutes";
		
		screen.appendText(output);
	}

	@Override
	public void processInput(String input) {
		try {
			int selection = Integer.parseInt(input);
			view.getPpApp().getLoggedInUser().editClockedTime(activity, selection, date);
			screen.appendText("Work hours successfully edited.");
			view.setController(new Controller_main(view));
		} catch (NumberFormatException e) {
			screen.appendText("Must be an integer.");
		} catch (IndexOutOfBoundsException e) {
			screen.invalidInput();			
		}
		
	}
	
	

}
