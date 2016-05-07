package pp.ui;

import pp.app.LoginException;

public class Controller_login extends Controller {

	public Controller_login(View view) {
		super(view);
		String text = "Enter user ID" + "\n";
		view.getScreen().appendText(text);;
	}
	
	@Override
	public void processInput(String input) {
		try {
			view.getPpApp().logIn(input);
			view.setController(new Controller_main(view));
		} catch (LoginException e) {
			view.getScreen().appendText("\n" + "Invalid user ID" + "\n");
		}
	}
	
}
