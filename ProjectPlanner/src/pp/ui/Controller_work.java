package pp.ui;

import pp.app.Activity;

public class Controller_work extends Controller {
	Activity activity;

	public Controller_work(View view, Activity activity) {
		super(view);
		this.activity = activity;
		
		String output = this.activity.toString() + "\n\n" + "Type Q to end work.";
		view.getScreen().appendText(output);

		view.getPpApp().getLoggedInUser().startWork(this.activity);
	}

	@Override
	public void processInput(String input) {
		if ("q".equals(input.toLowerCase())) {
			Long workedMinutes = view.getPpApp().getLoggedInUser().endWork();
			screen.appendText("Worked " + workedMinutes + " minutes " + "on activity.");
			view.setController(new Controller_main(view));
		} else {
			screen.invalidInput();
		}
		
	}

}