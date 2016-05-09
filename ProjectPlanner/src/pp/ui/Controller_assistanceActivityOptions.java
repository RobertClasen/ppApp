package pp.ui;

import pp.app.Activity;

/**
 * @author Robert
 */
public class Controller_assistanceActivityOptions extends Controller {
	private Activity activity;

	public Controller_assistanceActivityOptions(View view, Activity activity) {
		super(view);
		this.activity = activity;
		
		String output = "0) Begin work" + "\n" + 
						"1) Edit worked hours";
				
		screen.appendText(output);

		}
			

	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
			view.getPpApp().getLoggedInUser().startWork(this.activity);
			view.setController(new Controller_work(view,activity));
		} else if ("1".equals(input)) {
			view.setController(new Controller_selectDateToEdit(view, activity));					
		} else {
			screen.invalidInput();
		}
	}
}
