package pp.ui;

import pp.app.Activity;

/**
 * @author Kristian
 */

public class Controller_activities extends Controller {
	private int breakPoint;
	
	public Controller_activities(View view) {
		super(view);
		String output = "Your activities";

		int i = 0;
		for (Activity a : view.getPpApp().getLoggedInUser().getActivities()) {
			output += "\n" + "\t" + i + ") " + a.getTitle();
			i++;
		}
		breakPoint = i;
		
		output += "\n\n" + "Assistance activities";

		for (Activity a : view.getPpApp().getLoggedInUser().getAssistanceActivities()) {
			output += "\n" + "\t" + i + ") " + a.getTitle();
			i++;
		}
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		try {
			int selection = Integer.parseInt(input);
			if (selection < breakPoint && selection != breakPoint) {
				Activity activity = view.getPpApp().getLoggedInUser().getActivities().get(selection);
				view.setController(new Controller_activityOptions(view, activity));
			} else {
				Activity activity = view.getPpApp().getLoggedInUser().getAssistanceActivities().get(selection-breakPoint);
				view.setController(new Controller_assistanceActivityOptions(view, activity));
			}
		} catch (NumberFormatException e) {
			screen.appendText("Must be an integer.");
		} catch (IndexOutOfBoundsException e) {
			screen.appendText("You don't have that many activities.");			
		}
		
	}

}
