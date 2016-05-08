package pp.ui;

import pp.app.InputException;
import pp.app.User;

	public class Controller_userLastName extends Controller {
		private User user;
		
		public Controller_userLastName(View view, User user) {
			super(view);
			this.user = user;
			
			String output = "User last name?";
			
			screen.appendText(output);
		}
		
		@Override
		public void processInput(String input) {
			try {
				user.setLastName(input);
				view.getPpApp().registerUser(user);
				screen.appendText("User succussfully registered.");
				view.setController(new Controller_main(view));
			} catch (InputException e) {
				screen.appendText("Invalid last name. Try again.");			
			}
		}
}
