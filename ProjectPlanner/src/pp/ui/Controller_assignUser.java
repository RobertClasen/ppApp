package pp.ui;

import java.util.List;

import pp.app.Activity;
import pp.app.User;

public class Controller_assignUser extends Controller {
	private Activity activity;
	private List<User> availabelUsers;
	
	public Controller_assignUser(View view, Activity activity) {
		super(view);
		this.activity = activity;
		
		String output = "Select user";

		int i = 0;
		availabelUsers = view.getPpApp().availableUsers(activity.getStartDate());
		for (User u : availabelUsers) {
			output += "\n" + "\t" + i + ") " + u.getFirstname() + " " + u.getLastname() + " (" + u.getUserId() + ")";
			i++;
		}
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		try {
			int selection = Integer.parseInt(input);
			User user = availabelUsers.get(selection);
			activity.assignUserToActivity(user);
			screen.appendText("User succesfully added to activity.");
			view.setController(new Controller_main(view));
		} catch (NumberFormatException e) {
			screen.appendText("Must be an integer.");
		} catch (IndexOutOfBoundsException e) {
			screen.appendText("There is only " + availabelUsers.size() + " available user(s).");			
		}
	}

}
