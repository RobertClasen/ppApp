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
		try {
			int selection = Integer.parseInt(input);
			Activity activity = view.getPpApp().getLoggedInUser().getActivities().get(selection);
			view.setController(new Controller_work(view, activity));
		} catch (RuntimeException e) {
			view.getScreen().appendText("\n" + "Not a valid selection." + "\n");
		}
		
		
	}

}
