package pp.ui;

import pp.app.Activity;

public class Controller_activities extends Controller {
	
	public Controller_activities(View view) {
		super(view);
		String output = "Your activities";

		int i = 0;
		for (Activity a : view.getPpApp().getLoggedInUser().getActivities()) {
			output += "\n" + "\t" + i + ") " + a.getTitle();
			i++;
		}
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		try {
			int selection = Integer.parseInt(input);
			Activity activity = view.getPpApp().getLoggedInUser().getActivities().get(selection);
			view.setController(new Controller_activityOptions(view, activity));
		} catch (NumberFormatException e) {
			screen.appendText("Must be an integer.");
		} catch (IndexOutOfBoundsException e) {
			screen.appendText("You don't have that many activities.");			
		}
		
	}

}
