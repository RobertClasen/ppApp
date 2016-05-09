package pp.ui;

import pp.app.Activity;
import pp.app.Project;

/**
 * @author Pelle
 */

public class Controller_selectActivity extends Controller {
	Project project;
	
	public Controller_selectActivity(View view, Project project) {
		super(view);
		this.project = project;
		
		String output = "Select activity";

		int i = 0;
		for (Activity a : project.getActivities()) {
			output += "\n" + "\t" + i + ") " + a.getTitle();
			i++;
		}
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		try {
			int selection = Integer.parseInt(input);
			Activity activity = project.getActivities().get(selection);
			view.setController(new Controller_assignUser(view, activity));
		} catch (NumberFormatException e) {
			screen.appendText("Must be an integer.");
		} catch (IndexOutOfBoundsException e) {
			screen.appendText("You don't have that many activities.");			
		}
	}
}
