package pp.ui;

import pp.app.Activity;
import pp.app.InputException;
import pp.app.Project;

public class Controller_activityDescription extends Controller {
	private Activity activity;
	
	public Controller_activityDescription(View view, Activity activity) {
		super(view);
		this.activity = activity;
		
		String output = "Activity description?";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		try {
			activity.setDescription(input);
			view.setController(new Controller_activityStartDate(view, activity));
		} catch (InputException e) {
			screen.appendText("Invalid description length. Try again");			
		}
	}
}
