package pp.ui;

import pp.app.LoginException;

public class Controller_login extends Controller {

	public Controller_login(View view) {
		super(view);
		String text =  "Enter user ID";
		screen.appendText(text);;
	}
	
	@Override
	public void processInput(String input) {
		try {
			view.getPpApp().logIn(input);
			user = view.getPpApp().getLoggedInUser();
			String welcome = "Welcome " + view.getPpApp().getLoggedInUser().getFirstname();
			screen.appendText(welcome);
			view.setController(new Controller_main(view));
			
		} catch (LoginException e) {
			screen.appendText("Invalid user ID");
		}
	}
	
}
