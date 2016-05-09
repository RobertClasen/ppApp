package pp.ui;

import javafx.application.Platform;

/**
 * @author Kristian
 */

public class Controller_start extends Controller {

	public Controller_start(View view) {
		super(view);
		String output = "0) Login" + "\n" +
					    "1) Exit";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
			view.setController(new Controller_login(view));
		} else if ("1".equals(input)) {
			Platform.exit();
		} else {
			screen.invalidInput();
		}
	}

}
