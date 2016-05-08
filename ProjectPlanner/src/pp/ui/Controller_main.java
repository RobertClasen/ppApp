package pp.ui;

import pp.app.Project;
import pp.app.ProjectException;

public class Controller_main extends Controller {
	
	public Controller_main(View view) {
		super(view);
		
		String output = "0) Your projects" + "\n" +
				  	    "1) Your activities" + "\n" +
				  	    "2) Manage project" + "\n" +
				  	    "3) Logout";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
			// TODO view.setController(new Controller_???(view));
			
		} else if ("1".equals(input)) {
			if (view.getPpApp().getLoggedInUser().getActivities().size() > 0) {
				view.setController(new Controller_activities(view));
			} else {
				screen.appendText("You are currently not assigned to any activities.");				
			}
			
		} else if ("2".equals(input)) {
			try {
				Project project = view.getPpApp().getLoggedInUser().isLeadingProject();
				view.setController(new Controller_leadingProject(view, project));
			} catch (ProjectException e) {
				screen.appendText("You are currently not leading any projects." + "\n" +
						"Would you like start a new one?");
				view.setController(new Controller_newProject(view));
			}
			
		} else if ("3".equals(input)) {
			view.getPpApp().setLoggedIn(false);
			screen.appendText("Logged out.");
			view.setController(new Controller_start(view));
			
		} else {
			screen.invalidInput();			
		}
	}

}
