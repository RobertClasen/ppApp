package pp.ui;

import java.util.ArrayList;
import java.util.List;

import pp.app.Activity;
import pp.app.AvailabilityException;
import pp.app.User;

public class Controller_seekAssistance extends Controller {
	private Activity activity;
	private List<User> availableUsers;

	public Controller_seekAssistance(View view, Activity activity) {
		super(view);
		this.activity = activity;
		
		String output = "Select user";

		int i = 0;
		availableUsers = view.getPpApp().availableUsers(activity.getStartDate());
		List<User> usersToRemove = new ArrayList<>();
		for (int j = 0; j < availableUsers.size(); j++) {
			if (availableUsers.get(j).getActivities().contains(activity)) {
				usersToRemove.add(availableUsers.get(j));
			}
		}
		for (User u: usersToRemove){
			availableUsers.remove(u);
		}
		
		if (availableUsers.size() > 0) {
			for (User u : availableUsers) {
				output += "\n" + "\t" + i + ") " + u.getFirstname() + " " + u.getLastname() + " (" + u.getUserId() + ")";
				i++;
			}
			screen.appendText(output);
		} else {
			screen.appendText("No available users at this date.");
		}
	}
	
	@Override
	public void processInput(String input) {
		try {
			int selection = Integer.parseInt(input);
			User user = availableUsers.get(selection);
			view.getPpApp().getLoggedInUser().seekAssistance(user, activity);
//			activity.assignUserToActivity(user);
			screen.appendText("User is now assisting on activity.");
			view.setController(new Controller_main(view));
		} catch (NumberFormatException e) {
			screen.appendText("Must be an integer.");
		} catch (IndexOutOfBoundsException e) {
			screen.appendText("There is only " + availableUsers.size() + " available user(s).");			
		}
	}
}
