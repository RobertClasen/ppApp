package pp.ui;

import pp.app.Activity;
import pp.app.InputException;
import pp.app.Project;

/**
 * @author Rasmus
 */

public class Controller_activityTitle extends Controller {
	private Project project;
	private Activity activity; 
	
	public Controller_activityTitle(View view, Project project) {
		super(view);

		this.project = project;
		activity = new Activity(view.getPpApp(), project);
		
		String output = "Activity title?";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		try {
			activity.setTitle(input);
			view.setController(new Controller_activityDescription(view, activity));
		} catch (InputException e) {
			screen.appendText("Invalid title lenght. Try again.");			
		}
	}

}
