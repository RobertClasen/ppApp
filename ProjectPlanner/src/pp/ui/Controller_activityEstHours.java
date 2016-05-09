package pp.ui;

import pp.app.Activity;
import pp.app.InputException;

/**
 * @author Rasmus
 */

public class Controller_activityEstHours extends Controller {
	private Activity activity;
	
	public Controller_activityEstHours(View view, Activity activity) {
		super(view);
		this.activity = activity;
		
		String output = "Activity estimated hours?";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		try {
			activity.setEstimatedTime(Long.parseLong(input));
			activity.getProject().addActivity(activity);
			screen.appendText("Activity succesfully created." + "\n\n" + activity.toString());
			view.setController(new Controller_main(view));
		} catch (StringIndexOutOfBoundsException e) {
			screen.appendText("Invalid format. Try again.");			
		} catch (InputException e) {
			screen.appendText("Date must be in the future. Try again.");			
		}
	}

}
