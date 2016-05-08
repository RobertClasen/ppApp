package pp.ui;

import pp.app.Activity;
import pp.app.InputException;
import pp.app.Project;
import pp.app.User;


	public class Controller_userFirstName extends Controller {
		private User user;
		
		public Controller_userFirstName(View view) {
			super(view);

			user = new User(view.getPpApp());
			
			String output = "User first name?";
			
			screen.appendText(output);
		}
		
		@Override
		public void processInput(String input) {
			try {
				user.setFirstName(input);
				view.setController(new Controller_userLastName(view, user));
			} catch (InputException e) {
				screen.appendText("Invalid first name. Try again.");			
			}
		}
}