package pp.ui;

import pp.app.Activity;

public class Controller_activities extends Controller {
	
	public Controller_activities(View view) {
		super(view);
		String text = "\n" + 
					  "Your activities" + "\n";

		int i = 0;
		for (Activity a : view.getPpApp().getLoggedInUser().getActivities()) {
			text += "\t" + i + ") " + a.getTitle() + "\n";
			i++;
		}
		
		view.getScreen().appendText(text);
	}
	
	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
//			view.setController(new Controller_???(view));
		} else if ("1".equals(input)) {
//			view.setController(new Controller_???(view));
		} else if ("2".equals(input)) {
//			view.setController(new Controller_???(view));
		} else if ("3".equals(input)) {
			//TODO ppApp.logout
//			view.setController(new Controller_???(view));
		}
	}

}
