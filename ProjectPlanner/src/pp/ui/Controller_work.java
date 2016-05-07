package pp.ui;

import pp.app.Activity;

public class Controller_work extends Controller {
	Activity activity;

	public Controller_work(View view, Activity activity) {
		super(view);
		this.activity = activity;
		
		String text = activity.toString() + "\n" + "Type Q to end work.";
		view.getScreen().appendText("\n" + text + "\n");

//		view.getPpApp().getLoggedInUser().startWork(this.activity);
	}

	@Override
	public void processInput(String input) {
		if ("q".equals(input.toLowerCase())) {
//			Long workedMinutes = view.getPpApp().getLoggedInUser().endWork();
//			view.getScreen().appendText("\n" + "Worked " + workedMinutes + " on activity." + "\n");
		} else {
//			view.getScreen().appendText("\n" + "invalid input." + "\n");
		}
		
	}

}